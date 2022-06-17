package com.example.dota2wiki.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL


class ApiService {

    private val client: OkHttpClient = OkHttpClient()

    fun getRequest(sUrl: String): String? {
        var result: String? = null
        try {
            val url = URL(sUrl)
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            result = response.body?.string()
        } catch (err: Error) {
            print("Error when executing get request: " + err.localizedMessage)
        }
        return result
    }
}
