package com.example.submission1aplikasigithubuser.ui.favourite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1aplikasigithubuser.database.UserGithub
import com.example.submission1aplikasigithubuser.databinding.ItemUsersBinding
import com.example.submission1aplikasigithubuser.helper.NoteDiffCallback
import com.example.submission1aplikasigithubuser.helper.ViewModelFactory
import com.example.submission1aplikasigithubuser.ui.detailUserActivity.UserAddViewModel

class FavouriteListAdapter(private val appCompatActivity: AppCompatActivity) : RecyclerView.Adapter<FavouriteListAdapter.UserViewHolder>() {
    private lateinit var userAddViewModel : UserAddViewModel

    private val listUsers = ArrayList<UserGithub>()
    fun setListNotes(listUsers: List<UserGithub>) {
        val diffCallback = NoteDiffCallback(this.listUsers, listUsers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUsers.clear()
        this.listUsers.addAll(listUsers)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size

    inner class UserViewHolder(private val binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userGithub: UserGithub) {
            with(binding) {
                username.text = userGithub.login
                name.text = userGithub.name
                Glide.with(itemView.context)
                    .load(userGithub.avatar)
                    .apply(RequestOptions().override(640, 640))
                    .into(imgAvatar)

                if (listUsers.size == 0){

                }

                tvDelete.setOnClickListener {
                     userAddViewModel = obtainViewModel(appCompatActivity)
                    AlertDialog.Builder(it.context)
                        .setTitle("Hapus")
                        .setMessage("Apakah anda ingin menghapus dari daftar favorit?")
                        .setPositiveButton("Ya") { _, i ->
                            userAddViewModel.delete(userGithub)
                            Toast.makeText(it.context, "Berhasil di hapus", Toast.LENGTH_LONG)
                                .show()
                        }
                        .setNegativeButton("No") { _, i ->
                            Toast.makeText(it.context, "Batal di hapus", Toast.LENGTH_LONG).show()
                        }
                        .show()
                }

                tvDetail.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFavouriteActivity::class.java)
                    intent.putExtra(DetailFavouriteActivity.EXTRA_USER_FAVOURITE,userGithub)
                    itemView.context.startActivity(intent)
                }
            }
            }
        }

    private fun obtainViewModel(activity: AppCompatActivity): UserAddViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UserAddViewModel::class.java)
    }
    }
