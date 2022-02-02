package com.example.coronakvach

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.coronakvach.databinding.CountryListLayoutBinding
import com.example.coronakvach.model.AllCountryList
import com.example.coronakvach.model.AllCountryListItem
import java.text.NumberFormat

class Adapter(val onCountrySelect:(AllCountryListItem) -> Unit):ListAdapter<AllCountryListItem, Adapter.MyViewHolder>(CovidDiffUtil) {

    inner class MyViewHolder(val binding: CountryListLayoutBinding):RecyclerView.ViewHolder(binding.root)

    object CovidDiffUtil:DiffUtil.ItemCallback<AllCountryListItem>()
    {
        override fun areItemsTheSame(
            oldItem: AllCountryListItem,
            newItem: AllCountryListItem
        ): Boolean {
            return oldItem.countryInfo._id == newItem.countryInfo._id
        }

        override fun areContentsTheSame(
            oldItem: AllCountryListItem,
            newItem: AllCountryListItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CountryListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val countries = getItem(position)
        holder.binding.apply {
            tvCountryName.text = countries.country
            tvCases.text = NumberFormat.getInstance().format(countries.cases)
            tvSerialNo.text=(position+1).toString()
            ivFlagImg.load(countries.countryInfo.flag)

            root.setOnClickListener {
                onCountrySelect.invoke(countries)
            }
        }
    }
}