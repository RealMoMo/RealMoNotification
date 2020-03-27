package com.realmo.notification

import android.app.NotificationManager
import android.content.Context
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

/**
 * @name RealMoNotification
 * @author Realmo
 * @email   momo.weiye@gmail.com
 * @version 1.0.0
 * @time 2020/3/27 9:30
 * @describe
 */
class RealMoNotificationListenerService : NotificationListenerService() {

    override fun onCreate() {
        super.onCreate()
        Log.d("realmo","RealMoNotificationListenerService onCreate:"+android.os.Process.myPid())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("realmo","RealMoNotificationListenerService onDestroy")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.d("realmo","onNotificationPosted")
        getNotificationInfo(sbn)
    }


    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.d("realmo","onNotificationRemoved")
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d("realmo","onListenerConnected")
    }



    fun getNotificationInfo(sbn: StatusBarNotification?){
        val notification = sbn?.notification

        if(notification!=null){
            Log.d("realmo","notification info:"+notification.toString())
            Log.d("realmo","notification ticker texx:"+notification.tickerText)
            Log.d("realmo","notification content title:"+notification.extras.getCharSequence("android.title"))
            Log.d("realmo","notification content text:"+notification.extras.getCharSequence("android.text"))

            val contentIntent = notification.contentIntent
            if(contentIntent!= null){
                Log.d("realmo","contentIntent:"+contentIntent.toString())
                //对应点击通知的事件
                contentIntent.send()
            }
        }


    }

}