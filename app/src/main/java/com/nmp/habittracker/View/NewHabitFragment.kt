package com.nmp.habittracker.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nmp.habittracker.databinding.FragmentNewHabitBinding
import com.nmp.habittracker.model.Habit
import com.nmp.habittracker.model.HabitRepository

class NewHabitFragment : Fragment() {

    private lateinit var binding: FragmentNewHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewHabitBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val iconList = arrayOf(
            "water",
            "exercise",
            "book",
            "meditation"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            iconList
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerIcon.setAdapter(adapter)

        binding.btnAddHabit.setOnClickListener {

            val name = binding.txtHabitName.text.toString()
            val description = binding.txtDescription.text.toString()
            val goalText = binding.txtGoal.text.toString()
            val unit = binding.txtUnit.text.toString()
            val icon = binding.spinnerIcon.text.toString()

            if (
                name.isEmpty() ||
                description.isEmpty() ||
                goalText.isEmpty() ||
                unit.isEmpty()
            ) {

                Toast.makeText(
                    requireContext(),
                    "Semua field harus diisi",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val habit = Habit(
                name = name,
                description = description,
                goal = goalText.toInt(),
                unit = unit,
                icon = icon,
                progress = 0
            )

            HabitRepository.habitList.add(habit)

            Toast.makeText(
                requireContext(),
                "Habit berhasil ditambahkan",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().popBackStack()
        }
    }
}