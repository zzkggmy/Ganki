package com.example.kai.ganki.http

class Api {
    companion object {
        val retrofitService: ApiService by lazy { RetrofitHelper.create( "http://www.wanandroid.com/") }
    }
}