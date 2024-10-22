package com.pronacej.Pronacej.ResultadosSoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultadosProgramasSoa extends AppCompatActivity {

    private int participa_programa_uno;
    private int participa_programa_dos;
    private int participa_programa_tres;
    private int participa_programa_cuatro;
    private int participa_programa_cinco;
    private int participa_programa_no;

    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_programacion_soa);



        //Obtener los valores de los programas
        Intent intent = getIntent();
        participa_programa_uno = intent.getIntExtra("participa_programa_uno", 0);
        participa_programa_dos = intent.getIntExtra("participa_programa_dos", 0);
        participa_programa_tres = intent.getIntExtra("participa_programa_tres", 0);
        participa_programa_cuatro = intent.getIntExtra("participa_programa_cuatro", 0);
        participa_programa_cinco = intent.getIntExtra("participa_programa_cinco", 0);
        participa_programa_no = intent.getIntExtra("participa_programa_no", 0);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosProgramasSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Calcular el total de participantes
        int totalParticipantes = participa_programa_uno + participa_programa_dos + participa_programa_tres +
                participa_programa_cuatro + participa_programa_cinco + participa_programa_no;
        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalParticipantes)));
        // Calcular los porcentajes
        double porcentajeUno = (double) participa_programa_uno / totalParticipantes * 100;
        double porcentajeDos = (double) participa_programa_dos / totalParticipantes * 100;
        double porcentajeTres = (double) participa_programa_tres / totalParticipantes * 100;
        double porcentajeCuatro = (double) participa_programa_cuatro / totalParticipantes * 100;
        double porcentajeCinco = (double) participa_programa_cinco / totalParticipantes * 100;
        double porcentajeNo = (double) participa_programa_no / totalParticipantes * 100;

        // Configurar el gráfico de barras
        HorizontalBarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setExtraRightOffset(50f); // Añadir margen derecho

        // Configurar los datos para el gráfico
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, (float) porcentajeUno));
        entries.add(new BarEntry(1f, (float) porcentajeDos));
        entries.add(new BarEntry(2f, (float) porcentajeTres));
        entries.add(new BarEntry(3f, (float) porcentajeCuatro));
        entries.add(new BarEntry(4f, (float) porcentajeCinco));
        entries.add(new BarEntry(5f, (float) porcentajeNo));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej8));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej2));

        BarDataSet barDataSet = new BarDataSet(entries, "Programas");
        barDataSet.setColors(colors);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.8f);
        barData.setValueFormatter(new ValueFormatter() {
            private DecimalFormat df = new DecimalFormat("0.0");
            @Override
            public String getFormattedValue(float value) {
                return df.format(value) + "%";
            }
        });

        // Configurar ejes y leyenda
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Participa en 1", "Participa en 2", "Participa en 3", "Participa en 4", "Participa en 5", "Sin programa"}));

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setAxisMaximum(100f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Establecer los datos en el gráfico
        barChart.setData(barData);
        barChart.invalidate();

        // Configurar los TextView para mostrar los números
        ((TextView) findViewById(R.id.textView14Porcentaje)).setText(String.valueOf(participa_programa_uno));
        ((TextView) findViewById(R.id.textView14)).setText("En un Programa");
        ((TextView) findViewById(R.id.textViewparticipa_programa_dosPorcentaje)).setText(String.valueOf(participa_programa_dos));
        ((TextView) findViewById(R.id.textViewparticipa_programa_dos)).setText("En dos Programas");
        ((TextView) findViewById(R.id.textViewparticipa_programa_tresPorcentaje)).setText(String.valueOf(participa_programa_tres));
        ((TextView) findViewById(R.id.textViewparticipa_programa_tres)).setText("En tres Programas");
        ((TextView) findViewById(R.id.textViewparticipa_programa_cuatroPorcentaje)).setText(String.valueOf(participa_programa_cuatro));
        ((TextView) findViewById(R.id.textViewparticipa_programa_cuatro)).setText("En cuatro Programas");
        ((TextView) findViewById(R.id.textViewQuincePorcentaje)).setText(String.valueOf(participa_programa_cinco));
        ((TextView) findViewById(R.id.textViewCatorce)).setText("En cinco Programas");
        ((TextView) findViewById(R.id.textViewparticipa_programa_noPorcentaje)).setText(String.valueOf(participa_programa_no));
        ((TextView) findViewById(R.id.textViewparticipa_programa_no)).setText("Sin Programas");
    }
}