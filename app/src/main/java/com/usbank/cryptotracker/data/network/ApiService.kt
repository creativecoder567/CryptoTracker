package com.usbank.cryptotracker.data.network

import com.usbank.cryptotracker.data.model.CryptoResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v1/currencies/usd.json")
    suspend fun getCryptoPrices(): CryptoResponse
}
