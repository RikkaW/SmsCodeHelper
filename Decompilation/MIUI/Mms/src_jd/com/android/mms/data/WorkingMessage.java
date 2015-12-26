package com.android.mms.data;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.graphics.drawable.AnimatedRotateDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.provider.Telephony.Sms;
import android.provider.Telephony.Sms.Conversations;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;
import com.android.common.contacts.DataUsageStatUpdater;
import com.android.common.userhappiness.UserHappinessSignals;
import com.android.mms.ContentRestrictionException;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.MmsConfig;
import com.android.mms.ResolutionException;
import com.android.mms.UnsupportContentTypeException;
import com.android.mms.model.AudioModel;
import com.android.mms.model.FileAttachmentModel;
import com.android.mms.model.ImageModel;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.MediaModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.model.VCardModel;
import com.android.mms.model.VideoModel;
import com.android.mms.transaction.MessageSender;
import com.android.mms.transaction.SmsMessageSender;
import com.android.mms.ui.MessageEditableActivityBase;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SlideshowEditor;
import com.android.mms.util.DraftCache;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.SendReq;
import com.xiaomi.mms.utils.B2cMessageUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import miui.os.Build;

public class WorkingMessage
{
  private static final String[] MMS_DRAFT_PROJECTION = { "_id", "sub", "sub_cs", "date_full", "timed" };
  private static final String[] MMS_OUTBOX_PROJECTION;
  private static final String[] SMS_DRAFT_PROJECTION = { "body", "date", "timed" };
  private static final Executor sAsyncTaskExecutor;
  private static boolean sMmsEnabled = ;
  protected Activity mActivity;
  protected int mAttachmentType;
  protected ContentResolver mContentResolver = mContext.getContentResolver();
  protected Context mContext = MmsApp.getApp();
  protected Conversation mConversation;
  protected boolean mDiscarded = false;
  protected volatile boolean mHasMmsDraft;
  protected volatile boolean mHasSmsDraft;
  protected Uri mMessageUri;
  private int mMmsState;
  private boolean mSendByMx;
  private boolean mSendByMxV2;
  protected SlideshowModel mSlideshow;
  protected MessageStatusListener mStatusListener;
  protected CharSequence mSubject;
  protected CharSequence mText;
  private long mTimeToSend = 0L;
  private String mTimeToSendDesc;
  private List<String> mWorkingRecipients;
  
  static
  {
    MMS_OUTBOX_PROJECTION = new String[] { "_id", "m_size" };
    sAsyncTaskExecutor = Executors.newCachedThreadPool();
  }
  
  public WorkingMessage(MessageStatusListener paramMessageStatusListener)
  {
    mStatusListener = paramMessageStatusListener;
    if (mStatusListener != null) {
      mActivity = paramMessageStatusListener.getHostedActivity();
    }
    mAttachmentType = 0;
    mText = "";
  }
  
  private boolean addressContainsEmailToMms(Conversation paramConversation, String paramString)
  {
    if (MmsConfig.getEmailGateway() != null)
    {
      paramConversation = paramConversation.getRecipients().getNumbers();
      int j = paramConversation.length;
      int i = 0;
      while (i < j)
      {
        Contact localContact = Contact.get(paramConversation[i]);
        if (((Telephony.Mms.isEmailAddress(paramConversation[i])) && (!B2cMessageUtils.isB2cNumber(localContact))) || ((MessageUtils.isAlias(paramConversation[i])) && (android.telephony.SmsMessage.calculateLength(paramConversation[i] + " " + paramString, false)[0] > 1)))
        {
          updateState(1, true, true);
          ensureSlideshow();
          syncTextToSlideshow();
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  private void appendMedia(int paramInt, Uri paramUri)
    throws MmsException
  {
    if (paramInt == 0) {}
    int i;
    do
    {
      return;
      int j = 1;
      i = j;
      if (mSlideshow.size() == 1)
      {
        i = j;
        if (!mSlideshow.get(0).hasAudio())
        {
          i = j;
          if (!mSlideshow.get(0).hasVideo())
          {
            i = j;
            if (!mSlideshow.get(0).hasImage()) {
              i = 0;
            }
          }
        }
      }
    } while ((i != 0) && (!new SlideshowEditor(mContext, mSlideshow).addNewSlide()));
    SlideModel localSlideModel = mSlideshow.get(mSlideshow.size() - 1);
    if (paramInt == 1) {
      paramUri = new ImageModel(mContext, paramUri, mSlideshow.getLayout().getImageRegion());
    }
    for (;;)
    {
      localSlideModel.add(paramUri);
      if ((paramInt != 2) && (paramInt != 3)) {
        break;
      }
      localSlideModel.updateDuration(paramUri.getDuration());
      return;
      if (paramInt == 2)
      {
        paramUri = new VideoModel(mContext, paramUri, mSlideshow.getLayout().getImageRegion());
      }
      else
      {
        if (paramInt != 3) {
          break label225;
        }
        paramUri = new AudioModel(mContext, paramUri);
      }
    }
    label225:
    throw new IllegalArgumentException("changeMedia type=" + paramInt + ", uri=" + paramUri);
  }
  
  private void asyncDelete(final Uri paramUri, final String paramString, final String[] paramArrayOfString)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        SqliteWrapper.delete(mContext, mContentResolver, paramUri, paramString, paramArrayOfString);
      }
    }).start();
  }
  
  private void asyncDeleteDraftMmsMessage(Conversation paramConversation)
  {
    mHasMmsDraft = false;
    long l = paramConversation.getThreadId();
    if (l > 0L)
    {
      paramConversation = "thread_id = " + l;
      asyncDelete(Telephony.Mms.Draft.CONTENT_URI, paramConversation, null);
      mMessageUri = null;
    }
    while (mMessageUri == null) {
      return;
    }
    asyncDelete(mMessageUri, null, null);
    mMessageUri = null;
  }
  
