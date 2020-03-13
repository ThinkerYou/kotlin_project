package com.kotlin.common

import com.kotlin.common.unused.PropertiesDelegate
import org.junit.Test

class InfoProps: PropertiesDelegate.AbsProperties("info.properties"){
    var name:String by prop
    var email:String by prop
    val age:Int by prop
    val student:Boolean by prop
    val point:Float by prop
}

class PropertiesDelegateTest{
    @Test
    fun test(){
      val infoProps =   InfoProps()
        infoProps.let {
            println(it.name)
            println(it.email)
            println(it.age)
            println(it.student)
            println(it.point)
            it.name="lishixiang"
            it.email="lishixiang@kotlin.com"
        }
    }
}