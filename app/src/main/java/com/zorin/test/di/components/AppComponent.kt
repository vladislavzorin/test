package com.zorin.test.di.components

import com.zorin.test.di.modules.AppModule
import com.zorin.test.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [(AppModule::class)])
@Singleton
interface AppComponent {

    fun plusMainComponent(networkModule: NetworkModule):MainComponent

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(appModule:AppModule): Builder
    }
}