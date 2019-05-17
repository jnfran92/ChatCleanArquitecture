package com.speakliz.data.suppliers.utils.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the object out of the UI.
 */
public interface ThreadExecutor extends Executor { }
