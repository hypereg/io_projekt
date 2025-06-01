package com.example.myapplication.ui.oceny;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class OcenyAdapter extends RecyclerView.Adapter<OcenyAdapter.OcenaViewHolder> {

    private List<Ocena> listaOcen;

    public OcenyAdapter(List<Ocena> listaOcen) {
        this.listaOcen = listaOcen;
    }

    public static class OcenaViewHolder extends RecyclerView.ViewHolder {
        TextView przedmiot;
        TextView wartosc;
        TextView data;

        public OcenaViewHolder(@NonNull View itemView) {
            super(itemView);
            przedmiot = itemView.findViewById(R.id.ocena_przedmiot);
            wartosc = itemView.findViewById(R.id.ocena_wartosc);
            data = itemView.findViewById(R.id.ocena_data);
        }
    }

    @NonNull
    @Override
    public OcenaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ocena_item, parent, false); // ← tutaj wskazujesz layout
        return new OcenaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenaViewHolder holder, int position) {
        Ocena ocena = listaOcen.get(position);
        holder.przedmiot.setText(ocena.getPrzedmiot());
        holder.wartosc.setText(ocena.getWartosc());
        holder.data.setText(ocena.getData());
    }

    @Override
    public int getItemCount() {
        return listaOcen.size();
    }
}

