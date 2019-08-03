package com.carden.wanandroid_master

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.*
import android.widget.Toast
import com.carden.wanandroid_master.ViewModel.IndexViewModel
import com.carden.wanandroid_master.base.BaseActivity
import com.carden.wanandroid_master.common.Constants
import com.carden.wanandroid_master.entity.ModelIndexBanner
import com.carden.wanandroid_master.repository.IndexBannerRePository
import com.carden.wanandroid_master.utils.LogUtils
import kotlinx.android.synthetic.main.activity_base.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initData() {
    }

    override fun initView() {
        val toolbar = getHeaderToolBar()
        setSupportActionBar(toolbar)
        supportActionBar?.title="首页"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private var vm: IndexViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        //是否支持actionbar

//        supportActionBar?.setDisplayShowTitleEnabled(false)
        initData()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        fab.let {

        }
        fab.setOnClickListener {
            View.OnClickListener {
                //测试网络请求是否可用


            }
        }
//       supportActionBar?.title="yes"
    }

//    private fun initData() {
//        vm = ViewModelProviders.of(this).get(IndexViewModel::class.java)
//        vm?.getIndexBannerLiveData()?.observe(this, Observer {
//            LogUtils.getinstance()?.d(it.toString())
//
//        })
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }

    }

}
