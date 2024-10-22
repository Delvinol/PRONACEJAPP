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

import com.pronacej.Pronacej.FiltroFuga;
import com.pronacej.Pronacej.FiltroIntentoFugaTemporal;
import com.pronacej.Pronacej.FiltroTraslados;
import com.pronacej.Pronacej.InfoPublica.MegaoperativosGrafico;
import com.pronacej.Pronacej.InfoPublica.MotinGrafico;
import com.pronacej.Pronacej.InfoPublica.OperativosGrafico;
import com.pronacej.Pronacej.InfoPublica.ReyertasGrafico;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.Utils.FiltroFugaTemporal;

;

public class AccionesSeguridadCjdrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acciones_seguridad);

        ConstraintLayout opcionOcho= findViewById(R.id.Opcion8);
        ConstraintLayout opcionUno = findViewById(R.id.Opcion1);
        //ConstraintLayout opcionDos = findViewById(R.id.Opcion2);
        ConstraintLayout opcionTres = findViewById(R.id.Opcion3);
        ConstraintLayout opcionCuatro = findViewById(R.id.Opcion4);
        ConstraintLayout opcionCinco = findViewById(R.id.Opcion5);
        ConstraintLayout opcionSeis = findViewById(R.id.Opcion6);
        ConstraintLayout opcionOnce = findViewById(R.id.Opcion11);
        ConstraintLayout opcionDiez = findViewById(R.id.Opcion10);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        addTouchFeedback(opcionOcho);
        addTouchFeedback(opcionUno);
        addTouchFeedback(opcionCuatro);
        addTouchFeedback(opcionTres);
        addTouchFeedback(opcionCinco);
        addTouchFeedback(opcionSeis);
        addTouchFeedback(opcionOnce);
        addTouchFeedback(opcionDiez);


        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(AccionesSeguridadCjdrActivity.this, CategoriaMenu.class);
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
        opcionUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccionesSeguridadCjdrActivity.this, FiltroTraslados.class);
                startActivity(intent);
            }
        });


       // opcionDos.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   Intent intent = new Intent(AccionesSeguridadCjdrActivity.this, RecursosHumanosGrafico.class);
               // startActivity(intent);
           // }
        //});

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccionesSeguridadCjdrActivity.this, ReyertasGrafico.class);
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccionesSeguridadCjdrActivity.this, OperativosGrafico.class);
                startActivity(intent);
            }
        });

        opcionCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccionesSeguridadCjdrActivity.this, MegaoperativosGrafico.class);
                startActivity(intent);
            }
        });

        opcionSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccionesSeguridadCjdrActivity.this, FiltroIntentoFugaTemporal.class);
                startActivity(intent);
            }
        });

        opcionOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccionesSeguridadCjdrActivity.this, FiltroFuga.class);
                startActivity(intent);
            }
        });

        opcionOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccionesSeguridadCjdrActivity.this, FiltroFugaTemporal.class);
                startActivity(intent);
            }
        });

        opcionDiez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccionesSeguridadCjdrActivity.this, MotinGrafico.class);
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