/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.ListFragment
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.LayoutInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnCreateContextMenuListener
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.widget.AbsListView
 *  android.widget.AdapterView
 *  android.widget.AdapterView$AdapterContextMenuInfo
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  com.google.android.collect.Lists
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.mms.ui.BookmarkListAdapter;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.ISmsTextSizeAdjustHost;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.PickerActivity;
import com.android.mms.ui.SmsTextSizeAdjust;
import com.google.android.collect.Lists;
import java.util.ArrayList;

public class BookmarkFragment
extends ListFragment
implements ISmsTextSizeAdjustHost {
    private Activity mActivity;
    private BookmarkListAdapter mBookmarkListAdapter;
    private ListView mList;
    private boolean mPickerMode = false;
    private View mRootView;
    private SelectedMessage mSelectedMessage;

    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return super.onContextItemSelected(menuItem);
            }
            case 2: {
                this.mSelectedMessage.forwardMessage();
                return true;
            }
            case 3: {
                this.mSelectedMessage.delete();
                return true;
            }
            case 0: {
                this.mSelectedMessage.viewOrigin();
                return true;
            }
            case 1: 
        }
        this.mSelectedMessage.copy();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onCreateContextMenu(ContextMenu contextMenu, View object, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, (View)object, contextMenuInfo);
        if (this.mPickerMode) {
            return;
        }
        int n = ((AdapterView.AdapterContextMenuInfo)contextMenuInfo).position;
        object = (Cursor)this.mBookmarkListAdapter.getItem(n);
        if ((object = this.mBookmarkListAdapter.getCachedMessageItem((Cursor)object)) == null) return;
        this.mSelectedMessage.selectBookmark((MessageItem)object);
        contextMenu.setHeaderTitle((CharSequence)this.mSelectedMessage.getMessage());
        contextMenu.add(0, 0, 0, (CharSequence)this.getResources().getString(2131362093));
        contextMenu.add(0, 1, 0, (CharSequence)this.getResources().getString(2131361820));
        contextMenu.add(0, 2, 0, (CharSequence)this.getResources().getString(2131361816));
        contextMenu.add(0, 3, 0, (CharSequence)this.getResources().getString(2131362092));
        if (object.getMx2Type() != 3) return;
        contextMenu.getItem(2).setEnabled(false);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(2130968587, null);
        this.mActivity = this.getActivity();
        if (this.mActivity instanceof PickerActivity) {
            this.mPickerMode = true;
        }
        this.mSelectedMessage = new SelectedMessage();
        this.mBookmarkListAdapter = new BookmarkListAdapter((Context)this.mActivity);
        this.mList = (ListView)this.mRootView.findViewById(16908298);
        this.mList.setAdapter((ListAdapter)this.mBookmarkListAdapter);
        this.mList.setOnCreateContextMenuListener((View.OnCreateContextMenuListener)this);
        MessageUtils.setListViewTouchPadding((AbsListView)this.mList);
        layoutInflater = this.mRootView.findViewById(2131820564);
        if (layoutInflater != null) {
            this.mList.setEmptyView((View)layoutInflater);
        }
        this.setHasOptionsMenu(true);
        return this.mRootView;
    }

    public void onDestroyView() {
        if (this.mBookmarkListAdapter != null) {
            this.mBookmarkListAdapter.changeCursor(null);
        }
        super.onDestroyView();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onListItemClick(ListView listView, View view, int n, long l) {
        if ((n -= this.mList.getHeaderViewsCount()) < 0) {
            return;
        }
        if (this.mPickerMode) {
            listView = (Cursor)this.mBookmarkListAdapter.getItem(n);
            this.mSelectedMessage.selectBookmark(this.mBookmarkListAdapter.getCachedMessageItem((Cursor)listView));
            this.mSelectedMessage.returnPickerResult();
            return;
        }
        if (view.getParent() == null) return;
        view.showContextMenu();
    }

    public void onStart() {
        super.onStart();
        this.mBookmarkListAdapter.load();
        SmsTextSizeAdjust.getInstance().init(this, this.mActivity);
        SmsTextSizeAdjust.getInstance().refresh();
    }

    public void onStop() {
        if (this.mBookmarkListAdapter != null) {
            this.mBookmarkListAdapter.stop();
        }
        super.onStop();
        SmsTextSizeAdjust.clear(this);
    }

    @Override
    public void setTextSize(float f2) {
        if (this.mBookmarkListAdapter != null) {
            this.mBookmarkListAdapter.setTextSize(f2);
        }
    }

    private class SelectedMessage {
        private MessageItem mSelectedBookmark;

        private SelectedMessage() {
        }

        public void copy() {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add((Object)this.mSelectedBookmark);
            MessageUtils.copyMessageTextToClipboard((Context)BookmarkFragment.this.mActivity, arrayList);
        }

        public void delete() {
            AlertDialog.Builder builder = new AlertDialog.Builder((Context)BookmarkFragment.this.mActivity);
            builder.setTitle(2131362092);
            builder.setMessage(2131362094);
            builder.setIconAttribute(16843605);
            builder.setCancelable(true);
            builder.setPositiveButton(2131361891, new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    BookmarkFragment.this.mBookmarkListAdapter.deleteBookmark(SelectedMessage.this.mSelectedBookmark);
                }
            });
            builder.setNegativeButton(2131361892, null);
            builder.show();
        }

        public void forwardMessage() {
            MessageUtils.forwardMessage((Context)BookmarkFragment.this.mActivity, Lists.newArrayList((Object[])new MessageItem[]{this.mSelectedBookmark}), false);
        }

        public String getMessage() {
            return this.mSelectedBookmark.getBody();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void returnPickerResult() {
            Intent intent = new Intent();
            if (this.mSelectedBookmark.isMms() && this.mSelectedBookmark.isDownloaded()) {
                intent.putExtra("msg_uri", (Parcelable)this.mSelectedBookmark.getMessageUri());
                intent.putExtra("subject", this.mSelectedBookmark.getSubject());
            } else {
                String string2;
                String string3 = string2 = this.mSelectedBookmark.getBody();
                if (string2 == null) {
                    string3 = "";
                }
                intent.putExtra("android.intent.extra.shortcut.NAME", string3);
            }
            if (BookmarkFragment.this.mPickerMode) {
                ((PickerActivity)BookmarkFragment.this.mActivity).returnPickerResult(intent);
            }
        }

        public void selectBookmark(MessageItem messageItem) {
            this.mSelectedBookmark = messageItem;
        }

        public void viewOrigin() {
            long l = this.mSelectedBookmark.getThreadId();
            Intent intent = ComposeMessageRouterActivity.createIntent((Context)BookmarkFragment.this.mActivity, l);
            intent.setData(ComposeMessageRouterActivity.createViewOrginDataUri(l, this.mSelectedBookmark.getMsgId()));
            ComposeMessageRouterActivity.route((Context)BookmarkFragment.this.mActivity, intent);
        }

    }

}

