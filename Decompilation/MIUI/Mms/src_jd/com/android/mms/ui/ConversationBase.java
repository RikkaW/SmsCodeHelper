package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;
import com.android.fileexplorer.service.IDirParse;
import com.android.fileexplorer.service.IQueryCallBack.Stub;
import com.android.mms.audio.DeleteCallback;
import com.android.mms.audio.Mx2DeleteHelper;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.android.mms.model.SlideshowModel;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.TimedMessageReceiver;
import com.android.mms.util.DirParseSDK;
import com.android.mms.util.EditableListView.EditModeListener;
import com.android.mms.util.EditableListView.EditableListViewCheckable;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.SimCardManager;
import com.google.android.collect.Lists;
import com.google.android.collect.Maps;
import com.google.android.mms.ContentType;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduPart;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxMessageUtils;
import com.xiaomi.mms.utils.SmsReportUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.R.plurals;
import miui.R.string;
import miui.app.Activity;
import miui.app.ProgressDialog;
import miui.os.Build;
import miui.view.EditActionMode;
import miui.widget.GuidePopupWindow;

public abstract class ConversationBase
  extends MessageEditableActivityBase
  implements MessageListPullView.OnMoreListener
{
  protected static ProgressDialog mBatchDeleteProgressDialog;
  private static String sSaveMmsPartToDiskPath = null;
  private boolean mBackPressed = false;
  protected BackgroundQueryHandler mBackgroundQueryHandler;
  protected int mBatchDeleteTaskCount;
  private View mBlackWindowLayer;
  private String mBodySubstitution;
  private IQueryCallBack.Stub mCallback = new IQueryCallBack.Stub()
  {
    public void onQueryFinish()
      throws RemoteException
    {}
    
    public boolean onQueryItem(String paramAnonymousString, int paramAnonymousInt)
      throws RemoteException
    {
      return false;
    }
    
    public void onQueryItemEnd(String paramAnonymousString1, String paramAnonymousString2)
      throws RemoteException
    {
      if (TextUtils.isEmpty(paramAnonymousString2)) {
        return;
      }
      ConversationBase.access$2202(paramAnonymousString2);
    }
    
    public void onStartQuery(int paramAnonymousInt)
      throws RemoteException
    {}
  };
  private ContentResolver mContentResolver;
  private Context mContext;
  private final MessageListAdapter.OnDataSetChangedListener mDataSetChangedListener = new MessageListAdapter.OnDataSetChangedListener()
  {
    public boolean needHoldCache()
    {
      if (mMsgListView != null) {
        return mMsgListView.isDataSetChanged();
      }
      return false;
    }
    
    public void onContentChanged(MessageListAdapter paramAnonymousMessageListAdapter)
    {
      if (mHandler != null)
      {
        mHandler.removeCallbacks(mDelayOnContentChanged);
        mHandler.postDelayed(mDelayOnContentChanged, 50L);
      }
    }
    
    public void onDataSetChanged(MessageListAdapter paramAnonymousMessageListAdapter)
    {
      ConversationBase.access$1102(ConversationBase.this, true);
    }
  };
  private Runnable mDelayOnContentChanged = new Runnable()
  {
    public void run()
    {
      mConversation.update();
      startMsgListQuery();
    }
  };
  private DeleteCallback mDeleteCallback = new DeleteCallback()
  {
    public void onDeleteComplete(int paramAnonymousInt1, Object paramAnonymousObject, int paramAnonymousInt2)
    {
      paramAnonymousObject = ConversationBase.this;
      mBatchDeleteTaskCount -= 1;
      if ((mBatchDeleteTaskCount <= 0) && (ConversationBase.mBatchDeleteProgressDialog != null))
      {
        ConversationBase.mBatchDeleteProgressDialog.dismiss();
        ConversationBase.mBatchDeleteProgressDialog = null;
      }
    }
  };
  private String mHighlightText;
  private int mHoldMsgListNum;
  private boolean mInAnimation = false;
  private long mInitTargetMessageId;
  private boolean mIsPullDown;
  private boolean mIsStopped = false;
  private String mListItemStyle;
  protected PesudoListView mMessageListAnimator;
  protected View mMessageListBottomForeground;
  private final Handler mMessageListItemHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (what)
      {
      default: 
        Log.w("ConversationBase", "Unknown message: " + what);
        return;
      case 3: 
      case 9: 
        ConversationBase.this.handleMessageReSendItem("mms", paramAnonymousMessage);
        return;
      case 1: 
        ConversationBase.this.handleMessageEditItem("mms", paramAnonymousMessage);
        return;
      case 4: 
      case 5: 
      case 8: 
        ConversationBase.this.handleMessageReSendItem("sms", paramAnonymousMessage);
        return;
      case 2: 
        ConversationBase.this.handleMessageEditItem("sms", paramAnonymousMessage);
        return;
      case 12: 
        ConversationBase.this.handleMessageMxGuide(paramAnonymousMessage);
        return;
      case 101: 
        ConversationBase.this.hideBlackWindowLayer();
        return;
      }
      hideSoftKeyboard();
      ConversationBase.this.showBlackWindowLayer();
    }
  };
  protected View mMessageListTopForeground;
  protected MessageListAdapter mMsgListAdapter;
  protected MessageListPullView mMsgListView;
  private int mMsgListViewTopOld;
  private GuidePopupWindow mMxGuideWindow;
  private boolean mNeedChangeSlotId = false;
  private boolean mNeedUpdateSendFailedNotify;
  private int mNewMessageCount;
  private long mOldTimeToSend;
  private Cursor mPendingChangeCursor = null;
  private boolean mPossiblePendingNotification;
  private Runnable mPostStartMsgListQuery = new Runnable()
  {
    public void run()
    {
      startMsgListQuery();
    }
  };
  private final Handler mPullToMoreHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (what == 0)
      {
        if (mHoldMsgListNum > 20)
        {
          ConversationBase.access$2902(ConversationBase.this, 21);
          ConversationBase.access$2820(ConversationBase.this, 20);
        }
        for (;;)
        {
          ConversationBase.access$3002(ConversationBase.this, true);
          startMsgListQuery();
          mMsgListView.doneMore();
          return;
          ConversationBase.access$2902(ConversationBase.this, mHoldMsgListNum + 1);
          ConversationBase.access$2802(ConversationBase.this, 0);
          mMsgListView.setNeedMoreData(false);
        }
      }
      super.handleMessage(paramAnonymousMessage);
    }
  };
  private TextView mSecurityAlertView;
  private boolean mSentMessage;
  private AlertDialog mSimPickerDialog;
  protected int mTitleBarTallBgHeight;
  protected boolean mWaitingResource = false;
  private boolean wasSoftKeyboardEnabled;
  
  private void batchLockMessages(final List<MessageItem> paramList, boolean paramBoolean)
  {
    final StringBuilder localStringBuilder1 = new StringBuilder();
    final StringBuilder localStringBuilder2 = new StringBuilder();
    long l = mConversation.getThreadId();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      MessageItem localMessageItem = (MessageItem)paramList.next();
      if (localMessageItem.isSms())
      {
        if (localMessageItem.getThreadId() == l)
        {
          if (localStringBuilder1.length() == 0) {
            localStringBuilder1.append("_id").append(" in (");
          }
          localStringBuilder1.append(localMessageItem.getMsgId()).append(",");
        }
      }
      else if ((localMessageItem.isMms()) && (localMessageItem.isDownloaded()) && (localMessageItem.getThreadId() == l))
      {
        if (localStringBuilder2.length() == 0) {
          localStringBuilder2.append("_id").append(" in (");
        }
        localStringBuilder2.append(localMessageItem.getMsgId()).append(",");
      }
    }
    if (localStringBuilder1.length() > 0) {
      localStringBuilder1.deleteCharAt(localStringBuilder1.length() - 1).append(")");
    }
    if (localStringBuilder2.length() > 0) {
      localStringBuilder2.deleteCharAt(localStringBuilder2.length() - 1).append(")");
    }
    paramList = new ContentValues(1);
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      paramList.put("locked", Integer.valueOf(i));
      new Thread(new Runnable()
      {
        public void run()
        {
          if (localStringBuilder1.length() > 0) {
            getContentResolver().update(Telephony.Sms.CONTENT_URI, paramList, localStringBuilder1.toString(), null);
          }
          if (localStringBuilder2.length() > 0) {
            getContentResolver().update(Telephony.Mms.CONTENT_URI, paramList, localStringBuilder2.toString(), null);
          }
        }
      }, "lockMessage").start();
      return;
    }
  }
  
  public static boolean cancelFailedDownloadNotification(Intent paramIntent, Context paramContext)
  {
    if (MessagingNotification.isFailedToDownload(paramIntent))
    {
      MessagingNotification.cancelNotification(paramContext, 531);
      return true;
    }
    return false;
  }
  
  public static boolean cancelFailedToDeliverNotification(Intent paramIntent, Context paramContext)
  {
    if (MessagingNotification.isFailedToDeliver(paramIntent))
    {
      MessagingNotification.cancelNotification(paramContext, 789);
      return true;
    }
    return false;
  }
  
  private void changeCursor(Cursor paramCursor)
  {
    int j = handleNewSelectionPosition(paramCursor);
    handleNeedChangeSlotId(this, paramCursor);
    int i;
    int k;
    if (mMsgListAdapter.getCursor() == null)
    {
      i = 1;
      k = paramCursor.getCount() - mMsgListAdapter.getCount();
      if (isReadOnly()) {
        break label290;
      }
      if (j == -1) {
        break label193;
      }
      mMsgListAdapter.changeCursor(paramCursor);
      mMsgListView.setSelection(mMsgListView.getHeaderViewsCount() + j);
    }
    for (;;)
    {
      handleShowSecurityAlert(paramCursor);
      if (mIsPullDown)
      {
        mMsgListView.setSelection(mMsgListViewTopOld);
        mIsPullDown = false;
      }
      if ((paramCursor.getCount() == 0) && (!mSentMessage) && (!isNewHmsConversation(paramCursor)))
      {
        mConversation.clearThreadId();
        finish();
      }
      dispatchCursorChanged(paramCursor);
      if (paramCursor.getCount() > 0) {
        mSentMessage = false;
      }
      if (mTextEditor != null) {
        mTextEditor.requestFocus();
      }
      if (mMessageListAnimator.getVisibility() == 0) {
        showMessageAnimation();
      }
      return;
      i = 0;
      break;
      label193:
      if ((k > 0) || (i != 0) || (mNewMessageCount > 0))
      {
        if (((k > 0) || (mNewMessageCount > 0)) && (i == 0) && (mAllowAnimation) && (mMessageListAnimator.getVisibility() != 0) && (!mIsPullDown)) {
          snapshotMsgList();
        }
        mMsgListAdapter.changeCursor(paramCursor);
        if (!mIsPullDown) {
          mMsgListView.setNeedToScrollEnd(true);
        }
      }
      else
      {
        mMsgListAdapter.changeCursor(paramCursor);
        continue;
        label290:
        mMsgListAdapter.changeCursor(paramCursor);
      }
    }
  }
  
  private void checkPendingNotification()
  {
    if ((mPossiblePendingNotification) && (hasWindowFocus()))
    {
      mConversation.setPreMarkUnread(true);
      new Thread(new Runnable()
      {
        public void run()
        {
          mConversation.markAsReadSync();
        }
      }).start();
      mPossiblePendingNotification = false;
    }
  }
  
  private void clearMessageListAnimator()
  {
    int i = 0;
    while (i < mMessageListAnimator.getChildCount())
    {
      View localView = mMessageListAnimator.getChildAt(i);
      if ((localView instanceof MessageListItem)) {
        ((MessageListItem)localView).unbind();
      }
      i += 1;
    }
    mMessageListAnimator.removeAllViews();
    mMessageListAnimator.clearDisappearingChildren();
  }
  
  private void confirmBatchDeleteDialog(final BatchDeleteListener paramBatchDeleteListener, int paramInt, boolean paramBoolean)
  {
    View localView = View.inflate(this, 2130968602, null);
    ((TextView)localView.findViewById(2131820614)).setText(getString(2131362130, new Object[] { Integer.valueOf(paramInt) }));
    final CheckBox localCheckBox = (CheckBox)localView.findViewById(2131820615);
    if ((!paramBoolean) || (isGroup())) {
      localCheckBox.setVisibility(8);
    }
    for (;;)
    {
      new AlertDialog.Builder(this).setTitle(2131361915).setIconAttribute(16843605).setCancelable(true).setPositiveButton(2131361930, paramBatchDeleteListener).setNegativeButton(2131361892, null).setView(localView).show();
      return;
      paramBatchDeleteListener.setDeleteLockedMessage(localCheckBox.isChecked());
      localCheckBox.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramBatchDeleteListener.setDeleteLockedMessage(localCheckBox.isChecked());
        }
      });
    }
  }
  
  private void confirmDeleteDialog(DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1) {}
    String str1;
    for (int i = 2131361916;; i = 2131361915)
    {
      String str2 = getString(i);
      if (!paramBoolean2) {
        break;
      }
      str2 = getString(2131361915);
      str1 = getString(2131361919);
      new AlertDialog.Builder(this).setTitle(str2).setIconAttribute(16843605).setCancelable(true).setMessage(str1).setPositiveButton(2131361930, paramOnClickListener).setNegativeButton(2131361892, null).show();
      return;
    }
    if (paramBoolean1) {}
    for (i = 2131361921;; i = 2131361920)
    {
      str1 = getString(i);
      break;
    }
  }
  
  private String copyMedia(long paramLong)
  {
    PduBody localPduBody = PduBodyCache.getPduBody(this, ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, paramLong));
    if (localPduBody == null) {}
    StringBuilder localStringBuilder;
    int i;
    Object localObject;
    do
    {
      return null;
      localStringBuilder = new StringBuilder();
      int j = localPduBody.getPartsNum();
      i = 0;
      if (i >= j) {
        break label206;
      }
      localObject = localPduBody.getPart(i);
      String str = new String(((PduPart)localObject).getContentType());
      if ((!ContentType.isImageType(str)) && (!ContentType.isVideoType(str)) && (!ContentType.isAudioType(str)) && (!str.equals("application/ogg"))) {
        break;
      }
      localObject = MessageUtils.saveMmsPartToDisk(this, (PduPart)localObject, Long.toHexString(paramLong));
    } while (localObject == null);
    localStringBuilder.append("\n");
    if (TextUtils.isEmpty(sSaveMmsPartToDiskPath)) {
      localStringBuilder.append(MessageUtils.SAVE_MMS_PART_TO_DISK_PATH + (String)localObject);
    }
    for (;;)
    {
      i += 1;
      break;
      localStringBuilder.append(sSaveMmsPartToDiskPath + "/" + (String)localObject);
    }
    label206:
    return localStringBuilder.toString();
  }
  
  private void doDirParseQuery()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(MessageUtils.SAVE_MMS_PART_TO_DISK_PATH);
    try
    {
      IDirParse localIDirParse = DirParseSDK.getInstance().getService();
      if ((!localArrayList.isEmpty()) && (localIDirParse != null)) {
        localIDirParse.queryDirInfo(localArrayList, mCallback);
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e("ConversationBase", "doDirParseQuery exception", localException);
    }
  }
  
  private void editMessageItem(MessageItem paramMessageItem)
  {
    if (paramMessageItem.isSms()) {
      editSmsMessageItem(paramMessageItem);
    }
    for (;;)
    {
      if ((paramMessageItem.isFailedMessage()) && (mMsgListAdapter.getCount() <= 1)) {}
      postUpdateSendButtonState();
      return;
      editMmsMessageItem(paramMessageItem);
    }
  }
  
  private void editMmsMessageItem(MessageItem paramMessageItem)
  {
    mWorkingMessage.discard();
    mWorkingMessage = WorkingMessage.load(this, paramMessageItem.getMessageUri(), false);
    mWorkingMessage.setConversation(mConversation);
    mAttachmentView.update(mWorkingMessage);
    mWorkingMessage.setSubject(paramMessageItem.getSubject(), false);
    drawTopPanel();
  }
  
  private void editSmsMessageItem(MessageItem paramMessageItem)
  {
    synchronized (mConversation)
    {
      if (mConversation.getMessageCount() <= 1) {
        mConversation.clearThreadId();
      }
      ??? = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, paramMessageItem.getMsgId());
      SqliteWrapper.delete(this, mContentResolver, (Uri)???, null, null);
      mWorkingMessage.setText(paramMessageItem.getBody());
      return;
    }
  }
  
  private View getBlackWindowLayer()
  {
    if (mBlackWindowLayer == null) {
      mBlackWindowLayer = ((ViewStub)findViewById(2131820561)).inflate().findViewById(2131820559);
    }
    return mBlackWindowLayer;
  }
  
  private MessageItem getMessageItem(String paramString, long paramLong, boolean paramBoolean)
  {
    MessageListAdapter localMessageListAdapter = mMsgListAdapter;
    if (paramBoolean) {}
    for (Cursor localCursor = mMsgListAdapter.getCursor();; localCursor = null) {
      return localMessageListAdapter.getCachedMessageItem(paramString, paramLong, localCursor, false);
    }
  }
  
  private void gotoSelectCopyText(List<MessageItem> paramList)
  {
    Intent localIntent = new Intent(this, MessageSelectCopyTextActivity.class);
    MessageItem localMessageItem = (MessageItem)paramList.get(0);
    localIntent.putExtra("extra_contact", localMessageItem.getContactName());
    if (localMessageItem.getContact() != null) {
      localIntent.putExtra("extra_number", localMessageItem.getContact().getNumber());
    }
    localIntent.putExtra("extra_body", MessageUtils.getMessagesText(paramList));
    startActivity(localIntent);
  }
  
  private void handleMessageEditItem(String paramString, Message paramMessage)
  {
    paramString = getMessageItem(paramString, ((Long)obj).longValue(), false);
    if (paramString != null)
    {
      editMessageItem(paramString);
      drawBottomPanel();
    }
  }
  
  private void handleMessageMxGuide(Message paramMessage)
  {
    Activity localActivity = (Activity)mContext;
    if ((mMxGuideWindow == null) && (localActivity.isResumed()))
    {
      mMxGuideWindow = new GuidePopupWindow(mContext);
      mMxGuideWindow.setGuideText(2131362342);
      mMxGuideWindow.setOutsideTouchable(true);
      mMxGuideWindow.setInputMethodMode(2);
      mMxGuideWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
      {
        public void onDismiss()
        {
          ConversationBase.access$1002(ConversationBase.this, null);
        }
      });
      mMxGuideWindow.show((View)obj, true);
    }
  }
  
  private void handleMessageReSendItem(String paramString, Message paramMessage)
  {
    boolean bool = true;
    paramString = getMessageItem(paramString, ((Long)obj).longValue(), false);
    if (paramString != null)
    {
      if (!paramString.isSms()) {
        break label255;
      }
      mSentMessage = true;
      if (what == 5)
      {
        paramString = paramString.resendMsgInGroup().iterator();
        while (paramString.hasNext())
        {
          Uri localUri = (Uri)paramString.next();
          if (MessageUtils.requireDeliveryStatusBySlotId(mContext, Integer.valueOf(arg1).intValue())) {
            MSimUtils.moveMessageToFolder(mContext, localUri, 6, 0, null, Integer.valueOf(32), 0);
          } else {
            MSimUtils.moveMessageToFolder(mContext, localUri, 6, 0, null, null, 0);
          }
        }
      }
      if (what != 4) {
        break label222;
      }
      if (!MessageUtils.requireDeliveryStatusBySlotId(mContext, Integer.valueOf(arg1).intValue())) {
        break label201;
      }
      MSimUtils.moveMessageToFolder(mContext, paramString.getMessageUri(), 6, 0, null, Integer.valueOf(32), 0);
    }
    for (;;)
    {
      MSimUtils.sendQueuedMessage(mContext, Integer.valueOf(arg1).intValue());
      return;
      label201:
      MSimUtils.moveMessageToFolder(mContext, paramString.getMessageUri(), 6, 0, null, null, 0);
      continue;
      label222:
      if (what == 8) {
        MSimUtils.moveMessageToFolder(mContext, paramString.getMessageUri(), 6, 0, Long.valueOf(0L), null, 1);
      }
    }
    label255:
    if (what == 9) {}
    while (paramString.getMx2Type() == 3) {
      if (isMx2AudioAvailable())
      {
        MessageUtils.resendMms(mContext, this, paramString.getMessageUri(), bool, paramString.getMx2Type());
        return;
        bool = false;
      }
      else
      {
        Toast.makeText(mContext, 2131362358, 0).show();
        return;
      }
    }
    MessageUtils.resendMms(mContext, this, paramString.getMessageUri(), bool, paramString.getMx2Type());
  }
  
  private void handleNeedChangeSlotId(final Context paramContext, Cursor paramCursor)
  {
    if ((MSimUtils.isMSimInserted()) && (mNeedChangeSlotId))
    {
      long l = System.currentTimeMillis();
      if (paramCursor.moveToLast())
      {
        int i = MSimUtils.getSlotIdBySimInfoId(paramCursor.getInt(36));
        if (MSimUtils.isMSimSlotIdValid(i)) {
          mUseSlotId = i;
        }
        mHandler.post(new Runnable()
        {
          public void run()
          {
            updateSlotButtonStateBySlotId(paramContext, mUseSlotId);
          }
        });
        postUpdateSendButtonState();
      }
      Log.v("ConversationBase", "handleNeedChangeSlotId cost time is " + (System.currentTimeMillis() - l));
    }
    mNeedChangeSlotId = false;
  }
  
  private int handleNewSelectionPosition(Cursor paramCursor)
  {
    int j = -1;
    long l1 = System.currentTimeMillis();
    int i = j;
    long l2;
    if (mInitTargetMessageId != 0L)
    {
      paramCursor.moveToPosition(-1);
      i = j;
      if (paramCursor.moveToNext())
      {
        String str = paramCursor.getString(0);
        l2 = paramCursor.getLong(1);
        if (((!"sms".equals(str)) || (l2 != mInitTargetMessageId)) && ((!"mms".equals(str)) || (l2 != -mInitTargetMessageId))) {
          break label139;
        }
      }
    }
    for (i = paramCursor.getPosition();; i = paramCursor.getPosition())
    {
      Log.v("ConversationBase", "handleNewSelectionPosition cost time is " + (System.currentTimeMillis() - l1));
      return i;
      label139:
      if (l2 != mInitTargetMessageId) {
        break;
      }
    }
  }
  
  private void handleSmsReport()
  {
    new AsyncTask()
    {
      String mMsg = null;
      String mNumber = "";
      int mSimIndex = 0;
      
      protected Boolean doInBackground(Void... paramAnonymousVarArgs)
      {
        if (TextUtils.isEmpty(mMsg))
        {
          mMsg = "null";
          mSimIndex = 0;
        }
        try
        {
          Object localObject2 = PushSession.getInstance(mContext);
          Object localObject1 = ((PushSession)localObject2).getMyFullMid(mSimIndex);
          paramAnonymousVarArgs = (Void[])localObject1;
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            paramAnonymousVarArgs = "0";
          }
          localObject1 = paramAnonymousVarArgs.substring(paramAnonymousVarArgs.lastIndexOf('/') + 1);
          paramAnonymousVarArgs = (Void[])localObject1;
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            paramAnonymousVarArgs = "";
          }
          localObject2 = ((PushSession)localObject2).getMyMid(mSimIndex);
          localObject1 = localObject2;
          if (TextUtils.isEmpty((CharSequence)localObject2)) {
            localObject1 = "0";
          }
          paramAnonymousVarArgs = new Boolean(SmsReportUtil.instance().postSms(mContext, (String)localObject1, "", paramAnonymousVarArgs, mNumber, mMsg));
          return paramAnonymousVarArgs;
        }
        catch (Exception paramAnonymousVarArgs)
        {
          Log.w("ConversationBase", "Failed to post sms." + paramAnonymousVarArgs);
        }
        return new Boolean(false);
      }
      
      protected void onPostExecute(Boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean.booleanValue())
        {
          Toast.makeText(mContext, 2131362386, 0).show();
          return;
        }
        Toast.makeText(mContext, 2131362385, 0).show();
      }
      
      protected void onPreExecute()
      {
        Object localObject = getRecipients();
        if ((localObject != null) && (((ContactList)localObject).size() > 0)) {
          mNumber = ((Contact)((ContactList)localObject).get(0)).getNumber();
        }
        mMsg = null;
        mSimIndex = 0;
        localObject = mMsgListAdapter.getCursor();
        String str;
        int i;
        if ((localObject != null) && (((Cursor)localObject).getCount() > 0) && (((Cursor)localObject).moveToLast()))
        {
          str = ((Cursor)localObject).getString(0);
          i = -1;
          if (!"sms".equals(str)) {
            break label145;
          }
          i = 4;
        }
        for (;;)
        {
          if ((i != -1) && (36 != -1))
          {
            mMsg = ((Cursor)localObject).getString(i);
            i = MSimUtils.getSlotIdBySimInfoId(((Cursor)localObject).getLong(36));
            if (MSimUtils.isMSimSlotIdValid(i)) {
              mSimIndex = i;
            }
          }
          return;
          label145:
          if ("mms".equals(str)) {
            i = 33;
          }
        }
      }
    }.execute(new Void[0]);
  }
  
  private boolean haveSomethingToCopyToSDCard(MessageItem paramMessageItem)
  {
    if (paramMessageItem.getMx2Type() > 0) {
      return true;
    }
    paramMessageItem = PduBodyCache.getPduBody(this, ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, paramMessageItem.getMsgId()));
    if (paramMessageItem == null) {
      return false;
    }
    int j = paramMessageItem.getPartsNum();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label149;
      }
      String str = new String(paramMessageItem.getPart(i).getContentType());
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("ConversationBase", "[CMA] haveSomethingToCopyToSDCard: part[" + i + "] contentType=" + str);
      }
      if ((ContentType.isImageType(str)) || (ContentType.isVideoType(str)) || (ContentType.isAudioType(str)) || (str.equals("application/ogg"))) {
        break;
      }
      i += 1;
    }
    label149:
    return false;
  }
  
  private void hideBlackWindowLayer()
  {
    if (getBlackWindowLayer().isShown() == true) {
      getBlackWindowLayer().setVisibility(8);
    }
  }
  
  private void initLayoutStyle()
  {
    mListItemStyle = MessageListItem.Style.bubble.toString();
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    boolean bool = MessageUtils.getConversationGroupByTime(localSharedPreferences, mListItemStyle);
    int i = MessageUtils.getDeliverReportMode(localSharedPreferences, MessageUtils.getPrefNotificationEnabled(this));
    mMsgListAdapter.initLayoutStyle(mListItemStyle, bool, i);
    mMsgListAdapter.setOnDataSetChangedListener(mDataSetChangedListener);
  }
  
  private void lockMessage(final MessageItem paramMessageItem, boolean paramBoolean)
  {
    final Object localObject;
    if (paramMessageItem.isSms())
    {
      localObject = Telephony.Sms.CONTENT_URI;
      paramMessageItem = ContentUris.withAppendedId((Uri)localObject, paramMessageItem.getMsgId());
      localObject = new ContentValues(1);
      if (!paramBoolean) {
        break label83;
      }
    }
    label83:
    for (int i = 1;; i = 0)
    {
      ((ContentValues)localObject).put("locked", Integer.valueOf(i));
      new Thread(new Runnable()
      {
        public void run()
        {
          getContentResolver().update(paramMessageItem, localObject, null, null);
        }
      }, "lockMessage").start();
      return;
      localObject = Telephony.Mms.CONTENT_URI;
      break;
    }
  }
  
  private void requestChangeCursor(Cursor paramCursor)
  {
    if (mPendingChangeCursor != null)
    {
      mPendingChangeCursor.close();
      mPendingChangeCursor = null;
    }
    if ((mInAnimation) || (mWaitingResource))
    {
      mPendingChangeCursor = paramCursor;
      return;
    }
    changeCursor(paramCursor);
  }
  
  private void setSecurityAlertView(View paramView)
  {
    mSecurityAlertView = ((TextView)paramView.findViewById(2131820765));
    paramView = getString(2131362382);
    SpannableString localSpannableString = new SpannableString(paramView);
    TextSizeAdjustSpan localTextSizeAdjustSpan = new TextSizeAdjustSpan(this, 2131689553);
    localTextSizeAdjustSpan.needUnderline();
    localSpannableString.setSpan(localTextSizeAdjustSpan, 0, paramView.length(), 33);
    mSecurityAlertView.append(localSpannableString);
    mSecurityAlertView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = String.format(mContext.getResources().getString(2131362384), new Object[] { ((Contact)getRecipients().get(0)).getNumber() });
        new AlertDialog.Builder(mContext).setIconAttribute(16843605).setCancelable(true).setTitle(2131362383).setMessage(paramAnonymousView).setPositiveButton(17039370, new DialogInterface.OnClickListener()
        {
          public void onClick(final DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            ConversationBase.this.handleSmsReport();
            paramAnonymous2DialogInterface = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            if (!paramAnonymous2DialogInterface.getBoolean("already_remind_filter_stranger_mx_message", false))
            {
              if (!paramAnonymous2DialogInterface.getBoolean("pref_key_mx_filter_message_from_stranger", false)) {
                new AlertDialog.Builder(mContext).setIconAttribute(16843605).setCancelable(true).setTitle(2131362411).setMessage(2131362412).setPositiveButton(17039370, new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                  {
                    paramAnonymous2DialogInterface.edit().putBoolean("pref_key_mx_filter_message_from_stranger", true).commit();
                  }
                }).setNegativeButton(2131361892, null).show();
              }
              paramAnonymous2DialogInterface.edit().putBoolean("already_remind_filter_stranger_mx_message", true).commit();
            }
          }
        }).setNegativeButton(2131361892, null).show();
      }
    });
  }
  
  private void setSecurityAlertVisibility(boolean paramBoolean)
  {
    TextView localTextView;
    if (mSecurityAlertView != null)
    {
      localTextView = mSecurityAlertView;
      if (!paramBoolean) {
        break label24;
      }
    }
    label24:
    for (int i = 0;; i = 8)
    {
      localTextView.setVisibility(i);
      return;
    }
  }
  
  private void setTimeToSend(long paramLong)
  {
    MessageUtils.setMessageSendTime(this, mConversation.getThreadId(), mOldTimeToSend, paramLong);
    TimedMessageReceiver.scheduleNextTimedMsg(this);
  }
  
  private void showBlackWindowLayer()
  {
    if (!getBlackWindowLayer().isShown()) {
      getBlackWindowLayer().setVisibility(0);
    }
  }
  
  private void showMessageAnimation()
  {
    if (mInAnimation) {
      return;
    }
    mInAnimation = true;
    int n = mMessageListAnimator.getWidth();
    int i = mMessageListAnimator.getHeight();
    mMsgListView.measure(View.MeasureSpec.makeMeasureSpec(n, 1073741824), View.MeasureSpec.makeMeasureSpec(i, 1073741824));
    mMsgListView.layout(mMessageListAnimator.getLeft(), mMessageListAnimator.getTop(), mMessageListAnimator.getRight(), mMessageListAnimator.getBottom());
    Object localObject2 = Maps.newHashMap();
    i = 0;
    long l;
    if (i < mMessageListAnimator.getChildCount())
    {
      localObject1 = mMessageListAnimator.getChildAt(i);
      if ((localObject1 instanceof MessageListItem))
      {
        localObject1 = ((MessageListItem)localObject1).getMessageItem();
        if (!((MessageItem)localObject1).isMms()) {
          break label171;
        }
      }
      label171:
      for (l = -((MessageItem)localObject1).getMsgId();; l = ((MessageItem)localObject1).getMsgId())
      {
        ((HashMap)localObject2).put(Long.valueOf(l), Integer.valueOf(i));
        i += 1;
        break;
      }
    }
    Object localObject1 = null;
    int j = mMsgListView.getFirstVisiblePosition();
    int k = mMsgListView.getChildAt(0).getTop();
    Object localObject5 = mMsgListView.getAdapter();
    Object localObject4 = Lists.newArrayList();
    Object localObject3 = Lists.newArrayList();
    ArrayList localArrayList = Lists.newArrayList();
    i = 1;
    Object localObject6;
    int m;
    Object localObject7;
    for (;;)
    {
      if ((j < ((ListAdapter)localObject5).getCount() - mMsgListView.getFooterViewsCount()) && (k < mMessageListAnimator.getHeight()))
      {
        ((ListAdapter)localObject5).getItemViewType(j);
        localObject6 = ((ListAdapter)localObject5).getView(j, null, mMsgListView);
        if (j < mMsgListView.getHeaderViewsCount())
        {
          m = ((View)localObject6).getHeight() + k;
          k = i;
          i = m;
          j += 1;
          m = i;
          i = k;
          k = m;
        }
        else
        {
          ((View)localObject6).measure(View.MeasureSpec.makeMeasureSpec(n, 1073741824), 0);
          if (!(localObject6 instanceof MessageListItem)) {
            break label1100;
          }
          localObject7 = ((MessageListItem)localObject6).getMessageItem();
          if (((MessageItem)localObject7).isMms())
          {
            l = -((MessageItem)localObject7).getMsgId();
            label372:
            localObject7 = (Integer)((HashMap)localObject2).get(Long.valueOf(l));
            if (localObject7 != null) {
              break label487;
            }
            localObject7 = new ViewGroup.MarginLayoutParams(n, -2);
            ((ViewGroup.MarginLayoutParams)localObject7).setMargins(0, k, 0, 0);
            mMessageListAnimator.addView((View)localObject6, (ViewGroup.LayoutParams)localObject7);
            if (i == 0) {
              break label468;
            }
            ((ArrayList)localObject4).add(localObject6);
          }
        }
      }
    }
    label468:
    label487:
    label627:
    label673:
    label763:
    label769:
    label775:
    label823:
    label901:
    label1100:
    for (;;)
    {
      int i1 = ((View)localObject6).getMeasuredHeight();
      m = i;
      i = i1 + k;
      k = m;
      break;
      l = ((MessageItem)localObject7).getMsgId();
      break label372;
      localArrayList.add(localObject6);
      ((ArrayList)localObject3).add(localObject6);
      continue;
      ((ArrayList)localObject3).clear();
      localObject7 = mMessageListAnimator.getChildAt(((Integer)localObject7).intValue());
      i = getLayoutParamstopMargin;
      if (k != i)
      {
        localObject1 = new TranslateAnimation(0.0F, 0.0F, 0.0F, k - i);
        ((Animation)localObject1).setDuration(300L);
        ((View)localObject7).startAnimation((Animation)localObject1);
      }
      for (;;)
      {
        ((HashMap)localObject2).remove(Long.valueOf(l));
        i = 0;
        break;
        if (i != 0)
        {
          if ((mNewMessageCount > 0) && (!((ArrayList)localObject4).isEmpty()))
          {
            localArrayList.addAll((Collection)localObject4);
            ((ArrayList)localObject3).add(((ArrayList)localObject4).get(((ArrayList)localObject4).size() - 1));
            ((ArrayList)localObject4).clear();
          }
        }
        else
        {
          mNewMessageCount = 0;
          i = 0;
          if (i >= ((ArrayList)localObject4).size()) {
            break label775;
          }
          localObject1 = new AnimationSet(false);
          localObject5 = new TranslateAnimation(0.0F, 0.0F, -300.0F, 0.0F);
          if (!mAllowAnimation) {
            break label763;
          }
          l = 300L;
          ((TranslateAnimation)localObject5).setDuration(l);
          localObject6 = new AlphaAnimation(0.0F, 1.0F);
          if (!mAllowAnimation) {
            break label769;
          }
        }
        for (l = 300L;; l = 0L)
        {
          ((AlphaAnimation)localObject6).setDuration(l);
          ((AnimationSet)localObject1).addAnimation((Animation)localObject5);
          ((AnimationSet)localObject1).addAnimation((Animation)localObject6);
          ((View)((ArrayList)localObject4).get(i)).startAnimation((Animation)localObject1);
          i += 1;
          break label627;
          localArrayList.clear();
          ((ArrayList)localObject4).clear();
          ((ArrayList)localObject3).clear();
          break;
          l = 0L;
          break label673;
        }
        i = 0;
        if (i < ((ArrayList)localObject3).size())
        {
          localObject1 = new AnimationSet(false);
          localObject4 = new TranslateAnimation(0.0F, 0.0F, 300.0F, 0.0F);
          if (mAllowAnimation)
          {
            l = 300L;
            ((TranslateAnimation)localObject4).setDuration(l);
            localObject5 = new AlphaAnimation(0.0F, 1.0F);
            if (!mAllowAnimation) {
              break label901;
            }
          }
          for (l = 300L;; l = 0L)
          {
            ((AlphaAnimation)localObject5).setDuration(l);
            ((AnimationSet)localObject1).addAnimation((Animation)localObject4);
            ((AnimationSet)localObject1).addAnimation((Animation)localObject5);
            ((View)((ArrayList)localObject3).get(i)).startAnimation((Animation)localObject1);
            i += 1;
            break;
            l = 0L;
            break label823;
          }
        }
        i = 0;
        if (i < localArrayList.size() - ((ArrayList)localObject3).size())
        {
          localObject1 = new AlphaAnimation(0.0F, 1.0F);
          if (mAllowAnimation) {}
          for (l = 300L;; l = 0L)
          {
            ((Animation)localObject1).setDuration(l);
            ((View)localArrayList.get(i)).startAnimation((Animation)localObject1);
            i += 1;
            break;
          }
        }
        localObject2 = ((HashMap)localObject2).values().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          i = ((Integer)((Iterator)localObject2).next()).intValue();
          localObject3 = mMessageListAnimator.getChildAt(i);
          localObject1 = new AlphaAnimation(1.0F, 0.0F);
          ((Animation)localObject1).setDuration(300L);
          ((View)localObject3).startAnimation((Animation)localObject1);
        }
        if (localObject1 != null)
        {
          ((Animation)localObject1).setAnimationListener(new Animation.AnimationListener()
          {
            public void onAnimationEnd(Animation paramAnonymousAnimation)
            {
              mMessageListAnimator.setVisibility(4);
              ConversationBase.this.clearMessageListAnimator();
              mMsgListView.setVisibility(0);
              ConversationBase.access$1802(ConversationBase.this, false);
              mHandler.post(new Runnable()
              {
                public void run()
                {
                  applyPendingCursor();
                }
              });
            }
            
            public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
            
            public void onAnimationStart(Animation paramAnonymousAnimation) {}
          });
          return;
        }
        mMessageListAnimator.setVisibility(4);
        mMsgListView.setVisibility(0);
        mInAnimation = false;
        return;
      }
    }
  }
  
  private void snapshotMsgList()
  {
    int k = mMessageListAnimator.getWidth();
    clearMessageListAnimator();
    if (mMsgListView.getChildCount() == 0) {
      return;
    }
    int j = mMsgListView.getFirstVisiblePosition();
    int i = mMsgListView.getChildAt(0).getTop();
    ListAdapter localListAdapter = mMsgListView.getAdapter();
    if ((j < localListAdapter.getCount() - mMsgListView.getFooterViewsCount()) && (i < mMessageListAnimator.getHeight() + getBottomPanelHeight()))
    {
      localListAdapter.getItemViewType(j);
      View localView = localListAdapter.getView(j, null, mMsgListView);
      if (j < mMsgListView.getHeaderViewsCount()) {
        i += localView.getHeight();
      }
      for (;;)
      {
        j += 1;
        break;
        ViewGroup.MarginLayoutParams localMarginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
        localMarginLayoutParams.setMargins(0, i, 0, 0);
        mMessageListAnimator.addView(localView, localMarginLayoutParams);
        localView.measure(View.MeasureSpec.makeMeasureSpec(k, 1073741824), 0);
        i += localView.getMeasuredHeight();
      }
    }
    mMessageListAnimator.setVisibility(0);
    mMsgListView.setVisibility(8);
  }
  
  private void updateSendFailedNotification()
  {
    final long l = mConversation.getThreadId();
    if (l <= 0L) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        MessagingNotification.updateSendFailedNotificationForThread(ConversationBase.this, l);
      }
    }, "updateSendFailedNotification").start();
  }
  
  protected void applyPendingCursor()
  {
    if ((!mInAnimation) && (!mWaitingResource) && (mPendingChangeCursor != null))
    {
      changeCursor(mPendingChangeCursor);
      mPendingChangeCursor = null;
    }
  }
  
  protected String buildMsgListQueryLimit()
  {
    String str = "";
    if (mInitTargetMessageId == 0L) {
      str = mHoldMsgListNum + ",-1";
    }
    return str;
  }
  
  protected void dispatchCursorChanged(Cursor paramCursor) {}
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((mBlackWindowLayer != null) && (mBlackWindowLayer.getVisibility() == 0)) {
      return true;
    }
    if (mInitTargetMessageId != 0L)
    {
      mInitTargetMessageId = 0L;
      mMsgListView.setAllowTranscriptOnResize(true);
    }
    boolean bool = SmsTextSizeAdjust.getInstance().dispatchTouchEvent(paramMotionEvent);
    if (!bool) {
      return super.dispatchTouchEvent(paramMotionEvent);
    }
    mMsgListView.setAllowTranscriptOnResize(false);
    return bool;
  }
  
  protected void enableNotShowAllMsgList()
  {
    if ((mConversation.getMessageCount() > 50) && (mInitTargetMessageId == 0L))
    {
      mHoldMsgListNum = (mConversation.getMessageCount() - 50);
      mMsgListView.setNeedMoreData(true);
    }
  }
  
  protected ContactList getRecipients()
  {
    if (mConversation != null) {
      return mConversation.getRecipients();
    }
    Log.v("ConversationBase", "getRecipients is null");
    return null;
  }
  
  protected void handleShowSecurityAlert(Cursor paramCursor)
  {
    boolean bool2 = false;
    if ((isGroup()) || (mConversation == null) || (mConversation.isPrivate())) {
      Log.v("ConversationBase", "group, hms and private is not show warning info");
    }
    Object localObject;
    do
    {
      return;
      localObject = getRecipients();
    } while ((mInitTargetMessageId != 0L) || (localObject == null) || (((ContactList)localObject).size() != 1) || (((Contact)((ContactList)localObject).get(0)).existsInDatabase()));
    long l = System.currentTimeMillis();
    int m = paramCursor.getCount();
    int i;
    label120:
    int k;
    label144:
    int j;
    if (m > 5)
    {
      m = 5;
      if ((m <= 0) || (!paramCursor.moveToLast())) {
        break label289;
      }
      i = 0;
      localObject = paramCursor.getString(0);
      if (!"sms".equals(localObject)) {
        break label232;
      }
      k = 13;
      j = i;
      if (k != -1)
      {
        if (paramCursor.getInt(k) == 65537) {
          break label250;
        }
        j = i;
      }
    }
    for (;;)
    {
      boolean bool1 = bool2;
      if (m != 0)
      {
        bool1 = bool2;
        if (j >= m) {
          bool1 = true;
        }
      }
      setSecurityAlertVisibility(bool1);
      Log.v("ConversationBase", "handleShowSecurityAlert cost time is " + (System.currentTimeMillis() - l));
      return;
      break;
      label232:
      if ("mms".equals(localObject))
      {
        k = 31;
        break label144;
        label250:
        k = i + 1;
        j = k;
        if (!paramCursor.moveToPrevious()) {
          continue;
        }
        i = k;
        if (k < m) {
          break label120;
        }
        j = k;
        continue;
      }
      k = -1;
      break label144;
      label289:
      j = 0;
    }
  }
  
  protected void initMessageList()
  {
    if (mMsgListAdapter != null) {
      return;
    }
    mMsgListAdapter = new MessageListAdapter(this, null, mMsgListView, true, isGroup(), isReadOnly(), mConversation.getThreadId(), mHighlightText, mBodySubstitution, mInitTargetMessageId, mConversation.isPrivate(), mConversation.isB2c());
    mMsgListAdapter.setMsgListItemHandler(mMessageListItemHandler);
    mHoldMsgListNum = 0;
    mMsgListView.setAdapter(mMsgListAdapter);
    mMsgListView.setItemsCanFocus(false);
    mMsgListView.setVisibility(0);
    mMsgListView.setOnMoreListener(this);
  }
  
  protected void initResourceRefs()
  {
    super.initResourceRefs();
    mMsgListView = ((MessageListPullView)findViewById(2131820761));
    mMessageListAnimator = ((PesudoListView)findViewById(2131820762));
    View localView = getLayoutInflater().inflate(2130968658, null);
    mMsgListView.addFooterView(localView);
    setSecurityAlertView(localView);
    setListViewEditModeListener();
    mMsgListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        onMsgListViewScrollStateChanged(paramAnonymousAbsListView, paramAnonymousInt);
      }
    });
    if (mTextEditor != null) {
      mTextEditor.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          mMsgListView.setAllowTranscriptOnResize(true);
        }
      });
    }
    mMessageListTopForeground = findViewById(2131820763);
    mMessageListBottomForeground = findViewById(2131820764);
    mTitleBarTallBgHeight = getResources().getDimensionPixelSize(2131427367);
    setTopPlaceholderHeight(mTitleBarTallBgHeight);
  }
  
  protected void initialize(long paramLong)
  {
    initializeConversation(paramLong);
    Object localObject = getIntent();
    cancelFailedToDeliverNotification((Intent)localObject, this);
    Uri localUri = ((Intent)localObject).getData();
    if (localUri != null) {}
    try
    {
      String str = localUri.getQueryParameter("select_id");
      if (str != null)
      {
        mInitTargetMessageId = Long.parseLong(str);
        mHighlightText = localUri.getQueryParameter("highlight_text");
        mBodySubstitution = localUri.getQueryParameter("body_substitution");
        mMsgListView.setAllowTranscriptOnResize(false);
      }
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      for (;;) {}
    }
    mNewMessageCount = ((Intent)localObject).getIntExtra("new_message_count", 0);
    wasSoftKeyboardEnabled = ((Intent)localObject).getBooleanExtra("was_soft_keyboard_on", false);
    initMessageList();
    initialize();
    localObject = getRecipients();
    if ((localObject == null) || (((ContactList)localObject).isEmpty()))
    {
      Toast.makeText(this, 2131361937, 0).show();
      finish();
    }
    do
    {
      return;
      updateSendButtonState();
      mIsPullDown = false;
      mMsgListViewTopOld = 0;
    } while (!TextUtils.isEmpty(sSaveMmsPartToDiskPath));
    DirParseSDK.getInstance().init();
  }
  
  protected void initializeConversation(long paramLong)
  {
    mConversation = Conversation.get(paramLong);
  }
  
  protected boolean isGroup()
  {
    return false;
  }
  
  protected boolean isNewHmsConversation(Cursor paramCursor)
  {
    return false;
  }
  
  public boolean isPreparedForSending()
  {
    if (mAirModeOn) {}
    for (;;)
    {
      return false;
      int i;
      if (mWorkingMessage != null) {
        if ((mWorkingMessage.hasAttachment()) || (mWorkingMessage.hasText())) {
          i = 1;
        }
      }
      while (i != 0)
      {
        return MSimUtils.isMSimSlotIdValid(mUseSlotId);
        i = 0;
        continue;
        i = 0;
      }
    }
  }
  
  protected boolean isReadOnly()
  {
    return false;
  }
  
  public boolean isRunning()
  {
    return !mIsStopped;
  }
  
  public void loadMessageContent()
  {
    if (wasSoftKeyboardEnabled)
    {
      Log.v("ConversationBase", "soft keyboard is popped up, put off querying msg list");
      mMsgListView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          if (mIsSoftInputEnabled)
          {
            Log.v("ConversationBase", "start querying msg onGlobalLayout");
            mHandler.removeCallbacks(mPostStartMsgListQuery);
            mHandler.post(mPostStartMsgListQuery);
            mMsgListView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            ConversationBase.access$102(ConversationBase.this, false);
            return;
          }
          Log.w("ConversationBase", "soft keyboard is not enabled");
        }
      });
    }
    for (;;)
    {
      mNeedUpdateSendFailedNotify = true;
      drawBottomPanel();
      return;
      mHandler.removeCallbacks(mPostStartMsgListQuery);
      mHandler.post(mPostStartMsgListQuery);
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 5)
    {
      mWaitingForSubActivity = false;
      mTimedMsgIndicator.setClickable(true);
      if (paramInt2 == -1)
      {
        long l = paramIntent.getLongExtra(DateTimePickerActivity.FIELD_TIME, -1L);
        if (l != -1L) {
          setTimeToSend(l);
        }
      }
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    mBackPressed = true;
    super.onBackPressed();
  }
  
  protected void onChildSimInfoChanged()
  {
    if (MSimUtils.isMSimInserted())
    {
      mNeedChangeSlotId = true;
      startMsgListQuery();
    }
  }
  
  protected void onContactsUpdated(ContactList paramContactList)
  {
    super.onContactsUpdated(paramContactList);
    updateTitle(paramContactList);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mContext = this;
    mContentResolver = getContentResolver();
    mBackgroundQueryHandler = new BackgroundQueryHandler(mContentResolver);
    long l = getIntent().getLongExtra("thread_id", -1L);
    Log.v("ConversationBase", "loading thread " + l);
    if (l <= 0L)
    {
      finish();
      return;
    }
    initialize(l);
  }
  
  protected void onDestroy()
  {
    if (mPendingChangeCursor != null)
    {
      mPendingChangeCursor.close();
      mPendingChangeCursor = null;
    }
    if (mMsgListAdapter != null)
    {
      mMsgListAdapter.changeCursor(null);
      mMsgListAdapter.cleanCache();
    }
    mBackgroundQueryHandler.cancelOperation(9700);
    mBackgroundQueryHandler.cancelOperation(9701);
    mBackgroundQueryHandler.cancelOperation(9702);
    mBackgroundQueryHandler.cancelOperation(9527);
    if (mBatchDeleteProgressDialog != null)
    {
      mBatchDeleteProgressDialog.dismiss();
      mBatchDeleteProgressDialog = null;
    }
    if (mMxGuideWindow != null) {
      mMxGuideWindow.dismiss();
    }
    mMxGuideWindow = null;
    if (DirParseSDK.getInstance().getService() != null) {
      DirParseSDK.getInstance().close();
    }
    super.onDestroy();
  }
  
  public void onMessageSent()
  {
    super.onMessageSent();
    if (mMsgListAdapter.getCount() == 0) {
      startMsgListQuery();
    }
  }
  
  public void onMore()
  {
    Message localMessage = new Message();
    what = 0;
    mPullToMoreHandler.sendMessageDelayed(localMessage, 700L);
  }
  
  protected void onMsgListViewScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (paramInt == 1)
    {
      hideSoftKeyboard();
      disableAttachmentPanel();
      mMsgListView.setNeedToScrollEnd(false);
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    startActivity(paramIntent);
    finish();
  }
  
  protected void onResetMessageAnimationEnd()
  {
    if (!mAllowAnimation) {
      return;
    }
    mInAnimation = false;
    applyPendingCursor();
  }
  
  protected void onResetMessageAnimationStart()
  {
    if (!mAllowAnimation) {
      return;
    }
    mInAnimation = true;
    snapshotMsgList();
  }
  
  protected void onResume()
  {
    super.onResume();
    NewMessagePopupActivity.dismiss(this);
  }
  
  protected void onStart()
  {
    super.onStart();
    mIsStopped = false;
    initLayoutStyle();
    loadMessageContent();
    updateTitle(getRecipients());
  }
  
  protected void onStop()
  {
    super.onStop();
    mIsStopped = true;
    if (mPendingChangeCursor != null)
    {
      mPendingChangeCursor.close();
      mPendingChangeCursor = null;
    }
    if ((mBackPressed) && (mMsgListAdapter != null))
    {
      mMsgListAdapter.changeCursor(null);
      mBackPressed = false;
    }
    for (;;)
    {
      PreviewImageLoader.getInstance().cancelAllRequests(isFinishing());
      return;
      mMsgListAdapter.setOnDataSetChangedListener(null);
    }
  }
  
  public void onUserInteraction()
  {
    checkPendingNotification();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if (paramBoolean) {
      checkPendingNotification();
    }
    super.onWindowFocusChanged(paramBoolean);
  }
  
  public void sendMessage(int paramInt)
  {
    checkAndSendMessage(true, paramInt);
  }
  
  protected void setListViewEditModeListener()
  {
    mMsgListView.setEditModeListener(new ModeCallback());
  }
  
  protected void setSendTimeForSpecifiedMessage(MessageItem paramMessageItem)
  {
    if (!paramMessageItem.isTimed()) {
      return;
    }
    mTimedMsgIndicator.setClickable(false);
    mOldTimeToSend = paramMessageItem.getDate();
    paramMessageItem = new Intent("android.intent.action.PICK");
    paramMessageItem.setType(DateTimePickerActivity.CONTENT_TYPE);
    paramMessageItem.setPackage(getPackageName());
    paramMessageItem.putExtra(DateTimePickerActivity.FIELD_TIME, mOldTimeToSend);
    paramMessageItem.putExtra(DateTimePickerActivity.FIELD_TITLE, getString(2131362152));
    startActivityForResult(paramMessageItem, 5);
  }
  
  public void setTextSize(float paramFloat)
  {
    super.setTextSize(paramFloat);
    if (mMsgListAdapter != null) {
      mMsgListAdapter.setTextSize(paramFloat);
    }
    if ((mMsgListView != null) && (mMsgListView.getVisibility() == 0))
    {
      int j = mMsgListView.getChildCount();
      int i = 0;
      while (i < j)
      {
        View localView = mMsgListView.getChildAt(i);
        if ((localView != null) && ((localView instanceof MessageListItem))) {
          ((MessageListItem)localView).setBodyTextSize(paramFloat);
        }
        i += 1;
      }
    }
  }
  
  protected abstract void startMsgListQuery();
  
  protected abstract void updateTitle(ContactList paramContactList);
  
  protected boolean willDiscardDraft()
  {
    return false;
  }
  
  protected final class BackgroundQueryHandler
    extends AsyncQueryHandler
  {
    public BackgroundQueryHandler(ContentResolver paramContentResolver)
    {
      super();
    }
    
    protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      paramObject = ConversationBase.this;
      mBatchDeleteTaskCount -= 1;
      if ((mBatchDeleteTaskCount <= 0) && (ConversationBase.mBatchDeleteProgressDialog != null))
      {
        ConversationBase.mBatchDeleteProgressDialog.dismiss();
        ConversationBase.mBatchDeleteProgressDialog = null;
      }
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      switch (paramInt)
      {
      }
      for (;;)
      {
        return;
        if (mIsStopped) {
          if (paramCursor != null) {
            paramCursor.close();
          }
        }
        while (mNeedUpdateSendFailedNotify)
        {
          ConversationBase.access$1502(ConversationBase.this, false);
          ConversationBase.this.updateSendFailedNotification();
          return;
          if (paramCursor != null) {
            ConversationBase.this.requestChangeCursor(paramCursor);
          }
        }
      }
    }
  }
  
  private class BatchDeleteListener
    implements DialogInterface.OnClickListener
  {
    public ActionMode mActionMode;
    private boolean mDeleteLockedMessage;
    protected List<MessageItem> mSelectedMsgs;
    
    public BatchDeleteListener(ActionMode paramActionMode)
    {
      mSelectedMsgs = paramActionMode;
      ActionMode localActionMode;
      mActionMode = localActionMode;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      Object localObject1 = new HashSet();
      Object localObject2 = new HashSet();
      paramDialogInterface = mSelectedMsgs.iterator();
      Object localObject3;
      while (paramDialogInterface.hasNext())
      {
        localObject3 = (MessageItem)paramDialogInterface.next();
        if ("mms".equals(((MessageItem)localObject3).getType())) {
          ((HashSet)localObject2).add(Long.valueOf(((MessageItem)localObject3).getMsgId()));
        } else if ("sms".equals(((MessageItem)localObject3).getType())) {
          if (isGroup()) {
            ((HashSet)localObject1).add(Long.valueOf(((MessageItem)localObject3).getDate()));
          } else {
            ((HashSet)localObject1).add(Long.valueOf(((MessageItem)localObject3).getMsgId()));
          }
        }
      }
      if ((mDeleteLockedMessage) || (isGroup()))
      {
        paramDialogInterface = "";
        mBatchDeleteTaskCount = 0;
        if (!((HashSet)localObject1).isEmpty()) {
          if (!isGroup()) {
            break label399;
          }
        }
      }
      label399:
      for (localObject1 = String.format("(%s=%s AND (%s IN (%s)))", new Object[] { "thread_id", Long.valueOf(mConversation.getThreadId()), "date", TextUtils.join(",", (Iterable)localObject1) });; localObject1 = "_id IN (" + TextUtils.join(",", (Iterable)localObject1) + ")")
      {
        localObject3 = ConversationBase.this;
        mBatchDeleteTaskCount += 1;
        mBackgroundQueryHandler.startDelete(9700, null, Telephony.Sms.CONTENT_URI, DatabaseUtils.concatenateWhere(paramDialogInterface, (String)localObject1), null);
        if (!((HashSet)localObject2).isEmpty())
        {
          localObject1 = "_id IN (" + TextUtils.join(",", (Iterable)localObject2) + ")";
          localObject2 = ConversationBase.this;
          mBatchDeleteTaskCount += 1;
          Mx2DeleteHelper.deleteMms(9700, null, Telephony.Mms.CONTENT_URI, DatabaseUtils.concatenateWhere(paramDialogInterface, (String)localObject1), null, mDeleteCallback, mMsgListAdapter.getAudioItemCache());
        }
        if (mBatchDeleteTaskCount > 0) {
          ConversationBase.mBatchDeleteProgressDialog = ProgressDialog.show(ConversationBase.this, null, getString(2131362132), true, false);
        }
        mActionMode.finish();
        return;
        paramDialogInterface = "locked = 0";
        break;
      }
    }
    
    public void setDeleteLockedMessage(boolean paramBoolean)
    {
      mDeleteLockedMessage = paramBoolean;
    }
  }
  
  private class DeleteMessageListener
    implements DialogInterface.OnClickListener
  {
    private final ActionMode mActionMode;
    private final boolean mDeleteLocked;
    private final Uri mDeleteUri;
    private final boolean mIsSms;
    private final long mTimeStamp;
    
    public DeleteMessageListener(Uri paramUri, boolean paramBoolean1, long paramLong, boolean paramBoolean2, ActionMode paramActionMode)
    {
      mDeleteUri = paramUri;
      mDeleteLocked = paramBoolean1;
      mTimeStamp = paramLong;
      mIsSms = paramBoolean2;
      mActionMode = paramActionMode;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      if (isGroup())
      {
        if (mIsSms)
        {
          str = String.format("%s=%s AND %s=%s", new Object[] { "thread_id", Long.valueOf(mConversation.getThreadId()), "date", Long.valueOf(mTimeStamp) });
          mBackgroundQueryHandler.startDelete(9700, null, Telephony.Sms.CONTENT_URI, str, null);
        }
        for (;;)
        {
          paramDialogInterface.dismiss();
          mActionMode.finish();
          return;
          mBackgroundQueryHandler.startDelete(9700, null, mDeleteUri, null, null);
        }
      }
      Uri localUri = mDeleteUri;
      if (mDeleteLocked) {}
      for (String str = null;; str = "locked=0")
      {
        Mx2DeleteHelper.deleteMms(9700, null, localUri, str, null, mDeleteCallback, mMsgListAdapter.getAudioItemCache());
        break;
      }
    }
  }
  
  protected class ModeCallback
    implements EditableListView.EditModeListener
  {
    private EditableListView.EditableListViewCheckable mCheckable;
    private EditActionMode mEditActionMode;
    private Menu mRootMenu;
    
    protected ModeCallback() {}
    
    private List<MessageItem> getCheckedMessageItems()
    {
      HashSet localHashSet = mCheckable.getCheckedItemInPositions();
      return mMsgListAdapter.getCheckedItems(localHashSet);
    }
    
    private void handleMenuItemClick(final ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      List localList = getCheckedMessageItems();
      int i = paramMenuItem.getItemId();
      int j = localList.size();
      if (j == 0) {
        if (paramMenuItem.getItemId() == 16908313) {
          paramActionMode.finish();
        }
      }
      label760:
      do
      {
        return;
        if (paramMenuItem.getItemId() == 16908314)
        {
          if (mCheckable.isAllChecked())
          {
            mCheckable.checkNothing();
            return;
          }
          mCheckable.checkAll();
          return;
        }
        Log.e("ConversationBase", String.format("onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", new Object[] { Integer.valueOf(j), Integer.valueOf(i) }));
        return;
        if ((j != 1) && (i != 2131820914) && (i != 2131820915) && (i != 2131820913) && (i != 2131820912) && (i != 2131820911) && (i != 16908313) && (i != 16908314) && (i != 2131820919))
        {
          Log.e("ConversationBase", String.format("onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", new Object[] { Integer.valueOf(j), Integer.valueOf(i) }));
          return;
        }
        DialogInterface.OnClickListener local2 = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramActionMode.finish();
          }
        };
        Object localObject = (MessageItem)localList.get(0);
        switch (paramMenuItem.getItemId())
        {
        default: 
          return;
        case 16908313: 
          paramActionMode.finish();
          return;
        case 2131820912: 
          MessageUtils.copyMessageTextToClipboard(ConversationBase.this, localList);
          paramActionMode.finish();
          return;
        case 2131820911: 
          MessageUtils.forwardMessage(ConversationBase.this, localList, mConversation.isPrivate());
          paramActionMode.finish();
          return;
        case 2131820916: 
          i = ((Integer)mCheckable.getCheckedItemInPositions().iterator().next()).intValue();
          try
          {
            paramActionMode = (Cursor)mMsgListAdapter.getItem(i);
            paramActionMode = MessageUtils.getMessageDetails(ConversationBase.this, paramActionMode, (MessageItem)localObject);
            new AlertDialog.Builder(ConversationBase.this).setTitle(2131361941).setMessage(paramActionMode).setPositiveButton(17039370, local2).setCancelable(true).show();
            return;
          }
          catch (Exception paramActionMode)
          {
            Log.e("ConversationBase", "onMenuItemClick: get cursor failed", paramActionMode);
            return;
          }
        case 2131820913: 
          if (1 == j)
          {
            paramActionMode = new ConversationBase.DeleteMessageListener(ConversationBase.this, ((MessageItem)localObject).getMessageUri(), ((MessageItem)localObject).isLocked(), ((MessageItem)localObject).getDate(), ((MessageItem)localObject).isSms(), paramActionMode);
            ConversationBase.this.confirmDeleteDialog(paramActionMode, ((MessageItem)localObject).isLocked(), ((MessageItem)localObject).isGroup());
            return;
          }
          boolean bool2 = false;
          paramActionMode = new ConversationBase.BatchDeleteListener(ConversationBase.this, localList, paramActionMode);
          paramMenuItem = localList.iterator();
          do
          {
            bool1 = bool2;
            if (!paramMenuItem.hasNext()) {
              break;
            }
          } while (!((MessageItem)paramMenuItem.next()).isLocked());
          boolean bool1 = true;
          ConversationBase.this.confirmBatchDeleteDialog(paramActionMode, j, bool1);
          return;
        case 2131820917: 
          paramMenuItem = null;
          if (TextUtils.isEmpty(ConversationBase.sSaveMmsPartToDiskPath)) {
            ConversationBase.this.doDirParseQuery();
          }
          if (((MessageItem)localObject).getMx2Type() > 0)
          {
            localObject = ((MessageItem)localObject).getMx2Attachments();
            paramActionMode = paramMenuItem;
            if (localObject != null)
            {
              paramActionMode = paramMenuItem;
              if (((List)localObject).size() > 0)
              {
                paramActionMode = (Attachment)((List)localObject).get(0);
                paramActionMode = MxMessageUtils.saveAttachmentFileToDisk(ConversationBase.this, paramActionMode);
              }
            }
            paramMenuItem = new AlertDialog.Builder(ConversationBase.this);
            paramMenuItem.setTitle(2131362012);
            paramMenuItem.setIconAttribute(16843605);
            if (paramActionMode == null) {
              break label760;
            }
            paramMenuItem.setMessage(getString(2131362013, new Object[] { paramActionMode }));
          }
          for (;;)
          {
            paramMenuItem.setNeutralButton(17039370, local2);
            paramMenuItem.show();
            return;
            paramActionMode = ConversationBase.this.copyMedia(((MessageItem)localObject).getMsgId());
            break;
            paramMenuItem.setMessage(2131362014);
          }
        case 2131820914: 
          if (1 == j) {
            ConversationBase.this.lockMessage((MessageItem)localObject, true);
          }
          for (;;)
          {
            paramActionMode.finish();
            return;
            ConversationBase.this.batchLockMessages(localList, true);
          }
        case 2131820915: 
          if (1 == j) {
            ConversationBase.this.lockMessage((MessageItem)localObject, false);
          }
          for (;;)
          {
            paramActionMode.finish();
            return;
            ConversationBase.this.batchLockMessages(localList, false);
          }
        case 2131820918: 
          new ConversationBase.SelectedMessage(ConversationBase.this, ConversationBase.this, (MessageItem)localObject).saveMessageToSim();
          paramActionMode.finish();
          return;
        case 16908314: 
          if (mCheckable.isAllChecked())
          {
            mCheckable.checkNothing();
            return;
          }
          mCheckable.checkAll();
          return;
        }
      } while (!Build.IS_CM_CUSTOMIZATION_TEST);
      ConversationBase.this.gotoSelectCopyText(localList);
    }
    
    private void prepareMultipleSelectionMenu()
    {
      if (getRecipients().size() > 1) {}
      boolean bool5;
      int m;
      boolean bool4;
      boolean bool3;
      int n;
      Object localObject;
      for (int k = 1;; k = 0)
      {
        bool5 = mConversation.isPrivate();
        m = 1;
        j = 1;
        bool4 = false;
        boolean bool2 = false;
        bool3 = false;
        boolean bool1 = false;
        long l = mConversation.getThreadId();
        n = 0;
        i = 0;
        if (k != 0) {
          break;
        }
        localObject = getCheckedMessageItems().iterator();
        for (;;)
        {
          m = j;
          bool3 = bool1;
          n = i;
          bool4 = bool2;
          if (!((Iterator)localObject).hasNext()) {
            break;
          }
          MessageItem localMessageItem = (MessageItem)((Iterator)localObject).next();
          m = j;
          if (!localMessageItem.isLocked())
          {
            m = j;
            if (localMessageItem.getThreadId() == l)
            {
              m = j;
              if (localMessageItem.isDownloaded()) {
                m = 0;
              }
            }
          }
          bool3 = bool2;
          if (localMessageItem.hasText()) {
            bool3 = true;
          }
          bool4 = bool1;
          if (localMessageItem.isDownloaded()) {
            bool4 = true;
          }
          j = m;
          bool1 = bool4;
          bool2 = bool3;
          if (localMessageItem.getMx2Type() == 3)
          {
            i = 1;
            j = m;
            bool1 = bool4;
            bool2 = bool3;
          }
        }
      }
      int j = mRootMenu.size();
      int i = 0;
      if (i < j)
      {
        localObject = mRootMenu.getItem(i);
        switch (((MenuItem)localObject).getItemId())
        {
        case 2131820916: 
        case 2131820917: 
        default: 
          ((MenuItem)localObject).setEnabled(false);
        }
        for (;;)
        {
          i += 1;
          break;
          if (bool5)
          {
            ((MenuItem)localObject).setVisible(false);
          }
          else if ((!bool3) || (k != 0))
          {
            ((MenuItem)localObject).setVisible(true);
            ((MenuItem)localObject).setEnabled(false);
          }
          else if (m != 0)
          {
            ((MenuItem)localObject).setVisible(false);
          }
          else
          {
            ((MenuItem)localObject).setVisible(true);
            ((MenuItem)localObject).setEnabled(true);
            continue;
            if (bool5)
            {
              ((MenuItem)localObject).setVisible(false);
            }
            else if ((!bool3) || (k != 0))
            {
              ((MenuItem)localObject).setVisible(false);
            }
            else if (m != 0)
            {
              ((MenuItem)localObject).setVisible(true);
              ((MenuItem)localObject).setEnabled(true);
            }
            else
            {
              ((MenuItem)localObject).setVisible(false);
              continue;
              ((MenuItem)localObject).setEnabled(true);
              continue;
              ((MenuItem)localObject).setEnabled(bool4);
              continue;
              ((MenuItem)localObject).setEnabled(bool3);
              if (n != 0)
              {
                ((MenuItem)localObject).setEnabled(false);
                continue;
                ((MenuItem)localObject).setVisible(false);
                continue;
                ((MenuItem)localObject).setVisible(false);
              }
            }
          }
        }
      }
    }
    
    private void prepareNoneSelectionMenu()
    {
      boolean bool;
      int i;
      label37:
      MenuItem localMenuItem;
      if (getRecipients().size() > 1)
      {
        bool = mConversation.isPrivate();
        int j = mRootMenu.size();
        i = 0;
        if (i >= j) {
          return;
        }
        localMenuItem = mRootMenu.getItem(i);
        switch (localMenuItem.getItemId())
        {
        case 2131820916: 
        case 2131820917: 
        default: 
          localMenuItem.setEnabled(false);
        }
      }
      for (;;)
      {
        i += 1;
        break label37;
        break;
        if (bool) {
          localMenuItem.setVisible(false);
        }
        for (;;)
        {
          localMenuItem.setEnabled(false);
          break;
          localMenuItem.setVisible(true);
        }
        localMenuItem.setVisible(false);
        continue;
        localMenuItem.setVisible(false);
        continue;
        localMenuItem.setVisible(false);
      }
    }
    
    private void prepareSingleSelectionMenu()
    {
      Object localObject = getCheckedMessageItems();
      if (((List)localObject).size() != 1)
      {
        Log.e("ConversationBase", "prepareSingleSelectionMenu: expect size=1 but size=" + ((List)localObject).size());
        return;
      }
      localObject = (MessageItem)((List)localObject).get(0);
      boolean bool;
      if (mConversation.isPrivate())
      {
        mRootMenu.findItem(2131820914).setVisible(false);
        mRootMenu.findItem(2131820915).setVisible(false);
        mRootMenu.findItem(2131820913).setEnabled(true);
        mRootMenu.findItem(2131820912).setEnabled(((MessageItem)localObject).hasText());
        MenuItem localMenuItem = mRootMenu.findItem(2131820916);
        if (mConversation.isB2c()) {
          break label534;
        }
        bool = true;
        label174:
        localMenuItem.setEnabled(bool);
        if ((Build.IS_CM_CUSTOMIZATION_TEST) && (!((MessageItem)localObject).isGroup()))
        {
          mRootMenu.findItem(2131820919).setVisible(true);
          mRootMenu.findItem(2131820919).setEnabled(((MessageItem)localObject).hasText());
        }
        if (!((MessageItem)localObject).isMms()) {
          break label585;
        }
        if (!ConversationBase.this.haveSomethingToCopyToSDCard((MessageItem)localObject)) {
          break label539;
        }
        mRootMenu.findItem(2131820917).setEnabled(true);
        label271:
        if (((MessageItem)localObject).getMx2Type() != 3) {
          break label561;
        }
        mRootMenu.findItem(2131820911).setEnabled(false);
      }
      for (;;)
      {
        mRootMenu.findItem(2131820918).setVisible(false);
        return;
        if ((!((MessageItem)localObject).isGroup()) && (((MessageItem)localObject).isDownloaded()) && (mConversation.getThreadId() == ((MessageItem)localObject).getThreadId()))
        {
          if (((MessageItem)localObject).isLocked())
          {
            mRootMenu.findItem(2131820915).setVisible(true);
            mRootMenu.findItem(2131820915).setEnabled(true);
            mRootMenu.findItem(2131820914).setVisible(false);
            break;
          }
          if (((MessageItem)localObject).isLocked()) {
            break;
          }
          mRootMenu.findItem(2131820915).setVisible(false);
          mRootMenu.findItem(2131820914).setVisible(true);
          mRootMenu.findItem(2131820914).setEnabled(true);
          break;
        }
        mRootMenu.findItem(2131820915).setVisible(false);
        mRootMenu.findItem(2131820914).setVisible(true);
        mRootMenu.findItem(2131820914).setEnabled(false);
        break;
        label534:
        bool = false;
        break label174;
        label539:
        mRootMenu.findItem(2131820917).setEnabled(false);
        break label271;
        label561:
        mRootMenu.findItem(2131820911).setEnabled(((MessageItem)localObject).isDownloaded());
      }
      label585:
      mRootMenu.findItem(2131820917).setEnabled(false);
      mRootMenu.findItem(2131820911).setEnabled(true);
      if ((MSimUtils.getInsertedSimCount() > 0) && (!mConversation.isB2c()))
      {
        mRootMenu.findItem(2131820918).setVisible(true);
        return;
      }
      mRootMenu.findItem(2131820918).setVisible(false);
    }
    
    private void showEditModeAnimation(boolean paramBoolean)
    {
      int i = 0;
      while (i < mMsgListView.getChildCount())
      {
        View localView = mMsgListView.getChildAt(i);
        if ((localView instanceof MessageListItem)) {
          ((MessageListItem)localView).showEditModeAnimation(paramBoolean);
        }
        i += 1;
      }
    }
    
    private void updateMenu(int paramInt)
    {
      String str;
      if (paramInt == 0)
      {
        str = getString(R.string.action_mode_title_empty);
        ((ActionMode)mEditActionMode).setTitle(str);
        if (!mCheckable.isAllChecked()) {
          break label107;
        }
        mEditActionMode.setButton(16908314, R.string.action_mode_deselect_all);
      }
      for (;;)
      {
        if (paramInt <= 1) {
          break label124;
        }
        prepareMultipleSelectionMenu();
        return;
        str = getResources().getQuantityString(R.plurals.items_selected, mCheckable.count(), new Object[] { Integer.valueOf(mCheckable.count()) });
        break;
        label107:
        mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
      }
      label124:
      if (paramInt == 1)
      {
        prepareSingleSelectionMenu();
        return;
      }
      prepareNoneSelectionMenu();
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      handleMenuItemClick(paramActionMode, paramMenuItem);
      int i = paramMenuItem.getItemId();
      if ((2131820911 == i) || (2131820912 == i) || (2131820916 == i) || (2131820919 == i)) {
        paramActionMode.finish();
      }
      return true;
    }
    
    public void onCheckStateChanged(EditableListView.EditableListViewCheckable paramEditableListViewCheckable)
    {
      mCheckable = paramEditableListViewCheckable;
      mMsgListAdapter.setCheckedItem(mCheckable.getCheckedItemInIds());
      updateMenu(mCheckable.count());
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      mRootMenu = paramMenu;
      getMenuInflater().inflate(2131755012, paramMenu);
      mMsgListView.setAllowTranscriptOnResize(false);
      mMsgListAdapter.enterCheckMode();
      mCheckable = mMsgListView.getEditableListViewCheckable();
      mEditActionMode = ((EditActionMode)paramActionMode);
      updateMenu(0);
      disableAttachmentPanel();
      hideSoftKeyboard();
      mBottomPanel.setVisibility(8);
      mContactPanel.setVisibility(8);
      setTopPlaceholderHeight(0);
      mMessageListTopForeground.setVisibility(8);
      mMessageListBottomForeground.setVisibility(8);
      showEditModeAnimation(true);
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      mContactPanel.setVisibility(0);
      mMsgListView.post(new Runnable()
      {
        public void run()
        {
          mMsgListView.setAllowTranscriptOnResize(true);
        }
      });
      mMsgListAdapter.exitCheckMode();
      mBottomPanel.setVisibility(0);
      setTopPlaceholderHeight(mTitleBarTallBgHeight);
      mMessageListTopForeground.setVisibility(0);
      mMessageListBottomForeground.setVisibility(0);
      showEditModeAnimation(false);
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return false;
    }
    
    public void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean) {}
  }
  
  private static class PduBodyCache
  {
    private static PduBody mLastPduBody;
    private static Uri mLastUri;
    
    public static PduBody getPduBody(Context paramContext, Uri paramUri)
    {
      if (paramUri.equals(mLastUri)) {
        return mLastPduBody;
      }
      try
      {
        mLastPduBody = SlideshowModel.getPduBody(paramContext, paramUri);
        mLastUri = paramUri;
        return mLastPduBody;
      }
      catch (MmsException paramContext)
      {
        Log.e("ConversationBase", paramContext.getMessage(), paramContext);
      }
      return null;
    }
  }
  
  private class SelectedMessage
  {
    private Context mContext;
    private MessageItem mSelectedItem;
    
    public SelectedMessage(Context paramContext, MessageItem paramMessageItem)
    {
      mContext = paramContext;
      mSelectedItem = paramMessageItem;
    }
    
    private void createSimPickerDialog()
    {
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
      LinearLayout localLinearLayout = (LinearLayout)View.inflate(mContext, 2130968704, null);
      localLinearLayout.addView(createSimPickerItem(0), localLayoutParams);
      localLinearLayout.addView(createSimPickerItem(1), localLayoutParams);
      ConversationBase.access$3302(ConversationBase.this, new AlertDialog.Builder(mContext).setTitle(2131362032).setView(localLinearLayout).show());
    }
    
    private View createSimPickerItem(final int paramInt)
    {
      LinearLayout localLinearLayout = (LinearLayout)View.inflate(mContext, 2130968705, null);
      ((ImageView)localLinearLayout.findViewById(2131820664)).setImageResource(MSimUtils.getSimIconResId(paramInt));
      Object localObject = null;
      switch (paramInt)
      {
      }
      for (;;)
      {
        ((TextView)localLinearLayout.findViewById(2131820628)).setText((CharSequence)localObject);
        localLinearLayout.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ConversationBase.SelectedMessage.this.saveToIcc(paramInt);
            if (mSimPickerDialog != null)
            {
              mSimPickerDialog.dismiss();
              ConversationBase.access$3302(ConversationBase.this, null);
            }
          }
        });
        return localLinearLayout;
        if (!MSimUtils.isSimInserted(0))
        {
          localObject = mContext.getString(2131362235);
        }
        else
        {
          localObject = MSimUtils.getSimDisplayName(mContext, 0);
          continue;
          if (!MSimUtils.isSimInserted(1)) {
            localObject = mContext.getString(2131362236);
          } else {
            localObject = MSimUtils.getSimDisplayName(mContext, 1);
          }
        }
      }
    }
    
    private void saveToIcc(final int paramInt)
    {
      if (mSelectedItem.isSms()) {
        new AsyncTask()
        {
          protected Integer doInBackground(Void... paramAnonymousVarArgs)
          {
            if (mSelectedItem.isOutMessage()) {}
            for (int i = 5;; i = 1) {
              return Integer.valueOf(SimCardManager.getInstance(mContext).saveMessageToIcc(mSelectedItem.getAddress(), mSelectedItem.getBody(), mSelectedItem.getDate(), i, paramInt));
            }
          }
          
          protected void onPostExecute(Integer paramAnonymousInteger)
          {
            if (paramAnonymousInteger.intValue() == 1000)
            {
              Toast.makeText(mContext, 2131362241, 0).show();
              return;
            }
            if (paramAnonymousInteger.intValue() == 1002)
            {
              Toast.makeText(mContext, 2131361800, 0).show();
              return;
            }
            Toast.makeText(mContext, 2131362242, 0).show();
          }
        }.execute(new Void[0]);
      }
    }
    
    public void saveMessageToSim()
    {
      if (MSimUtils.isMSimInserted()) {
        createSimPickerDialog();
      }
      int i;
      do
      {
        return;
        i = MSimUtils.getInsertedSlotId();
      } while (i == -1);
      saveToIcc(i);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */