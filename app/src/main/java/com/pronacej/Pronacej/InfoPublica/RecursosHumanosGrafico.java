package com.pronacej.Pronacej.InfoPublica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.RecursoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecursosHumanosGrafico extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private TextView[] textViewsPorcentaje = new TextView[52];
    private TextView[] textViewsNombre = new TextView[52];
    private TextView textViewTotalCantidad1;

    private List<Map<String, Object>> trabajadoresData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_recursos_humanos);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(RecursosHumanosGrafico.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        ButtonBack.setOnClickListener(v -> onBackPressed());

        barChart = findViewById(R.id.barChart);
        initializeViews();
        fetchTrabajadoresData();
    }

    private void initializeViews() {
        textViewsPorcentaje[0] = findViewById(R.id.textViewAlfonsoUgartePorcentaje);
        textViewsPorcentaje[1] = findViewById(R.id.textViewMarcavallePorcentaje);
        textViewsPorcentaje[2] = findViewById(R.id.textViewPucallpaPorcentaje);
        textViewsPorcentaje[3] = findViewById(R.id.textViewEl_TamboPorcentaje);
        textViewsPorcentaje[4] = findViewById(R.id.textViewTrujilloPorcentaje);
        textViewsPorcentaje[5] = findViewById(R.id.textViewJose_QuinonesPorcentaje);
        textViewsPorcentaje[6] = findViewById(R.id.textViewMiguel_GrauPorcentaje);
        textViewsPorcentaje[7] = findViewById(R.id.textViewSanta_MargaritaPorcentaje);
        textViewsPorcentaje[8] = findViewById(R.id.textViewAnexoIIIPorcentaje);
        textViewsPorcentaje[9] = findViewById(R.id.textViewLimaPorcentaje);
        textViewsPorcentaje[10] = findViewById(R.id.textViewAlfonsoUgartePorcentajeNUEVO);
        textViewsPorcentaje[11] = findViewById(R.id.textViewMarcavallePorcentajeNUEVO);
        textViewsPorcentaje[12] = findViewById(R.id.textViewPucallpaPorcentajeNUEVO);
        textViewsPorcentaje[13] = findViewById(R.id.textViewEl_TamboPorcentajeNUEVO);
        textViewsPorcentaje[14] = findViewById(R.id.textViewTrujilloPorcentajeNUEVO);
        textViewsPorcentaje[15] = findViewById(R.id.textViewJose_QuinonesPorcentajeNUEVO);
        textViewsPorcentaje[16] = findViewById(R.id.textViewMiguel_GrauPorcentajeNUEVO);
        textViewsPorcentaje[17] = findViewById(R.id.textViewSanta_MargaritaPorcentajeNUEVO);
        textViewsPorcentaje[18] = findViewById(R.id.textViewAnexoIIIPorcentajeNUEVO);
        textViewsPorcentaje[19] = findViewById(R.id.textViewLimaPorcentajeNUEVO);
        textViewsPorcentaje[20] = findViewById(R.id.textViewAlfonsoUgartePorcentajeNUEVO2);
        textViewsPorcentaje[21] = findViewById(R.id.textViewMarcavallePorcentajeNUEVO2);
        textViewsPorcentaje[22] = findViewById(R.id.textViewPucallpaPorcentajeNUEVO2);
        textViewsPorcentaje[23] = findViewById(R.id.textViewEl_TamboPorcentajeNUEVO2);
        textViewsPorcentaje[24] = findViewById(R.id.textViewTrujilloPorcentajeNUEVO2);
        textViewsPorcentaje[25] = findViewById(R.id.textViewJose_QuinonesPorcentajeNUEVO2);
        textViewsPorcentaje[26] = findViewById(R.id.textViewMiguel_GrauPorcentajeNUEVO2);
        textViewsPorcentaje[27] = findViewById(R.id.textViewSanta_MargaritaPorcentajeNUEVO2);
        textViewsPorcentaje[28] = findViewById(R.id.textViewAnexoIIIPorcentajeNUEVO2);
        textViewsPorcentaje[29] = findViewById(R.id.textViewLimaPorcentajeNUEVO2);
        textViewsPorcentaje[30] = findViewById(R.id.textViewAlfonsoUgartePorcentajeNUEVO3);
        textViewsPorcentaje[31] = findViewById(R.id.textViewMarcavallePorcentajeNUEVO3);
        textViewsPorcentaje[32] = findViewById(R.id.textViewPucallpaPorcentajeNUEVO3);
        textViewsPorcentaje[33] = findViewById(R.id.textViewEl_TamboPorcentajeNUEVO3);
        textViewsPorcentaje[34] = findViewById(R.id.textViewTrujilloPorcentajeNUEVO3);
        textViewsPorcentaje[35] = findViewById(R.id.textViewJose_QuinonesPorcentajeNUEVO3);
        textViewsPorcentaje[36] = findViewById(R.id.textViewMiguel_GrauPorcentajeNUEVO3);
        textViewsPorcentaje[37] = findViewById(R.id.textViewSanta_MargaritaPorcentajeNUEVO3);
        textViewsPorcentaje[38] = findViewById(R.id.textViewAnexoIIIPorcentajeNUEVO3);
        textViewsPorcentaje[39] = findViewById(R.id.textViewLimaPorcentajeNUEVO3);
        textViewsPorcentaje[40] = findViewById(R.id.textViewAlfonsoUgartePorcentajeNUEVO4);
        textViewsPorcentaje[41] = findViewById(R.id.textViewMarcavallePorcentajeNUEVO4);
        textViewsPorcentaje[42] = findViewById(R.id.textViewPucallpaPorcentajeNUEVO4);
        textViewsPorcentaje[43] = findViewById(R.id.textViewEl_TamboPorcentajeNUEVO4);
        textViewsPorcentaje[44] = findViewById(R.id.textViewTrujilloPorcentajeNUEVO4);
        textViewsPorcentaje[45] = findViewById(R.id.textViewJose_QuinonesPorcentajeNUEVO4);
        textViewsPorcentaje[46] = findViewById(R.id.textViewMiguel_GrauPorcentajeNUEVO4);
        textViewsPorcentaje[47] = findViewById(R.id.textViewSanta_MargaritaPorcentajeNUEVO4);
        textViewsPorcentaje[48] = findViewById(R.id.textViewAnexoIIIPorcentajeNUEVO4);
        textViewsPorcentaje[49] = findViewById(R.id.textViewAnexoIIIPorcentajeNUEVO_UNO);
        textViewsPorcentaje[50] = findViewById(R.id.textViewAnexoIIIPorcentajeNUEVO_DOS);
        textViewsPorcentaje[51] = findViewById(R.id.textViewAnexoIIIPorcentajeNUEVO_TRES);


        textViewsNombre[0] = findViewById(R.id.textViewAlfonso_Ugarte);
        textViewsNombre[1] = findViewById(R.id.textViewMarcavalle);
        textViewsNombre[2] = findViewById(R.id.textViewPucallpa);
        textViewsNombre[3] = findViewById(R.id.textViewEl_Tambo);
        textViewsNombre[4] = findViewById(R.id.textViewTrujillo);
        textViewsNombre[5] = findViewById(R.id.textViewJose_Quinones);
        textViewsNombre[6] = findViewById(R.id.textViewMiguel_Grau);
        textViewsNombre[7] = findViewById(R.id.textViewSanta_Margarita);
        textViewsNombre[8] = findViewById(R.id.textViewAnexoIII);
        textViewsNombre[9] = findViewById(R.id.textViewLima);
        textViewsNombre[10] = findViewById(R.id.textViewAlfonso_UgarteNUEVO);
        textViewsNombre[11] = findViewById(R.id.textViewMarcavalleNUEVO);
        textViewsNombre[12] = findViewById(R.id.textViewPucallpaNUEVO);
        textViewsNombre[13] = findViewById(R.id.textViewEl_TamboNUEVO);
        textViewsNombre[14] = findViewById(R.id.textViewTrujilloNUEVO);
        textViewsNombre[15] = findViewById(R.id.textViewJose_QuinonesNUEVO);
        textViewsNombre[16] = findViewById(R.id.textViewMiguel_GrauNUEVO);
        textViewsNombre[17] = findViewById(R.id.textViewSanta_MargaritaNUEVO);
        textViewsNombre[18] = findViewById(R.id.textViewAnexoIIINUEVO);
        textViewsNombre[19] = findViewById(R.id.textViewLimaNUEVO);
        textViewsNombre[20] = findViewById(R.id.textViewAlfonso_UgarteNUEVO2);
        textViewsNombre[21] = findViewById(R.id.textViewMarcavalleNUEVO2);
        textViewsNombre[22] = findViewById(R.id.textViewPucallpaNUEVO2);
        textViewsNombre[23] = findViewById(R.id.textViewEl_TamboNUEVO2);
        textViewsNombre[24] = findViewById(R.id.textViewTrujilloNUEVO2);
        textViewsNombre[25] = findViewById(R.id.textViewJose_QuinonesNUEVO2);
        textViewsNombre[26] = findViewById(R.id.textViewMiguel_GrauNUEVO2);
        textViewsNombre[27] = findViewById(R.id.textViewSanta_MargaritaNUEVO2);
        textViewsNombre[28] = findViewById(R.id.textViewAnexoIIINUEVO2);
        textViewsNombre[29] = findViewById(R.id.textViewLimaNUEVO2);
        textViewsNombre[30] = findViewById(R.id.textViewAlfonso_UgarteNUEVO3);
        textViewsNombre[31] = findViewById(R.id.textViewMarcavalleNUEVO3);
        textViewsNombre[32] = findViewById(R.id.textViewPucallpaNUEVO3);
        textViewsNombre[33] = findViewById(R.id.textViewEl_TamboNUEVO3);
        textViewsNombre[34] = findViewById(R.id.textViewTrujilloNUEVO3);
        textViewsNombre[35] = findViewById(R.id.textViewJose_QuinonesNUEVO3);
        textViewsNombre[36] = findViewById(R.id.textViewMiguel_GrauNUEVO3);
        textViewsNombre[37] = findViewById(R.id.textViewSanta_MargaritaNUEVO3);
        textViewsNombre[38] = findViewById(R.id.textViewAnexoIIINUEVO3);
        textViewsNombre[39] = findViewById(R.id.textViewLimaNUEVO3);
        textViewsNombre[40] = findViewById(R.id.textViewAlfonso_UgarteNUEVO4);
        textViewsNombre[41] = findViewById(R.id.textViewMarcavalleNUEVO4);
        textViewsNombre[42] = findViewById(R.id.textViewPucallpaNUEVO4);
        textViewsNombre[43] = findViewById(R.id.textViewEl_TamboNUEVO4);
        textViewsNombre[44] = findViewById(R.id.textViewTrujilloNUEVO4);
        textViewsNombre[45] = findViewById(R.id.textViewJose_QuinonesNUEVO4);
        textViewsNombre[46] = findViewById(R.id.textViewMiguel_GrauNUEVO4);
        textViewsNombre[47] = findViewById(R.id.textViewSanta_MargaritaNUEVO4);
        textViewsNombre[48] = findViewById(R.id.textViewAnexoIIINUEVO4);
        textViewsNombre[49] = findViewById(R.id.textViewAnexoNUEVO_UNO);
        textViewsNombre[50] = findViewById(R.id.textViewAnexo_NUEVO_DOS);
        textViewsNombre[51] = findViewById(R.id.textViewAnexo_NUEVO_TRES);


        textViewTotalCantidad1 = findViewById(R.id.textViewTotalCantidad1);
    }
    private void fetchTrabajadoresData() {
        RecursoService service = Apis.getRecursoService();
        Call<List<Map<String, Object>>> call = service.getTrabajadores();

        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    trabajadoresData = response.body();
                    setupChart();
                } else {
                    Toast.makeText(RecursosHumanosGrafico.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                Toast.makeText(RecursosHumanosGrafico.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        float totalGeneral = 0;

        for (int i = 0; i < trabajadoresData.size(); i++) {
            Map<String, Object> trabajador = trabajadoresData.get(i);
            String funcion = (String) trabajador.get("funcion");
            double total = ((Number) trabajador.get("total")).doubleValue();

            entries.add(new BarEntry(i, (float) total));
            totalGeneral += total;

            if (i < textViewsPorcentaje.length && textViewsPorcentaje[i] != null) {
                textViewsPorcentaje[i].setText(String.format("Total: %.0f", total));
            }
            if (i < textViewsNombre.length && textViewsNombre[i] != null) {
                textViewsNombre[i].setText(funcion);
            }
        }

        textViewTotalCantidad1.setText(String.format("%.0f", totalGeneral));

        BarDataSet dataSet = new BarDataSet(entries, "Personal por Cargo");
        dataSet.setColor(ContextCompat.getColor(this, R.color.Pronacej1));
        dataSet.setValueTextSize(8f);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.black));

        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format("%.0f", value);
            }
        });

        BarData barData = new BarData(dataSet);

        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setTextSize(8f);
        barChart.getDescription().setEnabled(false);
        barChart.setExtraLeftOffset(30f);
        barChart.setExtraRightOffset(50f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value >= 0 && value < trabajadoresData.size()) {
                    return (String) trabajadoresData.get((int) value).get("funcion");
                }
                return "";
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(trabajadoresData.size());
        xAxis.setLabelRotationAngle(0);

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setTextSize(4f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        barChart.setData(barData);
        barChart.invalidate();
    }
}