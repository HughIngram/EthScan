package uk.co.hughingram.ethscan.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.hughingram.ethscan.R
import uk.co.hughingram.ethscan.network.ApiClient
import uk.co.hughingram.ethscan.network.EthereumTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val apiClient = ApiClient()
        apiClient.getTransactionList("0xfFfa5813ED9a5DB4880D7303DB7d0cBe41bC771F")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess= {
                    initList(it)
                    Log.i("api-result", it.toString())
                },
                onError = {

                }
            )
    }

    private fun initList(transactions: List<EthereumTransaction>) {
        val adapter = TransactionAdapter(transactions)
        transaction_adapter.layoutManager = LinearLayoutManager(this.baseContext)
        transaction_adapter.adapter = adapter
    }
}
