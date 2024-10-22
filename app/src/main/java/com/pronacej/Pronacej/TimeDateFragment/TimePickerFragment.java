package com.pronacej.Pronacej.TimeDateFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private EditText editText;

    public static TimePickerFragment newInstance(EditText editText){
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setEditText(editText);
        return fragment;
    }

    public void setEditText(EditText editText){
        this.editText = editText;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour,minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Manejar la hora seleccionada aquí
        String time = String.format("%02d:%02d", hourOfDay, minute);
        editText.setText(time);
    }
}

