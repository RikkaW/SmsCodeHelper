/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.AsyncQueryHandler
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.DatabaseUtils
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Message
 *  android.os.RemoteException
 *  android.preference.PreferenceManager
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Sms
 *  android.text.SpannableString
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.ActionMode
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewStub
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.AnimationSet
 *  android.view.animation.TranslateAnimation
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.CheckBox
 *  android.widget.EditText
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.PopupWindow
 *  android.widget.PopupWindow$OnDismissListener
 *  android.widget.TextView
 *  android.widget.Toast
 *  com.google.android.collect.Lists
 *  com.google.android.collect.Maps
 *  com.google.android.mms.ContentType
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.PduBody
 *  com.google.android.mms.pdu.PduPart
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashSet
 *  miui.R
 *  miui.R$plurals
 *  miui.R$string
 *  miui.app.Activity
 *  miui.app.ProgressDialog
 *  miui.os.Build
 *  miui.view.EditActionMode
 *  miui.widget.GuidePopupWindow
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.provider.Telephony;
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
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.android.fileexplorer.service.IDirParse;
import com.android.fileexplorer.service.IQueryCallBack;
import com.android.mms.audio.AudioItemCache;
import com.android.mms.audio.DeleteCallback;
import com.android.mms.audio.Mx2DeleteHelper;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.android.mms.model.SlideshowModel;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.TimedMessageReceiver;
import com.android.mms.ui.AttachmentView;
import com.android.mms.ui.DateTimePickerActivity;
import com.android.mms.ui.MessageEditableActivityBase;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.MessageListPullView;
import com.android.mms.ui.MessageSelectCopyTextActivity;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.NewMessagePopupActivity;
import com.android.mms.ui.PesudoListView;
import com.android.mms.ui.PreviewImageLoader;
import com.android.mms.ui.SmsTextSizeAdjust;
import com.android.mms.ui.TextSizeAdjustSpan;
import com.android.mms.util.DirParseSDK;
import com.android.mms.util.EditableListView;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.R;
import miui.app.Activity;
import miui.app.ProgressDialog;
import miui.os.Build;
import miui.view.EditActionMode;
import miui.widget.GuidePopupWindow;

