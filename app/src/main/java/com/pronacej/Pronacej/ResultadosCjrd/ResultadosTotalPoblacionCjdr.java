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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosTotalPoblacionCjdr extends AppCompatActivity {

    private int totalRegistros;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_total_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosTotalPoblacionCjdr.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        BarChart barChart = findViewById(R.id.barChart);

        totalRegistros = getIntent().getIntExtra("totalRegistros", 0);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, totalRegistros));

        BarDataSet dataSet = new BarDataSet(entries, "Total Registros");
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        // Agregar leyenda
        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

        // Actualizar el TextView con el total de registros
        TextView textView28 = findViewById(R.id.textView28);
        String mensaje = "Total de registros: " + totalRegistros;
        textView28.setText(mensaje);
    }
}
