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

import com.pronacej.Pronacej.Paspe.FiltroEdadPaspe;
import com.pronacej.Pronacej.Paspe.FiltroEducativaPaspe;
import com.pronacej.Pronacej.Paspe.FiltroLaboralPaspe;
import com.pronacej.Pronacej.Paspe.PoblacionPaspeActivity;
import com.pronacej.Pronacej.R;

public class MenuPaspeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_paspe);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ConstraintLayout opcionOcho = findViewById(R.id.Opcion8); //Resportediario o pobalcionPaspeActivity
        ConstraintLayout opcionUno = findViewById(R.id.Opcion1); //Poblacion eedad simple
        ConstraintLayout opcionCuatro = findViewById(R.id.Opcion4); // Insercion laboral
        ConstraintLayout opcionTres = findViewById(R.id.Opcion3); // Situacion educativa actual

        addTouchFeedback(opcionOcho);
        addTouchFeedback(opcionUno);
        addTouchFeedback(opcionCuatro);
        addTouchFeedback(opcionTres);

        // Recuperar el nombre del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName", "Usuario");

        // Actualizar todos los TextView4 con el nombre del usuario
        TextView textView4 = findViewById(R.id.textView4);
        textView4.setText("Bienvenido\n" + userName);
        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(MenuPaspeActivity.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });


        opcionOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPaspeActivity.this, PoblacionPaspeActivity.class); // por modificar
                startActivity(intent);
            }

        });

        opcionUno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPaspeActivity.this, FiltroEdadPaspe.class); // terminado
                startActivity(intent);
            }
        });

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPaspeActivity.this, FiltroEducativaPaspe.class); // terminado
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPaspeActivity.this, FiltroLaboralPaspe.class);
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