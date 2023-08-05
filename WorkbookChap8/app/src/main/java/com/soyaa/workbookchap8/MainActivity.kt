package com.soyaa.workbookchap8

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.soyaa.workbookchap8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* val sharedPrefs=getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val editor=sharedPrefs.edit()

        //초기화용
        editor.putString("eric","")
        editor.apply()
        //

        editor.putString("eric","android")
        val beforeapplyvalue=sharedPrefs.getString("eric","")
        Log.d("SP Before","${beforeapplyvalue}")
        editor.apply()

        val spvalue=sharedPrefs.getString("eric","")
        Log.d("SP","${spvalue}")
        */

        val roomDb=AppDatabase.getInstance(this)

        if(roomDb!=null){
            val user=User("에릭",23)

            roomDb.userDao().updateNameByUserId(1,"루나")

            val deleteUser=User("",0,1)
            roomDb.userDao().delete(deleteUser)

            val user=roomDb.userDao().selectByUserId(2)
            Log.d("DB","User Id 2 : $user")

            roomDb.userDao().insert(user)
            val userList=roomDb.userDao().selectAll()
            Log.d("DB","User List : ${userList}")
        }
    }
}
