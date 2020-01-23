package com.zorin.test.network

import com.zorin.test.network.model.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface AppApi {

    @GET("/json/JSONSample.json")
    fun getData(): Observable<Response>
}