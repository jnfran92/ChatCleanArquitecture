package com.juanchango.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juanchango.domain.model.PostModel;
import com.juanchango.presentation.R;
import com.juanchango.presentation.viewmodel.PostViewModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter needed for RecyclerView in order to show {@link PostModel} objects.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    //region ViewModel
    private List<PostViewModel> postViewModelList;
    //endregion


    //region Owner methods

    private final LayoutInflater layoutInflater;

    /**
     * Constructor of adapter, it needs Context
     * @param context app context
     */
    public PostsAdapter(Context context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.postViewModelList = Collections.emptyList();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.view_post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        final PostViewModel postViewModel = this.postViewModelList.get(position);
        holder.textViewId.setText(String.valueOf(postViewModel.getPostId()));
        holder.textViewTitle.setText(postViewModel.getTitle());
        holder.textViewBody.setText(postViewModel.getBody());
    }

    @Override
    public int getItemCount() {
        return (postViewModelList != null)? this.postViewModelList.size() : 0;
    }
    //endregion


    //region Getters and Setters

    /**
     * Get ViewModel List {@link PostViewModel}
     * @return list of {@link PostViewModel}
     */
    public Collection<PostViewModel> getPostViewModelList() {
        return postViewModelList;
    }

    /**
     * Set viewmodel List {@link PostViewModel}
     * @param postViewModelList list of {@link PostViewModel} objects.
     */
    public void setPostViewModelList(Collection<PostViewModel> postViewModelList) {
        this.validatePostViewModelList(postViewModelList);
        this.postViewModelList = (List<PostViewModel>) postViewModelList;
        this.notifyDataSetChanged();
    }

    private void validatePostViewModelList(Collection<PostViewModel> postViewModels){
        if(postViewModels == null){
            throw new IllegalArgumentException("This list cannot be null");
        }
    }

    //endregion

    //region ViewHolder

    static class PostViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_viewPostItem_id)
        TextView textViewId;

        @BindView(R.id.tv_viewPostItem_title)
        TextView textViewTitle;

        @BindView(R.id.tv_viewPostItem_body)
        TextView textViewBody;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //endregion


}
