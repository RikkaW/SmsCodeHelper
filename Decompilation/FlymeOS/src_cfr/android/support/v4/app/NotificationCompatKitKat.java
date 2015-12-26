/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.Notification$Action
 *  android.app.Notification$Builder
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.os.Bundle
 *  android.util.SparseArray
 *  android.widget.RemoteViews
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationBuilderWithActions;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.app.NotificationCompatJellybean;
import android.support.v4.app.RemoteInputCompatBase;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.List;

class NotificationCompatKitKat {
    NotificationCompatKitKat() {
    }

    public static NotificationCompatBase.Action getAction(Notification notification, int n2, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory2) {
        Notification.Action action = notification.actions[n2];
        Object var4_5 = null;
        SparseArray sparseArray = notification.extras.getSparseParcelableArray("android.support.actionExtras");
        notification = var4_5;
        if (sparseArray != null) {
            notification = (Bundle)sparseArray.get(n2);
        }
        return NotificationCompatJellybean.readAction(factory, factory2, action.icon, action.title, action.actionIntent, (Bundle)notification);
    }

    public static int getActionCount(Notification notification) {
        if (notification.actions != null) {
            return notification.actions.length;
        }
        return 0;
    }

    public static Bundle getExtras(Notification notification) {
        return notification.extras;
    }

    public static String getGroup(Notification notification) {
        return notification.extras.getString("android.support.groupKey");
    }

    public static boolean getLocalOnly(Notification notification) {
        return notification.extras.getBoolean("android.support.localOnly");
    }

    public static String getSortKey(Notification notification) {
        return notification.extras.getString("android.support.sortKey");
    }

    public static boolean isGroupSummary(Notification notification) {
        return notification.extras.getBoolean("android.support.isGroupSummary");
    }

    public static class Builder
    implements NotificationBuilderWithActions,
    NotificationBuilderWithBuilderAccessor {
        private Notification.Builder b;
        private List<Bundle> mActionExtrasList = new ArrayList();
        private Bundle mExtras;

        /*
         * Enabled aggressive block sorting
         */
        public Builder(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int n2, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int n3, int n4, boolean bl2, boolean bl3, boolean bl4, int n5, CharSequence charSequence4, boolean bl5, ArrayList<String> arrayList, Bundle bundle, String string2, boolean bl6, String string3) {
            context = new Notification.Builder(context).setWhen(notification.when).setShowWhen(bl3).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            bl3 = (notification.flags & 2) != 0;
            context = context.setOngoing(bl3);
            bl3 = (notification.flags & 8) != 0;
            context = context.setOnlyAlertOnce(bl3);
            bl3 = (notification.flags & 16) != 0;
            context = context.setAutoCancel(bl3).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            bl3 = (notification.flags & 128) != 0;
            this.b = context.setFullScreenIntent(pendingIntent2, bl3).setLargeIcon(bitmap).setNumber(n2).setUsesChronometer(bl4).setPriority(n5).setProgress(n3, n4, bl2);
            this.mExtras = new Bundle();
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                this.mExtras.putStringArray("android.people", (String[])arrayList.toArray((Object[])new String[arrayList.size()]));
            }
            if (bl5) {
                this.mExtras.putBoolean("android.support.localOnly", true);
            }
            if (string2 != null) {
                this.mExtras.putString("android.support.groupKey", string2);
                if (bl6) {
                    this.mExtras.putBoolean("android.support.isGroupSummary", true);
                } else {
                    this.mExtras.putBoolean("android.support.useSideChannel", true);
                }
            }
            if (string3 != null) {
                this.mExtras.putString("android.support.sortKey", string3);
            }
        }

        @Override
        public void addAction(NotificationCompatBase.Action action) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.b, action));
        }

        @Override
        public Notification build() {
            SparseArray<Bundle> sparseArray = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (sparseArray != null) {
                this.mExtras.putSparseParcelableArray("android.support.actionExtras", sparseArray);
            }
            this.b.setExtras(this.mExtras);
            return this.b.build();
        }

        @Override
        public Notification.Builder getBuilder() {
            return this.b;
        }
    }

}

