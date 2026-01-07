package ru.kpfu.itis.android.feature.saved_cities.api

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import ru.kpfu.itis.android.feature.saved_cities.api.domain.model.SavedCity
import ru.kpfu.itis.android.feature.saved_cities.api.domain.repository.SavedCityRepository
import ru.kpfu.itis.android.feature.saved_cities.api.domain.usecase.GetSavedCitiesUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class GetSavedCitiesUseCaseTest {

    private val repository: SavedCityRepository = mockk()
    private lateinit var useCase: GetSavedCitiesUseCase

    @Before
    fun setup() {
        useCase = GetSavedCitiesUseCase(repository)
    }

    @Test
    fun `invoke returns flow from repository`() = runTest {
        val flow = flowOf(emptyList<SavedCity>())

        every { repository.observeSavedCities() } returns flow

        val result = useCase()

        assertEquals(flow, result)
    }
}