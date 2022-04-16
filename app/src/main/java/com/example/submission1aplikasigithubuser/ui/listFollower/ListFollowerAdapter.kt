package com.example.submission1aplikasigithubuser.ui.listFollower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1aplikasigithubuser.databinding.ItemRowFollowerBinding
import com.example.submission1aplikasigithubuser.data.source.remote.response.FollowerResponseItem


class ListFollowerAdapter(private val listUser: ArrayList<FollowerResponseItem>) :
    RecyclerView.Adapter<ListFollowerAdapter.ListViewHolder>() {


    class ListViewHolder(private var binding: ItemRowFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listUser: FollowerResponseItem) {
            with(binding) {
                tvUsername.text = listUser.login
                tvName.text = listUser.login
                Glide.with(itemView.context)
                    .load(listUser.avatarUrl)
                    .apply(RequestOptions().override(640, 640))
                    .into(imgAvatar)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemRowFollowerBinding =
            ItemRowFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemRowFollowerBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)

    }

    override fun getItemCount(): Int = listUser.size


}