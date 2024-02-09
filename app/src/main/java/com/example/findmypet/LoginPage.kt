package com.example.findmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.findmypet.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {
    var btnlogin: Button? = null
    var inpEmail: EditText? = null
    var inpPass: EditText? = null
    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Login Page"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        init()

        mAuth = FirebaseAuth.getInstance()
        if (mAuth!!.currentUser != null) {
            startActivity(
                Intent(
                    this@LoginPage,
                    HomeFragment::class.java
                )
            )
            finish()
        }

        btnlogin?.setOnClickListener {
            val email = inpEmail?.text.toString().trim() { it <= ' ' }
            val password = inpPass?.text.toString().trim() { it <= ' ' }
//            check existed data
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your Email Address", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your Password", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Password was empty!")
                return@setOnClickListener
            }

//            check information from input fields with firebase
            mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (password.length < 6) {
                        inpPass?.error =
                            "Please check your password. Password must have minimum 6 characters."
                        Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        Toast.makeText(
                            this, "Authentication Failed: " +
                                    task.exception!!.message, Toast.LENGTH_LONG
                        ).show()
                        Log.d(
                            TAG, "Authentication Failed: " +
                                    task.exception!!.message
                        )
                    }
                } else {
                    Toast.makeText(
                        this, "Sign in successfully !",Toast.LENGTH_LONG).show()
                                Log . d (TAG, "Sign in successfully!"
                    )
                    startActivity(
                        Intent(
                            this@LoginPage,
                            HomeFragment::class.java
                        )
                    )
                    finish()
                }
            }
        }


    }

    fun init() {
        btnlogin = findViewById(R.id.login_btnLogin)
        inpEmail = findViewById(R.id.login_inp_email)
        inpPass = findViewById(R.id.login_inp_password)
    }
}