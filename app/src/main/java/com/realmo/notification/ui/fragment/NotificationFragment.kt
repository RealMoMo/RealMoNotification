package com.realmo.notification.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.realmo.notification.R
import com.realmo.notification.adapter.NotificationAdapter
import com.realmo.notification.databinding.FragmentNotificationBinding
import com.realmo.notification.viewmodel.NotificationViewModel


/**
 * @name RealMoNotification
 * @author Realmo
 * @email   momo.weiye@gmail.com
 * @version 1.0.0
 * @time 2020/3/30 10:25
 * @describe
 */
class NotificationFragment : Fragment(){


     lateinit var viewModel:NotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val notificationBinding: FragmentNotificationBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        viewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)
        val adapter = NotificationAdapter()
        notificationBinding.rvNotification.adapter  = adapter
        subscribeUi(adapter)
        return notificationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun subscribeUi(adapter: NotificationAdapter) {
        //需要配置kotlin jvm为1.8以上
        viewModel.notifications.observe(viewLifecycleOwner) {
            adapter.submitList(it.values.toMutableList())
        }
    }


}