package com.example.lovecalculator.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.lovecalculator.R
import com.example.lovecalculator.api.ApiClient
import com.example.lovecalculator.api.ApiInterface
import com.example.lovecalculator.model.RequestResponse
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_result)

        btCalc.setOnClickListener {
            val firstname = etFname.text.toString()
            val secondname = etSname.text.toString()

            val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("fname", firstname)
                .addFormDataPart("sname", secondname)
                .build()
        }
    }


        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("fname", firstName)
            .addFormDataPart("sname", secondname)
            .build()
}

private fun registerStudents(requestBody: RequestBody){
    val apiClient= ApiClient.buildService(ApiInterface::class.java)
    val registrationCall=apiClient.requestPercentage(requestBody)
    registrationCall.enqueue(object : Callback<RequestResponse> {
        override fun onFailure(call: Call<RequestResponse>, t: Throwable) {
            Toast.makeText(this,t.message, Toast.LENGTH_LONG).show()
        }

        override fun onResponse(
            call: Call<RequestResponse>,
            response: Response<RequestResponse>
        ) {
            if (response.isSuccessful){
                val message="Request Successful"
                Toast.makeText(this,response.body()?.firstName, Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))


                var i = progress_circular!!.progress
                Thread(Runnable {
                    while (i < 100) {
                        i += 5
                        try {
                            Thread.sleep(100)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                    }
                }).start()

            }
            else{
                Toast.makeText(this,response.errorBody().toString(), Toast.LENGTH_LONG).show()
            }
        }
    })
}
}