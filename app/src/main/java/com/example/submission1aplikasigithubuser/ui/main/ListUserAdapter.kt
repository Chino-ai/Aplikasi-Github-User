package com.example.submission1aplikasigithubuser.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1aplikasigithubuser.data.source.remote.response.ItemsItem
import com.example.submission1aplikasigithubuser.databinding.ItemRowUserBinding
import com.example.submission1aplikasigithubuser.ui.detailUserActivity.DetailUserActivity


class ListUserAdapter(private val listUser: ArrayList<ItemsItem>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {


    class ListViewHolder(private var binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listUser: ItemsItem) {
            with(binding) {
                tvUsername.text = listUser.login
                tvName.text = listUser.login
                tvDetail.setOnClickListener {
                    val intent = Intent(itemView.context, DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.EXTRA_USER, listUser)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(listUser.avatarUrl)
                    .apply(RequestOptions().override(640, 640))
                    .into(imgAvatar)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemRowUserBinding =
            ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemRowUserBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)

    }

    override fun getItemCount(): Int = listUser.size


}