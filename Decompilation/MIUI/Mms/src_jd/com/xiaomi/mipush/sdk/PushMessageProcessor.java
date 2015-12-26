package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.push.service.MIPushNotificationHelper;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMessage;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionCommandResult;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import com.xiaomi.xmpush.thrift.XmPushActionSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;

public class PushMessageProcessor
{
  private static Object lock = new Object();
  private static Queue<String> mCachedMsgIds;
  private static PushMessageProcessor sInstance = null;
  private Context sAppContext;
  
  private PushMessageProcessor(Context paramContext)
  {
    sAppContext = paramContext.getApplicationContext();
    if (sAppContext == null) {
      sAppContext = paramContext;
    }
  }
  
  private void ackMessage(XmPushActionContainer paramXmPushActionContainer)
  {
    PushMetaInfo localPushMetaInfo = paramXmPushActionContainer.getMetaInfo();
    XmPushActionAckMessage localXmPushActionAckMessage = new XmPushActionAckMessage();
    localXmPushActionAckMessage.setAppId(paramXmPushActionContainer.getAppid());
    localXmPushActionAckMessage.setId(localPushMetaInfo.getId());
    localXmPushActionAckMessage.setMessageTs(localPushMetaInfo.getMessageTs());
    if (!TextUtils.isEmpty(localPushMetaInfo.getTopic())) {
      localXmPushActionAckMessage.setTopic(localPushMetaInfo.getTopic());
    }
    PushServiceClient.getInstance(sAppContext).sendMessage(localXmPushActionAckMessage, ActionType.AckMessage, false, paramXmPushActionContainer.getMetaInfo());
  }
  
  private void ackMessage(XmPushActionSendMessage paramXmPushActionSendMessage, PushMetaInfo paramPushMetaInfo)
  {
    XmPushActionAckMessage localXmPushActionAckMessage = new XmPushActionAckMessage();
    localXmPushActionAckMessage.setAppId(paramXmPushActionSendMessage.getAppId());
    localXmPushActionAckMessage.setId(paramXmPushActionSendMessage.getId());
    localXmPushActionAckMessage.setMessageTs(paramXmPushActionSendMessage.getMessage().getCreateAt());
    if (!TextUtils.isEmpty(paramXmPushActionSendMessage.getTopic())) {
      localXmPushActionAckMessage.setTopic(paramXmPushActionSendMessage.getTopic());
    }
    if (!TextUtils.isEmpty(paramXmPushActionSendMessage.getAliasName())) {
      localXmPushActionAckMessage.setAliasName(paramXmPushActionSendMessage.getAliasName());
    }
    PushServiceClient.getInstance(sAppContext).sendMessage(localXmPushActionAckMessage, ActionType.AckMessage, paramPushMetaInfo);
  }
  
