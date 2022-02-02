package com.example.coronakvach.repo

import com.example.coronakvach.apiservices.ApiServices
import com.example.coronakvach.model.AllCountryList
import com.example.coronakvach.model.AllCountryListItem

object Repository {

    private val apiServices = Network.getApiServices()

    suspend fun getSpecificCountryDetails(CountryId:String):AllCountryListItem
    {
        return apiServices.getSpecificCountryDetails(CountryId)
    }

    suspend fun getAllCountriesData():AllCountryList
    {
        return apiServices.getGlobalCountryDetails()
    }
}