package com.example.submission1aplikasigithubuser.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.submission1aplikasigithubuser.R
import com.example.submission1aplikasigithubuser.data.source.remote.response.ItemsItem
import com.example.submission1aplikasigithubuser.databinding.ActivityMainBinding
import com.example.submission1aplikasigithubuser.databinding.ActivitySettingsBinding
import com.example.submission1aplikasigithubuser.ui.settings.SettingPreferences
import com.example.submission1aplikasigithubuser.ui.settings.SettingViewModel
import com.example.submission1aplikasigithubuser.ui.settings.SettingsActivity
import com.example.submission1aplikasigithubuser.ui.favourite.FavouriteActivity
import com.example.submission1aplikasigithubuser.viewModel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var settingsBinding: ActivitySettingsBinding
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mainViewModel.noData.observe(this, {
            activityMainBinding.noData.text = it
        })
        activityMainBinding.rvGithub.setHasFixedSize(true)

        mainViewModel.userGithub.observe(this, {
            setUserData(it)

        })
        mainViewModel.isLoading.observe(this, {
            showLoading(it)

        })

        mainViewModel.toast.observe(this, {
            it.getContentIfNotHandled()?.let {
                showToast(it)
            }
        })
        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        settingViewModel.getThemeSetting().observe(this,{
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                settingsBinding.checkBox.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                settingsBinding.checkBox.isChecked = false
            }
        })

        settingsBinding.checkBox.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }

        showRecycleView()

    }

    private fun setUserData(userData: List<ItemsItem>) {
        val listReview = ArrayList<ItemsItem>()
        for (review in userData) {
            listReview.add(review)
        }
        val adapter = ListUserAdapter(listReview)
        activityMainBinding.rvGithub.adapter = adapter


    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            activityMainBinding.progressBar.visibility = View.VISIBLE
        } else {
            activityMainBinding.progressBar.visibility = View.GONE
        }
    }

    private fun showToast(isToast: Boolean) {
        val caution = "No Internet Connection"
        if (isToast) {

            Toast.makeText(this@MainActivity, caution, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showRecycleView() {
        activityMainBinding.rvGithub.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.getUserGithub(query)
                searchView.clearFocus()
                return true

            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
           R.id.settings-> {
               val intent = Intent(this@MainActivity, SettingsActivity::class.java)
               startActivity(intent)
           }
            R.id.favourite->{
                val intent = Intent(this@MainActivity, FavouriteActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }


}