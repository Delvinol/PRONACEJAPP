package com.pronacej.Pronacej.InfoPublica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.InversionService;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FiltroAnual extends AppCompatActivity {

    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private Button btnYearPicker;
    private InversionService inversionService;
    private int selectedYear;
    private Calendar calendar;
    private List<Map<String, Object>> inversionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_presupuesto);

        btnYearPicker = findViewById(R.id.btnYearPicker);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);

        calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        updateDateButtonText();

        tvErrorFecha = findViewById(R.id.tvErrorFecha);

        inversionService = Apis.getInversionService();

        btnGenerarGrafico.setOnClickListener(view -> {
            llamarEndPoint();
        });

        btnYearPicker.setOnClickListener(view -> {
            openYearPicker();
        });

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(FiltroAnual.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        ButtonBack.setOnClickListener(v -> onBackPressed());
    }

    private void llamarEndPoint() {
        String fechaInicio = selectedYear + "-01-01";
        String fechaFin = selectedYear + "-12-31";

        Call<List<Map<String, Object>>> call = inversionService.filtrarInversiones(fechaInicio, fechaFin);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    inversionData = response.body();
                    if (!inversionData.isEmpty()) {
                        Intent intent = new Intent(FiltroAnual.this, ResumenPresupuestoActivity.class);
                        intent.putExtra("inversionData", (Serializable) inversionData);
                        startActivity(intent);
                    } else {
                        mostrarMensajeNoData();
                    }
                } else {
                    mostrarMensajeError("Error al obtener datos");
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                mostrarMensajeError("Error de conexión");
            }
        });
    }

    private void mostrarMensajeNoData() {
        runOnUiThread(() -> Toast.makeText(FiltroAnual.this, "No existe data para ese año", Toast.LENGTH_SHORT).show());
    }

    private void mostrarMensajeError(String mensaje) {
        runOnUiThread(() -> Toast.makeText(FiltroAnual.this, mensaje, Toast.LENGTH_SHORT).show());
    }

    public void openYearPicker() {
        final NumberPicker yearPicker = new NumberPicker(this);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        yearPicker.setMinValue(2000);
        yearPicker.setMaxValue(currentYear);
        yearPicker.setValue(selectedYear);
        yearPicker.setWrapSelectorWheel(false);  // Esto evita que la selección se envuelva

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar Año");
        builder.setView(yearPicker);
        builder.setPositiveButton("OK", (dialog, which) -> {
            selectedYear = yearPicker.getValue();
            updateDateButtonText();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void updateDateButtonText() {
        btnYearPicker.setText(String.valueOf(selectedYear));
    }
}
