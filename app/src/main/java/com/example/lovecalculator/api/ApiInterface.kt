package com.example.lovecalculator.api

import com.example.lovecalculator.model.CalculationResponse
import com.example.lovecalculator.model.RequestResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @GET("percentage")
    fun requestPercentage(@Header("Authorization") accessToken: RequestBody):retrofit2.Call<RequestResponse>

    @POST("response")
    fun getResponse(@Body requestBody: RequestBody):retrofit2.Call<CalculationResponse>
}