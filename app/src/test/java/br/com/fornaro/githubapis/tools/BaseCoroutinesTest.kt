package br.com.fornaro.githubapis.tools

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
abstract class BaseCoroutinesTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun clearDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
