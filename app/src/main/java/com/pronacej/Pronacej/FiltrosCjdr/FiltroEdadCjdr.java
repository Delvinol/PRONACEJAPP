package com.pronacej.Pronacej.FiltrosCjdr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.ResultadosCjrd.ResultadoEdadCjdr;
import com.pronacej.Pronacej.Time.MonthYearPickerDialog;
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.CjdrService;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroEdadCjdr extends AppCompatActivity {
    private boolean isRequestInProgress = false;

    private TextView tvErrorFecha;
    private Button btnGenerarGrafico, btnMonthYearPicker;
    private CjdrService cjdrService;
    private CheckBox cbIncluirEstadoIng;
    private CheckBox cbIncluirEstadoAten;
    private int selectedYear, selectedMonth;
    private Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_poblacion_cjdr);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(FiltroEdadCjdr.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        ButtonBack.setOnClickListener(v -> onBackPressed());

        btnMonthYearPicker = findViewById(R.id.btnMonthYearPicker);
        calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        updateDateButtonText();

        cbIncluirEstadoIng = findViewById(R.id.cbIncluirEstadoIng);
        cbIncluirEstadoAten = findViewById(R.id.cbIncluirEstadoAten);

        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        cjdrService = Apis.getCjdrService();

        btnGenerarGrafico.setOnClickListener(view -> {
            Log.d("FiltroEdadCjdr", "Botón Enviar clickeado");
            llamarEndPoint();
        });

        setupCheckBoxListeners();
    }

    private void setupCheckBoxListeners() {
        cbIncluirEstadoIng.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbIncluirEstadoAten.setChecked(false);
            }
        });

        cbIncluirEstadoAten.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbIncluirEstadoIng.setChecked(false);
            }
        });
    }

    private void llamarEndPoint() {
        if (isRequestInProgress) {
            // Si ya hay una solicitud en progreso, no hacer nada
            return;
        }
        isRequestInProgress = true;
        calendar.set(selectedYear, selectedMonth, 1);
        String fechaInicio = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.getTime());

        calendar.set(selectedYear, selectedMonth, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String fechaFin = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.getTime());

        boolean incluirEstadoIng = cbIncluirEstadoIng.isChecked();

        Log.d("FiltroEdadCjdr", "Llamando al endpoint con fecha: " + fechaInicio + " - " + fechaFin);

        Call<List<Map<String, Object>>> call = cjdrService.obtenerEdadSimpleCjdr(fechaInicio, fechaFin, incluirEstadoIng);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {

                isRequestInProgress = false;

                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    List<Map<String, Object>> datos = response.body();
                    if (contieneDataValida(datos)) {
                        // Convertir a JSON y pasar a ResultadoEdadCjdr
                        JSONArray jsonArray = new JSONArray(datos);
                        String datosJson = jsonArray.toString();
                        Intent intent = new Intent(FiltroEdadCjdr.this, ResultadoEdadCjdr.class);
                        intent.putExtra("datosEdad", datosJson);
                        startActivity(intent);
                    } else {
                        Toast.makeText(FiltroEdadCjdr.this, "No existe data en esa fecha", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(FiltroEdadCjdr.this, "Error al obtener datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                isRequestInProgress = false;

                Toast.makeText(FiltroEdadCjdr.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean contieneDataValida(List<Map<String, Object>> datos) {
        if (datos == null || datos.isEmpty()) return false;
        Map<String, Object> primerElemento = datos.get(0);
        for (Object valor : primerElemento.values()) {
            if (valor instanceof Number && ((Number) valor).doubleValue() > 0) {
                return true;
            }
        }
        return false;
    }

    public void openMonthYearPicker(View view) {
        MonthYearPickerDialog pd = new MonthYearPickerDialog();
        pd.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedYear = year;
                selectedMonth = monthOfYear;
                updateDateButtonText();
            }
        });
        pd.show(getSupportFragmentManager(), "MonthYearPickerDialog");
    }

    private void updateDateButtonText() {
        calendar.set(Calendar.YEAR, selectedYear);
        calendar.set(Calendar.MONTH, selectedMonth);
        String monthYearStr = new SimpleDateFormat("MMM yyyy", new Locale("es", "ES"))
                .format(calendar.getTime());
        btnMonthYearPicker.setText(monthYearStr.toUpperCase());
    }

}
