package com.visitrack.start.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import com.visitrack.R
import com.visitrack.core.utils.TokenPreference
import com.visitrack.databinding.ActivityLoginBinding
import com.visitrack.list.MainActivity
import com.visitrack.start.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.contentLogin.btnLogin.setOnClickListener(this)
        binding.contentLogin.registerHere.setOnClickListener(this)

        //Subscribe Push Notification
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        FirebaseMessaging.getInstance().token.addOnSuccessListener { deviceToken ->
            Log.d(MainActivity::class.java.simpleName, "Refreshed token: $deviceToken")
            token = deviceToken
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                val passwords = binding.contentLogin.edtPassword.text.toString().trim()
                val username = binding.contentLogin.edtUsername.text.toString().trim()
                if (username == "") {
                    binding.contentLogin.edtUsername.error = "Please Enter Your Username"
                    return
                } else if (passwords == "") {
                    binding.contentLogin.edtPassword.error = "Please Enter Your Password"
                    return
                }
                //LOGIN CODE
                val tokenPreference = TokenPreference(this)
                loginViewModel.getLogin(username, passwords, token).observe(this, { login ->
                    if (login.data?.success == true){
                        tokenPreference.setToken(login.data!!.token!!)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else if (login.data?.success == false){
                        binding.contentLogin.edtUsername.setText("")
                        binding.contentLogin.edtPassword.setText("")
                        Toast.makeText(this, R.string.login_error, Toast.LENGTH_SHORT).show()
                    }
                })
            }
            R.id.register_here -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}