package com.org.android.presentation.home.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.org.android.R
import com.org.android.databinding.ActivityTaskFirstBinding
import com.org.android.databinding.ActivityTaskSecondBinding
import com.org.android.presentation.core.BaseActivity
import com.org.android.presentation.home.HomeViewModel
import com.org.android.presentation.home.adapter.BoxListAdapter
import com.org.android.presentation.home.adapter.PersonListAdapter
import com.org.android.presentation.utility.EndlessPaginationScrollListener
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.logging.Logger

class TaskFirstActivity : BaseActivity() {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var adapter: BoxListAdapter
    override fun getBaseViewModel() = homeViewModel
    private lateinit var binding: ActivityTaskFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setupToolbar(
            binding.llToolbarMain.toolbar,
            getString(R.string.title_grid_list),
            true,
            Color.WHITE,
            toolbarColor = R.color.colorPrimary,
            backButtonColor = R.color.colorWhite
        )
        initClickListener()
        setupAdapter()

    }

    private fun initClickListener() {
        binding.btnSubmit.setOnClickListener {
            clearData()
            if (isValid()) {
                val mNumber = binding.edtNumber.text.toString().toInt()
                val sqrtNumberOf = Math.sqrt(mNumber.toDouble())
                val mList = arrayListOf<Int>()
                for (i in 0..mNumber.minus(1)) {
                    mList.add(i)
                }
                (binding.recyclerViewBox.layoutManager as GridLayoutManager).spanCount =
                    sqrtNumberOf.toInt()
                adapter.resetData(mList)
            }
        }
    }

    private fun isValid(): Boolean {
        if (binding.edtNumber.text.toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show()
            return false
        }
        val mNumber = binding.edtNumber.text.toString().toDouble()
        if (mNumber <= 0) {
            Toast.makeText(
                this,
                getString(R.string.validation_number_greater_than_zero),
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (!checkSquareRoot(mNumber)) {
            Toast.makeText(this, getString(R.string.validation_root_number), Toast.LENGTH_SHORT)
                .show()

            return false
        }
        return true
    }

    private fun checkSquareRoot(mNumber: Double): Boolean {
        val sqrtNumberOf = Math.sqrt(mNumber)
        com.org.android.presentation.utility.Logger.d("====mNumber=" + mNumber)
        com.org.android.presentation.utility.Logger.d("====msqrtNumberOf=" + sqrtNumberOf)
        if (sqrtNumberOf * sqrtNumberOf == mNumber) {
            return true
        }
        return false
    }

    private fun clearData() {
        adapter.clear()
        adapter.notifyDataSetChanged()
    }

    private fun setupAdapter() {
        adapter = BoxListAdapter()
        binding.recyclerViewBox.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}