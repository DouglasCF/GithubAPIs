package br.com.fornaro.githubapis.features.avatars

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import br.com.fornaro.githubapis.domain.repositories.UserRepository
import br.com.fornaro.githubapis.tools.BaseCoroutinesTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class AvatarsViewModelTest : BaseCoroutinesTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AvatarsViewModel

    @MockK
    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = AvatarsViewModel(
            userRepository = userRepository
        )
    }

    @Test
    fun `should start in loading state`() = runBlockingTest {
        viewModel.state.test {
            assertEquals(AvatarsState.Loading, expectItem())
        }
    }
}