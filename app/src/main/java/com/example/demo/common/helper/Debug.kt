package com.example.demo.common.helper

import android.util.Log

/** 
 *Common class for print log disable DEBUG in case you dont want to print it. 
 */
object Debug {
    private val DEBUG = true

    fun e(tag: String, msg: String) {
        if (DEBUG) {
            Log.e(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (DEBUG) {
            Log.i(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (DEBUG) {
            Log.w(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun v(tag: String, msg: String) {
        if (DEBUG) {
            Log.v(tag, msg)
        }
    }
}