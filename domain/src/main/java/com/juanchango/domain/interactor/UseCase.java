package com.juanchango.domain.interactor;
import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;

import io.reactivex.observers.DisposableObserver;

/**
 * Abstract class for a User Case (Interact(or) in terms of Clean Architecture)
 * This interface represents an execution unit for different use cases
 * (this means that any use case in the application should implement this contract)
 *
 * By Convention each useCase implementations will return the result using a {@link DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI Thread.
 *
 * @param <T>
 * @param <Params>
 */

public abstract class UseCase<T, Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;



}
