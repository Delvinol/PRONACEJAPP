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

public class ResultadosEstadoCivilCjdr extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private ArrayList<HashMap<String, String>> reportData;
    private TextView[] textViewsPorcentaje = new TextView[10];
    private TextView[] textViewsNombre = new TextView[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_estado_civil_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);



        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosEstadoCivilCjdr.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        barChart = findViewById(R.id.barChart);

        // Obtener los datos pasados desde la actividad anterior
        Intent intent = getIntent();
        reportData = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("reportData");

        // Verificar si reportData es null
        if (reportData == null || reportData.isEmpty()) {
            // Manejo de error si no hay datos
            return;
        }

        TextView textViewTotalCantidad1 = findViewById(R.id.textViewTotalCantidad1);
        TextView textViewTotalCantidad2 = findViewById(R.id.textViewTotalCantidad2);
        // Referencias a los TextView de porcentaje
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

        // Referencias a los TextView de nombres
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

        // Crear los objetos BarEntry a partir de los datos
        ArrayList<BarEntry> entries = new ArrayList<>();

        // Calcular la población total
        float totalMayores = 0;
        float totalMenores = 0;
        for (HashMap<String, String> data : reportData) {
            totalMayores += parseFloat(data.get("mayores_cjdr"));
            totalMenores += parseFloat(data.get("menores_cjdr"));
        }
        if (textViewTotalCantidad1 != null) {
            textViewTotalCantidad1.setText(String.format("%.0f", totalMayores));
        }
        if (textViewTotalCantidad2 != null) {
            textViewTotalCantidad2.setText(String.format("%.0f", totalMenores));
        }

        // Asignar los datos a los TextView y crear las entradas del gráfico
        for (int i = 0; i < reportData.size(); i++) {
            HashMap<String, String> data = reportData.get(i);
            float mayores = parseFloat(data.get("mayores_cjdr"));
            float menores = parseFloat(data.get("menores_cjdr"));
            float total = mayores + menores;

            float porcentajeMayores = (mayores / total) * 100;
            float porcentajeMenores = (menores / total) * 100;

            // Crear una entrada para cada centro_cjdr con los porcentajes de mayores y menores
            entries.add(new BarEntry(i, new float[]{porcentajeMayores, porcentajeMenores}));

            // Asignar texto a los TextView de porcentajes y nombres
            textViewsPorcentaje[i].setText(String.format("Mayores: %.0f\n Menores: %.0f", mayores, menores));
            textViewsNombre[i].setText(data.get("centro_cjdr"));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(this, R.color.Pronacej1)); //1
        colors.add(ContextCompat.getColor(this, R.color.Pronacej4));  //4

        // Crear los conjuntos de datos para el gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(getColors()); // Colores para cada conjunto de barras
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.black));
        dataSet.setStackLabels(new String[]{"Mayores", "Menores"}); // Etiquetas para las partes apiladas

        // Asigna el ValueFormatter al dataSet para mostrar porcentajes
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format("%.1f%%", value); // Mostrar porcentajes en las barras
            }
        });

        // Crear los datos del gráfico de barras
        BarData barData = new BarData(dataSet);

        // Configurar la leyenda
        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setTextSize(8f);

        // Configurar descripción del gráfico
        barChart.getDescription().setEnabled(false);

        // Añadir margen adicional a la izquierda y derecha del gráfico
        barChart.setExtraLeftOffset(10f); // Ajusta el valor según sea necesario
        barChart.setExtraRightOffset(50f); // Añadir margen derecho

        // Configurar el eje X
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

        // Establecer los datos en el gráfico y refrescar
        barChart.setData(barData);
        barChart.invalidate(); // refrescar
    }

    private float parseFloat(String value) {
        try {
            return value == null || value.isEmpty() ? 0 : Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Método para obtener los colores de las barras
    private ArrayList<Integer> getColors() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(this, R.color.Pronacej1)); // Color para mayores
        colors.add(ContextCompat.getColor(this, R.color.Pronacej4));  // Color para menores
        return colors;
    }
}
