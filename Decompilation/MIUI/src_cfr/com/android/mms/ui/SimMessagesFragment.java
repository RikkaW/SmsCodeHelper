/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Fragment
 *  android.content.AsyncQueryHandler
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.database.ContentObserver
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Bundle
 *  android.os.Handler
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.provider.Telephony$Sms$Sent
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.ActionMode
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.HashSet
 *  miui.R
 *  miui.R$id
 *  miui.R$plurals
 *  miui.R$string
 *  miui.app.ActionBar
 *  miui.app.Activity
 *  miui.os.Build
 *  miui.view.EditActionMode
 *  miui.view.ViewPager
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.ui.ManageSimMessages;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.NewMessageActivity;
import com.android.mms.util.EditableListView;
import com.android.mms.util.MSimUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import miui.R;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.os.Build;
import miui.view.EditActionMode;
import miui.view.ViewPager;

public class SimMessagesFragment
extends Fragment {
    public static float sTextSize;
    private Activity mActivity;
    private ContentResolver mContentResolver;
    private Uri mCurrentIccUri = null;
    private int mCurrentSlotId = 0;
    private TextView mEmptyView;
    private MessageListAdapter mListAdapter = null;
    private EditableListView mListView;
    private int mMessageCount = 0;
    private ProgressBar mProgressBar;
    private QueryHandler mQueryHandler = null;
    private final ContentObserver mSimChangeObserver;
    private int mState;

    public SimMessagesFragment() {
        this.mSimChangeObserver = new ContentObserver(new Handler()){

            public void onChange(boolean bl) {
                Log.d((String)"SimMessagesFragment", (String)"sim message change");
                SimMessagesFragment.this.startQuery();
            }
        };
    }

    static /* synthetic */ void access$1000(SimMessagesFragment simMessagesFragment, DialogInterface.OnClickListener onClickListener, CharSequence charSequence) {
        simMessagesFragment.confirmDeleteDialog(onClickListener, charSequence);
    }

    static /* synthetic */ int access$302(SimMessagesFragment simMessagesFragment, int n) {
        simMessagesFragment.mMessageCount = n;
        return n;
    }

    static /* synthetic */ MessageListAdapter access$402(SimMessagesFragment simMessagesFragment, MessageListAdapter messageListAdapter) {
        simMessagesFragment.mListAdapter = messageListAdapter;
        return messageListAdapter;
    }

    static /* synthetic */ int access$600(SimMessagesFragment simMessagesFragment) {
        return simMessagesFragment.mCurrentSlotId;
    }

    static /* synthetic */ void access$700(SimMessagesFragment simMessagesFragment, Cursor cursor) {
        simMessagesFragment.copyToPhoneMemory(cursor);
    }

    static /* synthetic */ void access$800(SimMessagesFragment simMessagesFragment, Cursor cursor) {
        simMessagesFragment.forwardMessage(cursor);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void asyncDelete(String string2) {
        string2 = MSimUtils.isMSim() ? this.mCurrentIccUri.buildUpon().appendQueryParameter("msgIndex", string2).build() : this.mCurrentIccUri.buildUpon().appendPath(string2).build();
        this.mQueryHandler.startDelete(1, (Object)null, (Uri)string2, null, null);
    }

    private void confirmDeleteDialog(DialogInterface.OnClickListener onClickListener, CharSequence charSequence) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this.mActivity);
        builder.setTitle(2131361915);
        builder.setIcon(17301543);
        builder.setCancelable(true);
        builder.setPositiveButton(2131361891, onClickListener);
        builder.setNegativeButton(2131361892, null);
        builder.setMessage(charSequence);
        builder.show();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void copyToPhoneMemory(Cursor var1_1) {
        var8_3 = var1_1.getString(var1_1.getColumnIndexOrThrow("address"));
        var9_4 = var1_1.getString(var1_1.getColumnIndexOrThrow("body"));
        var3_5 = var1_1.getLong(var1_1.getColumnIndexOrThrow("date"));
        var7_6 = this.isIncomingMessage(var1_1);
        var2_7 = 2131362243;
        if (!var7_6) ** GOTO lbl10
        try {
            var1_1 = Telephony.Sms.Inbox.CONTENT_URI;
            ** GOTO lbl11
lbl10: // 1 sources:
            var1_1 = Telephony.Sms.Sent.CONTENT_URI;
lbl11: // 2 sources:
            var5_8 = MSimUtils.getSimIdBySlotId(this.mCurrentSlotId);
            var1_1 = var1_1.buildUpon().appendQueryParameter("check_duplication", "1").build();
            var10_9 = this.mContentResolver;
            var3_5 = var7_6 != false ? Long.valueOf((long)var3_5) : System.currentTimeMillis();
            MSimUtils.addMessageToUri(var10_9, (Uri)var1_1, var8_3, var9_4, null, var3_5, true, false, -1, var5_8);
        }
        catch (SQLiteException var1_2) {
            SqliteWrapper.checkSQLiteException((Context)this.mActivity, (SQLiteException)var1_2);
            var2_7 = 2131362244;
        }
        Toast.makeText((Context)this.mActivity, (int)var2_7, (int)0).show();
    }

    private void deleteFromSim(HashSet<Integer> iterator) {
        Object object = this.mListAdapter.getCursor();
        if (object != null) {
            Object object22 = new ArrayList();
            iterator = iterator.iterator();
            while (iterator.hasNext()) {
                object.moveToPosition(((Integer)iterator.next()).intValue());
                object22.add(object.getString(object.getColumnIndexOrThrow("index_on_icc")));
            }
            iterator = object22.iterator();
            while (iterator.hasNext() && !this.mActivity.isFinishing()) {
                object = (String)iterator.next();
                if (TextUtils.isEmpty((CharSequence)object)) continue;
                for (Object object22 : TextUtils.split((String)object, (String)";")) {
                    if (TextUtils.isEmpty((CharSequence)object22)) continue;
                    this.asyncDelete((String)object22);
                }
            }
        }
        this.mListView.exitEditMode();
    }

    private void forwardMessage(Cursor object) {
        object = object.getString(object.getColumnIndexOrThrow("body"));
        Intent intent = new Intent((Context)this.mActivity, (Class)NewMessageActivity.class);
        intent.putExtra("exit_on_sent", true);
        intent.putExtra("sms_body", (String)object);
        intent.putExtra("forwarded_message", true);
        this.startActivity(intent);
    }

    private int getState() {
        return this.mState;
    }

    private boolean isIncomingMessage(Cursor cursor) {
        int n = cursor.getInt(cursor.getColumnIndexOrThrow("status"));
        if (n == 1 || n == 3) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setCurrentSlotInfo() {
        this.mCurrentSlotId = this.getArguments().getInt(MSimUtils.SLOT_ID);
        if (this.mCurrentSlotId == 0) {
            this.mCurrentIccUri = Uri.parse((String)"content://sms/icc");
            return;
        } else {
            if (this.mCurrentSlotId != 1) return;
            {
                this.mCurrentIccUri = Uri.parse((String)"content://sms/icc2");
                return;
            }
        }
    }

    private void startQuery() {
        this.updateState(2);
        try {
            this.mQueryHandler.cancelOperation(0);
            this.mQueryHandler.startQuery(0, (Object)null, this.mCurrentIccUri, null, null, null, null);
            return;
        }
        catch (SQLiteException var1_1) {
            SqliteWrapper.checkSQLiteException((Context)this.mActivity, (SQLiteException)var1_1);
            this.updateState(1);
            return;
        }
    }

    private void updateState(int n) {
        if (this.mState == n) {
            return;
        }
        this.mState = n;
        switch (n) {
            default: {
                Log.e((String)"SimMessagesFragment", (String)"Invalid State");
                return;
            }
            case 0: {
                this.mListView.setVisibility(0);
                this.mEmptyView.setVisibility(8);
                this.mProgressBar.setVisibility(8);
                this.mListView.requestFocus();
                return;
            }
            case 1: {
                this.mListView.setVisibility(8);
                this.mEmptyView.setVisibility(0);
                this.mProgressBar.setVisibility(8);
                return;
            }
            case 2: 
        }
        this.mListView.setVisibility(8);
        this.mEmptyView.setVisibility(8);
        this.mProgressBar.setVisibility(0);
    }

    public int getMessageCount() {
        return this.mMessageCount;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mActivity = (Activity)this.getActivity();
        this.mContentResolver = this.mActivity.getContentResolver();
        this.mQueryHandler = new QueryHandler(this.mContentResolver);
        layoutInflater = layoutInflater.inflate(2130968703, null);
        this.mListView = (EditableListView)layoutInflater.findViewById(2131820865);
        this.mEmptyView = (TextView)layoutInflater.findViewById(2131820866);
        this.mProgressBar = (ProgressBar)layoutInflater.findViewById(2131820795);
        this.mListView.setEditModeListener(new ModeCallback());
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                    if ((n -= SimMessagesFragment.this.mListView.getFirstVisiblePosition()) >= 0 && n < SimMessagesFragment.this.mListView.getChildCount() && SimMessagesFragment.this.mListView.getChildAt(n) instanceof MessageListItem) {
                        ((MessageListItem)SimMessagesFragment.this.mListView.getChildAt(n)).onMessageListItemClick();
                    }
                }
            });
        }
        this.setCurrentSlotInfo();
        this.startQuery();
        return layoutInflater;
    }

    public void onDestroyView() {
        if (this.mListAdapter != null && this.mListAdapter.getCursor() != null) {
            this.mListAdapter.changeCursor(null);
        }
        this.mListView.exitEditMode();
        super.onDestroyView();
    }

    public void onPause() {
        this.mContentResolver.unregisterContentObserver(this.mSimChangeObserver);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (this.getState() == 1) {
            Log.d((String)"SimMessagesFragment", (String)"onResume state is show empty");
            this.startQuery();
        }
        this.mContentResolver.registerContentObserver(this.mCurrentIccUri, true, this.mSimChangeObserver);
    }

    private class ModeCallback
    implements EditableListView.EditModeListener {
        private EditableListView.EditableListViewCheckable mCheckable;
        private EditActionMode mEditActionMode;
        private Menu mRootMenu;

        private ModeCallback() {
        }

        /*
         * Enabled aggressive block sorting
         */
        private void setTabsMode(boolean bl) {
            ActionBar actionBar = SimMessagesFragment.this.mActivity.getActionBar();
            if (actionBar != null) {
                boolean bl2 = bl;
                if (!bl) {
                    bl2 = actionBar.getFragmentTabCount() <= 1;
                }
                actionBar.setTabsMode(bl2);
            }
        }

        private void setViewPagerDraggable(boolean bl) {
            View view = SimMessagesFragment.this.mActivity.getWindow().findViewById(R.id.view_pager);
            if (view instanceof ViewPager) {
                ((ViewPager)view).setDraggable(bl);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void updateMenu(int n) {
            String string2;
            if (n == 0) {
                string2 = SimMessagesFragment.this.getString(R.string.action_mode_title_empty);
                this.mRootMenu.findItem(2131820908).setEnabled(false);
                this.mRootMenu.findItem(2131820909).setEnabled(false);
                this.mRootMenu.findItem(2131820910).setEnabled(false);
            } else {
                string2 = SimMessagesFragment.this.getResources().getQuantityString(R.plurals.items_selected, this.mCheckable.count(), new Object[]{this.mCheckable.count()});
                this.mRootMenu.findItem(2131820908).setEnabled(true);
                if (n == 1) {
                    this.mRootMenu.findItem(2131820909).setEnabled(true);
                    this.mRootMenu.findItem(2131820910).setEnabled(true);
                } else {
                    this.mRootMenu.findItem(2131820909).setEnabled(false);
                    this.mRootMenu.findItem(2131820910).setEnabled(false);
                }
            }
            ((ActionMode)this.mEditActionMode).setTitle((CharSequence)string2);
            if (this.mCheckable.isAllChecked()) {
                this.mEditActionMode.setButton(16908314, R.string.action_mode_deselect_all);
                return;
            }
            this.mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean onActionItemClicked(final ActionMode var1_1, MenuItem var2_2) {
            switch (var2_2.getItemId()) {
                case 2131820909: {
                    var2_2 = this.mCheckable.getCheckedItemInPositions();
                    if (var2_2.size() <= 0) return true;
                    var3_3 = (Integer)var2_2.iterator().next();
                    var2_2 = (HashSet<Integer>)SimMessagesFragment.access$400(SimMessagesFragment.this).getItem(var3_3);
                    SimMessagesFragment.access$700(SimMessagesFragment.this, (Cursor)var2_2);
                    var1_1.finish();
                    ** break;
                }
                case 2131820910: {
                    var2_2 = this.mCheckable.getCheckedItemInPositions();
                    if (var2_2.size() <= 0) return true;
                    var3_4 = (Integer)var2_2.iterator().next();
                    var2_2 = (Cursor)SimMessagesFragment.access$400(SimMessagesFragment.this).getItem(var3_4);
                    SimMessagesFragment.access$800(SimMessagesFragment.this, (Cursor)var2_2);
                    var1_1.finish();
                    ** break;
                }
                case 2131820908: {
                    var1_1 = this.mCheckable.getCheckedItemInPositions();
                    SimMessagesFragment.access$1000(SimMessagesFragment.this, new DialogInterface.OnClickListener(){

                        public void onClick(DialogInterface dialogInterface, int n) {
                            SimMessagesFragment.this.updateState(2);
                            SimMessagesFragment.this.deleteFromSim(var1_1);
                            dialogInterface.dismiss();
                        }
                    }, SimMessagesFragment.this.getResources().getString(2131361797));
                    ** break;
                }
                case 16908313: {
                    var1_1.finish();
                }
lbl24: // 5 sources:
                default: {
                    return true;
                }
                case 16908314: 
            }
            if (this.mCheckable.isAllChecked()) {
                this.mCheckable.checkNothing();
                return true;
            }
            this.mCheckable.checkAll();
            return true;
        }

        @Override
        public void onCheckStateChanged(EditableListView.EditableListViewCheckable editableListViewCheckable) {
            HashSet<Long> hashSet = editableListViewCheckable.getCheckedItemInIds();
            SimMessagesFragment.this.mListAdapter.setCheckedItem(hashSet);
            this.mCheckable = editableListViewCheckable;
            this.updateMenu(this.mCheckable.count());
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.mRootMenu = menu;
            SimMessagesFragment.this.mActivity.getMenuInflater().inflate(2131755011, menu);
            SimMessagesFragment.this.mListAdapter.enterCheckMode();
            this.mCheckable = SimMessagesFragment.this.mListView.getEditableListViewCheckable();
            this.mEditActionMode = (EditActionMode)actionMode;
            actionMode.setTitle(R.string.action_mode_title_empty);
            this.mEditActionMode.setButton(16908313, 17039360);
            this.mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
            this.setViewPagerDraggable(false);
            this.setTabsMode(true);
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.setViewPagerDraggable(true);
            this.setTabsMode(false);
            SimMessagesFragment.this.mListAdapter.exitCheckMode();
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public void onVisibleViewCheckStateChanged(View view, boolean bl) {
        }

    }

    private class QueryHandler
    extends AsyncQueryHandler {
        public QueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        protected void onQueryComplete(int var1_1, Object var2_2, Cursor var3_5) {
            block14 : {
                if (!SimMessagesFragment.this.isResumed()) {
                    if (var3_5 != null) {
                        var3_5.close();
                    }
                    SimMessagesFragment.access$200(SimMessagesFragment.this, 1);
                    return;
                    finally {
                        Log.d((String)"SimMessagesFragment", (String)"onQueryComplete cursor close");
                    }
                }
                if (var3_5 != null) {
                    var5_6 = 0;
                    var4_7 = false;
                    var1_1 = var5_6;
                    if (!var3_5.moveToFirst()) {
                        var1_1 = var5_6;
                        SimMessagesFragment.access$302(SimMessagesFragment.this, 0);
                        var1_1 = var5_6;
                        SimMessagesFragment.access$200(SimMessagesFragment.this, 1);
                        var1_1 = 1;
                        var4_7 = true;
                        Log.d((String)"SimMessagesFragment", (String)"cursor is empty");
                    }
                    ** if (var4_7) goto lbl-1000
lbl-1000: // 1 sources:
                    {
                        if (SimMessagesFragment.access$400(SimMessagesFragment.this) == null) {
                            SimMessagesFragment.access$402(SimMessagesFragment.this, new MessageListAdapter((Context)SimMessagesFragment.access$500(SimMessagesFragment.this), var3_5, SimMessagesFragment.access$100(SimMessagesFragment.this), false, null, null, 0));
                            SimMessagesFragment.access$400(SimMessagesFragment.this).setMsgListItemHandler(new Handler());
                            SimMessagesFragment.access$100(SimMessagesFragment.this).setAdapter((ListAdapter)SimMessagesFragment.access$400(SimMessagesFragment.this));
                            SimMessagesFragment.access$200(SimMessagesFragment.this, 0);
                            SimMessagesFragment.access$400(SimMessagesFragment.this).setTextSize(SimMessagesFragment.sTextSize);
                        } else {
                            SimMessagesFragment.access$400(SimMessagesFragment.this).changeCursor(var3_5);
                            SimMessagesFragment.access$200(SimMessagesFragment.this, 0);
                        }
                        SimMessagesFragment.access$302(SimMessagesFragment.this, SimMessagesFragment.access$400(SimMessagesFragment.this).getCount());
                        Log.d((String)"SimMessagesFragment", (String)"onQueryComplete change cursor");
                    }
lbl-1000: // 2 sources:
                    {
                        break block14;
                    }
                    finally {
                        if (var4_7) {
                            var3_5.close();
                        }
                    }
                }
                SimMessagesFragment.access$302(SimMessagesFragment.this, 0);
                SimMessagesFragment.access$200(SimMessagesFragment.this, 1);
                Log.d((String)"SimMessagesFragment", (String)"cursor is null");
            }
            if (Build.IS_CM_CUSTOMIZATION_TEST == false) return;
            ((ManageSimMessages)SimMessagesFragment.access$500(SimMessagesFragment.this)).updateSimInfo(SimMessagesFragment.access$600(SimMessagesFragment.this));
        }
    }

}

