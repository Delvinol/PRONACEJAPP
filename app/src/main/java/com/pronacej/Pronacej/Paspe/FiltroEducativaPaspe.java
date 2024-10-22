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

public class FiltroEducativaPaspe extends AppCompatActivity {
    private boolean isRequestInProgress = false;


    private int sea_estudia;
    private int sea_termino_basico;
    private int sea_termino_no_doc;
    private int reinsercion_educativa;
    private int insercion_productiva;
    private int continuidad_edu;
    private int apoyo_regularizar;
    private int cebr;
    private int ceba;
    private int cepre;
    private int academia;
    private int cetpro;
    private int instituto;
    private int universidad;
    private int ninguno;

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
        setContentView(R.layout.filtro_educativa_cjdr);

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
            Intent intentHome = new Intent(FiltroEducativaPaspe.this, CategoriaMenu.class);
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

        Call<List<Map<String, Object>>> call = paspeService.obtenerIE(fechaInicio, fechaFin, incluirEstadoIng);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                isRequestInProgress = false;

                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    List<Map<String, Object>> data = response.body();
                    if (contieneDataValida(data)) {
                        Map<String, Object> firstElement = data.get(0);
                        sea_estudia = getIntValue(firstElement, "sea_estudia");
                        sea_termino_basico = getIntValue(firstElement, "sea_termino_basico");
                        sea_termino_no_doc = getIntValue(firstElement, "sea_termino_no_doc");
                        reinsercion_educativa = getIntValue(firstElement, "reinsercion_educativa");
                        insercion_productiva = getIntValue(firstElement, "insercion_productiva");
                        continuidad_edu = getIntValue(firstElement, "continuidad_edu");
                        apoyo_regularizar = getIntValue(firstElement, "apoyo_regularizar");
                        cebr = getIntValue(firstElement, "cebr");
                        ceba = getIntValue(firstElement, "ceba");
                        cepre = getIntValue(firstElement, "cepre");
                        academia = getIntValue(firstElement, "academia");
                        cetpro = getIntValue(firstElement, "cetpro");
                        instituto = getIntValue(firstElement, "instituto");
                        universidad = getIntValue(firstElement, "universidad");
                        ninguno = getIntValue(firstElement, "no_aplica");

                        Intent intent = new Intent(FiltroEducativaPaspe.this, ResultadoCentroEducativoPaspe.class);
                        intent.putExtra("sea_estudia", sea_estudia);
                        intent.putExtra("sea_termino_basico", sea_termino_basico);
                        intent.putExtra("sea_termino_no_doc", sea_termino_no_doc);
                        intent.putExtra("reinsercion_educativa", reinsercion_educativa);
                        intent.putExtra("insercion_productiva", insercion_productiva);
                        intent.putExtra("continuidad_edu", continuidad_edu);
                        intent.putExtra("apoyo_regularizar", apoyo_regularizar);
                        intent.putExtra("cebr", cebr);
                        intent.putExtra("ceba", ceba);
                        intent.putExtra("cepre", cepre);
                        intent.putExtra("academia", academia);
                        intent.putExtra("cetpro", cetpro);
                        intent.putExtra("instituto", instituto);
                        intent.putExtra("universidad", universidad);
                        intent.putExtra("no_aplica", ninguno);

                        startActivity(intent);
                    } else {
                        Toast.makeText(FiltroEducativaPaspe.this, "No existe data en esa fecha", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(FiltroEducativaPaspe.this, "Error al obtener datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                isRequestInProgress = false;

                Toast.makeText(FiltroEducativaPaspe.this, "Error de conexión", Toast.LENGTH_LONG).show();
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
