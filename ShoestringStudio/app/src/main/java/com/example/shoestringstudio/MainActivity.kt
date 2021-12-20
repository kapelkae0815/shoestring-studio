package com.example.shoestringstudio


import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation.findNavController
import com.example.shoestringstudio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    //Stores the constraint layout menu
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Manifest.permission.READ_EXTERNAL_STORAGE;
        Manifest.permission.MANAGE_EXTERNAL_STORAGE;
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /**
     * Adds the ability to navigate up with the nav controller
     *
     * @return if the navigation controller successfully navigated up
     **/
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}