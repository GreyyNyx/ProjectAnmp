package com.nmp.habittracker.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.nmp.habittracker.R
import com.nmp.habittracker.ViewModel.DetailHabitViewModel
import com.nmp.habittracker.databinding.FragmentNewHabitBinding
import com.nmp.habittracker.model.Habit

class EditHabitFragment : Fragment(), View.OnClickListener {
    private lateinit var viewModel: DetailHabitViewModel
    private lateinit var binding: FragmentNewHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHabitBinding.inflate(inflater,container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailHabitViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.textView.text = "Edit Habit"
        binding.btnAddHabit.text = "Save Changes"
        binding.btnAddHabit.setOnClickListener(this)
        val uuid = EditHabitFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.habitLD.observe(viewLifecycleOwner, Observer {
            binding.habit = it
        })
    }

    override fun onClick(v: View) {
        val obj = binding.habit as Habit
        obj.goal = binding.txtGoal.text.toString().toIntOrNull() ?: obj.goal
        viewModel.updateHabit(obj)
        Toast.makeText(v.context, "Habit Updated", Toast.LENGTH_SHORT).show()
        v.findNavController().popBackStack()

    }
}