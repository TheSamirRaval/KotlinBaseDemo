package com.example.demo.common.api

import com.example.demo.common.repository.GetStatusRepository
import io.reactivex.Single
import retrofit2.http.*

/**
 * This class contains all the api calls
 */
interface ApiInterface {

    /**
     * This Api Calling using Get Methods.
     */
//    //Get Shop Category
//    @GET(WebConstant.URL_SHOP_CATEGORY)
//    fun shopCategory(): Observable<ShopCategoryRepository>

    /**
     * This Api Calling using Post Methods in Field Forms.
     */
//    //Shop By Type
//    @FormUrlEncoded
//    @POST(WebConstant.URL_SHOP_BY_TYPE)
//    fun shopByType(
//        @Field("user_id") userId: String, @Field("shop_type") shop_type: String,
//        @Field("latitude") latitude: String,
//        @Field("longitude") longitude: String
//    ): Single<GetStatusRepository>

    /**
     * This Api Calling using Post Methods.
     */
    //Get Status
    @POST(WebConstant.URL_GET_STATUS)
    fun getStatus(
    ): Single<GetStatusRepository>


}