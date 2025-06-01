package com.example.myapplication.ui.ogloszenie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class OgloszenieAdapter extends RecyclerView.Adapter<OgloszenieAdapter.ViewHolder> {
    private List<Ogloszenie> ogloszenia;

    public OgloszenieAdapter(List<Ogloszenie> ogloszenia) {
        this.ogloszenia = ogloszenia;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ogloszenie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ogloszenie ogloszenie = ogloszenia.get(position);

        holder.title.setText(ogloszenie.getTitle());
        holder.sender.setText(ogloszenie.getSender());
        holder.message.setText(ogloszenie.getMessage());

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            private boolean expanded = false;

            @Override
            public void onClick(View v) {
                expanded = !expanded;
                holder.message.setVisibility(expanded ? View.VISIBLE : View.GONE);
                holder.arrow.setImageResource(expanded ? R.drawable.arrow_down : R.drawable.arrow_right);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ogloszenia.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sender;
        public TextView title;
        public TextView message;
        public ImageView arrow;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.ocena_przedmiot);
            sender = itemView.findViewById(R.id.ocena_data);
            message = itemView.findViewById(R.id.ocena_message);
            arrow = itemView.findViewById(R.id.arrow);
        }
    }
}
