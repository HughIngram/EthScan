package uk.co.hughingram.ethscan.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.hughingram.ethscan.model.GasCalculator

interface EtherScanService {

    @GET("api?module=account&action=txlist&sort=asc&apikey=$API_KEY")
    fun listTransactions(@Query("address") address: String): Call<EtherScanTransactionList>

}

private const val API_KEY = "ZA36TYRUYRS3YNV7V9B5IWJFFM71AZZ26Y"

data class EtherScanTransactionList(val status: String, val message: String, val result: List<EthereumTransaction>)

fun EthereumTransaction.calculateGasFee(): Long =
    GasCalculator().calculateGasFee(gasUsed.toLong(), gasPrice.toLong())

@Parcelize
data class EthereumTransaction(
    val blockNumber: String,
    val blockHash: String,
    val timeStamp: String,
    val hash: String,
    val nonce: String,
    val transactionIndex: String,
    val from: String,
    val to: String,
    val value: String,
    val gas: String,
    val gasPrice: String,
    val input: String,
    val contractAddress: String,
    val cumulativeGasUsed: String,
    val txreceipt_status: String,
    val gasUsed: String,
    val confirmations: String,
    val isError: String
) : Parcelable
