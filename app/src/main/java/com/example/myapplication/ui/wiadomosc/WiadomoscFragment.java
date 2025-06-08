package com.example.myapplication.ui.wiadomosc;

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
import com.example.myapplication.ui.wiadomosc.WiadomoscAdapter;

import com.example.myapplication.databinding.FragmentWiadomoscBinding;
import com.example.myapplication.ui.wiadomosc.Wiadomosc;
import java.util.ArrayList;
import java.util.List;

public class WiadomoscFragment extends Fragment {

    private WiadomoscViewModel wiadomoscViewModel;
    private FragmentWiadomoscBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWiadomoscBinding.inflate(inflater, container, false);
        wiadomoscViewModel = new ViewModelProvider(this).get(WiadomoscViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.wiadomoscRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Wiadomosc> lista = new ArrayList<>();
        lista.add(new Wiadomosc("Dziekanat", "Przerwa świąteczna", "Od 3 czerwca przerwa w zajęciach.", "ogloszenie", "2024-06-01"));
        lista.add(new Wiadomosc("Sekretariat", "Nowy plan zajęć", "Zaktualizowany plan zajęć dostępny w systemie.", "ogloszenie", "2024-06-01"));
        lista.add(new Wiadomosc("Prowadzący", "Zmiana sali", "Zajęcia z ekonomii odbędą się w sali 105 zamiast 201.", "wiadomosc", "2024-06-02"));
        lista.add(new Wiadomosc("Dziekanat", "Opłaty semestralne", "Przypominamy o konieczności wniesienia opłat do 15 czerwca.", "ogloszenie", "2024-06-02"));
        lista.add(new Wiadomosc("Prowadzący", "Odwołane zajęcia", "Zajęcia z matematyki 4 czerwca zostały odwołane.", "wiadomosc", "2024-06-03"));

        WiadomoscAdapter adapter = new WiadomoscAdapter(lista);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}