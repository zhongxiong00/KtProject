package com.zxh.ktproject.net

import com.zxh.ktproject.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiManager() {

    companion object {
        private const val DEFAULT_TIMEOUT = 15000L
        private val mHeaderInterceptor = Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            builder.method(original.method(), original.body())
            chain.proceed(builder.build())
        }


        private val defaultOkhttp = OkHttpClient.Builder()
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(mHeaderInterceptor)
            .build()


        private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .baseUrl(BASEURL)
                .build()
        }

        private val mApiService by lazy {
            provideRetrofit(defaultOkhttp).create(ApiService::class.java)
        }


        private val BASEURL = BuildConfig.BASE_SERVER_URL

        private fun createApiService(): ApiService = mApiService

        fun getApiDefaultService(): ApiService = createApiService()

    }

}