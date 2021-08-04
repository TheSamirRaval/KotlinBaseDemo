package com.thankgreens.acom.example.demo.utils

import java.io.IOException

class NoConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String get() = "No internet available, please check your connected WIFi or Data"

}