package uk.co.hughingram.ethscan.network

import io.reactivex.Single
import uk.co.hughingram.ethscan.model.EthereumTransaction

interface ApiClient {
    fun getTransactionList(address: String): Single<List<EthereumTransaction>>
}