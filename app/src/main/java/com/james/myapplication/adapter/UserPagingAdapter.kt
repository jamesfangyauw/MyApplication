package com.james.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.james.myapplication.databinding.ItemListUserBinding
import com.james.myapplication.model.User
import com.james.myapplication.util.Constant


class UserPagingAdapter: PagingDataAdapter<User, com.james.myapplication.adapter.UserPagingAdapter.UserViewHolder>(
    com.james.myapplication.adapter.UserPagingAdapter.Companion.DIFF_CALLBACK
) {
    class UserViewHolder(private val binding: ItemListUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .circleCrop()
                    .into(ivAvatar)
                
                tvFirstNameLastName.text = "${user.firstName} ${user.lastName}"
                tvEmail.text = user.email
    
                itemView.setOnClickListener {
                    Constant.USER_NAME = "${user.firstName} ${user.lastName}"
                    Toast.makeText(itemView.context, "Selected User: ${user.firstName}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }
    
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
    
        }
    }
    
    override fun onBindViewHolder(holder:com.james.myapplication.adapter.UserPagingAdapter.UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.james.myapplication.adapter.UserPagingAdapter.UserViewHolder {
        val view = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return com.james.myapplication.adapter.UserPagingAdapter.UserViewHolder(
            view
        )
    }
}