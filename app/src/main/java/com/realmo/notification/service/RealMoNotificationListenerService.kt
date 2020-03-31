package com.realmo.notification.service

import android.app.NotificationManager
import android.content.Context
import android.graphics.drawable.Icon
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.databinding.ObservableField
import com.realmo.notification.model.NotificationBean
import com.realmo.notification.utils.parseToNotificationBean
import org.greenrobot.eventbus.EventBus

/**
 * @name RealMoNotification
 * @author Realmo
 * @email   momo.weiye@gmail.com
 * @version 1.0.0
 * @time 2020/3/27 9:30
 * @describe
 *
 * Android利用NotificationListenerService实现消息盒子功能_移动开发_Vanswells的博客-CSDN博客  https://blog.csdn.net/Vanswells/article/details/81033280
 */
class RealMoNotificationListenerService : NotificationListenerService() {


    companion object{
        var instance : RealMoNotificationListenerService?=null
    }

    val currentNotifications : ObservableField<MutableMap<Int,NotificationBean>>  = ObservableField(HashMap())
    override fun onCreate() {
        super.onCreate()
        Log.d("momo","oncreate")
        instance = this
        //TODO Android8.0获取的size是0，应该需要系统签名
        val activeNotifications = getActiveNotifications()
        Log.d("momo","size:"+activeNotifications.size)
        activeNotifications?.forEach {
            val notificationBean = parseToNotificationBean(it)
            if(notificationBean != null){
                currentNotifications.get()?.put(notificationBean.id,notificationBean)
                Log.d("momo","bean:"+currentNotifications.toString())
            }
        }
        EventBus.getDefault().post(currentNotifications.get())
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }


    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
//        getNotificationInfo(sbn)

        val notificationBean =parseToNotificationBean(sbn)
        if(notificationBean != null){
            Log.d("momo","onNotificationPosted")
            currentNotifications.get()?.put(notificationBean.id,notificationBean)
            EventBus.getDefault().post(currentNotifications.get())
        }
    }


    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        val notificationBean =parseToNotificationBean(sbn)
        if(notificationBean != null){
            Log.d("momo","onNotificationRemoved")
            currentNotifications.get()?.remove(notificationBean.id)
            EventBus.getDefault().post(currentNotifications.get())
        }
    }

    override fun onListenerConnected() {
        super.onListenerConnected()

    }



    fun getNotificationInfo(sbn: StatusBarNotification?){

        parseToNotificationBean(sbn)


//        val notification = sbn?.notification
//
//        if(notification!=null){
//            Log.d("realmo","notification id:"+sbn.id)
//            Log.d("realmo","notification info:"+notification.toString())
//            Log.d("realmo","notification ticker texx:"+notification.tickerText)
//            Log.d("realmo","notification content title:"+notification.extras.getCharSequence("android.title"))
//            Log.d("realmo","notification content text:"+notification.extras.getCharSequence("android.text"))
//            val contentIntent = notification.contentIntent
//            if(contentIntent!= null){
//                Log.d("realmo","contentIntent:"+contentIntent.toString())
//                //对应点击通知的事件
//                contentIntent.send()
//            }
//        }


    }




}