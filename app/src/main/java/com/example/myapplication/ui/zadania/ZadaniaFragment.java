package com.example.myapplication.ui.zadania;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

public class ZadaniaFragment extends Fragment {

    private ZadaniaViewModel zadaniaViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        zadaniaViewModel = new ViewModelProvider(this).get(ZadaniaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_zadania, container, false);
        return root;
    }
}