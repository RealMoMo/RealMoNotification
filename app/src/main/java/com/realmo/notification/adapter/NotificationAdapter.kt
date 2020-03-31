package com.realmo.notification.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.realmo.notification.databinding.ItemNotificationBinding
import com.realmo.notification.model.NotificationBean

/**
 * @name RealMoNotification
 * @author Realmo
 * @email   momo.weiye@gmail.com
 * @version 1.0.0
 * @time 2020/3/31 10:46
 * @describe
 */
class NotificationAdapter : ListAdapter<NotificationBean, RecyclerView.ViewHolder>(NotificationDiffCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NotificationViewHolder(ItemNotificationBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as NotificationViewHolder).bind(item)
    }

    class NotificationViewHolder(private val binding:ItemNotificationBinding)
        :RecyclerView.ViewHolder(binding.root){
        init {
            binding.setClickListener {

                binding.notification!!.contentIntent?.send()
            }
        }

        fun bind(item : NotificationBean){
            binding.apply {
                notification = item
                executePendingBindings()
            }
        }
    }


    private class NotificationDiffCallBack : DiffUtil.ItemCallback<NotificationBean>(){
        override fun areItemsTheSame(
            oldItem: NotificationBean,
            newItem: NotificationBean
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NotificationBean,
            newItem: NotificationBean
        ): Boolean {
            return oldItem.contentText!!.equals(newItem.contentText)
        }

    }

}
