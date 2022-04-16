package com.example.submission1aplikasigithubuser.ui.listFollowing


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1aplikasigithubuser.databinding.ItemRowFollowingBinding
import com.example.submission1aplikasigithubuser.data.source.remote.response.FollowingResponseItem


class ListFollowingAdapter(private val listUser: ArrayList<FollowingResponseItem>) :
    RecyclerView.Adapter<ListFollowingAdapter.ListViewHolder>() {


    class ListViewHolder(private var binding: ItemRowFollowingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listUser: FollowingResponseItem) {
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
        val itemRowFollowingBinding =
            ItemRowFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemRowFollowingBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)

    }

    override fun getItemCount(): Int = listUser.size


}