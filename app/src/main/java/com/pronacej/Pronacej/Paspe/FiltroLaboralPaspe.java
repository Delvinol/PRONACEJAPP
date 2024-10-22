package com.pronacej.Pronacej.Paspe;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.pronacej.Pronacej.Time.MonthYearPickerDialog;
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.PaspeService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroLaboralPaspe extends AppCompatActivity {
    private boolean isRequestInProgress = false;


    private int seguro_sis;
    private int seguro_essalud;
    private int seguro_particular;
    private int seguro_ninguno;
    private int trabaja_si;
    private int trabaja_no;
    private int trabaja_formal;
    private int trabaja_informal;
    private int trabaja_sin;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private Button btnMonthYearPicker;
    private PaspeService paspeService;
    private CheckBox cbIncluirEstadoIng;
    private CheckBox cbIncluirEstadoAten;
    private int selectedYear, selectedMonth;
    private Calendar calendar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_laboral_paspe);

        btnMonthYearPicker = findViewById(R.id.btnMonthYearPicker);
        calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        updateDateButtonText();

        cbIncluirEstadoIng = findViewById(R.id.cbIncluirEstadoIng);
        cbIncluirEstadoAten = findViewById(R.id.cbIncluirEstadoAten);

        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        paspeService = Apis.getPaspeService();

        btnGenerarGrafico.setOnClickListener(view -> {
            llamarEndPoint();
        });

        setupCheckBoxListeners();

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(FiltroLaboralPaspe.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        ButtonBack.setOnClickListener(v -> onBackPressed());
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

        Call<List<Map<String, Object>>> call = paspeService.obtenerIL(fechaInicio, fechaFin, incluirEstadoIng);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                isRequestInProgress = false;

                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    List<Map<String, Object>> data = response.body();
                    if (contieneDataValida(data)) {
                        Map<String, Object> firstElement = data.get(0);
                        seguro_sis = getIntValue(firstElement, "seguro_sis");
                        seguro_essalud = getIntValue(firstElement, "seguro_essalud");
                        seguro_particular = getIntValue(firstElement, "seguro_particular");
                        seguro_ninguno = getIntValue(firstElement, "seguro_ninguno");
                        trabaja_si = getIntValue(firstElement, "trabaja_si");
                        trabaja_no = getIntValue(firstElement, "trabaja_no");
                        trabaja_formal = getIntValue(firstElement, "trabaja_formal");
                        trabaja_informal = getIntValue(firstElement, "trabaja_informal");
                        trabaja_sin = getIntValue(firstElement, "trabaja_sin");

                        // Crear el Intent y añadir los extras
                        Intent intent = new Intent(FiltroLaboralPaspe.this, ResultadoLaboralIngresoPaspe.class);
                        intent.putExtra("seguro_sis", seguro_sis);
                        intent.putExtra("seguro_essalud", seguro_essalud);
                        intent.putExtra("seguro_particular", seguro_particular);
                        intent.putExtra("seguro_ninguno", seguro_ninguno);
                        intent.putExtra("trabaja_no", trabaja_no);
                        intent.putExtra("trabaja_si", trabaja_si);
                        intent.putExtra("trabaja_formal", trabaja_formal);
                        intent.putExtra("trabaja_informal", trabaja_informal);
                        intent.putExtra("trabaja_sin", trabaja_sin);

                        startActivity(intent);
                    } else {
                        Toast.makeText(FiltroLaboralPaspe.this, "No existe data en esa fecha", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(FiltroLaboralPaspe.this, "Error al obtener datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                isRequestInProgress = false;

                Toast.makeText(FiltroLaboralPaspe.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean contieneDataValida(List<Map<String, Object>> data) {
        if (data == null || data.isEmpty()) return false;
        Map<String, Object> firstElement = data.get(0);
        for (Object value : firstElement.values()) {
            if (value instanceof Number && ((Number) value).intValue() > 0) {
                return true;
            }
        }
        return false;
    }

    private int getIntValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return 0;
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
