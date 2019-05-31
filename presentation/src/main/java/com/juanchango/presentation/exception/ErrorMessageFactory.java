package com.juanchango.presentation.exception;

import android.content.Context;

import com.juanchango.data.suppliers.cache.exception.PostNotFoundException;
import com.juanchango.data.suppliers.net.exception.NetworkConnectionException;
import com.juanchango.presentation.R;

/**
 * Factory used to create error messages from an Exception as a condition. The string message is
 * got from Android Resources.
 *
 * Note: this class could be a good example for analise Liskov Substitution and Open Closed Principle
 */
public class ErrorMessageFactory {

    public ErrorMessageFactory() {
    }

    public static String create(Context context, Exception exception){
        String message = context.getString(R.string.default_error_message);

        if(exception instanceof NetworkConnectionException){
            message = context.getString(R.string.network_connection_error_message);
        }else if (exception instanceof PostNotFoundException){
            message = context.getString(R.string.post_notfound_error_message);
        }

        return message;
    }
}
