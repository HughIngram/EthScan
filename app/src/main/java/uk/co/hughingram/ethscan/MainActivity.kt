package uk.co.hughingram.ethscan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.rxkotlin.subscribeBy
import uk.co.hughingram.ethscan.network.ApiClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val apiClient = ApiClient()
        apiClient.getTransactionList("0xfFfa5813ED9a5DB4880D7303DB7d0cBe41bC771F")
            .subscribeBy (
                onSuccess= {
                    Log.i("api-result", it.toString())
                },
                onError = {

                }
            )
    }
}
