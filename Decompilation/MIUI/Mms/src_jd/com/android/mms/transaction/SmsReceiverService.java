package com.android.mms.transaction;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Telephony.Sms;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Sms.Intents;
import android.provider.Telephony.Sms.Outbox;
import android.provider.Telephony.Threads;
import android.telephony.ServiceState;
import android.telephony.SmsMessage;
import android.telephony.SmsMessage.MessageClass;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.data.Contact;
import com.android.mms.ui.ClassZeroActivity;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.miui.gallery.lib.MiuiGalleryUtils;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SmsReceiverService
  extends Service
{
  private static final String[] REPLACE_PROJECTION = { "_id", "address", "protocol", "sim_id" };
  private static final String[] SEND_PROJECTION = { "_id", "thread_id", "address", "body", "status", "mx_status", "mx_id", "sim_id", "b2c_numbers" };
  private Runnable mDelaySend = new Runnable()
  {
    public void run()
    {
      Log.d("SmsReceiverService", "send queued message without toast");
      MSimUtils.sendQueuedMessageNoToast(SmsReceiverService.this, 0);
    }
  };
  private int mResultCode;
  private boolean[] mSending;
  private ServiceHandler mServiceHandler;
  private Looper mServiceLooper;
  public Handler mToastHandler = new Handler();
  
  private String buildMessageString(SmsMessage[] paramArrayOfSmsMessage)
  {
    Object localObject1 = new StringBuilder();
    int k = 0;
    int j = 1;
    int m = paramArrayOfSmsMessage.length;
    int i = 0;
    Object localObject2;
    while (i < m)
    {
      localObject2 = paramArrayOfSmsMessage[i];
      k += ((SmsMessage)localObject2).getUserData().length;
      if (mWrappedSmsMessage != null) {
        ((StringBuilder)localObject1).append(((SmsMessage)localObject2).getDisplayMessageBody());
      }
      if (((SmsMessage)localObject2).getEncodingType() != 3) {
        j = 0;
      }
      i += 1;
    }
    if (j != 0)
    {
      localObject1 = new byte[k];
      j = 0;
      k = paramArrayOfSmsMessage.length;
      i = 0;
      while (i < k)
      {
        localObject2 = paramArrayOfSmsMessage[i].getUserData();
        m = localObject2.length;
        System.arraycopy(localObject2, 0, localObject1, j, m);
        j += m;
        i += 1;
      }
      try
      {
        paramArrayOfSmsMessage = new String((byte[])localObject1, "utf-16");
        return paramArrayOfSmsMessage;
      }
      catch (Exception paramArrayOfSmsMessage)
      {
        Log.e("SmsReceiverService", "buildMessageString: new string utf-16 error");
        return null;
      }
    }
    return ((StringBuilder)localObject1).toString();
  }
  
  private void displayClassZeroMessage(Context paramContext, SmsMessage paramSmsMessage, String paramString, long paramLong)
  {
    paramSmsMessage = new Intent(paramContext, ClassZeroActivity.class).putExtra("pdu", paramSmsMessage.getPdu()).putExtra("format", paramString).setFlags(402653184);
    paramSmsMessage.putExtra("sim_id", paramLong);
    paramContext.startActivity(paramSmsMessage);
  }
  
  private ContentValues extractContentValues(SmsMessage paramSmsMessage)
  {
    ContentValues localContentValues = new ContentValues();
    String str = paramSmsMessage.getDisplayOriginatingAddress();
    localContentValues.put("address", str);
    GregorianCalendar localGregorianCalendar1 = new GregorianCalendar(2011, 8, 18);
    GregorianCalendar localGregorianCalendar2 = new GregorianCalendar();
    long l = System.currentTimeMillis();
    localGregorianCalendar2.setTimeInMillis(l);
    if (localGregorianCalendar2.before(localGregorianCalendar1)) {
      l = paramSmsMessage.getTimestampMillis();
    }
    localContentValues.put("date", new Long(l));
    localContentValues.put("date_sent", Long.valueOf(paramSmsMessage.getTimestampMillis()));
    localContentValues.put("protocol", Integer.valueOf(paramSmsMessage.getProtocolIdentifier()));
    localContentValues.put("read", Integer.valueOf(0));
    localContentValues.put("seen", Integer.valueOf(0));
    if (MessageUtils.isServiceNumber(this, str))
    {
      localContentValues.put("advanced_seen", Integer.valueOf(1));
      if (paramSmsMessage.getPseudoSubject().length() > 0) {
        localContentValues.put("subject", paramSmsMessage.getPseudoSubject());
      }
      if (!paramSmsMessage.isReplyPathPresent()) {
        break label233;
      }
    }
    label233:
    for (int i = 1;; i = 0)
    {
      localContentValues.put("reply_path_present", Integer.valueOf(i));
      localContentValues.put("service_center", paramSmsMessage.getServiceCenterAddress());
      return localContentValues;
      localContentValues.put("advanced_seen", Integer.valueOf(0));
      break;
    }
  }
  
  private String getSelectionBySlotId(boolean paramBoolean, int paramInt)
  {
    long l = MSimUtils.getSimIdBySlotId(paramInt);
    String str2;
    String str1;
    if (l < 0L) {
      if (!MSimUtils.isSimInserted(paramInt)) {
        if ((MSimUtils.isAndroid50()) && (MSimUtils.isMSim()))
        {
          str2 = "address is not null AND address!='' AND mx_status!=196609";
          if (paramInt == 0)
          {
            str1 = str2;
            if (MSimUtils.isSimInserted(1))
            {
              l = MSimUtils.getSimIdBySlotId(1);
              str1 = "address is not null AND address!='' AND mx_status!=196609" + " AND " + "sim_id" + "!=" + l;
            }
            Log.v("SmsReceiverService", "sim is not inserted, and not register");
          }
        }
      }
    }
    do
    {
      do
      {
        return str1;
        str1 = str2;
        if (paramInt != 1) {
          break;
        }
        str1 = str2;
        if (!MSimUtils.isSimInserted(0)) {
          break;
        }
        l = MSimUtils.getSimIdBySlotId(0);
        str1 = "address is not null AND address!='' AND mx_status!=196609" + " AND " + "sim_id" + "!=" + l;
        break;
        registerForServiceStateChanged(paramInt);
        if (paramBoolean) {
          showToastWhenOffline(paramInt);
        }
        Log.v("SmsReceiverService", "sim is not inserted, but register");
        return null;
        MSimUtils.sendQueuedMessage(this, paramInt);
        Log.v("SmsReceiverService", "sim info is not ready, retry");
        return null;
        str2 = "address is not null AND address!='' AND mx_status!=196609";
        str1 = str2;
      } while (!MSimUtils.isMSim());
      str1 = str2;
    } while (!MSimUtils.isMSimInserted());
    if (paramInt == 1) {
      return "address is not null AND address!='' AND mx_status!=196609" + " AND " + "sim_id" + "=" + l;
    }
    l = MSimUtils.getSimIdBySlotId(1);
    return "address is not null AND address!='' AND mx_status!=196609" + " AND " + "sim_id" + "!=" + l;
  }
  
  private void handleBootCompleted()
  {
    if (moveOutboxMessagesToFailedBox() > 0) {
      MessagingNotification.notifySendFailed(getApplicationContext(), true);
    }
    MSimUtils.sendQueuedMessageNoToast(this, MSimUtils.SLOT_ID_ALL);
    MessagingNotification.blockingUpdateNewMessageIndicator(this, true, false);
  }
  
  private void handleSendMessage(Intent paramIntent)
  {
    int i = MSimUtils.getSlotIdFromIntent(paramIntent);
    boolean bool = paramIntent.getBooleanExtra("show_toast_when_offline", true);
    if (MSimUtils.isMSimSlotIdValid(i))
    {
      if (mSending[i] == 0) {
        sendFirstQueuedMessage(bool, i);
      }
      return;
    }
    Log.v("SmsReceiverService", "handleSendMessage slot id is not valid");
  }
  
  private void handleServiceStateChanged(Intent paramIntent)
  {
    if (ServiceState.newFromBundle(paramIntent.getExtras()).getState() == 0)
    {
      Log.d("SmsReceiverService", "service is in service");
      unRegisterForServiceStateChanged();
      mToastHandler.removeCallbacks(mDelaySend);
      mToastHandler.postDelayed(mDelaySend, 10000L);
    }
  }
  
  private void handleSmsReceived(Intent paramIntent, int paramInt)
  {
    SmsMessage[] arrayOfSmsMessage = Telephony.Sms.Intents.getMessagesFromIntent(paramIntent);
    String str = paramIntent.getStringExtra("format");
    int i = paramIntent.getIntExtra("blockType", 0);
    int j = MSimUtils.getSlotIdFromIntent(paramIntent);
    long l = MSimUtils.getSimIdBySlotId(j);
    if (l < 0L) {
      Log.e("SmsReceiverService", "handleSmsReceived: cannot get simId for slot " + j);
    }
    do
    {
      do
      {
        return;
        paramIntent = insertMessage(this, arrayOfSmsMessage, paramInt, str, l, i);
        str = arrayOfSmsMessage[0].getOriginatingAddress();
      } while ((paramIntent == null) || (MessageUtils.hasBlockedFlag(paramIntent)));
      MessagingNotification.blockingUpdateNewMessageIndicator(this, true, false);
    } while (i == 1);
    MessageUtils.processReceivedMsgReport(this, str, buildMessageString(arrayOfSmsMessage), j);
  }
  
  private void handleSmsSent(Intent paramIntent, int paramInt)
  {
    Uri localUri = paramIntent.getData();
    boolean bool = paramIntent.getBooleanExtra("SendNextMsg", false);
    int i = MSimUtils.getSlotIdFromIntent(paramIntent);
    if (MSimUtils.isMSimSlotIdValid(i)) {
      mSending[i] = false;
    }
    Log.d("SmsReceiverService", "message sent, uri: " + localUri + ", result: " + mResultCode);
    if (mResultCode == -1)
    {
      if (Log.isLoggable("Mms:transaction", 2)) {
        Log.v("SmsReceiverService", "handleSmsSent move message to sent folder uri: " + localUri);
      }
      if (!moveMessageToFolderIfNotFailed(this, localUri, 2, paramInt)) {
        Log.e("SmsReceiverService", "handleSmsSent: failed to move message " + localUri + " to sent folder");
      }
      if ((bool) && (MSimUtils.isMSimSlotIdValid(i))) {
        sendFirstQueuedMessage(true, i);
      }
      MessagingNotification.updateSendFailedNotification(this);
    }
    do
    {
      do
      {
        do
        {
          return;
          if ((mResultCode != 2) && (mResultCode != 4)) {
            break;
          }
          Log.v("SmsReceiverService", "handleSmsSent: no service, queuing message w/ uri: " + localUri + " slotId is " + i);
          registerForServiceStateChanged(i);
          Telephony.Sms.moveMessageToFolder(this, localUri, 6, paramInt);
        } while (!paramIntent.getBooleanExtra("show_toast_when_offline", true));
        showToastWhenOffline(i);
        return;
        if (mResultCode == 6)
        {
          mToastHandler.post(new Runnable()
          {
            public void run()
            {
              Toast.makeText(SmsReceiverService.this, getString(2131362001), 0).show();
            }
          });
          return;
        }
        if ((mResultCode != 133404) || (paramInt != 0)) {
          break;
        }
      } while (!Log.isLoggable("Mms:transaction", 2));
      Log.v("SmsReceiverService", "HTC phone mResultCode == RESULT_ERROR_HTC_TEMP_FAILURE && error == 0");
      return;
      messageFailedToSend(localUri, paramInt, i);
    } while ((!bool) || (!MSimUtils.isMSimSlotIdValid(i)));
    sendFirstQueuedMessage(true, i);
  }
  
  private Uri insertMessage(Context paramContext, SmsMessage[] paramArrayOfSmsMessage, int paramInt1, String paramString, long paramLong, int paramInt2)
  {
    SmsMessage localSmsMessage = paramArrayOfSmsMessage[0];
    if (isDuplicate(localSmsMessage))
    {
      Log.w("SmsReceiverService", "duplicated sms ignored");
      return null;
    }
    if (localSmsMessage.getDisplayMessageBody() == null) {
      return null;
    }
    if (localSmsMessage.getMessageClass() == SmsMessage.MessageClass.CLASS_0)
    {
      displayClassZeroMessage(paramContext, localSmsMessage, paramString, paramLong);
      return null;
    }
    if (localSmsMessage.isReplace()) {
      return replaceMessage(paramContext, paramArrayOfSmsMessage, paramInt1, paramLong, paramInt2);
    }
    return storeMessage(paramContext, paramArrayOfSmsMessage, paramInt1, paramLong, paramInt2);
  }
  
  private boolean isDuplicate(SmsMessage paramSmsMessage)
  {
    if ((paramSmsMessage.getMessageClass() == SmsMessage.MessageClass.CLASS_0) || (paramSmsMessage.getMessageClass() == SmsMessage.MessageClass.CLASS_2)) {
      return false;
    }
    String str1 = paramSmsMessage.getOriginatingAddress();
    if (TextUtils.isEmpty(str1)) {
      return false;
    }
    long l = paramSmsMessage.getTimestampMillis();
    if (l <= 0L) {
      return false;
    }
    paramSmsMessage = paramSmsMessage.getMessageBody();
    if (TextUtils.isEmpty(paramSmsMessage)) {
      return false;
    }
    ContentResolver localContentResolver = getContentResolver();
    Uri localUri = Telephony.Sms.Inbox.CONTENT_URI;
    String str2 = "address=? AND date_sent=" + l + " AND " + "body" + "=?" + " AND " + "mx_status" + "=" + 0;
    paramSmsMessage = localContentResolver.query(localUri, new String[] { "_id", "address" }, str2, new String[] { str1, paramSmsMessage }, null);
    if (paramSmsMessage != null) {}
    try
    {
      if (paramSmsMessage.moveToFirst())
      {
        l = paramSmsMessage.getLong(0);
        paramSmsMessage.getString(1);
        Log.w("SmsReceiverService", "receive duplicated message from , msg id=" + l);
        return true;
      }
      return false;
    }
    finally
    {
      paramSmsMessage.close();
    }
  }
  
  private void messageFailedToSend(Uri paramUri, int paramInt1, int paramInt2)
  {
    Log.v("SmsReceiverService", "messageFailedToSend msg failed uri: " + paramUri + " error: " + paramInt1);
    Telephony.Sms.moveMessageToFolder(this, paramUri, 5, paramInt1);
    MessagingNotification.notifySendFailed(getApplicationContext(), true, paramInt2);
  }
  
  public static boolean moveMessageToFolderIfNotFailed(Context paramContext, Uri paramUri, int paramInt1, int paramInt2)
  {
    if (paramUri == null) {
      return false;
    }
    int k = 0;
    int m = 0;
    int i = m;
    int j = k;
    ContentValues localContentValues;
    switch (paramInt1)
    {
    default: 
      return false;
    case 1: 
    case 3: 
      localContentValues = new ContentValues(3);
      localContentValues.put("type", Integer.valueOf(paramInt1));
      if (j != 0)
      {
        localContentValues.put("read", Integer.valueOf(0));
        label100:
        localContentValues.put("error_code", Integer.valueOf(paramInt2));
        if (1 != SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, "type!=5", null)) {
          break label176;
        }
      }
      break;
    }
    label176:
    for (boolean bool = true;; bool = false)
    {
      return bool;
      i = 1;
      j = k;
      break;
      j = 1;
      i = m;
      break;
      if (i == 0) {
        break label100;
      }
      localContentValues.put("read", Integer.valueOf(1));
      break label100;
    }
  }
  
  private int moveOutboxMessagesToFailedBox()
  {
    ContentValues localContentValues = new ContentValues(3);
    localContentValues.put("type", Integer.valueOf(5));
    localContentValues.put("error_code", Integer.valueOf(1));
    localContentValues.put("read", Integer.valueOf(0));
    int i = SqliteWrapper.update(getApplicationContext(), getContentResolver(), Telephony.Sms.Outbox.CONTENT_URI, localContentValues, "type = 4", null);
    if (!Log.isLoggable("Mms:transaction", 2)) {
      return i;
    }
    Log.v("SmsReceiverService", "moveOutboxMessagesToFailedBox messageCount: " + i);
    return i;
  }
  
  private void registerForServiceStateChanged(final int paramInt)
  {
    if (MSimUtils.isMSim())
    {
      if (MSimUtils.isAndroid50()) {
        mToastHandler.post(new Runnable()
        {
          public void run()
          {
            MmsSystemEventReceiver.getInstance().registerForServiceStateChanged(paramInt);
          }
        });
      }
      for (;;)
      {
        Log.v("SmsReceiverService", "register for service state");
        return;
        MmsSystemEventReceiver.getInstance().registerForServiceStateChanged(paramInt);
      }
    }
    Context localContext = getApplicationContext();
    unRegisterForServiceStateChanged();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.SERVICE_STATE");
    Log.v("SmsReceiverService", "register for service state");
    localContext.registerReceiver(SmsReceiver.getInstance(), localIntentFilter);
  }
  
  public static String replaceFormFeeds(String paramString)
  {
    return paramString.replace('\f', '\n');
  }
  
  private Uri replaceMessage(Context paramContext, SmsMessage[] paramArrayOfSmsMessage, int paramInt1, long paramLong, int paramInt2)
  {
    Object localObject1 = paramArrayOfSmsMessage[0];
    ContentValues localContentValues = extractContentValues((SmsMessage)localObject1);
    localContentValues.put("error_code", Integer.valueOf(paramInt1));
    int j = paramArrayOfSmsMessage.length;
    if (j == 1) {
      localContentValues.put("body", replaceFormFeeds(((SmsMessage)localObject1).getDisplayMessageBody()));
    }
    for (;;)
    {
      Object localObject2 = paramContext.getContentResolver();
      String str1 = ((SmsMessage)localObject1).getOriginatingAddress();
      localObject1 = Integer.toString(((SmsMessage)localObject1).getProtocolIdentifier());
      String str2 = Long.toString(paramLong);
      localObject1 = SqliteWrapper.query(paramContext, (ContentResolver)localObject2, Telephony.Sms.Inbox.CONTENT_URI, REPLACE_PROJECTION, "address = ? AND protocol = ? AND sim_id = ?", new String[] { str1, localObject1, str2 }, null);
      if (localObject1 != null) {}
      try
      {
        if (((Cursor)localObject1).moveToFirst())
        {
          paramLong = ((Cursor)localObject1).getLong(0);
          paramArrayOfSmsMessage = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, paramLong);
          SqliteWrapper.update(paramContext, (ContentResolver)localObject2, paramArrayOfSmsMessage, localContentValues, null, null);
          return paramArrayOfSmsMessage;
          localObject2 = new StringBuilder();
          int i = 0;
          while (i < j)
          {
            localObject1 = paramArrayOfSmsMessage[i];
            if (mWrappedSmsMessage != null) {
              ((StringBuilder)localObject2).append(((SmsMessage)localObject1).getDisplayMessageBody());
            }
            i += 1;
          }
          localContentValues.put("body", replaceFormFeeds(((StringBuilder)localObject2).toString()));
          continue;
        }
        return storeMessage(paramContext, paramArrayOfSmsMessage, paramInt1, paramLong, paramInt2);
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
    }
  }
  
  /* Error */
  private void sendFirstQueuedMessage(boolean paramBoolean, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 667
    //   5: invokestatic 673	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   8: astore 16
    //   10: aload_0
    //   11: invokevirtual 545	com/android/mms/transaction/SmsReceiverService:getContentResolver	()Landroid/content/ContentResolver;
    //   14: astore 17
    //   16: aload_0
    //   17: iload_1
    //   18: iload_2
    //   19: invokespecial 675	com/android/mms/transaction/SmsReceiverService:getSelectionBySlotId	(ZI)Ljava/lang/String;
    //   22: astore 18
    //   24: aload 18
    //   26: ifnonnull +15 -> 41
    //   29: ldc -118
    //   31: ldc_w 677
    //   34: invokestatic 309	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   37: pop
    //   38: aload_0
    //   39: monitorexit
    //   40: return
    //   41: iload_2
    //   42: invokestatic 285	com/android/mms/util/MSimUtils:getSimIdBySlotId	(I)J
    //   45: lstore 10
    //   47: aload_0
    //   48: aload 17
    //   50: aload 16
    //   52: getstatic 54	com/android/mms/transaction/SmsReceiverService:SEND_PROJECTION	[Ljava/lang/String;
    //   55: aload 18
    //   57: aconst_null
    //   58: ldc_w 679
    //   61: invokestatic 656	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   64: astore 18
    //   66: aload 18
    //   68: ifnull -30 -> 38
    //   71: aload 18
    //   73: invokeinterface 566 1 0
    //   78: ifeq +622 -> 700
    //   81: aload 18
    //   83: iconst_3
    //   84: invokeinterface 573 2 0
    //   89: astore 22
    //   91: aload 18
    //   93: iconst_2
    //   94: invokeinterface 573 2 0
    //   99: astore 20
    //   101: aload 18
    //   103: iconst_1
    //   104: invokeinterface 683 2 0
    //   109: istore_3
    //   110: aload 18
    //   112: iconst_4
    //   113: invokeinterface 683 2 0
    //   118: istore 4
    //   120: aload 18
    //   122: iconst_0
    //   123: invokeinterface 683 2 0
    //   128: istore 5
    //   130: aload 18
    //   132: iconst_5
    //   133: invokeinterface 683 2 0
    //   138: istore 6
    //   140: aload 18
    //   142: bipush 6
    //   144: invokeinterface 569 2 0
    //   149: lstore 8
    //   151: aload 18
    //   153: bipush 7
    //   155: invokeinterface 569 2 0
    //   160: lstore 12
    //   162: aload 18
    //   164: bipush 8
    //   166: invokeinterface 573 2 0
    //   171: astore 21
    //   173: aload 21
    //   175: invokestatic 538	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   178: ifne +82 -> 260
    //   181: iconst_1
    //   182: istore 7
    //   184: getstatic 657	android/provider/Telephony$Sms:CONTENT_URI	Landroid/net/Uri;
    //   187: iload 5
    //   189: i2l
    //   190: invokestatic 663	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   193: astore 19
    //   195: lload 10
    //   197: lconst_0
    //   198: lcmp
    //   199: ifge +67 -> 266
    //   202: aload_0
    //   203: getfield 367	com/android/mms/transaction/SmsReceiverService:mSending	[Z
    //   206: iload_2
    //   207: iconst_0
    //   208: bastore
    //   209: aload_0
    //   210: aload 19
    //   212: iconst_1
    //   213: iload_2
    //   214: invokespecial 498	com/android/mms/transaction/SmsReceiverService:messageFailedToSend	(Landroid/net/Uri;II)V
    //   217: ldc -118
    //   219: new 102	java/lang/StringBuilder
    //   222: dup
    //   223: invokespecial 103	java/lang/StringBuilder:<init>	()V
    //   226: ldc_w 685
    //   229: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: iload_2
    //   233: invokevirtual 425	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   236: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: invokestatic 146	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   242: pop
    //   243: aload 18
    //   245: invokeinterface 578 1 0
    //   250: goto -212 -> 38
    //   253: astore 16
    //   255: aload_0
    //   256: monitorexit
    //   257: aload 16
    //   259: athrow
    //   260: iconst_0
    //   261: istore 7
    //   263: goto -79 -> 184
    //   266: iload 5
    //   268: i2l
    //   269: lstore 14
    //   271: aload_0
    //   272: lload 14
    //   274: lload 12
    //   276: lload 10
    //   278: invokespecial 689	com/android/mms/transaction/SmsReceiverService:updateSimId	(JJJ)V
    //   281: aconst_null
    //   282: astore 17
    //   284: aload 17
    //   286: astore 16
    //   288: iload 6
    //   290: iconst_1
    //   291: if_icmpne +39 -> 330
    //   294: aload 20
    //   296: invokestatic 695	com/android/mms/data/Contact:get	(Ljava/lang/String;)Lcom/android/mms/data/Contact;
    //   299: astore 16
    //   301: aload_0
    //   302: aload 16
    //   304: invokevirtual 698	com/android/mms/data/Contact:getMxPhoneNumber	()Ljava/lang/String;
    //   307: invokestatic 704	com/xiaomi/mms/data/MxIdCache:getOrQuery	(Landroid/content/Context;Ljava/lang/String;)Lcom/xiaomi/mms/data/MxIdCache$MxIdCacheItem;
    //   310: astore 23
    //   312: aload 16
    //   314: invokestatic 710	com/xiaomi/mms/utils/B2cMessageUtils:isB2cNumber	(Lcom/android/mms/data/Contact;)Z
    //   317: ifeq +167 -> 484
    //   320: aload 16
    //   322: invokevirtual 713	com/android/mms/data/Contact:getNumber	()Ljava/lang/String;
    //   325: invokestatic 718	miui/yellowpage/MiPubUtils:trimDomainSuffix	(Ljava/lang/String;)Ljava/lang/String;
    //   328: astore 16
    //   330: aload_0
    //   331: invokestatic 723	com/xiaomi/mms/transaction/PushSession:getInstance	(Landroid/content/Context;)Lcom/xiaomi/mms/transaction/PushSession;
    //   334: iload_2
    //   335: invokevirtual 726	com/xiaomi/mms/transaction/PushSession:getMyFullMid	(I)Ljava/lang/String;
    //   338: astore 17
    //   340: aload 16
    //   342: ifnull +252 -> 594
    //   345: aload 17
    //   347: ifnull +247 -> 594
    //   350: lload 8
    //   352: lconst_0
    //   353: lcmp
    //   354: ifle +202 -> 556
    //   357: aload 22
    //   359: lload 8
    //   361: invokestatic 732	com/xiaomi/mms/utils/MxMessageLogicHelper:constructSmsBody	(Ljava/lang/String;J)Ljava/lang/String;
    //   364: astore 22
    //   366: aload 22
    //   368: invokestatic 735	com/xiaomi/mms/utils/MxMessageLogicHelper:base64Encode	(Ljava/lang/String;)Ljava/lang/String;
    //   371: astore 23
    //   373: aload 17
    //   375: aload 16
    //   377: invokestatic 739	com/xiaomi/mms/utils/MxMessageLogicHelper:obtainMessage	(Ljava/lang/String;Ljava/lang/String;)Lmiui/push/Message;
    //   380: astore 16
    //   382: aload 16
    //   384: lload 8
    //   386: invokestatic 741	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   389: invokevirtual 746	miui/push/Message:setPacketID	(Ljava/lang/String;)V
    //   392: aload 23
    //   394: invokestatic 538	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   397: ifeq +172 -> 569
    //   400: aload 16
    //   402: aload 22
    //   404: invokevirtual 749	miui/push/Message:setBody	(Ljava/lang/String;)V
    //   407: iload 7
    //   409: ifeq +15 -> 424
    //   412: aload 16
    //   414: aload 21
    //   416: aload 20
    //   418: invokestatic 753	com/xiaomi/mms/utils/MxMessageLogicHelper:constructB2cNumbers	(Ljava/lang/String;Ljava/lang/String;)Lmiui/push/CommonPacketExtension;
    //   421: invokevirtual 757	miui/push/Message:addExtension	(Lmiui/push/CommonPacketExtension;)V
    //   424: aload_0
    //   425: aload 19
    //   427: iconst_4
    //   428: iconst_0
    //   429: invokestatic 207	java/lang/System:currentTimeMillis	()J
    //   432: invokestatic 235	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   435: aconst_null
    //   436: iconst_1
    //   437: lload 8
    //   439: invokestatic 235	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   442: invokestatic 760	com/android/mms/util/MSimUtils:moveMessageToFolder	(Landroid/content/Context;Landroid/net/Uri;IILjava/lang/Long;Ljava/lang/Integer;ILjava/lang/Long;)Z
    //   445: pop
    //   446: aload_0
    //   447: invokestatic 765	miui/push/ServiceClient:getInstance	(Landroid/content/Context;)Lmiui/push/ServiceClient;
    //   450: aload 16
    //   452: invokevirtual 769	miui/push/ServiceClient:sendMessage	(Lmiui/push/Message;)Z
    //   455: ifeq +127 -> 582
    //   458: aload_0
    //   459: invokestatic 774	com/xiaomi/mms/transaction/MxMessageTrackService:startTrack	(Landroid/content/Context;)V
    //   462: aload_0
    //   463: getfield 367	com/android/mms/transaction/SmsReceiverService:mSending	[Z
    //   466: iload_2
    //   467: iconst_0
    //   468: bastore
    //   469: aload_0
    //   470: iload_2
    //   471: invokestatic 322	com/android/mms/util/MSimUtils:sendQueuedMessage	(Landroid/content/Context;I)V
    //   474: aload 18
    //   476: invokeinterface 578 1 0
    //   481: goto -443 -> 38
    //   484: aload 23
    //   486: ifnull +21 -> 507
    //   489: aload 23
    //   491: invokevirtual 779	com/xiaomi/mms/data/MxIdCache$MxIdCacheItem:allowSms	()Z
    //   494: ifeq +13 -> 507
    //   497: aload 23
    //   499: invokevirtual 782	com/xiaomi/mms/data/MxIdCache$MxIdCacheItem:getMId	()Ljava/lang/String;
    //   502: astore 16
    //   504: goto -174 -> 330
    //   507: aload 23
    //   509: ifnonnull +31 -> 540
    //   512: ldc -118
    //   514: ldc_w 784
    //   517: invokestatic 309	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   520: pop
    //   521: aload 17
    //   523: astore 16
    //   525: goto -195 -> 330
    //   528: astore 16
    //   530: aload 18
    //   532: invokeinterface 578 1 0
    //   537: aload 16
    //   539: athrow
    //   540: ldc -118
    //   542: ldc_w 786
    //   545: invokestatic 309	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   548: pop
    //   549: aload 17
    //   551: astore 16
    //   553: goto -223 -> 330
    //   556: iload 7
    //   558: invokestatic 790	com/xiaomi/mms/utils/MxMessageLogicHelper:nextMiId	(Z)Ljava/lang/Long;
    //   561: invokevirtual 793	java/lang/Long:longValue	()J
    //   564: lstore 8
    //   566: goto -209 -> 357
    //   569: aload 16
    //   571: aload 23
    //   573: ldc_w 795
    //   576: invokevirtual 797	miui/push/Message:setBody	(Ljava/lang/String;Ljava/lang/String;)V
    //   579: goto -172 -> 407
    //   582: aload_0
    //   583: lload 8
    //   585: iload_2
    //   586: iload 7
    //   588: invokestatic 801	com/android/mms/ui/MessageUtils:handleMxSmsFailed	(Landroid/content/Context;JIZ)V
    //   591: goto -129 -> 462
    //   594: iload 6
    //   596: iconst_1
    //   597: if_icmpne +54 -> 651
    //   600: ldc -118
    //   602: new 102	java/lang/StringBuilder
    //   605: dup
    //   606: invokespecial 103	java/lang/StringBuilder:<init>	()V
    //   609: ldc_w 803
    //   612: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: aload 19
    //   617: invokevirtual 453	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   620: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   623: invokestatic 507	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   626: pop
    //   627: aload_0
    //   628: aload 19
    //   630: iload_2
    //   631: iload 7
    //   633: invokestatic 806	com/android/mms/ui/MessageUtils:handleMxSmsFailed	(Landroid/content/Context;Landroid/net/Uri;IZ)V
    //   636: aload_0
    //   637: iload_2
    //   638: invokestatic 322	com/android/mms/util/MSimUtils:sendQueuedMessage	(Landroid/content/Context;I)V
    //   641: aload 18
    //   643: invokeinterface 578 1 0
    //   648: goto -610 -> 38
    //   651: iload_3
    //   652: i2l
    //   653: lstore 8
    //   655: iload 4
    //   657: bipush 32
    //   659: if_icmpne +51 -> 710
    //   662: iconst_1
    //   663: istore 7
    //   665: new 808	com/android/mms/transaction/SmsSingleRecipientSender
    //   668: dup
    //   669: aload_0
    //   670: aload 20
    //   672: aload 22
    //   674: lload 8
    //   676: iload 7
    //   678: aload 19
    //   680: iload_2
    //   681: iload_1
    //   682: invokespecial 811	com/android/mms/transaction/SmsSingleRecipientSender:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;JZLandroid/net/Uri;IZ)V
    //   685: astore 16
    //   687: aload 16
    //   689: invokevirtual 815	com/android/mms/transaction/SmsMessageSender:sendMessage	()Z
    //   692: pop
    //   693: aload_0
    //   694: getfield 367	com/android/mms/transaction/SmsReceiverService:mSending	[Z
    //   697: iload_2
    //   698: iconst_1
    //   699: bastore
    //   700: aload 18
    //   702: invokeinterface 578 1 0
    //   707: goto -669 -> 38
    //   710: iconst_0
    //   711: istore 7
    //   713: goto -48 -> 665
    //   716: astore 16
    //   718: ldc -118
    //   720: new 102	java/lang/StringBuilder
    //   723: dup
    //   724: invokespecial 103	java/lang/StringBuilder:<init>	()V
    //   727: ldc_w 817
    //   730: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   733: aload 19
    //   735: invokevirtual 453	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   738: ldc_w 819
    //   741: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   744: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   747: aload 16
    //   749: invokestatic 822	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   752: pop
    //   753: aload_0
    //   754: getfield 367	com/android/mms/transaction/SmsReceiverService:mSending	[Z
    //   757: iload_2
    //   758: iconst_0
    //   759: bastore
    //   760: aload_0
    //   761: aload 19
    //   763: iconst_1
    //   764: iload_2
    //   765: invokespecial 498	com/android/mms/transaction/SmsReceiverService:messageFailedToSend	(Landroid/net/Uri;II)V
    //   768: goto -68 -> 700
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	771	0	this	SmsReceiverService
    //   0	771	1	paramBoolean	boolean
    //   0	771	2	paramInt	int
    //   109	543	3	i	int
    //   118	542	4	j	int
    //   128	139	5	k	int
    //   138	460	6	m	int
    //   182	530	7	bool	boolean
    //   149	526	8	l1	long
    //   45	232	10	l2	long
    //   160	115	12	l3	long
    //   269	4	14	l4	long
    //   8	43	16	localUri1	Uri
    //   253	5	16	localObject1	Object
    //   286	238	16	localObject2	Object
    //   528	10	16	localObject3	Object
    //   551	137	16	localObject4	Object
    //   716	32	16	localMmsException	com.google.android.mms.MmsException
    //   14	536	17	localObject5	Object
    //   22	679	18	localObject6	Object
    //   193	569	19	localUri2	Uri
    //   99	572	20	str1	String
    //   171	244	21	str2	String
    //   89	584	22	str3	String
    //   310	262	23	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   2	24	253	finally
    //   29	38	253	finally
    //   41	66	253	finally
    //   243	250	253	finally
    //   474	481	253	finally
    //   530	540	253	finally
    //   641	648	253	finally
    //   700	707	253	finally
    //   71	181	528	finally
    //   184	195	528	finally
    //   202	243	528	finally
    //   271	281	528	finally
    //   294	330	528	finally
    //   330	340	528	finally
    //   357	407	528	finally
    //   412	424	528	finally
    //   424	462	528	finally
    //   462	474	528	finally
    //   489	504	528	finally
    //   512	521	528	finally
    //   540	549	528	finally
    //   556	566	528	finally
    //   569	579	528	finally
    //   582	591	528	finally
    //   600	641	528	finally
    //   665	687	528	finally
    //   687	700	528	finally
    //   718	768	528	finally
    //   687	700	716	com/google/android/mms/MmsException
  }
  
  private void showToastWhenOffline(final int paramInt)
  {
    mToastHandler.post(new Runnable()
    {
      public void run()
      {
        SmsReceiverService localSmsReceiverService = SmsReceiverService.this;
        Object localObject2 = "";
        Object localObject1 = localObject2;
        if (MSimUtils.isMSimInserted())
        {
          if (paramInt != 0) {
            break label91;
          }
          localObject1 = localSmsReceiverService.getString(2131362235);
        }
        for (;;)
        {
          localObject2 = localObject1;
          if (!TextUtils.isEmpty((CharSequence)localObject1)) {
            localObject2 = (String)localObject1 + " ";
          }
          Toast.makeText(localSmsReceiverService, (String)localObject2 + localSmsReceiverService.getString(2131362000), 0).show();
          return;
          label91:
          localObject1 = localObject2;
          if (paramInt == 1) {
            localObject1 = localSmsReceiverService.getString(2131362236);
          }
        }
      }
    });
  }
  
  private Uri storeMessage(Context paramContext, SmsMessage[] paramArrayOfSmsMessage, int paramInt1, long paramLong, int paramInt2)
  {
    SmsMessage localSmsMessage = paramArrayOfSmsMessage[0];
    ContentValues localContentValues = extractContentValues(localSmsMessage);
    localContentValues.put("error_code", Integer.valueOf(paramInt1));
    String str;
    Long localLong;
    if (paramArrayOfSmsMessage.length == 1)
    {
      str = replaceFormFeeds(localSmsMessage.getDisplayMessageBody());
      localContentValues.put("body", str);
      localLong = localContentValues.getAsLong("thread_id");
      paramArrayOfSmsMessage = localContentValues.getAsString("address");
      if (TextUtils.isEmpty(paramArrayOfSmsMessage)) {
        break label153;
      }
      Contact localContact = Contact.get(paramArrayOfSmsMessage);
      if (localContact != null) {
        paramArrayOfSmsMessage = localContact.getNumber();
      }
    }
    for (;;)
    {
      localContentValues.put("sim_id", Long.valueOf(paramLong));
      if (MSimUtils.getSlotIdBySimInfoId(paramLong) >= 0) {
        break label172;
      }
      Log.e("SmsReceiverService", "storeMessage: cannot get slotId for simId " + paramLong);
      return null;
      str = replaceFormFeeds(buildMessageString(paramArrayOfSmsMessage));
      break;
      label153:
      paramArrayOfSmsMessage = getString(2131362003);
      localContentValues.put("address", paramArrayOfSmsMessage);
    }
    label172:
    if (((localLong == null) || (localLong.longValue() == 0L)) && (paramArrayOfSmsMessage != null)) {
      localContentValues.put("thread_id", Long.valueOf(Telephony.Threads.getOrCreateThreadId(paramContext, paramArrayOfSmsMessage)));
    }
    if ((paramInt2 < 3) && ((MiuiGalleryUtils.handleAsAlbumShareInvitation(paramContext, null, str, "mms")) || (MessageUtils.handleFileShareMessage(paramContext, localSmsMessage.getDisplayOriginatingAddress(), str))))
    {
      localContentValues.put("block_type", Integer.valueOf(0));
      localContentValues.put("read", Integer.valueOf(1));
      MessageUtils.insertUniqueMessage(paramContext, localContentValues);
      return null;
    }
    localContentValues.put("read", Integer.valueOf(0));
    localContentValues.put("block_type", Integer.valueOf(paramInt2));
    return MessageUtils.insertUniqueMessage(paramContext, localContentValues);
  }
  
  private static String translateResultCode(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
    default: 
      return "Unknown error code";
    case -1: 
      return "Activity.RESULT_OK";
    case 1: 
      return "SmsManager.RESULT_ERROR_GENERIC_FAILURE";
    case 2: 
      return "SmsManager.RESULT_ERROR_RADIO_OFF";
    case 3: 
      return "SmsManager.RESULT_ERROR_NULL_PDU";
    case 4: 
      return "SmsManager.RESULT_ERROR_NO_SERVICE";
    case 5: 
      return "SmsManager.RESULT_ERROR_LIMIT_EXCEEDED";
    }
    return "SmsManager.RESULT_ERROR_FDN_CHECK_FAILURE";
  }
  
  private void unRegisterForServiceStateChanged()
  {
    Log.v("SmsReceiverService", "un register for service state");
    try
    {
      getApplicationContext().unregisterReceiver(SmsReceiver.getInstance());
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      Log.e("SmsReceiverService", "allow un-matched register-unregister calls");
    }
  }
  
  private void updateSimId(long paramLong1, long paramLong2, long paramLong3)
  {
    if ((MSimUtils.isMSim()) && (paramLong2 != paramLong3))
    {
      ContentValues localContentValues = new ContentValues(1);
      localContentValues.put("sim_id", Long.valueOf(paramLong3));
      SqliteWrapper.update(this, getContentResolver(), Telephony.Sms.CONTENT_URI, localContentValues, "_id=" + paramLong1, null);
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    HandlerThread localHandlerThread = new HandlerThread("SmsReceiverService", 10);
    localHandlerThread.start();
    mServiceLooper = localHandlerThread.getLooper();
    mServiceHandler = new ServiceHandler(mServiceLooper);
    int j = MSimUtils.getMultiSimCount();
    mSending = new boolean[j];
    int i = 0;
    while (i < j)
    {
      mSending[i] = false;
      i += 1;
    }
  }
  
  public void onDestroy()
  {
    mServiceLooper.quit();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    paramInt1 = 0;
    if (paramIntent != null) {
      paramInt1 = paramIntent.getIntExtra("result", 0);
    }
    mResultCode = paramInt1;
    if (mResultCode != 0) {
      Log.v("SmsReceiverService", "onStart: #" + paramInt2 + " mResultCode: " + mResultCode + " = " + translateResultCode(mResultCode));
    }
    Message localMessage = mServiceHandler.obtainMessage();
    arg1 = paramInt2;
    obj = paramIntent;
    mServiceHandler.sendMessage(localMessage);
    startService(new Intent(this, SmsReportService.class));
    return 2;
  }
  
  private final class ServiceHandler
    extends Handler
  {
    public ServiceHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      int i = arg1;
      paramMessage = (Intent)obj;
      String str;
      int j;
      if (paramMessage != null)
      {
        str = paramMessage.getAction();
        j = paramMessage.getIntExtra("errorCode", 0);
        if (Log.isLoggable("Mms:transaction", 2)) {
          Log.v("SmsReceiverService", "handleMessage action: " + str + " error: " + j);
        }
        if (!"com.android.mms.transaction.MESSAGE_SENT".equals(paramMessage.getAction())) {
          break label105;
        }
        SmsReceiverService.this.handleSmsSent(paramMessage, j);
      }
      for (;;)
      {
        SmsReceiver.finishStartingService(SmsReceiverService.this, i);
        return;
        label105:
        if ("android.provider.Telephony.SMS_DELIVER".equals(str)) {
          SmsReceiverService.this.handleSmsReceived(paramMessage, j);
        } else if ("android.intent.action.BOOT_COMPLETED".equals(str)) {
          SmsReceiverService.this.handleBootCompleted();
        } else if ("android.intent.action.SERVICE_STATE".equals(str)) {
          SmsReceiverService.this.handleServiceStateChanged(paramMessage);
        } else if ("com.android.mms.transaction.SEND_MESSAGE".equals(str)) {
          SmsReceiverService.this.handleSendMessage(paramMessage);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReceiverService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */