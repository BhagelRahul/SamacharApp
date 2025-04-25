package com.example.samacharapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samacharapp.Adapters.NewsAdapter
import com.example.samacharapp.databinding.ActivityMainBinding
import com.example.samacharapp.fragments.AboutFragment
import com.example.samacharapp.fragments.Contactfragment
import com.example.samacharapp.fragments.Loginfragment
import com.example.samacharapp.mydata.MyNewsdata
import com.example.samacharapp.mydata.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                binding.newsrecyclerView.recyclerview2.visibility = View.VISIBLE
            }
        }
        // Setup Toolbar
        setSupportActionBar(binding.newsrecyclerView.toolbar)

        // Drawer Toggle
        toggle = ActionBarDrawerToggle(
            this,
            binding.darwerlayout,
            binding.newsrecyclerView.toolbar,
            R.string.OpenNavigation,
            R.string.CloseNavigation
        )

        binding.darwerlayout.addDrawerListener(toggle)
        toggle.syncState()

        // Cancel Drawer Button
        val headerView = binding.navigationview.getHeaderView(0)
        val cancelButton: ImageView =
            headerView.findViewById(R.id.cancelDrawer)
        cancelButton.setOnClickListener {
            binding.darwerlayout.closeDrawer(GravityCompat.START)
        }

        // Navigation Drawer Items
        binding.navigationview.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.about -> replaceFragment(AboutFragment())
                R.id.profile -> replaceFragment(Contactfragment())
                R.id.share -> shareApp()     ///share app
            }
            binding.darwerlayout.closeDrawer(GravityCompat.START)
            true
        }

        // FAB Logout Button
        binding.fabbutton.setOnClickListener {
            logout()
        }

        // Setup RecyclerView
        newsAdapter = NewsAdapter()
        binding.newsrecyclerView.recyclerview2.layoutManager = LinearLayoutManager(this)
        binding.newsrecyclerView.recyclerview2.adapter = newsAdapter

//
        val prefs = getSharedPreferences("news_settings", MODE_PRIVATE)
        val lang = prefs.getString("news_lang", "en") ?: "en"
        fetchTopHeadlines()


        // Load News
        //fetchTopHeadlines()


    }

    private fun fetchTopHeadlines() {
        RetrofitInstance.apiService.getTopHeadlines("en", "923285118d68488bb294f702736565d5")
            .enqueue(object : Callback<MyNewsdata> {
                override fun onResponse(call: Call<MyNewsdata>, response: Response<MyNewsdata>) {
                    if (response.isSuccessful) {
                        val articles = response.body()?.articles ?: emptyList()
                        Log.d(
                            "MainActivity",
                            "Fetched articles: ${articles.size}"
                        ) // Log the size of the articles list
                        newsAdapter.submitList(articles)
                        binding.newsrecyclerView.recyclerview2.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, "News loaded", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Error: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<MyNewsdata>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failure: ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }
            })
    }


    ///on floating button
    private fun logout() {
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        // Redirect to LoginActivity
        val intent = Intent(this, Loginfragment::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    //replace fragment
    private fun replaceFragment(fragment: Fragment) {
        binding.newsrecyclerView.recyclerview2.visibility = GONE
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentlay, fragment)
            .addToBackStack(null)
            .commit()
    }

    //share app
    private fun shareApp() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out Samachar App!")
            putExtra(
                Intent.EXTRA_TEXT,
                "Hey! Check out this awesome news app:\nhttps://play.google.com/store/apps/details?id=com.yourapp.package"
            )
        }

        startActivity(Intent.createChooser(shareIntent, "Share Samachar App via"))
    }

    //language change
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settingmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("LANG_CLICKED", "Clicked item id: ${item.itemId}")

        return when (item.itemId) {
            R.id.english -> {
                setNewsLanguage("en")  // English language, US as the country
                true
            }

            R.id.germany -> {
                setNewsLanguage("fr")  // Hindi language, India as the country
                true
            }

            R.id.french -> {
                setNewsLanguage("en")  // French language, France as the country
                true
            }

            R.id.about -> {
                replaceFragment(AboutFragment())
                true
            }

            R.id.profile -> {
                replaceFragment(Contactfragment())
                true
            }

            R.id.share -> {
                shareApp()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setNewsLanguage(langCode: String) {
        val prefs = getSharedPreferences("news_settings", MODE_PRIVATE)
        prefs.edit().putString("news_lang", langCode).apply()
        fetchTopHeadlines()
    }



}