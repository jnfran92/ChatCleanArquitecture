package com.juanchango.data.suppliers.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.entity.mapper.PostEntityFromJsonMapper;
import com.juanchango.data.suppliers.local.LocalApi;
import com.juanchango.data.suppliers.net.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Class for Consume Rest API https://jsonplaceholder.typicode.com/posts
 * Implementation of {@link LocalApi}
 * It needs a {@link android.content.Context} context and the {@link PostEntityFromJsonMapper}
 */

public class RestApiImpl implements RestApi {

    private final Context context;
    private final PostEntityFromJsonMapper postEntityJsonMapper;

    public RestApiImpl(Context context, PostEntityFromJsonMapper postEntityJsonMapper) {

        if(context ==null || postEntityJsonMapper == null){
            throw new IllegalArgumentException("Constructor's arguments cannot be null");
        }

        this.context = context.getApplicationContext();
        this.postEntityJsonMapper = postEntityJsonMapper;
    }

    /**
     * Get the {@link Observable} type List {@link PostEntity} from Json response.
     *
     * @return {@link Observable} type List {@link PostEntity}
     */
    @Override
    public Observable<List<PostEntity>> postEntityList() {

        return Observable.create(emitter -> {

            if (isThereInternetConnection()){

                try {
                    String postListJsonResponse =
                            ApiConnection.createGET(API_URL_GET_POST_LIST).requestSyncCall();

                    if(postListJsonResponse !=null){
                        emitter.onNext(postEntityJsonMapper
                                .transformPostEntityList(postListJsonResponse));
                        emitter.onComplete();
                    }else {
                        emitter.onError(new NetworkConnectionException());
                    }

                } catch (MalformedURLException e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }

            }else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }

    /**
     * Get {@link Observable} type {@link PostEntity} by Id from Json response
     *
     * @param postId Id of the PostModel
     * @return  {@link Observable} type {@link PostEntity}
     */
    @Override
    public Observable<PostEntity> postEntityById(int postId) {
        return Observable.create(new ObservableOnSubscribe<PostEntity>() {
            @Override
            public void subscribe(ObservableEmitter<PostEntity> emitter) {

                if(isThereInternetConnection()){

                    try {
                        String postJsonResponse = ApiConnection.createGET(API_URL_GET_POST_DETAILS
                                + String.valueOf(postId))
                                .requestSyncCall();

                        if(postJsonResponse != null){
                            emitter.onNext(postEntityJsonMapper
                                    .transformPostEntity(postJsonResponse));
                            emitter.onComplete();
                        }else {
                            emitter.onError(new NetworkConnectionException());
                        }

                    } catch (MalformedURLException e) {
                        emitter.onError(e.getCause());
                    }

                }else {
                    emitter.onError(new NetworkConnectionException());
                }
            }
        });
    }


    /**
     * Test if the internet connection is enable
     *
     * @return boolean: true if it is Connected, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

}
