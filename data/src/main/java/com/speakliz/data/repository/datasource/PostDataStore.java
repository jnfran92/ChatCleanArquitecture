package com.speakliz.data.repository.datasource;

import com.speakliz.data.entity.PostEntity;

import java.util.List;
import io.reactivex.Observable;

/**
 * This Interface defines Observable of {@link PostEntity}
 * that have to be delivered for the domain layer
 * In this case Post can be grouped in a list or returned a single
 */

public interface PostDataStore {
    Observable<List<PostEntity>> postEntityList();
    Observable<PostEntity> postEntityDetails(int id);
}
