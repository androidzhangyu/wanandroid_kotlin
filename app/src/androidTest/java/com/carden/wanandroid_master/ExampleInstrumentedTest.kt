package com.carden.wanandroid_master

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented kotlintest, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under kotlintest.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.carden.wanandroid_master", appContext.packageName)
    }
}
