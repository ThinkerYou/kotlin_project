package com.kotlin.common

import com.kotlin.common.ext.no
import com.kotlin.common.ext.otherWise
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testBoolean() {
        val resultOtherWise = false.no {
           2
        }.otherWise{
           1
        }
        Assert.assertEquals(resultOtherWise,2)
        val result = true.no {
            2
        }.otherWise{
            1
        }
        Assert.assertEquals(result,1)
    }
    fun getBoolean() = false;
}
