package com.example.findmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.findmypet.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class Startpage : AppCompatActivity() {
    var btnGotoLogin:Button?=null
    var btnGotoSignup:Button?=null
    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Start page"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_page)
        init()

        btnGotoLogin?.setOnClickListener {
            var intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }
        btnGotoSignup?.setOnClickListener {
            var intent = Intent(this, SignupPage::class.java)
            startActivity(intent)
            finish()
        }
//        query data
        mAuth = FirebaseAuth.getInstance()

//        if login the go to home
        if (mAuth!!.currentUser != null) {
            Log.d(TAG, "Continue with: " + mAuth!!.currentUser!!.email)
// เป็นการสั5งให้ทําการ start activity ส่วนของหน้า result
            startActivity(Intent(this@Startpage,
                HomeFragment::class.java))
            finish()
        }



    }

    fun init(){
        btnGotoLogin = findViewById(R.id.start_btnLogin)
        btnGotoSignup = findViewById((R.id.start_btnSignup))
    }
}