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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadoInterTera extends AppCompatActivity {
    private int intervencion_aplica;
    private int intervencion_no_aplica;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_inter_tera);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoInterTera.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        intervencion_aplica = getIntent().getIntExtra("intervencion_aplica", 0);
        intervencion_no_aplica = getIntent().getIntExtra("intervencion_no_aplica", 0);

        // Calcular el total
        int totalIntervenciones = intervencion_aplica + intervencion_no_aplica;

        // Calcular los porcentajes
        double porcentajeAplica = (double) intervencion_aplica / totalIntervenciones * 100;
        double porcentajeNoAplica = (double) intervencion_no_aplica / totalIntervenciones * 100;

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de pastel
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(intervencion_aplica, "Si Participan"));
        entries.add(new PieEntry(intervencion_no_aplica, "No Participan"));

        // Crear el conjunto de datos del gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_red_light));

        // Configurar el tamaño del texto dentro del gráfico de pastel
        dataSet.setValueTextSize(12f);

        // Configurar la leyenda
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda

        // Agregar los datos al gráfico de pastel
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // Refrescar el gráfico

        // Mostrar los datos y porcentajes en el TextView
        String texto = String.format(
                "Se ve que existen %.2f%% (%d) de personas que no han recibido intervenciòn terapeutica mientras que el %.2f%% (%d) de personas si han recibido.",
                porcentajeNoAplica, intervencion_no_aplica,
                porcentajeAplica, intervencion_aplica);


        ((TextView) findViewById(R.id.textViewintervencion_aplicaPorcentaje)).setText(String.format("%.2f%%", porcentajeAplica));
        ((TextView) findViewById(R.id.textViewintervencion_aplica)).setText("Si Participan");

        ((TextView) findViewById(R.id.textViewintervencion_no_aplicaPorcentaje)).setText(String.format("%.2f%%", porcentajeNoAplica));
        ((TextView) findViewById(R.id.textViewintervencion_no_aplica)).setText("No Participan");


    }
}
