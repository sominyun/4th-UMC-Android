package com.soyaa.workbookchap10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.content.Intent
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import com.soyaa.workbookchap10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var googleApiClient: GoogleApiClient? = null
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Google 로그인 설정
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this) { connectionResult ->
                // Google 로그인 연결 실패 시 처리를 원하는 경우 여기에 코드를 추가하세요.
            }
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
        findViewById<ImageButton>(R.id.login_google).setOnClickListener{
            signIn()
        }
        findViewById<Button>(R.id.logout_google).setOnClickListener{
            signOut()
        }
    }

    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient!!)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient!!).setResultCallback { status ->
            if (status.isSuccess) {
                // 로그아웃 성공
            } else {
                // 로그아웃 실패
                // 실패 처리를 원하는 경우 여기에 코드를 추가하세요.
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
            val account = result?.signInAccount
            if (result!!.isSuccess && account != null) {
                val name = account.displayName
                // 로그인 성공
                if (name != null) {
                    showToast("Welcome, $name!")
                }
            } else {
                // 로그인 실패
                // 실패 처리를 원하는 경우 여기에 코드를 추가하세요.
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
