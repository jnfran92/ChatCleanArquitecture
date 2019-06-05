package com.juanchango.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.juanchango.presentation.view.activity.PostDetailsActivity;
import com.juanchango.presentation.view.activity.PostListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    Navigator() {
    }

    /**
     * Goes to the post list view
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToPostList(Context context){
        if(context != null){
            Intent intent = PostListActivity.getCallingIntent(context);
            context.startActivity(intent);
        }
    }

    /**
     * Goes to the post details view
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToPostDetails(Context context, int postId){
        if(context != null){
            Intent intent = PostDetailsActivity.getCallingIntent(context, postId);
            context.startActivity(intent);
        }
    }
}
