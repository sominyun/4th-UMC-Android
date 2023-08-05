package com.soyaa.workbookchap7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.soyaa.workbookchap7.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    var total=0
    var started=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.buttonStart.setOnClickListener{ start() }
        binding.buttonPause.setOnClickListener{ pause() }
        binding.buttonStop.setOnClickListener{ stop() }
    }

    fun start(){
        started=true
        thread(start=true){

            while(true){
                Thread.sleep(1000)
                if(!started) break
                total=total+1
                runOnUiThread{ //화면에 호출
                    binding.textTimer.text=formatTime(total)
                }
            }
        }
    }
    fun pause(){
        started=false
    }
    fun stop(){
        started=false
        total=0
        binding.textTimer.text="00 : 00"
    }

    fun formatTime(time:Int):String{
        val minute=String.format("%02d",time/60)
        val second=String.format("%02d",time%60)
        return "$minute : $second"
    }
}