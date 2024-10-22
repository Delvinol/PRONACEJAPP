package com.pronacej.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pronacej.Pronacej.FiltroSoaTotal.FiltroEducativaTotalSoa;
import com.pronacej.Pronacej.FiltroSoaTotal.FiltroInfraccionTotalSoa;
import com.pronacej.Pronacej.FiltroSoaTotal.FiltroLaboralTotalSoa;
import com.pronacej.Pronacej.FiltroSoaTotal.FiltroTratamientoTotalSoa;
import com.pronacej.Pronacej.FiltrosSoa.FiltroEdadSoa;
import com.pronacej.Pronacej.FiltrosSoa.FiltroSimpleSoa;
import com.pronacej.Pronacej.R;

public class MenuTotalesSoaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_totales_soa);

        ConstraintLayout opcionOcho = findViewById(R.id.Opcion8);
        ConstraintLayout opcionUno = findViewById(R.id.Opcion1);
        ConstraintLayout opcionDos = findViewById(R.id.Opcion2);
        ConstraintLayout opcionTres = findViewById(R.id.Opcion3);
        ConstraintLayout opcionCuatro = findViewById(R.id.Opcion4);
        ConstraintLayout opcionCinco = findViewById(R.id.Opcion5);


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
                Intent intent = new Intent(MenuTotalesSoaActivity.this, FiltroEdadSoa.class);
                startActivity(intent);
            }
        });

        opcionDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesSoaActivity.this, FiltroTratamientoTotalSoa.class);
                startActivity(intent);
            }
        });

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesSoaActivity.this, FiltroEducativaTotalSoa.class);
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesSoaActivity.this, FiltroLaboralTotalSoa.class);
                startActivity(intent);
            }
        });

        opcionCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesSoaActivity.this, FiltroInfraccionTotalSoa.class);
                startActivity(intent);
            }
        });

        opcionOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTotalesSoaActivity.this, FiltroSimpleSoa.class);
                startActivity(intent);
            }
        });

    }
}
