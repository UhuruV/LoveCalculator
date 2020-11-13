package com.example.lovecalculator.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lovecalculator.R
import com.example.lovecalculator.api.ApiClient
import com.example.lovecalculator.api.ApiInterface
import com.example.lovecalculator.model.CalculationResponse
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun calculationResponse(requestBody: RequestBody){
        val apiClient= ApiClient.buildService(ApiInterface::class.java)
        val loginCall=apiClient.getResponse(requestBody)
        loginCall.enqueue(object : Callback<CalculationResponse> {
            override fun onFailure(call: Call<CalculationResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()


            }

            override fun onResponse(call: Call<CalculationResponse>, response: Response<CalculationResponse>) {
                if (response.isSuccessful){
                    Toast.makeText(baseContext,response.body()?.result, Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext, MainActivity::class.java))
                    val accessToken=response.body()?.accessToken
                    val sharedPreferences=
                        PreferenceManager.getDefaultSharedPreferences(baseContext)

                    val editor=sharedPreferences.edit()
                    editor.putString("ACCESS_TOKEN_KEY"," ")
                    editor.apply()

                    val intent= Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(baseContext,response.errorBody().toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    }
