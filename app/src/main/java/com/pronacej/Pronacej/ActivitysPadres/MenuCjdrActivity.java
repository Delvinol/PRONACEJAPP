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

import com.pronacej.Pronacej.FiltrosCjdr.FiltroEdadCjdr;
import com.pronacej.Pronacej.FiltrosCjdr.FiltroEducativaCjdr;
import com.pronacej.Pronacej.FiltrosCjdr.FiltroInfraccionCjdr;
import com.pronacej.Pronacej.FiltrosCjdr.FiltroLaboralCjdr;
import com.pronacej.Pronacej.OpcionesCjdr.PoblacionCjdrActivity;
import com.pronacej.Pronacej.OpcionesCjdr.TratamientoDiferenciadoCjdrActivity;
import com.pronacej.Pronacej.R;

;

public class MenuCjdrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cjdr);

        // Creando widget del mismo tipo del layout que contiene las opciones y referenciándolos por su id
        ConstraintLayout opcionUno = findViewById(R.id.Opcion1);
        ConstraintLayout opcionDos = findViewById(R.id.Opcion2);
        ConstraintLayout opcionTres = findViewById(R.id.Opcion3);
        ConstraintLayout opcionCuatro = findViewById(R.id.Opcion4);
        ConstraintLayout opcionCinco = findViewById(R.id.Opcion5);
        ConstraintLayout opcionOcho= findViewById(R.id.Opcion8);

        addTouchFeedback(opcionUno);
        addTouchFeedback(opcionDos);
        addTouchFeedback(opcionTres);
        addTouchFeedback(opcionCuatro);
        addTouchFeedback(opcionCinco);
        addTouchFeedback(opcionOcho);

        // Creando botones y referenciándolos con su id del layout
        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(MenuCjdrActivity.this, CategoriaMenu.class);
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
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroEdadCjdr.class);
                startActivity(intent);
            }
        });

        opcionDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, TratamientoDiferenciadoCjdrActivity.class);
                startActivity(intent);
            }
        });

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroEducativaCjdr.class);
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroLaboralCjdr.class);
                startActivity(intent);
            }
        });

        opcionCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, FiltroInfraccionCjdr.class);
                startActivity(intent);
            }
        });

        opcionOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCjdrActivity.this, PoblacionCjdrActivity.class);
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