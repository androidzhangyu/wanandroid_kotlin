package com.carden.wanandroid_master.base

import com.carden.wanandroid_master.presenter.Ipresenter
import com.carden.wanandroid_master.view.IView
/**
 *
 * 作者: carden
 * 时间: 2019/6/20 15:39
 * 描述: presenter基类
 *
 */
abstract class BasePresenter<V : IView> : Ipresenter<V> {
    private var mView: V? = null
    override fun attachView(view: V) {
        view?.let {
            mView = it
        }
    }

    override fun detachView() {
        mView = null

    }

    override fun isViewAttached(): Boolean {
        return null != mView
    }
}