package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Rect;

import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.example.myapplication.ui.oceny.Ocena;
import com.example.myapplication.ui.oceny.OcenyAdapter;
import com.example.myapplication.ui.wiadomosc.Wiadomosc;
import com.example.myapplication.ui.wiadomosc.WiadomoscAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerViewOceny = binding.ocenyRecyclerView;
        recyclerViewOceny.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Ocena> lista = new ArrayList<>();
        lista.add(new Ocena("Matematyka", 5.0, "2024-05-10", "Kolos 1"));
        lista.add(new Ocena("Programowanie", 4.5, "2024-05-08", "Projekt"));
        lista.add(new Ocena("Ekonomia", 4.0, "2024-05-01", "Aktywnosc"));

        OcenyAdapter adapter = new OcenyAdapter(lista);
        recyclerViewOceny.setAdapter(adapter);

        recyclerViewOceny.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
                if (position != parent.getAdapter().getItemCount() - 1) {
                    outRect.bottom = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 15, view.getResources().getDisplayMetrics());
                }
            }
        });

        RecyclerView recyclerViewOgloszenia = binding.ogloszeniaRecyclerView;
        recyclerViewOgloszenia.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Wiadomosc> listaOgloszen = new ArrayList<>();
        listaOgloszen.add(new Wiadomosc("Dziekanat", "Przerwa świąteczna", "Od 3 czerwca przerwa w zajęciach. Uczelnia będzie zamknięta do 10 czerwca.", "ogloszenie", "2024-06-01"));
        listaOgloszen.add(new Wiadomosc("Sekretariat", "Nowy plan zajęć", "Zaktualizowany plan zajęć dostępny w systemie.", "ogloszenie", "2024-06-01"));
        listaOgloszen.add(new Wiadomosc("Prowadzący", "Zmiana sali", "Zajęcia z ekonomii odbędą się w sali 105 zamiast 201.", "wiadomosc", "2024-06-02"));
        listaOgloszen.add(new Wiadomosc("Dziekanat", "Opłaty semestralne", "Przypominamy o konieczności wniesienia opłat do 15 czerwca.", "ogloszenie", "2024-06-02"));
        listaOgloszen.add(new Wiadomosc("Samorząd", "Wybory do samorządu", "Zapraszamy do udziału w wyborach studenckich w dniach 7-9 czerwca.", "ogloszenie", "2024-06-03"));
        listaOgloszen.add(new Wiadomosc("Prowadzący", "Odwołane zajęcia", "Zajęcia z matematyki 4 czerwca zostały odwołane.", "wiadomosc", "2024-06-03"));
        listaOgloszen.add(new Wiadomosc("Dziekanat", "Praktyki studenckie", "Do 20 czerwca należy dostarczyć zaświadczenie o odbyciu praktyk.", "ogloszenie", "2024-06-04"));
        listaOgloszen.add(new Wiadomosc("Sekretariat", "Nowe legitymacje", "Odbiór nowych legitymacji studenckich w pokoju 14.", "ogloszenie", "2024-06-04"));
        listaOgloszen.add(new Wiadomosc("Biblioteka", "Zwrot książek", "Prosimy o zwrot wypożyczonych książek do końca semestru.", "ogloszenie", "2024-06-04"));
        listaOgloszen.add(new Wiadomosc("Samorząd", "Piknik studencki", "W piątek 14 czerwca organizujemy piknik – zapraszamy wszystkich studentów!", "ogloszenie", "2024-06-05"));
        WiadomoscAdapter ogloszeniaAdapter = new WiadomoscAdapter(listaOgloszen);
        recyclerViewOgloszenia.setAdapter(ogloszeniaAdapter);




//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}