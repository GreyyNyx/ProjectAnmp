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
        val habit = habitList[position]
        holder.binding.habitTitle.text = habit.name
        holder.binding.habitDescription.text = habit.description
        holder.binding.habitProgressScore.text = "${habit.progress} / ${habit.goal} ${habit.unit}"
        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.progress

        holder.binding.btnMinus.isEnabled = habit.progress > 0
        holder.binding.btnPlus.isEnabled = habit.progress < habit.goal

        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.goal) {
                habit.progress++

                holder.binding.progressBar.progress = habit.progress
                holder.binding.habitProgressScore.text = "${habit.progress} / ${habit.goal} ${habit.unit}"

                // update state
                holder.binding.btnMinus.isEnabled = true
                holder.binding.btnPlus.isEnabled = habit.progress < habit.goal
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            if (habit.progress > 0) {
                habit.progress--

                holder.binding.progressBar.progress = habit.progress
                holder.binding.habitProgressScore.text =
                    "${habit.progress} / ${habit.goal} ${habit.unit}"

                // update state
                holder.binding.btnPlus.isEnabled = true
                holder.binding.btnMinus.isEnabled = habit.progress > 0
            }
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