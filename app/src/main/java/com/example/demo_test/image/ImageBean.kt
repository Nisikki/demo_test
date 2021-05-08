package com.example.demo_test.image

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

/**
 * @Author: Nisikki
 * @Date: 2021/5/8
 * @Describe:
 */
@Parcelize
data class ImageBean(val id:Long,val uri: Uri,val url:String) : Parcelable {
}