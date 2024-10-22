package com.pronacej.Pronacej.FiltroSoaTotal;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.OpcionesSoa.TratamientoDiferenciadoSoaActivity;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.SoaService;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroTratamientoTotalSoa extends AppCompatActivity {

    private int participa_programa_uno;
    private int participa_programa_dos;
    private int participa_programa_tres;
    private int participa_programa_cuatro;
    private int participa_programa_cinco;
    private int participa_programa_no;
    private int justicia_si;
    private int justicia_no;
    private int agresor_si;
    private int agresor_no;
    private int salud_si;
    private int salud_no;
    private int adn_si;
    private int adn_no;
    private int intervencion_aplica;
    private int intervencion_no_aplica;
    private int firmes_aplica;
    private int firmes_no_aplica;

    private Button etFechaInicio;
    private Button etFechaFin;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private SoaService soaService;
    private DatePickerDialog datePickerDialogInicio;
    private DatePickerDialog datePickerDialogFinal;
    private Button dateButtonInicio;
    private CheckBox cbIncluirEstadoIng;
    private CheckBox cbIncluirEstadoAten;

    private Button dateButtonFinal;
    private String selectedDateInicio;
    private String selectedDateFinal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_tratamiento_total_soa);

        dateButtonInicio = findViewById(R.id.etFechaInicio);
        dateButtonFinal = findViewById(R.id.etFechaFin);
        cbIncluirEstadoIng = findViewById(R.id.cbIncluirEstadoIng);
        cbIncluirEstadoAten = findViewById(R.id.cbIncluirEstadoAten);

        initDatePickerInicio();
        initDatePickerFinal();
        selectedDateInicio = getTodaysDate();
        selectedDateFinal = getTodaysDate();


        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        soaService = Apis.getSoaService();

        btnGenerarGrafico.setOnClickListener(view -> {
            String fechaInicio = showSelectedDateInicio(dateButtonInicio).toString().trim();
            String fechaFin = showSelectedDateFinal(dateButtonFinal).toString().trim();
            boolean incluirEstadoIng = cbIncluirEstadoIng.isChecked();

            if (validarFechaFormato(fechaInicio) && (fechaFin.isEmpty() || validarFechaFormato(fechaFin))) {
                tvErrorFecha.setVisibility(View.GONE);
                if (fechaFin.isEmpty()) {
                    fechaFin = fechaInicio;
                    dateButtonFinal.setText(fechaInicio);
                }
                llamarEndPoint(fechaInicio, fechaFin, incluirEstadoIng);
            } else {
                tvErrorFecha.setVisibility(View.VISIBLE);
            }
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
    private boolean validarFechaFormato(String fecha) {
        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return fecha.matches(pattern);
    }

    private void llamarEndPoint(String fechaInicio, @Nullable String fechaFin, boolean incluirEstadoIng) {
        Call<List<Map<String, Object>>> call = soaService.obtenerTD(fechaInicio, fechaFin, incluirEstadoIng);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    List<Map<String, Object>> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        Map<String, Object> firstElement = data.get(0);
                        participa_programa_uno = getIntValue(firstElement, "participa_programa_uno");
                        participa_programa_dos = getIntValue(firstElement, "participa_programa_dos");
                        participa_programa_tres = getIntValue(firstElement, "participa_programa_tres");
                        participa_programa_cuatro = getIntValue(firstElement, "participa_programa_cuatro");
                        participa_programa_cinco = getIntValue(firstElement, "participa_programa_cinco");
                        participa_programa_no = getIntValue(firstElement, "participa_programa_no");
                        justicia_si = getIntValue(firstElement, "justicia_si");
                        justicia_no = getIntValue(firstElement, "justicia_no");
                        agresor_si = getIntValue(firstElement, "agresor_si");
                        agresor_no = getIntValue(firstElement, "agresor_no");
                        salud_si = getIntValue(firstElement, "salud_si");
                        salud_no = getIntValue(firstElement, "salud_no");
                        adn_si = getIntValue(firstElement, "adn_si");
                        adn_no = getIntValue(firstElement, "adn_no");
                        intervencion_aplica = getIntValue(firstElement, "intervencion_aplica");
                        intervencion_no_aplica = getIntValue(firstElement, "intervencion_no_aplica");
                        firmes_aplica = getIntValue(firstElement, "firmes_aplica");
                        firmes_no_aplica = getIntValue(firstElement, "firmes_no_aplica");

                        // Crear el Intent y añadir los extras
                        Intent intent = new Intent(FiltroTratamientoTotalSoa.this, TratamientoDiferenciadoSoaActivity.class);
                        intent.putExtra("participa_programa_uno", participa_programa_uno);
                        intent.putExtra("participa_programa_dos", participa_programa_dos);
                        intent.putExtra("participa_programa_tres", participa_programa_tres);
                        intent.putExtra("participa_programa_cuatro", participa_programa_cuatro);
                        intent.putExtra("participa_programa_cinco", participa_programa_cinco);
                        intent.putExtra("participa_programa_no", participa_programa_no);
                        intent.putExtra("justicia_si", justicia_si);
                        intent.putExtra("justicia_no", justicia_no);
                        intent.putExtra("agresor_si", agresor_si);
                        intent.putExtra("agresor_no", agresor_no);
                        intent.putExtra("salud_si", salud_si);
                        intent.putExtra("salud_no", salud_no);
                        intent.putExtra("adn_si", adn_si);
                        intent.putExtra("adn_no", adn_no);
                        intent.putExtra("intervencion_aplica", intervencion_aplica);
                        intent.putExtra("intervencion_no_aplica", intervencion_no_aplica);
                        intent.putExtra("firmes_aplica", firmes_aplica);
                        intent.putExtra("ingresoProcesado", firmes_no_aplica);

                        // Iniciar la actividad PoblacionCjdrActivity
                        startActivity(intent);
                    }
                } else {
                    // Maneja el caso de error
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                // Maneja el caso de fallo de la llamada
            }
        });
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


    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dayOfMonth, month, year);
    }

    private void initDatePickerInicio() {
        // Establece el Locale a español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                selectedDateInicio = makeDateString(dayOfMonth, month, year);
                dateButtonInicio.setText(selectedDateInicio);
                selectedDateFinal = selectedDateInicio;
                dateButtonFinal.setText(selectedDateFinal);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialogInicio = new DatePickerDialog(this, style, dateSetListener, year, month, dayOfMonth);
        datePickerDialogInicio.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private void initDatePickerFinal() {
        // Establece el Locale a español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                selectedDateFinal = makeDateString(dayOfMonth, month, year);
                dateButtonFinal.setText(selectedDateFinal);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialogFinal = new DatePickerDialog(this, style, dateSetListener, year, month, dayOfMonth);
        datePickerDialogFinal.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month) + " " + dayOfMonth + " " + year;
    }

    private String getMonthFormat(int month) {
        switch (month) {
            case 1: return "ENE";
            case 2: return "FEB";
            case 3: return "MAR";
            case 4: return "ABR";
            case 5: return "MAY";
            case 6: return "JUN";
            case 7: return "JUL";
            case 8: return "AGO";
            case 9: return "SEP";
            case 10: return "OCT";
            case 11: return "NOV";
            case 12: return "DIC";
            default: return "ENE";
        }
    }

    private String formatDateToYMD(int year, int month, int dayOfMonth) {
        return String.format("%04d-%02d-%02d", year, month, dayOfMonth);
    }

    public void openDatePickerInicio(View view) {
        datePickerDialogInicio.show();
    }

    public void openDatePickerFinal(View view) {
        datePickerDialogFinal.show();
    }

    public String showSelectedDateInicio(View view) {
        String[] datePartsInicio = selectedDateInicio.split(" ");
        int dayInicio = Integer.parseInt(datePartsInicio[1]);
        int monthInicio = getMonthNumber(datePartsInicio[0]);
        int yearInicio = Integer.parseInt(datePartsInicio[2]);
        String formattedDateInicio = formatDateToYMD(yearInicio, monthInicio, dayInicio);

        return formattedDateInicio;

    }
    public String showSelectedDateFinal(View view) {
        String[] datePartsFinal = selectedDateFinal.split(" ");
        int dayFinal = Integer.parseInt(datePartsFinal[1]);
        int monthFinal = getMonthNumber(datePartsFinal[0]);
        int yearFinal = Integer.parseInt(datePartsFinal[2]);
        String formattedDateFinal = formatDateToYMD(yearFinal, monthFinal, dayFinal);

        return formattedDateFinal;

    }

    private int getMonthNumber(String month) {
        switch (month) {
            case "ENE": return 1;
            case "FEB": return 2;
            case "MAR": return 3;
            case "ABR": return 4;
            case "MAY": return 5;
            case "JUN": return 6;
            case "JUL": return 7;
            case "AGO": return 8;
            case "SEP": return 9;
            case "OCT": return 10;
            case "NOV": return 11;
            case "DIC": return 12;
            default: return 1;
        }
    }
}