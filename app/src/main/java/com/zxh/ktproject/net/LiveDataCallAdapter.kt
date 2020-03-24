package com.zxh.ktproject.net

import androidx.lifecycle.LiveData
import com.zxh.ktproject.entity.RespData
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 将retrofit返回的call对象转换成LiveData
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (getRawType(returnType) != LiveData::class.java) {
            throw IllegalStateException("return type must be parameterized")
        }
        var observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        observableType = if (rawObservableType == Response::class.java) {
            if (observableType !is ParameterizedType) {
                throw IllegalArgumentException("Response must be parameterized")
            }
            getParameterUpperBound(0, observableType)
        } else {
            observableType
        }
        return LiveDataCallAdapter<Any>(observableType)
    }

}

class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<R>> {
    override fun adapt(call: Call<R>): LiveData<R> {
        return object : LiveData<R>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {

                if (started.compareAndSet(false, true)) {//确保只执行一次
                    call.enqueue(object : Callback<R> {
                        override fun onFailure(call: Call<R>, t: Throwable) {
                            postValue(RespData(null, -1, "网络错误") as R)
                        }

                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(response.body())
                        }

                    })
                }
            }
        }
    }

    override fun responseType(): Type {
        return responseType
    }
}