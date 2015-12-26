/*
 * Decompiled with CFR 0_110.
 */
package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.PduHeaders;

public class ReadRecInd
extends GenericPdu {
    public ReadRecInd(EncodedStringValue encodedStringValue, byte[] arrby, int n, int n2, EncodedStringValue[] arrencodedStringValue) throws InvalidHeaderValueException {
        this.setMessageType(135);
        this.setFrom(encodedStringValue);
        this.setMessageId(arrby);
        this.setMmsVersion(n);
        this.setTo(arrencodedStringValue);
        this.setReadStatus(n2);
    }

    ReadRecInd(PduHeaders pduHeaders) {
        super(pduHeaders);
    }

    public long getDate() {
        return this.mPduHeaders.getLongInteger(133);
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