  private void asyncUpdateDraftMmsMessage(final Conversation paramConversation, final boolean paramBoolean)
  {
    new Thread(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: invokestatic 36	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
        //   3: iconst_1
        //   4: invokevirtual 40	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
        //   7: aload_0
        //   8: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   11: getfield 44	com/android/mms/data/WorkingMessage:mContext	Landroid/content/Context;
        //   14: invokestatic 50	com/google/android/mms/pdu/MiuiPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/google/android/mms/pdu/MiuiPduPersister;
        //   17: astore_1
        //   18: aload_0
        //   19: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
        //   22: aload_0
        //   23: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   26: getfield 54	com/android/mms/data/WorkingMessage:mSubject	Ljava/lang/CharSequence;
        //   29: invokestatic 58	com/android/mms/data/WorkingMessage:access$100	(Lcom/android/mms/data/Conversation;Ljava/lang/CharSequence;)Lcom/google/android/mms/pdu/SendReq;
        //   32: astore_2
        //   33: aload_0
        //   34: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   37: getfield 62	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
        //   40: ifnonnull +87 -> 127
        //   43: aload_0
        //   44: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   47: aload_1
        //   48: aload_2
        //   49: aload_0
        //   50: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   53: getfield 66	com/android/mms/data/WorkingMessage:mSlideshow	Lcom/android/mms/model/SlideshowModel;
        //   56: invokestatic 70	com/android/mms/data/WorkingMessage:access$500	(Lcom/google/android/mms/pdu/MiuiPduPersister;Lcom/google/android/mms/pdu/SendReq;Lcom/android/mms/model/SlideshowModel;)Landroid/net/Uri;
        //   59: putfield 62	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
        //   62: aload_0
        //   63: getfield 25	com/android/mms/data/WorkingMessage$1:val$isStopping	Z
        //   66: ifeq +20 -> 86
        //   69: aload_0
        //   70: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
        //   73: invokevirtual 76	com/android/mms/data/Conversation:getMessageCount	()I
        //   76: ifne +10 -> 86
        //   79: aload_0
        //   80: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
        //   83: invokevirtual 79	com/android/mms/data/Conversation:clearThreadId	()V
        //   86: aload_0
        //   87: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   90: aload_0
        //   91: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
        //   94: invokevirtual 83	com/android/mms/data/Conversation:ensureThreadId	()J
        //   97: invokestatic 87	com/android/mms/data/WorkingMessage:access$800	(Lcom/android/mms/data/WorkingMessage;J)V
        //   100: aload_0
        //   101: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
        //   104: iconst_1
        //   105: invokevirtual 90	com/android/mms/data/Conversation:setDraftState	(Z)V
        //   108: aload_0
        //   109: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   112: aload_0
        //   113: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
        //   116: invokevirtual 94	com/android/mms/data/WorkingMessage:asyncDeleteDraftSmsMessage	(Lcom/android/mms/data/Conversation;)V
        //   119: invokestatic 36	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
        //   122: iconst_0
        //   123: invokevirtual 40	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
        //   126: return
        //   127: aload_0
        //   128: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   131: aload_0
        //   132: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   135: getfield 62	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
        //   138: aload_1
        //   139: aload_0
        //   140: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
        //   143: getfield 66	com/android/mms/data/WorkingMessage:mSlideshow	Lcom/android/mms/model/SlideshowModel;
        //   146: aload_2
        //   147: invokestatic 98	com/android/mms/data/WorkingMessage:access$600	(Lcom/android/mms/data/WorkingMessage;Landroid/net/Uri;Lcom/google/android/mms/pdu/MiuiPduPersister;Lcom/android/mms/model/SlideshowModel;Lcom/google/android/mms/pdu/SendReq;)V
        //   150: goto -88 -> 62
        //   153: astore_1
        //   154: invokestatic 36	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
        //   157: iconst_0
        //   158: invokevirtual 40	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
        //   161: aload_1
        //   162: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	163	0	this	1
        //   17	122	1	localMiuiPduPersister	MiuiPduPersister
        //   153	9	1	localObject	Object
        //   32	115	2	localSendReq	SendReq
        // Exception table:
        //   from	to	target	type
        //   0	62	153	finally
        //   62	86	153	finally
        //   86	119	153	finally
        //   127	150	153	finally
      }
    }).start();
  }
  
  private void asyncUpdateDraftSmsMessage(final Conversation paramConversation, final String paramString)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          DraftCache.getInstance().setSavingDraft(true);
          paramConversation.ensureThreadId();
          paramConversation.setDraftState(true);
          WorkingMessage.this.updateDraftSmsMessage(paramConversation, paramString);
          return;
        }
        finally
        {
          DraftCache.getInstance().setSavingDraft(false);
        }
      }
    }).start();
  }
  
  private void changeMedia(int paramInt, Uri paramUri)
    throws MmsException
  {
    mSlideshow.removeAllAttachFiles();
    SlideModel localSlideModel = mSlideshow.get(0);
    if (localSlideModel == null) {
      Log.w("Mms", "changeMedia: no slides!");
    }
    label59:
    label205:
    label218:
    label239:
    for (;;)
    {
      return;
      if (paramInt == 1)
      {
        paramUri = new ImageModel(mContext, paramUri, mSlideshow.getLayout().getImageRegion());
        checkMessageSize(localSlideModel, paramUri);
        if ((!Build.IS_CM_CUSTOMIZATION_TEST) || (paramInt == 2)) {
          break label218;
        }
        if (paramInt != 1) {
          break label205;
        }
        localSlideModel.removeImage();
      }
      for (;;)
      {
        if (paramInt == 0) {
          break label239;
        }
        localSlideModel.add(paramUri);
        if ((paramInt != 2) && (paramInt != 3)) {
          break;
        }
        localSlideModel.updateDuration(paramUri.getDuration());
        return;
        if (paramInt == 2)
        {
          paramUri = new VideoModel(mContext, paramUri, mSlideshow.getLayout().getImageRegion());
          break label59;
        }
        if (paramInt == 3)
        {
          paramUri = new AudioModel(mContext, paramUri);
          break label59;
        }
        throw new IllegalArgumentException("changeMedia type=" + paramInt + ", uri=" + paramUri);
        if (paramInt == 3)
        {
          localSlideModel.removeAudio();
          continue;
          localSlideModel.removeImage();
          localSlideModel.removeVideo();
          localSlideModel.removeAudio();
          mAttachmentType = 0;
        }
      }
    }
  }
  
  private void checkMessageSize(SlideModel paramSlideModel, MediaModel paramMediaModel)
  {
    Object localObject = null;
    int i;
    if (paramSlideModel.hasImage())
    {
      localObject = paramSlideModel.getImage();
      if (localObject != null)
      {
        if (!paramMediaModel.getMediaResizable()) {
          break label91;
        }
        i = 0;
        label30:
        if (!((MediaModel)localObject).getMediaResizable()) {
          break label99;
        }
      }
    }
    label91:
    label99:
    for (int j = 0;; j = ((MediaModel)localObject).getMediaSize())
    {
      if (i > j) {
        mSlideshow.checkMessageSize(i - j);
      }
      return;
      if (paramSlideModel.hasAudio())
      {
        localObject = paramSlideModel.getAudio();
        break;
      }
      if (!paramSlideModel.hasVideo()) {
        break;
      }
      localObject = paramSlideModel.getVideo();
      break;
      i = paramMediaModel.getMediaSize();
      break label30;
    }
  }
  
  private void correctAttachmentState(boolean paramBoolean)
  {
    int j = mSlideshow.sizeOfFilesAttach();
    int i = mSlideshow.size();
    if (i + j == 0) {
      removeAttachment(false);
    }
    for (;;)
    {
      updateState(4, hasAttachment(), paramBoolean);
      return;
      if ((j > 1) || (i > 1))
      {
        mAttachmentType = 5;
      }
      else
      {
        i = 0;
        SlideModel localSlideModel;
        if (j == 1)
        {
          if (((FileAttachmentModel)mSlideshow.getAttachFiles().get(0)).isVCard()) {
            i = 1;
          }
        }
        else
        {
          localSlideModel = mSlideshow.get(0);
          if (i == 0) {
            break label150;
          }
          if ((!localSlideModel.hasImage()) && (!localSlideModel.hasAudio()) && (!localSlideModel.hasVideo())) {
            break label142;
          }
          mAttachmentType = 5;
          continue;
        }
        throw new IllegalStateException("Unknown attachment file type");
        label142:
        mAttachmentType = 4;
        continue;
        label150:
        if ((localSlideModel.hasImage()) && (localSlideModel.hasAudio()))
        {
          if (Build.IS_CM_CUSTOMIZATION_TEST) {
            mAttachmentType = 1;
          } else {
            mAttachmentType = 5;
          }
        }
        else if (localSlideModel.hasImage()) {
          mAttachmentType = 1;
        } else if (localSlideModel.hasVideo()) {
          mAttachmentType = 2;
        } else if (localSlideModel.hasAudio()) {
          mAttachmentType = 3;
        }
      }
    }
  }
  
  private static Uri createDraftMmsMessage(MiuiPduPersister paramMiuiPduPersister, SendReq paramSendReq, SlideshowModel paramSlideshowModel)
  {
    try
    {
      PduBody localPduBody = paramSlideshowModel.toPduBody();
      paramSendReq.setBody(localPduBody);
      paramMiuiPduPersister = paramMiuiPduPersister.persist(paramSendReq, Telephony.Mms.Draft.CONTENT_URI, null, -1L);
      paramSlideshowModel.sync(localPduBody);
      return paramMiuiPduPersister;
    }
    catch (MmsException paramMiuiPduPersister) {}
    return null;
  }
  
  public static WorkingMessage createEmpty(MessageStatusListener paramMessageStatusListener)
  {
    return new WorkingMessage(paramMessageStatusListener);
  }
  
  private Dialog createProgressDialog(Context paramContext)
  {
    paramContext = new Dialog(paramContext, 2131689560);
    paramContext.setContentView(2130968629);
    paramContext.setCancelable(false);
    paramContext.getWindow().setLayout(-2, -2);
    paramContext.getWindow().setGravity(17);
    AnimatedRotateDrawable localAnimatedRotateDrawable = (AnimatedRotateDrawable)((ProgressBar)paramContext.findViewById(2131820671)).getIndeterminateDrawable();
    localAnimatedRotateDrawable.setFramesCount(60);
    localAnimatedRotateDrawable.setFramesDuration(20);
    return paramContext;
  }
  
  private void deleteDraftSmsMessage(long paramLong)
  {
    SqliteWrapper.delete(mContext, mContentResolver, ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, paramLong), "type=3", null);
  }
  
  private void ensureSlideshow()
  {
    if (mSlideshow != null) {
      return;
    }
    SlideshowModel localSlideshowModel = SlideshowModel.createNew(mContext);
    localSlideshowModel.add(new SlideModel(localSlideshowModel));
    mSlideshow = localSlideshowModel;
  }
  
  public static WorkingMessage load(MessageStatusListener paramMessageStatusListener, Uri paramUri, boolean paramBoolean)
  {
    Object localObject = paramUri;
    if (!paramUri.toString().startsWith(Telephony.Mms.Draft.CONTENT_URI.toString()))
    {
      localObject = paramUri;
      if (!paramBoolean)
      {
        localObject = MiuiPduPersister.getPduPersister(paramMessageStatusListener.getHostedActivity());
        if (Log.isLoggable("Mms:app", 2)) {
          LogTag.debug("load: moving %s to drafts", new Object[] { paramUri });
        }
      }
    }
    try
    {
      localObject = ((MiuiPduPersister)localObject).move(paramUri, Telephony.Mms.Draft.CONTENT_URI);
      paramMessageStatusListener = new WorkingMessage(paramMessageStatusListener);
      if (paramMessageStatusListener.loadFromUri((Uri)localObject, paramBoolean)) {
        return paramMessageStatusListener;
      }
    }
    catch (MmsException paramMessageStatusListener)
    {
      LogTag.error("Can't move %s to drafts", new Object[] { paramUri });
      return null;
    }
    return null;
  }
  
  public static WorkingMessage loadDraft(MessageStatusListener paramMessageStatusListener, Conversation paramConversation)
  {
    WorkingMessage localWorkingMessage = new WorkingMessage(paramMessageStatusListener);
    if (localWorkingMessage.loadFromConversation(paramConversation)) {
      return localWorkingMessage;
    }
    return createEmpty(paramMessageStatusListener);
  }
  
  private static SendReq makeSendReq(Conversation paramConversation, CharSequence paramCharSequence)
  {
    Object localObject = paramConversation.getRecipients().getNumbers(true);
    paramConversation = new SendReq();
    localObject = EncodedStringValue.encodeStrings((String[])localObject);
    if (localObject != null) {
      paramConversation.setTo((EncodedStringValue[])localObject);
    }
    if (!TextUtils.isEmpty(paramCharSequence)) {
      paramConversation.setSubject(new EncodedStringValue(paramCharSequence.toString()));
    }
    paramConversation.setDate(System.currentTimeMillis() / 1000L);
    return paramConversation;
  }
  
  private void markMmsMessageWithError(Uri paramUri)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("err_type", Integer.valueOf(10));
    long l = ContentUris.parseId(paramUri);
    SqliteWrapper.update(mContext, mContentResolver, Telephony.MmsSms.PendingMessages.CONTENT_URI, localContentValues, "msg_id=" + l, null);
  }
  
  private void prepareForSave(boolean paramBoolean)
  {
    syncWorkingRecipients();
    if (requiresMms())
    {
      ensureSlideshow();
      syncTextToSlideshow();
    }
  }
  
  private Uri readDraftMmsMessage(Context paramContext, Conversation paramConversation, StringBuilder paramStringBuilder)
  {
    String str = null;
    Object localObject = paramContext.getContentResolver();
    paramConversation = "thread_id = " + paramConversation.getThreadId();
    localObject = SqliteWrapper.query(paramContext, (ContentResolver)localObject, Telephony.Mms.Draft.CONTENT_URI, MMS_DRAFT_PROJECTION, paramConversation, null, null);
    paramConversation = str;
    if (localObject != null) {}
    try
    {
      if (((Cursor)localObject).moveToFirst())
      {
        paramConversation = ContentUris.withAppendedId(Telephony.Mms.Draft.CONTENT_URI, ((Cursor)localObject).getLong(0));
        str = MessageUtils.extractEncStrFromCursor((Cursor)localObject, 1, 2);
        if (((Cursor)localObject).getLong(4) > 0L) {}
        for (mTimeToSend = ((Cursor)localObject).getLong(3);; mTimeToSend = 0L)
        {
          mTimeToSendDesc = formatDateTime(paramContext, mTimeToSend);
          if (str != null) {
            paramStringBuilder.append(str);
          }
          if (Log.isLoggable("Mms:app", 2)) {
            LogTag.debug("readDraftMmsMessage uri: ", new Object[] { paramConversation });
          }
          return paramConversation;
        }
      }
    }
    finally
    {
      ((Cursor)localObject).close();
    }
    return null;
  }
  
  private void readDraftSmsMessage(Conversation paramConversation)
  {
    boolean bool = true;
    long l = paramConversation.getThreadId();
    if ((l <= 0L) || (!paramConversation.hasDraft()))
    {
      mText = "";
      return;
    }
    Object localObject = ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, l);
    mText = "";
    localObject = SqliteWrapper.query(mContext, mContentResolver, (Uri)localObject, SMS_DRAFT_PROJECTION, "type=3", null, null);
    if (localObject != null) {}
    for (;;)
    {
      try
      {
        if (!((Cursor)localObject).moveToFirst()) {
          break label228;
        }
        mText = ((Cursor)localObject).getString(0);
        if (((Cursor)localObject).getLong(2) > 0L)
        {
          mTimeToSend = ((Cursor)localObject).getLong(1);
          mTimeToSendDesc = formatDateTime(mContext, mTimeToSend);
          i = 1;
          ((Cursor)localObject).close();
          if ((i != 0) && (paramConversation.getMessageCount() == 0))
          {
            asyncDeleteDraftSmsMessage(paramConversation);
            clearConversation(paramConversation, true);
          }
          if (!Log.isLoggable("Mms:app", 2)) {
            break;
          }
          if (TextUtils.isEmpty(mText)) {
            break label223;
          }
          LogTag.debug("readDraftSmsMessage haveDraft: ", new Object[] { Boolean.valueOf(bool) });
          return;
        }
        mTimeToSend = 0L;
        continue;
        bool = false;
      }
      finally
      {
        ((Cursor)localObject).close();
      }
      label223:
      continue;
      label228:
      int i = 0;
      continue;
      i = 0;
    }
  }
  
  private void removeSubjectIfEmpty(boolean paramBoolean)
  {
    if (!hasSubject(true)) {
      setSubject(null, paramBoolean);
    }
  }
  
  private void setOrAppendFileAttachment(int paramInt, Uri paramUri, boolean paramBoolean)
    throws MmsException
  {
    if (paramInt == 4)
    {
      paramUri = new VCardModel(mContext.getApplicationContext(), paramUri);
      if (!paramBoolean) {
        break label73;
      }
      mSlideshow.addFileAttachment(paramUri);
    }
    for (;;)
    {
      return;
      throw new UnsupportContentTypeException("setOrAppendFileAttachment type=" + paramInt + ", uri=" + paramUri);
      label73:
      mSlideshow.removeAllAttachFiles();
      mSlideshow.addFileAttachment(paramUri);
      paramUri = mSlideshow.get(0);
      paramUri.removeImage();
      paramUri.removeVideo();
      paramUri.removeAudio();
      paramInt = mSlideshow.size() - 1;
      while (paramInt >= 1)
      {
        mSlideshow.remove(paramInt);
        paramInt -= 1;
      }
    }
  }
  
  private static String stateString(int paramInt)
  {
    if (paramInt == 0) {
      return "<none>";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramInt & 0x1) > 0) {
      localStringBuilder.append("RECIPIENTS_REQUIRE_MMS | ");
    }
    if ((paramInt & 0x2) > 0) {
      localStringBuilder.append("HAS_SUBJECT | ");
    }
    if ((paramInt & 0x4) > 0) {
      localStringBuilder.append("HAS_ATTACHMENT | ");
    }
    if ((paramInt & 0x8) > 0) {
      localStringBuilder.append("LENGTH_REQUIRES_MMS | ");
    }
    if ((paramInt & 0x10) > 0) {
      localStringBuilder.append("FORCE_MMS | ");
    }
    localStringBuilder.delete(localStringBuilder.length() - 3, localStringBuilder.length());
    return localStringBuilder.toString();
  }
  
  private void syncTextFromSlideshow()
  {
    if (mSlideshow.size() != 1) {}
    SlideModel localSlideModel;
    do
    {
      return;
      localSlideModel = mSlideshow.get(0);
    } while ((localSlideModel == null) || (!localSlideModel.hasText()));
    mText = localSlideModel.getText().getText();
  }
  
  private void syncTextToSlideshow()
  {
    if ((mSlideshow == null) || (mSlideshow.size() != 1)) {
      return;
    }
    SlideModel localSlideModel = mSlideshow.get(0);
    byte[] arrayOfByte = MessageUtils.charSequence2Byte(mText, "utf-8");
    localSlideModel.add(new TextModel(mContext, "text/plain", "text_0.txt", 106, arrayOfByte, mSlideshow.getLayout().getTextRegion()));
  }
  
  private void updateDraftMmsMessage(Uri paramUri, MiuiPduPersister paramMiuiPduPersister, SlideshowModel paramSlideshowModel, SendReq paramSendReq)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      LogTag.debug("updateDraftMmsMessage uri=%s", new Object[] { paramUri });
    }
    if (paramUri == null)
    {
      Log.e("WorkingMessage", "updateDraftMmsMessage null uri");
      return;
    }
    paramMiuiPduPersister.updateHeaders(paramUri, paramSendReq);
    paramSendReq = paramSlideshowModel.toPduBody();
    try
    {
      paramMiuiPduPersister.updateParts(paramUri, paramSendReq);
      MessageUtils.setMmsSendTime(mContext, paramUri, mTimeToSend, System.currentTimeMillis());
      paramSlideshowModel.sync(paramSendReq);
      return;
    }
    catch (MmsException paramMiuiPduPersister)
    {
      for (;;)
      {
        Log.e("WorkingMessage", "updateDraftMmsMessage: cannot update message " + paramUri);
      }
    }
  }
  
  private void updateDraftMmsMessageThreadId(long paramLong)
  {
    if ((mMessageUri != null) && (paramLong >= 0L))
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("thread_id", Long.valueOf(paramLong));
      SqliteWrapper.update(mContext, mContentResolver, mMessageUri, localContentValues, null, null);
    }
  }
  
  private void updateDraftSmsMessage(Conversation paramConversation, String paramString)
  {
    long l = paramConversation.getThreadId();
    if (l <= 0L) {
      return;
    }
    ContentValues localContentValues = new ContentValues(3);
    localContentValues.put("thread_id", Long.valueOf(l));
    localContentValues.put("body", paramString);
    localContentValues.put("type", Integer.valueOf(3));
    if (mTimeToSend > 0L)
    {
      localContentValues.put("timed", Long.valueOf(System.currentTimeMillis()));
      localContentValues.put("date", Long.valueOf(mTimeToSend));
    }
    for (;;)
    {
      SqliteWrapper.insert(mContext, mContentResolver, Telephony.Sms.CONTENT_URI, localContentValues);
      asyncDeleteDraftMmsMessage(paramConversation);
      return;
      localContentValues.put("timed", Integer.valueOf(0));
      localContentValues.put("date", Long.valueOf(System.currentTimeMillis()));
    }
  }
  
  public void asyncDeleteDraftSmsMessage(Conversation paramConversation)
  {
    mHasSmsDraft = false;
    long l = paramConversation.getThreadId();
    if (l > 0L) {
      asyncDelete(ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, l), "type=3", null);
    }
  }
  
  public boolean calcLengthRequiresMms()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!MmsConfig.getMultipartSmsEnabled()) {
      if (mConversation != null)
      {
        bool1 = bool2;
        if (mConversation.isB2c()) {}
      }
      else
      {
        bool1 = bool2;
        if (mAttachmentType == 0)
        {
          bool1 = bool2;
          if (hasText())
          {
            bool1 = bool2;
            if (android.telephony.SmsMessage.calculateLength(getText(), false)[0] >= MmsConfig.getSmsToMmsTextThreshold()) {
              bool1 = true;
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public void clearConversation(Conversation paramConversation, boolean paramBoolean)
  {
    if ((paramBoolean) && (paramConversation.getMessageCount() == 0)) {
      paramConversation.clearThreadId();
    }
    paramConversation.setDraftState(false);
  }
  
  /* Error */
  public void discard()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 888
    //   5: iconst_0
    //   6: anewarray 4	java/lang/Object
    //   9: invokestatic 587	com/android/mms/LogTag:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   12: aload_0
    //   13: getfield 105	com/android/mms/data/WorkingMessage:mDiscarded	Z
    //   16: istore_1
    //   17: iload_1
    //   18: iconst_1
    //   19: if_icmpne +6 -> 25
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: aload_0
    //   26: iconst_1
    //   27: putfield 105	com/android/mms/data/WorkingMessage:mDiscarded	Z
    //   30: aload_0
    //   31: getfield 356	com/android/mms/data/WorkingMessage:mHasMmsDraft	Z
    //   34: ifeq +20 -> 54
    //   37: aload_0
    //   38: aload_0
    //   39: getfield 866	com/android/mms/data/WorkingMessage:mConversation	Lcom/android/mms/data/Conversation;
    //   42: invokespecial 858	com/android/mms/data/WorkingMessage:asyncDeleteDraftMmsMessage	(Lcom/android/mms/data/Conversation;)V
    //   45: aload_0
    //   46: aload_0
    //   47: getfield 866	com/android/mms/data/WorkingMessage:mConversation	Lcom/android/mms/data/Conversation;
    //   50: iconst_1
    //   51: invokevirtual 727	com/android/mms/data/WorkingMessage:clearConversation	(Lcom/android/mms/data/Conversation;Z)V
    //   54: aload_0
    //   55: getfield 860	com/android/mms/data/WorkingMessage:mHasSmsDraft	Z
    //   58: ifeq -36 -> 22
    //   61: aload_0
    //   62: aload_0
    //   63: getfield 866	com/android/mms/data/WorkingMessage:mConversation	Lcom/android/mms/data/Conversation;
    //   66: invokevirtual 724	com/android/mms/data/WorkingMessage:asyncDeleteDraftSmsMessage	(Lcom/android/mms/data/Conversation;)V
    //   69: aload_0
    //   70: aload_0
    //   71: getfield 866	com/android/mms/data/WorkingMessage:mConversation	Lcom/android/mms/data/Conversation;
    //   74: iconst_1
    //   75: invokevirtual 727	com/android/mms/data/WorkingMessage:clearConversation	(Lcom/android/mms/data/Conversation;Z)V
    //   78: goto -56 -> 22
    //   81: astore_2
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_2
    //   85: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	this	WorkingMessage
    //   16	4	1	bool	boolean
    //   81	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	17	81	finally
    //   25	54	81	finally
    //   54	78	81	finally
  }
  
  public String formatDateTime(Context paramContext, long paramLong)
  {
    if (paramLong <= 0L) {
      return "";
    }
    if (DateFormat.is24HourFormat(paramContext)) {}
    for (int i = 128;; i = 64)
    {
      String str = DateUtils.formatDateTime(paramContext, paramLong, i | 0x15);
      paramContext = str;
      if (TextUtils.isEmpty(str)) {
        paramContext = "";
      }
      return paramContext;
    }
  }
  
  public Conversation getConversation()
  {
    return mConversation;
  }
  
  public int getCurrentMmsSize()
  {
    return MessageUtils.getCurrentMmsSize(mText, mSlideshow);
  }
  
  public SlideshowModel getSlideshow()
  {
    return mSlideshow;
  }
  
  public CharSequence getSubject()
  {
    return mSubject;
  }
  
  public CharSequence getText()
  {
    return mText;
  }
  
  public long getTimeToSend()
  {
    return mTimeToSend;
  }
  
  public String getTimeToSendDesc()
  {
    return mTimeToSendDesc;
  }
  
  public String getWorkingRecipients()
  {
    if (mWorkingRecipients == null) {
      return null;
    }
    return ContactList.getByNumbers(mWorkingRecipients).serialize();
  }
  
  public boolean hasAttachment()
  {
    return (mAttachmentType < 32) && (mAttachmentType > 0);
  }
  
  public boolean hasSlideshow()
  {
    return mAttachmentType == 5;
  }
  
  public boolean hasSubject(boolean paramBoolean)
  {
    boolean bool1 = false;
    if ((mMmsState & 0x2) != 0) {
      bool1 = true;
    }
    boolean bool2 = bool1;
    if (paramBoolean)
    {
      bool2 = bool1;
      if (bool1) {
        if (mSubject != null)
        {
          bool2 = bool1;
          if (TextUtils.getTrimmedLength(mSubject) > 0) {}
        }
        else
        {
          bool2 = false;
        }
      }
    }
    return bool2;
  }
  
  public boolean hasText()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (mText != null)
    {
      int j = mText.length();
      int i = 0;
      int k;
      while (i < j)
      {
        k = mText.charAt(i);
        if ((k >= 32) && (k != 65535)) {
          break;
        }
        i += 1;
      }
      while (j > i)
      {
        k = mText.charAt(j - 1);
        if ((k >= 32) && (k != 65535)) {
          break;
        }
        j -= 1;
      }
      bool1 = bool2;
      if (j > i) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isDiscarded()
  {
    return mDiscarded;
  }
  
  public boolean isFakeMmsForDraft()
  {
    return (mMmsState & 0x10) > 0;
  }
  
  public boolean isWorthSaving()
  {
    return (hasText()) || (hasSubject(true)) || (hasAttachment()) || (hasSlideshow()) || (isFakeMmsForDraft());
  }
  
  protected boolean loadFromConversation(Conversation paramConversation)
  {
    if (paramConversation.getThreadId() <= 0L) {}
    StringBuilder localStringBuilder;
    do
    {
      return false;
      readDraftSmsMessage(paramConversation);
      if (!TextUtils.isEmpty(mText))
      {
        mHasSmsDraft = true;
        return true;
      }
      localStringBuilder = new StringBuilder();
      paramConversation = readDraftMmsMessage(mContext, paramConversation, localStringBuilder);
    } while ((paramConversation == null) || (!loadFromUri(paramConversation, false)));
    if (localStringBuilder.length() > 0) {
      setSubject(localStringBuilder.toString(), false);
    }
    setLengthRequiresMms(calcLengthRequiresMms(), false);
    return true;
  }
  
  public boolean loadFromUri(Uri paramUri, boolean paramBoolean)
  {
    return loadFromUri(paramUri, paramBoolean, false);
  }
  
  public boolean loadFromUri(Uri paramUri, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      LogTag.debug("loadFromUri %s", new Object[] { paramUri });
    }
    for (;;)
    {
      try
      {
        mSlideshow = SlideshowModel.createFromMessageUri(mContext, paramUri);
        if (paramBoolean1)
        {
          mMessageUri = null;
          mSlideshow.onModelChanged(null, true);
          mHasMmsDraft = true;
          syncTextFromSlideshow();
          correctAttachmentState(paramBoolean2);
          return true;
        }
      }
      catch (MmsException localMmsException)
      {
        LogTag.error("Couldn't load URI %s", new Object[] { paramUri });
        return false;
      }
      mMessageUri = paramUri;
    }
  }
  
  public void removeAttachment(boolean paramBoolean)
  {
    mAttachmentType = 0;
    mSlideshow = null;
    if (mMessageUri != null)
    {
      asyncDelete(mMessageUri, null, null);
      mMessageUri = null;
    }
    updateState(4, false, paramBoolean);
    if ((paramBoolean) && (mStatusListener != null)) {
      mStatusListener.onAttachmentChanged();
    }
  }
  
  public void removeFakeMmsForDraft()
  {
    updateState(16, false, false);
  }
  
  public boolean requiresMms()
  {
    return mMmsState > 0;
  }
  
  /* Error */
  public Uri saveAsMms(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 105	com/android/mms/data/WorkingMessage:mDiscarded	Z
    //   4: ifeq +15 -> 19
    //   7: ldc_w 981
    //   10: iconst_0
    //   11: anewarray 4	java/lang/Object
    //   14: invokestatic 984	com/android/mms/LogTag:warn	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   17: aconst_null
    //   18: areturn
    //   19: aload_0
    //   20: bipush 16
    //   22: iconst_1
    //   23: iload_1
    //   24: invokevirtual 247	com/android/mms/data/WorkingMessage:updateState	(IZZ)V
    //   27: aload_0
    //   28: iconst_1
    //   29: invokespecial 986	com/android/mms/data/WorkingMessage:prepareForSave	(Z)V
    //   32: invokestatic 992	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   35: iconst_1
    //   36: invokevirtual 995	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
    //   39: aload_0
    //   40: getfield 866	com/android/mms/data/WorkingMessage:mConversation	Lcom/android/mms/data/Conversation;
    //   43: invokevirtual 998	com/android/mms/data/Conversation:ensureThreadId	()J
    //   46: pop2
    //   47: aload_0
    //   48: getfield 866	com/android/mms/data/WorkingMessage:mConversation	Lcom/android/mms/data/Conversation;
    //   51: iconst_1
    //   52: invokevirtual 885	com/android/mms/data/Conversation:setDraftState	(Z)V
    //   55: aload_0
    //   56: getfield 115	com/android/mms/data/WorkingMessage:mContext	Landroid/content/Context;
    //   59: invokestatic 573	com/google/android/mms/pdu/MiuiPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/google/android/mms/pdu/MiuiPduPersister;
    //   62: astore_2
    //   63: aload_0
    //   64: getfield 866	com/android/mms/data/WorkingMessage:mConversation	Lcom/android/mms/data/Conversation;
    //   67: aload_0
    //   68: getfield 910	com/android/mms/data/WorkingMessage:mSubject	Ljava/lang/CharSequence;
    //   71: invokestatic 148	com/android/mms/data/WorkingMessage:makeSendReq	(Lcom/android/mms/data/Conversation;Ljava/lang/CharSequence;)Lcom/google/android/mms/pdu/SendReq;
    //   74: astore_3
    //   75: aload_0
    //   76: getfield 374	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
    //   79: ifnonnull +33 -> 112
    //   82: aload_0
    //   83: aload_2
    //   84: aload_3
    //   85: aload_0
    //   86: getfield 259	com/android/mms/data/WorkingMessage:mSlideshow	Lcom/android/mms/model/SlideshowModel;
    //   89: invokestatic 163	com/android/mms/data/WorkingMessage:createDraftMmsMessage	(Lcom/google/android/mms/pdu/MiuiPduPersister;Lcom/google/android/mms/pdu/SendReq;Lcom/android/mms/model/SlideshowModel;)Landroid/net/Uri;
    //   92: putfield 374	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
    //   95: aload_0
    //   96: iconst_1
    //   97: putfield 356	com/android/mms/data/WorkingMessage:mHasMmsDraft	Z
    //   100: invokestatic 992	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   103: iconst_0
    //   104: invokevirtual 995	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
    //   107: aload_0
    //   108: getfield 374	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
    //   111: areturn
    //   112: aload_0
    //   113: aload_0
    //   114: getfield 374	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
    //   117: aload_2
    //   118: aload_0
    //   119: getfield 259	com/android/mms/data/WorkingMessage:mSlideshow	Lcom/android/mms/model/SlideshowModel;
    //   122: aload_3
    //   123: invokespecial 169	com/android/mms/data/WorkingMessage:updateDraftMmsMessage	(Landroid/net/Uri;Lcom/google/android/mms/pdu/MiuiPduPersister;Lcom/android/mms/model/SlideshowModel;Lcom/google/android/mms/pdu/SendReq;)V
    //   126: goto -31 -> 95
    //   129: astore_2
    //   130: invokestatic 992	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   133: iconst_0
    //   134: invokevirtual 995	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
    //   137: aload_2
    //   138: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	WorkingMessage
    //   0	139	1	paramBoolean	boolean
    //   62	56	2	localMiuiPduPersister	MiuiPduPersister
    //   129	9	2	localObject	Object
    //   74	49	3	localSendReq	SendReq
    // Exception table:
    //   from	to	target	type
    //   32	95	129	finally
    //   95	100	129	finally
    //   112	126	129	finally
  }
  
  public void saveDraft(boolean paramBoolean)
  {
    if (mDiscarded)
    {
      LogTag.warn("saveDraft mDiscarded: true mConversation: skipping saving draft and bailing", new Object[0]);
      return;
    }
    if (mConversation == null) {
      throw new IllegalStateException("saveDraft() called with no conversation");
    }
    prepareForSave(false);
    if (requiresMms())
    {
      asyncUpdateDraftMmsMessage(mConversation, paramBoolean);
      mHasMmsDraft = true;
      mConversation.setDraftState(true);
      return;
    }
    String str = mText.toString();
    if (!TextUtils.isEmpty(str))
    {
      asyncUpdateDraftSmsMessage(mConversation, str);
      mHasSmsDraft = true;
      mConversation.setDraftState(true);
      return;
    }
    asyncDeleteDraftMmsMessage(mConversation);
  }
  
  public void send(String paramString, int paramInt)
  {
    sendSmsAndMms(paramString, paramInt);
  }
  
  public void sendSmsAndMms(String paramString, int paramInt)
  {
    if (Log.isLoggable("Mms:transaction", 2)) {
      LogTag.debug("send", new Object[0]);
    }
    removeSubjectIfEmpty(true);
    prepareForSave(true);
    Conversation localConversation = mConversation;
    String str = mText.toString();
    if ((requiresMms()) || (addressContainsEmailToMms(localConversation, str))) {
      if (MmsConfig.getUaProfUrl() == null)
      {
        paramString = new ContentRestrictionException("WorkingMessage.send MMS sending failure. mms_config.xml is missing uaProfUrl setting.  uaProfUrl is required for MMS service, but can be absent for SMS.");
        Log.e("WorkingMessage", "WorkingMessage.send MMS sending failure. mms_config.xml is missing uaProfUrl setting.  uaProfUrl is required for MMS service, but can be absent for SMS.", paramString);
        throw paramString;
      }
    }
    for (paramString = new SendMessageTask(localConversation, mMessageUri, mSlideshow);; paramString = new SendMessageTask(localConversation, mText.toString(), paramString))
    {
      paramString.setSlotId(paramInt);
      paramString.setSendByMx(mSendByMx);
      paramString.setSendByMx2(mSendByMxV2);
      paramString.executeOnExecutor(sAsyncTaskExecutor, new Void[0]);
      RecipientIdCache.updateNumbers(localConversation.getThreadId(), localConversation.getRecipients());
      mDiscarded = true;
      return;
    }
  }
  
  public int setAttachment(int paramInt, Uri paramUri, boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      LogTag.debug("setAttachment type=%d uri %s", new Object[] { Integer.valueOf(paramInt), paramUri });
    }
    int i = 0;
    if ((paramInt == 0) && (mAttachmentType == 5) && (mSlideshow != null) && (paramUri == null) && (!paramBoolean)) {
      new SlideshowEditor(mContext, mSlideshow).removeAllSlides();
    }
    ensureSlideshow();
    if (paramInt == 4) {}
    try
    {
      setOrAppendFileAttachment(paramInt, paramUri, paramBoolean);
      while (i == 0)
      {
        mAttachmentType = paramInt;
        if (mStatusListener != null) {
          mStatusListener.onAttachmentChanged();
        }
        if ((paramBoolean) || (!calcLengthRequiresMms())) {
          break label238;
        }
        setLengthRequiresMms(true, false);
        correctAttachmentState(false);
        return i;
        if (paramBoolean) {
          appendMedia(paramInt, paramUri);
        } else {
          changeMedia(paramInt, paramUri);
        }
      }
    }
    catch (UnsupportContentTypeException paramUri)
    {
      for (;;)
      {
        i = -3;
      }
    }
    catch (ExceedMessageSizeException paramUri)
    {
      for (;;)
      {
        i = -2;
      }
    }
    catch (ResolutionException paramUri)
    {
      for (;;)
      {
        i = -4;
      }
    }
    catch (ContentRestrictionException paramUri)
    {
      for (;;)
      {
        i = -1;
        continue;
        if ((paramBoolean) && (paramInt != 4))
        {
          new SlideshowEditor(mContext, mSlideshow).removeSlide(mSlideshow.size() - 1);
          continue;
          updateState(4, hasAttachment(), true);
        }
      }
    }
    catch (MmsException paramUri)
    {
      for (;;)
      {
        label238:
        i = -1;
      }
    }
  }
  
  public void setConversation(Conversation paramConversation)
  {
    mConversation = paramConversation;
    setHasEmail(paramConversation.getRecipients().containsEmail(), false);
  }
  
  public void setHasEmail(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (MmsConfig.getEmailGateway() != null)
    {
      updateState(1, false, paramBoolean2);
      return;
    }
    updateState(1, paramBoolean1, paramBoolean2);
  }
  
  public void setLengthRequiresMms(boolean paramBoolean1, boolean paramBoolean2)
  {
    updateState(8, paramBoolean1, paramBoolean2);
  }
  
  public void setSendByMx(boolean paramBoolean)
  {
    mSendByMx = paramBoolean;
  }
  
  public void setSendByMxV2(boolean paramBoolean)
  {
    mSendByMxV2 = paramBoolean;
  }
  
  public void setSubject(CharSequence paramCharSequence, boolean paramBoolean)
  {
    mSubject = paramCharSequence;
    if (paramCharSequence != null) {}
    for (boolean bool = true;; bool = false)
    {
      updateState(2, bool, paramBoolean);
      return;
    }
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    mText = paramCharSequence;
    setLengthRequiresMms(calcLengthRequiresMms(), true);
  }
  
  public void setTimeToSend(long paramLong)
  {
    mTimeToSend = paramLong;
  }
  
  public void setTimeToSendDesc(String paramString)
  {
    mTimeToSendDesc = paramString;
  }
  
  public void setWorkingRecipients(List<String> paramList)
  {
    mWorkingRecipients = paramList;
    int i;
    if (paramList != null) {
      i = paramList.size();
    }
    switch (i)
    {
    default: 
      new StringBuilder().append("{...} len=").append(i).toString();
      return;
    case 1: 
      paramList = (String)paramList.get(0);
      return;
    }
  }
  
  public void syncWorkingRecipients()
  {
    if (mWorkingRecipients != null)
    {
      ContactList localContactList = ContactList.getByNumbers(mWorkingRecipients);
      if (!localContactList.equals(mConversation.getRecipients()))
      {
        if (mConversation.getThreadId() != 0L) {
          asyncDeleteDraftSmsMessage(mConversation);
        }
        mConversation.setRecipients(localContactList);
      }
      mWorkingRecipients = null;
    }
  }
  
  public void tryInsertExtraText(String paramString)
  {
    if (mSlideshow == null)
    {
      LogTag.error("error: slideshow is null, create an empty one", new Object[0]);
      ensureSlideshow();
    }
    int i = 0;
    for (;;)
    {
      if (i < mSlideshow.size())
      {
        SlideModel localSlideModel = mSlideshow.get(i);
        if ((localSlideModel != null) && (!localSlideModel.hasText()))
        {
          TextModel localTextModel = new TextModel(mActivity, "text/plain", "text_" + i + ".txt", mSlideshow.getLayout().getTextRegion());
          localTextModel.setText(paramString);
          localSlideModel.add(localTextModel);
        }
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  public void unDiscard()
  {
    mDiscarded = false;
  }
  
  protected void updateState(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!sMmsEnabled) {
      return;
    }
    int i = mMmsState;
    if (paramBoolean1)
    {
      mMmsState |= paramInt;
      label27:
      if ((mMmsState == 16) && ((i & 0xFFFFFFEF) > 0)) {
        mMmsState = 0;
      }
      if (paramBoolean2)
      {
        if ((i != 0) || (mMmsState == 0) || (mStatusListener == null)) {
          break label158;
        }
        mStatusListener.onProtocolChanged(true);
      }
      label82:
      if ((i == mMmsState) || (!Log.isLoggable("Mms:app", 2))) {
        break label188;
      }
      if (!paramBoolean1) {
        break label190;
      }
    }
    label158:
    label188:
    label190:
    for (String str = "+";; str = "-")
    {
      LogTag.debug("updateState: %s%s = %s", new Object[] { str, stateString(paramInt), stateString(mMmsState) });
      return;
      mMmsState &= (paramInt ^ 0xFFFFFFFF);
      break label27;
      if ((i == 0) || (mMmsState != 0) || (mStatusListener == null)) {
        break label82;
      }
      mStatusListener.onProtocolChanged(false);
      break label82;
      break;
    }
  }
  
  public static abstract interface MessageStatusListener
  {
    public abstract Activity getHostedActivity();
    
    public abstract void onAttachmentChanged();
    
    public abstract void onAttachmentError(int paramInt, Uri paramUri, boolean paramBoolean);
    
    public abstract void onMaxPendingMessagesReached();
    
    public abstract void onMessageSent();
    
    public abstract void onPreMessageSent();
    
    public abstract void onProtocolChanged(boolean paramBoolean);
  }
  
  private class SendMessageTask
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
    
    public SendMessageTask(Conversation paramConversation, Uri paramUri, SlideshowModel paramSlideshowModel)
    {
      mConv = paramConversation;
      mMmsUri = mMessageUri;
      mPersister = MiuiPduPersister.getPduPersister(mContext);
      mSlideshow = paramSlideshowModel;
      mNeedShow = true;
    }
    
    public SendMessageTask(Conversation paramConversation, String paramString1, String paramString2)
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
      UserHappinessSignals.userAcceptedImeText(mContext);
      if (mStatusListener != null) {
        mStatusListener.onPreMessageSent();
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
      for (paramConversation = "WorkingMessage.preSendSmsWorker threadId changed or recipients changed. origThreadId: " + l1 + " new threadId: " + l2 + " also mConversation.getThreadId(): " + mConversation.getThreadId();; paramConversation = "Recipients in window: ")
      {
        if (mActivity != null) {
          LogTag.warnPossibleRecipientMismatch(paramConversation, mActivity);
        }
        sendSmsWorker(paramString1, str, l2);
        WorkingMessage.this.deleteDraftSmsMessage(l2);
        return;
      }
    }
    
    /* Error */
    private void sendMmsWorker(Conversation paramConversation, Uri paramUri, MiuiPduPersister paramMiuiPduPersister, SlideshowModel paramSlideshowModel, SendReq paramSendReq)
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
      //   0	577	5	paramSendReq	SendReq
      //   339	100	6	i	int
      //   61	501	7	l1	long
      //   58	42	9	l2	long
      //   11	383	11	localObject	Object
      //   39	126	12	localCursor	Cursor
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
      paramString1 = new SmsMessageSender(mContext, paramString2, paramString1, paramLong, mTimeToSend, mSendByMx, mSlotId);
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
      DataUsageStatUpdater localDataUsageStatUpdater = new DataUsageStatUpdater(mContext);
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
        paramVarArgs = WorkingMessage.makeSendReq(mConv, mSubject);
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
      if ((mWasSent) && (mStatusListener != null)) {
        mStatusListener.onMessageSent();
      }
      if ((mStatusListener != null) && (mStatusListener.getHostedActivity().getWindow().isDestroyed()))
      {
        mNeedShow = false;
        mProgressDialog = null;
      }
      if ((mNeedShow) && (mProgressDialog != null) && (mProgressDialog.isShowing()))
      {
        mProgressDialog.dismiss();
        mProgressDialog = null;
      }
      if ((mActivity != null) && ((mActivity instanceof MessageEditableActivityBase))) {
        ((MessageEditableActivityBase)mActivity).setTaskDialog(null);
      }
    }
    
    protected void onPreExecute()
    {
      if (mNeedShow)
      {
        mProgressDialog = WorkingMessage.this.createProgressDialog(mActivity);
        if ((mActivity != null) && ((mActivity instanceof MessageEditableActivityBase))) {
          ((MessageEditableActivityBase)mActivity).setTaskDialog(mProgressDialog);
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
}

/* Location:
 * Qualified Name:     com.android.mms.data.WorkingMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */