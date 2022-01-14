package com.org.android.presentation.splash

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


import android.os.Bundle
import com.org.android.BuildConfig
import com.org.android.databinding.ActivitySplashBinding
import com.org.android.presentation.core.BaseActivity
import com.org.android.presentation.utility.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun getBaseViewModel() = splashViewModel

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        if (BuildConfig.DEBUG) {
            binding.tvAppVersion.visible()
        } else {
            binding.tvAppVersion.gone()
        }

        launch {
            delay(AppConstant.TIMEOUT)
            startActivityCustom(IntentHelper.getHomeScreenIntent(this@SplashActivity, true))
        }
    }
}
