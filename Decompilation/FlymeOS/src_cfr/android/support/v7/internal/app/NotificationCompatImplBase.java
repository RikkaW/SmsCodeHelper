/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.Notification$Builder
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.SystemClock
 *  android.widget.RemoteViews
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.text.NumberFormat
 */
package android.support.v7.internal.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompatBase;
import android.support.v7.appcompat.R;
import android.widget.RemoteViews;
import java.text.NumberFormat;
import java.util.List;

public class NotificationCompatImplBase {
    static final int MAX_MEDIA_BUTTONS = 5;
    static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;

    /*
     * Enabled aggressive block sorting
     */
    private static RemoteViews applyStandardTemplate(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int n2, Bitmap bitmap, CharSequence charSequence4, boolean bl2, long l2, int n3, boolean bl3) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), n3);
        n3 = 0;
        int n4 = 0;
        if (bitmap != null && Build.VERSION.SDK_INT >= 16) {
            remoteViews.setImageViewBitmap(R.id.icon, bitmap);
        } else {
            remoteViews.setViewVisibility(R.id.icon, 8);
        }
        if (charSequence != null) {
            remoteViews.setTextViewText(R.id.title, charSequence);
        }
        if (charSequence2 != null) {
            remoteViews.setTextViewText(R.id.text, charSequence2);
            n3 = 1;
        }
        if (charSequence3 != null) {
            remoteViews.setTextViewText(R.id.info, charSequence3);
            remoteViews.setViewVisibility(R.id.info, 0);
            n2 = 1;
        } else if (n2 > 0) {
            if (n2 > context.getResources().getInteger(R.integer.status_bar_notification_info_maxnum)) {
                remoteViews.setTextViewText(R.id.info, (CharSequence)context.getResources().getString(R.string.status_bar_notification_info_overflow));
            } else {
                charSequence = NumberFormat.getIntegerInstance();
                remoteViews.setTextViewText(R.id.info, (CharSequence)charSequence.format((long)n2));
            }
            remoteViews.setViewVisibility(R.id.info, 0);
            n2 = 1;
        } else {
            remoteViews.setViewVisibility(R.id.info, 8);
            n2 = n3;
        }
        n3 = n4;
        if (charSequence4 != null) {
            n3 = n4;
            if (Build.VERSION.SDK_INT >= 16) {
                remoteViews.setTextViewText(R.id.text, charSequence4);
                if (charSequence2 != null) {
                    remoteViews.setTextViewText(R.id.text2, charSequence2);
                    remoteViews.setViewVisibility(R.id.text2, 0);
                    n3 = 1;
                } else {
                    remoteViews.setViewVisibility(R.id.text2, 8);
                    n3 = n4;
                }
            }
        }
        if (n3 != 0 && Build.VERSION.SDK_INT >= 16) {
            if (bl3) {
                float f2 = context.getResources().getDimensionPixelSize(R.dimen.notification_subtext_size);
                remoteViews.setTextViewTextSize(R.id.text, 0, f2);
            }
            remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
        }
        if (l2 != 0) {
            if (bl2) {
                remoteViews.setViewVisibility(R.id.chronometer, 0);
                remoteViews.setLong(R.id.chronometer, "setBase", SystemClock.elapsedRealtime() - System.currentTimeMillis() + l2);
                remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
            } else {
                remoteViews.setViewVisibility(R.id.time, 0);
                remoteViews.setLong(R.id.time, "setTime", l2);
            }
        }
        n3 = R.id.line3;
        n2 = n2 != 0 ? 0 : 8;
        remoteViews.setViewVisibility(n3, n2);
        return remoteViews;
    }

    private static <T extends NotificationCompatBase.Action> RemoteViews generateBigContentView(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int n2, Bitmap bitmap, CharSequence charSequence4, boolean bl2, long l2, List<T> list, boolean bl3, PendingIntent pendingIntent) {
        int n3 = Math.min((int)list.size(), (int)5);
        charSequence = NotificationCompatImplBase.applyStandardTemplate(context, charSequence, charSequence2, charSequence3, n2, bitmap, charSequence4, bl2, l2, NotificationCompatImplBase.getBigLayoutResource(n3), false);
        charSequence.removeAllViews(R.id.media_actions);
        if (n3 > 0) {
            for (n2 = 0; n2 < n3; ++n2) {
                charSequence2 = NotificationCompatImplBase.generateMediaActionButton(context, (NotificationCompatBase.Action)list.get(n2));
                charSequence.addView(R.id.media_actions, (RemoteViews)charSequence2);
            }
        }
        if (bl3) {
            charSequence.setViewVisibility(R.id.cancel_action, 0);
            charSequence.setInt(R.id.cancel_action, "setAlpha", context.getResources().getInteger(R.integer.cancel_button_image_alpha));
            charSequence.setOnClickPendingIntent(R.id.cancel_action, pendingIntent);
            return charSequence;
        }
        charSequence.setViewVisibility(R.id.cancel_action, 8);
        return charSequence;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static <T extends NotificationCompatBase.Action> RemoteViews generateContentView(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int n2, Bitmap bitmap, CharSequence charSequence4, boolean bl2, long l2, List<T> list, int[] arrn, boolean bl3, PendingIntent pendingIntent) {
        charSequence = NotificationCompatImplBase.applyStandardTemplate(context, charSequence, charSequence2, charSequence3, n2, bitmap, charSequence4, bl2, l2, R.layout.notification_template_media, true);
        int n3 = list.size();
        n2 = arrn == null ? 0 : Math.min((int)arrn.length, (int)3);
        charSequence.removeAllViews(R.id.media_actions);
        if (n2 > 0) {
            for (int i2 = 0; i2 < n2; ++i2) {
                if (i2 >= n3) {
                    throw new IllegalArgumentException(String.format((String)"setShowActionsInCompactView: action %d out of bounds (max %d)", (Object[])new Object[]{i2, n3 - 1}));
                }
                charSequence2 = NotificationCompatImplBase.generateMediaActionButton(context, (NotificationCompatBase.Action)list.get(arrn[i2]));
                charSequence.addView(R.id.media_actions, (RemoteViews)charSequence2);
            }
        }
        if (bl3) {
            charSequence.setViewVisibility(R.id.end_padder, 8);
            charSequence.setViewVisibility(R.id.cancel_action, 0);
            charSequence.setOnClickPendingIntent(R.id.cancel_action, pendingIntent);
            charSequence.setInt(R.id.cancel_action, "setAlpha", context.getResources().getInteger(R.integer.cancel_button_image_alpha));
            return charSequence;
        }
        charSequence.setViewVisibility(R.id.end_padder, 0);
        charSequence.setViewVisibility(R.id.cancel_action, 8);
        return charSequence;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static RemoteViews generateMediaActionButton(Context context, NotificationCompatBase.Action action) {
        boolean bl2 = action.getActionIntent() == null;
        context = new RemoteViews(context.getPackageName(), R.layout.notification_media_action);
        context.setImageViewResource(R.id.action0, action.getIcon());
        if (!bl2) {
            context.setOnClickPendingIntent(R.id.action0, action.getActionIntent());
        }
        context.setContentDescription(R.id.action0, action.getTitle());
        return context;
    }

    private static int getBigLayoutResource(int n2) {
        if (n2 <= 3) {
            return R.layout.notification_template_big_media_narrow;
        }
        return R.layout.notification_template_big_media;
    }

    public static <T extends NotificationCompatBase.Action> void overrideBigContentView(Notification notification, Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int n2, Bitmap bitmap, CharSequence charSequence4, boolean bl2, long l2, List<T> list, boolean bl3, PendingIntent pendingIntent) {
        notification.bigContentView = NotificationCompatImplBase.generateBigContentView(context, charSequence, charSequence2, charSequence3, n2, bitmap, charSequence4, bl2, l2, list, bl3, pendingIntent);
        if (bl3) {
            notification.flags |= 2;
        }
    }

    public static <T extends NotificationCompatBase.Action> void overrideContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int n2, Bitmap bitmap, CharSequence charSequence4, boolean bl2, long l2, List<T> list, int[] arrn, boolean bl3, PendingIntent pendingIntent) {
        context = NotificationCompatImplBase.generateContentView(context, charSequence, charSequence2, charSequence3, n2, bitmap, charSequence4, bl2, l2, list, arrn, bl3, pendingIntent);
        notificationBuilderWithBuilderAccessor.getBuilder().setContent((RemoteViews)context);
        if (bl3) {
            notificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
        }
    }
}

