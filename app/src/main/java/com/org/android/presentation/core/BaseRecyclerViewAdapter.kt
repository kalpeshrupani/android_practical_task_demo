package com.org.android.presentation.core

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Kalpesh Rupani
 * kameelrupani@gmail.com
 */


abstract class BaseRecyclerViewAdapter<T>(
    private var mList: ArrayList<T> = arrayListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return getViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(@NonNull holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.adapterPosition >= mList.size) {
            bindData(holder, null, position)
            return
        }
        val item = mList.get(position)
        bindData(holder, item, position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    protected fun onItemClick(position: Int, data: Any?) {}

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    abstract fun bindData(holder: RecyclerView.ViewHolder?, item: T?, position: Int)

    fun clear() {
        val oldSize = mList.size
        mList.clear()
        notifyItemRangeRemoved(0, oldSize)
    }

    fun updateData(list: List<T>) {
        this.mList = list as ArrayList<T>
        notifyDataSetChanged()
    }

    fun setData(list: List<T>) {
        this.mList.addAll(list)
        notifyItemRangeInserted(0, mList.size)
    }

    fun addData(list: List<T>) {
        val oldSize = mList.size
        mList.addAll(list)
        notifyItemRangeInserted(oldSize, mList.size)
    }

    fun updateItem(position: Int, item: T) {
        mList[position] = item
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        mList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(item: T) {
        val position = mList.size
        mList.add(item)
        notifyItemInserted(position)
    }
}