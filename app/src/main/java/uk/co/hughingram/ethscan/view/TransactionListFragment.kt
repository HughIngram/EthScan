package uk.co.hughingram.ethscan.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_transaction_list.*
import uk.co.hughingram.ethscan.R
import uk.co.hughingram.ethscan.network.ApiClient
import uk.co.hughingram.ethscan.network.EthereumTransaction

class TransactionListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun initList(transactions: List<EthereumTransaction>) {
        val adapter = TransactionAdapter(transactions)
        transaction_adapter.layoutManager = LinearLayoutManager(context)
        transaction_adapter.adapter = adapter
    }

}
