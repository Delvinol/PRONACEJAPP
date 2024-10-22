package com.pronacej.Pronacej.ResultadosSoa;

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

public class ResultadoReporteDiarioSoa extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private ArrayList<HashMap<String, String>> reportData;
    private TextView[] textViewsPorcentaje = new TextView[26];
    private TextView[] textViewsNombre = new TextView[26];
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_reporte_diario_soa);

        initializeViews();

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoReporteDiarioSoa.this, CategoriaMenu.class);
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

                R.id.textViewAyacuchoCantidad,
                R.id.textViewCallaoCantidad,
                R.id.textViewCaneteCantidad,
                R.id.textViewCerroColoradoCantidad,
                R.id.textViewChiclayoCantidad,
                R.id.textViewCuscoCantidad,
                R.id.textViewHuancavelicaCantidad,
                R.id.textViewHuancayoCantidad,
                R.id.textViewHuanucoCantidad,
                R.id.textViewHuarazCantidad,
                R.id.textViewHuauraCantidad,
                R.id.textViewLaTinguinaCantidad,
                R.id.textViewLimaEsteCantidad,
                R.id.textViewLimaNorteCantidad,
                R.id.textViewLoretoCantidad,
                R.id.textViewMadreDeDiosCantidad,
                R.id.textViewPaucarpataCantidad,
                R.id.textViewPunoCantidad,
                R.id.textViewRimacCantidad,
                R.id.textViewSantaCantidad,
                R.id.textViewSelvaCentralCantidad,
                R.id.textViewSullanaCantidad,
                R.id.textViewTrujilloCantidad,
                R.id.textViewTumbesCantidad,
                R.id.textViewUcayaliCantidad,
                R.id.textViewVentanillaCantidad

        };

        int[] nombreIds = {
                R.id.textViewAyacucho,
                R.id.textViewCallao,
                R.id.textViewCanete,
                R.id.textViewCerroColorado,
                R.id.textViewChiclayo,
                R.id.textViewCusco,
                R.id.textViewHuancavelica,
                R.id.textViewHuancayo,
                R.id.textViewHuanuco,
                R.id.textViewHuaraz,
                R.id.textViewHuaura,
                R.id.textViewLaTinguina,
                R.id.textViewLimaEste,
                R.id.textViewLimaNorte,
                R.id.textViewLoreto,
                R.id.textViewMadreDeDios,
                R.id.textViewPaucarpata,
                R.id.textViewPuno,
                R.id.textViewRimac,
                R.id.textViewSanta,
                R.id.textViewSelvaCentral,
                R.id.textViewSullana,
                R.id.textViewTrujillo,
                R.id.textViewTumbes,
                R.id.textViewUcayali,
                R.id.textViewVentanilla
        };

        for (int i = 0; i < 26; i++) {
            textViewsPorcentaje[i] = findViewById(porcentajeIds[i]);
            textViewsNombre[i] = findViewById(nombreIds[i]);
        }
    }

    private void setupChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            int colorId = getResources().getIdentifier("Pronacej" + i, "color", getPackageName());
            colors.add(ContextCompat.getColor(this, colorId));
        }

        float poblacionTotal = 0;
        for (HashMap<String, String> data : reportData) {
            poblacionTotal += parseFloat(data.get("poblacion_soa"));
        }

        for (int i = 0; i < reportData.size(); i++) {
            HashMap<String, String> data = reportData.get(i);
            float poblacion = parseFloat(data.get("poblacion_soa"));
            float porcentaje = (poblacion / poblacionTotal) * 100;

            entries.add(new BarEntry(i, poblacion));
            if (textViewsPorcentaje[i] != null) {
                textViewsPorcentaje[i].setText(String.format("%d", (int) poblacion));
            }
            if (textViewsNombre[i] != null) {
                textViewsNombre[i].setText(data.get("centro_soa"));
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
                return reportData.get((int) value).get("centro_soa");
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

        float finalPoblacionTotal = poblacionTotal;
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getBarLabel(BarEntry barEntry) {
                float poblacion = barEntry.getY();
                float porcentaje = (poblacion / finalPoblacionTotal) * 100;
                return String.format("%.1f%%", porcentaje);
            }
        });

        barChart.invalidate();

        textViewTotalCantidad.setText(String.format("%d", (int) poblacionTotal));
    }

    private float parseFloat(String value) {
        try {
            return value == null || value.isEmpty() ? 0 : Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
