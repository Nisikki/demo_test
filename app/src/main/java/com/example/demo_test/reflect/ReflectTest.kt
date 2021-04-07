package com.example.demo_test.reflect

import com.example.demo_test.utils.mLog
import java.lang.reflect.Modifier
import com.example.demo_test.reflect.TestEntity as TestEntity1

/**
 * @Author: Nisikki
 * @Date: 2021/2/5
 * @Describe:
 */
object ReflectTest {

    var p = arrayOf<Class<*>?>(Int::class.javaPrimitiveType, String::class.java)

    private fun getTestEntityClass(): Class<TestEntity1> {
        val test = TestEntity1(1, "序号")
        return test.javaClass
    }


    fun getAllConstruction() {
        val constructor= getTestEntityClass().declaredConstructors
        constructor.forEach { it ->
            val cla = it.parameterTypes
            val name = "${Modifier.toString(it.modifiers)} 参数："
            val sp = StringBuilder(name)
            cla.forEach {
                sp.append(it.name).append(",")
            }
            mLog.e(sp.toString())
        }
    }

    fun getSomeConstruction(){
        val constructor= getTestEntityClass().getDeclaredConstructor(*p)
        constructor.newInstance(5,"获取某些构造方法")

    }

    fun getMethod(){
        val constructor= getTestEntityClass().getDeclaredConstructor(*p)
      val cla =  constructor.newInstance(5,"获取 TestEntity.class ")
        val method = getTestEntityClass().getDeclaredMethod("test1", p[1])
        method.isAccessible = true
        method.invoke( cla,"咔咔咔")

        val field = getTestEntityClass().getDeclaredField("b")
        field.isAccessible = true
        mLog.e(field.name)
        field.set(cla,"EMmmmm");
        mLog.e(field.get(cla) as String)
    }
}