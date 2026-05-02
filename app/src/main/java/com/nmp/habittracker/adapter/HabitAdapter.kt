class HabitAdapter(private val list: MutableList<Habit>) :
    RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habit = list[position]

        holder.binding.txtName.text = habit.name
        holder.binding.txtDesc.text = habit.desc
        holder.binding.txtProgress.text = "${habit.progress} / ${habit.goal}"

        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.progress

        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.goal) {
                habit.progress++
                notifyItemChanged(position)
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            if (habit.progress > 0) {
                habit.progress--
                notifyItemChanged(position)
            }
        }
    }
}