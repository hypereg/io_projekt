package com.example.myapplication.ui.oceny;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;
import java.util.Locale;

public class OcenyPrzedmiotAdapter extends RecyclerView.Adapter<OcenyPrzedmiotAdapter.ViewHolder> {
    private List<OcenyPrzedmiot> listaOcen;

    public OcenyPrzedmiotAdapter(List<OcenyPrzedmiot> listaOcen) {
        this.listaOcen = listaOcen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ocenyprzedmiot_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OcenyPrzedmiot ocenyPrzedmiot = listaOcen.get(position);

        StringBuilder ocenyString = new StringBuilder();
        for (Ocena ocena : ocenyPrzedmiot.getOceny()) {
            ocenyString.append((int) ocena.getWartosc()).append(" ");
        }

        holder.przedmiot.setText(ocenyPrzedmiot.getNazwaPrzedmiotu());
        holder.oceny.setText(ocenyString.toString().trim());
        holder.srednia.setText(String.format(Locale.getDefault(), "%.2f", ocenyPrzedmiot.getSrednia()));

        holder.szczegoly.setVisibility(View.GONE);
        holder.szczegoly.removeAllViews();

        holder.root.setOnClickListener(v -> {
            boolean isExpanded = holder.szczegoly.getVisibility() == View.VISIBLE;

            if (isExpanded) {
                holder.szczegoly.setVisibility(View.GONE);
                holder.oceny.setVisibility(View.VISIBLE);
                holder.przedmiot.setTextAppearance(holder.itemView.getContext(), R.style.ItemTitle);
                ((LinearLayout.LayoutParams) holder.przedmiot.getLayoutParams()).gravity = Gravity.START;
            } else {
                holder.szczegoly.removeAllViews();

                for (int i = 0; i < ocenyPrzedmiot.getOceny().size(); i++) {
                    Ocena ocena = ocenyPrzedmiot.getOceny().get(i);

                    LinearLayout wiersz = new LinearLayout(holder.itemView.getContext());
                    wiersz.setOrientation(LinearLayout.HORIZONTAL);
                    wiersz.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    int backgroundColor = (i % 2 == 0) ? 0xFFF0F0F0 : 0xFFFFFFFF;
                    wiersz.setBackgroundColor(backgroundColor);
                    wiersz.setPadding(0, 8, 16, 8);


                    TextView ocenaView = new TextView(holder.itemView.getContext());
                    ocenaView.setText(String.valueOf(ocena.getWartosc()));
                    LinearLayout.LayoutParams ocenaParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                    ocenaView.setLayoutParams(ocenaParams);
                    ocenaView.setTextAppearance(holder.itemView.getContext(), R.style.ItemOpis);
                    ocenaView.setGravity(Gravity.START);
                    ocenaView.setPadding(8, 0, 0, 0);

                    TextView nazwaView = new TextView(holder.itemView.getContext());
                    nazwaView.setText(ocena.getTitle());
                    LinearLayout.LayoutParams nazwaParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
                    nazwaParams.setMarginStart(16);
                    nazwaView.setLayoutParams(nazwaParams);
                    nazwaView.setTextAppearance(holder.itemView.getContext(), R.style.ItemOpis);
                    nazwaView.setGravity(Gravity.START);

                    TextView dataView = new TextView(holder.itemView.getContext());
                    dataView.setText(ocena.getData());
                    dataView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2));
                    dataView.setTextAppearance(holder.itemView.getContext(), R.style.ItemOpis);
                    dataView.setGravity(Gravity.END);
                    dataView.setPadding(0, 0, 8, 0);

                    wiersz.addView(ocenaView);
                    wiersz.addView(nazwaView);
                    wiersz.addView(dataView);

                    holder.szczegoly.addView(wiersz);
                }


                holder.oceny.setVisibility(View.GONE);
                holder.szczegoly.setVisibility(View.VISIBLE);
                holder.przedmiot.setTextAppearance(holder.itemView.getContext(), R.style.ItemTitleExpanded);
                ((LinearLayout.LayoutParams) holder.przedmiot.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaOcen.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView przedmiot, oceny, srednia;
        LinearLayout szczegoly, root;

        public ViewHolder(View itemView) {
            super(itemView);
            przedmiot = itemView.findViewById(R.id.ocenyprzedmiot_przedmiot);
            oceny = itemView.findViewById(R.id.ocenyprzedmiot_oceny);
            srednia = itemView.findViewById(R.id.ocenyprzedmiot_srednia);
            szczegoly = itemView.findViewById(R.id.ocenyprzedmiot_szczegoly);
            root = itemView.findViewById(R.id.kafelek_root);
        }
    }
}
