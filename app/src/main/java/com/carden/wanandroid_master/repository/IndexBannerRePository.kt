package com.carden.wanandroid_master.repository

import android.arch.lifecycle.MutableLiveData
import com.carden.wanandroid_master.api.IndexService
import com.carden.wanandroid_master.entity.ModelArticalBean
import com.carden.wanandroid_master.entity.ModelIndexBanner
import com.carden.wanandroid_master.utils.NetUtils
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * 作者: carden
 * 时间: 2019/7/9 18:33
 * 描述: banner轮询以及
 *
 */
class IndexBannerRePository {
    private var indexBannerLiveData: MutableLiveData<ModelIndexBanner>? = null
    private var articalLiveData: MutableLiveData<ModelArticalBean>? = null

    init {
        getIndexBannerLiveData()
    }

    fun getIndexBannerLiveData() {
        val retrofit = NetUtils.getRetrofit()
        val service = retrofit?.create(IndexService::class.java)

        service?.let {

            it.getIndexBanner().subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ModelIndexBanner> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(value: ModelIndexBanner?) {
                    indexBannerLiveData?.value = value
                }

                override fun onError(e: Throwable?) {
                    indexBannerLiveData = null

                }


            })
        }


    }

    fun getArticalLiveData(num: Int) {

    }

    fun getBanner(): MutableLiveData<ModelIndexBanner>? = indexBannerLiveData
    fun getArtical(): MutableLiveData<ModelArticalBean>? = articalLiveData


}