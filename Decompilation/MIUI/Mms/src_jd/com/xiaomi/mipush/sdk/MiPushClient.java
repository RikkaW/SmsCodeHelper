package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.PreferenceUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.push.service.DeviceInfo;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionCommand;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistration;
import com.xiaomi.xmpush.thrift.XmPushActionSubscription;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class MiPushClient
{
  private static boolean awakeService = true;
  private static Context sContext;
  private static long sCurMsgId = System.currentTimeMillis();
  
  public static long accountSetTime(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("mipush_extra", 0).getLong("account_" + paramString, -1L);
  }
  
  static void addAcceptTime(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext.getSharedPreferences("mipush_extra", 0).edit().putString("accept_time", paramString1 + "," + paramString2).commit();
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  static void addAccount(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getSharedPreferences("mipush_extra", 0).edit().putLong("account_" + paramString, System.currentTimeMillis()).commit();
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  static void addAlias(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getSharedPreferences("mipush_extra", 0).edit().putLong("alias_" + paramString, System.currentTimeMillis()).commit();
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private static void addPullNotificationTime(Context paramContext)
  {
    paramContext.getSharedPreferences("mipush_extra", 0).edit().putLong("last_pull_notification", System.currentTimeMillis()).commit();
  }
  
  private static void addRegRequestTime(Context paramContext)
  {
    paramContext.getSharedPreferences("mipush_extra", 0).edit().putLong("last_reg_request", System.currentTimeMillis()).commit();
  }
  
  static void addTopic(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getSharedPreferences("mipush_extra", 0).edit().putLong("topic_" + paramString, System.currentTimeMillis()).commit();
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static long aliasSetTime(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("mipush_extra", 0).getLong("alias_" + paramString, -1L);
  }
  
  private static void awakePushServices(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("mipush_extra", 0);
    if (System.currentTimeMillis() - 86400000L < localSharedPreferences.getLong("wake_up", 0L)) {
      return;
    }
    localSharedPreferences.edit().putLong("wake_up", System.currentTimeMillis()).commit();
    new Thread(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
        //   4: invokestatic 29	com/xiaomi/mipush/sdk/MiPushClient:shouldUseMIUIPush	(Landroid/content/Context;)Z
        //   7: ifne +211 -> 218
        //   10: iconst_1
        //   11: aload_0
        //   12: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
        //   15: invokestatic 35	com/xiaomi/mipush/sdk/AppInfoHolder:getInstance	(Landroid/content/Context;)Lcom/xiaomi/mipush/sdk/AppInfoHolder;
        //   18: invokevirtual 39	com/xiaomi/mipush/sdk/AppInfoHolder:getEnvType	()I
        //   21: if_icmpne +197 -> 218
        //   24: aload_0
        //   25: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
        //   28: invokevirtual 45	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
        //   31: iconst_4
        //   32: invokevirtual 51	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
        //   35: astore 4
        //   37: aload 4
        //   39: ifnull +179 -> 218
        //   42: aload 4
        //   44: invokeinterface 57 1 0
        //   49: astore 4
        //   51: aload 4
        //   53: invokeinterface 63 1 0
        //   58: ifeq +160 -> 218
        //   61: aload 4
        //   63: invokeinterface 67 1 0
        //   68: checkcast 69	android/content/pm/PackageInfo
        //   71: getfield 73	android/content/pm/PackageInfo:services	[Landroid/content/pm/ServiceInfo;
        //   74: astore 5
        //   76: aload 5
        //   78: ifnull -27 -> 51
        //   81: aload 5
        //   83: arraylength
        //   84: istore_2
        //   85: iconst_0
        //   86: istore_1
        //   87: iload_1
        //   88: iload_2
        //   89: if_icmpge -38 -> 51
        //   92: aload 5
        //   94: iload_1
        //   95: aaload
        //   96: astore 6
        //   98: aload 6
        //   100: getfield 79	android/content/pm/ServiceInfo:exported	Z
        //   103: ifeq +106 -> 209
        //   106: aload 6
        //   108: getfield 82	android/content/pm/ServiceInfo:enabled	Z
        //   111: ifeq +98 -> 209
        //   114: ldc 84
        //   116: aload 6
        //   118: getfield 88	android/content/pm/ServiceInfo:name	Ljava/lang/String;
        //   121: invokevirtual 94	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   124: ifeq +85 -> 209
        //   127: aload_0
        //   128: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
        //   131: invokevirtual 98	android/content/Context:getPackageName	()Ljava/lang/String;
        //   134: aload 6
        //   136: getfield 101	android/content/pm/ServiceInfo:packageName	Ljava/lang/String;
        //   139: invokevirtual 94	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   142: istore_3
        //   143: iload_3
        //   144: ifne +65 -> 209
        //   147: invokestatic 107	java/lang/Math:random	()D
        //   150: ldc2_w 108
        //   153: dmul
        //   154: ldc2_w 110
        //   157: dadd
        //   158: d2l
        //   159: ldc2_w 112
        //   162: lmul
        //   163: invokestatic 119	java/lang/Thread:sleep	(J)V
        //   166: new 121	android/content/Intent
        //   169: dup
        //   170: invokespecial 122	android/content/Intent:<init>	()V
        //   173: astore 7
        //   175: aload 7
        //   177: aload 6
        //   179: getfield 101	android/content/pm/ServiceInfo:packageName	Ljava/lang/String;
        //   182: aload 6
        //   184: getfield 88	android/content/pm/ServiceInfo:name	Ljava/lang/String;
        //   187: invokevirtual 126	android/content/Intent:setClassName	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   190: pop
        //   191: aload 7
        //   193: ldc -128
        //   195: invokevirtual 132	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
        //   198: pop
        //   199: aload_0
        //   200: getfield 16	com/xiaomi/mipush/sdk/MiPushClient$3:val$context	Landroid/content/Context;
        //   203: aload 7
        //   205: invokevirtual 136	android/content/Context:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
        //   208: pop
        //   209: iload_1
        //   210: iconst_1
        //   211: iadd
        //   212: istore_1
        //   213: goto -126 -> 87
        //   216: astore 4
        //   218: return
        //   219: astore 7
        //   221: goto -55 -> 166
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	224	0	this	3
        //   86	127	1	i	int
        //   84	6	2	j	int
        //   142	2	3	bool	boolean
        //   35	27	4	localObject	Object
        //   216	1	4	localThrowable	Throwable
        //   74	19	5	arrayOfServiceInfo	android.content.pm.ServiceInfo[]
        //   96	87	6	localServiceInfo	android.content.pm.ServiceInfo
        //   173	31	7	localIntent	android.content.Intent
        //   219	1	7	localInterruptedException	InterruptedException
        // Exception table:
        //   from	to	target	type
        //   24	37	216	java/lang/Throwable
        //   42	51	216	java/lang/Throwable
        //   51	76	216	java/lang/Throwable
        //   81	85	216	java/lang/Throwable
        //   98	143	216	java/lang/Throwable
        //   147	166	216	java/lang/Throwable
        //   166	209	216	java/lang/Throwable
        //   147	166	219	java/lang/InterruptedException
      }
    }).start();
  }
  
  private static void checkNotNull(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException("param " + paramString + " is not nullable");
    }
  }
  
  protected static void clearExtras(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("mipush_extra", 0);
    long l = paramContext.getLong("wake_up", 0L);
    paramContext = paramContext.edit();
    paramContext.clear();
    if (l > 0L) {
      paramContext.putLong("wake_up", l);
    }
    paramContext.commit();
  }
  
  public static void clearLocalNotificationType(Context paramContext)
  {
    PushServiceClient.getInstance(paramContext).clearLocalNotificationType();
  }
  
  public static void clearNotification(Context paramContext)
  {
    PushServiceClient.getInstance(paramContext).clearNotification(-1);
  }
  
  protected static String generatePacketID()
  {
    try
    {
      String str = XMStringUtils.generateRandomString(4) + sCurMsgId;
      sCurMsgId += 1L;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  @Deprecated
  public static void initialize(Context paramContext, String paramString1, String paramString2, MiPushClientCallback paramMiPushClientCallback)
  {
    checkNotNull(paramContext, "context");
    checkNotNull(paramString1, "appID");
    checkNotNull(paramString2, "appToken");
    for (;;)
    {
      try
      {
        sContext = paramContext.getApplicationContext();
        if (sContext == null) {
          sContext = paramContext;
        }
        if (paramMiPushClientCallback != null) {
          PushMessageHandler.addPushCallbackClass(paramMiPushClientCallback);
        }
        if (AppInfoHolder.getInstance(sContext).getEnvType() == Constants.getEnvType()) {
          break label582;
        }
        bool = true;
        if ((!bool) && (!shouldSendRegRequest(sContext)))
        {
          PushServiceClient.getInstance(paramContext).awakePushService();
          MyLog.warn("Could not send  register message within 5s repeatly .");
          return;
        }
        if ((bool) || (!AppInfoHolder.getInstance(sContext).appRegistered(paramString1, paramString2)) || (AppInfoHolder.getInstance(sContext).invalidated())) {
          break label458;
        }
        if (1 == PushMessageHelper.getPushMode(paramContext))
        {
          checkNotNull(paramMiPushClientCallback, "callback");
          paramMiPushClientCallback.onInitializeResult(0L, null, AppInfoHolder.getInstance(paramContext).getRegID());
          PushServiceClient.getInstance(paramContext).awakePushService();
          if (AppInfoHolder.getInstance(sContext).checkVersionNameChanged())
          {
            paramString1 = new XmPushActionNotification();
            paramString1.setAppId(AppInfoHolder.getInstance(paramContext).getAppID());
            paramString1.setType("client_info_update");
            paramString1.setId(generatePacketID());
            extra = new HashMap();
            extra.put("app_version", AppInfoHolder.getVersionName(sContext, sContext.getPackageName()));
            paramString2 = AppInfoHolder.getInstance(sContext).getRegResource();
            if (!TextUtils.isEmpty(paramString2)) {
              extra.put("deviceid", paramString2);
            }
            PushServiceClient.getInstance(paramContext).sendMessage(paramString1, ActionType.Notification, false, null);
          }
          if (!PreferenceUtils.getSettingBoolean(sContext, "update_devId", false))
          {
            updateIMEI();
            PreferenceUtils.setSettingBoolean(sContext, "update_devId", true);
          }
          if ((shouldUseMIUIPush(sContext)) && (shouldPullNotification(sContext)))
          {
            paramContext = new XmPushActionNotification();
            paramContext.setAppId(AppInfoHolder.getInstance(sContext).getAppID());
            paramContext.setType("pull");
            paramContext.setId(generatePacketID());
            paramContext.setRequireAck(false);
            PushServiceClient.getInstance(sContext).sendMessage(paramContext, ActionType.Notification, false, null, false);
            addPullNotificationTime(sContext);
          }
          if (awakeService) {
            awakePushServices(sContext);
          }
          addRegRequestTime(sContext);
          return;
        }
      }
      catch (Throwable paramContext)
      {
        MyLog.e(paramContext);
        return;
      }
      paramString1 = new ArrayList();
      paramString1.add(AppInfoHolder.getInstance(paramContext).getRegID());
      paramString1 = PushMessageHelper.generateCommandMessage("register", paramString1, 0L, null, null);
      PushMessageHelper.sendCommandMessageBroadcast(sContext, paramString1);
      continue;
      label458:
      paramMiPushClientCallback = XMStringUtils.generateRandomString(6);
      AppInfoHolder.getInstance(sContext).clear();
      AppInfoHolder.getInstance(sContext).setEnvType(Constants.getEnvType());
      AppInfoHolder.getInstance(sContext).putAppIDAndToken(paramString1, paramString2, paramMiPushClientCallback);
      clearExtras(sContext);
      XmPushActionRegistration localXmPushActionRegistration = new XmPushActionRegistration();
      localXmPushActionRegistration.setId(generatePacketID());
      localXmPushActionRegistration.setAppId(paramString1);
      localXmPushActionRegistration.setToken(paramString2);
      localXmPushActionRegistration.setPackageName(paramContext.getPackageName());
      localXmPushActionRegistration.setDeviceId(paramMiPushClientCallback);
      localXmPushActionRegistration.setAppVersion(AppInfoHolder.getVersionName(paramContext, paramContext.getPackageName()));
      PushServiceClient.getInstance(sContext).register(localXmPushActionRegistration, bool);
      continue;
      label582:
      boolean bool = false;
    }
  }
  
  static void reInitialize(Context paramContext)
  {
    if (!AppInfoHolder.getInstance(paramContext).appRegistered()) {
      return;
    }
    String str1 = XMStringUtils.generateRandomString(6);
    String str2 = AppInfoHolder.getInstance(paramContext).getAppID();
    String str3 = AppInfoHolder.getInstance(paramContext).getAppToken();
    AppInfoHolder.getInstance(paramContext).clear();
    AppInfoHolder.getInstance(paramContext).putAppIDAndToken(str2, str3, str1);
    XmPushActionRegistration localXmPushActionRegistration = new XmPushActionRegistration();
    localXmPushActionRegistration.setId(generatePacketID());
    localXmPushActionRegistration.setAppId(str2);
    localXmPushActionRegistration.setToken(str3);
    localXmPushActionRegistration.setDeviceId(str1);
    localXmPushActionRegistration.setPackageName(paramContext.getPackageName());
    localXmPushActionRegistration.setAppVersion(AppInfoHolder.getVersionName(paramContext, paramContext.getPackageName()));
    PushServiceClient.getInstance(paramContext).register(localXmPushActionRegistration, false);
  }
  
  public static void registerPush(Context paramContext, final String paramString1, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        MiPushClient.initialize(val$context, paramString1, paramString2, null);
      }
    }).start();
  }
  
  static void removeAccount(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getSharedPreferences("mipush_extra", 0).edit().remove("account_" + paramString).commit();
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  static void removeAlias(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getSharedPreferences("mipush_extra", 0).edit().remove("alias_" + paramString).commit();
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  static void removeTopic(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getSharedPreferences("mipush_extra", 0).edit().remove("topic_" + paramString).commit();
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  static void reportIgnoreRegMessageClicked(Context paramContext, String paramString1, PushMetaInfo paramPushMetaInfo, String paramString2, String paramString3)
  {
    XmPushActionNotification localXmPushActionNotification = new XmPushActionNotification();
    if (TextUtils.isEmpty(paramString3))
    {
      MyLog.e("do not report clicked message");
      return;
    }
    localXmPushActionNotification.setAppId(paramString3);
    localXmPushActionNotification.setType("bar:click");
    localXmPushActionNotification.setId(paramString1);
    localXmPushActionNotification.setRequireAck(false);
    PushServiceClient.getInstance(paramContext).sendMessage(localXmPushActionNotification, ActionType.Notification, false, true, paramPushMetaInfo, true, paramString2, paramString3);
  }
  
  static void reportMessageClicked(Context paramContext, String paramString1, PushMetaInfo paramPushMetaInfo, String paramString2)
  {
    XmPushActionNotification localXmPushActionNotification = new XmPushActionNotification();
    if (TextUtils.isEmpty(paramString2)) {
      if (AppInfoHolder.getInstance(paramContext).checkAppInfo()) {
        localXmPushActionNotification.setAppId(AppInfoHolder.getInstance(paramContext).getAppID());
      }
    }
    for (;;)
    {
      localXmPushActionNotification.setType("bar:click");
      localXmPushActionNotification.setId(paramString1);
      localXmPushActionNotification.setRequireAck(false);
      PushServiceClient.getInstance(paramContext).sendMessage(localXmPushActionNotification, ActionType.Notification, false, paramPushMetaInfo);
      return;
      MyLog.e("do not report clicked message");
      return;
      localXmPushActionNotification.setAppId(paramString2);
    }
  }
  
  public static void setAlias(Context paramContext, String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1)) {
      setCommand(paramContext, "set-alias", paramString1, paramString2);
    }
  }
  
  protected static void setCommand(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    ArrayList localArrayList = new ArrayList();
    if (!TextUtils.isEmpty(paramString2)) {
      localArrayList.add(paramString2);
    }
    if (("set-alias".equalsIgnoreCase(paramString1)) && (System.currentTimeMillis() - aliasSetTime(paramContext, paramString2) < 3600000L))
    {
      if (1 == PushMessageHelper.getPushMode(paramContext))
      {
        PushMessageHandler.onCommandResult(paramContext, paramString3, paramString1, 0L, null, localArrayList);
        return;
      }
      PushMessageHelper.sendCommandMessageBroadcast(paramContext, PushMessageHelper.generateCommandMessage("set-alias", localArrayList, 0L, null, null));
      return;
    }
    if (("unset-alias".equalsIgnoreCase(paramString1)) && (aliasSetTime(paramContext, paramString2) < 0L))
    {
      MyLog.warn("Don't cancel alias for " + localArrayList + " is unseted");
      return;
    }
    if (("set-account".equalsIgnoreCase(paramString1)) && (System.currentTimeMillis() - accountSetTime(paramContext, paramString2) < 3600000L))
    {
      if (1 == PushMessageHelper.getPushMode(paramContext))
      {
        PushMessageHandler.onCommandResult(paramContext, paramString3, paramString1, 0L, null, localArrayList);
        return;
      }
      PushMessageHelper.sendCommandMessageBroadcast(paramContext, PushMessageHelper.generateCommandMessage("set-account", localArrayList, 0L, null, null));
      return;
    }
    if (("unset-account".equalsIgnoreCase(paramString1)) && (accountSetTime(paramContext, paramString2) < 0L))
    {
      MyLog.warn("Don't cancel account for " + localArrayList + " is unseted");
      return;
    }
    setCommand(paramContext, paramString1, localArrayList, paramString3);
  }
  
  protected static void setCommand(Context paramContext, String paramString1, ArrayList<String> paramArrayList, String paramString2)
  {
    if (TextUtils.isEmpty(AppInfoHolder.getInstance(paramContext).getAppID())) {
      return;
    }
    XmPushActionCommand localXmPushActionCommand = new XmPushActionCommand();
    localXmPushActionCommand.setId(generatePacketID());
    localXmPushActionCommand.setAppId(AppInfoHolder.getInstance(paramContext).getAppID());
    localXmPushActionCommand.setCmdName(paramString1);
    paramString1 = paramArrayList.iterator();
    while (paramString1.hasNext()) {
      localXmPushActionCommand.addToCmdArgs((String)paramString1.next());
    }
    localXmPushActionCommand.setCategory(paramString2);
    localXmPushActionCommand.setPackageName(paramContext.getPackageName());
    PushServiceClient.getInstance(paramContext).sendMessage(localXmPushActionCommand, ActionType.Command, null);
  }
  
  private static boolean shouldPullNotification(Context paramContext)
  {
    boolean bool = false;
    paramContext = paramContext.getSharedPreferences("mipush_extra", 0);
    if (System.currentTimeMillis() - paramContext.getLong("last_pull_notification", -1L) > 300000L) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean shouldSendRegRequest(Context paramContext)
  {
    boolean bool = false;
    paramContext = paramContext.getSharedPreferences("mipush_extra", 0);
    if (System.currentTimeMillis() - paramContext.getLong("last_reg_request", -1L) > 5000L) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean shouldUseMIUIPush(Context paramContext)
  {
    return PushServiceClient.getInstance(paramContext).shouldUseMIUIPush();
  }
  
  public static void subscribe(Context paramContext, String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(AppInfoHolder.getInstance(paramContext).getAppID())) || (TextUtils.isEmpty(paramString1))) {
      return;
    }
    if (System.currentTimeMillis() - topicSubscribedTime(paramContext, paramString1) > 86400000L)
    {
      XmPushActionSubscription localXmPushActionSubscription = new XmPushActionSubscription();
      localXmPushActionSubscription.setId(generatePacketID());
      localXmPushActionSubscription.setAppId(AppInfoHolder.getInstance(paramContext).getAppID());
      localXmPushActionSubscription.setTopic(paramString1);
      localXmPushActionSubscription.setPackageName(paramContext.getPackageName());
      localXmPushActionSubscription.setCategory(paramString2);
      PushServiceClient.getInstance(paramContext).sendMessage(localXmPushActionSubscription, ActionType.Subscription, null);
      return;
    }
    if (1 == PushMessageHelper.getPushMode(paramContext))
    {
      PushMessageHandler.onSubscribeResult(paramContext, paramString2, 0L, null, paramString1);
      return;
    }
    paramString2 = new ArrayList();
    paramString2.add(paramString1);
    PushMessageHelper.sendCommandMessageBroadcast(paramContext, PushMessageHelper.generateCommandMessage("subscribe-topic", paramString2, 0L, null, null));
  }
  
  public static long topicSubscribedTime(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("mipush_extra", 0).getLong("topic_" + paramString, -1L);
  }
  
  public static void unregisterPush(Context paramContext)
  {
    if (!AppInfoHolder.getInstance(paramContext).checkAppInfo()) {
      return;
    }
    XmPushActionUnRegistration localXmPushActionUnRegistration = new XmPushActionUnRegistration();
    localXmPushActionUnRegistration.setId(generatePacketID());
    localXmPushActionUnRegistration.setAppId(AppInfoHolder.getInstance(paramContext).getAppID());
    localXmPushActionUnRegistration.setRegId(AppInfoHolder.getInstance(paramContext).getRegID());
    localXmPushActionUnRegistration.setToken(AppInfoHolder.getInstance(paramContext).getAppToken());
    localXmPushActionUnRegistration.setPackageName(paramContext.getPackageName());
    PushServiceClient.getInstance(paramContext).unregister(localXmPushActionUnRegistration);
    PushMessageHandler.removeAllPushCallbackClass();
    AppInfoHolder.getInstance(paramContext).invalidate();
    clearExtras(paramContext);
    clearLocalNotificationType(paramContext);
    clearNotification(paramContext);
  }
  
  private static void updateIMEI()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        if (DeviceInfo.blockingGetIMEI(MiPushClient.sContext) != null)
        {
          XmPushActionNotification localXmPushActionNotification = new XmPushActionNotification();
          localXmPushActionNotification.setAppId(AppInfoHolder.getInstance(MiPushClient.sContext).getAppID());
          localXmPushActionNotification.setType("client_info_update");
          localXmPushActionNotification.setId(MiPushClient.generatePacketID());
          localXmPushActionNotification.setExtra(new HashMap());
          localXmPushActionNotification.getExtra().put("imei_md5", XMStringUtils.getMd5Digest(DeviceInfo.blockingGetIMEI(MiPushClient.sContext)));
          PushServiceClient.getInstance(MiPushClient.sContext).sendMessage(localXmPushActionNotification, ActionType.Notification, false, null);
        }
      }
    }).start();
  }
  
  @Deprecated
  public static abstract class MiPushClientCallback
  {
    private String category;
    
    protected String getCategory()
    {
      return category;
    }
    
    public void onCommandResult(String paramString1, long paramLong, String paramString2, List<String> paramList) {}
    
    public void onInitializeResult(long paramLong, String paramString1, String paramString2) {}
    
    public void onReceiveMessage(MiPushMessage paramMiPushMessage) {}
    
    public void onReceiveMessage(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {}
    
    public void onSubscribeResult(long paramLong, String paramString1, String paramString2) {}
    
    public void onUnsubscribeResult(long paramLong, String paramString1, String paramString2) {}
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.MiPushClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */