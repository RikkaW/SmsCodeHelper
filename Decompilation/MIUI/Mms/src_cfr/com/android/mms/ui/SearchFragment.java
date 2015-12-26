/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.provider.Telephony
 *  android.provider.Telephony$MmsSms
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.BaseAdapter
 *  android.widget.Button
 *  android.widget.LinearLayout
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 *  com.google.android.collect.Lists
 *  com.google.android.collect.Sets
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  miui.R
 *  miui.R$drawable
 *  miui.date.DateUtils
 */
package com.android.mms.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Telephony;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.LogTag;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.google.android.collect.Lists;
import com.google.android.collect.Sets;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import miui.R;
import miui.date.DateUtils;

public class SearchFragment
extends Fragment
implements View.OnTouchListener {
    private Activity mActivity;
    private SearchAdapter mAdapter;
    Contact.UpdateListener mContactListener;
    Handler mHandler = new Handler();
    private ListView mListView;
    private boolean mPrivateMode = false;
    Runnable mRequestRequery;
    private boolean mRetryQuery = false;
    private TextView mSearchCount;

    public SearchFragment() {
        this.mContactListener = new Contact.UpdateListener(){

            @Override
            public void onUpdate(Contact contact) {
                SearchFragment.this.mHandler.removeCallbacks(SearchFragment.this.mRequestRequery);
                SearchFragment.this.mHandler.postDelayed(SearchFragment.this.mRequestRequery, 500);
            }
        };
        this.mRequestRequery = new Runnable(){

            @Override
            public void run() {
                if (!SearchFragment.this.mAdapter.hasRunningTask()) {
                    SearchFragment.this.mAdapter.query();
                }
            }
        };
        this.mPrivateMode = false;
    }

    public SearchFragment(boolean bl) {
        this.mContactListener = new ;
        this.mRequestRequery = new ;
        this.mPrivateMode = bl;
    }

    private void hideSoftKeyboard() {
        ((InputMethodManager)this.mActivity.getSystemService("input_method")).hideSoftInputFromWindow(this.mListView.getWindowToken(), 0);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        viewGroup = layoutInflater.inflate(2130968697, viewGroup, false);
        this.mActivity = this.getActivity();
        this.mListView = (ListView)viewGroup.findViewById(16908298);
        this.mListView.setFocusable(true);
        this.mListView.setFocusableInTouchMode(true);
        this.mListView.setOnTouchListener((View.OnTouchListener)this);
        this.mSearchCount = (TextView)layoutInflater.inflate(2130968700, null);
        this.mListView.addFooterView((View)this.mSearchCount, (Object)null, false);
        this.mAdapter = new SearchAdapter();
        this.mListView.setAdapter((ListAdapter)this.mAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> intent, View view, int n, long l) {
                if ((n -= SearchFragment.this.mListView.getHeaderViewsCount()) >= 0 && (intent = SearchFragment.this.mAdapter.getIntent(n)) != null) {
                    MiStatSdkHelper.recordSearch("search_click_item");
                    ComposeMessageRouterActivity.route((Context)SearchFragment.this.mActivity, intent);
                    SearchFragment.this.mRetryQuery = true;
                }
            }
        });
        layoutInflater = viewGroup.findViewById(2131820564);
        this.mListView.setEmptyView((View)layoutInflater);
        return viewGroup;
    }

    public void onDestroyView() {
        this.mAdapter.closeCursor();
        super.onDestroyView();
    }

    public void onStart() {
        super.onStart();
        if (this.mRetryQuery) {
            this.mHandler.post(this.mRequestRequery);
        }
        this.mRetryQuery = false;
    }

    public void onStop() {
        super.onStop();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view == this.mListView) {
            this.hideSoftKeyboard();
        }
        return false;
    }

    public void query(String string2) {
        this.mAdapter.query(string2);
    }

    public void selectToFirst() {
        this.mListView.setSelection(0);
    }

    class SearchAdapter
    extends BaseAdapter {
        private int mBodyPos;
        private ContactList mContacts;
        private ArrayList<Conversation> mConversationForMessages;
        private ArrayList<Conversation> mConversations;
        private int mDatePos;
        private HashSet<String> mDuplicatedNames;
        private HashSet<Conversation> mExpandedConversations;
        private AsyncTask<Void, Void, Void> mLatestTask;
        private Cursor mMsgCursor;
        private ArrayList<Integer> mMsgPosition;
        private String mQueryString;
        private int mRowIdPos;
        private int mSubjectPos;
        private int mThreadIdPos;

        SearchAdapter() {
            this.mExpandedConversations = Sets.newHashSet();
            this.mLatestTask = null;
        }

        public void closeCursor() {
            if (this.mMsgCursor != null) {
                this.mMsgCursor.close();
            }
            this.mMsgCursor = null;
        }

        public int getCount() {
            if (this.mMsgCursor == null || this.mConversations == null) {
                return 0;
            }
            return this.mMsgCursor.getCount() + this.mConversations.size() + this.mContacts.size();
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public Intent getIntent(int n) {
            if (this.mMsgCursor == null) {
                return null;
            }
            if (n < this.mConversations.size()) {
                long l = ((Conversation)this.mConversations.get(n)).getThreadId();
                return ComposeMessageRouterActivity.createIntent((Context)SearchFragment.this.mActivity, l);
            }
            if (n >= this.mConversations.size() + this.mConversationForMessages.size()) {
                if ((n = n - this.mConversations.size() - this.mConversationForMessages.size()) >= this.mContacts.size()) return null;
                String string2 = ((Contact)this.mContacts.get(n)).getNumber();
                Intent intent = ComposeMessageRouterActivity.createIntent((Context)SearchFragment.this.mActivity, -1);
                intent.putExtra("address", string2);
                return intent;
            }
            if (!this.mMsgCursor.moveToPosition(((Integer)this.mMsgPosition.get(n -= this.mConversations.size())).intValue())) {
                LogTag.error("SearchFragment failed to move cursor to position " + n, new Object[0]);
                return null;
            }
            long l = this.mMsgCursor.getLong(this.mThreadIdPos);
            long l2 = this.mMsgCursor.getLong(this.mRowIdPos);
            String string3 = null;
            if (l2 < 0) {
                string3 = this.mMsgCursor.getString(this.mBodyPos);
            }
            string3 = new Intent("android.intent.action.VIEW", ComposeMessageRouterActivity.createSearchResultDataUri(l, l2, this.mQueryString, string3));
            ComposeMessageRouterActivity.prepareIntent((Intent)string3, l);
            return string3;
        }

        public Object getItem(int n) {
            return null;
        }

        public long getItemId(int n) {
            return n;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int getItemViewType(int n) {
            if (n < this.mConversations.size()) {
                return 0;
            }
            if (n >= this.mConversations.size() + this.mConversationForMessages.size()) {
                return 7;
            }
            int n2 = n - this.mConversations.size();
            Conversation conversation = (Conversation)this.mConversationForMessages.get(n2);
            n = this.mExpandedConversations.contains((Object)conversation) ? 10000 : 5;
            boolean bl = n2 == 0 || conversation != this.mConversationForMessages.get(n2 - 1);
            boolean bl2 = n2 == this.mMsgCursor.getCount() - 1 || conversation != this.mConversationForMessages.get(n2 + 1);
            boolean bl3 = n2 >= n - 1 && conversation == this.mConversationForMessages.get(n2 - n + 1) && (n2 == n - 1 || conversation != this.mConversationForMessages.get(n2 - n));
            if (bl) {
                if (!bl2 && !bl3) {
                    return 2;
                }
                return 1;
            }
            if (bl2) {
                return 3;
            }
            return 4;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public View getView(int var1_1, View var2_2, ViewGroup var3_3) {
            if (this.mMsgCursor == null) return null;
            if (this.mConversations == null) {
                return null;
            }
            var4_4 = this.getItemViewType(var1_1);
            switch (var4_4) {
                default: {
                    if (!(var2_2 instanceof LinearLayout)) break;
                    ** GOTO lbl89
                }
                case 0: {
                    if (!(var2_2 instanceof LinearLayout)) {
                        var2_2 = LayoutInflater.from((Context)SearchFragment.access$200(SearchFragment.this)).inflate(2130968699, (ViewGroup)var3_3, false);
                    }
                    var3_3 = (TextViewSnippet)var2_2.findViewById(2131820846);
                    var8_6 = (TextViewSnippet)var2_2.findViewById(2131820854);
                    var9_9 = (TextView)var2_2.findViewById(2131820859);
                    var10_12 = (Conversation)this.mConversations.get(var1_1);
                    var11_14 = (Contact)var10_12.getRecipients().get(0);
                    var3_3.setText(var11_14.getName(), this.mQueryString, 2131689495);
                    if (this.mDuplicatedNames.contains((Object)var11_14.getName()) || var11_14.getName().indexOf(this.mQueryString) == -1 && var11_14.getNumber().indexOf(this.mQueryString) != -1) {
                        var8_6.setVisibility(0);
                        var8_6.setText((CharSequence)var11_14.getNumber());
                        var8_6.measure(0, 0);
                        var8_6.getLayoutParams().width = var8_6.getMeasuredWidth();
                        var8_6.setText(var11_14.getNumber(), this.mQueryString, 2131689499);
                    } else {
                        var8_6.setVisibility(8);
                    }
                    var9_9.setText((CharSequence)SearchFragment.this.getResources().getQuantityString(2131623943, var10_12.getMessageCount(), new Object[]{var10_12.getMessageCount()}));
                    return var2_2;
                }
                case 6: {
                    if (var2_2 == null) return new View((Context)SearchFragment.access$200(SearchFragment.this));
                    return var2_2;
                }
                case 7: {
                    if (!(var2_2 instanceof LinearLayout)) {
                        var2_2 = LayoutInflater.from((Context)SearchFragment.access$200(SearchFragment.this)).inflate(2130968698, (ViewGroup)var3_3, false);
                    }
                    var3_3 = (TextViewSnippet)var2_2.findViewById(2131820846);
                    var8_7 = (TextViewSnippet)var2_2.findViewById(2131820854);
                    var4_4 = this.mConversations.size();
                    var5_16 = this.mConversationForMessages.size();
                    var9_10 = (Contact)this.mContacts.get(var1_1 - var4_4 - var5_16);
                    var3_3.setText(var9_10.getName(), this.mQueryString, 2131689495);
                    var8_7.setText((CharSequence)var9_10.getNumber());
                    var8_7.measure(0, 0);
                    var8_7.getLayoutParams().width = var8_7.getMeasuredWidth();
                    var8_7.setText(var9_10.getNumber(), this.mQueryString, 2131689499);
                    return var2_2;
                }
            }
            var2_2 = LayoutInflater.from((Context)SearchFragment.access$200(SearchFragment.this)).inflate(2130968701, (ViewGroup)var3_3, false);
            var3_3 = (TextView)var2_2.findViewById(2131820846);
            var8_5 = var2_2.findViewById(2131820861);
            var9_8 = var2_2.findViewById(2131820797);
            var10_11 = (Button)var2_2.findViewById(2131820862);
            var11_13 = SearchFragment.this.getActivity().getResources().getDrawable(R.drawable.preference_item_bg);
            switch (var4_4) {
                default: {
                    LogTag.error("Invalid item type in search result", new Object[0]);
                    break;
                }
                case 1: {
                    var11_13.setLevel(0);
                    var8_5.setBackground((Drawable)var11_13);
                    var3_3.setVisibility(0);
                    var9_8.setVisibility(0);
                    var10_11.setVisibility(8);
                    break;
                }
                case 2: {
                    var11_13.setLevel(1);
                    var8_5.setBackground((Drawable)var11_13);
                    var3_3.setVisibility(0);
                    var9_8.setVisibility(8);
                    var10_11.setVisibility(8);
                    break;
                }
                case 3: {
                    var11_13.setLevel(3);
                    var8_5.setBackground((Drawable)var11_13);
                    var3_3.setVisibility(8);
                    var9_8.setVisibility(0);
                    var10_11.setVisibility(8);
                    break;
                }
                case 4: {
                    var11_13.setLevel(3);
                    var8_5.setBackground((Drawable)var11_13);
                    var3_3.setVisibility(8);
                    var9_8.setVisibility(0);
                    var10_11.setVisibility(0);
                    break;
                }
                case 5: {
                    var11_13.setLevel(2);
                    var8_5.setBackground((Drawable)var11_13);
                    var3_3.setVisibility(8);
                    var9_8.setVisibility(8);
                    var10_11.setVisibility(8);
                }
            }
lbl89: // 7 sources:
            var3_3 = (TextView)var2_2.findViewById(2131820846);
            var11_13 = (TextViewSnippet)var2_2.findViewById(2131820614);
            var10_11 = (TextView)var2_2.findViewById(2131820590);
            var8_5 = (Button)var2_2.findViewById(2131820862);
            this.mMsgCursor.moveToPosition(((Integer)this.mMsgPosition.get(var1_1 -= this.mConversations.size())).intValue());
            var9_8 = (Conversation)this.mConversationForMessages.get(var1_1);
            if (var3_3.getVisibility() == 0) {
                var3_3.setText((CharSequence)var9_8.getContactNames());
            }
            var3_3 = (var3_3 = this.mMsgCursor.getString(this.mSubjectPos)) != null ? (!TextUtils.isEmpty((CharSequence)var3_3) ? new EncodedStringValue(106, MiuiPduPersister.getBytes((String)var3_3)).getString() : "") : this.mMsgCursor.getString(this.mBodyPos);
            var11_13.setText((String)var3_3, this.mQueryString, 2131689499);
            var6_15 = this.mMsgCursor.getLong(this.mDatePos);
            var3_3 = new StringBuilder();
            DateUtils.formatRelativeTime((StringBuilder)var3_3, (long)var6_15, (boolean)false);
            var10_11.setText((CharSequence)var3_3.toString());
            var3_3 = var2_2;
            if (var4_4 != 4) return var3_3;
            var8_5.setOnClickListener(new View.OnClickListener((Conversation)var9_8){
                final /* synthetic */ Conversation val$c;

                public void onClick(View view) {
                    SearchAdapter.this.mExpandedConversations.add((Object)this.val$c);
                    SearchAdapter.this.notifyDataSetChanged();
                }
            });
            return var2_2;
        }

        public int getViewTypeCount() {
            return 8;
        }

        public boolean hasRunningTask() {
            if (this.mLatestTask != null) {
                return true;
            }
            return false;
        }

        public void query() {
            this.mLatestTask = new AsyncTask<Void, Void, Void>(){
                private String mQueryInTask;
                private ContactList mResultContacts;
                private ArrayList<Conversation> mResultConversationForMessages;
                private ArrayList<Conversation> mResultConversations;
                private Cursor mResultCursor;
                private HashSet<String> mResultDuplicatedNames;
                private HashMap<Long, ArrayList<Integer>> mResultMsgPos;
                private ArrayList<Long> mResultMsgThreadIds;

                /*
                 * Enabled aggressive block sorting
                 */
                protected /* varargs */ Void doInBackground(Void ... object) {
                    long l;
                    if (TextUtils.isEmpty((CharSequence)this.mQueryInTask)) {
                        return null;
                    }
                    Object object2 = Telephony.MmsSms.SEARCH_URI.buildUpon().appendQueryParameter("pattern", this.mQueryInTask);
                    object = SearchFragment.this.mPrivateMode ? "1" : "0";
                    object = object2.appendQueryParameter("privacy_flag", (String)object).build();
                    this.mResultCursor = SqliteWrapper.query((Context)SearchFragment.this.mActivity, (ContentResolver)SearchFragment.this.mActivity.getContentResolver(), (Uri)object, (String[])null, (String)null, (String[])null, (String)null);
                    if (this.mResultCursor == null) {
                        return null;
                    }
                    if (SearchAdapter.this.mLatestTask != this) {
                        return null;
                    }
                    int n = this.mResultCursor.getColumnIndex("thread_id");
                    this.mResultConversationForMessages = Lists.newArrayList();
                    this.mResultMsgPos = new HashMap();
                    this.mResultMsgThreadIds = Lists.newArrayList();
                    this.mResultCursor.moveToPosition(-1);
                    int n2 = 0;
                    while (this.mResultCursor.moveToNext()) {
                        l = this.mResultCursor.getLong(n);
                        if (this.mResultMsgPos.containsKey((Object)l)) {
                            ((ArrayList)this.mResultMsgPos.get((Object)l)).add((Object)n2);
                        } else {
                            object = Lists.newArrayList();
                            object.add((Object)n2);
                            this.mResultMsgPos.put((Object)l, object);
                            this.mResultMsgThreadIds.add((Object)l);
                        }
                        ++n2;
                    }
                    object = this.mResultMsgThreadIds.iterator();
                    while (object.hasNext()) {
                        l = (Long)object.next();
                        n = ((ArrayList)this.mResultMsgPos.get((Object)l)).size();
                        object2 = Conversation.get(l);
                        for (n2 = 0; n2 < n; ++n2) {
                            this.mResultConversationForMessages.add(object2);
                        }
                    }
                    this.mResultConversations = Conversation.searchForConversations((Context)SearchFragment.this.mActivity, this.mQueryInTask, SearchFragment.this.mPrivateMode);
                    object = Sets.newHashSet();
                    object2 = Sets.newHashSet();
                    this.mResultDuplicatedNames = Sets.newHashSet();
                    Object object3 = this.mResultConversations.iterator();
                    while (object3.hasNext()) {
                        Contact contact = (Contact)((Conversation)object3.next()).getRecipients().get(0);
                        String string2 = contact.getName();
                        object2.add((Object)contact);
                        if (object.contains((Object)string2)) {
                            this.mResultDuplicatedNames.add((Object)string2);
                            continue;
                        }
                        object.add((Object)string2);
                    }
                    this.mResultContacts = Contact.searchForContacts(this.mQueryInTask);
                    this.mResultContacts.removeAll(object2);
                    object2 = this.mResultContacts.iterator();
                    while (object2.hasNext()) {
                        object3 = ((Contact)object2.next()).getName();
                        if (object.contains(object3)) {
                            this.mResultDuplicatedNames.add(object3);
                            continue;
                        }
                        object.add(object3);
                    }
                    return null;
                }

                protected void onPostExecute(Void object) {
                    if (SearchAdapter.this.mLatestTask != this || !SearchFragment.this.isAdded()) {
                        if (this.mResultCursor != null) {
                            this.mResultCursor.close();
                        }
                        return;
                    }
                    SearchAdapter.this.mLatestTask = null;
                    SearchAdapter.this.closeCursor();
                    SearchAdapter.this.mMsgCursor = this.mResultCursor;
                    SearchAdapter.this.mConversations = this.mResultConversations;
                    SearchAdapter.this.mConversationForMessages = this.mResultConversationForMessages;
                    SearchAdapter.this.mDuplicatedNames = this.mResultDuplicatedNames;
                    SearchAdapter.this.mContacts = this.mResultContacts;
                    SearchAdapter.this.mExpandedConversations.clear();
                    SearchAdapter.this.mMsgPosition = Lists.newArrayList();
                    if (this.mResultMsgThreadIds != null && this.mResultMsgPos != null) {
                        object = this.mResultMsgThreadIds.iterator();
                        while (object.hasNext()) {
                            long l = (Long)object.next();
                            Iterator iterator = ((ArrayList)this.mResultMsgPos.get((Object)l)).iterator();
                            while (iterator.hasNext()) {
                                int n = (Integer)iterator.next();
                                SearchAdapter.this.mMsgPosition.add((Object)n);
                            }
                        }
                    }
                    if (SearchAdapter.this.mMsgCursor != null) {
                        SearchAdapter.this.mThreadIdPos = SearchAdapter.this.mMsgCursor.getColumnIndex("thread_id");
                        SearchAdapter.this.mBodyPos = SearchAdapter.this.mMsgCursor.getColumnIndex("body");
                        SearchAdapter.this.mSubjectPos = SearchAdapter.this.mMsgCursor.getColumnIndex("sub");
                        SearchAdapter.this.mRowIdPos = SearchAdapter.this.mMsgCursor.getColumnIndex("_id");
                        SearchAdapter.this.mDatePos = SearchAdapter.this.mMsgCursor.getColumnIndex("date");
                    }
                    SearchFragment.this.mSearchCount.setText((CharSequence)SearchFragment.this.getResources().getQuantityString(2131623939, SearchAdapter.this.getCount(), new Object[]{SearchAdapter.this.getCount(), SearchAdapter.this.mQueryString}));
                    SearchAdapter.this.notifyDataSetChanged();
                }
            };
            this.mLatestTask.execute((Object[])new Void[0]);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void query(String string2) {
            string2 = TextUtils.isEmpty((CharSequence)string2) ? null : string2.trim();
            this.mQueryString = string2;
            this.query();
        }

    }

    public static class TextViewSnippet
    extends TextView {
        private String mFullText;
        private int mHlAppearance;
        private String mTargetString;
        private String[] mTargetTokens;

        public TextViewSnippet(Context context) {
            super(context);
        }

        public TextViewSnippet(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public TextViewSnippet(Context context, AttributeSet attributeSet, int n) {
            super(context, attributeSet, n);
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
            int n5;
            String string2 = this.mTargetTokens[0].toLowerCase();
            String string3 = this.mFullText.toLowerCase();
            String string4 = string2.toLowerCase();
            int n6 = string3.indexOf(string2);
            if (this.mHlAppearance == 0 || n6 == -1) {
                this.setText((CharSequence)this.mFullText);
                super.onLayout(bl, n, n2, n3, n4);
                return;
            }
            int n7 = string4.length();
            if (n7 > (n5 = string3.length())) {
                this.setText((CharSequence)this.mFullText);
                super.onLayout(bl, n, n2, n3, n4);
                return;
            }
            string3 = this.getPaint();
            float f2 = string3.measureText(string2);
            float f3 = string3.measureText(this.mFullText);
            float f4 = this.getWidth();
            string2 = this.mFullText;
            if (f2 > f4) {
                string2 = this.mFullText.substring(n6, n6 + n7);
            } else if (f3 <= f4) {
                string2 = this.mFullText;
            } else {
                float f5 = string3.measureText("\u2026");
                n7 = n6 + this.mTargetTokens[0].length();
                f2 = string3.measureText(this.mFullText, n6, n7);
                while (f2 < f4 - 2.0f * f5 && (n6 > 0 || n7 < n5)) {
                    int n8 = n6;
                    f3 = f2;
                    if (n6 > 0) {
                        n8 = n6 - 1;
                        f3 = f2 + string3.measureText(this.mFullText, n8, n8 + 1);
                    }
                    n6 = n8;
                    f2 = f3;
                    if (n7 >= n5) continue;
                    f2 = f3 + string3.measureText(this.mFullText, n7 - 1, ++n7);
                    n6 = n8;
                }
                string2 = n6 == 0 ? "" : "\u2026";
                string4 = this.mFullText.substring(n6, n7);
                string3 = n7 == n5 ? "" : "\u2026";
                string2 = String.format((String)"%s%s%s", (Object[])new Object[]{string2, string4, string3});
            }
            MessageUtils.showTextWithHighlight(this, string2, this.mTargetString, this.mHlAppearance);
            super.onLayout(bl, n, n2, n3, n4);
        }

        public void setText(String string2, String string3, int n) {
            this.mFullText = string2;
            this.mTargetString = string3;
            this.mTargetTokens = string3.split(" +");
            this.mHlAppearance = n;
            this.requestLayout();
        }
    }

}

