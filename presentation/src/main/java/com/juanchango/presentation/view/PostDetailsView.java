package com.juanchango.presentation.view;

import com.juanchango.presentation.viewmodel.PostViewModel;

/**
 * Interface representing a View in a model view presenter pattern.
 * In this case is used as a view representing a post.
 */
public interface PostDetailsView extends LoadDataView {

    /**
     * Render a post in the UI.
     *
     * @param postViewModel The {@link PostViewModel} the will be shown.
     */
    void renderPost(PostViewModel postViewModel);
}
