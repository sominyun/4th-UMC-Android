package com.soyaa.workbookchap7_2_timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.soyaa.workbookchap7_2_timer.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnMain.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data", binding.edtMain.text.toString().toInt())
            startActivity(intent)
        }

    }
}