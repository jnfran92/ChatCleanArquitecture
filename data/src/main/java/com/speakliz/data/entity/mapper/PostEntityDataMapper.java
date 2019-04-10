package com.speakliz.data.entity.mapper;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.domain.model.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Singleton, map {@link PostEntity} to
 * {@link Post}
 */
public class PostEntityDataMapper {

    private static PostEntityDataMapper instance;

    /**
     * Private Access Singleton
     */
    private PostEntityDataMapper() {
    }

    /**
     * Singleton
     * @return {@link PostEntityDataMapper}
     */
    public PostEntityDataMapper getInstance(){
        if(instance == null){
            instance = new PostEntityDataMapper();
        }
        return instance;
    }


    /**
     * Transform bellow objects
     * @param postEntity {@link PostEntity}
     * @return {@link Post}
     */
    public Post transform(PostEntity postEntity){

        Post post = null;

        if(postEntity != null){
            post = new Post(postEntity.getUserId());
            post.setUserId(postEntity.getUserId());
            post.setBody(postEntity.getBody());
            post.setTitle(postEntity.getTitle());
        }
        return post;
    }

    /**
     * Transfor a list of {@link Post} to Collection of {@link PostEntity}
     * @param postEntityList Collection of {@link PostEntity} to be transformed
     * @return List of {@link Post}
     */
    public List<Post> transform(Collection<PostEntity> postEntityList){
        final List<Post> postList = new ArrayList<>(20);

        if(postEntityList != null){
            for (PostEntity postEntity:postEntityList){
                final Post post = transform(postEntity);

                if(post != null){
                    postList.add(post);
                }
            }
        }
        return postList;
    }
}
