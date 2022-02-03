package com.ersubhadip.instantweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ersubhadip.instantweather.R
import com.etebarian.meowbottomnavigation.MeowBottomNavigation


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting up navBar
        var bottomNavigation:MeowBottomNavigation=findViewById(R.id.bottomNavigationView)
        bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_forecast))
        bottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_weather))
        bottomNavigation.add(MeowBottomNavigation.Model(3,R.drawable.ic_graph))

        bottomNavigation.show(2,true)

        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                1->Toast.makeText(this@MainActivity,"Forecast",LENGTH_SHORT).show()

                2->Toast.makeText(this@MainActivity,"Home",LENGTH_SHORT).show()

                3->Toast.makeText(this@MainActivity,"Weather Analytics",LENGTH_SHORT).show()

                else -> Toast.makeText(this@MainActivity,"Hello",LENGTH_SHORT).show()
            }
        }


//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
//        val navController = navHostFragment.navController


    }
}