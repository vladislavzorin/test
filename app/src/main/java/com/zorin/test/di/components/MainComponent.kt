package com.zorin.test.di.components

import com.zorin.test.activities.MainActivity
import com.zorin.test.di.modules.NetworkModule
import com.zorin.test.di.scope.MainScope
import dagger.Subcomponent

@Subcomponent(modules = [(NetworkModule::class)])
@MainScope
interface MainComponent {

    fun inject(serverListViewModel: MainActivity)
}