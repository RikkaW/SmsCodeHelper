/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentUris
 *  android.content.Context
 *  android.net.Uri
 *  android.text.TextUtils
 *  com.google.android.mms.pdu.EncodedStringValue
 *  java.lang.String
 */
package com.android.mms.data;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.google.android.mms.pdu.EncodedStringValue;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.utils.Mx2PduPersister;

public class Mx2WorkingMessage
extends WorkingMessage {
    private Mx2MessageModel mMxMessage;

    public Mx2WorkingMessage(WorkingMessage.MessageStatusListener messageStatusListener) {
        super(messageStatusListener);
    }

    private static Uri createDraftMx2Message(Context context, Mx2MessageModel mx2MessageModel) {
        mx2MessageModel.setBoxId(3);
        return Mx2PduPersister.insertMxMessage(context, mx2MessageModel);
    }

    public static Mx2WorkingMessage createEmpty(WorkingMessage.MessageStatusListener messageStatusListener) {
        return new Mx2WorkingMessage(messageStatusListener);
    }

    public static Mx2WorkingMessage loadDraft(WorkingMessage.MessageStatusListener messageStatusListener, Conversation conversation) {
        Mx2WorkingMessage mx2WorkingMessage = new Mx2WorkingMessage(messageStatusListener);
        if (mx2WorkingMessage.loadFromConversation(conversation)) {
            return mx2WorkingMessage;
        }
        return Mx2WorkingMessage.createEmpty(messageStatusListener);
    }

    private static int updateDraftMx2Message(Context context, Uri uri, Mx2MessageModel mx2MessageModel) {
        return Mx2PduPersister.updateMxMessage(context, mx2MessageModel, ContentUris.parseId((Uri)uri));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void persistMxMessage() {
        EncodedStringValue[] arrencodedStringValue;
        long l;
        if (!TextUtils.isEmpty((CharSequence)this.mText)) {
            this.mMxMessage.setBody(this.mText.toString());
        }
        if (this.mMessageUri == null) {
            l = this.mConversation.getThreadId();
            this.mMxMessage.setThreadId(l);
            this.mMessageUri = Mx2WorkingMessage.createDraftMx2Message(this.mContext, this.mMxMessage);
        } else {
            Mx2WorkingMessage.updateDraftMx2Message(this.mContext, this.mMessageUri, this.mMxMessage);
        }
        if (this.mConversation != null && (l = ContentUris.parseId((Uri)this.mMessageUri)) > 0 && (arrencodedStringValue = this.mConversation.getRecipients()) != null && !arrencodedStringValue.isEmpty()) {
            arrencodedStringValue = EncodedStringValue.encodeStrings((String[])arrencodedStringValue.getNumbers());
            Mx2PduPersister.updateAddress(this.mContext, l, 151, arrencodedStringValue);
        }
        this.mHasMmsDraft = true;
    }

    @Override
    public void removeAttachment(boolean bl) {
        this.mMxMessage = null;
        this.updateState(32, false, bl);
        super.removeAttachment(bl);
    }

    public void setMessageUri(Uri uri) {
        this.mMessageUri = uri;
    }

    public void setMxMessage(Mx2MessageModel mx2MessageModel) {
        this.mMxMessage = mx2MessageModel;
    }
}

