package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Rect;

import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.example.myapplication.ui.oceny.Ocena;
import com.example.myapplication.ui.oceny.OcenyAdapter;
import com.example.myapplication.ui.ogloszenie.Ogloszenie;
import com.example.myapplication.ui.ogloszenie.OgloszenieAdapter;

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

        List<Ogloszenie> listaOgloszen = new ArrayList<>();
        listaOgloszen.add(new Ogloszenie("Dziekanat", "Przerwa świąteczna", "Od 3 czerwca przerwa w zajęciach. Uczelnia będzie zamknięta do 10 czerwca."));
        listaOgloszen.add(new Ogloszenie("Sekretariat", "Nowy plan zajęć", "Zaktualizowany plan zajęć dostępny w systemie."));
        listaOgloszen.add(new Ogloszenie("Prowadzący", "Zmiana sali", "Zajęcia z ekonomii odbędą się w sali 105 zamiast 201."));
        listaOgloszen.add(new Ogloszenie("Dziekanat", "Opłaty semestralne", "Przypominamy o konieczności wniesienia opłat do 15 czerwca."));
        listaOgloszen.add(new Ogloszenie("Samorząd", "Wybory do samorządu", "Zapraszamy do udziału w wyborach studenckich w dniach 7-9 czerwca."));
        listaOgloszen.add(new Ogloszenie("Prowadzący", "Odwołane zajęcia", "Zajęcia z matematyki 4 czerwca zostały odwołane."));
        listaOgloszen.add(new Ogloszenie("Dziekanat", "Praktyki studenckie", "Do 20 czerwca należy dostarczyć zaświadczenie o odbyciu praktyk."));
        listaOgloszen.add(new Ogloszenie("Sekretariat", "Nowe legitymacje", "Odbiór nowych legitymacji studenckich w pokoju 14."));
        listaOgloszen.add(new Ogloszenie("Biblioteka", "Zwrot książek", "Prosimy o zwrot wypożyczonych książek do końca semestru."));
        listaOgloszen.add(new Ogloszenie("Samorząd", "Piknik studencki", "W piątek 14 czerwca organizujemy piknik – zapraszamy wszystkich studentów!"));

        OgloszenieAdapter ogloszeniaAdapter = new OgloszenieAdapter(listaOgloszen);
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