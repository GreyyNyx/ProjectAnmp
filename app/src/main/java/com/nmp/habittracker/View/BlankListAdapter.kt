package com.nmp.habittracker.View

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.nmp.habittracker.R
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
        val iconRes = getHabitIcon(habit.name)

        holder.binding.habitTitle.text = habit.name
        holder.binding.habitDescription.text = habit.description
        holder.binding.habitProgressScore.text = "${habit.progress} / ${habit.goal} ${habit.unit}"
        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.progress
        holder.binding.habitIcon.setImageResource(iconRes)


        holder.binding.btnMinus.isEnabled = habit.progress > 0
        holder.binding.btnPlus.isEnabled = habit.progress < habit.goal
        holder.binding.habitProgressIcon.isVisible = habit.progress == habit.goal
        holder.binding.viewProgress.isVisible = habit.progress == habit.goal
        updateHabitProgress(holder, habit.progress,habit.goal)

        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.goal) {
                habit.progress++

                holder.binding.progressBar.progress = habit.progress
                holder.binding.habitProgressScore.text = "${habit.progress} / ${habit.goal} ${habit.unit}"

                // update state
                holder.binding.btnMinus.isEnabled = true
                holder.binding.btnPlus.isEnabled = habit.progress < habit.goal
                holder.binding.habitProgressIcon.isVisible = habit.progress == habit.goal
                holder.binding.viewProgress.isVisible = habit.progress == habit.goal
                updateHabitProgress(holder, habit.progress,habit.goal)
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            if (habit.progress > 0) {
                habit.progress--

                holder.binding.progressBar.progress = habit.progress
                holder.binding.habitProgressScore.text = "${habit.progress} / ${habit.goal} ${habit.unit}"

                // update state
                holder.binding.btnPlus.isEnabled = true
                holder.binding.btnMinus.isEnabled = habit.progress > 0
                holder.binding.habitProgressIcon.isVisible = habit.progress == habit.goal
                holder.binding.viewProgress.isVisible = habit.progress == habit.goal
                updateHabitProgress(holder, habit.progress,habit.goal)
            }
        }
    }

    fun getHabitIcon(name: String): Int {
        return when (name.lowercase()) {
            "drink water" -> R.drawable.glass_of_water
            "exercise" -> R.drawable.exercise
            "read books" -> R.drawable.book
            "meditation" -> R.drawable.yoga
            "sleep" -> R.drawable.sleeping
            "study" -> R.drawable.study
            "wake up" -> R.drawable.wake_up
            else -> R.drawable.habit
        }
    }

    fun updateHabitProgress(
        holder: BlankListAdapter.BlankViewHolder,
        progress: Int,
        goal: Int
    ) {
        val chip = holder.binding.habitProgress

        if (progress == goal) {
            chip.text = "Completed"
            chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#3CB103"))
            chip.setTextColor(Color.WHITE)
        } else {
            chip.text = "In Progress"
            chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#DBD5D5"))
            chip.setTextColor(Color.BLACK)
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