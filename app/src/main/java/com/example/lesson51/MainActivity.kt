package com.example.lesson51

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {


    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        setupNavigation(savedInstanceState)

//
//        if (isRegistered()) {
//            Log.e("Data", "HasData")
//            navController.navigate(R.id.userInfoFragment)
//
//        } else {
//
//            Log.e("Data", "NoData")
//        }
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
