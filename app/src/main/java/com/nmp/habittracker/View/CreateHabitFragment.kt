binding.btnCreate.setOnClickListener {

    val name = binding.txtName.text.toString()
    val desc = binding.txtDesc.text.toString()
    val goalText = binding.txtGoal.text.toString()

    if (name.isEmpty() || desc.isEmpty() || goalText.isEmpty()) {
        Toast.makeText(requireContext(), "Semua field harus diisi", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
    }

    val goal = goalText.toInt()

    val habit = Habit(name, desc, goal, 0, "icon")

    HabitRepository.habitList.add(habit)

    Toast.makeText(requireContext(), "Habit ditambahkan", Toast.LENGTH_SHORT).show()

    Navigation.findNavController(it).popBackStack()
}