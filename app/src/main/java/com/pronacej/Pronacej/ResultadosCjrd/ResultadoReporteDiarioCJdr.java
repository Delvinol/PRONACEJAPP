package com.pronacej.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.os.Bundle;
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

public class ResultadoReporteDiarioCJdr extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private ArrayList<HashMap<String, String>> reportData;
    private TextView[] textViewsPorcentaje = new TextView[10];
    private TextView[] textViewsNombre = new TextView[10];
    private TextView textViewTotalCantidad;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_reporte_diario_cjdr);

        initializeViews();

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoReporteDiarioCJdr.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        reportData = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("reportData");

        setupChart();
    }

    private void initializeViews() {
        barChart = findViewById(R.id.barChart);
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        int[] porcentajeIds = {
                R.id.textViewLimaPorcentaje,
                R.id.textViewAnexoIIIPorcentaje,
                R.id.textViewSanta_MargaritaPorcentaje,
                R.id.textViewMiguel_GrauPorcentaje,
                R.id.textViewJose_QuinonesPorcentaje,
                R.id.textViewTrujilloPorcentaje,
                R.id.textViewEl_TamboPorcentaje,
                R.id.textViewPucallpaPorcentaje,
                R.id.textViewMarcavallePorcentaje,
                R.id.textViewAlfonsoUgartePorcentaje
        };

        int[] nombreIds = {
                R.id.textViewLima,
                R.id.textViewAnexoIII,
                R.id.textViewSanta_Margarita,
                R.id.textViewMiguel_Grau,
                R.id.textViewJose_Quinones,
                R.id.textViewTrujillo,
                R.id.textViewEl_Tambo,
                R.id.textViewPucallpa,
                R.id.textViewMarcavalle,
                R.id.textViewAlfonso_Ugarte
        };

        for (int i = 0; i < 10; i++) {
            textViewsPorcentaje[i] = findViewById(porcentajeIds[i]);
            textViewsNombre[i] = findViewById(nombreIds[i]);
        }
    }

    private void setupChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(ContextCompat.getColor(this, R.color.Pronacej1));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej2));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej3));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej4));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej5));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej6));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej7));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej8));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej9));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej10));

        float poblacionTotal = 0;
        for (HashMap<String, String> data : reportData) {
            poblacionTotal += Float.parseFloat(data.get("poblacion_cjdr"));
        }

        for (int i = 0; i < reportData.size(); i++) {
            HashMap<String, String> data = reportData.get(i);
            float poblacion = Float.parseFloat(data.get("poblacion_cjdr"));
            float porcentaje = (poblacion / poblacionTotal) * 100;

            entries.add(new BarEntry(i, poblacion));
            if (textViewsPorcentaje[i] != null) {
                textViewsPorcentaje[i].setText(String.format("%.0f", poblacion));
            }
            if (textViewsNombre[i] != null) {
                textViewsNombre[i].setText(data.get("centro_cjdr"));
            }
        }

        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.black));

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.8f);

        barChart.setFitBars(true);
        barChart.setExtraRightOffset(50f);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setTextSize(12f);

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
        xAxis.setLabelRotationAngle(0);  // Cambia el ángulo de rotación a 0 para que las etiquetas estén en horizontal

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        float finalPoblacionTotal = poblacionTotal;
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getBarLabel(BarEntry barEntry) {
                float poblacion = barEntry.getY();
                float porcentaje = (poblacion / finalPoblacionTotal) * 100;
                if (porcentaje >= 10) {
                    return String.format("%.1f%%", porcentaje);
                } else {
                    return String.format("%.1f%%", porcentaje);
                }
            }
        });

        barChart.invalidate();

        textViewTotalCantidad.setText(String.format("%.0f", poblacionTotal));
    }
}
