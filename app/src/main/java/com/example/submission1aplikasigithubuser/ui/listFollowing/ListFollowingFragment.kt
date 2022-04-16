package com.example.submission1aplikasigithubuser.ui.listFollowing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1aplikasigithubuser.ui.detailUserActivity.DetailUserActivity.Companion.name
import com.example.submission1aplikasigithubuser.data.source.remote.response.FollowingResponseItem
import com.example.submission1aplikasigithubuser.databinding.FragmentListFollowingBinding


class ListFollowingFragment : Fragment() {
    private var _listFollowingBinding: FragmentListFollowingBinding? = null
    private val listFollowingBinding get() = _listFollowingBinding as FragmentListFollowingBinding


    private lateinit var listFollowingViewModel: ListFollowingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _listFollowingBinding = FragmentListFollowingBinding.inflate(layoutInflater)
        return listFollowingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFollowingViewModel = ViewModelProvider(this)[ListFollowingViewModel::class.java]

        listFollowingViewModel.noData.observe(viewLifecycleOwner) {
            listFollowingBinding.noData.text = it
        }

        listFollowingViewModel.userFollowing.observe(viewLifecycleOwner) {
            setUserData(it)
        }

        listFollowingViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        listFollowingViewModel.toast.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                showToast(it)
            }
        }

        listFollowingViewModel.getUserFollowingGithub(name)
        showRecycleView()

    }


    private fun setUserData(userData: List<FollowingResponseItem>) {
        val listReview = ArrayList<FollowingResponseItem>()
        for (review in userData) {
            listReview.add(review)
        }
        val adapter = ListFollowingAdapter(listReview)
        listFollowingBinding.rv.adapter = adapter


    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            listFollowingBinding.progressBar.visibility = View.VISIBLE
        } else {
            listFollowingBinding.progressBar.visibility = View.GONE
        }
    }

    private fun showToast(isToast: Boolean) {
        val caution = "No Internet Connection"
        if (isToast) {

            Toast.makeText(context, caution, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showRecycleView() {
        listFollowingBinding.rv.layoutManager = LinearLayoutManager(context)
    }


}