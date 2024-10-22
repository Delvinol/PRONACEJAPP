package com.pronacej.Pronacej.ResultadosCjrd;

public class PercentValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
    public String getFormattedValue(float value) {
        return String.format("%.1f%%", value);
    }
}
