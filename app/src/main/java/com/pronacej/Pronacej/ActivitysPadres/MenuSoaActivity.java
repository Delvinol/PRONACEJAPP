package com.pronacej.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pronacej.Pronacej.FiltrosSoa.FiltroEdadSoa;
import com.pronacej.Pronacej.FiltrosSoa.FiltroEducativaSoa;
import com.pronacej.Pronacej.FiltrosSoa.FiltroInfraccionSoa;
import com.pronacej.Pronacej.FiltrosSoa.FiltroLaboralSoa;
import com.pronacej.Pronacej.OpcionesSoa.PoblacionSoaActivity;
import com.pronacej.Pronacej.OpcionesSoa.TratamientoDiferenciadoSoaActivity;
import com.pronacej.Pronacej.R;



public class MenuSoaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_soa);

        ConstraintLayout opcionOcho = findViewById(R.id.Opcion8);
        ConstraintLayout opcionUno = findViewById(R.id.Opcion1);
        ConstraintLayout opcionDos = findViewById(R.id.Opcion2);
        ConstraintLayout opcionTres = findViewById(R.id.Opcion3);
        ConstraintLayout opcionCuatro = findViewById(R.id.Opcion4);
        ConstraintLayout opcionCinco = findViewById(R.id.Opcion5);

        addTouchFeedback(opcionUno);
        addTouchFeedback(opcionDos);
        addTouchFeedback(opcionTres);
        addTouchFeedback(opcionCuatro);
        addTouchFeedback(opcionCinco);
        addTouchFeedback(opcionOcho);



        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(MenuSoaActivity.this, CategoriaMenu.class);
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



        // Eventos que abrirá las otras actividades
        opcionUno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, FiltroEdadSoa.class);
                startActivity(intent);
            }
        });

        opcionDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, TratamientoDiferenciadoSoaActivity.class);
                startActivity(intent);
            }
        });

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, FiltroEducativaSoa.class);
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, FiltroLaboralSoa.class);
                startActivity(intent);
            }
        });

        opcionCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, FiltroInfraccionSoa.class);
                startActivity(intent);
            }
        });

        opcionOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, PoblacionSoaActivity.class);
                startActivity(intent);
            }
        });

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