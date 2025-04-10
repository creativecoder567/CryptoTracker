package com.usbank.cryptotracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usbank.cryptotracker.data.model.CryptoResponse
import com.usbank.cryptotracker.data.respository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    private val _cryptoData = MutableStateFlow<CryptoResponse?>(null)
    val cryptoData: StateFlow<CryptoResponse?> = _cryptoData

    init {
        fetchCryptoPrices()
    }

    private fun fetchCryptoPrices() {
        viewModelScope.launch {
            try {
                val response = repository.getCryptoPrices()
                _cryptoData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
