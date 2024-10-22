package com.pronacej.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.ArrayList;

public class ResultadosSituacionEducativaActualCjdr extends AppCompatActivity {

    private int sea_estudia;
    private int sea_termino_basico;
    private int sea_termino_no_doc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_situacion_academica_actual_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosSituacionEducativaActualCjdr.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Obtener los valores de las variables desde el intent
        sea_estudia = getIntent().getIntExtra("sea_estudia", 0);
        sea_termino_basico = getIntent().getIntExtra("sea_termino_basico", 0);
        sea_termino_no_doc = getIntent().getIntExtra("sea_termino_no_doc", 0);

        int totalSEA = sea_estudia + sea_termino_basico + sea_termino_no_doc;

        // Calcular los porcentajes
        double porcentajesea_estudia = (double) sea_estudia / totalSEA * 100;
        double porcentajesea_termino_basico = (double) sea_termino_basico / totalSEA * 100;
        double porcentajesea_termino_no_doc = (double) sea_termino_no_doc / totalSEA * 100;

        ((TextView) findViewById(R.id.textViewsea_estudiaPorcentaje)).setText(String.format("%.2f%%", porcentajesea_estudia));
        ((TextView) findViewById(R.id.textViewsea_estudia)).setText("Estudia");

        ((TextView) findViewById(R.id.textViewsea_termino_basicoPorcentaje)).setText(String.format("%.2f%%", porcentajesea_termino_basico));
        ((TextView) findViewById(R.id.textViewsea_termino_basico)).setText("Basico terminado");

        ((TextView) findViewById(R.id.textViewsea_termino_no_docPorcentaje)).setText(String.format("%.2f%%", porcentajesea_termino_no_doc));
        ((TextView) findViewById(R.id.textViewsea_termino_no_doc)).setText("No doc");


        ScatterChart scatterChart = findViewById(R.id.scatterChart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, sea_estudia));
        entries.add(new Entry(1, sea_termino_basico));
        entries.add(new Entry(2, sea_termino_no_doc));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej6));


        ScatterDataSet dataSet = new ScatterDataSet(entries, "Situación Educativa Actual");
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        dataSet.setScatterShapeSize(15f);
        dataSet.setColors(colors);

        ArrayList<IScatterDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        ScatterData scatterData = new ScatterData(dataSets);
        scatterChart.setData(scatterData);

        scatterChart.getDescription().setEnabled(false);

        XAxis xAxis = scatterChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Estudia", "Terminó Básico", "Terminó No Doc"}));

        YAxis yAxis = scatterChart.getAxisLeft();
        yAxis.setDrawGridLines(false);

        scatterChart.getAxisRight().setEnabled(false);

        Legend legend = scatterChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        scatterChart.invalidate();

    }

}
