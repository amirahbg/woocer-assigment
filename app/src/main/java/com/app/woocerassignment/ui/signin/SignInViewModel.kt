package com.app.woocerassignment.ui.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.woocerassignment.data.signin.SignInRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepo: SignInRepo
) : ViewModel() {

    //region fields
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _message: Channel<String> = Channel()
    val message = _message.receiveAsFlow()

    private val _signInCompleted: Channel<Unit> = Channel()
    val signInCompleted = _signInCompleted.receiveAsFlow()
    //endregion

    init {
        if (signInRepo.isLoggedIn()) {
            viewModelScope.launch {
                _signInCompleted.send(Unit)
            }
        }
    }

    //region Public functions
    fun signIn(
        name: String,
        email: String,
        website: String,
        username: String,
        password: String
    ) {
        val tempWeb = "https://wpt.woocer.com/"
        val tempUsername = "ck_85f212310cfff32728cc4c933331aa6bcf3002ef"
        val tempPassword = "cs_ee784168289012a919a008985d2252fadecea2bb"
        viewModelScope.launch {
            signInRepo.signIn(name, email, tempWeb, tempUsername, tempPassword)
                .onStart { _isLoading.value = true }
                .collect {
                    Log.i("TAG", "signIn: $it")
                    _isLoading.value = false

                    it
                        .onSuccess { handleSuccess() }
                        .onFailure { throwable -> handleFailure(throwable) }

                }
        }
    }
    //endregion

    //region Private functions
    private suspend fun handleFailure(throwable: Throwable) {
        _message.send(throwable.message ?: "Unknown Error!")
    }

    private suspend fun handleSuccess() {
        _signInCompleted.send(Unit)
    }
    //endregion
}