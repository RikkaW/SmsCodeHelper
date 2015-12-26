/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.Cursor
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.AbsListView$RecyclerListener
 *  android.widget.CursorAdapter
 *  com.google.android.collect.Sets
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashSet
 */
package com.android.mms.ui;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CursorAdapter;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ConversationListItem;
import com.android.mms.util.EditableListView;
import com.google.android.collect.Sets;
import java.util.HashSet;

public class ConversationListAdapter
extends CursorAdapter
implements AbsListView.RecyclerListener,
EditableListView.ICheckableAdapter {
    private int mBlockedMsgUnseenCount = 0;
    private HashSet<Long> mCheckedIds = Sets.newHashSet();
    private final LayoutInflater mFactory;
    private boolean mIsCheckMode = false;
    private boolean mIsCompositeMode = false;
    private OnContentChangedListener mOnContentChangedListener;
    private int mPlaceHolderType = 0;
    private int mServiceProviderUnseenCount = 0;
    private long mSpSentinelId = -1;
    private long mUIChangeCursorTime = 0;

    public ConversationListAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
        this.mFactory = LayoutInflater.from((Context)context);
    }

    public ConversationListAdapter(Context context, Cursor cursor, boolean bl) {
        super(context, cursor, false);
        this.mFactory = LayoutInflater.from((Context)context);
        this.mIsCompositeMode = bl;
    }

    private void resetListItemMarkUnread(Conversation conversation) {
        long l = conversation.getPostMarkUnread();
        if (l > 0 && this.mUIChangeCursorTime > l && conversation.getPreMarkUnread()) {
            conversation.setPostMarkUnread(0);
            conversation.setPreMarkUnread(false);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean allowChecked(int n) {
        boolean bl = true;
        if (!this.mDataValid) return true;
        if (this.mCursor == null) return true;
        if (!this.mCursor.moveToPosition(n)) return true;
        if (this.mCursor.getLong(this.mRowIDColumn) < 0) {
            return false;
        }
        if (!this.mIsCompositeMode) return true;
        if (this.mSpSentinelId != -1) {
            if (this.mSpSentinelId == this.mCursor.getLong(this.mRowIDColumn)) return false;
            return bl;
        }
        Conversation conversation = Conversation.from(this.mContext, this.mCursor);
        if (conversation == null) return true;
        if (!conversation.isServiceProvider()) return true;
        if (!conversation.isSticky()) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void bindView(View object, Context context, Cursor cursor) {
        if (!(object instanceof ConversationListItem)) {
            Log.e((String)"ConversationListAdapter", (String)("Unexpected bound view: " + object));
            return;
        }
        object = (ConversationListItem)object;
        Conversation conversation = Conversation.from(context, cursor);
        this.resetListItemMarkUnread(conversation);
        conversation.setIsChecked(this.mCheckedIds.contains((Object)conversation.getThreadId()));
        boolean bl = this.isSPThreadSentinel(conversation);
        object.setIsSpSentinel(bl);
        object.setPlaceHolderType(0);
        if (bl) {
            this.mSpSentinelId = conversation.getThreadId();
        } else {
            long l = conversation.getThreadId();
            if (Conversation.isBlockedPlaceHolder(this.mPlaceHolderType) && l == -100) {
                object.setPlaceHolderType(1);
            } else if (Conversation.isMipubPlaceHolder(this.mPlaceHolderType) && l == -101) {
                object.setPlaceHolderType(2);
            }
        }
        object.bind(context, conversation, this.mIsCheckMode, cursor, this.mServiceProviderUnseenCount, this.mBlockedMsgUnseenCount);
    }

    public void enterCheckMode() {
        this.mIsCheckMode = true;
    }

    public void exitCheckMode() {
        this.mIsCheckMode = false;
        this.mCheckedIds = Sets.newHashSet();
    }

    @Override
    public int getDisableCheckedCount() {
        int n = 0;
        if (this.mIsCompositeMode) {
            n = 0 + 1;
        }
        int n2 = this.mPlaceHolderType;
        while (n2 > 0) {
            n2 &= n2 - 1;
            ++n;
        }
        return n;
    }

    public boolean isSPThreadSentinel(Conversation conversation) {
        boolean bl;
        boolean bl2 = bl = false;
        if (conversation != null) {
            bl2 = bl;
            if (this.mIsCompositeMode) {
                bl2 = bl;
                if (conversation.isServiceProvider()) {
                    bl2 = bl;
                    if (!conversation.isSticky()) {
                        bl2 = true;
                    }
                }
            }
        }
        return bl2;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mFactory.inflate(2130968595, viewGroup, false);
    }

    protected void onContentChanged() {
        if (this.mCursor != null && !this.mCursor.isClosed() && this.mOnContentChangedListener != null) {
            this.mOnContentChangedListener.onContentChanged(this);
        }
    }

    public void onMovedToScrapHeap(View view) {
        ((ConversationListItem)view).unbind();
    }

    public void resetSpSentinelId() {
        this.mSpSentinelId = -1;
    }

    public void setCheckedItem(HashSet<Long> hashSet) {
        if (hashSet == null) {
            this.mCheckedIds = Sets.newHashSet();
            return;
        }
        this.mCheckedIds = hashSet;
    }

    public void setCompositeMode(boolean bl) {
        this.mIsCompositeMode = bl;
    }

    public void setOnContentChangedListener(OnContentChangedListener onContentChangedListener) {
        this.mOnContentChangedListener = onContentChangedListener;
    }

    public void setPlaceHolderType(int n) {
        this.mPlaceHolderType = n;
    }

    public void setServiceProviderUnseen(int n) {
        this.mServiceProviderUnseenCount = n;
    }

    public void setShowBlockedMsgUnseen(int n) {
        this.mBlockedMsgUnseenCount = n;
    }

    public void setSpSentinelId(long l) {
        this.mSpSentinelId = l;
    }

    public void setUIChangeCursorTime() {
        this.mUIChangeCursorTime = System.currentTimeMillis();
    }

    public static interface OnContentChangedListener {
        public void onContentChanged(ConversationListAdapter var1);
    }

}

