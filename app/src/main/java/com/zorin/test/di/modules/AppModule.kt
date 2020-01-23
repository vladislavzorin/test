package com.zorin.test.di.modules

import android.content.Context
import com.zorin.test.adapters.MainAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var context: Context) {

    @Provides
    @Singleton
    fun provideContext():Context{
        return this.context
    }

    @Provides
    @Singleton
    internal fun provideMainAdapter(): MainAdapter {
        return MainAdapter()
    }

}