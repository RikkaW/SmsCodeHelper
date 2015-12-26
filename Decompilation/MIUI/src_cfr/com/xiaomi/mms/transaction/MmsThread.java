/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.mms.data.Contact;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.MmsTabActivity;
import com.xiaomi.mms.transaction.MsgThread;

public class MmsThread
extends MsgThread {
    public MmsThread(long l, String string2) {
        super(l, string2);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) return false;
        if (this.getClass() != object.getClass()) {
            return false;
        }
        object = (MmsThread)object;
        if (this.mThreadId == object.mThreadId) return true;
        return false;
    }

    @Override
    public Intent getConversationClickIntent(Context context) {
        return ComposeMessageRouterActivity.createIntent(context, this.mThreadId);
    }

    @Override
    public Intent getCvListClickIntent(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClass(context, (Class)MmsTabActivity.class);
        intent.setType("vnd.android-dir/mms-sms");
        return intent;
    }

    @Override
    public String getPackageName() {
        return "com.android.mms";
    }

    @Override
    public Uri getPeoplePreferenceUri(String object) {
        if ((object = Contact.get((String)object).load(true, true)) != null) {
            return object.getPeopleReferenceUri();
        }
        return null;
    }

    public int hashCode() {
        return ("MmsThread" + this.mThreadId).hashCode();
    }
}

