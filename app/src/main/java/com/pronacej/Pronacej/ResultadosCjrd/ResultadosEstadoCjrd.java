package com.pronacej.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultadosEstadoCjrd extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private ArrayList<HashMap<String, String>> reportData;
    private TextView[] textViewsPorcentaje = new TextView[10];
    private TextView[] textViewsNombre = new TextView[10];
    private TextView textViewTotalCantidad1;
    private TextView textViewTotalCantidad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_estado_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosEstadoCjrd.this, CategoriaMenu.class);
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

        Intent intent = getIntent();
        reportData = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("reportData");

        if (reportData == null || reportData.isEmpty()) {
            Log.e("ResultadosEstadoCjrd", "No hay datos en reportData");
            return;
        }

        initializeViews();
        setupChart();
    }

    private void initializeViews() {
        textViewTotalCantidad1 = findViewById(R.id.textViewTotalCantidad1);
        textViewTotalCantidad2 = findViewById(R.id.textViewTotalCantidad2);

        Log.d("ResultadosEstadoCjrd", "textViewTotalCantidad1: " + (textViewTotalCantidad1 == null ? "null" : "not null"));
        Log.d("ResultadosEstadoCjrd", "textViewTotalCantidad2: " + (textViewTotalCantidad2 == null ? "null" : "not null"));

        textViewsPorcentaje[9] = findViewById(R.id.textViewAlfonsoUgartePorcentaje);
        textViewsPorcentaje[8] = findViewById(R.id.textViewMarcavallePorcentaje);
        textViewsPorcentaje[7] = findViewById(R.id.textViewPucallpaPorcentaje);
        textViewsPorcentaje[6] = findViewById(R.id.textViewEl_TamboPorcentaje);
        textViewsPorcentaje[5] = findViewById(R.id.textViewTrujilloPorcentaje);
        textViewsPorcentaje[4] = findViewById(R.id.textViewJose_QuinonesPorcentaje);
        textViewsPorcentaje[3] = findViewById(R.id.textViewMiguel_GrauPorcentaje);
        textViewsPorcentaje[2] = findViewById(R.id.textViewSanta_MargaritaPorcentaje);
        textViewsPorcentaje[1] = findViewById(R.id.textViewAnexoIIIPorcentaje);
        textViewsPorcentaje[0] = findViewById(R.id.textViewLimaPorcentaje);

        textViewsNombre[9] = findViewById(R.id.textViewAlfonso_Ugarte);
        textViewsNombre[8] = findViewById(R.id.textViewMarcavalle);
        textViewsNombre[7] = findViewById(R.id.textViewPucallpa);
        textViewsNombre[6] = findViewById(R.id.textViewEl_Tambo);
        textViewsNombre[5] = findViewById(R.id.textViewTrujillo);
        textViewsNombre[4] = findViewById(R.id.textViewJose_Quinones);
        textViewsNombre[3] = findViewById(R.id.textViewMiguel_Grau);
        textViewsNombre[2] = findViewById(R.id.textViewSanta_Margarita);
        textViewsNombre[1] = findViewById(R.id.textViewAnexoIII);
        textViewsNombre[0] = findViewById(R.id.textViewLima);

        float totalEgresos = 0;
        float totalIngresos = 0;
        for (HashMap<String, String> data : reportData) {
            totalEgresos += safeParseFloat(data.get("egresos_cjdr"));
            totalIngresos += safeParseFloat(data.get("ingresos_cjdr"));
        }

        if (textViewTotalCantidad1 != null) {
            textViewTotalCantidad1.setText(String.format("%.0f", totalIngresos));
        }
        if (textViewTotalCantidad2 != null) {
            textViewTotalCantidad2.setText(String.format("%.0f", totalEgresos));
        }
    }

    private void setupChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < reportData.size(); i++) {
            HashMap<String, String> data = reportData.get(i);
            float egresos = safeParseFloat(data.get("egresos_cjdr"));
            float ingresos = safeParseFloat(data.get("ingresos_cjdr"));

            entries.add(new BarEntry(i, new float[]{ingresos, egresos}));
            if (textViewsPorcentaje[i] != null) {
                textViewsPorcentaje[i].setText(String.format("Egresos: %.0f\nIngresos: %.0f", egresos, ingresos));
            }
            if (textViewsNombre[i] != null) {
                textViewsNombre[i].setText(data.get("centro_cjdr"));
            }
        }

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(this, R.color.Pronacej2));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej1));

        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.black));
        dataSet.setStackLabels(new String[]{"Ingresos", "Egresos"});

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
                return reportData.get((int) value).get("centro_cjdr");
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(reportData.size());
        xAxis.setLabelRotationAngle(0);

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        barChart.setData(barData);
        barChart.invalidate();
    }

    private float safeParseFloat(String value) {
        try {
            if (value == null || value.equals("null") || value.isEmpty()) {
                return 0;
            }
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            Log.e("ResultadosEstadoCjrd", "Error al parsear float: " + value, e);
            return 0;
        }
    }
}