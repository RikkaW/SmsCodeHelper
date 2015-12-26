/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.view.Menu
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ConversationBase;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.MessageListPullView;
import com.android.mms.ui.MultipleRecipientsConversationActivity;
import com.android.mms.ui.SizeAwareLinearLayout;
import com.android.mms.util.EditableListView;

public class MultipleRecipientsConversationDetailsActivity
extends MultipleRecipientsConversationActivity {
    private long mTimedValue;
    private long mTimestamp;

    @Override
    protected int getContentViewResId() {
        return 2130968672;
    }

    @Override
    protected void initMessageList() {
        super.initMessageList();
        this.mMsgContentView.setOnMeasureListener(null);
        this.mMsgListView.setItemsCanFocus(false);
        this.mMsgListView.setClickable(false);
        this.mMsgListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                if (view != null && view instanceof MessageListItem) {
                    ((MessageListItem)view).onMessageListItemClick();
                }
            }
        });
        this.mBottomPanel.setVisibility(8);
        this.disableAttachmentPanel();
    }

    @Override
    protected void initResourceRefs() {
        super.initResourceRefs();
        this.mMsgListView.setEditModeListener(null);
    }

    @Override
    protected void initialize(long l) {
        super.initialize(l);
        Intent intent = this.getIntent();
        this.mTimestamp = intent.getLongExtra("timestamp", -1);
        this.mTimedValue = intent.getLongExtra("timed_value", -1);
        if (l <= 0 || this.mTimestamp < 0 || this.mTimedValue < 0) {
            this.finish();
        }
    }

    @Override
    protected boolean isGroup() {
        return false;
    }

    @Override
    protected boolean isReadOnly() {
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    protected void saveDraft(boolean bl) {
    }

    @Override
    protected void startMsgListQuery() {
        Uri uri = this.mConversation.getUri();
        if (uri == null) {
            return;
        }
        this.mBackgroundQueryHandler.cancelOperation(9527);
        try {
            this.mBackgroundQueryHandler.startQuery(9527, (Object)null, uri, MessageListAdapter.PROJECTION, "normalized_date=" + this.mTimestamp + " AND timed=" + this.mTimedValue, null, null);
            return;
        }
        catch (SQLiteException var1_2) {
            SqliteWrapper.checkSQLiteException((Context)this, (SQLiteException)var1_2);
            return;
        }
    }

}

