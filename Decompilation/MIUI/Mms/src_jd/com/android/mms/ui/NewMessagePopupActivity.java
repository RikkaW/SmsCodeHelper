package com.android.mms.ui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.MiuiConfiguration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.android.mms.data.Contact;
import com.android.mms.data.Contact.UpdateListener;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.SendMessageService;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxCacheStatusListener;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.transaction.PushSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import miui.telephony.PhoneNumberUtils;

public class NewMessagePopupActivity
  extends ListActivity
  implements TextWatcher, View.OnClickListener, Contact.UpdateListener, ISmsTextSizeAdjustHost, SizeAwareLinearLayout.OnMeasureListener, MxIdCache.MxCacheStatusListener
{
  private static NewMessagePopupActivity sInstance = null;
  private NewMessagePopupListAdapter mAdapter;
  private View mBottonEditor;
  private ImageView mButtonDelete;
  private ImageView mButtonDismiss;
  private SizeAwareLinearLayout mContentView;
  MessageThread mCurrentMessageThread = null;
  private int mDefaultCursorDrawableRes;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (what)
      {
      }
      for (;;)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
        NewMessagePopupActivity.this.updateMiMessageStyle();
      }
    }
  };
  private IntentFilter mIntentFilter;
  private long mLastMxSmsTime1;
  private long mLastMxSmsTime2;
  private ListView mMessageList;
  private TextView mMessageSender;
  LinkedList<MessageThread> mMessageThreads = new LinkedList();
  private BroadcastReceiver mPushStatusReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      NewMessagePopupActivity.this.updateMiMessageStyle();
    }
  };
  private Button mSendButton;
  private View mSendButton1;
  private ImageView mSendButton1Image;
  private TextView mSendButton1Text;
  private View mSendButton2;
  private ImageView mSendButton2Image;
  private TextView mSendButton2Text;
  private ImageView mSendSlotIdView;
  private TextView mSenderDetails;
  private View mSenderPanel;
  private View mSimButtonContainer;
  private TextView mTextCounter;
  private EditText mTextEditor;
  private int mUseSlotId;
  
  private void addMessage(MessageItem paramMessageItem, String paramString)
  {
    MessageThread localMessageThread2 = findMessageThreadAndRemoveFromList(paramString);
    MessageThread localMessageThread1 = localMessageThread2;
    if (localMessageThread2 == null)
    {
      localMessageThread1 = new MessageThread();
      address = paramString;
      messageList = new ArrayList();
    }
    if (!contains(messageList, paramMessageItem)) {
      messageList.add(paramMessageItem);
    }
    mMessageThreads.addFirst(localMessageThread1);
    if (mCurrentMessageThread == localMessageThread1)
    {
      mCurrentMessageThread.markedRead = false;
      showCurrentThread(false);
    }
    while (!canSwitchThread()) {
      return;
    }
    mCurrentMessageThread = localMessageThread1;
    showCurrentThread(true);
  }
  
  private boolean canSwitchThread()
  {
    return (mCurrentMessageThread == null) || (mTextEditor.getText().length() == 0);
  }
  
  private void cancelFloatNotification()
  {
    MessagingNotification.cancelFloatNotification(this);
  }
  
  private boolean contains(ArrayList<MessageItem> paramArrayList, MessageItem paramMessageItem)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      MessageItem localMessageItem = (MessageItem)paramArrayList.get(i);
      if ((localMessageItem.getMessageUri() != null) && (paramMessageItem.getMessageUri() != null) && (localMessageItem.getMessageUri().equals(paramMessageItem.getMessageUri()))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void deleteMessage()
  {
    mSendButton.setEnabled(false);
    SendMessageService.startMarkRead(this, mCurrentMessageThread.address);
    ArrayList localArrayList = mCurrentMessageThread.messageList;
    int i = 0;
    while (i < localArrayList.size())
    {
      SendMessageService.startDelete(this, ((MessageItem)localArrayList.get(i)).getMessageUri());
      i += 1;
    }
    proceedToNextThread();
  }
  
  public static void dismiss(Context paramContext)
  {
    MessagingNotification.cancelFloatNotification(paramContext);
    if (sInstance != null)
    {
      hideSoftInputMethod(sInstance);
      sInstance.finish();
      sInstance = null;
    }
  }
  
  private void dismissMessage()
  {
    mSendButton.setEnabled(false);
    markAsReadAsync(mCurrentMessageThread);
    proceedToNextThread();
  }
  
  private MessageThread findMessageThreadAndRemoveFromList(String paramString)
  {
    Iterator localIterator = mMessageThreads.iterator();
    while (localIterator.hasNext())
    {
      MessageThread localMessageThread = (MessageThread)localIterator.next();
      if (address.equals(paramString))
      {
        localIterator.remove();
        return localMessageThread;
      }
    }
    return null;
  }
  
  private long getCurrentThreadId()
  {
    return Conversation.get(ContactList.getByNumbers(mCurrentMessageThread.address, false)).getThreadId();
  }
  
  private void gotoConversation()
  {
    if (mTextEditor.getText().length() > 0)
    {
      ((ClipboardManager)getSystemService("clipboard")).setText(mTextEditor.getText());
      Toast.makeText(this, 2131362142, 1).show();
    }
    ComposeMessageRouterActivity.route(this, ComposeMessageRouterActivity.createIntent(this, getCurrentThreadId()));
    finish();
  }
  
  private static void hideSoftInputMethod(Activity paramActivity)
  {
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramActivity.getWindow().getDecorView().getWindowToken(), 0);
  }
  
  private boolean isMxAvailable(int paramInt)
  {
    MxIdCache.MxIdCacheItem localMxIdCacheItem = MxIdCache.getOrQuery(this, Contact.get(mCurrentMessageThread.address).getMxPhoneNumber());
    return (MSimUtils.isSimInserted(paramInt)) && (PushSession.getInstance(this).isConnected(paramInt)) && (localMxIdCacheItem != null) && (localMxIdCacheItem.allowSms());
  }
  
  private void markAsReadAsync(MessageThread paramMessageThread)
  {
    if (markedRead) {
      return;
    }
    markedRead = true;
    SendMessageService.startMarkRead(this, address);
  }
  
  private void proceedToNextThread()
  {
    Iterator localIterator = mMessageThreads.iterator();
    while (localIterator.hasNext()) {
      if ((MessageThread)localIterator.next() == mCurrentMessageThread) {
        localIterator.remove();
      }
    }
    if (mMessageThreads.isEmpty())
    {
      dismiss(this);
      return;
    }
    mCurrentMessageThread = ((MessageThread)mMessageThreads.getFirst());
    showCurrentThread(true);
    updateMiMessageStyle();
  }
  
  private void processMessage(Intent paramIntent)
  {
    Uri localUri = paramIntent.getData();
    String str = paramIntent.getStringExtra("body");
    long l = paramIntent.getLongExtra("time", 0L);
    mUseSlotId = MSimUtils.getSlotIdFromIntent(paramIntent);
    addMessage(new MessageItem(this, "sms", localUri, l, str, MSimUtils.getSimIdBySlotId(mUseSlotId)), paramIntent.getStringExtra("from"));
  }
  
  private void sendMessage()
  {
    boolean bool = false;
    mSendButton.setEnabled(false);
    if (mUseSlotId == 1) {}
    for (long l = mLastMxSmsTime1;; l = mLastMxSmsTime2)
    {
      if ((isMxAvailable(mUseSlotId)) || (System.currentTimeMillis() - Math.abs(l) <= 3000L)) {
        bool = true;
      }
      SendMessageService.startSend(this, mCurrentMessageThread.address, mTextEditor.getText().toString(), bool, mUseSlotId);
      proceedToNextThread();
      return;
    }
  }
  
  private void showCurrentThread(boolean paramBoolean)
  {
    showCurrentThreadHeader();
    mAdapter.setThread(mCurrentMessageThread);
    mAdapter.notifyDataSetChanged();
    if (paramBoolean)
    {
      mTextEditor.setText("");
      mLastMxSmsTime1 = 0L;
      mLastMxSmsTime2 = 0L;
    }
    Button localButton = mSendButton;
    if (mTextEditor.getText().length() > 0) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      localButton.setEnabled(paramBoolean);
      return;
    }
  }
  
  private void showCurrentThreadHeader()
  {
    updateCurrentThreadHeader(Contact.get(mCurrentMessageThread.address).load(false, true));
  }
  
  private void updateCurrentThreadHeader(Contact paramContact)
  {
    String str = PhoneNumberUtils.parseTelocationString(this, mCurrentMessageThread.address);
    if (paramContact.existsInDatabase())
    {
      mMessageSender.setText(paramContact.getName());
      if (TextUtils.isEmpty(str)) {
        mSenderDetails.setText(paramContact.getNumber());
      }
    }
    for (;;)
    {
      MessagingNotification.setCurrentMessageThreadId(getCurrentThreadId());
      return;
      mSenderDetails.setText(paramContact.getNumber() + " " + str);
      continue;
      if (paramContact.isYellowPageNumber())
      {
        mMessageSender.setText(paramContact.getName());
        mSenderDetails.setText(paramContact.getNumber());
      }
      else
      {
        mMessageSender.setText(paramContact.getNumber());
        if (TextUtils.isEmpty(str))
        {
          mSenderDetails.setVisibility(8);
          mSenderDetails.setText("");
        }
        else
        {
          mSenderDetails.setVisibility(0);
          mSenderDetails.setText(str);
        }
      }
    }
  }
  
  private void updateMiMessageStyle()
  {
    boolean bool1 = isMxAvailable(0);
    boolean bool2 = isMxAvailable(1);
    if (bool1)
    {
      if (mSendButton1 != null) {
        mSendButton1.setBackgroundResource(2130837958);
      }
      mLastMxSmsTime1 = 1L;
      if (!bool2) {
        break label172;
      }
      if (mSendButton2 != null) {
        mSendButton2.setBackgroundResource(2130837958);
      }
      mLastMxSmsTime2 = 1L;
      label64:
      if (((!bool1) || (mUseSlotId != 0)) && ((!bool2) || (mUseSlotId != 1))) {
        break label221;
      }
      mBottonEditor.setBackgroundResource(2130837893);
      mSendButton.setBackgroundResource(2130837951);
      if (MiuiConfiguration.getScaleMode() != 11) {
        break label208;
      }
      mTextEditor.setHint(2131362182);
    }
    for (;;)
    {
      mTextEditor.setCursorDrawableRes(2130838007);
      return;
      if (mSendButton1 != null) {
        mSendButton1.setBackgroundResource(2130837961);
      }
      if (mLastMxSmsTime1 != 1L) {
        break;
      }
      mLastMxSmsTime1 = System.currentTimeMillis();
      break;
      label172:
      if (mSendButton2 != null) {
        mSendButton2.setBackgroundResource(2130837961);
      }
      if (mLastMxSmsTime2 != 1L) {
        break label64;
      }
      mLastMxSmsTime2 = System.currentTimeMillis();
      break label64;
      label208:
      mTextEditor.setHint(2131362183);
    }
    label221:
    mBottonEditor.setBackgroundResource(2130837894);
    mSendButton.setBackgroundResource(2130837941);
    mTextEditor.setHint(2131362178);
    mTextEditor.setCursorDrawableRes(mDefaultCursorDrawableRes);
  }
  
  private void updateSlotButtonStateBySlotId(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      mSendSlotIdView.setBackgroundResource(2130837881);
      mSimButtonContainer.setVisibility(8);
      mSendButton1Text.setTextAppearance(paramContext, 2131689558);
      mSendButton2Text.setTextAppearance(paramContext, 2131689559);
      mSendButton1Image.setImageResource(2130837769);
      mSendButton2Image.setImageResource(2130837971);
      return;
    }
    mSendSlotIdView.setBackgroundResource(2130837882);
    mSimButtonContainer.setVisibility(8);
    mSendButton2Text.setTextAppearance(paramContext, 2131689558);
    mSendButton1Text.setTextAppearance(paramContext, 2131689559);
    mSendButton2Image.setImageResource(2130837771);
    mSendButton1Image.setImageResource(2130837970);
  }
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onBackPressed()
  {
    markAsReadAsync(mCurrentMessageThread);
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      return;
      if ((mSimButtonContainer != null) && (mSimButtonContainer.getVisibility() == 0)) {
        mSimButtonContainer.setVisibility(8);
      }
      sendMessage();
      return;
      mUseSlotId = 0;
      updateSlotButtonStateBySlotId(this, mUseSlotId);
      updateMiMessageStyle();
      return;
      mUseSlotId = 1;
      updateSlotButtonStateBySlotId(this, mUseSlotId);
      updateMiMessageStyle();
      return;
    } while (mSimButtonContainer == null);
    if (mSimButtonContainer.getVisibility() == 8)
    {
      mSimButtonContainer.setVisibility(0);
      return;
    }
    mSimButtonContainer.setVisibility(8);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    sInstance = this;
    super.onCreate(paramBundle);
    mContentView = ((SizeAwareLinearLayout)getLayoutInflater().inflate(2130968680, null));
    mContentView.setOnMeasureListener(this);
    setContentView(mContentView);
    MessageUtils.setWindowLayoutParams(this);
    mButtonDelete = ((ImageView)findViewById(2131820831));
    mButtonDismiss = ((ImageView)findViewById(2131820835));
    mSenderPanel = findViewById(2131820832);
    mMessageSender = ((TextView)findViewById(2131820833));
    mSenderDetails = ((TextView)findViewById(2131820834));
    mMessageList = getListView();
    mTextEditor = ((EditText)findViewById(2131820693));
    mTextCounter = ((TextView)findViewById(2131820691));
    mSendButton = ((Button)findViewById(2131820692));
    mBottonEditor = findViewById(2131820836);
    if (MSimUtils.isMSim())
    {
      mSendSlotIdView = ((ImageView)findViewById(2131820793));
      findViewById(2131820837).setVisibility(0);
      mSimButtonContainer = findViewById(2131820696);
      mSendButton1 = findViewById(2131820698);
      mSendButton1.setOnClickListener(this);
      mSendButton1Text = ((TextView)findViewById(2131820700));
      mSendButton1Image = ((ImageView)findViewById(2131820699));
      mSendButton2 = findViewById(2131820701);
      mSendButton2.setOnClickListener(this);
      mSendButton2Text = ((TextView)findViewById(2131820703));
      mSendButton2Image = ((ImageView)findViewById(2131820702));
    }
    paramBundle = getLayoutInflater().inflate(2130968658, null);
    mMessageList.addFooterView(paramBundle);
    mAdapter = new NewMessagePopupListAdapter(this);
    setListAdapter(mAdapter);
    mButtonDelete.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NewMessagePopupActivity.this.deleteMessage();
      }
    });
    mButtonDismiss.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NewMessagePopupActivity.this.dismissMessage();
      }
    });
    mSendButton.setOnClickListener(this);
    mSenderPanel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NewMessagePopupActivity.this.gotoConversation();
      }
    });
    mTextEditor.addTextChangedListener(this);
    mTextEditor.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 4)
        {
          if (TextUtils.isEmpty(mTextEditor.getText())) {
            break label30;
          }
          NewMessagePopupActivity.this.sendMessage();
        }
        for (;;)
        {
          return false;
          label30:
          Toast.makeText(NewMessagePopupActivity.this, 2131361865, 1).show();
        }
      }
    });
    mTextEditor.requestFocus();
    mDefaultCursorDrawableRes = MessageUtils.resolveDefaultCursorDrawableRes(this);
    cancelFloatNotification();
    processMessage(getIntent());
    if (MSimUtils.isMSimInserted()) {
      if (mUseSlotId == 1)
      {
        mSendSlotIdView.setBackgroundResource(2130837882);
        mSendSlotIdView.setOnClickListener(this);
        updateSlotButtonStateBySlotId(this, mUseSlotId);
      }
    }
    for (;;)
    {
      mIntentFilter = new IntentFilter();
      mIntentFilter.addAction("com.xiaomi.mms.PUSH_STATUS_CHANGED");
      MxIdCache.addStatusListener(this);
      return;
      mSendSlotIdView.setBackgroundResource(2130837881);
      break;
      if (mSendSlotIdView != null) {
        mSendSlotIdView.setVisibility(8);
      }
      if (mSimButtonContainer != null) {
        mSimButtonContainer.setVisibility(8);
      }
    }
  }
  
  protected void onDestroy()
  {
    MxIdCache.removeStatusListener(this);
    if (sInstance == this) {
      sInstance = null;
    }
    super.onDestroy();
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    gotoConversation();
  }
  
  public void onMxIdAdded(String paramString1, String paramString2) {}
  
  public void onMxIdOffline(String paramString1, String paramString2)
  {
    mHandler.obtainMessage(1).sendToTarget();
  }
  
  public void onMxIdOnline(String paramString1, String paramString2)
  {
    mHandler.obtainMessage(1).sendToTarget();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    cancelFloatNotification();
    setIntent(paramIntent);
    processMessage(paramIntent);
  }
  
  protected void onPause()
  {
    unregisterReceiver(mPushStatusReceiver);
    Contact.removeListener(this);
    MessagingNotification.setCurrentMessageThreadId(0L);
    super.onPause();
  }
  
  public void onPostLayout() {}
  
  public void onPreMeasure(SizeAwareLinearLayout paramSizeAwareLinearLayout, int paramInt1, int paramInt2)
  {
    if (View.MeasureSpec.getMode(paramInt2) != Integer.MIN_VALUE) {
      return;
    }
    if (mTextEditor.getLineCount() <= 2)
    {
      mTextCounter.setVisibility(8);
      return;
    }
    mTextCounter.setVisibility(0);
  }
  
  protected void onResume()
  {
    super.onResume();
    mAdapter.notifyDataSetChanged();
    updateMiMessageStyle();
    registerReceiver(mPushStatusReceiver, mIntentFilter);
    Contact.addListener(this);
    showCurrentThreadHeader();
  }
  
  protected void onStart()
  {
    super.onStart();
    SmsTextSizeAdjust.getInstance().init(this, this);
    SmsTextSizeAdjust.getInstance().refresh();
  }
  
  protected void onStop()
  {
    SmsTextSizeAdjust.getInstance();
    SmsTextSizeAdjust.clear(this);
    super.onStop();
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    Button localButton = mSendButton;
    if (paramCharSequence.length() > 0) {}
    for (boolean bool = true;; bool = false)
    {
      localButton.setEnabled(bool);
      mTextCounter.setText(MessageUtils.getMessageStats(paramCharSequence));
      if (paramCharSequence.length() > 0) {
        markAsReadAsync(mCurrentMessageThread);
      }
      return;
    }
  }
  
  public void onUpdate(final Contact paramContact)
  {
    if (mHandler != null) {
      mHandler.post(new Runnable()
      {
        public void run()
        {
          if ((Contact.get(mCurrentMessageThread.address) != paramContact) || (isFinishing())) {
            return;
          }
          NewMessagePopupActivity.this.updateCurrentThreadHeader(paramContact);
        }
      });
    }
  }
  
  public void setTextSize(float paramFloat)
  {
    if (mTextEditor != null) {
      mTextEditor.setTextSize(0, paramFloat);
    }
    if (mAdapter != null) {
      mAdapter.setTextSize(paramFloat);
    }
  }
  
  public static class MessagePopupLayout
    extends SizeAwareLinearLayout
  {
    public MessagePopupLayout(Context paramContext)
    {
      super();
    }
    
    public MessagePopupLayout(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    protected void onMeasure(int paramInt1, int paramInt2)
    {
      super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(((Activity)getContext()).getWindow().getWindowManager().getDefaultDisplay().getHeight() / 2, Integer.MIN_VALUE));
    }
  }
  
  class MessageThread
  {
    public String address;
    public boolean markedRead;
    ArrayList<MessageItem> messageList;
    
    MessageThread() {}
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessagePopupActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */