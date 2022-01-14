package com.org.android.presentation.utility

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */

import android.content.Context
import android.content.Intent
import com.org.android.presentation.home.activities.HomeActivity
import com.org.android.presentation.home.activities.TaskSecondActivity


class IntentHelper {

    companion object {

        fun getHomeScreenIntent(context: Context, isClearFlag: Boolean? = false): Intent {
            return Intent(context, HomeActivity::class.java).also {
                if (isClearFlag != null) {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        }

        fun getTaskSecondScreenIntent(context: Context, isClearFlag: Boolean? = false): Intent {
            return Intent(context, TaskSecondActivity::class.java).also {
                if (isClearFlag != null) {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        }
    }
}