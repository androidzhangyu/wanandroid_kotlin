package com.carden.wanandroid_master.api

import com.carden.wanandroid_master.entity.ModelArticalBean
import com.carden.wanandroid_master.entity.ModelIndexBanner
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface IndexService {
    @GET("banner/json")
    fun getIndexBanner(): Observable<ModelIndexBanner>
    //首页文章列表
    @GET
    fun getArtical(@Url url:String):Observable<ModelArticalBean>

}