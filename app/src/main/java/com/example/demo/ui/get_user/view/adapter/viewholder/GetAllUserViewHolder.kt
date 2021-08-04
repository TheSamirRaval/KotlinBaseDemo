package com.example.demo.ui.get_user.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.demo.common.room.model.User
import com.example.demo.databinding.ItemUserListBinding

class GetAllUserViewHolder(
        val itemBinding: ItemUserListBinding,
        val chatItemClick: ((Int) -> Unit)? = null
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(user: User) {
        itemBinding.userNameTV.text = user.name
        itemBinding.emailTV.text = user.email
        itemBinding.placeTV.text = user.place

        itemView.setOnClickListener {
            chatItemClick?.invoke(adapterPosition)
        }

    }
}