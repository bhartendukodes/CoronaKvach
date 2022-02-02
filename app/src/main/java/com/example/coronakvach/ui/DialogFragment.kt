package com.example.coronakvach.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronakvach.Adapter
import com.example.coronakvach.R
import com.example.coronakvach.databinding.FragmentDialogBinding
import com.example.coronakvach.databinding.FragmentHomeBinding
import com.example.coronakvach.model.AllCountryListItem
import com.example.coronakvach.viewmodel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogFragment(val onCountrySelect: (AllCountryListItem) ->Unit) :BottomSheetDialogFragment()
{
    private val binding by lazy {
        FragmentDialogBinding.inflate(layoutInflater)
    }

    private val homeViewModel:HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val adapterDialog by lazy {
        Adapter {
            onCountrySelect.invoke(it)
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView()
        observer()
        getDetails()
        return binding.root
    }

    private fun getDetails() {
        homeViewModel.getAllCountriesData()
    }

    private fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter=adapterDialog
    }

    private fun observer()
    {
        homeViewModel.allCountryListItem.observe(viewLifecycleOwner)
        {
            adapterDialog.submitList(it)
        }
    }
}
