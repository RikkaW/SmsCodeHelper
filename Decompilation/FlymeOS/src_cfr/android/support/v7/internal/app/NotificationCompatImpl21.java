/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.Notification$Builder
 *  android.app.Notification$MediaStyle
 *  android.media.session.MediaSession
 *  android.media.session.MediaSession$Token
 *  java.lang.Object
 */
package android.support.v7.internal.app;

import android.app.Notification;
import android.media.session.MediaSession;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

public class NotificationCompatImpl21 {
    public static void addMediaStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, int[] arrn, Object object) {
        notificationBuilderWithBuilderAccessor = new Notification.MediaStyle(notificationBuilderWithBuilderAccessor.getBuilder());
        if (arrn != null) {
            notificationBuilderWithBuilderAccessor.setShowActionsInCompactView(arrn);
        }
        if (object != null) {
            notificationBuilderWithBuilderAccessor.setMediaSession((MediaSession.Token)object);
        }
    }
}

