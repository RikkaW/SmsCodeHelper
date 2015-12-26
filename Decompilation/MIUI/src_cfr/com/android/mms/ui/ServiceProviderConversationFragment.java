/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.View
 *  android.view.View$OnKeyListener
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.AbsListView$RecyclerListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ImageView
 *  android.widget.ListAdapter
 *  android.widget.TextView
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  miui.R
 *  miui.R$drawable
 *  miui.os.Build
 *  miui.widget.DropDownSingleChoiceMenu
 *  miui.widget.DropDownSingleChoiceMenu$OnMenuListener
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.ConversationFragment;
import com.android.mms.ui.ConversationListAdapter;
import com.android.mms.ui.MessageListPullView;
import com.android.mms.util.EditableListView;
import java.util.ArrayList;
import java.util.List;
import miui.R;
import miui.os.Build;
import miui.widget.DropDownSingleChoiceMenu;

public class ServiceProviderConversationFragment
extends ConversationFragment {
    private View mCategoryTitle;
    private boolean mHasMenu;
    protected final AdapterView.OnItemClickListener mOnClickListener;
    public int mSelectedCategory = 1;
    private List<Integer> mServiceCategoryList = new ArrayList(4);
    private DropDownSingleChoiceMenu mServiceCategoryMenu;
    private List<String> mServiceCategoryNameList = new ArrayList(4);
    private TextView mServiceCategoryText;
    private ImageView mTitleArrow;

    public ServiceProviderConversationFragment() {
        this.mOnClickListener = new AdapterView.OnItemClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onItemClick(AdapterView<?> cursor, View view, int n, long l) {
                if (n == 0 && ServiceProviderConversationFragment.this.mHasMenu) {
                    ServiceProviderConversationFragment.this.showServiceCategoryList();
                    return;
                } else {
                    if (n == ServiceProviderConversationFragment.this.mListView.getAdapter().getCount() - 1) return;
                    {
                        cursor = (Cursor)ServiceProviderConversationFragment.this.mListView.getItemAtPosition(n);
                        l = Conversation.from((Context)ServiceProviderConversationFragment.this.mActivity, cursor).getThreadId();
                        Log.d((String)"ServiceProviderConversationFragment", (String)("onListItemClick: pos=" + n + ", view=" + (Object)view + ", tid=" + l));
                        cursor = ComposeMessageRouterActivity.createIntent((Context)ServiceProviderConversationFragment.this.mActivity, l);
                        ServiceProviderConversationFragment.this.startActivity((Intent)cursor);
                        return;
                    }
                }
            }
        };
        this.initServiceCategoryList();
    }

    private int getSelectedCategoryPosition(int n) {
        for (int i = 0; i < this.mServiceCategoryList.size(); ++i) {
            if (n != this.mServiceCategoryList.get(i)) continue;
            return i;
        }
        return 0;
    }

    private void initServiceCategoryList() {
        this.mServiceCategoryList.add(1);
        this.mServiceCategoryList.add(2);
        Application application = MmsApp.getApp();
        this.mServiceCategoryNameList.add(application.getString(2131362280));
        this.mServiceCategoryNameList.add(application.getString(2131362281));
        this.mSelectedCategory = 1;
    }

    private void initServiceCategoryMenu() {
        this.mServiceCategoryMenu = new DropDownSingleChoiceMenu((Context)this.mActivity);
        this.mServiceCategoryMenu.setItems(this.mServiceCategoryNameList);
        this.mServiceCategoryMenu.setAnchorView(this.mCategoryTitle);
        this.mServiceCategoryMenu.setOnMenuListener(new DropDownSingleChoiceMenu.OnMenuListener(){

            public void onDismiss() {
                ServiceProviderConversationFragment.this.mTitleArrow.setImageResource(R.drawable.expander_open_light);
            }

            public void onItemSelected(DropDownSingleChoiceMenu dropDownSingleChoiceMenu, int n) {
                ServiceProviderConversationFragment.this.onPopupItemClicked(n);
            }

            public void onShow() {
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onPopupItemClicked(int n) {
        if (n < 0 || n >= this.mServiceCategoryList.size() || this.mSelectedCategory == this.mServiceCategoryList.get(n)) {
            return;
        }
        this.mSelectedCategory = this.mServiceCategoryList.get(n);
        if (this.mSelectedCategory != 1) {
            this.mListView.setHeaderHidden(false);
        } else {
            this.mListView.setHeaderHidden(true);
        }
        this.updateCategoryTitle(this.mServiceCategoryNameList.get(n));
        this.startAsyncQuery(false);
    }

    private void showServiceCategoryList() {
        this.mTitleArrow.setImageResource(R.drawable.expander_close_light);
        this.mServiceCategoryMenu.setSelectedItem(this.getSelectedCategoryPosition(this.mSelectedCategory));
        this.mServiceCategoryMenu.show();
    }

    private void updateCategoryTitle(String string2) {
        string2 = String.format((String)this.getActivity().getString(2131362279), (Object[])new Object[]{string2});
        this.mServiceCategoryText.setText((CharSequence)string2);
    }

    @Override
    protected void initListCompositeMode(Context context) {
        this.mIsCompositeMode = false;
        this.mPlaceHolderType &= -2;
        this.mPlaceHolderType &= -3;
        if (this.mListAdapter != null) {
            this.mListAdapter.setCompositeMode(this.mIsCompositeMode);
            this.mListAdapter.setPlaceHolderType(this.mPlaceHolderType);
        }
    }

    @Override
    protected void markConversationAsSeen() {
        Conversation.markServiceProviderConversationAsSeen(this.mActivity.getApplicationContext(), this.mSelectedCategory);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mActivity = this.getActivity();
        this.mQueryHandler = new ConversationFragment.ThreadListQueryHandler(this.mActivity.getContentResolver());
        this.mRootView = layoutInflater.inflate(2130968600, null);
        this.mListView = (MessageListPullView)this.mRootView.findViewById(16908298);
        this.mListView.setOnKeyListener(this.mThreadListKeyListener);
        this.mListView.setEditModeListener(new ConversationFragment.ModeCallback());
        this.mListView.setAllowTranscriptOnResize(false);
        boolean bl = !Build.checkRegion((String)"IN");
        this.mHasMenu = bl;
        if (this.mHasMenu) {
            this.mCategoryTitle = layoutInflater.inflate(2130968702, null);
            this.mServiceCategoryText = (TextView)this.mCategoryTitle.findViewById(2131820863);
            this.mListView.addHeaderView(this.mCategoryTitle);
            if (this.mSelectedCategory != 1) {
                this.mListView.setHeaderHidden(false);
            } else {
                this.mListView.setHeaderHidden(true);
            }
            this.mTitleArrow = (ImageView)this.mCategoryTitle.findViewById(2131820864);
            int n = this.getSelectedCategoryPosition(this.mSelectedCategory);
            this.updateCategoryTitle(this.mServiceCategoryNameList.get(n));
            this.initServiceCategoryMenu();
        } else {
            this.mListView.setHeaderHidden(false);
        }
        this.mListView.enableEmptyImgView(false);
        this.mListView.setEmptyImgViewImageResource(2130837594);
        this.mListAdapter = new ConversationListAdapter((Context)this.mActivity, null);
        this.mListView.setAdapter((ListAdapter)this.mListAdapter);
        this.mListView.setRecyclerListener((AbsListView.RecyclerListener)this.mListAdapter);
        this.mListView.setOnItemClickListener(this.mOnClickListener);
        this.mListView.setOnScrollListener((AbsListView.OnScrollListener)this);
        this.mListView.enableDragEvent(true);
        this.initialize();
        return this.mRootView;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void startAsyncQuery(boolean bl) {
        try {
            this.mQueryHandler.cancelOperation(1701);
            int n = this.mHasMenu ? this.mSelectedCategory : 1;
            Conversation.startQueryForServiceProvider(this.mQueryHandler, 1701, n);
            if (bl) {
                this.startQueryTimedThreads();
            }
            return;
        }
        catch (SQLiteException var3_3) {
            SqliteWrapper.checkSQLiteException((Context)this.mActivity, (SQLiteException)var3_3);
            return;
        }
    }

}

