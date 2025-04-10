package com.usbank.cryptotracker.data.model

data class CryptoResponse(
    val date: String,
    val usd: Map<String, Double>
)
