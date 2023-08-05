package com.soyaa.workbookchap6_2_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.soyaa.workbookchap6_2_2.R
import com.soyaa.workbookchap6_2_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val mainVPAdapter=MainVPAdapter(this)
        viewBinding.vpMain.adapter=mainVPAdapter

        val tabTittleArray= arrayOf(
            "1",
            "2",
            "3"
        )

        TabLayoutMediator(viewBinding.tabMain,viewBinding.vpMain){ tab,position->
            tab.text=tabTittleArray[position]
        }.attach()

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
                    R.id.menu_airplane->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id,AirplaneFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_heart->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id,HeartFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_setting->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, SettingFragment())
                            .commitAllowingStateLoss()
                    }

                }
                true
            }


            selectedItemId= R.id.menu_home

        }
    }
}