package com.meizu.cloud.pushsdk.notification;

import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class NotificationTools
{
  private static final String TAG = "NotificationTools";
  
  public static Intent buildIntent(Context paramContext, MPushMessage paramMPushMessage)
  {
    Object localObject1;
    Object localObject2;
    if ("0".equals(paramMPushMessage.getClickType()))
    {
      paramContext = paramContext.getPackageManager().getLaunchIntentForPackage(paramMPushMessage.getPackageName());
      if (paramMPushMessage.getParams() != null)
      {
        paramMPushMessage = paramMPushMessage.getParams().entrySet().iterator();
        while (paramMPushMessage.hasNext())
        {
          localObject1 = (Map.Entry)paramMPushMessage.next();
          Log.i("NotificationTools", " launcher activity key " + (String)((Map.Entry)localObject1).getKey() + " value " + (String)((Map.Entry)localObject1).getValue());
          if ((!TextUtils.isEmpty((CharSequence)((Map.Entry)localObject1).getKey())) && (!TextUtils.isEmpty((CharSequence)((Map.Entry)localObject1).getValue())))
          {
            localObject2 = urlEncode((String)((Map.Entry)localObject1).getValue());
            paramContext.putExtra((String)((Map.Entry)localObject1).getKey(), (String)localObject2);
          }
        }
        return paramContext;
      }
    }
    else
    {
      if ("1".equals(paramMPushMessage.getClickType()))
      {
        paramContext = "";
        localObject1 = paramContext;
        if (paramMPushMessage.getParams() != null)
        {
          localObject2 = paramMPushMessage.getParams().entrySet().iterator();
          localObject1 = paramContext;
          if (((Iterator)localObject2).hasNext())
          {
            localObject1 = (Map.Entry)((Iterator)localObject2).next();
            Log.i("NotificationTools", " key " + (String)((Map.Entry)localObject1).getKey() + " value " + (String)((Map.Entry)localObject1).getValue());
            if ((TextUtils.isEmpty((CharSequence)((Map.Entry)localObject1).getKey())) || (TextUtils.isEmpty((CharSequence)((Map.Entry)localObject1).getValue()))) {
              break label577;
            }
            String str = urlEncode((String)((Map.Entry)localObject1).getValue());
            paramContext = paramContext + "S." + (String)((Map.Entry)localObject1).getKey() + "=" + str + ";";
          }
        }
      }
      label577:
      for (;;)
      {
        Log.i("NotificationTools", "paramValue " + paramContext);
        break;
        paramMPushMessage = new StringBuilder().append("intent:#Intent;component=").append(paramMPushMessage.getPackageName()).append("/").append((String)paramMPushMessage.getExtra().get("activity"));
        if (TextUtils.isEmpty((CharSequence)localObject1)) {}
        for (paramContext = ";";; paramContext = ";" + (String)localObject1)
        {
          paramContext = paramContext + "end";
          Log.i("NotificationTools", "open activity intent uri " + paramContext);
          try
          {
            paramContext = Intent.parseUri(paramContext, 1);
            return paramContext;
          }
          catch (URISyntaxException paramContext)
          {
            paramContext.printStackTrace();
            return null;
          }
        }
        if ("2".equals(paramMPushMessage.getClickType())) {
          return new Intent("android.intent.action.VIEW", Uri.parse((String)paramMPushMessage.getExtra().get("url")));
        }
        return null;
      }
    }
    return paramContext;
  }
  
  public static Bitmap getAppIcon(Context paramContext, MPushMessage paramMPushMessage)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramMPushMessage = ((BitmapDrawable)localPackageManager.getApplicationIcon(paramMPushMessage.getPackageName())).getBitmap();
      return paramMPushMessage;
    }
    catch (PackageManager.NameNotFoundException paramMPushMessage)
    {
      Log.i("NotificationTools", "getappicon error " + paramMPushMessage.getMessage());
    }
    return ((BitmapDrawable)paramContext.getApplicationInfo().loadIcon(paramContext.getPackageManager())).getBitmap();
  }
  
  public static Notification onCreateNotification(Context paramContext, MPushMessage paramMPushMessage, PushNotificationBuilder paramPushNotificationBuilder)
  {
    String str1 = paramMPushMessage.getContent();
    String str2 = paramMPushMessage.getTitle();
    String str3 = paramMPushMessage.getContent();
    Notification.Builder localBuilder = new Notification.Builder(paramContext);
    if ((paramPushNotificationBuilder != null) && (paramPushNotificationBuilder.getmLargIcon() != 0))
    {
      localBuilder.setLargeIcon(BitmapFactory.decodeResource(paramContext.getResources(), paramPushNotificationBuilder.getmLargIcon()));
      if ((paramPushNotificationBuilder == null) || (paramPushNotificationBuilder.getmStatusbarIcon() == 0)) {
        break label163;
      }
      localBuilder.setSmallIcon(paramPushNotificationBuilder.getmStatusbarIcon());
      label76:
      localBuilder.setTicker(str1);
      localBuilder.setContentTitle(str2);
      localBuilder.setContentText(str3);
      localBuilder.setAutoCancel(true);
      localBuilder.setDefaults(1);
      if (!MinSdkChecker.isSupportBigTextStyleAndAction()) {
        break label179;
      }
      localBuilder.setStyle(new Notification.BigTextStyle().bigText(str3));
    }
    for (;;)
    {
      if (!MinSdkChecker.isSupportNotificationBuild()) {
        break label190;
      }
      return localBuilder.build();
      localBuilder.setLargeIcon(getAppIcon(paramContext, paramMPushMessage));
      break;
      label163:
      localBuilder.setSmallIcon(getApplicationInfoicon);
      break label76;
      label179:
      localBuilder.setContentText(str3);
    }
    label190:
    return localBuilder.getNotification();
  }
  
  public static void showPrivateNotification(Context paramContext, MPushMessage paramMPushMessage, PushNotificationBuilder paramPushNotificationBuilder)
  {
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    Object localObject1 = new Intent();
    ((Intent)localObject1).setData(Uri.parse("custom://" + System.currentTimeMillis()));
    ((Intent)localObject1).putExtra("pushMessage", paramMPushMessage);
    ((Intent)localObject1).putExtra("method", "private");
    Object localObject2 = BroadCastManager.findReceiver(paramContext, "com.meizu.flyme.push.intent.MESSAGE", paramContext.getPackageName());
    Log.i("NotificationTools", "current notify receiver name " + (String)localObject2);
    ((Intent)localObject1).setClassName(paramContext.getPackageName(), (String)localObject2);
    ((Intent)localObject1).setAction("com.meizu.flyme.push.intent.MESSAGE");
    localObject1 = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject1, 1073741824);
    Intent localIntent = new Intent();
    localIntent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
    localIntent.putExtra("pushMessage", paramMPushMessage);
    localIntent.putExtra("method", "notification_delete");
    localIntent.setClassName(paramContext.getPackageName(), (String)localObject2);
    localIntent.setAction("com.meizu.flyme.push.intent.MESSAGE");
    localObject2 = PendingIntent.getBroadcast(paramContext, 0, localIntent, 1073741824);
    paramPushNotificationBuilder = onCreateNotification(paramContext, paramMPushMessage, paramPushNotificationBuilder);
    contentIntent = ((PendingIntent)localObject1);
    deleteIntent = ((PendingIntent)localObject2);
    int j = (int)System.currentTimeMillis();
    int i = j;
    if ("true".equals(paramMPushMessage.getIsDiscard()))
    {
      if (PushPreferencesUtils.getDiscardNotificationId(paramContext, paramMPushMessage.getPackageName()) == 0) {
        PushPreferencesUtils.putDiscardNotificationIdByPackageName(paramContext, paramMPushMessage.getPackageName(), j);
      }
      i = j;
      if (!TextUtils.isEmpty(paramMPushMessage.getTaskId()))
      {
        if (PushPreferencesUtils.getDiscardNotificationTaskId(paramContext, paramMPushMessage.getPackageName()) != 0) {
          break label409;
        }
        PushPreferencesUtils.putDiscardNotificationTaskId(paramContext, paramMPushMessage.getPackageName(), Integer.valueOf(paramMPushMessage.getTaskId()).intValue());
      }
    }
    for (i = j;; i = PushPreferencesUtils.getDiscardNotificationId(paramContext, paramMPushMessage.getPackageName()))
    {
      Log.i("NotificationTools", "current package " + paramMPushMessage.getPackageName() + " notificationId=" + i + " taskId=" + paramMPushMessage.getTaskId());
      localNotificationManager.notify(i, paramPushNotificationBuilder);
      return;
      label409:
      if (Integer.valueOf(paramMPushMessage.getTaskId()).intValue() < PushPreferencesUtils.getDiscardNotificationTaskId(paramContext, paramMPushMessage.getPackageName()))
      {
        Log.i("NotificationTools", "current package " + paramMPushMessage.getPackageName() + " taskid " + paramMPushMessage.getTaskId() + " dont show notification");
        return;
      }
      PushPreferencesUtils.putDiscardNotificationTaskId(paramContext, paramMPushMessage.getPackageName(), Integer.valueOf(paramMPushMessage.getTaskId()).intValue());
    }
  }
  
  private static String urlEncode(String paramString)
  {
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      paramString = str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        Log.i("NotificationTools", "encode url fail");
      }
    }
    Log.i("NotificationTools", "encode all value is " + paramString);
    return paramString;
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.notification.NotificationTools
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */