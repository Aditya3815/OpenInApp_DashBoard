package com.example.openinapp_dashboard.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openinapp_dashboard.models.OverallUrlChart
import com.example.openinapp_dashboard.repository.ChartRepository
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChartViewModel(private val repository: ChartRepository) : ViewModel() {
    private val _chartData = MutableStateFlow<LineDataSet?>(null)
    val chartData: StateFlow<LineDataSet?> = _chartData

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchChartData(token: String) {
        viewModelScope.launch {
            repository.getDashboardData(token).fold(
                onSuccess = { dashboardData ->
                    dashboardData.overall_url_chart?.let { urlChart ->
                        val entries = urlChart.toEntries()
                        _chartData.value = LineDataSet(entries, "Overall URL Chart").apply {
                            color = android.graphics.Color.BLUE
                            setDrawCircles(false)
                            lineWidth = 2f
                        }
                    } ?: run {
                        _error.value = "Overall URL chart data is missing"
                    }
                },
                onFailure = { exception ->
                    _error.value = exception.message ?: "An unknown error occurred"
                }
            )
        }
    }

    private fun OverallUrlChart.toEntries(): List<Entry> {
        return listOf(
            Entry(0f, hour0?.toFloat() ?: 0f),
            Entry(1f, hour1?.toFloat() ?: 0f),
            Entry(2f, hour2?.toFloat() ?: 0f),
            Entry(3f, hour3?.toFloat() ?: 0f),
            Entry(4f, hour4?.toFloat() ?: 0f),
            Entry(5f, hour5?.toFloat() ?: 0f),
            Entry(6f, hour6?.toFloat() ?: 0f),
            Entry(7f, hour7?.toFloat() ?: 0f),
            Entry(8f, hour8?.toFloat() ?: 0f),
            Entry(9f, hour9?.toFloat() ?: 0f),
            Entry(10f, hour10?.toFloat() ?: 0f),
            Entry(11f, hour11?.toFloat() ?: 0f),
            Entry(12f, hour12?.toFloat() ?: 0f),
            Entry(13f, hour13?.toFloat() ?: 0f),
            Entry(14f, hour14?.toFloat() ?: 0f),
            Entry(15f, hour15?.toFloat() ?: 0f),
            Entry(16f, hour16?.toFloat() ?: 0f),
            Entry(17f, hour17?.toFloat() ?: 0f),
            Entry(18f, hour18?.toFloat() ?: 0f),
            Entry(19f, hour19?.toFloat() ?: 0f),
            Entry(20f, hour20?.toFloat() ?: 0f),
            Entry(21f, hour21?.toFloat() ?: 0f),
            Entry(22f, hour22?.toFloat() ?: 0f),
            Entry(23f, hour23?.toFloat() ?: 0f)
        )
    }
}