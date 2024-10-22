package com.pronacej.Pronacej.ResultadosSoa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosEstadoSoa extends AppCompatActivity {

    private int estado_cierre_post;
    private int estado_egr;
    private int estado_ing;
    private int estado_ing_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_estado_soa);

        Intent intent = getIntent();
        estado_cierre_post = intent.getIntExtra("estado_cierre_post", 0);
        estado_egr = intent.getIntExtra("estado_egr", 0);
        estado_ing = intent.getIntExtra("estado_ing", 0);
        estado_ing_post = intent.getIntExtra("estado_ing_post", 0);

        // Calcular el total
        int totalEstados = estado_cierre_post + estado_egr + estado_ing + estado_ing_post;

        // Calcular los porcentajes
        double porcentajeCierrePost = (double) estado_cierre_post / totalEstados * 100;
        double porcentajeEgreso = (double) estado_egr / totalEstados * 100;
        double porcentajeIngreso = (double) estado_ing / totalEstados * 100;
        double porcentajeIngresoPost = (double) estado_ing_post / totalEstados * 100;

        ((TextView) findViewById(R.id.textViewestado_cierre_postPorcentaje)).setText(String.format("%.2f%%", porcentajeCierrePost));
        ((TextView) findViewById(R.id.textViewestado_cierre_post)).setText("Cierre post");
        ((TextView) findViewById(R.id.textViewestado_egrPorcentaje)).setText(String.format("%.2f%%", porcentajeEgreso));
        ((TextView) findViewById(R.id.textViewestado_egr)).setText("Egreso");
        ((TextView) findViewById(R.id.textViewestado_ingPorcentaje)).setText(String.format("%.2f%%", porcentajeIngreso));
        ((TextView) findViewById(R.id.textViewestado_ing)).setText("Ingreso");
        ((TextView) findViewById(R.id.textViewestado_ing_postPorcentaje)).setText(String.format("%.2f%%", porcentajeIngresoPost));
        ((TextView) findViewById(R.id.textViewestado_ing_post)).setText("Ingreso Post");

        // Crear una lista de entradas de datos para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, estado_cierre_post));
        entries.add(new BarEntry(1, estado_egr));
        entries.add(new BarEntry(2, estado_ing));
        entries.add(new BarEntry(3, estado_ing_post));

        // Crear un conjunto de datos para el gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "Estado");

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej8));
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej5));

        // Configurar los nombres de las columnas en la leyenda
        String[] columnNames = new String[]{"Cierre Post", "Egreso", "Ingreso", "Ingreso Post"};
        dataSet.setStackLabels(columnNames);
        dataSet.setColors(colors); // Colores de las barras

        // Configurar el gráfico de barras
        BarChart chart = findViewById(R.id.barChart);
        chart.getDescription().setEnabled(false);

        // Crear una instancia de BarData y configurarla con el conjunto de datos
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);

        // Establecer los datos en el gráfico
        chart.setData(barData);
        chart.invalidate();

        // Configurar la leyenda
        Legend legend = chart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda
        legend.setTextSize(12f); // Tamaño del texto de la leyenda
        legend.setForm(Legend.LegendForm.SQUARE); // Forma de la leyenda
        legend.setTextColor(Color.BLACK); // Color del texto de la leyenda
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM); // Alineación vertical de la leyenda
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER); // Alineación horizontal de la leyenda
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Orientación de la leyenda
        legend.setDrawInside(false); // Dibujar la leyenda dentro del gráfico

        // Mostrar los datos y porcentajes en el TextView
        String texto = String.format(
                "Cierre Post: %d personas (%.2f%%) " +
                        "Egreso: %d personas (%.2f%%) " +
                        "Ingreso: %d personas (%.2f%%) " +
                        "Ingreso Post: %d personas (%.2f%%)",
                estado_cierre_post, porcentajeCierrePost,
                estado_egr, porcentajeEgreso,
                estado_ing, porcentajeIngreso,
                estado_ing_post, porcentajeIngresoPost);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosEstadoSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });


    }
}
