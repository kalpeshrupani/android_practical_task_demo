package com.org.android.presentation.utility

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import android.app.AlertDialog
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet
import com.org.android.BuildConfig
import com.org.android.R

class AppVersionInfo : AppCompatImageView {

    private var infoImage: Int? = null

    constructor(context: Context?) : super(context!!) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (BuildConfig.DEBUG) {
            attrs?.let {
                val typedArray =
                    context.obtainStyledAttributes(it, R.styleable.app_version_info, 0, 0)
                infoImage =
                    typedArray.getResourceId(
                        R.styleable.app_version_info_image,
                        android.R.drawable.ic_dialog_info
                    )
                if (infoImage != null) {
                    setImageResource(infoImage!!)
                } else {
                    setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            android.R.drawable.ic_dialog_info
                        )
                    )
                }
                setOnClickListener {
                    infoDialog()
                }
                typedArray.recycle()
            }
        }
    }

    private fun infoDialog() {
        var appInfo = ""
        appInfo = appInfo.plus("Build Type : " + BuildConfig.BUILD_TYPE)
            .plus("\nVersion Name : " + BuildConfig.VERSION_NAME)
            .plus("\nVersion Code : " + BuildConfig.VERSION_CODE)
            .plus("\nServer URL : " + BuildConfig.SERVER_URL)

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setPositiveButton("Ok") { dialog, _ -> dialog?.dismiss() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setTitle(context.resources.getString(R.string.app_name))
        alertDialog.setMessage(appInfo)
        alertDialog.setCancelable(true)
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }


}