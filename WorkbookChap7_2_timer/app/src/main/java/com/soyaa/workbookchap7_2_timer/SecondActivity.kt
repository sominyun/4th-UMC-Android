package com.soyaa.workbookchap7_2_timer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.soyaa.workbookchap7_2_timer.databinding.ActivityMainBinding
import com.soyaa.workbookchap7_2_timer.databinding.ActivitySecondBinding
import kotlin.concurrent.thread

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding

    private var total=0

    var started = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val total1=intent.getIntExtra("data",0)
        total=total1

        binding.textTimer.text=formatTime(total1)
        binding.buttonStart.setOnClickListener { start() }
        binding.buttonPause.setOnClickListener { pause() }
        binding.buttonStop.setOnClickListener { stop() }
    }

    fun start() {

        started = true
        thread(start = true) {

            while (true) {
                Thread.sleep(1000)
                if (!started) break
                total = total - 1
                runOnUiThread {//화면에 호출
                    binding.textTimer.text = formatTime(total)
                }
                if(total==0){
                    val total1=intent.getIntExtra("data",0)
                    runOnUiThread{
                        Toast.makeText(this, "$total1 초 끝났어용~~", Toast.LENGTH_SHORT).show()
                    }
                    break
                }
            }
        }
    }

    fun pause() {
        started = false
    }

    fun stop() {
        started = false
        total = 0
        binding.textTimer.text = "00 : 00"
    }

    fun formatTime(time: Int): String {
        val minute = String.format("%02d", time / 60)
        val second = String.format("%02d", time % 60)
        return "$minute : $second"
    }
}




