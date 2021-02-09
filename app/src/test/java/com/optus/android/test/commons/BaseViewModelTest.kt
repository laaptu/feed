package com.optus.android.test.commons

import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.extension.RegisterExtension

abstract class BaseViewModelTest {
    @RegisterExtension
    @JvmField
    val coroutinesTestRule = CoroutinesTestRule()

    @RegisterExtension
    @JvmField
    val instantTaskExecutorRule = TaskExecutorRule()

    @OptIn()
    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
        coroutinesTestRule.testDispatcher.runBlockingTest(block)
    }
}