  public static PushMessageProcessor getInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new PushMessageProcessor(paramContext);
    }
    return sInstance;
  }
  
  /* Error */
  public static Intent getNotificationMessageIntent(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnull +14 -> 15
    //   4: aload_2
    //   5: ldc -118
    //   7: invokeinterface 144 2 0
    //   12: ifne +5 -> 17
    //   15: aconst_null
    //   16: areturn
    //   17: aload_2
    //   18: ldc -118
    //   20: invokeinterface 148 2 0
    //   25: checkcast 150	java/lang/String
    //   28: astore_3
    //   29: getstatic 156	com/xiaomi/push/service/PushConstants:NOTIFICATION_CLICK_DEFAULT	Ljava/lang/String;
    //   32: aload_3
    //   33: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   36: ifeq +71 -> 107
    //   39: aload_0
    //   40: invokevirtual 163	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   43: aload_1
    //   44: invokevirtual 169	android/content/pm/PackageManager:getLaunchIntentForPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   47: astore_1
    //   48: aload_1
    //   49: ifnull -34 -> 15
    //   52: aload_1
    //   53: ldc -86
    //   55: invokevirtual 176	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   58: pop
    //   59: aload_0
    //   60: invokevirtual 163	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   63: aload_1
    //   64: ldc -79
    //   66: invokevirtual 181	android/content/pm/PackageManager:resolveActivity	(Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
    //   69: astore_0
    //   70: aload_0
    //   71: ifnull -56 -> 15
    //   74: aload_1
    //   75: areturn
    //   76: astore_1
    //   77: new 183	java/lang/StringBuilder
    //   80: dup
    //   81: invokespecial 184	java/lang/StringBuilder:<init>	()V
    //   84: ldc -70
    //   86: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: aload_1
    //   90: invokevirtual 192	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   93: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 195	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokestatic 201	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/String;)V
    //   102: aconst_null
    //   103: astore_1
    //   104: goto -56 -> 48
    //   107: getstatic 204	com/xiaomi/push/service/PushConstants:NOTIFICATION_CLICK_INTENT	Ljava/lang/String;
    //   110: aload_3
    //   111: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   114: ifeq +189 -> 303
    //   117: aload_2
    //   118: ldc -50
    //   120: invokeinterface 144 2 0
    //   125: ifeq +67 -> 192
    //   128: aload_2
    //   129: ldc -50
    //   131: invokeinterface 148 2 0
    //   136: checkcast 150	java/lang/String
    //   139: astore_2
    //   140: aload_2
    //   141: ifnull +375 -> 516
    //   144: aload_2
    //   145: iconst_1
    //   146: invokestatic 210	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   149: astore_2
    //   150: aload_2
    //   151: aload_1
    //   152: invokevirtual 213	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   155: pop
    //   156: aload_2
    //   157: astore_1
    //   158: goto -110 -> 48
    //   161: astore_3
    //   162: aload_2
    //   163: astore_1
    //   164: new 183	java/lang/StringBuilder
    //   167: dup
    //   168: invokespecial 184	java/lang/StringBuilder:<init>	()V
    //   171: ldc -70
    //   173: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: aload_3
    //   177: invokevirtual 214	java/net/URISyntaxException:getMessage	()Ljava/lang/String;
    //   180: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: invokevirtual 195	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: invokestatic 201	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/String;)V
    //   189: goto -141 -> 48
    //   192: aload_2
    //   193: ldc -40
    //   195: invokeinterface 144 2 0
    //   200: ifeq +311 -> 511
    //   203: aload_2
    //   204: ldc -40
    //   206: invokeinterface 148 2 0
    //   211: checkcast 150	java/lang/String
    //   214: astore 4
    //   216: new 172	android/content/Intent
    //   219: dup
    //   220: invokespecial 217	android/content/Intent:<init>	()V
    //   223: astore_3
    //   224: aload_3
    //   225: new 219	android/content/ComponentName
    //   228: dup
    //   229: aload_1
    //   230: aload 4
    //   232: invokespecial 222	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   235: invokevirtual 226	android/content/Intent:setComponent	(Landroid/content/ComponentName;)Landroid/content/Intent;
    //   238: pop
    //   239: aload_2
    //   240: ldc -28
    //   242: invokeinterface 144 2 0
    //   247: ifeq +22 -> 269
    //   250: aload_3
    //   251: aload_2
    //   252: ldc -28
    //   254: invokeinterface 148 2 0
    //   259: checkcast 150	java/lang/String
    //   262: invokestatic 234	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   265: invokevirtual 237	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   268: pop
    //   269: aload_3
    //   270: astore_1
    //   271: goto -223 -> 48
    //   274: astore_1
    //   275: new 183	java/lang/StringBuilder
    //   278: dup
    //   279: invokespecial 184	java/lang/StringBuilder:<init>	()V
    //   282: ldc -17
    //   284: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: aload_1
    //   288: invokevirtual 240	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   291: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: invokevirtual 195	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   297: invokestatic 201	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/String;)V
    //   300: goto -31 -> 269
    //   303: getstatic 243	com/xiaomi/push/service/PushConstants:NOTIFICATION_CLICK_WEB_PAGE	Ljava/lang/String;
    //   306: aload_3
    //   307: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   310: ifeq +201 -> 511
    //   313: aload_2
    //   314: ldc -11
    //   316: invokeinterface 148 2 0
    //   321: checkcast 150	java/lang/String
    //   324: astore_1
    //   325: aload_1
    //   326: ifnull +185 -> 511
    //   329: aload_1
    //   330: invokevirtual 248	java/lang/String:trim	()Ljava/lang/String;
    //   333: astore_1
    //   334: aload_1
    //   335: ldc -6
    //   337: invokevirtual 254	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   340: ifne +168 -> 508
    //   343: aload_1
    //   344: ldc_w 256
    //   347: invokevirtual 254	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   350: ifne +158 -> 508
    //   353: new 183	java/lang/StringBuilder
    //   356: dup
    //   357: invokespecial 184	java/lang/StringBuilder:<init>	()V
    //   360: ldc -6
    //   362: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: aload_1
    //   366: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   369: invokevirtual 195	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   372: astore_1
    //   373: new 258	java/net/URL
    //   376: dup
    //   377: aload_1
    //   378: invokespecial 260	java/net/URL:<init>	(Ljava/lang/String;)V
    //   381: invokevirtual 263	java/net/URL:getProtocol	()Ljava/lang/String;
    //   384: astore_2
    //   385: ldc_w 265
    //   388: aload_2
    //   389: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   392: ifne +13 -> 405
    //   395: ldc_w 267
    //   398: aload_2
    //   399: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   402: ifeq +101 -> 503
    //   405: new 172	android/content/Intent
    //   408: dup
    //   409: ldc_w 269
    //   412: invokespecial 270	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   415: astore_2
    //   416: aload_2
    //   417: aload_1
    //   418: invokestatic 276	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   421: invokevirtual 280	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   424: pop
    //   425: aload_2
    //   426: astore_1
    //   427: goto -379 -> 48
    //   430: astore_3
    //   431: aload_2
    //   432: astore_1
    //   433: aload_3
    //   434: astore_2
    //   435: new 183	java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial 184	java/lang/StringBuilder:<init>	()V
    //   442: ldc -70
    //   444: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: aload_2
    //   448: invokevirtual 281	java/net/MalformedURLException:getMessage	()Ljava/lang/String;
    //   451: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: invokevirtual 195	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   457: invokestatic 201	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/String;)V
    //   460: goto -412 -> 48
    //   463: astore_0
    //   464: new 183	java/lang/StringBuilder
    //   467: dup
    //   468: invokespecial 184	java/lang/StringBuilder:<init>	()V
    //   471: ldc -70
    //   473: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   476: aload_0
    //   477: invokevirtual 192	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   480: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: invokevirtual 195	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   486: invokestatic 201	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/String;)V
    //   489: aconst_null
    //   490: areturn
    //   491: astore_2
    //   492: aconst_null
    //   493: astore_1
    //   494: goto -59 -> 435
    //   497: astore_3
    //   498: aconst_null
    //   499: astore_1
    //   500: goto -336 -> 164
    //   503: aconst_null
    //   504: astore_1
    //   505: goto -457 -> 48
    //   508: goto -135 -> 373
    //   511: aconst_null
    //   512: astore_1
    //   513: goto -465 -> 48
    //   516: aconst_null
    //   517: astore_1
    //   518: goto -470 -> 48
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	521	0	paramContext	Context
    //   0	521	1	paramString	String
    //   0	521	2	paramMap	Map<String, String>
    //   28	83	3	str1	String
    //   161	16	3	localURISyntaxException1	java.net.URISyntaxException
    //   223	84	3	localIntent	Intent
    //   430	4	3	localMalformedURLException	java.net.MalformedURLException
    //   497	1	3	localURISyntaxException2	java.net.URISyntaxException
    //   214	17	4	str2	String
    // Exception table:
    //   from	to	target	type
    //   39	48	76	java/lang/Exception
    //   150	156	161	java/net/URISyntaxException
    //   239	269	274	java/lang/NumberFormatException
    //   416	425	430	java/net/MalformedURLException
    //   59	70	463	java/lang/Exception
    //   373	405	491	java/net/MalformedURLException
    //   405	416	491	java/net/MalformedURLException
    //   144	150	497	java/net/URISyntaxException
  }
  
  private static boolean isDuplicateMessage(Context paramContext, String paramString)
  {
    synchronized (lock)
    {
      paramContext = AppInfoHolder.getInstance(paramContext).getSharedPreferences();
      if (mCachedMsgIds == null)
      {
        String[] arrayOfString = paramContext.getString("pref_msg_ids", "").split(",");
        mCachedMsgIds = new LinkedList();
        int j = arrayOfString.length;
        int i = 0;
        while (i < j)
        {
          String str = arrayOfString[i];
          mCachedMsgIds.add(str);
          i += 1;
        }
      }
      if (mCachedMsgIds.contains(paramString)) {
        return true;
      }
      mCachedMsgIds.add(paramString);
      if (mCachedMsgIds.size() > 10) {
        mCachedMsgIds.poll();
      }
      paramString = XMStringUtils.join(mCachedMsgIds, ",");
      paramContext = paramContext.edit();
      paramContext.putString("pref_msg_ids", paramString);
      paramContext.commit();
      return false;
    }
  }
  
  private PushMessageHandler.PushMessageInterface processMessage(XmPushActionContainer paramXmPushActionContainer, boolean paramBoolean, byte[] paramArrayOfByte)
  {
    for (;;)
    {
      try
      {
        localObject1 = PushContainerHelper.getResponseMessageBodyFromContainer(sAppContext, paramXmPushActionContainer);
        if (localObject1 == null)
        {
          MyLog.e("receiving an un-recognized message. " + action);
          return null;
        }
        MyLog.v("receive a message." + localObject1);
        localObject2 = paramXmPushActionContainer.getAction();
        MyLog.warn("processing a message, action=" + localObject2);
        switch (localObject2)
        {
        default: 
          return null;
        }
      }
      catch (TException paramXmPushActionContainer)
      {
        MyLog.e(paramXmPushActionContainer);
        MyLog.e("receive a message which action string is not valid. is the reg expired?");
        return null;
      }
      paramArrayOfByte = (XmPushActionRegistrationResult)localObject1;
      if (errorCode == 0L) {
        AppInfoHolder.getInstance(sAppContext).putRegIDAndSecret(regId, regSecret);
      }
      paramXmPushActionContainer = null;
      if (!TextUtils.isEmpty(regId))
      {
        paramXmPushActionContainer = new ArrayList();
        paramXmPushActionContainer.add(regId);
      }
      paramXmPushActionContainer = PushMessageHelper.generateCommandMessage("register", paramXmPushActionContainer, errorCode, reason, null);
      PushServiceClient.getInstance(sAppContext).processPendRequest();
      return paramXmPushActionContainer;
      if (errorCode == 0L)
      {
        AppInfoHolder.getInstance(sAppContext).clear();
        MiPushClient.clearExtras(sAppContext);
      }
      PushMessageHandler.removeAllPushCallbackClass();
      continue;
      if ((AppInfoHolder.getInstance(sAppContext).isPaused()) && (!paramBoolean))
      {
        MyLog.warn("receive a message in pause state. drop it");
        return null;
      }
      XmPushActionSendMessage localXmPushActionSendMessage = (XmPushActionSendMessage)localObject1;
      PushMessage localPushMessage = localXmPushActionSendMessage.getMessage();
      if (localPushMessage == null)
      {
        MyLog.e("receive an empty message without push content, drop it");
        return null;
      }
      if (paramBoolean)
      {
        if (MIPushNotificationHelper.isBusinessMessage(paramXmPushActionContainer)) {
          MiPushClient.reportIgnoreRegMessageClicked(sAppContext, localPushMessage.getId(), paramXmPushActionContainer.getMetaInfo(), packageName, localPushMessage.getAppId());
        }
      }
      else
      {
        if (!paramBoolean)
        {
          if ((TextUtils.isEmpty(localXmPushActionSendMessage.getAliasName())) || (MiPushClient.aliasSetTime(sAppContext, localXmPushActionSendMessage.getAliasName()) >= 0L)) {
            break label584;
          }
          MiPushClient.addAlias(sAppContext, localXmPushActionSendMessage.getAliasName());
        }
        label424:
        Object localObject3 = null;
        localObject2 = null;
        localObject1 = localObject2;
        if (metaInfo != null)
        {
          localObject1 = localObject2;
          if (metaInfo.getExtra() != null) {
            localObject1 = (String)metaInfo.extra.get("jobkey");
          }
        }
        localObject2 = localObject1;
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          localObject2 = localPushMessage.getId();
        }
        if ((paramBoolean) || (!isDuplicateMessage(sAppContext, (String)localObject2))) {
          break label627;
        }
        MyLog.warn("drop a duplicate message, key=" + (String)localObject2);
        paramArrayOfByte = (byte[])localObject3;
      }
      label584:
      label627:
      do
      {
        do
        {
          do
          {
            if ((paramXmPushActionContainer.getMetaInfo() == null) && (!paramBoolean)) {
              ackMessage(localXmPushActionSendMessage, paramXmPushActionContainer.getMetaInfo());
            }
            return paramArrayOfByte;
            MiPushClient.reportMessageClicked(sAppContext, localPushMessage.getId(), paramXmPushActionContainer.getMetaInfo(), localPushMessage.getAppId());
            break;
            if ((TextUtils.isEmpty(localXmPushActionSendMessage.getTopic())) || (MiPushClient.topicSubscribedTime(sAppContext, localXmPushActionSendMessage.getTopic()) >= 0L)) {
              break label424;
            }
            MiPushClient.addTopic(sAppContext, localXmPushActionSendMessage.getTopic());
            break label424;
            localObject1 = PushMessageHelper.generateMessage(localXmPushActionSendMessage, paramXmPushActionContainer.getMetaInfo(), paramBoolean);
            if ((((MiPushMessage)localObject1).getPassThrough() == 0) && (!paramBoolean) && (MIPushNotificationHelper.isNotifyForeground(((MiPushMessage)localObject1).getExtra())))
            {
              MIPushNotificationHelper.notifyPushMessage(sAppContext, paramXmPushActionContainer, paramArrayOfByte);
              return null;
            }
            MyLog.warn("receive a message, msgid=" + localPushMessage.getId() + ", jobkey=" + (String)localObject2);
            paramArrayOfByte = (byte[])localObject1;
          } while (!paramBoolean);
          paramArrayOfByte = (byte[])localObject1;
        } while (((MiPushMessage)localObject1).getExtra() == null);
        paramArrayOfByte = (byte[])localObject1;
      } while (!((MiPushMessage)localObject1).getExtra().containsKey("notify_effect"));
      Object localObject2 = ((MiPushMessage)localObject1).getExtra();
      paramArrayOfByte = (String)((Map)localObject2).get("notify_effect");
      if (MIPushNotificationHelper.isBusinessMessage(paramXmPushActionContainer))
      {
        paramXmPushActionContainer = getNotificationMessageIntent(sAppContext, packageName, (Map)localObject2);
        if (paramXmPushActionContainer == null)
        {
          MyLog.warn("Getting Intent fail from ignore reg message. ");
          return null;
        }
        paramArrayOfByte = localPushMessage.getPayload();
        if (!TextUtils.isEmpty(paramArrayOfByte)) {
          paramXmPushActionContainer.putExtra("payload", paramArrayOfByte);
        }
        sAppContext.startActivity(paramXmPushActionContainer);
      }
      for (;;)
      {
        return null;
        paramXmPushActionContainer = getNotificationMessageIntent(sAppContext, sAppContext.getPackageName(), (Map)localObject2);
        if (paramXmPushActionContainer != null)
        {
          if (!paramArrayOfByte.equals(PushConstants.NOTIFICATION_CLICK_WEB_PAGE)) {
            paramXmPushActionContainer.putExtra("key_message", (Serializable)localObject1);
          }
          sAppContext.startActivity(paramXmPushActionContainer);
        }
      }
      paramArrayOfByte = (XmPushActionSubscriptionResult)localObject1;
      if (errorCode == 0L) {
        MiPushClient.addTopic(sAppContext, paramArrayOfByte.getTopic());
      }
      paramXmPushActionContainer = null;
      if (!TextUtils.isEmpty(paramArrayOfByte.getTopic()))
      {
        paramXmPushActionContainer = new ArrayList();
        paramXmPushActionContainer.add(paramArrayOfByte.getTopic());
      }
      return PushMessageHelper.generateCommandMessage("subscribe-topic", paramXmPushActionContainer, errorCode, reason, paramArrayOfByte.getCategory());
      paramArrayOfByte = (XmPushActionUnSubscriptionResult)localObject1;
      if (errorCode == 0L) {
        MiPushClient.removeTopic(sAppContext, paramArrayOfByte.getTopic());
      }
      paramXmPushActionContainer = null;
      if (!TextUtils.isEmpty(paramArrayOfByte.getTopic()))
      {
        paramXmPushActionContainer = new ArrayList();
        paramXmPushActionContainer.add(paramArrayOfByte.getTopic());
      }
      return PushMessageHelper.generateCommandMessage("unsubscibe-topic", paramXmPushActionContainer, errorCode, reason, paramArrayOfByte.getCategory());
      Object localObject1 = (XmPushActionCommandResult)localObject1;
      localObject2 = ((XmPushActionCommandResult)localObject1).getCmdName();
      paramArrayOfByte = ((XmPushActionCommandResult)localObject1).getCmdArgs();
      paramXmPushActionContainer = paramArrayOfByte;
      if (errorCode == 0L)
      {
        if ((!TextUtils.equals((CharSequence)localObject2, "accept-time")) || (paramArrayOfByte == null) || (paramArrayOfByte.size() <= 1)) {
          break label1216;
        }
        MiPushClient.addAcceptTime(sAppContext, (String)paramArrayOfByte.get(0), (String)paramArrayOfByte.get(1));
        if ((!"00:00".equals(paramArrayOfByte.get(0))) || (!"00:00".equals(paramArrayOfByte.get(1)))) {
          break label1202;
        }
        AppInfoHolder.getInstance(sAppContext).setPaused(true);
        paramXmPushActionContainer = getTimeForTimeZone(TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault(), paramArrayOfByte);
      }
      for (;;)
      {
        return PushMessageHelper.generateCommandMessage((String)localObject2, paramXmPushActionContainer, errorCode, reason, ((XmPushActionCommandResult)localObject1).getCategory());
        label1202:
        AppInfoHolder.getInstance(sAppContext).setPaused(false);
        break;
        label1216:
        if ((TextUtils.equals((CharSequence)localObject2, "set-alias")) && (paramArrayOfByte != null) && (paramArrayOfByte.size() > 0))
        {
          MiPushClient.addAlias(sAppContext, (String)paramArrayOfByte.get(0));
          paramXmPushActionContainer = paramArrayOfByte;
        }
        else if ((TextUtils.equals((CharSequence)localObject2, "unset-alias")) && (paramArrayOfByte != null) && (paramArrayOfByte.size() > 0))
        {
          MiPushClient.removeAlias(sAppContext, (String)paramArrayOfByte.get(0));
          paramXmPushActionContainer = paramArrayOfByte;
        }
        else if ((TextUtils.equals((CharSequence)localObject2, "set-account")) && (paramArrayOfByte != null) && (paramArrayOfByte.size() > 0))
        {
          MiPushClient.addAccount(sAppContext, (String)paramArrayOfByte.get(0));
          paramXmPushActionContainer = paramArrayOfByte;
        }
        else
        {
          paramXmPushActionContainer = paramArrayOfByte;
          if (TextUtils.equals((CharSequence)localObject2, "unset-account"))
          {
            paramXmPushActionContainer = paramArrayOfByte;
            if (paramArrayOfByte != null)
            {
              paramXmPushActionContainer = paramArrayOfByte;
              if (paramArrayOfByte.size() > 0)
              {
                MiPushClient.removeAccount(sAppContext, (String)paramArrayOfByte.get(0));
                paramXmPushActionContainer = paramArrayOfByte;
              }
            }
          }
        }
      }
      paramXmPushActionContainer = (XmPushActionNotification)localObject1;
      if ("registration id expired".equalsIgnoreCase(type))
      {
        MiPushClient.reInitialize(sAppContext);
      }
      else if (("client_info_update_ok".equalsIgnoreCase(type)) && (paramXmPushActionContainer.getExtra() != null) && (paramXmPushActionContainer.getExtra().containsKey("app_version")))
      {
        paramXmPushActionContainer = (String)paramXmPushActionContainer.getExtra().get("app_version");
        AppInfoHolder.getInstance(sAppContext).updateVersionName(paramXmPushActionContainer);
      }
    }
  }
  
  private PushMessageHandler.PushMessageInterface processMessage(XmPushActionContainer paramXmPushActionContainer, byte[] paramArrayOfByte)
  {
    XmPushActionSendMessage localXmPushActionSendMessage;
    PushMessage localPushMessage;
    try
    {
      paramArrayOfByte = PushContainerHelper.getResponseMessageBodyFromContainer(sAppContext, paramXmPushActionContainer);
      if (paramArrayOfByte == null)
      {
        MyLog.e("message arrived: receiving an un-recognized message. " + action);
        return null;
      }
      MyLog.v("message arrived: receive a message." + paramArrayOfByte);
      localActionType = paramXmPushActionContainer.getAction();
      MyLog.warn("message arrived: processing an arrived message, action=" + localActionType);
      switch (localActionType)
      {
      default: 
        return null;
      }
      localXmPushActionSendMessage = (XmPushActionSendMessage)paramArrayOfByte;
      localPushMessage = localXmPushActionSendMessage.getMessage();
      if (localPushMessage == null)
      {
        MyLog.e("message arrived: receive an empty message without push content, drop it");
        return null;
      }
    }
    catch (TException paramXmPushActionContainer)
    {
      MyLog.e(paramXmPushActionContainer);
      MyLog.e("message arrived: receive a message which action string is not valid. is the reg expired?");
      return null;
    }
    ActionType localActionType = null;
    paramArrayOfByte = localActionType;
    if (metaInfo != null)
    {
      paramArrayOfByte = localActionType;
      if (metaInfo.getExtra() != null) {
        paramArrayOfByte = (String)metaInfo.extra.get("jobkey");
      }
    }
    paramXmPushActionContainer = PushMessageHelper.generateMessage(localXmPushActionSendMessage, paramXmPushActionContainer.getMetaInfo(), false);
    paramXmPushActionContainer.setArrivedMessage(true);
    MyLog.warn("message arrived: receive a message, msgid=" + localPushMessage.getId() + ", jobkey=" + paramArrayOfByte);
    return paramXmPushActionContainer;
  }
  
  public List<String> getTimeForTimeZone(TimeZone paramTimeZone1, TimeZone paramTimeZone2, List<String> paramList)
  {
    if (paramTimeZone1.equals(paramTimeZone2)) {
      return paramList;
    }
    long l1 = (paramTimeZone1.getRawOffset() - paramTimeZone2.getRawOffset()) / 1000 / 60;
    long l4 = Long.parseLong(((String)paramList.get(0)).split(":")[0]);
    long l5 = Long.parseLong(((String)paramList.get(0)).split(":")[1]);
    long l2 = Long.parseLong(((String)paramList.get(1)).split(":")[0]);
    long l3 = Long.parseLong(((String)paramList.get(1)).split(":")[1]);
    l4 = (60L * l4 + l5 - l1 + 1440L) % 1440L;
    l1 = (60L * l2 + l3 - l1 + 1440L) % 1440L;
    paramTimeZone1 = new ArrayList();
    paramTimeZone1.add(String.format("%1$02d:%2$02d", new Object[] { Long.valueOf(l4 / 60L), Long.valueOf(l4 % 60L) }));
    paramTimeZone1.add(String.format("%1$02d:%2$02d", new Object[] { Long.valueOf(l1 / 60L), Long.valueOf(l1 % 60L) }));
    return paramTimeZone1;
  }
  
  public PushMessageHandler.PushMessageInterface proccessIntent(Intent paramIntent)
  {
    Object localObject3 = paramIntent.getAction();
    MyLog.warn("receive an intent from server, action=" + (String)localObject3);
    Object localObject2 = paramIntent.getStringExtra("mrt");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = Long.toString(System.currentTimeMillis());
    }
    boolean bool;
    if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(localObject3))
    {
      localObject2 = paramIntent.getByteArrayExtra("mipush_payload");
      bool = paramIntent.getBooleanExtra("mipush_notified", false);
      if (localObject2 == null) {
        MyLog.e("receiving an empty message, drop");
      }
    }
    label265:
    label272:
    label297:
    label334:
    label371:
    label379:
    do
    {
      return null;
      localObject3 = new XmPushActionContainer();
      AppInfoHolder localAppInfoHolder;
      try
      {
        XmPushThriftSerializeUtils.convertByteArrayToThriftObject((TBase)localObject3, (byte[])localObject2);
        localAppInfoHolder = AppInfoHolder.getInstance(sAppContext);
        paramIntent = ((XmPushActionContainer)localObject3).getMetaInfo();
        if ((((XmPushActionContainer)localObject3).getAction() == ActionType.SendMessage) && (paramIntent != null) && (!localAppInfoHolder.isPaused()) && (!bool))
        {
          if (paramIntent != null)
          {
            ((XmPushActionContainer)localObject3).getMetaInfo().putToExtra("mrt", (String)localObject1);
            ((XmPushActionContainer)localObject3).getMetaInfo().putToExtra("mat", Long.toString(System.currentTimeMillis()));
          }
          ackMessage((XmPushActionContainer)localObject3);
        }
        if ((((XmPushActionContainer)localObject3).getAction() != ActionType.SendMessage) || (((XmPushActionContainer)localObject3).isEncryptAction())) {
          break label297;
        }
        if (MIPushNotificationHelper.isBusinessMessage((XmPushActionContainer)localObject3)) {
          break label272;
        }
        localObject1 = ((XmPushActionContainer)localObject3).getPackageName();
        if (paramIntent == null) {
          break label265;
        }
        paramIntent = paramIntent.getId();
      }
      catch (TException paramIntent)
      {
        for (;;)
        {
          MyLog.e(paramIntent);
          return null;
          paramIntent = "";
        }
        if ((!bool) || (paramIntent.getExtra() == null) || (!paramIntent.getExtra().containsKey("notify_effect"))) {
          break label334;
        }
        if ((localAppInfoHolder.appRegistered()) || (action == ActionType.Registration)) {
          break label379;
        }
        if (!MIPushNotificationHelper.isBusinessMessage((XmPushActionContainer)localObject3)) {
          break label371;
        }
        return processMessage((XmPushActionContainer)localObject3, bool, (byte[])localObject2);
        MyLog.warn(String.format("drop an un-encrypted messages. %1$s, %2$s", new Object[] { ((XmPushActionContainer)localObject3).getPackageName(), paramIntent.getId() }));
        return null;
      }
      catch (Exception paramIntent)
      {
        MyLog.e(paramIntent);
        return null;
      }
      MyLog.warn(String.format("drop an un-encrypted messages. %1$s, %2$s", new Object[] { localObject1, paramIntent }));
      return null;
      MyLog.e("receive message without registration. need unregister or re-register!");
      return null;
      if ((localAppInfoHolder.appRegistered()) && (localAppInfoHolder.invalidated()))
      {
        if (action == ActionType.UnRegistration)
        {
          localAppInfoHolder.clear();
          MiPushClient.clearExtras(sAppContext);
          PushMessageHandler.removeAllPushCallbackClass();
          return null;
        }
        MiPushClient.unregisterPush(sAppContext);
        return null;
      }
      paramIntent = processMessage((XmPushActionContainer)localObject3, bool, (byte[])localObject2);
      return paramIntent;
      if ("com.xiaomi.mipush.ERROR".equals(localObject3))
      {
        localObject1 = new MiPushCommandMessage();
        localObject2 = new XmPushActionContainer();
      }
      try
      {
        localObject3 = paramIntent.getByteArrayExtra("mipush_payload");
        if (localObject3 != null) {
          XmPushThriftSerializeUtils.convertByteArrayToThriftObject((TBase)localObject2, (byte[])localObject3);
        }
      }
      catch (TException localTException)
      {
        for (;;) {}
      }
      ((MiPushCommandMessage)localObject1).setCommand(String.valueOf(((XmPushActionContainer)localObject2).getAction()));
      ((MiPushCommandMessage)localObject1).setResultCode(paramIntent.getIntExtra("mipush_error_code", 0));
      ((MiPushCommandMessage)localObject1).setReason(paramIntent.getStringExtra("mipush_error_msg"));
      MyLog.e("receive a error message. code = " + paramIntent.getIntExtra("mipush_error_code", 0) + ", msg= " + paramIntent.getStringExtra("mipush_error_msg"));
      return (PushMessageHandler.PushMessageInterface)localObject1;
    } while (!"com.xiaomi.mipush.MESSAGE_ARRIVED".equals(localObject3));
    paramIntent = paramIntent.getByteArrayExtra("mipush_payload");
    if (paramIntent == null)
    {
      MyLog.e("message arrived: receiving an empty message, drop");
      return null;
    }
    localObject1 = new XmPushActionContainer();
    try
    {
      XmPushThriftSerializeUtils.convertByteArrayToThriftObject((TBase)localObject1, paramIntent);
      localObject2 = AppInfoHolder.getInstance(sAppContext);
      ((XmPushActionContainer)localObject1).getMetaInfo();
      if (!((AppInfoHolder)localObject2).appRegistered()) {
        if (MIPushNotificationHelper.isBusinessMessage((XmPushActionContainer)localObject1))
        {
          MyLog.e("message arrived: receive ignore reg message invalid!");
          return null;
        }
      }
    }
    catch (TException paramIntent)
    {
      MyLog.e(paramIntent);
      return null;
      MyLog.e("message arrived: receive message without registration. need unregister or re-register!");
      return null;
    }
    catch (Exception paramIntent)
    {
      MyLog.e(paramIntent);
      return null;
    }
    if ((((AppInfoHolder)localObject2).appRegistered()) && (((AppInfoHolder)localObject2).invalidated()))
    {
      MyLog.e("message arrived: app info is invalidated");
      return null;
    }
    paramIntent = processMessage((XmPushActionContainer)localObject1, paramIntent);
    return paramIntent;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.PushMessageProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */