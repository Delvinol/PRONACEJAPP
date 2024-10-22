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

public class ResultadosFirmesAdelanteSoa extends AppCompatActivity {
    private int firmes_aplica;
    private int firmes_no_aplica;
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_firme_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosFirmesAdelanteSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        firmes_aplica = getIntent().getIntExtra("firmes_aplica", 0);
        firmes_no_aplica = getIntent().getIntExtra("firmes_no_aplica", 0);
        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);
        int totalFirmes = firmes_aplica + firmes_no_aplica;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalFirmes)));
        // Calcular los porcentajes
        double porcentajeAplica = (double) firmes_aplica / totalFirmes * 100;
        double porcentajeNoAplica = (double) firmes_no_aplica / totalFirmes * 100;

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) porcentajeAplica, "Si Participan"));
        entries.add(new PieEntry((float) porcentajeNoAplica, "No Participan"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej2));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueFormatter(new PercentValueFormatter());
        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        pieChart.getDescription().setEnabled(false);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        pieChart.invalidate();

        ((TextView) findViewById(R.id.textViewfirmes_aplicaPorcentaje)).setText(String.format("%d", firmes_aplica));
        ((TextView) findViewById(R.id.textViewfirmes_aplica)).setText("Si Participan");

        ((TextView) findViewById(R.id.textViewfirmes_no_aplicaPorcentaje)).setText(String.format("%d", firmes_no_aplica));
        ((TextView) findViewById(R.id.textViewfirmes_no_aplica)).setText("No Participan");
    }
    public class PercentValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        public String getFormattedValue(float value) {
            return String.format("%.1f%%", value);
        }
    }
}
