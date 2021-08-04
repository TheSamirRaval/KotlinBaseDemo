package com.example.demo.common.helper

import android.util.Log
import timber.log.Timber

/** 
 *Common class for print log disable DEBUG in case you dont want to print it. 
 */
object Debug {
    private val DEBUG = true

    fun e(tag: String, msg: String) {
        if (DEBUG) {
            Timber.e(msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (DEBUG) {
            Timber.i(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (DEBUG) {
            Timber.w(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (DEBUG) {
            Timber.d(tag, msg)
        }
    }

    fun v(tag: String, msg: String) {
        if (DEBUG) {
            Timber.v(tag, msg)
        }
    }
}