package com.carden.wanandroid_master.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.carden.wanandroid_master.presenter.Ipresenter
import com.carden.wanandroid_master.view.IView
/**
 *
 * 作者: carden
 * 时间: 2019/6/20 15:23
 * 描述: MVPActivity基类,项目中未使用到MVP,舍弃
 *
 */
abstract class BaseMvpActivity<out P : Ipresenter<IView>> : AppCompatActivity(), IView {
    private var mPresenter: P? = null

    protected abstract fun createPresenter(): P
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())
        initPresenter()
        init()

    }

    protected abstract fun getLayoutId(): Int

    private fun initPresenter() {
        mPresenter = createPresenter()
        mPresenter?.let {
            it.attachView(this@BaseMvpActivity)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.let {
            it.detachView()
        }
    }

    protected abstract fun init()


}