package com.example.searchmovies.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.searchmovies.R
import com.example.searchmovies.model.receivers.MainBroadcastReceiver
import com.example.searchmovies.viewmodel.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val receiver = MainBroadcastReceiver(Constants.BROADCAST_CONNECTIVITY_ACTION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        val bottomMenu: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val homeFragment = MainFragment()
        val searchFragment = SearchFragment()
        val settingsFragment = SettingsFragment()
        val contactsFragment = ContactsFragment()

        bottomMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> setCurrentFragment(homeFragment)
                R.id.menu_search -> setCurrentFragment(searchFragment)
                R.id.menu_settings -> setCurrentFragment(settingsFragment)
                R.id.menu_contacts -> setCurrentFragment(contactsFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}