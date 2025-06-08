package com.example.myapplication.ui.zadania;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ZadanieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public static class SectionedItem {
        public String date;
        public Zadanie zadanie;
        public boolean isHeader;
        public SectionedItem(String date) { this.date = date; this.isHeader = true; }
        public SectionedItem(Zadanie zadanie) { this.zadanie = zadanie; this.isHeader = false; }
    }

    private final List<SectionedItem> items;
    private final OnZadanieClickListener clickListener;

    public ZadanieAdapter(List<Zadanie> zadania, OnZadanieClickListener clickListener) {
        this.items = new ArrayList<>();
        this.clickListener = clickListener;
        Map<String, List<Zadanie>> grouped = new LinkedHashMap<>();
        for (Zadanie z : zadania) {
            grouped.computeIfAbsent(z.getDataWykonania(), k -> new ArrayList<>()).add(z);
        }
        for (Map.Entry<String, List<Zadanie>> entry : grouped.entrySet()) {
            items.add(new SectionedItem(entry.getKey()));
            for (Zadanie z : entry.getValue()) {
                items.add(new SectionedItem(z));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).isHeader ? TYPE_HEADER : TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zadanie_section_header, parent, false);
            return new HeaderViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zadanie_item, parent, false);
            return new ItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionedItem item = items.get(position);
        if (item.isHeader) {
            ((HeaderViewHolder) holder).date.setText(item.date);
            // Usuwanie odstępu u góry dla pierwszej kreski
            View headerView = holder.itemView;
            if (position == 0) {
                headerView.setPadding(
                        headerView.getPaddingLeft(),
                        0,
                        headerView.getPaddingRight(),
                        headerView.getPaddingBottom()
                );
            } else {
                headerView.setPadding(
                        headerView.getPaddingLeft(),
                        dpToPx(headerView, 8),
                        headerView.getPaddingRight(),
                        headerView.getPaddingBottom()
                );
            }
        } else {
            Zadanie zadanie = item.zadanie;
            ItemViewHolder vh = (ItemViewHolder) holder;
            vh.temat.setText(zadanie.getTemat());
            vh.przedmiot.setText(zadanie.getPrzedmiot());
            // Formatowanie daty na postać dd-MM
            String data = zadanie.getDataWykonania();
            if (data != null && data.length() >= 10) {
                String dzienMiesiac = data.substring(8, 10) + "-" + data.substring(5, 7);
                vh.data.setText(dzienMiesiac);
            } else {
                vh.data.setText("");
            }
            vh.itemView.setOnClickListener(v -> {
                if (clickListener != null) clickListener.onZadanieClick(zadanie);
            });
        }
    }

    private int dpToPx(View view, int dp) {
        float density = view.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.section_date);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView temat;
        TextView przedmiot;
        TextView data;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            temat = itemView.findViewById(R.id.zadanie_temat);
            przedmiot = itemView.findViewById(R.id.zadanie_przedmiot);
            data = itemView.findViewById(R.id.zadanie_data);
        }
    }

    public interface OnZadanieClickListener {
        void onZadanieClick(Zadanie zadanie);
    }
}