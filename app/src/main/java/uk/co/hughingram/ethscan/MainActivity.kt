package uk.co.hughingram.ethscan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.hughingram.ethscan.network.EtherScanService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Single.fromCallable {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api-rinkeby.etherscan.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(EtherScanService::class.java)
            val transactionListCall = service.listTransactions("0xfFfa5813ED9a5DB4880D7303DB7d0cBe41bC771F")
            transactionListCall.execute().body().toString()
        }.subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    Log.i("request-result", it)
                },
                onError = {
                    Log.e("request-result", "", it)
                }
            )
    }
}
