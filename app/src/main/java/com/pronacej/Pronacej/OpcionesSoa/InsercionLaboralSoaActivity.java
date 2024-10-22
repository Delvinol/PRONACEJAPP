package com.pronacej.Pronacej.OpcionesSoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.ResultadosSoa.ResultadosSeguroMedicoSoa;
import com.pronacej.Pronacej.ResultadosSoa.ResultadosSituacionLaboralActualSoa;

public class InsercionLaboralSoaActivity extends AppCompatActivity {

    private int seguro_sis;
    private int seguro_essalud;
    private int seguro_particular;
    private int seguro_ninguno;
    private int trabaja_si;
    private int trabaja_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insercion_laboral_soa);
        Intent intent = getIntent();
        seguro_sis = intent.getIntExtra("seguro_sis", 0);
        seguro_essalud = intent.getIntExtra("seguro_essalud", 0);
        seguro_particular = intent.getIntExtra("seguro_particular", 0);
        seguro_ninguno = intent.getIntExtra("seguro_ninguno", 0);
        trabaja_si = intent.getIntExtra("trabaja_si", 0);
        trabaja_no = intent.getIntExtra("trabaja_no", 0);
        // Asignar click listener a los ConstraintLayouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);

        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion1 = new Intent(InsercionLaboralSoaActivity.this, ResultadosSituacionLaboralActualSoa.class);
                intentOpcion1.putExtra("trabaja_si", trabaja_si);
                intentOpcion1.putExtra("trabaja_no", trabaja_no);
                startActivity(intentOpcion1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion2 = new Intent(InsercionLaboralSoaActivity.this, ResultadosSeguroMedicoSoa.class);

                intentOpcion2.putExtra("seguro_sis", seguro_sis);
                intentOpcion2.putExtra("seguro_essalud", seguro_essalud);
                intentOpcion2.putExtra("seguro_particular", seguro_particular);
                intentOpcion2.putExtra("seguro_ninguno", seguro_ninguno);
                startActivity(intentOpcion2);
            }
        });
    }
}

