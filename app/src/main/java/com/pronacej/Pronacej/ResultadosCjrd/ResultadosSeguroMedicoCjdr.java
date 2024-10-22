package com.pronacej.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;


import java.util.ArrayList;
import java.util.List;

public class ResultadosSeguroMedicoCjdr extends AppCompatActivity {

    private int seguro_sis;
    private int seguro_essalud;
    private int seguro_particular;
    private int seguro_ninguno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_seguro_medico_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosSeguroMedicoCjdr.this, CategoriaMenu.class);
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
        seguro_sis = getIntent().getIntExtra("seguro_sis", 0);
        seguro_essalud = getIntent().getIntExtra("seguro_essalud", 0);
        seguro_particular = getIntent().getIntExtra("seguro_particular", 0);
        seguro_ninguno = getIntent().getIntExtra("seguro_ninguno", 0);

        int totalSeguro = seguro_essalud + seguro_ninguno + seguro_particular + seguro_sis;

        // Calcular los porcentajes
        double porcentajesis = (double) seguro_sis / totalSeguro * 100;
        double porcentajeessalud = (double) seguro_essalud / totalSeguro * 100;
        double porcentajeparticular = (double) seguro_particular / totalSeguro * 100;
        double porcentajeninguno = (double) seguro_ninguno / totalSeguro * 100;

        ((TextView) findViewById(R.id.textViewseguro_sisPorcentaje)).setText(String.format("%.2f%%", porcentajesis));
        ((TextView) findViewById(R.id.textViewseguro_sis)).setText("SIS");

        ((TextView) findViewById(R.id.textViewseguro_essaludPorcentaje)).setText(String.format("%.2f%%", porcentajeessalud));
        ((TextView) findViewById(R.id.textViewseguro_essalud)).setText("Essalud");

        ((TextView) findViewById(R.id.textViewseguro_ningunoPorcentaje)).setText(String.format("%.2f%%", porcentajeninguno));
        ((TextView) findViewById(R.id.textViewseguro_ninguno)).setText("Ninguno");

        ((TextView) findViewById(R.id.textViewseguro_particularPorcentaje)).setText(String.format("%.2f%%", porcentajeparticular));
        ((TextView) findViewById(R.id.textViewseguro_particular)).setText("Particular");

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, seguro_sis));
        entries.add(new BarEntry(1f, seguro_essalud));
        entries.add(new BarEntry(2f, seguro_particular));
        entries.add(new BarEntry(3f, seguro_ninguno));


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej4));

        // Crear el conjunto de datos del gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "Seguro Médico");
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        // Configurar la leyenda
        Legend legend = barChart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda

        // Configurar el eje X
        barChart.getXAxis().setEnabled(true); // Habilitar el eje X

        // Agregar los datos al gráfico de barras
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.invalidate(); // Refrescar el gráfico


    }
}
