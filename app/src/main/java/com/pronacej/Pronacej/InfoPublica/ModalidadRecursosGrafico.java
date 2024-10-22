package com.pronacej.Pronacej.InfoPublica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.RecursoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModalidadRecursosGrafico extends AppCompatActivity {

    private int dl728;
    private int dl1057;
    private TextView textViewTotalCantidad;
    private PieChart pieChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_modalidad_recurso);

        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);
        pieChart = findViewById(R.id.pieChart);

        // Obtener el servicio usando la clase Apis
        RecursoService service = Apis.getRecursoService();

        // Realizar la llamada a la API
        Call<List<Map<String, Object>>> call = service.getRegimes();
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    processData(response.body());
                } else {
                    Toast.makeText(ModalidadRecursosGrafico.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                Toast.makeText(ModalidadRecursosGrafico.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar botones
        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(ModalidadRecursosGrafico.this, CategoriaMenu.class);
            startActivity(intentHome);
        });
        ButtonBack.setOnClickListener(v -> onBackPressed());
    }

    private void processData(List<Map<String, Object>> data) {
        for (Map<String, Object> regime : data) {
            String name = (String) regime.get("nombre_regimen");
            Object totalObj = regime.get("total");
            int value = 0;

            if (totalObj instanceof Integer) {
                value = (Integer) totalObj;
            } else if (totalObj instanceof Double) {
                value = ((Double) totalObj).intValue();
            } else if (totalObj instanceof String) {
                try {
                    value = Integer.parseInt((String) totalObj);
                } catch (NumberFormatException e) {
                    // Manejar el error si la cadena no puede ser parseada a int
                    e.printStackTrace();
                }
            }

            if ("DL 728".equals(name)) {
                dl728 = value;
            } else if ("DL 1057".equals(name)) {
                dl1057 = value;
            }
        }

        updateUI();
    }

    private void updateUI() {
        int totalGeneral = dl728 + dl1057;
        textViewTotalCantidad.setText(String.format("Total: %d", totalGeneral));

        double porcentajeDL728 = (double) dl728 / totalGeneral * 100;
        double porcentajeDL1057 = (double) dl1057 / totalGeneral * 100;

        ((TextView) findViewById(R.id.textViewfirmes_aplicaPorcentaje)).setText(String.format("%.2f%%", porcentajeDL728));
        ((TextView) findViewById(R.id.textViewfirmes_aplica)).setText("DL 728");

        ((TextView) findViewById(R.id.textViewfirmes_no_aplicaPorcentaje)).setText(String.format("%.2f%%", porcentajeDL1057));
        ((TextView) findViewById(R.id.textViewfirmes_no_aplica)).setText("DL 1057");

        updatePieChart();
    }

    private void updatePieChart() {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(dl728, "DL 728"));
        entries.add(new PieEntry(dl1057, "DL 1057"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej2));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format("%d", (int) value);
            }
        });

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(false);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.setEntryLabelColor(getResources().getColor(R.color.black));

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        pieChart.invalidate();
    }
}
