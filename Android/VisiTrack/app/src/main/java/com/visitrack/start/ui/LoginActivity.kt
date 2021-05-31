package com.visitrack.start.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.visitrack.R
import com.visitrack.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contentLogin.btnLogin.setOnClickListener(this)

        binding.contentLogin.registerHere.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.btn_login -> {
                val passwords = binding.contentLogin.edtPassword.text.toString().trim()
                val username = binding.contentLogin.edtUsername.text.toString().trim()
                if (username.equals("")) {
                    binding.contentLogin.edtUsername.error = "Please Enter Your Username"
                } else if (passwords.equals("")) {
                    binding.contentLogin.edtPassword.error = "Please Enter Your Password"
                }
            }
        }
    }
}