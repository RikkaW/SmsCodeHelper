/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.graphics.Rect
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.provider.MiuiSettings
 *  android.provider.MiuiSettings$MiuiVoip
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ArrayAdapter
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.PopupWindow
 *  android.widget.TextView
 *  java.lang.Boolean
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  miui.provider.VoipContract
 *  miui.provider.VoipContract$UsersColumn
 *  miui.yellowpage.MiPubUtils
 *  miui.yellowpage.ModuleIntent
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MiuiSettings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.ConversationBase;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.MessageListPullView;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SingleRecipientConversationHeader;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.util.EditableListView;
import com.android.mms.util.MiStatSdkHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miui.provider.VoipContract;
import miui.yellowpage.MiPubUtils;
import miui.yellowpage.ModuleIntent;

public class SingleRecipientConversationActivity
extends ConversationBase {
    private IntentFilter mActivateMXBCFilter;
    protected Contact mContact;
    private BroadcastReceiver mFinishReceiver;
    private SingleRecipientConversationHeader mHeader;
    private AsyncTask<Void, Void, Void> mLoadLocalMenuTask;
    protected ArrayList<ModuleIntent> mMenuList;
    private BroadcastReceiver mMxStatusReceiver;
    protected PopupWindow mPopupMenu;
    private AsyncTask<Void, Void, Void> mRemoteMenuTask;
    private UnderstandLoader.RequestCallback mRequestCallback;
    private AsyncTask<Void, Void, Boolean> mVoipQueryTask;
    protected Long mYellowPageId;
    protected String mYellowPageName;

    public SingleRecipientConversationActivity() {
        this.mFinishReceiver = new BroadcastReceiver(){

            /*
             * Enabled aggressive block sorting
             */
            public void onReceive(Context object, Intent intent) {
                object = intent.getAction();
                if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(object)) {
                    if (!"homekey".equals((Object)intent.getStringExtra("reason")) || !SingleRecipientConversationActivity.this.mConversation.isPrivate()) return;
                    {
                        SingleRecipientConversationActivity.this.finish();
                        return;
                    }
                } else {
                    if (!"android.intent.action.SCREEN_OFF".equals(object) || !SingleRecipientConversationActivity.this.mConversation.isPrivate()) return;
                    {
                        SingleRecipientConversationActivity.this.finish();
                        return;
                    }
                }
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createHeaderMenu(Contact contact) {
        if (contact.isB2cNumber() || contact.isEmail()) {
            this.mHeader.hideCallMenu();
            return;
        } else {
            if (!MiuiSettings.MiuiVoip.isVoipEnabled((Context)this)) return;
            {
                Log.v((String)"SingleRecipientCA", (String)" query voip status ");
                this.queryVoipStatus(contact.getNumber());
                return;
            }
        }
    }

    private void onModuleIntentClick(ModuleIntent moduleIntent) {
        MiStatSdkHelper.recordBottomMenuClick(this.mYellowPageName, moduleIntent.getTitle());
        moduleIntent = moduleIntent.getIntent();
        moduleIntent.putExtra("source", "sms_bottom_menu");
        moduleIntent.putExtra("com.miui.yellowpage.extra.yid", (Serializable)this.mYellowPageId);
        this.startActivity((Intent)moduleIntent);
    }

    private void registerFinishReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.registerReceiver(this.mFinishReceiver, intentFilter);
    }

    private void requestRemoteMenu() {
        if (this.mRemoteMenuTask != null) {
            return;
        }
        this.mRemoteMenuTask = new AsyncTask<Void, Void, Void>(){

            protected /* varargs */ Void doInBackground(Void ... arrvoid) {
                MiPubUtils.getYellowPageMenu((Context)SingleRecipientConversationActivity.this, (long)SingleRecipientConversationActivity.this.mYellowPageId, (boolean)true, (int)4);
                return null;
            }

            protected void onPostExecute(Void void_) {
                SingleRecipientConversationActivity.this.mRemoteMenuTask = null;
            }
        };
        this.mRemoteMenuTask.execute((Object[])new Void[0]);
    }

    private void toggleSubMenu(View view, ModuleIntent moduleIntent) {
        MiStatSdkHelper.recordBottomMenuClick(this.mYellowPageName, moduleIntent.getTitle());
        if (this.mPopupMenu == null || !this.mPopupMenu.isShowing()) {
            View view2 = LayoutInflater.from((Context)MmsApp.getApp().getApplicationContext()).inflate(2130968636, null);
            this.mPopupMenu = new PopupWindow(view2, -2, -2);
            moduleIntent = moduleIntent.getSubModuleIntent();
            ArrayList arrayList = new ArrayList();
            Iterator iterator = moduleIntent.iterator();
            while (iterator.hasNext()) {
                arrayList.add(((ModuleIntent)iterator.next()).getTitle());
            }
            iterator = (ListView)view2.findViewById(2131820705);
            iterator.setAdapter((ListAdapter)new ArrayAdapter((Context)this, 2130968637, 2131820629, (List)arrayList));
            iterator.setOnItemClickListener(new AdapterView.OnItemClickListener((ArrayList)moduleIntent){
                final /* synthetic */ ArrayList val$subMenus;

                public void onItemClick(AdapterView<?> moduleIntent, View view, int n, long l) {
                    moduleIntent = (ModuleIntent)this.val$subMenus.get(n);
                    SingleRecipientConversationActivity.this.mPopupMenu.dismiss();
                    SingleRecipientConversationActivity.this.onModuleIntentClick(moduleIntent);
                }
            });
            iterator.setOverScrollMode(2);
            this.mPopupMenu.setFocusable(true);
            this.mPopupMenu.setBackgroundDrawable((Drawable)new BitmapDrawable());
            this.mPopupMenu.setOutsideTouchable(true);
            iterator.measure(0, 0);
            view2 = view2.getBackground();
            arrayList = new Rect();
            if (view2 != null) {
                view2.getPadding((Rect)arrayList);
            }
            int n = this.getResources().getDimensionPixelSize(2131427479);
            this.mPopupMenu.setHeight(iterator.getMeasuredHeight() * moduleIntent.size() + arrayList.top + arrayList.bottom);
            this.mPopupMenu.setWidth(n);
            this.mPopupMenu.showAsDropDown(view, (view.getWidth() - n) / 2, 0);
        }
    }

    private void unRegisterFinishReceiver() {
        this.unregisterReceiver(this.mFinishReceiver);
    }

    @Override
    protected int getContentViewResId() {
        return 2130968707;
    }

    @Override
    protected void initMessageList() {
        super.initMessageList();
        this.enableNotShowAllMsgList();
        if (this.getRecipients() != null && this.getRecipients().size() > 0) {
            String string2 = ((Contact)this.getRecipients().get(0)).getNumber();
            if (MessageUtils.allowMenuMode() && ((Contact)this.getRecipients().get(0)).isYellowPageNumber()) {
                this.prepareMenuMode();
            }
            this.createHeaderMenu((Contact)this.getRecipients().get(0));
            this.mMsgListAdapter.setThreadAddress(string2);
            this.mWaitingResource = true;
            Log.v((String)"SingleRecipientCA", (String)" begin request loading resources");
            if (this.mRequestCallback == null) {
                this.mRequestCallback = new UnderstandLoader.RequestCallback(){

                    @Override
                    public void onRequestDone(boolean bl) {
                        Log.v((String)"SingleRecipientCA", (String)" request loading resources done");
                        SingleRecipientConversationActivity.this.mWaitingResource = false;
                        SingleRecipientConversationActivity.this.applyPendingCursor();
                    }
                };
            }
            UnderstandLoader.request(string2, this.mRequestCallback);
        }
        this.mMsgListView.setOnItemDoubleClickListener(new EditableListView.OnItemDoubleClickListener(){

            @Override
            public void onDoubleClick(AdapterView<?> adapterView, View view, int n, long l) {
                Log.v((String)"TAG", (String)("onDoubleClick " + n));
                if ((n -= SingleRecipientConversationActivity.this.mMsgListView.getFirstVisiblePosition()) >= 0 && n < SingleRecipientConversationActivity.this.mMsgListView.getChildCount() && SingleRecipientConversationActivity.this.mMsgListView.getChildAt(n) instanceof MessageListItem) {
                    ((MessageListItem)SingleRecipientConversationActivity.this.mMsgListView.getChildAt(n)).onMessageListItemDoubleClick();
                }
            }

            @Override
            public void onSingleClick(AdapterView<?> adapterView, View view, int n, long l) {
                Log.v((String)"TAG", (String)("onSingleClick " + n));
                if ((n -= SingleRecipientConversationActivity.this.mMsgListView.getFirstVisiblePosition()) >= 0 && n < SingleRecipientConversationActivity.this.mMsgListView.getChildCount() && SingleRecipientConversationActivity.this.mMsgListView.getChildAt(n) instanceof MessageListItem) {
                    ((MessageListItem)SingleRecipientConversationActivity.this.mMsgListView.getChildAt(n)).onMessageListItemClick();
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
        SingleRecipientConversationActivity.cancelFailedDownloadNotification(this.getIntent(), (Context)this);
    }

    @Override
    protected void onContactAdded(Contact contact) {
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivateMXBCFilter = new IntentFilter();
        this.mActivateMXBCFilter.addAction("com.xiaomi.mms.action.ENBALE_RESULT");
        this.mMxStatusReceiver = new MxStatusReceiver();
        this.registerFinishReceiver();
    }

    @Override
    protected void onDestroy() {
        if (this.getRecipients() != null && this.getRecipients().size() > 0) {
            UnderstandLoader.destroy(((Contact)this.getRecipients().get(0)).getNumber(), this.mRequestCallback);
        }
        this.unRegisterFinishReceiver();
        super.onDestroy();
    }

    @Override
    public void onMxIdAdded(String string2, String string3) {
        super.onMxIdAdded(string2, string3);
        this.postSwitchMsgType();
    }

    protected void onPause() {
        super.onPause();
        MiStatSdkHelper.recordPageEnd();
        MessagingNotification.setCurrentMessageThreadId(0);
        if (this.mMxStatusReceiver != null) {
            this.unregisterReceiver(this.mMxStatusReceiver);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MiStatSdkHelper.recordPageStart((Activity)this, "SingRecipient");
        Object object = this.getRecipients();
        if (object != null && !object.isEmpty()) {
            object = object.iterator();
            while (object.hasNext()) {
                super.onContactAdded((Contact)object.next());
            }
        }
        MessagingNotification.setCurrentMessageThreadId(this.mConversation.getThreadId());
        if (this.mMxStatusReceiver != null && this.mActivateMXBCFilter != null) {
            this.registerReceiver(this.mMxStatusReceiver, this.mActivateMXBCFilter);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void prepareMenuMode() {
        if (this.mConversation == null || this.mConversation.getRecipients() == null || this.mConversation.getRecipients().size() < 1) {
            return;
        }
        if (this.mLoadLocalMenuTask != null) {
            this.mLoadLocalMenuTask.cancel(true);
        }
        this.mLoadLocalMenuTask = new AsyncTask<Void, Void, Void>(){

            protected /* varargs */ Void doInBackground(Void ... arrvoid) {
                SingleRecipientConversationActivity.this.mMenuList = MiPubUtils.getYellowPageMenu((Context)SingleRecipientConversationActivity.this, (long)SingleRecipientConversationActivity.this.mYellowPageId, (boolean)false, (int)4);
                return null;
            }

            protected void onPostExecute(Void void_) {
                SingleRecipientConversationActivity.this.mLoadLocalMenuTask = null;
                if (SingleRecipientConversationActivity.this.isFinishing()) {
                    return;
                }
                SingleRecipientConversationActivity.this.findViewById(2131820694).setVisibility(0);
                SingleRecipientConversationActivity.this.showMenuMode();
                SingleRecipientConversationActivity.this.requestRemoteMenu();
            }

            protected void onPreExecute() {
                SingleRecipientConversationActivity.this.mContact = (Contact)SingleRecipientConversationActivity.this.mConversation.getRecipients().get(0);
                SingleRecipientConversationActivity.this.mYellowPageId = SingleRecipientConversationActivity.this.mContact.getYellowPageId();
                SingleRecipientConversationActivity.this.mYellowPageName = SingleRecipientConversationActivity.this.mContact.getName();
            }
        };
        this.mLoadLocalMenuTask.execute((Object[])new Void[0]);
    }

    protected void queryVoipStatus(final String string2) {
        if (this.mVoipQueryTask != null) {
            this.mVoipQueryTask.cancel(true);
        }
        this.mVoipQueryTask = new AsyncTask<Void, Void, Boolean>(){

            /*
             * Enabled aggressive block sorting
             */
            protected /* varargs */ Boolean doInBackground(Void ... cursor) {
                boolean bl = true;
                cursor = SingleRecipientConversationActivity.this.getContentResolver().query(VoipContract.USERS_URI, VoipContract.UsersColumn.PROJECTION, null, new String[]{string2}, null);
                Log.v((String)"SingleRecipientCA", (String)(" query result cursor is " + (Object)cursor));
                if (cursor == null) {
                    bl = false;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return bl;
            }

            /*
             * Enabled aggressive block sorting
             */
            protected void onPostExecute(Boolean bl) {
                SingleRecipientConversationActivity.this.mVoipQueryTask = null;
                if (SingleRecipientConversationActivity.this.isFinishing() || !bl.booleanValue()) {
                    return;
                }
                SingleRecipientConversationActivity.this.mHeader.updateState(true);
            }
        };
        this.mVoipQueryTask.execute((Object[])new Void[0]);
    }

    protected void showInputMode() {
        this.findViewById(2131820688).setVisibility(0);
        this.findViewById(2131820681).setVisibility(8);
        if (this.mTextEditor != null) {
            this.mTextEditor.requestFocus();
        }
        this.mSwitchBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SingleRecipientConversationActivity.this.showMenuMode();
            }
        });
        this.mSwitchBtn.setBackgroundResource(2130837851);
        this.mIsSwitchMenuBtn = true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void showMenuMode() {
        View view;
        int n;
        if (this.mMenuList == null) {
            return;
        }
        this.hideSoftKeyboard();
        if (this.mSimButtonContainer != null) {
            this.mSimButtonContainer.setVisibility(8);
        }
        if ((view = this.findViewById(2131820681)) == null) return;
        TextView[] arrtextView = new TextView[]{(TextView)view.findViewById(2131820683), (TextView)view.findViewById(2131820684), (TextView)view.findViewById(2131820685), (TextView)view.findViewById(2131820686)};
        if (this.mMenuList.size() == 0) {
            Log.w((String)"SingleRecipientCA", (String)"no menu item found");
            return;
        }
        MiStatSdkHelper.recordBottomMenuShown(this.mYellowPageName);
        int n2 = n = this.mMenuList.size();
        if (n > 4) {
            n2 = 4;
        }
        for (n = 0; n < n2; ++n) {
            final ModuleIntent moduleIntent = (ModuleIntent)this.mMenuList.get(n);
            if (moduleIntent.getSubItemsFlag()) {
                arrtextView[n].setBackgroundResource(2130837852);
            }
            arrtextView[n].setText((CharSequence)moduleIntent.getTitle());
            arrtextView[n].setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    if (moduleIntent.getSubItemsFlag()) {
                        SingleRecipientConversationActivity.this.toggleSubMenu(view, moduleIntent);
                        return;
                    }
                    SingleRecipientConversationActivity.this.onModuleIntentClick(moduleIntent);
                }
            });
        }
        for (n2 = this.mMenuList.size(); n2 < 4; ++n2) {
            arrtextView[n2].setVisibility(8);
        }
        view.setVisibility(0);
        view = (Button)view.findViewById(2131820682);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SingleRecipientConversationActivity.this.showInputMode();
                }
            });
        }
        this.findViewById(2131820688).setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void startMsgListQuery() {
        Object object;
        Uri uri;
        Object object2;
        Log.v((String)"SingleRecipientCA", (String)"querying message list");
        uri = this.mConversation.getUri();
        if (uri == null) {
            Log.i((String)"SingleRecipientCA", (String)"conversation uri is null, it is a new conv");
            return;
        }
        object = null;
        try {
            object = object2 = ((Contact)this.getRecipients().get(0)).getNumber();
        }
        catch (Exception var2_5) {}
        object2 = uri;
        if (!TextUtils.isEmpty((CharSequence)object)) {
            object2 = Uri.withAppendedPath((Uri)uri, (String)Uri.encode((String)object));
        }
        object2 = object2.buildUpon().appendQueryParameter("limit", this.buildMsgListQueryLimit());
        object = this.mConversation.isPrivate() ? "1" : "0";
        object = object2.appendQueryParameter("privacy_flag", (String)object).build();
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"SingleRecipientCA", (String)("startMsgListQuery for " + object));
        }
        this.mBackgroundQueryHandler.cancelOperation(9527);
        try {
            this.mBackgroundQueryHandler.startQuery(9527, (Object)null, (Uri)object, MessageListAdapter.PROJECTION, null, null, null);
            return;
        }
        catch (SQLiteException var1_3) {
            SqliteWrapper.checkSQLiteException((Context)this, (SQLiteException)var1_3);
            return;
        }
    }

    @Override
    protected void updateTitle(ContactList contactList) {
        this.mHeader.updateTitle(contactList);
    }

    private class MxStatusReceiver
    extends BroadcastReceiver {
        private MxStatusReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("com.xiaomi.mms.action.ENBALE_RESULT".equals((Object)intent.getAction()) && intent.getBooleanExtra("success", false)) {
                SingleRecipientConversationActivity.this.postSwitchMsgType();
            }
        }
    }

}

