package com.example.myapplication.ui.wiadomosc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.myapplication.R;

public class WiadomoscTileView extends LinearLayout {

    private boolean expanded = false;
    private TextView message;
    private ImageView arrow;

    public WiadomoscTileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.ogloszenie_item, this, true);

        arrow = findViewById(R.id.arrow);
        message = findViewById(R.id.ocena_message);

        findViewById(R.id.kafelek_container).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded = !expanded;
                if (expanded) {
                    message.setVisibility(VISIBLE);
                    arrow.setImageResource(R.drawable.arrow_down);
                } else {
                    message.setVisibility(GONE);
                    arrow.setImageResource(R.drawable.arrow_right);
                }
            }
        });
    }
}
