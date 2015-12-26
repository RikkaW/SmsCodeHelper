/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Dialog
 *  android.content.ActivityNotFoundException
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Configuration
 *  android.content.res.MiuiConfiguration
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.IBinder
 *  android.os.Looper
 *  android.os.Message
 *  android.os.Parcelable
 *  android.os.RemoteException
 *  android.os.ServiceManager
 *  android.os.SystemProperties
 *  android.preference.PreferenceManager
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.telephony.SmsMessage
 *  android.text.Editable
 *  android.text.InputFilter
 *  android.text.InputFilter$LengthFilter
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.text.method.TextKeyListener
 *  android.util.Log
 *  android.view.Display
 *  android.view.IWindowManager
 *  android.view.IWindowManager$Stub
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.View$OnKeyListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewParent
 *  android.view.ViewStub
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.AnimationUtils
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  android.widget.Toast
 *  com.google.android.collect.Sets
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.net.URLDecoder
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Map$Entry
 *  java.util.concurrent.ConcurrentHashMap
 *  miui.app.Activity
 *  miui.app.OnStatusBarChangeListener
 *  miui.os.Build
 *  miui.telephony.PhoneNumberUtils
 *  miui.telephony.PhoneNumberUtils$PhoneNumber
 *  miui.telephony.SubscriptionManager
 *  miui.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 *  miui.util.AttributeResolver
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.Display;
import android.view.IWindowManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.MmsConfig;
import com.android.mms.audio.AudioBtnTouchRunnable;
import com.android.mms.audio.AudioRecordingController;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.android.mms.model.ContactParser;
import com.android.mms.model.SlideshowModel;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.ui.AttachmentProcessor;
import com.android.mms.ui.AttachmentView;
import com.android.mms.ui.B2cMessageConversationActivity;
import com.android.mms.ui.DateTimePickerActivity;
import com.android.mms.ui.ISmsTextSizeAdjustHost;
import com.android.mms.ui.LinearAnimator;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SizeAwareLinearLayout;
import com.android.mms.ui.SmsTextSizeAdjust;
import com.android.mms.util.MSimUtils;
import com.google.android.collect.Sets;
import com.xiaomi.mms.data.MxCapability;
import com.xiaomi.mms.data.MxIdCache;
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
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import miui.app.OnStatusBarChangeListener;
import miui.os.Build;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.SubscriptionManager;
import miui.util.AttributeResolver;

