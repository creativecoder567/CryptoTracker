package com.usbank.cryptotracker.data.respository


import com.usbank.cryptotracker.data.model.CryptoResponse
import com.usbank.cryptotracker.data.network.ApiService
import javax.inject.Inject

class CryptoRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCryptoPrices(): CryptoResponse {
        return apiService.getCryptoPrices()
    }
}
