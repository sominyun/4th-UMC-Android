package com.soyaa.workbookchap6_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.soyaa.workbookchap6_2.databinding.ActivityMainBinding

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
            "One",
            "Two",
        )

        TabLayoutMediator(viewBinding.tabMain,viewBinding.vpMain){ tab,position->
            tab.text=tabTittleArray[position]
        }.attach()
    }
}