package com.example.demo_test.network

/**
 * @Author: Nisikki
 * @Date: 2021/5/12
 * @Describe:
 */
data class BaseResponse<T>(
    val status: Int = 0,
    val data: T
) {
}