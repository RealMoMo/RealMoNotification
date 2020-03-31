package com.realmo.notification.model

import android.app.Notification
import android.app.PendingIntent
import android.graphics.drawable.Icon

/**
 * @name RealMoNotification
 * @author Realmo
 * @email   momo.weiye@gmail.com
 * @version 1.0.0
 * @time 2020/3/30 11:01
 * @describe
 */
data class NotificationBean(
    val notification: Notification?,
    val id: Int,
    val contentTitle: CharSequence?,
    val contentText: CharSequence?,
    val packageName: String,
    val icon: Icon?,
    val contentIntent: PendingIntent?
) {
}