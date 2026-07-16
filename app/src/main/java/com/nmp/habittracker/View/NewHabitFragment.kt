package com.nmp.habittracker.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nmp.habittracker.Util.FileHelper
import com.nmp.habittracker.ViewModel.ListViewModel
import com.nmp.habittracker.databinding.FragmentNewHabitBinding
import com.nmp.habittracker.model.Habit

class NewHabitFragment : Fragment() {

    private lateinit var binding: FragmentNewHabitBinding
    private lateinit var viewModel: ListViewModel

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
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        binding.habit = Habit(
            name = "",
            description = "",
            goal = 0,
            unit = "",
            icon = "",
            progress = 0
        )

        val iconNameList = arrayOf(
            "Drink Water",
            "Exercise",
            "Book",
            "Yoga",
            "Sleeping",
            "Study",
            "Wake Up",
            "Checklist",
            "Salary"
        )

        val iconFileList = arrayOf(
            "glass_of_water",
            "exercise",
            "book",
            "yoga",
            "sleeping",
            "study",
            "wake_up",
            "check_list",
            "salary"
        )


        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            iconNameList
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
            val selectedName = binding.spinnerIcon.text.toString()
            val selectedIndex = iconNameList.indexOf(selectedName)
            val icon = iconFileList[selectedIndex]

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

            viewModel.addHabit(habit)

            Toast.makeText(
                requireContext(),
                "Habit berhasil ditambahkan",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().popBackStack()
        }
    }
}