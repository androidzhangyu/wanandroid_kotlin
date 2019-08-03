package com.carden.wanandroid_master.utils

import android.text.TextUtils
import android.util.Log
import com.carden.wanandroid_master.BuildConfig
import com.carden.wanandroid_master.common.Constants

/**
 *
 * 作者: carden
 * 时间: 2019/6/20 15:43
 * 描述: Log日志类
 *
 */
class LogUtils {
    val isSwitch: Boolean = BuildConfig.DEBUG

    companion object {
        var mLogUtils: LogUtils? = null
        fun getinstance(): LogUtils? {
            if (null == mLogUtils) {
                synchronized(LogUtils::class.java) {
                    mLogUtils = LogUtils()
                }

            }
            return mLogUtils

        }
    }

    /**
     *Debug
     */
    fun d(message: String) {
        if (!TextUtils.isEmpty(message) && isSwitch) {
            Log.d(Constants.TAG, message)
        }
    }

    /**
     * warn
     */
    fun w(message: String) {
        if (!TextUtils.isEmpty(message) && isSwitch) {
            Log.w(Constants.TAG, message)
        }

    }
    /**
     * error
     */
    fun e(message: String) {
        if (!TextUtils.isEmpty(message)&&isSwitch) {
            Log.e(Constants.TAG, message)
        }

    }
    /**
     * verbose
     */
    fun v(message: String) {
        if (!TextUtils.isEmpty(message)&&isSwitch) {
            Log.v(Constants.TAG, message)
        }

    }


}