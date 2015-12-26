/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  android.accounts.AccountManager
 *  android.accounts.OnAccountsUpdateListener
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Fragment
 *  android.content.AsyncQueryHandler
 *  android.content.ComponentName
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.OperationApplicationException
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.Looper
 *  android.os.Message
 *  android.os.RemoteException
 *  android.preference.PreferenceManager
 *  android.provider.MiuiSettings
 *  android.provider.MiuiSettings$AntiSpam
 *  android.provider.MiuiSettings$MiuiVoip
 *  android.provider.MiuiSettings$System
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$Sms
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.ActionMode
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnKeyListener
 *  android.view.ViewGroup
 *  android.view.ViewStub
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.AbsListView$RecyclerListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.CheckBox
 *  android.widget.HeaderViewListAdapter
 *  android.widget.ListAdapter
 *  android.widget.TextView
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.HashSet
 *  miui.R
 *  miui.R$drawable
 *  miui.R$layout
 *  miui.R$string
 *  miui.accounts.ExtraAccountManager
 *  miui.app.AlertDialog
 *  miui.app.ProgressDialog
 *  miui.cloud.util.SyncAlertHelper
 *  miui.os.Build
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$MmsSms
 *  miui.provider.ExtraTelephony$Phonelist
 *  miui.telephony.PhoneNumberUtils
 *  miui.telephony.PhoneNumberUtils$PhoneNumber
 *  miui.telephony.SubscriptionManager
 *  miui.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 *  miui.view.EditActionMode
 *  miui.widget.MiCloudStateView
 *  miui.widget.MiCloudStateView$ISyncInfoProvider
 *  miui.widget.ScrollableViewDrawer
 *  miui.yellowpage.YellowPageImgLoader
 */
package com.android.mms.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.provider.MiuiSettings;
import android.provider.Settings;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.AlertDialogHelper;
import com.android.mms.ui.BookmarkActivity;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.ConversationListAdapter;
import com.android.mms.ui.ConversationListItem;
import com.android.mms.ui.MessageListPullView;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.MmsTabActivity;
import com.android.mms.ui.PrivateConversationFragment;
import com.android.mms.ui.PrivateEntryView;
import com.android.mms.ui.ServiceProviderConversationFragment;
import com.android.mms.ui.ServiceProviderConversationListActivity;
import com.android.mms.ui.WildMsgDialog;
import com.android.mms.util.DraftCache;
import com.android.mms.util.EditableListView;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.android.mms.util.SmsSyncInfoProviderWrapper;
import com.xiaomi.mms.transaction.MxActivateService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import miui.R;
import miui.accounts.ExtraAccountManager;
import miui.app.ProgressDialog;
import miui.cloud.util.SyncAlertHelper;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.SubscriptionManager;
import miui.view.EditActionMode;
import miui.widget.MiCloudStateView;
import miui.widget.ScrollableViewDrawer;
import miui.yellowpage.YellowPageImgLoader;

