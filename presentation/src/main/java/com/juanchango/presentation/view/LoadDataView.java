package com.juanchango.presentation.view;

import android.content.Context;

/**
 * Interface representing a View that will use to load data.
 */
public interface LoadDataView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide loading bar.
     */
    void hideLoading();

    /**
     * Show a retry view in case of an error.
     */
    void showRetry();

    /**
     * Hide retry view.
     */
    void hideRetry();

    /**
     * Show error view using a String message.
     */
    void showError(String message);

    /**
     * Get {@link Context})
     */
    Context context();
}
