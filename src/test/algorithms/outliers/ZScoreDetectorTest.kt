package algorithms.outliers

import core.algorithms.outliers.ZScoreDetector
import core.domain.DataSeries
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ZScoreDetectorTest {

    private val detector = ZScoreDetector()

    @Test
    fun `detect outliers with default threshold`() {
        val data = DataSeries(
            values = listOf(
                50.0, 51.0, 49.0, 50.5, 48.5, 51.5, 49.5, 50.0,
                50.2, 49.8, 50.1, 49.9, 50.3, 49.7,
                200.0
            )
        )

        val result = detector.detect(data)

        println("Method: ${result.method}")
        println("Threshold: ${result.threshold}")
        println("Outliers: ${result.outlierCount}")
        println("Outlier indices: ${result.outlierIndices}")

        assertTrue(result.outlierIndices.contains(14), "Should detect 200.0 as outlier")
    }

    @Test
    fun `zscore is not robust to outliers`() {
        val data = DataSeries(
            values = listOf(10.0, 11.0, 12.0, 13.0, 100.0, 12.0, 11.0, 13.0)
        )

        val result = detector.detect(data)

        println("Non-robust example:")
        println("  Outliers: ${result.outlierCount}")
        println("  Note: 100.0 may not be detected due to non-robustness")
    }
}
