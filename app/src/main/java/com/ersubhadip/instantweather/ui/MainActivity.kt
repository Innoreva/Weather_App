package com.ersubhadip.instantweather.ui

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ersubhadip.instantweather.R
import com.etebarian.meowbottomnavigation.MeowBottomNavigation


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting up navBar
        val bottomNavigation: MeowBottomNavigation = findViewById(R.id.bottomNavigationView)
        bottomNavigation.add(
            MeowBottomNavigation.Model(
                R.string.forecastFragment,
                R.drawable.ic_forecast
            )
        )
        bottomNavigation.add(MeowBottomNavigation.Model(R.string.homeFragment, R.drawable.ic_cloud))
        bottomNavigation.add(
            MeowBottomNavigation.Model(
                R.string.graphFragment,
                R.drawable.ic_search
            )
        )

        bottomNavigation.show(R.string.homeFragment, true)

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val forecastFragment = ForecastFragment()

        setFragment(homeFragment, R.string.homeFragment)


        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                R.string.forecastFragment -> {
                    setFragment(forecastFragment, R.string.forecastFragment)

                }

                R.string.homeFragment -> {
                    setFragment(homeFragment, R.string.homeFragment)

                }

                R.string.graphFragment -> {
                    setFragment(searchFragment, R.string.graphFragment)
                }

                else -> Toast.makeText(this@MainActivity, "Hello", LENGTH_SHORT).show()
            }
        }


    }

    private fun setFragment(fragment: Fragment, label: Int) {
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.flFragmentContainer, fragment
            )
            commit()
            this@MainActivity.setTitle(label)
        }
    }

}