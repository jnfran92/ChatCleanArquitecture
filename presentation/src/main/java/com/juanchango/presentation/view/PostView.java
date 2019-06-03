package com.juanchango.presentation.view;

import com.juanchango.domain.model.PostModel;
import com.juanchango.presentation.viewmodel.PostViewModel;

public interface PostView extends LoadDataView {
    void renderPost(PostViewModel postViewModel);
}
