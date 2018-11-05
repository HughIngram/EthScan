package uk.co.hughingram.ethscan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


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

fun EthereumTransaction.calculateGasFee(): Long =
    GasCalculator().calculateGasFee(gasUsed.toLong(), gasPrice.toLong())

fun EthereumTransaction.humanReadableTimeStamp(): String =
    DateFormatter().formatDate(timeStamp.toLong())

