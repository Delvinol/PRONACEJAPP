package com.pronacej.Pronacej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.InfoPublica.TrasladosGrafico;

public class FiltroTraslados extends AppCompatActivity {

    private Spinner yearSpinner;
    private int selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_traslados);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(FiltroTraslados.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        ButtonBack.setOnClickListener(v -> onBackPressed());

        yearSpinner = findViewById(R.id.yearSpinner);
        Button selectButton = findViewById(R.id.selectButton);

        // Configurar el adaptador del Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.year_arrayTraslado, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter);

        // Configurar el listener del Spinner
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Año por defecto si no se selecciona nada
                selectedYear = 2022;
            }
        });

        // Configurar el listener del botón
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedYear != 0) {
                    Intent intent = new Intent(FiltroTraslados.this, TrasladosGrafico.class);
                    intent.putExtra("selectedYear", selectedYear);
                    startActivity(intent);
                } else {
                    Toast.makeText(FiltroTraslados.this, "Por favor, seleccione un año.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
