/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.ActionMode
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.View
 *  android.view.View$OnKeyListener
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.AbsListView$RecyclerListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ListAdapter
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  miui.R
 *  miui.R$layout
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.ConversationFragment;
import com.android.mms.ui.ConversationListAdapter;
import com.android.mms.ui.MessageListPullView;
import com.android.mms.ui.PrivateConversationListActivity;
import com.android.mms.util.EditableListView;
import miui.R;

public class PrivateConversationFragment
extends ConversationFragment {
    protected final AdapterView.OnItemClickListener mOnClickListener;

    public PrivateConversationFragment() {
        this.mOnClickListener = new AdapterView.OnItemClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onItemClick(AdapterView<?> cursor, View view, int n, long l) {
                if (n == 0) {
                    ((PrivateConversationListActivity)PrivateConversationFragment.this.mActivity).startSearchMode(PrivateConversationFragment.this.mSearchView, PrivateConversationFragment.this.mRootView);
                    return;
                } else {
                    if (n == PrivateConversationFragment.this.mListView.getAdapter().getCount() - 1) return;
                    {
                        cursor = (Cursor)PrivateConversationFragment.this.mListView.getItemAtPosition(n);
                        l = Conversation.from((Context)PrivateConversationFragment.this.mActivity, cursor).getThreadId();
                        Log.d((String)"ConversationFragment", (String)("onListItemClick: pos=" + n + ", view=" + (Object)view + ", tid=" + l));
                        cursor = ComposeMessageRouterActivity.createIntent((Context)PrivateConversationFragment.this.mActivity, l);
                        PrivateConversationFragment.this.startActivity((Intent)cursor);
                        return;
                    }
                }
            }
        };
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
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mActivity = this.getActivity();
        this.mRootView = layoutInflater.inflate(2130968600, null);
        this.mListView = (MessageListPullView)this.mRootView.findViewById(16908298);
        this.mListView.setOnKeyListener(this.mThreadListKeyListener);
        this.mListView.setEditModeListener(new ConversationFragment.ModeCallback(){

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                menu.removeItem(2131820906);
                return true;
            }
        });
        this.mListView.enableEmptyImgView(false);
        this.mListView.setEmptyImgViewImageResource(2130837902);
        this.mListView.setAllowTranscriptOnResize(false);
        this.mSearchView = layoutInflater.inflate(R.layout.search_stub, null, false);
        this.mListView.addHeaderView(this.mSearchView);
        this.mSearchViewHint = (TextView)this.mSearchView.findViewById(16908297);
        this.mSearchViewHint.setHint((CharSequence)this.getResources().getString(2131362212, new Object[]{0}));
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
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected void startAsyncQuery(boolean bl) {
        try {
            this.mQueryHandler.cancelOperation(1701);
            Conversation.startQueryForAll(this.mQueryHandler, 1701, true, false, 0);
            if (!bl) return;
        }
        catch (SQLiteException var2_2) {
            SqliteWrapper.checkSQLiteException((Context)this.mActivity, (SQLiteException)var2_2);
            return;
        }
        this.startQueryTimedThreads();
        this.startQuerySearchHintCount();
    }

    @Override
    protected void startQuerySearchHintCount() {
        this.mQueryHandler.cancelOperation(1704);
        Uri uri = this.sSearchHintUri.buildUpon().appendQueryParameter("privacy_flag", "1").build();
        this.mQueryHandler.startQuery(1704, (Object)null, uri, null, null, null, null);
    }

}

