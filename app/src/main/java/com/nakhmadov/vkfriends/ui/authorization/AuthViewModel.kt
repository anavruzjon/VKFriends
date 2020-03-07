package com.nakhmadov.vkfriends.ui.authorization

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nakhmadov.vkfriends.ui.authorization.AuthFragment.Companion.ACCESS_TOKEN

class AuthViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {

    companion object {
        const val RESPONSE_REDIRECT_URL = "https://oauth.vk.com/blank.html#access_token="
        const val EXPIRES_IN = "&expires_in="
    }

    private val _doAuthorization = MutableLiveData<Boolean>()
    val doAuthorization: LiveData<Boolean> = _doAuthorization

    val webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(v: WebView?, r: WebResourceRequest?): Boolean {
            val url = r?.url.toString()
            if (url.startsWith(RESPONSE_REDIRECT_URL)) {
                val accessToken = url.substringAfter(RESPONSE_REDIRECT_URL).substringBefore(EXPIRES_IN)
                val editor = sharedPreferences.edit()
                editor.putString(ACCESS_TOKEN, accessToken)
                editor.apply()
                doneAuth()
                return true
            }
            return false
        }
    }

    fun vkAuth() {
        _doAuthorization.value = true
    }

    fun doneAuth() {
        _doAuthorization.value = false
    }
}
