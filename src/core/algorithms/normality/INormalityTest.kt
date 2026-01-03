package core.algorithms.normality

import core.domain.NormalityTestResult

interface INormalityTest {
    fun test(data: List<Double>, alpha: Double = 0.05): NormalityTestResult

    fun getTestName(): String
}
