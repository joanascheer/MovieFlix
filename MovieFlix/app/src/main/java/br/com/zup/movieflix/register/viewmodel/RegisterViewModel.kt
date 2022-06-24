package br.com.zup.movieflix.register.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.movieflix.register.model.RegisterModel
import br.com.zup.movieflix.register.repository.RegisterRepository

class RegisterViewModel: ViewModel() {
    private val repository = RegisterRepository()
    private var _response: MutableLiveData<RegisterModel> = MutableLiveData()
    val response: LiveData<RegisterModel> = _response

    fun authentication(registeredUser: RegisterModel){
        try {
            _response.value = repository.verifyData(registeredUser)
        }catch (e: Exception){
            Log.i("Error", "------> ${e.message}")
        }
    }
}
