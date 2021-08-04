package com.example.demo.ui.mandobk_shop.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.demo.base.BaseViewModel
import com.example.demo.base.rxjava.autoDispose
import com.example.demo.common.api.WebConstant
import com.example.demo.common.model.StatusList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * This is MandoBKShop Screen view model
 */
class MandoBkShopViewModel : BaseViewModel() {
    var statusList: MutableLiveData<MutableList<StatusList>> = MutableLiveData()


    fun callGetStatusApi() {
        try {
            apiInterface.getStatus()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ shopByTypeRepository ->
                    if (shopByTypeRepository.response == WebConstant.SUCCESS) {
                        message.value = shopByTypeRepository.message
                        statusList.value = shopByTypeRepository.getStatusList()?.toMutableList()
                    } else {
                        errorMessage.value = shopByTypeRepository.message
                    }
                }, {
                    errorMessage.value = it.toString()
                }).autoDispose(compositeDisposable)

        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }


}