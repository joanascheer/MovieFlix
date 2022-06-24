package br.com.zup.movieflix.register.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.databinding.ActivityRegisterBinding
import br.com.zup.movieflix.home.view.HomeActivity
import br.com.zup.movieflix.register.model.RegisterModel
import br.com.zup.movieflix.register.repository.RegisterRepository
import br.com.zup.movieflix.register.viewmodel.RegisterViewModel
import br.com.zup.movieflix.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bvLogin.setOnClickListener {
            observables()
        }
    }

    fun getUserData(): RegisterModel {
        return RegisterModel(
            username = binding.etUserNameRegister.text.toString(),
            email = binding.etEmailRegister.text.toString(),
            password = binding.etPasswordRegister.text.toString()
        )
    }

    private fun observables() {
        viewModel.authentication(getUserData())
        viewModel.response.observe(this) {
            if (authenticateFields()) {
                enterHomeActivity()
            } else {
                failMessage()
            }
            cleanInsertedData()
        }
    }

    private fun sucessMessage() {
        Toast.makeText(this, REGISTER_SUCESSFUL, Toast.LENGTH_LONG).show()
    }

    private fun failMessage() {
        Toast.makeText(this, REGISTER_FAIL, Toast.LENGTH_LONG).show()
    }

    private fun enterHomeActivity() {
        sucessMessage()
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun authenticateFields(): Boolean {
        when {
            binding.etUserNameRegister.text.isEmpty() -> {
                binding.etUserNameRegister.error = INVALID_USERNAME
                return false
            }
            binding.etEmailRegister.text.isEmpty() -> {
                binding.etEmailRegister.error = INVALID_EMAIL
                return false
            }
            binding.etPasswordRegister.text.isEmpty() -> {
                binding.etPasswordRegister.error = INVALID_PASSWORD
                return false
            }
            !binding.etPasswordRegister.text.contentEquals(binding.etConfirmPasswordRegister.text) -> {
                binding.etPasswordRegister.error = INVALID_KEYS
                binding.etConfirmPasswordRegister.error = INVALID_KEYS
                return false
            }
        }
        return true
    }

    private fun cleanInsertedData() {
        binding.etUserNameRegister.text.clear()
        binding.etEmailRegister.text.clear()
        binding.etPasswordRegister.text.clear()
        binding.etConfirmPasswordRegister.text.clear()
    }
}

