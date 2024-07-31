package com.example.openinapp_dashboard.models

data class Data(
    val favourite_links: List<Any>,
    val overall_url_chart: OverallUrlChart,
    val recent_links: List<RecentLink>,
    val top_links: List<TopLink>
)