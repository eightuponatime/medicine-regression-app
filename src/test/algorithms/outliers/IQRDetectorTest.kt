package algorithms.outliers

import core.algorithms.outliers.IQRDetector
import core.domain.DataSeries
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class IQRDetectorTest {
    private val detector = IQRDetector()

    @Test
    fun `detect outliers with default threshold`() {
        val data = DataSeries(
            values = listOf(10.0, 12.0, 11.0, 13.0, 100.0, 12.5, 11.5, -50.0, 13.0),
        )

        val result = detector.detect(data)

        println("method: ${result.method}")
        println("threshold: ${result.threshold}")
        println("outliers found: ${result.outlierCount}")
        println("outlier indices: ${result.outlierIndices}")
        println("clean rate: ${result.cleanRate * 100}%")

        assertTrue(
            result.outlierIndices.contains(4),
            "Should detect 100.0 as outlier"
        )
        assertTrue(
            result.outlierIndices.contains(7),
            "Should detect -50.0 as outlier"
        )
        assertEquals(
            7,
            result.cleanedData.size,
            "Should remove 2 outliers"
        )
    }
@Test
    fun `detect with strict threshold`() {
        val data = DataSeries(
            values = listOf(10.0, 12.0, 11.0, 13.0, 20.0, 12.5, 11.5, 13.0),
        )

        val result = detector.detect(data, threshold = 3.0)

        println("Strict threshold - outliers: ${result.outlierCount}")

        assertTrue(result.outlierCount <= 1)
    }

    @Test
    fun `no outliers in clean data`() {
        val data = DataSeries(
            values = listOf(10.0, 11.0, 12.0, 13.0, 14.0, 15.0),
        )

        val result = detector.detect(data)

        assertEquals(0, result.outlierCount, "Should find no outliers")
        assertEquals(data.size, result.cleanedData.size)
    }
}
