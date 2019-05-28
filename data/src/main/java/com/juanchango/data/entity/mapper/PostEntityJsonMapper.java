package com.juanchango.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.juanchango.data.entity.PostEntity;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Map Json object to {@link com.juanchango.data.entity.PostEntity}
 */
public class PostEntityJsonMapper {


    private final Gson gson;

    @Inject
    public PostEntityJsonMapper() {
        this.gson = new Gson();
    }


    /**
     * Transform a valid Json String to {@link PostEntity}
     *
     * @param postJsonResponse A json string representing a Post Details
     * @return {@link PostEntity}
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public PostEntity transformPostEntity(String postJsonResponse) throws JsonSyntaxException {
        final Type postEntityType = new TypeToken<PostEntity>() {}.getType();
        return this.gson.fromJson(postJsonResponse, postEntityType);
    }

    /**
     * Transform a valid Json String to a List of {@link PostEntity}
     *
     * @param postListJsonResponse A jason string representing Collection of Posts
     * @return Collection of {@link PostEntity}
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<PostEntity> transformPostEntityList (String postListJsonResponse)
            throws JsonSyntaxException{
        final Type postListEntityType = new TypeToken<List<PostEntity>>() {}.getType();
        return this.gson.fromJson(postListJsonResponse, postListEntityType);
    }

}
