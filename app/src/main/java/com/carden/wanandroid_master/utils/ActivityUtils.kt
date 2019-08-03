package com.carden.wanandroid_master.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tencent.bugly.proguard.s
import java.lang.Exception
import java.nio.channels.ClosedSelectorException
import java.util.*

/**
 *
 * 作者: carden
 * 时间: 2019/7/24 14:50
 * 描述: Activity管理
 *
 */
class ActivityUtils {
    companion object {
        private  var sActivity: Stack<Activity>? = null
        fun addActivity(activity: Activity) {
            if (sActivity == null) {
                sActivity = Stack<Activity>()
            }
            sActivity?.add(activity)

        }

        /**
         * 获取当前acvity
         */
        fun currentActivity(): Activity? = sActivity?.lastElement()

        /**
         *结束当前activity
         */
        fun finishCurrentActivity() {
            currentActivity()?.let {
                it.finish()

            }

        }

        /**
         * 结束指定的activity
         *
         *
         */
        fun finishActivity(activity: Activity) {
            activity?.let {
                sActivity?.remove(it)
                it.finish()

            }
        }

        /**
         *结束指定类名的activity
         */
        fun finishClassActivity(clazz: Class<*>) {
            for (activity in sActivity!!) {
                if (activity.javaClass==clazz) {
                    finishActivity(activity)
                }
            }

        }

        /**
         * 结束所有的activity
         */
        fun finishAllActivity() {
            val size: Int= sActivity?.size!!
            if (size > 0) {
                for (i in 1 until size) {
                    finishActivity(sActivity?.get(i)!!)
                }
            }
            sActivity?.clear()

        }

        /**
         * 退出应用
         */
        fun exitApp(context: Context) {
            try {
                finishAllActivity()
                val activityManage: ActivityManager =
                    context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                activityManage.restartPackage(context.packageName)
                System.exit(0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        /**
         * 获取Stack
         */
        fun getActivityStack(): Stack<Activity> {
            return sActivity!!
        }

        /**
         * 判断指定的activity是否存在
         *
         */
        fun isExistActivity(context: Context, clssName: String): Boolean {
            val intent: Intent = Intent()
            intent.setClassName(context.packageName, clssName)
            return !(context.packageManager.resolveActivity(
                intent,
                0
            ) == null || intent.resolveActivity(context.packageManager) == null || context.packageManager.queryIntentActivities(
                intent,
                0
            ).size == 0)

        }

        /**
         * 打开指定activity
         */
        fun lunchActivity(context: Context, className: String, bundle: Bundle) {
            context.startActivity(getComponentNameIntent(context.packageName, className, bundle))
        }

        /**
         * 获取其他应用的Intent
         */
        fun getComponentNameIntent(packageName: String, className: String, budle: Bundle): Intent {
            val intent = Intent(Intent.ACTION_VIEW)
            if (budle != null) {
                intent.putExtras(budle)
            }
            val cn = ComponentName(packageName, className)
            intent.setComponent(cn)
            return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        /**
         * 跳转activity、并且结束之前的activity
         *
         */
        fun skipActivityAndFinishAll(context: Context, claz: Class<*>, bundle: Bundle) {
            val intent = Intent(context, claz)
            intent.putExtras(bundle)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
            (context as Activity).finish()


        }

        /**
         * activity之间的跳转
         *
         */
        fun skipActivity(context: Context, claz: Class<*>, bundle: Bundle) {
            val intent = Intent(context, claz)
            intent.putExtras(bundle)
            context.startActivity(intent)
            (context as Activity).finish()
        }

        /**
         * activity之间跳转，不传递参数
         */

        fun skipActivity(context: Context, claz: Class<*>) {
            val intent = Intent(context, claz)
            context.startActivity(intent)
            (context as Activity).finish()

        }

        /**
         * startActivityforResult
         */
        fun skipActivityForResult(context: Context, claz: Class<*>, bundle: Bundle, requestCode: Int) {
            val intent = Intent(context, claz)
            intent.putExtras(bundle)
            (context as Activity).startActivityForResult(intent, requestCode)


        }

        /**
         * startActivityforResult
         */
        fun skipActivityForResult(context: Context, claz: Class<*>, requestCode: Int) {
            val intent = Intent(context, claz)
            (context as Activity).startActivityForResult(intent, requestCode)
        }

        fun getLauncherActivity(context: Context):String{
            val packageName=context.packageName;
            val intent = Intent(Intent.ACTION_MAIN, null)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            val pm = context.packageManager
            val infos = pm.queryIntentActivities(intent, 0)
            for (info in infos){
                if (info.activityInfo.packageName == packageName){
                    return info.activityInfo.name

                }
            }

        return  "no $packageName"

        }

    }
}