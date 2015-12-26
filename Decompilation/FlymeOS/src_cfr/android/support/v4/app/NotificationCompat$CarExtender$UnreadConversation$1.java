/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.RemoteInputCompatBase;

final class NotificationCompat$CarExtender$UnreadConversation$1
implements NotificationCompatBase.UnreadConversation.Factory {
    NotificationCompat$CarExtender$UnreadConversation$1() {
    }

    @Override
    public NotificationCompat.CarExtender.UnreadConversation build(String[] arrstring, RemoteInputCompatBase.RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] arrstring2, long l2) {
        return new NotificationCompat.CarExtender.UnreadConversation(arrstring, (RemoteInput)remoteInput, pendingIntent, pendingIntent2, arrstring2, l2);
    }
}

