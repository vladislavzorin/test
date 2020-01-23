package com.zorin.test.repo

import com.zorin.test.network.AppApi
import com.zorin.test.network.model.Response
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainRepositoryImpl(var appApi: AppApi):MainRepository {

    override fun request(): Observable<Response> {
        return appApi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}