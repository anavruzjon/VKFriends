package com.nakhmadov.vkfriends.ui.authorization

import android.annotation.SuppressLint
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
import androidx.navigation.fragment.findNavController
import com.nakhmadov.vkfriends.BuildConfig

import com.nakhmadov.vkfriends.R
import com.nakhmadov.vkfriends.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    companion object {
        const val APP_PREFERENCES_NAME = "preferences"
        const val ACCESS_TOKEN = "access_token"
    }

    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPreferences = (activity as AppCompatActivity).getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE)

        val viewModelFactory = AuthViewModelFactory(sharedPreferences)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initWebView()

        viewModel.doAuthorization.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.authButton.visibility = View.GONE
                    binding.webView.visibility = View.VISIBLE
                } else {
                    binding.webView.visibility = View.GONE
                    findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToFriendsFragment())
                }
            }
        })
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView() {
        binding.webView.webViewClient = viewModel.webViewClient
        binding.webView.settings.javaScriptEnabled = true
        val redirectUrl = getString(R.string.redirect_url)
        val appId: String = BuildConfig.APP_ID
        val loadUrl = getString(R.string.auth_url, appId, redirectUrl)
        binding.webView.loadUrl(loadUrl)
    }
}
