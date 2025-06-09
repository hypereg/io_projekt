package com.example.myapplication.ui.zadania;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ZadanieSzczegolyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_zadanie_szczegoly);

        String zadanieId = getIntent().getStringExtra("zadanie_id");
        Zadanie zadanie = ZadaniaRepo.getInstance().getZadanieById(zadanieId);
        if (zadanie == null) {
            finish();
            return;
        }

        ImageButton backBtn = findViewById(R.id.back_button);
        TextView tytul = findViewById(R.id.tytul_zadania);
        TextView termin = findViewById(R.id.termin_zadania);
        TextView status = findViewById(R.id.status_zadania);
        TextView opis = findViewById(R.id.opis_zadania);
        Button btnZalacz = findViewById(R.id.btn_zalacz_pliki);
        Button btnOddaj = findViewById(R.id.btn_oddaj_zadanie);

        tytul.setText(zadanie.getTemat());
        termin.setText("Termin: " + zadanie.getDataWykonania());
        opis.setText(zadanie.getOpis());

        String statusText;
        String btnOddajText;
        switch (zadanie.getStatus()) {
            case PRZYSZLE:
                statusText = "Nie oddane";
                btnOddajText = "Oddaj";
                break;
            case ZALEGLE:
                statusText = "Nie oddane";
                btnOddajText = "Oddaj po czasie";
                break;
            case UKONCZONE:
                statusText = "Oddane";
                btnOddajText = "Oddane";
                break;
            default:
                statusText = "-";
                btnOddajText = "Oddaj";
        }
        status.setText("Status: " + statusText);
        btnOddaj.setText(btnOddajText);

        backBtn.setOnClickListener(v -> finish());
        // btnZalacz i btnOddaj - obsługa do uzupełnienia
    }
}
