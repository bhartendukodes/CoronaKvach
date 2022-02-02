package com.example.coronakvach.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coronakvach.model.AllCountryList
import com.example.coronakvach.model.AllCountryListItem
import com.example.coronakvach.repo.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel():ViewModel() {

    val specificCountryDetails = MutableLiveData<AllCountryListItem>()
    val allCountryListItem = MutableLiveData<AllCountryList>()

    fun getSpecificCountryDetails(countryId:String)
    {
        try {
            viewModelScope.launch {
                val covidCase = Repository.getSpecificCountryDetails(countryId)
                specificCountryDetails.postValue(covidCase)
            }
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
    }

    fun getAllCountriesData()
    {
        try {
            viewModelScope.launch {
                val covidCases = Repository.getAllCountriesData()
                allCountryListItem.postValue(covidCases)
            }
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
    }
}