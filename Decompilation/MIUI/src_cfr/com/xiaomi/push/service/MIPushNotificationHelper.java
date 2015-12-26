/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.ActivityManager
 *  android.app.ActivityManager$RunningAppProcessInfo
 *  android.app.Notification
 *  android.app.Notification$BigTextStyle
 *  android.app.Notification$Builder
 *  android.app.Notification$Style
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.text.TextUtils
 *  android.util.DisplayMetrics
 *  android.util.Pair
 *  android.widget.RemoteViews
 *  java.lang.Class
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 *  java.lang.reflect.Method
 *  java.text.SimpleDateFormat
 *  java.util.Arrays
 *  java.util.Date
 *  java.util.LinkedList
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.widget.RemoteViews;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MIPushNotificationHelper {
    public static long lastNotify = 0;
    private static LinkedList<Pair<Integer, String>> notifyIDCache = new LinkedList();

    static void clearLocalNotifyType(Context context, String string2) {
        context.getSharedPreferences("pref_notify_type", 0).edit().remove(string2).commit();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void clearNotification(Context linkedList, String string2) {
        NotificationManager notificationManager = (NotificationManager)linkedList.getSystemService("notification");
        linkedList = notifyIDCache;
        synchronized (linkedList) {
            Iterator iterator = ((LinkedList)notifyIDCache.clone()).iterator();
            while (iterator.hasNext()) {
                Pair pair = (Pair)iterator.next();
                if (!TextUtils.equals((CharSequence)((CharSequence)pair.second), (CharSequence)string2)) continue;
                notificationManager.cancel(((Integer)pair.first).intValue());
                notifyIDCache.remove((Object)pair);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void clearNotification(Context linkedList, String string2, int n) {
        linkedList = (NotificationManager)linkedList.getSystemService("notification");
        n = string2.hashCode() / 10 * 10 + n;
        linkedList.cancel(n);
        linkedList = notifyIDCache;
        synchronized (linkedList) {
            for (Pair pair : notifyIDCache) {
                if (n != (Integer)pair.first || !TextUtils.equals((CharSequence)string2, (CharSequence)((CharSequence)pair.second))) continue;
                notifyIDCache.remove((Object)pair);
                break;
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String[] determineTitleAndDespByDIP(Context object, PushMetaInfo object2) {
        Object object3 = object2.getTitle();
        String string2 = object2.getDescription();
        Map<String, String> map = object2.getExtra();
        Object object4 = string2;
        object2 = object3;
        if (map == null) return new String[]{object2, object4};
        {
            int n = object.getResources().getDisplayMetrics().widthPixels;
            float f2 = object.getResources().getDisplayMetrics().density;
            if ((n = Float.valueOf((float)((float)n / f2 + 0.5f)).intValue()) <= 320) {
                object = map.get("title_short");
                if (!TextUtils.isEmpty((CharSequence)object)) {
                    object3 = object;
                }
                object = map.get("description_short");
                object4 = string2;
                object2 = object3;
                if (TextUtils.isEmpty((CharSequence)object)) return new String[]{object2, object4};
                {
                    object4 = object;
                    object2 = object3;
                    return new String[]{object2, object4};
                }
            } else {
                object4 = string2;
                object2 = object3;
                if (n <= 360) return new String[]{object2, object4};
                {
                    object = map.get("title_long");
                    if (!TextUtils.isEmpty((CharSequence)object)) {
                        object3 = object;
                    }
                    object = map.get("description_long");
                    object4 = string2;
                    object2 = object3;
                    if (TextUtils.isEmpty((CharSequence)object)) return new String[]{object2, object4};
                    {
                        object4 = object;
                        object2 = object3;
                    }
                }
            }
        }
        return new String[]{object2, object4};
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Bitmap drawableToBitmap(Drawable drawable2) {
        int n;
        if (drawable2 instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable2).getBitmap();
        }
        int n2 = drawable2.getIntrinsicWidth();
        if (n2 <= 0) {
            n2 = 1;
        }
        if ((n = drawable2.getIntrinsicHeight()) <= 0) {
            n = 1;
        }
        Bitmap bitmap = Bitmap.createBitmap((int)n2, (int)n, (Bitmap.Config)Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable2.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable2.draw(canvas);
        return bitmap;
    }

    private static Bitmap getBitmapFromId(Context context, int n) {
        return MIPushNotificationHelper.drawableToBitmap(context.getResources().getDrawable(n));
    }

    private static PendingIntent getClickedPendingIntent(Context context, XmPushActionContainer xmPushActionContainer, PushMetaInfo pushMetaInfo, byte[] arrby) {
        if (pushMetaInfo != null && !TextUtils.isEmpty((CharSequence)pushMetaInfo.url)) {
            xmPushActionContainer = new Intent("android.intent.action.VIEW");
            xmPushActionContainer.setData(Uri.parse((String)pushMetaInfo.url));
            xmPushActionContainer.addFlags(268435456);
            return PendingIntent.getActivity((Context)context, (int)0, (Intent)xmPushActionContainer, (int)134217728);
        }
        if (MIPushNotificationHelper.isBusinessMessage(xmPushActionContainer)) {
            xmPushActionContainer = new Intent();
            xmPushActionContainer.setComponent(new ComponentName("com.xiaomi.xmsf", "com.xiaomi.mipush.sdk.PushMessageHandler"));
            xmPushActionContainer.putExtra("mipush_payload", arrby);
            xmPushActionContainer.putExtra("mipush_notified", true);
            xmPushActionContainer.addCategory(String.valueOf((int)pushMetaInfo.getNotifyId()));
            return PendingIntent.getService((Context)context, (int)0, (Intent)xmPushActionContainer, (int)134217728);
        }
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setComponent(new ComponentName(xmPushActionContainer.packageName, "com.xiaomi.mipush.sdk.PushMessageHandler"));
        intent.putExtra("mipush_payload", arrby);
        intent.putExtra("mipush_notified", true);
        intent.addCategory(String.valueOf((int)pushMetaInfo.getNotifyId()));
        return PendingIntent.getService((Context)context, (int)0, (Intent)intent, (int)134217728);
    }

    private static int getIconId(Context context, String string2, String string3) {
        if (string2.equals((Object)context.getPackageName())) {
            return context.getResources().getIdentifier(string3, "drawable", string2);
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static int getIdForSmallIcon(Context context, String string2) {
        int n = MIPushNotificationHelper.getIconId(context, string2, "mipush_notification");
        int n2 = MIPushNotificationHelper.getIconId(context, string2, "mipush_small_notification");
        if (n <= 0) {
            n = n2 > 0 ? n2 : context.getApplicationInfo().icon;
        }
        n2 = n;
        if (n != 0) return n2;
        n2 = n;
        if (Build.VERSION.SDK_INT < 9) return n2;
        return context.getApplicationInfo().logo;
    }

    static int getLocalNotifyType(Context context, String string2) {
        return context.getSharedPreferences("pref_notify_type", 0).getInt(string2, Integer.MAX_VALUE);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static RemoteViews getNotificationForCustomLayout(Context object, XmPushActionContainer object2, byte[] object3) {
        String string2;
        Object object4;
        Iterator iterator;
        Resources resources;
        object3 = object2.getMetaInfo();
        String string3 = MIPushNotificationHelper.getTargetPackage((XmPushActionContainer)object2);
        if ((object3 = object3.getExtra()) == null) {
            return null;
        }
        object2 = object3.get("layout_name");
        object3 = object3.get("layout_value");
        if (TextUtils.isEmpty((CharSequence)object2)) return null;
        if (TextUtils.isEmpty((CharSequence)object3)) {
            return null;
        }
        object = object.getPackageManager();
        try {
            resources = object.getResourcesForApplication(string3);
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            MyLog.e((Throwable)var0_1);
            return null;
        }
        int n = resources.getIdentifier((String)object2, "layout", string3);
        if (n == 0) {
            return null;
        }
        object2 = new RemoteViews(string3, n);
        try {
            object3 = new JSONObject((String)object3);
            if (object3.has("text")) {
                object = object3.getJSONObject("text");
                iterator = object.keys();
                while (iterator.hasNext()) {
                    object4 = (String)iterator.next();
                    string2 = object.getString((String)object4);
                    n = resources.getIdentifier((String)object4, "id", string3);
                    if (n <= 0) continue;
                    object2.setTextViewText(n, (CharSequence)string2);
                }
            }
            if (object3.has("image")) {
                object = object3.getJSONObject("image");
                iterator = object.keys();
                while (iterator.hasNext()) {
                    object4 = (String)iterator.next();
                    string2 = object.getString((String)object4);
                    n = resources.getIdentifier((String)object4, "id", string3);
                    int n2 = resources.getIdentifier(string2, "drawable", string3);
                    if (n <= 0) continue;
                    object2.setImageViewResource(n, n2);
                }
            }
        }
        catch (JSONException var0_2) {
            MyLog.e((Throwable)var0_2);
            return null;
        }
        object = object2;
        if (!object3.has("time")) return object;
        iterator = object3.getJSONObject("time");
        object4 = iterator.keys();
        do {
            object = object2;
            if (!object4.hasNext()) return object;
            string2 = (String)object4.next();
            object = object3 = iterator.getString(string2);
            if (object3.length() == 0) {
                object = "yy-MM-dd hh:mm";
            }
            if ((n = resources.getIdentifier(string2, "id", string3)) <= 0) continue;
            long l = System.currentTimeMillis();
            object2.setTextViewText(n, (CharSequence)new SimpleDateFormat((String)object).format(new Date(l)));
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"NewApi"})
    private static Notification getNotificationForLargeIcons(Context object, XmPushActionContainer xmPushActionContainer, byte[] object2, RemoteViews map, PendingIntent pendingIntent) {
        PushMetaInfo pushMetaInfo = xmPushActionContainer.getMetaInfo();
        object2 = new Notification.Builder((Context)object);
        String[] arrstring = MIPushNotificationHelper.determineTitleAndDespByDIP((Context)object, pushMetaInfo);
        object2.setContentTitle((CharSequence)arrstring[0]);
        object2.setContentText((CharSequence)arrstring[1]);
        if (map != null) {
            object2.setContent(map);
        } else if (Build.VERSION.SDK_INT >= 16) {
            object2.setStyle((Notification.Style)new Notification.BigTextStyle().bigText((CharSequence)arrstring[1]));
        }
        object2.setWhen(System.currentTimeMillis());
        object2.setContentIntent(pendingIntent);
        int n = MIPushNotificationHelper.getIconId((Context)object, MIPushNotificationHelper.getTargetPackage(xmPushActionContainer), "mipush_notification");
        int n2 = MIPushNotificationHelper.getIconId((Context)object, MIPushNotificationHelper.getTargetPackage(xmPushActionContainer), "mipush_small_notification");
        if (n > 0 && n2 > 0) {
            object2.setLargeIcon(MIPushNotificationHelper.getBitmapFromId((Context)object, n));
            object2.setSmallIcon(n2);
        } else {
            object2.setSmallIcon(MIPushNotificationHelper.getIdForSmallIcon((Context)object, MIPushNotificationHelper.getTargetPackage(xmPushActionContainer)));
        }
        object2.setAutoCancel(true);
        long l = System.currentTimeMillis();
        map = pushMetaInfo.getExtra();
        if (map != null && map.containsKey("ticker")) {
            object2.setTicker((CharSequence)map.get("ticker"));
        }
        if (l - lastNotify > 10000) {
            lastNotify = l;
            n = pushMetaInfo.notifyType;
            if (MIPushNotificationHelper.hasLocalNotifyType((Context)object, MIPushNotificationHelper.getTargetPackage(xmPushActionContainer))) {
                n = MIPushNotificationHelper.getLocalNotifyType((Context)object, MIPushNotificationHelper.getTargetPackage(xmPushActionContainer));
            }
            object2.setDefaults(n);
            if (map != null && (n & 1) != 0 && !TextUtils.isEmpty((CharSequence)(object = map.get("sound_uri"))) && object.startsWith("android.resource://" + MIPushNotificationHelper.getTargetPackage(xmPushActionContainer))) {
                object2.setDefaults(n ^ 1);
                object2.setSound(Uri.parse((String)object));
            }
        }
        return object2.getNotification();
    }

    static String getTargetPackage(XmPushActionContainer xmPushActionContainer) {
        Object object;
        if ("com.xiaomi.xmsf".equals((Object)xmPushActionContainer.packageName) && (object = xmPushActionContainer.getMetaInfo()) != null && object.getExtra() != null && !TextUtils.isEmpty((CharSequence)(object = object.getExtra().get("miui_package_name")))) {
            return object;
        }
        return xmPushActionContainer.packageName;
    }

    static boolean hasLocalNotifyType(Context context, String string2) {
        return context.getSharedPreferences("pref_notify_type", 0).contains(string2);
    }

    public static boolean isApplicationForeground(Context iterator, String string2) {
        if ((iterator = ((ActivityManager)iterator.getSystemService("activity")).getRunningAppProcesses()) != null) {
            iterator = iterator.iterator();
            while (iterator.hasNext()) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)iterator.next();
                if (runningAppProcessInfo.importance != 100 || !Arrays.asList((Object[])runningAppProcessInfo.pkgList).contains(string2)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isBusinessMessage(XmPushActionContainer cloneable) {
        if ((cloneable = cloneable.getMetaInfo()) != null && cloneable.isIgnoreRegInfo()) {
            return true;
        }
        return false;
    }

    public static boolean isNotifyForeground(Map<String, String> map) {
        if (map == null || !map.containsKey("notify_foreground")) {
            return true;
        }
        return "1".equals((Object)map.get("notify_foreground"));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void notifyPushMessage(Context linkedList, XmPushActionContainer xmPushActionContainer, byte[] object) {
        int n;
        NotificationManager notificationManager = (NotificationManager)linkedList.getSystemService("notification");
        PushMetaInfo pushMetaInfo = xmPushActionContainer.getMetaInfo();
        RemoteViews remoteViews = MIPushNotificationHelper.getNotificationForCustomLayout((Context)linkedList, xmPushActionContainer, (byte[])object);
        Object object2 = MIPushNotificationHelper.getClickedPendingIntent((Context)linkedList, xmPushActionContainer, pushMetaInfo, (byte[])object);
        if (object2 == null) {
            MyLog.warn("The click PendingIntent is null. ");
            return;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            object = (Object)MIPushNotificationHelper.getNotificationForLargeIcons((Context)linkedList, xmPushActionContainer, (byte[])object, remoteViews, (PendingIntent)object2);
        } else {
            long l;
            object = new Notification(MIPushNotificationHelper.getIdForSmallIcon((Context)linkedList, MIPushNotificationHelper.getTargetPackage(xmPushActionContainer)), null, System.currentTimeMillis());
            String[] arrstring = MIPushNotificationHelper.determineTitleAndDespByDIP((Context)linkedList, pushMetaInfo);
            try {
                object.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(object, new Object[]{linkedList, arrstring[0], arrstring[1], object2});
            }
            catch (NoSuchMethodException var9_7) {
                MyLog.e(var9_7);
            }
            catch (IllegalAccessException var9_8) {
                MyLog.e(var9_8);
            }
            catch (IllegalArgumentException var9_9) {
                MyLog.e(var9_9);
            }
            catch (InvocationTargetException var9_10) {
                MyLog.e(var9_10);
            }
            if ((object2 = pushMetaInfo.getExtra()) != null && object2.containsKey("ticker")) {
                object.tickerText = (CharSequence)object2.get("ticker");
            }
            if ((l = System.currentTimeMillis()) - lastNotify > 10000) {
                lastNotify = l;
                n = pushMetaInfo.notifyType;
                if (MIPushNotificationHelper.hasLocalNotifyType(linkedList, MIPushNotificationHelper.getTargetPackage(xmPushActionContainer))) {
                    n = MIPushNotificationHelper.getLocalNotifyType(linkedList, MIPushNotificationHelper.getTargetPackage(xmPushActionContainer));
                }
                object.defaults = n;
                if (object2 != null && (n & 1) != 0 && !TextUtils.isEmpty((CharSequence)(object2 = (String)object2.get("sound_uri"))) && object2.startsWith("android.resource://" + MIPushNotificationHelper.getTargetPackage(xmPushActionContainer))) {
                    object.defaults = n ^ 1;
                    object.sound = Uri.parse((String)object2);
                }
            }
            object.flags |= 16;
            if (remoteViews != null) {
                object.contentView = remoteViews;
            }
        }
        if ("com.xiaomi.xmsf".equals((Object)linkedList.getPackageName())) {
            MIPushNotificationHelper.setTargetPackage((Notification)object, MIPushNotificationHelper.getTargetPackage(xmPushActionContainer));
        }
        n = pushMetaInfo.getNotifyId() + MIPushNotificationHelper.getTargetPackage(xmPushActionContainer).hashCode() / 10 * 10;
        notificationManager.notify(n, (Notification)object);
        xmPushActionContainer = new Pair((Object)n, (Object)MIPushNotificationHelper.getTargetPackage(xmPushActionContainer));
        linkedList = notifyIDCache;
        synchronized (linkedList) {
            notifyIDCache.add((Object)xmPushActionContainer);
            if (notifyIDCache.size() > 100) {
                notifyIDCache.remove();
            }
            return;
        }
    }

    static void setLocalNotifyType(Context context, String string2, int n) {
        context.getSharedPreferences("pref_notify_type", 0).edit().putInt(string2, n).commit();
    }

    private static Notification setTargetPackage(Notification notification, String string2) {
        try {
            Object object = Notification.class.getDeclaredField("extraNotification");
            object.setAccessible(true);
            object = object.get((Object)notification);
            Method method = object.getClass().getDeclaredMethod("setTargetPkg", new Class[]{CharSequence.class});
            method.setAccessible(true);
            method.invoke(object, new Object[]{string2});
            return notification;
        }
        catch (Exception var1_2) {
            MyLog.e(var1_2);
            return notification;
        }
    }
}

