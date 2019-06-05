package com.juanchango.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.juanchango.presentation.R;

import timber.log.Timber;

import com.juanchango.presentation.navigation.Navigator;

public class PostDetailsActivity extends BaseActivity {

    /**
     * Creates and intent used for {@link Navigator}
     *
     * @param context Application Context.
     * @return  Intent of this Class
     */
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, PostDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("onResume");

    }
}
