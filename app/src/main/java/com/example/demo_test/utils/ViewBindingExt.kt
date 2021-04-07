@file:JvmName("ViewBindingExt")
@file:Suppress("UNCHECKED_CAST")

package com.example.demo_test.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import java.lang.reflect.ParameterizedType

/**
 * fragment使用的话，layoutInflater为onCreateView方法里面的inflater参数
 * @param typeIndex ViewBinding所在泛型的下标，如<T, VB: ViewBinding>的话，typeIndex为1
 * @author Jowan
 * Created on 12/18/20.
 */
@JvmOverloads
fun <VB> Any.inflater2Binding(
    layoutInflater: LayoutInflater,
    container: ViewGroup? = null,
    typeIndex: Int = 0,
    attachToRoot: Boolean = false
): VB {
    // 利用反射，调用指定ViewBinding中的inflate方法填充视图
    val type = javaClass.genericSuperclass
    val clazz = (type as ParameterizedType).actualTypeArguments[typeIndex] as Class<VB>
    val method = clazz.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )
    return method.invoke(null, layoutInflater, container, attachToRoot) as VB
}