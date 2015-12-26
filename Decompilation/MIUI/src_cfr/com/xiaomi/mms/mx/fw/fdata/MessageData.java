/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.mx.fw.fdata;

public class MessageData {
    public String mBody;
    public String mExt;
    public String mFrom;
    public String mFromDeviceId;
    public String mFseq;
    public boolean mIsGlobal = false;
    public int mIsRead;
    public boolean mIsTemplate = false;
    public boolean mIsTransient = false;
    public String mPacketID;
    public long mReceivedTime;
    public long mSentTime;
    public String mSeq;
    public String mTo;
    private String mType;

    public MessageData(String string2, int n, long l, long l2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, boolean bl, boolean bl2, boolean bl3) {
        this.mPacketID = string2;
        this.mIsRead = n;
        this.mSentTime = l;
        this.mReceivedTime = l2;
        this.mFrom = string3;
        this.mType = string4;
        this.mExt = string5;
        this.mSeq = string6;
        this.mFseq = string7;
        this.mFromDeviceId = string8;
        this.mTo = string9;
        this.mBody = string10;
        this.mIsTransient = bl;
        this.mIsGlobal = bl2;
        this.mIsTemplate = bl3;
    }

    public int getType() {
        if (this.mType.equalsIgnoreCase("subscribe")) {
            return 1;
        }
        return 0;
    }
}