public class ConversationFragment
extends Fragment
implements AbsListView.OnScrollListener,
DraftCache.OnDraftChangedListener {
    private static final String[] MMS_THREAD_ID_PROJECTION;
    private static final String[] SMS_THREAD_ID_PROJECTION;
    protected static final Uri UNSEEN_BLOCKED_MSG_COUNT_URI;
    protected static final Uri UNSEEN_SP_MSG_COUNT_URI;
    private static ProgressDialog mAddBlackListProgressDialog;
    private static ProgressDialog mDeleteProgressDialog;
    private static int mPrivateSmsUnlockDistance;
    protected Activity mActivity;
    private AsyncTask<Void, Void, Void> mAddBlackListTask;
    protected View mBookmarkView;
    protected boolean mBookmarkViewAdded;
    protected boolean mBookmarkVisible;
    protected View mCloudRecommendView;
    private MiCloudStateView mCloudStateView;
    private final ConversationListAdapter.OnContentChangedListener mContentChangedListener;
    private int mFinishedTimedQuery = 0;
    protected boolean mIsCompositeMode;
    protected boolean mIsCreateFirstQuery = false;
    private boolean mIsMSimInserted;
    private boolean mIsSimpleMode;
    private boolean mIsStoped = false;
    protected ConversationListAdapter mListAdapter;
    protected MessageListPullView mListView;
    private boolean mNeedToMarkAsSeen;
    private miui.app.AlertDialog mNetworkDialog;
    private AlertDialog mOldMsgPromptDialog;
    private OnAccountsUpdateListener mOnAccountsUpdateListener;
    private final AdapterView.OnItemClickListener mOnClickListener;
    private Runnable mOnDraftChanged;
    protected int mPlaceHolderType = 0;
    protected ThreadListQueryHandler mQueryHandler;
    private int mRecommendVisible = 8;
    protected View mRootView;
    protected View mSearchView;
    protected TextView mSearchViewHint;
    private boolean mShowCloudHeader = false;
    private boolean mShowCloudStateView;
    private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener;
    protected final View.OnKeyListener mThreadListKeyListener;
    private Handler mUIHandler;
    private boolean mUnlockPrivateMode;
    private DialogInterface.OnClickListener mUserNoticeAgreedListener;
    private DialogInterface.OnClickListener mUserNoticeCancelledListener;
    private WildMsgDialog mWildMsgDialog;
    private Handler mWorkerHander = null;
    private HandlerThread mWorkerThread = null;
    private View mYellowpageRecommendView;
    private int mYpRecommendVisible = 8;
    protected final Uri sSearchHintUri = Uri.withAppendedPath((Uri)Telephony.MmsSms.CONTENT_URI, (String)"searchCount");

    static {
        UNSEEN_SP_MSG_COUNT_URI = Uri.withAppendedPath((Uri)Telephony.MmsSms.CONTENT_URI, (String)"unseenSpMsgCount");
        UNSEEN_BLOCKED_MSG_COUNT_URI = Uri.withAppendedPath((Uri)Telephony.MmsSms.CONTENT_URI, (String)"unseenBlockedMsgCount");
        SMS_THREAD_ID_PROJECTION = new String[]{"thread_id"};
        MMS_THREAD_ID_PROJECTION = new String[]{"thread_id"};
    }

    public ConversationFragment() {
        this.mUIHandler = new Handler(){

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message message) {
                Log.v((String)"ConversationFragment", (String)("handle msg on main thread, msg: " + message.what));
                switch (message.what) {
                    case 1001: {
                        if (!ConversationFragment.this.shouldAsk((Context)ConversationFragment.this.mActivity)) return;
                        {
                            ConversationFragment.this.askUploadOldMessages((Context)ConversationFragment.this.mActivity);
                            ConversationFragment.this.askDownloadWildMessages((Context)ConversationFragment.this.mActivity);
                            return;
                        }
                    }
                    case 1002: {
                        if (ConversationFragment.this.mIsStoped) return;
                        {
                            if (((Boolean)message.obj).booleanValue()) {
                                ConversationFragment.this.showCloudRecommendView();
                                return;
                            }
                            ConversationFragment.this.hideCloudRecommendView();
                            return;
                        }
                    }
                    case 1003: {
                        ConversationFragment.this.startAsyncQuery(true);
                        return;
                    }
                    case 1005: {
                        if (DraftCache.getInstance().getSavingDraft()) {
                            ConversationFragment.this.mUIHandler.removeMessages(1005);
                            ConversationFragment.this.mUIHandler.sendEmptyMessageDelayed(1005, 1000);
                            return;
                        }
                        Conversation.asyncDeleteObsoleteThreads(ConversationFragment.this.mQueryHandler, 1803);
                        return;
                    }
                    case 1006: {
                        if (ConversationFragment.this.mIsStoped) return;
                        {
                            ConversationFragment.this.checkAndShowNetworkingDialog();
                            return;
                        }
                    }
                    default: {
                        return;
                    }
                    case 1007: 
                }
                if (ConversationFragment.this.mIsStoped) return;
                {
                    ConversationFragment.this.showYellowpageRecommend();
                    return;
                }
            }
        };
        this.mContentChangedListener = new ConversationListAdapter.OnContentChangedListener(){

            @Override
            public void onContentChanged(ConversationListAdapter conversationListAdapter) {
                ConversationFragment.this.mUIHandler.removeMessages(1003);
                ConversationFragment.this.mUIHandler.sendEmptyMessageDelayed(1003, 50);
            }
        };
        this.mOnDraftChanged = new Runnable(){

            @Override
            public void run() {
                ConversationFragment.this.mListAdapter.notifyDataSetChanged();
            }
        };
        this.mOnClickListener = new AdapterView.OnItemClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onItemClick(AdapterView<?> object, View view, int n, long l) {
                if (n == 1) {
                    if (ConversationFragment.this.mRootView instanceof ScrollableViewDrawer) {
                        ((ScrollableViewDrawer)ConversationFragment.this.mRootView).resetState();
                    }
                    ((MmsTabActivity)ConversationFragment.this.mActivity).startSearchMode(ConversationFragment.this.mSearchView, ConversationFragment.this.mRootView);
                    return;
                }
                if (n == 2 && ConversationFragment.this.mBookmarkVisible) {
                    object = new Intent((Context)ConversationFragment.this.mActivity, (Class)BookmarkActivity.class);
                    ConversationFragment.this.startActivity((Intent)object);
                    return;
                }
                if (n == ConversationFragment.this.mListView.getAdapter().getCount() - 1) return;
                {
                    object = (Cursor)ConversationFragment.this.mListView.getItemAtPosition(n);
                    l = (object = Conversation.from((Context)ConversationFragment.this.mActivity, (Cursor)object)).getThreadId();
                    if (l == -100) {
                        object = new Intent(MiuiSettings.AntiSpam.ACTION_SOURCE_SMS);
                        object.setComponent(new ComponentName("com.miui.antispam", "com.miui.antispam.ui.activity.MainActivity"));
                        ConversationFragment.this.mActivity.startActivity((Intent)object);
                        return;
                    }
                }
                if (l == -101) {
                    object = new Intent();
                    object.setAction("com.miui.mipub.action_open_threadlist");
                    object.setPackage("com.miui.mipub");
                    ConversationFragment.this.startActivity((Intent)object);
                    return;
                }
                Log.d((String)"ConversationFragment", (String)("onListItemClick: pos=" + n + ", view=" + (Object)view + ", tid=" + l));
                if (ConversationFragment.this.mIsCompositeMode && object.isServiceProvider() && !object.isSticky()) {
                    object = new Intent((Context)ConversationFragment.this.mActivity, (Class)ServiceProviderConversationListActivity.class);
                    ConversationFragment.this.startActivity((Intent)object);
                    return;
                }
                object = ComposeMessageRouterActivity.createIntent((Context)ConversationFragment.this.mActivity, l);
                ConversationFragment.this.startActivity((Intent)object);
            }
        };
        this.mThreadListKeyListener = new View.OnKeyListener(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean onKey(View view, int n, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0) return false;
                switch (n) {
                    default: {
                        return false;
                    }
                    case 67: 
                }
                long l = ConversationFragment.this.mListView.getSelectedItemId();
                if (l <= 0) return true;
                ConversationFragment.confirmDeleteThread(l, ConversationFragment.this.mQueryHandler);
                return true;
            }
        };
        this.mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener(){

            public void onSubscriptionsChanged() {
                boolean bl = MSimUtils.isMSimInserted();
                if (ConversationFragment.this.mIsMSimInserted != bl) {
                    ConversationFragment.this.mIsMSimInserted = bl;
                    ConversationFragment.this.startAsyncQuery(false);
                }
            }
        };
        this.mUserNoticeAgreedListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                MessageUtils.setYpRecommendEnabled((Context)ConversationFragment.this.mActivity, false);
                ConversationFragment.this.enableSmartMessage();
                MessageUtils.setNetworkRecommendDate((Context)ConversationFragment.this.mActivity, System.currentTimeMillis());
                MiStatSdkHelper.recordNetworkAllowRecommend("network_recommend_goto");
            }
        };
        this.mUserNoticeCancelledListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                MessageUtils.setYpRecommendEnabled((Context)ConversationFragment.this.mActivity, true);
                MessageUtils.setNetworkRecommendDate((Context)ConversationFragment.this.mActivity, System.currentTimeMillis());
                MiStatSdkHelper.recordNetworkAllowRecommend("network_recommend_close");
                ConversationFragment.this.showYellowpageRecommend();
            }
        };
    }

    static /* synthetic */ boolean access$1800(ConversationFragment conversationFragment) {
        return conversationFragment.mNeedToMarkAsSeen;
    }

    static /* synthetic */ boolean access$1802(ConversationFragment conversationFragment, boolean bl) {
        conversationFragment.mNeedToMarkAsSeen = bl;
        return bl;
    }

    static /* synthetic */ int access$1904(ConversationFragment conversationFragment) {
        int n;
        conversationFragment.mFinishedTimedQuery = n = conversationFragment.mFinishedTimedQuery + 1;
        return n;
    }

    static /* synthetic */ void access$2400(ConversationFragment conversationFragment, MenuItem menuItem, boolean bl, boolean bl2) {
        conversationFragment.updateStickMenu(menuItem, bl, bl2);
    }

    private void addBlackList(final Context context, final Set<Long> set) {
        new AlertDialog.Builder(context).setIconAttribute(16843605).setTitle(2131362261).setMessage(2131362262).setCancelable(true).setPositiveButton(17039370, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (ConversationFragment.this.mAddBlackListTask != null) {
                    Log.e((String)"ConversationFragment", (String)"add black list task is running");
                    return;
                }
                ConversationFragment.this.mAddBlackListTask = (AsyncTask)new AsyncTask<Void, Void, Void>(){
                    private ContentResolver mContentResolver;
                    private Context mContext;
                    private Set<Long> mThreadIds;

                    private Uri getBlockedConvUriByNumber(String string2) {
                        return ContentUris.withAppendedId((Uri)ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI.buildUpon().appendQueryParameter("blocked_conv_addr", string2).build(), (long)0);
                    }

                    /*
                     * Unable to fully structure code
                     * Enabled aggressive block sorting
                     * Enabled unnecessary exception pruning
                     * Enabled aggressive exception aggregation
                     * Lifted jumps to return sites
                     */
                    protected /* varargs */ Void doInBackground(Void ... var1_1) {
                        var1_1 = new ArrayList();
                        var6_4 = new ArrayList();
                        var3_5 = 0;
                        var2_6 = 0;
                        var7_7 = this.mThreadIds.iterator();
                        while (var7_7.hasNext()) {
                            var8_10 = var7_7.next();
                            var8_10 = ((Contact)Conversation.get((Context)MmsApp.getApp(), var8_10.longValue()).getRecipients().get(0)).getNumber().replace((CharSequence)"+86", (CharSequence)"");
                            if (TextUtils.isEmpty((CharSequence)var8_10)) {
                                Log.d((String)"ConversationFragment", (String)"number is null");
                                continue;
                            }
                            if (!ExtraTelephony.isInBlacklist((Context)this.mContext, (String)(var8_10 = PhoneNumberUtils.PhoneNumber.parse((CharSequence)var8_10).getNormalizedNumber(false, true)))) {
                                var9_13 = new ContentValues(1);
                                var9_13.put("number", (String)var8_10);
                                var9_13.put("type", "1");
                                var9_13.put("state", Integer.valueOf((int)0));
                                var9_13.put("isdisplay", Integer.valueOf((int)0));
                                var9_13.put("notes", Integer.valueOf((int)0));
                                var1_1.add((Object)ContentProviderOperation.newInsert((Uri)ExtraTelephony.Phonelist.CONTENT_URI).withValues(var9_13).build());
                            } else {
                                var9_13 = new ContentValues(1);
                                var9_13.put("state", Integer.valueOf((int)0));
                                var1_1.add((Object)ContentProviderOperation.newUpdate((Uri)ExtraTelephony.Phonelist.CONTENT_URI).withSelection("number=?", new String[]{var8_10}).withValues(var9_13).build());
                                Log.d((String)"ConversationFragment", (String)"number has been in black list");
                            }
                            var9_13 = new ContentValues(1);
                            var9_13.put("block_type", Integer.valueOf((int)5));
                            var6_4.add((Object)ContentProviderOperation.newUpdate((Uri)this.getBlockedConvUriByNumber((String)var8_10)).withValues(var9_13).build());
                            var4_8 = var1_1.size();
                            var5_9 = var6_4.size();
                            if (var4_8 <= 10) {
                                var3_5 = var4_8;
                                var2_6 = var5_9;
                                if (var5_9 <= 10) continue;
                            }
                            if (var4_8 <= 0) ** GOTO lbl37
                            try {
                                this.mContentResolver.applyBatch("antispam", var1_1);
lbl37: // 2 sources:
                                this.mContentResolver.applyBatch("mms-sms", var6_4);
                            }
                            catch (RemoteException var8_11) {
                                var8_11.printStackTrace();
                            }
                            catch (OperationApplicationException var8_12) {
                                var8_12.printStackTrace();
                            }
                            var1_1.clear();
                            var6_4.clear();
                            var3_5 = var4_8;
                            var2_6 = var5_9;
                        }
                        if (var3_5 <= 0) {
                            if (var2_6 <= 0) return null;
                        }
                        if (var3_5 <= 0) ** GOTO lbl54
                        try {
                            this.mContentResolver.applyBatch("antispam", var1_1);
lbl54: // 2 sources:
                            this.mContentResolver.applyBatch("mms-sms", var6_4);
                            return null;
                        }
                        catch (RemoteException var1_2) {
                            var1_2.printStackTrace();
                            return null;
                        }
                        catch (OperationApplicationException var1_3) {
                            var1_3.printStackTrace();
                            return null;
                        }
                    }

                    protected void onPostExecute(Void void_) {
                        ConversationFragment.this.mAddBlackListTask = null;
                        if (mAddBlackListProgressDialog != null) {
                            mAddBlackListProgressDialog.dismiss();
                        }
                        mAddBlackListProgressDialog = null;
                        ConversationFragment.this.mListView.exitEditMode();
                    }

                    protected void onPreExecute() {
                        this.mContext = context;
                        this.mThreadIds = set;
                        this.mContentResolver = context.getContentResolver();
                        mAddBlackListProgressDialog = ProgressDialog.show((Context)this.mContext, (CharSequence)null, (CharSequence)this.mContext.getString(2131362263), (boolean)true, (boolean)false);
                    }
                };
                ConversationFragment.this.mAddBlackListTask.execute((Object[])new Void[0]);
                dialogInterface.dismiss();
            }

        }).setNegativeButton(2131361892, null).show();
    }

    private void askCloudRecommend() {
        if (!this.mShowCloudHeader) {
            return;
        }
        this.mWorkerHander.removeMessages(1002);
        this.mWorkerHander.sendEmptyMessageDelayed(1002, 300);
    }

    private void askDownloadWildMessages(Context context) {
        if (Settings.System.getInt((ContentResolver)context.getContentResolver(), (String)"mms_sync_wild_msg_state", (int)0) != 1) {
            return;
        }
        if (this.mWildMsgDialog != null) {
            this.mWildMsgDialog.cancel();
        }
        this.mWildMsgDialog = new WildMsgDialog(context, false);
        this.mWildMsgDialog.show();
    }

    private void askSmartMessageRecommend() {
        this.mWorkerHander.removeMessages(1006);
        this.mWorkerHander.sendEmptyMessageDelayed(1006, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void askUploadOldMessages(final Context context) {
        Object object;
        if (Settings.System.getInt((ContentResolver)context.getContentResolver(), (String)"mms_upload_old_msg_state", (int)0) != 1 || MessageUtils.isPrivacyModeEnabled(context) || !MSimUtils.isSimInserted() || (object = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !object.isAvailable()) {
            return;
        }
        object = Settings.System.getString((ContentResolver)context.getContentResolver(), (String)"mms_upload_old_msg_accounts");
        final String string2 = this.queryXiaomiAccountName(context);
        if (this.mOldMsgPromptDialog != null && this.mOldMsgPromptDialog.isShowing()) {
            this.mOldMsgPromptDialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(2131362199);
        builder.setMessage((CharSequence)this.getString(2131362200, new Object[]{object, string2}));
        builder.setIconAttribute(16843605);
        builder.setCancelable(true);
        builder.setPositiveButton(2131361891, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                Settings.System.putInt((ContentResolver)context.getContentResolver(), (String)"mms_upload_old_msg_state", (int)0);
                Settings.System.putString((ContentResolver)context.getContentResolver(), (String)"mms_upload_old_msg_accounts", (String)null);
                dialogInterface = new ContentValues();
                dialogInterface.put("account", string2);
                dialogInterface.put("bind_id", Integer.valueOf((int)0));
                context.getContentResolver().update(Telephony.Sms.CONTENT_URI, (ContentValues)dialogInterface, "account is not null and account != ?", new String[]{string2});
                context.getContentResolver().update(Telephony.Mms.CONTENT_URI, (ContentValues)dialogInterface, "account is not null and account != ?", new String[]{string2});
                MessageUtils.forceSync(context);
            }
        });
        builder.setNegativeButton(2131361892, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                Settings.System.putInt((ContentResolver)context.getContentResolver(), (String)"mms_upload_old_msg_state", (int)0);
                Settings.System.putString((ContentResolver)context.getContentResolver(), (String)"mms_upload_old_msg_accounts", (String)null);
            }
        });
        this.mOldMsgPromptDialog = builder.show();
    }

    private void checkAndShowNetworkingDialog() {
        if (this instanceof ServiceProviderConversationFragment || this instanceof PrivateConversationFragment) {
            return;
        }
        MiStatSdkHelper.recordNetworkAllowRecommend("network_recommend_show");
        MessageUtils.setNetworkRecommendDate((Context)this.mActivity, 0);
        if (this.mNetworkDialog != null) {
            this.mNetworkDialog.show();
            return;
        }
        this.mNetworkDialog = AlertDialogHelper.showUserNoticeDialog((Context)this.mActivity, 2131362401, 2131362402, 2131362403, 0, this.mUserNoticeAgreedListener, this.mUserNoticeCancelledListener);
    }

    public static void confirmDeleteThread(long l, AsyncQueryHandler asyncQueryHandler) {
        ArrayList arrayList = null;
        if (l != -1) {
            arrayList = new ArrayList();
            arrayList.add((Object)l);
        }
        ConversationFragment.confirmDeleteThreads(arrayList, asyncQueryHandler);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void confirmDeleteThreadDialog(final DeleteThreadListener deleteThreadListener, final Collection<Long> checkBox, boolean bl, Context context) {
        View view = View.inflate((Context)context, (int)2130968602, (ViewGroup)null);
        TextView textView = (TextView)view.findViewById(2131820614);
        if (checkBox == null) {
            textView.setText(2131361918);
        } else {
            int n = checkBox.size();
            textView.setText((CharSequence)context.getResources().getQuantityString(2131623936, n, new Object[]{n}));
        }
        checkBox = (CheckBox)view.findViewById(2131820615);
        if (!bl) {
            checkBox.setVisibility(8);
        } else {
            deleteThreadListener.setDeleteLockedMessage(checkBox.isChecked());
            checkBox.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    deleteThreadListener.setDeleteLockedMessage(checkBox.isChecked());
                }
            });
        }
        new AlertDialog.Builder(context).setTitle(2131361915).setIconAttribute(16843605).setCancelable(true).setPositiveButton(2131361930, (DialogInterface.OnClickListener)deleteThreadListener).setNegativeButton(2131361892, null).setView(view).show();
    }

    public static void confirmDeleteThreads(Collection<Long> collection, AsyncQueryHandler asyncQueryHandler) {
        Conversation.startQueryHaveLockedMessages(asyncQueryHandler, collection, 1802);
    }

    private void enableCloudState(boolean bl) {
        if (!(this.mRootView instanceof ScrollableViewDrawer)) {
            return;
        }
        ScrollableViewDrawer scrollableViewDrawer = (ScrollableViewDrawer)this.mRootView;
        if (bl) {
            this.mShowCloudStateView = true;
            if (this.mCloudStateView == null) {
                this.mCloudStateView = (MiCloudStateView)this.mRootView.findViewById(2131820603);
                this.mCloudStateView.setSyncInfoProvider((MiCloudStateView.ISyncInfoProvider)new SmsSyncInfoProviderWrapper());
                this.mCloudStateView.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        view = new Intent("com.xiaomi.action.MICLOUD_MAIN");
                        ConversationFragment.this.startActivity((Intent)view);
                    }
                });
            }
            this.mCloudStateView.registerObserver();
            scrollableViewDrawer.setDragEnabled(true);
            this.mListView.setOnPrivateColorChangeListener(new PrivateEntryView.OnPrivateColorChangeListener(){

                @Override
                public void onColorChange(int n) {
                    ConversationFragment.this.mCloudStateView.setBackgroundColor(n);
                }
            });
            return;
        }
        if (this.mCloudStateView != null) {
            this.mCloudStateView.unregisterObserver();
        }
        scrollableViewDrawer.resetState();
        this.mShowCloudStateView = false;
        scrollableViewDrawer.setDragEnabled(false);
        this.mListView.setOnPrivateColorChangeListener(null);
    }

    private void enableSmartMessage() {
        MessageUtils.setAllowNetworkAccess((Context)this.mActivity, true);
        MessageUtils.enableYellowPage((Context)this.mActivity);
        new QueryActivateStatusTask(this.mActivity).execute((Object[])new Void[0]);
    }

    private void hideCloudRecommendView() {
        if (this.mCloudRecommendView != null) {
            this.mCloudRecommendView.setVisibility(8);
        }
    }

    private void hideYellowpageRecommend() {
        MessageUtils.setYpRecommendEnabled((Context)this.mActivity, false);
        if (this.mYellowpageRecommendView != null) {
            this.mYellowpageRecommendView.setVisibility(8);
        }
        this.refreshCloudStateView();
    }

    private boolean isSticky(Set<Long> object) {
        object = object.iterator();
        while (object.hasNext()) {
            if (Conversation.get((Long)object.next()).isSticky()) continue;
            return false;
        }
        return true;
    }

    private /* varargs */ void log(String string2, Object ... arrobject) {
        string2 = String.format((String)string2, (Object[])arrobject);
        Log.d((String)"ConversationFragment", (String)("[" + Thread.currentThread().getId() + "] " + string2));
    }

    private String queryXiaomiAccountName(Context arraccount) {
        if ((arraccount = AccountManager.get((Context)arraccount).getAccountsByType("com.xiaomi")).length == 0) {
            return null;
        }
        return arraccount[0].name;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void refreshCloudStateView() {
        Activity activity = this.getActivity();
        if (activity != null) {
            boolean bl = ExtraAccountManager.getXiaomiAccount((Context)activity) != null;
            this.enableCloudState(bl);
        }
    }

    private void refreshListViewReadItem() {
        int n = 0;
        View view;
        while (!(n >= this.mListView.getChildCount() || (view = this.mListView.getChildAt(n)) instanceof ConversationListItem && ((ConversationListItem)view).updatePreMarkUnReadView())) {
            ++n;
        }
        return;
    }

    private boolean shouldAsk(Context context) {
        if (MessageUtils.isPrivacyModeEnabled(context)) {
            return false;
        }
        if (!MSimUtils.isSimInserted()) {
            return false;
        }
        if ((context = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !context.isAvailable()) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void showCloudRecommendView() {
        if (this.mYellowpageRecommendView != null && this.mYellowpageRecommendView.getVisibility() == 0) {
            return;
        }
        this.enableCloudState(false);
        if (this.mCloudRecommendView == null) {
            View view = this.getView();
            if (view == null) return;
            if ((view = (ViewStub)view.findViewById(2131820604)) == null) return;
            this.mCloudRecommendView = view.inflate();
            if (this.mCloudRecommendView == null) return;
            this.mCloudRecommendView.findViewById(2131820573).setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    if (ConversationFragment.this.getActivity() != null) {
                        SyncAlertHelper.handleSyncAlert((Context)ConversationFragment.this.getActivity(), (String)"sms");
                    }
                }
            });
            this.mCloudRecommendView.findViewById(2131820574).setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    if (ConversationFragment.this.getActivity() != null) {
                        SyncAlertHelper.recordTime((Context)MmsApp.getApp(), (String)"sms");
                    }
                    ConversationFragment.this.refreshCloudStateView();
                    ConversationFragment.this.hideCloudRecommendView();
                }
            });
            return;
        }
        this.mCloudRecommendView.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void showYellowpageRecommend() {
        if (this.mCloudRecommendView != null && this.mCloudRecommendView.getVisibility() == 0) {
            return;
        }
        if (this.mYellowpageRecommendView == null) {
            View view = this.getView();
            if (view == null) return;
            if ((view = (ViewStub)view.findViewById(2131820605)) != null) {
                this.mYellowpageRecommendView = view.inflate();
            }
            if (this.mYellowpageRecommendView != null) {
                this.mYellowpageRecommendView.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        ConversationFragment.this.hideYellowpageRecommend();
                        MiStatSdkHelper.recordNetworkAllowRecommend("yellowpage_recommend_goto");
                        ConversationFragment.this.checkAndShowNetworkingDialog();
                    }
                });
                this.mYellowpageRecommendView.findViewById(2131820574).setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        ConversationFragment.this.hideYellowpageRecommend();
                        MiStatSdkHelper.recordNetworkAllowRecommend("yellowpage_recommend_close");
                        MessageUtils.setNetworkRecommendDate((Context)ConversationFragment.this.mActivity, System.currentTimeMillis());
                    }
                });
            }
        }
        if (this.mYellowpageRecommendView == null) return;
        this.enableCloudState(false);
        this.mYellowpageRecommendView.setVisibility(0);
        MiStatSdkHelper.recordNetworkAllowRecommend("yellowpage_recommend_show");
    }

    private void startQueryUnSeenBlockedCount() {
        this.mQueryHandler.cancelOperation(1904);
        this.mQueryHandler.startQuery(1904, (Object)null, UNSEEN_BLOCKED_MSG_COUNT_URI, null, null, null, null);
    }

    private void startQueryUnSeenSPCount() {
        this.mQueryHandler.cancelOperation(1901);
        this.mQueryHandler.startQuery(1901, (Object)null, UNSEEN_SP_MSG_COUNT_URI, null, null, null, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void stickThread(Set<Long> set, boolean bl) {
        ContentValues contentValues = new ContentValues(1);
        long l = bl ? System.currentTimeMillis() : 0;
        Long l2 = l;
        contentValues.put("stick_time", l2);
        Conversation.startUpdateThreads(this.mQueryHandler, 1804, set, contentValues);
        Conversation.updateCachedThreadStickTime(set, l2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateStickMenu(MenuItem menuItem, boolean bl, boolean bl2) {
        int n = 2131361931;
        if (!bl2) {
            menuItem.setTitle(2131361931);
            menuItem.setIcon(R.drawable.action_button_stick_light);
            return;
        }
        if (!bl) {
            n = 2131361932;
        }
        menuItem.setTitle(n);
        n = bl ? R.drawable.action_button_stick_light : R.drawable.action_button_unstick_light;
        menuItem.setIcon(n);
    }

    public void createFirstQuery(Context context) {
        this.mQueryHandler = new ThreadListQueryHandler(context.getContentResolver());
        this.initListCompositeMode(context);
        this.startAsyncQuery(true);
        this.mIsCreateFirstQuery = true;
    }

    public void forceHideHintView() {
        if (this.mCloudRecommendView != null) {
            this.mRecommendVisible = this.mCloudRecommendView.getVisibility();
            this.mCloudRecommendView.setVisibility(8);
        }
        if (this.mYellowpageRecommendView != null) {
            this.mYpRecommendVisible = this.mYellowpageRecommendView.getVisibility();
            this.mYellowpageRecommendView.setVisibility(8);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void initListCompositeMode(Context context) {
        context = PreferenceManager.getDefaultSharedPreferences((Context)context);
        this.mIsCompositeMode = Build.IS_INTERNATIONAL_BUILD && !Build.checkRegion((String)"IN") ? false : context.getBoolean("pref_key_collapse_sp_messages", true);
        this.mPlaceHolderType = context.getBoolean("pref_key_show_blocked_messages", true) ? (this.mPlaceHolderType |= 1) : (this.mPlaceHolderType &= -2);
        this.mPlaceHolderType |= 2;
    }

    protected void initialize() {
        this.setHasOptionsMenu(true);
        this.mIsMSimInserted = MSimUtils.isMSimInserted();
        MSimUtils.registerChangeListener(this.mActivity.getApplicationContext(), this.mSimInfoChangeListener);
        this.mWorkerThread = new HandlerThread("Conv Thread", 10);
        this.mWorkerThread.start();
        this.mWorkerHander = new Handler(this.mWorkerThread.getLooper()){

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message message) {
                Log.v((String)"ConversationFragment", (String)("handle msg on Worker thread, msg: " + message.what));
                switch (message.what) {
                    default: {
                        return;
                    }
                    case 1002: {
                        boolean bl = SyncAlertHelper.isNeedAlert((Context)MmsApp.getApp().getApplicationContext(), (String)"sms");
                        Log.v((String)"ConversationFragment", (String)("isNeedAlert ? " + bl));
                        message = new Message();
                        message.what = 1002;
                        message.obj = bl;
                        ConversationFragment.this.mUIHandler.sendMessage(message);
                        return;
                    }
                    case 1006: {
                        if (MessageUtils.isNetworkRecommendDateLong((Context)ConversationFragment.this.mActivity) && MessageUtils.isSmartMessageNotReady((Context)ConversationFragment.this.mActivity)) {
                            message = new Message();
                            message.what = 1006;
                            ConversationFragment.this.mUIHandler.sendMessage(message);
                            return;
                        }
                        if (!MessageUtils.isYpRecommendEnabled((Context)ConversationFragment.this.mActivity) || !MessageUtils.isSmartMessageNotReady((Context)ConversationFragment.this.mActivity)) return;
                        message = new Message();
                        message.what = 1007;
                        ConversationFragment.this.mUIHandler.sendMessage(message);
                        return;
                    }
                }
            }
        };
    }

    protected void markConversationAsSeen() {
        if (this.mIsCompositeMode) {
            Conversation.markConversationAsNotified(this.mActivity.getApplicationContext());
            return;
        }
        Conversation.markAllConversationsAsSeen(this.mActivity.getApplicationContext());
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131755009, menu);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mActivity = this.getActivity();
        this.mRootView = layoutInflater.inflate(2130968598, null);
        this.mListView = (MessageListPullView)this.mRootView.findViewById(16908298);
        this.mListView.setOnKeyListener(this.mThreadListKeyListener);
        this.mListView.setEditModeListener(new ModeCallback());
        this.mListView.enableEmptyImgView(false);
        this.mListView.setEmptyImgViewImageResource(2130837594);
        this.mListView.setAllowTranscriptOnResize(false);
        mPrivateSmsUnlockDistance = (int)this.mActivity.getResources().getDimension(2131427388);
        this.mListView.setScrolledListener(new MessageListPullView.ScrolledListener(){

            @Override
            public void onScrolled(int n) {
                if (!ConversationFragment.this.mUnlockPrivateMode && n > mPrivateSmsUnlockDistance && ((MmsTabActivity)ConversationFragment.this.mActivity).enterPrivateActivity()) {
                    ConversationFragment.this.mUnlockPrivateMode = true;
                    ConversationFragment.this.mActivity.overridePendingTransition(2131034118, 17432577);
                }
            }
        });
        this.refreshCloudStateView();
        this.mSearchView = layoutInflater.inflate(R.layout.search_stub, null, false);
        this.mBookmarkView = null;
        this.mBookmarkViewAdded = false;
        this.mBookmarkVisible = false;
        this.mListView.addHeaderView(this.mSearchView);
        this.mSearchViewHint = (TextView)this.mSearchView.findViewById(16908297);
        this.mSearchViewHint.setHint((CharSequence)this.getResources().getString(2131362212, new Object[]{0}));
        this.mListAdapter = new ConversationListAdapter((Context)this.mActivity, null, true);
        this.mListView.setAdapter((ListAdapter)this.mListAdapter);
        this.mListView.setRecyclerListener((AbsListView.RecyclerListener)this.mListAdapter);
        this.mListView.setOnItemClickListener(this.mOnClickListener);
        this.mListView.setOnScrollListener((AbsListView.OnScrollListener)this);
        this.mListView.enableDragEvent(true);
        this.mOnAccountsUpdateListener = new OnAccountsUpdateListener(){

            public void onAccountsUpdated(Account[] arraccount) {
                ConversationFragment.this.refreshCloudStateView();
            }
        };
        AccountManager.get((Context)this.mActivity).addOnAccountsUpdatedListener(this.mOnAccountsUpdateListener, new Handler(), true);
        this.mShowCloudHeader = true;
        this.initialize();
        return this.mRootView;
    }

    public void onDestroy() {
        if (this.mOnAccountsUpdateListener != null) {
            AccountManager.get((Context)this.getActivity()).removeOnAccountsUpdatedListener(this.mOnAccountsUpdateListener);
        }
        super.onDestroy();
    }

    public void onDestroyView() {
        if (this.mAddBlackListTask != null) {
            this.mAddBlackListTask.cancel(true);
        }
        this.mAddBlackListTask = null;
        if (mAddBlackListProgressDialog != null) {
            mAddBlackListProgressDialog.dismiss();
        }
        mAddBlackListProgressDialog = null;
        if (mDeleteProgressDialog != null) {
            mDeleteProgressDialog.dismiss();
        }
        mDeleteProgressDialog = null;
        this.mWorkerThread.quit();
        this.mWorkerThread = null;
        this.mUIHandler.removeMessages(1002);
        this.mUIHandler.removeMessages(1003);
        this.mUIHandler.removeMessages(1001);
        this.mUIHandler.removeMessages(1005);
        this.mUIHandler.removeMessages(1006);
        this.mListAdapter.changeCursor(null);
        MSimUtils.unregisterChangeListener(this.mActivity.getApplicationContext(), this.mSimInfoChangeListener);
        super.onDestroyView();
        if (!this.mIsSimpleMode) {
            Contact.stopCaching();
        }
    }

    @Override
    public void onDraftChanged(long l, boolean bl) {
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            this.log("onDraftChanged: threadId=" + l + ", hasDraft=" + bl, new Object[0]);
        }
        this.mQueryHandler.removeCallbacks(this.mOnDraftChanged);
        this.mQueryHandler.postDelayed(this.mOnDraftChanged, 20);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 2131820905: 
        }
        menuItem = ComposeMessageRouterActivity.createIntent((Context)this.mActivity, 0);
        menuItem.putExtra("exit_animation_in", 17432576);
        menuItem.putExtra("exit_animation_out", 2131034120);
        ComposeMessageRouterActivity.route((Context)this.mActivity, (Intent)menuItem);
        this.mActivity.overridePendingTransition(2131034121, 17432577);
        return true;
    }

    public void onPause() {
        this.mListView.clearDragAnimation();
        if (this.mNetworkDialog != null) {
            this.mNetworkDialog.dismiss();
        }
        if (this.mYellowpageRecommendView != null) {
            this.mYellowpageRecommendView.setVisibility(8);
        }
        super.onPause();
    }

    public void onResume() {
        this.askCloudRecommend();
        this.askSmartMessageRecommend();
        super.onResume();
        if (!this.mIsSimpleMode) {
            Contact.resumeCaching();
        }
    }

    public void onScroll(AbsListView absListView, int n, int n2, int n3) {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onScrollStateChanged(AbsListView absListView, int n) {
        if (this.mIsSimpleMode) return;
        if (n == 2) {
            Contact.pauseCaching();
            YellowPageImgLoader.pauseLoading((Context)this.getActivity());
            return;
        }
        Contact.resumeCaching();
        YellowPageImgLoader.resumeLoading((Context)this.getActivity());
    }

    public void onStart() {
        if (this.mRootView instanceof ScrollableViewDrawer) {
            ((ScrollableViewDrawer)this.mRootView).resetState();
        }
        super.onStart();
        this.refreshListViewReadItem();
        this.mIsSimpleMode = MiuiSettings.System.isSimpleMode((Context)this.mActivity);
        if (!this.mIsSimpleMode) {
            Contact.invalidatePhotoCache();
        }
        MessagingNotification.cancelNotification(this.mActivity.getApplicationContext(), 239);
        DraftCache.getInstance().addOnDraftChangedListener(this);
        this.mListAdapter.setOnContentChangedListener(this.mContentChangedListener);
        this.mNeedToMarkAsSeen = true;
        this.mUnlockPrivateMode = false;
        this.mIsStoped = false;
        if (!this.mIsCreateFirstQuery) {
            this.initListCompositeMode((Context)this.mActivity);
            this.mUIHandler.removeMessages(1003);
            this.mUIHandler.sendEmptyMessageDelayed(1003, 10);
        }
        this.mIsCreateFirstQuery = false;
        if (this.mActivity instanceof MmsTabActivity) {
            this.mUIHandler.removeMessages(1001);
            this.mUIHandler.sendEmptyMessageDelayed(1001, 100);
        }
        this.askSmartMessageRecommend();
        Contact.asyncloadAllInBackground();
        if (this.mCloudStateView != null && this.mShowCloudStateView) {
            this.mCloudStateView.registerObserver();
            this.mCloudStateView.updateState(true);
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mWildMsgDialog != null) {
            this.mWildMsgDialog.cancel();
        }
        this.mWildMsgDialog = null;
        this.mListAdapter.setOnContentChangedListener(null);
        DraftCache.getInstance().removeOnDraftChangedListener(this);
        Contact.removeAllListener();
        if (this.mUIHandler != null) {
            this.mUIHandler.removeMessages(1001);
            this.mUIHandler.removeMessages(1003);
            this.mUIHandler.removeMessages(1006);
        }
        this.mIsStoped = true;
        if (this.mCloudStateView != null && this.mShowCloudStateView) {
            this.mCloudStateView.unregisterObserver();
        }
        this.mListView.enableEmptyImgView(false);
        this.mListView.clearEmptyImgAndTxt();
    }

    public void restoreHintView() {
        if (this.mCloudRecommendView != null) {
            this.mCloudRecommendView.setVisibility(this.mRecommendVisible);
        }
        if (this.mYellowpageRecommendView != null && this.mRecommendVisible == 8) {
            this.mYellowpageRecommendView.setVisibility(this.mYpRecommendVisible);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void startAsyncQuery(boolean bl) {
        try {
            this.mQueryHandler.cancelOperation(1701);
            Conversation.startQueryForAll(this.mQueryHandler, 1701, false, this.mIsCompositeMode, this.mPlaceHolderType);
            if (this.mIsCompositeMode && bl) {
                this.startQueryUnSeenSPCount();
                if (this.mListAdapter != null) {
                    this.mListAdapter.resetSpSentinelId();
                }
                this.mQueryHandler.cancelOperation(1902);
                Conversation.startQuerySpSentinelId(this.mQueryHandler, 1902);
            }
            if (bl) {
                if (Conversation.isBlockedPlaceHolder(this.mPlaceHolderType)) {
                    this.startQueryUnSeenBlockedCount();
                }
                this.mQueryHandler.cancelOperation(1903);
                Conversation.startQueryHaveLockedMessages(this.mQueryHandler, -1, 1903, false);
            }
            if (bl) {
                this.startQueryTimedThreads();
                this.startQuerySearchHintCount();
            }
            if (this.mCloudStateView != null) {
                this.mCloudStateView.updateState();
            }
            return;
        }
        catch (SQLiteException var2_2) {
            SqliteWrapper.checkSQLiteException((Context)this.mActivity, (SQLiteException)var2_2);
            return;
        }
    }

    protected void startQuerySearchHintCount() {
        this.mQueryHandler.cancelOperation(1704);
        Uri uri = this.sSearchHintUri.buildUpon().appendQueryParameter("privacy_flag", "0").build();
        this.mQueryHandler.startQuery(1704, (Object)null, uri, null, null, null, null);
    }

    protected void startQueryTimedThreads() {
        this.mQueryHandler.cancelOperation(1702);
        this.mQueryHandler.cancelOperation(1703);
        Conversation.TimedThreads.clear();
        this.mFinishedTimedQuery = 0;
        this.mQueryHandler.startQuery(1702, (Object)null, Telephony.Sms.CONTENT_URI, SMS_THREAD_ID_PROJECTION, "timed>0", null, null);
        this.mQueryHandler.startQuery(1703, (Object)null, Telephony.Mms.CONTENT_URI, MMS_THREAD_ID_PROJECTION, "timed>0", null, null);
    }

    public static class DeleteThreadListener
    implements DialogInterface.OnClickListener {
        private final Context mContext;
        private boolean mDeleteLockedMessages;
        private final AsyncQueryHandler mHandler;
        private final Collection<Long> mThreadIds;

        public DeleteThreadListener(Collection<Long> collection, AsyncQueryHandler asyncQueryHandler, Context context) {
            this.mThreadIds = collection;
            this.mHandler = asyncQueryHandler;
            this.mContext = context;
        }

        public void onClick(DialogInterface dialogInterface, int n) {
            MessageUtils.handleReadReport(this.mContext, this.mThreadIds, 129, new Runnable(){

                @Override
                public void run() {
                    mDeleteProgressDialog = ProgressDialog.show((Context)DeleteThreadListener.this.mContext, (CharSequence)null, (CharSequence)DeleteThreadListener.this.mContext.getString(2131362131), (boolean)true, (boolean)false);
                    if (DeleteThreadListener.this.mThreadIds == null) {
                        Conversation.startDeleteAll(DeleteThreadListener.this.mHandler, 1801, DeleteThreadListener.this.mDeleteLockedMessages);
                        return;
                    }
                    Conversation.startDelete(DeleteThreadListener.this.mHandler, 1801, DeleteThreadListener.this.mDeleteLockedMessages, DeleteThreadListener.this.mThreadIds);
                }
            });
            dialogInterface.dismiss();
        }

        public void setDeleteLockedMessage(boolean bl) {
            this.mDeleteLockedMessages = bl;
        }

    }

    protected class ModeCallback
    implements EditableListView.EditModeListener {
        private ConversationListAdapter mAdapter;
        private EditableListView.EditableListViewCheckable mCheckable;
        private EditActionMode mEditActionMode;
        private HeaderViewListAdapter mHeaderAdapter;
        private Menu mRootMenu;

        protected ModeCallback() {
        }

        /*
         * Exception decompiling
         */
        private void updateMenu(int var1_1, boolean var2_2, Set<Long> var3_3) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onActionItemClicked(ActionMode hashSet, MenuItem object) {
            switch (object.getItemId()) {
                case 2131820908: {
                    hashSet = this.mCheckable.getCheckedItemInIds();
                    if (hashSet.size() > 0) {
                        ConversationFragment.confirmDeleteThreads(hashSet, ConversationFragment.this.mQueryHandler);
                        return true;
                    }
                }
                default: {
                    return true;
                }
                case 16908313: {
                    hashSet.finish();
                    return true;
                }
                case 16908314: {
                    if (this.mCheckable.isAllChecked()) {
                        this.mCheckable.checkNothing();
                        return true;
                    }
                    this.mCheckable.checkAll();
                    return true;
                }
                case 2131820907: {
                    hashSet = this.mCheckable.getCheckedItemInIds();
                    object = ConversationFragment.this;
                    boolean bl = !ConversationFragment.this.isSticky(hashSet);
                    ((ConversationFragment)object).stickThread(hashSet, bl);
                    return true;
                }
                case 2131820906: 
            }
            hashSet = this.mCheckable.getCheckedItemInIds();
            ConversationFragment.this.addBlackList((Context)ConversationFragment.this.mActivity, hashSet);
            return true;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void onCheckStateChanged(EditableListView.EditableListViewCheckable editableListViewCheckable) {
            HashSet<Long> hashSet = editableListViewCheckable.getCheckedItemInIds();
            this.mAdapter.setCheckedItem(hashSet);
            boolean bl = ConversationFragment.this.isSticky(hashSet);
            this.mCheckable = editableListViewCheckable;
            int n = this.mCheckable.count();
            bl = !bl;
            this.updateMenu(n, bl, (Set<Long>)hashSet);
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.mRootMenu = menu;
            ConversationFragment.this.mActivity.getMenuInflater().inflate(2131755010, menu);
            this.mHeaderAdapter = (HeaderViewListAdapter)ConversationFragment.this.mListView.getAdapter();
            this.mAdapter = (ConversationListAdapter)this.mHeaderAdapter.getWrappedAdapter();
            this.mAdapter.enterCheckMode();
            this.mCheckable = ConversationFragment.this.mListView.getEditableListViewCheckable();
            this.mEditActionMode = (EditActionMode)actionMode;
            actionMode.setTitle(R.string.action_mode_title_empty);
            this.mEditActionMode.setButton(16908313, 17039360);
            this.mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
            this.updateMenu(0, false, (Set<Long>)new HashSet(0));
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mAdapter.exitCheckMode();
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public void onVisibleViewCheckStateChanged(View view, boolean bl) {
        }
    }

    private static class QueryActivateStatusTask
    extends AsyncTask<Void, Void, Integer> {
        private Activity mActivity;
        private boolean mHasVoip;
        private boolean mVoipHasActivated;

        public QueryActivateStatusTask(Activity activity) {
            this.mActivity = activity;
            this.mHasVoip = false;
            this.mVoipHasActivated = false;
        }

        protected /* varargs */ Integer doInBackground(Void ... arrvoid) {
            this.mHasVoip = MessageUtils.hasVoipPackage((Context)this.mActivity);
            this.mVoipHasActivated = MiuiSettings.MiuiVoip.isVoipActivated((Context)this.mActivity);
            return MxActivateService.getSimActivateStatus((Context)this.mActivity);
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void onPostExecute(Integer n) {
            if (n == 3) {
                if (MessageUtils.isMxDisabledBySlotId((Context)this.mActivity, 0)) {
                    MxActivateService.enableMx((Context)this.mActivity, 0, true, true);
                }
                if (MSimUtils.isMSim() && MessageUtils.isMxDisabledBySlotId((Context)this.mActivity, 1)) {
                    MxActivateService.enableMx((Context)this.mActivity, 1, true, true);
                }
                if (!this.mHasVoip || this.mVoipHasActivated) return;
                {
                    n = new Intent("com.miui.voip.action.TURN_ON_VOIP");
                    n.putExtra("extra_turn_on_voip_from", 2);
                    this.mActivity.sendBroadcast((Intent)n);
                    return;
                }
            } else {
                if (n != 1) return;
                {
                    new AlertDialog.Builder((Context)this.mActivity).setTitle(2131362401).setCancelable(false).setMessage(2131362404).setPositiveButton(2131362403, new DialogInterface.OnClickListener(){

                        public void onClick(DialogInterface dialogInterface, int n) {
                            MessageUtils.activatePhone((Context)QueryActivateStatusTask.this.mActivity, QueryActivateStatusTask.this.mHasVoip);
                        }
                    }).setNegativeButton(2131361892, null).show();
                    return;
                }
            }
        }

    }

    protected final class ThreadListQueryHandler
    extends AsyncQueryHandler {
        public ThreadListQueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        /*
         * Enabled aggressive block sorting
         */
        private void showBookMark() {
            int n = 0;
            if (ConversationFragment.this.getActivity() == null) return;
            if (ConversationFragment.this.mBookmarkVisible) {
                if (ConversationFragment.this.mBookmarkView == null) {
                    ConversationFragment.this.mBookmarkView = ConversationFragment.this.getActivity().getLayoutInflater().inflate(2130968596, null, false);
                }
                if (!ConversationFragment.this.mBookmarkViewAdded) {
                    ConversationFragment.this.mListView.addHeaderView(ConversationFragment.this.mBookmarkView);
                    ConversationFragment.this.mListView.resetHeaderHeight();
                    ConversationFragment.this.mBookmarkViewAdded = true;
                }
                View view = ConversationFragment.this.mBookmarkView.findViewById(2131820599);
                if (ConversationFragment.this.mIsSimpleMode) {
                    n = 8;
                }
                view.setVisibility(n);
                return;
            } else {
                if (ConversationFragment.this.mBookmarkView == null) return;
                {
                    ConversationFragment.this.mListView.removeHeaderView(ConversationFragment.this.mBookmarkView);
                    ConversationFragment.this.mListView.resetHeaderHeight();
                    ConversationFragment.this.mBookmarkViewAdded = false;
                    return;
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void onDeleteComplete(int n, Object object, int n2) {
            switch (n) {
                default: {
                    return;
                }
                case 1801: {
                    ConversationFragment.this.mListView.exitEditMode();
                    DraftCache.getInstance().refresh();
                    Conversation.init((Context)ConversationFragment.this.mActivity);
                    MessagingNotification.nonBlockingUpdateNewMessageIndicator((Context)ConversationFragment.this.mActivity, false, false);
                    MessagingNotification.updateSendFailedNotification((Context)ConversationFragment.this.mActivity);
                    if (mDeleteProgressDialog == null) return;
                    mDeleteProgressDialog.dismiss();
                    mDeleteProgressDialog = null;
                    return;
                }
            }
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        protected void onQueryComplete(int var1_1, Object var2_2, Cursor var3_10) {
            Log.v((String)"ConversationFragment", (String)("onQueryComplete token is " + var1_1));
            switch (var1_1) {
                default: {
                    Log.e((String)"ConversationFragment", (String)("onQueryComplete called with unknown token " + var1_1));
                    return;
                }
                case 1701: {
                    if (ConversationFragment.this.mListAdapter == null) {
                        Log.v((String)"ConversationFragment", (String)"not init list adapter");
                        ConversationFragment.this.mIsCreateFirstQuery = false;
                        if (var3_10 == null) return;
                        var3_10.close();
                        return;
                    }
                    ConversationFragment.this.mListAdapter.setCompositeMode(ConversationFragment.this.mIsCompositeMode);
                    ConversationFragment.this.mListAdapter.setPlaceHolderType(ConversationFragment.this.mPlaceHolderType);
                    ConversationFragment.this.mListView.setEmptyTxtViewText(2131361938);
                    if (ConversationFragment.access$300(ConversationFragment.this)) {
                        if (var3_10 != null) {
                            var3_10.close();
                        }
                        Log.v((String)"ConversationFragment", (String)"query cursor close for stop");
                    } else {
                        ConversationFragment.this.mListAdapter.changeCursor(var3_10);
                        ConversationFragment.this.mListView.enableEmptyImgView(true);
                    }
                    ConversationFragment.this.mListAdapter.setUIChangeCursorTime();
                    if (ConversationFragment.access$1800(ConversationFragment.this) == false) return;
                    ConversationFragment.access$1802(ConversationFragment.this, false);
                    ConversationFragment.this.markConversationAsSeen();
                    if (ConversationFragment.this.mActivity instanceof MmsTabActivity == false) return;
                    ConversationFragment.access$600(ConversationFragment.this).removeMessages(1005);
                    ConversationFragment.access$600(ConversationFragment.this).sendEmptyMessageDelayed(1005, 0);
                    return;
                }
                case 1802: {
                    var4_11 = false;
                    var5_12 = false;
                    if (var3_10 == null) ** GOTO lbl40
                    var1_1 = var3_10.getCount();
                    var4_11 = var5_12;
                    if (var1_1 <= 0) ** GOTO lbl40
                    var4_11 = true;
lbl40: // 3 sources:
                    var2_2 = (Collection)var2_2;
                    ConversationFragment.confirmDeleteThreadDialog(new DeleteThreadListener((Collection<Long>)var2_2, ConversationFragment.this.mQueryHandler, (Context)ConversationFragment.this.mActivity), var2_2, var4_11, (Context)ConversationFragment.this.mActivity);
                    return;
                    finally {
                        var3_10.close();
                    }
                }
                case 1702: 
                case 1703: {
                    if (var3_10 == null) return;
                    try {
                        var3_10.moveToPosition(-1);
                        while (var3_10.moveToNext()) {
                            Conversation.TimedThreads.setHasTimedMessage(var3_10.getLong(0));
                        }
                        if (ConversationFragment.access$1904(ConversationFragment.this) != 2) return;
                        ConversationFragment.this.mListAdapter.notifyDataSetChanged();
                        return;
                    }
                    finally {
                        var3_10.close();
                    }
                }
                case 1704: {
                    if (var3_10 == null) return;
                    try {
                        if (ConversationFragment.this.getActivity() == null) return;
                        var1_1 = 0;
                        if (var3_10.getCount() > 0) {
                            var3_10.moveToPosition(0);
                            var1_1 = var3_10.getInt(0);
                        }
                        ConversationFragment.this.mSearchViewHint.setHint((CharSequence)ConversationFragment.this.getResources().getString(2131362212, new Object[]{var1_1}));
                        if (ConversationFragment.access$1200(ConversationFragment.this) == null) return;
                        ConversationFragment.access$1200(ConversationFragment.this).setTotalCountText(ConversationFragment.this.getResources().getQuantityString(2131623941, var1_1, new Object[]{var1_1}));
                        return;
                    }
                    finally {
                        var3_10.close();
                    }
                }
                case 1901: {
                    if (var3_10 == null) return;
                    try {
                        if (var3_10.moveToFirst() == false) return;
                        ConversationFragment.this.mListAdapter.setServiceProviderUnseen(var3_10.getInt(0));
                        return;
                    }
                    finally {
                        var3_10.close();
                    }
                }
                case 1904: {
                    if (var3_10 == null) return;
                    try {
                        if (var3_10.moveToFirst() == false) return;
                        ConversationFragment.this.mListAdapter.setShowBlockedMsgUnseen(var3_10.getInt(0));
                        return;
                    }
                    finally {
                        var3_10.close();
                    }
                }
                case 1902: {
                    if (var3_10 == null) return;
                    try {
                        if (var3_10.moveToFirst() == false) return;
                        ConversationFragment.this.mListAdapter.setSpSentinelId(var3_10.getLong(0));
                        return;
                    }
                    finally {
                        var3_10.close();
                    }
                }
                case 1903: 
            }
            ConversationFragment.this.mBookmarkVisible = false;
            if (var3_10 == null) return;
            try {
                if (var3_10.getCount() > 0) {
                    ConversationFragment.this.mBookmarkVisible = true;
                }
                this.showBookMark();
                return;
            }
            finally {
                var3_10.close();
            }
        }

        protected void onUpdateComplete(int n, Object object, int n2) {
            switch (n) {
                default: {
                    return;
                }
                case 1804: 
            }
            ConversationFragment.this.mListView.exitEditMode();
            ConversationFragment.this.startAsyncQuery(false);
        }
    }

}

