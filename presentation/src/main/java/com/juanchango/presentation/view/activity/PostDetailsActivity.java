package com.juanchango.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.juanchango.presentation.R;
import com.juanchango.presentation.di.component.ApplicationComponent;
import com.juanchango.presentation.di.component.DaggerPostComponent;
import com.juanchango.presentation.di.component.PostComponent;
import com.juanchango.presentation.navigation.Navigator;
import com.juanchango.presentation.view.fragment.PostDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class PostDetailsActivity extends BaseActivity {

    public static final String INTENT_EXTRA_POST_ID = PostDetailsActivity.class.getName() + "_post_id";

    int postId;

//    Fragment
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    // PostComponent
    private PostComponent postComponent;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        ButterKnife.bind(this);

        postId = getIntent().getIntExtra(INTENT_EXTRA_POST_ID, 0);
        this.initComponent();
    }

    private void initComponent(){
        postComponent = DaggerPostComponent
                .builder()
                .applicationComponent(this.getApplicationComponent())
                .activityModule(this.getActivityModule())
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("onResume");
        initFragment();
    }

    private void initFragment() {

        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_EXTRA_POST_ID, postId);

        Fragment fragmentPostDetails = new PostDetailsFragment();
        fragmentPostDetails.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fy_PostDetailsActivity_frameContainer, fragmentPostDetails);
        fragmentTransaction.commit();

    }

    public PostComponent getPostComponent(){
        return postComponent;
    }

}
