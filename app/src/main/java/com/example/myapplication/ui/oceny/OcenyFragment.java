package com.example.myapplication.ui.oceny;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

public class OcenyFragment extends Fragment {

    private OcenyViewModel ocenyViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ocenyViewModel = new ViewModelProvider(this).get(OcenyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_oceny, container, false);
        return root;
    }
}