/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.DataSetObserver
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Parcelable
 *  android.preference.PreferenceManager
 *  android.provider.CallLog
 *  android.provider.CallLog$Calls
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$Contacts
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.text.ClipboardManager
 *  android.text.Editable
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.View$OnFocusChangeListener
 *  android.view.View$OnKeyListener
 *  android.view.View$OnLongClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.Window
 *  android.view.animation.Animation
 *  android.view.animation.AnimationUtils
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.Filter
 *  android.widget.LinearLayout
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.PopupWindow
 *  android.widget.PopupWindow$OnDismissListener
 *  android.widget.ScrollView
 *  android.widget.TextView
 *  android.widget.Toast
 *  com.google.android.collect.Lists
 *  com.google.android.collect.Maps
 *  java.lang.Boolean
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.StringTokenizer
 *  miui.app.ProgressDialog
 *  miui.os.Build
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$MmsSms
 *  miui.telephony.PhoneNumberUtils
 *  miui.util.AttributeResolver
 *  miui.view.menu.ContextMenuDialog
 *  miui.widget.GuidePopupWindow
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.LogTag;
import com.android.mms.MmsConfig;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.MessageEditableActivityBase;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.RecipientsAdapter;
import com.android.mms.ui.RowLayout;
import com.android.mms.ui.SizeAwareLinearLayout;
import com.android.mms.ui.SmsTextSizeAdjust;
import com.android.mms.ui.StaticGridView;
import com.android.mms.util.MSimUtils;
import com.google.android.collect.Lists;
import com.google.android.collect.Maps;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.B2cMessageUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import miui.app.ProgressDialog;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import miui.telephony.PhoneNumberUtils;
import miui.util.AttributeResolver;
import miui.view.menu.ContextMenuDialog;
import miui.widget.GuidePopupWindow;

