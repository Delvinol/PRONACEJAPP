package com.pronacej.Pronacej.InfoPublica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.SeguridadService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReyertasGrafico extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private TextView[] textViewsPorcentaje = new TextView[10];
    private TextView[] textViewsNombre = new TextView[10];

    private TextView textViewTotalCantidad1;

    private List<Map<String, Object>> reyertasData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_reyerta);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(ReyertasGrafico.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        ButtonBack.setOnClickListener(v -> onBackPressed());

        barChart = findViewById(R.id.barChart);
        initializeViews();
        fetchReyertasData();
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

    private void fetchReyertasData() {
        SeguridadService service = Apis.getSeguridadService();
        Call<List<Map<String, Object>>> call = service.getReyertas();

        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    reyertasData = response.body();
                    setupChart();
                } else {
                    Toast.makeText(ReyertasGrafico.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                Toast.makeText(ReyertasGrafico.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        float totalGeneral = 0;

        for (int i = 0; i < reyertasData.size(); i++) {
            Map<String, Object> reyerta = reyertasData.get(i);
            String funcion = (String) reyerta.get("centro_juvenil");
            double total = ((Number) reyerta.get("total")).doubleValue();

            entries.add(new BarEntry(i, (float) total));
            totalGeneral += total;

            if (i < textViewsPorcentaje.length && textViewsPorcentaje[i] != null) {
                textViewsPorcentaje[i].setText(String.format("Total: %.0f", total));
            }
            if (i < textViewsNombre.length && textViewsNombre[i] != null) {
                textViewsNombre[i].setText(funcion);
            }
        }

        textViewTotalCantidad1.setText(String.format("%.0f", totalGeneral));

        BarDataSet dataSet = new BarDataSet(entries, "Reyertas Totales por cada centro CJDR");
        dataSet.setColor(ContextCompat.getColor(this, R.color.Pronacej3));
        dataSet.setValueTextSize(8f);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.black));

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
        barChart.setExtraLeftOffset(30f);
        barChart.setExtraRightOffset(50f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value >= 0 && value < reyertasData.size()) {
                    return (String) reyertasData.get((int) value).get("centro_juvenil");
                }
                return "";
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(reyertasData.size());
        xAxis.setLabelRotationAngle(0);

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setTextSize(4f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        barChart.setData(barData);
        barChart.invalidate();
    }
}
