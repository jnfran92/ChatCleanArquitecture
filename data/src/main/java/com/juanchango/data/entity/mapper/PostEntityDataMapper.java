package com.juanchango.data.entity.mapper;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.domain.model.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Singleton, map {@link PostEntity} to
 * {@link Post}
 */
@Singleton
public class PostEntityDataMapper {

    /**
     * Private Access Singleton
     */
    @Inject
    PostEntityDataMapper() {
    }


    /**
     * Transform bellow objects
     * @param postEntity {@link PostEntity}
     * @return {@link Post}
     */
    public Post transform(PostEntity postEntity){

        Post post = null;

        if(postEntity != null){
            post = new Post(postEntity.getPostId());
            post.setUserId(postEntity.getUserId());
            post.setBody(postEntity.getBody());
            post.setTitle(postEntity.getTitle());
        }
        return post;
    }

    /**
     * Transfer a list of {@link Post} to Collection of {@link PostEntity}
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
