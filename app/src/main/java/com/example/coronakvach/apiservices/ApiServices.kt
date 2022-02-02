package com.example.coronakvach.apiservices

import com.example.coronakvach.model.AllCountryList
import com.example.coronakvach.model.AllCountryListItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("countries/{countryId}")
    suspend fun getSpecificCountryDetails(
        @Path("countryId") countryId:String
    ):AllCountryListItem

    @GET("countries")
    suspend fun getGlobalCountryDetails():AllCountryList
}