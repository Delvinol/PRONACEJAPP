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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class ResultadoPoblacionPorGenero extends AppCompatActivity {

    private TextView textViewTotalCantidad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_genero_paspe);

        // Referencia a los botones
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonHome = findViewById(R.id.buttonHome);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);
        // Configuración del botón de inicio
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoPoblacionPorGenero.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        // Configuración del botón de atrás
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Obtener los datos del intent
        Intent intent = getIntent();
        int sexo_masculino = intent.getIntExtra("sexo_masculino", 0);
        int sexo_femenino = intent.getIntExtra("sexo_femenino", 0);

        // Calcular el total
        int total = sexo_masculino + sexo_femenino;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(total)));
        // Calcular los porcentajes
        double porcentajeMasculino = (double) sexo_masculino / total * 100;
        double porcentajeFemenino = (double) sexo_femenino / total * 100;

        // Actualizar los TextView con los valores numéricos
        TextView textViewMasculinoPorcentaje = findViewById(R.id.textViewinser_labo_internaPorcentaje);
        TextView textViewMasculino = findViewById(R.id.textViewinser_labo_interna);
        textViewMasculinoPorcentaje.setText(String.valueOf(sexo_masculino));
        textViewMasculino.setText("Masculino");

        TextView textViewFemeninoPorcentaje = findViewById(R.id.textViewinser_labo_externaPorcentaje);
        TextView textViewFemenino = findViewById(R.id.textViewinser_labo_externa);
        textViewFemeninoPorcentaje.setText(String.valueOf(sexo_femenino));
        textViewFemenino.setText("Femenino");

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(true);

        // Crear las entradas del gráfico de pastel
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) porcentajeMasculino, "Masculino"));
        entries.add(new PieEntry((float) porcentajeFemenino, "Femenino"));

        // Configurar los colores del gráfico
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6)); // Color para Masculino
        colors.add(getResources().getColor(R.color.Pronacej2)); // Color para Femenino

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(10f); // Tamaño del texto en sp
        dataSet.setValueFormatter(new PercentFormatter(pieChart));

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // Refrescar el gráfico

        // Personalizar la leyenda
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(10f); // Reduce el tamaño del texto de la leyenda
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(10f);
        legend.setXEntrySpace(10f);
        legend.setTextColor(getResources().getColor(R.color.black));
        legend.setWordWrapEnabled(true); // Permitir el ajuste de línea si el texto es demasiado largo
    }
}
