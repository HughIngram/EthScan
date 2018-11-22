package uk.co.hughingram.ethscan

import io.reactivex.Single
import uk.co.hughingram.ethscan.model.EthereumTransaction
import uk.co.hughingram.ethscan.network.ApiClient
import java.util.*

class MockApiClient : ApiClient {

    override fun getTransactionList(address: String): Single<List<EthereumTransaction>> =
        Single.fromCallable {
            listOf(
                generateRandomTransaction(),
                generateRandomTransaction(),
                generateRandomTransaction()
            )
        }

    private fun generateRandomTransaction() = EthereumTransaction(
        timeStamp = "1542886198",
        blockHash = UUID.randomUUID().toString(),
        blockNumber = UUID.randomUUID().toString(),
        hash = UUID.randomUUID().toString(),
        nonce = Random().nextInt().toString(),
        transactionIndex = UUID.randomUUID().toString(),
        from = UUID.randomUUID().toString(),
        to = UUID.randomUUID().toString(),
        value = UUID.randomUUID().toString(),
        gas = "123",
        gasPrice = "2",
        gasUsed = "3",
        input = UUID.randomUUID().toString(),
        contractAddress = UUID.randomUUID().toString(),
        cumulativeGasUsed = UUID.randomUUID().toString(),
        txreceipt_status = UUID.randomUUID().toString(),
        confirmations = UUID.randomUUID().toString(),
        isError = UUID.randomUUID().toString()
    )
}