package com.example.myapplication.ui.zadania;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class ZadanieSzczegolyFragment extends Fragment {
    public static final String ARG_ZADANIE_ID = "zadanie_id";
    private Zadanie zadanie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zadanie_szczegoly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String zadanieId = getArguments() != null ? getArguments().getString(ARG_ZADANIE_ID) : null;
        if (zadanieId == null) return;
        zadanie = ZadaniaRepo.getInstance().getZadanieById(zadanieId);
        if (zadanie == null) return;

        ImageButton backBtn = view.findViewById(R.id.back_button);
        TextView tytul = view.findViewById(R.id.tytul_zadania);
        TextView termin = view.findViewById(R.id.termin_zadania);
        TextView status = view.findViewById(R.id.status_zadania);
        TextView opis = view.findViewById(R.id.opis_zadania);
        Button btnZalacz = view.findViewById(R.id.btn_zalacz_pliki);
        Button btnOddaj = view.findViewById(R.id.btn_oddaj_zadanie);

        tytul.setText(zadanie.getTemat());
        termin.setText("Termin: " + zadanie.getDataWykonania());
        status.setText("Status: " + zadanie.getStatus().name());
        opis.setText(zadanie.getOpis());

        backBtn.setOnClickListener(v -> requireActivity().onBackPressed());
        // btnZalacz i btnOddaj - obsługa do uzupełnienia
    }
}

