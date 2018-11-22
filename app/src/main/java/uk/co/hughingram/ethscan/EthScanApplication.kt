package uk.co.hughingram.ethscan

import android.app.Application
import uk.co.hughingram.ethscan.network.ApiClient
import uk.co.hughingram.ethscan.network.ApiClientImpl
import uk.co.hughingram.ethscan.network.ApiClientProvider

class EthScanApplication : Application(), ApiClientProvider {

    override var apiClient = ApiClientImpl() as ApiClient

}
