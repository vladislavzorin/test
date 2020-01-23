package com.zorin.test.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy

import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainView: MvpView {

    fun showError(msg:String)
    fun startLoading()
    fun stopLoading()
}