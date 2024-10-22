package com.pronacej.Pronacej.ResultadosSoa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ResultadoEdadSoa extends AppCompatActivity {

    private HorizontalBarChart barChart;

    private TextView[] textViewsPorcentaje = new TextView[8];
    private TextView[] textViewsNombre = new TextView[8];
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_edad_soa);

        barChart = findViewById(R.id.barChart);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoEdadSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });


        // Referencias a los TextView de porcentaje
        textViewsPorcentaje[0] = findViewById(R.id.textViewCatorcePorcentaje);
        textViewsPorcentaje[1] = findViewById(R.id.textViewQuincePorcentaje);
        textViewsPorcentaje[2] = findViewById(R.id.textViewDieciseisPorcentaje);
        textViewsPorcentaje[3] = findViewById(R.id.textViewDiecisietePorcentaje);
        textViewsPorcentaje[4] = findViewById(R.id.textViewDieciochoPorcentaje);
        textViewsPorcentaje[5] = findViewById(R.id.textViewDiecinuevePorcentaje);
        textViewsPorcentaje[6] = findViewById(R.id.textViewVeintePorcentaje);
        textViewsPorcentaje[7] = findViewById(R.id.textViewVeintiunoPorcentaje);

        // Referencias a los TextView de nombres
        textViewsNombre[0] = findViewById(R.id.textViewCatorce);
        textViewsNombre[1] = findViewById(R.id.textViewQuince);
        textViewsNombre[2] = findViewById(R.id.textViewDieciseis);
        textViewsNombre[3] = findViewById(R.id.textViewDiecisiete);
        textViewsNombre[4] = findViewById(R.id.textViewDieciocho);
        textViewsNombre[5] = findViewById(R.id.textViewDiecinueve);
        textViewsNombre[6] = findViewById(R.id.textViewVeinte);
        textViewsNombre[7] = findViewById(R.id.textViewVeintiuno);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        // Obtener los datos pasados desde la actividad anterior
        String datosJson = getIntent().getStringExtra("datosEdad");
        if (datosJson != null) {
            try {
                JSONArray jsonArray = new JSONArray(datosJson);
                if (jsonArray.length() > 0) {
                    JSONObject datos = jsonArray.getJSONObject(0);
                    crearGrafico(datos);
                } else {
                    Log.e("ResultadoEdadSoa", "No se encontraron datos en la lista");
                }
            } catch (JSONException e) {
                Log.e("ResultadoEdadSoa", "Error al parsear JSON", e);
            }
        } else {
            Log.e("ResultadoEdadSoa", "No se recibieron datos");
        }
    }

    private void crearGrafico(JSONObject datos) throws JSONException {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        // Calcular el total de todos los valores
        float total = 0;
        Iterator<String> keys = datos.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!key.equals("estado_ing")) { // Excluir "estado_ing"
                total += datos.getDouble(key);
            }
        }

        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(total)));

        // Mapeo de datos a valores de entrada
        int i = 0;
        keys = datos.keys(); // Reiniciar el iterador
        while (keys.hasNext()) {
            String key = keys.next();
            if (!key.equals("estado_ing")) { // Excluir "estado_ing"
                float valor = (float) datos.getDouble(key);
                float porcentaje = (valor / total) * 100;

                String formattedPorcentaje = String.format("%.2f%%", porcentaje);


                entries.add(new BarEntry(i, porcentaje));

                String[] opcionesNombre = {"14 años", "15 años", "16 años", "17 años", "18 años", "19 años", "20 años", "21 años a más"};

                labels.add(opcionesNombre[i]);

                // Asignar datos a los TextView
                if (i < textViewsPorcentaje.length && i < textViewsNombre.length) {
                    textViewsPorcentaje[i].setText(String.format("%d", (int)valor)+" de");
                    String capitalizedKey = key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase();
                    textViewsNombre[i].setText(opcionesNombre[i]);
                }

                i++;
            }
        }

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej8));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej9));
        colors.add(getResources().getColor(R.color.Pronacej10));

        BarDataSet dataSet = new BarDataSet(entries, "Edades");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueFormatter(new PercentValueFormatter());


        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        // Configurar el eje X
        barChart.getXAxis().setValueFormatter(new CustomAxisValueFormatter(labels));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

        // Habilitar zoom y scroll
        barChart.setScaleEnabled(true);
        barChart.setPinchZoom(true);

        // Configurar el eje Y
        barChart.getAxisLeft().setGranularity(1f);
        barChart.getAxisRight().setEnabled(false);

        // Configurar leyenda y descripción
        barChart.getLegend().setEnabled(true);
        barChart.getDescription().setEnabled(false);

        barChart.invalidate(); // refrescar
    }

    // Clase formateadora personalizada para el eje X
    class CustomAxisValueFormatter extends IndexAxisValueFormatter {

        private final ArrayList<String> originalLabels;

        public CustomAxisValueFormatter(ArrayList<String> labels) {
            super(labels);
            this.originalLabels = labels;
        }

        @Override
        public String getFormattedValue(float value) {
            int index = (int) value;
            if (index >= 0 && index < originalLabels.size()) {
                String label = originalLabels.get(index);
                if (barChart.getViewPortHandler().getScaleX() > 1) {
                    // Mostrar la etiqueta completa si el zoom está activo
                    return label;
                } else {
                    // Mostrar solo las primeras 6 letras si no hay zoom
                    return label.length() > 6 ? label.substring(0, 7) : label;
                }
            }
            return "";
        }
    }
    public class PercentValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        public String getFormattedValue(float value) {
            return String.format("%.1f%%", value);
        }
    }
}
