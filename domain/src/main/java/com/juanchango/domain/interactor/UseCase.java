package com.juanchango.domain.interactor;
import com.fernandocejas.arrow.checks.Preconditions;
import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a User Case (Interact(or) in terms of Clean Architecture)
 * This interface represents an execution unit for different use cases
 * (this means that any use case in the application should implement this contract)
 *
 * By Convention each useCase implementations will return the result using a {@link DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI Thread.
 *
 * @param <T> is the type of the Observable that is used in the {@link CompositeDisposable}
 * @param <Params>
 */

public abstract class UseCase<T, Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable compositeDisposable;

    UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Builds and {@link Observable} which will be used when executing
     * the current {@link UseCase}
     */
    abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     * Executes the current User Case.
     * Creates and {@link io.reactivex.Observable} linked to a {@link DisposableObserver},
     * subscribe it in Thread for emmit and receive data and
     * add it to internal Disposables {@link CompositeDisposable}.
     *
     * @param observer object base to create and obser
     * @param params
     */
    public void execute(DisposableObserver<T> observer, Params params){
        Preconditions.checkNotNull(observer);
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

    /**
     * Add {@link Disposable} to current {@link CompositeDisposable}
     * Disposable is and Object which is an {@link Observable} that has a
     * {@link DisposableObserver} subscribe to it.
     *
     * @param disposable disposable to add.
     */
    private void addDisposable(Disposable disposable){
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(compositeDisposable);
        compositeDisposable.add(disposable);
    }
}
