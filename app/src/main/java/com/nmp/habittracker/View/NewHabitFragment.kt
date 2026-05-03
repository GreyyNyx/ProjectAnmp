package com.nmp.habittracker.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nmp.habittracker.R
import com.nmp.habittracker.databinding.FragmentBlankBinding
import com.nmp.habittracker.databinding.FragmentNewHabitBinding

class NewHabitFragment : Fragment() {
    private lateinit var binding: FragmentNewHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHabitBinding.inflate(inflater,container,false)
        return binding.root
    }
}