package uk.co.hughingram.ethscan.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
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
import uk.co.hughingram.ethscan.model.EthereumTransaction
import uk.co.hughingram.ethscan.network.ApiClient

class TransactionListFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSwipeListener()
        val cache = (activity as MainActivity).cache
        if (cache.isEmpty()) {
            downloadTransactions()
        } else {
            initList(cache)
        }
    }

    private fun setUpSwipeListener() {
        swipe_container.setOnRefreshListener { downloadTransactions() }
        swipe_container.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(context!!, R.color.light_blue)
        )
    }

    private fun downloadTransactions() {
        val apiClient = ApiClient()
        apiClient.getTransactionList(ETH_ADDRESS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                swipe_container.isRefreshing = true
            }
            .doFinally {
                swipe_container.isRefreshing = false
            }
            .subscribeBy(
                onSuccess = { transactionList ->
                    initList(transactionList)
                    (activity as MainActivity).cache = transactionList
                },
                onError = { e ->
                    Log.e("TransactionList", "error", e)
                }
            )
    }

    private fun initList(transactions: List<EthereumTransaction>) {
        val adapter = TransactionAdapter(transactions.sortedByDescending { it.nonce.toInt() })
        transaction_adapter.layoutManager = LinearLayoutManager(context)
        transaction_adapter.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

}

private const val ETH_ADDRESS = "0xfFfa5813ED9a5DB4880D7303DB7d0cBe41bC771F"
