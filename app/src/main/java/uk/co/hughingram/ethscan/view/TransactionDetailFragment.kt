package uk.co.hughingram.ethscan.view

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_transaction_detail.*
import uk.co.hughingram.ethscan.R
import uk.co.hughingram.ethscan.model.calculateGasFee
import uk.co.hughingram.ethscan.model.humanReadableTimeStamp


class TransactionDetailFragment : BaseFragment() {

    override val showUpButtonInActionBar = true
    override val fragmentLayout: Int = R.layout.fragment_transaction_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData()
    }

    private fun showData() {
        val transaction = TransactionDetailFragmentArgs.fromBundle(arguments).transaction ?: return
        timestamp.text = transaction.humanReadableTimeStamp()
        transaction_hash.text = transaction.hash
        from.text = transaction.from
        to.text = transaction.to
        value.text = transaction.value
        data.text = transaction.input
        gas_fees.text = transaction.calculateGasFee().toString()
    }

}
