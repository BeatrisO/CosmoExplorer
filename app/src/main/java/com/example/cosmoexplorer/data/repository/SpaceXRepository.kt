package com.example.cosmoexplorer.data.repository

import com.example.cosmoexplorer.data.model.Rocket
import com.example.space.data.remote.RetrofitInstance

class SpaceXRepository {
    suspend fun getRockets(): List<Rocket> {
        return RetrofitInstance.apiSpaceX.getRockets()
    }
}