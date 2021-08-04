package com.example.demo.common.repository

import com.example.demo.base.BaseRepository


class GetStatusRepository( ) :
    BaseRepository() {


    fun getStatusList() = data?.statusList

}