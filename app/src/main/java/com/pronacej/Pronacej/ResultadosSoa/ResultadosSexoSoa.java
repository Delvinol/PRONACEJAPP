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

public class ResultadosSexoSoa extends AppCompatActivity {

    private int sexo_masculino;
    private int sexo_femenino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_sexo_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosSexoSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Obtener los valores de sexo masculino y sexo femenino
        Intent intent = getIntent();
        sexo_masculino = intent.getIntExtra("sexo_masculino", 0);
        sexo_femenino = intent.getIntExtra("sexo_femenino", 0);

        // Calcular el total de participantes
        int totalParticipantes = sexo_masculino + sexo_femenino;

        // Calcular los porcentajes
        double porcentajeMasculino = (double) sexo_masculino / totalParticipantes * 100;
        double porcentajeFemenino = (double) sexo_femenino / totalParticipantes * 100;

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej1));
        colors.add(getResources().getColor(R.color.Pronacej2));

        // Crear una lista de entradas de datos para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, sexo_masculino));
        entries.add(new BarEntry(1, sexo_femenino));

        // Crear un conjunto de datos para el gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "Sexo");

        // Configurar los nombres de las columnas en la leyenda
        String[] columnNames = new String[]{"Masculino", "Femenino"};
        dataSet.setStackLabels(columnNames);
        dataSet.setColors(colors); // Colores de las barras

        // Configurar el gráfico de barras
        BarChart chart = findViewById(R.id.chart);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);

        // Crear una instancia de BarData y configurarla con el conjunto de datos
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);

        // Establecer los datos en el gráfico
        chart.setData(barData);
        chart.invalidate();

        // Configurar la leyenda
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        ((TextView) findViewById(R.id.textViewsexo_masculinoPorcentaje)).setText(String.format("%.2f%%", porcentajeMasculino));
        ((TextView) findViewById(R.id.textViewsexo_masculino)).setText("Masculino");
        ((TextView) findViewById(R.id.textViewsexo_femeninoPorcentaje)).setText(String.format("%.2f%%", porcentajeFemenino));
        ((TextView) findViewById(R.id.textViewsexo_femenino)).setText("Femenino");

    }
}
