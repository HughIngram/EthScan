package uk.co.hughingram.ethscan.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EtherScanService {

    @GET("api?module=account&action=txlist&sort=asc&apikey=ZA36TYRUYRS3YNV7V9B5IWJFFM71AZZ26Y")
    fun listTransactions(@Query("address") address: String): Call<EtherScanTransactionList>

}

data class EtherScanTransactionList(val status: String, val message: String, val result: List<EtherScanTransaction>)

data class EtherScanTransaction(
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
    val input: String,
    val contractAddress: String,
    val cumulativeGasUsed: String,
    val txreceipt_status: String,
    val gasUsed: String,
    val confirmations: String,
    val isError: String
)