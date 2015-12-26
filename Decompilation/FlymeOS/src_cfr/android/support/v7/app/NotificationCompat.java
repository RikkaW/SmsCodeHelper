/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 *  java.util.ArrayList
 */
package android.support.v7.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.internal.app.NotificationCompatImpl21;
import android.support.v7.internal.app.NotificationCompatImplBase;
import java.util.ArrayList;

public class NotificationCompat
extends android.support.v4.app.NotificationCompat {
    private static void addBigMediaStyleToBuilderJellybean(Notification notification, NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof MediaStyle) {
            MediaStyle mediaStyle = (MediaStyle)builder.mStyle;
            NotificationCompatImplBase.overrideBigContentView(notification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.mNotification.when, builder.mActions, mediaStyle.mShowCancelButton, mediaStyle.mCancelButtonIntent);
        }
    }

    private static void addMediaStyleToBuilderIcs(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof MediaStyle) {
            MediaStyle mediaStyle = (MediaStyle)builder.mStyle;
            NotificationCompatImplBase.overrideContentView(notificationBuilderWithBuilderAccessor, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.mNotification.when, builder.mActions, mediaStyle.mActionsToShowInCompact, mediaStyle.mShowCancelButton, mediaStyle.mCancelButtonIntent);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void addMediaStyleToBuilderLollipop(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, NotificationCompat.Style object) {
        if (object instanceof MediaStyle) {
            object = (MediaStyle)object;
            int[] arrn = object.mActionsToShowInCompact;
            object = object.mToken != null ? object.mToken.getToken() : null;
            NotificationCompatImpl21.addMediaStyle(notificationBuilderWithBuilderAccessor, arrn, object);
        }
    }

    public static class Builder
    extends NotificationCompat.Builder {
        public Builder(Context context) {
            super(context);
        }

        @Override
        protected NotificationCompat.BuilderExtender getExtender() {
            if (Build.VERSION.SDK_INT >= 21) {
                return new LollipopExtender();
            }
            if (Build.VERSION.SDK_INT >= 16) {
                return new JellybeanExtender();
            }
            if (Build.VERSION.SDK_INT >= 14) {
                return new IceCreamSandwichExtender();
            }
            return super.getExtender();
        }
    }

    static class IceCreamSandwichExtender
    extends NotificationCompat.BuilderExtender {
        private IceCreamSandwichExtender() {
        }

        @Override
        public Notification build(NotificationCompat.Builder builder, NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationCompat.addMediaStyleToBuilderIcs(notificationBuilderWithBuilderAccessor, builder);
            return notificationBuilderWithBuilderAccessor.build();
        }
    }

    static class JellybeanExtender
    extends NotificationCompat.BuilderExtender {
        private JellybeanExtender() {
        }

        @Override
        public Notification build(NotificationCompat.Builder builder, NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationCompat.addMediaStyleToBuilderIcs(notificationBuilderWithBuilderAccessor, builder);
            notificationBuilderWithBuilderAccessor = notificationBuilderWithBuilderAccessor.build();
            NotificationCompat.addBigMediaStyleToBuilderJellybean((Notification)notificationBuilderWithBuilderAccessor, builder);
            return notificationBuilderWithBuilderAccessor;
        }
    }

    static class LollipopExtender
    extends NotificationCompat.BuilderExtender {
        private LollipopExtender() {
        }

        @Override
        public Notification build(NotificationCompat.Builder builder, NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationCompat.addMediaStyleToBuilderLollipop(notificationBuilderWithBuilderAccessor, builder.mStyle);
            return notificationBuilderWithBuilderAccessor.build();
        }
    }

    public static class MediaStyle
    extends NotificationCompat.Style {
        int[] mActionsToShowInCompact = null;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        MediaSessionCompat.Token mToken;

        public MediaStyle() {
        }

        public MediaStyle(NotificationCompat.Builder builder) {
            this.setBuilder(builder);
        }

        public void setCancelButtonIntent(PendingIntent pendingIntent) {
            this.mCancelButtonIntent = pendingIntent;
        }

        public MediaStyle setMediaSession(MediaSessionCompat.Token token) {
            this.mToken = token;
            return this;
        }

        public /* varargs */ MediaStyle setShowActionsInCompactView(int ... arrn) {
            this.mActionsToShowInCompact = arrn;
            return this;
        }

        public void setShowCancelButton(boolean bl2) {
            this.mShowCancelButton = bl2;
        }
    }

}

