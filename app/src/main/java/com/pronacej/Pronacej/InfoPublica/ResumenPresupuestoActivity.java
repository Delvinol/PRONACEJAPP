package com.pronacej.Pronacej.InfoPublica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ResumenPresupuestoActivity extends AppCompatActivity {
    private List<Map<String, Object>> inversionData;
    private TextView tvMontoPimTotal, tvMontoDevengadoTotal, tvPorcentajeTotal;
    private Button btnVerDetalles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_presupuesto);

        tvMontoPimTotal = findViewById(R.id.tvMontoPimTotal);
        tvMontoDevengadoTotal = findViewById(R.id.tvMontoDevengadoTotal);
        tvPorcentajeTotal = findViewById(R.id.tvPorcentajeTotal);
        btnVerDetalles = findViewById(R.id.btnVerDetalles);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResumenPresupuestoActivity.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        inversionData = (List<Map<String, Object>>) getIntent().getSerializableExtra("inversionData");
        if (inversionData != null) {
            calcularYMostrarTotales();
        }

        btnVerDetalles.setOnClickListener(v -> {
            Intent intent = new Intent(ResumenPresupuestoActivity.this, PresupuestoPublicoActivity.class);
            intent.putExtra("inversionData", (Serializable) inversionData);
            startActivity(intent);
        });
    }

    private void calcularYMostrarTotales() {
        double montoPimTotal = 0;
        double montoDevengadoTotal = 0;

        for (Map<String, Object> row : inversionData) {
            montoPimTotal += (double) row.get("monto_pim");
            montoDevengadoTotal += (double) row.get("monto_devengado");
        }

        double porcentajeTotal = (montoDevengadoTotal / montoPimTotal) * 100;

        tvMontoPimTotal.setText(String.format("Monto PIM Total: S/. %.2f", montoPimTotal));
        tvMontoDevengadoTotal.setText(String.format("Monto Devengado Total: S/. %.2f", montoDevengadoTotal));
        tvPorcentajeTotal.setText(String.format("Porcentaje Total: %.2f%%", porcentajeTotal));
    }
}