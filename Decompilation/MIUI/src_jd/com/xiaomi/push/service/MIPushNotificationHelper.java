package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
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

public class MIPushNotificationHelper
{
  public static long lastNotify = 0L;
  private static LinkedList<Pair<Integer, String>> notifyIDCache = new LinkedList();
  
  static void clearLocalNotifyType(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("pref_notify_type", 0).edit().remove(paramString).commit();
  }
  
  public static void clearNotification(Context arg0, String paramString)
  {
    NotificationManager localNotificationManager = (NotificationManager)???.getSystemService("notification");
    synchronized (notifyIDCache)
    {
      Iterator localIterator = ((LinkedList)notifyIDCache.clone()).iterator();
      while (localIterator.hasNext())
      {
        Pair localPair = (Pair)localIterator.next();
        if (TextUtils.equals((CharSequence)second, paramString))
        {
          localNotificationManager.cancel(((Integer)first).intValue());
          notifyIDCache.remove(localPair);
        }
      }
    }
  }
  
  public static void clearNotification(Context arg0, String paramString, int paramInt)
  {
    ??? = (NotificationManager)???.getSystemService("notification");
    paramInt = paramString.hashCode() / 10 * 10 + paramInt;
    ???.cancel(paramInt);
    synchronized (notifyIDCache)
    {
      Iterator localIterator = notifyIDCache.iterator();
      while (localIterator.hasNext())
      {
        Pair localPair = (Pair)localIterator.next();
        if ((paramInt == ((Integer)first).intValue()) && (TextUtils.equals(paramString, (CharSequence)second))) {
          notifyIDCache.remove(localPair);
        }
      }
      return;
    }
  }
  
