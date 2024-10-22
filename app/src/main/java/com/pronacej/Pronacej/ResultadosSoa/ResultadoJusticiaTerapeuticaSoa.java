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
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class ResultadoJusticiaTerapeuticaSoa extends AppCompatActivity {

    private int justicia_si;
    private int justicia_no;
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_justicia_terapeutica_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoJusticiaTerapeuticaSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        justicia_si = getIntent().getIntExtra("justicia_si", 0);
        justicia_no = getIntent().getIntExtra("justicia_no", 0);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        int total = justicia_si + justicia_no;

        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(total)));
        float porcentaje_si = ((float) justicia_si / total) * 100;
        float porcentaje_no = ((float) justicia_no / total) * 100;

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de pastel
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(porcentaje_si, "Si Participan"));
        entries.add(new PieEntry(porcentaje_no, "No Participan"));

        // Configurar los colores para las entradas
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(android.R.color.holo_green_light));
        colors.add(getResources().getColor(android.R.color.holo_red_light));

        // Crear el conjunto de datos del gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);

        // Configurar el tamaño del texto dentro de las entradas
        dataSet.setValueTextSize(12f);

        // Agregar leyenda
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        data.setValueFormatter(new PercentValueFormatter());

        // Configurar la leyenda
        pieChart.getLegend().setEnabled(true);

        pieChart.invalidate(); // Refrescar el gráfico

        // Calcular los porcentajes


        ((TextView) findViewById(R.id.textView14Porcentaje)).setText(String.format("%d", justicia_si));
        ((TextView) findViewById(R.id.textView14)).setText("Si Participan");
        ((TextView) findViewById(R.id.textViewjusticia_noPorcentaje)).setText(String.format("%d", justicia_no));
        ((TextView) findViewById(R.id.textViewjusticia_no)).setText("No Participan");



    }
    public class PercentValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        public String getFormattedValue(float value) {
            return String.format("%.1f%%", value);
        }
    }
}
