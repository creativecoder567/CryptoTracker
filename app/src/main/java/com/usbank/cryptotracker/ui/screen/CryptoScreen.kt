package com.usbank.cryptotracker.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.usbank.cryptotracker.data.model.CryptoResponse
import com.usbank.cryptotracker.ui.viewmodel.CryptoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoScreen(viewModel: CryptoViewModel = hiltViewModel()) {
    val cryptoData by viewModel.cryptoData.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crypto Prices") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        content = { innerPadding ->
            CryptoListContent(
                data = cryptoData,
                modifier = Modifier.padding(innerPadding),
                onClick = { symbol ->
                    Toast.makeText(context, "$symbol clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    )
}

@Composable
fun CryptoListContent(
    data: CryptoResponse?,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (data?.usd != null) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(data.usd.toList()) { (symbol, price) ->
                    CryptoCard(symbol.uppercase(), price, onClick)
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun CryptoCard(symbol: String, price: Double, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick(symbol) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(symbol, style = MaterialTheme.typography.titleMedium)
            Text(
                text = "$${"%.4f".format(price)}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CryptoCardPreview() {
    CryptoCard(symbol = "BTC", price = 62000.2567) {
        // No-op click for preview
    }
}

@Preview(showBackground = true)
@Composable
fun CryptoListContentPreview() {
    val sampleData = CryptoResponse(
        usd = mapOf(
            "BTC" to 62000.25,
            "ETH" to 3000.75,
            "DOGE" to 0.12
        ),
        date = "2023-09-01"
    )

    CryptoListContent(
        data = sampleData,
        onClick = {}
    )
}

