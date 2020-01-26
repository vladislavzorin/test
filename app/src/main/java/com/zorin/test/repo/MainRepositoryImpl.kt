package com.zorin.test.repo

import com.zorin.test.network.AppApi
import com.zorin.test.network.model.Response
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainRepositoryImpl(var appApi: AppApi):MainRepository {

    override fun request(): Observable<Response> {
        return appApi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.retryWhen{ob -> ob.take(1).delay(15, TimeUnit.SECONDS)}

    }
}