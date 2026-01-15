package com.example.cosmoexplorer.data.model

data class Rocket (
    val name: String
)

data class RocketsResponse(
    val rockets: List<Rocket>
)