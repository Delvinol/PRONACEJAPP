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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSituacionLaboralActualCjdr extends AppCompatActivity {

    private int trabaja_si;
    private int trabaja_no;

    private TextView textViewTotalCantidad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_situacion_laboral_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosSituacionLaboralActualCjdr.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        Intent intent = getIntent();
        int trabaja_no = intent.getIntExtra("trabaja_no", 0);
        int trabaja_si = intent.getIntExtra("trabaja_si", 0);

        int totalSLA = trabaja_no + trabaja_si;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalSLA)));

// Calcular los porcentajes
        double porcentajeTrabajaNo = (double) trabaja_no / totalSLA * 100;
        double porcentajeTrabajaSi = (double) trabaja_si / totalSLA * 100;

        ((TextView) findViewById(R.id.textViewinser_labo_internaPorcentaje)).setText(String.format("%d", (int) Math.round(trabaja_no)));
        ((TextView) findViewById(R.id.textViewinser_labo_interna)).setText("No Trabaja");

        ((TextView) findViewById(R.id.textViewinser_labo_externaPorcentaje)).setText(String.format("%d", (int) Math.round(trabaja_si)));
        ((TextView) findViewById(R.id.textViewinser_labo_externa)).setText("Sí Trabaja");


        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(true);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) porcentajeTrabajaNo, "No Trabaja"));
        entries.add(new PieEntry((float) porcentajeTrabajaSi, "Sí Trabaja"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej2));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueFormatter(new PercentFormatter(pieChart));

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // Refrescar el gráfico

        // Customize legend
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(12f);
        legend.setXEntrySpace(10f);
        legend.setTextColor(getResources().getColor(R.color.black));
    }
}
