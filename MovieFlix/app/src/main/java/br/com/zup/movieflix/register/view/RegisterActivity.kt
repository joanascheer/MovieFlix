package br.com.zup.movieflix.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.R
import br.com.zup.movieflix.databinding.ActivityRegisterBinding
import br.com.zup.movieflix.home.view.HomeActivity
import br.com.zup.movieflix.login.viewmodel.LoginViewModel
import br.com.zup.movieflix.register.model.RegisterModel
import br.com.zup.movieflix.register.repository.RegisterRepository
import br.com.zup.movieflix.register.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var nome: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var passwordRepeat: String
    private val repository = RegisterRepository()
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.bvLogin.setOnClickListener {
//            recuperarDados()
//            val register = RegisterModel(nome, email, password, passwordRepeat)
//            viewModel.authentication(register) //verificar se item na lista
//            viewModel.response.observe(this){
//                if (it.authentication){
//                    startActivity(Intent(this,HomeActivity::class.java))
//                }
//            }
//        }

        binding.bvLogin.setOnClickListener {
            recuperarDados()
            val register = RegisterModel(nome, email, password, passwordRepeat)
            if (passwordVerification(password, passwordRepeat)) {
                repository.registerUser(register)
                viewModel.authentication(register)
                viewModel.response.observe(this) {
                    if (register.authentication) {
                        Toast.makeText(this,"Usuário cadastrado com sucesso!",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                    } else {
                        Toast.makeText(this, "Algo deu errado, tente novamente.", Toast.LENGTH_LONG).show()
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
    private fun recuperarDados() {
        nome = binding.etUserNameRegister.text.toString()
        email = binding.etEmailRegister.text.toString()
        password = binding.etPasswordRegister.text.toString()
        passwordRepeat = binding.etConfirmPasswordRegister.text.toString()

        passwordVerification(password,passwordRepeat)
    }

    private fun passwordVerification(password: String, passwordRepeat: String): Boolean {
        if (password != passwordRepeat) {
            Toast.makeText(this,"As duas senhas devem ser iguais",Toast.LENGTH_LONG).show()
        } else {
            return true
        }
        return false
    }

}