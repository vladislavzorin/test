package com.zorin.test

import android.app.Application
import android.content.Context
import com.zorin.test.di.components.AppComponent
import com.zorin.test.di.components.DaggerAppComponent
import com.zorin.test.di.components.MainComponent
import com.zorin.test.di.modules.AppModule
import com.zorin.test.di.modules.NetworkModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger(this.applicationContext)
    }

    companion object {

        lateinit var appComponent: AppComponent
        var mainComponent: MainComponent? = null

        fun initDagger(context: Context){
            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
        }

        fun plusLoginComponent(){
            if (mainComponent == null){
                mainComponent = appComponent.plusMainComponent(NetworkModule())
            }
        }

        fun minusLoginComponent(){
            mainComponent = null
        }

        fun getComponent():MainComponent = mainComponent!!
    }
}