public abstract class MessageEditableActivityBase
extends miui.app.Activity
implements View.OnClickListener,
WorkingMessage.MessageStatusListener,
ISmsTextSizeAdjustHost,
SizeAwareLinearLayout.OnMeasureListener,
MxIdCache.MxCacheStatusListener {
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
    private BroadcastReceiver mBroadcastReceiver;
    private AudioBtnTouchRunnable mBtnTouch;
    protected View mContactPanel;
    protected View mContentParent;
    protected FrameLayout mContentView;
    protected Conversation mConversation;
    private int mDefaultCursorDrawableRes;
    protected View mEditorContainer;
    private boolean mExitOnSent;
    protected Handler mHandler;
    protected FrameLayout mHistoryView;
    protected Button mHomeButton;
    protected InputMethodManager mInputMethodManager;
    protected boolean mIsLandscape;
    protected boolean mIsSoftInputEnabled = false;
    protected boolean mIsSwitchMenuBtn;
    private long[] mLastMxMmsTime;
    private long[] mLastMxSmsTime;
    private Toast mMaxPendingMessagesReachedToast;
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
    private Runnable mPostUpdateSendButtonStateRunnable;
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
    protected SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener;
    protected MmsSystemEventReceiver.SimStateChangedListener mSimStateChagnedListener;
    protected int mSoftKeyboardMinHeight;
    protected int mStatusBarHeight = 0;
    protected View mSubjectDivider;
    private final TextWatcher mSubjectEditorWatcher;
    private final View.OnKeyListener mSubjectKeyListener;
    protected EditText mSubjectTextEditor;
    protected Button mSwitchBtn;
    private Runnable mSwitchMsgTypeRunnable;
    protected Dialog mTaskDialog;
    protected TextView mTextCounter;
    protected EditText mTextEditor;
    protected final TextWatcher mTextEditorWatcher;
    protected TextView mTimedDescView;
    protected ImageButton mTimedMsgIndicator;
    protected boolean mToastForDraftSave;
    protected int mTopPlaceholderHeight;
    private AsyncTask<Void, Void, Boolean> mUpdateContactTask;
    protected int mUseSlotId;
    protected boolean mWaitingForSubActivity;
    protected WorkingMessage mWorkingMessage;

    public MessageEditableActivityBase() {
        this.mTextEditorWatcher = new TextWatcher(){
            private final long MX_STATUS_CHECK_DURATION;
            private long lastCheckTime;
            private int mOldEnd;
            private int mOldStart;
            private String mOldText;

            public void afterTextChanged(Editable object) {
                if (MessageEditableActivityBase.this.mWorkingMessage.requiresMms()) {
                    object = this.mOldText;
                    int n = this.mOldStart;
                    int n2 = this.mOldEnd;
                    MessageEditableActivityBase.this.confirmRemovingChenghu(null, new Runnable((String)object, n, n2){
                        final /* synthetic */ int val$oldEnd;
                        final /* synthetic */ int val$oldStart;
                        final /* synthetic */ String val$oldText;

                        @Override
                        public void run() {
                            MessageEditableActivityBase.this.mTextEditor.setText((CharSequence)this.val$oldText);
                            MessageEditableActivityBase.this.mTextEditor.setSelection(this.val$oldStart, this.val$oldEnd);
                        }
                    });
                }
                if (System.currentTimeMillis() - this.lastCheckTime > 10000) {
                    this.lastCheckTime = System.currentTimeMillis();
                    if (MessageEditableActivityBase.this.mConversation != null && (object = MessageEditableActivityBase.this.mConversation.getRecipients()) != null) {
                        MessageEditableActivityBase.this.postSwitchMsgType();
                        if (MxActivateService.isMxEnabled((Context)MessageEditableActivityBase.this)) {
                            object = object.iterator();
                            while (object.hasNext()) {
                                Contact contact = (Contact)object.next();
                                MxIdCache.getOrQuery((Context)MessageEditableActivityBase.this, contact.getMxPhoneNumber());
                            }
                        }
                    }
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                this.mOldText = MessageEditableActivityBase.this.mTextEditor.getText().toString();
                this.mOldStart = MessageEditableActivityBase.this.mTextEditor.getSelectionStart();
                this.mOldEnd = MessageEditableActivityBase.this.mTextEditor.getSelectionEnd();
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                if (Build.IS_CM_CUSTOMIZATION_TEST && charSequence.length() > 3100) {
                    Toast.makeText((Context)MessageEditableActivityBase.this, (CharSequence)MessageEditableActivityBase.this.getString(2131361850), (int)1).show();
                    MessageEditableActivityBase.this.mTextEditor.setText((CharSequence)this.mOldText);
                    return;
                }
                MessageEditableActivityBase.this.onUserInteraction();
                MessageEditableActivityBase.this.mWorkingMessage.setText(charSequence);
                if (MessageEditableActivityBase.this.updateCounter()) {
                    Toast.makeText((Context)MessageEditableActivityBase.this, (CharSequence)MessageEditableActivityBase.this.getString(2131361849), (int)1).show();
                    MessageEditableActivityBase.this.mWorkingMessage.setText(this.mOldText);
                    MessageEditableActivityBase.this.mTextEditor.setText((CharSequence)this.mOldText);
                    return;
                }
                MessageEditableActivityBase.this.postUpdateSendButtonState();
            }

        };
        this.mPostUpdateSendButtonStateRunnable = new Runnable(){

            @Override
            public void run() {
                Log.v((String)"MessageEditableActivityBase", (String)"run updateSendButtonState");
                MessageEditableActivityBase.this.updateSendButtonState();
            }
        };
        this.mMaxPendingMessagesReachedToast = null;
        this.mSubjectKeyListener = new View.OnKeyListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onKey(View view, int n, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0 || n != 67 || MessageEditableActivityBase.this.mSubjectTextEditor.length() != 0) {
                    return false;
                }
                MessageEditableActivityBase.this.showSubjectEditor(false);
                if (MessageEditableActivityBase.this.mTextEditor != null) {
                    MessageEditableActivityBase.this.mTextEditor.requestFocus();
                }
                MessageEditableActivityBase.this.mWorkingMessage.setSubject(null, true);
                return true;
            }
        };
        this.mSubjectEditorWatcher = new TextWatcher(){
            private boolean mIsEnglish;
            private boolean mNested;
            private CharSequence mOldText;
            private int mSelectionEnd;
            private Toast mToast;

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            private boolean isTooLong(CharSequence charSequence) {
                boolean bl = false;
                this.mIsEnglish = true;
                for (int i = 0; i < charSequence.length(); ++i) {
                    if (charSequence.charAt(i) <= '') continue;
                    this.mIsEnglish = false;
                }
                if (!this.mIsEnglish) {
                    if (charSequence.length() > 13) return true;
                }
                boolean bl2 = bl;
                if (!this.mIsEnglish) return bl2;
                bl2 = bl;
                if (charSequence.length() <= 40) return bl2;
                return true;
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public void afterTextChanged(Editable object) {
                if (this.mNested) {
                    return;
                }
                if (this.isTooLong((CharSequence)object)) {
                    int n;
                    int n2;
                    if (this.mIsEnglish) {
                        n = 2131362141;
                        n2 = 40;
                    } else {
                        n = 2131362140;
                        n2 = 13;
                    }
                    if (this.mToast == null) {
                        this.mToast = Toast.makeText((Context)MessageEditableActivityBase.this, (int)n, (int)1);
                    } else {
                        this.mToast.setText(n);
                    }
                    this.mToast.show();
                    this.mNested = true;
                    String string = "";
                    n = 0;
                    String string2 = string;
                    if (!TextUtils.isEmpty((CharSequence)this.mOldText)) {
                        void var6_6;
                        int n3 = this.mOldText.length();
                        n = n3 - this.mSelectionEnd;
                        if (n > 0) {
                            CharSequence charSequence = this.mOldText.subSequence(this.mSelectionEnd, n3);
                        }
                        string2 = var6_6;
                        if (TextUtils.isEmpty((CharSequence)var6_6)) {
                            string2 = "";
                            n = 0;
                        }
                    }
                    n = n2 - n;
                    object = object.subSequence(0, n);
                    MessageEditableActivityBase.this.mSubjectTextEditor.setText((CharSequence)(object.toString() + string2.toString()));
                    MessageEditableActivityBase.this.mSubjectTextEditor.setSelection(n);
                    this.mNested = false;
                }
                if (!MessageEditableActivityBase.this.isSubjectEditorVisible()) return;
                MessageEditableActivityBase.this.mWorkingMessage.setSubject((CharSequence)MessageEditableActivityBase.this.mSubjectTextEditor.getText(), true);
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                if (this.mNested) {
                    return;
                }
                this.mOldText = charSequence.subSequence(0, charSequence.length());
                this.mSelectionEnd = n + n2;
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }
        };
        this.mSwitchMsgTypeRunnable = new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                boolean bl;
                Object object;
                Log.v((String)"MessageEditableActivityBase", (String)"run mSwitchMsgTypeRunnable");
                ContactList contactList = MessageEditableActivityBase.this.getRecipients();
                if (contactList == null) {
                    Log.v((String)"MessageEditableActivityBase", (String)"mSwitchMsgTypeRunnable recipients is null");
                    return;
                }
                boolean bl2 = MessageEditableActivityBase.this.mWorkingMessage.requiresMms();
                MessageEditableActivityBase.this.mMxStyle = false;
                boolean bl3 = false;
                boolean bl4 = false;
                if (MSimUtils.isSimInserted(0)) {
                    bl3 = MessageEditableActivityBase.this.isMxAvailable(0);
                }
                if (MSimUtils.isSimInserted(1)) {
                    bl4 = MessageEditableActivityBase.this.isMxAvailable(1);
                }
                if (MessageEditableActivityBase.this.isB2cMessageActivity()) {
                    MessageEditableActivityBase.this.mMxStyle = true;
                } else if (MSimUtils.isMSimInserted()) {
                    object = MessageEditableActivityBase.this;
                    bl = bl3 && bl4 || MessageEditableActivityBase.this.mUseSlotId == 0 && bl3 || MessageEditableActivityBase.this.mUseSlotId == 1 && bl4;
                    object.mMxStyle = bl;
                } else if (MSimUtils.isSimInserted(0)) {
                    MessageEditableActivityBase.this.mMxStyle = bl3;
                } else if (MSimUtils.isSimInserted(1)) {
                    MessageEditableActivityBase.this.mMxStyle = bl4;
                }
                int n = MiuiConfiguration.getScaleMode() == 11 ? 1 : 0;
                if (MessageEditableActivityBase.this.isB2cMessageActivity()) {
                    MessageEditableActivityBase.this.mEditorContainer.setBackgroundResource(2130837588);
                    if (MessageEditableActivityBase.this.mSendButton != null) {
                        MessageEditableActivityBase.this.mSendButton.setBackgroundResource(2130837941);
                    }
                    MessageEditableActivityBase.this.mTextEditor.setCursorDrawableRes(MessageEditableActivityBase.this.mDefaultCursorDrawableRes);
                    bl = MxActivateService.isMxEnabled(MessageEditableActivityBase.this.getApplicationContext(), 0);
                    boolean bl5 = MxActivateService.isMxEnabled(MessageEditableActivityBase.this.getApplicationContext(), 1);
                    boolean bl6 = MSimUtils.isMSimSlotIdValid(0) && PushSession.getInstance(MessageEditableActivityBase.this.getApplicationContext()).isConnected(0);
                    boolean bl7 = MSimUtils.isMSimSlotIdValid(1) && PushSession.getInstance(MessageEditableActivityBase.this.getApplicationContext()).isConnected(1);
                    bl6 = bl && bl5 && bl6 && bl7 || MessageEditableActivityBase.this.mUseSlotId == 0 && bl6 || MessageEditableActivityBase.this.mUseSlotId == 1 && bl7;
                    if (bl6) {
                        object = MessageEditableActivityBase.this.mTextEditor;
                        n = n != 0 ? 2131362182 : 2131362183;
                        object.setHint(n);
                    } else {
                        object = MessageEditableActivityBase.this.mTextEditor;
                        n = n != 0 ? 2131362407 : 2131362406;
                        object.setHint(n);
                    }
                } else if (MessageEditableActivityBase.this.mMxStyle) {
                    MessageEditableActivityBase.this.mEditorContainer.setBackgroundResource(2130837589);
                    if (MessageEditableActivityBase.this.mSendButton != null) {
                        MessageEditableActivityBase.this.mSendButton.setBackgroundResource(2130837951);
                    }
                    MessageEditableActivityBase.this.mTextEditor.setCursorDrawableRes(2130838007);
                    if (contactList.size() > 0) {
                        if (n != 0) {
                            MessageEditableActivityBase.this.mTextEditor.setHint(2131362182);
                        } else {
                            MessageEditableActivityBase.this.mTextEditor.setHint(2131362183);
                        }
                    } else {
                        MessageEditableActivityBase.this.mTextEditor.setHint((CharSequence)"");
                    }
                } else {
                    MessageEditableActivityBase.this.mEditorContainer.setBackgroundResource(2130837588);
                    if (MessageEditableActivityBase.this.mSendButton != null) {
                        MessageEditableActivityBase.this.mSendButton.setBackgroundResource(2130837941);
                    }
                    MessageEditableActivityBase.this.mTextEditor.setCursorDrawableRes(MessageEditableActivityBase.this.mDefaultCursorDrawableRes);
                    if (n != 0) {
                        EditText editText = MessageEditableActivityBase.this.mTextEditor;
                        object = bl2 ? MessageEditableActivityBase.this.getString(2131362181) : MessageEditableActivityBase.this.getString(2131362180);
                        editText.setHint((CharSequence)object);
                    } else {
                        EditText editText = MessageEditableActivityBase.this.mTextEditor;
                        object = bl2 ? MessageEditableActivityBase.this.getString(2131362179) : MessageEditableActivityBase.this.getString(2131362178);
                        editText.setHint((CharSequence)object);
                    }
                }
                if (bl3) {
                    if (bl2) {
                        MessageEditableActivityBase.access$1700((MessageEditableActivityBase)MessageEditableActivityBase.this)[0] = 1;
                    } else {
                        MessageEditableActivityBase.access$1800((MessageEditableActivityBase)MessageEditableActivityBase.this)[0] = 1;
                    }
                    if (MessageEditableActivityBase.this.mSendButton1 != null) {
                        MessageEditableActivityBase.this.mSendButton1.setBackgroundResource(2130837958);
                    }
                } else {
                    if (bl2) {
                        if (MessageEditableActivityBase.this.mLastMxMmsTime[0] == 1) {
                            MessageEditableActivityBase.access$1700((MessageEditableActivityBase)MessageEditableActivityBase.this)[0] = System.currentTimeMillis();
                        }
                    } else if (MessageEditableActivityBase.this.mLastMxSmsTime[0] == 1) {
                        MessageEditableActivityBase.access$1800((MessageEditableActivityBase)MessageEditableActivityBase.this)[0] = System.currentTimeMillis();
                    }
                    if (MessageEditableActivityBase.this.mSendButton1 != null) {
                        MessageEditableActivityBase.this.mSendButton1.setBackgroundResource(2130837961);
                    }
                }
                if (bl4) {
                    if (bl2) {
                        MessageEditableActivityBase.access$1700((MessageEditableActivityBase)MessageEditableActivityBase.this)[1] = 1;
                    } else {
                        MessageEditableActivityBase.access$1800((MessageEditableActivityBase)MessageEditableActivityBase.this)[1] = 1;
                    }
                    if (MessageEditableActivityBase.this.mSendButton2 != null) {
                        MessageEditableActivityBase.this.mSendButton2.setBackgroundResource(2130837958);
                    }
                } else {
                    if (bl2) {
                        if (MessageEditableActivityBase.this.mLastMxMmsTime[1] == 1) {
                            MessageEditableActivityBase.access$1700((MessageEditableActivityBase)MessageEditableActivityBase.this)[1] = System.currentTimeMillis();
                        }
                    } else if (MessageEditableActivityBase.this.mLastMxSmsTime[1] == 1) {
                        MessageEditableActivityBase.access$1800((MessageEditableActivityBase)MessageEditableActivityBase.this)[1] = System.currentTimeMillis();
                    }
                    if (MessageEditableActivityBase.this.mSendButton2 != null) {
                        MessageEditableActivityBase.this.mSendButton2.setBackgroundResource(2130837961);
                    }
                }
                Log.v((String)"MessageEditableActivityBase", (String)("switch to mx mode: " + MessageEditableActivityBase.this.mMxStyle + ", recipients: " + contactList.size() + ", sms: " + MessageEditableActivityBase.this.mMxSmsRecipients.size() + ", mms: " + MessageEditableActivityBase.this.mMxMmsRecipients.size()));
                MessageEditableActivityBase.this.postUpdateSendButtonState();
            }
        };
        this.mHandler = new Handler(){

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message message) {
                String string;
                Log.v((String)"MessageEditableActivityBase", (String)("handle msg on main thread, msg: " + message.what));
                switch (message.what) {
                    default: {
                        super.handleMessage(message);
                        return;
                    }
                    case 5: {
                        MessageEditableActivityBase.this.postSwitchMsgType();
                        return;
                    }
                    case 1: 
                    case 4: {
                        String string2 = (String)message.obj;
                        long l = message.getData().getLong("capability");
                        MessageEditableActivityBase.this.addOrRemoveRecipient(l, string2);
                        MessageEditableActivityBase.this.mPendingRecipients.remove(string2);
                        if (message.what != 1) return;
                        MessageEditableActivityBase.this.postSwitchMsgType();
                        return;
                    }
                    case 3: {
                        ContactList contactList = MessageEditableActivityBase.this.getRecipients();
                        if (contactList == null) return;
                        string = (String)message.obj;
                        if (!contactList.contains((Object)Contact.get(string))) {
                            Log.w((String)"MessageEditableActivityBase", (String)"recipient is no longer in list");
                            return;
                        } else {
                            break;
                        }
                    }
                }
                long l = message.getData().getLong("capability");
                MessageEditableActivityBase.this.addOrRemoveRecipient(l, string);
                MessageEditableActivityBase.this.postSwitchMsgType();
            }
        };
        this.mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onSubscriptionsChanged() {
                boolean bl = true;
                if (MSimUtils.isMSimInserted()) {
                    long l = MSimUtils.getSimIdBySlotId(0);
                    long l2 = MSimUtils.getSimIdBySlotId(1);
                    if (MessageEditableActivityBase.this.mBackupSimId1 == l && MessageEditableActivityBase.this.mBackupSimId2 == l2) {
                        bl = false;
                    } else {
                        MessageEditableActivityBase.this.mBackupSimId1 = l;
                        MessageEditableActivityBase.this.mBackupSimId2 = l2;
                    }
                } else {
                    MessageEditableActivityBase.this.mBackupSimId1 = -1;
                    MessageEditableActivityBase.this.mBackupSimId2 = -1;
                }
                if (bl) {
                    Log.d((String)"MessageEditableActivityBase", (String)"update sim info change");
                    MessageEditableActivityBase.this.updateSlotRelatedState();
                    MessageEditableActivityBase.this.postUpdateSendButtonState();
                    MessageEditableActivityBase.this.onChildSimInfoChanged();
                    return;
                }
                MessageEditableActivityBase.this.updateSlotButtonInfo();
            }
        };
        this.mSimStateChagnedListener = new MmsSystemEventReceiver.SimStateChangedListener(){

            @Override
            public void onSimStateChanged(String string) {
                Log.d((String)"MessageEditableActivityBase", (String)"update sim state change");
                MessageEditableActivityBase.this.updateSlotRelatedState();
                MessageEditableActivityBase.this.postUpdateSendButtonState();
            }
        };
        this.mBroadcastReceiver = new BroadcastReceiver(){

            public void onReceive(Context context, Intent intent) {
                if ("android.intent.action.AIRPLANE_MODE".equals((Object)intent.getAction())) {
                    MessageEditableActivityBase.this.mAirModeOn = intent.getBooleanExtra("state", false);
                    MessageEditableActivityBase.this.postUpdateSendButtonState();
                    return;
                }
                if (PushSession.getInstance(context).getConnectedSimIndex() > 0) {
                    MessageEditableActivityBase.this.mMxEnabled = true;
                }
                MessageEditableActivityBase.this.onPushStatusChanged();
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void addOrRemoveRecipient(long l, String string) {
        string = Contact.normalizePhoneNumber(string);
        MxCapability mxCapability = new MxCapability(l);
        if (mxCapability.allowMms()) {
            this.mMxMmsRecipients.put(string, PRESENCE);
        } else {
            this.mMxMmsRecipients.remove(string);
        }
        if (mxCapability.allowSms()) {
            this.mMxSmsRecipients.put(string, PRESENCE);
        } else {
            this.mMxSmsRecipients.remove(string);
        }
        if (mxCapability.allowAudio()) {
            this.mMxAudioRecipients.put(string, PRESENCE);
        } else {
            this.mMxAudioRecipients.remove(string);
        }
        if (mxCapability.allowMxV2()) {
            this.mMxV2Recipients.put(string, PRESENCE);
            return;
        }
        this.mMxV2Recipients.remove(string);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean allowAnimation() {
        boolean bl = false;
        Object object = IWindowManager.Stub.asInterface((IBinder)ServiceManager.getService((String)"window"));
        try {
            object = object.getAnimationScales();
            if (object == null) return bl;
        }
        catch (RemoteException var4_3) {
            return bl;
        }
        Object object2 = object.length >= 1 ? (Object)((Object)(object[0] + 0.5f) % 10) : 0;
        Object object3 = object2;
        if (object.length < 2) return bl;
        object3 = object2 + ((Object)(object[1] + 0.5f) & 7) * 10;
        if (object3 == false) return bl;
        return true;
    }

    private boolean canSendByMx(int n) {
        return this.canSendByMx(n, this.isMxAvailable(n));
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean canSendByMx(int n, boolean bl) {
        if (n < 0) return false;
        if (!MxActivateService.isMxEnabled((Context)this, n)) return false;
        if (this.mWorkingMessage.getTimeToSend() > 0) {
            return false;
        }
        boolean bl2 = this.mWorkingMessage.requiresMms();
        long l = System.currentTimeMillis();
        if (bl) return true;
        if (bl2) {
            if (l - Math.abs((long)this.mLastMxMmsTime[n]) <= 3000) return true;
        }
        if (bl2) return false;
        if (l - Math.abs((long)this.mLastMxSmsTime[n]) > 3000) return false;
        return true;
    }

    private void cancelUpdateContactNames() {
        if (this.mUpdateContactTask != null) {
            this.mUpdateContactTask.cancel(true);
            this.mUpdateContactTask = null;
        }
    }

    private boolean containsEmailAddress() {
        this.mWorkingMessage.syncWorkingRecipients();
        return this.mWorkingMessage.getConversation().getRecipients().containsEmail();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String getBody(Uri arrstring) {
        if (arrstring == null) {
            return null;
        }
        if (!(arrstring = arrstring.getSchemeSpecificPart()).contains((CharSequence)"?")) return null;
        arrstring = arrstring.substring(arrstring.indexOf(63) + 1).split("&");
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            String string = arrstring[n2];
            if (string.startsWith("body=")) {
                try {
                    return URLDecoder.decode((String)string.substring(5), (String)"UTF-8");
                }
                catch (UnsupportedEncodingException var4_5) {
                    // empty catch block
                }
            }
            ++n2;
        }
        return null;
    }

    private View getContentParent() {
        View view = this.findViewById(16908290);
        if (view.getParent().getParent().getParent() != null) {
            return (View)view.getParent();
        }
        return view;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean handleSendIntent(Intent object) {
        boolean bl = true;
        Bundle bundle = object.getExtras();
        if (bundle == null) {
            return false;
        }
        String string = object.getType();
        String string2 = object.getAction();
        if ("android.intent.action.SEND".equals((Object)string2)) {
            boolean bl2;
            if (!MessageUtils.isSendingContactByVCard(this.mSharedPrefs) && !TextUtils.isEmpty((CharSequence)(object = ContactParser.getContactParser((Context)this).getContactFromIntent((Intent)object)))) {
                this.mWorkingMessage.setText((CharSequence)object);
                return true;
            }
            if (bundle.containsKey("android.intent.extra.STREAM")) {
                object = (Uri)bundle.getParcelable("android.intent.extra.STREAM");
                this.mAttachmentProcessor.addAttachment(string, (Uri)object, false);
                bl2 = true;
            } else {
                bl2 = false;
            }
            if (!bundle.containsKey("android.intent.extra.TEXT")) return bl;
            this.mWorkingMessage.setText(bundle.getString("android.intent.extra.TEXT"));
            return bl;
        }
        if ("android.intent.action.SEND_MULTIPLE".equals((Object)string2) && bundle.containsKey("android.intent.extra.STREAM")) {
            int n;
            object = this.mWorkingMessage.getSlideshow();
            string2 = bundle.getParcelableArrayList("android.intent.extra.STREAM");
            int n2 = object != null ? object.size() : 0;
            final int n3 = n = string2.size();
            if (n + n2 > 20) {
                n3 = Math.min((int)(20 - n2), (int)n);
                Toast.makeText((Context)this, (CharSequence)this.getString(2131361874, new Object[]{20, n3}), (int)1).show();
            }
            object = null;
            if (bundle.containsKey("android.intent.extra.TEXT")) {
                object = bundle.getString("android.intent.extra.TEXT");
            }
            bundle = new AlertDialog.Builder((Context)this).setIconAttribute(16843605).setTitle(2131361875).setMessage(2131361876).create();
            Runnable runnable = new Runnable((AlertDialog)bundle){
                final /* synthetic */ AlertDialog val$dialog;

                @Override
                public void run() {
                    this.val$dialog.show();
                }
            };
            this.mHandler.postDelayed(runnable, 1000);
            new Thread(new Runnable((ArrayList)string2, string, (String)object, runnable, (AlertDialog)bundle){
                final /* synthetic */ AlertDialog val$dialog;
                final /* synthetic */ String val$finalText;
                final /* synthetic */ String val$mimeType;
                final /* synthetic */ Runnable val$showProgress;
                final /* synthetic */ ArrayList val$uris;

                @Override
                public void run() {
                    for (int i = 0; i < n3; ++i) {
                        Parcelable parcelable = (Parcelable)this.val$uris.get(i);
                        MessageEditableActivityBase.this.mAttachmentProcessor.addAttachment(this.val$mimeType, (Uri)parcelable, true);
                    }
                    if (!TextUtils.isEmpty((CharSequence)this.val$finalText)) {
                        MessageEditableActivityBase.this.mWorkingMessage.tryInsertExtraText(this.val$finalText);
                    }
                    MessageEditableActivityBase.this.mHandler.removeCallbacks(this.val$showProgress);
                    this.val$dialog.dismiss();
                }
            }, "addAttachment").start();
            return true;
        }
        if (!"android.intent.action.SEND_MSG".equals((Object)string2)) {
            return false;
        }
        if (!Conversation.checkContentScheme((Uri)(object = object.getData()))) {
            return false;
        }
        this.mAttachmentProcessor.addAttachment(string, (Uri)object, false);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean handleSendToIntent(Intent intent) {
        boolean bl;
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return false;
        }
        String string = intent.getType();
        if (!"android.intent.action.SENDTO".equals((Object)intent.getAction())) return false;
        if (bundle.containsKey("android.intent.extra.STREAM")) {
            intent = (Uri)bundle.getParcelable("android.intent.extra.STREAM");
            this.mAttachmentProcessor.addAttachment(string, (Uri)intent, false);
            bl = true;
        } else {
            bl = false;
        }
        if (!bundle.containsKey("android.intent.extra.TEXT")) return true;
        this.mWorkingMessage.setText(bundle.getString("android.intent.extra.TEXT"));
        return true;
    }

    private boolean hasMessage() {
        if (this.isSubjectEditorVisible() || this.isPreparedForSending()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean hasRecipientsToRevise() {
        boolean bl = false;
        HashSet hashSet = Sets.newHashSet((Object[])this.mSharedPrefs.getString("nickname_revised_numbers", "").split("\n"));
        ContactList contactList = this.mConversation.getRecipients();
        int n = 0;
        do {
            boolean bl2 = bl;
            if (n >= contactList.size()) return bl2;
            Contact contact = (Contact)contactList.get(n);
            if (contact.existsInDatabase() && contact.isPhoneNumber()) {
                contact = PhoneNumberUtils.PhoneNumber.parse((CharSequence)contact.getNumber());
                String string = contact.getNormalizedNumber(false, true);
                contact.recycle();
                if (!hashSet.contains((Object)string)) {
                    return true;
                }
            }
            ++n;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void hideAudioViews() {
        if (this.mSendButton != null) {
            View view = this.mSendButton;
            int n = this.mMxStyle && !this.isB2cMessageActivity() ? 2130837951 : 2130837941;
            view.setBackgroundResource(n);
            boolean bl = this.isPreparedForSending();
            this.mSendButton.setEnabled(bl);
            this.mSendButton.setFocusable(bl);
        }
    }

    private boolean isB2cMessageActivity() {
        return this instanceof B2cMessageConversationActivity;
    }

    private boolean isContain(ContactList object, Map<String, Object> map) {
        if (object.size() == map.size()) {
            return true;
        }
        object = object.iterator();
        while (object.hasNext()) {
            boolean bl;
            block3 : {
                Contact contact = (Contact)object.next();
                boolean bl2 = false;
                Iterator<String> iterator = map.keySet().iterator();
                do {
                    bl = bl2;
                    if (!iterator.hasNext()) break block3;
                } while (!PhoneNumberUtils.PhoneNumber.parse((CharSequence)contact.getCompareKey()).getNormalizedNumber(true, false).equals((Object)PhoneNumberUtils.PhoneNumber.parse((CharSequence)iterator.next()).getNormalizedNumber(true, false)));
                bl = true;
            }
            if (bl) continue;
            return false;
        }
        return true;
    }

    private boolean isMxAvailable(int n) {
        boolean bl = this.mWorkingMessage.requiresMms();
        this.mMxEnabled = MxActivateService.isMxEnabled((Context)this);
        ContactList contactList = this.getRecipients();
        if (MxActivateService.isMxEnabled((Context)this, n) && this.mWorkingMessage.getTimeToSend() <= 0 && MSimUtils.isSimInserted(n) && PushSession.getInstance((Context)this).isConnected(n) && contactList.size() > 0 && (bl && contactList.size() == this.mMxMmsRecipients.size() || !bl && contactList.size() == this.mMxSmsRecipients.size())) {
            return true;
        }
        return false;
    }

    private boolean recipientsOverSize() {
        if (this.mWorkingMessage.requiresMms() && this.getRecipients().size() > 50) {
            new AlertDialog.Builder((Context)this).setIconAttribute(16843605).setTitle(2131361865).setMessage(2131361877).setPositiveButton(2131361891, null).show();
            return true;
        }
        return false;
    }

    private void resetMessage() {
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"MessageEditableActivityBase", (String)"resetMessage");
        }
        this.mAttachmentView.setVisibility(8);
        this.mAudioNameView.setVisibility(8);
        this.mTimedMsgIndicator.setVisibility(8);
        this.mTimedDescView.setVisibility(8);
        this.showSubjectEditor(false);
        this.mTextEditor.requestFocus();
        this.mTextEditor.removeTextChangedListener(this.mTextEditorWatcher);
        TextKeyListener.clear((Editable)this.mTextEditor.getText());
        this.mWorkingMessage = WorkingMessage.createEmpty(this);
        this.mWorkingMessage.setConversation(this.mConversation);
        this.drawBottomPanel();
        this.mAttachmentProcessor.updateAttachmentTypeStates();
        this.mTextEditor.addTextChangedListener(this.mTextEditorWatcher);
        if (this.mIsLandscape) {
            this.hideSoftKeyboard();
        }
        this.mSendingMessage = false;
    }

    private void sendMessage(boolean bl, final int n) {
        if (bl && Boolean.parseBoolean((String)SystemProperties.get((String)"ril.cdma.inecmmode"))) {
            try {
                this.startActivityForResult(new Intent("android.intent.action.ACTION_SHOW_NOTICE_ECM_BLOCK_OTHERS", null), 1);
                this.mSavedSlotIdForECM = n;
                return;
            }
            catch (ActivityNotFoundException var3_3) {
                Log.e((String)"MessageEditableActivityBase", (String)"Cannot find EmergencyCallbackModeExitDialog", (Throwable)var3_3);
            }
        }
        if (Build.IS_CU_CUSTOMIZATION && this.containsEmailAddress()) {
            new AlertDialog.Builder((Context)this).setIconAttribute(16843605).setMessage(2131362305).setPositiveButton(2131361843, new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n2) {
                    MessageEditableActivityBase.this.sendingMessage(n);
                }
            }).setNegativeButton(2131361892, null).show();
            return;
        }
        this.sendingMessage(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void sendingMessage(int n) {
        if (!this.mSendingMessage) {
            String string = this.mWorkingMessage.getWorkingRecipients();
            if (this.isB2cMessageActivity()) {
                Log.v((String)"MessageEditableActivityBase", (String)"send b2c msg");
                this.mWorkingMessage.setSendByMx(true);
                this.mWorkingMessage.setSendByMxV2(true);
            } else {
                boolean bl = this.canSendByMx(n);
                Log.v((String)"MessageEditableActivityBase", (String)("sending msg by mx: " + bl));
                this.mWorkingMessage.setSendByMx(bl);
                this.mWorkingMessage.setSendByMxV2(this.canSendByMxV2(n));
            }
            this.mWorkingMessage.send(string, n);
            this.mSendingMessage = true;
        }
    }

    private void showAudioViews() {
        if (this.mSendButton != null) {
            if (this.mAudioController == null) {
                this.mAudioController = new AudioRecordingController(this, this.isMx2AudioAvailable(), this.mBottomPanel, (ViewStub)this.findViewById(2131820689), (ViewStub)this.findViewById(2131820803), this.mSendButton, (View)this.mSwitchBtn, (View)this.mTextEditor, (Context)this);
                this.mBtnTouch = new AudioBtnTouchRunnable(this.mAudioController);
            }
            this.mSendButton.setEnabled(true);
            this.mSendButton.setBackgroundResource(2130837578);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void showSubjectEditor(boolean bl) {
        int n = 0;
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"MessageEditableActivityBase", (String)("showSubjectEditor: " + bl));
        }
        if (this.mSubjectTextEditor == null) {
            if (!bl) {
                return;
            }
            this.mSubjectTextEditor = (EditText)this.findViewById(2131820596);
            this.mSubjectTextEditor.setTextSize(0, SmsTextSizeAdjust.getInstance().getTextSize());
            this.mSubjectDivider = this.findViewById(2131820789);
        }
        EditText editText = this.mSubjectTextEditor;
        View.OnKeyListener onKeyListener = bl ? this.mSubjectKeyListener : null;
        editText.setOnKeyListener(onKeyListener);
        if (bl) {
            this.mSubjectTextEditor.removeTextChangedListener(this.mSubjectEditorWatcher);
            this.mSubjectTextEditor.addTextChangedListener(this.mSubjectEditorWatcher);
        } else {
            this.mSubjectTextEditor.removeTextChangedListener(this.mSubjectEditorWatcher);
        }
        onKeyListener = this.mSubjectTextEditor;
        int n2 = bl ? 0 : 8;
        onKeyListener.setVisibility(n2);
        this.mSubjectTextEditor.setText(this.mWorkingMessage.getSubject());
        onKeyListener = this.mSubjectDivider;
        n2 = bl ? n : 8;
        onKeyListener.setVisibility(n2);
        this.invalidateOptionsMenu();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void toastConvertInfo(boolean bl) {
        int n = bl ? 2131361868 : 2131361869;
        Toast.makeText((Context)this, (int)n, (int)0).show();
    }

    private void updateContactNames() {
        this.mUpdateContactTask = new AsyncTask<Void, Void, Boolean>(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            protected /* varargs */ Boolean doInBackground(Void ... object) {
                object = MessageEditableActivityBase.this.mConversation.getRecipients();
                boolean bl = false;
                object = object.iterator();
                while (object.hasNext()) {
                    Contact contact = (Contact)object.next();
                    if (this.isCancelled()) {
                        return bl;
                    }
                    String string = contact.getName();
                    contact.load(true, true);
                    if (string.equals((Object)contact.getName())) continue;
                    bl = true;
                }
                return bl;
            }

            protected void onPostExecute(Boolean bl) {
                if (bl.booleanValue()) {
                    MessageEditableActivityBase.this.onContactsUpdated(MessageEditableActivityBase.this.mConversation.getRecipients());
                }
            }
        };
        this.mUpdateContactTask.execute((Object[])new Void[0]);
    }

    private boolean updateCounter() {
        return this.updateCounter(this.mWorkingMessage.requiresMms());
    }

    private boolean updateCounter(boolean bl) {
        if (bl) {
            if (Build.IS_CM_CUSTOMIZATION || Build.IS_CU_CUSTOMIZATION) {
                int n = this.mWorkingMessage.getCurrentMmsSize();
                if (n > MmsConfig.getMaxMessageSize()) {
                    this.mTextCounter.setText(2131361844);
                    return false;
                }
                String string = this.getString(2131361844) + "\n";
                string = string + String.valueOf((int)((n - 1) / 1024 + 1)) + "Kb";
                this.mTextCounter.setText((CharSequence)string);
                return false;
            }
            this.mTextCounter.setText(2131361844);
            return false;
        }
        int[] arrn = SmsMessage.calculateLength((CharSequence)this.mWorkingMessage.getText(), (boolean)false);
        int n = arrn[0];
        int n2 = arrn[2];
        this.mTextCounter.setText((CharSequence)MessageUtils.getMessageStats(n, n2));
        return false;
    }

    private void updateNicknameRevisedNumbers() {
        Object object = Sets.newHashSet((Object[])this.mSharedPrefs.getString("nickname_revised_numbers", "").split("\n"));
        ContactList contactList = this.mConversation.getRecipients();
        for (int i = 0; i < contactList.size(); ++i) {
            Contact contact = (Contact)contactList.get(i);
            if (!contact.existsInDatabase() || !contact.isPhoneNumber()) continue;
            contact = PhoneNumberUtils.PhoneNumber.parse((CharSequence)contact.getNumber());
            String string = contact.getNormalizedNumber(false, true);
            contact.recycle();
            object.add((Object)string);
        }
        object = TextUtils.join((CharSequence)"\n", (Object[])object.toArray());
        contactList = this.mSharedPrefs.edit();
        contactList.putString("nickname_revised_numbers", (String)object);
        contactList.commit();
    }

    protected boolean canSendByMxV2(int n) {
        if (this.canSendByMx(n, this.isMxV2Available()) && this.isContain(this.getRecipients(), this.mMxV2Recipients)) {
            return true;
        }
        return false;
    }

    public void cancelTiming() {
        this.mTimedMsgIndicator.setVisibility(8);
        this.mTimedDescView.setVisibility(8);
        this.mWorkingMessage.setTimeToSend(0);
        this.mWorkingMessage.setTimeToSendDesc("");
        this.postSwitchMsgType();
    }

    protected void checkAndSendMessage(boolean bl, int n) {
        if (MessageUtils.isPrivacyModeEnabled((Context)this)) {
            Toast.makeText((Context)this, (int)2131362145, (int)0).show();
            return;
        }
        this.sendMessage(bl, n);
    }

    public void confirmRemovingChenghu(final Runnable runnable, final Runnable runnable2) {
        if (this.mTextEditor.getText().toString().indexOf(65535) == -1) {
            if (runnable != null) {
                runnable.run();
            }
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setMessage(2131362139);
        builder.setPositiveButton(17039370, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface = MessageEditableActivityBase.this.mTextEditor.getText();
                n = 0;
                while (n < dialogInterface.length()) {
                    int n2 = n;
                    if (dialogInterface.charAt(n) == '\uffff') {
                        dialogInterface.delete(n, n + 1);
                        n2 = n - 1;
                    }
                    n = n2 + 1;
                }
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        builder.setNegativeButton(17039360, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener(){

            public void onCancel(DialogInterface dialogInterface) {
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
        builder.create().show();
    }

    public boolean containChenghu() {
        if (this.mTextEditor.getText().toString().indexOf(65535) != -1) {
            return true;
        }
        return false;
    }

    public void delayedShowSoftKeyboard(final boolean bl) {
        this.mHandler.postDelayed(new Runnable(){

            @Override
            public void run() {
                if (bl) {
                    MessageEditableActivityBase.this.showSoftKeyboard();
                    return;
                }
                MessageEditableActivityBase.this.hideSoftKeyboard();
            }
        }, 200);
    }

    protected void disableAttachmentPanel() {
        this.mAttachmentProcessor.disableAttachmentPanel();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mAudioController != null && this.mAudioController.dispatchTouchEvent(motionEvent, this.mUseSlotId, this.isMx2AudioAvailable())) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void drawBottomPanel() {
        String string;
        if (this.mWorkingMessage.getTimeToSend() > 0) {
            this.mTimedMsgIndicator.setVisibility(0);
        } else {
            this.mTimedMsgIndicator.setVisibility(8);
        }
        if (TextUtils.isEmpty((CharSequence)(string = this.mWorkingMessage.getTimeToSendDesc()))) {
            this.mTimedDescView.setVisibility(8);
        } else {
            this.mTimedDescView.setVisibility(0);
            this.mTimedDescView.setText((CharSequence)string);
        }
        if (this.mWorkingMessage.hasSlideshow()) {
            this.mTextEditor.setVisibility(8);
        } else {
            CharSequence charSequence;
            this.mTextEditor.setVisibility(0);
            if (this.mWorkingMessage.hasAttachment()) {
                this.mTextEditor.setHint((CharSequence)"");
            }
            if ((charSequence = this.mWorkingMessage.getText()) != null) {
                if (!TextUtils.equals((CharSequence)this.mTextEditor.getText(), (CharSequence)charSequence)) {
                    this.mTextEditor.setTextKeepState(charSequence);
                }
            } else {
                this.mTextEditor.setText((CharSequence)"");
            }
        }
        this.updateCounter();
        this.postSwitchMsgType();
    }

    protected void drawTopPanel() {
        this.showSubjectEditor(this.mWorkingMessage.hasSubject(false));
    }

    protected void enableAttachmentPanel() {
        this.getEditMessageFocus();
        this.mAttachmentProcessor.enableAttachmentPanel();
    }

    protected void exit() {
        if (!this.mWorkingMessage.isWorthSaving()) {
            this.postExit();
            return;
        }
        if (this.willDiscardDraft()) {
            MessageUtils.showDiscardDraftConfirmDialog((Context)this, new DiscardDraftListener());
            return;
        }
        this.mToastForDraftSave = true;
        this.postExit();
    }

    public AttachmentView getAttachmentView() {
        return this.mAttachmentView;
    }

    protected int getBottomPanelHeight() {
        return this.mBottomPanel.getHeight();
    }

    protected abstract int getContentViewResId();

    public EditText getEditMessageFocus() {
        if (this.mSubjectTextEditor != null && this.mSubjectTextEditor.hasFocus()) {
            return this.mSubjectTextEditor;
        }
        if (this.mTextEditor.hasFocus()) {
            return this.mTextEditor;
        }
        this.mTextEditor.requestFocus();
        return this.mTextEditor;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public miui.app.Activity getHostedActivity() {
        return this;
    }

    protected abstract ContactList getRecipients();

    public EditText getTextEditor() {
        return this.mTextEditor;
    }

    public WorkingMessage getWorkingMessage() {
        return this.mWorkingMessage;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected boolean handleForwardedMessage() {
        Intent intent = this.getIntent();
        if (!intent.getBooleanExtra("forwarded_message", false)) {
            return false;
        }
        this.mOrgMsgIsPrivate = intent.getBooleanExtra("orig_message_is_private", false);
        Uri uri = (Uri)intent.getParcelableExtra("msg_uri");
        intent.getIntExtra("mx2type", 0);
        if (Log.isLoggable((String)"Mms:app", (int)3)) {
            Log.d((String)"MessageEditableActivityBase", (String)("handle forwarded message " + (Object)uri));
        }
        if (uri != null) {
            this.mWorkingMessage = WorkingMessage.load(this, uri, true);
            this.mWorkingMessage.setSubject(intent.getStringExtra("subject"), false);
            do {
                return true;
                break;
            } while (true);
        }
        this.mWorkingMessage.setText(intent.getStringExtra("sms_body"));
        return true;
    }

    protected void handleIntent(Intent intent) {
        String string;
        this.mWorkingMessage = WorkingMessage.createEmpty(this);
        if (!this.handleForwardedMessage()) {
            this.loadDraft();
        }
        if (!TextUtils.isEmpty((CharSequence)(string = this.getBody(intent.getData())))) {
            this.mWorkingMessage.setText(string);
        }
        if (!TextUtils.isEmpty((CharSequence)(string = intent.getStringExtra("sms_body")))) {
            this.mWorkingMessage.setText(string);
        }
        if (!TextUtils.isEmpty((CharSequence)(string = intent.getStringExtra("subject")))) {
            this.mWorkingMessage.setSubject(string, false);
        }
        this.mExitOnSent = intent.getBooleanExtra("exit_on_sent", false);
        this.mWorkingMessage.setConversation(this.mConversation);
        this.handleSendIntent(intent);
        this.handleSendToIntent(intent);
    }

    protected void hideSoftKeyboard() {
        View view;
        View view2 = view = this.mMsgContentView.findFocus();
        if (view == null) {
            view2 = this.mContentView;
        }
        this.mInputMethodManager.hideSoftInputFromWindow(view2.getWindowToken(), 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void initBottomPanelResourceRefs() {
        this.mBottomPanel = this.findViewById(2131820687);
        this.mEditorContainer = this.findViewById(2131820784);
        this.mTextEditor = (EditText)this.findViewById(2131820693);
        this.mTextEditor.addTextChangedListener(this.mTextEditorWatcher);
        this.mTextEditor.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
                if (n == 4) {
                    if (MessageEditableActivityBase.this.isPreparedForSending()) {
                        MessageEditableActivityBase.this.sendMessage(MessageEditableActivityBase.this.mUseSlotId);
                        return true;
                    }
                    Toast.makeText((Context)MessageEditableActivityBase.this, (int)2131361865, (int)1).show();
                }
                return false;
            }
        });
        this.mTextEditor.setTextColor(AttributeResolver.resolveColor((Context)this, (int)16843601));
        this.mTextEditor.setHintTextColor(this.getResources().getColor(2131296311));
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(3101);
            this.mTextEditor.setFilters(new InputFilter[]{lengthFilter});
        }
        this.mTextCounter = (TextView)this.findViewById(2131820691);
        if (MSimUtils.isMSim()) {
            this.mSendSlotIdView = (ImageView)this.mEditorContainer.findViewById(2131820793);
            this.findViewById(2131820695).setVisibility(0);
            this.mSimButtonContainer = this.findViewById(2131820696);
            this.mSendButton1 = this.findViewById(2131820698);
            this.mSendButton1.setOnClickListener((View.OnClickListener)this);
            this.mSendButton1Text = (TextView)this.findViewById(2131820700);
            this.mSendButton1Image = (ImageView)this.findViewById(2131820699);
            this.mSendButton2 = this.findViewById(2131820701);
            this.mSendButton2.setOnClickListener((View.OnClickListener)this);
            this.mSendButton2Text = (TextView)this.findViewById(2131820703);
            this.mSendButton2Image = (ImageView)this.findViewById(2131820702);
            this.mSendSlotIdView.setOnClickListener((View.OnClickListener)this);
            this.mBackupSimId1 = MSimUtils.getSimIdBySlotId(0);
            this.mBackupSimId2 = MSimUtils.getSimIdBySlotId(1);
        } else {
            this.mBackupSimId1 = -1;
            this.mBackupSimId2 = -1;
        }
        this.updateSlotRelatedState();
        this.mSendButton = this.findViewById(2131820692);
        this.mSendButton.setOnClickListener((View.OnClickListener)this);
        this.mSendButton.setOnTouchListener(new View.OnTouchListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!MessageEditableActivityBase.this.mMxStyle) return false;
                if (MessageEditableActivityBase.this.mAirModeOn) return false;
                if (MessageEditableActivityBase.this.hasMessage()) return false;
                if (MessageEditableActivityBase.this.mAudioController == null) return false;
                if (MessageEditableActivityBase.this.mAudioController.isRecording()) return false;
                switch (motionEvent.getAction()) {
                    case 0: {
                        if (MessageEditableActivityBase.this.mSimButtonContainer != null) {
                            MessageEditableActivityBase.this.mSimButtonContainer.setVisibility(8);
                        }
                        MessageEditableActivityBase.this.mWorkingMessage.syncWorkingRecipients();
                        MessageEditableActivityBase.this.mAudioController.setThreadId(MessageEditableActivityBase.this.mConversation.ensureThreadId());
                        MessageEditableActivityBase.this.mAudioController.setConversation(MessageEditableActivityBase.this.mConversation);
                        MessageEditableActivityBase.this.mBtnTouch.setEvent(motionEvent);
                        MessageEditableActivityBase.this.mHandler.postDelayed((Runnable)MessageEditableActivityBase.this.mBtnTouch, 200);
                    }
                    default: {
                        return false;
                    }
                    case 1: 
                }
                if (MessageEditableActivityBase.this.mBtnTouch.isRunning()) return false;
                MessageEditableActivityBase.this.mHandler.removeCallbacks((Runnable)MessageEditableActivityBase.this.mBtnTouch);
                MessageEditableActivityBase.this.mAudioController.showRemind();
                return false;
            }
        });
        this.mAttachmentView = (AttachmentView)this.findViewById(2131820790);
        this.mAttachmentView.setHandler(new Handler(){

            public void handleMessage(Message message) {
                MessageEditableActivityBase.this.mAttachmentProcessor.handleAttachentMessage(message);
            }
        });
        this.mAudioNameView = (TextView)this.findViewById(2131820621);
        this.mAttachmentView.setAudioNameView(this.mAudioNameView);
        this.mSwitchBtn = (Button)this.findViewById(2131820690);
        this.mSwitchBtn.setOnClickListener((View.OnClickListener)this);
        this.mIsSwitchMenuBtn = false;
        this.mTimedMsgIndicator = (ImageButton)this.findViewById(2131820787);
        this.mTimedMsgIndicator.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                MessageEditableActivityBase.this.setTiming();
            }
        });
        this.mTimedDescView = (TextView)this.findViewById(2131820788);
        this.findViewById(2131820792).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (MessageEditableActivityBase.this.isVisible((View)MessageEditableActivityBase.this.mTextEditor)) {
                    MessageEditableActivityBase.this.mTextEditor.requestFocus();
                    MessageEditableActivityBase.this.mTextEditor.setSelection(0);
                    MessageEditableActivityBase.this.showSoftKeyboard();
                }
            }
        });
    }

    protected void initResourceRefs() {
        this.mHistoryView = (FrameLayout)this.findViewById(2131820760);
        this.initBottomPanelResourceRefs();
        this.mMessageContentPanel = this.findViewById(2131820786);
        this.mHomeButton = (Button)this.findViewById(2131820651);
        if (this.mHomeButton != null) {
            this.mHomeButton.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    MessageEditableActivityBase.this.exit();
                }
            });
        }
        this.mContentParent = this.getContentParent();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void initialize() {
        this.initializeBottomPanel();
        boolean bl = this.getResources().getConfiguration().orientation == 2;
        this.mIsLandscape = bl;
    }

    protected void initializeBottomPanel() {
        this.handleIntent(this.getIntent());
        if (MSimUtils.isMSimInserted()) {
            int n = MSimUtils.getSlotIdBySimInfoId(this.mConversation.getLastSimId());
            if (MSimUtils.isMSimSlotIdValid(n)) {
                this.mUseSlotId = n;
            }
            this.updateSlotButtonStateBySlotId((Context)this, this.mUseSlotId);
        }
        this.drawTopPanel();
        this.drawBottomPanel();
        this.mAttachmentView.update(this.mWorkingMessage);
    }

    void insertChenghu() {
        int n = this.mTextEditor.getSelectionStart();
        this.mTextEditor.getText().insert(n, (CharSequence)String.valueOf((char)'\uffff'));
        this.mTextEditor.setSelection(n + 1);
        this.startNicknamePicker(false);
        this.showSoftKeyboard();
    }

    public void insertPhraseSms(String string) {
        if (TextUtils.isEmpty((CharSequence)string)) {
            return;
        }
        int n = this.mTextEditor.getSelectionStart();
        this.mTextEditor.getText().insert(n, (CharSequence)string);
        this.showSoftKeyboard();
    }

    protected boolean isAttachmentPanelVisible() {
        return this.mAttachmentProcessor.isAttachmentPanelVisible();
    }

    public boolean isMx2AudioAvailable() {
        if (this.getRecipients().size() == this.mMxAudioRecipients.size()) {
            return true;
        }
        return false;
    }

    protected boolean isMxV2Available() {
        return this.isContain(this.getRecipients(), this.mMxV2Recipients);
    }

    public abstract boolean isPreparedForSending();

    protected boolean isSubjectEditorVisible() {
        if (this.mSubjectTextEditor != null && this.mSubjectTextEditor.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    protected boolean isVisible(View view) {
        if (view != null && view.getVisibility() != 8) {
            return true;
        }
        return false;
    }

    protected void loadDraft() {
        if (!this.mConversation.hasDraft()) {
            return;
        }
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"MessageEditableActivityBase", (String)"loadDraft: call WorkingMessage.loadDraft");
        }
        this.mWorkingMessage = WorkingMessage.loadDraft(this, this.mConversation);
        this.updateCounter();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onActivityResult(int n, int n2, Intent object) {
        int n3 = 0;
        super.onActivityResult(n, n2, (Intent)object);
        this.mWaitingForSubActivity = false;
        if (this.mWorkingMessage.isFakeMmsForDraft()) {
            this.mWorkingMessage.removeFakeMmsForDraft();
        }
        if (n == 1) {
            if (object.getBooleanExtra("exit_ecm_result", false)) {
                this.sendMessage(false, this.mSavedSlotIdForECM);
            }
            this.mSavedSlotIdForECM = -1;
            return;
        } else if (n == 3 && object != null) {
            String[] arrstring = object.getStringArrayExtra("numbers");
            object = object.getStringArrayExtra("names");
            if (arrstring == null || object == null || arrstring.length != object.length) return;
            {
                for (n = n3; n < arrstring.length; ++n) {
                    String string = arrstring[n];
                    String string2 = object[n];
                    Contact.get(string).setNickname(string2);
                }
                return;
            }
        } else {
            if (n != 4) {
                this.mAttachmentProcessor.onActivityResult(n, n2, (Intent)object);
                return;
            }
            this.mTimedMsgIndicator.setClickable(true);
            if (n2 != -1) return;
            {
                long l = object.getLongExtra(DateTimePickerActivity.FIELD_TIME, -1);
                if (l != -1) {
                    this.mTimedMsgIndicator.setVisibility(0);
                    this.mWorkingMessage.setTimeToSend(l);
                    this.mTimedDescView.setVisibility(0);
                    object = this.mWorkingMessage.formatDateTime((Context)this, l);
                    this.mWorkingMessage.setTimeToSendDesc((String)object);
                    this.mTimedDescView.setText((CharSequence)object);
                }
                this.postSwitchMsgType();
                return;
            }
        }
    }

    @Override
    public void onAttachmentChanged() {
        this.runOnUiThread(new Runnable(){

            @Override
            public void run() {
                MessageEditableActivityBase.this.mAttachmentView.update(MessageEditableActivityBase.this.mWorkingMessage);
                MessageEditableActivityBase.this.mAttachmentProcessor.updateAttachmentTypeStates();
                MessageEditableActivityBase.this.drawBottomPanel();
            }
        });
    }

    @Override
    public void onAttachmentError(final int n, final Uri uri, final boolean bl) {
        this.runOnUiThread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                MessageEditableActivityBase.this.mAttachmentProcessor.handleAddAttachmentError(n, 2131361995);
                if (MessageEditableActivityBase.this.getWindow().isDestroyed() || !bl || MessageEditableActivityBase.this.mWorkingMessage == null) {
                    return;
                }
                MessageEditableActivityBase.this.mWorkingMessage.loadFromUri(uri, false);
                MessageEditableActivityBase.this.mAttachmentView.update(MessageEditableActivityBase.this.mWorkingMessage);
                MessageEditableActivityBase.this.drawTopPanel();
                MessageEditableActivityBase.this.drawBottomPanel();
                MessageEditableActivityBase.this.mAttachmentProcessor.updateAttachmentTypeStates();
            }
        });
    }

    public void onBackPressed() {
        if (this.mAttachmentView.onBackPressed()) {
            return;
        }
        if (this.isAttachmentPanelVisible()) {
            if (!this.mAttachmentProcessor.isOnAttachmentPanel()) {
                this.mAttachmentProcessor.gotoAttachmentPanel(true);
                return;
            }
            this.disableAttachmentPanel();
            return;
        }
        this.exit();
    }

    protected void onBottomPanelStop() {
        this.saveDraft(true);
        AudioTalkMediaPlayer audioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp());
        if (audioTalkMediaPlayer.isPlaying()) {
            audioTalkMediaPlayer.stop();
        }
        if (this.mAudioController != null) {
            this.mAudioController.release();
        }
    }

    protected void onBottomPanelUpdate() {
        if (this.mWorkingMessage.isDiscarded()) {
            this.mConversation.ensureThreadId();
            this.mWorkingMessage.unDiscard();
        }
        this.mMxEnabled = MxActivateService.isMxEnabled((Context)this);
        if (!this.mMxEnabled) {
            int n;
            for (n = 0; n < this.mLastMxMmsTime.length; ++n) {
                this.mLastMxMmsTime[n] = 0;
            }
            for (n = 0; n < this.mLastMxSmsTime.length; ++n) {
                this.mLastMxSmsTime[n] = 0;
            }
        }
    }

    protected abstract void onChildSimInfoChanged();

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case 2131820692: {
                if (Build.IS_CM_CUSTOMIZATION_TEST && this.recipientsOverSize() || !this.isPreparedForSending()) return;
                {
                    if (this.mSimButtonContainer != null && this.mSimButtonContainer.getVisibility() == 0) {
                        this.mSimButtonContainer.setVisibility(8);
                    }
                    this.sendMessage(this.mUseSlotId);
                    return;
                }
            }
            case 2131820698: {
                this.mUseSlotId = 0;
                this.updateSlotButtonStateBySlotId((Context)this, this.mUseSlotId);
                this.postSwitchMsgType();
                return;
            }
            case 2131820701: {
                this.mUseSlotId = 1;
                this.updateSlotButtonStateBySlotId((Context)this, this.mUseSlotId);
                this.postSwitchMsgType();
                return;
            }
            case 2131820690: {
                if (!this.isAttachmentPanelVisible()) {
                    this.enableAttachmentPanel();
                    this.hideSoftKeyboard();
                    return;
                }
                if (!this.mWorkingMessage.hasSlideshow() && this.showSoftKeyboard()) return;
                {
                    this.disableAttachmentPanel();
                    return;
                }
            }
            default: {
                return;
            }
            case 2131820793: 
        }
        if (this.mSimButtonContainer == null) return;
        {
            if (this.mSimButtonContainer.getVisibility() == 8) {
                this.mSimButtonContainer.setVisibility(0);
                return;
            }
        }
        this.mSimButtonContainer.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean bl = configuration.orientation == 2;
        if (this.mIsLandscape == bl) return;
        this.mIsLandscape = bl;
        if (this.mAttachmentView != null) {
            this.mAttachmentView.update(this.mWorkingMessage);
        }
    }

    protected void onContactAdded(Contact object) {
        object = object.getMxPhoneNumber();
        this.mPendingRecipients.put((String)object, PRESENCE);
        if (this.mMxEnabled) {
            this.mQueryStatusHandler.obtainMessage(1, object).sendToTarget();
            return;
        }
        this.postSwitchMsgType();
    }

    protected void onContactRemoved(Contact object) {
        object = Contact.normalizePhoneNumber(object.getMxPhoneNumber());
        this.mPendingRecipients.remove(object);
        this.mMxMmsRecipients.remove(object);
        this.mMxSmsRecipients.remove(object);
        this.mMxAudioRecipients.remove(object);
        this.mMxV2Recipients.remove(object);
        this.postSwitchMsgType();
    }

    protected void onContactStatusUpdate(String string) {
    }

    protected void onContactsUpdated(ContactList contactList) {
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle bundle) {
        if (this.getIntent().getBooleanExtra("showUI", false)) {
            this.getWindow().addFlags(4194304);
        }
        super.onCreate(bundle);
        MessageUtils.loadFontSizeConfiguration((Context)this);
        this.mSoftKeyboardMinHeight = this.getResources().getDimensionPixelSize(2131427343);
        this.mMessageContentPanelMinHeight = this.getResources().getDimensionPixelSize(2131427488);
        this.setContentView(this.getContentViewResId());
        this.mContentView = (FrameLayout)this.findViewById(16908290);
        this.mMsgContentView = (SizeAwareLinearLayout)this.mContentView.findViewById(2131820560);
        this.mMsgContentView.setOnMeasureListener(this);
        this.mLastMxMmsTime = new long[2];
        this.mLastMxSmsTime = new long[2];
        this.mSharedPrefs = PreferenceManager.getDefaultSharedPreferences((Context)this.getBaseContext());
        this.mAttachmentProcessor = new AttachmentProcessor(this);
        this.mInputMethodManager = (InputMethodManager)this.getSystemService("input_method");
        this.mDefaultCursorDrawableRes = MessageUtils.resolveDefaultCursorDrawableRes((Context)this);
        this.mContactPanel = this.mContentView.findViewById(2131820798);
        this.initResourceRefs();
        this.setOnStatusBarChangeListener(new OnStatusBarChangeListener(){

            public void onStatusBarHeightChange(int n) {
                if (MessageEditableActivityBase.this.mStatusBarHeight == n) {
                    return;
                }
                MessageEditableActivityBase.this.mContactPanel.setPadding(MessageEditableActivityBase.this.mContactPanel.getPaddingLeft(), MessageEditableActivityBase.this.mContactPanel.getPaddingTop() - MessageEditableActivityBase.this.mStatusBarHeight + n, MessageEditableActivityBase.this.mContactPanel.getPaddingRight(), MessageEditableActivityBase.this.mContactPanel.getPaddingBottom());
                MessageEditableActivityBase.this.mStatusBarHeight = n;
            }
        });
        MxIdCache.addStatusListener(this);
        this.mMxEnabled = MxActivateService.isMxEnabled((Context)this);
        this.mQueryStatusWorkThread = new HandlerThread("MX status query thread", 10);
        this.mQueryStatusWorkThread.start();
        this.mQueryStatusHandler = new QueryMxStatusHandler(this.mQueryStatusWorkThread.getLooper());
        bundle = new IntentFilter();
        bundle.addAction("com.xiaomi.mms.PUSH_STATUS_CHANGED");
        bundle.addAction("android.intent.action.AIRPLANE_MODE");
        this.registerReceiver(this.mBroadcastReceiver, (IntentFilter)bundle);
        this.registerSimRelatedListener();
        boolean bl = Settings.System.getInt((ContentResolver)this.getContentResolver(), (String)"airplane_mode_on", (int)0) == 1;
        this.mAirModeOn = bl;
    }

    protected void onDestroy() {
        this.mQueryStatusWorkThread.quit();
        this.unregisterReceiver(this.mBroadcastReceiver);
        MxIdCache.removeStatusListener(this);
        this.unRegisterSimRelatedListener();
        super.onDestroy();
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                return super.onKeyDown(n, keyEvent);
            }
            case 82: 
        }
        MessageUtils.launchMessagePreference((Context)this);
        return true;
    }

    @Override
    public void onMaxPendingMessagesReached() {
        this.saveDraft(false);
        this.runOnUiThread(new Runnable(){

            @Override
            public void run() {
                MessageEditableActivityBase.this.mSendingMessage = false;
                if (MessageEditableActivityBase.this.mMaxPendingMessagesReachedToast == null) {
                    MessageEditableActivityBase.this.mMaxPendingMessagesReachedToast = Toast.makeText((Context)MessageEditableActivityBase.this, (int)2131362036, (int)1);
                }
                MessageEditableActivityBase.this.mMaxPendingMessagesReachedToast.show();
            }
        });
    }

    @Override
    public void onMessageSent() {
        if (this.getWindow().isDestroyed()) {
            return;
        }
        this.runOnUiThread(new Runnable(){

            @Override
            public void run() {
                if (MessageEditableActivityBase.this.mExitOnSent) {
                    MessageEditableActivityBase.this.finish();
                }
            }
        });
    }

    @Override
    public void onMxIdAdded(String string, String string2) {
    }

    @Override
    public void onMxIdOffline(String string, String string2) {
        if (!this.mMxEnabled) {
            Log.w((String)"MessageEditableActivityBase", (String)"mx id offline, but mx disabled");
            return;
        }
        this.mQueryStatusHandler.obtainMessage(3, (Object)string2).sendToTarget();
    }

    @Override
    public void onMxIdOnline(String string, String string2) {
        if (!this.mMxEnabled) {
            Log.w((String)"MessageEditableActivityBase", (String)"mx id online, but mx disabled");
            return;
        }
        this.mQueryStatusHandler.obtainMessage(3, (Object)string2).sendToTarget();
        this.onContactStatusUpdate(string2);
    }

    @Override
    public void onPostLayout() {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void onPreMeasure(SizeAwareLinearLayout var1_1, int var2_2, int var3_3) {
        this.mTextEditor.setMinHeight(0);
        this.mBottomPanel.measure(var2_2, 0);
        if (this.mTextEditor.getLineCount() <= 2 && !this.mWorkingMessage.hasAttachment() && this.mWorkingMessage.getSubject() == null) {
            this.mTextCounter.setVisibility(8);
        } else {
            this.mTextCounter.setVisibility(0);
        }
        if ((var3_3 = this.mMessageContentPanelMinHeight - this.mMessageContentPanel.getMeasuredHeight()) > 0) {
            this.mTextEditor.setMinHeight(var3_3 + this.mTextEditor.getMeasuredHeight());
        }
        var1_1 = (ViewGroup.MarginLayoutParams)this.mContentParent.getLayoutParams();
        var4_4 = var1_1.topMargin;
        var5_5 = var1_1.bottomMargin;
        var8_6 = var5_5 >= this.mSoftKeyboardMinHeight;
        if (var8_6 == this.mIsSoftInputEnabled) ** GOTO lbl-1000
        this.onSoftInputStateChange(var8_6);
        if (var8_6) lbl-1000: // 2 sources:
        {
            if (!this.mIsSoftInputEnabled && this.isAttachmentPanelVisible()) {
                this.disableAttachmentPanel();
            }
        }
        if (this.mIsLandscape && var8_6) {
            this.mAttachmentView.dismissPopup();
        }
        if (!this.mIsSwitchMenuBtn) {
            var1_1 = this.mSwitchBtn;
            var3_3 = this.isAttachmentPanelVisible() != false ? 2130837786 : 2130837790;
            var1_1.setBackgroundResource(var3_3);
        }
        this.mIsSoftInputEnabled = var8_6;
        var1_1 = this.getWindow().getWindowManager().getDefaultDisplay();
        var3_3 = var1_1.getHeight();
        var6_7 = this.mContentView.getPaddingTop();
        var7_8 = this.mTopPlaceholderHeight;
        var5_5 = this.mAttachmentProcessor.getAttachmentPanelHeight(var2_2, var5_5);
        var9_9 = new View[]{this.mBottomPanel};
        var5_5 = var3_3 - var6_7 - var4_4 - var7_8 - var5_5;
        var4_4 = 0;
        do {
            if (var4_4 >= var9_9.length) {
                this.mHistoryView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, var5_5, 0.0f));
                return;
            }
            var10_10 = var9_9[var4_4];
            var3_3 = var5_5;
            if (this.isVisible(var10_10)) {
                var10_10.measure(var2_2, View.MeasureSpec.makeMeasureSpec((int)Integer.MIN_VALUE, (int)var1_1.getHeight()));
                if (var5_5 < var10_10.getMeasuredHeight()) {
                    var10_10.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, var5_5, 0.0f));
                    var3_3 = 0;
                } else {
                    var10_10.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2, 0.0f));
                    var3_3 = var5_5 - var10_10.getMeasuredHeight();
                }
            }
            ++var4_4;
            var5_5 = var3_3;
        } while (true);
    }

    @Override
    public void onPreMessageSent() {
        this.runOnUiThread(new Runnable(){

            @Override
            public void run() {
                MessageEditableActivityBase.this.resetMessageWithAnimation();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onProtocolChanged(final boolean bl) {
        final boolean bl2 = this.mAudioController == null ? false : this.mAudioController.isSendingMx2();
        this.runOnUiThread(new Runnable(){

            @Override
            public void run() {
                if (!bl2) {
                    MessageEditableActivityBase.this.toastConvertInfo(bl);
                }
                MessageEditableActivityBase.this.updateCounter(bl);
                MessageEditableActivityBase.this.postSwitchMsgType();
                MessageEditableActivityBase.this.mAttachmentProcessor.updateAttachmentTypeStates();
                if (bl2) {
                    MessageEditableActivityBase.this.mAudioController.resetIsSendingMx2();
                }
            }
        });
    }

    protected void onPushStatusChanged() {
        this.mQueryStatusHandler.obtainMessage(2).sendToTarget();
    }

    protected void onResetMessageAnimationEnd() {
    }

    protected void onResetMessageAnimationStart() {
    }

    protected void onRestart() {
        super.onRestart();
        this.onBottomPanelUpdate();
    }

    protected void onSoftInputStateChange(boolean bl) {
    }

    protected void onStart() {
        super.onStart();
        this.mAllowAnimation = this.allowAnimation();
        if (this.mWorkingMessage != null) {
            this.mWorkingMessage.syncWorkingRecipients();
        }
        this.updateContactNames();
        SmsTextSizeAdjust.getInstance().init(this, (Activity)this);
        SmsTextSizeAdjust.getInstance().refresh();
    }

    protected void onStop() {
        this.onBottomPanelStop();
        this.cancelUpdateContactNames();
        if (this.mTaskDialog != null && this.mTaskDialog.isShowing()) {
            this.mTaskDialog.dismiss();
        }
        this.mTaskDialog = null;
        SmsTextSizeAdjust.clear(this);
        super.onStop();
    }

    protected void postExit() {
        this.finish();
    }

    protected void postSwitchMsgType() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mSwitchMsgTypeRunnable);
            this.mHandler.postDelayed(this.mSwitchMsgTypeRunnable, 10);
            return;
        }
        Log.v((String)"MessageEditableActivityBase", (String)"postSwitchMsgType mHandler is null");
    }

    public void postUpdateSendButtonState() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mPostUpdateSendButtonStateRunnable);
            this.mHandler.postDelayed(this.mPostUpdateSendButtonStateRunnable, 10);
            return;
        }
        Log.v((String)"MessageEditableActivityBase", (String)"postUpdateSendButtonState mHandler is null");
    }

    protected void registerSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.registerChangeListener((Context)this, this.mSimInfoChangeListener);
            return;
        }
        MmsSystemEventReceiver.getInstance().registerSimStateChangedListener(this.mSimStateChagnedListener);
    }

    public void resetMessageWithAnimation() {
        this.onResetMessageAnimationStart();
        Animation animation = AnimationUtils.loadAnimation((Context)this.getBaseContext(), (int)2131034117);
        if (!this.mAllowAnimation) {
            animation.setDuration(0);
        }
        animation.setAnimationListener(new Animation.AnimationListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onAnimationEnd(Animation animation) {
                int n = MessageEditableActivityBase.this.mMessageContentPanel.getHeight();
                MessageEditableActivityBase.this.resetMessage();
                MessageEditableActivityBase.this.mMessageContentPanel.getLayoutParams().height = n;
                float f2 = n;
                float f3 = MessageEditableActivityBase.this.mMessageContentPanelMinHeight;
                long l = MessageEditableActivityBase.this.mAllowAnimation ? 100 : 0;
                LinearAnimator.start(f2, f3, l, new LinearAnimator.FrameHandler(){

                    @Override
                    public void onEnd() {
                        MessageEditableActivityBase.this.mMessageContentPanel.getLayoutParams().height = -2;
                        MessageEditableActivityBase.this.mMessageContentPanel.requestLayout();
                        MessageEditableActivityBase.this.mMessageContentPanel.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){

                            public boolean onPreDraw() {
                                MessageEditableActivityBase.this.onResetMessageAnimationEnd();
                                MessageEditableActivityBase.this.mMessageContentPanel.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
                                return true;
                            }
                        });
                    }

                    @Override
                    public void onFrame(float f2) {
                        MessageEditableActivityBase.this.mMessageContentPanel.getLayoutParams().height = (int)f2;
                        MessageEditableActivityBase.this.mMessageContentPanel.requestLayout();
                    }

                });
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

        });
        this.findViewById(2131820786).startAnimation(animation);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void saveDraft(boolean bl) {
        if (this.mWorkingMessage.isDiscarded()) {
            return;
        }
        if (!this.mWaitingForSubActivity && !this.mWorkingMessage.isWorthSaving()) {
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                Log.v((String)"MessageEditableActivityBase", (String)"saveDraft: not worth saving, discard WorkingMessage and bail");
            }
            this.mWorkingMessage.discard();
            return;
        }
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"MessageEditableActivityBase", (String)"saveDraft: call WorkingMessage.saveDraft");
        }
        this.mWorkingMessage.saveDraft(bl);
        if (!this.mToastForDraftSave) return;
        Toast.makeText((Context)this, (int)2131361872, (int)0).show();
    }

    public abstract void sendMessage(int var1);

    public void setTaskDialog(Dialog dialog) {
        this.mTaskDialog = dialog;
    }

    @Override
    public void setTextSize(float f2) {
        if (this.mTextEditor != null) {
            this.mTextEditor.setTextSize(0, f2);
        }
        if (this.mSubjectTextEditor != null) {
            this.mSubjectTextEditor.setTextSize(0, f2);
        }
    }

    public void setTiming() {
        long l;
        long l2 = l = this.mWorkingMessage.getTimeToSend();
        if (l == 0) {
            l2 = System.currentTimeMillis() + 600000;
        }
        this.mTimedMsgIndicator.setClickable(false);
        Intent intent = new Intent((Context)this, (Class)DateTimePickerActivity.class);
        intent.setType(DateTimePickerActivity.CONTENT_TYPE);
        intent.putExtra(DateTimePickerActivity.FIELD_TIME, l2);
        intent.putExtra(DateTimePickerActivity.FIELD_TITLE, this.getString(2131362152));
        this.startActivityForResult(intent, 4);
    }

    public void setTopPlaceholderHeight(int n) {
        ((ViewGroup.MarginLayoutParams)this.mMsgContentView.getLayoutParams()).topMargin = this.mTopPlaceholderHeight = n;
        this.mMsgContentView.requestLayout();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected boolean showSoftKeyboard() {
        View view;
        View view2 = view = this.mContentView.findFocus();
        if (view == null) {
            if (this.isVisible((View)this.mTextEditor)) {
                this.mTextEditor.requestFocus();
                view2 = this.mTextEditor;
            } else {
                view2 = view;
                if (this.isVisible((View)this.mSubjectTextEditor)) {
                    this.mSubjectTextEditor.requestFocus();
                    view2 = this.mSubjectTextEditor;
                }
            }
        }
        if (view2 == null) {
            return false;
        }
        this.mInputMethodManager.showSoftInput(view2, 0);
        return true;
    }

    public void startActivityForResult(Intent intent, int n) {
        if (n >= 0) {
            this.mWaitingForSubActivity = true;
        }
        try {
            super.startActivityForResult(intent, n);
            return;
        }
        catch (ActivityNotFoundException var1_2) {
            Toast.makeText((Context)this, (CharSequence)this.getString(2131362137), (int)0).show();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void startNicknamePicker(boolean bl) {
        ContactList contactList;
        this.mWorkingMessage.syncWorkingRecipients();
        if (!bl) {
            if (!this.hasRecipientsToRevise()) return;
        }
        if ((contactList = this.mConversation.getRecipients()).size() > 0) {
            String[] arrstring = new String[contactList.size()];
            int n = 0;
            do {
                if (n >= contactList.size()) {
                    contactList = new Intent("android.intent.action.PICK");
                    contactList.setType("vnd.android.cursor.item/nickname");
                    contactList.setPackage(MessageUtils.getContactsPackageName());
                    contactList.putExtra("numbers", arrstring);
                    contactList.putExtra("android.intent.extra.TEXT", this.getString(2131362049));
                    this.startActivityForResult((Intent)contactList, 3);
                    this.updateNicknameRevisedNumbers();
                    return;
                }
                arrstring[n] = ((Contact)contactList.get(n)).getNumber();
                ++n;
            } while (true);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setTitle(2131362049);
        builder.setIconAttribute(16843605);
        if (bl) {
            builder.setMessage(2131362050);
        } else {
            builder.setMessage(2131362051);
        }
        builder.setPositiveButton(17039370, null);
        builder.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void toggleSubject() {
        if (!this.isSubjectEditorVisible()) {
            this.mWorkingMessage.setSubject("", true);
            this.showSubjectEditor(true);
            this.mSubjectTextEditor.requestFocus();
            this.hideAudioViews();
        } else {
            this.mWorkingMessage.setSubject(null, true);
            this.showSubjectEditor(false);
            this.mTextEditor.requestFocus();
        }
        this.postSwitchMsgType();
    }

    protected void unRegisterSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.unregisterChangeListener((Context)this, this.mSimInfoChangeListener);
            return;
        }
        MmsSystemEventReceiver.getInstance().unRegisterSimStateChangedListener(this.mSimStateChagnedListener);
    }

    public void updateSendButtonState() {
        if (this.mMxStyle && !this.mAirModeOn && MSimUtils.isMSimSlotIdValid(this.mUseSlotId) && !this.hasMessage() && !this.isB2cMessageActivity()) {
            this.showAudioViews();
            return;
        }
        this.hideAudioViews();
    }

    protected void updateSlotButtonInfo() {
        String string = MSimUtils.getSimDisplayName((Context)this, 0);
        if (!TextUtils.isEmpty((CharSequence)string)) {
            this.mSendButton1Text.setText((CharSequence)string);
        }
        if (!TextUtils.isEmpty((CharSequence)(string = MSimUtils.getSimDisplayName((Context)this, 1)))) {
            this.mSendButton2Text.setText((CharSequence)string);
        }
    }

    protected void updateSlotButtonStateBySlotId(Context context, int n) {
        switch (n) {
            default: {
                this.mSendSlotIdView.setBackgroundResource(2130837880);
                this.mSimButtonContainer.setVisibility(0);
                this.mSendButton1Text.setTextAppearance(context, 2131689559);
                this.mSendButton2Text.setTextAppearance(context, 2131689559);
                this.mSendButton1Image.setImageResource(2130837970);
                this.mSendButton2Image.setImageResource(2130837971);
                return;
            }
            case 0: {
                this.mSendSlotIdView.setBackgroundResource(2130837881);
                this.mSimButtonContainer.setVisibility(8);
                this.mSendButton1Text.setTextAppearance(context, 2131689558);
                this.mSendButton2Text.setTextAppearance(context, 2131689559);
                this.mSendButton1Image.setImageResource(2130837769);
                this.mSendButton2Image.setImageResource(2130837971);
                return;
            }
            case 1: 
        }
        this.mSendSlotIdView.setBackgroundResource(2130837882);
        this.mSimButtonContainer.setVisibility(8);
        this.mSendButton2Text.setTextAppearance(context, 2131689558);
        this.mSendButton1Text.setTextAppearance(context, 2131689559);
        this.mSendButton2Image.setImageResource(2130837771);
        this.mSendButton1Image.setImageResource(2130837970);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void updateSlotRelatedState() {
        if (MSimUtils.isMSimInserted()) {
            this.mUseSlotId = MSimUtils.getPreferredSmsSlotId();
            if (this.mSendSlotIdView != null) {
                this.mSendSlotIdView.setVisibility(0);
            }
            this.updateSlotButtonInfo();
            return;
        } else {
            this.mUseSlotId = MSimUtils.getInsertedSlotId();
            if (this.mSimButtonContainer != null) {
                this.mSimButtonContainer.setVisibility(8);
            }
            if (this.mSendSlotIdView == null) return;
            {
                this.mSendSlotIdView.setVisibility(8);
                return;
            }
        }
    }

    protected abstract boolean willDiscardDraft();

    private class DiscardDraftListener
    implements DialogInterface.OnClickListener {
        private DiscardDraftListener() {
        }

        public void onClick(DialogInterface dialogInterface, int n) {
            MessageEditableActivityBase.this.mWorkingMessage.discard();
            dialogInterface.dismiss();
            MessageEditableActivityBase.this.finish();
        }
    }

    private class QueryMxStatusHandler
    extends Handler {
        private QueryMxStatusHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message object) {
            switch (object.what) {
                default: {
                    return;
                }
                case 1: 
                case 3: 
                case 4: {
                    String string = (String)object.obj;
                    MxIdCache.MxIdCacheItem mxIdCacheItem = MxIdCache.getOrQuery((Context)MessageEditableActivityBase.this, string, false);
                    Bundle bundle = new Bundle();
                    if (mxIdCacheItem != null) {
                        bundle.putLong("capability", mxIdCacheItem.getCapability());
                    }
                    object = MessageEditableActivityBase.this.mHandler.obtainMessage(object.what, (Object)string);
                    object.setData(bundle);
                    object.sendToTarget();
                    return;
                }
                case 2: {
                    MessageEditableActivityBase.this.mQueryStatusHandler.removeMessages(2);
                    object = MessageEditableActivityBase.this.mPendingRecipients.entrySet().iterator();
                    while (object.hasNext()) {
                        String string = (String)((Map.Entry)object.next()).getKey();
                        MessageEditableActivityBase.this.mQueryStatusHandler.obtainMessage(4, (Object)string).sendToTarget();
                    }
                    MessageEditableActivityBase.this.mQueryStatusHandler.obtainMessage(5).sendToTarget();
                    return;
                }
                case 5: 
            }
            MessageEditableActivityBase.this.mHandler.obtainMessage(5).sendToTarget();
        }
    }

}

