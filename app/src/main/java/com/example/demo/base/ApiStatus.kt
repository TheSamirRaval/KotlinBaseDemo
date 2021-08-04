package com.example.demo.base

import com.example.demo.common.api.WebConstant
import com.example.demo.common.model.Response
import com.google.gson.annotations.SerializedName

abstract class ApiStatus {
//    @SerializedName(WebConstant.CODE)
//    var code: String = "0"

    @SerializedName(WebConstant.RESPONSE)
    var response: Boolean = true

    @SerializedName(WebConstant.MESSAGE)
    var message: String = ""

    @SerializedName(WebConstant.DATA)
    var data: Response ?= null

}