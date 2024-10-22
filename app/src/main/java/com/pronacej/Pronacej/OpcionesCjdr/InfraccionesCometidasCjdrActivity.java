package com.pronacej.Pronacej.OpcionesCjdr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.ResultadosCjrd.ResultadosDelitoCjdr;
import com.pronacej.Pronacej.ResultadosCjrd.ResultadosSituacionJuridicaActualCjdr;
import com.pronacej.Pronacej.ResultadosCjrd.ResultadosSituacionJuridicaIngresoCjdr;

public class InfraccionesCometidasCjdrActivity extends AppCompatActivity {

    private int autoaborto;
    private int exposicion_peligro;
    private int feminicidio;
    private int homicidio_c;
    private int homicidio_s;
    private int lesiones_g;
    private int lesiones_l;
    private int parricidio;
    private int sicariato;
    private int otros;
    private int juridica_sentenciado;
    private int juridica_procesado;
    private int ingreso_sentenciado;
    private int ingreso_procesado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infraccion_cjdr);

        Intent intent = getIntent();
        autoaborto = intent.getIntExtra("autoaborto", 0);
        exposicion_peligro = intent.getIntExtra("exposicion_peligro", 0);
        feminicidio = intent.getIntExtra("feminicidio", 0);
        homicidio_c = intent.getIntExtra("homicidio_c", 0);
        homicidio_s = intent.getIntExtra("homicidio_s", 0);
        lesiones_g = intent.getIntExtra("lesiones_g", 0);
        lesiones_l = intent.getIntExtra("lesiones_l", 0);
        parricidio = intent.getIntExtra("parricidio", 0);
        sicariato = intent.getIntExtra("sicariato", 0);
        otros = intent.getIntExtra("otros", 0);
        juridica_sentenciado = intent.getIntExtra("juridica_sentenciado", 0);
        juridica_procesado = intent.getIntExtra("juridica_procesado", 0);
        ingreso_sentenciado = intent.getIntExtra("ingreso_sentenciado", 0);
        ingreso_procesado = intent.getIntExtra("ingreso_procesado", 0);
        // Asignar click listener a los ConstraintLayouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);
        ConstraintLayout opcion3 = findViewById(R.id.Opcion3);
        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(InfraccionesCometidasCjdrActivity.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion1 = new Intent(InfraccionesCometidasCjdrActivity.this, ResultadosDelitoCjdr.class);
                intentOpcion1.putExtra("autoaborto", autoaborto);
                intentOpcion1.putExtra("exposicion_peligro", exposicion_peligro);
                intentOpcion1.putExtra("feminicidio", feminicidio);
                intentOpcion1.putExtra("homicidio_c", homicidio_c);
                intentOpcion1.putExtra("homicidio_s", homicidio_s);
                intentOpcion1.putExtra("lesiones_g", lesiones_g);
                intentOpcion1.putExtra("lesiones_l", lesiones_l);
                intentOpcion1.putExtra("parricidio", parricidio);
                intentOpcion1.putExtra("sicariato", sicariato);
                intentOpcion1.putExtra("otros", otros);
                startActivity(intentOpcion1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion2 = new Intent(InfraccionesCometidasCjdrActivity.this, ResultadosSituacionJuridicaActualCjdr.class);
                intentOpcion2.putExtra("juridica_sentenciado", juridica_sentenciado);
                intentOpcion2.putExtra("juridica_procesado", juridica_procesado);
                startActivity(intentOpcion2);
            }
        });

        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion3 = new Intent(InfraccionesCometidasCjdrActivity.this, ResultadosSituacionJuridicaIngresoCjdr.class);
                intentOpcion3.putExtra("ingreso_sentenciado", ingreso_sentenciado);
                intentOpcion3.putExtra("ingreso_procesado", ingreso_procesado);
                startActivity(intentOpcion3);
            }
        });



    }
}
