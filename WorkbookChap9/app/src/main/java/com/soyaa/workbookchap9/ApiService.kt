package com.soyaa.workbookchap9
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/app/user/email-check")
    fun getCheckEmail(
        @Query("email") email:String
    ): Call<Response>

}