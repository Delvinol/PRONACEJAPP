package com.pronacej.Pronacej.FiltrosSoa;

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
import com.pronacej.Pronacej.ResultadosSoa.ResultadosDelitoSoa;
import com.pronacej.Pronacej.Time.MonthYearPickerDialog;
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.SoaService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroInfraccionSoa extends AppCompatActivity {
    private boolean isRequestInProgress = false;


    private int autoaborto;
    private int exposicion_peligro;
    private int feminicidio;
    private int homicidio_c;
    private int homicidio_s;
    private int lesiones_g;
    private int lesiones_l;
    private int parricidio;
    private int sicariato;
    private int otros;
    private int juridica_sentenciado;
    private int juridica_procesado;
    private int ingreso_sentenciado;
    private int ingreso_procesado;

    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private Button btnMonthYearPicker;
    private SoaService soaService;
    private CheckBox cbIncluirEstadoIng;
    private CheckBox cbIncluirEstadoAten;
    private int selectedYear, selectedMonth;
    private Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_infraccion_soa);

        btnMonthYearPicker = findViewById(R.id.btnMonthYearPicker);
        calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        updateDateButtonText();

        cbIncluirEstadoIng = findViewById(R.id.cbIncluirEstadoIng);
        cbIncluirEstadoAten = findViewById(R.id.cbIncluirEstadoAten);

        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        soaService = Apis.getSoaService();

        btnGenerarGrafico.setOnClickListener(view -> {
            llamarEndPoint();
        });

        setupCheckBoxListeners();

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(FiltroInfraccionSoa.this, CategoriaMenu.class);
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

        Call<List<Map<String, Object>>> call = soaService.obtenerIC(fechaInicio, fechaFin, incluirEstadoIng);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                isRequestInProgress = false;

                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    List<Map<String, Object>> data = response.body();
                    if (contieneDataValida(data)) {
                        Map<String, Object> firstElement = data.get(0);
                        Intent intent = new Intent(FiltroInfraccionSoa.this, ResultadosDelitoSoa.class);
                        juridica_sentenciado = getIntValue(firstElement, "juridica_sentenciado");
                        juridica_procesado = getIntValue(firstElement, "juridica_procesado");
                        ingreso_sentenciado = getIntValue(firstElement, "ingreso_sentenciado");
                        ingreso_procesado = getIntValue(firstElement, "ingreso_procesado");

                        intent.putExtra("agresiones_mujeres", getIntValue(firstElement, "agresiones_mujeres"));
                        intent.putExtra("autoaborto", getIntValue(firstElement, "autoaborto"));
                        intent.putExtra("exposicion_peligro", getIntValue(firstElement, "exposicion_peligro"));
                        intent.putExtra("feminicidio", getIntValue(firstElement, "feminicidio"));
                        intent.putExtra("homicidio_c", getIntValue(firstElement, "homicidio_c"));
                        intent.putExtra("homicidio_ct", getIntValue(firstElement, "homicidio_ct"));
                        intent.putExtra("homicidio_culposo", getIntValue(firstElement, "homicidio_culposo"));
                        intent.putExtra("homicidio_s", getIntValue(firstElement, "homicidio_s"));
                        intent.putExtra("homicidio_st", getIntValue(firstElement, "homicidio_st"));
                        intent.putExtra("infanticidio", getIntValue(firstElement, "infanticidio"));
                        intent.putExtra("lesiones_culposas", getIntValue(firstElement, "lesiones_culposas"));
                        intent.putExtra("lesiones_culposas_agravadas", getIntValue(firstElement, "lesiones_culposas_agravadas"));
                        intent.putExtra("lesiones_dolosas", getIntValue(firstElement, "lesiones_dolosas"));
                        intent.putExtra("lesiones_graves", getIntValue(firstElement, "lesiones_graves"));
                        intent.putExtra("lesiones_leves", getIntValue(firstElement, "lesiones_leves"));
                        intent.putExtra("lesiones_leves_agravadas", getIntValue(firstElement, "lesiones_leves_agravadas"));
                        intent.putExtra("lesiones_leves_tentativa", getIntValue(firstElement, "lesiones_leves_tentativa"));
                        intent.putExtra("maltrato", getIntValue(firstElement, "maltrato"));
                        intent.putExtra("parricidio", getIntValue(firstElement, "parricidio"));
                        intent.putExtra("sicariato", getIntValue(firstElement, "sicariato"));
                        intent.putExtra("otros", getIntValue(firstElement, "otros"));
                        intent.putExtra("trafico_drogas", getIntValue(firstElement, "trafico_drogas"));
                        intent.putExtra("apropiacion_ilicita", getIntValue(firstElement, "apropiacion_ilicita"));
                        intent.putExtra("chantaje", getIntValue(firstElement, "chantaje"));
                        intent.putExtra("danos_agravados", getIntValue(firstElement, "danos_agravados"));
                        intent.putExtra("extorsion", getIntValue(firstElement, "extorsion"));
                        intent.putExtra("extorsion_agravada", getIntValue(firstElement, "extorsion_agravada"));
                        intent.putExtra("hurto_agravado", getIntValue(firstElement, "hurto_agravado"));
                        intent.putExtra("hurto_agravado_tentativa", getIntValue(firstElement, "hurto_agravado_tentativa"));
                        intent.putExtra("hurto_ganado", getIntValue(firstElement, "hurto_ganado"));
                        intent.putExtra("hurto_simple", getIntValue(firstElement, "hurto_simple"));
                        intent.putExtra("hurto_simple_tentativa", getIntValue(firstElement, "hurto_simple_tentativa"));
                        intent.putExtra("hurto_simple_dano", getIntValue(firstElement, "hurto_simple_dano"));
                        intent.putExtra("receptacion_agravada", getIntValue(firstElement, "receptacion_agravada"));
                        intent.putExtra("robo", getIntValue(firstElement, "robo"));
                        intent.putExtra("robo_agravado", getIntValue(firstElement, "robo_agravado"));
                        intent.putExtra("robo_ganado", getIntValue(firstElement, "robo_ganado"));
                        intent.putExtra("robo_tentativa", getIntValue(firstElement, "robo_tentativa"));
                        intent.putExtra("usurpacion_agravada", getIntValue(firstElement, "usurpacion_agravada"));
                        intent.putExtra("acoso", getIntValue(firstElement, "acoso"));
                        intent.putExtra("acoso_sexual", getIntValue(firstElement, "acoso_sexual"));
                        intent.putExtra("chantaje_sexual", getIntValue(firstElement, "chantaje_sexual"));
                        intent.putExtra("coaccion", getIntValue(firstElement, "coaccion"));
                        intent.putExtra("exhibiciones_obscenas", getIntValue(firstElement, "exhibiciones_obscenas"));
                        intent.putExtra("favorecimiento_prostitucion", getIntValue(firstElement, "favorecimiento_prostitucion"));
                        intent.putExtra("proposiciones_sexuales", getIntValue(firstElement, "proposiciones_sexuales"));
                        intent.putExtra("secuestro", getIntValue(firstElement, "secuestro"));
                        intent.putExtra("tocamientos_menores", getIntValue(firstElement, "tocamientos_menores"));
                        intent.putExtra("tocamientos_sin_consentimiento", getIntValue(firstElement, "tocamientos_sin_consentimiento"));
                        intent.putExtra("violacion_menor", getIntValue(firstElement, "violacion_menor"));
                        intent.putExtra("violacion_inconsciencia", getIntValue(firstElement, "violacion_inconsciencia"));
                        intent.putExtra("violacion_sexual", getIntValue(firstElement, "violacion_sexual"));
                        intent.putExtra("violacion_menor_tentativa", getIntValue(firstElement, "violacion_menor_tentativa"));
                        intent.putExtra("violacion_tentativa", getIntValue(firstElement, "violacion_tentativa"));
                        intent.putExtra("violacion_domicilio", getIntValue(firstElement, "violacion_domicilio"));
                        intent.putExtra("conduccion_ebriedad", getIntValue(firstElement, "conduccion_ebriedad"));
                        intent.putExtra("fabricacion_armas", getIntValue(firstElement, "fabricacion_armas"));
                        intent.putExtra("fabricacion_materiales_peligrosos", getIntValue(firstElement, "fabricacion_materiales_peligrosos"));
                        intent.putExtra("produccion_peligro_comun", getIntValue(firstElement, "produccion_peligro_comun"));
                        intent.putExtra("produccion_trafico_armas", getIntValue(firstElement, "produccion_trafico_armas"));
                        intent.putExtra("trafico_microcomercializacion", getIntValue(firstElement, "trafico_microcomercializacion"));
                        intent.putExtra("trafico_insumos_quimicos", getIntValue(firstElement, "trafico_insumos_quimicos"));
                        intent.putExtra("violacion_medidas_sanitarias", getIntValue(firstElement, "violacion_medidas_sanitarias"));
                        intent.putExtra("encubrimiento_personal", getIntValue(firstElement, "encubrimiento_personal"));
                        intent.putExtra("fuga_accidente", getIntValue(firstElement, "fuga_accidente"));
                        intent.putExtra("resistencia_autoridad", getIntValue(firstElement, "resistencia_autoridad"));
                        intent.putExtra("juridica_sentenciado", juridica_sentenciado);
                        intent.putExtra("juridica_procesado", juridica_procesado);
                        intent.putExtra("ingreso_sentenciado", ingreso_sentenciado);
                        intent.putExtra("ingreso_procesado", ingreso_procesado);
                        // Iniciar la actividad PoblacionCjdrActivity
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
                isRequestInProgress = false;

                mostrarMensajeError("Error de conexión");
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

    private void mostrarMensajeNoData() {
        runOnUiThread(() -> Toast.makeText(FiltroInfraccionSoa.this, "No existe data en esa fecha", Toast.LENGTH_SHORT).show());
    }

    private void mostrarMensajeError(String mensaje) {
        runOnUiThread(() -> Toast.makeText(FiltroInfraccionSoa.this, mensaje, Toast.LENGTH_SHORT).show());
    }

    private int getIntValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value instanceof Double) {
            return ((Double) value).intValue();
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else {
            return 0; // O cualquier valor por defecto que consideres adecuado
        }
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