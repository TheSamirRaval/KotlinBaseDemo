package com.example.demo.common.model

import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("status_list")
        val statusList: ArrayList<StatusList>
)

data class StatusList(
        @SerializedName("status_id")
        val statusId: String,
        @SerializedName("status_name")
        val statusName: String
)

