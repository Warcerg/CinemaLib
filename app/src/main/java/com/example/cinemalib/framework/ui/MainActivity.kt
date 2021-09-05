package com.example.cinemalib.framework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.cinemalib.R
import com.example.cinemalib.framework.ui.contacts.ContactsFragment
import com.example.cinemalib.framework.ui.history.HistoryFragment
import com.example.cinemalib.framework.ui.main.MainFragment
import com.example.cinemalib.framework.ui.map.MapsFragment
import com.example.cinemalib.framework.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchText = search?.actionView as SearchView
        searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity,query,Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_about -> {
                Toast.makeText(this@MainActivity,R.string.about_app_frag,Toast.LENGTH_LONG).show()
                true
            }
            R.id.menu_history -> {
                openFragment(HistoryFragment.newInstance())
                true
            }
            R.id.menu_settings -> {
                openFragment(SettingsFragment.newInstance())
                true
            }
            R.id.menu_contacts -> {
                openFragment(ContactsFragment.newInstance())
                true
            }
            R.id.menu_map -> {
                openFragment(MapsFragment.newInstance())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.apply {
            getSupportFragmentManager().findFragmentById(R.id.container)?.let {
                beginTransaction()
                        .add(R.id.container, fragment)
                        .addToBackStack("")
                        .remove(it)
                        .commitAllowingStateLoss()
            }
        }
    }
}