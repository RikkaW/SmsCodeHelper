package com.xiaomi.mms.mx.fw;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import android.util.LruCache;
import com.xiaomi.mms.mx.fw.fdata.HmsConversationInfo;
import com.xiaomi.mms.mx.fw.fdata.MessageData;
import com.xiaomi.mms.mx.fw.xmppmsg.HmsExtensionData;
import com.xiaomi.mms.mx.fw.xmppmsg.HmsExtensionData.HmsMessageEntry;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import miui.provider.ExtraTelephony.Hms;
import org.json.JSONException;
import org.json.JSONObject;

public class HmsPersister
{
  private static Context mAppContext = null;
  private static Object mLock = new Object();
  private static long sCurrentMessageId = -1L;
  private static HmsPersister sInstance;
  private ConcurrentLinkedQueue<AckData> mAckDataQueue = null;
  private ConcurrentLinkedQueue<Message> mMessageReceivedLinkedQueue;
  
  private HmsPersister(Context paramContext)
  {
    mAppContext = paramContext;
    mAckDataQueue = new ConcurrentLinkedQueue();
    mMessageReceivedLinkedQueue = new ConcurrentLinkedQueue();
  }
  
  private static ContentValues convertToContentValues(Context paramContext, MessageData paramMessageData)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("read", Integer.valueOf(mIsRead));
    localContentValues.put("seen", Integer.valueOf(0));
    localContentValues.put("date", Long.valueOf(mReceivedTime));
    localContentValues.put("mx_type", Integer.valueOf(paramMessageData.getType()));
    HashMap localHashMap = new HashMap();
    localHashMap.put("body", mBody);
    if (mIsTemplate) {
      localHashMap.put("template", "");
    }
    localContentValues.put("mx_extension", putToMxExtension(mExt, localHashMap));
    localContentValues.put("snippet", generateSnippet(mExt));
    localContentValues.put("address", mFrom);
    localContentValues.put("advanced_seen", Integer.valueOf(1));
    localContentValues.put("mx_content", generateMxContent(mExt));
    localContentValues.put("type", Integer.valueOf(1));
    localContentValues.put("mx_seq", Long.valueOf(mSeq));
    long l = Telephony.Threads.getOrCreateThreadId(paramContext, mFrom);
    if (l > 0L)
    {
      localContentValues.put("thread_id", Long.valueOf(l));
      return localContentValues;
    }
    Log.w("HmsPersister", "this is a bad message , because this message data address " + mFrom + " do not have address like xxxxx@xiaomi.com");
    return localContentValues;
  }
  
  private void enqueueAckMessage(boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
  {
    if (!paramBoolean1)
    {
      AckData localAckData = new AckData();
      toAccountSmtp = paramMessage.getFrom();
      extId = paramMessage.getPacketID();
      ext = "received";
      seq = paramMessage.getSeq();
      isGlobal = paramBoolean2;
      mAckDataQueue.add(localAckData);
    }
  }
  
  public static String generateMxContent(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = HmsExtensionData.get(paramString);
      if ((paramString != null) && (paramString.getHmsMessageEntry() != null)) {
        localStringBuilder.append(getHmsMessageEntrycontent);
      }
    }
    paramString = null;
    if (!TextUtils.isEmpty(localStringBuilder.toString())) {
      paramString = localStringBuilder.toString().replaceAll("<[^>]*>", "");
    }
    return paramString;
  }
  
  public static long generateNewId(Context arg0)
  {
    for (;;)
    {
      long l;
      int i;
      synchronized (mLock)
      {
        l = sCurrentMessageId;
        if (sCurrentMessageId < 0L)
        {
          i = 1;
          if (i != 0) {
            l = initIdGenerator(???);
          }
        }
      }
      synchronized (mLock)
      {
        if (sCurrentMessageId < 0L) {
          sCurrentMessageId = l;
        }
        l = sCurrentMessageId;
        sCurrentMessageId = 1L + l;
        return l;
        i = 0;
        continue;
        ??? = finally;
        throw ???;
      }
    }
  }
  
  public static String generateSnippet(String paramString)
  {
    String str3 = "";
    String str2 = "";
    String str1 = str2;
    if (!TextUtils.isEmpty(paramString))
    {
      HmsExtensionData localHmsExtensionData = HmsExtensionData.get(paramString);
      paramString = str3;
      if (localHmsExtensionData != null)
      {
        paramString = str3;
        if (localHmsExtensionData.getHmsMessageEntry() != null)
        {
          str1 = getHmsMessageEntrytitle;
          paramString = str1;
          if (TextUtils.isEmpty(str1)) {
            paramString = getHmsMessageEntrycontent;
          }
        }
      }
      str1 = str2;
      if (!TextUtils.isEmpty(paramString)) {
        str1 = paramString.replaceAll("<[^>]*>", "");
      }
    }
    return str1;
  }
  
  public static Context getContext()
  {
    return mAppContext;
  }
  
  private static HmsConversationInfo getHmsConversationInfo(Context paramContext, String paramString)
  {
    Cursor localCursor = getNewestHmsConversationInfo(paramContext, paramString);
    if ((localCursor != null) && (localCursor.moveToFirst()))
    {
      paramContext = localCursor.getString(localCursor.getColumnIndex("mx_seq"));
      if (TextUtils.isEmpty(paramContext)) {}
    }
    for (paramContext = new HmsConversationInfo(paramContext);; paramContext = null)
    {
      if ((localCursor != null) && (!localCursor.isClosed())) {
        localCursor.close();
      }
      if (Log.isDebug()) {
        if (paramContext == null) {
          break label129;
        }
      }
      label129:
      for (long l = paramContext.getReadSeq();; l = 0L)
      {
        Log.d("HmsPersister", "get hms conversation info with miid : " + paramString + " seq : " + l);
        return paramContext;
      }
    }
  }
  
  private static HmsConversationInfo getHmsConversationInfo(String paramString)
  {
    return getHmsConversationInfo(getContext(), paramString);
  }
  
  public static HmsPersister getInstance()
  {
    return getInstance(mAppContext);
  }
  
  public static HmsPersister getInstance(Context paramContext)
  {
    if (sInstance == null) {}
    try
    {
      if (sInstance == null) {
        sInstance = new HmsPersister(paramContext);
      }
      return sInstance;
    }
    finally {}
  }
  
  private static long getMaxMessageId(Context paramContext)
  {
    Context localContext = null;
    try
    {
      paramContext = paramContext.getContentResolver().query(ExtraTelephony.Hms.CONTENT_URI, new String[] { "_id" }, null, null, " _id DESC  LIMIT 1");
      if (paramContext != null)
      {
        localContext = paramContext;
        if (paramContext.moveToFirst())
        {
          localContext = paramContext;
          long l = paramContext.getLong(0);
          return l;
        }
      }
      return 0L;
    }
    finally
    {
      if (localContext != null) {
        localContext.close();
      }
    }
  }
  
  public static Cursor getNewestHmsConversationInfo(Context paramContext, String paramString)
  {
    Object localObject = null;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    paramString = localContentResolver.query(Uri.parse("content://hms/address/" + URLEncoder.encode(paramString)), new String[] { "thread_id" }, null, null, "date DESC limit 1");
    paramContext = (Context)localObject;
    if (paramString != null) {
      paramContext = (Context)localObject;
    }
    try
    {
      if (paramString.moveToFirst())
      {
        long l = paramString.getLong(paramString.getColumnIndex("thread_id"));
        paramContext = (Context)localObject;
        if (l > 0L)
        {
          paramContext = "_id = " + l;
          paramContext = localContentResolver.query(Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "conversations").buildUpon().appendQueryParameter("simple", "true").build(), new String[] { "mx_seq" }, paramContext, null, null);
        }
      }
      return paramContext;
    }
    finally
    {
      if ((paramString != null) && (!paramString.isClosed())) {
        paramString.close();
      }
    }
  }
  
  private void handleHmsAckReceived(Message paramMessage)
  {
    paramMessage = paramMessage.getExtension("received").getAttributeValue("id");
    String str = "mx_message_id = " + paramMessage;
    HmsMessageLogicHelper.updateHmsTypeByMessageId(getContext(), paramMessage, 2);
    HmsMessageLogicHelper.moveHmsToFolder(getContext(), 2, str);
  }
  
  private void handleHmsXmppReceived(MessageData paramMessageData)
  {
    Log.d("HmsPersister", "handle received msg count 1");
    Object localObject = addMessageToUri(paramMessageData);
    StringBuilder localStringBuilder = new StringBuilder().append("handle insert to table and return row : ");
    if (localObject != null) {}
    for (localObject = ((Uri)localObject).toSafeString();; localObject = "")
    {
      Log.d("HmsPersister", (String)localObject);
      localObject = new HmsConversationInfo();
      ((HmsConversationInfo)localObject).setReadSeq(Long.valueOf(mSeq).longValue());
      long l = Telephony.Threads.getOrCreateThreadId(mAppContext, mFrom);
      if (l >= 0L) {
        updateSeq((HmsConversationInfo)localObject, "_id = " + l, null);
      }
      putToCache(mFrom, (HmsConversationInfo)localObject);
      sendAckMessage();
      localObject = new Intent("com.xiaomi.mms.action_hms_notification");
      ((Intent)localObject).putExtra("extra_hms_notification_address", mFrom);
      ((Intent)localObject).putExtra("extra_hms_notification_body", mBody);
      ((Intent)localObject).setPackage(getContext().getPackageName());
      getContext().startService((Intent)localObject);
      return;
    }
  }
  
  private static long initIdGenerator(Context paramContext)
  {
    long l2 = System.currentTimeMillis();
    long l3 = getMaxMessageId(paramContext);
    long l1 = l2;
    if (l3 > l2) {
      l1 = l3 + 1L;
    }
    Log.i("HmsPersister", "the sId is initialized to be " + l1);
    return l1;
  }
  
  /* Error */
  private boolean isAlreadyReceived(Message paramMessage)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual 194	com/xiaomi/smack/packet/Message:getFrom	()Ljava/lang/String;
    //   6: astore 4
    //   8: aload_0
    //   9: aload 4
    //   11: invokevirtual 488	com/xiaomi/mms/mx/fw/HmsPersister:get	(Ljava/lang/String;)Lcom/xiaomi/mms/mx/fw/fdata/HmsConversationInfo;
    //   14: ifnull +9 -> 23
    //   17: getstatic 27	com/xiaomi/mms/mx/fw/HmsPersister:mAppContext	Landroid/content/Context;
    //   20: ifnonnull +5 -> 25
    //   23: iconst_0
    //   24: ireturn
    //   25: getstatic 27	com/xiaomi/mms/mx/fw/HmsPersister:mAppContext	Landroid/content/Context;
    //   28: invokevirtual 327	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   31: astore 5
    //   33: new 168	java/lang/StringBuilder
    //   36: dup
    //   37: ldc -120
    //   39: invokespecial 489	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   42: ldc_w 491
    //   45: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: ldc -106
    //   50: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: ldc_w 493
    //   56: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: astore 6
    //   64: aload_1
    //   65: invokevirtual 211	com/xiaomi/smack/packet/Message:getSeq	()Ljava/lang/String;
    //   68: astore_1
    //   69: aload 5
    //   71: getstatic 333	miui/provider/ExtraTelephony$Hms:CONTENT_URI	Landroid/net/Uri;
    //   74: iconst_1
    //   75: anewarray 246	java/lang/String
    //   78: dup
    //   79: iconst_0
    //   80: ldc -106
    //   82: aastore
    //   83: aload 6
    //   85: iconst_2
    //   86: anewarray 246	java/lang/String
    //   89: dup
    //   90: iconst_0
    //   91: aload 4
    //   93: aastore
    //   94: dup
    //   95: iconst_1
    //   96: aload_1
    //   97: aastore
    //   98: aconst_null
    //   99: invokevirtual 343	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   102: astore_1
    //   103: aload_1
    //   104: ifnull +22 -> 126
    //   107: aload_1
    //   108: invokeinterface 496 1 0
    //   113: istore_2
    //   114: iload_2
    //   115: ifle +13 -> 128
    //   118: iconst_1
    //   119: istore_3
    //   120: aload_1
    //   121: invokeinterface 290 1 0
    //   126: iload_3
    //   127: ireturn
    //   128: iconst_0
    //   129: istore_3
    //   130: goto -10 -> 120
    //   133: astore 4
    //   135: aload_1
    //   136: invokeinterface 290 1 0
    //   141: aload 4
    //   143: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	144	0	this	HmsPersister
    //   0	144	1	paramMessage	Message
    //   113	2	2	i	int
    //   1	129	3	bool	boolean
    //   6	86	4	str1	String
    //   133	9	4	localObject	Object
    //   31	39	5	localContentResolver	ContentResolver
    //   62	22	6	str2	String
    // Exception table:
    //   from	to	target	type
    //   107	114	133	finally
  }
  
  private boolean isValidSeq(String paramString)
  {
    boolean bool = false;
    if (TextUtils.isEmpty(paramString))
    {
      Log.d("HmsPersister", "the message is invalid because the seq is null");
      return false;
    }
    long l1 = -1L;
    try
    {
      long l2 = Long.parseLong(paramString);
      l1 = l2;
      bool = true;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        Log.e("HmsPersister", "error seq :" + paramString);
      }
    }
    if (l1 < 0L) {
      bool = false;
    }
    return bool;
  }
  
  private static String putToMxExtension(String paramString, Map<String, Object> paramMap)
  {
    JSONObject localJSONObject;
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        localJSONObject = new JSONObject(paramString);
        paramMap = paramMap.entrySet().iterator();
        while (paramMap.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramMap.next();
          localJSONObject.put((String)localEntry.getKey(), localEntry.getValue());
          continue;
          return paramString;
        }
      }
      catch (JSONException paramMap)
      {
        Log.e("HmsPersister", "failed to put some fields to mxExtension");
      }
    }
    paramMap = localJSONObject.toString();
    return paramMap;
  }
  
  private void sendAckMessage()
  {
    for (;;)
    {
      AckData localAckData = (AckData)mAckDataQueue.poll();
      if (localAckData == null) {
        break;
      }
      HmsChannelService.sendAckMessage(mAppContext, toAccountSmtp, ext, extId, seq, isGlobal);
    }
  }
  
  public Uri addMessageToUri(MessageData paramMessageData)
  {
    ContentResolver localContentResolver = mAppContext.getContentResolver();
    paramMessageData = convertToContentValues(mAppContext, paramMessageData);
    return localContentResolver.insert(ExtraTelephony.Hms.CONTENT_URI, paramMessageData);
  }
  
  public HmsConversationInfo get(String paramString)
  {
    HmsConversationInfo localHmsConversationInfo = HmsConversationInfoCache.INSTANCE.get(paramString);
    if (localHmsConversationInfo != null) {}
    for (long l = localHmsConversationInfo.getReadSeq();; l = -1L)
    {
      Log.d("HmsPersister", "get local seq with miid : " + paramString + "  seq : " + l);
      return localHmsConversationInfo;
    }
  }
  
  public MessageData process(Message paramMessage)
  {
    String str = paramMessage.getFrom();
    if (TextUtils.isEmpty(str))
    {
      Log.e("HmsPersister", String.format("received a message sent by someone in my black list, from=%1$s, id=%2$s", new Object[] { str, paramMessage.getFrom() }));
      return null;
    }
    Log.w("HmsPersister", String.format("received message from %1$s  to %2$s, id = %3$s, type = %4$s", new Object[] { str, paramMessage.getTo(), paramMessage.getPacketID(), paramMessage.getType() }));
    return HmsXMPPProcessor.getInstance(mAppContext).processHmsMessage(paramMessage);
  }
  
  public void process(Vector<Message> paramVector)
  {
    mMessageReceivedLinkedQueue.addAll(paramVector);
    for (;;)
    {
      paramVector = (Message)mMessageReceivedLinkedQueue.poll();
      if (paramVector == null) {
        break;
      }
      if (paramVector.getType().equalsIgnoreCase("ack"))
      {
        handleHmsAckReceived(paramVector);
      }
      else
      {
        boolean bool1 = MipubMessageUtils.isTransient(paramVector);
        boolean bool2 = MipubMessageUtils.isGlobal(paramVector);
        if (!isValidSeq(paramVector.getSeq()))
        {
          Log.d("HmsPersister", "this message seq is invalid ,drop this message! the seq is " + paramVector.getSeq());
          enqueueAckMessage(bool1, bool2, paramVector);
          sendAckMessage();
        }
        else if (isAlreadyReceived(paramVector))
        {
          Log.d("HmsPersister", "this message already received! the seq is " + paramVector.getSeq());
          enqueueAckMessage(bool1, bool2, paramVector);
          sendAckMessage();
        }
        else
        {
          MessageData localMessageData = process(paramVector);
          if (localMessageData != null) {
            enqueueAckMessage(bool1, bool2, paramVector);
          }
          if (localMessageData != null) {
            handleHmsXmppReceived(localMessageData);
          }
        }
      }
    }
  }
  
  public void putToCache(String paramString, HmsConversationInfo paramHmsConversationInfo)
  {
    HmsConversationInfoCache.INSTANCE.put(paramString, paramHmsConversationInfo);
  }
  
  public int updateSeq(HmsConversationInfo paramHmsConversationInfo, String paramString, String[] paramArrayOfString)
  {
    ContentResolver localContentResolver = mAppContext.getContentResolver();
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("mx_seq", paramHmsConversationInfo.toJson());
    return localContentResolver.update(Telephony.Threads.CONTENT_URI, localContentValues, paramString, paramArrayOfString);
  }
  
  public static class AckData
  {
    public boolean deliverRequired = false;
    public String ext;
    public String extId;
    public boolean isGlobal = false;
    public String seq;
    public String toAccountSmtp;
  }
  
  private static enum HmsConversationInfoCache
  {
    INSTANCE;
    
    private LruCache<String, HmsConversationInfo> mLruCache = new LruCache(40);
    
    private HmsConversationInfoCache() {}
    
    public HmsConversationInfo get(String paramString)
    {
      Object localObject;
      if (TextUtils.isEmpty(paramString)) {
        localObject = null;
      }
      HmsConversationInfo localHmsConversationInfo;
      do
      {
        do
        {
          return (HmsConversationInfo)localObject;
          localHmsConversationInfo = (HmsConversationInfo)mLruCache.get(paramString);
          localObject = localHmsConversationInfo;
        } while (localHmsConversationInfo != null);
        localHmsConversationInfo = HmsPersister.getHmsConversationInfo(paramString);
        localObject = localHmsConversationInfo;
      } while (localHmsConversationInfo == null);
      mLruCache.put(paramString, localHmsConversationInfo);
      return localHmsConversationInfo;
    }
    
    public void put(String paramString, HmsConversationInfo paramHmsConversationInfo)
    {
      if ((TextUtils.isEmpty(paramString)) || (paramHmsConversationInfo == null)) {
        return;
      }
      synchronized (INSTANCE)
      {
        mLruCache.put(paramString, paramHmsConversationInfo);
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.HmsPersister
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */