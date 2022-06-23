package br.com.zup.movieflix.register.model

class RegisterModel(
    var username: String,
    var email: String,
    var password: String,
    var passwordRepeat: String,
    var authentication: Boolean = false
)