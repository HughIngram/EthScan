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
        val cache = (activity as MainActivity).cache
        if (cache.isEmpty()) {
            downloadTransactions()
        } else {
            initList(cache)
        }
    }
    private fun downloadTransactions() {
        val apiClient = ApiClient()
        apiClient.getTransactionList(ETH_ADDRESS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    initList(it)
                    (activity as MainActivity).cache = it
                },
                onError = { Log.e("TransactionList", "error", it) }
            )
    }

    private fun initList(transactions: List<EthereumTransaction>) {
        val adapter = TransactionAdapter(transactions.sortedByDescending { it.nonce.toInt() })
        transaction_adapter.layoutManager = LinearLayoutManager(context)
        transaction_adapter.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

}

private const val ETH_ADDRESS = "0xfFfa5813ED9a5DB4880D7303DB7d0cBe41bC771F"