public abstract class ConversationBase
extends MessageEditableActivityBase
implements MessageListPullView.OnMoreListener {
    protected static ProgressDialog mBatchDeleteProgressDialog;
    private static String sSaveMmsPartToDiskPath;
    private boolean mBackPressed = false;
    protected BackgroundQueryHandler mBackgroundQueryHandler;
    protected int mBatchDeleteTaskCount;
    private View mBlackWindowLayer;
    private String mBodySubstitution;
    private IQueryCallBack.Stub mCallback;
    private ContentResolver mContentResolver;
    private Context mContext;
    private final MessageListAdapter.OnDataSetChangedListener mDataSetChangedListener;
    private Runnable mDelayOnContentChanged;
    private DeleteCallback mDeleteCallback;
    private String mHighlightText;
    private int mHoldMsgListNum;
    private boolean mInAnimation;
    private long mInitTargetMessageId;
    private boolean mIsPullDown;
    private boolean mIsStopped = false;
    private String mListItemStyle;
    protected PesudoListView mMessageListAnimator;
    protected View mMessageListBottomForeground;
    private final Handler mMessageListItemHandler;
    protected View mMessageListTopForeground;
    protected MessageListAdapter mMsgListAdapter;
    protected MessageListPullView mMsgListView;
    private int mMsgListViewTopOld;
    private GuidePopupWindow mMxGuideWindow;
    private boolean mNeedChangeSlotId = false;
    private boolean mNeedUpdateSendFailedNotify;
    private int mNewMessageCount;
    private long mOldTimeToSend;
    private Cursor mPendingChangeCursor;
    private boolean mPossiblePendingNotification;
    private Runnable mPostStartMsgListQuery;
    private final Handler mPullToMoreHandler;
    private TextView mSecurityAlertView;
    private boolean mSentMessage;
    private AlertDialog mSimPickerDialog;
    protected int mTitleBarTallBgHeight;
    protected boolean mWaitingResource;
    private boolean wasSoftKeyboardEnabled;

    static {
        sSaveMmsPartToDiskPath = null;
    }

    public ConversationBase() {
        this.mDeleteCallback = new DeleteCallback(){

            @Override
            public void onDeleteComplete(int n, Object object, int n2) {
                object = ConversationBase.this;
                --object.mBatchDeleteTaskCount;
                if (ConversationBase.this.mBatchDeleteTaskCount <= 0 && ConversationBase.mBatchDeleteProgressDialog != null) {
                    ConversationBase.mBatchDeleteProgressDialog.dismiss();
                    ConversationBase.mBatchDeleteProgressDialog = null;
                }
            }
        };
        this.mPostStartMsgListQuery = new Runnable(){

            @Override
            public void run() {
                ConversationBase.this.startMsgListQuery();
            }
        };
        this.mMessageListItemHandler = new Handler(){

            public void handleMessage(Message message) {
                switch (message.what) {
                    default: {
                        Log.w((String)"ConversationBase", (String)("Unknown message: " + message.what));
                        return;
                    }
                    case 3: 
                    case 9: {
                        ConversationBase.this.handleMessageReSendItem("mms", message);
                        return;
                    }
                    case 1: {
                        ConversationBase.this.handleMessageEditItem("mms", message);
                        return;
                    }
                    case 4: 
                    case 5: 
                    case 8: {
                        ConversationBase.this.handleMessageReSendItem("sms", message);
                        return;
                    }
                    case 2: {
                        ConversationBase.this.handleMessageEditItem("sms", message);
                        return;
                    }
                    case 12: {
                        ConversationBase.this.handleMessageMxGuide(message);
                        return;
                    }
                    case 101: {
                        ConversationBase.this.hideBlackWindowLayer();
                        return;
                    }
                    case 100: 
                }
                ConversationBase.this.hideSoftKeyboard();
                ConversationBase.this.showBlackWindowLayer();
            }
        };
        this.mDataSetChangedListener = new MessageListAdapter.OnDataSetChangedListener(){

            @Override
            public boolean needHoldCache() {
                if (ConversationBase.this.mMsgListView != null) {
                    return ConversationBase.this.mMsgListView.isDataSetChanged();
                }
                return false;
            }

            @Override
            public void onContentChanged(MessageListAdapter messageListAdapter) {
                if (ConversationBase.this.mHandler != null) {
                    ConversationBase.this.mHandler.removeCallbacks(ConversationBase.this.mDelayOnContentChanged);
                    ConversationBase.this.mHandler.postDelayed(ConversationBase.this.mDelayOnContentChanged, 50);
                }
            }

            @Override
            public void onDataSetChanged(MessageListAdapter messageListAdapter) {
                ConversationBase.this.mPossiblePendingNotification = true;
            }
        };
        this.mDelayOnContentChanged = new Runnable(){

            @Override
            public void run() {
                ConversationBase.this.mConversation.update();
                ConversationBase.this.startMsgListQuery();
            }
        };
        this.mPendingChangeCursor = null;
        this.mInAnimation = false;
        this.mWaitingResource = false;
        this.mPullToMoreHandler = new Handler(){

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message message) {
                if (message.what != 0) {
                    super.handleMessage(message);
                    return;
                }
                if (ConversationBase.this.mHoldMsgListNum > 20) {
                    ConversationBase.this.mMsgListViewTopOld = 21;
                    ConversationBase.access$2820(ConversationBase.this, 20);
                } else {
                    ConversationBase.this.mMsgListViewTopOld = ConversationBase.this.mHoldMsgListNum + 1;
                    ConversationBase.this.mHoldMsgListNum = 0;
                    ConversationBase.this.mMsgListView.setNeedMoreData(false);
                }
                ConversationBase.this.mIsPullDown = true;
                ConversationBase.this.startMsgListQuery();
                ConversationBase.this.mMsgListView.doneMore();
            }
        };
        this.mCallback = new IQueryCallBack.Stub(){

            @Override
            public void onQueryFinish() throws RemoteException {
            }

            @Override
            public boolean onQueryItem(String string, int n) throws RemoteException {
                return false;
            }

            @Override
            public void onQueryItemEnd(String string, String string2) throws RemoteException {
                if (TextUtils.isEmpty((CharSequence)string2)) {
                    return;
                }
                sSaveMmsPartToDiskPath = string2;
            }

            @Override
            public void onStartQuery(int n) throws RemoteException {
            }
        };
    }

    static /* synthetic */ void access$2000(ConversationBase conversationBase, DialogInterface.OnClickListener onClickListener, boolean bl, boolean bl2) {
        conversationBase.confirmDeleteDialog(onClickListener, bl, bl2);
    }

    static /* synthetic */ void access$2100(ConversationBase conversationBase, BatchDeleteListener batchDeleteListener, int n, boolean bl) {
        conversationBase.confirmBatchDeleteDialog(batchDeleteListener, n, bl);
    }

    static /* synthetic */ String access$2200() {
        return sSaveMmsPartToDiskPath;
    }

    static /* synthetic */ void access$2300(ConversationBase conversationBase) {
        conversationBase.doDirParseQuery();
    }

    static /* synthetic */ String access$2400(ConversationBase conversationBase, long l) {
        return conversationBase.copyMedia(l);
    }

    static /* synthetic */ void access$2500(ConversationBase conversationBase, MessageItem messageItem, boolean bl) {
        conversationBase.lockMessage(messageItem, bl);
    }

    static /* synthetic */ void access$2600(ConversationBase conversationBase, List list, boolean bl) {
        conversationBase.batchLockMessages(list, bl);
    }

    static /* synthetic */ void access$2700(ConversationBase conversationBase, List list) {
        conversationBase.gotoSelectCopyText(list);
    }

    static /* synthetic */ int access$2820(ConversationBase conversationBase, int n) {
        conversationBase.mHoldMsgListNum = n = conversationBase.mHoldMsgListNum - n;
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void batchLockMessages(final List<MessageItem> contentValues, boolean bl) {
        final StringBuilder stringBuilder = new StringBuilder();
        final StringBuilder stringBuilder2 = new StringBuilder();
        long l = this.mConversation.getThreadId();
        for (MessageItem messageItem : contentValues) {
            if (messageItem.isSms()) {
                if (messageItem.getThreadId() != l) continue;
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("_id").append(" in (");
                }
                stringBuilder.append(messageItem.getMsgId()).append(",");
                continue;
            }
            if (!messageItem.isMms() || !messageItem.isDownloaded() || messageItem.getThreadId() != l) continue;
            if (stringBuilder2.length() == 0) {
                stringBuilder2.append("_id").append(" in (");
            }
            stringBuilder2.append(messageItem.getMsgId()).append(",");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(")");
        }
        if (stringBuilder2.length() > 0) {
            stringBuilder2.deleteCharAt(stringBuilder2.length() - 1).append(")");
        }
        contentValues = new ContentValues(1);
        int n = bl ? 1 : 0;
        contentValues.put("locked", Integer.valueOf((int)n));
        new Thread(new Runnable(){

            @Override
            public void run() {
                if (stringBuilder.length() > 0) {
                    ConversationBase.this.getContentResolver().update(Telephony.Sms.CONTENT_URI, contentValues, stringBuilder.toString(), null);
                }
                if (stringBuilder2.length() > 0) {
                    ConversationBase.this.getContentResolver().update(Telephony.Mms.CONTENT_URI, contentValues, stringBuilder2.toString(), null);
                }
            }
        }, "lockMessage").start();
    }

    public static boolean cancelFailedDownloadNotification(Intent intent, Context context) {
        if (MessagingNotification.isFailedToDownload(intent)) {
            MessagingNotification.cancelNotification(context, 531);
            return true;
        }
        return false;
    }

    public static boolean cancelFailedToDeliverNotification(Intent intent, Context context) {
        if (MessagingNotification.isFailedToDeliver(intent)) {
            MessagingNotification.cancelNotification(context, 789);
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void changeCursor(Cursor cursor) {
        int n = this.handleNewSelectionPosition(cursor);
        this.handleNeedChangeSlotId((Context)this, cursor);
        boolean bl = this.mMsgListAdapter.getCursor() == null;
        int n2 = cursor.getCount() - this.mMsgListAdapter.getCount();
        if (!this.isReadOnly()) {
            if (n != -1) {
                this.mMsgListAdapter.changeCursor(cursor);
                this.mMsgListView.setSelection(this.mMsgListView.getHeaderViewsCount() + n);
            } else if (n2 > 0 || bl || this.mNewMessageCount > 0) {
                if (!(n2 <= 0 && this.mNewMessageCount <= 0 || bl || !this.mAllowAnimation || this.mMessageListAnimator.getVisibility() == 0 || this.mIsPullDown)) {
                    this.snapshotMsgList();
                }
                this.mMsgListAdapter.changeCursor(cursor);
                if (!this.mIsPullDown) {
                    this.mMsgListView.setNeedToScrollEnd(true);
                }
            } else {
                this.mMsgListAdapter.changeCursor(cursor);
            }
        } else {
            this.mMsgListAdapter.changeCursor(cursor);
        }
        this.handleShowSecurityAlert(cursor);
        if (this.mIsPullDown) {
            this.mMsgListView.setSelection(this.mMsgListViewTopOld);
            this.mIsPullDown = false;
        }
        if (cursor.getCount() == 0 && !this.mSentMessage && !this.isNewHmsConversation(cursor)) {
            this.mConversation.clearThreadId();
            this.finish();
        }
        this.dispatchCursorChanged(cursor);
        if (cursor.getCount() > 0) {
            this.mSentMessage = false;
        }
        if (this.mTextEditor != null) {
            this.mTextEditor.requestFocus();
        }
        if (this.mMessageListAnimator.getVisibility() == 0) {
            this.showMessageAnimation();
        }
    }

    private void checkPendingNotification() {
        if (this.mPossiblePendingNotification && this.hasWindowFocus()) {
            this.mConversation.setPreMarkUnread(true);
            new Thread(new Runnable(){

                @Override
                public void run() {
                    ConversationBase.this.mConversation.markAsReadSync();
                }
            }).start();
            this.mPossiblePendingNotification = false;
        }
    }

    private void clearMessageListAnimator() {
        for (int i = 0; i < this.mMessageListAnimator.getChildCount(); ++i) {
            View view = this.mMessageListAnimator.getChildAt(i);
            if (!(view instanceof MessageListItem)) continue;
            ((MessageListItem)view).unbind();
        }
        this.mMessageListAnimator.removeAllViews();
        this.mMessageListAnimator.clearDisappearingChildren();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void confirmBatchDeleteDialog(final BatchDeleteListener batchDeleteListener, int n, boolean bl) {
        View view = View.inflate((Context)this, (int)2130968602, (ViewGroup)null);
        ((TextView)view.findViewById(2131820614)).setText((CharSequence)this.getString(2131362130, new Object[]{n}));
        final CheckBox checkBox = (CheckBox)view.findViewById(2131820615);
        if (!bl || this.isGroup()) {
            checkBox.setVisibility(8);
        } else {
            batchDeleteListener.setDeleteLockedMessage(checkBox.isChecked());
            checkBox.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    batchDeleteListener.setDeleteLockedMessage(checkBox.isChecked());
                }
            });
        }
        new AlertDialog.Builder((Context)this).setTitle(2131361915).setIconAttribute(16843605).setCancelable(true).setPositiveButton(2131361930, (DialogInterface.OnClickListener)batchDeleteListener).setNegativeButton(2131361892, null).setView(view).show();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void confirmDeleteDialog(DialogInterface.OnClickListener onClickListener, boolean bl, boolean bl2) {
        String string2;
        int n = bl ? 2131361916 : 2131361915;
        String string3 = this.getString(n);
        if (bl2) {
            string3 = this.getString(2131361915);
            string2 = this.getString(2131361919);
        } else {
            n = bl ? 2131361921 : 2131361920;
            string2 = this.getString(n);
        }
        new AlertDialog.Builder((Context)this).setTitle((CharSequence)string3).setIconAttribute(16843605).setCancelable(true).setMessage((CharSequence)string2).setPositiveButton(2131361930, onClickListener).setNegativeButton(2131361892, null).show();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private String copyMedia(long l) {
        PduBody pduBody = PduBodyCache.getPduBody((Context)this, ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)l));
        if (pduBody == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n = pduBody.getPartsNum();
        int n2 = 0;
        while (n2 < n) {
            Object object = pduBody.getPart(n2);
            String string2 = new String(object.getContentType());
            if (ContentType.isImageType((String)string2) || ContentType.isVideoType((String)string2) || ContentType.isAudioType((String)string2) || string2.equals((Object)"application/ogg")) {
                if ((object = MessageUtils.saveMmsPartToDisk((Context)this, (PduPart)object, Long.toHexString((long)l))) == null) return null;
                stringBuilder.append("\n");
                if (TextUtils.isEmpty((CharSequence)sSaveMmsPartToDiskPath)) {
                    stringBuilder.append(MessageUtils.SAVE_MMS_PART_TO_DISK_PATH + (String)object);
                } else {
                    stringBuilder.append(sSaveMmsPartToDiskPath + "/" + (String)object);
                }
            }
            ++n2;
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void doDirParseQuery() {
        IDirParse iDirParse;
        ArrayList arrayList = new ArrayList();
        arrayList.add(MessageUtils.SAVE_MMS_PART_TO_DISK_PATH);
        try {
            iDirParse = DirParseSDK.getInstance().getService();
            if (arrayList.isEmpty() || iDirParse == null) return;
        }
        catch (Exception var1_2) {
            Log.e((String)"ConversationBase", (String)"doDirParseQuery exception", (Throwable)var1_2);
            return;
        }
        iDirParse.queryDirInfo((List<String>)arrayList, this.mCallback);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void editMessageItem(MessageItem messageItem) {
        if (messageItem.isSms()) {
            this.editSmsMessageItem(messageItem);
        } else {
            this.editMmsMessageItem(messageItem);
        }
        if (!messageItem.isFailedMessage() || this.mMsgListAdapter.getCount() <= 1) {
            // empty if block
        }
        this.postUpdateSendButtonState();
    }

    private void editMmsMessageItem(MessageItem messageItem) {
        this.mWorkingMessage.discard();
        this.mWorkingMessage = WorkingMessage.load(this, messageItem.getMessageUri(), false);
        this.mWorkingMessage.setConversation(this.mConversation);
        this.mAttachmentView.update(this.mWorkingMessage);
        this.mWorkingMessage.setSubject(messageItem.getSubject(), false);
        this.drawTopPanel();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void editSmsMessageItem(MessageItem messageItem) {
        Conversation conversation = this.mConversation;
        synchronized (conversation) {
            if (this.mConversation.getMessageCount() <= 1) {
                this.mConversation.clearThreadId();
            }
        }
        conversation = ContentUris.withAppendedId((Uri)Telephony.Sms.CONTENT_URI, (long)messageItem.getMsgId());
        SqliteWrapper.delete((Context)this, (ContentResolver)this.mContentResolver, (Uri)conversation, (String)null, (String[])null);
        this.mWorkingMessage.setText(messageItem.getBody());
    }

    private View getBlackWindowLayer() {
        if (this.mBlackWindowLayer == null) {
            this.mBlackWindowLayer = ((ViewStub)this.findViewById(2131820561)).inflate().findViewById(2131820559);
        }
        return this.mBlackWindowLayer;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private MessageItem getMessageItem(String string2, long l, boolean bl) {
        Cursor cursor;
        MessageListAdapter messageListAdapter = this.mMsgListAdapter;
        if (bl) {
            cursor = this.mMsgListAdapter.getCursor();
            do {
                return messageListAdapter.getCachedMessageItem(string2, l, cursor, false);
                break;
            } while (true);
        }
        cursor = null;
        return messageListAdapter.getCachedMessageItem(string2, l, cursor, false);
    }

    private void gotoSelectCopyText(List<MessageItem> list) {
        Intent intent = new Intent((Context)this, (Class)MessageSelectCopyTextActivity.class);
        MessageItem messageItem = list.get(0);
        intent.putExtra("extra_contact", messageItem.getContactName());
        if (messageItem.getContact() != null) {
            intent.putExtra("extra_number", messageItem.getContact().getNumber());
        }
        intent.putExtra("extra_body", MessageUtils.getMessagesText(list));
        this.startActivity(intent);
    }

    private void handleMessageEditItem(String object, Message message) {
        if ((object = this.getMessageItem((String)object, (Long)message.obj, false)) != null) {
            this.editMessageItem((MessageItem)object);
            this.drawBottomPanel();
        }
    }

    private void handleMessageMxGuide(Message message) {
        Activity activity = (Activity)this.mContext;
        if (this.mMxGuideWindow == null && activity.isResumed()) {
            this.mMxGuideWindow = new GuidePopupWindow(this.mContext);
            this.mMxGuideWindow.setGuideText(2131362342);
            this.mMxGuideWindow.setOutsideTouchable(true);
            this.mMxGuideWindow.setInputMethodMode(2);
            this.mMxGuideWindow.setOnDismissListener(new PopupWindow.OnDismissListener(){

                public void onDismiss() {
                    ConversationBase.this.mMxGuideWindow = null;
                }
            });
            this.mMxGuideWindow.show((View)message.obj, true);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void handleMessageReSendItem(String var1_1, Message var2_2) {
        var3_3 = true;
        if ((var1_1 = this.getMessageItem((String)var1_1, (Long)var2_2.obj, false)) == null) return;
        if (!var1_1.isSms()) ** GOTO lbl17
        this.mSentMessage = true;
        if (var2_2.what != 5) ** GOTO lbl8
        var1_1 = var1_1.resendMsgInGroup().iterator();
        ** GOTO lbl27
lbl8: // 1 sources:
        if (var2_2.what != 4) ** GOTO lbl14
        if (MessageUtils.requireDeliveryStatusBySlotId(this.mContext, var2_2.arg1)) {
            MSimUtils.moveMessageToFolder(this.mContext, var1_1.getMessageUri(), 6, 0, null, 32, 0);
        } else {
            MSimUtils.moveMessageToFolder(this.mContext, var1_1.getMessageUri(), 6, 0, null, null, 0);
        }
        ** GOTO lbl34
lbl14: // 1 sources:
        if (var2_2.what != 8) ** GOTO lbl34
        MSimUtils.moveMessageToFolder(this.mContext, var1_1.getMessageUri(), 6, 0, 0, null, 1);
        ** GOTO lbl34
lbl17: // 1 sources:
        if (var2_2.what != 9) {
            var3_3 = false;
        }
        if (var1_1.getMx2Type() != 3) {
            MessageUtils.resendMms(this.mContext, this, var1_1.getMessageUri(), var3_3, var1_1.getMx2Type());
            return;
        }
        if (this.isMx2AudioAvailable()) {
            MessageUtils.resendMms(this.mContext, this, var1_1.getMessageUri(), var3_3, var1_1.getMx2Type());
            return;
        }
        Toast.makeText((Context)this.mContext, (int)2131362358, (int)0).show();
        return;
lbl27: // 3 sources:
        while (var1_1.hasNext()) {
            var4_4 = (Uri)var1_1.next();
            if (MessageUtils.requireDeliveryStatusBySlotId(this.mContext, var2_2.arg1)) {
                MSimUtils.moveMessageToFolder(this.mContext, var4_4, 6, 0, null, 32, 0);
                continue;
            }
            MSimUtils.moveMessageToFolder(this.mContext, var4_4, 6, 0, null, null, 0);
        }
lbl34: // 5 sources:
        MSimUtils.sendQueuedMessage(this.mContext, var2_2.arg1);
    }

    private void handleNeedChangeSlotId(final Context context, Cursor cursor) {
        if (MSimUtils.isMSimInserted() && this.mNeedChangeSlotId) {
            long l = System.currentTimeMillis();
            if (cursor.moveToLast()) {
                int n = MSimUtils.getSlotIdBySimInfoId(cursor.getInt(36));
                if (MSimUtils.isMSimSlotIdValid(n)) {
                    this.mUseSlotId = n;
                }
                this.mHandler.post(new Runnable(){

                    @Override
                    public void run() {
                        ConversationBase.this.updateSlotButtonStateBySlotId(context, ConversationBase.this.mUseSlotId);
                    }
                });
                this.postUpdateSendButtonState();
            }
            Log.v((String)"ConversationBase", (String)("handleNeedChangeSlotId cost time is " + (System.currentTimeMillis() - l)));
        }
        this.mNeedChangeSlotId = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int handleNewSelectionPosition(Cursor cursor) {
        int n;
        long l;
        block2 : {
            int n2 = -1;
            l = System.currentTimeMillis();
            n = n2;
            if (this.mInitTargetMessageId != 0) {
                long l2;
                cursor.moveToPosition(-1);
                do {
                    n = n2;
                    if (!cursor.moveToNext()) break block2;
                    String string2 = cursor.getString(0);
                    l2 = cursor.getLong(1);
                    if ((!"sms".equals((Object)string2) || l2 != this.mInitTargetMessageId) && (!"mms".equals((Object)string2) || l2 != - this.mInitTargetMessageId)) continue;
                    n = cursor.getPosition();
                    break block2;
                } while (l2 != this.mInitTargetMessageId);
                n = cursor.getPosition();
            }
        }
        Log.v((String)"ConversationBase", (String)("handleNewSelectionPosition cost time is " + (System.currentTimeMillis() - l)));
        return n;
    }

    private void handleSmsReport() {
        new AsyncTask<Void, Void, Boolean>(){
            String mMsg;
            String mNumber;
            int mSimIndex;

            protected /* varargs */ Boolean doInBackground(Void ... object) {
                Object object2;
                block11 : {
                    Object object3;
                    block10 : {
                        block9 : {
                            if (TextUtils.isEmpty((CharSequence)this.mMsg)) {
                                this.mMsg = "null";
                                this.mSimIndex = 0;
                            }
                            try {
                                object3 = PushSession.getInstance(ConversationBase.this.mContext);
                                object = object2 = object3.getMyFullMid(this.mSimIndex);
                            }
                            catch (Exception var1_2) {
                                Log.w((String)"ConversationBase", (String)("Failed to post sms." + (Object)((Object)var1_2)));
                                return new Boolean(false);
                            }
                            if (!TextUtils.isEmpty((CharSequence)object2)) break block9;
                            object = "0";
                        }
                        object = object2 = object.substring(object.lastIndexOf(47) + 1);
                        if (!TextUtils.isEmpty((CharSequence)object2)) break block10;
                        object = "";
                    }
                    object2 = object3 = object3.getMyMid(this.mSimIndex);
                    if (!TextUtils.isEmpty((CharSequence)object3)) break block11;
                    object2 = "0";
                }
                object = new Boolean(SmsReportUtil.instance().postSms(ConversationBase.this.mContext, (String)object2, "", (String)object, this.mNumber, this.mMsg));
                return object;
            }

            protected void onPostExecute(Boolean bl) {
                if (bl.booleanValue()) {
                    Toast.makeText((Context)ConversationBase.this.mContext, (int)2131362386, (int)0).show();
                    return;
                }
                Toast.makeText((Context)ConversationBase.this.mContext, (int)2131362385, (int)0).show();
            }

            /*
             * Enabled aggressive block sorting
             */
            protected void onPreExecute() {
                ContactList contactList = ConversationBase.this.getRecipients();
                if (contactList != null && contactList.size() > 0) {
                    this.mNumber = ((Contact)contactList.get(0)).getNumber();
                }
                this.mMsg = null;
                this.mSimIndex = 0;
                contactList = ConversationBase.this.mMsgListAdapter.getCursor();
                if (contactList != null && contactList.getCount() > 0 && contactList.moveToLast()) {
                    String string = contactList.getString(0);
                    int n = -1;
                    if ("sms".equals((Object)string)) {
                        n = 4;
                    } else if ("mms".equals((Object)string)) {
                        n = 33;
                    }
                    if (n != -1 && 36 != -1) {
                        this.mMsg = contactList.getString(n);
                        n = MSimUtils.getSlotIdBySimInfoId(contactList.getLong(36));
                        if (MSimUtils.isMSimSlotIdValid(n)) {
                            this.mSimIndex = n;
                        }
                    }
                }
            }
        }.execute((Object[])new Void[0]);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean haveSomethingToCopyToSDCard(MessageItem messageItem) {
        if (messageItem.getMx2Type() > 0) {
            return true;
        }
        if ((messageItem = PduBodyCache.getPduBody((Context)this, ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)messageItem.getMsgId()))) == null) {
            return false;
        }
        int n = messageItem.getPartsNum();
        int n2 = 0;
        while (n2 < n) {
            String string2 = new String(messageItem.getPart(n2).getContentType());
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                Log.v((String)"ConversationBase", (String)("[CMA] haveSomethingToCopyToSDCard: part[" + n2 + "] contentType=" + string2));
            }
            if (ContentType.isImageType((String)string2)) return true;
            if (ContentType.isVideoType((String)string2)) return true;
            if (ContentType.isAudioType((String)string2)) return true;
            if (string2.equals((Object)"application/ogg")) return true;
            ++n2;
        }
        return false;
    }

    private void hideBlackWindowLayer() {
        if (this.getBlackWindowLayer().isShown()) {
            this.getBlackWindowLayer().setVisibility(8);
        }
    }

    private void initLayoutStyle() {
        this.mListItemStyle = MessageListItem.Style.bubble.toString();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)this);
        boolean bl = MessageUtils.getConversationGroupByTime(sharedPreferences, this.mListItemStyle);
        int n = MessageUtils.getDeliverReportMode(sharedPreferences, MessageUtils.getPrefNotificationEnabled((Context)this));
        this.mMsgListAdapter.initLayoutStyle(this.mListItemStyle, bl, n);
        this.mMsgListAdapter.setOnDataSetChangedListener(this.mDataSetChangedListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void lockMessage(MessageItem messageItem, boolean bl) {
        Uri uri = messageItem.isSms() ? Telephony.Sms.CONTENT_URI : Telephony.Mms.CONTENT_URI;
        messageItem = ContentUris.withAppendedId((Uri)uri, (long)messageItem.getMsgId());
        uri = new ContentValues(1);
        int n = bl ? 1 : 0;
        uri.put("locked", Integer.valueOf((int)n));
        new Thread(new Runnable((Uri)messageItem, (ContentValues)uri){
            final /* synthetic */ Uri val$lockUri;
            final /* synthetic */ ContentValues val$values;

            @Override
            public void run() {
                ConversationBase.this.getContentResolver().update(this.val$lockUri, this.val$values, null, null);
            }
        }, "lockMessage").start();
    }

    private void requestChangeCursor(Cursor cursor) {
        if (this.mPendingChangeCursor != null) {
            this.mPendingChangeCursor.close();
            this.mPendingChangeCursor = null;
        }
        if (this.mInAnimation || this.mWaitingResource) {
            this.mPendingChangeCursor = cursor;
            return;
        }
        this.changeCursor(cursor);
    }

    private void setSecurityAlertView(View object) {
        this.mSecurityAlertView = (TextView)object.findViewById(2131820765);
        object = this.getString(2131362382);
        SpannableString spannableString = new SpannableString((CharSequence)object);
        TextSizeAdjustSpan textSizeAdjustSpan = new TextSizeAdjustSpan((Context)this, 2131689553);
        textSizeAdjustSpan.needUnderline();
        spannableString.setSpan((Object)textSizeAdjustSpan, 0, object.length(), 33);
        this.mSecurityAlertView.append((CharSequence)spannableString);
        this.mSecurityAlertView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                object = String.format((String)ConversationBase.this.mContext.getResources().getString(2131362384), (Object[])new Object[]{((Contact)ConversationBase.this.getRecipients().get(0)).getNumber()});
                new AlertDialog.Builder(ConversationBase.this.mContext).setIconAttribute(16843605).setCancelable(true).setTitle(2131362383).setMessage((CharSequence)object).setPositiveButton(17039370, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        ConversationBase.this.handleSmsReport();
                        dialogInterface = PreferenceManager.getDefaultSharedPreferences((Context)ConversationBase.this.getApplicationContext());
                        if (!dialogInterface.getBoolean("already_remind_filter_stranger_mx_message", false)) {
                            if (!dialogInterface.getBoolean("pref_key_mx_filter_message_from_stranger", false)) {
                                new AlertDialog.Builder(ConversationBase.this.mContext).setIconAttribute(16843605).setCancelable(true).setTitle(2131362411).setMessage(2131362412).setPositiveButton(17039370, new DialogInterface.OnClickListener((SharedPreferences)dialogInterface){
                                    final /* synthetic */ SharedPreferences val$sp;

                                    public void onClick(DialogInterface dialogInterface, int n) {
                                        this.val$sp.edit().putBoolean("pref_key_mx_filter_message_from_stranger", true).commit();
                                    }
                                }).setNegativeButton(2131361892, null).show();
                            }
                            dialogInterface.edit().putBoolean("already_remind_filter_stranger_mx_message", true).commit();
                        }
                    }

                }).setNegativeButton(2131361892, null).show();
            }

        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setSecurityAlertVisibility(boolean bl) {
        if (this.mSecurityAlertView != null) {
            TextView textView = this.mSecurityAlertView;
            int n = bl ? 0 : 8;
            textView.setVisibility(n);
        }
    }

    private void setTimeToSend(long l) {
        MessageUtils.setMessageSendTime((Context)this, this.mConversation.getThreadId(), this.mOldTimeToSend, l);
        TimedMessageReceiver.scheduleNextTimedMsg((Context)this);
    }

    private void showBlackWindowLayer() {
        if (!this.getBlackWindowLayer().isShown()) {
            this.getBlackWindowLayer().setVisibility(0);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void showMessageAnimation() {
        View view;
        ArrayList arrayList;
        int n;
        ListAdapter listAdapter;
        ArrayList arrayList2;
        Object object;
        ArrayList arrayList3;
        Object object2;
        long l;
        block19 : {
            if (this.mInAnimation) {
                return;
            }
            this.mInAnimation = true;
            int n2 = this.mMessageListAnimator.getWidth();
            n = this.mMessageListAnimator.getHeight();
            this.mMsgListView.measure(View.MeasureSpec.makeMeasureSpec((int)n2, (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)n, (int)1073741824));
            this.mMsgListView.layout(this.mMessageListAnimator.getLeft(), this.mMessageListAnimator.getTop(), this.mMessageListAnimator.getRight(), this.mMessageListAnimator.getBottom());
            object2 = Maps.newHashMap();
            for (n = 0; n < this.mMessageListAnimator.getChildCount(); ++n) {
                object = this.mMessageListAnimator.getChildAt(n);
                if (!(object instanceof MessageListItem)) continue;
                l = (object = ((MessageListItem)object).getMessageItem()).isMms() ? - object.getMsgId() : object.getMsgId();
                object2.put((Object)l, (Object)n);
            }
            object = null;
            int n3 = this.mMsgListView.getChildAt(0).getTop();
            listAdapter = this.mMsgListView.getAdapter();
            arrayList = Lists.newArrayList();
            arrayList2 = Lists.newArrayList();
            arrayList3 = Lists.newArrayList();
            n = 1;
            for (int i = this.mMsgListView.getFirstVisiblePosition(); i < listAdapter.getCount() - this.mMsgListView.getFooterViewsCount(); ++i) {
                if (n3 < this.mMessageListAnimator.getHeight()) {
                    int n4;
                    listAdapter.getItemViewType(i);
                    view = listAdapter.getView(i, null, (ViewGroup)this.mMsgListView);
                    if (i < this.mMsgListView.getHeaderViewsCount()) {
                        n4 = view.getHeight() + n3;
                        n3 = n;
                        n = n4;
                    } else {
                        view.measure(View.MeasureSpec.makeMeasureSpec((int)n2, (int)1073741824), 0);
                        if (view instanceof MessageListItem) {
                            MessageItem messageItem = ((MessageListItem)view).getMessageItem();
                            l = messageItem.isMms() ? - messageItem.getMsgId() : messageItem.getMsgId();
                            messageItem = (Integer)object2.get((Object)l);
                            if (messageItem == null) {
                                messageItem = new ViewGroup.MarginLayoutParams(n2, -2);
                                messageItem.setMargins(0, n3, 0, 0);
                                this.mMessageListAnimator.addView(view, (ViewGroup.LayoutParams)messageItem);
                                if (n != 0) {
                                    arrayList.add((Object)view);
                                } else {
                                    arrayList3.add((Object)view);
                                    arrayList2.add((Object)view);
                                }
                            } else {
                                arrayList2.clear();
                                messageItem = this.mMessageListAnimator.getChildAt(messageItem.intValue());
                                n = ((ViewGroup.MarginLayoutParams)messageItem.getLayoutParams()).topMargin;
                                if (n3 != n) {
                                    object = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float)(n3 - n));
                                    object.setDuration(300);
                                    messageItem.startAnimation((Animation)object);
                                }
                                object2.remove((Object)l);
                                n = 0;
                            }
                        }
                        int n5 = view.getMeasuredHeight();
                        n4 = n;
                        n = n5 + n3;
                        n3 = n4;
                    }
                    n4 = n;
                    n = n3;
                    n3 = n4;
                    continue;
                }
                if (n == 0) break block19;
            }
            if (this.mNewMessageCount > 0 && !arrayList.isEmpty()) {
                arrayList3.addAll((Collection)arrayList);
                arrayList2.add(arrayList.get(arrayList.size() - 1));
                arrayList.clear();
            } else {
                arrayList3.clear();
                arrayList.clear();
                arrayList2.clear();
            }
        }
        this.mNewMessageCount = 0;
        for (n = 0; n < arrayList.size(); ++n) {
            object = new AnimationSet(false);
            listAdapter = new TranslateAnimation(0.0f, 0.0f, -300.0f, 0.0f);
            l = this.mAllowAnimation ? 300 : 0;
            listAdapter.setDuration(l);
            view = new AlphaAnimation(0.0f, 1.0f);
            l = this.mAllowAnimation ? 300 : 0;
            view.setDuration(l);
            object.addAnimation((Animation)listAdapter);
            object.addAnimation((Animation)view);
            ((View)arrayList.get(n)).startAnimation((Animation)object);
        }
        for (n = 0; n < arrayList2.size(); ++n) {
            object = new AnimationSet(false);
            arrayList = new TranslateAnimation(0.0f, 0.0f, 300.0f, 0.0f);
            l = this.mAllowAnimation ? 300 : 0;
            arrayList.setDuration(l);
            listAdapter = new AlphaAnimation(0.0f, 1.0f);
            l = this.mAllowAnimation ? 300 : 0;
            listAdapter.setDuration(l);
            object.addAnimation((Animation)arrayList);
            object.addAnimation((Animation)listAdapter);
            ((View)arrayList2.get(n)).startAnimation((Animation)object);
        }
        for (n = 0; n < arrayList3.size() - arrayList2.size(); ++n) {
            object = new AlphaAnimation(0.0f, 1.0f);
            l = this.mAllowAnimation ? 300 : 0;
            object.setDuration(l);
            ((View)arrayList3.get(n)).startAnimation((Animation)object);
        }
        object2 = object2.values().iterator();
        while (object2.hasNext()) {
            n = (Integer)object2.next();
            arrayList2 = this.mMessageListAnimator.getChildAt(n);
            object = new AlphaAnimation(1.0f, 0.0f);
            object.setDuration(300);
            arrayList2.startAnimation((Animation)object);
        }
        if (object != null) {
            object.setAnimationListener(new Animation.AnimationListener(){

                public void onAnimationEnd(Animation animation) {
                    ConversationBase.this.mMessageListAnimator.setVisibility(4);
                    ConversationBase.this.clearMessageListAnimator();
                    ConversationBase.this.mMsgListView.setVisibility(0);
                    ConversationBase.this.mInAnimation = false;
                    ConversationBase.this.mHandler.post(new Runnable(){

                        @Override
                        public void run() {
                            ConversationBase.this.applyPendingCursor();
                        }
                    });
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

            });
            return;
        }
        this.mMessageListAnimator.setVisibility(4);
        this.mMsgListView.setVisibility(0);
        this.mInAnimation = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void snapshotMsgList() {
        int n = this.mMessageListAnimator.getWidth();
        this.clearMessageListAnimator();
        if (this.mMsgListView.getChildCount() == 0) {
            return;
        }
        int n2 = this.mMsgListView.getChildAt(0).getTop();
        ListAdapter listAdapter = this.mMsgListView.getAdapter();
        for (int i = this.mMsgListView.getFirstVisiblePosition(); i < listAdapter.getCount() - this.mMsgListView.getFooterViewsCount() && n2 < this.mMessageListAnimator.getHeight() + this.getBottomPanelHeight(); ++i) {
            listAdapter.getItemViewType(i);
            View view = listAdapter.getView(i, null, (ViewGroup)this.mMsgListView);
            if (i < this.mMsgListView.getHeaderViewsCount()) {
                n2 += view.getHeight();
                continue;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
            marginLayoutParams.setMargins(0, n2, 0, 0);
            this.mMessageListAnimator.addView(view, (ViewGroup.LayoutParams)marginLayoutParams);
            view.measure(View.MeasureSpec.makeMeasureSpec((int)n, (int)1073741824), 0);
            n2 += view.getMeasuredHeight();
        }
        this.mMessageListAnimator.setVisibility(0);
        this.mMsgListView.setVisibility(8);
    }

    private void updateSendFailedNotification() {
        final long l = this.mConversation.getThreadId();
        if (l <= 0) {
            return;
        }
        new Thread(new Runnable(){

            @Override
            public void run() {
                MessagingNotification.updateSendFailedNotificationForThread((Context)ConversationBase.this, l);
            }
        }, "updateSendFailedNotification").start();
    }

    protected void applyPendingCursor() {
        if (!this.mInAnimation && !this.mWaitingResource && this.mPendingChangeCursor != null) {
            this.changeCursor(this.mPendingChangeCursor);
            this.mPendingChangeCursor = null;
        }
    }

    protected String buildMsgListQueryLimit() {
        String string2 = "";
        if (this.mInitTargetMessageId == 0) {
            string2 = "" + this.mHoldMsgListNum + ",-1";
        }
        return string2;
    }

    protected void dispatchCursorChanged(Cursor cursor) {
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean bl;
        if (this.mBlackWindowLayer != null && this.mBlackWindowLayer.getVisibility() == 0) {
            return true;
        }
        if (this.mInitTargetMessageId != 0) {
            this.mInitTargetMessageId = 0;
            this.mMsgListView.setAllowTranscriptOnResize(true);
        }
        if (!(bl = SmsTextSizeAdjust.getInstance().dispatchTouchEvent(motionEvent))) {
            return super.dispatchTouchEvent(motionEvent);
        }
        this.mMsgListView.setAllowTranscriptOnResize(false);
        return bl;
    }

    protected void enableNotShowAllMsgList() {
        if (this.mConversation.getMessageCount() > 50 && this.mInitTargetMessageId == 0) {
            this.mHoldMsgListNum = this.mConversation.getMessageCount() - 50;
            this.mMsgListView.setNeedMoreData(true);
        }
    }

    @Override
    protected ContactList getRecipients() {
        if (this.mConversation != null) {
            return this.mConversation.getRecipients();
        }
        Log.v((String)"ConversationBase", (String)"getRecipients is null");
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void handleShowSecurityAlert(Cursor cursor) {
        long l;
        int n;
        boolean bl;
        int n2;
        block8 : {
            bl = false;
            if (this.isGroup() || this.mConversation == null || this.mConversation.isPrivate()) {
                Log.v((String)"ConversationBase", (String)"group, hms and private is not show warning info");
                return;
            }
            Object object = this.getRecipients();
            if (this.mInitTargetMessageId != 0) return;
            if (object == null) return;
            if (object.size() != 1) return;
            if (((Contact)object.get(0)).existsInDatabase()) return;
            l = System.currentTimeMillis();
            n2 = cursor.getCount();
            if (n2 > 5) {
                n2 = 5;
            }
            if (n2 <= 0 || !cursor.moveToLast()) {
                n = 0;
            } else {
                int n3;
                int n4 = 0;
                do {
                    n3 = "sms".equals(object = cursor.getString(0)) ? 13 : ("mms".equals(object) ? 31 : -1);
                    n = n4;
                    if (n3 == -1) break block8;
                    if (cursor.getInt(n3) != 65537) {
                        n = n4;
                        break block8;
                    }
                    n = n3 = n4 + 1;
                    if (!cursor.moveToPrevious()) break block8;
                    n4 = n3;
                } while (n3 < n2);
                n = n3;
            }
        }
        boolean bl2 = bl;
        if (n2 != 0) {
            bl2 = bl;
            if (n >= n2) {
                bl2 = true;
            }
        }
        this.setSecurityAlertVisibility(bl2);
        Log.v((String)"ConversationBase", (String)("handleShowSecurityAlert cost time is " + (System.currentTimeMillis() - l)));
    }

    protected void initMessageList() {
        if (this.mMsgListAdapter != null) {
            return;
        }
        this.mMsgListAdapter = new MessageListAdapter((Context)this, null, this.mMsgListView, true, this.isGroup(), this.isReadOnly(), this.mConversation.getThreadId(), this.mHighlightText, this.mBodySubstitution, this.mInitTargetMessageId, this.mConversation.isPrivate(), this.mConversation.isB2c());
        this.mMsgListAdapter.setMsgListItemHandler(this.mMessageListItemHandler);
        this.mHoldMsgListNum = 0;
        this.mMsgListView.setAdapter((ListAdapter)this.mMsgListAdapter);
        this.mMsgListView.setItemsCanFocus(false);
        this.mMsgListView.setVisibility(0);
        this.mMsgListView.setOnMoreListener(this);
    }

    @Override
    protected void initResourceRefs() {
        super.initResourceRefs();
        this.mMsgListView = (MessageListPullView)this.findViewById(2131820761);
        this.mMessageListAnimator = (PesudoListView)this.findViewById(2131820762);
        View view = this.getLayoutInflater().inflate(2130968658, null);
        this.mMsgListView.addFooterView(view);
        this.setSecurityAlertView(view);
        this.setListViewEditModeListener();
        this.mMsgListView.setOnScrollListener(new AbsListView.OnScrollListener(){

            public void onScroll(AbsListView absListView, int n, int n2, int n3) {
            }

            public void onScrollStateChanged(AbsListView absListView, int n) {
                ConversationBase.this.onMsgListViewScrollStateChanged(absListView, n);
            }
        });
        if (this.mTextEditor != null) {
            this.mTextEditor.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ConversationBase.this.mMsgListView.setAllowTranscriptOnResize(true);
                }
            });
        }
        this.mMessageListTopForeground = this.findViewById(2131820763);
        this.mMessageListBottomForeground = this.findViewById(2131820764);
        this.mTitleBarTallBgHeight = this.getResources().getDimensionPixelSize(2131427367);
        this.setTopPlaceholderHeight(this.mTitleBarTallBgHeight);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void initialize(long l) {
        Object object;
        this.initializeConversation(l);
        object = this.getIntent();
        ConversationBase.cancelFailedToDeliverNotification((Intent)object, (Context)this);
        Uri uri = object.getData();
        if (uri != null) {
            try {
                String string2 = uri.getQueryParameter("select_id");
                if (string2 != null) {
                    this.mInitTargetMessageId = Long.parseLong((String)string2);
                    this.mHighlightText = uri.getQueryParameter("highlight_text");
                    this.mBodySubstitution = uri.getQueryParameter("body_substitution");
                    this.mMsgListView.setAllowTranscriptOnResize(false);
                }
            }
            catch (UnsupportedOperationException var4_4) {}
        }
        this.mNewMessageCount = object.getIntExtra("new_message_count", 0);
        this.wasSoftKeyboardEnabled = object.getBooleanExtra("was_soft_keyboard_on", false);
        this.initMessageList();
        this.initialize();
        object = this.getRecipients();
        if (object == null || object.isEmpty()) {
            Toast.makeText((Context)this, (int)2131361937, (int)0).show();
            this.finish();
            return;
        } else {
            this.updateSendButtonState();
            this.mIsPullDown = false;
            this.mMsgListViewTopOld = 0;
            if (!TextUtils.isEmpty((CharSequence)sSaveMmsPartToDiskPath)) return;
            {
                DirParseSDK.getInstance().init();
                return;
            }
        }
    }

    protected void initializeConversation(long l) {
        this.mConversation = Conversation.get(l);
    }

    protected boolean isGroup() {
        return false;
    }

    protected boolean isNewHmsConversation(Cursor cursor) {
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean isPreparedForSending() {
        if (this.mAirModeOn) {
            return false;
        }
        if (this.mWorkingMessage == null) return false;
        if (!this.mWorkingMessage.hasAttachment() && !this.mWorkingMessage.hasText()) return false;
        return MSimUtils.isMSimSlotIdValid(this.mUseSlotId);
    }

    protected boolean isReadOnly() {
        return false;
    }

    public boolean isRunning() {
        if (!this.mIsStopped) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void loadMessageContent() {
        if (this.wasSoftKeyboardEnabled) {
            Log.v((String)"ConversationBase", (String)"soft keyboard is popped up, put off querying msg list");
            this.mMsgListView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

                public void onGlobalLayout() {
                    if (ConversationBase.this.mIsSoftInputEnabled) {
                        Log.v((String)"ConversationBase", (String)"start querying msg onGlobalLayout");
                        ConversationBase.this.mHandler.removeCallbacks(ConversationBase.this.mPostStartMsgListQuery);
                        ConversationBase.this.mHandler.post(ConversationBase.this.mPostStartMsgListQuery);
                        ConversationBase.this.mMsgListView.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
                        ConversationBase.this.wasSoftKeyboardEnabled = false;
                        return;
                    }
                    Log.w((String)"ConversationBase", (String)"soft keyboard is not enabled");
                }
            });
        } else {
            this.mHandler.removeCallbacks(this.mPostStartMsgListQuery);
            this.mHandler.post(this.mPostStartMsgListQuery);
        }
        this.mNeedUpdateSendFailedNotify = true;
        this.drawBottomPanel();
    }

    @Override
    protected void onActivityResult(int n, int n2, Intent intent) {
        if (n == 5) {
            long l;
            this.mWaitingForSubActivity = false;
            this.mTimedMsgIndicator.setClickable(true);
            if (n2 == -1 && (l = intent.getLongExtra(DateTimePickerActivity.FIELD_TIME, -1)) != -1) {
                this.setTimeToSend(l);
            }
            return;
        }
        super.onActivityResult(n, n2, intent);
    }

    @Override
    public void onBackPressed() {
        this.mBackPressed = true;
        super.onBackPressed();
    }

    @Override
    protected void onChildSimInfoChanged() {
        if (MSimUtils.isMSimInserted()) {
            this.mNeedChangeSlotId = true;
            this.startMsgListQuery();
        }
    }

    @Override
    protected void onContactsUpdated(ContactList contactList) {
        super.onContactsUpdated(contactList);
        this.updateTitle(contactList);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mContentResolver = this.getContentResolver();
        this.mBackgroundQueryHandler = new BackgroundQueryHandler(this.mContentResolver);
        long l = this.getIntent().getLongExtra("thread_id", -1);
        Log.v((String)"ConversationBase", (String)("loading thread " + l));
        if (l <= 0) {
            this.finish();
            return;
        }
        this.initialize(l);
    }

    @Override
    protected void onDestroy() {
        if (this.mPendingChangeCursor != null) {
            this.mPendingChangeCursor.close();
            this.mPendingChangeCursor = null;
        }
        if (this.mMsgListAdapter != null) {
            this.mMsgListAdapter.changeCursor(null);
            this.mMsgListAdapter.cleanCache();
        }
        this.mBackgroundQueryHandler.cancelOperation(9700);
        this.mBackgroundQueryHandler.cancelOperation(9701);
        this.mBackgroundQueryHandler.cancelOperation(9702);
        this.mBackgroundQueryHandler.cancelOperation(9527);
        if (mBatchDeleteProgressDialog != null) {
            mBatchDeleteProgressDialog.dismiss();
            mBatchDeleteProgressDialog = null;
        }
        if (this.mMxGuideWindow != null) {
            this.mMxGuideWindow.dismiss();
        }
        this.mMxGuideWindow = null;
        if (DirParseSDK.getInstance().getService() != null) {
            DirParseSDK.getInstance().close();
        }
        super.onDestroy();
    }

    @Override
    public void onMessageSent() {
        super.onMessageSent();
        if (this.mMsgListAdapter.getCount() == 0) {
            this.startMsgListQuery();
        }
    }

    @Override
    public void onMore() {
        Message message = new Message();
        message.what = 0;
        this.mPullToMoreHandler.sendMessageDelayed(message, 700);
    }

    protected void onMsgListViewScrollStateChanged(AbsListView absListView, int n) {
        if (n == 1) {
            this.hideSoftKeyboard();
            this.disableAttachmentPanel();
            this.mMsgListView.setNeedToScrollEnd(false);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    protected void onResetMessageAnimationEnd() {
        if (!this.mAllowAnimation) {
            return;
        }
        this.mInAnimation = false;
        this.applyPendingCursor();
    }

    @Override
    protected void onResetMessageAnimationStart() {
        if (!this.mAllowAnimation) {
            return;
        }
        this.mInAnimation = true;
        this.snapshotMsgList();
    }

    protected void onResume() {
        super.onResume();
        NewMessagePopupActivity.dismiss((Context)this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.mIsStopped = false;
        this.initLayoutStyle();
        this.loadMessageContent();
        this.updateTitle(this.getRecipients());
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onStop() {
        super.onStop();
        this.mIsStopped = true;
        if (this.mPendingChangeCursor != null) {
            this.mPendingChangeCursor.close();
            this.mPendingChangeCursor = null;
        }
        if (this.mBackPressed && this.mMsgListAdapter != null) {
            this.mMsgListAdapter.changeCursor(null);
            this.mBackPressed = false;
        } else {
            this.mMsgListAdapter.setOnDataSetChangedListener(null);
        }
        PreviewImageLoader.getInstance().cancelAllRequests(this.isFinishing());
    }

    public void onUserInteraction() {
        this.checkPendingNotification();
    }

    public void onWindowFocusChanged(boolean bl) {
        if (bl) {
            this.checkPendingNotification();
        }
        super.onWindowFocusChanged(bl);
    }

    @Override
    public void sendMessage(int n) {
        this.checkAndSendMessage(true, n);
    }

    protected void setListViewEditModeListener() {
        this.mMsgListView.setEditModeListener(new ModeCallback());
    }

    protected void setSendTimeForSpecifiedMessage(MessageItem messageItem) {
        if (!messageItem.isTimed()) {
            return;
        }
        this.mTimedMsgIndicator.setClickable(false);
        this.mOldTimeToSend = messageItem.getDate();
        messageItem = new Intent("android.intent.action.PICK");
        messageItem.setType(DateTimePickerActivity.CONTENT_TYPE);
        messageItem.setPackage(this.getPackageName());
        messageItem.putExtra(DateTimePickerActivity.FIELD_TIME, this.mOldTimeToSend);
        messageItem.putExtra(DateTimePickerActivity.FIELD_TITLE, this.getString(2131362152));
        this.startActivityForResult((Intent)messageItem, 5);
    }

    @Override
    public void setTextSize(float f2) {
        super.setTextSize(f2);
        if (this.mMsgListAdapter != null) {
            this.mMsgListAdapter.setTextSize(f2);
        }
        if (this.mMsgListView != null && this.mMsgListView.getVisibility() == 0) {
            int n = this.mMsgListView.getChildCount();
            for (int i = 0; i < n; ++i) {
                View view = this.mMsgListView.getChildAt(i);
                if (view == null || !(view instanceof MessageListItem)) continue;
                ((MessageListItem)view).setBodyTextSize(f2);
            }
        }
    }

    protected abstract void startMsgListQuery();

    protected abstract void updateTitle(ContactList var1);

    @Override
    protected boolean willDiscardDraft() {
        return false;
    }

    protected final class BackgroundQueryHandler
    extends AsyncQueryHandler {
        public BackgroundQueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        protected void onDeleteComplete(int n, Object object, int n2) {
            object = ConversationBase.this;
            --object.mBatchDeleteTaskCount;
            if (ConversationBase.this.mBatchDeleteTaskCount <= 0 && ConversationBase.mBatchDeleteProgressDialog != null) {
                ConversationBase.mBatchDeleteProgressDialog.dismiss();
                ConversationBase.mBatchDeleteProgressDialog = null;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void onQueryComplete(int n, Object object, Cursor cursor) {
            switch (n) {
                default: {
                    return;
                }
                case 9527: {
                    if (ConversationBase.this.mIsStopped) {
                        if (cursor != null) {
                            cursor.close();
                        }
                    } else if (cursor != null) {
                        ConversationBase.this.requestChangeCursor(cursor);
                    }
                    if (!ConversationBase.this.mNeedUpdateSendFailedNotify) return;
                    ConversationBase.this.mNeedUpdateSendFailedNotify = false;
                    ConversationBase.this.updateSendFailedNotification();
                    return;
                }
            }
        }
    }

    private class BatchDeleteListener
    implements DialogInterface.OnClickListener {
        public ActionMode mActionMode;
        private boolean mDeleteLockedMessage;
        protected List<MessageItem> mSelectedMsgs;

        public BatchDeleteListener(List<MessageItem> list, ActionMode actionMode) {
            this.mSelectedMsgs = list;
            this.mActionMode = actionMode;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onClick(DialogInterface object, int n) {
            Object object2 = new HashSet();
            Object object3 = new HashSet();
            for (MessageItem messageItem : this.mSelectedMsgs) {
                if ("mms".equals((Object)messageItem.getType())) {
                    object3.add((Object)messageItem.getMsgId());
                    continue;
                }
                if (!"sms".equals((Object)messageItem.getType())) continue;
                if (ConversationBase.this.isGroup()) {
                    object2.add((Object)messageItem.getDate());
                    continue;
                }
                object2.add((Object)messageItem.getMsgId());
            }
            object = this.mDeleteLockedMessage || ConversationBase.this.isGroup() ? "" : "locked = 0";
            ConversationBase.this.mBatchDeleteTaskCount = 0;
            if (!object2.isEmpty()) {
                object2 = ConversationBase.this.isGroup() ? String.format((String)"(%s=%s AND (%s IN (%s)))", (Object[])new Object[]{"thread_id", ConversationBase.this.mConversation.getThreadId(), "date", TextUtils.join((CharSequence)",", (Iterable)object2)}) : "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)object2) + ")";
                ConversationBase conversationBase = ConversationBase.this;
                ++conversationBase.mBatchDeleteTaskCount;
                ConversationBase.this.mBackgroundQueryHandler.startDelete(9700, (Object)null, Telephony.Sms.CONTENT_URI, DatabaseUtils.concatenateWhere((String)object, (String)object2), null);
            }
            if (!object3.isEmpty()) {
                object2 = "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)object3) + ")";
                object3 = ConversationBase.this;
                ++object3.mBatchDeleteTaskCount;
                Mx2DeleteHelper.deleteMms(9700, null, Telephony.Mms.CONTENT_URI, DatabaseUtils.concatenateWhere((String)object, (String)object2), null, ConversationBase.this.mDeleteCallback, ConversationBase.this.mMsgListAdapter.getAudioItemCache());
            }
            if (ConversationBase.this.mBatchDeleteTaskCount > 0) {
                ConversationBase.mBatchDeleteProgressDialog = ProgressDialog.show((Context)ConversationBase.this, (CharSequence)null, (CharSequence)ConversationBase.this.getString(2131362132), (boolean)true, (boolean)false);
            }
            this.mActionMode.finish();
        }

        public void setDeleteLockedMessage(boolean bl) {
            this.mDeleteLockedMessage = bl;
        }
    }

    private class DeleteMessageListener
    implements DialogInterface.OnClickListener {
        private final ActionMode mActionMode;
        private final boolean mDeleteLocked;
        private final Uri mDeleteUri;
        private final boolean mIsSms;
        private final long mTimeStamp;

        public DeleteMessageListener(Uri uri, boolean bl, long l, boolean bl2, ActionMode actionMode) {
            this.mDeleteUri = uri;
            this.mDeleteLocked = bl;
            this.mTimeStamp = l;
            this.mIsSms = bl2;
            this.mActionMode = actionMode;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onClick(DialogInterface dialogInterface, int n) {
            if (ConversationBase.this.isGroup()) {
                if (this.mIsSms) {
                    String string = String.format((String)"%s=%s AND %s=%s", (Object[])new Object[]{"thread_id", ConversationBase.this.mConversation.getThreadId(), "date", this.mTimeStamp});
                    ConversationBase.this.mBackgroundQueryHandler.startDelete(9700, (Object)null, Telephony.Sms.CONTENT_URI, string, null);
                } else {
                    ConversationBase.this.mBackgroundQueryHandler.startDelete(9700, (Object)null, this.mDeleteUri, null, null);
                }
            } else {
                Uri uri = this.mDeleteUri;
                String string = this.mDeleteLocked ? null : "locked=0";
                Mx2DeleteHelper.deleteMms(9700, null, uri, string, null, ConversationBase.this.mDeleteCallback, ConversationBase.this.mMsgListAdapter.getAudioItemCache());
            }
            dialogInterface.dismiss();
            this.mActionMode.finish();
        }
    }

    protected class ModeCallback
    implements EditableListView.EditModeListener {
        private EditableListView.EditableListViewCheckable mCheckable;
        private EditActionMode mEditActionMode;
        private Menu mRootMenu;

        protected ModeCallback() {
        }

        private List<MessageItem> getCheckedMessageItems() {
            HashSet<Integer> hashSet = this.mCheckable.getCheckedItemInPositions();
            return ConversationBase.this.mMsgListAdapter.getCheckedItems(hashSet);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        private void handleMenuItemClick(ActionMode var1_1, MenuItem var2_3) {
            var9_4 = this.getCheckedMessageItems();
            var3_5 = var2_3.getItemId();
            var4_6 = var9_4.size();
            if (var4_6 == 0) {
                if (var2_3.getItemId() == 16908313) {
                    var1_1.finish();
                    return;
                }
                if (var2_3.getItemId() != 16908314) {
                    Log.e((String)"ConversationBase", (String)String.format((String)"onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", (Object[])new Object[]{var4_6, var3_5}));
                    return;
                }
                if (this.mCheckable.isAllChecked()) {
                    this.mCheckable.checkNothing();
                    return;
                }
                this.mCheckable.checkAll();
                return;
            }
            if (var4_6 != 1 && var3_5 != 2131820914 && var3_5 != 2131820915 && var3_5 != 2131820913 && var3_5 != 2131820912 && var3_5 != 2131820911 && var3_5 != 16908313 && var3_5 != 16908314 && var3_5 != 2131820919) {
                Log.e((String)"ConversationBase", (String)String.format((String)"onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", (Object[])new Object[]{var4_6, var3_5}));
                return;
            }
            var7_7 = new DialogInterface.OnClickListener((ActionMode)var1_1){
                final /* synthetic */ ActionMode val$mode;

                public void onClick(DialogInterface dialogInterface, int n) {
                    this.val$mode.finish();
                }
            };
            var8_8 = var9_4.get(0);
            switch (var2_3.getItemId()) {
                default: {
                    return;
                }
                case 16908313: {
                    var1_1.finish();
                    return;
                }
                case 2131820912: {
                    MessageUtils.copyMessageTextToClipboard((Context)ConversationBase.this, var9_4);
                    var1_1.finish();
                    return;
                }
                case 2131820911: {
                    MessageUtils.forwardMessage((Context)ConversationBase.this, var9_4, ConversationBase.this.mConversation.isPrivate());
                    var1_1.finish();
                    return;
                }
                case 2131820916: {
                    var3_5 = (Integer)this.mCheckable.getCheckedItemInPositions().iterator().next();
                    try {
                        var1_1 = (Cursor)ConversationBase.this.mMsgListAdapter.getItem(var3_5);
                    }
                    catch (Exception var1_2) {
                        Log.e((String)"ConversationBase", (String)"onMenuItemClick: get cursor failed", (Throwable)var1_2);
                        return;
                    }
                    var1_1 = MessageUtils.getMessageDetails((Context)ConversationBase.this, (Cursor)var1_1, (MessageItem)var8_8);
                    new AlertDialog.Builder((Context)ConversationBase.this).setTitle(2131361941).setMessage((CharSequence)var1_1).setPositiveButton(17039370, var7_7).setCancelable(true).show();
                    return;
                }
                case 2131820913: {
                    if (1 == var4_6) {
                        var1_1 = new DeleteMessageListener(var8_8.getMessageUri(), var8_8.isLocked(), var8_8.getDate(), var8_8.isSms(), (ActionMode)var1_1);
                        ConversationBase.access$2000(ConversationBase.this, (DialogInterface.OnClickListener)var1_1, var8_8.isLocked(), var8_8.isGroup());
                        return;
                    }
                    var6_9 = false;
                    var1_1 = new BatchDeleteListener(var9_4, (ActionMode)var1_1);
                    var2_3 = var9_4.iterator();
                    do {
                        var5_10 = var6_9;
                        if (!var2_3.hasNext()) ** GOTO lbl58
                    } while (!((MessageItem)var2_3.next()).isLocked());
                    var5_10 = true;
lbl58: // 2 sources:
                    ConversationBase.access$2100(ConversationBase.this, (BatchDeleteListener)var1_1, var4_6, var5_10);
                    return;
                }
                case 2131820917: {
                    var2_3 = null;
                    if (TextUtils.isEmpty((CharSequence)ConversationBase.access$2200())) {
                        ConversationBase.access$2300(ConversationBase.this);
                    }
                    if (var8_8.getMx2Type() > 0) {
                        var8_8 = var8_8.getMx2Attachments();
                        var1_1 = var2_3;
                        if (var8_8 != null) {
                            var1_1 = var2_3;
                            if (var8_8.size() > 0) {
                                var1_1 = (Attachment)var8_8.get(0);
                                var1_1 = MxMessageUtils.saveAttachmentFileToDisk((Context)ConversationBase.this, (Attachment)var1_1);
                            }
                        }
                    } else {
                        var1_1 = ConversationBase.access$2400(ConversationBase.this, var8_8.getMsgId());
                    }
                    var2_3 = new AlertDialog.Builder((Context)ConversationBase.this);
                    var2_3.setTitle(2131362012);
                    var2_3.setIconAttribute(16843605);
                    if (var1_1 != null) {
                        var2_3.setMessage((CharSequence)ConversationBase.this.getString(2131362013, new Object[]{var1_1}));
                    } else {
                        var2_3.setMessage(2131362014);
                    }
                    var2_3.setNeutralButton(17039370, var7_7);
                    var2_3.show();
                    return;
                }
                case 2131820914: {
                    if (1 == var4_6) {
                        ConversationBase.access$2500(ConversationBase.this, (MessageItem)var8_8, true);
                    } else {
                        ConversationBase.access$2600(ConversationBase.this, var9_4, true);
                    }
                    var1_1.finish();
                    return;
                }
                case 2131820915: {
                    if (1 == var4_6) {
                        ConversationBase.access$2500(ConversationBase.this, (MessageItem)var8_8, false);
                    } else {
                        ConversationBase.access$2600(ConversationBase.this, var9_4, false);
                    }
                    var1_1.finish();
                    return;
                }
                case 2131820918: {
                    new SelectedMessage((Context)ConversationBase.this, (MessageItem)var8_8).saveMessageToSim();
                    var1_1.finish();
                    return;
                }
                case 16908314: {
                    if (this.mCheckable.isAllChecked()) {
                        this.mCheckable.checkNothing();
                        return;
                    }
                    this.mCheckable.checkAll();
                    return;
                }
                case 2131820919: 
            }
            if (Build.IS_CM_CUSTOMIZATION_TEST == false) return;
            ConversationBase.access$2700(ConversationBase.this, var9_4);
        }

        /*
         * Enabled aggressive block sorting
         */
        private void prepareMultipleSelectionMenu() {
            MenuItem menuItem;
            boolean bl = ConversationBase.this.getRecipients().size() > 1;
            boolean bl2 = ConversationBase.this.mConversation.isPrivate();
            int n = 1;
            int n2 = 1;
            boolean bl3 = false;
            boolean bl4 = false;
            boolean bl5 = false;
            boolean bl6 = false;
            long l = ConversationBase.this.mConversation.getThreadId();
            int n3 = 0;
            int n4 = 0;
            if (!bl) {
                menuItem = this.getCheckedMessageItems().iterator();
                do {
                    n = n2;
                    bl5 = bl6;
                    n3 = n4;
                    bl3 = bl4;
                    if (!menuItem.hasNext()) break;
                    MessageItem messageItem = (MessageItem)menuItem.next();
                    n = n2;
                    if (!messageItem.isLocked()) {
                        n = n2;
                        if (messageItem.getThreadId() == l) {
                            n = n2;
                            if (messageItem.isDownloaded()) {
                                n = 0;
                            }
                        }
                    }
                    bl5 = bl4;
                    if (messageItem.hasText()) {
                        bl5 = true;
                    }
                    bl3 = bl6;
                    if (messageItem.isDownloaded()) {
                        bl3 = true;
                    }
                    n2 = n;
                    bl6 = bl3;
                    bl4 = bl5;
                    if (messageItem.getMx2Type() != 3) continue;
                    n4 = 1;
                    n2 = n;
                    bl6 = bl3;
                    bl4 = bl5;
                } while (true);
            }
            n2 = this.mRootMenu.size();
            n4 = 0;
            while (n4 < n2) {
                menuItem = this.mRootMenu.getItem(n4);
                switch (menuItem.getItemId()) {
                    default: {
                        menuItem.setEnabled(false);
                        break;
                    }
                    case 2131820914: {
                        if (bl2) {
                            menuItem.setVisible(false);
                            break;
                        }
                        if (!bl5 || bl) {
                            menuItem.setVisible(true);
                            menuItem.setEnabled(false);
                            break;
                        }
                        if (n != 0) {
                            menuItem.setVisible(false);
                            break;
                        }
                        menuItem.setVisible(true);
                        menuItem.setEnabled(true);
                        break;
                    }
                    case 2131820915: {
                        if (bl2) {
                            menuItem.setVisible(false);
                            break;
                        }
                        if (!bl5 || bl) {
                            menuItem.setVisible(false);
                            break;
                        }
                        if (n != 0) {
                            menuItem.setVisible(true);
                            menuItem.setEnabled(true);
                            break;
                        }
                        menuItem.setVisible(false);
                        break;
                    }
                    case 2131820913: {
                        menuItem.setEnabled(true);
                        break;
                    }
                    case 2131820912: {
                        menuItem.setEnabled(bl3);
                        break;
                    }
                    case 2131820911: {
                        menuItem.setEnabled(bl5);
                        if (n3 == 0) break;
                        menuItem.setEnabled(false);
                        break;
                    }
                    case 2131820918: {
                        menuItem.setVisible(false);
                        break;
                    }
                    case 2131820919: {
                        menuItem.setVisible(false);
                    }
                }
                ++n4;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void prepareNoneSelectionMenu() {
            if (ConversationBase.this.getRecipients().size() > 1) {
                // empty if block
            }
            boolean bl = ConversationBase.this.mConversation.isPrivate();
            int n = this.mRootMenu.size();
            int n2 = 0;
            while (n2 < n) {
                MenuItem menuItem = this.mRootMenu.getItem(n2);
                switch (menuItem.getItemId()) {
                    default: {
                        menuItem.setEnabled(false);
                        break;
                    }
                    case 2131820914: {
                        if (bl) {
                            menuItem.setVisible(false);
                        } else {
                            menuItem.setVisible(true);
                        }
                        menuItem.setEnabled(false);
                        break;
                    }
                    case 2131820915: {
                        menuItem.setVisible(false);
                        break;
                    }
                    case 2131820918: {
                        menuItem.setVisible(false);
                        break;
                    }
                    case 2131820919: {
                        menuItem.setVisible(false);
                    }
                }
                ++n2;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void prepareSingleSelectionMenu() {
            List<MessageItem> list = this.getCheckedMessageItems();
            if (list.size() != 1) {
                Log.e((String)"ConversationBase", (String)("prepareSingleSelectionMenu: expect size=1 but size=" + list.size()));
                return;
            }
            list = list.get(0);
            if (ConversationBase.this.mConversation.isPrivate()) {
                this.mRootMenu.findItem(2131820914).setVisible(false);
                this.mRootMenu.findItem(2131820915).setVisible(false);
            } else if (!list.isGroup() && list.isDownloaded() && ConversationBase.this.mConversation.getThreadId() == list.getThreadId()) {
                if (list.isLocked()) {
                    this.mRootMenu.findItem(2131820915).setVisible(true);
                    this.mRootMenu.findItem(2131820915).setEnabled(true);
                    this.mRootMenu.findItem(2131820914).setVisible(false);
                } else if (!list.isLocked()) {
                    this.mRootMenu.findItem(2131820915).setVisible(false);
                    this.mRootMenu.findItem(2131820914).setVisible(true);
                    this.mRootMenu.findItem(2131820914).setEnabled(true);
                }
            } else {
                this.mRootMenu.findItem(2131820915).setVisible(false);
                this.mRootMenu.findItem(2131820914).setVisible(true);
                this.mRootMenu.findItem(2131820914).setEnabled(false);
            }
            this.mRootMenu.findItem(2131820913).setEnabled(true);
            this.mRootMenu.findItem(2131820912).setEnabled(list.hasText());
            MenuItem menuItem = this.mRootMenu.findItem(2131820916);
            boolean bl = !ConversationBase.this.mConversation.isB2c();
            menuItem.setEnabled(bl);
            if (Build.IS_CM_CUSTOMIZATION_TEST && !list.isGroup()) {
                this.mRootMenu.findItem(2131820919).setVisible(true);
                this.mRootMenu.findItem(2131820919).setEnabled(list.hasText());
            }
            if (list.isMms()) {
                if (ConversationBase.this.haveSomethingToCopyToSDCard((MessageItem)((Object)list))) {
                    this.mRootMenu.findItem(2131820917).setEnabled(true);
                } else {
                    this.mRootMenu.findItem(2131820917).setEnabled(false);
                }
                if (list.getMx2Type() == 3) {
                    this.mRootMenu.findItem(2131820911).setEnabled(false);
                } else {
                    this.mRootMenu.findItem(2131820911).setEnabled(list.isDownloaded());
                }
                this.mRootMenu.findItem(2131820918).setVisible(false);
                return;
            }
            this.mRootMenu.findItem(2131820917).setEnabled(false);
            this.mRootMenu.findItem(2131820911).setEnabled(true);
            if (MSimUtils.getInsertedSimCount() > 0 && !ConversationBase.this.mConversation.isB2c()) {
                this.mRootMenu.findItem(2131820918).setVisible(true);
                return;
            }
            this.mRootMenu.findItem(2131820918).setVisible(false);
        }

        private void showEditModeAnimation(boolean bl) {
            for (int i = 0; i < ConversationBase.this.mMsgListView.getChildCount(); ++i) {
                View view = ConversationBase.this.mMsgListView.getChildAt(i);
                if (!(view instanceof MessageListItem)) continue;
                ((MessageListItem)view).showEditModeAnimation(bl);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void updateMenu(int n) {
            String string2 = n == 0 ? ConversationBase.this.getString(R.string.action_mode_title_empty) : ConversationBase.this.getResources().getQuantityString(R.plurals.items_selected, this.mCheckable.count(), new Object[]{this.mCheckable.count()});
            ((ActionMode)this.mEditActionMode).setTitle((CharSequence)string2);
            if (this.mCheckable.isAllChecked()) {
                this.mEditActionMode.setButton(16908314, R.string.action_mode_deselect_all);
            } else {
                this.mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
            }
            if (n > 1) {
                this.prepareMultipleSelectionMenu();
                return;
            }
            if (n == 1) {
                this.prepareSingleSelectionMenu();
                return;
            }
            this.prepareNoneSelectionMenu();
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            this.handleMenuItemClick(actionMode, menuItem);
            int n = menuItem.getItemId();
            if (2131820911 == n || 2131820912 == n || 2131820916 == n || 2131820919 == n) {
                actionMode.finish();
            }
            return true;
        }

        @Override
        public void onCheckStateChanged(EditableListView.EditableListViewCheckable editableListViewCheckable) {
            this.mCheckable = editableListViewCheckable;
            ConversationBase.this.mMsgListAdapter.setCheckedItem(this.mCheckable.getCheckedItemInIds());
            this.updateMenu(this.mCheckable.count());
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.mRootMenu = menu;
            ConversationBase.this.getMenuInflater().inflate(2131755012, menu);
            ConversationBase.this.mMsgListView.setAllowTranscriptOnResize(false);
            ConversationBase.this.mMsgListAdapter.enterCheckMode();
            this.mCheckable = ConversationBase.this.mMsgListView.getEditableListViewCheckable();
            this.mEditActionMode = (EditActionMode)actionMode;
            this.updateMenu(0);
            ConversationBase.this.disableAttachmentPanel();
            ConversationBase.this.hideSoftKeyboard();
            ConversationBase.this.mBottomPanel.setVisibility(8);
            ConversationBase.this.mContactPanel.setVisibility(8);
            ConversationBase.this.setTopPlaceholderHeight(0);
            ConversationBase.this.mMessageListTopForeground.setVisibility(8);
            ConversationBase.this.mMessageListBottomForeground.setVisibility(8);
            this.showEditModeAnimation(true);
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            ConversationBase.this.mContactPanel.setVisibility(0);
            ConversationBase.this.mMsgListView.post(new Runnable(){

                @Override
                public void run() {
                    ConversationBase.this.mMsgListView.setAllowTranscriptOnResize(true);
                }
            });
            ConversationBase.this.mMsgListAdapter.exitCheckMode();
            ConversationBase.this.mBottomPanel.setVisibility(0);
            ConversationBase.this.setTopPlaceholderHeight(ConversationBase.this.mTitleBarTallBgHeight);
            ConversationBase.this.mMessageListTopForeground.setVisibility(0);
            ConversationBase.this.mMessageListBottomForeground.setVisibility(0);
            this.showEditModeAnimation(false);
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public void onVisibleViewCheckStateChanged(View view, boolean bl) {
        }

    }

    private static class PduBodyCache {
        private static PduBody mLastPduBody;
        private static Uri mLastUri;

        private PduBodyCache() {
        }

        public static PduBody getPduBody(Context context, Uri uri) {
            if (uri.equals((Object)mLastUri)) {
                return mLastPduBody;
            }
            try {
                mLastPduBody = SlideshowModel.getPduBody(context, uri);
                mLastUri = uri;
                return mLastPduBody;
            }
            catch (MmsException var0_1) {
                Log.e((String)"ConversationBase", (String)var0_1.getMessage(), (Throwable)var0_1);
                return null;
            }
        }
    }

    private class SelectedMessage {
        private Context mContext;
        private MessageItem mSelectedItem;

        public SelectedMessage(Context context, MessageItem messageItem) {
            this.mContext = context;
            this.mSelectedItem = messageItem;
        }

        private void createSimPickerDialog() {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            LinearLayout linearLayout = (LinearLayout)View.inflate((Context)this.mContext, (int)2130968704, (ViewGroup)null);
            linearLayout.addView(this.createSimPickerItem(0), (ViewGroup.LayoutParams)layoutParams);
            linearLayout.addView(this.createSimPickerItem(1), (ViewGroup.LayoutParams)layoutParams);
            ConversationBase.this.mSimPickerDialog = new AlertDialog.Builder(this.mContext).setTitle(2131362032).setView((View)linearLayout).show();
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        private View createSimPickerItem(final int var1_1) {
            var3_2 = (LinearLayout)View.inflate((Context)this.mContext, (int)2130968705, (ViewGroup)null);
            ((ImageView)var3_2.findViewById(2131820664)).setImageResource(MSimUtils.getSimIconResId(var1_1));
            var2_3 = null;
            switch (var1_1) {
                case 0: {
                    var2_3 = MSimUtils.isSimInserted(0) == false ? this.mContext.getString(2131362235) : MSimUtils.getSimDisplayName(this.mContext, 0);
                }
                default: {
                    ** GOTO lbl11
                }
                case 1: 
            }
            var2_3 = MSimUtils.isSimInserted(1) == false ? this.mContext.getString(2131362236) : MSimUtils.getSimDisplayName(this.mContext, 1);
lbl11: // 2 sources:
            ((TextView)var3_2.findViewById(2131820628)).setText((CharSequence)var2_3);
            var3_2.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SelectedMessage.this.saveToIcc(var1_1);
                    if (ConversationBase.this.mSimPickerDialog != null) {
                        ConversationBase.this.mSimPickerDialog.dismiss();
                        ConversationBase.this.mSimPickerDialog = null;
                    }
                }
            });
            return var3_2;
        }

        private void saveToIcc(final int n) {
            if (this.mSelectedItem.isSms()) {
                new AsyncTask<Void, Void, Integer>(){

                    /*
                     * Enabled force condition propagation
                     * Lifted jumps to return sites
                     */
                    protected /* varargs */ Integer doInBackground(Void ... arrvoid) {
                        int n2;
                        if (SelectedMessage.this.mSelectedItem.isOutMessage()) {
                            n2 = 5;
                            do {
                                return SimCardManager.getInstance(SelectedMessage.this.mContext).saveMessageToIcc(SelectedMessage.this.mSelectedItem.getAddress(), SelectedMessage.this.mSelectedItem.getBody(), SelectedMessage.this.mSelectedItem.getDate(), n2, n);
                                break;
                            } while (true);
                        }
                        n2 = 1;
                        return SimCardManager.getInstance(SelectedMessage.this.mContext).saveMessageToIcc(SelectedMessage.this.mSelectedItem.getAddress(), SelectedMessage.this.mSelectedItem.getBody(), SelectedMessage.this.mSelectedItem.getDate(), n2, n);
                    }

                    protected void onPostExecute(Integer n2) {
                        if (n2 == 1000) {
                            Toast.makeText((Context)SelectedMessage.this.mContext, (int)2131362241, (int)0).show();
                            return;
                        }
                        if (n2 == 1002) {
                            Toast.makeText((Context)SelectedMessage.this.mContext, (int)2131361800, (int)0).show();
                            return;
                        }
                        Toast.makeText((Context)SelectedMessage.this.mContext, (int)2131362242, (int)0).show();
                    }
                }.execute((Object[])new Void[0]);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void saveMessageToSim() {
            if (MSimUtils.isMSimInserted()) {
                this.createSimPickerDialog();
                return;
            } else {
                int n = MSimUtils.getInsertedSlotId();
                if (n == -1) return;
                {
                    this.saveToIcc(n);
                    return;
                }
            }
        }

    }

}

