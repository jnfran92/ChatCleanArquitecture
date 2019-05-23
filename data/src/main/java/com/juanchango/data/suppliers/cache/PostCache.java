package com.juanchango.data.suppliers.cache;

import com.juanchango.data.entity.PostEntity;

import io.reactivex.Observable;

/**
 * Get and post cached {@link PostEntity} data.
 */

public interface PostCache {

    /**
     * Get cached {@link PostEntity}
     *
     * @param postId    int PostId
     * @return @link PostEntity in Observable object
     */
    Observable<PostEntity> get(final int postId);

    /**
     * Cache {@link PostEntity}
     *
     * @param postEntity {@link PostEntity}
     */
    void put(PostEntity postEntity);

    /**
     * Verify if is Cached a {@link PostEntity} by passing the postId argument
     *
     * @param postId    postId int
     * @return  boolean true if it is cached or false if it is not.
     */
    boolean isCached(final int postId);

    /**
     * Check if the cache data is expired
     *
     * @return true if it is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evact all elements of the cache
     */
    void evictAll();


}
