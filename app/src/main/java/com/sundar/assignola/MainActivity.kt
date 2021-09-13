package com.sundar.assignola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.JsonObject
import com.sundar.assignola.adapter.DataAdapter
import com.sundar.assignola.classes.ApiServices
import com.sundar.assignola.classes.AppBaseActivity
import com.sundar.assignola.classes.HelperClass
import com.sundar.assignola.interfaces.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class MainActivity : AppBaseActivity() {

    private val TAG = "MainActivityTAG"
    private val activity = this@MainActivity
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        swiperefreshlayout.setOnRefreshListener {
            Handler().postDelayed(Runnable {
                swipeRefreshLayout.isRefreshing = false
            }, 4000)
            home()
        }

        home()
    }

    private fun showContentView() {
        content_view.visibility = View.VISIBLE
        progress_loader.visibility = View.GONE
    }

    private fun showLoader() {
        content_view.visibility = View.GONE
        progress_loader.visibility = View.VISIBLE
    }


    private fun home(
        // int: page_count
    ) {

        // Log.d("sundarcheck", "page_count list: " + page_count)
        if (HelperClass.getNetworkInfo(activity)) {
            val apiInterface = ApiServices.apiService(activity).create(ApiInterface::class.java)
            val call = apiInterface.getHome(0)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                    } else if (response.code() == HttpURLConnection.HTTP_OK) {
                        showContentView()

                        //if response in directly show object
                        // val body = JSONObject(response.body().toString())
                        //if response in directly show arraylist
                        val body = JSONArray(response.body().toString())

                        data_recyclerview.adapter = DataAdapter(activity, body)

                    }

                    Log.d(TAG, "Resopnse of home api: " + response.body().toString())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    //   Handler().postDelayed(Runnable { enableOrientation() }, 1000)
                    // HelperClass.hideLoader()
                    HelperClass.snackbar(
                        activity,
                        main_layout,
                        getString(R.string.something_went_wrong)
                    )
                }

            })


        } else {
            //no network


            retry_layout.visibility = View.VISIBLE

            //Button click to again api hit
            btn_retry.setOnClickListener {
            }


            /* HelperClass.snackbar(*/
            /*     activity,*/
            /*     main_layout,*/
            /*     getString(R.string.check_your_internet_connection)*/
            /* )*/

        }

    }

}
