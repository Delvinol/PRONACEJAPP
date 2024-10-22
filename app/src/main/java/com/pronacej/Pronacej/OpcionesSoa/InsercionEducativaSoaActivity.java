package com.pronacej.Pronacej.OpcionesSoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.ResultadosSoa.ResultadoCentroEducativoSoa;
import com.pronacej.Pronacej.ResultadosSoa.ResultadoReinsercionEducativaSoa;
import com.pronacej.Pronacej.ResultadosSoa.ResultadosSituacionEducativaActualSoa;

public class InsercionEducativaSoaActivity extends AppCompatActivity {

    private int sea_estudia;
    private int sea_termino_basico;
    private int sea_termino_no_doc;
    private int reinsercion_educativa;
    private int insercion_productiva;
    private int continuidad_edu;
    private int apoyo_regularizar;
    private int cebr;
    private int ceba;
    private int cepre;
    private int academia;
    private int cetpro;
    private int instituto;
    private int universidad;
    private int ninguno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insercion_educativa_soa);

        Intent intent = getIntent();
        sea_estudia = intent.getIntExtra("sea_estudia", 0);
        sea_termino_basico = intent.getIntExtra("sea_termino_basico", 0);
        sea_termino_no_doc = intent.getIntExtra("sea_termino_no_doc", 0);
        reinsercion_educativa = intent.getIntExtra("reinsercion_educativa", 0);
        insercion_productiva = intent.getIntExtra("insercion_productiva", 0);
        continuidad_edu = intent.getIntExtra("continuidad_edu", 0);
        apoyo_regularizar = intent.getIntExtra("apoyo_regularizar", 0);
        cebr = intent.getIntExtra("cebr", 0);
        ceba = intent.getIntExtra("ceba", 0);
        cepre = intent.getIntExtra("cepre", 0);
        academia = intent.getIntExtra("academia", 0);
        cetpro = intent.getIntExtra("cetpro", 0);
        instituto = intent.getIntExtra("instituto", 0);
        universidad = intent.getIntExtra("universidad", 0);
        ninguno = intent.getIntExtra("ninguno", 0);
        // Asignar click listener a los ConstraintLayouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);
        ConstraintLayout opcion3 = findViewById(R.id.Opcion3);
        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(InsercionEducativaSoaActivity.this, CategoriaMenu.class);
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
                Intent intentOpcion1 = new Intent(InsercionEducativaSoaActivity.this, ResultadosSituacionEducativaActualSoa.class);
                intentOpcion1.putExtra("sea_estudia", sea_estudia);
                intentOpcion1.putExtra("sea_termino_basico", sea_termino_basico);
                intentOpcion1.putExtra("sea_termino_no_doc", sea_termino_no_doc);
                startActivity(intentOpcion1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion2 = new Intent(InsercionEducativaSoaActivity.this, ResultadoReinsercionEducativaSoa.class);
                intentOpcion2.putExtra("reinsercion_educativa", reinsercion_educativa);
                intentOpcion2.putExtra("insercion_productiva", insercion_productiva);
                intentOpcion2.putExtra("continuidad_edu", continuidad_edu);
                intentOpcion2.putExtra("apoyo_regularizar", apoyo_regularizar);
                startActivity(intentOpcion2);
            }
        });

        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion3 = new Intent(InsercionEducativaSoaActivity.this, ResultadoCentroEducativoSoa.class);
                intentOpcion3.putExtra("cebr", cebr);
                intentOpcion3.putExtra("ceba", ceba);
                intentOpcion3.putExtra("cepre", cepre);
                intentOpcion3.putExtra("academia", academia);
                intentOpcion3.putExtra("cetpro", cetpro);
                intentOpcion3.putExtra("instituto", instituto);
                intentOpcion3.putExtra("universidad", universidad);
                intentOpcion3.putExtra("ninguno", ninguno);
                startActivity(intentOpcion3);
            }
        });
    }

}