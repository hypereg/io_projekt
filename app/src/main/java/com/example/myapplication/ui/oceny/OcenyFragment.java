package com.example.myapplication.ui.oceny;

import android.app.AlertDialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentOcenyBinding;

import java.util.ArrayList;
import java.util.List;

public class OcenyFragment extends Fragment {

    private OcenyViewModel ocenyViewModel;
    private FragmentOcenyBinding binding;
    private List<OcenyPrzedmiot> listaOcen = new ArrayList<>();
    private OcenyPrzedmiotAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOcenyBinding.inflate(inflater, container, false);
        ocenyViewModel = new ViewModelProvider(this).get(OcenyViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.getRoot().findViewById(R.id.button_add_grade)
                .setBackgroundColor(android.graphics.Color.TRANSPARENT);
        binding.getRoot().findViewById(R.id.button_remove_grade)
                .setBackgroundColor(android.graphics.Color.TRANSPARENT);
        RecyclerView recyclerViewOceny = binding.ocenyRecyclerView;
        recyclerViewOceny.setLayoutManager(new LinearLayoutManager(getContext()));

        if (listaOcen.isEmpty()) {
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
        }

        adapter = new OcenyPrzedmiotAdapter(listaOcen);
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

//        binding.getRoot().findViewById(R.id.button_add_grade).setOnClickListener(v -> {
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            View dialogView = inflater.inflate(R.layout.dialog_add_subject, null);
//
//            EditText etNazwa = dialogView.findViewById(R.id.editTextSubjectName);
//            EditText etOcena = dialogView.findViewById(R.id.editTextGradeValue);
//            EditText etData = dialogView.findViewById(R.id.editTextGradeDate);
//            EditText etOpis = dialogView.findViewById(R.id.editTextGradeDesc);
//
//            new AlertDialog.Builder(getContext())
//                    .setTitle("Dodaj przedmiot")
//                    .setView(dialogView)
//                    .setPositiveButton("Dodaj", (dialog, which) -> {
//                        String nazwa = etNazwa.getText().toString().trim();
//                        String ocenaStr = etOcena.getText().toString().trim();
//                        String data = etData.getText().toString().trim();
//                        String opis = etOpis.getText().toString().trim();
//
//                        if (!nazwa.isEmpty() && !ocenaStr.isEmpty()) {
//                            double wartoscOceny;
//                            try {
//                                wartoscOceny = Double.parseDouble(ocenaStr);
//                            } catch (NumberFormatException e) {
//                                wartoscOceny = 0.0;
//                            }
//                            List<Ocena> ocenyNowe = new ArrayList<>();
//                            ocenyNowe.add(new Ocena(nazwa, wartoscOceny, data, opis));
//                            listaOcen.add(new OcenyPrzedmiot(nazwa, ocenyNowe));
//                            adapter.notifyItemInserted(listaOcen.size() - 1);
//                        }
//                    })
//                    .setNegativeButton("Anuluj", null)
//                    .show();
//        });
        binding.getRoot().findViewById(R.id.button_add_grade).setOnClickListener(v -> {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View dialogView = inflater.inflate(R.layout.dodaj_ocene, null);

            Spinner spinnerSubjects = dialogView.findViewById(R.id.spinnerSubjects);
            EditText etOcena = dialogView.findViewById(R.id.editTextGradeValue);
            EditText etData = dialogView.findViewById(R.id.editTextGradeDate);
            EditText etOpis = dialogView.findViewById(R.id.editTextGradeDesc);

            List<String> nazwyPrzedmiotow = new ArrayList<>();
            for (OcenyPrzedmiot op : listaOcen) {
                nazwyPrzedmiotow.add(op.getNazwaPrzedmiotu());
            }
            ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, nazwyPrzedmiotow);
            adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubjects.setAdapter(adapterSpinner);

            new AlertDialog.Builder(getContext())
                    .setTitle("Dodaj ocenę do przedmiotu")
                    .setView(dialogView)
                    .setPositiveButton("Dodaj", (dialog, which) -> {
                        int pos = spinnerSubjects.getSelectedItemPosition();
                        String ocenaStr = etOcena.getText().toString().trim();
                        String data = etData.getText().toString().trim();
                        String opis = etOpis.getText().toString().trim();

                        if (pos >= 0 && !ocenaStr.isEmpty()) {
                            double wartoscOceny;
                            try {
                                wartoscOceny = Double.parseDouble(ocenaStr);
                            } catch (NumberFormatException e) {
                                wartoscOceny = 0.0;
                            }
                            OcenyPrzedmiot wybrany = listaOcen.get(pos);
                            wybrany.getOceny().add(new Ocena(wybrany.getNazwaPrzedmiotu(), wartoscOceny, data, opis));
                            adapter.notifyItemChanged(pos);
                        }
                    })
                    .setNegativeButton("Anuluj", null)
                    .show();
        });
        binding.getRoot().findViewById(R.id.button_remove_grade).setOnClickListener(v -> {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View dialogView = inflater.inflate(R.layout.usun_ocene, null);

            Spinner spinnerSubjects = dialogView.findViewById(R.id.spinnerSubjects);
            Spinner spinnerGrades = dialogView.findViewById(R.id.spinnerGrades);

            List<String> nazwyPrzedmiotow = new ArrayList<>();
            for (OcenyPrzedmiot op : listaOcen) {
                nazwyPrzedmiotow.add(op.getNazwaPrzedmiotu());
            }
            ArrayAdapter<String> adapterSubjects = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, nazwyPrzedmiotow);
            adapterSubjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubjects.setAdapter(adapterSubjects);

            spinnerSubjects.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                    OcenyPrzedmiot wybrany = listaOcen.get(position);
                    List<String> opisyOcen = new ArrayList<>();
                    List<Ocena> oceny = wybrany.getOceny();
                    for (int i = 0; i < oceny.size(); i++) {
                        Ocena ocena = oceny.get(i);
                        opisyOcen.add((i + 1) + ": " + ocena.getWartosc() + ", " + ocena.getData());
                    }
                    ArrayAdapter<String> adapterGrades = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, opisyOcen);
                    adapterGrades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerGrades.setAdapter(adapterGrades);
                }
                @Override
                public void onNothingSelected(android.widget.AdapterView<?> parent) {}
            });

            if (!listaOcen.isEmpty()) {
                List<String> opisyOcen = new ArrayList<>();
                List<Ocena> oceny = listaOcen.get(0).getOceny();
                for (int i = 0; i < oceny.size(); i++) {
                    Ocena ocena = oceny.get(i);
                    opisyOcen.add((i + 1) + ": " + ocena.getWartosc() + ", " + ocena.getData());
                }
                ArrayAdapter<String> adapterGrades = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, opisyOcen);
                adapterGrades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGrades.setAdapter(adapterGrades);
            }

            new AlertDialog.Builder(getContext())
                    .setTitle("Usuń wybraną ocenę")
                    .setView(dialogView)
                    .setPositiveButton("Usuń", (dialog, which) -> {
                        int posPrzedmiot = spinnerSubjects.getSelectedItemPosition();
                        int posOcena = spinnerGrades.getSelectedItemPosition();
                        if (posPrzedmiot >= 0 && posOcena >= 0) {
                            OcenyPrzedmiot wybrany = listaOcen.get(posPrzedmiot);
                            if (posOcena < wybrany.getOceny().size()) {
                                wybrany.getOceny().remove(posOcena);
                                adapter.notifyItemChanged(posPrzedmiot);
                            }
                        }
                    })
                    .setNegativeButton("Anuluj", null)
                    .show();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}