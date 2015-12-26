/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.mx.fw.fdata;

import android.text.TextUtils;
import com.xiaomi.mms.mx.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class HmsConversationInfo {
    private long mMaxSeq = 0;
    private boolean mNotifyDisabled = false;
    private long mReadSeq = 0;
    private String mVerifyInfo = "";

    public HmsConversationInfo() {
    }

    public HmsConversationInfo(String string2) {
        this.updateWithJSON(string2);
    }

    public long getReadSeq() {
        return this.mReadSeq;
    }

    public void setReadSeq(long l) {
        this.mReadSeq = l;
    }

    public String toJson() {
        try {
            Object object = new JSONObject();
            object.put("maxSeq", this.mMaxSeq);
            object.put("readSeq", this.mReadSeq);
            object.put("verifyInfo", (Object)this.mVerifyInfo);
            object.put("notifyDisabled", this.mNotifyDisabled);
            object = object.toString();
            return object;
        }
        catch (JSONException var1_2) {
            Log.e("HmsDetailInfo", var1_2.toString());
            return "";
        }
    }

    public void updateWithJSON(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            try {
                string2 = new JSONObject(string2);
                this.mMaxSeq = string2.optLong("maxSeq");
                this.mReadSeq = string2.optLong("readSeq");
                this.mVerifyInfo = string2.optString("verifyInfo");
                this.mNotifyDisabled = string2.optBoolean("notifyDisabled");
                return;
            }
            catch (JSONException var1_2) {
                Log.e("HmsDetailInfo", var1_2.toString());
                return;
            }
        }
        this.mMaxSeq = 0;
        this.mReadSeq = 0;
        this.mNotifyDisabled = false;
        this.mVerifyInfo = "";
    }
}

