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

public class ResultadosSituacionJuridicaActualCjdr extends AppCompatActivity {

    private int juridica_sentenciado;
    private int juridica_procesado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_situacion_juridica_actual_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosSituacionJuridicaActualCjdr.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Obtener los valores de ingresoSentenciado y ingresoProcesado
        Intent intent = getIntent();
        juridica_sentenciado = intent.getIntExtra("juridica_sentenciado", 0);
        juridica_procesado = intent.getIntExtra("juridica_procesado", 0);

        // Calcular el total de participantes
        int totalParticipantes = juridica_sentenciado + juridica_procesado;

        // Calcular los porcentajes
        double porcentajeSentenciado = (double) juridica_sentenciado / totalParticipantes * 100;
        double porcentajeProcesado = (double) juridica_procesado / totalParticipantes * 100;

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, juridica_sentenciado));
        entries.add(new BarEntry(1f, juridica_procesado));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej3));

        // Crear el conjunto de datos del gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "Situación Jurídica Actual");
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        // Configurar la leyenda
        Legend legend = barChart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda

        // Agregar los datos al gráfico de barras
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.invalidate(); // Refrescar el gráfico

        ((TextView) findViewById(R.id.textViewjuridica_sentenciadoPorcentaje)).setText(String.format("%.2f%%", porcentajeSentenciado));
        ((TextView) findViewById(R.id.textViewjuridica_sentenciado)).setText("Sentenciado");
        ((TextView) findViewById(R.id.textViewjuridica_procesadoPorcentaje)).setText(String.format("%.2f%%", porcentajeProcesado));
        ((TextView) findViewById(R.id.textViewjuridica_procesado)).setText("Procesado");
    }
}
