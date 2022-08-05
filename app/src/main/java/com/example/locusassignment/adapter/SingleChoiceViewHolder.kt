package com.example.locusassignment.adapter

import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.locusassignment.databinding.ChoiseItemLayoutBinding
import com.example.locusassignment.datamodule.ResponseDataItem

class SingleChoiceViewHolder(private val item: ChoiseItemLayoutBinding): RecyclerView.ViewHolder(item.root) {

    fun showData(responseDataItem: ResponseDataItem) {
        item.tvTitle.text= responseDataItem.title
        item.radioGroup.orientation=LinearLayout.VERTICAL
        item.radioGroup.removeAllViews()
        responseDataItem.dataMap.options.forEach {
            val radioButton = RadioButton(item.root.context)
            radioButton.text = it
            item.radioGroup.addView(radioButton)
        }
    }
}