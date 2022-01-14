package com.org.android.presentation.core

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import androidx.lifecycle.Observer
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.appcompat.widget.AppCompatTextView
import com.org.android.R
import com.org.android.domain.exceptions.MyException
import com.org.android.presentation.utility.showDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    companion object {
        var dialogShowing = false
    }

    lateinit var toolbar1: Toolbar
    private var needToShowBackButton: Boolean? = false

    lateinit var job: Job
    private var progress: CustomProgressDialog? = null
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    abstract fun getBaseViewModel(): BaseViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()

        progress = CustomProgressDialog(this)

        attachBaseObserver()

    }

    private fun attachBaseObserver() {
        getBaseViewModel()?.exceptionLiveData?.observe(this, Observer {
            hideProgress()
            it?.apply {
                when (this) {
                    is MyException.InternetConnectionException -> showErrorDialog(getString(R.string.exception_error_network))
                    is MyException.JsonException -> showErrorDialog(getString(R.string.exception_error_unparceble))
                    is MyException.ServerNotAvailableException -> showErrorDialog(getString(R.string.exception_error_server))
                    is MyException.DatabaseException -> showErrorDialog(getString(R.string.exception_error_database))
                    is MyException.NetworkCallCancelException -> {
                    }
                    else -> {
                        var message = it.message
                        if (message?.isEmpty() == true) {
                            message = it.localizedMessage
                        }
                        showErrorDialog("Unknown error : " + message)
                    }
                }
            }
        })
    }

    private fun showErrorDialog(message: String) {
        showDialog(
            getString(R.string.app_name),
            message,
            getString(R.string.ok), DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    protected fun addFragment(
        @IdRes containerViewId: Int, fragment: Fragment,
        fragmentTag: String
    ) {
        supportFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment, fragmentTag)
            .disallowAddToBackStack()
            .commit()
    }

    protected fun replaceFragment(
        @IdRes containerViewId: Int, fragment: Fragment,
        fragmentTag: String,
        addToBackStack: Boolean? = false
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment, fragmentTag)
            .addToBackStack(if (addToBackStack!!) fragment::class.java.simpleName else null)
            .commit()
    }

    protected fun replaceFragmentWithPop(
        @IdRes containerViewId: Int, fragment: Fragment,
        fragmentTag: String,
        addToBackStack: Boolean? = false
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment, fragmentTag)
            .disallowAddToBackStack()
            .commit()
    }


    fun showProgress() {
        if (!this.isFinishing) {
            progress?.show()
        }
    }

    fun hideProgress() {
        if (!this.isFinishing && progress?.isShowing == true) {
            progress?.dismiss()
        }
    }

    /**
     * Method for perform multiple action by single line
     *
     * @param toolbar - pass your toolbar added in your layout
     * @param title - screen title
     * @param needToShowBackButton - want to show back button or not in activity
     * @param titleTextColor - color for toolbar title
     * @param toolbarColor - color for toolbar background
     * @param backButtonColor - color for back button
     */
    fun setupToolbar(
        toolbar: Toolbar,
        title: String,
        needToShowBackButton: Boolean? = true,
        titleTextColor: Int? = R.color.colorBlack,
        toolbarColor: Int? = null,
        backButtonColor: Int? = null
    ) {
        this.toolbar1 = toolbar
        this.needToShowBackButton = needToShowBackButton
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        if (needToShowBackButton!!) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(true)
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black)
        }
        toolbar.findViewById<AppCompatTextView>(R.id.txt_header).text = title
        toolbar.findViewById<AppCompatTextView>(R.id.txt_header).setTextColor(titleTextColor!!)

        /* if (toolbarColor != null) {
             toolbar.background = ColorDrawable(toolbarColor)
         } else {
             toolbar.background = ColorDrawable(Color.TRANSPARENT)
         }*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.invisible, menu)
        return super.onCreateOptionsMenu(menu)
    }

}