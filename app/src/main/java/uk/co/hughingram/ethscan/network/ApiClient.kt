package uk.co.hughingram.ethscan.network

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.hughingram.ethscan.model.EthereumTransaction

class ApiClient {

    fun getTransactionList(address: String): Single<List<EthereumTransaction>> = Single.fromCallable {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(EtherScanService::class.java)
        val transactionListCall = service.listTransactions(address)
        transactionListCall.execute().body()
    }.map {
        it.result
    }.subscribeOn(Schedulers.io())

}

private interface EtherScanService {

    @GET("api?module=account&action=txlist&sort=asc&apikey=$API_KEY")
    fun listTransactions(@Query("address") address: String): Call<EtherScanTransactionList>

}

private data class EtherScanTransactionList(
    val status: String,
    val message: String,
    val result: List<EthereumTransaction>
)

private const val BASE_URL = "https://api-rinkeby.etherscan.io/"
private const val API_KEY = "ZA36TYRUYRS3YNV7V9B5IWJFFM71AZZ26Y"
