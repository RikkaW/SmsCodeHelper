/*
 * Decompiled with CFR 0_110.
 */
package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.PduHeaders;

public class ReadOrigInd
extends GenericPdu {
    public ReadOrigInd() throws InvalidHeaderValueException {
        this.setMessageType(136);
    }

    ReadOrigInd(PduHeaders pduHeaders) {
        super(pduHeaders);
    }

    public long getDate() {
        return this.mPduHeaders.getLongInteger(133);
    }

    @Override
    public EncodedStringValue getFrom() {
        return this.mPduHeaders.getEncodedStringValue(137);
    }

    public byte[] getMessageId() {
        return this.mPduHeaders.getTextString(139);
    }

    public int getReadStatus() {
        return this.mPduHeaders.getOctet(155);
    }

    public EncodedStringValue[] getTo() {
        return this.mPduHeaders.getEncodedStringValues(151);
    }

    public void setDate(long l) {
        this.mPduHeaders.setLongInteger(l, 133);
    }

    @Override
    public void setFrom(EncodedStringValue encodedStringValue) {
        this.mPduHeaders.setEncodedStringValue(encodedStringValue, 137);
    }

    public void setMessageId(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 139);
    }

    public void setReadStatus(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 155);
    }

    public void setTo(EncodedStringValue[] arrencodedStringValue) {
        this.mPduHeaders.setEncodedStringValues(arrencodedStringValue, 151);
    }
}

