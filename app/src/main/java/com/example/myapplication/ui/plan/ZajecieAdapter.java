package com.example.myapplication.ui.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class ZajecieAdapter extends RecyclerView.Adapter<ZajecieAdapter.ViewHolder> {
    private List<Zajecie> zajecia;

    public ZajecieAdapter(List<Zajecie> zajecia) {
        this.zajecia = zajecia;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.zajecie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Zajecie z = zajecia.get(position);
        holder.nazwa.setText(z.getNazwa() + " (" + z.getTyp() + ")");
        holder.prowadzacy.setText(z.getProwadzacy());
        holder.godzina.setText(z.getPoczatek() + " - " + z.getKoniec());
        holder.sala.setText(z.getSala());
    }

    @Override
    public int getItemCount() {
        return zajecia.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nazwa;
        TextView prowadzacy;
        TextView godzina;
        TextView sala;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nazwa = itemView.findViewById(R.id.zajecie_nazwa);
            prowadzacy = itemView.findViewById(R.id.zajecie_prowadzacy);
            godzina = itemView.findViewById(R.id.zajecie_godzina);
            sala = itemView.findViewById(R.id.zajecie_sala);
        }
    }
}