public class NewMessageActivity
extends MessageEditableActivityBase {
    private static float ADJUST_SPEED;
    private static final String RECIPIENT_SEPARATORS;
    private View mAddRecipientButton;
    private boolean mAdjustEditorHeight;
    private ContactList mCommitedRecipients = new ContactList();
    private View mConfirmRecipientButton;
    private Runnable mDrawContactPanelRunnable;
    private boolean mExtraCallPrivateRecipientConv;
    private GuidePopupWindow mGuidePopupWindow;
    private Runnable mHideSoftKeyboardRunnable;
    private ArrayList<String> mOriginalCommitedNumbers = Lists.newArrayList();
    private ProgressDialog mPickContactsProgressDialog;
    private AsyncTask<Void, Void, Void> mPickContactsTask;
    private HashMap<Contact, List<Uri>> mPickedUrisMap = Maps.newHashMap();
    private StaticGridView mRecentContactGrid;
    private ContactList mRecentContacts = new ContactList();
    private boolean mRecentContactsDrawn;
    private boolean mRecentContactsLoaded;
    private RecipientsAdapter mRecipientAdapter;
    private EditText mRecipientEditor;
    private TextView mRecipientEditorCount;
    private int mRecipientRowPadding;
    private RowLayout mRecipientRows;
    private ScrollView mRecipientRowsScroller;
    private final View.OnKeyListener mRecipientsKeyListener;
    private TextView mRecipientsViewer;
    private TextView mRecipientsViewerCount;
    private TextView mRecipientsViewerHead;
    private LinearLayout mRecipientsViewerLinearLayout;
    private final TextWatcher mRecipientsWatcher;
    private ListView mSuggestionList;
    private int mTitleViewHeightOneRow;
    private int mTitleViewHeightThreeRow;
    private int mTitleViewHeightTwoRow;
    private AsyncTask<Void, Void, Boolean> mUpdateCommitedRecipientsTask;
    protected Runnable mUpdateRecipientsBkgRunnable;

    /*
     * Enabled aggressive block sorting
     */
    static {
        String string2 = Build.IS_INTERNATIONAL_BUILD ? "\n\r\t,\uff0c;\uff1b" : " \n\r\t,\uff0c;\uff1b";
        RECIPIENT_SEPARATORS = string2;
        ADJUST_SPEED = 0.5f;
    }

    public NewMessageActivity() {
        this.mDrawContactPanelRunnable = new Runnable(){

            @Override
            public void run() {
                Log.v((String)"NewMessageActivity", (String)"run mDrawContactPanelRunnable");
                int n = NewMessageActivity.this.switchRecipientsRowView();
                if (NewMessageActivity.this.isVisible((View)NewMessageActivity.this.mRecipientRowsScroller)) {
                    NewMessageActivity.this.mRecipientEditorCount.setText((CharSequence)NewMessageActivity.this.getBaseContext().getString(2131362055, new Object[]{NewMessageActivity.this.recipientCount()}));
                    if (n > 2) {
                        NewMessageActivity.this.mRecipientEditorCount.setVisibility(0);
                        NewMessageActivity.this.mRecipientRowsScroller.setVerticalScrollBarEnabled(true);
                        return;
                    }
                    NewMessageActivity.this.mRecipientEditorCount.setVisibility(8);
                    NewMessageActivity.this.mRecipientRowsScroller.setVerticalScrollBarEnabled(false);
                    return;
                }
                NewMessageActivity.this.mRecipientEditorCount.setVisibility(8);
            }
        };
        this.mRecipientsKeyListener = new View.OnKeyListener(){

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public boolean onKey(View view, int n, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                switch (n) {
                    default: {
                        return false;
                    }
                    case 67: 
                }
                if (NewMessageActivity.this.mRecipientEditor.getSelectionEnd() != 0) return false;
                if (NewMessageActivity.this.mCommitedRecipients.isEmpty()) return false;
                NewMessageActivity.this.removeRecipient(NewMessageActivity.this.mCommitedRecipients.size() - 1);
                return true;
            }
        };
        this.mRecipientsWatcher = new TextWatcher(){

            /*
             * Enabled aggressive block sorting
             */
            public void afterTextChanged(Editable editable) {
                if (NewMessageActivity.this.mCommitedRecipients.isEmpty() && NewMessageActivity.this.mRecipientEditor.getText().length() == 0) {
                    NewMessageActivity.this.mRecipientsViewerHead.setVisibility(0);
                } else {
                    NewMessageActivity.this.mRecipientsViewerHead.setVisibility(8);
                }
                String string2 = editable.toString() + '$';
                StringTokenizer stringTokenizer = new StringTokenizer(string2, RECIPIENT_SEPARATORS);
                ArrayList arrayList = new ArrayList();
                while (stringTokenizer.hasMoreTokens()) {
                    arrayList.add((Object)stringTokenizer.nextToken());
                }
                if (arrayList.size() > 1 || ((String)arrayList.get(0)).length() < string2.length()) {
                    int n;
                    string2 = (String)arrayList.get(arrayList.size() - 1);
                    string2 = string2.substring(0, string2.length() - 1);
                    int n2 = n = NewMessageActivity.this.mRecipientEditor.getSelectionEnd() - (editable.length() - string2.length());
                    if (n < 0) {
                        n2 = 0;
                    }
                    NewMessageActivity.this.mRecipientEditor.setText((CharSequence)string2);
                    NewMessageActivity.this.mRecipientAdapter.start();
                    NewMessageActivity.this.mRecipientAdapter.getFilter().filter((CharSequence)string2);
                    NewMessageActivity.this.mRecipientEditor.setSelection(n2);
                    n = 0;
                    for (n2 = 0; n2 < arrayList.size() - 1; ++n2) {
                        int n3 = n;
                        if (NewMessageActivity.this.commitRecipient((String)arrayList.get(n2))) {
                            n3 = n;
                            if (n == 0) {
                                n3 = 1;
                            }
                        }
                        n = n3;
                    }
                } else {
                    NewMessageActivity.this.mRecipientAdapter.start();
                    NewMessageActivity.this.mRecipientAdapter.getFilter().filter((CharSequence)editable);
                    NewMessageActivity.this.mSuggestionList.setSelection(0);
                }
                NewMessageActivity.this.postUpdateSendButtonState();
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                NewMessageActivity.this.onUserInteraction();
            }
        };
        this.mUpdateRecipientsBkgRunnable = new Runnable(){

            @Override
            public void run() {
                if (NewMessageActivity.this.mRecipientRowsScroller.getVisibility() == 0) {
                    NewMessageActivity.this.updateRecipientsBackground();
                }
            }
        };
        this.mHideSoftKeyboardRunnable = new Runnable(){

            @Override
            public void run() {
                NewMessageActivity.this.hideSoftKeyboard();
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void addRecipient(final Contact contact, final String string2) {
        String string3;
        if (this.mCommitedRecipients.indexOf((Object)contact) != -1) {
            return;
        }
        this.mOriginalCommitedNumbers.add((Object)string2);
        this.mCommitedRecipients.add(contact);
        TextView textView = (TextView)this.getLayoutInflater().inflate(2130968695, null, false);
        if (this.contactNameExists(contact)) {
            textView.setText((CharSequence)contact.getName());
        } else {
            textView.setText((CharSequence)string2);
        }
        if (this.allowMx(string3 = contact.getMxPhoneNumber())) {
            textView.setTextColor(this.getResources().getColor(2131296361));
            textView.setBackgroundResource(2130837872);
        }
        textView.setTag((Object)string3);
        textView.setOnClickListener(new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onClick(View view) {
                NewMessageActivity.this.mRecipientRows.removeView(view);
                NewMessageActivity.this.mCommitedRecipients.remove(contact);
                view = NewMessageActivity.this.mPickedUrisMap;
                synchronized (view) {
                    NewMessageActivity.this.mPickedUrisMap.remove((Object)contact);
                }
                NewMessageActivity.this.postDrawContactPanel();
                NewMessageActivity.this.mOriginalCommitedNumbers.remove((Object)string2);
                NewMessageActivity.this.enableContactInRecentList(contact, true);
                NewMessageActivity.this.onContactRemoved(contact);
                NewMessageActivity.this.syncNumbersToWorkingMessage();
                if (NewMessageActivity.this.mCommitedRecipients.isEmpty() && NewMessageActivity.this.mRecipientEditor.getText().length() == 0) {
                    NewMessageActivity.this.mRecipientsViewerHead.setVisibility(0);
                }
            }
        });
        textView.setOnLongClickListener(new View.OnLongClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onLongClick(View object) {
                ContextMenuDialog contextMenuDialog = new ContextMenuDialog((Context)NewMessageActivity.this);
                contextMenuDialog.setTitle(string2);
                if (NewMessageActivity.this.contactNameExists(contact)) {
                    contextMenuDialog.setTitle(String.format((String)"%s (%s)", (Object[])new Object[]{string2, contact.getName()}));
                    contextMenuDialog.addMenuItem(2131361889, new Runnable(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        @Override
                        public void run() {
                            Intent intent;
                            if (contact.isYellowPageNumber()) {
                                intent = new Intent("com.miui.yellowpage.action.VIEW");
                                intent.putExtra("android.intent.extra.PHONE_NUMBER", contact.getNumber());
                                intent.putExtra("source", "sms_recent_contacts");
                            } else {
                                intent = new Intent("android.intent.action.VIEW", contact.getUri());
                            }
                            intent.setFlags(524288);
                            NewMessageActivity.this.startActivity(intent);
                        }
                    });
                } else {
                    object = Telephony.Mms.isPhoneNumber((String)string2) ? "phone" : (Telephony.Mms.isEmailAddress((String)string2) ? "email" : null);
                    if (object != null) {
                        contextMenuDialog.addMenuItem(2131362083, new Runnable((String)object){
                            final /* synthetic */ String val$insert;

                            @Override
                            public void run() {
                                Intent intent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
                                intent.putExtra(this.val$insert, string2);
                                intent.setFlags(524288);
                                NewMessageActivity.this.startActivity(intent);
                            }
                        });
                    }
                }
                contextMenuDialog.addMenuItem(2131362080, new Runnable(){

                    @Override
                    public void run() {
                        ((ClipboardManager)NewMessageActivity.this.getSystemService("clipboard")).setText((CharSequence)string2);
                        Toast.makeText((Context)NewMessageActivity.this, (int)2131362081, (int)0).show();
                    }
                });
                contextMenuDialog.show();
                return true;
            }

        });
        this.mRecipientRows.addView((View)textView, this.mRecipientRows.getChildCount() - 1, (ViewGroup.LayoutParams)new RowLayout.LayoutParams(-2, -2));
        this.postDrawContactPanel();
        this.enableContactInRecentList(contact, false);
        this.onContactAdded(contact);
    }

    private boolean allowMx(String object) {
        object = MxIdCache.get((String)object);
        boolean bl = this.mWorkingMessage.requiresMms();
        if (object != null && (bl && object.allowMms() || !bl && object.allowSms())) {
            return true;
        }
        return false;
    }

    private void cancelUpdateCommitedRecipients() {
        if (this.mUpdateCommitedRecipientsTask != null) {
            this.mUpdateCommitedRecipientsTask.cancel(true);
        }
        this.mUpdateCommitedRecipientsTask = null;
    }

    private void commitEditingRecipient() {
        Editable editable = this.mRecipientEditor.getText();
        if (editable.length() > 0) {
            this.mRecipientEditor.setText((CharSequence)"");
            if (this.commitRecipient(editable.toString())) {
                this.updateCommitedRecipients();
            }
        }
    }

    private void commitRecipient(Contact contact) {
        this.commitRecipient(contact, contact.getNumber());
    }

    private boolean commitRecipient(Contact contact, String string2) {
        if (this.mCommitedRecipients.indexOf((Object)contact) != -1) {
            return false;
        }
        boolean bl = false;
        if (this.mCommitedRecipients.isEmpty()) {
            bl = true;
        }
        this.addRecipient(contact, string2);
        this.syncNumbersToWorkingMessage();
        this.mRecipientsViewerHead.setVisibility(8);
        if (bl) {
            this.postUpdateSendButtonState();
        }
        return true;
    }

    private boolean commitRecipient(String string2) {
        return this.commitRecipient(Contact.get(string2).load(false, false), string2);
    }

    private boolean contactNameExists(Contact contact) {
        if (contact.existsInDatabase() || contact.isYellowPageNumber() && !TextUtils.isEmpty((CharSequence)contact.getName()) && PhoneNumberUtils.isChinaMobileNumber((String)contact.getNumber())) {
            return true;
        }
        return false;
    }

    private GuidePopupWindow createUserGuide(View view, String string2, PopupWindow.OnDismissListener onDismissListener) {
        GuidePopupWindow guidePopupWindow = null;
        if (this.isResumed()) {
            guidePopupWindow = new GuidePopupWindow((Context)this);
            guidePopupWindow.setGuideText(string2);
            guidePopupWindow.setOutsideTouchable(true);
            guidePopupWindow.setInputMethodMode(2);
            if (onDismissListener != null) {
                guidePopupWindow.setOnDismissListener(onDismissListener);
            }
            guidePopupWindow.show(view, true);
        }
        return guidePopupWindow;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void drawRecipientContactsGrid(int n) {
        int n2;
        if (this.mRecentContactsDrawn) {
            return;
        }
        int n3 = View.MeasureSpec.getSize((int)n);
        if (!this.mIsLandscape) {
            n = 3;
            n2 = this.getResources().getDimensionPixelSize(2131427364);
        } else {
            n = 6;
            n2 = this.getResources().getDimensionPixelSize(2131427365);
        }
        this.mRecentContactGrid.set((this.mRecentContacts.size() + n - 1) / n, n, n3 /= n, n2);
        n3 = this.mRecentContactGrid.getChildCount();
        n2 = 0;
        do {
            if (n2 >= n3) {
                this.mRecentContactsDrawn = true;
                return;
            }
            View view = this.mRecentContactGrid.getChildAt(n2).findViewById(2131820851);
            if (n2 % n == n - 1) {
                view.setVisibility(8);
            } else {
                view.setVisibility(0);
            }
            ++n2;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void enableContactInRecentList(Contact contact, boolean bl) {
        int n = this.mRecentContactGrid.getChildCount();
        int n2 = 0;
        while (n2 < n) {
            View view = this.mRecentContactGrid.getChildAt(n2);
            if (contact == (Contact)this.mRecentContacts.get(n2)) {
                view.findViewById(2131820852).setEnabled(bl);
                return;
            }
            ++n2;
        }
    }

    private String getCommitedRecipientName(int n) {
        Contact contact = (Contact)this.mCommitedRecipients.get(n);
        if (this.contactNameExists(contact)) {
            return contact.getName();
        }
        return (String)this.mOriginalCommitedNumbers.get(n);
    }

    private ArrayList<String> getRecipientNumbers() {
        ArrayList arrayList = new ArrayList(this.mOriginalCommitedNumbers);
        if (this.hasUncommitedRecipient()) {
            arrayList.add((Object)this.mRecipientEditor.getText().toString());
        }
        return arrayList;
    }

    private boolean hasUncommitedRecipient() {
        if (this.mRecipientEditor.getText().length() > 0) {
            return true;
        }
        return false;
    }

    private void initRecipientsEditor() {
        this.mRecipientEditor = (EditText)this.findViewById(2131820823);
        this.mRecipientEditor.setTextColor(AttributeResolver.resolveColor((Context)this, (int)16843601));
        this.mRecipientEditor.addTextChangedListener(this.mRecipientsWatcher);
        this.mRecipientEditor.setOnKeyListener(this.mRecipientsKeyListener);
        this.mRecipientEditor.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            public void onFocusChange(View view, boolean bl) {
                if (PreferenceManager.getDefaultSharedPreferences((Context)NewMessageActivity.this).getBoolean("pref_key_show_recent_contacts", true)) {
                    NewMessageActivity.this.showRecentContactGridWithAnim(bl);
                }
                NewMessageActivity.this.onRecipientEditorFocusChange(bl);
            }
        });
    }

    private boolean isValidAddress(String string2) {
        if (this.mWorkingMessage.requiresMms()) {
            return MessageUtils.isValidMmsAddress(string2);
        }
        boolean bl = true;
        String string3 = string2;
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            string3 = Contact.removeSpaceForAddress(string2);
            bl = PhoneNumberUtils.isDialable((String)string3);
        }
        if (bl && PhoneNumberUtils.isWellFormedSmsAddress((String)string3) || Telephony.Mms.isEmailAddress((String)string3)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void launchMultiplePhonePicker() {
        this.commitEditingRecipient();
        Intent intent = new Intent("com.android.contacts.action.GET_MULTIPLE_PHONES");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("vnd.android.cursor.dir/phone_v2");
        intent.setPackage(MessageUtils.getContactsPackageName());
        intent.putExtra("android.intent.extra.include_unknown_numbers", true);
        intent.putExtra("number_count", MmsConfig.getRecipientLimit());
        ArrayList arrayList = new ArrayList();
        Iterator iterator = this.mCommitedRecipients.iterator();
        while (iterator.hasNext()) {
            Contact contact = (Contact)iterator.next();
            Object object = this.mPickedUrisMap;
            // MONITORENTER : object
            List list = (List)this.mPickedUrisMap.get((Object)contact);
            // MONITOREXIT : object
            if (list != null && list.size() > 0) {
                object = list.iterator();
                while (object.hasNext()) {
                    arrayList.add((Uri)object.next());
                }
                continue;
            }
            int n = contact.getContactMethodType();
            if (1 == n) {
                object = contact.getPhoneUri();
                if (arrayList.contains(object)) continue;
                arrayList.add(object);
                continue;
            }
            if (2 != n || arrayList.contains(object = contact.getEmailUri())) continue;
            arrayList.add(object);
        }
        if (arrayList.size() > 0) {
            intent.putExtra("com.android.contacts.extra.PHONE_URIS", (Parcelable[])arrayList.toArray((T[])new Uri[arrayList.size()]));
        }
        this.startActivityForResult(intent, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onRecipientEditorFocusChange(boolean bl) {
        if (bl) {
            this.showSoftKeyboard();
        } else {
            this.commitEditingRecipient();
            this.updateRecipientsViewerText();
            if (this.mAllowAnimation) {
                this.mRecipientRowsScroller.startAnimation(AnimationUtils.loadAnimation((Context)this.getBaseContext(), (int)2131034117));
                this.mRecipientsViewerLinearLayout.startAnimation(AnimationUtils.loadAnimation((Context)this.getBaseContext(), (int)2131034116));
            }
            this.mRecipientRowsScroller.setVisibility(8);
            this.mRecipientsViewerLinearLayout.setVisibility(0);
        }
        this.postDrawContactPanel();
    }

    private void postDrawContactPanel() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mDrawContactPanelRunnable);
            this.mHandler.postDelayed(this.mDrawContactPanelRunnable, 10);
            return;
        }
        Log.v((String)"NewMessageActivity", (String)"postDrawContactPanel mHandler is null");
    }

    private void postHideSoftKeyboard() {
        this.mHandler.removeCallbacks(this.mHideSoftKeyboardRunnable);
        this.mHandler.postDelayed(this.mHideSoftKeyboardRunnable, 10);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void postUpdateRecipientsBackground(boolean bl) {
        this.mHandler.removeCallbacks(this.mUpdateRecipientsBkgRunnable);
        Handler handler = this.mHandler;
        Runnable runnable = this.mUpdateRecipientsBkgRunnable;
        long l = bl ? 2000 : 0;
        handler.postDelayed(runnable, l);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void processPickResult(final Intent arrparcelable) {
        this.mWorkingMessage.asyncDeleteDraftSmsMessage(this.mConversation);
        this.removeAllRecipients();
        arrparcelable = arrparcelable.getParcelableArrayExtra("com.android.contacts.extra.PHONE_URIS");
        int n = arrparcelable != null ? arrparcelable.length : 0;
        int n2 = MmsConfig.getRecipientLimit();
        if (n2 != Integer.MAX_VALUE && n > n2) {
            new AlertDialog.Builder((Context)this).setTitle(2131362037).setIconAttribute(16843605).setMessage((CharSequence)this.getString(2131361873, new Object[]{n, n2})).setPositiveButton(17039370, null).create().show();
            return;
        }
        if (this.mPickContactsTask != null) {
            this.mPickContactsTask.cancel(true);
        }
        this.mPickContactsTask = new AsyncTask<Void, Void, Void>(){
            private HashMap<Contact, List<Uri>> mMap;

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            protected /* varargs */ Void doInBackground(Void ... object) {
                if (arrparcelable == null || arrparcelable.length <= 0 || NewMessageActivity.this.mPickedUrisMap == null) {
                    return null;
                }
                ArrayList arrayList = Lists.newArrayList();
                Parcelable[] arrparcelable2 = arrparcelable;
                int n = arrparcelable2.length;
                int n2 = 0;
                while (n2 < n) {
                    Contact contact;
                    List list;
                    object = arrparcelable2[n2];
                    if (this.isCancelled()) {
                        return null;
                    }
                    Uri uri = (Uri)object;
                    if ("tel".equals((Object)uri.getScheme())) {
                        contact = Contact.get(uri.getSchemeSpecificPart());
                        object = NewMessageActivity.this.mPickedUrisMap;
                        synchronized (object) {
                            list = (List)NewMessageActivity.this.mPickedUrisMap.get((Object)contact);
                        }
                        object = list;
                        if (list == null) {
                            object = new ArrayList(2);
                        }
                        object.add(uri);
                        this.mMap.put((Object)contact, object);
                    } else if ("content".equals((Object)uri.getScheme())) {
                        arrayList.add((Object)uri);
                    } else {
                        object = uri.getSchemeSpecificPart();
                        if (MessageUtils.isEmail((String)object)) {
                            uri = Contact.get((String)object);
                            object = null;
                            list = NewMessageActivity.this.mPickedUrisMap;
                            synchronized (list) {
                                if (NewMessageActivity.this.mPickedUrisMap.containsKey(uri)) {
                                    object = (List)NewMessageActivity.this.mPickedUrisMap.get(uri);
                                }
                            }
                            this.mMap.put(uri, object);
                        }
                    }
                    object = Contact.getByPhoneUris((Parcelable[])arrayList.toArray((Object[])new Parcelable[arrayList.size()]));
                    if (this.isCancelled()) {
                        return null;
                    }
                    if (object != null) {
                        uri = object.iterator();
                        while (uri.hasNext()) {
                            contact = (Contact)uri.next();
                            if (this.isCancelled()) {
                                return null;
                            }
                            object = null;
                            list = NewMessageActivity.this.mPickedUrisMap;
                            synchronized (list) {
                                if (NewMessageActivity.this.mPickedUrisMap.containsKey((Object)contact)) {
                                    object = (List)NewMessageActivity.this.mPickedUrisMap.get((Object)contact);
                                }
                            }
                            this.mMap.put((Object)contact, object);
                        }
                    }
                    ++n2;
                }
                return null;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            protected void onPostExecute(Void void_) {
                for (Contact contact : this.mMap.keySet()) {
                    NewMessageActivity.this.addRecipient(contact, contact.getNumber());
                    void_ = NewMessageActivity.this.mPickedUrisMap;
                    synchronized (void_) {
                        if (!NewMessageActivity.this.mPickedUrisMap.containsKey((Object)contact)) {
                            NewMessageActivity.this.mPickedUrisMap.put((Object)contact, this.mMap.get((Object)contact));
                        }
                        continue;
                    }
                }
                this.mMap.clear();
                this.mMap = null;
                NewMessageActivity.this.syncNumbersToWorkingMessage();
                if (NewMessageActivity.this.mRecipientsViewer.getVisibility() == 0) {
                    NewMessageActivity.this.updateRecipientsViewerText();
                }
                if (NewMessageActivity.this.mPickContactsProgressDialog != null && NewMessageActivity.this.mPickContactsProgressDialog.isShowing()) {
                    NewMessageActivity.this.mPickContactsProgressDialog.dismiss();
                }
                NewMessageActivity.this.mPickContactsProgressDialog = null;
                NewMessageActivity.this.mPickContactsTask = null;
                NewMessageActivity.this.textEditorRequestFocus();
            }

            protected void onPreExecute() {
                NewMessageActivity.this.mPickContactsProgressDialog = new ProgressDialog((Context)NewMessageActivity.this);
                NewMessageActivity.this.mPickContactsProgressDialog.setMessage(NewMessageActivity.this.getText(2131362038));
                NewMessageActivity.this.mPickContactsProgressDialog.setIndeterminate(true);
                NewMessageActivity.this.mPickContactsProgressDialog.setCancelable(false);
                NewMessageActivity.this.mPickContactsProgressDialog.show();
                NewMessageActivity.this.mRecipientsViewerHead.setVisibility(8);
                this.mMap = Maps.newHashMap();
            }
        };
        this.mPickContactsTask.execute((Object[])new Void[0]);
    }

    private void removeAllRecipients() {
        this.mRecipientRows.removeViews(0, this.mCommitedRecipients.size());
        this.mCommitedRecipients.clear();
        this.mOriginalCommitedNumbers.clear();
        this.mRecipientEditor.setText((CharSequence)"");
        for (int i = 0; i < this.mRecentContactGrid.getChildCount(); ++i) {
            View view = this.mRecentContactGrid.getChildAt(i).findViewById(2131820852);
            if (view == null) continue;
            view.setEnabled(true);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void removeRecipient(int n) {
        Contact contact = this.mCommitedRecipients.remove(n);
        if (contact != null) {
            HashMap<Contact, List<Uri>> hashMap = this.mPickedUrisMap;
            synchronized (hashMap) {
                this.mPickedUrisMap.remove((Object)contact);
            }
        }
        this.mOriginalCommitedNumbers.remove(n);
        this.syncNumbersToWorkingMessage();
        this.mRecipientRows.removeViewAt(n);
        this.postDrawContactPanel();
        if (this.mCommitedRecipients.isEmpty() && this.mRecipientEditor.getText().length() == 0) {
            this.mRecipientsViewerHead.setVisibility(0);
        }
        this.enableContactInRecentList(contact, true);
        this.onContactRemoved(contact);
    }

    private void showAddAttachmentPanelGuide(SharedPreferences sharedPreferences) {
        PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener(){

            public void onDismiss() {
                NewMessageActivity.this.mGuidePopupWindow = null;
            }
        };
        this.mGuidePopupWindow = this.createUserGuide((View)this.mSwitchBtn, this.getString(2131362144), onDismissListener);
        if (this.mGuidePopupWindow != null) {
            sharedPreferences = sharedPreferences.edit();
            sharedPreferences.putBoolean("guide_add_attachments", false);
            sharedPreferences.commit();
        }
    }

    private void showRecentContactGrid(boolean bl) {
        if (bl && this.mRecentContacts.size() > 0 && this.mRecipientEditor.hasFocus()) {
            this.mRecentContactGrid.setVisibility(0);
            return;
        }
        this.mRecentContactGrid.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void showRecentContactGridWithAnim(boolean bl) {
        if (this.mRecentContactGrid.getVisibility() == 8 && bl) {
            if (this.mAllowAnimation) {
                Animation animation = AnimationUtils.loadAnimation((Context)this.getBaseContext(), (int)2131034116);
                this.mRecentContactGrid.startAnimation(animation);
            }
            this.mRecentContactGrid.setVisibility(0);
            return;
        } else {
            if (this.mRecentContactGrid.getVisibility() != 0 || bl) return;
            {
                if (this.mAllowAnimation) {
                    Animation animation = AnimationUtils.loadAnimation((Context)this.getBaseContext(), (int)2131034117);
                    this.mRecentContactGrid.startAnimation(animation);
                }
                this.mRecentContactGrid.setVisibility(8);
                return;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private int switchRecipientsRowView() {
        var1_1 = this.mRecipientRows.getRowCount();
        if (this.mRecipientAdapter.getCount() > 0 && this.mRecipientEditor.getText().length() > 0) {
            var1_1 = 1;
            this.mSuggestionList.setVisibility(0);
            this.mBottomPanel.setVisibility(8);
            this.showRecentContactGrid(false);
        } else {
            this.mSuggestionList.setVisibility(8);
            this.mBottomPanel.setVisibility(0);
            this.showRecentContactGrid(true);
        }
        if (this.mRecipientRowsScroller.getVisibility() != 0) {
            var2_2 = 1;
        } else {
            var1_1 = Math.min((int)var1_1, (int)3);
            var3_3 = this.mRecipientEditor.getHeight() * var1_1 + (var1_1 - 1) * this.mRecipientRowPadding;
            var2_2 = var1_1;
            if (this.mRecipientRowsScroller.getLayoutParams().height != var3_3) {
                this.mRecipientRowsScroller.getLayoutParams().height = var3_3;
                this.mRecipientRowsScroller.smoothScrollTo(0, var3_3);
                this.mRecipientRowsScroller.requestLayout();
                var2_2 = var1_1;
            }
        }
        var1_1 = this.mTitleViewHeightOneRow;
        switch (var2_2) {
            case 1: {
                this.mContactPanel.getLayoutParams().height = this.mTitleViewHeightOneRow;
                var1_1 = this.mTitleViewHeightOneRow;
                ** break;
            }
            case 2: {
                this.mContactPanel.getLayoutParams().height = this.mTitleViewHeightTwoRow;
                var1_1 = this.mTitleViewHeightTwoRow;
            }
lbl31: // 3 sources:
            default: {
                ** GOTO lbl36
            }
            case 3: 
        }
        this.mContactPanel.getLayoutParams().height = this.mTitleViewHeightThreeRow;
        var1_1 = this.mTitleViewHeightThreeRow;
lbl36: // 2 sources:
        this.setTopPlaceholderHeight(var1_1);
        return var2_2;
    }

    private void syncNumbersToWorkingMessage() {
        this.mWorkingMessage.setWorkingRecipients((List<String>)this.getRecipientNumbers());
        this.mWorkingMessage.setHasEmail(this.containsEmail(), true);
    }

    private void textEditorRequestFocus() {
        if (!this.mCommitedRecipients.isEmpty() && this.isVisible((View)this.mTextEditor)) {
            this.mTextEditor.requestFocus();
        }
    }

    private void updateCommitedRecipients() {
        this.cancelUpdateCommitedRecipients();
        if (!this.isResumed()) {
            LogTag.verbose("updateCommitedRecipients is not resumed", new Object[0]);
            return;
        }
        this.mUpdateCommitedRecipientsTask = new AsyncTask<Void, Void, Boolean>(){
            private ContactList mContactList;

            /*
             * Enabled aggressive block sorting
             */
            protected /* varargs */ Boolean doInBackground(Void ... object) {
                boolean bl = false;
                boolean bl2 = false;
                boolean bl3 = bl;
                if (this.mContactList == null) return bl3;
                bl3 = bl;
                if (this.mContactList.isEmpty()) return bl3;
                object = this.mContactList.iterator();
                do {
                    bl3 = bl2;
                    if (!object.hasNext()) return bl3;
                    Contact contact = (Contact)object.next();
                    if (this.isCancelled()) {
                        bl3 = false;
                        return bl3;
                    }
                    if (contact.existsInDatabase()) continue;
                    contact.load(true, true);
                    bl2 = true;
                } while (!this.isCancelled());
                bl3 = false;
                return bl3;
            }

            protected void onCancelled() {
                if (this.mContactList != null) {
                    this.mContactList.clear();
                }
                this.mContactList = null;
            }

            protected void onPostExecute(Boolean bl) {
                NewMessageActivity.this.mUpdateCommitedRecipientsTask = null;
                if (this.mContactList != null) {
                    this.mContactList.clear();
                }
                this.mContactList = null;
                if (bl.booleanValue() && NewMessageActivity.this.isResumed()) {
                    NewMessageActivity.this.updateRecipientsViewerText();
                    NewMessageActivity.this.updateRecipientRows();
                }
            }

            protected void onPreExecute() {
                this.mContactList = (ContactList)((Object)NewMessageActivity.this.mCommitedRecipients.clone());
            }
        };
        this.mUpdateCommitedRecipientsTask.execute((Object[])new Void[0]);
    }

    private void updateRecipientRows() {
        for (int i = 0; i < this.mRecipientRows.getChildCount() - 1; ++i) {
            ((TextView)this.mRecipientRows.getChildAt(i)).setText((CharSequence)((Contact)this.mCommitedRecipients.get(i)).getName());
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateRecipientsBackground() {
        int n = this.mRecipientRows.getChildCount();
        int n2 = 0;
        while (n2 < n) {
            Object object = this.mRecipientRows.getChildAt(n2);
            if (object instanceof TextView && object.getTag() != null) {
                TextView textView = (TextView)object;
                if (PushSession.getInstance((Context)this).getConnectedSimIndex() >= 0) {
                    if ((object = MxIdCache.getOrQuery((Context)this, (String)object.getTag())) != null && object.allowSms()) {
                        textView.setTextColor(this.getResources().getColor(2131296361));
                        textView.setBackgroundResource(2130837872);
                    } else {
                        textView.setTextColor(AttributeResolver.resolveColor((Context)this, (int)16843601));
                        textView.setBackgroundResource(2130837928);
                    }
                } else {
                    textView.setTextColor(AttributeResolver.resolveColor((Context)this, (int)16843601));
                    textView.setBackgroundResource(2130837928);
                }
            }
            ++n2;
        }
    }

    private void updateRecipientsViewerText() {
        int n = this.mCommitedRecipients.size();
        if (n == 0) {
            this.mRecipientsViewerHead.setVisibility(0);
            this.mRecipientsViewer.setText((CharSequence)"");
            this.mRecipientsViewerCount.setText((CharSequence)"");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(this.getCommitedRecipientName(0));
        for (int i = 1; i < this.mCommitedRecipients.size(); ++i) {
            stringBuilder.append(", ");
            stringBuilder.append(this.getCommitedRecipientName(i));
        }
        this.mRecipientsViewerHead.setVisibility(8);
        this.mRecipientsViewer.setText((CharSequence)stringBuilder);
        if (n > 1) {
            this.mRecipientsViewerCount.setVisibility(0);
            this.mRecipientsViewerCount.setText((CharSequence)this.getResources().getString(2131362054, new Object[]{n}));
            return;
        }
        this.mRecipientsViewerCount.setVisibility(8);
    }

    private void writeContactNumbers() {
        for (int i = 0; i < this.mCommitedRecipients.size(); ++i) {
            ((Contact)this.mCommitedRecipients.get(i)).setNumber((String)this.mOriginalCommitedNumbers.get(i));
        }
    }

    public boolean containsEmail() {
        for (String string2 : this.getRecipientNumbers()) {
            Contact contact = Contact.get(string2);
            if (!Telephony.Mms.isEmailAddress((String)string2) || B2cMessageUtils.isB2cNumber(contact)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean bl;
        boolean bl2 = bl = SmsTextSizeAdjust.getInstance().dispatchTouchEvent(motionEvent);
        if (!bl) {
            bl2 = super.dispatchTouchEvent(motionEvent);
        }
        return bl2;
    }

    public String formatInvalidNumbers() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string2 : this.getRecipientNumbers()) {
            if (this.isValidAddress(string2)) continue;
            if (stringBuilder.length() != 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(string2);
        }
        return stringBuilder.toString();
    }

    @Override
    protected int getContentViewResId() {
        return 2130968678;
    }

    @Override
    protected ContactList getRecipients() {
        return this.mCommitedRecipients;
    }

    public boolean hasInvalidRecipient() {
        for (String string2 : this.getRecipientNumbers()) {
            if (this.isValidAddress(string2)) continue;
            if (MmsConfig.getEmailGateway() == null) {
                return true;
            }
            if (MessageUtils.isAlias(string2)) continue;
            return true;
        }
        return false;
    }

    public boolean hasValidRecipient() {
        Iterator iterator = this.getRecipientNumbers().iterator();
        while (iterator.hasNext()) {
            if (!this.isValidAddress((String)iterator.next())) continue;
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    void initRecentList() {
        if (!PreferenceManager.getDefaultSharedPreferences((Context)this).getBoolean("pref_key_show_recent_contacts", true)) {
            if (!this.mRecentContactsLoaded) return;
            {
                this.mRecentContactsLoaded = false;
                this.mRecentContacts.clear();
                this.mRecentContactGrid.removeAllViews();
                return;
            }
        } else {
            if (this.mRecentContactsLoaded) return;
            {
                this.mRecentContactsLoaded = true;
                this.mRecentContacts.clear();
                this.mRecentContactGrid.removeAllViews();
                new AsyncTask<Void, Void, Void>(){
                    private final HashSet<String> mExistingNames;

                    private void addRecentRecipient(String object) {
                        object = Contact.get((String)object);
                        object.load(true, false);
                        if (NewMessageActivity.this.contactNameExists((Contact)object) && !this.mExistingNames.contains((Object)object.getName())) {
                            this.mExistingNames.add((Object)object.getName());
                            NewMessageActivity.this.mRecentContacts.add((Contact)object);
                        }
                    }

                    /*
                     * Enabled aggressive block sorting
                     * Enabled unnecessary exception pruning
                     * Enabled aggressive exception aggregation
                     */
                    protected /* varargs */ Void doInBackground(Void ... object) {
                        Cursor cursor = NewMessageActivity.this.getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[]{"number", "date"}, null, null, "date DESC LIMIT 50");
                        if (cursor == null) {
                            return null;
                        }
                        Cursor cursor2 = NewMessageActivity.this.getContentResolver().query(ExtraTelephony.MmsSms.CONTENT_RECENT_RECIPIENTS_URI, new String[]{"address", "date"}, null, null, "date DESC LIMIT 50");
                        if (cursor2 == null) {
                            cursor.close();
                            return null;
                        }
                        try {
                            cursor.moveToPosition(-1);
                            cursor2.moveToPosition(-1);
                            Object object2 = null;
                            object = null;
                            long l = 0;
                            long l2 = 0;
                            if (cursor.moveToNext()) {
                                object2 = cursor.getString(0);
                                l = cursor.getLong(1);
                            }
                            long l3 = l;
                            Object object3 = object2;
                            if (cursor2.moveToNext()) {
                                object = cursor2.getString(0);
                                l2 = cursor2.getLong(1);
                                object3 = object2;
                                l3 = l;
                            }
                            do {
                                object2 = object3;
                                if (cursor.isAfterLast()) break;
                                object2 = object3;
                                if (cursor2.isAfterLast()) break;
                                object2 = object3;
                                if (NewMessageActivity.this.mRecentContacts.size() >= 6) break;
                                if (l3 > l2) {
                                    this.addRecentRecipient((String)object3);
                                    if (!cursor.moveToNext()) continue;
                                    object3 = cursor.getString(0);
                                    l3 = cursor.getLong(1);
                                    continue;
                                }
                                this.addRecentRecipient((String)object);
                                if (!cursor2.moveToNext()) continue;
                                object = cursor2.getString(0);
                                l2 = cursor2.getLong(1);
                            } while (true);
                            do {
                                object3 = object;
                                if (cursor.isAfterLast()) break;
                                object3 = object;
                                if (NewMessageActivity.this.mRecentContacts.size() >= 6) break;
                                this.addRecentRecipient((String)object2);
                                if (!cursor.moveToNext()) continue;
                                object2 = cursor.getString(0);
                            } while (true);
                            while (!cursor2.isAfterLast() && NewMessageActivity.this.mRecentContacts.size() < 6) {
                                this.addRecentRecipient((String)object3);
                                if (!cursor2.moveToNext()) continue;
                                object3 = cursor2.getString(0);
                            }
                            return null;
                        }
                        finally {
                            cursor.close();
                            cursor2.close();
                        }
                    }

                    protected void onPostExecute(Void object) {
                        boolean bl = false;
                        object = NewMessageActivity.this.mRecentContacts.iterator();
                        while (object.hasNext()) {
                            final Contact contact = (Contact)object.next();
                            View view = NewMessageActivity.this.getLayoutInflater().inflate(2130968693, (ViewGroup)NewMessageActivity.this.mRecentContactGrid, false);
                            TextView textView = (TextView)view.findViewById(2131820852);
                            if (NewMessageActivity.this.mCommitedRecipients.contains((Object)contact)) {
                                textView.setEnabled(false);
                            }
                            textView.setText((CharSequence)contact.getName());
                            textView.setOnClickListener(new View.OnClickListener(){

                                public void onClick(View view) {
                                    NewMessageActivity.this.commitRecipient(contact);
                                    NewMessageActivity.this.updateRecipientsViewerText();
                                }
                            });
                            NewMessageActivity.this.mRecentContactGrid.addView(view);
                            NewMessageActivity.this.mRecentContactsDrawn = false;
                        }
                        object = NewMessageActivity.this;
                        boolean bl2 = bl;
                        if (NewMessageActivity.this.mRecentContacts.size() > 0) {
                            bl2 = bl;
                            if (NewMessageActivity.this.mRecipientEditor.hasFocus()) {
                                bl2 = true;
                            }
                        }
                        ((NewMessageActivity)object).showRecentContactGridWithAnim(bl2);
                    }

                }.execute((Object[])new Void[0]);
                return;
            }
        }
    }

    @Override
    protected void initResourceRefs() {
        super.initResourceRefs();
        this.mAddRecipientButton = this.findViewById(2131820828);
        this.mAddRecipientButton.setOnClickListener((View.OnClickListener)this);
        this.mConfirmRecipientButton = this.findViewById(2131820829);
        this.mConfirmRecipientButton.setOnClickListener((View.OnClickListener)this);
        this.mRecipientRowsScroller = (ScrollView)this.findViewById(2131820821);
        this.mRecipientRows = (RowLayout)this.findViewById(2131820822);
        this.mRecipientsViewerLinearLayout = (LinearLayout)this.findViewById(2131820824);
        this.mRecipientsViewerLinearLayout.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NewMessageActivity.this.mRecipientsViewerLinearLayout.setVisibility(8);
                NewMessageActivity.this.mRecipientRowsScroller.setVisibility(0);
                NewMessageActivity.this.mRecipientEditor.requestFocus();
                NewMessageActivity.this.mRecipientRowsScroller.scrollTo(0, NewMessageActivity.this.mRecipientRows.getMeasuredHeight());
                NewMessageActivity.this.showSoftKeyboard();
                NewMessageActivity.this.postUpdateRecipientsBackground(true);
            }
        });
        this.mRecipientEditorCount = (TextView)this.findViewById(2131820830);
        this.mRecipientsViewerHead = (TextView)this.findViewById(2131820820);
        this.mRecipientsViewerHead.setTextColor(this.getResources().getColor(2131296304));
        this.mRecipientsViewer = (TextView)this.findViewById(2131820825);
        this.mRecipientsViewer.setTextColor(AttributeResolver.resolveColor((Context)this, (int)16843601));
        this.mRecipientsViewerCount = (TextView)this.findViewById(2131820826);
        this.mRecipientsViewerCount.setTextColor(AttributeResolver.resolveColor((Context)this, (int)16843601));
        this.mSuggestionList = (ListView)this.findViewById(2131820818);
        this.mRecipientAdapter = new RecipientsAdapter((Context)this);
        this.mRecipientAdapter.registerDataSetObserver(new DataSetObserver(){

            /*
             * Enabled aggressive block sorting
             */
            public void onChanged() {
                if (NewMessageActivity.this.mRecipientAdapter.getCount() > 0 && NewMessageActivity.this.mRecipientEditor.getText().length() > 0) {
                    NewMessageActivity.this.mConfirmRecipientButton.setVisibility(0);
                    NewMessageActivity.this.mAddRecipientButton.setVisibility(8);
                } else {
                    NewMessageActivity.this.mConfirmRecipientButton.setVisibility(8);
                    NewMessageActivity.this.mAddRecipientButton.setVisibility(0);
                }
                NewMessageActivity.this.switchRecipientsRowView();
            }
        });
        this.mSuggestionList.setAdapter((ListAdapter)this.mRecipientAdapter);
        this.mSuggestionList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> cursor, View view, int n, long l) {
                cursor = (Cursor)NewMessageActivity.this.mRecipientAdapter.getItem(n);
                NewMessageActivity.this.mRecipientEditor.setText((CharSequence)"");
                if (NewMessageActivity.this.commitRecipient(cursor.getString(3))) {
                    NewMessageActivity.this.updateCommitedRecipients();
                }
            }
        });
        this.mSuggestionList.setOnScrollListener(new AbsListView.OnScrollListener(){

            public void onScroll(AbsListView absListView, int n, int n2, int n3) {
            }

            public void onScrollStateChanged(AbsListView absListView, int n) {
                if (n == 2) {
                    Contact.pauseCaching();
                    return;
                }
                Contact.resumeCaching();
            }
        });
        this.mSuggestionList.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View view, MotionEvent motionEvent) {
                NewMessageActivity.this.postHideSoftKeyboard();
                return false;
            }
        });
        this.mRecentContactGrid = (StaticGridView)this.findViewById(2131820819);
        if (MSimUtils.isMSimInserted()) {
            this.updateSlotButtonStateBySlotId((Context)this, this.mUseSlotId);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public boolean isPreparedForSending() {
        int n = 1;
        if (this.mAirModeOn) {
            return false;
        }
        if (this.mWorkingMessage == null) return false;
        if (!this.mWorkingMessage.hasAttachment()) {
            if (!this.mWorkingMessage.hasText()) return false;
        }
        int n2 = 1;
        if (n2 == 0) return false;
        n2 = this.recipientCount();
        if (n2 <= 0) return false;
        if (n2 > MmsConfig.getRecipientLimit()) return false;
        n2 = n;
        if (n2 == 0) return false;
        return MSimUtils.isMSimSlotIdValid(this.mUseSlotId);
    }

    @Override
    protected void loadDraft() {
        if (!this.mExtraCallPrivateRecipientConv) {
            super.loadDraft();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        switch (n) {
            default: {
                return;
            }
            case 0: {
                if (intent == null) return;
                this.processPickResult(intent);
                return;
            }
        }
    }

    @Override
    protected void onChildSimInfoChanged() {
        if (MSimUtils.isMSimInserted()) {
            this.updateSlotButtonStateBySlotId((Context)this, this.mUseSlotId);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default: {
                super.onClick(view);
                return;
            }
            case 2131820828: {
                this.launchMultiplePhonePicker();
                return;
            }
            case 2131820829: 
        }
        this.commitEditingRecipient();
    }

    @Override
    protected void onContactStatusUpdate(final String string2) {
        super.onContactStatusUpdate(string2);
        this.runOnUiThread(new Runnable(){

            @Override
            public void run() {
                NewMessageActivity.this.updateRecipientBackground(string2);
            }
        });
    }

    @Override
    protected void onContactsUpdated(ContactList contactList) {
        super.onContactsUpdated(contactList);
        this.updateRecipientsViewerText();
        this.updateRecipientRows();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onCreate(Bundle object) {
        Object object2;
        super.onCreate((Bundle)object);
        Object object3 = this.getIntent();
        long l = object3.getLongExtra("thread_id", -1);
        boolean bl = object3.getBooleanExtra("is_from_blocked", false);
        object = object3.getStringExtra("reply_address");
        this.mExtraCallPrivateRecipientConv = object3.getBooleanExtra("private_recipient", false);
        if (l == -1) {
            object2 = object3.getData();
            if (object2 == null) {
                if (!TextUtils.isEmpty((CharSequence)(object3 = object3.getStringExtra("address")))) {
                    this.mConversation = Conversation.createNew((Context)this, ContactList.getByNumbers((String)object3, true));
                } else {
                    LogTag.verbose("Creating an empty conversation", new Object[0]);
                    this.mConversation = Conversation.createNew((Context)this);
                }
            } else {
                LogTag.verbose("Creating a conversation by uri", new Object[0]);
                this.mConversation = Conversation.createNew((Context)this, (Uri)object2);
            }
        } else {
            LogTag.verbose("Creating a conversation by threadId %d", l);
            this.mConversation = Conversation.createNew((Context)this, l);
        }
        this.initRecipientsEditor();
        this.initialize();
        if (bl) {
            this.mConversation.setRecipients(ContactList.getByNumbers((String)object, false));
        }
        object = this.mConversation.getRecipients();
        object3 = object.iterator();
        while (object3.hasNext()) {
            object2 = (Contact)object3.next();
            this.addRecipient((Contact)object2, object2.getNumber());
        }
        this.updateSendButtonState();
        this.syncNumbersToWorkingMessage();
        this.updateRecipientsViewerText();
        if (object.size() > 0) {
            this.mRecipientsViewerHead.setVisibility(8);
        } else {
            this.mRecipientsViewerHead.setVisibility(0);
        }
        object3 = this.getResources();
        this.mRecipientRowPadding = object3.getDimensionPixelSize(2131427346);
        this.mTitleViewHeightOneRow = object3.getDimensionPixelSize(2131427368);
        this.mTitleViewHeightTwoRow = object3.getDimensionPixelSize(2131427369);
        this.mTitleViewHeightThreeRow = object3.getDimensionPixelSize(2131427370);
        if (!object.isEmpty()) {
            this.mTextEditor.requestFocus();
            return;
        }
        this.mRecipientEditor.requestFocus();
    }

    @Override
    protected void onDestroy() {
        if (this.mGuidePopupWindow != null) {
            this.mGuidePopupWindow.dismiss();
        }
        this.mGuidePopupWindow = null;
        this.mRecipientAdapter.changeCursor(null);
        this.mRecipientAdapter.stop();
        super.onDestroy();
    }

    @Override
    public void onMessageSent() {
        super.onMessageSent();
        if (this.getWindow().isDestroyed()) {
            return;
        }
        this.runOnUiThread(new Runnable(){

            @Override
            public void run() {
                Conversation conversation = Conversation.get(NewMessageActivity.this.mConversation.getThreadId());
                conversation.update();
                if (!conversation.isPrivate() || conversation.isPrivate() && NewMessageActivity.this.mOrgMsgIsPrivate) {
                    conversation = ComposeMessageRouterActivity.createIntent((Context)NewMessageActivity.this, NewMessageActivity.this.mConversation.getThreadId());
                    conversation.putExtra("new_message_count", 1);
                    conversation.putExtra("was_soft_keyboard_on", NewMessageActivity.this.mIsSoftInputEnabled);
                    conversation.addFlags(65536);
                    ComposeMessageRouterActivity.route((Context)NewMessageActivity.this, (Intent)conversation);
                }
                NewMessageActivity.this.finish();
                NewMessageActivity.this.overridePendingTransition(17432576, 17432577);
            }
        });
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.getWindow().setWindowAnimations(0);
        intent.addFlags(65536);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public void onPostLayout() {
        if (this.mAdjustEditorHeight) {
            return;
        }
        Drawable drawable2 = this.getResources().getDrawable(2130837929);
        if (drawable2 != null) {
            Rect rect = new Rect();
            drawable2.getPadding(rect);
            ViewGroup.LayoutParams layoutParams = this.mRecipientEditor.getLayoutParams();
            int n = drawable2.getIntrinsicHeight();
            int n2 = this.mRecipientEditor.getMeasuredHeight();
            int n3 = rect.bottom;
            layoutParams.height = Math.max((int)n, (int)(rect.top + (n2 + n3)));
        }
        this.mAdjustEditorHeight = true;
    }

    @Override
    public void onPreMeasure(SizeAwareLinearLayout sizeAwareLinearLayout, int n, int n2) {
        this.drawRecipientContactsGrid(n);
        this.mDrawContactPanelRunnable.run();
        super.onPreMeasure(sizeAwareLinearLayout, n, n2);
    }

    @Override
    protected void onPushStatusChanged() {
        super.onPushStatusChanged();
        this.postUpdateRecipientsBackground(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.initRecentList();
        Contact.resumeCaching();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.cancelUpdateCommitedRecipients();
        Contact.pauseCaching();
        if (this.mPickContactsTask != null) {
            this.mPickContactsTask.cancel(true);
        }
        this.mPickContactsTask = null;
        if (this.mPickContactsProgressDialog != null && this.mPickContactsProgressDialog.isShowing()) {
            this.mPickContactsProgressDialog.dismiss();
        }
        this.mPickContactsProgressDialog = null;
    }

    public void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
    }

    @Override
    protected void postExit() {
        super.postExit();
        int n = this.getIntent().getIntExtra("exit_animation_in", -1);
        int n2 = this.getIntent().getIntExtra("exit_animation_out", -1);
        if (n != -1 && n2 != -1) {
            this.overridePendingTransition(n, n2);
        }
    }

    protected int recipientCount() {
        if (this.hasUncommitedRecipient()) {
            return this.mCommitedRecipients.size() + 1;
        }
        return this.mCommitedRecipients.size();
    }

    @Override
    protected void saveDraft(boolean bl) {
        this.commitEditingRecipient();
        this.writeContactNumbers();
        super.saveDraft(bl);
    }

    @Override
    public void sendMessage(int n) {
        this.commitEditingRecipient();
        if (this.hasInvalidRecipient()) {
            if (this.hasValidRecipient()) {
                String string2 = this.getString(2131361859, new Object[]{this.formatInvalidNumbers()});
                new AlertDialog.Builder((Context)this).setIconAttribute(16843605).setTitle((CharSequence)string2).setMessage(2131361864).setPositiveButton(2131361896, (DialogInterface.OnClickListener)new SendIgnoreInvalidRecipientListener(n)).setNegativeButton(2131361892, (DialogInterface.OnClickListener)new CancelSendingListener()).show();
                return;
            }
            new AlertDialog.Builder((Context)this).setIconAttribute(16843605).setTitle(2131361865).setMessage(2131361866).setPositiveButton(2131361891, (DialogInterface.OnClickListener)new CancelSendingListener()).show();
            return;
        }
        this.writeContactNumbers();
        this.checkAndSendMessage(true, n);
    }

    @Override
    public void startNicknamePicker(boolean bl) {
        this.commitEditingRecipient();
        super.startNicknamePicker(bl);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void updateRecipientBackground(String string2) {
        int n = this.mRecipientRows.getChildCount();
        int n2 = 0;
        while (n2 < n) {
            View view = this.mRecipientRows.getChildAt(n2);
            if (view instanceof TextView && string2.equals(view.getTag())) {
                view = (TextView)view;
                if (this.allowMx(string2)) {
                    view.setTextColor(this.getResources().getColor(2131296361));
                    view.setBackgroundResource(2130837872);
                    return;
                }
                view.setTextColor(AttributeResolver.resolveColor((Context)this, (int)16843601));
                view.setBackgroundResource(2130837928);
                return;
            }
            ++n2;
        }
    }

    @Override
    protected boolean willDiscardDraft() {
        if (!this.hasValidRecipient()) {
            return true;
        }
        return false;
    }

    private class CancelSendingListener
    implements DialogInterface.OnClickListener {
        private CancelSendingListener() {
        }

        public void onClick(DialogInterface dialogInterface, int n) {
            dialogInterface.dismiss();
        }
    }

    private class SendIgnoreInvalidRecipientListener
    implements DialogInterface.OnClickListener {
        private final int mSlotId;

        public SendIgnoreInvalidRecipientListener(int n) {
            this.mSlotId = n;
        }

        public void onClick(DialogInterface dialogInterface, int n) {
            NewMessageActivity.this.checkAndSendMessage(true, this.mSlotId);
            dialogInterface.dismiss();
        }
    }

}

