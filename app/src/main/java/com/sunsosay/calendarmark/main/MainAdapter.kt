package com.sunsosay.calendarmark.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunsosay.calendarmark.R
import com.sunsosay.calendarmark.data.DueDate
import com.sunsosay.calendarmark.databinding.ItemEventBinding
import kotlin.properties.Delegates

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var dataVerifyLicense by Delegates.observable(listOf<DueDate>()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false))
    }

    override fun getItemCount() = dataVerifyLicense.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            tvTitle.text = dataVerifyLicense[position].title
            tvDetail.text = dataVerifyLicense[position].detail
            tvStarttime.text = dataVerifyLicense[position].startTime
            tvEndtime.text = dataVerifyLicense[position].endTime
            crdItemevent.setOnClickListener {
                val intent = Intent(holder.binding?.root?.context, MainActivity::class.java)
                intent.putExtra("dataEvent", dataVerifyLicense[position])
                holder.binding?.root?.context?.startActivity(intent)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemEventBinding? = null

        init {
            binding = DataBindingUtil.bind(view)
        }
    }
}