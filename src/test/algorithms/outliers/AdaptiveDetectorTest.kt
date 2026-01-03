package algorithms.outliers

import core.algorithms.normality.JarqueBeraTest
import core.algorithms.outliers.AdaptiveOutlierDetector
import core.algorithms.outliers.IQRDetector
import core.algorithms.outliers.ZScoreDetector
import core.domain.DataSeries
import org.junit.jupiter.api.Test

class AdaptiveDetectorTest {

    private val detector = AdaptiveOutlierDetector(
        normalityTest = JarqueBeraTest(),
        iqrDetector = IQRDetector(),
        zscoreDetector = ZScoreDetector()
    )

    @Test
    fun `adaptive detection on normal data`() {
        val normalData = DataSeries(
            values = listOf(10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 100.0)
        )

        val result = detector.detect(normalData)

        println("=== Adaptive Detection (Normal Data) ===")
        println("Method: ${result.method}")
        println("Outliers: ${result.outlierCount}")
        println("Indices: ${result.outlierIndices}")
    }

    @Test
    fun `adaptive detection on skewed data`() {
        val skewedData = DataSeries(
            values = listOf(1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 200.0)
        )

        val result = detector.detect(skewedData)

        println("=== Adaptive Detection (Skewed Data) ===")
        println("Method: ${result.method}")
        println("Outliers: ${result.outlierCount}")
        println("Indices: ${result.outlierIndices}")
    }
}
