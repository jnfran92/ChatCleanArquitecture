package com.juanchango.data.entity.mapper;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.domain.model.PostModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Singleton, map {@link PostEntity} to
 * {@link PostModel}
 */
@Singleton
public class PostFromEntityMapper {

    /**
     * Private Access Singleton
     */
    @Inject
    PostFromEntityMapper() {
    }

    private static PostFromEntityMapper instance;
    public static PostFromEntityMapper getInstance(){
        if(instance == null){
            instance = new PostFromEntityMapper();
        }
        return instance;
    }

    /**
     * Transform bellow objects
     * @param postEntity {@link PostEntity}
     * @return {@link PostModel}
     */
    public PostModel transform(PostEntity postEntity){

        PostModel post = null;

        if(postEntity != null){
            post = new PostModel(postEntity.getPostId());
            post.setUserId(postEntity.getUserId());
            post.setBody(postEntity.getBody());
            post.setTitle(postEntity.getTitle());
        }
        return post;
    }

    /**
     * Transfer a list of {@link PostModel} to Collection of {@link PostEntity}
     * @param postEntityList Collection of {@link PostEntity} to be transformed
     * @return List of {@link PostModel}
     */
    public List<PostModel> transform(Collection<PostEntity> postEntityList){
        final List<PostModel> postList = new ArrayList<>(20);

        if(postEntityList != null){
            for (PostEntity postEntity:postEntityList){
                final PostModel post = transform(postEntity);

                if(post != null){
                    postList.add(post);
                }
            }
        }
        return postList;
    }
}
