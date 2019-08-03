package com.carden.wanandroid_master.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.carden.wanandroid_master.entity.ModelArticalBean
import com.carden.wanandroid_master.entity.ModelIndexBanner
import com.carden.wanandroid_master.repository.IndexBannerRePository

/**
 *
 * 作者: carden
 * 时间: 2019/7/9 18:06
 * 描述: banner轮询以及分类数据列表
 *
 */
class IndexViewModel(application: Application) : AndroidViewModel(application) {
    //banner轮询数据获取
    private var indexBannerLiveData: MutableLiveData<ModelIndexBanner>? = null
    private var indexBannerRepository: IndexBannerRePository? = null
    private var articalLiveData: MutableLiveData<ModelArticalBean>? = null

    init {
        indexBannerRepository = IndexBannerRePository()
        indexBannerLiveData = indexBannerRepository?.getBanner()
        articalLiveData = indexBannerRepository?.getArtical()

    }

    fun getIndexBanner() {
        indexBannerRepository?.getIndexBannerLiveData()
    }

    fun getArticalData(num: Int) {
        indexBannerRepository?.getArticalLiveData(num)
    }

    fun getIndexBannerLiveData(): MutableLiveData<ModelIndexBanner>? = indexBannerLiveData
    fun getArticalLiveData(): MutableLiveData<ModelArticalBean>? = articalLiveData
}