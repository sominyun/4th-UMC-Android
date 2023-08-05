package com.soyaa.workbookchap8_login

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, "8d398db5a4f959e00a1da1ea84b0ed94")
    }
}