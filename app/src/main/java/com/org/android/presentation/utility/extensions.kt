package com.org.android.presentation.utility

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.Html
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.org.android.R
import com.org.android.presentation.core.BaseActivity
import com.org.android.presentation.core.GlideApp
import java.util.*


/**
 * Extension functions for set visibility of any view by calling
 * yourView.visible()
 * yourView.gone()
 */
fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * For start activity
 *
 * @param intent
 * @param requestCode - Nullable
 */
fun Activity.startActivityCustom(intent: Intent, requestCode: Int? = 0) {
    if (requestCode != null) {
        this.startActivityForResult(intent, requestCode)
    } else {
        this.startActivity(intent)
    }
}

fun Fragment.startActivityCustom(intent: Intent, requestCode: Int? = 0) {
    if (requestCode != null) {
        this.startActivityForResult(intent, requestCode)
    } else {
        this.startActivity(intent)
    }
}

/**
 * For load image
 *
 * @param image - image url, file, uri
 */
fun AppCompatImageView.loadImage(
    image: Any,
    placeholder: Int? = R.drawable.icn_placeholder_square
) {
    GlideApp.with(this.context)
        .load(image)
        .placeholder(placeholder!!)
        .into(this)
}

fun AppCompatImageView.loadImageRound(
    image: Any,
    placeholder: Int? = R.drawable.icn_placeholder_square
) {
    GlideApp.with(this.context)
        .load(image)
        .placeholder(placeholder!!)
        .circleCrop()
        .into(this)
}

/**
 * For show dialog
 *
 * @param title - title which shown in dialog (application name)
 * @param msg - message which shown in dialog
 * @param positiveText - positive button text
 * @param listener - positive button listener
 * @param negativeText - negative button text
 * @param negativeListener - negative button listener
 * @param icon - drawable icon which shown is dialog
 */
fun Context.showDialog(
    title: String? = this.resources.getString(R.string.app_name),
    msg: String,
    positiveText: String? = this.resources.getString(R.string.ok),
    listener: DialogInterface.OnClickListener? = null,
    negativeText: String? = this.resources.getString(R.string.cancel),
    negativeListener: DialogInterface.OnClickListener? = null,
    icon: Int? = null
) {
    if (BaseActivity.dialogShowing) {
        return
    }
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(msg)
    builder.setCancelable(false)
    builder.setPositiveButton(positiveText) { dialog, which ->
        BaseActivity.dialogShowing = false
        listener?.onClick(dialog, which)
    }
    if (negativeListener != null) {
        builder.setNegativeButton(negativeText) { dialog, which ->
            BaseActivity.dialogShowing = false
            listener?.onClick(dialog, which)
        }
    }
    if (icon != null) {
        builder.setIcon(icon)
    }
    builder.create().show()
    BaseActivity.dialogShowing = true
}

/**
 * For validate email, mobile, password
 */
fun Context.isValidEmail(text: String): Boolean {
    return !TextUtils.isEmpty(text)
            && Patterns.EMAIL_ADDRESS.matcher(text).matches()
            && (text.length >= this.resources.getInteger(R.integer.min_length_email))
            && (text.length <= this.resources.getInteger(R.integer.max_length_email))
}

fun Context.isValidPhone(text: String): Boolean {
    return !TextUtils.isEmpty(text)
            && Patterns.PHONE.matcher(text).matches()
            && (text.length >= this.resources.getInteger(R.integer.min_length_mobile))
            && (text.length <= this.resources.getInteger(R.integer.max_length_mobile))
}

fun Context.isValidPassword(text: String): Boolean {
    return !TextUtils.isEmpty(text)
            && (text.length >= this.resources.getInteger(R.integer.min_length_password))
            && (text.length <= this.resources.getInteger(R.integer.max_length_password))
}

fun Context.isPasswordAndConfirmPasswordMatch(password: String, confirmPass: String): Boolean {
    return !TextUtils.isEmpty(password)
            && !TextUtils.isEmpty(confirmPass)
            && password.contentEquals(confirmPass)
}


fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
        return false
    }
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
}

fun Context.getDeviceTimeZone(): String {
    val timeZone: String = Calendar.getInstance().timeZone.id
    return timeZone
}

fun Context.getAndroidID(): String {
    return Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID)
}

fun Context.hideKeyboardFrom(view: View) {
    val imm: InputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

