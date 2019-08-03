package com.carden.wanandroid_master.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.carden.wanandroid_master.R
import com.carden.wanandroid_master.utils.ActivityUtils
import com.carden.wanandroid_master.utils.Density
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Density.setCustomDensity(this,application)
        setContentView(R.layout.activity_base)
        val view:View=layoutInflater.inflate(getLayoutId(),ll_content,false)
        ll_content.addView(view)
        initView()
        initData()
        ActivityUtils.addActivity(this)
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.bg_main_color)



    }

    protected abstract fun initView()
    protected abstract fun initData()
    protected abstract fun getLayoutId():Int
    override fun onDestroy() {
        super.onDestroy()
        ActivityUtils.finishActivity(this)
    }
    fun getHeaderToolBar(): android.support.v7.widget.Toolbar? {
       return if (toolbar!=null){
           toolbar.visibility=View.VISIBLE
           toolbar
       }else{
           null
       }


    }
}
