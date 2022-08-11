package test.ui

import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.R
import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.databinding.ActivityMainBinding
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import test.constants.switch
import test.fragments.CartFragment
import test.fragments.HomeFragment
import test.fragments.ProductFragment
import test.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var homeFragment: HomeFragment? = null
    private var cartFragment: CartFragment? = null
    private var productFragment: ProductFragment? = null
    private var profileFragment: ProfileFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColorStart()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        listeners()
    }

    private fun init() {
        if (homeFragment == null) {
            homeFragment = HomeFragment()
        }
        supportFragmentManager.switch(binding.fragmentContainer.id, homeFragment!!, "HomeFragment")
    }

    private fun listeners() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_file -> {
                    if (homeFragment == null) {
                        homeFragment = HomeFragment()
                    }
                    supportFragmentManager.switch(binding.fragmentContainer.id, homeFragment!!, "HomeFragment")
                    true
                }
                R.id.action_recent -> {
                    if (productFragment == null) {
                        productFragment = ProductFragment()
                    }
                    supportFragmentManager.switch(binding.fragmentContainer.id, productFragment!!, "RecentFragment")
                    true
                }
                R.id.action_favorite -> {
                    if (cartFragment == null) {
                        cartFragment = CartFragment()
                    }
                    supportFragmentManager.switch(binding.fragmentContainer.id, cartFragment!!, "BookmarkFragment")
                    true
                }
                else -> {
                    if (profileFragment == null) {
                        profileFragment = ProfileFragment()
                    }
                    supportFragmentManager.switch(binding.fragmentContainer.id, profileFragment!!, "DownloadFragment")
                    true
                }
            }
        }
    }


    private fun setStatusBarColorStart() {
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.navigationBarColor = ResourcesCompat.getColor(resources, R.color.black, null)
        window.statusBarColor = Color.TRANSPARENT
    }

    private fun setStatusBarColorEnd() {
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE or
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.black, null)
        window.navigationBarColor = ResourcesCompat.getColor(resources, R.color.black, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        setStatusBarColorEnd()
    }
}