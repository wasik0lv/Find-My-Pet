package com.example.findmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.findmypet.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class SignupPage : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Signup Page"
    var btnSignup: Button? = null //button sign up
    var inpEmail: EditText? = null  // input email
    var inpPass: EditText? = null //input password
    var inpTel: EditText? = null //input  telephone number
    var inpConPass: EditText? = null //input confirm password
    var inpUsername: EditText? = null //input username
    var inpfname: EditText? = null //input firstname
    var inplname: EditText? = null // input lastname
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)
        init()
        mAuth = FirebaseAuth.getInstance()
        if (mAuth!!.currentUser != null) {
            startActivity(
                Intent(
                    this@SignupPage,
                    HomeFragment::class.java
                )
            )
            finish()
        }

        btnSignup?.setOnClickListener {
            val email = inpEmail?.text.toString().trim()
            val password = inpPass?.text.toString().trim()
            val confirmPassword = inpConPass?.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this@SignupPage, "Please fill in all fields", Toast.LENGTH_SHORT)
                    .show()
            } else if (password != confirmPassword) {
                Toast.makeText(this@SignupPage, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                mAuth?.createUserWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@SignupPage,
                                "Sign up successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@SignupPage, HomeFragment::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@SignupPage,
                                "Sign up failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }


    }

    fun init() {
        btnSignup = findViewById(R.id.signup_btnSingup)
        inpfname = findViewById(R.id.signup_inpFname)
        inplname = findViewById(R.id.signup_inpLname)
        inpEmail = findViewById(R.id.signup_inpEmail)
        inpTel = findViewById(R.id.signup_inpPhonenum)
        inpPass = findViewById(R.id.signup_inpPassword)
        inpConPass = findViewById(R.id.signup_inpConPassword)
        inpUsername = findViewById(R.id.signup_inpUsername)
    }
}