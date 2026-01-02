package core.domain

import java.time.LocalDateTime

data class DataSeries(
    val values: List<Double>,
    val name: String,
    val timestamps: List<LocalDateTime>? = null
) {
    val size: Int
        get() = values.size

    fun isTimeSeries(): Boolean = timestamps != null

    init {
        if (timestamps != null) {
            require(timestamps.size == values.size) {
                "${timestamps.size} != ${values.size}"
            }
        }
    }
}
