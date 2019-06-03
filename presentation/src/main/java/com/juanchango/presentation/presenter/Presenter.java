package com.juanchango.presentation.presenter;

/**
 * Interface representing a presenter in a model view presenter MVP pattern.
 */
public interface Presenter {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume method.
     */
    void resume();

    /**
     * Method that control the lifecycle od the view. It should be called in the view's onPause().
     */
    void pause();

    /**
     * Method tha control de lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();


}
