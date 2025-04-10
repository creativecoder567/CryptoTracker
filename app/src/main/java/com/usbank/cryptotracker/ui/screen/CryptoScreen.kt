package com.usbank.cryptotracker.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.usbank.cryptotracker.ui.viewmodel.CryptoViewModel

@Composable
fun CryptoScreen(viewModel: CryptoViewModel= hiltViewModel()) {
    val cryptoData by viewModel.cryptoData.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Crypto Prices", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        cryptoData?.usd?.forEach { (symbol, price) ->
            Text("$symbol : $price")
        } ?: Text("Loading...")
    }
}
