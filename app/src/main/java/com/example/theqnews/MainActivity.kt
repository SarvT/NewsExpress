package com.example.theqnews

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), itemClicked {

    private lateinit var mAdapter: MAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetcher()
        mAdapter = MAdapter(this)
        recyclerView.adapter = mAdapter



    }
//    private fun fetcher(): ArrayList<String> {
//        val list = ArrayList<String>()
//        for (i in 0 until 15){
//            list.add("item no. $i")
//        }
//        return list
//    }

    override fun clicked(item: Source) {
        Toast.makeText(this, "You clicked the item $item", Toast.LENGTH_SHORT).show()
//        val builder = CustomTabsIntent.Builder()
//        val customTabsIntent = builder.build()
//        customTabsIntent.launchUrl(this, Uri.parse(item.newsUrl))
    }

    private fun fetcher(){
//        loadingBar.visibility = View.VISIBLE
//            val textView = findViewById<TextView>(R.id.text)
// ...

// Instantiate the RequestQueue.
//        val queue = Volley.newRequestQueue(this)
        val url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=3212fb5d74dc43009ca1cb717557acff"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                // Display the first 500 characters of the response string.
//                    textView.text = "Response is: ${response.substring(0, 500)}"
//                newsUrl = response.getString("url")
                val jsonArray = it.getJSONArray("articles")
                val sourceArray = ArrayList<Source>()
                for (i in 0 until jsonArray.length()){
                    val jsonObject = jsonArray.getJSONObject(i)
                    val source = Source(
                        jsonObject.getString("title"),
                        jsonObject.getString("author"),
                        jsonObject.getString("url"),
                        jsonObject.getString("urlToImage")
                    )
                    sourceArray.add(source)
                }
                mAdapter.updater(sourceArray)
            },
            { Toast.makeText(this, "Please try again!", Toast.LENGTH_SHORT).show() })
//                Response.ErrorListener { textView.text = "That didn't work!" })

// Add the request to the RequestQueue.
//        queue.add(jsonObjectRequest)
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

//    private fun share(){
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.type = "text/plain"
//        intent.putExtra(Intent.EXTRA_TEXT, "HA Ha HA! Check this out funny meme : $newsUrl")
//        val chooser = Intent.createChooser(intent, "Choose a way to share")
//        startActivity(chooser)
//    }
}