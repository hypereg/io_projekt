package com.example.myapplication.ui.plan;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

public class PlanFragment extends Fragment {

    private PlanViewModel planViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plan, container, false);
        return root;
    }
}