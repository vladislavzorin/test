package com.zorin.test.presenters


import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.zorin.test.adapters.MainAdapter
import com.zorin.test.network.model.Element
import com.zorin.test.network.model.Response
import com.zorin.test.repo.MainRepository
import com.zorin.test.views.MainView
import java.util.concurrent.TimeUnit

@InjectViewState
class MainPresenter(var mainRepository: MainRepository,var adapter:MainAdapter): MvpPresenter<MainView>() {

    var listElements:MutableList<Element> = ArrayList()

    fun login(){
        if (adapter.itemCount == 0){
            mainRepository.request()
                .doOnSubscribe{viewState.startLoading()}
                .doOnTerminate{viewState.stopLoading()}
                .retryWhen{ob -> ob.take(3).delay(15, TimeUnit.SECONDS)}
                .subscribe({value -> processingResults(value)},{processingError()})
        }
    }

    private fun processingResults(results: Response){

        for (view in results.view){
            for (element in results.data){
                if(view == element.name){
                    listElements.add(element)
                    break
                }
            }
        }

        adapter.setData(listElements)
    }

    private fun processingError(){
        viewState.showError("Ошибка загрузки!")
    }
}