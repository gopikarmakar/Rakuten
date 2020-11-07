package jp.com.rakuten

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import jp.com.httpclient.HttpClient
import jp.com.httpclient.Items
import jp.com.httpclient.ResponseCallback

/**
 * MainActivity accessing HttpClient module.
 */
class MainActivity : AppCompatActivity(), ResponseCallback {

    companion object {
        const val TAG: String = "Rakuten"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val url = "https://api.github.com/search/repositories?q=android+org:rakutentech"
        HttpClient.request(url, this)
    }

    override fun onFailure(msg: String) {
        Log.d(TAG, msg)
    }

    override fun onSuccess(response: Items?) {
        response?.run {
            this?.items?.forEach {

                val msg = "Name = " + it.name + " Privacy Status = " + it.privacy + " " +
                        " Description = " + it.description + " " + " Language = " + it.language

                //Log.d(TAG, msg)
                Log.d(TAG, it.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
