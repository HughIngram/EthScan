package uk.co.hughingram.ethscan.model

class GasCalculator {

    fun calculateGasFee(gasUsed: Long, gasPrice: Long): Long = gasUsed * gasPrice

}