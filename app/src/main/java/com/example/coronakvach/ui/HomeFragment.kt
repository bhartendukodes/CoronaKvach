package com.example.coronakvach.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coronakvach.R
import com.example.coronakvach.databinding.FragmentHomeBinding
import com.example.coronakvach.model.AllCountryListItem
import com.example.coronakvach.viewmodel.HomeViewModel
import org.eazegraph.lib.models.PieModel
import org.ocpsoft.prettytime.PrettyTime
import java.text.NumberFormat
import java.util.*


class HomeFragment :Fragment(R.layout.fragment_home)
{

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val homeViewModel:HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observer()
        onClick()
        getDetails()
        return binding.root
    }

    private fun onClick() {
        binding.countryLocation.setOnClickListener {
            DialogFragment{
                homeViewModel.getSpecificCountryDetails(it.country)
            }.show(childFragmentManager,"c")
        }
    }

    private fun getDetails() {
        homeViewModel.getSpecificCountryDetails("India")
    }

    private fun observer() {
        homeViewModel.specificCountryDetails.observe(viewLifecycleOwner)
        {
            initUi(it)
        }
    }

    private fun initUi(it: AllCountryListItem?) {
        binding.apply {
            if (it!=null)
            {
                countryLocation.text=it.country

                cases.text = NumberFormat.getInstance().format(it.cases)
                todayCases.text = NumberFormat.getInstance().format(it.todayCases)
                activeCases.text = NumberFormat.getInstance().format(it.active)
                totalRecover.text = NumberFormat.getInstance().format(it.recovered)
                todayRecover.text = NumberFormat.getInstance().format(it.todayRecovered)
                deaths.text = NumberFormat.getInstance().format(it.deaths)
                todayDeaths.text = NumberFormat.getInstance().format(it.todayDeaths)
                test.text = NumberFormat.getInstance().format(it.tests)
                tvSeriousCase.text = NumberFormat.getInstance().format(it.critical)

                binding.piechart.addPieSlice(it.cases?.let { it1 ->
                    PieModel(
                        "ConfirmCases",
                        it1.toFloat(),
                        ContextCompat.getColor(requireContext(), R.color.yellow_pie)
                    )
                })
                binding.piechart.addPieSlice(it.active?.let { it1 ->
                    PieModel(
                        "Active",
                        it1.toFloat(), ContextCompat.getColor(requireContext(), R.color.blue_pie)
                    )
                })
                binding.piechart.addPieSlice(it.recovered?.let { it1 ->
                    PieModel(
                        "Recovered",
                        it1.toFloat(), ContextCompat.getColor(requireContext(), R.color.green_pie)
                    )
                })
                binding.piechart.addPieSlice(it.deaths?.let { it1 ->
                    PieModel(
                        "Deaths",
                        it1.toFloat(), ContextCompat.getColor(requireContext(), R.color.red_pie)
                    )
                })
                binding.piechart.startAnimation();
                setUpdateDate(it.updated)
            }
        }
    }

    private fun setUpdateDate(date: Long) {
        val prettyTime = PrettyTime()
        val timeMoment = prettyTime.format(date?.let {
            Date(it+1000*60*10)
        })
        binding.tvUpdate.text = "Update " + timeMoment
    }

}
