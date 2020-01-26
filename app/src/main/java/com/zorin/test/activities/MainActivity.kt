package com.zorin.test.activities

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.zorin.test.App
import com.zorin.test.R
import com.zorin.test.adapters.MainAdapter
import com.zorin.test.presenters.MainPresenter
import com.zorin.test.repo.MainRepository
import com.zorin.test.views.MainView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var adapter: MainAdapter

    @ProvidePresenter
    fun provideMainPresenter():MainPresenter{
        return MainPresenter(mainRepository,adapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.plusLoginComponent()
        App.getComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        mainPresenter.requestToServer()
    }

    override fun showError(msg: String) {
        Toast.makeText(applicationContext,"$msg",Toast.LENGTH_LONG).show()
    }

    override fun startLoading() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun stopLoading() {
        swipe_refresh_layout.isRefreshing = false
    }

    private fun initRecyclerView(){
        elements_list.layoutManager = LinearLayoutManager(
                this,
        RecyclerView.VERTICAL,
        false
        )

        elements_list.adapter = adapter

        swipe_refresh_layout.setOnRefreshListener {
            mainPresenter.updateQuery()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        App.minusLoginComponent()
    }
}
