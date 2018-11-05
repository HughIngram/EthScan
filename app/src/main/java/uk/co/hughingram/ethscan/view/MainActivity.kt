package uk.co.hughingram.ethscan.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import uk.co.hughingram.ethscan.R
import uk.co.hughingram.ethscan.network.EthereumTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.my_nav_host_fragment).navigateUp()

    var cache: List<EthereumTransaction> = listOf()

}
