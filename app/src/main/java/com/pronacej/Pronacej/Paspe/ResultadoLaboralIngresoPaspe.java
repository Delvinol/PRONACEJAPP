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

public class ResultadoLaboralIngresoPaspe extends AppCompatActivity {

    private int trabaja_formal;
    private int trabaja_informal;
    private int trabaja_sin;

    private TextView textViewTotalCantidad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_laboral_ingreso_paspe);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadoLaboralIngresoPaspe.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        Intent intent = getIntent();
        int trabaja_formal = intent.getIntExtra("trabaja_formal", 0);
        int trabaja_informal = intent.getIntExtra("trabaja_informal", 0);
        int trabaja_sin = intent.getIntExtra("trabaja_sin", 0);
        int trabaja_eti = intent.getIntExtra("trabaja_eti", 0);
        int trabaja_paspe = intent.getIntExtra("trabaja_paspe", 0);


        int totalSLA = trabaja_formal + trabaja_informal + trabaja_sin+trabaja_eti+trabaja_paspe;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalSLA)));

// Calcular los porcentajes
        double porcentajeTrabajaInformal = (double) trabaja_informal / totalSLA * 100;
        double porcentajeTrabajaFormal = (double) trabaja_formal / totalSLA * 100;
        double porcentajeTrabajaSin = (double) trabaja_sin / totalSLA * 100;
        double porcentajeTrabajaEti = (double) trabaja_eti / totalSLA * 100;
        double porcentajeTrabajaPaspe = (double) trabaja_paspe / totalSLA * 100;

        ((TextView) findViewById(R.id.textViewinser_labo_internaPorcentaje)).setText(String.format("%d", (int) Math.round(trabaja_informal)));
        ((TextView) findViewById(R.id.textViewinser_labo_interna)).setText("Trabaja Informal");

        ((TextView) findViewById(R.id.textViewinser_labo_externaPorcentaje)).setText(String.format("%d", (int) Math.round(trabaja_formal)));
        ((TextView) findViewById(R.id.textViewinser_labo_externa)).setText("Trabaja Formal");

        ((TextView) findViewById(R.id.textView_sin_Porcentaje)).setText(String.format("%d", (int) Math.round(trabaja_sin)));
        ((TextView) findViewById(R.id.textViewinser_sin)).setText("Sin Trabajo");

        ((TextView) findViewById(R.id.textViewEtiCantidad)).setText(String.format("%d", (int) Math.round(trabaja_eti)));
        ((TextView) findViewById(R.id.textViewEtiNombre)).setText("TRABAJA POR INSERCIÓN LABORAL - ETI SOA");

        ((TextView) findViewById(R.id.textViewInserLaboPaspeCantidad)).setText(String.format("%d", (int) Math.round(trabaja_paspe)));
        ((TextView) findViewById(R.id.textViewIserLaboPaspe)).setText("TRABAJA POR INSERCIÓN LABORAL - PASPE");


        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(true);



        // Crear las entradas del gráfico de pastel en dos columnas
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) porcentajeTrabajaInformal, "Trabaja Informal"));
        entries.add(new PieEntry((float) porcentajeTrabajaFormal, "Trabaja Formal"));
        entries.add(new PieEntry((float) porcentajeTrabajaSin, "Sin Trabajo"));
        entries.add(new PieEntry((float) porcentajeTrabajaEti, "ETI SOA"));
        entries.add(new PieEntry((float) porcentajeTrabajaPaspe, "Inserción Lab. Paspe"));


        // Si quieres que "Sin Trabajo" esté abajo, agrega la entrada de Sin Trabajo al final de la lista
        // y asegúrate de mantener el orden de las otras dos entradas como prefieras.

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6)); // Color para Trabaja Informalmente
        colors.add(getResources().getColor(R.color.Pronacej2)); // Color para Trabaja Formalmente
        colors.add(getResources().getColor(R.color.Pronacej5)); // Color para Sin Trabajo
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej8));

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
