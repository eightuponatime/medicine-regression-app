package core.algorithms.normality

import core.domain.NormalityTestResult
import org.apache.commons.math3.stat.descriptive.moment.Kurtosis
import org.apache.commons.math3.stat.descriptive.moment.Skewness
import org.apache.commons.math3.distribution.ChiSquaredDistribution
import kotlin.math.pow

class JarqueBeraTest : INormalityTest {

    override fun test(data: List<Double>, alpha: Double): NormalityTestResult {
        require(data.size >= 8) {
            "Jarque-Bera test requires at least 8 data points, got ${data.size}"
        }
        require(alpha in 0.0..1.0) {
            "Alpha must be between 0 and 1, got $alpha"
        }

        val values = data.toDoubleArray()
        val n = values.size.toDouble()

        val skewnessCalc = Skewness()
        val kurtosisCalc = Kurtosis()

        val skewness = skewnessCalc.evaluate(values)
        val kurtosis = kurtosisCalc.evaluate(values)

        val jb = (n / 6.0) * (skewness.pow(2) + ((kurtosis - 3.0).pow(2) / 4.0))

        val chiSquared = ChiSquaredDistribution(2.0)
        val pValue = 1.0 - chiSquared.cumulativeProbability(jb)

        return NormalityTestResult(
            isNormal = pValue > alpha,
            pValue = pValue,
            testStatistic = jb,
            testName = getTestName(),
            alpha = alpha
        )
    }

    override fun getTestName(): String = "Jarque-Bera"
}
