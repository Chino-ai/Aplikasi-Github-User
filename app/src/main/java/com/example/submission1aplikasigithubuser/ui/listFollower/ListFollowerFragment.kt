package com.example.submission1aplikasigithubuser.ui.listFollower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1aplikasigithubuser.databinding.FragmentListFollowerBinding
import com.example.submission1aplikasigithubuser.ui.detailUserActivity.DetailUserActivity.Companion.name
import com.example.submission1aplikasigithubuser.data.source.remote.response.FollowerResponseItem


class ListFollowerFragment : Fragment() {
    private var _listFollowerBinding: FragmentListFollowerBinding? = null
    private val listFollowerBinding get() = _listFollowerBinding as FragmentListFollowerBinding
    private lateinit var listFollowerViewModel: ListFollowerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _listFollowerBinding = FragmentListFollowerBinding.inflate(layoutInflater)
        return listFollowerBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFollowerViewModel = ViewModelProvider(this)[ListFollowerViewModel::class.java]

        listFollowerViewModel.noData.observe(viewLifecycleOwner, {
            listFollowerBinding.noData.text = it
        })

        listFollowerViewModel.userFollower.observe(viewLifecycleOwner, {
            setUserData(it)
        })

        listFollowerViewModel.isLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })

        listFollowerViewModel.toast.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                showToast(it)
            }
        })

        listFollowerViewModel.getUserFollowerGithub(name)
        showRecycleView()
    }

    private fun setUserData(userData: List<FollowerResponseItem>) {
        val listReview = ArrayList<FollowerResponseItem>()
        for (review in userData) {
            listReview.add(review)
        }
        val adapter = ListFollowerAdapter(listReview)
        listFollowerBinding.rv.adapter = adapter


    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            listFollowerBinding.progressBar.visibility = View.VISIBLE
        } else {
            listFollowerBinding.progressBar.visibility = View.GONE
        }
    }

    private fun showToast(isToast: Boolean) {
        val caution = "No Internet Connection"
        if (isToast) {

            Toast.makeText(context, caution, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showRecycleView() {
        listFollowerBinding.rv.layoutManager = LinearLayoutManager(context)
    }


}