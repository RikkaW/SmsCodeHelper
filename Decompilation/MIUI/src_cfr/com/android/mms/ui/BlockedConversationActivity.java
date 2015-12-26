/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Bundle
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.ActionMode
 *  android.view.Display
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.Window
 *  android.view.WindowManager
 *  android.widget.AdapterView
 *  android.widget.Button
 *  android.widget.CheckBox
 *  android.widget.FrameLayout
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashSet
 *  miui.R
 *  miui.R$plurals
 *  miui.R$string
 *  miui.app.ProgressDialog
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$MmsSms
 *  miui.provider.ExtraTelephony$Phonelist
 *  miui.telephony.PhoneNumberUtils
 *  miui.telephony.PhoneNumberUtils$PhoneNumber
 *  miui.telephony.SubscriptionManager
 *  miui.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 *  miui.view.EditActionMode
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.ConversationBase;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.MessageListPullView;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SingleRecipientConversationHeader;
import com.android.mms.ui.SizeAwareLinearLayout;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.util.EditableListView;
import com.android.mms.util.MSimUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.R;
import miui.app.ProgressDialog;
import miui.provider.ExtraTelephony;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.SubscriptionManager;
import miui.view.EditActionMode;

public class BlockedConversationActivity
extends ConversationBase {
    private static final Uri BLOCKED_MMS_URI;
    private static final Uri BLOCKED_SMS_URI;
    private AlertDialog mAlertDialog;
    private View mBlockedBottomPanel;
    private SingleRecipientConversationHeader mHeader;
    private boolean mIsFromSms = false;
    private Button mNoBlockButton;
    private View.OnClickListener mNoBlockClickListener;
    private String mNumber;
    private UnderstandLoader.RequestCallback mRequestCallback;

    static {
        BLOCKED_SMS_URI = Telephony.Sms.Inbox.CONTENT_URI.buildUpon().appendQueryParameter("blocked_flag", "1").build();
        BLOCKED_MMS_URI = Telephony.Mms.Inbox.CONTENT_URI.buildUpon().appendQueryParameter("blocked_flag", "1").build();
    }

    public BlockedConversationActivity() {
        this.mNoBlockClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                if (BlockedConversationActivity.this.mIsFromSms) {
                    view = new Intent("android.intent.action.VIEW");
                    view.putExtra("number", BlockedConversationActivity.this.mNumber);
                    view.putExtra("is_from_blocked", true);
                    view.putExtra("reply_address", BlockedConversationActivity.this.mNumber);
                    view.putExtra("thread_id", BlockedConversationActivity.this.mConversation.getThreadId());
                    view.setPackage("com.android.mms");
                    ComposeMessageRouterActivity.processIntent((Context)BlockedConversationActivity.this, (Intent)view);
                    BlockedConversationActivity.this.startActivity((Intent)view);
                    return;
                }
                new AlertDialog.Builder((Context)BlockedConversationActivity.this).setTitle(2131361924).setMessage(2131361923).setPositiveButton(2131361924, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        BlockedConversationActivity.this.unblockMessage(BlockedConversationActivity.this.mNumber);
                    }
                }).setNegativeButton(17039360, null).show();
            }

        };
    }

    private void confirmBatchDeleteDialog(BatchDeleteListener batchDeleteListener, int n) {
        View view = View.inflate((Context)this, (int)2130968602, (ViewGroup)null);
        ((TextView)view.findViewById(2131820614)).setText((CharSequence)this.getString(2131362130, new Object[]{n}));
        ((CheckBox)view.findViewById(2131820615)).setVisibility(8);
        new AlertDialog.Builder((Context)this).setTitle(2131361915).setIconAttribute(16843605).setCancelable(true).setPositiveButton(2131361930, (DialogInterface.OnClickListener)batchDeleteListener).setNegativeButton(2131361892, null).setView(view).show();
    }

    private void confirmDeleteDialog(DialogInterface.OnClickListener onClickListener) {
        String string2 = this.getString(2131361915);
        String string3 = this.getString(2131361920);
        new AlertDialog.Builder((Context)this).setTitle((CharSequence)string2).setIconAttribute(16843605).setCancelable(true).setMessage((CharSequence)string3).setPositiveButton(2131361930, onClickListener).setNegativeButton(2131361892, null).show();
    }

    private void confirmRestoreDialog(final RestoreListener restoreListener) {
        String string2 = this.getString(2131361926);
        String string3 = this.getString(2131361927);
        View view = LayoutInflater.from((Context)this).inflate(2130968713, null);
        final CheckBox checkBox = (CheckBox)view.findViewById(2131820890);
        checkBox.setChecked(false);
        checkBox.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (restoreListener != null) {
                    restoreListener.setUnblockChecked(checkBox.isChecked());
                }
            }
        });
        checkBox = new AlertDialog.Builder((Context)this);
        if (this.mAlertDialog == null) {
            this.mAlertDialog = checkBox.setTitle((CharSequence)string2).setMessage((CharSequence)string3).setView(view).setIconAttribute(16843605).setCancelable(true).setPositiveButton(2131361928, (DialogInterface.OnClickListener)restoreListener).setNegativeButton(17039360, null).show();
            return;
        }
        this.mAlertDialog.show();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void unblockMessage(String string2) {
        block17 : {
            String string3;
            block16 : {
                string3 = PhoneNumberUtils.PhoneNumber.parse((CharSequence)string2).getNormalizedNumber(false, true);
                string2 = this.getContentResolver().query(ExtraTelephony.Phonelist.CONTENT_URI, null, "number='" + string3 + "' and " + "type" + "='" + "1" + "' and " + "sync_dirty" + "<> " + 1, null, null);
                if (string2 != null) {
                    try {
                        if (!string2.moveToNext()) break block16;
                        int n = string2.getInt(string2.getColumnIndex("state"));
                        int n2 = string2.getInt(string2.getColumnIndex("sync_dirty"));
                        if (n == 0) {
                            string3 = new ContentValues();
                            string3.put("state", Integer.valueOf((int)2));
                            n = n2;
                            if (n2 == 3) {
                                n = 2;
                            }
                            string3.put("sync_dirty", Integer.valueOf((int)n));
                            this.getContentResolver().update(ContentUris.withAppendedId((Uri)ExtraTelephony.Phonelist.CONTENT_URI, (long)string2.getLong(0)), (ContentValues)string3, null, null);
                            break block17;
                        }
                        if (n == 1) {
                            if (n2 == 3 || n2 == 2) {
                                string3 = new ContentValues();
                                string3.put("sync_dirty", Integer.valueOf((int)1));
                                this.getContentResolver().update(Uri.withAppendedPath((Uri)ExtraTelephony.Phonelist.CONTENT_URI, (String)String.valueOf((long)string2.getLong(0))), (ContentValues)string3, null, null);
                            }
                            this.getContentResolver().delete(ContentUris.withAppendedId((Uri)ExtraTelephony.Phonelist.CONTENT_URI, (long)string2.getLong(0)), null, null);
                            break block17;
                        }
                        try {
                            if (!ExtraTelephony.isInHidingWhite((Context)this, (String)string3)) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("number", string3);
                                contentValues.put("state", Integer.valueOf((int)0));
                                contentValues.put("isdisplay", Integer.valueOf((int)0));
                                contentValues.put("type", "2");
                                this.getContentResolver().insert(ExtraTelephony.Phonelist.CONTENT_URI, contentValues);
                            }
                            break block17;
                        }
                        catch (Exception var4_3) {
                            var4_3.printStackTrace();
                        }
                        break block17;
                    }
                    catch (Throwable var4_4) {
                        throw var4_4;
                    }
                    finally {
                        if (string2 != null) {
                            string2.close();
                        }
                        break block17;
                    }
                }
            }
            if (!ExtraTelephony.isInHidingWhite((Context)this, (String)string3)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("number", string3);
                contentValues.put("state", Integer.valueOf((int)0));
                contentValues.put("type", "2");
                contentValues.put("isdisplay", Integer.valueOf((int)0));
                this.getContentResolver().insert(ExtraTelephony.Phonelist.CONTENT_URI, contentValues);
            }
        }
        string2 = new ContentValues();
        string2.put("block_type", Integer.valueOf((int)0));
        this.getContentResolver().update(Uri.withAppendedPath((Uri)ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI, (String)String.valueOf((long)this.mConversation.getThreadId())), (ContentValues)string2, null, null);
    }

    private void updateIntent(Context context) {
        Intent intent = this.getIntent();
        intent.putExtra("thread_id", Conversation.getBlockedConv(context, -1, intent.getStringExtra("number")).getThreadId());
    }

    @Override
    protected void drawBottomPanel() {
    }

    @Override
    protected void drawTopPanel() {
    }

    @Override
    protected void exit() {
        this.postExit();
    }

    @Override
    protected int getBottomPanelHeight() {
        return this.mBlockedBottomPanel.getHeight();
    }

    @Override
    protected int getContentViewResId() {
        return 2130968585;
    }

    @Override
    protected void handleIntent(Intent intent) {
    }

    @Override
    protected void initBottomPanelResourceRefs() {
    }

    @Override
    protected void initMessageList() {
        super.initMessageList();
        this.enableNotShowAllMsgList();
        this.mMsgListAdapter.setThreadAddress(this.mNumber);
        if (!TextUtils.isEmpty((CharSequence)this.mNumber)) {
            this.mWaitingResource = true;
            Log.v((String)"BlockedConversationActivity", (String)" begin request loading resources");
            if (this.mRequestCallback == null) {
                this.mRequestCallback = new UnderstandLoader.RequestCallback(){

                    @Override
                    public void onRequestDone(boolean bl) {
                        Log.v((String)"BlockedConversationActivity", (String)" request loading resources done");
                        BlockedConversationActivity.this.mWaitingResource = false;
                        BlockedConversationActivity.this.applyPendingCursor();
                    }
                };
            }
            UnderstandLoader.request(this.mNumber, this.mRequestCallback);
        }
        this.mMsgListView.setOnItemDoubleClickListener(new EditableListView.OnItemDoubleClickListener(){

            @Override
            public void onDoubleClick(AdapterView<?> adapterView, View view, int n, long l) {
                Log.v((String)"TAG", (String)("onDoubleClick " + n));
                if ((n -= BlockedConversationActivity.this.mMsgListView.getFirstVisiblePosition()) >= 0 && n < BlockedConversationActivity.this.mMsgListView.getChildCount() && BlockedConversationActivity.this.mMsgListView.getChildAt(n) instanceof MessageListItem) {
                    ((MessageListItem)BlockedConversationActivity.this.mMsgListView.getChildAt(n)).onMessageListItemDoubleClick();
                }
            }

            @Override
            public void onSingleClick(AdapterView<?> adapterView, View view, int n, long l) {
                if ((n -= BlockedConversationActivity.this.mMsgListView.getFirstVisiblePosition()) >= 0 && n < BlockedConversationActivity.this.mMsgListView.getChildCount() && BlockedConversationActivity.this.mMsgListView.getChildAt(n) instanceof MessageListItem) {
                    ((MessageListItem)BlockedConversationActivity.this.mMsgListView.getChildAt(n)).onMessageListItemClick();
                }
            }
        });
    }

    @Override
    protected void initResourceRefs() {
        super.initResourceRefs();
        this.mHeader = (SingleRecipientConversationHeader)this.findViewById(2131820798);
    }

    @Override
    protected void initialize(long l) {
        super.initialize(l);
        BlockedConversationActivity.cancelFailedDownloadNotification(this.getIntent(), (Context)this);
    }

    @Override
    protected void initializeBottomPanel() {
    }

    @Override
    protected void initializeConversation(long l) {
        this.mConversation = Conversation.getBlockedConv((Context)this, l, null);
    }

    @Override
    public void onBackPressed() {
        this.exit();
    }

    @Override
    protected void onBottomPanelStop() {
    }

    @Override
    protected void onBottomPanelUpdate() {
    }

    @Override
    protected void onContactAdded(Contact contact) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onCreate(Bundle bundle) {
        this.updateIntent((Context)this);
        super.onCreate(bundle);
        this.mIsFromSms = this.getIntent().getBooleanExtra("isFromSms", false);
        this.mNumber = "";
        if (this.getRecipients() != null && this.getRecipients().size() > 0) {
            this.mNumber = ((Contact)this.getRecipients().get(0)).getNumber();
        }
        this.mBlockedBottomPanel = this.findViewById(2131820562);
        bundle = this.mNoBlockButton = (Button)this.findViewById(2131820563);
        int n = this.mIsFromSms ? 2131362253 : 2131362252;
        bundle.setText(n);
        this.mNoBlockButton.setOnClickListener(this.mNoBlockClickListener);
    }

    @Override
    protected void onDestroy() {
        if (!TextUtils.isEmpty((CharSequence)this.mNumber)) {
            UnderstandLoader.destroy(this.mNumber, this.mRequestCallback);
        }
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        MessagingNotification.setCurrentMessageThreadId(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onPreMeasure(SizeAwareLinearLayout sizeAwareLinearLayout, int n, int n2) {
        sizeAwareLinearLayout = (ViewGroup.MarginLayoutParams)this.mContentParent.getLayoutParams();
        n2 = sizeAwareLinearLayout.topMargin;
        int n3 = sizeAwareLinearLayout.bottomMargin;
        sizeAwareLinearLayout = this.getWindow().getWindowManager().getDefaultDisplay();
        int n4 = sizeAwareLinearLayout.getHeight() - n2 - n3 - this.mContentView.getPaddingTop() - this.mTopPlaceholderHeight;
        View[] arrview = new View[]{this.mBlockedBottomPanel};
        n3 = 0;
        do {
            if (n3 >= arrview.length) {
                this.mHistoryView.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, n4, 0.0f));
                return;
            }
            View view = arrview[n3];
            n2 = n4;
            if (this.isVisible(view)) {
                view.measure(n, View.MeasureSpec.makeMeasureSpec((int)Integer.MIN_VALUE, (int)sizeAwareLinearLayout.getHeight()));
                if (n4 < view.getMeasuredHeight()) {
                    view.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, n4, 0.0f));
                    n2 = 0;
                } else {
                    view.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2, 0.0f));
                    n2 = n4 - view.getMeasuredHeight();
                }
            }
            ++n3;
            n4 = n2;
        } while (true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Object object = this.getRecipients();
        if (object != null && !object.isEmpty()) {
            object = object.iterator();
            while (object.hasNext()) {
                super.onContactAdded((Contact)object.next());
            }
        }
        MessagingNotification.setCurrentMessageThreadId(this.mConversation.getThreadId());
    }

    @Override
    protected void onStop() {
        super.onStop();
        ContentValues contentValues = new ContentValues();
        contentValues.put("read", Integer.valueOf((int)1));
        Uri uri = ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI.buildUpon().appendQueryParameter("blocked_flag", "1").build();
        this.getContentResolver().update(ContentUris.withAppendedId((Uri)uri, (long)this.mConversation.getThreadId()), contentValues, null, null);
    }

    @Override
    protected void postSwitchMsgType() {
    }

    @Override
    protected void registerSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.registerChangeListener((Context)this, this.mSimInfoChangeListener);
        }
    }

    @Override
    protected void setListViewEditModeListener() {
        this.mMsgListView.setEditModeListener(new BlockedModeCallback());
    }

    @Override
    protected void startMsgListQuery() {
        Log.v((String)"BlockedConversationActivity", (String)"querying blocked message list");
        Uri uri = this.mConversation.getBlockedUri();
        if (uri == null) {
            Log.i((String)"BlockedConversationActivity", (String)"conversation uri is null, it is a new conv");
            return;
        }
        uri = uri.buildUpon().appendQueryParameter("limit", this.buildMsgListQueryLimit()).build();
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"BlockedConversationActivity", (String)("startMsgListQuery for " + (Object)uri));
        }
        this.mBackgroundQueryHandler.cancelOperation(9527);
        try {
            this.mBackgroundQueryHandler.startQuery(9527, (Object)null, uri, MessageListAdapter.PROJECTION, null, null, null);
            return;
        }
        catch (SQLiteException var1_2) {
            SqliteWrapper.checkSQLiteException((Context)this, (SQLiteException)var1_2);
            return;
        }
    }

    @Override
    protected void unRegisterSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.unregisterChangeListener((Context)this, this.mSimInfoChangeListener);
        }
    }

    @Override
    protected void updateSlotButtonInfo() {
    }

    @Override
    protected void updateSlotButtonStateBySlotId(Context context, int n) {
    }

    @Override
    protected void updateSlotRelatedState() {
    }

    @Override
    protected void updateTitle(ContactList contactList) {
        this.mHeader.updateTitle(contactList);
    }

    private class BatchDeleteListener
    implements DialogInterface.OnClickListener {
        private ActionMode mActionMode;
        private List<MessageItem> mSelectedMsgs;

        public BatchDeleteListener(List<MessageItem> list, ActionMode actionMode) {
            this.mSelectedMsgs = list;
            this.mActionMode = actionMode;
        }

        public void onClick(DialogInterface object, int n) {
            Object object2 = new HashSet();
            object = new HashSet();
            for (MessageItem messageItem : this.mSelectedMsgs) {
                if (messageItem.getType().equals((Object)"mms")) {
                    object.add((Object)messageItem.getMsgId());
                    continue;
                }
                if (!messageItem.getType().equals((Object)"sms")) continue;
                object2.add((Object)messageItem.getMsgId());
            }
            BlockedConversationActivity.this.mBatchDeleteTaskCount = 0;
            if (!object2.isEmpty()) {
                object2 = "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)object2) + ")";
                BlockedConversationActivity blockedConversationActivity = BlockedConversationActivity.this;
                ++blockedConversationActivity.mBatchDeleteTaskCount;
                BlockedConversationActivity.this.mBackgroundQueryHandler.startDelete(9701, (Object)null, BLOCKED_SMS_URI, (String)object2, null);
            }
            if (!object.isEmpty()) {
                object = "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)object) + ")";
                object2 = BlockedConversationActivity.this;
                ++object2.mBatchDeleteTaskCount;
                BlockedConversationActivity.this.mBackgroundQueryHandler.startDelete(9701, (Object)null, BLOCKED_MMS_URI, (String)object, null);
            }
            if (BlockedConversationActivity.this.mBatchDeleteTaskCount > 0) {
                ConversationBase.mBatchDeleteProgressDialog = ProgressDialog.show((Context)BlockedConversationActivity.this, (CharSequence)null, (CharSequence)BlockedConversationActivity.this.getString(2131362132), (boolean)true, (boolean)false);
            }
            this.mActionMode.finish();
        }
    }

    private class BlockedModeCallback
    implements EditableListView.EditModeListener {
        private EditableListView.EditableListViewCheckable mCheckable;
        private EditActionMode mEditActionMode;
        private Menu mRootMenu;

        private BlockedModeCallback() {
        }

        private List<MessageItem> getCheckedMessageItems() {
            HashSet<Integer> hashSet = this.mCheckable.getCheckedItemInPositions();
            return BlockedConversationActivity.this.mMsgListAdapter.getCheckedItems(hashSet);
        }

        private void handleMenuItemClick(ActionMode object, MenuItem menuItem) {
            List<MessageItem> list = this.getCheckedMessageItems();
            int n = menuItem.getItemId();
            int n2 = list.size();
            if (n2 == 0) {
                if (menuItem.getItemId() == 16908313) {
                    object.finish();
                    return;
                }
                if (menuItem.getItemId() == 16908314) {
                    if (this.mCheckable.isAllChecked()) {
                        this.mCheckable.checkNothing();
                        return;
                    }
                    this.mCheckable.checkAll();
                    return;
                }
                Log.e((String)"BlockedConversationActivity", (String)String.format((String)"onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", (Object[])new Object[]{n2, n}));
                return;
            }
            if (n2 != 1 && n != 2131820902 && n != 2131820903 && n != 2131820904 && n != 16908313 && n != 16908314) {
                Log.e((String)"BlockedConversationActivity", (String)String.format((String)"onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", (Object[])new Object[]{n2, n}));
                return;
            }
            MessageItem messageItem = list.get(0);
            switch (menuItem.getItemId()) {
                default: {
                    return;
                }
                case 16908313: {
                    object.finish();
                    return;
                }
                case 2131820902: {
                    MessageUtils.copyMessageTextToClipboard((Context)BlockedConversationActivity.this, list);
                    object.finish();
                    return;
                }
                case 2131820903: {
                    if (1 == n2) {
                        object = new DeleteMessageListener(messageItem.getMessageUri(), messageItem.getDate(), messageItem.isSms(), (ActionMode)object);
                        BlockedConversationActivity.this.confirmDeleteDialog((DialogInterface.OnClickListener)object);
                        return;
                    }
                    object = new BatchDeleteListener(list, (ActionMode)object);
                    BlockedConversationActivity.this.confirmBatchDeleteDialog((BatchDeleteListener)object, n2);
                    return;
                }
                case 2131820904: {
                    BlockedConversationActivity.this.confirmRestoreDialog(new RestoreListener(list, (ActionMode)object));
                    return;
                }
                case 16908314: 
            }
            if (this.mCheckable.isAllChecked()) {
                this.mCheckable.checkNothing();
                return;
            }
            this.mCheckable.checkAll();
        }

        private void prepareNoneSelectionMenu() {
            int n = this.mRootMenu.size();
            for (int i = 0; i < n; ++i) {
                this.mRootMenu.getItem(i).setEnabled(false);
            }
        }

        private void prepareSelectionMenu() {
            int n = this.mRootMenu.size();
            for (int i = 0; i < n; ++i) {
                this.mRootMenu.getItem(i).setEnabled(true);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void updateMenu(int n) {
            String string2 = n == 0 ? BlockedConversationActivity.this.getString(R.string.action_mode_title_empty) : BlockedConversationActivity.this.getResources().getQuantityString(R.plurals.items_selected, this.mCheckable.count(), new Object[]{this.mCheckable.count()});
            ((ActionMode)this.mEditActionMode).setTitle((CharSequence)string2);
            if (this.mCheckable.isAllChecked()) {
                this.mEditActionMode.setButton(16908314, R.string.action_mode_deselect_all);
            } else {
                this.mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
            }
            if (n > 0) {
                this.prepareSelectionMenu();
                return;
            }
            this.prepareNoneSelectionMenu();
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            this.handleMenuItemClick(actionMode, menuItem);
            int n = menuItem.getItemId();
            if (2131820911 == n || 2131820912 == n || 2131820916 == n) {
                actionMode.finish();
            }
            return true;
        }

        @Override
        public void onCheckStateChanged(EditableListView.EditableListViewCheckable editableListViewCheckable) {
            this.mCheckable = editableListViewCheckable;
            BlockedConversationActivity.this.mMsgListAdapter.setCheckedItem(this.mCheckable.getCheckedItemInIds());
            this.updateMenu(this.mCheckable.count());
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.mRootMenu = menu;
            BlockedConversationActivity.this.getMenuInflater().inflate(2131755008, menu);
            this.prepareNoneSelectionMenu();
            BlockedConversationActivity.this.mMsgListView.setAllowTranscriptOnResize(false);
            BlockedConversationActivity.this.mMsgListAdapter.enterCheckMode();
            this.mCheckable = BlockedConversationActivity.this.mMsgListView.getEditableListViewCheckable();
            this.mEditActionMode = (EditActionMode)actionMode;
            this.updateMenu(0);
            BlockedConversationActivity.this.mBlockedBottomPanel.setVisibility(8);
            BlockedConversationActivity.this.mContactPanel.setVisibility(8);
            BlockedConversationActivity.this.setTopPlaceholderHeight(0);
            BlockedConversationActivity.this.mHistoryView.setForeground(null);
            for (int i = 0; i < BlockedConversationActivity.this.mMsgListView.getChildCount(); ++i) {
                actionMode = BlockedConversationActivity.this.mMsgListView.getChildAt(i);
                if (!(actionMode instanceof MessageListItem)) continue;
                ((MessageListItem)actionMode).showEditModeAnimation(true);
            }
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            BlockedConversationActivity.this.mContactPanel.setVisibility(0);
            BlockedConversationActivity.this.mMsgListView.post(new Runnable(){

                @Override
                public void run() {
                    BlockedConversationActivity.this.mMsgListView.setAllowTranscriptOnResize(true);
                }
            });
            BlockedConversationActivity.this.mMsgListAdapter.exitCheckMode();
            BlockedConversationActivity.this.mBlockedBottomPanel.setVisibility(0);
            BlockedConversationActivity.this.setTopPlaceholderHeight(BlockedConversationActivity.this.mTitleBarTallBgHeight);
            BlockedConversationActivity.this.mHistoryView.setForeground(BlockedConversationActivity.this.getResources().getDrawable(2130837856));
            for (int i = 0; i < BlockedConversationActivity.this.mMsgListView.getChildCount(); ++i) {
                actionMode = BlockedConversationActivity.this.mMsgListView.getChildAt(i);
                if (!(actionMode instanceof MessageListItem)) continue;
                ((MessageListItem)actionMode).showEditModeAnimation(false);
            }
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public void onVisibleViewCheckStateChanged(View view, boolean bl) {
        }

    }

    private class DeleteMessageListener
    implements DialogInterface.OnClickListener {
        private final ActionMode mActionMode;
        private final Uri mDeleteUri;

        public DeleteMessageListener(Uri uri, long l, boolean bl, ActionMode actionMode) {
            this.mDeleteUri = uri.buildUpon().appendQueryParameter("blocked_flag", "1").build();
            this.mActionMode = actionMode;
        }

        public void onClick(DialogInterface dialogInterface, int n) {
            BlockedConversationActivity.this.mBackgroundQueryHandler.startDelete(9701, (Object)null, this.mDeleteUri, null, null);
            dialogInterface.dismiss();
            this.mActionMode.finish();
        }
    }

    private class RestoreListener
    implements DialogInterface.OnClickListener {
        private ActionMode mActionMode;
        private boolean mChecked;
        private List<MessageItem> mSelectedMsgs;

        public RestoreListener(List<MessageItem> list, ActionMode actionMode) {
            this.mSelectedMsgs = list;
            this.mActionMode = actionMode;
            this.mChecked = false;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public void onClick(DialogInterface var1_1, int var2_2) {
            if (!this.mChecked) {
                var3_3 = new HashSet();
                var1_1 = new HashSet();
                var4_4 = this.mSelectedMsgs.iterator();
            } else {
                BlockedConversationActivity.access$200(BlockedConversationActivity.this, BlockedConversationActivity.access$100(BlockedConversationActivity.this));
lbl7: // 3 sources:
                do {
                    this.mActionMode.finish();
                    return;
                    break;
                } while (true);
            }
            while (var4_4.hasNext()) {
                var5_5 = var4_4.next();
                if (var5_5.getType().equals((Object)"mms")) {
                    var1_1.add((Object)var5_5.getMsgId());
                    continue;
                }
                if (!var5_5.getType().equals((Object)"sms")) continue;
                var3_3.add((Object)var5_5.getMsgId());
            }
            var4_4 = new ContentValues();
            var4_4.put("block_type", Integer.valueOf((int)0));
            if (!var3_3.isEmpty()) {
                var3_3 = "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)var3_3) + ")";
                BlockedConversationActivity.this.mBackgroundQueryHandler.startUpdate(9702, (Object)null, BlockedConversationActivity.access$800(), var4_4, (String)var3_3, null);
            }
            if (var1_1.isEmpty()) ** GOTO lbl7
            var1_1 = "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)var1_1) + ")";
            BlockedConversationActivity.this.mBackgroundQueryHandler.startUpdate(9702, (Object)null, BlockedConversationActivity.access$900(), var4_4, (String)var1_1, null);
            ** while (true)
        }

        public void setUnblockChecked(boolean bl) {
            this.mChecked = bl;
        }
    }

}

