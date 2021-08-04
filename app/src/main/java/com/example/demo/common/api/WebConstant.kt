package com.example.demo.common.api

object WebConstant {
    const val BASE_URL = "https://dev.naturaljewels.club"


    const val ENDPOINT_URL = "$BASE_URL/api/v1/api_customer_product/"
//    const val ENDPOINT_URL = "$BASE_URL/API/Service.php?Service="
//    const val ENDPOINT_URL = "webservice/Services.php?Service="

    //Get Status List
    const val URL_GET_STATUS = ENDPOINT_URL + "get_status_list"

    /*Get Status Module*/
    const val SUCCESS = true
    const val FAILED = false
    const val DATA = "data"
    const val MESSAGE = "message"
    const val RESPONSE= "response"

}