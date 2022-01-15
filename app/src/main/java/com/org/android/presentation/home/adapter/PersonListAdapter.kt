package com.org.android.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.org.android.R
import com.org.android.data.models.Person
import com.org.android.databinding.CellPhotoItemBinding
import com.org.android.presentation.core.BaseRecyclerViewAdapter
import com.org.android.presentation.utility.loadImage

class PersonListAdapter() : BaseRecyclerViewAdapter<Person>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cellPhotoItemBinding =
            CellPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(cellPhotoItemBinding)
    }

    override fun bindData(holder: RecyclerView.ViewHolder?, item: Person?, position: Int) {
        if (holder != null && item != null) {
            val viewHolder = holder as ViewHolder
            viewHolder.bindData(item)
        }
    }

    inner class ViewHolder(private val itemBinding: CellPhotoItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(item: Person) {
            //set or bind data
            itemBinding.ivImage.loadImage(
                item.avatar.orEmpty(),
                R.drawable.ic_user_placeholder
            )
            itemBinding.tvTitle.text = item.firstName.plus(item.lastName)
            itemBinding.tvEmail.text = item.email


            //onclick event
            itemBinding.root.setOnClickListener {

            }
        }
    }

    object PersonComprator : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }
}