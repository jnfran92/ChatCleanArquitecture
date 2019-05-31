package com.juanchango.presentation.view;

import com.juanchango.domain.model.PostModel;
import com.juanchango.presentation.viewmodel.PostViewModel;

import java.util.Collection;

public interface PostListView extends LoadDataView {
    void renderPostList(Collection<PostViewModel> postViewModels);
    void viewPost(PostViewModel postViewModel);
}
