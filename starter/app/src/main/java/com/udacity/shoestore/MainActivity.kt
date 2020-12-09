package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.login.LoginViewModel
import com.udacity.shoestore.shoes.ListShoesViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var listViewModel: ListShoesViewModel
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        listViewModel = ViewModelProvider(this).get(ListShoesViewModel::class.java)

        navController.let {
            val appBarConfiguration = AppBarConfiguration
                .Builder()
                .setFallbackOnNavigateUpListener {
                    onBackPressed()
                    true
                }.build()

            setSupportActionBar(binding.toolbar)
            binding.toolbar.setupWithNavController(it, appBarConfiguration)

        }
    }

}
