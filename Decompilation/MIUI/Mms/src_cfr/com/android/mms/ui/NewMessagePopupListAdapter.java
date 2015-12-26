/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.NewMessagePopupActivity;
import java.util.ArrayList;

public class NewMessagePopupListAdapter
extends BaseAdapter {
    private Activity mActivity;
    private MessageListAdapter.GetMsgItem mGetMsgItem;
    private LayoutInflater mInflater;
    private String mItemStyle = MessageListItem.Style.bubble.toString();
    private NewMessagePopupActivity.MessageThread mMessageThread;
    private float mTextSize;

    NewMessagePopupListAdapter(Activity activity) {
        this.mGetMsgItem = new MessageListAdapter.GetMsgItem(){

            @Override
            public MessageItem getCurrMessageItem(int n) {
                return NewMessagePopupListAdapter.this.getCachedMessageItem(n);
            }

            @Override
            public MessageItem getNextMessageItem(int n) {
                return NewMessagePopupListAdapter.this.getCachedMessageItem(n + 1);
            }

            @Override
            public MessageItem getPreMessageItem(int n) {
                return NewMessagePopupListAdapter.this.getCachedMessageItem(n - 1);
            }
        };
        this.mActivity = activity;
        this.mInflater = (LayoutInflater)activity.getSystemService("layout_inflater");
    }

    private MessageItem getCachedMessageItem(int n) {
        if (n < 0 || n >= this.mMessageThread.messageList.size()) {
            return null;
        }
        return (MessageItem)this.mMessageThread.messageList.get(n);
    }

    private boolean isMessageThreadNull() {
        if (this.mMessageThread == null || this.mMessageThread.messageList == null) {
            return true;
        }
        return false;
    }

    public int getCount() {
        if (this.isMessageThreadNull()) {
            return 0;
        }
        return this.mMessageThread.messageList.size();
    }

    public Object getItem(int n) {
        return null;
    }

    public long getItemId(int n) {
        return 0;
    }

    public int getItemViewType(int n) {
        if (this.isMessageThreadNull()) {
            return -1;
        }
        return MessageListAdapter.getItemLayoutStyle((Context)this.mActivity, this.mItemStyle, true, 0, false, this.mGetMsgItem, n);
    }

    public View getView(int n, View object, ViewGroup viewGroup) {
        if (this.isMessageThreadNull()) {
            return null;
        }
        MessageListItem messageListItem = (MessageListItem)object;
        MessageItem messageItem = (MessageItem)this.mMessageThread.messageList.get(n);
        object = messageListItem;
        if (messageListItem == null) {
            object = (MessageListItem)this.mInflater.inflate(2130968659, viewGroup, false);
            object.bind(messageItem);
        }
        object.rebind(messageItem, false, false, null, null);
        if (this.mTextSize != 0.0f) {
            object.setBodyTextSize(this.mTextSize);
        }
        return object;
    }

    public int getViewTypeCount() {
        return 12;
    }

    public void notifyDataSetChanged() {
        for (int i = 0; i < this.getCount(); ++i) {
            MessageItem messageItem = this.getCachedMessageItem(i);
            if (messageItem == null) continue;
            messageItem.resetLayoutStyle();
        }
        super.notifyDataSetChanged();
    }

    public void setTextSize(float f2) {
        this.mTextSize = f2;
    }

    public void setThread(NewMessagePopupActivity.MessageThread messageThread) {
        this.mMessageThread = messageThread;
    }

}

