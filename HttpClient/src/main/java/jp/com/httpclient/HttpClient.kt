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

    public fun request(url: String, callback: ResponseCallback){

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                Log.d("Rakuten", body)
                
                val gson = GsonBuilder().create()
                val items = gson.fromJson(body, Items::class.java)
                callback.onSuccess(items)
            }

            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure("Failed to execute")
            }
        })
    }
}

class Items(val items: List<Data>)

class Data(val name: String, val privacy: Boolean, val description: String, val language: String) {

    override fun toString(): String {
        return "Name = " + name + " Privacy Status = " + privacy + " " +
                " Description = " + description + " " + " Language = " + language
    }
}

interface ResponseCallback {
    fun onSuccess(response: Items?)
    fun onFailure(msg: String)
}
