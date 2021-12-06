package com.app.woocerassignment.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.woocerassignment.data.product.ProductRepo
import com.app.woocerassignment.data.product.local.dto.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepo: ProductRepo
) : ViewModel() {
    //region fields
    private lateinit var job: Job
    private val _products: MutableStateFlow<List<ProductEntity>> = MutableStateFlow(listOf())
    val products: StateFlow<List<ProductEntity>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _message: Channel<String> = Channel()
    val message = _message.receiveAsFlow()
    //endregion

    //region Public functions
    fun getAllProducts() {
        if (this::job.isInitialized) {
            // need to cancel previous request
            job.cancel()
        }

        job = viewModelScope.launch {
            productRepo.getAllProducts()
                .onStart { _isLoading.value = true }
                .collect {
                    _isLoading.value = false
                    it
                        .onSuccess { products -> handleSuccess(products) }
                        .onFailure { error -> handleError(error) }
                }
        }
    }
    //endregion

    //region Private functions
    private fun handleError(error: Throwable) {
        viewModelScope.launch {
            _message.send(error.message ?: "Unknown Error!")
        }
    }

    private fun handleSuccess(products: List<ProductEntity>) {
        _products.value = products
    }
    //endregion
}