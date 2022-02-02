package com.example.coronakvach.repo

import com.example.coronakvach.apiservices.ApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private const val BASE_URL="https://corona.lmao.ninja/v2/"

    fun getApiServices():ApiServices
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
}