package com.org.android.presentation.utility

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import android.util.Log
import com.org.android.BuildConfig

class Logger {
    companion object {

        private val TAG: String = "Capermint"

        fun d(msg: String) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, msg)
            }
        }

        fun e(msg: String) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, msg)
            }
        }

        fun i(msg: String) {
            if (BuildConfig.DEBUG) {
                Log.i(TAG, msg)
            }
        }

        fun w(msg: String) {
            if (BuildConfig.DEBUG) {
                Log.w(TAG, msg)
            }
        }

    }
}