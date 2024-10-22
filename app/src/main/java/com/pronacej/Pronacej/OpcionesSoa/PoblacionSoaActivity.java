package com.pronacej.Pronacej.OpcionesSoa;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.ResultadosSoa.ResultadoReporteDiarioSoa;
import com.pronacej.Pronacej.ResultadosSoa.ResultadosSituacionJuridicaSoa;
import com.pronacej.Pronacej.Utils.Apis;
import com.pronacej.Pronacej.Utils.SoaService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoblacionSoaActivity extends AppCompatActivity {
    private static final String TAG = "PoblacionSoaActivity";
    private ArrayList<HashMap<String, String>> reportData;
    private Button dateButton;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private DatePickerDialog datePickerDialog;
    private String selectedDate;
    private SoaService soaService;
    private ConstraintLayout opcion1;
    private ConstraintLayout opcion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion_soa);

        initViews();
        setupDatePicker();
        setupListeners();
        soaService = Apis.getSoaService();
        // Llamar a la API por primera vez con la fecha por defecto
        llamarEndPoint(showSelectedDate());

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(PoblacionSoaActivity.this, CategoriaMenu.class);
                startActivity(intentHome);
            }

        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Llamar al método onBackPressed para ir atrás
            }
        });
    }

    private void initViews() {
        dateButton = findViewById(R.id.etFechaInicio);
        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        opcion1 = findViewById(R.id.Opcion1);
        opcion2 = findViewById(R.id.Opcion2);

        addTouchFeedback(opcion1);
        addTouchFeedback(opcion2);


        selectedDate = getTodaysDate();
        dateButton.setText(selectedDate);
    }

    private void setupDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1;
            selectedDate = makeDateString(dayOfMonth, month, year);
            dateButton.setText(selectedDate);
            llamarEndPoint(showSelectedDate());
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private void setupListeners() {
        dateButton.setOnClickListener(this::openDatePickerInicio);
        opcion1.setOnClickListener(v -> handleOpcion1Click());
        opcion2.setOnClickListener(v -> handleOpcion2Click());
    }

    private void llamarEndPoint(String fechaSeleccionada) {
        Call<List<Map<String, Object>>> call = soaService.obtenerReporteDiarioSoa(fechaSeleccionada);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    processResponse(response.body());
                } else {
                    Log.e(TAG, "Error en la respuesta: " + response.message());
                    Toast.makeText(PoblacionSoaActivity.this, "Escoge una opción", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                Log.e(TAG, "Fallo en la llamada: " + t.getMessage());
                Toast.makeText(PoblacionSoaActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processResponse(List<Map<String, Object>> data) {
        reportData = new ArrayList<>();
        for (Map<String, Object> item : data) {
            HashMap<String, String> reportItem = new HashMap<>();
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                reportItem.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
            reportData.add(reportItem);
        }
        Log.d(TAG, "Datos recibidos: " + reportData.toString());
        opcion1.setEnabled(true);
        opcion2.setEnabled(true);
    }

    private void handleOpcion1Click() {
        if (reportData != null && !reportData.isEmpty()) {
            ArrayList<HashMap<String, String>> filteredData = filterData("centro_soa", "poblacion_soa");
            Log.d(TAG, "Datos enviados a ResultadoReporteDiarioSoa: " + filteredData);
            Intent intentOpcion1 = new Intent(this, ResultadoReporteDiarioSoa.class);
            intentOpcion1.putExtra("reportData", filteredData);
            startActivity(intentOpcion1);
        } else {
            Log.e(TAG, "reportData es null o está vacío");
            Toast.makeText(this, "No hay datos disponibles", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleOpcion2Click() {
        if (reportData != null && !reportData.isEmpty()) {
            ArrayList<HashMap<String, String>> filteredData = filterData("centro_soa", "varones_soa", "mujeres_soa");
            Log.d(TAG, "Datos enviados a ResultadosSituacionJuridicaSoa: " + filteredData);
            Intent intentOpcion2 = new Intent(this, ResultadosSituacionJuridicaSoa.class);
            intentOpcion2.putExtra("reportData", filteredData);
            startActivity(intentOpcion2);
        } else {
            Log.e(TAG, "reportData es null o está vacío");
            Toast.makeText(this, "No hay datos disponibles", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<HashMap<String, String>> filterData(String... keys) {
        ArrayList<HashMap<String, String>> filteredData = new ArrayList<>();
        for (HashMap<String, String> data : reportData) {
            HashMap<String, String> filteredEntry = new HashMap<>();
            for (String key : keys) {
                if (data.containsKey(key)) {
                    filteredEntry.put(key, data.get(key));
                } else {
                    Log.w(TAG, "Clave no encontrada: " + key);
                    filteredEntry.put(key, "N/A");
                }
            }
            filteredData.add(filteredEntry);
        }
        return filteredData;
    }

    public void openDatePickerInicio(View view) {
        datePickerDialog.show();
    }

    private String showSelectedDate() {
        String[] dateParts = selectedDate.split(" ");
        int day = Integer.parseInt(dateParts[1]);
        int month = getMonthNumber(dateParts[0]);
        int year = Integer.parseInt(dateParts[2]);
        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1) return "ENE";
        if (month == 2) return "FEB";
        if (month == 3) return "MAR";
        if (month == 4) return "ABR";
        if (month == 5) return "MAY";
        if (month == 6) return "JUN";
        if (month == 7) return "JUL";
        if (month == 8) return "AGO";
        if (month == 9) return "SEP";
        if (month == 10) return "OCT";
        if (month == 11) return "NOV";
        if (month == 12) return "DIC";
        return "ENE";
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

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private boolean validarFechaFormato(String fecha) {
        return fecha.matches("^\\d{4}-\\d{2}-\\d{2}$");
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