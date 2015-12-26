/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnShowListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.text.TextUtils
 *  android.util.Pair
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnCreateContextMenuListener
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.widget.AbsListView
 *  android.widget.AdapterView
 *  android.widget.AdapterView$AdapterContextMenuInfo
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.ActionBar
 *  miui.app.Activity
 *  miui.app.AlertDialog
 *  miui.app.AlertDialog$Builder
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.PhraseListAdapter;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.app.AlertDialog;

public class PhraseActivity
extends Activity {
    private ActionBar mActionBar;
    private Activity mActivity;
    private ListView mList;
    private AdapterView.OnItemClickListener mOnClickListener;
    private PhraseListAdapter mPhraseListAdapter;
    private SelectedMessage mSelectedMessage;

    public PhraseActivity() {
        this.mOnClickListener = new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                PhraseActivity.this.onListItemClick((ListView)adapterView, view, n, l);
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onListItemClick(ListView listView, View view, int n, long l) {
        if (n - this.mList.getHeaderViewsCount() < 0 || view.getParent() == null) {
            return;
        }
        view.showContextMenu();
    }

    public void onBackPressed() {
        this.setResult(-1, null);
        super.onBackPressed();
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return super.onContextItemSelected(menuItem);
            }
            case 1: {
                this.mSelectedMessage.edit();
                return true;
            }
            case 2: 
        }
        this.mSelectedMessage.delete();
        return true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968687);
        this.mActivity = this;
        this.mSelectedMessage = new SelectedMessage();
        this.mPhraseListAdapter = new PhraseListAdapter((Context)this, 2130968685);
        this.mList = (ListView)this.findViewById(2131820842);
        this.mList.setAdapter((ListAdapter)this.mPhraseListAdapter);
        this.mList.setOnCreateContextMenuListener((View.OnCreateContextMenuListener)this);
        this.mList.setOnItemClickListener(this.mOnClickListener);
        MessageUtils.setListViewTouchPadding((AbsListView)this.mList);
        bundle = (TextView)this.findViewById(2131820564);
        if (bundle != null) {
            bundle.setText((CharSequence)this.getResources().getString(2131362103));
            this.mList.setEmptyView((View)bundle);
        }
        this.mActionBar = this.getActionBar();
        this.mActionBar.setTitle(2131362096);
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View object, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, (View)object, contextMenuInfo);
        int n = ((AdapterView.AdapterContextMenuInfo)contextMenuInfo).position;
        object = (String)this.mPhraseListAdapter.getItem(n);
        this.mSelectedMessage.selectPhrase(n, (String)object);
        contextMenu.setHeaderTitle((CharSequence)this.mSelectedMessage.getMessage());
        contextMenu.add(0, 1, 0, (CharSequence)this.getResources().getString(2131361960));
        contextMenu.add(0, 2, 0, (CharSequence)this.getResources().getString(2131362100));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(2131755013, menu);
        return true;
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

    /*
     * Enabled aggressive block sorting
     */
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 16908332: {
                this.setResult(-1, null);
                this.finish();
                return true;
            }
            case 2131820920: {
                if (this.mPhraseListAdapter.isFull()) return false;
                this.mSelectedMessage.selectPhrase(-1, null);
                this.mSelectedMessage.edit();
                return true;
            }
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    public void onStart() {
        super.onStart();
        this.mPhraseListAdapter.load();
    }

    public void onStop() {
        super.onStop();
        this.mPhraseListAdapter.notifyDataSetInvalidated();
    }

    private class SelectedMessage {
        private AlertDialog mEditNewPhraseDialog;
        private TextView mEditNewPhraseView;
        private View mEditNewPhraseViewContainer;
        private boolean mNewPhrase;
        private Pair<String, Integer> mSelectedPhrase;

        public SelectedMessage() {
            this.buildEditNewPhraseDialog();
        }

        static /* synthetic */ Pair access$200(SelectedMessage selectedMessage) {
            return selectedMessage.mSelectedPhrase;
        }

        private void buildEditNewPhraseDialog() {
            this.mEditNewPhraseViewContainer = LayoutInflater.from((Context)PhraseActivity.this.mActivity).inflate(2130968684, null);
            this.mEditNewPhraseView = (TextView)this.mEditNewPhraseViewContainer.findViewById(2131820839);
            AlertDialog.Builder builder = new AlertDialog.Builder((Context)PhraseActivity.this.mActivity);
            builder.setCancelable(true);
            builder.setView(this.mEditNewPhraseViewContainer);
            builder.setOnShowListener(new DialogInterface.OnShowListener(){

                public void onShow(DialogInterface dialogInterface) {
                    MessageUtils.requestInputMethod((Context)PhraseActivity.this.mActivity, SelectedMessage.this.mEditNewPhraseView);
                }
            });
            builder.setPositiveButton(2131361998, new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface object, int n) {
                    object = SelectedMessage.this.mEditNewPhraseView.getText().toString();
                    if (SelectedMessage.this.mNewPhrase) {
                        SelectedMessage.this.mNewPhrase = false;
                        if (TextUtils.isEmpty((CharSequence)object)) {
                            return;
                        }
                        PhraseActivity.this.mPhraseListAdapter.addPhrase((String)object);
                        return;
                    }
                    if (TextUtils.isEmpty((CharSequence)object)) {
                        SelectedMessage.this.delete();
                        return;
                    }
                    PhraseActivity.this.mPhraseListAdapter.setPhrase((Integer)SelectedMessage.access$200((SelectedMessage)SelectedMessage.this).second, (String)object);
                }
            });
            builder.setNegativeButton(2131361892, null);
            this.mEditNewPhraseDialog = builder.create();
        }

        public void delete() {
            AlertDialog.Builder builder = new AlertDialog.Builder((Context)PhraseActivity.this.mActivity);
            builder.setTitle(2131362100);
            builder.setMessage(2131362101);
            builder.setCancelable(true);
            builder.setPositiveButton(2131361930, new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    PhraseActivity.this.mPhraseListAdapter.deletePhrase((Integer)SelectedMessage.access$200((SelectedMessage)SelectedMessage.this).second);
                }
            });
            builder.setNegativeButton(2131361892, null);
            builder.show();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void edit() {
            String string2;
            int n;
            if (!this.mNewPhrase) {
                string2 = (String)this.mSelectedPhrase.first;
                n = 2131362098;
            } else {
                string2 = null;
                n = 2131362097;
            }
            this.mEditNewPhraseView.setText((CharSequence)string2);
            this.mEditNewPhraseDialog.setTitle((CharSequence)PhraseActivity.this.getResources().getString(n));
            this.mEditNewPhraseDialog.show();
        }

        public String getMessage() {
            return (String)this.mSelectedPhrase.first;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void selectPhrase(int n, String string2) {
            this.mNewPhrase = string2 == null;
            this.mSelectedPhrase = new Pair((Object)string2, (Object)n);
        }

    }

}

