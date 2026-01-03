package core.algorithms.outliers

import core.algorithms.normality.INormalityTest
import core.domain.DataSeries
import core.domain.OutlierDetectionResult

class AdaptiveOutlierDetector(
    private val normalityTest: INormalityTest,
    private val iqrDetector: IOutlierDetector,
    private val zscoreDetector: IOutlierDetector
) : IOutlierDetector {

    override fun detect(
        data: DataSeries,
        threshold: Double
    ): OutlierDetectionResult {
        val normalityResult = normalityTest.test(data.values)

        val selectedDetector = if (normalityResult.isNormal) {
            zscoreDetector
        } else {
            iqrDetector
        }

        val result = selectedDetector.detect(data, threshold)

        return result.copy(
            method = "${getMethodName()} -> ${selectedDetector.getMethodName()} " +
                    "(p=${String.format("%.4f", normalityResult.pValue)})"
        )
    }

    override fun getMethodName(): String = "Adaptive"

    override fun getDefaultThreshold(): Double = 1.5
}
