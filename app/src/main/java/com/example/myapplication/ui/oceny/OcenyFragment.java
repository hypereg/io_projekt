package com.example.myapplication.ui.oceny;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.FragmentOcenyBinding;

import java.util.ArrayList;
import java.util.List;

public class OcenyFragment extends Fragment {

    private OcenyViewModel ocenyViewModel;
    private FragmentOcenyBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOcenyBinding.inflate(inflater, container, false);
        ocenyViewModel = new ViewModelProvider(this).get(OcenyViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewOceny = binding.ocenyRecyclerView;
        recyclerViewOceny.setLayoutManager(new LinearLayoutManager(getContext()));

        List<OcenyPrzedmiot> listaOcen = new ArrayList<>();

        List<Ocena> ocenyMat = new ArrayList<>();
        ocenyMat.add(new Ocena("Matematyka", 5.0, "2024-03-15", "Sprawdzian"));
        ocenyMat.add(new Ocena("Matematyka", 4.0, "2024-03-10", "Kartkówka"));
        ocenyMat.add(new Ocena("Matematyka", 4.5, "2024-03-01", "Odpowiedź"));
        listaOcen.add(new OcenyPrzedmiot("Matematyka", ocenyMat));

        List<Ocena> ocenyPol = new ArrayList<>();
        ocenyPol.add(new Ocena("Język polski", 3.5, "2024-02-28", "Wypracowanie"));
        ocenyPol.add(new Ocena("Język polski", 4.0, "2024-03-12", "Czytanie ze zrozumieniem"));
        listaOcen.add(new OcenyPrzedmiot("Język polski", ocenyPol));

        List<Ocena> ocenyAng = new ArrayList<>();
        ocenyAng.add(new Ocena("Język angielski", 5.0, "2024-03-18", "Test"));
        ocenyAng.add(new Ocena("Język angielski", 5.0, "2024-02-25", "Kartkówka"));
        ocenyAng.add(new Ocena("Język angielski", 4.5, "2024-03-08", "Projekt"));
        listaOcen.add(new OcenyPrzedmiot("Język angielski", ocenyAng));

        List<Ocena> ocenyBio = new ArrayList<>();
        ocenyBio.add(new Ocena("Biologia", 4.0, "2024-03-17", "Odpowiedź"));
        ocenyBio.add(new Ocena("Biologia", 3.0, "2024-02-19", "Praca domowa"));
        ocenyBio.add(new Ocena("Biologia", 3.5, "2024-03-05", "Projekt"));
        listaOcen.add(new OcenyPrzedmiot("Biologia", ocenyBio));

        List<Ocena> ocenyGeo = new ArrayList<>();
        ocenyGeo.add(new Ocena("Geografia", 4.0, "2024-02-12", "Mapy"));
        ocenyGeo.add(new Ocena("Geografia", 4.5, "2024-03-09", "Sprawdzian"));
        listaOcen.add(new OcenyPrzedmiot("Geografia", ocenyGeo));

        List<Ocena> ocenyHis = new ArrayList<>();
        ocenyHis.add(new Ocena("Historia", 5.0, "2024-03-11", "Odpowiedź"));
        ocenyHis.add(new Ocena("Historia", 4.5, "2024-02-14", "Esej"));
        listaOcen.add(new OcenyPrzedmiot("Historia", ocenyHis));

        List<Ocena> ocenyWf = new ArrayList<>();
        ocenyWf.add(new Ocena("Wychowanie fizyczne", 5.0, "2024-03-06", "Aktywność"));
        listaOcen.add(new OcenyPrzedmiot("Wychowanie fizyczne", ocenyWf));

        List<Ocena> ocenyInf = new ArrayList<>();
        ocenyInf.add(new Ocena("Informatyka", 5.0, "2024-03-15", "Projekt"));
        ocenyInf.add(new Ocena("Informatyka", 5.0, "2024-02-27", "Zadanie domowe"));
        ocenyInf.add(new Ocena("Informatyka", 4.0, "2024-03-03", "Quiz"));
        listaOcen.add(new OcenyPrzedmiot("Informatyka", ocenyInf));

        List<Ocena> ocenyChe = new ArrayList<>();
        ocenyChe.add(new Ocena("Chemia", 3.0, "2024-02-24", "Sprawdzian"));
        ocenyChe.add(new Ocena("Chemia", 4.0, "2024-03-04", "Odpowiedź"));
        listaOcen.add(new OcenyPrzedmiot("Chemia", ocenyChe));

        List<Ocena> ocenyFiz = new ArrayList<>();
        ocenyFiz.add(new Ocena("Fizyka", 4.0, "2024-03-13", "Kartkówka"));
        ocenyFiz.add(new Ocena("Fizyka", 3.5, "2024-03-01", "Zadanie domowe"));
        ocenyFiz.add(new Ocena("Fizyka", 5.0, "2024-02-15", "Sprawdzian"));
        listaOcen.add(new OcenyPrzedmiot("Fizyka", ocenyFiz));


        OcenyPrzedmiotAdapter adapter = new OcenyPrzedmiotAdapter(listaOcen);
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}