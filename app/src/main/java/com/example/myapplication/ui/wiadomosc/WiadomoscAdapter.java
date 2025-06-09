package com.example.myapplication.ui.wiadomosc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.wiadomosc.Wiadomosc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WiadomoscAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public static class SectionedItem {
        public String date;
        public Wiadomosc wiadomosc;
        public boolean isHeader;
        public SectionedItem(String date) { this.date = date; this.isHeader = true; }
        public SectionedItem(Wiadomosc wiadomosc) { this.wiadomosc = wiadomosc; this.isHeader = false; }
    }

    private final List<SectionedItem> items;

    public WiadomoscAdapter(List<Wiadomosc> wiadomosci) {
        this.items = new ArrayList<>();
        Map<String, List<Wiadomosc>> grouped = new LinkedHashMap<>();
        for (Wiadomosc w : wiadomosci) {
            grouped.computeIfAbsent(w.getData(), k -> new ArrayList<>()).add(w);
        }
        for (Map.Entry<String, List<Wiadomosc>> entry : grouped.entrySet()) {
            items.add(new SectionedItem(entry.getKey()));
            for (Wiadomosc w : entry.getValue()) {
                items.add(new SectionedItem(w));
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_section_header, parent, false);
            return new HeaderViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wiadomosc_item, parent, false);
            return new ItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionedItem item = items.get(position);
        if (item.isHeader) {
            ((HeaderViewHolder) holder).date.setText(item.date);
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
            Wiadomosc wiadomosc = item.wiadomosc;
            ItemViewHolder vh = (ItemViewHolder) holder;
            vh.title.setText(wiadomosc.getTitle());
            vh.sender.setText(wiadomosc.getSender());
            vh.message.setText(wiadomosc.getMessage());
            vh.arrow.setOnClickListener(new View.OnClickListener() {
                private boolean expanded = false;
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    vh.message.setVisibility(expanded ? View.VISIBLE : View.GONE);
                    vh.arrow.setImageResource(expanded ? R.drawable.arrow_down : R.drawable.arrow_right);
                }
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
        TextView sender;
        TextView title;
        TextView message;
        ImageView arrow;
        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.ocena_przedmiot);
            sender = itemView.findViewById(R.id.ocena_data);
            message = itemView.findViewById(R.id.ocena_message);
            arrow = itemView.findViewById(R.id.arrow);
        }
    }
}