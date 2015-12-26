/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.os.Bundle
 *  java.lang.Object
 */
package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.RemoteInputCompatBase;

final class NotificationCompat$Action$1
implements NotificationCompatBase.Action.Factory {
    NotificationCompat$Action$1() {
    }

    @Override
    public NotificationCompat.Action build(int n2, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInputCompatBase.RemoteInput[] arrremoteInput) {
        return new NotificationCompat.Action(n2, charSequence, pendingIntent, bundle, (RemoteInput[])arrremoteInput, null);
    }

    public NotificationCompat.Action[] newArray(int n2) {
        return new NotificationCompat.Action[n2];
    }
}

