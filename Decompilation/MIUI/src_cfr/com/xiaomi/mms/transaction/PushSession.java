/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.mms.util.MSimUtils;

public class PushSession {
    private static PushSession mInstance;
    private final PushAccountInfo[] mPushAccountInfos = new PushAccountInfo[MSimUtils.getMultiSimCount()];

    private PushSession(Context context) {
        for (int i = 0; i < this.mPushAccountInfos.length; ++i) {
            this.mPushAccountInfos[i] = new PushAccountInfo();
        }
    }

    public static PushSession getInstance(Context object) {
        synchronized (PushSession.class) {
            if (mInstance == null) {
                mInstance = new PushSession((Context)object);
            }
            object = mInstance;
            return object;
        }
    }

    private void notifyChannelChanged(Context context, int n) {
        Intent intent = new Intent("com.xiaomi.mms.PUSH_STATUS_CHANGED");
        intent.setPackage(context.getPackageName());
        intent.putExtra("extra_sim_index", n);
        context.sendBroadcast(intent);
    }

    private void resetPushAccountInfo(int n) {
        this.mPushAccountInfos[n].myMid = null;
        this.mPushAccountInfos[n].myFullMid = null;
        this.mPushAccountInfos[n].myPhone = null;
        this.mPushAccountInfos[n].status = Status.DISCONNECTED;
    }

    private void setMyFullMid(int n, String string2, String string3) {
        this.mPushAccountInfos[n].myMid = string2;
        this.mPushAccountInfos[n].myFullMid = string2 + "@xiaomi.com" + "/" + string3;
    }

    private void setMyPhone(int n, String string2) {
        this.mPushAccountInfos[n].myPhone = string2;
    }

    public int getConnectedSimIndex() {
        for (int i = 0; i < this.mPushAccountInfos.length; ++i) {
            if (!this.isConnected(i)) continue;
            return i;
        }
        return -1;
    }

    public String getMyFullMid(int n) {
        return this.mPushAccountInfos[n].myFullMid;
    }

    public String getMyMid(int n) {
        if (MSimUtils.isMSimSlotIdValid(n)) {
            return this.mPushAccountInfos[n].myMid;
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int getSimIdByMid(String string2) {
        if (string2 == null) return -1;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return -1;
        }
        int n = 0;
        while (n < this.mPushAccountInfos.length) {
            int n2 = n++;
            if (string2.equals((Object)this.mPushAccountInfos[n].myMid)) return n2;
        }
        return -1;
    }

    public Status getStatus(int n) {
        return this.mPushAccountInfos[n].status;
    }

    public boolean isConnected(int n) {
        PushAccountInfo pushAccountInfo = this.mPushAccountInfos[n];
        if (pushAccountInfo.status == Status.CONNECTED && pushAccountInfo.myFullMid != null && pushAccountInfo.myMid != null && pushAccountInfo.myPhone != null) {
            return true;
        }
        return false;
    }

    void resetAllStatus(Context arrpushAccountInfo, Status status) {
        arrpushAccountInfo = this.mPushAccountInfos;
        int n = arrpushAccountInfo.length;
        for (int i = 0; i < n; ++i) {
            arrpushAccountInfo[i].status = status;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    void setMyFullMidAndPhone(int n, String string2, String string3, String string4) {
        for (int i = 0; i < this.mPushAccountInfos.length; ++i) {
            if (!MSimUtils.isSimInserted(i)) {
                this.resetPushAccountInfo(i);
                continue;
            }
            if (i == n || !TextUtils.equals((CharSequence)string2, (CharSequence)this.mPushAccountInfos[i].myMid) && !TextUtils.equals((CharSequence)string4, (CharSequence)this.mPushAccountInfos[i].myPhone)) continue;
            this.resetPushAccountInfo(i);
        }
        if (MSimUtils.isSimInserted(n)) {
            this.setMyFullMid(n, string2, string3);
            this.setMyPhone(n, string4);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    void setStatus(Context context, int n, Status status) {
        boolean bl = this.mPushAccountInfos[n].status != status;
        this.mPushAccountInfos[n].status = status;
        if (bl) {
            this.notifyChannelChanged(context, n);
        }
    }

    private static class PushAccountInfo {
        public volatile String myFullMid;
        public volatile String myMid;
        public volatile String myPhone;
        public volatile Status status = Status.DISCONNECTED;

        private PushAccountInfo() {
        }
    }

    public static enum Status {
        CONNECTED,
        DISCONNECTED;
        

        private Status() {
        }
    }

}

