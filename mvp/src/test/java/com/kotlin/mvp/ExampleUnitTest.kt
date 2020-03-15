package com.kotlin.mvp

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val outer = Outer()
        val inner =  outer::inner
    }
}
class Outer{
   inner class inner{
    }
}

class Foo
fun function(factory:()->Foo){
    val x :Foo = factory()
}

var x = 1;
fun isOdd(x:Int)= x%2!=0
fun <A,B,C>compose(f:(B)->C,g:(A)->B):(A)->C{
    return {x->f(g(x))}
}
