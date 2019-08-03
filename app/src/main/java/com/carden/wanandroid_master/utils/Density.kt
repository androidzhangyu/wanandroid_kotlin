package com.carden.wanandroid_master.utils

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.util.DisplayMetrics

object Density {
    private  var sNoncompatDensity:Float=0.toFloat()
    private  var sNonCompatScalDensity:Float=0.toFloat()
    fun setCustomDensity(activity: Activity, application: Application){
        var appDisplayMetrics: DisplayMetrics =application.resources.displayMetrics

        if(sNoncompatDensity==0.toFloat()){
            sNoncompatDensity=appDisplayMetrics.density
            sNonCompatScalDensity=appDisplayMetrics.scaledDensity

            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onLowMemory() {

                }

                override fun onConfigurationChanged(newConfig: Configuration?) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNonCompatScalDensity = application.resources.displayMetrics.scaledDensity
                    }
                }
            })
            val targetDensity = appDisplayMetrics.widthPixels.toFloat() / 384
            val targetScaledDensity = targetDensity * (sNonCompatScalDensity / sNoncompatDensity)
            val targetDensityDpi = (160 * targetDensity).toInt()
            appDisplayMetrics.density = targetDensity
            appDisplayMetrics.scaledDensity = targetScaledDensity
            appDisplayMetrics.densityDpi = targetDensityDpi

            val activityDisplayMetrics = activity.resources.displayMetrics
            activityDisplayMetrics.density = targetDensity
            activityDisplayMetrics.scaledDensity = targetScaledDensity
            activityDisplayMetrics.densityDpi = targetDensityDpi
        }
    }


}