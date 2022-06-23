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

    fun authentication(user: RegisterModel) {
        try {
            _response.value = authenticate(user)
        }catch (ex: Exception){
            Log.i("Error", "------> ${ex.message}")
        }
    }

    //verifico se o usuario esta na lista.
    private fun authenticate(user: RegisterModel) : RegisterModel {
        try {
            if (repository.bancoDeDados.contains(user)) {
                user.authentication = true
            }
        } catch (e:Exception) {
            Log.i("Error","----> ${e.message}")
        }

        return user
    }

//    fun verifyRegisteredUsers (user: RegisterModel):Boolean {
//        try{
//            _response.value = repository.registeredUsers(user)
//            return true
//        } catch (e: Exception) {
//            Log.i("Error","----> ${e.message}")
//        }
//        return false
//    }
}