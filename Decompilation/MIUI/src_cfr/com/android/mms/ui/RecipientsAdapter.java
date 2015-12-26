/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$CommonDataKinds
 *  android.provider.ContactsContract$CommonDataKinds$Phone
 *  android.text.Annotation
 *  android.text.SpannableString
 *  android.text.TextUtils
 *  android.util.Log
 *  android.view.View
 *  android.widget.ResourceCursorAdapter
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  miui.telephony.PhoneNumberUtils
 *  miui.telephony.PhoneNumberUtils$TelocationQueryListener
 */
package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.android.mms.data.Contact;
import miui.telephony.PhoneNumberUtils;

public class RecipientsAdapter
extends ResourceCursorAdapter
implements PhoneNumberUtils.TelocationQueryListener {
    private static final String[] PROJECTION_PHONE = new String[]{"_id", "contact_id", "data2", "data1", "data3", "display_name", "data4", "photo_id"};
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private boolean mIsStoped = false;

    public RecipientsAdapter(Context context) {
        super(context, 2130968694, null, false);
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
    }

    public final void bindView(View view, Context object, Cursor cursor) {
        TextView textView = (TextView)view.findViewById(2131820846);
        textView.setText((CharSequence)cursor.getString(5));
        textView.setVisibility(0);
        textView = (TextView)view.findViewById(2131820854);
        String string2 = cursor.getString(3);
        textView.setText((CharSequence)string2);
        TextView textView2 = (TextView)view.findViewById(2131820855);
        int n = cursor.getInt(2);
        String string3 = cursor.getString(4);
        textView2.setText((CharSequence)ContactsContract.CommonDataKinds.Phone.getTypeLabel((Resources)object.getResources(), (int)n, (CharSequence)string3).toString());
        PhoneNumberUtils.queryTelocationStringAsync((int)101, (Object)textView, (Object)string2, (Object)((TextView)view.findViewById(2131820856)), (Object)null, (PhoneNumberUtils.TelocationQueryListener)this, (Context)this.mContext, (String)string2);
        view = view.findViewById(2131820797);
        view.setBackgroundResource(2130837917);
        object = cursor.getString(1);
        if (!cursor.isLast()) {
            cursor.moveToNext();
            if (object.equals((Object)cursor.getString(1))) {
                view.setBackgroundResource(2130837915);
            }
            cursor.moveToPrevious();
        }
    }

    public void changeCursor(Cursor cursor) {
        if (this.mIsStoped) {
            if (cursor != null) {
                cursor.close();
            }
            Log.v((String)"RecipientsAdapter", (String)"change cursor close for stop");
            return;
        }
        super.changeCursor(cursor);
    }

    /*
     * Enabled aggressive block sorting
     */
    public final CharSequence convertToString(Cursor cursor) {
        String string2 = cursor.getString(3);
        if (string2 == null) {
            return "";
        }
        String string3 = string2.trim();
        string2 = cursor.getString(5);
        int n = cursor.getInt(2);
        String string4 = cursor.getString(4);
        CharSequence charSequence = ContactsContract.CommonDataKinds.Phone.getDisplayLabel((Context)this.mContext, (int)n, (CharSequence)string4);
        string2 = string2 == null ? "" : string2.replace((CharSequence)", ", (CharSequence)" ").replace((CharSequence)",", (CharSequence)" ");
        SpannableString spannableString = new SpannableString((CharSequence)Contact.formatNameAndNumber(string2, string3, cursor.getString(6)));
        n = spannableString.length();
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            spannableString.setSpan((Object)new Annotation("name", string2), 0, n, 33);
        } else {
            spannableString.setSpan((Object)new Annotation("name", string3), 0, n, 33);
        }
        spannableString.setSpan((Object)new Annotation("person_id", cursor.getString(1)), 0, n, 33);
        spannableString.setSpan((Object)new Annotation("label", charSequence.toString()), 0, n, 33);
        spannableString.setSpan((Object)new Annotation("number", string3), 0, n, 33);
        return spannableString;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onComplete(Object object, Object object2, Object object3, Object object4, String string2) {
        if (!TextUtils.equals((CharSequence)((TextView)object).getText(), (CharSequence)((String)object2))) return;
        object = (TextView)object3;
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            object.setText((CharSequence)string2);
            return;
        }
        object.setText((CharSequence)"");
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String string2 = null;
        if (charSequence != null) {
            string2 = charSequence.toString();
        }
        charSequence = ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI.buildUpon().appendPath(string2).appendQueryParameter("type", "short_text").build();
        return this.mContentResolver.query((Uri)charSequence, PROJECTION_PHONE, null, null, null);
    }

    public void start() {
        this.mIsStoped = false;
    }

    public void stop() {
        this.mIsStoped = true;
    }
}

