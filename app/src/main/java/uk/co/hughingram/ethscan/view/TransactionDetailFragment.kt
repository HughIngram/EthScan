package uk.co.hughingram.ethscan.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_transaction_detail.*
import uk.co.hughingram.ethscan.R


class TransactionDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val transaction = TransactionDetailFragmentArgs.fromBundle(arguments).transaction
        timestamp.text = transaction?.timeStamp
        block_hash.text = transaction?.blockHash
        from.text = transaction?.from
        to.text = transaction?.to
        data.text = transaction?.input
    }
}
