package jp.com.httpclient

import okhttp3.*
import java.io.IOException
import com.google.gson.GsonBuilder

/**
 * Created by gopi karmakar on 06,November,2020
 */
class HttpClient {

    public fun request(url: String){

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()

                val gson = GsonBuilder().create()
                val items = gson.fromJson(body, Items::class.java)
                print(items)
            }

            override fun onFailure(call: Call, e: IOException) {
                print("Failed to execute")
            }
        })
    }
}

private class Items(val items: List<Data>)

private class Data(val name: String, val privacy: Boolean, val description: String, val language: String)