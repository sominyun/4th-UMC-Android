package com.soyaa.workbookchap6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soyaa.workbookchap6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.containerFragment.id,HomeFragment())
            .commitAllowingStateLoss()
        viewBinding.navBottom.run{
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.menu_home->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id,HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_setting->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id,SettingFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }

            selectedItemId= R.id.menu_home
        }
    }
}