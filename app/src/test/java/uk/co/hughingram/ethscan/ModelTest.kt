package uk.co.hughingram.ethscan

import org.junit.Test

import org.junit.Assert.*
import uk.co.hughingram.ethscan.model.DateFormatter
import uk.co.hughingram.ethscan.model.GasCalculator

class ModelTest {

    @Test
    fun gasUsedCalculation() {
        val calculator = GasCalculator()
        assertEquals(5667393000000000, calculator.calculateGasFee(5667393, 1000000000))
        assertEquals(0, calculator.calculateGasFee(0, 1000000000))
    }

    @Test
    fun dateFormat() {
        val formatter = DateFormatter()
        assertEquals("2018-11-05 00:47:37", formatter.formatDate(1541378857))
    }
}
