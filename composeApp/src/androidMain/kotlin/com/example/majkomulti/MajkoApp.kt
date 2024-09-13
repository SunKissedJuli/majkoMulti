package com.example.majkomulti

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.majkomulti.di.KoinInjector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.module

class MajkoApp: Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInjector.koinApp
            .androidLogger(Level.NONE)
            .androidContext(this@MajkoApp)
        activityInject()
    }

    private fun activityInject() {
        this.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            override fun onActivityResumed(activity: Activity) {
                KoinInjector.koin.loadModules(listOf(module {
                    single { activity }
                }))
            }

            override fun onActivityPaused(activity: Activity) {
                KoinInjector.koin.unloadModules(listOf(module {
                    single { activity }
                }))
            }


            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

            override fun onActivityStarted(activity: Activity) {}

            override fun onActivityStopped(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {}


        })
    }


}

