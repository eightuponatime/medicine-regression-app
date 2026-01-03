package core.algorithms.outliers

import core.domain.DataSeries
import core.domain.OutlierDetectionResult
import org.apache.commons.math3.stat.descriptive.moment.Mean
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation
import kotlin.math.abs

class ZScoreDetector : IOutlierDetector {

    override fun detect(
        data: DataSeries,
        threshold: Double
    ): OutlierDetectionResult {
        require(data.size >= 3) {
            "Z-Score requires at least 3 data points, got ${data.size}"
        }
        require(threshold > 0) {
            "Threshold must be positive, got $threshold"
        }

        val values = data.values.toDoubleArray()

        val mean = Mean().evaluate(values)
        val std = StandardDeviation().evaluate(values)

        require(std > 0.0) {
            "Standard deviation is zero - all values are identical"
        }

        val outlierIndices = data.values.mapIndexedNotNull { index, value ->
            val zScore = abs((value - mean) / std)
            if (zScore > threshold) index else null
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

    override fun getMethodName(): String = "Z-Score"

    override fun getDefaultThreshold(): Double = 3.0
}
