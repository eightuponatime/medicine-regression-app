package core.domain

data class NormalityTestResult(
    val isNormal: Boolean,
    val pValue: Double,
    val testStatistic: Double,
    val testName: String,
    val alpha: Double = 0.05
) {
    val confidenceLevel: Double get() = 1.0 - alpha
}
