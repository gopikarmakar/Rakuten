package jp.com.httpclient

import android.content.ClipData
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

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
                Log.d("Rakuten", items.toString())
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d("Rakuten","Failed to execute")
            }
        })
    }
}

private class Items(val items: List<Data>) {

    override fun toString(): String {
        var message: String = ""
        items.forEach {
            message = "Name = " + it.name + "\n" + " Privacy Status = " + it.privacy + "\n" +
                    " Description = " + it.description + "\n" + " Language = " + it.language
        }
        return message
    }
}

private class Data(val name: String, val privacy: Boolean, val description: String, val language: String)