/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.Notification$Builder
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.widget.RemoteViews
 *  java.lang.Object
 */
package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.widget.RemoteViews;

class NotificationCompatIceCreamSandwich {
    NotificationCompatIceCreamSandwich() {
    }

    public static class Builder
    implements NotificationBuilderWithBuilderAccessor {
        private Notification.Builder b;

        /*
         * Enabled aggressive block sorting
         */
        public Builder(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int n2, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int n3, int n4, boolean bl2) {
            context = new Notification.Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            boolean bl3 = (notification.flags & 2) != 0;
            context = context.setOngoing(bl3);
            bl3 = (notification.flags & 8) != 0;
            context = context.setOnlyAlertOnce(bl3);
            bl3 = (notification.flags & 16) != 0;
            context = context.setAutoCancel(bl3).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            bl3 = (notification.flags & 128) != 0;
            this.b = context.setFullScreenIntent(pendingIntent2, bl3).setLargeIcon(bitmap).setNumber(n2).setProgress(n3, n4, bl2);
        }

        @Override
        public Notification build() {
            return this.b.getNotification();
        }

        @Override
        public Notification.Builder getBuilder() {
            return this.b;
        }
    }

}

