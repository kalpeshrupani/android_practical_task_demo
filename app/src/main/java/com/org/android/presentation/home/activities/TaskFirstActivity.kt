package com.org.android.presentation.home.activities

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.org.android.R
import com.org.android.databinding.ActivityTaskFirstBinding
import com.org.android.presentation.core.BaseActivity
import com.org.android.presentation.home.HomeViewModel
import com.org.android.presentation.home.adapter.BoxListAdapter
import com.org.android.presentation.utility.Logger
import com.org.android.presentation.utility.hideKeyboardFrom
import com.org.android.presentation.utility.showDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class TaskFirstActivity : BaseActivity() {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var adapter: BoxListAdapter
    override fun getBaseViewModel() = homeViewModel
    private lateinit var binding: ActivityTaskFirstBinding
    private var mRandomList = arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        setupToolbar(
            binding.llToolbarMain.toolbar,
            getString(R.string.practical_task_1),
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
            hideKeyboardFrom(it)
            if (isValid()) {
                val mNumber = binding.edtNumber.text.toString().toInt()
                val sqrtNumberOf = Math.sqrt(mNumber.toDouble())
                val mList = arrayListOf<Int>()
                for (i in 0..mNumber.minus(1)) {
                    mList.add(i)
                }
                (binding.recyclerViewBox.layoutManager as GridLayoutManager).spanCount =
                    sqrtNumberOf.toInt()
                adapter.updateData(mList)
                startColorChange(mList)
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
        Logger.d("====mNumber=" + mNumber)
        Logger.d("====msqrtNumberOf=" + sqrtNumberOf)
        if (sqrtNumberOf * sqrtNumberOf == mNumber) {
            return true
        }
        return false
    }

    private fun clearData() {
        adapter.mListBlueColor.clear()
        adapter.mRedPosition = -1
        mRandomList.clear()
        adapter.clear()
        adapter.notifyDataSetChanged()
    }

    private fun setupAdapter() {
        adapter = BoxListAdapter(this) { data: Int, position: Int ->
            if (mRandomList.size == 0) {
                showDialog("", getString(R.string.label_wow_you_did_it), getString(R.string.okay),
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                        clearData()
                    })
            } else {
                turnBoxRed()
            }
        }
        binding.recyclerViewBox.adapter = adapter
    }

    private fun startColorChange(mList: ArrayList<Int>) {
        mRandomList = mList.shuffled() as ArrayList<Int>
        Log.e("==randomList", mRandomList.joinToString())
        launch {
            delay(3000L)// 3 Second Timer
            runOnUiThread {
                turnBoxRed()
            }

        }
    }

    private fun turnBoxRed() {
        adapter.mRedPosition = mRandomList[0]
        adapter.notifyItemChanged(mRandomList[0])
        mRandomList.removeAt(0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}