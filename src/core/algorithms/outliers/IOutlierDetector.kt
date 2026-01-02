package core.algorithms.outliers

import core.domain.DataSeries
import core.domain.OutlierDetectionResult

interface IOutlierDetector {
    fun detect(
        data: DataSeries,
        threshold: Double = getDefaultThreshold()
    ): OutlierDetectionResult

    fun getMethodName(): String

    fun getDefaultThreshold(): Double
}
