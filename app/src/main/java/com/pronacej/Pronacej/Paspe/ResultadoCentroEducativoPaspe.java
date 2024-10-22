package com.pronacej.Pronacej.Paspe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
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

public class ResultadoCentroEducativoPaspe extends AppCompatActivity {

    private int cebr;
    private int ceba;
    private int cepre;
    private int academia;
    private int cetpro;
    private int instituto;
    private int universidad;
    private int ninguno;
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_centro_educativo_paspe);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoCentroEducativoPaspe.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Obtener los valores de las variables desde el intent
        Intent intent = getIntent();
        cebr = getIntent().getIntExtra("cebr", 0);
        ceba = getIntent().getIntExtra("ceba", 0);
        cepre = getIntent().getIntExtra("cepre", 0);
        academia = getIntent().getIntExtra("academia", 0);
        cetpro = getIntent().getIntExtra("cetpro", 0);
        instituto = getIntent().getIntExtra("instituto", 0);
        universidad = getIntent().getIntExtra("universidad", 0);
        ninguno = getIntent().getIntExtra("no_aplica", 0);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        // Calcular el total de personas
        int totalPersonas = cebr + ceba + cepre + academia + cetpro + instituto + universidad + ninguno;

        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalPersonas)));

        // Calcular los porcentajes
        double porcentajeCebr = (double) cebr / totalPersonas * 100;
        double porcentajeCeba = (double) ceba / totalPersonas * 100;
        double porcentajeCepre = (double) cepre / totalPersonas * 100;
        double porcentajeAcademia = (double) academia / totalPersonas * 100;
        double porcentajeCetpro = (double) cetpro / totalPersonas * 100;
        double porcentajeInstituto = (double) instituto / totalPersonas * 100;
        double porcentajeUniversidad = (double) universidad / totalPersonas * 100;
        double porcentajeNinguno = (double) ninguno / totalPersonas * 100;

        // Configurar TextViews para mostrar los porcentajes y nombres
        ((TextView) findViewById(R.id.textViewcebrPorcentaje)).setText(String.valueOf(cebr));
        ((TextView) findViewById(R.id.textViewcebr)).setText("CEBR");

        ((TextView) findViewById(R.id.textViewcebaPorcentaje)).setText(String.valueOf(ceba));
        ((TextView) findViewById(R.id.textViewceba)).setText("CEBA");

        ((TextView) findViewById(R.id.textViewceprePorcentaje)).setText(String.valueOf(cepre));
        ((TextView) findViewById(R.id.textViewcepre)).setText("CEPRE");

        ((TextView) findViewById(R.id.textViewacademiaPorcentaje)).setText(String.valueOf(academia));
        ((TextView) findViewById(R.id.textViewacademia)).setText("Academia");

        ((TextView) findViewById(R.id.textViewcetproPorcentaje)).setText(String.valueOf(cetpro));
        ((TextView) findViewById(R.id.textViewcetpro)).setText("CETPRO");

        ((TextView) findViewById(R.id.textViewinstitutoPorcentaje)).setText(String.valueOf(instituto));
        ((TextView) findViewById(R.id.textViewinstituto)).setText("Instituto");

        ((TextView) findViewById(R.id.textViewuniversidadPorcentaje)).setText(String.valueOf(universidad));
        ((TextView) findViewById(R.id.textViewuniversidad)).setText("Universidad");

        ((TextView) findViewById(R.id.textViewningunoPorcentaje)).setText(String.valueOf(ninguno));
        ((TextView) findViewById(R.id.textViewninguno)).setText("No aplica");

        // Configurar el gráfico de barras horizontales
        HorizontalBarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, (float) porcentajeCebr));
        entries.add(new BarEntry(1, (float) porcentajeCeba));
        entries.add(new BarEntry(2, (float) porcentajeCepre));
        entries.add(new BarEntry(3, (float) porcentajeAcademia));
        entries.add(new BarEntry(4, (float) porcentajeCetpro));
        entries.add(new BarEntry(5, (float) porcentajeInstituto));
        entries.add(new BarEntry(6, (float) porcentajeUniversidad));
        entries.add(new BarEntry(7, (float) porcentajeNinguno));


        // Configurar los nombres en la leyenda
        String[] legendLabels = {"CEBR", "CEBA", "CEPRE", "Academia", "CETPRO", "Instituto", "Universidad", "No aplica"};



        // Asignar colores a las barras
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej1));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej8));

        BarDataSet dataSet = new BarDataSet(entries, "Centro Educativo");
        dataSet.setColors(colors);
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f); // Ancho de las barras
        data.setValueTextSize(14f); // Aumenta el tamaño del texto

        // Nuevo formateador personalizado
        data.setValueFormatter(new ValueFormatter() {
            private DecimalFormat mFormat = new DecimalFormat("0.0");
            @Override
            public String getFormattedValue(float value) {
                return mFormat.format(value) + "%";
            }
        });

        barChart.setData(data);
        barChart.setFitBars(true);

        // Configurar el eje X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{
                "CEBR", "CEBA", "CEPRE", "Academia",
                "CETPRO", "Instituto", "Universidad", "No aplica"
        }));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(10);

        // Configurar el eje Y (izquierda y derecha)
        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        barChart.invalidate(); // refrescar el gráfico
    }
}
