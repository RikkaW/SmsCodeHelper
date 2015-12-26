package com.android.mms.data;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import com.android.common.contacts.DataUsageStatUpdater;
import com.android.common.userhappiness.UserHappinessSignals;
import com.android.mms.LogTag;
import com.android.mms.model.SlideshowModel;
import com.android.mms.transaction.MessageSender;
import com.android.mms.transaction.SmsMessageSender;
import com.android.mms.ui.MessageEditableActivityBase;
import com.google.android.mms.pdu.MiuiPduPersister;
import java.util.ArrayList;
import java.util.Arrays;

class WorkingMessage$SendMessageTask
  extends AsyncTask<Void, Void, Void>
{
  private Conversation mConv;
  private Uri mMmsUri;
  private String mMsgText;
  private boolean mNeedShow;
  private MiuiPduPersister mPersister;
  private Dialog mProgressDialog;
  private String mRecipientsInUI;
  private volatile boolean mSendByMx;
  private volatile boolean mSendByMxV2;
  private SlideshowModel mSlideshow;
  private int mSlotId;
  private boolean mWasSent;
  
  public WorkingMessage$SendMessageTask(WorkingMessage paramWorkingMessage, Conversation paramConversation, Uri paramUri, SlideshowModel paramSlideshowModel)
  {
    mConv = paramConversation;
    mMmsUri = mMessageUri;
    mPersister = MiuiPduPersister.getPduPersister(mContext);
    mSlideshow = paramSlideshowModel;
    mNeedShow = true;
  }
  
  public WorkingMessage$SendMessageTask(WorkingMessage paramWorkingMessage, Conversation paramConversation, String paramString1, String paramString2)
  {
    mConv = paramConversation;
    mMsgText = paramString1;
    mRecipientsInUI = paramString2;
    if (paramConversation.getRecipients().size() > 1) {}
    for (;;)
    {
      mNeedShow = bool;
      return;
      bool = false;
    }
  }
  
  private void preSendSmsWorker(Conversation paramConversation, String paramString1, String paramString2)
  {
    UserHappinessSignals.userAcceptedImeText(this$0.mContext);
    if (this$0.mStatusListener != null) {
      this$0.mStatusListener.onPreMessageSent();
    }
    long l1 = paramConversation.getThreadId();
    long l2 = paramConversation.ensureThreadId();
    String str = paramConversation.getRecipients().serialize();
    if (((l1 != 0L) && (l1 != l2)) || ((!str.equals(paramString2)) && (!TextUtils.isEmpty(paramString2)))) {
      if ((l1 == 0L) || (l1 == l2)) {
        break label188;
      }
    }
    label188:
    for (paramConversation = "WorkingMessage.preSendSmsWorker threadId changed or recipients changed. origThreadId: " + l1 + " new threadId: " + l2 + " also mConversation.getThreadId(): " + this$0.mConversation.getThreadId();; paramConversation = "Recipients in window: ")
    {
      if (this$0.mActivity != null) {
        LogTag.warnPossibleRecipientMismatch(paramConversation, this$0.mActivity);
      }
      sendSmsWorker(paramString1, str, l2);
      WorkingMessage.access$200(this$0, l2);
      return;
    }
  }
  
  /* Error */
  private void sendMmsWorker(Conversation paramConversation, Uri paramUri, MiuiPduPersister paramMiuiPduPersister, SlideshowModel paramSlideshowModel, com.google.android.mms.pdu.SendReq paramSendReq)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   4: getfield 48	com/android/mms/data/WorkingMessage:mContext	Landroid/content/Context;
    //   7: invokestatic 86	com/android/common/userhappiness/UserHappinessSignals:userAcceptedImeText	(Landroid/content/Context;)V
    //   10: aconst_null
    //   11: astore 11
    //   13: aload_0
    //   14: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   17: getfield 48	com/android/mms/data/WorkingMessage:mContext	Landroid/content/Context;
    //   20: aload_0
    //   21: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   24: getfield 172	com/android/mms/data/WorkingMessage:mContentResolver	Landroid/content/ContentResolver;
    //   27: getstatic 177	android/provider/Telephony$Mms$Outbox:CONTENT_URI	Landroid/net/Uri;
    //   30: invokestatic 181	com/android/mms/data/WorkingMessage:access$400	()[Ljava/lang/String;
    //   33: aconst_null
    //   34: aconst_null
    //   35: aconst_null
    //   36: invokestatic 187	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   39: astore 12
    //   41: aload 12
    //   43: ifnull +116 -> 159
    //   46: aload 12
    //   48: astore 11
    //   50: invokestatic 192	com/android/mms/MmsConfig:getMaxSizeScaleForPendingMmsAllowed	()I
    //   53: invokestatic 195	com/android/mms/MmsConfig:getMaxMessageSize	()I
    //   56: imul
    //   57: i2l
    //   58: lstore 9
    //   60: lconst_0
    //   61: lstore 7
    //   63: aload 12
    //   65: astore 11
    //   67: aload 12
    //   69: invokeinterface 201 1 0
    //   74: ifeq +23 -> 97
    //   77: aload 12
    //   79: astore 11
    //   81: lload 7
    //   83: aload 12
    //   85: iconst_1
    //   86: invokeinterface 205 2 0
    //   91: ladd
    //   92: lstore 7
    //   94: goto -31 -> 63
    //   97: lload 7
    //   99: lload 9
    //   101: lcmp
    //   102: iflt +57 -> 159
    //   105: aload 12
    //   107: astore 11
    //   109: aload_0
    //   110: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   113: invokevirtual 208	com/android/mms/data/WorkingMessage:unDiscard	()V
    //   116: aload 12
    //   118: astore 11
    //   120: aload_0
    //   121: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   124: getfield 90	com/android/mms/data/WorkingMessage:mStatusListener	Lcom/android/mms/data/WorkingMessage$MessageStatusListener;
    //   127: ifnull +19 -> 146
    //   130: aload 12
    //   132: astore 11
    //   134: aload_0
    //   135: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   138: getfield 90	com/android/mms/data/WorkingMessage:mStatusListener	Lcom/android/mms/data/WorkingMessage$MessageStatusListener;
    //   141: invokeinterface 211 1 0
    //   146: aload 12
    //   148: ifnull +10 -> 158
    //   151: aload 12
    //   153: invokeinterface 214 1 0
    //   158: return
    //   159: aload 12
    //   161: ifnull +10 -> 171
    //   164: aload 12
    //   166: invokeinterface 214 1 0
    //   171: aload_0
    //   172: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   175: getfield 90	com/android/mms/data/WorkingMessage:mStatusListener	Lcom/android/mms/data/WorkingMessage$MessageStatusListener;
    //   178: ifnull +15 -> 193
    //   181: aload_0
    //   182: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   185: getfield 90	com/android/mms/data/WorkingMessage:mStatusListener	Lcom/android/mms/data/WorkingMessage$MessageStatusListener;
    //   188: invokeinterface 95 1 0
    //   193: invokestatic 220	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   196: iconst_1
    //   197: invokevirtual 224	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
    //   200: aload_1
    //   201: invokevirtual 102	com/android/mms/data/Conversation:ensureThreadId	()J
    //   204: lstore 7
    //   206: ldc -30
    //   208: iconst_2
    //   209: invokestatic 232	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   212: ifeq +29 -> 241
    //   215: new 120	java/lang/StringBuilder
    //   218: dup
    //   219: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   222: ldc -22
    //   224: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: aload_2
    //   228: invokevirtual 237	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   231: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   234: iconst_0
    //   235: anewarray 239	java/lang/Object
    //   238: invokestatic 243	com/android/mms/LogTag:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   241: aload_1
    //   242: invokevirtual 72	com/android/mms/data/Conversation:getRecipients	()Lcom/android/mms/data/ContactList;
    //   245: iconst_1
    //   246: invokevirtual 247	com/android/mms/data/ContactList:getNumbers	(Z)[Ljava/lang/String;
    //   249: astore 11
    //   251: aload 11
    //   253: arraylength
    //   254: iconst_1
    //   255: if_icmpne +54 -> 309
    //   258: aload_0
    //   259: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   262: getfield 48	com/android/mms/data/WorkingMessage:mContext	Landroid/content/Context;
    //   265: aload_1
    //   266: invokevirtual 99	com/android/mms/data/Conversation:getThreadId	()J
    //   269: aload 11
    //   271: iconst_0
    //   272: aaload
    //   273: invokestatic 251	com/android/mms/data/Conversation:verifySingleRecipient	(Landroid/content/Context;JLjava/lang/String;)Ljava/lang/String;
    //   276: astore_1
    //   277: aload_1
    //   278: aload 11
    //   280: iconst_0
    //   281: aaload
    //   282: invokevirtual 112	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   285: ifne +24 -> 309
    //   288: aload 11
    //   290: iconst_0
    //   291: aload_1
    //   292: aastore
    //   293: aload 11
    //   295: invokestatic 257	com/google/android/mms/pdu/EncodedStringValue:encodeStrings	([Ljava/lang/String;)[Lcom/google/android/mms/pdu/EncodedStringValue;
    //   298: astore_1
    //   299: aload_1
    //   300: ifnull +9 -> 309
    //   303: aload 5
    //   305: aload_1
    //   306: invokevirtual 263	com/google/android/mms/pdu/SendReq:setTo	([Lcom/google/android/mms/pdu/EncodedStringValue;)V
    //   309: aload_2
    //   310: ifnonnull +92 -> 402
    //   313: aload_3
    //   314: aload 5
    //   316: aload 4
    //   318: invokestatic 267	com/android/mms/data/WorkingMessage:access$500	(Lcom/google/android/mms/pdu/MiuiPduPersister;Lcom/google/android/mms/pdu/SendReq;Lcom/android/mms/model/SlideshowModel;)Landroid/net/Uri;
    //   321: astore_2
    //   322: invokestatic 220	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   325: iconst_0
    //   326: invokevirtual 224	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
    //   329: aload_0
    //   330: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   333: lload 7
    //   335: invokestatic 158	com/android/mms/data/WorkingMessage:access$200	(Lcom/android/mms/data/WorkingMessage;J)V
    //   338: iconst_0
    //   339: istore 6
    //   341: aload 4
    //   343: aload_2
    //   344: invokevirtual 273	com/android/mms/model/SlideshowModel:finalResize	(Landroid/net/Uri;)V
    //   347: iload 6
    //   349: ifeq +94 -> 443
    //   352: aload_0
    //   353: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   356: aload_2
    //   357: invokestatic 277	com/android/mms/data/WorkingMessage:access$700	(Lcom/android/mms/data/WorkingMessage;Landroid/net/Uri;)V
    //   360: aload_0
    //   361: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   364: getfield 90	com/android/mms/data/WorkingMessage:mStatusListener	Lcom/android/mms/data/WorkingMessage$MessageStatusListener;
    //   367: ifnull -209 -> 158
    //   370: aload_0
    //   371: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   374: getfield 90	com/android/mms/data/WorkingMessage:mStatusListener	Lcom/android/mms/data/WorkingMessage$MessageStatusListener;
    //   377: iload 6
    //   379: aload_2
    //   380: iconst_1
    //   381: invokeinterface 281 4 0
    //   386: return
    //   387: astore_1
    //   388: aload 11
    //   390: ifnull +10 -> 400
    //   393: aload 11
    //   395: invokeinterface 214 1 0
    //   400: aload_1
    //   401: athrow
    //   402: aload_0
    //   403: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   406: aload_2
    //   407: aload_3
    //   408: aload 4
    //   410: aload 5
    //   412: invokestatic 285	com/android/mms/data/WorkingMessage:access$600	(Lcom/android/mms/data/WorkingMessage;Landroid/net/Uri;Lcom/google/android/mms/pdu/MiuiPduPersister;Lcom/android/mms/model/SlideshowModel;Lcom/google/android/mms/pdu/SendReq;)V
    //   415: goto -93 -> 322
    //   418: astore_1
    //   419: invokestatic 220	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   422: iconst_0
    //   423: invokevirtual 224	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
    //   426: aload_1
    //   427: athrow
    //   428: astore_1
    //   429: bipush -2
    //   431: istore 6
    //   433: goto -86 -> 347
    //   436: astore_1
    //   437: iconst_m1
    //   438: istore 6
    //   440: goto -93 -> 347
    //   443: ldc_w 287
    //   446: iconst_1
    //   447: anewarray 239	java/lang/Object
    //   450: dup
    //   451: iconst_0
    //   452: lload 7
    //   454: invokestatic 293	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   457: aastore
    //   458: invokestatic 243	com/android/mms/LogTag:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   461: new 295	com/android/mms/transaction/MmsMessageSender
    //   464: dup
    //   465: aload_0
    //   466: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   469: getfield 48	com/android/mms/data/WorkingMessage:mContext	Landroid/content/Context;
    //   472: aload_2
    //   473: aload 4
    //   475: invokevirtual 298	com/android/mms/model/SlideshowModel:getCurrentMessageSize	()I
    //   478: i2l
    //   479: aload_0
    //   480: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   483: invokestatic 302	com/android/mms/data/WorkingMessage:access$300	(Lcom/android/mms/data/WorkingMessage;)J
    //   486: aload_0
    //   487: getfield 304	com/android/mms/data/WorkingMessage$SendMessageTask:mSendByMx	Z
    //   490: aload_0
    //   491: getfield 306	com/android/mms/data/WorkingMessage$SendMessageTask:mSlotId	I
    //   494: invokespecial 309	com/android/mms/transaction/MmsMessageSender:<init>	(Landroid/content/Context;Landroid/net/Uri;JJZI)V
    //   497: astore_1
    //   498: aload_1
    //   499: invokeinterface 314 1 0
    //   504: ifne +24 -> 528
    //   507: aload_0
    //   508: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   511: getfield 48	com/android/mms/data/WorkingMessage:mContext	Landroid/content/Context;
    //   514: aload_0
    //   515: getfield 34	com/android/mms/data/WorkingMessage$SendMessageTask:this$0	Lcom/android/mms/data/WorkingMessage;
    //   518: getfield 172	com/android/mms/data/WorkingMessage:mContentResolver	Landroid/content/ContentResolver;
    //   521: aload_2
    //   522: aconst_null
    //   523: aconst_null
    //   524: invokestatic 318	android/database/sqlite/SqliteWrapper:delete	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   527: pop
    //   528: aload_0
    //   529: iconst_1
    //   530: putfield 320	com/android/mms/data/WorkingMessage$SendMessageTask:mWasSent	Z
    //   533: return
    //   534: astore_1
    //   535: ldc_w 322
    //   538: new 120	java/lang/StringBuilder
    //   541: dup
    //   542: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   545: ldc_w 324
    //   548: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   551: aload_2
    //   552: invokevirtual 237	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   555: ldc_w 326
    //   558: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   561: lload 7
    //   563: invokevirtual 130	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   566: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   569: aload_1
    //   570: invokestatic 330	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   573: pop
    //   574: goto -46 -> 528
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	577	0	this	SendMessageTask
    //   0	577	1	paramConversation	Conversation
    //   0	577	2	paramUri	Uri
    //   0	577	3	paramMiuiPduPersister	MiuiPduPersister
    //   0	577	4	paramSlideshowModel	SlideshowModel
    //   0	577	5	paramSendReq	com.google.android.mms.pdu.SendReq
    //   339	100	6	i	int
    //   61	501	7	l1	long
    //   58	42	9	l2	long
    //   11	383	11	localObject	Object
    //   39	126	12	localCursor	android.database.Cursor
    // Exception table:
    //   from	to	target	type
    //   13	41	387	finally
    //   50	60	387	finally
    //   67	77	387	finally
    //   81	94	387	finally
    //   109	116	387	finally
    //   120	130	387	finally
    //   134	146	387	finally
    //   193	241	418	finally
    //   241	288	418	finally
    //   293	299	418	finally
    //   303	309	418	finally
    //   313	322	418	finally
    //   402	415	418	finally
    //   341	347	428	com/android/mms/ExceedMessageSizeException
    //   341	347	436	com/google/android/mms/MmsException
    //   498	528	534	java/lang/Exception
  }
  
  private void sendSmsWorker(String paramString1, String paramString2, long paramLong)
  {
    paramString2 = TextUtils.split(paramString2, ";");
    Log.d("Mms:transaction", "sendSmsWorker sending message: threadId=" + paramLong);
    paramString1 = new SmsMessageSender(this$0.mContext, paramString2, paramString1, paramLong, WorkingMessage.access$300(this$0), mSendByMx, mSlotId);
    try
    {
      paramString1.sendMessage();
      mWasSent = true;
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        Log.e("WorkingMessage", "Failed to send SMS message, threadId=" + paramLong, paramString1);
      }
    }
  }
  
  private void updateSendStats(Conversation paramConversation)
  {
    paramConversation = new ArrayList(Arrays.asList(paramConversation.getRecipients().getNumbers()));
    DataUsageStatUpdater localDataUsageStatUpdater = new DataUsageStatUpdater(this$0.mContext);
    int m = paramConversation.size();
    if (m <= 50) {
      localDataUsageStatUpdater.updateWithPhoneNumber(paramConversation);
    }
    ArrayList localArrayList;
    int i;
    do
    {
      return;
      localArrayList = new ArrayList();
      i = 0;
      int j = 0;
      while (j < m)
      {
        int k = i + 1;
        localArrayList.add(paramConversation.get(j));
        i = k;
        if (k > 50)
        {
          localDataUsageStatUpdater.updateWithPhoneNumber(localArrayList);
          localArrayList.clear();
          i = 0;
        }
        j += 1;
      }
    } while (i <= 0);
    localDataUsageStatUpdater.updateWithPhoneNumber(localArrayList);
  }
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    if (mSlideshow != null)
    {
      mSlideshow.prepareForSend();
      paramVarArgs = WorkingMessage.access$100(mConv, this$0.mSubject);
      sendMmsWorker(mConv, mMmsUri, mPersister, mSlideshow, paramVarArgs);
    }
    for (;;)
    {
      updateSendStats(mConv);
      mConv.setDraftState(false);
      return null;
      preSendSmsWorker(mConv, mMsgText, mRecipientsInUI);
    }
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    if ((mWasSent) && (this$0.mStatusListener != null)) {
      this$0.mStatusListener.onMessageSent();
    }
    if ((this$0.mStatusListener != null) && (this$0.mStatusListener.getHostedActivity().getWindow().isDestroyed()))
    {
      mNeedShow = false;
      mProgressDialog = null;
    }
    if ((mNeedShow) && (mProgressDialog != null) && (mProgressDialog.isShowing()))
    {
      mProgressDialog.dismiss();
      mProgressDialog = null;
    }
    if ((this$0.mActivity != null) && ((this$0.mActivity instanceof MessageEditableActivityBase))) {
      ((MessageEditableActivityBase)this$0.mActivity).setTaskDialog(null);
    }
  }
  
  protected void onPreExecute()
  {
    if (mNeedShow)
    {
      mProgressDialog = WorkingMessage.access$000(this$0, this$0.mActivity);
      if ((this$0.mActivity != null) && ((this$0.mActivity instanceof MessageEditableActivityBase))) {
        ((MessageEditableActivityBase)this$0.mActivity).setTaskDialog(mProgressDialog);
      }
      mProgressDialog.show();
    }
  }
  
  public void setSendByMx(boolean paramBoolean)
  {
    mSendByMx = paramBoolean;
  }
  
  public void setSendByMx2(boolean paramBoolean)
  {
    mSendByMxV2 = paramBoolean;
  }
  
  public void setSlotId(int paramInt)
  {
    mSlotId = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.WorkingMessage.SendMessageTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */