/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.database.Cursor
 *  android.text.TextUtils
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.mms.data;

import android.database.Cursor;
import android.text.TextUtils;
import com.android.mms.data.Conversation;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import java.util.ArrayList;
import java.util.List;

public class Mx2MessageModel {
    private List<Attachment> mAttachments;
    private String mBody;
    private int mBoxId;
    public Conversation mConversation;
    private long mDate;
    private long mDateMsPart;
    private long mDateSent;
    private String mExpireTimestamp;
    private boolean mLocked;
    private long mMsgId;
    private String mMxExtension;
    private long mMxId;
    private String mMxType;
    private long mSimId;
    private long mThreadId;
    private int mType;

    public Mx2MessageModel() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public Mx2MessageModel(Cursor cursor) {
        boolean bl = true;
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() == 1) {
            this.mBody = cursor.getString(3);
            if (!TextUtils.isEmpty((CharSequence)this.mBody)) {
                this.mBody = new EncodedStringValue(MiuiPduPersister.getBytes((String)this.mBody)).getString();
            }
            this.mMsgId = cursor.getLong(0);
            this.mDate = cursor.getLong(6);
            this.mDateMsPart = cursor.getLong(7);
            this.mDateSent = cursor.getLong(8);
            this.mBoxId = cursor.getInt(1);
            this.mThreadId = cursor.getLong(2);
            this.mSimId = cursor.getLong(10);
            this.mType = cursor.getInt(5);
            this.mExpireTimestamp = cursor.getString(9);
            if (cursor.getInt(11) != 1) {
                bl = false;
            }
            this.mLocked = bl;
            this.mMxExtension = cursor.getString(13);
            this.mMxType = cursor.getString(12);
            this.mMxId = cursor.getLong(4);
            this.mAttachments = Mx2ExtentionHelper.praseAttachmentsExtentionString(this.mMxExtension);
        }
    }

    public boolean addAttachment(Attachment attachment) {
        if (this.mAttachments == null) {
            this.mAttachments = new ArrayList();
        }
        return this.mAttachments.add(attachment);
    }

    public String buildLocalExtension() {
        return Mx2ExtentionHelper.generateAttachmentsExtentionString(this.mAttachments, false);
    }

    public String buildXmppExtension() {
        return Mx2ExtentionHelper.generateAttachmentsExtentionString(this.mAttachments, true);
    }

    public Attachment getAttachment() {
        return this.getAttachment(0);
    }

    public Attachment getAttachment(int n) {
        if (this.mAttachments != null && this.mAttachments.size() > n) {
            return this.mAttachments.get(n);
        }
        return null;
    }

    public List<Attachment> getAttachments() {
        return this.mAttachments;
    }

    public String getBody() {
        return this.mBody;
    }

    public int getBoxId() {
        return this.mBoxId;
    }

    public long getDate() {
        return this.mDate;
    }

    public long getDateMsPart() {
        return this.mDateMsPart;
    }

    public long getDateSent() {
        return this.mDateSent;
    }

    public String getExpireTimestamp() {
        return this.mExpireTimestamp;
    }

    public long getMsgId() {
        return this.mMsgId;
    }

    public String getMxType() {
        return this.mMxType;
    }

    public long getSimId() {
        return this.mSimId;
    }

    public long getThreadId() {
        return this.mThreadId;
    }

    public int getType() {
        return this.mType;
    }

    public boolean hasAttachment() {
        if (this.mAttachments != null && this.mAttachments.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isLocked() {
        return this.mLocked;
    }

    public void setAttachments(List<Attachment> list) {
        this.mAttachments = list;
    }

    public void setBody(String string2) {
        this.mBody = string2;
    }

    public void setBoxId(int n) {
        this.mBoxId = n;
    }

    public void setDate(long l) {
        this.mDate = l;
    }

    public void setDateMsPart(long l) {
        this.mDateMsPart = l;
    }

    public void setDateSent(long l) {
        this.mDateSent = l;
    }

    public void setMxExtension(String string2) {
        this.mMxExtension = string2;
    }

    public void setMxType(String string2) {
        this.mMxType = string2;
    }

    public void setSimId(long l) {
        this.mSimId = l;
    }

    public void setThreadId(long l) {
        this.mThreadId = l;
    }

    public void setType(int n) {
        this.mType = n;
    }
}

