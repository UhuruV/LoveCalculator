package com.example.lovecalculator.model

import com.google.gson.annotations.SerializedName

data class CalculationResponse (
    @SerializedName("percentage")val percentage:Int,
    @SerializedName("result")val result:String,
    @SerializedName("access_token")var accessToken:String
)