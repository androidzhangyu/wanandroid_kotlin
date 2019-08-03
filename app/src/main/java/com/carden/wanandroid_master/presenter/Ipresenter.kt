package com.carden.wanandroid_master.presenter

import com.carden.wanandroid_master.view.IView
/**
 *
 * 作者: carden
 * 时间: 2019/6/20 15:40
 * 描述: 扩展类，可以与view生命周期绑定在一起
 *
 */
 interface  Ipresenter<V:IView>{
     /**
      *依附view的生命周期
      */
     fun attachView(view:V)

     /**
      * 分离view
      */
     fun detachView()

     /**
      * view是否脱离
      */
     fun  isViewAttached():Boolean

}