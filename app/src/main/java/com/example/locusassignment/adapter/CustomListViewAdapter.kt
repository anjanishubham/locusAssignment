package com.example.locusassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.locusassignment.databinding.ChoiseItemLayoutBinding
import com.example.locusassignment.databinding.CommentItemLayoutBinding
import com.example.locusassignment.databinding.ImageItemLayoutBinding
import com.example.locusassignment.datamodule.ResponseData
import com.example.locusassignment.listeners.ClickListener

class CustomListViewAdapter(private val data: ResponseData,
                            private val listener: ClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(viewType){
            VIEW_TYPE_IMAGE -> {
                binding= ImageItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return ImageViewHolder(binding as ImageItemLayoutBinding,listener)
            }
            VIEW_TYPE_CHOICE -> {
                binding= ChoiseItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return SingleChoiceViewHolder(binding as ChoiseItemLayoutBinding)
            }
            VIEW_TYPE_COMMENT ->{
                binding= CommentItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return CommentViewHolder(binding as CommentItemLayoutBinding)
            }
            else -> {
                binding = ImageItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CommentViewHolder(binding as CommentItemLayoutBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ImageViewHolder -> {
                holder.showData(data[position])
            }
            is SingleChoiceViewHolder ->{
                holder.showData(data[position])
            }
            is CommentViewHolder ->{
                holder.showData(data[position])
            }
        }
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun getItemViewType(position: Int): Int {

        when(data[position].type){

            "PHOTO" ->{
                return VIEW_TYPE_IMAGE

            }
            "SINGLE_CHOICE" ->{
                return VIEW_TYPE_CHOICE
            }
            "COMMENT" ->{
                return VIEW_TYPE_COMMENT
            }
            else ->{
                return VIEW_TYPE_COMMENT
            }
        }

    }

    companion object {
      private  const val VIEW_TYPE_IMAGE = 1
      private  const val VIEW_TYPE_CHOICE = 2
      private  const val VIEW_TYPE_COMMENT = 3
    }
}