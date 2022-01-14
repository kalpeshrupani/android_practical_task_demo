package com.org.android.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.org.android.R
import com.org.android.databinding.CellBoxItemBinding
import com.org.android.presentation.core.BaseRecyclerViewAdapter

open class BoxListAdapter(
    val mContext: Context,
    val onRedboxClick: (data: Int, position: Int) -> Unit
) : BaseRecyclerViewAdapter<Int>() {
    val mListBlueColor = arrayListOf<Int>()
    var mRedPosition = -1
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
            if (adapterPosition == mRedPosition) {
                //Red Color
                itemBinding.imgBox.setBackgroundColor(
                    ContextCompat.getColor(
                        mContext,
                        R.color.colorRed
                    )
                )
            } else if (mListBlueColor.contains(adapterPosition)) {
                //Blue Color
                itemBinding.imgBox.setBackgroundColor(
                    ContextCompat.getColor(
                        mContext,
                        R.color.colorBlue
                    )
                )
            } else {
                //White Color
                itemBinding.imgBox.setBackgroundColor(
                    ContextCompat.getColor(
                        mContext,
                        R.color.colorWhite
                    )
                )
            }

            //onclick event
            itemBinding.root.setOnClickListener {
                if (adapterPosition == mRedPosition) {
                    mListBlueColor.add(adapterPosition)
                    mRedPosition = -1
                    notifyDataSetChanged()
                    //Enable Click
                    onRedboxClick(item, adapterPosition)
                }
            }
        }
    }
}