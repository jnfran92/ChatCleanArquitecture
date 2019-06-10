package com.juanchango.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.juanchango.presentation.R;
import com.juanchango.presentation.presenter.PostDetailsPresenter;
import com.juanchango.presentation.view.PostDetailsView;
import com.juanchango.presentation.view.activity.PostDetailsActivity;
import com.juanchango.presentation.viewmodel.PostViewModel;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class PostDetailsFragment extends BaseFragment implements PostDetailsView {

    private static final String PARAM_POST_ID = PostDetailsFragment.class.getName() + "_post_id";

    @BindView(R.id.tv_postDetailsFragment_postId)
    TextView textViewPostId;

    @BindView(R.id.tv_postDetailsFragment_title)
    TextView textViewTitle;

    @BindView(R.id.tv_postDetailsFragment_body)
    TextView textViewBody;

    @BindView(R.id.pb_postDetailsFragment_loading)
    ProgressBar progressBarLoading;

    @Inject
    PostDetailsPresenter postDetailsPresenter;


    public PostDetailsFragment() {
    }

    public static Fragment forPost(int postId){
        final Bundle bundle = new Bundle();
        bundle.putInt(PARAM_POST_ID, postId);
        final Fragment fragmentPostDetails = new PostDetailsFragment();
        fragmentPostDetails.setArguments(bundle);
        return fragmentPostDetails;
    }


    private PostDetailsActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_post_details, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (PostDetailsActivity) getActivity();
        this.initInjection();
        this.startViews();
    }

    // Injection
    private void initInjection(){
        activity.getPostComponent().inject(this);
    }

    private void startViews() {
        final int postId = Objects.requireNonNull(getArguments()).getInt(PARAM_POST_ID, -1);
        postDetailsPresenter.setView(this);
        postDetailsPresenter.initialize(postId);
    }

    /*
    View implementations
     */
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
        return Objects.requireNonNull(getActivity()).getApplicationContext();
    }

    // Progress Bar
    private void showProgressBar(){
        progressBarLoading.setVisibility(View.VISIBLE);
        progressBarLoading.setIndeterminate(true);
    }

    private void hideProgressBar(){
        progressBarLoading.setVisibility(View.INVISIBLE);
    }

    // Toast
    private void showToastMessage(String message){
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show();
    }

}
