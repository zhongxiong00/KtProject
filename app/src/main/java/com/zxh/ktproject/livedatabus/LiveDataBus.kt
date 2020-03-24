package com.zxh.ktproject.livedatabus

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
object LiveDataBus {

    private val bus: ConcurrentHashMap<Class<*>, MutableLiveData<*>> by lazy {
        ConcurrentHashMap<Class<*>, MutableLiveData<*>>()
    }

    @JvmStatic
    @MainThread
    inline fun <T> with(clazz: Class<T>): MutableLiveData<T> {
        return getLiveData(clazz)
    }

    @JvmStatic
    inline fun <reified T> post(t: T) {
        getLiveData(T::class.java).postValue(t)
    }

    @JvmStatic
    fun <T> getLiveData(clazz: Class<T>): MutableLiveData<T> {
        if (!bus.containsKey(clazz)) {
            bus[clazz] = MutableLiveData<T>()
        }
        return bus[clazz] as MutableLiveData<T>
    }

    @JvmStatic
    fun <T> remove(clazz: Class<T>) {
        if (bus.containsKey(clazz)) {
            bus.remove(clazz)
        }
    }

    @JvmStatic
    fun getLiveDataVersion(liveData: LiveData<*>): Int {
        val versionField = LiveData::class.java.getDeclaredField("mVersion")
        versionField.isAccessible = true
        return versionField.get(liveData) as Int
    }
}

fun <T> MutableLiveData<T>.observerNonSticky(onChange: (t: T) -> Unit) {
    observeForever(LiveDataBusObserve(this, onChange))
}

fun <T> MutableLiveData<T>.observerNonSticky(owner: LifecycleOwner, onChange: (t: T) -> Unit) {
    observe(owner, LiveDataBusObserve(this, onChange))
}

/**
 * 非黏性observer
 */
class LiveDataBusObserve<T>(
    private val liveData: MutableLiveData<T>,
    private val onChange: (t: T) -> Unit
) :
    Observer<T> {
    var mLastVersion: Int = LiveDataBus.getLiveDataVersion(liveData)
    private val mIsFirstVersion = AtomicBoolean(true)
    override fun onChanged(t: T) {
        if (mIsFirstVersion.compareAndSet(true, false)) {
            //只走一次之后 后面的就正常了 走正常回调
            val currentLiveDataVersion = LiveDataBus.getLiveDataVersion(liveData)
            if (mLastVersion >= currentLiveDataVersion) {
                return
            }
            mLastVersion = currentLiveDataVersion
            onChange(t)
        } else {
            onChange(t)
        }
    }
}