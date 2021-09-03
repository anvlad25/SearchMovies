package com.example.searchmovies.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.searchmovies.databinding.FragmentSettingsBinding
import com.example.searchmovies.viewmodel.Constants

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            binding.adultCheckBox.isChecked = it.getSharedPreferences("test", Context.MODE_PRIVATE)
                .getBoolean(Constants.INCLUDE_ADULT, true)
        }

        binding.adultCheckBox.setOnClickListener {
            activity?.let {
                val preferences = it.getSharedPreferences("test", Context.MODE_PRIVATE)
                val editor = preferences.edit()
                editor.putBoolean(Constants.INCLUDE_ADULT, binding.adultCheckBox.isChecked)
                editor.apply()
            }
        }
    }
}