package com.juanchango.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.juanchango.presentation.R;
import com.juanchango.presentation.di.component.DaggerPostComponent;
import com.juanchango.presentation.di.component.PostComponent;
import com.juanchango.presentation.navigation.Navigator;
import com.juanchango.presentation.view.fragment.PostDetailsFragment;

import butterknife.ButterKnife;
import timber.log.Timber;

public class PostDetailsActivity extends BaseActivity {

    private static final String INTENT_EXTRA_POST_ID = PostDetailsActivity.class.getName() + "_post_id";

    /**
     * Creates and intent used for {@link Navigator}
     *
     * @param context Application Context.
     * @return  Intent of this Class
     */
    public static Intent getCallingIntent(Context context, int postId) {
        final Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra(INTENT_EXTRA_POST_ID, postId);
        return intent;
    }

    private int postId;
    private PostComponent postComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        ButterKnife.bind(this);

        this.initActivity(savedInstanceState);
        this.initComponent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(outState != null){
            outState.putInt(INTENT_EXTRA_POST_ID, this.postId);
        }
        super.onSaveInstanceState(outState);
    }

    private void initActivity(Bundle savedInstanceState){
        if (savedInstanceState == null){
            this.postId = getIntent().getIntExtra(INTENT_EXTRA_POST_ID, -1);
            this.addFragment(R.id.fy_PostDetailsActivity_frameContainer,
                    PostDetailsFragment.forPost(this.postId));
        }else {
            this.postId = savedInstanceState.getInt(INTENT_EXTRA_POST_ID);
        }
    }

    private void initComponent(){
        postComponent = DaggerPostComponent
                .builder()
                .applicationComponent(this.getApplicationComponent())
                .activityModule(this.getActivityModule())
                .build();
    }

    public PostComponent getPostComponent(){
        return postComponent;
    }

}
