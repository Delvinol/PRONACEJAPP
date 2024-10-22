package com.pronacej.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pronacej.Pronacej.R;

public class OpcionesCentrosJuvenilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_centros_juveniles);

        ConstraintLayout buttonUserA = findViewById(R.id.Opcion1);
        ConstraintLayout buttonUserB = findViewById(R.id.Opcion2);
        ConstraintLayout buttonUserC = findViewById(R.id.Opcion35);
        //ConstraintLayout buttonUserD = findViewById(R.id.Opcion4);

        addTouchFeedback(buttonUserA);
        addTouchFeedback(buttonUserB);
        addTouchFeedback(buttonUserC);


        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(OpcionesCentrosJuvenilesActivity.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });




        // Recuperar el nombre del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName", "Usuario");

        // Actualizar todos los TextView4 con el nombre del usuario
        TextView textView4 = findViewById(R.id.textView4);
        textView4.setText("Bienvenido\n" + userName);
        buttonUserA.setOnClickListener(view -> {
            startActivity(new Intent(OpcionesCentrosJuvenilesActivity.this, MenuCjdrActivity.class));
            // Acción cuando se presiona el botón Usuario A
            Toast.makeText(OpcionesCentrosJuvenilesActivity.this, "Seleccionaste CJDR", Toast.LENGTH_SHORT).show();
        });

        buttonUserB.setOnClickListener(view -> {
            startActivity(new Intent(OpcionesCentrosJuvenilesActivity.this, MenuSoaActivity.class));
            // Acción cuando se presiona el botón Usuario B
            Toast.makeText(OpcionesCentrosJuvenilesActivity.this, "Seleccionaste SOA", Toast.LENGTH_SHORT).show();
        });

        buttonUserC.setOnClickListener(view -> {
            startActivity(new Intent(OpcionesCentrosJuvenilesActivity.this, MenuPaspeActivity.class));
            // Acción cuando se presiona el botón Usuario C
            Toast.makeText(OpcionesCentrosJuvenilesActivity.this, "Seleccionaste PASPE", Toast.LENGTH_SHORT).show();
        });

        /*buttonUserD.setOnClickListener(view -> {
            startActivity(new Intent(OpcionesCentrosJuvenilesActivity.this, MenuTotalesSoaActivity.class));
            // Acción cuando se presiona el botón Usuario C
            Toast.makeText(OpcionesCentrosJuvenilesActivity.this, "Seleccionaste totales SOA", Toast.LENGTH_SHORT).show();
        });*/
    }
    private void addTouchFeedback(View view) {
        view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setAlpha(0.7f);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    v.setAlpha(1.0f);
                    break;
            }
            return false;
        });
    }
}
