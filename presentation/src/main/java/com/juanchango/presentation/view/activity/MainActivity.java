package com.juanchango.presentation.view.activity;

import android.os.Bundle;
import android.widget.Button;

import com.juanchango.presentation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_mainActivity_go)
    Button buttonGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Timber.plant(new Timber.DebugTree());
        Timber.i("onCreate using timber");
    }

    /**
     * Retrieves data Button Go
     */
    @OnClick(R.id.bt_mainActivity_go)
    void setButtonGoClick(){
        Timber.i("Button go");
        this.navigator.navigateToPostList(this);
    }

}
