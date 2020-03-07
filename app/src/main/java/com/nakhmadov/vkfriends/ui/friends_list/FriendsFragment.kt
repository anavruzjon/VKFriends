package com.nakhmadov.vkfriends.ui.friends_list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.nakhmadov.vkfriends.R
import com.nakhmadov.vkfriends.adapters.friends_list.FriendsListAdapter
import com.nakhmadov.vkfriends.data.remote.ApiService
import com.nakhmadov.vkfriends.data.repository.FriendsRepository
import com.nakhmadov.vkfriends.databinding.FragmentFriendsBinding
import com.nakhmadov.vkfriends.ui.authorization.AuthFragment.Companion.APP_PREFERENCES_NAME

class FriendsFragment : Fragment() {


    private lateinit var viewModel: FriendsViewModel
    private lateinit var binding: FragmentFriendsBinding
    private lateinit var adapter: FriendsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val network = ApiService.retrofitApi
        val sharedPrefs = (activity as AppCompatActivity).getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE)

        val repository = FriendsRepository.getRepository(network, sharedPrefs)
        val viewModelFactory = FriendsViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(FriendsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.userFriends.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.profileInfo.observe(viewLifecycleOwner, Observer {
            val name = it.firstName + " " + it.lastName
            (activity as AppCompatActivity).supportActionBar?.title = name
        })

        setupRecycler()
    }

    private fun setupRecycler() {
        binding.friendsListRecycler.setHasFixedSize(true)
        binding.friendsListRecycler.layoutManager = LinearLayoutManager(context)
        adapter = FriendsListAdapter()
        binding.friendsListRecycler.adapter = adapter

    }
}
