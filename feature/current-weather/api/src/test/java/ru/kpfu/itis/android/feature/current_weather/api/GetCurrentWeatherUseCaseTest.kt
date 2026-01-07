package ru.kpfu.itis.android.feature.current_weather.api

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.current_weather.api.domain.model.CurrentWeather
import ru.kpfu.itis.android.feature.current_weather.api.domain.repository.CurrentWeatherRepository
import ru.kpfu.itis.android.feature.current_weather.api.domain.usecase.GetCurrentWeatherUseCase

@ExperimentalCoroutinesApi
class GetCurrentWeatherUseCaseTest {

    private lateinit var repository: CurrentWeatherRepository
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @Before
    fun setUp() {
        repository = mockk()
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(repository)
    }

    @Test
    fun `invoke should call repository with correct query and return success`() = runTest {
        val query = "Moscow"
        val expectedWeather = CurrentWeather(
            cityName = "Moscow",
            temperatureInCelsius = 20,
            description = "Sunny",
            latitude = 55.7558,
            longitude = 37.6173
        )
        val apiResult = ApiResult.Success(expectedWeather)

        coEvery { repository.getCurrentWeather(query) } returns apiResult

        val result = getCurrentWeatherUseCase(query)

        coVerify(exactly = 1) { repository.getCurrentWeather(query) }
        assertTrue(result is ApiResult.Success)
        assertEquals(expectedWeather, (result as ApiResult.Success).data)
    }

    @Test
    fun `invoke should return error when repository returns error`() = runTest {
        val query = "London"
        val exception = RuntimeException("Network error")
        val apiResult = ApiResult.Failure(exception)

        coEvery { repository.getCurrentWeather(query) } returns apiResult

        val result = getCurrentWeatherUseCase(query)

        assertTrue(result is ApiResult.Failure)
        assertEquals(exception, (result as ApiResult.Failure).throwable)
    }

    @Test
    fun `invoke should handle empty query`() = runTest {
        val query = ""
        val exception = RuntimeException("Invalid query")
        val apiResult = ApiResult.Failure(exception)

        coEvery { repository.getCurrentWeather(query) } returns apiResult

        val result = getCurrentWeatherUseCase(query)

        assertTrue(result is ApiResult.Failure)
        assertEquals(exception, (result as ApiResult.Failure).throwable)
    }
}