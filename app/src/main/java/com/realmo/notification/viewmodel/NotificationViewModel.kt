package com.realmo.notification.viewmodel

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.realmo.notification.model.NotificationBean
import com.realmo.notification.service.RealMoNotificationListenerService
import com.realmo.notification.utils.parseToNotificationBean

/**
 * @name RealMoNotification
 * @author Realmo
 * @email   momo.weiye@gmail.com
 * @version 1.0.0
 * @time 2020/3/31 11:21
 * @describe
 */
class NotificationViewModel : ViewModel() {

    val notifications :MutableLiveData<MutableMap<Int,NotificationBean>> = MutableLiveData(getNotications())


    private fun getNotications() : MutableMap<Int,NotificationBean>{
        return RealMoNotificationListenerService.instance?.currentNotifications?.get()!!
    }
}


