package com.example.demo.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.demo.base.BaseViewModel
import com.example.demo.base.rxjava.autoDispose
import com.example.demo.common.room.model.ChatResponse
import com.example.demo.common.room.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * This is Login Screen view model
 */
class LoginViewModel : BaseViewModel() {

    val userValueRegister: MutableLiveData<Boolean> = MutableLiveData()
    val userValueLogin: MutableLiveData<Boolean> = MutableLiveData()
    val userValue: MutableLiveData<Boolean> = MutableLiveData()
    val chatResponseValue: MutableLiveData<Boolean> = MutableLiveData()
    val userData: MutableLiveData<User> = MutableLiveData()
    val userDataList: MutableLiveData<MutableList<User>> = MutableLiveData()
    var arrayChatDbList: MutableLiveData<List<ChatResponse>> = MutableLiveData()


    fun insertUser(user: User) {
        userDao.add(user).observeOn(AndroidSchedulers.mainThread()).doOnComplete {
            userValueRegister.value = false
        }.subscribeOn(Schedulers.io())
                .subscribe {
                    userValueRegister.value = true
                }.autoDispose(compositeDisposable)
    }

    fun checkUserAvailableOrNot(email: String, password: String) {
        userDao.getUser(email, password).observeOn(AndroidSchedulers.mainThread()).doOnComplete {
            userValueLogin.value = false
        }.subscribeOn(Schedulers.io())
                .subscribe { user ->
                    if (user != null) {
                        userData.value = user
                    }
                }.autoDispose(compositeDisposable)
    }

    fun getAllUser() {
        userDao.getAll().observeOn(AndroidSchedulers.mainThread()).doOnComplete {
            userValue.value = false
        }.subscribeOn(Schedulers.io())
                .subscribe { userList ->
                    if (userList != null) {
                        userDataList.value = userList.toMutableList()
                    }
                }.autoDispose(compositeDisposable)
    }

    /**
     * This method is used to get Chat Details from local DB
     */
    fun getChatDetails(chatId: String) {
        chatResponseDao.get(chatId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ chatList ->

                    if (chatList != null) {
                        this.arrayChatDbList.postValue(chatList)
                    }
                }, {
                    it.printStackTrace()
                }).autoDispose(compositeDisposable = compositeDisposable)
    }

}