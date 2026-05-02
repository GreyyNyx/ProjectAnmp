private lateinit var adapter: HabitAdapter

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    adapter = HabitAdapter(HabitRepository.habitList)

    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    binding.recyclerView.adapter = adapter

    // FAB ke create habit
    binding.fab.setOnClickListener {
        val action = DashboardFragmentDirections.actionDashboardToCreate()
        Navigation.findNavController(it).navigate(action)
    }
}