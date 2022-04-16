package com.example.submission1aplikasigithubuser.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.submission1aplikasigithubuser.database.UserGithub

class NoteDiffCallback(
    private val mOldUserList: List<UserGithub>,
    private val mNewUserList: List<UserGithub>
): DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return mOldUserList.size
    }

    override fun getNewListSize(): Int {
        return mNewUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserList[oldItemPosition].id == mNewUserList[newItemPosition].id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldUserList[oldItemPosition]
        val newEmployee = mNewUserList[newItemPosition]
        return oldEmployee.login == newEmployee.login && oldEmployee.name == newEmployee.name
    }
}