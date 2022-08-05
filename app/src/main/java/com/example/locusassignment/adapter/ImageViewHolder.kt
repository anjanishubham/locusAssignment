package com.example.locusassignment.adapter

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.locusassignment.databinding.ImageItemLayoutBinding
import com.example.locusassignment.datamodule.ResponseDataItem
import com.example.locusassignment.listeners.ClickListener

class ImageViewHolder(private val item: ImageItemLayoutBinding,
                        private val listener: ClickListener): RecyclerView.ViewHolder(item.root) {

    init {
            item.iv.setOnClickListener {
                listener.openCamera(adapterPosition)
            }
            item.ivRemoved.setOnClickListener {
                listener.removeImage(adapterPosition)
            }
    }
    fun showData(responseDataItem: ResponseDataItem) {
        item.tvTitle.text = responseDataItem.title
        item.iv.setImageURI(null)
       if(responseDataItem.dataMap.options.isNullOrEmpty().not() ){
           item.iv.setImageURI(Uri.parse(responseDataItem.dataMap.options.first()))
           item.ivRemoved.visibility = View.VISIBLE
       }else{
           item.ivRemoved.visibility = View.GONE
       }
    }

}