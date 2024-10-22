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

import com.pronacej.Pronacej.InfoPublica.FiltroAnual;
import com.pronacej.Pronacej.R;
public class OpcionPronacejDigital extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pronacejdigital);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ConstraintLayout categoriaUno = findViewById(R.id.Opcion6);
        ConstraintLayout categoriaDos = findViewById(R.id.Opcion7);
        ConstraintLayout categoriaTres = findViewById(R.id.Opcion9);

        addTouchFeedback(categoriaUno);
        addTouchFeedback(categoriaDos);
        addTouchFeedback(categoriaTres);


        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(OpcionPronacejDigital.this, CategoriaMenu.class);
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

        // Estableciendo las funciones del botón
        categoriaUno.setOnClickListener(view -> {
            //Intent para pasar a otro activity
            Intent intent = new Intent(OpcionPronacejDigital.this, AccionesSeguridadCjdrActivity.class);
            //Llamado a la acción de intent
            startActivity(intent);
        });


        categoriaDos.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(OpcionPronacejDigital.this, FiltroAnual.class);  //para 2023 es ResultadoDepartamentos ; para 2024 es InversionesPublicaActivity
            // Llamado a la acción de intent
            startActivity(intent);
        });

        categoriaTres.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(OpcionPronacejDigital.this, OpcionesRecursosActivity.class);
            // Llamado a la acción de intent
            startActivity(intent);
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
