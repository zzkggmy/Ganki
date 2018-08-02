package com.example.kai.ganki.http

import android.text.TextUtils
import com.example.kai.ganki.utils.spGetString
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHelper {

//    private val url = "http://www.wanandroid.com/"

    fun create(url: String): ApiService {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            addInterceptor { chain ->
                val request: Request = if (!TextUtils.isEmpty(spGetString("token"))) {
                    chain.request().newBuilder().addHeader("Authorization","Bearer "+spGetString("token")).build()
                } else {
                    chain.request()
                }
                val response: Response = chain.proceed(request)
                response
            }
        }
        return Retrofit.Builder().baseUrl(url).client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}