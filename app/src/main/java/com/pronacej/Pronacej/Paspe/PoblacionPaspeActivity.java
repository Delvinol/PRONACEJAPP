package com.pronacej.Pronacej.Paspe;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class PoblacionPaspeActivity extends AppCompatActivity {
    private int totalRegistros;
    private int ingresoSentenciado;
    private int ingresoProcesado;
    private int estado_cierre_post;
    private int estado_egr;
    private int estado_ing;
    private int estado_ing_post;
    private int estado_civil_casado;
    private int estado_civil_conviviente;
    private int estado_civil_separado;
    private int estado_civil_soltero;
    private int estado_civil_viudo;
    private int sexo_masculino;
    private int sexo_femenino;

    private EditText etFechaInicio;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private String selectedDate;
    private PaspeService paspeService ;
    private CheckBox cbIncluirEstadoIng;
    private CheckBox cbIncluirEstadoAten;
    private Button btnMonthYearPicker;
    private int selectedYear, selectedMonth;
    private Calendar calendar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_poblacion_cjdr);

        btnMonthYearPicker = findViewById(R.id.btnMonthYearPicker);
        calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        updateDateButtonText();

        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        paspeService = Apis.getPaspeService();


        cbIncluirEstadoIng = findViewById(R.id.cbIncluirEstadoIng);
        cbIncluirEstadoAten = findViewById(R.id.cbIncluirEstadoAten);

        btnGenerarGrafico.setOnClickListener(view -> {
            llamarEndPoint();
        });

        setupCheckBoxListeners();

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(PoblacionPaspeActivity.this, CategoriaMenu.class);
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
        calendar.set(selectedYear, selectedMonth, 1);
        String fechaInicio = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.getTime());

        calendar.set(selectedYear, selectedMonth, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String fechaFin = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.getTime());

        boolean incluirEstadoIng = cbIncluirEstadoIng.isChecked();

        Call<List<Map<String, Object>>> call = paspeService.obtenerePopulation(fechaInicio, fechaFin, incluirEstadoIng);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    List<Map<String, Object>> data = response.body();
                    if (contieneDataValida(data)) {
                        Map<String, Object> firstElement = data.get(0);
                        totalRegistros = getIntValue(firstElement, "total_registros");
                        ingresoSentenciado = getIntValue(firstElement, "ingreso_sentenciado");
                        ingresoProcesado = getIntValue(firstElement, "ingreso_procesado");
                        estado_cierre_post = getIntValue(firstElement, "estado_cierre_post");
                        estado_egr = getIntValue(firstElement, "estado_egr");
                        estado_ing = getIntValue(firstElement, "estado_ing");
                        estado_ing_post = getIntValue(firstElement, "estado_ing_post");
                        estado_civil_casado = getIntValue(firstElement, "estado_civil_casado");
                        estado_civil_conviviente = getIntValue(firstElement, "estado_civil_conviviente");
                        estado_civil_separado = getIntValue(firstElement, "estado_civil_separado");
                        estado_civil_soltero = getIntValue(firstElement, "estado_civil_soltero");
                        estado_civil_viudo = getIntValue(firstElement, "estado_civil_viudo");
                        sexo_masculino = getIntValue(firstElement, "sexo_masculino");
                        sexo_femenino = getIntValue(firstElement, "sexo_femenino");

                        // Crear el Intent y añadir los extras
                        Intent intent = new Intent(PoblacionPaspeActivity.this, ResultadoPoblacionPorGenero.class);
                        intent.putExtra("totalRegistros", totalRegistros);
                        intent.putExtra("ingresoSentenciado", ingresoSentenciado);
                        intent.putExtra("ingresoProcesado", ingresoProcesado);
                        intent.putExtra("estado_cierre_post", estado_cierre_post);
                        intent.putExtra("estado_egr", estado_egr);
                        intent.putExtra("estado_ing", estado_ing);
                        intent.putExtra("estado_ing_post", estado_ing_post);
                        intent.putExtra("estado_civil_casado", estado_civil_casado);
                        intent.putExtra("estado_civil_conviviente", estado_civil_conviviente);
                        intent.putExtra("estado_civil_separado", estado_civil_separado);
                        intent.putExtra("estado_civil_soltero", estado_civil_soltero);
                        intent.putExtra("estado_civil_viudo", estado_civil_viudo);
                        intent.putExtra("sexo_masculino", sexo_masculino);
                        intent.putExtra("sexo_femenino", sexo_femenino);

                        // Iniciar la actividad ResultadoPoblacionPorGenero
                        startActivity(intent);
                    } else {
                        Toast.makeText(PoblacionPaspeActivity.this, "No existe data en esa fecha", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(PoblacionPaspeActivity.this, "Error al obtener datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                Toast.makeText(PoblacionPaspeActivity.this, "Error de conexión", Toast.LENGTH_LONG).show();
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
    private void addTouchFeedback(View view) {
        view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setAlpha(0.7f);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    v.setAlpha(1.0f);
                    break;
            }
            return false;
        });
    }
}
