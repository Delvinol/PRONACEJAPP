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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSituacionLaboralActualSoa extends AppCompatActivity {

    private int trabaja_si;
    private int trabaja_no;
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_situacion_laboral_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosSituacionLaboralActualSoa.this, CategoriaMenu.class);
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
        trabaja_si = getIntent().getIntExtra("trabaja_si", 0);
        trabaja_no = getIntent().getIntExtra("trabaja_no", 0);
        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);
        int totalSLA = trabaja_si + trabaja_no;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalSLA)));
        // Calcular los porcentajes
        double porcentajeTrabajaSi = (double) trabaja_si / totalSLA * 100;
        double porcentajeTrabajaNo = (double) trabaja_no / totalSLA * 100;

        ((TextView) findViewById(R.id.textViewinser_labo_internaPorcentaje)).setText(String.format("%d", trabaja_si));
        ((TextView) findViewById(R.id.textViewinser_labo_interna)).setText("Si Trabaja");

        ((TextView) findViewById(R.id.textViewinser_labo_externaPorcentaje)).setText(String.format("%d", trabaja_no));
        ((TextView) findViewById(R.id.textViewinser_labo_externa)).setText("No trabaja");

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) porcentajeTrabajaNo, "No Trabaja"));
        entries.add(new PieEntry((float) porcentajeTrabajaSi, "Si Trabaja"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);
        dataSet.setValueFormatter(new PercentValueFormatter());

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // Refrescar el gráfico

        // Customize legend
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(12f);
        legend.setXEntrySpace(10f);
        legend.setTextColor(getResources().getColor(R.color.black));
    }
    public class PercentValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        public String getFormattedValue(float value) {
            return String.format("%.1f%%", value);
        }
    }
}
