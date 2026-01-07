package ru.kpfu.itis.android.feature.forecast.api

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.forecast.api.domain.model.ForecastDay
import ru.kpfu.itis.android.feature.forecast.api.domain.repository.ForecastRepository
import ru.kpfu.itis.android.feature.forecast.api.domain.usecase.GetForecastUseCase

@ExperimentalCoroutinesApi
class GetForecastUseCaseTest {

    private lateinit var repository: ForecastRepository
    private lateinit var getForecastUseCase: GetForecastUseCase

    @Before
    fun setUp() {
        repository = mockk()
        getForecastUseCase = GetForecastUseCase(repository)
    }

    @Test
    fun `invoke should call repository with correct parameters and return success`() = runTest {
        val query = "Moscow"
        val days = 5
        val expectedForecast = listOf(
            ForecastDay(
                date = "2023-10-01",
                avgTempC = 15,
                condition = "Sunny",
                iconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png"
            ),
            ForecastDay(
                date = "2023-10-02",
                avgTempC = 16,
                condition = "Partly cloudy",
                iconUrl = "//cdn.weatherapi.com/weather/64x64/day/116.png"
            )
        )
        val apiResult = ApiResult.Success(expectedForecast)

        coEvery { repository.getForecast(query, days) } returns apiResult

        val result = getForecastUseCase(query, days)

        coVerify(exactly = 1) { repository.getForecast(query, days) }
        assertTrue(result is ApiResult.Success)
        assertEquals(expectedForecast, (result as ApiResult.Success).data)
    }

    @Test
    fun `invoke should use default days when not specified`() = runTest {
        val query = "London"
        val defaultDays = 5
        val expectedForecast = emptyList<ForecastDay>()
        val apiResult = ApiResult.Success(expectedForecast)

        coEvery { repository.getForecast(query, defaultDays) } returns apiResult

        val result = getForecastUseCase(query)

        coVerify(exactly = 1) { repository.getForecast(query, defaultDays) }
        assertTrue(result is ApiResult.Success)
        assertEquals(expectedForecast, (result as ApiResult.Success).data)
    }

    @Test
    fun `invoke should return error when repository returns error`() = runTest {
        val query = "Paris"
        val days = 3
        val exception = RuntimeException("API Error")
        val apiResult = ApiResult.Failure(exception)

        coEvery { repository.getForecast(query, days) } returns apiResult

        val result = getForecastUseCase(query, days)

        assertTrue(result is ApiResult.Failure)
        assertEquals(exception, (result as ApiResult.Failure).throwable)
    }

    @Test
    fun `invoke should handle custom days parameter`() = runTest {
        val query = "Berlin"
        val customDays = 7
        val expectedForecast = listOf(
            ForecastDay(
                date = "2023-10-01",
                avgTempC = 18,
                condition = "Clear",
                iconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png"
            )
        )
        val apiResult = ApiResult.Success(expectedForecast)

        coEvery { repository.getForecast(query, customDays) } returns apiResult

        val result = getForecastUseCase(query, customDays)

        coVerify(exactly = 1) { repository.getForecast(query, customDays) }
        assertTrue(result is ApiResult.Success)
    }
}