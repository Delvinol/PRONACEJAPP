package com.pronacej.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;

import java.util.ArrayList;
import java.util.List;

public class ResultadosLaboralIngresoCjdr extends AppCompatActivity {

    private int trabaja_formal;
    private int trabaja_informal;
    private int trabaja_sin;

    private TextView textViewTotalCantidad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_laboral_ingreso_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosLaboralIngresoCjdr.this, CategoriaMenu.class);
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
        int trabaja_formal = intent.getIntExtra("trabaja_formal", 0);
        int trabaja_informal = intent.getIntExtra("trabaja_informal", 0);
        int trabaja_sin = intent.getIntExtra("trabaja_sin", 0);

        int totalSLA = trabaja_formal + trabaja_informal + trabaja_sin;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalSLA)));

// Calcular los porcentajes
        double porcentajeTrabajaInformal = (double) trabaja_informal / totalSLA * 100;
        double porcentajeTrabajaFormal = (double) trabaja_formal / totalSLA * 100;
        double porcentajeTrabajaSin = (double) trabaja_sin / totalSLA * 100;

        ((TextView) findViewById(R.id.textViewinser_labo_internaPorcentaje)).setText(String.format("%d", (int) Math.round(trabaja_informal)));
        ((TextView) findViewById(R.id.textViewinser_labo_interna)).setText("Trabaja Informal");

        ((TextView) findViewById(R.id.textViewinser_labo_externaPorcentaje)).setText(String.format("%d", (int) Math.round(trabaja_formal)));
        ((TextView) findViewById(R.id.textViewinser_labo_externa)).setText("Trabaja Formal");

        ((TextView) findViewById(R.id.textView_sin_Porcentaje)).setText(String.format("%d", (int) Math.round(trabaja_sin)));
        ((TextView) findViewById(R.id.textViewinser_sin)).setText("Sin Trabajo");


        // Configurar el gráfico de barras horizontales
        HorizontalBarChart horizontalBarChart = findViewById(R.id.horizontalBarChart);
        horizontalBarChart.getDescription().setEnabled(false);
        horizontalBarChart.setDrawValueAboveBar(true);

        // Crear las entradas del gráfico de barras horizontales
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, trabaja_informal));
        entries.add(new BarEntry(1, trabaja_formal));
        entries.add(new BarEntry(2, trabaja_sin));

        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(new int[]{
                getResources().getColor(R.color.Pronacej6),
                getResources().getColor(R.color.Pronacej2),
                getResources().getColor(R.color.Pronacej5)
        });
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        horizontalBarChart.setData(barData);

        // Configurar el eje X (etiquetas)
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Trabaja Informal", "Trabaja Formal", "Sin Trabajo"}));

        // Configurar el eje Y (valores)
        YAxis leftAxis = horizontalBarChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularity(1f);

        YAxis rightAxis = horizontalBarChart.getAxisRight();
        rightAxis.setEnabled(false);

        // Formatear los valores para mostrar porcentajes
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format("%.1f%%", (value / totalSLA) * 100);
            }
        });

        horizontalBarChart.setFitBars(true);
        horizontalBarChart.invalidate();

        // Personalizar la leyenda
        Legend legend = horizontalBarChart.getLegend();
        legend.setEnabled(false); // Desactivamos la leyenda ya que las etiquetas están en el eje X


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6)); // Color para Trabaja Informalmente
        colors.add(getResources().getColor(R.color.Pronacej2)); // Color para Trabaja Formalmente
        colors.add(getResources().getColor(R.color.Pronacej5)); // Color para Sin Trabajo
    }
}
