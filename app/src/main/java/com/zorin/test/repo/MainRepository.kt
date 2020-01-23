package com.zorin.test.repo

import com.zorin.test.network.model.Response
import io.reactivex.Observable

interface MainRepository {

    fun request(): Observable<Response>
}