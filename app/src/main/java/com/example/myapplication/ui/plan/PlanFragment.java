package com.example.myapplication.ui.plan;

import android.app.DatePickerDialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.FragmentPlanBinding;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class PlanFragment extends Fragment {

    private PlanViewModel planViewModel;
    private FragmentPlanBinding binding;
    private ZajecieAdapter adapter;
    private int currentIndex = 0;
    private List<Zajecie> planData = new ArrayList<>();
    private List<String> dates = new ArrayList<>();
    private Calendar selectedDate = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        binding = FragmentPlanBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepareData();
        prepareDates();

        RecyclerView recyclerView = binding.planRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if (position > 0) {
                    outRect.top = (int) (view.getContext().getResources().getDisplayMetrics().density * 8);
                }
            }
        });

        List<MaterialButton> buttons = Arrays.asList(binding.buttonDate1, binding.buttonDate2, binding.buttonDate3);
        updateDateButtons();
        buttons.get(0).setChecked(true);

        View.OnClickListener buttonListener = v -> {
            int index = buttons.indexOf(v);
            if (index == 0) {
                showDatePicker();
            } else if (index > 0) {
                shiftDate(index);
            }
        };
        for (MaterialButton b : buttons) {
            b.setOnClickListener(buttonListener);
        }

        binding.buttonPrev.setOnClickListener(v -> shiftDate(-1));
        binding.buttonNext.setOnClickListener(v -> shiftDate(1));

        // Obsługa przycisku "Dzisiaj"
        binding.buttonToday.setOnClickListener(v -> {
            Calendar today = Calendar.getInstance();
            selectedDate.set(Calendar.YEAR, today.get(Calendar.YEAR));
            selectedDate.set(Calendar.MONTH, today.get(Calendar.MONTH));
            selectedDate.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));
            updateDateButtons();
            updatePlanForSelectedDate();
        });

        updatePlanForSelectedDate();
    }

    private void showDatePicker() {
        int year = selectedDate.get(Calendar.YEAR);
        int month = selectedDate.get(Calendar.MONTH);
        int day = selectedDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(requireContext(), (view, y, m, d) -> {
            selectedDate.set(y, m, d);
            updateDateButtons();
            updatePlanForSelectedDate();
        }, year, month, day);
        dialog.show();
    }

    private void shiftDate(int offset) {
        selectedDate.add(Calendar.DAY_OF_MONTH, offset);
        updateDateButtons();
        updatePlanForSelectedDate();
    }

    private void updateDateButtons() {
        List<MaterialButton> buttons = Arrays.asList(binding.buttonDate1, binding.buttonDate2, binding.buttonDate3);
        for (int i = 0; i < buttons.size(); i++) {
            Calendar cal = (Calendar) selectedDate.clone();
            cal.add(Calendar.DAY_OF_MONTH, i);
            buttons.get(i).setText(dateFormat.format(cal.getTime()));
            buttons.get(i).setChecked(i == 0);
        }
    }

    private void updatePlanForSelectedDate() {
        String dateStr = dateFormat.format(selectedDate.getTime());
        List<Zajecie> zajecia = new ArrayList<>();
        for (Zajecie z : planData) {
            if (z.getData().equals(dateStr)) {
                zajecia.add(z);
            }
        }
        adapter = new ZajecieAdapter(zajecia);
        binding.planRecyclerView.setAdapter(adapter);
    }

    private void prepareDates() {
        dates.clear();
        dates.add("01.05");
        dates.add("02.05");
        dates.add("03.05");
    }

    private void selectDay(int index) {
        currentIndex = index;
        List<MaterialButton> buttons = Arrays.asList(binding.buttonDate1, binding.buttonDate2, binding.buttonDate3);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setChecked(i == index);
        }
        // Poprawka: wyświetl zajęcia tylko z wybranej daty
        String dateStr = dateFormat.format(selectedDate.getTime());
        List<Zajecie> zajecia = new ArrayList<>();
        for (Zajecie z : planData) {
            if (z.getData().equals(dateStr)) {
                zajecia.add(z);
            }
        }
        adapter = new ZajecieAdapter(zajecia);
        binding.planRecyclerView.setAdapter(adapter);
    }

    private void prepareData() {
        planData = ZajeciaDataProvider.getPlanData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}