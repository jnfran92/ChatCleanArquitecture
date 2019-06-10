package com.juanchango.presentation.view.fragment;


import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.juanchango.presentation.view.activity.HasComponent;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {


    protected void showToastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected <C> C getComponent(Class<C> componentClass){
        return componentClass.cast(((HasComponent<C>) Objects.requireNonNull(getActivity())).getComponent());
    }
}
