package core.repositories

import core.domain.DataSeries

interface IDataRepository {
    fun loadTimeSeries(patientId: Int, metric: String): DataSeries
}
