package uk.co.hughingram.ethscan.network

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    fun getTransactionList(address: String): Single<List<EtherScanTransaction>> = Single.fromCallable {
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

private const val BASE_URL = "https://api-rinkeby.etherscan.io/"