package com.juanchango.presentation.view;

import com.juanchango.presentation.viewmodel.PostViewModel;

import java.util.Collection;

/**
 * Interface to communicates Domain and Presentation Layer. It contains render methods.
 */
public interface PostListView extends LoadDataView {

    /**
     * Render a Collection of {@link PostViewModel} objects.
     *
     * @param postViewModels collection {@link PostViewModel} to render.
     */
    void renderPostList(Collection<PostViewModel> postViewModels);

    /**
     * Render {@link PostViewModel} object.
     *
     * @param postViewModel {@link PostViewModel} objec to render.
     */
    void viewPost(PostViewModel postViewModel);
}
