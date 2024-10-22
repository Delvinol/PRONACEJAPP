package com.pronacej.Pronacej.ResultadosSoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSituacionJuridicaIngresoSoa extends AppCompatActivity {

    private int ingreso_sentenciado;
    private int ingreso_procesado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_situacion_juridica_ingreso_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosSituacionJuridicaIngresoSoa.this, CategoriaMenu.class);
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
        ingreso_sentenciado = intent.getIntExtra("ingreso_sentenciado", 0);
        ingreso_procesado = intent.getIntExtra("ingreso_procesado", 0);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);



// Calcular el total de participantes
        int totalParticipantes = ingreso_sentenciado + ingreso_procesado;

// Calcular los porcentajes
        double porcentajeSentenciado = (double) ingreso_sentenciado / totalParticipantes * 100;
        double porcentajeProcesado = (double) ingreso_procesado / totalParticipantes * 100;

        ((TextView) findViewById(R.id.textViewingreso_sentenciadoPorcentaje)).setText(String.format("%.2f%%", porcentajeSentenciado));
        ((TextView) findViewById(R.id.textViewingreso_sentenciado)).setText("Ingreso de sentenciado");

        ((TextView) findViewById(R.id.textViewingreso_procesadoPorcentaje)).setText(String.format("%.2f%%", porcentajeProcesado));
        ((TextView) findViewById(R.id.textViewingreso_procesado)).setText("Ingreso de procesado");


        // Configurar los datos para el gráfico
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, ingreso_sentenciado));
        entries.add(new BarEntry(1f, ingreso_procesado));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej3));


        BarDataSet barDataSet = new BarDataSet(entries, "Situación Jurídica Ingreso");
        barDataSet.setColors(colors);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);

        // Configurar ejes y leyenda
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        Legend legend = barChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Establecer los datos en el gráfico
        barChart.setData(barData);
        barChart.invalidate();
    }
}
