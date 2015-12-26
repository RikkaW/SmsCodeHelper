/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.mms.data.Contact;

public abstract class MsgThread {
    public String mAddress;
    public long mThreadId;

    public MsgThread(long l, String string2) {
        this.mThreadId = l;
        this.mAddress = string2;
    }

    public abstract Intent getConversationClickIntent(Context var1);

    public abstract Intent getCvListClickIntent(Context var1);

    public abstract String getPackageName();

    public abstract Uri getPeoplePreferenceUri(String var1);

    public String getSenderName(Context object, String string2) {
        object = Contact.get(string2).load(true, true).getName();
        if (object == null) {
            return "";
        }
        return object.replace('\n', ' ').replace('\r', ' ');
    }
}

