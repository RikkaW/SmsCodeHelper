/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.AsyncQueryHandler
 *  android.content.BroadcastReceiver
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnShowListener
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.OperationApplicationException
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.os.RemoteException
 *  android.preference.CheckBoxPreference
 *  android.preference.Preference
 *  android.preference.Preference$OnPreferenceChangeListener
 *  android.preference.PreferenceCategory
 *  android.preference.PreferenceScreen
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.security.ChooseLockSettingsHelper
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  android.widget.Toast
 *  com.google.android.collect.Lists
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  miui.R
 *  miui.R$drawable
 *  miui.app.AlertDialog
 *  miui.app.AlertDialog$Builder
 *  miui.app.ProgressDialog
 *  miui.preference.PreferenceActivity
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$PrivateAddresses
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.BroadcastReceiver;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.OperationApplicationException;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.security.ChooseLockSettingsHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.PrivateContactPreference;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import miui.R;
import miui.app.AlertDialog;
import miui.app.ProgressDialog;
import miui.preference.PreferenceActivity;
import miui.provider.ExtraTelephony;

public class PrivatePreferenceActivity
extends PreferenceActivity
implements Preference.OnPreferenceChangeListener,
Contact.UpdateListener {
    private Activity mActivity;
    private ChooseLockSettingsHelper mChooseLockSettingsHelper;
    private AsyncTask<Void, Void, ContentProviderResult[]> mDeleteTask;
    private ProgressDialog mExportingProgressDialog;
    private BroadcastReceiver mFinishReceiver;
    private ProgressDialog mImportingProgressDialog;
    private AsyncTask<Void, Void, ContentProviderResult[]> mInsertTask;
    private TextView mNumberEdit;
    PrivateContactPreference.OnClickDeleteBtnListener mOnClickDelBtnListener;
    private HashMap<Contact, PrivateContactPreference> mPreferenceMap;
    private PreferenceCategory mPrivateContactListPref;
    private CheckBoxPreference mPrivateNewNotificationPref;
    private CheckBoxPreference mPrivateProtectUsagePref;
    private CheckBoxPreference mPrivateProtectVisiblePref;
    private ContactListQueryHandler mQueryHandler;

    public PrivatePreferenceActivity() {
        this.mOnClickDelBtnListener = new PrivateContactPreference.OnClickDeleteBtnListener(){

            @Override
            public void onClick(long l, Contact contact) {
                PrivatePreferenceActivity.this.confirmExportPrivateContactDialog(new ExportPrivateContactListener(l, contact, PrivatePreferenceActivity.this.mQueryHandler, (Context)PrivatePreferenceActivity.this.mActivity), (Context)PrivatePreferenceActivity.this.mActivity);
            }
        };
        this.mFinishReceiver = new BroadcastReceiver(){

            /*
             * Enabled aggressive block sorting
             */
            public void onReceive(Context object, Intent intent) {
                object = intent.getAction();
                if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(object)) {
                    if (!"homekey".equals((Object)intent.getStringExtra("reason"))) return;
                    {
                        PrivatePreferenceActivity.this.finish();
                        return;
                    }
                } else {
                    if (!"android.intent.action.SCREEN_OFF".equals(object)) return;
                    {
                        PrivatePreferenceActivity.this.finish();
                        return;
                    }
                }
            }
        };
    }

    private void addManually() {
        View view = LayoutInflater.from((Context)this.mActivity).inflate(2130968690, null);
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this.mActivity);
        builder.setView(view).setTitle(2131362217);
        this.mNumberEdit = (TextView)view.findViewById(2131820847);
        builder.setOnShowListener(new DialogInterface.OnShowListener(){

            public void onShow(DialogInterface dialogInterface) {
                MessageUtils.requestInputMethod((Context)PrivatePreferenceActivity.this.mActivity, PrivatePreferenceActivity.this.mNumberEdit);
            }
        });
        builder.setPositiveButton(2131362032, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface object, int n) {
                object = PrivatePreferenceActivity.this.mNumberEdit.getText().toString();
                if (TextUtils.isEmpty((CharSequence)object)) {
                    Toast.makeText((Context)PrivatePreferenceActivity.this.mActivity, (int)2131362264, (int)0).show();
                    return;
                }
                HashSet hashSet = new HashSet();
                hashSet.add(object);
                PrivatePreferenceActivity.this.startAsyncInsertContact(PrivatePreferenceActivity.this.mQueryHandler, hashSet);
            }
        });
        builder.setNegativeButton(17039360, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void addNewPrivateContactDialog() {
        String string2 = this.mActivity.getResources().getString(2131362230);
        String string3 = this.mActivity.getResources().getString(2131362231);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface.dismiss();
                switch (n) {
                    default: {
                        return;
                    }
                    case 0: {
                        PrivatePreferenceActivity.this.addManually();
                        return;
                    }
                    case 1: 
                }
                PrivatePreferenceActivity.this.launchMultiplePhonePicker();
            }
        };
        new AlertDialog.Builder((Context)this.mActivity).setCancelable(true).setItems((CharSequence[])new String[]{string2, string3}, onClickListener).show();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean checkPrivateMessage(Context context, String string2) {
        block3 : {
            if (TextUtils.isEmpty((CharSequence)string2)) {
                return false;
            }
            if ((context = context.getContentResolver().query(ExtraTelephony.PrivateAddresses.CONTENT_URI, null, "address=?", new String[]{string2}, null)) == null) return false;
            try {
                int n = context.getCount();
                if (n <= 0) break block3;
            }
            catch (Throwable throwable) {
                context.close();
                throw throwable;
            }
            context.close();
            return true;
        }
        context.close();
        return false;
    }

    private void confirmExportPrivateContactDialog(ExportPrivateContactListener exportPrivateContactListener, Context context) {
        View view = View.inflate((Context)context, (int)2130968606, (ViewGroup)null);
        new AlertDialog.Builder(context).setTitle(2131362227).setCancelable(true).setPositiveButton(2131361891, (DialogInterface.OnClickListener)exportPrivateContactListener).setNegativeButton(2131361892, null).setView(view).show();
    }

    private void launchMultiplePhonePicker() {
        Intent intent = new Intent("com.android.contacts.action.GET_MULTIPLE_PHONES");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("vnd.android.cursor.dir/phone_v2");
        intent.putExtra("android.intent.extra.include_unknown_numbers", true);
        this.startActivityForResult(intent, 2);
    }

    private void processPickResult(Intent intent) {
        Object object = ContactList.blockingGetByUris(intent.getParcelableArrayExtra("com.android.contacts.extra.PHONE_URIS"));
        intent = new HashSet();
        object = object.iterator();
        while (object.hasNext()) {
            intent.add((Object)((Contact)object.next()).getNumber());
        }
        this.mImportingProgressDialog = ProgressDialog.show((Context)this.mActivity, (CharSequence)null, (CharSequence)this.mActivity.getString(2131362225), (boolean)true, (boolean)false);
        this.startAsyncInsertContact(this.mQueryHandler, (HashSet<String>)intent);
    }

    private void registerFinishReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.registerReceiver(this.mFinishReceiver, intentFilter);
    }

    private void startAsyncDeleteContact(AsyncQueryHandler asyncQueryHandler, final long l, final String string2) {
        this.mDeleteTask = new AsyncTask<Void, Void, ContentProviderResult[]>(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            protected /* varargs */ ContentProviderResult[] doInBackground(Void ... arrcontentProviderResult) {
                arrcontentProviderResult = Lists.newArrayList();
                arrcontentProviderResult.add((Object)ContentProviderOperation.newDelete((Uri)ContentUris.withAppendedId((Uri)ExtraTelephony.PrivateAddresses.CONTENT_URI, (long)l)).build());
                arrcontentProviderResult = PrivatePreferenceActivity.this.getContentResolver().applyBatch("mms-sms", (ArrayList)arrcontentProviderResult);
                if (arrcontentProviderResult == null) return arrcontentProviderResult;
                try {
                    PrivatePreferenceActivity.this.updateConversation((Context)PrivatePreferenceActivity.this, string2, false);
                }
                catch (RemoteException var1_2) {
                    var1_2.printStackTrace();
                    do {
                        return null;
                        break;
                    } while (true);
                }
                catch (OperationApplicationException var1_3) {
                    var1_3.printStackTrace();
                    return null;
                }
                return arrcontentProviderResult;
            }

            protected void onPostExecute(ContentProviderResult[] arrcontentProviderResult) {
                PrivatePreferenceActivity.this.mDeleteTask = null;
                if (arrcontentProviderResult != null) {
                    if (PrivatePreferenceActivity.this.mExportingProgressDialog != null) {
                        PrivatePreferenceActivity.this.mExportingProgressDialog.dismiss();
                        PrivatePreferenceActivity.this.mExportingProgressDialog = null;
                    }
                    PrivatePreferenceActivity.startAsyncQueryContact(PrivatePreferenceActivity.this.mQueryHandler);
                }
            }
        }.execute((Object[])new Void[0]);
    }

    private void startAsyncInsertContact(AsyncQueryHandler asyncQueryHandler, final HashSet<String> hashSet) {
        this.mInsertTask = new AsyncTask<Void, Void, ContentProviderResult[]>(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            protected /* varargs */ ContentProviderResult[] doInBackground(Void ... arrcontentProviderResult) {
                Iterator iterator;
                arrcontentProviderResult = Lists.newArrayList();
                ContentProviderResult[] arrcontentProviderResult2 = new ContentProviderResult[](1);
                for (String string2 : hashSet) {
                    if (TextUtils.isEmpty((CharSequence)string2)) continue;
                    arrcontentProviderResult2.put("address", string2);
                    arrcontentProviderResult.add((Object)ContentProviderOperation.newInsert((Uri)ExtraTelephony.PrivateAddresses.CONTENT_URI).withValues((ContentValues)arrcontentProviderResult2).build());
                }
                arrcontentProviderResult = arrcontentProviderResult2 = PrivatePreferenceActivity.this.getContentResolver().applyBatch("mms-sms", (ArrayList)arrcontentProviderResult);
                if (arrcontentProviderResult2 == null) return arrcontentProviderResult;
                try {
                    iterator = hashSet.iterator();
                }
                catch (RemoteException var1_2) {
                    var1_2.printStackTrace();
                    do {
                        return null;
                        break;
                    } while (true);
                }
                catch (OperationApplicationException var1_3) {
                    var1_3.printStackTrace();
                    return null;
                }
                do {
                    arrcontentProviderResult = arrcontentProviderResult2;
                    if (!iterator.hasNext()) return arrcontentProviderResult;
                    arrcontentProviderResult = (String)iterator.next();
                    PrivatePreferenceActivity.this.updateConversation((Context)PrivatePreferenceActivity.this, (String)arrcontentProviderResult, true);
                    continue;
                    break;
                } while (true);
            }

            protected void onPostExecute(ContentProviderResult[] arrcontentProviderResult) {
                PrivatePreferenceActivity.this.mInsertTask = null;
                if (arrcontentProviderResult != null) {
                    if (PrivatePreferenceActivity.this.mImportingProgressDialog != null) {
                        PrivatePreferenceActivity.this.mImportingProgressDialog.dismiss();
                        PrivatePreferenceActivity.this.mImportingProgressDialog = null;
                    }
                    PrivatePreferenceActivity.startAsyncQueryContact(PrivatePreferenceActivity.this.mQueryHandler);
                }
            }
        }.execute((Object[])new Void[0]);
    }

    private static void startAsyncQueryContact(AsyncQueryHandler asyncQueryHandler) {
        asyncQueryHandler.cancelOperation(1701);
        asyncQueryHandler.startQuery(1701, (Object)null, ExtraTelephony.PrivateAddresses.CONTENT_URI, null, null, null, null);
    }

    private void unRegisterFinishReceiver() {
        this.unregisterReceiver(this.mFinishReceiver);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateCacheAndUI(Cursor cursor) {
        cursor.moveToPosition(-1);
        this.mPreferenceMap.clear();
        this.mPrivateContactListPref.removeAll();
        while (cursor.moveToNext()) {
            long l = cursor.getLong(0);
            Contact contact = Contact.get(cursor.getString(1));
            PrivateContactPreference privateContactPreference = new PrivateContactPreference((Context)this.mActivity);
            privateContactPreference.setShowInfo(l, contact);
            privateContactPreference.setOnDeleteBtnClickListener(this.mOnClickDelBtnListener);
            this.mPreferenceMap.put((Object)contact, (Object)privateContactPreference);
            this.mPrivateContactListPref.addPreference((Preference)privateContactPreference);
        }
        if (this.mPrivateContactListPref.getPreferenceCount() == 0) {
            this.getPreferenceScreen().removePreference((Preference)this.mPrivateContactListPref);
        } else {
            this.getPreferenceScreen().addPreference((Preference)this.mPrivateContactListPref);
        }
        Contact.addListener(this);
        Contact.invalidatePhotoCache();
        Contact.asyncloadAllInBackground();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateConversation(Context object, String string2, boolean bl) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            Log.d((String)"PrivatePreferenceActivity", (String)"updateConversation address is null");
            return;
        } else {
            if ((object = Conversation.getFromCache((Context)object, ContactList.getByNumbers(string2, false))) == null) {
                Log.d((String)"PrivatePreferenceActivity", (String)"updateConversation conv is null");
                return;
            }
            if (bl && !object.isPrivate()) {
                object.setPrivate(true);
                return;
            }
            if (bl || !object.isPrivate()) return;
            {
                object.setPrivate(false);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 0) {
            if (n2 != -1) {
                this.mPrivateProtectUsagePref.setChecked(false);
                return;
            }
            this.mPrivateProtectUsagePref.setChecked(true);
            return;
        } else {
            if (n == 1) {
                if (n2 == -1) {
                    this.mPrivateProtectUsagePref.setChecked(false);
                    return;
                }
                this.mPrivateProtectUsagePref.setChecked(true);
                return;
            }
            if (n != 2 || intent == null) return;
            {
                this.processPickResult(intent);
                return;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.addPreferencesFromResource(2131099655);
        this.mActivity = this;
        this.mQueryHandler = new ContactListQueryHandler(this.mActivity.getContentResolver());
        this.mPreferenceMap = new HashMap();
        this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper((Activity)this, 1);
        this.mPrivateNewNotificationPref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_private_new_notification");
        this.mPrivateNewNotificationPref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        this.mPrivateProtectUsagePref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_private_protect_usage");
        this.mPrivateProtectUsagePref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        this.mPrivateProtectVisiblePref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_private_protect_visible");
        this.mPrivateProtectVisiblePref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        this.mPrivateContactListPref = (PreferenceCategory)this.findPreference((CharSequence)"pref_key_private_contact_list");
        this.registerFinishReceiver();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, 2131362217).setIcon(R.drawable.action_button_new_light).setShowAsAction(1);
        return true;
    }

    protected void onDestroy() {
        this.unRegisterFinishReceiver();
        this.mQueryHandler.cancelOperation(1701);
        if (this.mInsertTask != null) {
            this.mInsertTask.cancel(true);
            this.mInsertTask = null;
        }
        if (this.mDeleteTask != null) {
            this.mDeleteTask.cancel(true);
            this.mDeleteTask = null;
        }
        if (this.mImportingProgressDialog != null) {
            this.mImportingProgressDialog.dismiss();
            this.mImportingProgressDialog = null;
        }
        if (this.mExportingProgressDialog != null) {
            this.mExportingProgressDialog.dismiss();
            this.mExportingProgressDialog = null;
        }
        super.onDestroy();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 0: {
                this.addNewPrivateContactDialog();
                do {
                    return true;
                    break;
                } while (true);
            }
            case 16908332: 
        }
        this.finish();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onPreferenceChange(Preference preference, Object object) {
        int n = 0;
        int n2 = 0;
        if (preference == this.mPrivateNewNotificationPref) {
            boolean bl = (Boolean)object;
            preference = this.getContentResolver();
            if (bl) {
                n2 = 1;
            }
            Settings.System.putInt((ContentResolver)preference, (String)"pref_key_enable_private_notification", (int)n2);
            return true;
        } else if (preference == this.mPrivateProtectUsagePref) {
            if (((Boolean)object).booleanValue()) {
                preference = new Intent();
                preference.setClassName("com.android.settings", "com.android.settings.ChooseSmsLockPattern");
                preference.setAction("android.app.action.SET_SMS_NEW_PASSWORD");
                this.startActivityForResult((Intent)preference, 0);
                return true;
            }
            if (!this.mChooseLockSettingsHelper.isPrivateSmsEnabled()) return true;
            {
                preference = new Intent();
                preference.setClassName("com.android.settings", "com.android.settings.ConfirmSmsLockPattern");
                preference.setAction("android.app.action.CONFIRM_SMS_PASSWORD");
                preference.putExtra("confirm_purpose", 1);
                this.startActivityForResult((Intent)preference, 1);
                return true;
            }
        } else {
            if (preference != this.mPrivateProtectVisiblePref) return true;
            {
                boolean bl = (Boolean)object;
                preference = this.getContentResolver();
                n2 = n;
                if (bl) {
                    n2 = 1;
                }
                Settings.System.putInt((ContentResolver)preference, (String)"private_sms_lock_pattern_visible_pattern", (int)n2);
                return true;
            }
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    protected void onResume() {
        super.onResume();
        this.mPrivateNewNotificationPref.setChecked(MessageUtils.getPrefPrivateNotificationEnabled((Context)this));
        this.mPrivateProtectUsagePref.setChecked(this.mChooseLockSettingsHelper.isPrivateSmsEnabled());
        this.mPrivateProtectVisiblePref.setChecked(MessageUtils.getPrefPrivateLockPatternVisible((Context)this));
    }

    protected void onStart() {
        super.onStart();
        PrivatePreferenceActivity.startAsyncQueryContact(this.mQueryHandler);
    }

    @Override
    public void onUpdate(Contact object) {
        if ((object = (PrivateContactPreference)((Object)this.mPreferenceMap.get(object))) != null) {
            object.refreshShowInfo();
        }
    }

    private final class ContactListQueryHandler
    extends AsyncQueryHandler {
        public ContactListQueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        protected void onQueryComplete(int n, Object object, Cursor cursor) {
            switch (n) {
                default: {
                    return;
                }
                case 1701: 
            }
            if (cursor == null) return;
            try {
                PrivatePreferenceActivity.this.updateCacheAndUI(cursor);
                return;
            }
            finally {
                cursor.close();
            }
        }
    }

    private class ExportPrivateContactListener
    implements DialogInterface.OnClickListener {
        private final Contact mContact;
        private final Context mContext;
        private final AsyncQueryHandler mHandler;
        private final long mItemId;

        public ExportPrivateContactListener(long l, Contact contact, AsyncQueryHandler asyncQueryHandler, Context context) {
            this.mItemId = l;
            this.mContact = contact;
            this.mContext = context;
            this.mHandler = asyncQueryHandler;
        }

        public void onClick(DialogInterface dialogInterface, int n) {
            PrivatePreferenceActivity.this.mExportingProgressDialog = ProgressDialog.show((Context)this.mContext, (CharSequence)null, (CharSequence)this.mContext.getString(2131362226), (boolean)true, (boolean)false);
            PrivatePreferenceActivity.this.startAsyncDeleteContact(this.mHandler, this.mItemId, this.mContact.getNumber());
        }
    }

}

