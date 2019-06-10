package com.juanchango.presentation.view.activity;

/**
 * Interface contract for every activity which has a component. It is needed for DI.
 */
public interface HasComponent<C> {
    C getComponent();
}
