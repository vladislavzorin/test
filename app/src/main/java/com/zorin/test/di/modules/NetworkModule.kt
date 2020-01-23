package com.zorin.test.di.modules

import com.zorin.test.adapters.MainAdapter
import com.zorin.test.di.scope.MainScope
import com.zorin.test.network.AppApi
import com.zorin.test.repo.MainRepository
import com.zorin.test.repo.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    @MainScope
    internal fun providePostApi(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }

    @Provides
    @MainScope
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://chat.pryaniky.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @MainScope
    internal fun provideMainReopsitory(api:AppApi): MainRepository{
        return MainRepositoryImpl(appApi = api)
    }

}