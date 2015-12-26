/*
 * Decompiled with CFR 0_110.
 */
package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.PduHeaders;

public class SendConf
extends GenericPdu {
    public SendConf() throws InvalidHeaderValueException {
        this.setMessageType(129);
    }

    SendConf(PduHeaders pduHeaders) {
        super(pduHeaders);
    }

    public byte[] getMessageId() {
        return this.mPduHeaders.getTextString(139);
    }

    public int getResponseStatus() {
        return this.mPduHeaders.getOctet(146);
    }

    public byte[] getTransactionId() {
        return this.mPduHeaders.getTextString(152);
    }

    public void setMessageId(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 139);
    }

    public void setResponseStatus(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 146);
    }

    public void setTransactionId(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 152);
    }
}

