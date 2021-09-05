package com.example.marsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ret = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val services = ret.create(WeatherApis::class.java)
        services.getUserInfo((0..10).random()).enqueue(object : Callback<UserInfoData>{
            override fun onResponse(call: Call<UserInfoData>, response: Response<UserInfoData>)  {
                val Image = findViewById<ImageView>(R.id.MyImage)
                Glide.with(this@MainActivity)
                    .load(response.body()?.data?.avatar)
                    .into(Image)
                val text1 = findViewById<TextView>(R.id.first_name)
                text1.setText(response.body()?.data?.first_name)
                val text2 = findViewById<TextView>(R.id.last_name)
                text2.setText(response.body()?.data?.last_name)

            }

            override fun onFailure(call: Call<UserInfoData>, t: Throwable) {
                print ("hi")
            }

        })

    }
    data class UserInfoData(val data: UserInfo)
    data class UserInfo(val id: Double, val email: String, val first_name: String, val last_name: String, val avatar: String)



    }




