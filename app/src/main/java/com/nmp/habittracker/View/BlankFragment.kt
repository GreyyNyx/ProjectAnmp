package com.nmp.habittracker.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp.habittracker.R
import com.nmp.habittracker.ViewModel.ListViewModel
import com.nmp.habittracker.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {
    private lateinit var binding: FragmentBlankBinding
    private lateinit var viewModel: ListViewModel
    private val blankListAdapter = BlankListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = blankListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recViewHabit.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        binding.fabHabit.setOnClickListener {
            val action = BlankFragmentDirections.actionNewHabitFragment()
            it.findNavController().navigate(action)
        }
    }

    fun observeViewModel(){
        viewModel.habitsLD.observe(viewLifecycleOwner, Observer {
            blankListAdapter.updateHabitList(it)
        })

        viewModel.habitLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if (it==true){
                binding.txtError?.visibility = View.VISIBLE
            }else{
                binding.txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.progressLoad.visibility = View.VISIBLE
                binding.recViewHabit.visibility = View.GONE
            } else {
                binding.progressLoad.visibility = View.GONE
                binding.recViewHabit.visibility = View.VISIBLE
            }
        }
    }
}