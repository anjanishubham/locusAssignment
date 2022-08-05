package com.example.locusassignment.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.locusassignment.databinding.CommentItemLayoutBinding
import com.example.locusassignment.datamodule.ResponseDataItem

class CommentViewHolder(private val item : CommentItemLayoutBinding): RecyclerView.ViewHolder(item.root) {

   init {
       item.commentOnOff.setOnCheckedChangeListener { buttonView, isChecked ->
           if(isChecked){
               item.edComment.visibility= View.VISIBLE
           }else{
               item.edComment.visibility=View.GONE
           }
       }
   }
    fun showData(responseDataItem: ResponseDataItem) {
            item.tvCommentTitle.text = responseDataItem.title
    }

}