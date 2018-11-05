package uk.co.hughingram.ethscan.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import androidx.navigation.findNavController
import uk.co.hughingram.ethscan.R
import uk.co.hughingram.ethscan.model.EthereumTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customiseActionBar()
    }

    private fun customiseActionBar() {
        val actionBar = supportActionBar!!
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        val v = LayoutInflater.from(this).inflate(R.layout.title, null)
        actionBar.customView = v
    }

    override fun onSupportNavigateUp() = findNavController(R.id.my_nav_host_fragment).navigateUp()

    var cache: List<EthereumTransaction> = listOf()

}
