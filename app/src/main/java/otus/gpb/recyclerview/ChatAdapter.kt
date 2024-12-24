package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatAdapter(private val chatItems: MutableList<ChatItem>) :
    RecyclerView.Adapter<ChatItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val view = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.bind(chatItems[position])
    }

    override fun getItemCount(): Int = chatItems.size

    fun removeItem(position: Int) {
        chatItems.removeAt(position)
        notifyItemRemoved(position)
    }
}

class ChatItemViewHolder(private val binding: ChatItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChatItem) {
        binding.textViewName.text = item.name
        binding.textViewMessage.text = item.message
        binding.textViewDate.text = item.date
        binding.imageViewItemAvatar.setImageResource(R.drawable.baseline_contact_phone_24)
    }
}
