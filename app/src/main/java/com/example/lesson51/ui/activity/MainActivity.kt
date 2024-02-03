package com.example.lesson51.ui.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.lesson51.R

class MainActivity : AppCompatActivity() {


    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences =
            getSharedPreferences(R.string.preferences_name.toString(), Context.MODE_PRIVATE)
        setupNavigation(savedInstanceState)


    }

    private fun isRegistered(): Boolean {

        return sharedPreferences.contains("username")
    }

    private fun setupNavigation(savedInstanceState: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        if (savedInstanceState == null) {
            val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

            if (isRegistered()) {
                navGraph.setStartDestination(R.id.userInfoFragment)
                navController.graph = navGraph
            }
        }
    }


}
