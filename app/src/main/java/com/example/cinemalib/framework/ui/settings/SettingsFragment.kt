package com.example.cinemalib.framework.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemalib.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!
    private var isAdultFilter: Boolean = false


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initSwitch()
    }

    private fun initData() {
        activity?.let {
            isAdultFilter = it.getPreferences(Context.MODE_PRIVATE).getBoolean(ADULTFILTER, false)
        }
        with(binding){
            adultSwitch.isChecked = isAdultFilter
        }
    }

    private fun initSwitch()  {
        with(binding){
            adultSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    isAdultFilter = !isAdultFilter
                    safeDataToDisk()
                } else {
                    isAdultFilter = !isAdultFilter
                    safeDataToDisk()
                }
            }
        }
    }

    private fun safeDataToDisk() {
        activity?.let {
            val preferences = it.getPreferences(Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putBoolean(ADULTFILTER, isAdultFilter)
            editor.apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val ADULTFILTER = "ADULT_FILTER"

        fun newInstance() = SettingsFragment()
    }
}