  private static String[] determineTitleAndDespByDIP(Context paramContext, PushMetaInfo paramPushMetaInfo)
  {
    Object localObject2 = paramPushMetaInfo.getTitle();
    String str = paramPushMetaInfo.getDescription();
    Map localMap = paramPushMetaInfo.getExtra();
    Object localObject1 = str;
    paramPushMetaInfo = (PushMetaInfo)localObject2;
    int i;
    if (localMap != null)
    {
      i = getResourcesgetDisplayMetricswidthPixels;
      float f = getResourcesgetDisplayMetricsdensity;
      i = Float.valueOf(i / f + 0.5F).intValue();
      if (i > 320) {
        break label143;
      }
      paramContext = (String)localMap.get("title_short");
      if (!TextUtils.isEmpty(paramContext)) {
        localObject2 = paramContext;
      }
      paramContext = (String)localMap.get("description_short");
      localObject1 = str;
      paramPushMetaInfo = (PushMetaInfo)localObject2;
      if (!TextUtils.isEmpty(paramContext))
      {
        localObject1 = paramContext;
        paramPushMetaInfo = (PushMetaInfo)localObject2;
      }
    }
    for (;;)
    {
      return new String[] { paramPushMetaInfo, localObject1 };
      label143:
      localObject1 = str;
      paramPushMetaInfo = (PushMetaInfo)localObject2;
      if (i > 360)
      {
        paramContext = (String)localMap.get("title_long");
        if (!TextUtils.isEmpty(paramContext)) {
          localObject2 = paramContext;
        }
        paramContext = (String)localMap.get("description_long");
        localObject1 = str;
        paramPushMetaInfo = (PushMetaInfo)localObject2;
        if (!TextUtils.isEmpty(paramContext))
        {
          localObject1 = paramContext;
          paramPushMetaInfo = (PushMetaInfo)localObject2;
        }
      }
    }
  }
  
  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    int i = paramDrawable.getIntrinsicWidth();
    int j;
    if (i > 0)
    {
      j = paramDrawable.getIntrinsicHeight();
      if (j <= 0) {
        break label81;
      }
    }
    for (;;)
    {
      Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
      paramDrawable.draw(localCanvas);
      return localBitmap;
      i = 1;
      break;
      label81:
      j = 1;
    }
  }
  
  private static Bitmap getBitmapFromId(Context paramContext, int paramInt)
  {
    return drawableToBitmap(paramContext.getResources().getDrawable(paramInt));
  }
  
  private static PendingIntent getClickedPendingIntent(Context paramContext, XmPushActionContainer paramXmPushActionContainer, PushMetaInfo paramPushMetaInfo, byte[] paramArrayOfByte)
  {
    if ((paramPushMetaInfo != null) && (!TextUtils.isEmpty(url)))
    {
      paramXmPushActionContainer = new Intent("android.intent.action.VIEW");
      paramXmPushActionContainer.setData(Uri.parse(url));
      paramXmPushActionContainer.addFlags(268435456);
      return PendingIntent.getActivity(paramContext, 0, paramXmPushActionContainer, 134217728);
    }
    if (isBusinessMessage(paramXmPushActionContainer))
    {
      paramXmPushActionContainer = new Intent();
      paramXmPushActionContainer.setComponent(new ComponentName("com.xiaomi.xmsf", "com.xiaomi.mipush.sdk.PushMessageHandler"));
      paramXmPushActionContainer.putExtra("mipush_payload", paramArrayOfByte);
      paramXmPushActionContainer.putExtra("mipush_notified", true);
      paramXmPushActionContainer.addCategory(String.valueOf(paramPushMetaInfo.getNotifyId()));
      return PendingIntent.getService(paramContext, 0, paramXmPushActionContainer, 134217728);
    }
    Intent localIntent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
    localIntent.setComponent(new ComponentName(packageName, "com.xiaomi.mipush.sdk.PushMessageHandler"));
    localIntent.putExtra("mipush_payload", paramArrayOfByte);
    localIntent.putExtra("mipush_notified", true);
    localIntent.addCategory(String.valueOf(paramPushMetaInfo.getNotifyId()));
    return PendingIntent.getService(paramContext, 0, localIntent, 134217728);
  }
  
  private static int getIconId(Context paramContext, String paramString1, String paramString2)
  {
    if (paramString1.equals(paramContext.getPackageName())) {
      return paramContext.getResources().getIdentifier(paramString2, "drawable", paramString1);
    }
    return 0;
  }
  
  private static int getIdForSmallIcon(Context paramContext, String paramString)
  {
    int i = getIconId(paramContext, paramString, "mipush_notification");
    int j = getIconId(paramContext, paramString, "mipush_small_notification");
    if (i > 0) {}
    for (;;)
    {
      j = i;
      if (i == 0)
      {
        j = i;
        if (Build.VERSION.SDK_INT >= 9) {
          j = getApplicationInfologo;
        }
      }
      return j;
      if (j > 0) {
        i = j;
      } else {
        i = getApplicationInfoicon;
      }
    }
  }
  
  static int getLocalNotifyType(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("pref_notify_type", 0).getInt(paramString, Integer.MAX_VALUE);
  }
  
  private static RemoteViews getNotificationForCustomLayout(Context paramContext, XmPushActionContainer paramXmPushActionContainer, byte[] paramArrayOfByte)
  {
    paramArrayOfByte = paramXmPushActionContainer.getMetaInfo();
    String str1 = getTargetPackage(paramXmPushActionContainer);
    paramArrayOfByte = paramArrayOfByte.getExtra();
    if (paramArrayOfByte == null) {
      paramContext = null;
    }
    Resources localResources;
    int i;
    String str2;
    label307:
    do
    {
      return paramContext;
      paramXmPushActionContainer = (String)paramArrayOfByte.get("layout_name");
      paramArrayOfByte = (String)paramArrayOfByte.get("layout_value");
      if ((TextUtils.isEmpty(paramXmPushActionContainer)) || (TextUtils.isEmpty(paramArrayOfByte))) {
        return null;
      }
      paramContext = paramContext.getPackageManager();
      try
      {
        localResources = paramContext.getResourcesForApplication(str1);
        i = localResources.getIdentifier(paramXmPushActionContainer, "layout", str1);
        if (i == 0) {
          return null;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        MyLog.e(paramContext);
        return null;
      }
      paramXmPushActionContainer = new RemoteViews(str1, i);
      try
      {
        paramArrayOfByte = new JSONObject(paramArrayOfByte);
        if (paramArrayOfByte.has("text"))
        {
          paramContext = paramArrayOfByte.getJSONObject("text");
          localObject1 = paramContext.keys();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            str2 = paramContext.getString((String)localObject2);
            i = localResources.getIdentifier((String)localObject2, "id", str1);
            if (i > 0) {
              paramXmPushActionContainer.setTextViewText(i, str2);
            }
          }
        }
        if (!paramArrayOfByte.has("image")) {
          break label307;
        }
      }
      catch (JSONException paramContext)
      {
        MyLog.e(paramContext);
        return null;
      }
      paramContext = paramArrayOfByte.getJSONObject("image");
      localObject1 = paramContext.keys();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        str2 = paramContext.getString((String)localObject2);
        i = localResources.getIdentifier((String)localObject2, "id", str1);
        int j = localResources.getIdentifier(str2, "drawable", str1);
        if (i > 0) {
          paramXmPushActionContainer.setImageViewResource(i, j);
        }
      }
      paramContext = paramXmPushActionContainer;
    } while (!paramArrayOfByte.has("time"));
    Object localObject1 = paramArrayOfByte.getJSONObject("time");
    Object localObject2 = ((JSONObject)localObject1).keys();
    for (;;)
    {
      paramContext = paramXmPushActionContainer;
      if (!((Iterator)localObject2).hasNext()) {
        break;
      }
      str2 = (String)((Iterator)localObject2).next();
      paramArrayOfByte = ((JSONObject)localObject1).getString(str2);
      paramContext = paramArrayOfByte;
      if (paramArrayOfByte.length() == 0) {
        paramContext = "yy-MM-dd hh:mm";
      }
      i = localResources.getIdentifier(str2, "id", str1);
      if (i > 0)
      {
        long l = System.currentTimeMillis();
        paramXmPushActionContainer.setTextViewText(i, new SimpleDateFormat(paramContext).format(new Date(l)));
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  private static Notification getNotificationForLargeIcons(Context paramContext, XmPushActionContainer paramXmPushActionContainer, byte[] paramArrayOfByte, RemoteViews paramRemoteViews, PendingIntent paramPendingIntent)
  {
    PushMetaInfo localPushMetaInfo = paramXmPushActionContainer.getMetaInfo();
    paramArrayOfByte = new Notification.Builder(paramContext);
    String[] arrayOfString = determineTitleAndDespByDIP(paramContext, localPushMetaInfo);
    paramArrayOfByte.setContentTitle(arrayOfString[0]);
    paramArrayOfByte.setContentText(arrayOfString[1]);
    int i;
    if (paramRemoteViews != null)
    {
      paramArrayOfByte.setContent(paramRemoteViews);
      paramArrayOfByte.setWhen(System.currentTimeMillis());
      paramArrayOfByte.setContentIntent(paramPendingIntent);
      i = getIconId(paramContext, getTargetPackage(paramXmPushActionContainer), "mipush_notification");
      int j = getIconId(paramContext, getTargetPackage(paramXmPushActionContainer), "mipush_small_notification");
      if ((i <= 0) || (j <= 0)) {
        break label337;
      }
      paramArrayOfByte.setLargeIcon(getBitmapFromId(paramContext, i));
      paramArrayOfByte.setSmallIcon(j);
    }
    for (;;)
    {
      paramArrayOfByte.setAutoCancel(true);
      long l = System.currentTimeMillis();
      paramRemoteViews = localPushMetaInfo.getExtra();
      if ((paramRemoteViews != null) && (paramRemoteViews.containsKey("ticker"))) {
        paramArrayOfByte.setTicker((CharSequence)paramRemoteViews.get("ticker"));
      }
      if (l - lastNotify > 10000L)
      {
        lastNotify = l;
        i = notifyType;
        if (hasLocalNotifyType(paramContext, getTargetPackage(paramXmPushActionContainer))) {
          i = getLocalNotifyType(paramContext, getTargetPackage(paramXmPushActionContainer));
        }
        paramArrayOfByte.setDefaults(i);
        if ((paramRemoteViews != null) && ((i & 0x1) != 0))
        {
          paramContext = (String)paramRemoteViews.get("sound_uri");
          if ((!TextUtils.isEmpty(paramContext)) && (paramContext.startsWith("android.resource://" + getTargetPackage(paramXmPushActionContainer))))
          {
            paramArrayOfByte.setDefaults(i ^ 0x1);
            paramArrayOfByte.setSound(Uri.parse(paramContext));
          }
        }
      }
      return paramArrayOfByte.getNotification();
      if (Build.VERSION.SDK_INT < 16) {
        break;
      }
      paramArrayOfByte.setStyle(new Notification.BigTextStyle().bigText(arrayOfString[1]));
      break;
      label337:
      paramArrayOfByte.setSmallIcon(getIdForSmallIcon(paramContext, getTargetPackage(paramXmPushActionContainer)));
    }
  }
  
  static String getTargetPackage(XmPushActionContainer paramXmPushActionContainer)
  {
    if ("com.xiaomi.xmsf".equals(packageName))
    {
      Object localObject = paramXmPushActionContainer.getMetaInfo();
      if ((localObject != null) && (((PushMetaInfo)localObject).getExtra() != null))
      {
        localObject = (String)((PushMetaInfo)localObject).getExtra().get("miui_package_name");
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          return (String)localObject;
        }
      }
    }
    return packageName;
  }
  
  static boolean hasLocalNotifyType(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("pref_notify_type", 0).contains(paramString);
  }
  
  public static boolean isApplicationForeground(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if ((importance == 100) && (Arrays.asList(pkgList).contains(paramString))) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean isBusinessMessage(XmPushActionContainer paramXmPushActionContainer)
  {
    paramXmPushActionContainer = paramXmPushActionContainer.getMetaInfo();
    return (paramXmPushActionContainer != null) && (paramXmPushActionContainer.isIgnoreRegInfo());
  }
  
  public static boolean isNotifyForeground(Map<String, String> paramMap)
  {
    if ((paramMap == null) || (!paramMap.containsKey("notify_foreground"))) {
      return true;
    }
    return "1".equals((String)paramMap.get("notify_foreground"));
  }
  
  public static void notifyPushMessage(Context arg0, XmPushActionContainer paramXmPushActionContainer, byte[] paramArrayOfByte)
  {
    NotificationManager localNotificationManager = (NotificationManager)???.getSystemService("notification");
    PushMetaInfo localPushMetaInfo = paramXmPushActionContainer.getMetaInfo();
    RemoteViews localRemoteViews = getNotificationForCustomLayout(???, paramXmPushActionContainer, paramArrayOfByte);
    Object localObject = getClickedPendingIntent(???, paramXmPushActionContainer, localPushMetaInfo, paramArrayOfByte);
    if (localObject == null)
    {
      MyLog.warn("The click PendingIntent is null. ");
      return;
    }
    if (Build.VERSION.SDK_INT >= 11) {
      paramArrayOfByte = getNotificationForLargeIcons(???, paramXmPushActionContainer, paramArrayOfByte, localRemoteViews, (PendingIntent)localObject);
    }
    for (;;)
    {
      if ("com.xiaomi.xmsf".equals(???.getPackageName())) {
        setTargetPackage(paramArrayOfByte, getTargetPackage(paramXmPushActionContainer));
      }
      int i = localPushMetaInfo.getNotifyId() + getTargetPackage(paramXmPushActionContainer).hashCode() / 10 * 10;
      localNotificationManager.notify(i, paramArrayOfByte);
      paramXmPushActionContainer = new Pair(Integer.valueOf(i), getTargetPackage(paramXmPushActionContainer));
      synchronized (notifyIDCache)
      {
        notifyIDCache.add(paramXmPushActionContainer);
        if (notifyIDCache.size() > 100) {
          notifyIDCache.remove();
        }
        return;
      }
      paramArrayOfByte = new Notification(getIdForSmallIcon(???, getTargetPackage(paramXmPushActionContainer)), null, System.currentTimeMillis());
      String[] arrayOfString = determineTitleAndDespByDIP(???, localPushMetaInfo);
      try
      {
        paramArrayOfByte.getClass().getMethod("setLatestEventInfo", new Class[] { Context.class, CharSequence.class, CharSequence.class, PendingIntent.class }).invoke(paramArrayOfByte, new Object[] { ???, arrayOfString[0], arrayOfString[1], localObject });
        localObject = localPushMetaInfo.getExtra();
        if ((localObject != null) && (((Map)localObject).containsKey("ticker"))) {
          tickerText = ((CharSequence)((Map)localObject).get("ticker"));
        }
        long l = System.currentTimeMillis();
        if (l - lastNotify > 10000L)
        {
          lastNotify = l;
          i = notifyType;
          if (!hasLocalNotifyType(???, getTargetPackage(paramXmPushActionContainer))) {
            break label508;
          }
          i = getLocalNotifyType(???, getTargetPackage(paramXmPushActionContainer));
          defaults = i;
          if ((localObject != null) && ((i & 0x1) != 0))
          {
            localObject = (String)((Map)localObject).get("sound_uri");
            if ((!TextUtils.isEmpty((CharSequence)localObject)) && (((String)localObject).startsWith("android.resource://" + getTargetPackage(paramXmPushActionContainer))))
            {
              defaults = (i ^ 0x1);
              sound = Uri.parse((String)localObject);
            }
          }
        }
        flags |= 0x10;
        if (localRemoteViews != null) {
          contentView = localRemoteViews;
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        for (;;)
        {
          MyLog.e(localNoSuchMethodException);
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;)
        {
          MyLog.e(localIllegalAccessException);
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          MyLog.e(localIllegalArgumentException);
        }
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        label508:
        for (;;)
        {
          MyLog.e(localInvocationTargetException);
        }
      }
    }
  }
  
  static void setLocalNotifyType(Context paramContext, String paramString, int paramInt)
  {
    paramContext.getSharedPreferences("pref_notify_type", 0).edit().putInt(paramString, paramInt).commit();
  }
  
  private static Notification setTargetPackage(Notification paramNotification, String paramString)
  {
    try
    {
      Object localObject = Notification.class.getDeclaredField("extraNotification");
      ((Field)localObject).setAccessible(true);
      localObject = ((Field)localObject).get(paramNotification);
      Method localMethod = localObject.getClass().getDeclaredMethod("setTargetPkg", new Class[] { CharSequence.class });
      localMethod.setAccessible(true);
      localMethod.invoke(localObject, new Object[] { paramString });
      return paramNotification;
    }
    catch (Exception paramString)
    {
      MyLog.e(paramString);
    }
    return paramNotification;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.MIPushNotificationHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */