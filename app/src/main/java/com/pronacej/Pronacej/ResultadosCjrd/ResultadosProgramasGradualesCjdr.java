package com.pronacej.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultadosProgramasGradualesCjdr extends AppCompatActivity {

    private int programa_uno;
    private int programa_dos;
    private int programa_tres;
    private int programa_cuatro;
    private int programa_no_aplica;
    private int pii;
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_programas_graduales_cjdr);

        // Obtener los valores de los programas
        Intent intent = getIntent();
        programa_uno = intent.getIntExtra("programa_uno", 0);
        programa_dos = intent.getIntExtra("programa_dos", 0);
        programa_tres = intent.getIntExtra("programa_tres", 0);
        programa_cuatro = intent.getIntExtra("programa_cuatro", 0);
        programa_no_aplica = intent.getIntExtra("programa_no_aplica", 0);
        pii = intent.getIntExtra("pii", 0);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        setupNavigation();
        setupChart();
        setupTextViews();
    }

    private void setupNavigation() {
        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(ResultadosProgramasGradualesCjdr.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        ButtonBack.setOnClickListener(v -> onBackPressed());
    }

    private void setupChart() {
        HorizontalBarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setExtraRightOffset(50f);
        barChart.setExtraLeftOffset(10f);
        barChart.setMinimumHeight(800);

        int totalParticipantes = programa_uno + programa_dos + programa_tres + programa_cuatro + programa_no_aplica + pii;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalParticipantes)));

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, (float) programa_uno / totalParticipantes * 100));
        entries.add(new BarEntry(1f, (float) programa_dos / totalParticipantes * 100));
        entries.add(new BarEntry(2f, (float) programa_tres / totalParticipantes * 100));
        entries.add(new BarEntry(3f, (float) programa_cuatro / totalParticipantes * 100));
        entries.add(new BarEntry(4f, (float) programa_no_aplica / totalParticipantes * 100));
        entries.add(new BarEntry(5f, (float) pii / totalParticipantes * 100));


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej8));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej3));

        BarDataSet barDataSet = new BarDataSet(entries, "Participación");
        barDataSet.setColors(colors);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.8f);
        barData.setValueFormatter(new ValueFormatter() {
            private DecimalFormat df = new DecimalFormat("0.0");
            @Override
            public String getFormattedValue(float value) {
                return df.format(value) + "%";
            }
        });

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(5);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Programa I", "Programa II", "Programa III", "Programa IV", "No Aplica", "PII"}));
        xAxis.setTextSize(10f);
        xAxis.setDrawLabels(true);

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setAxisMaximum(100f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        barChart.setData(barData);
        barChart.invalidate();
    }

    private void setupTextViews() {
        ((TextView) findViewById(R.id.textViewPrograma1Cantidad)).setText(String.valueOf(programa_uno));
        ((TextView) findViewById(R.id.textViewPrograma1)).setText("En Programa I");
        ((TextView) findViewById(R.id.textViewPrograma2Cantidad)).setText(String.valueOf(programa_dos));
        ((TextView) findViewById(R.id.textViewPrograma2)).setText("En Programa II");
        ((TextView) findViewById(R.id.textViewPrograma3Cantidad)).setText(String.valueOf(programa_tres));
        ((TextView) findViewById(R.id.textViewPrograma3)).setText("En Programa III");
        ((TextView) findViewById(R.id.textViewPrograma4Cantidad)).setText(String.valueOf(programa_cuatro));
        ((TextView) findViewById(R.id.textViewPrograma4)).setText("En Programa IV");
        ((TextView) findViewById(R.id.textViewNoAplicaCantidad)).setText(String.valueOf(programa_no_aplica));
        ((TextView) findViewById(R.id.textViewNoAplica)).setText("No aplica");
        ((TextView) findViewById(R.id.textViewPIICantidad)).setText(String.valueOf(pii));
        ((TextView) findViewById(R.id.textViewPII)).setText("PII");
    }
}