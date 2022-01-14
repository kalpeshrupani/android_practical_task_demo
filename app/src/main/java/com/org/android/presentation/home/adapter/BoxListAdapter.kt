package com.org.android.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.org.android.R
import com.org.android.data.models.Person
import com.org.android.databinding.CellBoxItemBinding
import com.org.android.databinding.CellPhotoItemBinding
import com.org.android.presentation.core.BaseRecyclerViewAdapter
import com.org.android.presentation.utility.loadImage

class BoxListAdapter : BaseRecyclerViewAdapter<Int>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cellBoxItemBinding =
            CellBoxItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(cellBoxItemBinding)
    }

    override fun bindData(holder: RecyclerView.ViewHolder?, item: Int?, position: Int) {
        if (holder != null && item != null) {
            val viewHolder = holder as ViewHolder
            viewHolder.bindData(item)
        }
    }

    inner class ViewHolder(private val itemBinding: CellBoxItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(item: Int) {
            //set or bind data


            //onclick event
            itemBinding.root.setOnClickListener {

            }
        }
    }
}