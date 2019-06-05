package com.juanchango.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.juanchango.presentation.R;
import com.juanchango.presentation.di.component.DaggerPostComponent;
import com.juanchango.presentation.di.component.PostComponent;
import com.juanchango.presentation.navigation.Navigator;
import com.juanchango.presentation.presenter.PostDetailsPresenter;
import com.juanchango.presentation.view.PostDetailsView;
import com.juanchango.presentation.viewmodel.PostViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class PostDetailsActivity extends BaseActivity implements PostDetailsView {

    private static final String INTENT_EXTRA_POST_ID = PostDetailsActivity.class.getName() + "_postId";

    @BindView(R.id.tv_postDetailsActivity_postId)
    TextView textViewPostId;

    @BindView(R.id.tv_postDetailsActivity_title)
    TextView textViewTitle;

    @BindView(R.id.tv_postDetailsActivity_body)
    TextView textViewBody;

    @BindView(R.id.pb_postDetailsActivity_loading)
    ProgressBar progressBarLoading;

    @Inject
    PostDetailsPresenter postDetailsPresenter;

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

        this.initInjection();
        this.startViews();
    }



    private void initInjection(){
        PostComponent postComponent = DaggerPostComponent.builder()
                .applicationComponent(this.getApplicationComponent())
                .activityModule(this.getActivityModule())
                .build();

        postComponent.inject(this);
    }

    private void startViews() {
        final int postId = getIntent().getIntExtra(INTENT_EXTRA_POST_ID, 0);
        postDetailsPresenter.setView(this);
        postDetailsPresenter.initialize(postId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("onResume");

    }

    //PostDetailsView implementation
    @Override
    public void renderPost(PostViewModel postViewModel) {
        Timber.i("renderPost(): ");
        textViewPostId.setText(String.valueOf(postViewModel.getPostId()));
        textViewTitle.setText(postViewModel.getTitle());
        textViewBody.setText(postViewModel.getBody());
    }

    @Override
    public void showLoading() {
        Timber.i("showLoading(): ");
        showProgressBar();

    }

    @Override
    public void hideLoading() {
        Timber.i("hideLoading(): ");
        hideProgressBar();
    }

    @Override
    public void showRetry() {
        Timber.i("showRetry(): ");
    }

    @Override
    public void hideRetry() {
        Timber.i("hideRetry(): ");
    }

    @Override
    public void showError(String message) {
        Timber.i("showError(): ");
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getApplication();
    }

    // Progress Bar
    private void showProgressBar(){
        progressBarLoading.setVisibility(View.VISIBLE);
        progressBarLoading.setIndeterminate(true);
    }

    private void hideProgressBar(){
        progressBarLoading.setVisibility(View.GONE);
    }

    // Toast
    private void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
