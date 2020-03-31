package com.realmo.notification.viewmodel

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.realmo.notification.model.NotificationBean
import com.realmo.notification.service.RealMoNotificationListenerService
import com.realmo.notification.utils.parseToNotificationBean
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * @name RealMoNotification
 * @author Realmo
 * @email   momo.weiye@gmail.com
 * @version 1.0.0
 * @time 2020/3/31 11:21
 * @describe
 */
class NotificationViewModel: ViewModel() {

    init {
        EventBus.getDefault().register(this)
    }

    var notifications :MutableLiveData<MutableMap<Int,NotificationBean>> = MutableLiveData(getNotications())


    private fun getNotications() : MutableMap<Int,NotificationBean>{
        return RealMoNotificationListenerService.instance?.currentNotifications?.get() ?:HashMap<Int,NotificationBean>()

    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun init(notificationMap : MutableMap<Int,NotificationBean>){
        Log.d("momo","init")
//        notifications = MutableLiveData(RealMoNotificationListenerService.instance?.currentNotifications?.get()!!)
//        notifications.value?.put(1,NotificationBean(null,1,"111","1111","haha",null,null))
//        val map : MutableMap<Int,NotificationBean> = HashMap()
//        map.put(1,NotificationBean(null,1,"111","1111","haha",null,null))
        notifications.postValue(RealMoNotificationListenerService.instance?.currentNotifications?.get())
        Log.d("momo","init size:"+ notifications.value?.size)

    }
}


