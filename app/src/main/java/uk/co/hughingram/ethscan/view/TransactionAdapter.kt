package uk.co.hughingram.ethscan.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.item_transaction.view.*
import uk.co.hughingram.ethscan.R
import uk.co.hughingram.ethscan.network.EthereumTransaction

class TransactionAdapter(private val transactions: List<EthereumTransaction>) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.timeStamp.text = transaction.timeStamp
        holder.setListener(
            Navigation.createNavigateOnClickListener(R.id.action_transactionListFragment_to_transactionDetailFragment)
        )
    }

    override fun getItemCount(): Int = transactions.size
}

class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val timeStamp: TextView = itemView.timestamp

    fun setListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
    }

}
