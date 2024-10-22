package com.pronacej.Pronacej.InfoPublica;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PresupuestoPublicoActivity extends AppCompatActivity {


    private TableLayout tableLayout;
    private List<Map<String, Object>> inversionData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presupuesto_url);


        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);



        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(PresupuestoPublicoActivity.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });
        tableLayout = findViewById(R.id.tableLayout);


        inversionData = (List<Map<String, Object>>) getIntent().getSerializableExtra("inversionData");
        if (inversionData != null) {
            crearTabla(inversionData);
        }
    }


    private void crearTabla(List<Map<String, Object>> data) {
        tableLayout.removeAllViews();

        // Agrupar datos por "numero_secuencia"
        Map<Integer, Map<String, Object>> groupedData = new HashMap<>();
        for (Map<String, Object> row : data) {
            int secuencia = ((Double) row.get("numero_secuencia")).intValue();
            if (groupedData.containsKey(secuencia)) {
                Map<String, Object> existingRow = groupedData.get(secuencia);
                double montoPim = (double) existingRow.get("monto_pim") + (double) row.get("monto_pim");
                double montoDevengado = (double) existingRow.get("monto_devengado") + (double) row.get("monto_devengado");
                double porcentaje = (montoDevengado / montoPim) * 100;

                existingRow.put("monto_pim", montoPim);
                existingRow.put("monto_devengado", montoDevengado);
                existingRow.put("porcentaje", porcentaje);
            } else {
                groupedData.put(secuencia, new HashMap<>(row));
            }
        }

        // Añadir encabezados de tabla
        TableRow headerRow = new TableRow(this);
        String[] headers = {"Secuencia", "Nombre", "Departamento", "Provincia", "Distrito", "Monto PIM", "Monto Devengado", "Porcentaje"};
        for (String header : headers) {
            TextView textView = new TextView(this);
            textView.setText(header);
            textView.setPadding(8, 8, 8, 8);
            textView.setBackgroundResource(R.drawable.cell_border);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.parseColor("#3F51B5"));
            textView.setTypeface(null, android.graphics.Typeface.BOLD);
            headerRow.addView(textView);
        }
        tableLayout.addView(headerRow);

        // Añadir filas de datos agrupados
        boolean alternate = false;
        for (Map<String, Object> row : groupedData.values()) {
            TableRow tableRow = new TableRow(this);
            tableRow.setBackgroundColor(alternate ? Color.parseColor("#F5F5F5") : Color.parseColor("#FFFFFF"));
            tableRow.setPadding(8, 8, 8, 8);

            // Formato de secuencia
            int secuencia = ((Double) row.get("numero_secuencia")).intValue();
            String secuenciaFormateada = String.format("%04d", secuencia);
            addCellToRow(tableRow, secuenciaFormateada);

            addCellToRow(tableRow, (String) row.get("nombre_secuencia"));
            addCellToRow(tableRow, (String) row.get("departamento"));
            addCellToRow(tableRow, (String) row.get("provincia"));
            addCellToRow(tableRow, (String) row.get("distrito"));

            // Formato de monto PIM
            double montoPim = (double) row.get("monto_pim");
            String montoPimFormateado = String.format("S/. %.2f", montoPim);
            addCellToRow(tableRow, montoPimFormateado);

            // Formato de monto Devengado
            double montoDevengado = (double) row.get("monto_devengado");
            String montoDevengadoFormateado = String.format("S/. %.2f", montoDevengado);
            addCellToRow(tableRow, montoDevengadoFormateado);

            // Formato de porcentaje
            double porcentaje = (double) row.get("porcentaje");
            String porcentajeFormateado = String.format("%.2f%%", porcentaje);
            addCellToRow(tableRow, porcentajeFormateado);

            tableLayout.addView(tableRow);
            alternate = !alternate;
        }
    }


    private void addCellToRow(TableRow tableRow, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        textView.setBackgroundResource(R.drawable.cell_border);
        tableRow.addView(textView);
    }
}
