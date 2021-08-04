package com.example.demo.base

import com.example.demo.MyApplication
import io.reactivex.disposables.CompositeDisposable

open class BaseRepository : ApiStatus(){
    companion object {
        val compositeDisposable: CompositeDisposable = CompositeDisposable()
        val userDao = MyApplication.getAppRoomDB().userDao()
        val chatResponseDao = MyApplication.getAppRoomDB().chatResponseDao()
    }
}