package com.pronacej.Pronacej.Paspe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ResultadoEdadPaspe extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private TextView[] textViewsPorcentaje = new TextView[8];
    private TextView[] textViewsNombre = new TextView[8];
    private TextView textViewTotalCantidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_edad_paspe);

        barChart = findViewById(R.id.barChart);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(ResultadoEdadPaspe.this, CategoriaMenu.class);
            startActivity(intentHome);
        });
        ButtonBack.setOnClickListener(v -> onBackPressed());

        initializeTextViews();

        String datosJson = getIntent().getStringExtra("datosEdad");
        if (datosJson != null) {
            try {
                JSONArray jsonArray = new JSONArray(datosJson);
                if (jsonArray.length() > 0) {
                    JSONObject datos = jsonArray.getJSONObject(0);
                    crearGrafico(datos);
                } else {
                    Log.e("ResultadoEdadCjdr", "No se encontraron datos en la lista");
                }
            } catch (JSONException e) {
                Log.e("ResultadoEdadCjdr", "Error al parsear JSON", e);
            }
        } else {
            Log.e("ResultadoEdadCjdr", "No se recibieron datos");
        }
    }

    private void initializeTextViews() {
        int[] porcentajeIds = {R.id.textViewCatorcePorcentaje, R.id.textViewQuincePorcentaje, R.id.textViewDieciseisPorcentaje,
                R.id.textViewDiecisietePorcentaje, R.id.textViewDieciochoPorcentaje, R.id.textViewDiecinuevePorcentaje,
                R.id.textViewVeintePorcentaje, R.id.textViewVeintiunoPorcentaje};
        int[] nombreIds = {R.id.textViewCatorce, R.id.textViewQuince, R.id.textViewDieciseis, R.id.textViewDiecisiete,
                R.id.textViewDieciocho, R.id.textViewDiecinueve, R.id.textViewVeinte, R.id.textViewVeintiuno};

        for (int i = 0; i < 8; i++) {
            textViewsPorcentaje[i] = findViewById(porcentajeIds[i]);
            textViewsNombre[i] = findViewById(nombreIds[i]);
        }

        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);
    }

    private void crearGrafico(JSONObject datos) throws JSONException {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        float total = 0;
        Iterator<String> keys = datos.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!key.equals("estado_ing")) {
                total += datos.getDouble(key);
            }
        }

        String[] opcionesNombre = {"14 años", "15 años", "16 años", "17 años", "18 años", "19 años", "20 años", "21 años a más"};


        int i = 0;
        keys = datos.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!key.equals("estado_ing")) {
                float valor = (float) datos.getDouble(key);
                float porcentaje = (valor / total) * 100;
                entries.add(new BarEntry(i, porcentaje));
                labels.add(opcionesNombre[i]);

                if (i < textViewsPorcentaje.length && i < textViewsNombre.length) {
                    textViewsPorcentaje[i].setText(String.format("%d", Math.round(valor))+" de");
                    textViewsNombre[i].setText(opcionesNombre[i]);
                }

                i++;
            }
        }
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(total)));
        setupBarChart(entries, labels);
    }

    private void setupBarChart(ArrayList<BarEntry> entries, ArrayList<String> labels) {
        BarDataSet dataSet = new BarDataSet(entries, "Edades");
        dataSet.setColors(getColors());
        dataSet.setValueTextSize(10f); // Reducido para mejor ajuste
        dataSet.setValueTextColor(Color.BLACK); // Asegura que el texto sea visible
        dataSet.setValueFormatter(new PercentValueFormatter());

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.7f); // Reduce el ancho de las barras para dejar espacio al texto

        barChart.setData(barData);
        barChart.setFitBars(true); // Ajusta las barras al ancho del gráfico

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelRotationAngle(0);
        xAxis.setTextSize(8f); // Reducido para evitar superposición

        barChart.setExtraOffsets(10f, 10f, 50f, 10f); // Añade espacio extra a la derecha

        barChart.setDrawValueAboveBar(true); // Dibuja los valores fuera de las barras
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);

        barChart.getAxisLeft().setAxisMinimum(0f);
        barChart.getAxisRight().setEnabled(false);

        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);

        barChart.invalidate();
    }

    public class PercentValueFormatter extends ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return String.format("%.1f%%", value);
        }
    }
    private ArrayList<Integer> getColors() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej8));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej9));
        colors.add(getResources().getColor(R.color.Pronacej10));
        return colors;
    }
}