package com.nmp.habittracker.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nmp.habittracker.databinding.FragmentBlankBinding
import com.nmp.habittracker.databinding.HabitListItemBinding
import com.nmp.habittracker.model.Habit

class BlankListAdapter(val habitList:ArrayList<Habit>): RecyclerView.Adapter<BlankListAdapter.BlankViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BlankViewHolder {
        val binding = HabitListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return BlankViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BlankViewHolder,
        position: Int
    ) {
        holder.binding.habitTitle.text = habitList[position].name
        holder.binding.habitDescription.text = habitList[position].description
        holder.binding.habitProgressScore.text = habitList[position].progress.toString() + " / " + habitList[position].goal.toString() + habitList[position].unit
        holder.binding.progressBar.max = habitList[position].goal
        holder.binding.progressBar.progress = habitList[position].progress
        holder.binding.btnPlus.setOnClickListener {

        }
        holder.binding.btnMinus.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

    class BlankViewHolder(var binding: HabitListItemBinding): RecyclerView.ViewHolder(binding.root)
}