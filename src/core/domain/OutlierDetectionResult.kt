package core.domain

data class OutlierDetectionResult(
    val originalData: DataSeries,
    val outlierIndices: List<Int>,
    val cleanedData: DataSeries,
    val method: String,
    val threshold: Double
) {
    val outlierCount: Int
        get() = outlierIndices.size

    val cleanRate: Double
        get() = 1.0 - (outlierCount.toDouble() / originalData.size)
}
