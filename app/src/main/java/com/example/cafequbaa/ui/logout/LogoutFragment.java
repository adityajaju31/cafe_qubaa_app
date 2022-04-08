package com.example.cafequbaa.ui.logout;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cafequbaa.R;
import com.example.cafequbaa.signInpage;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.logout_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LogoutViewModel.class);
        Intent intent= new Intent(getActivity(), signInpage.class);
        startActivity(intent);
        // TODO: Use the ViewModel
    }

}
