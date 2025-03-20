package com.example.kotlinfbmicrowafe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinfbmicrowafe.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() { // наследуем AppCompatActivity
    private lateinit var binding: ActivityMainBinding
    val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            signInUser(email, password)
        }

        binding.registerRedirectButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }



    private fun signInUser(email: String, password: String) {


        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseAuth", "Вход успешен")
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.e("FirebaseAuth", "Ошибка входа: ${task.exception?.message}")
                }
            }
    }
}
