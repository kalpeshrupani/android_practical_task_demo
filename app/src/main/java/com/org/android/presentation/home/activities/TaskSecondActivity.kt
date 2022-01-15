package com.org.android.presentation.home.activities

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.org.android.R
import com.org.android.data.models.Person
import com.org.android.databinding.ActivityTaskSecondBinding
import com.org.android.presentation.core.BaseActivity
import com.org.android.presentation.home.HomeViewModel
import com.org.android.presentation.home.adapter.PersonListAdapter
import com.org.android.presentation.utility.EndlessPaginationScrollListener
import com.org.android.presentation.utility.isNetworkAvailable
import org.koin.android.viewmodel.ext.android.viewModel


class TaskSecondActivity : BaseActivity() {
    private val homeViewModel: HomeViewModel by viewModel()

    override fun getBaseViewModel() = homeViewModel

    private lateinit var adapter: PersonListAdapter

    private var page: Int = 1
    private var hasMore: Boolean? = false
    private var isLoading: Boolean? = false

    private lateinit var binding: ActivityTaskSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        setupToolbar(
            binding.llToolbarMain.toolbar,
            getString(R.string.practical_task_2),
            true,
            Color.WHITE,
            toolbarColor = R.color.colorPrimary,
            backButtonColor = R.color.colorWhite
        )

        attachObserver()
        setupAdapter()
        getPersonList()

        binding.swipe.setOnRefreshListener {
            getPersonList()
        }

    }

    private fun attachObserver() {
        homeViewModel.personListRSLiveData.observe(this, Observer {
            it?.apply {
                updateAdapter(this.personList.orEmpty())
                hasMore = this.totalPages ?: 1 > this@TaskSecondActivity.page
                this@TaskSecondActivity.page++
            }
            hideSwipeProgressBar()
            isLoading = false
        })
    }

    private fun getPersonList() {
        if (isNetworkAvailable()) {
            isLoading = true
            homeViewModel.getPersonList(page)
            if (page == 1) {
                showSwipeProgressBar()
            }
        }
    }

    private fun setupAdapter() {
        adapter = PersonListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : EndlessPaginationScrollListener() {
            override fun requestNewPage() {
                super.requestNewPage()
                if (isLoading == false && hasMore!!) {
                    getPersonList()
                }
            }
        })
    }

    private fun updateAdapter(list: List<Person>) {
        if (page == 1) {
            adapter.setData(list)
        } else {
            adapter.addData(list)
        }
    }

    private fun showSwipeProgressBar() {
        if (binding.swipe.isRefreshing == false) {
            binding.swipe.isRefreshing = true
        }
    }

    private fun hideSwipeProgressBar() {
        if (binding.swipe.isRefreshing == true) {
            binding.swipe.isRefreshing = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}