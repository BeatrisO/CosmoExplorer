package com.example.cosmoexplorer.data.model

data class Rocket(
    val id: String,
    val name: String,
    val description: String,

    val active: Boolean,
    val first_flight: String,
    val success_rate_pct: Int,
    val cost_per_launch: Long,
    val wikipedia: String,

    val flickr_images: List<String>
)