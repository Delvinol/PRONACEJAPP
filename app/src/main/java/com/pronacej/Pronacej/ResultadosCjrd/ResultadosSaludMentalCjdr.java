package com.pronacej.Pronacej.ResultadosCjrd;

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

public class ResultadosSaludMentalCjdr extends AppCompatActivity {

    private int salud_si;
    private int salud_no;
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_salud_mental_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosSaludMentalCjdr.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Obtener los valores de salud mental desde el intent
        salud_si = getIntent().getIntExtra("salud_si", 0);
        salud_no = getIntent().getIntExtra("salud_no", 0);
        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        int totalSalud = salud_no + salud_si;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalSalud)));
        // Calcular los porcentajes
        double porcentajesalud_si = (double) salud_si / totalSalud * 100;
        double porcentajesalud_no = (double) salud_no / totalSalud * 100;


        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);


        // Crear las entradas para el gráfico de pastel
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) porcentajesalud_si, "Si Participan"));
        entries.add(new PieEntry((float) porcentajesalud_no, "No Participan"));

        // Crear el conjunto de datos del gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_red_light));
        dataSet.setValueFormatter(new PercentValueFormatter());


        // Configurar el tamaño del texto dentro del gráfico de pastel
        dataSet.setValueTextSize(12f);

        // Configurar la leyenda
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda

        // Agregar los datos al gráfico de pastel
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // Refrescar el gráfico


        ((TextView) findViewById(R.id.textViewsalud_siPorcentaje)).setText(String.format("%d", (int) Math.round(salud_si)));
        ((TextView) findViewById(R.id.textViewsalud_si)).setText("Si Participan");


        ((TextView) findViewById(R.id.textViewsalud_noPorcentaje)).setText(String.format("%d", (int) Math.round(salud_no)));
        ((TextView) findViewById(R.id.textViewsalud_no)).setText("No Participan");



    }

    public class PercentValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        public String getFormattedValue(float value) {
            return String.format("%.1f%%", value);
        }
    }
}
