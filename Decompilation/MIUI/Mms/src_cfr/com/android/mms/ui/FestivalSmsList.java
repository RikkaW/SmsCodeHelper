/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Dialog
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.database.Cursor
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.text.TextUtils
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.LayoutInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$AdapterContextMenuInfo
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.CheckBox
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.ActionBar
 *  miui.app.Activity
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.data.FestivalDatabase;
import com.android.mms.ui.FestivalFragment;
import com.android.mms.ui.FestivalSmsListAdapter;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.NewMessageActivity;
import java.util.concurrent.Executor;
import miui.app.ActionBar;
import miui.app.Activity;

public class FestivalSmsList
extends Activity
implements AbsListView.OnScrollListener {
    private ActionBar mActionBar;
    private Activity mActivity;
    private long mCategoryId;
    private View mDownloadingView;
    private TextView mEmptyView;
    private AsyncTask<Void, Void, Boolean> mGettingMoreTask;
    private boolean mIsAtLastItem;
    private boolean mIsGettingMessages;
    private FestivalSmsListAdapter mListAdapter;
    private ListView mListView;
    private String mMessageBody;
    private AdapterView.OnItemClickListener mOnClickListener;
    private boolean mPickerMode;
    private Dialog mShowNetworkingDlg;
    private View mShuffleView;

    public FestivalSmsList() {
        this.mOnClickListener = new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                FestivalSmsList.this.onListItemClick((ListView)adapterView, view, n, l);
            }
        };
    }

    private void checkAndShowNetworkingDialog() {
        View view = View.inflate((Context)this, (int)2130968609, (ViewGroup)null);
        final CheckBox checkBox = (CheckBox)view.findViewById(2131820639);
        checkBox.setChecked(true);
        this.mShowNetworkingDlg = new AlertDialog.Builder((Context)this).setPositiveButton((CharSequence)this.getString(2131362337), new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (checkBox.isChecked()) {
                    FestivalFragment.setAllowNetworkingDialog((Context)FestivalSmsList.this.mActivity, false);
                }
                FestivalFragment.setAllowNetworking((Context)FestivalSmsList.this.mActivity, true);
                FestivalSmsList.this.startGettingMoreMessages();
            }
        }).setNegativeButton(17039360, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (checkBox.isChecked()) {
                    FestivalFragment.setAllowNetworkingDialog((Context)FestivalSmsList.this.mActivity, false);
                }
                FestivalFragment.setAllowNetworking((Context)FestivalSmsList.this.mActivity, false);
            }
        }).setView(view).setCancelable(false).show();
    }

    private void loadCategoryInfo() {
        FestivalDatabase festivalDatabase = FestivalDatabase.getInstance();
        String string2 = "category_id=" + this.mCategoryId;
        if ((festivalDatabase = festivalDatabase.query("categories", new String[]{"title", "image_text"}, string2, null, null, null, null)) == null) {
            return;
        }
        try {
            if (festivalDatabase.moveToFirst()) {
                string2 = festivalDatabase.getString(0);
                String string3 = festivalDatabase.getString(1);
                if (!TextUtils.isEmpty((CharSequence)string3)) {
                    View view = this.getLayoutInflater().inflate(2130968608, null);
                    ((TextView)view.findViewById(2131820638)).setText((CharSequence)string3);
                    this.mListView.addHeaderView(view, (Object)null, false);
                }
                this.mActionBar.setTitle((CharSequence)string2);
            }
            return;
        }
        finally {
            festivalDatabase.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void onListItemClick(ListView object, View view, int n, long l) {
        if ((n -= this.mListView.getHeaderViewsCount()) < 0) {
            return;
        }
        if (this.mPickerMode) {
            object = this.mListAdapter.getItemMessage(n);
            view = new Intent();
            view.putExtra("android.intent.extra.shortcut.NAME", (String)object);
            this.setResult(-1, (Intent)view);
            this.finish();
            return;
        }
        if (view.getParent() == null) return;
        view.showContextMenu();
    }

    private void startGettingMoreMessages() {
        if (this.mIsGettingMessages) {
            return;
        }
        this.mIsGettingMessages = true;
        this.mDownloadingView.setVisibility(0);
        this.mGettingMoreTask = new AsyncTask<Void, Void, Boolean>(){

            protected /* varargs */ Boolean doInBackground(Void ... arrvoid) {
                return FestivalDatabase.getInstance().getMoreMessages(FestivalSmsList.this.mCategoryId);
            }

            /*
             * Enabled aggressive block sorting
             */
            protected void onPostExecute(Boolean bl) {
                FestivalSmsList.this.mGettingMoreTask = null;
                if (!bl.booleanValue()) {
                    Toast.makeText((Context)FestivalSmsList.this, (int)2131362060, (int)0).show();
                } else {
                    FestivalSmsList.this.mListAdapter.requery();
                }
                FestivalSmsList.this.mIsGettingMessages = false;
                FestivalSmsList.this.mDownloadingView.setVisibility(8);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        int n = ((AdapterView.AdapterContextMenuInfo)menuItem.getMenuInfo()).position;
        int n2 = this.mListView.getHeaderViewsCount();
        this.mMessageBody = this.mListAdapter.getItemMessage(n - n2);
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 2131361816: {
                menuItem = new Intent((Context)this, (Class)NewMessageActivity.class);
                menuItem.putExtra("sms_body", this.mMessageBody);
                menuItem.putExtra("forwarded_message", true);
                this.startActivity((Intent)menuItem);
                return true;
            }
            case 2131362080: 
        }
        ((ClipboardManager)this.getSystemService("clipboard")).setText((CharSequence)this.mMessageBody);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.mActivity = this;
        this.setContentView(2130968615);
        object = this.getIntent();
        this.mCategoryId = object.getLongExtra("category_id", -1);
        this.mDownloadingView = this.findViewById(2131820642);
        this.mListView = (ListView)this.findViewById(2131820644);
        this.mListView.setOnScrollListener((AbsListView.OnScrollListener)this);
        this.mListView.setOnItemClickListener(this.mOnClickListener);
        this.mListView.setEmptyView((View)this.mEmptyView);
        MessageUtils.setListViewTouchPadding((AbsListView)this.mListView);
        object = object.getAction();
        boolean bl = object != null && object.equals((Object)"android.intent.action.PICK");
        this.mPickerMode = bl;
        if (!this.mPickerMode) {
            this.registerForContextMenu((View)this.mListView);
        }
        this.mIsAtLastItem = false;
        this.mIsGettingMessages = false;
        this.mActionBar = this.getActionBar();
        this.mActionBar.setHomeButtonEnabled(true);
        this.mActionBar.setCustomView(2130968617);
        this.mActionBar.setDisplayShowCustomEnabled(true);
        this.mShuffleView = this.mActionBar.getCustomView().findViewById(2131820646);
        this.mShuffleView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FestivalSmsList.this.mListAdapter.shuffle();
            }
        });
        this.loadCategoryInfo();
        this.mListAdapter = new FestivalSmsListAdapter((Context)this, this.mCategoryId);
        this.mListView.setAdapter((ListAdapter)this.mListAdapter);
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        contextMenu.add(0, 2131361816, 0, 2131361816);
        contextMenu.add(0, 2131362080, 0, 2131362080);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 16908332: 
        }
        this.finish();
        return true;
    }

    public void onResume() {
        super.onResume();
        if (!MessageUtils.shouldShowFestival((Context)this)) {
            this.finish();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onScroll(AbsListView absListView, int n, int n2, int n3) {
        boolean bl = n3 == n + n2;
        this.mIsAtLastItem = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onScrollStateChanged(AbsListView absListView, int n) {
        if (n != 0 || this.mDownloadingView.getVisibility() != 8 || !this.mIsAtLastItem) return;
        {
            if (FestivalFragment.isAllowNetworkingDialog((Context)this.mActivity)) {
                this.checkAndShowNetworkingDialog();
                return;
            } else {
                if (!FestivalFragment.isAllowNetworking((Context)this)) return;
                {
                    this.startGettingMoreMessages();
                    return;
                }
            }
        }
    }

    protected void onStart() {
        super.onStart();
        this.mListAdapter.requery();
    }

    protected void onStop() {
        super.onStop();
        if (this.mGettingMoreTask != null) {
            this.mGettingMoreTask.cancel(true);
            this.mGettingMoreTask = null;
        }
        if (this.mShowNetworkingDlg != null) {
            this.mShowNetworkingDlg.dismiss();
            this.mShowNetworkingDlg = null;
        }
        this.mListAdapter.close();
    }

}

