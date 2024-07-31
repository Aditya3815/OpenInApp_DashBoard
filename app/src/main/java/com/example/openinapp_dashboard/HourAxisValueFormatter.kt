package com.example.openinapp_dashboard

import com.github.mikephil.charting.formatter.ValueFormatter

class HourAxisValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return "${value.toInt()}:00"
    }
}