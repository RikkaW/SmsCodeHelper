/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.ListActivity
 *  android.content.BroadcastReceiver
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.res.MiuiConfiguration
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.os.Message
 *  android.text.Editable
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.util.AttributeSet
 *  android.view.Display
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  android.widget.Toast
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.LinkedList
 *  miui.telephony.PhoneNumberUtils
 */
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
import android.os.IBinder;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.SendMessageService;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.ISmsTextSizeAdjustHost;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.NewMessagePopupListAdapter;
import com.android.mms.ui.SizeAwareLinearLayout;
import com.android.mms.ui.SmsTextSizeAdjust;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.transaction.PushSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import miui.telephony.PhoneNumberUtils;

public class NewMessagePopupActivity
extends ListActivity
implements TextWatcher,
View.OnClickListener,
Contact.UpdateListener,
ISmsTextSizeAdjustHost,
SizeAwareLinearLayout.OnMeasureListener,
MxIdCache.MxCacheStatusListener {
    private static NewMessagePopupActivity sInstance = null;
    private NewMessagePopupListAdapter mAdapter;
    private View mBottonEditor;
    private ImageView mButtonDelete;
    private ImageView mButtonDismiss;
    private SizeAwareLinearLayout mContentView;
    MessageThread mCurrentMessageThread = null;
    private int mDefaultCursorDrawableRes;
    private Handler mHandler;
    private IntentFilter mIntentFilter;
    private long mLastMxSmsTime1;
    private long mLastMxSmsTime2;
    private ListView mMessageList;
    private TextView mMessageSender;
    LinkedList<MessageThread> mMessageThreads = new LinkedList();
    private BroadcastReceiver mPushStatusReceiver;
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

    public NewMessagePopupActivity() {
        this.mPushStatusReceiver = new BroadcastReceiver(){

            public void onReceive(Context context, Intent intent) {
                NewMessagePopupActivity.this.updateMiMessageStyle();
            }
        };
        this.mHandler = new Handler(){

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message message) {
                switch (message.what) {
                    default: {
                        break;
                    }
                    case 1: {
                        NewMessagePopupActivity.this.updateMiMessageStyle();
                    }
                }
                super.handleMessage(message);
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void addMessage(MessageItem messageItem, String string2) {
        MessageThread messageThread;
        MessageThread messageThread2 = messageThread = this.findMessageThreadAndRemoveFromList(string2);
        if (messageThread == null) {
            messageThread2 = new MessageThread();
            messageThread2.address = string2;
            messageThread2.messageList = new ArrayList();
        }
        if (!this.contains(messageThread2.messageList, messageItem)) {
            messageThread2.messageList.add((Object)messageItem);
        }
        this.mMessageThreads.addFirst((Object)messageThread2);
        if (this.mCurrentMessageThread == messageThread2) {
            this.mCurrentMessageThread.markedRead = false;
            this.showCurrentThread(false);
            return;
        } else {
            if (!this.canSwitchThread()) return;
            {
                this.mCurrentMessageThread = messageThread2;
                this.showCurrentThread(true);
                return;
            }
        }
    }

    private boolean canSwitchThread() {
        if (this.mCurrentMessageThread == null || this.mTextEditor.getText().length() == 0) {
            return true;
        }
        return false;
    }

    private void cancelFloatNotification() {
        MessagingNotification.cancelFloatNotification((Context)this);
    }

    private boolean contains(ArrayList<MessageItem> arrayList, MessageItem messageItem) {
        for (int i = 0; i < arrayList.size(); ++i) {
            MessageItem messageItem2 = (MessageItem)arrayList.get(i);
            if (messageItem2.getMessageUri() == null || messageItem.getMessageUri() == null || !messageItem2.getMessageUri().equals((Object)messageItem.getMessageUri())) continue;
            return true;
        }
        return false;
    }

    private void deleteMessage() {
        this.mSendButton.setEnabled(false);
        SendMessageService.startMarkRead((Context)this, this.mCurrentMessageThread.address);
        ArrayList<MessageItem> arrayList = this.mCurrentMessageThread.messageList;
        for (int i = 0; i < arrayList.size(); ++i) {
            SendMessageService.startDelete((Context)this, ((MessageItem)arrayList.get(i)).getMessageUri());
        }
        this.proceedToNextThread();
    }

    public static void dismiss(Context context) {
        MessagingNotification.cancelFloatNotification(context);
        if (sInstance != null) {
            NewMessagePopupActivity.hideSoftInputMethod((Activity)sInstance);
            sInstance.finish();
            sInstance = null;
        }
    }

    private void dismissMessage() {
        this.mSendButton.setEnabled(false);
        this.markAsReadAsync(this.mCurrentMessageThread);
        this.proceedToNextThread();
    }

    private MessageThread findMessageThreadAndRemoveFromList(String string2) {
        Iterator iterator = this.mMessageThreads.iterator();
        while (iterator.hasNext()) {
            MessageThread messageThread = (MessageThread)iterator.next();
            if (!messageThread.address.equals((Object)string2)) continue;
            iterator.remove();
            return messageThread;
        }
        return null;
    }

    private long getCurrentThreadId() {
        return Conversation.get(ContactList.getByNumbers(this.mCurrentMessageThread.address, false)).getThreadId();
    }

    private void gotoConversation() {
        if (this.mTextEditor.getText().length() > 0) {
            ((ClipboardManager)this.getSystemService("clipboard")).setText((CharSequence)this.mTextEditor.getText());
            Toast.makeText((Context)this, (int)2131362142, (int)1).show();
        }
        ComposeMessageRouterActivity.route((Context)this, ComposeMessageRouterActivity.createIntent((Context)this, this.getCurrentThreadId()));
        this.finish();
    }

    private static void hideSoftInputMethod(Activity activity) {
        ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

    private boolean isMxAvailable(int n) {
        MxIdCache.MxIdCacheItem mxIdCacheItem = MxIdCache.getOrQuery((Context)this, Contact.get(this.mCurrentMessageThread.address).getMxPhoneNumber());
        if (MSimUtils.isSimInserted(n) && PushSession.getInstance((Context)this).isConnected(n) && mxIdCacheItem != null && mxIdCacheItem.allowSms()) {
            return true;
        }
        return false;
    }

    private void markAsReadAsync(MessageThread messageThread) {
        if (messageThread.markedRead) {
            return;
        }
        messageThread.markedRead = true;
        SendMessageService.startMarkRead((Context)this, messageThread.address);
    }

    private void proceedToNextThread() {
        Iterator iterator = this.mMessageThreads.iterator();
        while (iterator.hasNext()) {
            if ((MessageThread)iterator.next() != this.mCurrentMessageThread) continue;
            iterator.remove();
            break;
        }
        if (this.mMessageThreads.isEmpty()) {
            NewMessagePopupActivity.dismiss((Context)this);
            return;
        }
        this.mCurrentMessageThread = (MessageThread)this.mMessageThreads.getFirst();
        this.showCurrentThread(true);
        this.updateMiMessageStyle();
    }

    private void processMessage(Intent intent) {
        Uri uri = intent.getData();
        String string2 = intent.getStringExtra("body");
        long l = intent.getLongExtra("time", 0);
        this.mUseSlotId = MSimUtils.getSlotIdFromIntent(intent);
        this.addMessage(new MessageItem((Context)this, "sms", uri, l, string2, MSimUtils.getSimIdBySlotId(this.mUseSlotId)), intent.getStringExtra("from"));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void sendMessage() {
        boolean bl = false;
        this.mSendButton.setEnabled(false);
        long l = this.mUseSlotId == 1 ? this.mLastMxSmsTime1 : this.mLastMxSmsTime2;
        if (this.isMxAvailable(this.mUseSlotId) || System.currentTimeMillis() - Math.abs((long)l) <= 3000) {
            bl = true;
        }
        SendMessageService.startSend((Context)this, this.mCurrentMessageThread.address, this.mTextEditor.getText().toString(), bl, this.mUseSlotId);
        this.proceedToNextThread();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void showCurrentThread(boolean bl) {
        this.showCurrentThreadHeader();
        this.mAdapter.setThread(this.mCurrentMessageThread);
        this.mAdapter.notifyDataSetChanged();
        if (bl) {
            this.mTextEditor.setText((CharSequence)"");
            this.mLastMxSmsTime1 = 0;
            this.mLastMxSmsTime2 = 0;
        }
        Button button = this.mSendButton;
        bl = this.mTextEditor.getText().length() > 0;
        button.setEnabled(bl);
    }

    private void showCurrentThreadHeader() {
        this.updateCurrentThreadHeader(Contact.get(this.mCurrentMessageThread.address).load(false, true));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateCurrentThreadHeader(Contact contact) {
        String string2 = PhoneNumberUtils.parseTelocationString((Context)this, (CharSequence)this.mCurrentMessageThread.address);
        if (contact.existsInDatabase()) {
            this.mMessageSender.setText((CharSequence)contact.getName());
            if (TextUtils.isEmpty((CharSequence)string2)) {
                this.mSenderDetails.setText((CharSequence)contact.getNumber());
            } else {
                this.mSenderDetails.setText((CharSequence)(contact.getNumber() + " " + string2));
            }
        } else if (contact.isYellowPageNumber()) {
            this.mMessageSender.setText((CharSequence)contact.getName());
            this.mSenderDetails.setText((CharSequence)contact.getNumber());
        } else {
            this.mMessageSender.setText((CharSequence)contact.getNumber());
            if (TextUtils.isEmpty((CharSequence)string2)) {
                this.mSenderDetails.setVisibility(8);
                this.mSenderDetails.setText((CharSequence)"");
            } else {
                this.mSenderDetails.setVisibility(0);
                this.mSenderDetails.setText((CharSequence)string2);
            }
        }
        MessagingNotification.setCurrentMessageThreadId(this.getCurrentThreadId());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateMiMessageStyle() {
        boolean bl = this.isMxAvailable(0);
        boolean bl2 = this.isMxAvailable(1);
        if (bl) {
            if (this.mSendButton1 != null) {
                this.mSendButton1.setBackgroundResource(2130837958);
            }
            this.mLastMxSmsTime1 = 1;
        } else {
            if (this.mSendButton1 != null) {
                this.mSendButton1.setBackgroundResource(2130837961);
            }
            if (this.mLastMxSmsTime1 == 1) {
                this.mLastMxSmsTime1 = System.currentTimeMillis();
            }
        }
        if (bl2) {
            if (this.mSendButton2 != null) {
                this.mSendButton2.setBackgroundResource(2130837958);
            }
            this.mLastMxSmsTime2 = 1;
        } else {
            if (this.mSendButton2 != null) {
                this.mSendButton2.setBackgroundResource(2130837961);
            }
            if (this.mLastMxSmsTime2 == 1) {
                this.mLastMxSmsTime2 = System.currentTimeMillis();
            }
        }
        if (bl && this.mUseSlotId == 0 || bl2 && this.mUseSlotId == 1) {
            this.mBottonEditor.setBackgroundResource(2130837893);
            this.mSendButton.setBackgroundResource(2130837951);
            if (MiuiConfiguration.getScaleMode() == 11) {
                this.mTextEditor.setHint(2131362182);
            } else {
                this.mTextEditor.setHint(2131362183);
            }
            this.mTextEditor.setCursorDrawableRes(2130838007);
            return;
        }
        this.mBottonEditor.setBackgroundResource(2130837894);
        this.mSendButton.setBackgroundResource(2130837941);
        this.mTextEditor.setHint(2131362178);
        this.mTextEditor.setCursorDrawableRes(this.mDefaultCursorDrawableRes);
    }

    private void updateSlotButtonStateBySlotId(Context context, int n) {
        switch (n) {
            default: {
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

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
    }

    public void onBackPressed() {
        this.markAsReadAsync(this.mCurrentMessageThread);
        super.onBackPressed();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(View view) {
        switch (view.getId()) {
            default: {
                return;
            }
            case 2131820692: {
                if (this.mSimButtonContainer != null && this.mSimButtonContainer.getVisibility() == 0) {
                    this.mSimButtonContainer.setVisibility(8);
                }
                this.sendMessage();
                return;
            }
            case 2131820698: {
                this.mUseSlotId = 0;
                this.updateSlotButtonStateBySlotId((Context)this, this.mUseSlotId);
                this.updateMiMessageStyle();
                return;
            }
            case 2131820701: {
                this.mUseSlotId = 1;
                this.updateSlotButtonStateBySlotId((Context)this, this.mUseSlotId);
                this.updateMiMessageStyle();
                return;
            }
            case 2131820793: {
                if (this.mSimButtonContainer == null) return;
                if (this.mSimButtonContainer.getVisibility() == 8) {
                    this.mSimButtonContainer.setVisibility(0);
                    return;
                } else {
                    break;
                }
            }
        }
        this.mSimButtonContainer.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle bundle) {
        sInstance = this;
        super.onCreate(bundle);
        this.mContentView = (SizeAwareLinearLayout)this.getLayoutInflater().inflate(2130968680, null);
        this.mContentView.setOnMeasureListener(this);
        this.setContentView((View)this.mContentView);
        MessageUtils.setWindowLayoutParams((Activity)this);
        this.mButtonDelete = (ImageView)this.findViewById(2131820831);
        this.mButtonDismiss = (ImageView)this.findViewById(2131820835);
        this.mSenderPanel = this.findViewById(2131820832);
        this.mMessageSender = (TextView)this.findViewById(2131820833);
        this.mSenderDetails = (TextView)this.findViewById(2131820834);
        this.mMessageList = this.getListView();
        this.mTextEditor = (EditText)this.findViewById(2131820693);
        this.mTextCounter = (TextView)this.findViewById(2131820691);
        this.mSendButton = (Button)this.findViewById(2131820692);
        this.mBottonEditor = this.findViewById(2131820836);
        if (MSimUtils.isMSim()) {
            this.mSendSlotIdView = (ImageView)this.findViewById(2131820793);
            this.findViewById(2131820837).setVisibility(0);
            this.mSimButtonContainer = this.findViewById(2131820696);
            this.mSendButton1 = this.findViewById(2131820698);
            this.mSendButton1.setOnClickListener((View.OnClickListener)this);
            this.mSendButton1Text = (TextView)this.findViewById(2131820700);
            this.mSendButton1Image = (ImageView)this.findViewById(2131820699);
            this.mSendButton2 = this.findViewById(2131820701);
            this.mSendButton2.setOnClickListener((View.OnClickListener)this);
            this.mSendButton2Text = (TextView)this.findViewById(2131820703);
            this.mSendButton2Image = (ImageView)this.findViewById(2131820702);
        }
        bundle = this.getLayoutInflater().inflate(2130968658, null);
        this.mMessageList.addFooterView((View)bundle);
        this.mAdapter = new NewMessagePopupListAdapter((Activity)this);
        this.setListAdapter((ListAdapter)this.mAdapter);
        this.mButtonDelete.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NewMessagePopupActivity.this.deleteMessage();
            }
        });
        this.mButtonDismiss.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NewMessagePopupActivity.this.dismissMessage();
            }
        });
        this.mSendButton.setOnClickListener((View.OnClickListener)this);
        this.mSenderPanel.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NewMessagePopupActivity.this.gotoConversation();
            }
        });
        this.mTextEditor.addTextChangedListener((TextWatcher)this);
        this.mTextEditor.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
                if (n != 4) return false;
                if (!TextUtils.isEmpty((CharSequence)NewMessagePopupActivity.this.mTextEditor.getText())) {
                    NewMessagePopupActivity.this.sendMessage();
                    return false;
                }
                Toast.makeText((Context)NewMessagePopupActivity.this, (int)2131361865, (int)1).show();
                return false;
            }
        });
        this.mTextEditor.requestFocus();
        this.mDefaultCursorDrawableRes = MessageUtils.resolveDefaultCursorDrawableRes((Context)this);
        this.cancelFloatNotification();
        this.processMessage(this.getIntent());
        if (MSimUtils.isMSimInserted()) {
            if (this.mUseSlotId == 1) {
                this.mSendSlotIdView.setBackgroundResource(2130837882);
            } else {
                this.mSendSlotIdView.setBackgroundResource(2130837881);
            }
            this.mSendSlotIdView.setOnClickListener((View.OnClickListener)this);
            this.updateSlotButtonStateBySlotId((Context)this, this.mUseSlotId);
        } else {
            if (this.mSendSlotIdView != null) {
                this.mSendSlotIdView.setVisibility(8);
            }
            if (this.mSimButtonContainer != null) {
                this.mSimButtonContainer.setVisibility(8);
            }
        }
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction("com.xiaomi.mms.PUSH_STATUS_CHANGED");
        MxIdCache.addStatusListener(this);
    }

    protected void onDestroy() {
        MxIdCache.removeStatusListener(this);
        if (sInstance == this) {
            sInstance = null;
        }
        super.onDestroy();
    }

    protected void onListItemClick(ListView listView, View view, int n, long l) {
        this.gotoConversation();
    }

    @Override
    public void onMxIdAdded(String string2, String string3) {
    }

    @Override
    public void onMxIdOffline(String string2, String string3) {
        this.mHandler.obtainMessage(1).sendToTarget();
    }

    @Override
    public void onMxIdOnline(String string2, String string3) {
        this.mHandler.obtainMessage(1).sendToTarget();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.cancelFloatNotification();
        this.setIntent(intent);
        this.processMessage(intent);
    }

    protected void onPause() {
        this.unregisterReceiver(this.mPushStatusReceiver);
        Contact.removeListener(this);
        MessagingNotification.setCurrentMessageThreadId(0);
        super.onPause();
    }

    @Override
    public void onPostLayout() {
    }

    @Override
    public void onPreMeasure(SizeAwareLinearLayout sizeAwareLinearLayout, int n, int n2) {
        if (View.MeasureSpec.getMode((int)n2) != Integer.MIN_VALUE) {
            return;
        }
        if (this.mTextEditor.getLineCount() <= 2) {
            this.mTextCounter.setVisibility(8);
            return;
        }
        this.mTextCounter.setVisibility(0);
    }

    protected void onResume() {
        super.onResume();
        this.mAdapter.notifyDataSetChanged();
        this.updateMiMessageStyle();
        this.registerReceiver(this.mPushStatusReceiver, this.mIntentFilter);
        Contact.addListener(this);
        this.showCurrentThreadHeader();
    }

    protected void onStart() {
        super.onStart();
        SmsTextSizeAdjust.getInstance().init(this, (Activity)this);
        SmsTextSizeAdjust.getInstance().refresh();
    }

    protected void onStop() {
        SmsTextSizeAdjust.getInstance();
        SmsTextSizeAdjust.clear(this);
        super.onStop();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
        Button button = this.mSendButton;
        boolean bl = charSequence.length() > 0;
        button.setEnabled(bl);
        this.mTextCounter.setText((CharSequence)MessageUtils.getMessageStats(charSequence));
        if (charSequence.length() > 0) {
            this.markAsReadAsync(this.mCurrentMessageThread);
        }
    }

    @Override
    public void onUpdate(final Contact contact) {
        if (this.mHandler != null) {
            this.mHandler.post(new Runnable(){

                @Override
                public void run() {
                    if (Contact.get(NewMessagePopupActivity.this.mCurrentMessageThread.address) != contact || NewMessagePopupActivity.this.isFinishing()) {
                        return;
                    }
                    NewMessagePopupActivity.this.updateCurrentThreadHeader(contact);
                }
            });
        }
    }

    @Override
    public void setTextSize(float f2) {
        if (this.mTextEditor != null) {
            this.mTextEditor.setTextSize(0, f2);
        }
        if (this.mAdapter != null) {
            this.mAdapter.setTextSize(f2);
        }
    }

    public static class MessagePopupLayout
    extends SizeAwareLinearLayout {
        public MessagePopupLayout(Context context) {
            super(context);
        }

        public MessagePopupLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override
        protected void onMeasure(int n, int n2) {
            super.onMeasure(n, View.MeasureSpec.makeMeasureSpec((int)(((Activity)this.getContext()).getWindow().getWindowManager().getDefaultDisplay().getHeight() / 2), (int)Integer.MIN_VALUE));
        }
    }

    class MessageThread {
        public String address;
        public boolean markedRead;
        ArrayList<MessageItem> messageList;

        MessageThread() {
        }
    }

}

