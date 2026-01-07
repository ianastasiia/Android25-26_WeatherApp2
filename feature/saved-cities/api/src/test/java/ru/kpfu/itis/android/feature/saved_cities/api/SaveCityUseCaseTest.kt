package ru.kpfu.itis.android.feature.saved_cities.api

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity
import ru.kpfu.itis.android.feature.saved_cities.api.domain.repository.SavedCityRepository
import ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase.SaveCityUseCase

@ExperimentalCoroutinesApi
class SaveCityUseCaseTest {

    private lateinit var repository: SavedCityRepository
    private lateinit var saveCityUseCase: SaveCityUseCase

    @Before
    fun setUp() {
        repository = mockk()
        saveCityUseCase = SaveCityUseCase(repository)
    }

    @Test
    fun `invoke should call repository with correct city`() = runTest {
        val city = SavedCity(name = "Moscow", latitude = 55.7558, longitude = 37.6173)

        coEvery { repository.savedCity(city) } returns Unit

        saveCityUseCase(city)

        coVerify(exactly = 1) { repository.savedCity(city) }
    }

    @Test
    fun `invoke should handle city with special characters`() = runTest {
        val city = SavedCity(name = "New York City", latitude = 40.7128, longitude = -74.0060)

        coEvery { repository.savedCity(city) } returns Unit

        saveCityUseCase(city)

        coVerify(exactly = 1) { repository.savedCity(city) }
    }

    @Test(expected = Exception::class)
    fun `invoke should propagate exception when repository throws`() = runTest {
        val city = SavedCity(name = "London", latitude = 51.5074, longitude = -0.1278)
        val exception = RuntimeException("Database error")

        coEvery { repository.savedCity(city) } throws exception

        saveCityUseCase(city)
    }
}