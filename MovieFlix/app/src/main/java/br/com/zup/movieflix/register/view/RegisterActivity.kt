package br.com.zup.movieflix.register.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.databinding.ActivityRegisterBinding
import br.com.zup.movieflix.login.view.LoginActivity
import br.com.zup.movieflix.register.model.RegisterModel
import br.com.zup.movieflix.register.repository.RegisterRepository
import br.com.zup.movieflix.register.viewmodel.RegisterViewModel
import br.com.zup.movieflix.util.CADASTRO_FAIL
import br.com.zup.movieflix.util.CADASTRO_OK
import br.com.zup.movieflix.util.EQUAL_PASSWORD

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var user: RegisterModel
    private val repository = RegisterRepository()
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bvLogin.setOnClickListener {
            val register = recuperarDados()
            if (passwordVerification(register.password, register.passwordRepeat)) {
                repository.registerUser(register)
                viewModel.authentication(register)
                viewModel.response.observe(this) {
                    if (it.authentication) {
                        Toast.makeText(
                            this,
                            CADASTRO_OK,
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this, CADASTRO_FAIL, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
            cleanData()
        }
    }

    private fun cleanData() {
        binding.etUserNameRegister.text.clear()
        binding.etEmailRegister.text.clear()
        binding.etPasswordRegister.text.clear()
        binding.etConfirmPasswordRegister.text.clear()
    }

    private fun recuperarDados(): RegisterModel {
        user.username = binding.etUserNameRegister.text.toString()
        user.email = binding.etEmailRegister.text.toString()
        user.password = binding.etPasswordRegister.text.toString()
        user.passwordRepeat = binding.etConfirmPasswordRegister.text.toString()
        return RegisterModel(
            user.username,
            user.email,
            user.password,
            user.passwordRepeat
        )
    }

    private fun passwordVerification(password: String, passwordRepeat: String): Boolean {
        if (password != passwordRepeat) {
            Toast.makeText(this, EQUAL_PASSWORD, Toast.LENGTH_LONG).show()
        } else {
            return true
        }
        return false
    }

}