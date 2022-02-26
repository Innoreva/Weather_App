package com.ersubhadip.instantweather.ui

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.ersubhadip.instantweather.R
import com.etebarian.meowbottomnavigation.MeowBottomNavigation


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //asking user to give permission
        permissionRequest()

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
                R.string.searchFragment,
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

                R.string.searchFragment -> {
                    setFragment(searchFragment, R.string.searchFragment)
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

    //Checking permission granted or not
    fun hasCoarseLocationPermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    fun hasFineLocationPermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    fun permissionRequest() {
        var permissions = mutableListOf<String>()

        if (!hasCoarseLocationPermission()) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasFineLocationPermission()) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {

                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle("Warning!")
                    dialog.setMessage("Are you sure won't give permission?")
                    dialog.setPositiveButton("Give Permissions") { dialogInterface, which ->
                        //Goto Settings
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)

                        Toast.makeText(
                            this,
                            "Give location permissions to Instant Weather",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    dialog.setNegativeButton("Exit") { dialogInterface, which ->
                        //close app by force
                        finish()
                    }
                    dialog.setCancelable(false)
                    val showD = dialog.create()
                    showD.show()
                } else {
                    Log.d("PERMISSION", "Granted")
                    setFragment(HomeFragment(), R.string.homeFragment)
                }
            }
        }
    }
}