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

public class ResultadoAgresoresSexualesSoa extends AppCompatActivity {

    private int agresor_si;
    private int agresor_no;
    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_agresores_sexuales_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoAgresoresSexualesSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        TextView txtSummary = findViewById(R.id.textView28);
        TextView txtPorcentajeSi = findViewById(R.id.textView13);
        TextView txtMensajeSi = findViewById(R.id.textView7);
        TextView txtPorcentajeNo = findViewById(R.id.textView135);
        TextView txtMensajeNo = findViewById(R.id.textView75);

        // Obtener los valores de las variables desde el intent
        agresor_si = getIntent().getIntExtra("agresor_si", 18);
        agresor_no = getIntent().getIntExtra("agresor_no", 72);
        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);
        // Calcular el total
        int total = agresor_si + agresor_no;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(total)));
        // Calcular los porcentajes
        double porcentajeAgresorSi = (total != 0) ? (agresor_si * 100.0 / total) : 0;
        double porcentajeAgresorNo = (total != 0) ? (agresor_no * 100.0 / total) : 0;

        int[] colors = {
                getResources().getColor(android.R.color.holo_red_light),  // Agresor
                getResources().getColor(android.R.color.holo_green_light) // No Agresor
        };
        // Configurar los textos de los TextView
        txtPorcentajeSi.setText(String.format("%d", agresor_si));
        txtMensajeSi.setText(String.format("Si Participan"));
        txtPorcentajeNo.setText(String.format("%d", agresor_no));
        txtMensajeNo.setText(String.format("No Participan"));

        // Configurar el mensaje en el TextView de resumen
        String summaryText = String.format(
                "El gráfico muestra que existe un %.0f%% (%d) de personas que tienen indicios de agresión mientras que el resto %.0f%% (%d) no tienen historia agresiva.",
                porcentajeAgresorSi, agresor_si, porcentajeAgresorNo, agresor_no
        );
        txtSummary.setText(summaryText);

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de pastel
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) porcentajeAgresorSi, "Si Participan"));
        entries.add(new PieEntry((float) porcentajeAgresorNo, "No Participan"));

        // Configurar los colores para las porciones
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueFormatter(new PercentValueFormatter());

        // Agregar los datos al gráfico de pastel
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // Refrescar el gráfico

        // Configurar la leyenda
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(12f);
        legend.setXEntrySpace(10f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
    }
    public class PercentValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
        public String getFormattedValue(float value) {
            return String.format("%.1f%%", value);
        }
    }
}
