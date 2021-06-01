package com.visitrack.start.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.visitrack.R
import com.visitrack.core.utils.TokenPreference
import com.visitrack.databinding.ActivityRegisterBinding
import com.visitrack.start.viewmodel.RegisterViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel : RegisterViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contentRegister.btnRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.btn_register -> {
                val passwords = binding.contentRegister.edtPassword.text.toString().trim()
                val username = binding.contentRegister.edtUsername.text.toString().trim()
                if (username.equals("")) {
                    binding.contentRegister.edtUsername.error = "Please Enter Your Username"
                    return
                } else if (passwords.equals("")) {
                    binding.contentRegister.edtPassword.error = "Please Enter Your Password"
                    return
                }

                val tokenPreference = TokenPreference(this)

                registerViewModel.getRegister(username, passwords). observe(this,{ register->
                    if (register.data?.success == "true"){
                        tokenPreference.getToken()
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this, R.string.register_error, Toast.LENGTH_SHORT).show()
                        binding.contentRegister.edtUsername.setText("")
                        binding.contentRegister.edtPassword.setText("")
                    }
                })
            }
        }
    }
}