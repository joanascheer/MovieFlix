package br.com.zup.movieflix.register.repository

import android.widget.Toast
import br.com.zup.movieflix.register.model.RegisterModel

class RegisterRepository {
    val bancoDeDados = mutableListOf<RegisterModel>()

    fun registeredUsers(user: RegisterModel): MutableList<RegisterModel> {
        bancoDeDados.add(
            RegisterModel(
            username = "Joana",
            email = "joana@user.com",
            password = "1234",
            passwordRepeat = "1234"
        )
        )
        bancoDeDados.add(
            RegisterModel(
            username = "Mikael",
            email = "mikael@user.com",
            password = "2345",
            passwordRepeat = "2345"
        )
        )

        return bancoDeDados
    }



    fun registerUser(user:RegisterModel): MutableList<RegisterModel> {
        bancoDeDados.add(user)
        return bancoDeDados
    }

//    fun authenticate (register : RegisterModel) : RegisterModel {
//
//        if(register.username == "usuario" && register.password == "1234"){
//            register.authentication = true
//        }else{
//            register.authentication = false
//        }
//
//        return register
//    }

}