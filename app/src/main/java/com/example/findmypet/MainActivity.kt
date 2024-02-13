package com.example.findmypet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.findmypet.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()
        init()
        setupNavigation()
    }

    private fun initViews() {
        val toolbar: Toolbar = binding.toolbar

        // Set up toolbar menu item click listeners
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tb_btn_scanner -> {
                    // Handle click on Scanner item
                    showScanner()
                    true // Return true to indicate that the click event has been handled
                }
                R.id.tb_btn_chat -> {
                    // Handle click on Chat item
                    showChat()
                    true // Return true to indicate that the click event has been handled
                }
                else -> false
            }
        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init() {
        // Initialization code
    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_progression, R.id.navigation_petcard,
                R.id.navigation_doctor, R.id.navigation_account
            )
        )
        val toolbar: Toolbar = binding.toolbar
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun showScanner() {
        Toast.makeText(this, "this is scanner", Toast.LENGTH_SHORT).show()
    }
    private fun showChat() {
        Toast.makeText(this, "this is chat", Toast.LENGTH_SHORT).show()
    }

}




