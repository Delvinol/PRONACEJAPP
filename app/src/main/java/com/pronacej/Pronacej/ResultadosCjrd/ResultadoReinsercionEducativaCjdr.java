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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class ResultadoReinsercionEducativaCjdr extends AppCompatActivity {

    private int reinsercion_educativa;
    private int insercion_productiva;
    private int continuidad_edu;
    private int apoyo_regularizar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_reinsercion_edu_cjdr);
        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoReinsercionEducativaCjdr.this, CategoriaMenu.class);
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
        reinsercion_educativa = getIntent().getIntExtra("reinsercion_educativa", 0);
        insercion_productiva = getIntent().getIntExtra("insercion_productiva", 0);
        continuidad_edu = getIntent().getIntExtra("continuidad_edu", 0);
        apoyo_regularizar = getIntent().getIntExtra("apoyo_regularizar", 0);

        // Calcular el total de participantes
        int totalParticipantes = reinsercion_educativa + insercion_productiva + continuidad_edu + apoyo_regularizar;

        // Calcular los porcentajes
        double porcentajeReinsercion = (double) reinsercion_educativa / totalParticipantes * 100;
        double porcentajeInsercion = (double) insercion_productiva / totalParticipantes * 100;
        double porcentajeContinuidad = (double) continuidad_edu / totalParticipantes * 100;
        double porcentajeApoyo = (double) apoyo_regularizar / totalParticipantes * 100;

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, reinsercion_educativa));
        entries.add(new BarEntry(1, insercion_productiva));
        entries.add(new BarEntry(2, continuidad_edu));
        entries.add(new BarEntry(3, apoyo_regularizar));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej7));

        BarDataSet dataSet = new BarDataSet(entries, "Resultado Reinserción Educativa");
        dataSet.setColors(colors);

        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BarData barData = new BarData(dataSets);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);

        // Personalizar el eje X con formateo dinámico
        String[] labels = {"Reinserción Educativa", "Inserción Productiva", "Continuidad Educativa", "Apoyo para Regularizar"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // Intervalo mínimo entre etiquetas
        DynamicXAxisValueFormatter formatter = new DynamicXAxisValueFormatter(labels, barChart);
        xAxis.setValueFormatter(formatter);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setDrawGridLines(false);

        barChart.getAxisRight().setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        barChart.invalidate();

        // Actualizar los porcentajes en los TextViews
        ((TextView) findViewById(R.id.textViewreinsercion_educativaPorcentaje)).setText(String.format("%.2f%%", porcentajeReinsercion));
        ((TextView) findViewById(R.id.textViewreinsercion_educativa)).setText("Reinserción educativa");

        ((TextView) findViewById(R.id.textViewinsercion_productivaPorcentaje)).setText(String.format("%.2f%%", porcentajeInsercion));
        ((TextView) findViewById(R.id.textViewinsercion_productiva)).setText("Inserción productiva");

        ((TextView) findViewById(R.id.textViewsexo_masculinoPorcentaje)).setText(String.format("%.2f%%", porcentajeContinuidad));
        ((TextView) findViewById(R.id.textViewsexo_masculino)).setText("Continuidad educativa");

        ((TextView) findViewById(R.id.textViewsexo_femeninoPorcentaje)).setText(String.format("%.2f%%", porcentajeApoyo));
        ((TextView) findViewById(R.id.textViewsexo_femenino)).setText("Apoyo regularizado");
    }

    // Clase para formatear dinámicamente las etiquetas del eje X
    private static class DynamicXAxisValueFormatter extends ValueFormatter {

        private final String[] labels;
        private final BarChart barChart;

        DynamicXAxisValueFormatter(String[] labels, BarChart barChart) {
            this.labels = labels;
            this.barChart = barChart;
        }

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            int index = (int) value;
            if (index >= 0 && index < labels.length) {
                // Obtener el nivel de zoom
                float scaleX = barChart.getViewPortHandler().getScaleX();

                if (scaleX > 1.5f) { // Ajustar este valor según el nivel de zoom que desees
                    return labels[index]; // Mostrar texto completo
                } else {
                    return labels[index].substring(0, Math.min(labels[index].length(), 6)); // Mostrar máximo 6 letras
                }
            }
            return "";
        }
    }
}
