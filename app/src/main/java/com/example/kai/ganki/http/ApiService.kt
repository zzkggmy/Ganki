package com.example.kai.ganki.http

import com.example.kai.ganki.entity.*
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.*

interface ApiService {


    //获取首页文章
    @GET("article/list/{page}/json")
    fun getArticle(@Path("page") page: Int): Deferred<ArticleBean>

    //注册
    @POST("user/register")
    @FormUrlEncoded
    fun register(@FieldMap map: Map<String, String>): Deferred<RegisterBean>

    //登录
    @POST("user/login")
    @FormUrlEncoded
    fun login(@FieldMap map: Map<String, String>): Deferred<LoginBean>

    //结构
    @GET("tree/json")
    fun getTree(): Deferred<TreeBean>

    //获取子结构下的所有文章
    @GET("article/list/{page}/json")
    fun getTreeArticle(@Path("page") page: Int,@Query("cid") cid: Int): Deferred<TreeArticleBean>

    //获取热词
    @GET("/hotkey/json")
    fun getHot(): Deferred<HotBean>

}