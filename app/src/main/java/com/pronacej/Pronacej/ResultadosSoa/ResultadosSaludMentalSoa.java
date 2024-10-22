package com.pronacej.Pronacej.ResultadosSoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSaludMentalSoa extends AppCompatActivity {

    private int salud_si;
    private int salud_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_salud_mental_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosSaludMentalSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Obtener los valores de salud mental desde el intent
        salud_si = getIntent().getIntExtra("salud_si", 0);
        salud_no = getIntent().getIntExtra("salud_no", 0);

        int totalSalud = salud_no + salud_si;

        // Calcular los porcentajes
        double porcentajesalud_si = (double) salud_si / totalSalud * 100;
        double porcentajesalud_no = (double) salud_no / totalSalud * 100;

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de pastel
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(salud_si, "Participa"));
        entries.add(new PieEntry(salud_no, "No participa"));

        // Crear el conjunto de datos del gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "Estado de Salud Mental");
        dataSet.setColors(getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_red_light));

        // Configurar el tamaño del texto dentro del gráfico de pastel
        dataSet.setValueTextSize(12f);

        // Configurar la leyenda
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda

        // Agregar los datos al gráfico de pastel
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // Refrescar el gráfico


        ((TextView) findViewById(R.id.textViewsalud_siPorcentaje)).setText(String.format("%.2f%%", porcentajesalud_si));
        ((TextView) findViewById(R.id.textViewsalud_si)).setText("Participa");


        ((TextView) findViewById(R.id.textViewsalud_noPorcentaje)).setText(String.format("%.2f%%", porcentajesalud_no));
        ((TextView) findViewById(R.id.textViewsalud_no)).setText("No participa");



    }
}
