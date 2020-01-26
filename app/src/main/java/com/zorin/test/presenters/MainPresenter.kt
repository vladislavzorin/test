package com.zorin.test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.zorin.test.adapters.MainAdapter
import com.zorin.test.network.model.Element
import com.zorin.test.network.model.Response
import com.zorin.test.repo.MainRepository
import com.zorin.test.views.MainView


@InjectViewState
class MainPresenter(var mainRepository: MainRepository,var adapter:MainAdapter): MvpPresenter<MainView>() {

    var listElements:MutableList<Element> = ArrayList()

    fun requestToServer(){
        if (adapter.itemCount == 0){
            mainRepository.request()
                .doOnSubscribe{viewState.startLoading()}
                .doOnTerminate{viewState.stopLoading()}
                .subscribe({value -> processingResults(value)},{processingError()})
        }
    }

    private fun processingResults(results: Response){

        for (view in results.view){
            if (view in listOf("hz","picture","selector"))
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

    fun updateQuery(){
        adapter.deleteData()
        requestToServer()
    }
}