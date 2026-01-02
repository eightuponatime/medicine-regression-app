package core.algorithms.outliers

import core.domain.DataSeries
import core.domain.OutlierDetectionResult
import org.apache.commons.math3.stat.descriptive.rank.Percentile

class IQRDetector : IOutlierDetector {
    override fun detect(
        data: DataSeries,
        threshold: Double): OutlierDetectionResult {

        require(data.size >= 4) {
            "iqr requires at least 4 data points, got only ${data.size}"
        }
        require(threshold > 0) {
            "threshold must be positive, got ${threshold} <= 0"
        }

        val values = data.values.toDoubleArray()
        val percentile = Percentile()

        val q1 = percentile.evaluate(values, 25.0)
        val q3 = percentile.evaluate(values, 75.0)
        val iqr = q3 - q1

        val lowerBound = q1 - threshold * iqr
        val upperBound = q3 + threshold * iqr

        val outlierIndices = data.values.mapIndexedNotNull { index, value ->
            if (value < lowerBound || value > upperBound) index else null
        }

        val cleanedValues = data.values.filterIndexed { index, _ ->
            index !in outlierIndices
        }

        val cleanedTimestamps = data.timestamps?.filterIndexed { index, _ ->
            index !in outlierIndices
        }

        val cleanedData = DataSeries(
            values = cleanedValues,
            timestamps = cleanedTimestamps
        )

        return OutlierDetectionResult(
            originalData = data,
            outlierIndices = outlierIndices,
            cleanedData = cleanedData,
            method = getMethodName(),
            threshold = threshold
        )
    }

    override fun getMethodName(): String = "IQR"

    override fun getDefaultThreshold(): Double = 1.5
}
