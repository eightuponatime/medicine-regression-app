package data;

import org.junit.jupiter.api.Test
import shared.data.Metrics
import shared.data.MimicCsvRepository
import kotlin.test.assertTrue

class ReadMimicTest {
    @Test
    fun `just read and print heart rate`() {
        val repository = MimicCsvRepository()

        val heartRate = repository.loadTimeSeries(
            patientId = 10005817,
            metric = Metrics.HEART_RATE.name
        )

        println("size: ${heartRate.values.size}")
        println("first 5 values:")
        heartRate.values.take(5).forEachIndexed { index, value ->
            val timestamp = heartRate.timestamps?.get(index)
            println("\t[$timestamp] value #$index -> $value")
        }

        assertTrue(heartRate.size > 0, "shouldn't be empty")
        assertTrue(heartRate.isTimeSeries(), "it's time series")
    }
}
