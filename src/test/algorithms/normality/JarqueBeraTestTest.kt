package algorithms.normality

import core.algorithms.normality.JarqueBeraTest
import org.junit.jupiter.api.Test
import kotlin.math.*
import kotlin.random.Random
import kotlin.test.assertTrue

class JarqueBeraTestTest {

    private val test = JarqueBeraTest()

    @Test
    fun `jarque-bera test executes successfully`() {
        val data = List(100) { Random.nextDouble() * 100 }

        val result = test.test(data)

        println("Test execution:")
        println("  p-value: ${result.pValue}")
        println("  test statistic: ${result.testStatistic}")

        assertTrue(result.pValue >= 0.0 && result.pValue <= 1.0)
        assertTrue(result.testStatistic >= 0.0)
    }

    @Test
    fun `should detect non-normal distribution`() {
        val nonNormalData = listOf(
            1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0, 34.0, 55.0, 89.0,
            144.0, 233.0, 377.0, 610.0
        )

        val result = test.test(nonNormalData)

        println("Non-normal data test:")
        println("  p-value: ${result.pValue}")
        println("  is normal: ${result.isNormal}")

        assertTrue(!result.isNormal)
    }
}
