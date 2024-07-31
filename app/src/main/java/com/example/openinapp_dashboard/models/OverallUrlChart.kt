package com.example.openinapp_dashboard.models

import com.google.gson.annotations.SerializedName

data class OverallUrlChart(
    @SerializedName("00:00") val hour0: Int,
    @SerializedName("01:00") val hour1: Int,
    @SerializedName("02:00") val hour2: Int,
    @SerializedName("03:00") val hour3: Int,
    @SerializedName("04:00") val hour4: Int,
    @SerializedName("05:00") val hour5: Int,
    @SerializedName("06:00") val hour6: Int,
    @SerializedName("07:00") val hour7: Int,
    @SerializedName("08:00") val hour8: Int,
    @SerializedName("09:00") val hour9: Int,
    @SerializedName("10:00") val hour10: Int,
    @SerializedName("11:00") val hour11: Int,
    @SerializedName("12:00") val hour12: Int,
    @SerializedName("13:00") val hour13: Int,
    @SerializedName("14:00") val hour14: Int,
    @SerializedName("15:00") val hour15: Int,
    @SerializedName("16:00") val hour16: Int,
    @SerializedName("17:00") val hour17: Int,
    @SerializedName("18:00") val hour18: Int,
    @SerializedName("19:00") val hour19: Int,
    @SerializedName("20:00") val hour20: Int,
    @SerializedName("21:00") val hour21: Int,
    @SerializedName("22:00") val hour22: Int,
    @SerializedName("23:00") val hour23: Int
)