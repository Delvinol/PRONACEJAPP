package com.pronacej.Pronacej.ResultadosSoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosEstadoCivilSoa extends AppCompatActivity {

    private int estado_civil_casado;
    private int estado_civil_conviviente;
    private int estado_civil_separado;
    private int estado_civil_soltero;
    private int estado_civil_viudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_estado_civil_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosEstadoCivilSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });

        // Obtener los valores de estado civil desde el intent
        Intent intent = getIntent();
        estado_civil_casado = intent.getIntExtra("estado_civil_casado", 0);
        estado_civil_conviviente = intent.getIntExtra("estado_civil_conviviente", 0);
        estado_civil_separado = intent.getIntExtra("estado_civil_separado", 0);
        estado_civil_soltero = intent.getIntExtra("estado_civil_soltero", 0);
        estado_civil_viudo = intent.getIntExtra("estado_civil_viudo", 0);

        // Calcular el total de estados civiles
        int totalEstadoCivil = estado_civil_casado + estado_civil_conviviente + estado_civil_separado +
                estado_civil_soltero + estado_civil_viudo;

        // Calcular los porcentajes
        double porcentajeCasado = (double) estado_civil_casado / totalEstadoCivil * 100;
        double porcentajeConviviente = (double) estado_civil_conviviente / totalEstadoCivil * 100;
        double porcentajeSeparado = (double) estado_civil_separado / totalEstadoCivil * 100;
        double porcentajeSoltero = (double) estado_civil_soltero / totalEstadoCivil * 100;
        double porcentajeViudo = (double) estado_civil_viudo / totalEstadoCivil * 100;

        // Construir el texto
        String texto = String.format(
                "Casado: %d personas (%.2f%%) " +
                        "Conviviente: %d personas (%.2f%%) " +
                        "Separado: %d personas (%.2f%%) " +
                        "Soltero: %d personas (%.2f%%) " +
                        "Viudo: %d personas (%.2f%%)",
                estado_civil_casado, porcentajeCasado,
                estado_civil_conviviente, porcentajeConviviente,
                estado_civil_separado, porcentajeSeparado,
                estado_civil_soltero, porcentajeSoltero,
                estado_civil_viudo, porcentajeViudo);


        ((TextView) findViewById(R.id.textViewAlfonsoUgartePorcentaje)).setText(String.format("%.2f%%", porcentajeCasado));
        ((TextView) findViewById(R.id.textViewAlfonso_Ugarte)).setText("Casados");
        ((TextView) findViewById(R.id.textViewMarcavallePorcentaje)).setText(String.format("%.2f%%", porcentajeConviviente));
        ((TextView) findViewById(R.id.textViewMarcavalle)).setText("Convivientes");
        ((TextView) findViewById(R.id.textViewPucallpaPorcentaje)).setText(String.format("%.2f%%", porcentajeSeparado));
        ((TextView) findViewById(R.id.textViewPucallpa)).setText("Separado");
        ((TextView) findViewById(R.id.textViewEl_TamboPorcentaje)).setText(String.format("%.2f%%", porcentajeSoltero));
        ((TextView) findViewById(R.id.textViewEl_Tambo)).setText("Soltero");
        ((TextView) findViewById(R.id.textViewTrujilloPorcentaje)).setText(String.format("%.2f%%", porcentajeViudo));
        ((TextView) findViewById(R.id.textViewTrujillo)).setText("Viudo");


        // Crear una lista de entradas de datos para el gráfico de pastel
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(estado_civil_casado, "Casado"));
        entries.add(new PieEntry(estado_civil_conviviente, "Conviviente"));
        entries.add(new PieEntry(estado_civil_separado, "Separado"));
        entries.add(new PieEntry(estado_civil_soltero, "Soltero"));
        entries.add(new PieEntry(estado_civil_viudo, "Viudo"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej7));


        // Crear un conjunto de datos para el gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "Estado Civil");
        dataSet.setColors(colors);

        // Configurar el gráfico de pastel
        PieChart chart = findViewById(R.id.pieChart);
        chart.getDescription().setEnabled(false);

        // Crear una instancia de PieData y configurarla con el conjunto de datos
        PieData pieData = new PieData(dataSet);
        chart.setData(pieData);
        chart.invalidate(); // Refrescar el gráfico
    }
}
