package com.pronacej.Pronacej.InfoPublica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.SeguridadService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrasladosGrafico extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private int selectedYear;
    private TextView[] textViewsPorcentaje = new TextView[9];
    private TextView[] textViewsNombre = new TextView[9];

    private TextView textViewTotalCantidad1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_traslados);

        barChart = findViewById(R.id.barChart);
        textViewTotalCantidad1 = findViewById(R.id.textViewTotalCantidad1);

        initializeViews(); // Inicia las vistas adicionales

        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonHome = findViewById(R.id.buttonHome);

        buttonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(TrasladosGrafico.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        buttonBack.setOnClickListener(v -> onBackPressed());

        // Obtener el año seleccionado del intent
        selectedYear = getIntent().getIntExtra("selectedYear", 0);

        fetchTrasladosData();
    }

    private void fetchTrasladosData() {
        SeguridadService service = Apis.getSeguridadService();
        Call<List<Map<String, Object>>> call = service.getTraslados();

        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Map<String, Object>> allData = response.body();
                    List<Map<String, Object>> filteredData = filterDataByYear(allData, selectedYear);
                    setupChart(filteredData);
                    updateLegend(filteredData);
                } else {
                    Toast.makeText(TrasladosGrafico.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                Toast.makeText(TrasladosGrafico.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Map<String, Object>> filterDataByYear(List<Map<String, Object>> allData, int year) {
        List<Map<String, Object>> filteredData = new ArrayList<>();
        for (Map<String, Object> data : allData) {
            if (((Number) data.get("fecha")).intValue() == year) {
                filteredData.add(data);
            }
        }
        return filteredData;
    }

    private void setupChart(List<Map<String, Object>> data) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        int totalFugas = 0;

        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> traslado = data.get(i);
            String centroJuvenil = (String) traslado.get("centro_juvenil");
            int total = ((Number) traslado.get("total")).intValue();

            entries.add(new BarEntry(i, total));
            labels.add(centroJuvenil);
            totalFugas += total;
        }

        textViewTotalCantidad1.setText(String.valueOf(totalFugas));

        BarDataSet dataSet = new BarDataSet(entries, "Traslados por Centro Juvenil");
        dataSet.setColor(ContextCompat.getColor(this, R.color.Pronacej2));
        dataSet.setValueTextSize(8f);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.black));

        BarData barData = new BarData(dataSet);

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);

        // Añadir márgenes a la izquierda y derecha
        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setTextSize(8f);
        barChart.getDescription().setEnabled(false);
        barChart.setExtraLeftOffset(30f);
        barChart.setExtraRightOffset(50f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labels.size());
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < labels.size()) {
                    return labels.get(index);
                }
                return "";
            }
        });
        // Eliminar la rotación del texto
        // xAxis.setLabelRotationAngle(45f); // Esta línea se elimina para que el texto esté horizontal

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setTextSize(4f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        barChart.setData(barData);
        barChart.invalidate();
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

        textViewsNombre[0] = findViewById(R.id.textViewAlfonso_Ugarte);
        textViewsNombre[1] = findViewById(R.id.textViewMarcavalle);
        textViewsNombre[2] = findViewById(R.id.textViewPucallpa);
        textViewsNombre[3] = findViewById(R.id.textViewEl_Tambo);
        textViewsNombre[4] = findViewById(R.id.textViewTrujillo);
        textViewsNombre[5] = findViewById(R.id.textViewJose_Quinones);
        textViewsNombre[6] = findViewById(R.id.textViewMiguel_Grau);
        textViewsNombre[7] = findViewById(R.id.textViewSanta_Margarita);
        textViewsNombre[8] = findViewById(R.id.textViewAnexoIII);

        textViewTotalCantidad1 = findViewById(R.id.textViewTotalCantidad1);
    }

    private void updateLegend(List<Map<String, Object>> data) {
        for (int i = 0; i < data.size() && i < textViewsPorcentaje.length; i++) {
            Map<String, Object> fuga = data.get(i);
            int total = ((Number) fuga.get("total")).intValue();
            String centroJuvenil = (String) fuga.get("centro_juvenil");

            // Actualizar el TextView con el nombre del centro juvenil
            textViewsNombre[i].setText(centroJuvenil);

            // Actualizar el TextView con el total
            textViewsPorcentaje[i].setText("Total: " + total);

            // Hacer que los TextView sean visibles
            textViewsNombre[i].setVisibility(View.VISIBLE);
            textViewsPorcentaje[i].setVisibility(View.VISIBLE);
        }
    }
}
