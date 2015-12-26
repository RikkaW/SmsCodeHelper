/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.RemoteInputCompatBase;

public class NotificationCompatBase {

    public static abstract class Action {
        public abstract PendingIntent getActionIntent();

        public abstract Bundle getExtras();

        public abstract int getIcon();

        public abstract RemoteInputCompatBase.RemoteInput[] getRemoteInputs();

        public abstract CharSequence getTitle();

        public static interface Factory {
            public Action build(int var1, CharSequence var2, PendingIntent var3, Bundle var4, RemoteInputCompatBase.RemoteInput[] var5);

            public Action[] newArray(int var1);
        }

    }

    public static abstract class UnreadConversation {
        abstract long getLatestTimestamp();

        abstract String[] getMessages();

        abstract String getParticipant();

        abstract String[] getParticipants();

        abstract PendingIntent getReadPendingIntent();

        abstract RemoteInputCompatBase.RemoteInput getRemoteInput();

        abstract PendingIntent getReplyPendingIntent();

        public static interface Factory {
            public UnreadConversation build(String[] var1, RemoteInputCompatBase.RemoteInput var2, PendingIntent var3, PendingIntent var4, String[] var5, long var6);
        }

    }

}

