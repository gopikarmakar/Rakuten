package jp.com.httpclient

import android.content.ClipData
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException

/**
 * Created by gopi karmakar on 06,November,2020
 */

/**
 * Single instance Class
 */
object HttpClient {

    const val TAG: String = "Rakuten"

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    /**
     * Main http request method
     */
    fun request(url: String, callback: ResponseCallback) {

        scope.async {
            fetch(url, callback)
        }
    }

    /**
     * Method to cancel the ongoing couroutine job.
     */
    fun cancel() {
        scope.cancel()
    }

    /**
     * Making network communications and parsing JSON
     */
    private suspend fun fetch(url: String, callback: ResponseCallback) {

        withContext(Dispatchers.IO){

            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()

            client.newCall(request).enqueue(object: Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response?.body()?.string()
                    Log.d(TAG, body)

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
}

/**
 * List of response parameters
 */
class Items(
    val items: List<Data>
)

/**
 * Response Parameters
 */
class Data(
    val name: String,
    val privacy: Boolean,
    val description: String,
    val language: String
) {
    override fun toString(): String {
        return "Name = " + name + " Privacy Status = " + privacy + " " +
                " Description = " + description + " " + " Language = " + language
    }
}

/**
 * Response callback interface
 */
interface ResponseCallback {
    fun onSuccess(response: Items?)
    fun onFailure(msg: String)
}
