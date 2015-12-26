/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.util.Log
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ConversationBase;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.MessageListPullView;
import com.android.mms.ui.MultipleRecipientsConversationHeader;
import java.util.Iterator;

public class MultipleRecipientsConversationActivity
extends ConversationBase {
    private MultipleRecipientsConversationHeader mHeader;
    private long mThreadId;

    @Override
    protected int getContentViewResId() {
        return 2130968671;
    }

    @Override
    protected void initMessageList() {
        super.initMessageList();
        this.mMsgListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                if ((n -= MultipleRecipientsConversationActivity.this.mMsgListView.getFirstVisiblePosition()) >= 0 && n < MultipleRecipientsConversationActivity.this.mMsgListView.getChildCount() && MultipleRecipientsConversationActivity.this.mMsgListView.getChildAt(n) instanceof MessageListItem) {
                    ((MessageListItem)MultipleRecipientsConversationActivity.this.mMsgListView.getChildAt(n)).onMessageListItemClick();
                }
            }
        });
    }

    @Override
    protected void initResourceRefs() {
        super.initResourceRefs();
        this.mHeader = (MultipleRecipientsConversationHeader)this.findViewById(2131820798);
    }

    @Override
    protected void initialize(long l) {
        super.initialize(l);
        this.mThreadId = l;
    }

    @Override
    protected boolean isGroup() {
        return true;
    }

    @Override
    protected void onContactAdded(Contact contact) {
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
    }

    @Override
    protected void startMsgListQuery() {
        Uri uri = this.mConversation.getUri();
        if (uri == null) {
            return;
        }
        uri = uri.buildUpon().appendPath("group").build();
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"MultipleRecipientsCA", (String)("startMsgListQuery MultiRecipientCA for " + (Object)uri));
        }
        this.mBackgroundQueryHandler.cancelOperation(9527);
        try {
            this.mBackgroundQueryHandler.startQuery(9527, (Object)null, uri, MessageListAdapter.GROUP_MSG_PROJECTION, null, null, null);
            return;
        }
        catch (SQLiteException var1_2) {
            SqliteWrapper.checkSQLiteException((Context)this, (SQLiteException)var1_2);
            return;
        }
    }

    @Override
    protected void updateTitle(ContactList contactList) {
        this.mHeader.updateTitle(contactList);
    }

}

