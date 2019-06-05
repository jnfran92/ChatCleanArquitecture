package com.juanchango.presentation.view.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

public class PostAdapterLayoutManager extends LinearLayoutManager {

    @Inject
    public PostAdapterLayoutManager(Context context) {
        super(context);
    }
}
