package com.zxh.ktproject.entity

class RespData<T>(
    var t: T?,
    var code: Int,
    var message: String

) {
    fun parseT(clazz: Class<T>): T? {
        if (t == null) {
            try {

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return t
    }

    fun isSuccess() = code == 200
}