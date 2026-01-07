package ru.kpfu.itis.android.feature.search_city.api

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.kpfu.itis.android.core.network.model.ApiResult
import ru.kpfu.itis.android.feature.search_city.api.domain.model.City
import ru.kpfu.itis.android.feature.search_city.api.domain.repository.CitySearchRepository
import ru.kpfu.itis.android.feature.search_city.api.domain.usecase.SearchCityUseCase

@ExperimentalCoroutinesApi
class SearchCityUseCaseTest {

    private lateinit var repository: CitySearchRepository
    private lateinit var searchCityUseCase: SearchCityUseCase

    @Before
    fun setUp() {
        repository = mockk()
        searchCityUseCase = SearchCityUseCase(repository)
    }

    @Test
    fun `invoke should call repository with correct query and return success`() = runTest {
        val query = "Kazan"
        val expectedCities = listOf(
            City(name = "Kazan", country = "Russia", query = "Kazan,Russia"),
            City(name = "Kazan", country = "Turkey", query = "Kazan,Turkey")
        )
        val apiResult = ApiResult.Success(expectedCities)

        coEvery { repository.search(query) } returns apiResult

        val result = searchCityUseCase(query)

        coVerify(exactly = 1) { repository.search(query) }
        assertTrue(result is ApiResult.Success)
        assertEquals(expectedCities, (result as ApiResult.Success).data)
    }

    @Test
    fun `invoke should return success with empty list when no cities found`() = runTest {
        val query = "NonExistentCity"
        val apiResult = ApiResult.Success(emptyList<City>())

        coEvery { repository.search(query) } returns apiResult

        val result = searchCityUseCase(query)

        assertTrue(result is ApiResult.Success)
        val cities = (result as ApiResult.Success).data
        assertTrue(cities.isEmpty())
    }

    @Test
    fun `invoke should return error when repository returns error`() = runTest {
        val query = "Moscow"
        val exception = RuntimeException("Network error")
        val apiResult = ApiResult.Failure(exception)

        coEvery { repository.search(query) } returns apiResult

        val result = searchCityUseCase(query)

        assertTrue(result is ApiResult.Failure)
        assertEquals(exception, (result as ApiResult.Failure).throwable)
    }

    @Test
    fun `invoke should propagate exception when repository throws`() = runTest {
        val query = "London"
        val exception = RuntimeException("Database error")

        coEvery { repository.search(query) } throws exception

        try {
            searchCityUseCase(query)
            assert(false)
        } catch (e: RuntimeException) {
            assertEquals(exception, e)
        }
    }
}