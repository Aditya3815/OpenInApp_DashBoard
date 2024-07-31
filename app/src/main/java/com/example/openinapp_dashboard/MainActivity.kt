package com.example.openinapp_dashboard

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.openinapp_dashboard.api.ServiceLocator
import com.example.openinapp_dashboard.databinding.ActivityMainBinding
import com.example.openinapp_dashboard.fragments.RecentLinkFragment
import com.example.openinapp_dashboard.fragments.TopLinkFragment
import com.example.openinapp_dashboard.repository.ChartRepository
import com.example.openinapp_dashboard.viewmodel.ChartViewModel
import com.example.openinapp_dashboard.viewmodel.ChartViewModelFactory
import com.github.mikephil.charting.data.LineData
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ViewPagerAdapter
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: ChartViewModel by viewModels { ChartViewModelFactory(ServiceLocator.repository) }


    private val bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        initView()
        setupChart()
        observeChartData()
        viewModel.fetchChartData("Bearer $bearerToken")

        binding.username.apply {
            val user = intent.getStringExtra("name")
            text = "$user 🖐️"
        }
        binding.greeting.apply {
            val greet = intent.getStringExtra("greeting")
            text = greet
        }
    }

    private fun observeChartData() {
        lifecycleScope.launch {
            viewModel.chartData.collect { lineDataSet ->
                lineDataSet?.let {
                    val lineData = LineData(it)
                    binding.lineChart.data = lineData
                    binding.lineChart.invalidate()
                }
            }
        }
    }

    private fun setupChart() {
        binding.lineChart.apply {
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            xAxis.valueFormatter = HourAxisValueFormatter()
            axisRight.isEnabled = false
        }
    }

    private fun initView() {
        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = 1
        TabLayoutMediator(binding.tabBarLayout, binding.viewPager) { tab, position ->
            tab.text = adapter.fragmentTitleList[position]
        }.attach()
    }

    inner class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val fragmentList = ArrayList<Fragment>()
        val fragmentTitleList = arrayListOf("Top Links", "Recent Links")

        init {
            fragmentList.add(TopLinkFragment())
            fragmentList.add(RecentLinkFragment())
        }

        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]
    }
}