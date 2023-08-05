package com.soyaa.workbookchap9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        //웹브라우저열기
        val retrofit = Retrofit.Builder()
            .baseUrl("https://prodmp.eric-rc.shop/")
            .client(builder)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //주소입력
        val apiService = retrofit.create(ApiService::class.java)

        //입력한 주소 중에 하나로 연결 시도
        apiService.getCheckEmail("abc@abc.com").enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    val responseData = response.body()

                    if (responseData != null)
                        Log.d("Retrofit","Response\nCode: ${responseData.code} Message: ${responseData.code}")
                } else {
                    Log.w("Retrofit","Response Not Successful ${response.code()}")
                }
            }


            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("Retrofit", "Error!", t)
            }
        })
    }
}