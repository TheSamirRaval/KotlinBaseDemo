package com.example.demo.common.repository

import com.example.demo.base.BaseRepository


class LoginRepository( ) :
    BaseRepository() {


    fun getStatusList() = data?.statusList

}