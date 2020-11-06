package jp.com.httpclient

import okhttp3.*
import java.io.IOException

/**
 * Created by gopi karmakar on 06,November,2020
 */
class HttpClient {

    fun fetch() {

        val url = "https://api.github.com/search/repositories?q=android+org:rakutentech"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute")
            }
        })
    }
}