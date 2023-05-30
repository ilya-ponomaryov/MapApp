package com.example.mapapp.common.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mapapp.R
import com.example.mapapp.common.coordinate.ToolbarMenuClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navigation: NavController
        get() =
            Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        return when (item.itemId) {
            R.id.save_coordinates -> {
                val mapFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
                if (mapFragment is ToolbarMenuClickListener) {
                    mapFragment.onToolbarMenuItemClicked(item)
                }
                true
            }
            R.id.markers_list -> {
                navigation.navigate(R.id.action_mapFragment_to_markersFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}