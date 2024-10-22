package com.pronacej.Pronacej.InfoPublica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;

import java.util.ArrayList;

public class FugasGrafico extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private TextView[] textViewsPorcentaje = new TextView[10];
    private TextView[] textViewsNombre = new TextView[10];
    private TextView textViewTotalCantidad1;

    private String[] centros = {"CJDR PIURA", "CJDR CHICLAYO", "CJDR TRUJILLO", "CJDR HUANCAYO", "CJDR PUCALLPA", "CJDR AREQUIPA", "CJDR CUSCO", "CJDR LIMA", "CJDR ANEXO 3 ANCÓN II", "CJDR SANTA MARGARITA"};
    private float[][] data = {
            {2, 0, 38, 9, 1, 0},
            {0, 0, 0, 19, 0, 4},
            {1, 8, 2, 0, 1, 8},
            {0, 1, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0},
            {3, 1, 1, 2, 2, 0},
            {0, 8, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_fugas_control);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(FugasGrafico.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        barChart = findViewById(R.id.barChart);
        initializeViews();

        // Get the selected year from the intent
        Intent intent = getIntent();
        int selectedYear = intent.getIntExtra("selectedYear", 2019);

        // Calculate the index based on the selected year
        int yearIndex = selectedYear - 2019;

        setupChart(yearIndex);
    }

    private void initializeViews() {
        textViewsPorcentaje[0] = findViewById(R.id.textViewAlfonsoUgartePorcentaje);
        textViewsPorcentaje[1] = findViewById(R.id.textViewMarcavallePorcentaje);
        textViewsPorcentaje[2] = findViewById(R.id.textViewPucallpaPorcentaje);
        textViewsPorcentaje[3] = findViewById(R.id.textViewEl_TamboPorcentaje);
        textViewsPorcentaje[4] = findViewById(R.id.textViewTrujilloPorcentaje);
        textViewsPorcentaje[5] = findViewById(R.id.textViewJose_QuinonesPorcentaje);
        textViewsPorcentaje[6] = findViewById(R.id.textViewMiguel_GrauPorcentaje);
        textViewsPorcentaje[7] = findViewById(R.id.textViewSanta_MargaritaPorcentaje);
        textViewsPorcentaje[8] = findViewById(R.id.textViewAnexoIIIPorcentaje);
        textViewsPorcentaje[9] = findViewById(R.id.textViewLimaPorcentaje);

        textViewsNombre[0] = findViewById(R.id.textViewAlfonso_Ugarte);
        textViewsNombre[1] = findViewById(R.id.textViewMarcavalle);
        textViewsNombre[2] = findViewById(R.id.textViewPucallpa);
        textViewsNombre[3] = findViewById(R.id.textViewEl_Tambo);
        textViewsNombre[4] = findViewById(R.id.textViewTrujillo);
        textViewsNombre[5] = findViewById(R.id.textViewJose_Quinones);
        textViewsNombre[6] = findViewById(R.id.textViewMiguel_Grau);
        textViewsNombre[7] = findViewById(R.id.textViewSanta_Margarita);
        textViewsNombre[8] = findViewById(R.id.textViewAnexoIII);
        textViewsNombre[9] = findViewById(R.id.textViewLima);

        textViewTotalCantidad1 = findViewById(R.id.textViewTotalCantidad1);
    }

    private void setupChart(int yearIndex) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        float totalGeneral = 0;  // Variable para acumular el total general

        for (int i = 0; i < centros.length; i++) {
            float total = data[i][yearIndex];
            entries.add(new BarEntry(i, total));

            // Sumar al total general
            totalGeneral += total;

            // Set text for percentage and name views
            if (textViewsPorcentaje[i] != null) {
                textViewsPorcentaje[i].setText(String.format("Total: %.0f", total));
            }
            if (textViewsNombre[i] != null) {
                textViewsNombre[i].setText(centros[i]);
            }
        }

        // Actualizar el TextView con el total general
        textViewTotalCantidad1.setText(String.format("%.0f", totalGeneral));

        BarDataSet dataSet = new BarDataSet(entries, "Fugas por Centro");
        dataSet.setColor(ContextCompat.getColor(this, R.color.Pronacej1));
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.black));

        // ValueFormatter to format values without decimals
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format("%.0f", value);
            }
        });

        BarData barData = new BarData(dataSet);

        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setTextSize(8f);
        barChart.getDescription().setEnabled(false);
        barChart.setExtraLeftOffset(10f);
        barChart.setExtraRightOffset(50f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return centros[(int) value];
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(centros.length);
        xAxis.setLabelRotationAngle(0);

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        barChart.setData(barData);
        barChart.invalidate();
    }
}
