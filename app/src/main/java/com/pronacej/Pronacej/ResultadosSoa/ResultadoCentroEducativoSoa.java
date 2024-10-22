package com.pronacej.Pronacej.ResultadosSoa;

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
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class ResultadoCentroEducativoSoa extends AppCompatActivity {

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
        setContentView(R.layout.resultado_centro_educativo_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoCentroEducativoSoa.this, CategoriaMenu.class);
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
        ((TextView) findViewById(R.id.textViewcebrPorcentaje)).setText(String.format("%d", cebr));
        ((TextView) findViewById(R.id.textViewcebr)).setText("CEBR");

        ((TextView) findViewById(R.id.textViewcebaPorcentaje)).setText(String.format("%d", ceba));
        ((TextView) findViewById(R.id.textViewceba)).setText("CEBA");

        ((TextView) findViewById(R.id.textViewceprePorcentaje)).setText(String.format("%d", cepre));
        ((TextView) findViewById(R.id.textViewcepre)).setText("CEPRE");

        ((TextView) findViewById(R.id.textViewacademiaPorcentaje)).setText(String.format("%d", academia));
        ((TextView) findViewById(R.id.textViewacademia)).setText("Academia");

        ((TextView) findViewById(R.id.textViewcetproPorcentaje)).setText(String.format("%d", cetpro));
        ((TextView) findViewById(R.id.textViewcetpro)).setText("CETPRO");

        ((TextView) findViewById(R.id.textViewinstitutoPorcentaje)).setText(String.format("%d", instituto));
        ((TextView) findViewById(R.id.textViewinstituto)).setText("Instituto");

        ((TextView) findViewById(R.id.textViewuniversidadPorcentaje)).setText(String.format("%d", universidad));
        ((TextView) findViewById(R.id.textViewuniversidad)).setText("Universidad");

        ((TextView) findViewById(R.id.textViewningunoPorcentaje)).setText(String.format("%d", ninguno));
        ((TextView) findViewById(R.id.textViewninguno)).setText("No aplica");

        // Configurar el gráfico de barras horizontales
        HorizontalBarChart barChart = findViewById(R.id.barChart);

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

        BarDataSet dataSet = new BarDataSet(entries, "Centro Educativo");
        dataSet.setValueFormatter(new PercentValueFormatter());

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
        dataSet.setColors(colors);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);


        BarData barData = new BarData(dataSets);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(legendLabels));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Habilitar zoom y scroll
        barChart.setScaleEnabled(true);
        barChart.setPinchZoom(true);

        barChart.invalidate();
    }
    public class PercentValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        public String getFormattedValue(float value) {
            return String.format("%.1f%%", value);
        }
    }
}
