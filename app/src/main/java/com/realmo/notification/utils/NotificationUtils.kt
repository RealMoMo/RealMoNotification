package com.realmo.notification.utils

import android.service.notification.StatusBarNotification
import com.realmo.notification.model.NotificationBean

/**
 * @name RealMoNotification
 * @author Realmo
 * @email   momo.weiye@gmail.com
 * @version 1.0.0
 * @time 2020/3/31 19:06
 * @describe
 */
fun parseToNotificationBean(sbn: StatusBarNotification?) : NotificationBean?{
    val notification = sbn?.notification

    if(notification!=null) {
        return NotificationBean(
            notification,
            sbn.id,
            notification.extras.getCharSequence("android.title"),
            notification.extras.getCharSequence("android.text"),
            sbn.packageName,
            null,
            notification.contentIntent
        )
    }
    return null
}