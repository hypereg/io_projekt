package com.example.myapplication.ui.zadania;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.FragmentTransaction;

public class ZadaniaFragment extends Fragment {

    private ZadaniaViewModel zadaniaViewModel;
    private List<Zadanie> allZadania = new ArrayList<>();
    private ZadanieAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        zadaniaViewModel = new ViewModelProvider(this).get(ZadaniaViewModel.class);
        return inflater.inflate(R.layout.fragment_zadania, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.zadaniaRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        allZadania.clear();
        allZadania.add(new Zadanie("1", "Matematyka", "2024-05-01", "2024-05-10",
                Zadanie.Status.PRZYSZLE, "Powtórka działu", "Rozdział 3-4", new ArrayList<>()));
        allZadania.add(new Zadanie("2", "Informatyka", "2024-04-20", "2024-04-25",
                Zadanie.Status.ZALEGLE, "Projekt aplikacji", "Zakończyć moduł logowania", new ArrayList<>()));
        allZadania.add(new Zadanie("3", "Biologia", "2024-04-10", "2024-04-12",
                Zadanie.Status.UKONCZONE, "Referat", "Temat dowolny", new ArrayList<>()));
        allZadania.add(new Zadanie("4", "Fizyka", "2024-05-02", "2024-05-10",
                Zadanie.Status.PRZYSZLE, "Zadania z dynamiki", "Rozwiązać zadania 1-5", new ArrayList<>()));
        allZadania.add(new Zadanie("5", "Chemia", "2024-05-03", "2024-05-11",
                Zadanie.Status.PRZYSZLE, "Sprawozdanie z laboratorium", "Opisać doświadczenie 2", new ArrayList<>()));
        allZadania.add(new Zadanie("6", "Historia", "2024-04-18", "2024-04-25",
                Zadanie.Status.ZALEGLE, "Esej o II wojnie światowej", "Min. 2 strony", new ArrayList<>()));
        allZadania.add(new Zadanie("7", "Język polski", "2024-04-09", "2024-04-12",
                Zadanie.Status.UKONCZONE, "Analiza wiersza", "Wybrać dowolny utwór", new ArrayList<>()));
        allZadania.add(new Zadanie("8", "WOS", "2024-05-01", "2024-05-10",
                Zadanie.Status.PRZYSZLE, "Prezentacja o Unii Europejskiej", "Przygotować slajdy", new ArrayList<>()));
        allZadania.add(new Zadanie("9", "Geografia", "2024-05-03", "2024-05-11",
                Zadanie.Status.PRZYSZLE, "Mapa konturowa", "Zaznaczyć rzeki Polski", new ArrayList<>()));
        allZadania.add(new Zadanie("10", "Matematyka", "2024-04-20", "2024-04-25",
                Zadanie.Status.ZALEGLE, "Zadania z funkcji", "Zadania 6-10", new ArrayList<>()));

        ZadaniaRepo.getInstance().setZadania(allZadania);

        adapter = new ZadanieAdapter(filterByStatus(Zadanie.Status.PRZYSZLE), zadanie -> {
            Intent intent = new Intent(requireContext(), ZadanieSzczegolyActivity.class);
            intent.putExtra("zadanie_id", zadanie.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        MaterialButton btnFuture = view.findViewById(R.id.filter_future);
        MaterialButton btnOverdue = view.findViewById(R.id.filter_overdue);
        MaterialButton btnDone = view.findViewById(R.id.filter_done);

        btnFuture.setChecked(true);
        btnOverdue.setChecked(false);
        btnDone.setChecked(false);

        btnFuture.setOnClickListener(v -> {
            btnFuture.setChecked(true);
            btnOverdue.setChecked(false);
            btnDone.setChecked(false);
            adapter = new ZadanieAdapter(filterByStatus(Zadanie.Status.PRZYSZLE), zadanie -> {
                Intent intent = new Intent(requireContext(), ZadanieSzczegolyActivity.class);
                intent.putExtra("zadanie_id", zadanie.getId());
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        });
        btnOverdue.setOnClickListener(v -> {
            btnFuture.setChecked(false);
            btnOverdue.setChecked(true);
            btnDone.setChecked(false);
            adapter = new ZadanieAdapter(filterByStatus(Zadanie.Status.ZALEGLE), zadanie -> {
                Intent intent = new Intent(requireContext(), ZadanieSzczegolyActivity.class);
                intent.putExtra("zadanie_id", zadanie.getId());
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        });
        btnDone.setOnClickListener(v -> {
            btnFuture.setChecked(false);
            btnOverdue.setChecked(false);
            btnDone.setChecked(true);
            adapter = new ZadanieAdapter(filterByStatus(Zadanie.Status.UKONCZONE), zadanie -> {
                Intent intent = new Intent(requireContext(), ZadanieSzczegolyActivity.class);
                intent.putExtra("zadanie_id", zadanie.getId());
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        });
    }

    private List<Zadanie> filterByStatus(Zadanie.Status status) {
        List<Zadanie> filtered = new ArrayList<>();
        for (Zadanie z : allZadania) {
            if (z.getStatus() == status) filtered.add(z);
        }
        return filtered;
    }
}