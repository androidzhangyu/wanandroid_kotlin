package com.carden.wanandroid_master.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.View
import android.widget.Toast

/**
 *
 * 作者: carden
 * 时间: 2019/8/3 15:28
 * 描述: 基类
 *
 */
abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
    }

    abstract fun initEvent()

    abstract fun initView()
    fun showToast(message: String) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
    }
}