package br.com.zup.movieflix.register.repository

import android.widget.Toast
import br.com.zup.movieflix.register.model.RegisterModel

class RegisterRepository {

    fun verifyData(registeredUser: RegisterModel): RegisterModel? {
        if (registeredUser.username.isNotEmpty() && registeredUser.email.isNotEmpty() && registeredUser.password.isNotEmpty()) {
            return registeredUser
        }
        return null
    }

}