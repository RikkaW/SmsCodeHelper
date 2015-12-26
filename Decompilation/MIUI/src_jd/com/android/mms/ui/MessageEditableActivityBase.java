package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.provider.Settings.System;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.Display;
import android.view.IWindowManager;
import android.view.IWindowManager.Stub;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.MmsConfig;
import com.android.mms.audio.AudioBtnTouchRunnable;
import com.android.mms.audio.AudioRecordingController;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.android.mms.data.WorkingMessage.MessageStatusListener;
import com.android.mms.model.ContactParser;
import com.android.mms.model.SlideshowModel;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.transaction.MmsSystemEventReceiver.SimStateChangedListener;
import com.android.mms.util.MSimUtils;
import com.google.android.collect.Sets;
import com.xiaomi.mms.data.MxCapability;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxCacheStatusListener;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.PushSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import miui.app.Activity;
import miui.app.OnStatusBarChangeListener;
import miui.os.Build;
import miui.telephony.PhoneNumberUtils.PhoneNumber;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;
import miui.util.AttributeResolver;

public abstract class MessageEditableActivityBase
  extends Activity
  implements View.OnClickListener, WorkingMessage.MessageStatusListener, ISmsTextSizeAdjustHost, SizeAwareLinearLayout.OnMeasureListener, MxIdCache.MxCacheStatusListener
{
  private static final Object PRESENCE = new Object();
  protected boolean mAirModeOn = false;
  protected boolean mAllowAnimation = false;
  protected AttachmentProcessor mAttachmentProcessor;
  protected AttachmentView mAttachmentView;
  private AudioRecordingController mAudioController;
  private TextView mAudioNameView;
  private long mBackupSimId1;
  private long mBackupSimId2;
  protected View mBottomPanel;
  private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ("android.intent.action.AIRPLANE_MODE".equals(paramAnonymousIntent.getAction()))
      {
        mAirModeOn = paramAnonymousIntent.getBooleanExtra("state", false);
        postUpdateSendButtonState();
        return;
      }
      if (PushSession.getInstance(paramAnonymousContext).getConnectedSimIndex() > 0) {
        MessageEditableActivityBase.access$2702(MessageEditableActivityBase.this, true);
      }
      onPushStatusChanged();
    }
  };
  private AudioBtnTouchRunnable mBtnTouch;
  protected View mContactPanel;
  protected View mContentParent;
  protected FrameLayout mContentView;
  protected Conversation mConversation;
  private int mDefaultCursorDrawableRes;
  protected View mEditorContainer;
  private boolean mExitOnSent;
  protected Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      Log.v("MessageEditableActivityBase", "handle msg on main thread, msg: " + what);
      switch (what)
      {
      case 2: 
      default: 
        super.handleMessage(paramAnonymousMessage);
      }
      Object localObject;
      do
      {
        do
        {
          return;
          postSwitchMsgType();
          return;
          localObject = (String)obj;
          l = paramAnonymousMessage.getData().getLong("capability");
          MessageEditableActivityBase.this.addOrRemoveRecipient(l, (String)localObject);
          mPendingRecipients.remove(localObject);
        } while (what != 1);
        postSwitchMsgType();
        return;
        localObject = getRecipients();
      } while (localObject == null);
      String str = (String)obj;
      if (!((ContactList)localObject).contains(Contact.get(str)))
      {
        Log.w("MessageEditableActivityBase", "recipient is no longer in list");
        return;
      }
      long l = paramAnonymousMessage.getData().getLong("capability");
      MessageEditableActivityBase.this.addOrRemoveRecipient(l, str);
      postSwitchMsgType();
    }
  };
  protected FrameLayout mHistoryView;
  protected Button mHomeButton;
  protected InputMethodManager mInputMethodManager;
  protected boolean mIsLandscape;
  protected boolean mIsSoftInputEnabled = false;
  protected boolean mIsSwitchMenuBtn;
  private long[] mLastMxMmsTime;
  private long[] mLastMxSmsTime;
  private Toast mMaxPendingMessagesReachedToast = null;
  protected View mMessageContentPanel;
  protected int mMessageContentPanelMinHeight;
  protected SizeAwareLinearLayout mMsgContentView;
  private final Map<String, Object> mMxAudioRecipients = new HashMap();
  private boolean mMxEnabled;
  private final Map<String, Object> mMxMmsRecipients = new HashMap();
  private final Map<String, Object> mMxSmsRecipients = new HashMap();
  protected boolean mMxStyle;
  private final Map<String, Object> mMxV2Recipients = new HashMap();
  protected boolean mOrgMsgIsPrivate = false;
  private final Map<String, Object> mPendingRecipients = new ConcurrentHashMap();
  private Runnable mPostUpdateSendButtonStateRunnable = new Runnable()
  {
    public void run()
    {
      Log.v("MessageEditableActivityBase", "run updateSendButtonState");
      updateSendButtonState();
    }
  };
  private Handler mQueryStatusHandler;
  private HandlerThread mQueryStatusWorkThread;
  private int mSavedSlotIdForECM = -1;
  protected View mSendButton;
  protected View mSendButton1;
  private ImageView mSendButton1Image;
  private TextView mSendButton1Text;
  private View mSendButton2;
  private ImageView mSendButton2Image;
  private TextView mSendButton2Text;
  private ImageView mSendSlotIdView;
  private boolean mSendingMessage;
  protected SharedPreferences mSharedPrefs;
  protected View mSimButtonContainer;
  protected SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener()
  {
    public void onSubscriptionsChanged()
    {
      int i = 1;
      long l1;
      long l2;
      if (MSimUtils.isMSimInserted())
      {
        l1 = MSimUtils.getSimIdBySlotId(0);
        l2 = MSimUtils.getSimIdBySlotId(1);
        if ((mBackupSimId1 == l1) && (mBackupSimId2 == l2)) {
          i = 0;
        }
      }
      while (i != 0)
      {
        Log.d("MessageEditableActivityBase", "update sim info change");
        updateSlotRelatedState();
        postUpdateSendButtonState();
        onChildSimInfoChanged();
        return;
        MessageEditableActivityBase.access$2502(MessageEditableActivityBase.this, l1);
        MessageEditableActivityBase.access$2602(MessageEditableActivityBase.this, l2);
        continue;
        MessageEditableActivityBase.access$2502(MessageEditableActivityBase.this, -1L);
        MessageEditableActivityBase.access$2602(MessageEditableActivityBase.this, -1L);
      }
      updateSlotButtonInfo();
    }
  };
  protected MmsSystemEventReceiver.SimStateChangedListener mSimStateChagnedListener = new MmsSystemEventReceiver.SimStateChangedListener()
  {
    public void onSimStateChanged(String paramAnonymousString)
    {
      Log.d("MessageEditableActivityBase", "update sim state change");
      updateSlotRelatedState();
      postUpdateSendButtonState();
    }
  };
  protected int mSoftKeyboardMinHeight;
  protected int mStatusBarHeight = 0;
  protected View mSubjectDivider;
  private final TextWatcher mSubjectEditorWatcher = new TextWatcher()
  {
    private boolean mIsEnglish = true;
    private boolean mNested = false;
    private CharSequence mOldText = null;
    private int mSelectionEnd = 0;
    private Toast mToast = null;
    
    private boolean isTooLong(CharSequence paramAnonymousCharSequence)
    {
      boolean bool2 = false;
      mIsEnglish = true;
      int i = 0;
      while (i < paramAnonymousCharSequence.length())
      {
        if (paramAnonymousCharSequence.charAt(i) > '') {
          mIsEnglish = false;
        }
        i += 1;
      }
      boolean bool1;
      if ((mIsEnglish) || (paramAnonymousCharSequence.length() <= 13))
      {
        bool1 = bool2;
        if (mIsEnglish)
        {
          bool1 = bool2;
          if (paramAnonymousCharSequence.length() <= 40) {}
        }
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    
    public void afterTextChanged(Editable paramAnonymousEditable)
    {
      if (mNested) {
        return;
      }
      int i;
      int j;
      if (isTooLong(paramAnonymousEditable))
      {
        if (!mIsEnglish) {
          break label235;
        }
        i = 2131362141;
        j = 40;
        label29:
        if (mToast != null) {
          break label244;
        }
        mToast = Toast.makeText(MessageEditableActivityBase.this, i, 1);
      }
      for (;;)
      {
        mToast.show();
        mNested = true;
        Object localObject2 = "";
        i = 0;
        Object localObject1 = localObject2;
        if (!TextUtils.isEmpty(mOldText))
        {
          int k = mOldText.length();
          i = k - mSelectionEnd;
          if (i > 0) {
            localObject2 = mOldText.subSequence(mSelectionEnd, k);
          }
          localObject1 = localObject2;
          if (TextUtils.isEmpty((CharSequence)localObject2))
          {
            localObject1 = "";
            i = 0;
          }
        }
        i = j - i;
        paramAnonymousEditable = paramAnonymousEditable.subSequence(0, i);
        mSubjectTextEditor.setText(paramAnonymousEditable.toString() + localObject1.toString());
        mSubjectTextEditor.setSelection(i);
        mNested = false;
        if (!isSubjectEditorVisible()) {
          break;
        }
        mWorkingMessage.setSubject(mSubjectTextEditor.getText(), true);
        return;
        label235:
        i = 2131362140;
        j = 13;
        break label29;
        label244:
        mToast.setText(i);
      }
    }
    
    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      if (mNested) {
        return;
      }
      mOldText = paramAnonymousCharSequence.subSequence(0, paramAnonymousCharSequence.length());
      mSelectionEnd = (paramAnonymousInt1 + paramAnonymousInt2);
    }
    
    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
  };
  private final View.OnKeyListener mSubjectKeyListener = new View.OnKeyListener()
  {
    public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
    {
      if (paramAnonymousKeyEvent.getAction() != 0) {}
      while ((paramAnonymousInt != 67) || (mSubjectTextEditor.length() != 0)) {
        return false;
      }
      MessageEditableActivityBase.this.showSubjectEditor(false);
      if (mTextEditor != null) {
        mTextEditor.requestFocus();
      }
      mWorkingMessage.setSubject(null, true);
      return true;
    }
  };
  protected EditText mSubjectTextEditor;
  protected Button mSwitchBtn;
  private Runnable mSwitchMsgTypeRunnable = new Runnable()
  {
    public void run()
    {
      Log.v("MessageEditableActivityBase", "run mSwitchMsgTypeRunnable");
      ContactList localContactList = getRecipients();
      if (localContactList == null)
      {
        Log.v("MessageEditableActivityBase", "mSwitchMsgTypeRunnable recipients is null");
        return;
      }
      boolean bool4 = mWorkingMessage.requiresMms();
      mMxStyle = false;
      boolean bool1 = false;
      boolean bool2 = false;
      if (MSimUtils.isSimInserted(0)) {
        bool1 = MessageEditableActivityBase.this.isMxAvailable(0);
      }
      if (MSimUtils.isSimInserted(1)) {
        bool2 = MessageEditableActivityBase.this.isMxAvailable(1);
      }
      int i;
      label119:
      boolean bool3;
      int j;
      label232:
      int k;
      label258:
      label307:
      Object localObject;
      if (MessageEditableActivityBase.this.isB2cMessageActivity())
      {
        mMxStyle = true;
        if (MiuiConfiguration.getScaleMode() != 11) {
          break label619;
        }
        i = 1;
        if (!MessageEditableActivityBase.this.isB2cMessageActivity()) {
          break label676;
        }
        mEditorContainer.setBackgroundResource(2130837588);
        if (mSendButton != null) {
          mSendButton.setBackgroundResource(2130837941);
        }
        mTextEditor.setCursorDrawableRes(mDefaultCursorDrawableRes);
        bool3 = MxActivateService.isMxEnabled(getApplicationContext(), 0);
        boolean bool5 = MxActivateService.isMxEnabled(getApplicationContext(), 1);
        if ((!MSimUtils.isMSimSlotIdValid(0)) || (!PushSession.getInstance(getApplicationContext()).isConnected(0))) {
          break label624;
        }
        j = 1;
        if ((!MSimUtils.isMSimSlotIdValid(1)) || (!PushSession.getInstance(getApplicationContext()).isConnected(1))) {
          break label629;
        }
        k = 1;
        if (((!bool3) || (!bool5) || (j == 0) || (k == 0)) && ((mUseSlotId != 0) || (j == 0)) && ((mUseSlotId != 1) || (k == 0))) {
          break label634;
        }
        j = 1;
        if (j == 0) {
          break label645;
        }
        localObject = mTextEditor;
        if (i == 0) {
          break label639;
        }
        i = 2131362182;
        label327:
        ((EditText)localObject).setHint(i);
        label333:
        if (!bool1) {
          break label955;
        }
        if (!bool4) {
          break label942;
        }
        mLastMxMmsTime[0] = 1L;
        label353:
        if (mSendButton1 != null) {
          mSendButton1.setBackgroundResource(2130837958);
        }
        label375:
        if (!bool2) {
          break label1053;
        }
        if (!bool4) {
          break label1040;
        }
        mLastMxMmsTime[1] = 1L;
        label395:
        if (mSendButton2 != null) {
          mSendButton2.setBackgroundResource(2130837958);
        }
      }
      label619:
      label624:
      label629:
      label634:
      label639:
      label645:
      label676:
      label942:
      label955:
      label1038:
      label1040:
      label1053:
      label1136:
      for (;;)
      {
        Log.v("MessageEditableActivityBase", "switch to mx mode: " + mMxStyle + ", recipients: " + localContactList.size() + ", sms: " + mMxSmsRecipients.size() + ", mms: " + mMxMmsRecipients.size());
        postUpdateSendButtonState();
        return;
        if (MSimUtils.isMSimInserted())
        {
          localObject = MessageEditableActivityBase.this;
          if (((bool1) && (bool2)) || ((mUseSlotId == 0) && (bool1)) || ((mUseSlotId == 1) && (bool2))) {}
          for (bool3 = true;; bool3 = false)
          {
            mMxStyle = bool3;
            break;
          }
        }
        if (MSimUtils.isSimInserted(0))
        {
          mMxStyle = bool1;
          break;
        }
        if (!MSimUtils.isSimInserted(1)) {
          break;
        }
        mMxStyle = bool2;
        break;
        i = 0;
        break label119;
        j = 0;
        break label232;
        k = 0;
        break label258;
        j = 0;
        break label307;
        i = 2131362183;
        break label327;
        localObject = mTextEditor;
        if (i != 0) {}
        for (i = 2131362407;; i = 2131362406)
        {
          ((EditText)localObject).setHint(i);
          break;
        }
        if (mMxStyle)
        {
          mEditorContainer.setBackgroundResource(2130837589);
          if (mSendButton != null) {
            mSendButton.setBackgroundResource(2130837951);
          }
          mTextEditor.setCursorDrawableRes(2130838007);
          if (localContactList.size() > 0)
          {
            if (i != 0)
            {
              mTextEditor.setHint(2131362182);
              break label333;
            }
            mTextEditor.setHint(2131362183);
            break label333;
          }
          mTextEditor.setHint("");
          break label333;
        }
        mEditorContainer.setBackgroundResource(2130837588);
        if (mSendButton != null) {
          mSendButton.setBackgroundResource(2130837941);
        }
        mTextEditor.setCursorDrawableRes(mDefaultCursorDrawableRes);
        if (i != 0)
        {
          localEditText = mTextEditor;
          if (bool4) {}
          for (localObject = getString(2131362181);; localObject = getString(2131362180))
          {
            localEditText.setHint((CharSequence)localObject);
            break;
          }
        }
        EditText localEditText = mTextEditor;
        if (bool4) {}
        for (localObject = getString(2131362179);; localObject = getString(2131362178))
        {
          localEditText.setHint((CharSequence)localObject);
          break;
        }
        mLastMxSmsTime[0] = 1L;
        break label353;
        if (bool4) {
          if (mLastMxMmsTime[0] == 1L) {
            mLastMxMmsTime[0] = System.currentTimeMillis();
          }
        }
        for (;;)
        {
          if (mSendButton1 == null) {
            break label1038;
          }
          mSendButton1.setBackgroundResource(2130837961);
          break;
          if (mLastMxSmsTime[0] == 1L) {
            mLastMxSmsTime[0] = System.currentTimeMillis();
          }
        }
        break label375;
        mLastMxSmsTime[1] = 1L;
        break label395;
        if (bool4) {
          if (mLastMxMmsTime[1] == 1L) {
            mLastMxMmsTime[1] = System.currentTimeMillis();
          }
        }
        for (;;)
        {
          if (mSendButton2 == null) {
            break label1136;
          }
          mSendButton2.setBackgroundResource(2130837961);
          break;
          if (mLastMxSmsTime[1] == 1L) {
            mLastMxSmsTime[1] = System.currentTimeMillis();
          }
        }
      }
    }
  };
  protected Dialog mTaskDialog;
  protected TextView mTextCounter;
  protected EditText mTextEditor;
  protected final TextWatcher mTextEditorWatcher = new TextWatcher()
  {
    private final long MX_STATUS_CHECK_DURATION = 10000L;
    private long lastCheckTime = System.currentTimeMillis();
    private int mOldEnd = 0;
    private int mOldStart = 0;
    private String mOldText = null;
    
    public void afterTextChanged(final Editable paramAnonymousEditable)
    {
      if (mWorkingMessage.requiresMms())
      {
        paramAnonymousEditable = mOldText;
        final int i = mOldStart;
        final int j = mOldEnd;
        confirmRemovingChenghu(null, new Runnable()
        {
          public void run()
          {
            mTextEditor.setText(paramAnonymousEditable);
            mTextEditor.setSelection(i, j);
          }
        });
      }
      if (System.currentTimeMillis() - lastCheckTime > 10000L)
      {
        lastCheckTime = System.currentTimeMillis();
        if (mConversation != null)
        {
          paramAnonymousEditable = mConversation.getRecipients();
          if (paramAnonymousEditable != null)
          {
            postSwitchMsgType();
            if (MxActivateService.isMxEnabled(MessageEditableActivityBase.this))
            {
              paramAnonymousEditable = paramAnonymousEditable.iterator();
              while (paramAnonymousEditable.hasNext())
              {
                Contact localContact = (Contact)paramAnonymousEditable.next();
                MxIdCache.getOrQuery(MessageEditableActivityBase.this, localContact.getMxPhoneNumber());
              }
            }
          }
        }
      }
    }
    
    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      mOldText = mTextEditor.getText().toString();
      mOldStart = mTextEditor.getSelectionStart();
      mOldEnd = mTextEditor.getSelectionEnd();
    }
    
    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      if ((Build.IS_CM_CUSTOMIZATION_TEST) && (paramAnonymousCharSequence.length() > 3100))
      {
        Toast.makeText(MessageEditableActivityBase.this, getString(2131361850), 1).show();
        mTextEditor.setText(mOldText);
        return;
      }
      onUserInteraction();
      mWorkingMessage.setText(paramAnonymousCharSequence);
      if (MessageEditableActivityBase.this.updateCounter())
      {
        Toast.makeText(MessageEditableActivityBase.this, getString(2131361849), 1).show();
        mWorkingMessage.setText(mOldText);
        mTextEditor.setText(mOldText);
        return;
      }
      postUpdateSendButtonState();
    }
  };
  protected TextView mTimedDescView;
  protected ImageButton mTimedMsgIndicator;
  protected boolean mToastForDraftSave;
  protected int mTopPlaceholderHeight;
  private AsyncTask<Void, Void, Boolean> mUpdateContactTask;
  protected int mUseSlotId;
  protected boolean mWaitingForSubActivity;
  protected WorkingMessage mWorkingMessage;
  
  private void addOrRemoveRecipient(long paramLong, String paramString)
  {
    paramString = Contact.normalizePhoneNumber(paramString);
    MxCapability localMxCapability = new MxCapability(paramLong);
    if (localMxCapability.allowMms())
    {
      mMxMmsRecipients.put(paramString, PRESENCE);
      if (!localMxCapability.allowSms()) {
        break label118;
      }
      mMxSmsRecipients.put(paramString, PRESENCE);
      label59:
      if (!localMxCapability.allowAudio()) {
        break label132;
      }
      mMxAudioRecipients.put(paramString, PRESENCE);
    }
    for (;;)
    {
      if (!localMxCapability.allowMxV2()) {
        break label146;
      }
      mMxV2Recipients.put(paramString, PRESENCE);
      return;
      mMxMmsRecipients.remove(paramString);
      break;
      label118:
      mMxSmsRecipients.remove(paramString);
      break label59;
      label132:
      mMxAudioRecipients.remove(paramString);
    }
    label146:
    mMxV2Recipients.remove(paramString);
  }
  
  private boolean allowAnimation()
  {
    boolean bool = false;
    Object localObject1 = IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
    try
    {
      localObject1 = ((IWindowManager)localObject1).getAnimationScales();
      if (localObject1 != null) {
        if (localObject1.length >= 1)
        {
          i = (int)(localObject1[0] + 0.5F) % 10;
          j = i;
          if (localObject1.length >= 2) {
            j = i + ((int)(localObject1[1] + 0.5F) & 0x7) * 10;
          }
          if (j != 0) {
            bool = true;
          }
          return bool;
        }
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Object localObject2 = null;
        continue;
        int i = 0;
        continue;
        int j = 0;
      }
    }
  }
  
  private boolean canSendByMx(int paramInt)
  {
    return canSendByMx(paramInt, isMxAvailable(paramInt));
  }
  
  private boolean canSendByMx(int paramInt, boolean paramBoolean)
  {
    if ((paramInt < 0) || (!MxActivateService.isMxEnabled(this, paramInt)) || (mWorkingMessage.getTimeToSend() > 0L)) {}
    boolean bool;
    long l;
    do
    {
      return false;
      bool = mWorkingMessage.requiresMms();
      l = System.currentTimeMillis();
    } while ((!paramBoolean) && ((!bool) || (l - Math.abs(mLastMxMmsTime[paramInt]) > 3000L)) && ((bool) || (l - Math.abs(mLastMxSmsTime[paramInt]) > 3000L)));
    return true;
  }
  
  private void cancelUpdateContactNames()
  {
    if (mUpdateContactTask != null)
    {
      mUpdateContactTask.cancel(true);
      mUpdateContactTask = null;
    }
  }
  
  private boolean containsEmailAddress()
  {
    mWorkingMessage.syncWorkingRecipients();
    return mWorkingMessage.getConversation().getRecipients().containsEmail();
  }
  
  private String getBody(Uri paramUri)
  {
    if (paramUri == null) {}
    for (;;)
    {
      return null;
      paramUri = paramUri.getSchemeSpecificPart();
      if (paramUri.contains("?"))
      {
        paramUri = paramUri.substring(paramUri.indexOf('?') + 1).split("&");
        int j = paramUri.length;
        int i = 0;
        while (i < j)
        {
          String str = paramUri[i];
          if (str.startsWith("body=")) {
            try
            {
              str = URLDecoder.decode(str.substring(5), "UTF-8");
              return str;
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
          }
          i += 1;
        }
      }
    }
  }
  
  private View getContentParent()
  {
    View localView = findViewById(16908290);
    if (localView.getParent().getParent().getParent() != null) {
      return (View)localView.getParent();
    }
    return localView;
  }
  
  private boolean handleSendIntent(final Intent paramIntent)
  {
    boolean bool = true;
    final Object localObject1 = paramIntent.getExtras();
    if (localObject1 == null)
    {
      bool = false;
      return bool;
    }
    final String str = paramIntent.getType();
    final Object localObject2 = paramIntent.getAction();
    if ("android.intent.action.SEND".equals(localObject2))
    {
      if (!MessageUtils.isSendingContactByVCard(mSharedPrefs))
      {
        paramIntent = ContactParser.getContactParser(this).getContactFromIntent(paramIntent);
        if (!TextUtils.isEmpty(paramIntent))
        {
          mWorkingMessage.setText(paramIntent);
          return true;
        }
      }
      if (!((Bundle)localObject1).containsKey("android.intent.extra.STREAM")) {
        break label415;
      }
      paramIntent = (Uri)((Bundle)localObject1).getParcelable("android.intent.extra.STREAM");
      mAttachmentProcessor.addAttachment(str, paramIntent, false);
    }
    label415:
    for (int i = 1;; i = 0)
    {
      if (((Bundle)localObject1).containsKey("android.intent.extra.TEXT"))
      {
        mWorkingMessage.setText(((Bundle)localObject1).getString("android.intent.extra.TEXT"));
        i = 1;
      }
      if (i != 0) {
        break;
      }
      do
      {
        return false;
        if (("android.intent.action.SEND_MULTIPLE".equals(localObject2)) && (((Bundle)localObject1).containsKey("android.intent.extra.STREAM")))
        {
          paramIntent = mWorkingMessage.getSlideshow();
          localObject2 = ((Bundle)localObject1).getParcelableArrayList("android.intent.extra.STREAM");
          if (paramIntent != null) {}
          for (i = paramIntent.size();; i = 0)
          {
            int k = ((ArrayList)localObject2).size();
            final int j = k;
            if (k + i > 20)
            {
              j = Math.min(20 - i, k);
              Toast.makeText(this, getString(2131361874, new Object[] { Integer.valueOf(20), Integer.valueOf(j) }), 1).show();
            }
            paramIntent = null;
            if (((Bundle)localObject1).containsKey("android.intent.extra.TEXT")) {
              paramIntent = ((Bundle)localObject1).getString("android.intent.extra.TEXT");
            }
            localObject1 = new AlertDialog.Builder(this).setIconAttribute(16843605).setTitle(2131361875).setMessage(2131361876).create();
            final Runnable local9 = new Runnable()
            {
              public void run()
              {
                localObject1.show();
              }
            };
            mHandler.postDelayed(local9, 1000L);
            new Thread(new Runnable()
            {
              public void run()
              {
                int i = 0;
                while (i < j)
                {
                  Parcelable localParcelable = (Parcelable)localObject2.get(i);
                  mAttachmentProcessor.addAttachment(str, (Uri)localParcelable, true);
                  i += 1;
                }
                if (!TextUtils.isEmpty(paramIntent)) {
                  mWorkingMessage.tryInsertExtraText(paramIntent);
                }
                mHandler.removeCallbacks(local9);
                localObject1.dismiss();
              }
            }, "addAttachment").start();
            return true;
          }
        }
      } while (!"android.intent.action.SEND_MSG".equals(localObject2));
      paramIntent = paramIntent.getData();
      if (!Conversation.checkContentScheme(paramIntent)) {
        return false;
      }
      mAttachmentProcessor.addAttachment(str, paramIntent, false);
      return true;
    }
  }
  
  private boolean handleSendToIntent(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    if (localBundle == null) {}
    String str;
    do
    {
      return false;
      str = paramIntent.getType();
    } while (!"android.intent.action.SENDTO".equals(paramIntent.getAction()));
    if (localBundle.containsKey("android.intent.extra.STREAM"))
    {
      paramIntent = (Uri)localBundle.getParcelable("android.intent.extra.STREAM");
      mAttachmentProcessor.addAttachment(str, paramIntent, false);
    }
    for (int i = 1;; i = 0)
    {
      if (localBundle.containsKey("android.intent.extra.TEXT"))
      {
        mWorkingMessage.setText(localBundle.getString("android.intent.extra.TEXT"));
        i = 1;
      }
      if (i == 0) {
        break;
      }
      return true;
    }
  }
  
  private boolean hasMessage()
  {
    return (isSubjectEditorVisible()) || (isPreparedForSending());
  }
  
  private boolean hasRecipientsToRevise()
  {
    boolean bool2 = false;
    HashSet localHashSet = Sets.newHashSet(mSharedPrefs.getString("nickname_revised_numbers", "").split("\n"));
    ContactList localContactList = mConversation.getRecipients();
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < localContactList.size())
      {
        Object localObject = (Contact)localContactList.get(i);
        if ((((Contact)localObject).existsInDatabase()) && (((Contact)localObject).isPhoneNumber()))
        {
          localObject = PhoneNumberUtils.PhoneNumber.parse(((Contact)localObject).getNumber());
          String str = ((PhoneNumberUtils.PhoneNumber)localObject).getNormalizedNumber(false, true);
          ((PhoneNumberUtils.PhoneNumber)localObject).recycle();
          if (!localHashSet.contains(str)) {
            bool1 = true;
          }
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  private void hideAudioViews()
  {
    View localView;
    if (mSendButton != null)
    {
      localView = mSendButton;
      if ((!mMxStyle) || (isB2cMessageActivity())) {
        break label57;
      }
    }
    label57:
    for (int i = 2130837951;; i = 2130837941)
    {
      localView.setBackgroundResource(i);
      boolean bool = isPreparedForSending();
      mSendButton.setEnabled(bool);
      mSendButton.setFocusable(bool);
      return;
    }
  }
  
  private boolean isB2cMessageActivity()
  {
    return this instanceof B2cMessageConversationActivity;
  }
  
  private boolean isContain(ContactList paramContactList, Map<String, Object> paramMap)
  {
    if (paramContactList.size() == paramMap.size()) {
      return true;
    }
    paramContactList = paramContactList.iterator();
    while (paramContactList.hasNext())
    {
      Contact localContact = (Contact)paramContactList.next();
      int j = 0;
      Iterator localIterator = paramMap.keySet().iterator();
      do
      {
        i = j;
        if (!localIterator.hasNext()) {
          break;
        }
      } while (!PhoneNumberUtils.PhoneNumber.parse(localContact.getCompareKey()).getNormalizedNumber(true, false).equals(PhoneNumberUtils.PhoneNumber.parse((CharSequence)localIterator.next()).getNormalizedNumber(true, false)));
      int i = 1;
      if (i == 0) {
        return false;
      }
    }
    return true;
  }
  
  private boolean isMxAvailable(int paramInt)
  {
    boolean bool = mWorkingMessage.requiresMms();
    mMxEnabled = MxActivateService.isMxEnabled(this);
    ContactList localContactList = getRecipients();
    return (MxActivateService.isMxEnabled(this, paramInt)) && (mWorkingMessage.getTimeToSend() <= 0L) && (MSimUtils.isSimInserted(paramInt)) && (PushSession.getInstance(this).isConnected(paramInt)) && (localContactList.size() > 0) && (((bool) && (localContactList.size() == mMxMmsRecipients.size())) || ((!bool) && (localContactList.size() == mMxSmsRecipients.size())));
  }
  
  private boolean recipientsOverSize()
  {
    if ((mWorkingMessage.requiresMms()) && (getRecipients().size() > 50))
    {
      new AlertDialog.Builder(this).setIconAttribute(16843605).setTitle(2131361865).setMessage(2131361877).setPositiveButton(2131361891, null).show();
      return true;
    }
    return false;
  }
  
  private void resetMessage()
  {
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("MessageEditableActivityBase", "resetMessage");
    }
    mAttachmentView.setVisibility(8);
    mAudioNameView.setVisibility(8);
    mTimedMsgIndicator.setVisibility(8);
    mTimedDescView.setVisibility(8);
    showSubjectEditor(false);
    mTextEditor.requestFocus();
    mTextEditor.removeTextChangedListener(mTextEditorWatcher);
    TextKeyListener.clear(mTextEditor.getText());
    mWorkingMessage = WorkingMessage.createEmpty(this);
    mWorkingMessage.setConversation(mConversation);
    drawBottomPanel();
    mAttachmentProcessor.updateAttachmentTypeStates();
    mTextEditor.addTextChangedListener(mTextEditorWatcher);
    if (mIsLandscape) {
      hideSoftKeyboard();
    }
    mSendingMessage = false;
  }
  
  private void sendMessage(boolean paramBoolean, final int paramInt)
  {
    if ((paramBoolean) && (Boolean.parseBoolean(SystemProperties.get("ril.cdma.inecmmode")))) {
      try
      {
        startActivityForResult(new Intent("android.intent.action.ACTION_SHOW_NOTICE_ECM_BLOCK_OTHERS", null), 1);
        mSavedSlotIdForECM = paramInt;
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        Log.e("MessageEditableActivityBase", "Cannot find EmergencyCallbackModeExitDialog", localActivityNotFoundException);
      }
    }
    if ((Build.IS_CU_CUSTOMIZATION) && (containsEmailAddress()))
    {
      new AlertDialog.Builder(this).setIconAttribute(16843605).setMessage(2131362305).setPositiveButton(2131361843, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MessageEditableActivityBase.this.sendingMessage(paramInt);
        }
      }).setNegativeButton(2131361892, null).show();
      return;
    }
    sendingMessage(paramInt);
  }
  
  private void sendingMessage(int paramInt)
  {
    String str;
    if (!mSendingMessage)
    {
      str = mWorkingMessage.getWorkingRecipients();
      if (!isB2cMessageActivity()) {
        break label63;
      }
      Log.v("MessageEditableActivityBase", "send b2c msg");
      mWorkingMessage.setSendByMx(true);
      mWorkingMessage.setSendByMxV2(true);
    }
    for (;;)
    {
      mWorkingMessage.send(str, paramInt);
      mSendingMessage = true;
      return;
      label63:
      boolean bool = canSendByMx(paramInt);
      Log.v("MessageEditableActivityBase", "sending msg by mx: " + bool);
      mWorkingMessage.setSendByMx(bool);
      mWorkingMessage.setSendByMxV2(canSendByMxV2(paramInt));
    }
  }
  
  private void showAudioViews()
  {
    if (mSendButton != null)
    {
      if (mAudioController == null)
      {
        mAudioController = new AudioRecordingController(this, isMx2AudioAvailable(), mBottomPanel, (ViewStub)findViewById(2131820689), (ViewStub)findViewById(2131820803), mSendButton, mSwitchBtn, mTextEditor, this);
        mBtnTouch = new AudioBtnTouchRunnable(mAudioController);
      }
      mSendButton.setEnabled(true);
      mSendButton.setBackgroundResource(2130837578);
    }
  }
  
  private void showSubjectEditor(boolean paramBoolean)
  {
    int j = 0;
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("MessageEditableActivityBase", "showSubjectEditor: " + paramBoolean);
    }
    if (mSubjectTextEditor == null)
    {
      if (!paramBoolean) {
        return;
      }
      mSubjectTextEditor = ((EditText)findViewById(2131820596));
      mSubjectTextEditor.setTextSize(0, SmsTextSizeAdjust.getInstance().getTextSize());
      mSubjectDivider = findViewById(2131820789);
    }
    EditText localEditText = mSubjectTextEditor;
    Object localObject;
    if (paramBoolean)
    {
      localObject = mSubjectKeyListener;
      localEditText.setOnKeyListener((View.OnKeyListener)localObject);
      if (!paramBoolean) {
        break label200;
      }
      mSubjectTextEditor.removeTextChangedListener(mSubjectEditorWatcher);
      mSubjectTextEditor.addTextChangedListener(mSubjectEditorWatcher);
      label139:
      localObject = mSubjectTextEditor;
      if (!paramBoolean) {
        break label214;
      }
      i = 0;
      label151:
      ((EditText)localObject).setVisibility(i);
      mSubjectTextEditor.setText(mWorkingMessage.getSubject());
      localObject = mSubjectDivider;
      if (!paramBoolean) {
        break label220;
      }
    }
    label200:
    label214:
    label220:
    for (int i = j;; i = 8)
    {
      ((View)localObject).setVisibility(i);
      invalidateOptionsMenu();
      return;
      localObject = null;
      break;
      mSubjectTextEditor.removeTextChangedListener(mSubjectEditorWatcher);
      break label139;
      i = 8;
      break label151;
    }
  }
  
  private void toastConvertInfo(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 2131361868;; i = 2131361869)
    {
      Toast.makeText(this, i, 0).show();
      return;
    }
  }
  
  private void updateContactNames()
  {
    mUpdateContactTask = new AsyncTask()
    {
      protected Boolean doInBackground(Void... paramAnonymousVarArgs)
      {
        paramAnonymousVarArgs = mConversation.getRecipients();
        boolean bool = false;
        paramAnonymousVarArgs = paramAnonymousVarArgs.iterator();
        for (;;)
        {
          Contact localContact;
          if (paramAnonymousVarArgs.hasNext())
          {
            localContact = (Contact)paramAnonymousVarArgs.next();
            if (!isCancelled()) {}
          }
          else
          {
            return Boolean.valueOf(bool);
          }
          String str = localContact.getName();
          localContact.load(true, true);
          if (!str.equals(localContact.getName())) {
            bool = true;
          }
        }
      }
      
      protected void onPostExecute(Boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean.booleanValue()) {
          onContactsUpdated(mConversation.getRecipients());
        }
      }
    };
    mUpdateContactTask.execute(new Void[0]);
  }
  
  private boolean updateCounter()
  {
    return updateCounter(mWorkingMessage.requiresMms());
  }
  
  private boolean updateCounter(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if ((Build.IS_CM_CUSTOMIZATION) || (Build.IS_CU_CUSTOMIZATION))
      {
        i = mWorkingMessage.getCurrentMmsSize();
        if (i > MmsConfig.getMaxMessageSize())
        {
          mTextCounter.setText(2131361844);
          return false;
        }
        localObject = getString(2131361844) + "\n";
        localObject = (String)localObject + String.valueOf((i - 1) / 1024 + 1) + "Kb";
        mTextCounter.setText((CharSequence)localObject);
        return false;
      }
      mTextCounter.setText(2131361844);
      return false;
    }
    Object localObject = SmsMessage.calculateLength(mWorkingMessage.getText(), false);
    int i = localObject[0];
    int j = localObject[2];
    mTextCounter.setText(MessageUtils.getMessageStats(i, j));
    return false;
  }
  
  private void updateNicknameRevisedNumbers()
  {
    Object localObject1 = Sets.newHashSet(mSharedPrefs.getString("nickname_revised_numbers", "").split("\n"));
    Object localObject2 = mConversation.getRecipients();
    int i = 0;
    while (i < ((ContactList)localObject2).size())
    {
      Object localObject3 = (Contact)((ContactList)localObject2).get(i);
      if ((((Contact)localObject3).existsInDatabase()) && (((Contact)localObject3).isPhoneNumber()))
      {
        localObject3 = PhoneNumberUtils.PhoneNumber.parse(((Contact)localObject3).getNumber());
        String str = ((PhoneNumberUtils.PhoneNumber)localObject3).getNormalizedNumber(false, true);
        ((PhoneNumberUtils.PhoneNumber)localObject3).recycle();
        ((HashSet)localObject1).add(str);
      }
      i += 1;
    }
    localObject1 = TextUtils.join("\n", ((HashSet)localObject1).toArray());
    localObject2 = mSharedPrefs.edit();
    ((SharedPreferences.Editor)localObject2).putString("nickname_revised_numbers", (String)localObject1);
    ((SharedPreferences.Editor)localObject2).commit();
  }
  
  protected boolean canSendByMxV2(int paramInt)
  {
    return (canSendByMx(paramInt, isMxV2Available())) && (isContain(getRecipients(), mMxV2Recipients));
  }
  
  public void cancelTiming()
  {
    mTimedMsgIndicator.setVisibility(8);
    mTimedDescView.setVisibility(8);
    mWorkingMessage.setTimeToSend(0L);
    mWorkingMessage.setTimeToSendDesc("");
    postSwitchMsgType();
  }
  
  protected void checkAndSendMessage(boolean paramBoolean, int paramInt)
  {
    if (MessageUtils.isPrivacyModeEnabled(this))
    {
      Toast.makeText(this, 2131362145, 0).show();
      return;
    }
    sendMessage(paramBoolean, paramInt);
  }
  
  public void confirmRemovingChenghu(final Runnable paramRunnable1, final Runnable paramRunnable2)
  {
    if (mTextEditor.getText().toString().indexOf(65535) == -1)
    {
      if (paramRunnable1 != null) {
        paramRunnable1.run();
      }
      return;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(2131362139);
    localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = mTextEditor.getText();
        int i;
        for (paramAnonymousInt = 0; paramAnonymousInt < paramAnonymousDialogInterface.length(); paramAnonymousInt = i + 1)
        {
          i = paramAnonymousInt;
          if (paramAnonymousDialogInterface.charAt(paramAnonymousInt) == 65535)
          {
            paramAnonymousDialogInterface.delete(paramAnonymousInt, paramAnonymousInt + 1);
            i = paramAnonymousInt - 1;
          }
        }
        if (paramRunnable1 != null) {
          paramRunnable1.run();
        }
      }
    });
    localBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramRunnable2 != null) {
          paramRunnable2.run();
        }
      }
    });
    localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        if (paramRunnable2 != null) {
          paramRunnable2.run();
        }
      }
    });
    localBuilder.create().show();
  }
  
  public boolean containChenghu()
  {
    return mTextEditor.getText().toString().indexOf(65535) != -1;
  }
  
  public void delayedShowSoftKeyboard(final boolean paramBoolean)
  {
    mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        if (paramBoolean)
        {
          showSoftKeyboard();
          return;
        }
        hideSoftKeyboard();
      }
    }, 200L);
  }
  
  protected void disableAttachmentPanel()
  {
    mAttachmentProcessor.disableAttachmentPanel();
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((mAudioController != null) && (mAudioController.dispatchTouchEvent(paramMotionEvent, mUseSlotId, isMx2AudioAvailable()))) {
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  protected void drawBottomPanel()
  {
    Object localObject;
    if (mWorkingMessage.getTimeToSend() > 0L)
    {
      mTimedMsgIndicator.setVisibility(0);
      localObject = mWorkingMessage.getTimeToSendDesc();
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        break label85;
      }
      mTimedDescView.setVisibility(8);
      label44:
      if (!mWorkingMessage.hasSlideshow()) {
        break label104;
      }
      mTextEditor.setVisibility(8);
    }
    for (;;)
    {
      updateCounter();
      postSwitchMsgType();
      return;
      mTimedMsgIndicator.setVisibility(8);
      break;
      label85:
      mTimedDescView.setVisibility(0);
      mTimedDescView.setText((CharSequence)localObject);
      break label44;
      label104:
      mTextEditor.setVisibility(0);
      if (mWorkingMessage.hasAttachment()) {
        mTextEditor.setHint("");
      }
      localObject = mWorkingMessage.getText();
      if (localObject != null)
      {
        if (!TextUtils.equals(mTextEditor.getText(), (CharSequence)localObject)) {
          mTextEditor.setTextKeepState((CharSequence)localObject);
        }
      }
      else {
        mTextEditor.setText("");
      }
    }
  }
  
  protected void drawTopPanel()
  {
    showSubjectEditor(mWorkingMessage.hasSubject(false));
  }
  
  protected void enableAttachmentPanel()
  {
    getEditMessageFocus();
    mAttachmentProcessor.enableAttachmentPanel();
  }
  
  protected void exit()
  {
    if (!mWorkingMessage.isWorthSaving())
    {
      postExit();
      return;
    }
    if (willDiscardDraft())
    {
      MessageUtils.showDiscardDraftConfirmDialog(this, new DiscardDraftListener(null));
      return;
    }
    mToastForDraftSave = true;
    postExit();
  }
  
  public AttachmentView getAttachmentView()
  {
    return mAttachmentView;
  }
  
  protected int getBottomPanelHeight()
  {
    return mBottomPanel.getHeight();
  }
  
  protected abstract int getContentViewResId();
  
  public EditText getEditMessageFocus()
  {
    if ((mSubjectTextEditor != null) && (mSubjectTextEditor.hasFocus())) {
      return mSubjectTextEditor;
    }
    if (mTextEditor.hasFocus()) {
      return mTextEditor;
    }
    mTextEditor.requestFocus();
    return mTextEditor;
  }
  
  public Handler getHandler()
  {
    return mHandler;
  }
  
  public Activity getHostedActivity()
  {
    return this;
  }
  
  protected abstract ContactList getRecipients();
  
  public EditText getTextEditor()
  {
    return mTextEditor;
  }
  
  public WorkingMessage getWorkingMessage()
  {
    return mWorkingMessage;
  }
  
  protected boolean handleForwardedMessage()
  {
    Intent localIntent = getIntent();
    if (!localIntent.getBooleanExtra("forwarded_message", false)) {
      return false;
    }
    mOrgMsgIsPrivate = localIntent.getBooleanExtra("orig_message_is_private", false);
    Uri localUri = (Uri)localIntent.getParcelableExtra("msg_uri");
    localIntent.getIntExtra("mx2type", 0);
    if (Log.isLoggable("Mms:app", 3)) {
      Log.d("MessageEditableActivityBase", "handle forwarded message " + localUri);
    }
    if (localUri != null)
    {
      mWorkingMessage = WorkingMessage.load(this, localUri, true);
      mWorkingMessage.setSubject(localIntent.getStringExtra("subject"), false);
    }
    for (;;)
    {
      return true;
      mWorkingMessage.setText(localIntent.getStringExtra("sms_body"));
    }
  }
  
  protected void handleIntent(Intent paramIntent)
  {
    mWorkingMessage = WorkingMessage.createEmpty(this);
    if (!handleForwardedMessage()) {
      loadDraft();
    }
    String str = getBody(paramIntent.getData());
    if (!TextUtils.isEmpty(str)) {
      mWorkingMessage.setText(str);
    }
    str = paramIntent.getStringExtra("sms_body");
    if (!TextUtils.isEmpty(str)) {
      mWorkingMessage.setText(str);
    }
    str = paramIntent.getStringExtra("subject");
    if (!TextUtils.isEmpty(str)) {
      mWorkingMessage.setSubject(str, false);
    }
    mExitOnSent = paramIntent.getBooleanExtra("exit_on_sent", false);
    mWorkingMessage.setConversation(mConversation);
    handleSendIntent(paramIntent);
    handleSendToIntent(paramIntent);
  }
  
  protected void hideSoftKeyboard()
  {
    View localView = mMsgContentView.findFocus();
    Object localObject = localView;
    if (localView == null) {
      localObject = mContentView;
    }
    mInputMethodManager.hideSoftInputFromWindow(((View)localObject).getWindowToken(), 0);
  }
  
  protected void initBottomPanelResourceRefs()
  {
    mBottomPanel = findViewById(2131820687);
    mEditorContainer = findViewById(2131820784);
    mTextEditor = ((EditText)findViewById(2131820693));
    mTextEditor.addTextChangedListener(mTextEditorWatcher);
    mTextEditor.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 4)
        {
          if (isPreparedForSending())
          {
            sendMessage(mUseSlotId);
            return true;
          }
          Toast.makeText(MessageEditableActivityBase.this, 2131361865, 1).show();
        }
        return false;
      }
    });
    mTextEditor.setTextColor(AttributeResolver.resolveColor(this, 16843601));
    mTextEditor.setHintTextColor(getResources().getColor(2131296311));
    if (Build.IS_CM_CUSTOMIZATION_TEST)
    {
      InputFilter.LengthFilter localLengthFilter = new InputFilter.LengthFilter(3101);
      mTextEditor.setFilters(new InputFilter[] { localLengthFilter });
    }
    mTextCounter = ((TextView)findViewById(2131820691));
    if (MSimUtils.isMSim())
    {
      mSendSlotIdView = ((ImageView)mEditorContainer.findViewById(2131820793));
      findViewById(2131820695).setVisibility(0);
      mSimButtonContainer = findViewById(2131820696);
      mSendButton1 = findViewById(2131820698);
      mSendButton1.setOnClickListener(this);
      mSendButton1Text = ((TextView)findViewById(2131820700));
      mSendButton1Image = ((ImageView)findViewById(2131820699));
      mSendButton2 = findViewById(2131820701);
      mSendButton2.setOnClickListener(this);
      mSendButton2Text = ((TextView)findViewById(2131820703));
      mSendButton2Image = ((ImageView)findViewById(2131820702));
      mSendSlotIdView.setOnClickListener(this);
      mBackupSimId1 = MSimUtils.getSimIdBySlotId(0);
    }
    for (mBackupSimId2 = MSimUtils.getSimIdBySlotId(1);; mBackupSimId2 = -1L)
    {
      updateSlotRelatedState();
      mSendButton = findViewById(2131820692);
      mSendButton.setOnClickListener(this);
      mSendButton.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if ((mMxStyle) && (!mAirModeOn) && (!MessageEditableActivityBase.this.hasMessage()) && (mAudioController != null) && (!mAudioController.isRecording())) {
            switch (paramAnonymousMotionEvent.getAction())
            {
            }
          }
          for (;;)
          {
            return false;
            if (mSimButtonContainer != null) {
              mSimButtonContainer.setVisibility(8);
            }
            mWorkingMessage.syncWorkingRecipients();
            mAudioController.setThreadId(mConversation.ensureThreadId());
            mAudioController.setConversation(mConversation);
            mBtnTouch.setEvent(paramAnonymousMotionEvent);
            mHandler.postDelayed(mBtnTouch, 200L);
            continue;
            if (!mBtnTouch.isRunning())
            {
              mHandler.removeCallbacks(mBtnTouch);
              mAudioController.showRemind();
            }
          }
        }
      });
      mAttachmentView = ((AttachmentView)findViewById(2131820790));
      mAttachmentView.setHandler(new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          mAttachmentProcessor.handleAttachentMessage(paramAnonymousMessage);
        }
      });
      mAudioNameView = ((TextView)findViewById(2131820621));
      mAttachmentView.setAudioNameView(mAudioNameView);
      mSwitchBtn = ((Button)findViewById(2131820690));
      mSwitchBtn.setOnClickListener(this);
      mIsSwitchMenuBtn = false;
      mTimedMsgIndicator = ((ImageButton)findViewById(2131820787));
      mTimedMsgIndicator.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          setTiming();
        }
      });
      mTimedDescView = ((TextView)findViewById(2131820788));
      findViewById(2131820792).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (isVisible(mTextEditor))
          {
            mTextEditor.requestFocus();
            mTextEditor.setSelection(0);
            showSoftKeyboard();
          }
        }
      });
      return;
      mBackupSimId1 = -1L;
    }
  }
  
  protected void initResourceRefs()
  {
    mHistoryView = ((FrameLayout)findViewById(2131820760));
    initBottomPanelResourceRefs();
    mMessageContentPanel = findViewById(2131820786);
    mHomeButton = ((Button)findViewById(2131820651));
    if (mHomeButton != null) {
      mHomeButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          exit();
        }
      });
    }
    mContentParent = getContentParent();
  }
  
  protected void initialize()
  {
    initializeBottomPanel();
    if (getResourcesgetConfigurationorientation == 2) {}
    for (boolean bool = true;; bool = false)
    {
      mIsLandscape = bool;
      return;
    }
  }
  
  protected void initializeBottomPanel()
  {
    handleIntent(getIntent());
    if (MSimUtils.isMSimInserted())
    {
      int i = MSimUtils.getSlotIdBySimInfoId(mConversation.getLastSimId());
      if (MSimUtils.isMSimSlotIdValid(i)) {
        mUseSlotId = i;
      }
      updateSlotButtonStateBySlotId(this, mUseSlotId);
    }
    drawTopPanel();
    drawBottomPanel();
    mAttachmentView.update(mWorkingMessage);
  }
  
  void insertChenghu()
  {
    int i = mTextEditor.getSelectionStart();
    mTextEditor.getText().insert(i, String.valueOf(65535));
    mTextEditor.setSelection(i + 1);
    startNicknamePicker(false);
    showSoftKeyboard();
  }
  
  public void insertPhraseSms(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    int i = mTextEditor.getSelectionStart();
    mTextEditor.getText().insert(i, paramString);
    showSoftKeyboard();
  }
  
  protected boolean isAttachmentPanelVisible()
  {
    return mAttachmentProcessor.isAttachmentPanelVisible();
  }
  
  public boolean isMx2AudioAvailable()
  {
    return getRecipients().size() == mMxAudioRecipients.size();
  }
  
  protected boolean isMxV2Available()
  {
    return isContain(getRecipients(), mMxV2Recipients);
  }
  
  public abstract boolean isPreparedForSending();
  
  protected boolean isSubjectEditorVisible()
  {
    return (mSubjectTextEditor != null) && (mSubjectTextEditor.getVisibility() == 0);
  }
  
  protected boolean isVisible(View paramView)
  {
    return (paramView != null) && (paramView.getVisibility() != 8);
  }
  
  protected void loadDraft()
  {
    if (!mConversation.hasDraft()) {
      return;
    }
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("MessageEditableActivityBase", "loadDraft: call WorkingMessage.loadDraft");
    }
    mWorkingMessage = WorkingMessage.loadDraft(this, mConversation);
    updateCounter();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    int i = 0;
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    mWaitingForSubActivity = false;
    if (mWorkingMessage.isFakeMmsForDraft()) {
      mWorkingMessage.removeFakeMmsForDraft();
    }
    if (paramInt1 == 1)
    {
      if (paramIntent.getBooleanExtra("exit_ecm_result", false)) {
        sendMessage(false, mSavedSlotIdForECM);
      }
      mSavedSlotIdForECM = -1;
    }
    do
    {
      for (;;)
      {
        return;
        if ((paramInt1 != 3) || (paramIntent == null)) {
          break;
        }
        String[] arrayOfString = paramIntent.getStringArrayExtra("numbers");
        paramIntent = paramIntent.getStringArrayExtra("names");
        if ((arrayOfString != null) && (paramIntent != null) && (arrayOfString.length == paramIntent.length))
        {
          paramInt1 = i;
          while (paramInt1 < arrayOfString.length)
          {
            String str1 = arrayOfString[paramInt1];
            String str2 = paramIntent[paramInt1];
            Contact.get(str1).setNickname(str2);
            paramInt1 += 1;
          }
        }
      }
      if (paramInt1 != 4) {
        break;
      }
      mTimedMsgIndicator.setClickable(true);
    } while (paramInt2 != -1);
    long l = paramIntent.getLongExtra(DateTimePickerActivity.FIELD_TIME, -1L);
    if (l != -1L)
    {
      mTimedMsgIndicator.setVisibility(0);
      mWorkingMessage.setTimeToSend(l);
      mTimedDescView.setVisibility(0);
      paramIntent = mWorkingMessage.formatDateTime(this, l);
      mWorkingMessage.setTimeToSendDesc(paramIntent);
      mTimedDescView.setText(paramIntent);
    }
    postSwitchMsgType();
    return;
    mAttachmentProcessor.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onAttachmentChanged()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        mAttachmentView.update(mWorkingMessage);
        mAttachmentProcessor.updateAttachmentTypeStates();
        drawBottomPanel();
      }
    });
  }
  
  public void onAttachmentError(final int paramInt, final Uri paramUri, final boolean paramBoolean)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        mAttachmentProcessor.handleAddAttachmentError(paramInt, 2131361995);
        if (getWindow().isDestroyed()) {}
        while ((!paramBoolean) || (mWorkingMessage == null)) {
          return;
        }
        mWorkingMessage.loadFromUri(paramUri, false);
        mAttachmentView.update(mWorkingMessage);
        drawTopPanel();
        drawBottomPanel();
        mAttachmentProcessor.updateAttachmentTypeStates();
      }
    });
  }
  
  public void onBackPressed()
  {
    if (mAttachmentView.onBackPressed()) {
      return;
    }
    if (isAttachmentPanelVisible())
    {
      if (!mAttachmentProcessor.isOnAttachmentPanel())
      {
        mAttachmentProcessor.gotoAttachmentPanel(true);
        return;
      }
      disableAttachmentPanel();
      return;
    }
    exit();
  }
  
  protected void onBottomPanelStop()
  {
    saveDraft(true);
    AudioTalkMediaPlayer localAudioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance(MmsApp.getApp());
    if (localAudioTalkMediaPlayer.isPlaying()) {
      localAudioTalkMediaPlayer.stop();
    }
    if (mAudioController != null) {
      mAudioController.release();
    }
  }
  
  protected void onBottomPanelUpdate()
  {
    if (mWorkingMessage.isDiscarded())
    {
      mConversation.ensureThreadId();
      mWorkingMessage.unDiscard();
    }
    mMxEnabled = MxActivateService.isMxEnabled(this);
    if (!mMxEnabled)
    {
      int i = 0;
      while (i < mLastMxMmsTime.length)
      {
        mLastMxMmsTime[i] = 0L;
        i += 1;
      }
      i = 0;
      while (i < mLastMxSmsTime.length)
      {
        mLastMxSmsTime[i] = 0L;
        i += 1;
      }
    }
  }
  
  protected abstract void onChildSimInfoChanged();
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      do
      {
        do
        {
          return;
        } while (((Build.IS_CM_CUSTOMIZATION_TEST) && (recipientsOverSize())) || (!isPreparedForSending()));
        if ((mSimButtonContainer != null) && (mSimButtonContainer.getVisibility() == 0)) {
          mSimButtonContainer.setVisibility(8);
        }
        sendMessage(mUseSlotId);
        return;
        mUseSlotId = 0;
        updateSlotButtonStateBySlotId(this, mUseSlotId);
        postSwitchMsgType();
        return;
        mUseSlotId = 1;
        updateSlotButtonStateBySlotId(this, mUseSlotId);
        postSwitchMsgType();
        return;
        if (!isAttachmentPanelVisible())
        {
          enableAttachmentPanel();
          hideSoftKeyboard();
          return;
        }
      } while ((!mWorkingMessage.hasSlideshow()) && (showSoftKeyboard()));
      disableAttachmentPanel();
      return;
    } while (mSimButtonContainer == null);
    if (mSimButtonContainer.getVisibility() == 8)
    {
      mSimButtonContainer.setVisibility(0);
      return;
    }
    mSimButtonContainer.setVisibility(8);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (orientation == 2) {}
    for (boolean bool = true;; bool = false)
    {
      if (mIsLandscape != bool)
      {
        mIsLandscape = bool;
        if (mAttachmentView != null) {
          mAttachmentView.update(mWorkingMessage);
        }
      }
      return;
    }
  }
  
  protected void onContactAdded(Contact paramContact)
  {
    paramContact = paramContact.getMxPhoneNumber();
    mPendingRecipients.put(paramContact, PRESENCE);
    if (mMxEnabled)
    {
      mQueryStatusHandler.obtainMessage(1, paramContact).sendToTarget();
      return;
    }
    postSwitchMsgType();
  }
  
  protected void onContactRemoved(Contact paramContact)
  {
    paramContact = Contact.normalizePhoneNumber(paramContact.getMxPhoneNumber());
    mPendingRecipients.remove(paramContact);
    mMxMmsRecipients.remove(paramContact);
    mMxSmsRecipients.remove(paramContact);
    mMxAudioRecipients.remove(paramContact);
    mMxV2Recipients.remove(paramContact);
    postSwitchMsgType();
  }
  
  protected void onContactStatusUpdate(String paramString) {}
  
  protected void onContactsUpdated(ContactList paramContactList) {}
  
  protected void onCreate(Bundle paramBundle)
  {
    if (getIntent().getBooleanExtra("showUI", false)) {
      getWindow().addFlags(4194304);
    }
    super.onCreate(paramBundle);
    MessageUtils.loadFontSizeConfiguration(this);
    mSoftKeyboardMinHeight = getResources().getDimensionPixelSize(2131427343);
    mMessageContentPanelMinHeight = getResources().getDimensionPixelSize(2131427488);
    setContentView(getContentViewResId());
    mContentView = ((FrameLayout)findViewById(16908290));
    mMsgContentView = ((SizeAwareLinearLayout)mContentView.findViewById(2131820560));
    mMsgContentView.setOnMeasureListener(this);
    mLastMxMmsTime = new long[2];
    mLastMxSmsTime = new long[2];
    mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    mAttachmentProcessor = new AttachmentProcessor(this);
    mInputMethodManager = ((InputMethodManager)getSystemService("input_method"));
    mDefaultCursorDrawableRes = MessageUtils.resolveDefaultCursorDrawableRes(this);
    mContactPanel = mContentView.findViewById(2131820798);
    initResourceRefs();
    setOnStatusBarChangeListener(new OnStatusBarChangeListener()
    {
      public void onStatusBarHeightChange(int paramAnonymousInt)
      {
        if (mStatusBarHeight == paramAnonymousInt) {
          return;
        }
        mContactPanel.setPadding(mContactPanel.getPaddingLeft(), mContactPanel.getPaddingTop() - mStatusBarHeight + paramAnonymousInt, mContactPanel.getPaddingRight(), mContactPanel.getPaddingBottom());
        mStatusBarHeight = paramAnonymousInt;
      }
    });
    MxIdCache.addStatusListener(this);
    mMxEnabled = MxActivateService.isMxEnabled(this);
    mQueryStatusWorkThread = new HandlerThread("MX status query thread", 10);
    mQueryStatusWorkThread.start();
    mQueryStatusHandler = new QueryMxStatusHandler(mQueryStatusWorkThread.getLooper(), null);
    paramBundle = new IntentFilter();
    paramBundle.addAction("com.xiaomi.mms.PUSH_STATUS_CHANGED");
    paramBundle.addAction("android.intent.action.AIRPLANE_MODE");
    registerReceiver(mBroadcastReceiver, paramBundle);
    registerSimRelatedListener();
    if (Settings.System.getInt(getContentResolver(), "airplane_mode_on", 0) == 1) {}
    for (boolean bool = true;; bool = false)
    {
      mAirModeOn = bool;
      return;
    }
  }
  
  protected void onDestroy()
  {
    mQueryStatusWorkThread.quit();
    unregisterReceiver(mBroadcastReceiver);
    MxIdCache.removeStatusListener(this);
    unRegisterSimRelatedListener();
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    MessageUtils.launchMessagePreference(this);
    return true;
  }
  
  public void onMaxPendingMessagesReached()
  {
    saveDraft(false);
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MessageEditableActivityBase.access$902(MessageEditableActivityBase.this, false);
        if (mMaxPendingMessagesReachedToast == null) {
          MessageEditableActivityBase.access$1002(MessageEditableActivityBase.this, Toast.makeText(MessageEditableActivityBase.this, 2131362036, 1));
        }
        mMaxPendingMessagesReachedToast.show();
      }
    });
  }
  
  public void onMessageSent()
  {
    if (getWindow().isDestroyed()) {
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (mExitOnSent) {
          finish();
        }
      }
    });
  }
  
  public void onMxIdAdded(String paramString1, String paramString2) {}
  
  public void onMxIdOffline(String paramString1, String paramString2)
  {
    if (!mMxEnabled)
    {
      Log.w("MessageEditableActivityBase", "mx id offline, but mx disabled");
      return;
    }
    mQueryStatusHandler.obtainMessage(3, paramString2).sendToTarget();
  }
  
  public void onMxIdOnline(String paramString1, String paramString2)
  {
    if (!mMxEnabled)
    {
      Log.w("MessageEditableActivityBase", "mx id online, but mx disabled");
      return;
    }
    mQueryStatusHandler.obtainMessage(3, paramString2).sendToTarget();
    onContactStatusUpdate(paramString2);
  }
  
  public void onPostLayout() {}
  
  public void onPreMeasure(SizeAwareLinearLayout paramSizeAwareLinearLayout, int paramInt1, int paramInt2)
  {
    mTextEditor.setMinHeight(0);
    mBottomPanel.measure(paramInt1, 0);
    int i;
    int j;
    boolean bool;
    label125:
    label205:
    label293:
    View localView;
    if ((mTextEditor.getLineCount() <= 2) && (!mWorkingMessage.hasAttachment()) && (mWorkingMessage.getSubject() == null))
    {
      mTextCounter.setVisibility(8);
      paramInt2 = mMessageContentPanelMinHeight - mMessageContentPanel.getMeasuredHeight();
      if (paramInt2 > 0) {
        mTextEditor.setMinHeight(paramInt2 + mTextEditor.getMeasuredHeight());
      }
      paramSizeAwareLinearLayout = (ViewGroup.MarginLayoutParams)mContentParent.getLayoutParams();
      i = topMargin;
      j = bottomMargin;
      if (j < mSoftKeyboardMinHeight) {
        break label387;
      }
      bool = true;
      if (bool != mIsSoftInputEnabled) {
        onSoftInputStateChange(bool);
      }
      if ((bool) && (!mIsSoftInputEnabled) && (isAttachmentPanelVisible())) {
        disableAttachmentPanel();
      }
      if ((mIsLandscape) && (bool)) {
        mAttachmentView.dismissPopup();
      }
      if (!mIsSwitchMenuBtn)
      {
        paramSizeAwareLinearLayout = mSwitchBtn;
        if (!isAttachmentPanelVisible()) {
          break label393;
        }
        paramInt2 = 2130837786;
        paramSizeAwareLinearLayout.setBackgroundResource(paramInt2);
      }
      mIsSoftInputEnabled = bool;
      paramSizeAwareLinearLayout = getWindow().getWindowManager().getDefaultDisplay();
      paramInt2 = paramSizeAwareLinearLayout.getHeight();
      int k = mContentView.getPaddingTop();
      int m = mTopPlaceholderHeight;
      j = mAttachmentProcessor.getAttachmentPanelHeight(paramInt1, j);
      View[] arrayOfView = new View[1];
      arrayOfView[0] = mBottomPanel;
      j = paramInt2 - k - i - m - j;
      i = 0;
      if (i >= arrayOfView.length) {
        break label428;
      }
      localView = arrayOfView[i];
      paramInt2 = j;
      if (isVisible(localView))
      {
        localView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(Integer.MIN_VALUE, paramSizeAwareLinearLayout.getHeight()));
        if (j >= localView.getMeasuredHeight()) {
          break label400;
        }
        localView.setLayoutParams(new LinearLayout.LayoutParams(-1, j, 0.0F));
      }
    }
    for (paramInt2 = 0;; paramInt2 = j - localView.getMeasuredHeight())
    {
      i += 1;
      j = paramInt2;
      break label293;
      mTextCounter.setVisibility(0);
      break;
      label387:
      bool = false;
      break label125;
      label393:
      paramInt2 = 2130837790;
      break label205;
      label400:
      localView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 0.0F));
    }
    label428:
    mHistoryView.setLayoutParams(new LinearLayout.LayoutParams(-1, j, 0.0F));
  }
  
  public void onPreMessageSent()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        resetMessageWithAnimation();
      }
    });
  }
  
  public void onProtocolChanged(final boolean paramBoolean)
  {
    if (mAudioController == null) {}
    for (final boolean bool = false;; bool = mAudioController.isSendingMx2())
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (!bool) {
            MessageEditableActivityBase.this.toastConvertInfo(paramBoolean);
          }
          MessageEditableActivityBase.this.updateCounter(paramBoolean);
          postSwitchMsgType();
          mAttachmentProcessor.updateAttachmentTypeStates();
          if (bool) {
            mAudioController.resetIsSendingMx2();
          }
        }
      });
      return;
    }
  }
  
  protected void onPushStatusChanged()
  {
    mQueryStatusHandler.obtainMessage(2).sendToTarget();
  }
  
  protected void onResetMessageAnimationEnd() {}
  
  protected void onResetMessageAnimationStart() {}
  
  protected void onRestart()
  {
    super.onRestart();
    onBottomPanelUpdate();
  }
  
  protected void onSoftInputStateChange(boolean paramBoolean) {}
  
  protected void onStart()
  {
    super.onStart();
    mAllowAnimation = allowAnimation();
    if (mWorkingMessage != null) {
      mWorkingMessage.syncWorkingRecipients();
    }
    updateContactNames();
    SmsTextSizeAdjust.getInstance().init(this, this);
    SmsTextSizeAdjust.getInstance().refresh();
  }
  
  protected void onStop()
  {
    onBottomPanelStop();
    cancelUpdateContactNames();
    if ((mTaskDialog != null) && (mTaskDialog.isShowing())) {
      mTaskDialog.dismiss();
    }
    mTaskDialog = null;
    SmsTextSizeAdjust.clear(this);
    super.onStop();
  }
  
  protected void postExit()
  {
    finish();
  }
  
  protected void postSwitchMsgType()
  {
    if (mHandler != null)
    {
      mHandler.removeCallbacks(mSwitchMsgTypeRunnable);
      mHandler.postDelayed(mSwitchMsgTypeRunnable, 10L);
      return;
    }
    Log.v("MessageEditableActivityBase", "postSwitchMsgType mHandler is null");
  }
  
  public void postUpdateSendButtonState()
  {
    if (mHandler != null)
    {
      mHandler.removeCallbacks(mPostUpdateSendButtonStateRunnable);
      mHandler.postDelayed(mPostUpdateSendButtonStateRunnable, 10L);
      return;
    }
    Log.v("MessageEditableActivityBase", "postUpdateSendButtonState mHandler is null");
  }
  
  protected void registerSimRelatedListener()
  {
    if (MSimUtils.isMSim())
    {
      MSimUtils.registerChangeListener(this, mSimInfoChangeListener);
      return;
    }
    MmsSystemEventReceiver.getInstance().registerSimStateChangedListener(mSimStateChagnedListener);
  }
  
  public void resetMessageWithAnimation()
  {
    onResetMessageAnimationStart();
    Animation localAnimation = AnimationUtils.loadAnimation(getBaseContext(), 2131034117);
    if (!mAllowAnimation) {
      localAnimation.setDuration(0L);
    }
    localAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        int i = mMessageContentPanel.getHeight();
        MessageEditableActivityBase.this.resetMessage();
        mMessageContentPanel.getLayoutParams().height = i;
        float f1 = i;
        float f2 = mMessageContentPanelMinHeight;
        if (mAllowAnimation) {}
        for (long l = 100L;; l = 0L)
        {
          LinearAnimator.start(f1, f2, l, new LinearAnimator.FrameHandler()
          {
            public void onEnd()
            {
              mMessageContentPanel.getLayoutParams().height = -2;
              mMessageContentPanel.requestLayout();
              mMessageContentPanel.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
              {
                public boolean onPreDraw()
                {
                  onResetMessageAnimationEnd();
                  mMessageContentPanel.getViewTreeObserver().removeOnPreDrawListener(this);
                  return true;
                }
              });
            }
            
            public void onFrame(float paramAnonymous2Float)
            {
              mMessageContentPanel.getLayoutParams().height = ((int)paramAnonymous2Float);
              mMessageContentPanel.requestLayout();
            }
          });
          return;
        }
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
    findViewById(2131820786).startAnimation(localAnimation);
  }
  
  protected void saveDraft(boolean paramBoolean)
  {
    if (mWorkingMessage.isDiscarded()) {}
    do
    {
      return;
      if ((!mWaitingForSubActivity) && (!mWorkingMessage.isWorthSaving()))
      {
        if (Log.isLoggable("Mms:app", 2)) {
          Log.v("MessageEditableActivityBase", "saveDraft: not worth saving, discard WorkingMessage and bail");
        }
        mWorkingMessage.discard();
        return;
      }
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("MessageEditableActivityBase", "saveDraft: call WorkingMessage.saveDraft");
      }
      mWorkingMessage.saveDraft(paramBoolean);
    } while (!mToastForDraftSave);
    Toast.makeText(this, 2131361872, 0).show();
  }
  
  public abstract void sendMessage(int paramInt);
  
  public void setTaskDialog(Dialog paramDialog)
  {
    mTaskDialog = paramDialog;
  }
  
  public void setTextSize(float paramFloat)
  {
    if (mTextEditor != null) {
      mTextEditor.setTextSize(0, paramFloat);
    }
    if (mSubjectTextEditor != null) {
      mSubjectTextEditor.setTextSize(0, paramFloat);
    }
  }
  
  public void setTiming()
  {
    long l2 = mWorkingMessage.getTimeToSend();
    long l1 = l2;
    if (l2 == 0L) {
      l1 = System.currentTimeMillis() + 600000L;
    }
    mTimedMsgIndicator.setClickable(false);
    Intent localIntent = new Intent(this, DateTimePickerActivity.class);
    localIntent.setType(DateTimePickerActivity.CONTENT_TYPE);
    localIntent.putExtra(DateTimePickerActivity.FIELD_TIME, l1);
    localIntent.putExtra(DateTimePickerActivity.FIELD_TITLE, getString(2131362152));
    startActivityForResult(localIntent, 4);
  }
  
  public void setTopPlaceholderHeight(int paramInt)
  {
    mTopPlaceholderHeight = paramInt;
    mMsgContentView.getLayoutParams()).topMargin = mTopPlaceholderHeight;
    mMsgContentView.requestLayout();
  }
  
  protected boolean showSoftKeyboard()
  {
    View localView = mContentView.findFocus();
    Object localObject = localView;
    if (localView == null)
    {
      if (!isVisible(mTextEditor)) {
        break label44;
      }
      mTextEditor.requestFocus();
      localObject = mTextEditor;
    }
    while (localObject == null)
    {
      return false;
      label44:
      localObject = localView;
      if (isVisible(mSubjectTextEditor))
      {
        mSubjectTextEditor.requestFocus();
        localObject = mSubjectTextEditor;
      }
    }
    mInputMethodManager.showSoftInput((View)localObject, 0);
    return true;
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    if (paramInt >= 0) {
      mWaitingForSubActivity = true;
    }
    try
    {
      super.startActivityForResult(paramIntent, paramInt);
      return;
    }
    catch (ActivityNotFoundException paramIntent)
    {
      Toast.makeText(this, getString(2131362137), 0).show();
    }
  }
  
  public void startNicknamePicker(boolean paramBoolean)
  {
    mWorkingMessage.syncWorkingRecipients();
    if ((paramBoolean) || (hasRecipientsToRevise()))
    {
      Object localObject2 = mConversation.getRecipients();
      if (((ContactList)localObject2).size() > 0)
      {
        localObject1 = new String[((ContactList)localObject2).size()];
        int i = 0;
        while (i < ((ContactList)localObject2).size())
        {
          localObject1[i] = ((Contact)((ContactList)localObject2).get(i)).getNumber();
          i += 1;
        }
        localObject2 = new Intent("android.intent.action.PICK");
        ((Intent)localObject2).setType("vnd.android.cursor.item/nickname");
        ((Intent)localObject2).setPackage(MessageUtils.getContactsPackageName());
        ((Intent)localObject2).putExtra("numbers", (String[])localObject1);
        ((Intent)localObject2).putExtra("android.intent.extra.TEXT", getString(2131362049));
        startActivityForResult((Intent)localObject2, 3);
        updateNicknameRevisedNumbers();
      }
    }
    else
    {
      return;
    }
    Object localObject1 = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject1).setTitle(2131362049);
    ((AlertDialog.Builder)localObject1).setIconAttribute(16843605);
    if (paramBoolean) {
      ((AlertDialog.Builder)localObject1).setMessage(2131362050);
    }
    for (;;)
    {
      ((AlertDialog.Builder)localObject1).setPositiveButton(17039370, null);
      ((AlertDialog.Builder)localObject1).show();
      return;
      ((AlertDialog.Builder)localObject1).setMessage(2131362051);
    }
  }
  
  public void toggleSubject()
  {
    if (!isSubjectEditorVisible())
    {
      mWorkingMessage.setSubject("", true);
      showSubjectEditor(true);
      mSubjectTextEditor.requestFocus();
      hideAudioViews();
    }
    for (;;)
    {
      postSwitchMsgType();
      return;
      mWorkingMessage.setSubject(null, true);
      showSubjectEditor(false);
      mTextEditor.requestFocus();
    }
  }
  
  protected void unRegisterSimRelatedListener()
  {
    if (MSimUtils.isMSim())
    {
      MSimUtils.unregisterChangeListener(this, mSimInfoChangeListener);
      return;
    }
    MmsSystemEventReceiver.getInstance().unRegisterSimStateChangedListener(mSimStateChagnedListener);
  }
  
  public void updateSendButtonState()
  {
    if ((mMxStyle) && (!mAirModeOn) && (MSimUtils.isMSimSlotIdValid(mUseSlotId)) && (!hasMessage()) && (!isB2cMessageActivity()))
    {
      showAudioViews();
      return;
    }
    hideAudioViews();
  }
  
  protected void updateSlotButtonInfo()
  {
    String str = MSimUtils.getSimDisplayName(this, 0);
    if (!TextUtils.isEmpty(str)) {
      mSendButton1Text.setText(str);
    }
    str = MSimUtils.getSimDisplayName(this, 1);
    if (!TextUtils.isEmpty(str)) {
      mSendButton2Text.setText(str);
    }
  }
  
  protected void updateSlotButtonStateBySlotId(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      mSendSlotIdView.setBackgroundResource(2130837880);
      mSimButtonContainer.setVisibility(0);
      mSendButton1Text.setTextAppearance(paramContext, 2131689559);
      mSendButton2Text.setTextAppearance(paramContext, 2131689559);
      mSendButton1Image.setImageResource(2130837970);
      mSendButton2Image.setImageResource(2130837971);
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
  
  protected void updateSlotRelatedState()
  {
    if (MSimUtils.isMSimInserted())
    {
      mUseSlotId = MSimUtils.getPreferredSmsSlotId();
      if (mSendSlotIdView != null) {
        mSendSlotIdView.setVisibility(0);
      }
      updateSlotButtonInfo();
    }
    do
    {
      return;
      mUseSlotId = MSimUtils.getInsertedSlotId();
      if (mSimButtonContainer != null) {
        mSimButtonContainer.setVisibility(8);
      }
    } while (mSendSlotIdView == null);
    mSendSlotIdView.setVisibility(8);
  }
  
  protected abstract boolean willDiscardDraft();
  
  private class DiscardDraftListener
    implements DialogInterface.OnClickListener
  {
    private DiscardDraftListener() {}
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      mWorkingMessage.discard();
      paramDialogInterface.dismiss();
      finish();
    }
  }
  
  private class QueryMxStatusHandler
    extends Handler
  {
    private QueryMxStatusHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      Object localObject;
      switch (what)
      {
      default: 
        return;
      case 1: 
      case 3: 
      case 4: 
        String str = (String)obj;
        MxIdCache.MxIdCacheItem localMxIdCacheItem = MxIdCache.getOrQuery(MessageEditableActivityBase.this, str, false);
        localObject = new Bundle();
        if (localMxIdCacheItem != null) {
          ((Bundle)localObject).putLong("capability", localMxIdCacheItem.getCapability());
        }
        paramMessage = mHandler.obtainMessage(what, str);
        paramMessage.setData((Bundle)localObject);
        paramMessage.sendToTarget();
        return;
      case 2: 
        mQueryStatusHandler.removeMessages(2);
        paramMessage = mPendingRecipients.entrySet().iterator();
        while (paramMessage.hasNext())
        {
          localObject = (String)((Map.Entry)paramMessage.next()).getKey();
          mQueryStatusHandler.obtainMessage(4, localObject).sendToTarget();
        }
        mQueryStatusHandler.obtainMessage(5).sendToTarget();
        return;
      }
      mHandler.obtainMessage(5).sendToTarget();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */