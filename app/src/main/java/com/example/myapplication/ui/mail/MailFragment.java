package com.example.myapplication.ui.mail;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

public class MailFragment extends Fragment {

    private MailViewModel mailViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mailViewModel = new ViewModelProvider(this).get(MailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mail, container, false);
        return root;
    }
}