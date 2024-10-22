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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class ResultadosFirmesAdelanteCjdr extends AppCompatActivity {
    private int firmes_aplica;
    private int firmes_no_aplica;
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_firme_cjdr);

        firmes_aplica = getIntent().getIntExtra("firmes_aplica", 0);
        firmes_no_aplica = getIntent().getIntExtra("firmes_no_aplica", 0);
        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        int totalFirmes = firmes_aplica + firmes_no_aplica;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalFirmes)));

        // Calcular los porcentajes
        double porcentajeAplica = (double) firmes_aplica / totalFirmes * 100;
        double porcentajeNoAplica = (double) firmes_no_aplica / totalFirmes * 100;

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, firmes_aplica));
        entries.add(new BarEntry(1, firmes_no_aplica));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej2));

        BarDataSet dataSet = new BarDataSet(entries, "Firmes");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(new String[]{"Si Participan", "No Participan"}));
        barChart.getXAxis().setGranularity(1f); // Intervalo mínimo entre etiquetas
        barChart.getXAxis().setLabelCount(2); // Asegura que solo haya 2 etiquetas

        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        barChart.invalidate();

        ((TextView) findViewById(R.id.textViewfirmes_aplicaPorcentaje)).setText(String.format("%.2f%%", porcentajeAplica));
        ((TextView) findViewById(R.id.textViewfirmes_aplica)).setText("Aplica");

        ((TextView) findViewById(R.id.textViewfirmes_no_aplicaPorcentaje)).setText(String.format("%.2f%%", porcentajeNoAplica));
        ((TextView) findViewById(R.id.textViewfirmes_no_aplica)).setText("No aplica");



        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosFirmesAdelanteCjdr.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });
    }
}
