package com.pronacej.Pronacej.TimeDateFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment
        // El propio framento es un listener así que
        // debe implementar: OnDateSetListener
        implements DatePickerDialog.OnDateSetListener {
    // EditText al que se le asocia este Picker
    private EditText editText;
    /*
    Queremos pasarle el EditText al constructor de
    la clase pero no se puede modificar el constructor
    de un fragmento, así que debemos crear un método
    estático que devuelva una instancia de esta clase,
    pasándole a este "constructor estático" el EditText
    al que está asociado y en el que queremos que se
    escriban las fechas al ser seleccionadas
    */
    public static DatePickerFragment newInstance(EditText editText) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setEditText(editText);
        return fragment;
    }
    // Tenemos que crear un setter ya que el
    // "constructor" es estático
    public void setEditText(EditText editText){
        this.editText = editText;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Ponemos la fecha actual para el datepicker
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        //Si ya hemos seleccionado una fecha en el picker y sigue
        //el formato que le hemos puesto nosotros (en este caso
        //dd/MM/yyyy) muestra la fecha seleccionada en el picker en
        //lugar de la fecha actual
        if(this.editText.getText().toString().length()>0) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = null;
            try {
                parsedDate = formatter.parse(this.editText.getText().toString());
                c.setTime(parsedDate);
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Devolver una instancia del DatePickerDialog
        // indicando quién es el listener del picker (this)
        // y la fecha pre-seleccionada (year, month, day)
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    //Cuando se seleccione una fecha, el
    // evento llamará a este método
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String selectedDate = String.format("%02d", day) + "/" + String.format("%02d", (month+1))  + "/" + String.format("%04d", year);
        this.editText.setText(selectedDate);
    }


}