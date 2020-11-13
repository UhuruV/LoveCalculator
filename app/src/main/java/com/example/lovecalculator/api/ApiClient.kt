package com.example.lovecalculator.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val client=OkHttpClient.Builder().build()
    var retrofit= Retrofit.Builder()
        .baseUrl("https://love-calculator.p.rapidapi.com/getPercentage?")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service:Class<T>): T {
        return retrofit.create(service)
    }

}