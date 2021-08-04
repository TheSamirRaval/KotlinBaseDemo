package com.example.demo.common.room.helpers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * This class is used to convert DataType for Model Class
 */
open class DTCCustomerDetails {
    private val gson = Gson()

//    @TypeConverter
//    fun stringToList(data: String?): CustomerDetails? {
//        if (data == null) {
//            return null
//        }
//
//        val listType = object : TypeToken<CustomerDetails>() {
//
//        }.type
//
//        return gson.fromJson(data, listType)
//    }
//
//    @TypeConverter
//    fun ListToString(someObjects: CustomerDetails?): String? {
//        return gson.toJson(someObjects)
//    }
}