package shared.data

import core.domain.DataSeries
import core.repositories.IDataRepository
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MimicCsvRepository(
    private val charteventsPath: String = "data/mimic-iv/chartevents.csv"
) : IDataRepository {

    private val metricMapping: Map<String, Int> = mapOf(
        Metrics.HEART_RATE.name to 220045,
        Metrics.BLOOD_PRESSURE_SYSTOLIC.name to 220050,
        Metrics.BLOOD_PRESSURE_DIASTOLIC.name to 220051,
        Metrics.TEMPERATURE.name to 223762,
        Metrics.GLUCOSE.name to 220621,
    );

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    // read mimic-iv time series
    override fun loadTimeSeries(patientId: Int, metric: String): DataSeries {
        val itemId: Int? = metricMapping[metric]

        val file = File(charteventsPath)
        val rows: List<List<String>> = csvReader().readAll(file)

        val headlessRows: List<List<String>> = rows.drop(1)

        val data = headlessRows
            .filter { row ->
                row[0] == patientId.toString() &&
                    row[6] == itemId.toString() &&
                    row.getOrNull(8)?.toDoubleOrNull() != null
            }
            .map { row ->
                val timestamp = LocalDateTime.parse(row[4], dateFormatter)
                val value = row[8].toDouble()
                timestamp to value
            }
            .sortedBy { it.first }

        return DataSeries(
            values = data.map { it.second },
            name = "${metric}_patient_$patientId",
            timestamps = data.map { it.first }
        )
    }
}

enum class Metrics {
    HEART_RATE,
    BLOOD_PRESSURE_SYSTOLIC,
    BLOOD_PRESSURE_DIASTOLIC,
    TEMPERATURE,
    GLUCOSE
}
