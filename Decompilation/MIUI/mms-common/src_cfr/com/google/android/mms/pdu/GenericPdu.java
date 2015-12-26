/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.PduHeaders;

public class GenericPdu {
    PduHeaders mPduHeaders = null;

    public GenericPdu() {
        this.mPduHeaders = new PduHeaders();
    }

    GenericPdu(PduHeaders pduHeaders) {
        this.mPduHeaders = pduHeaders;
    }

    public EncodedStringValue getFrom() {
        return this.mPduHeaders.getEncodedStringValue(137);
    }

    public int getMessageType() {
        return this.mPduHeaders.getOctet(140);
    }

    public int getMmsVersion() {
        return this.mPduHeaders.getOctet(141);
    }

    PduHeaders getPduHeaders() {
        return this.mPduHeaders;
    }

    public void setFrom(EncodedStringValue encodedStringValue) {
        this.mPduHeaders.setEncodedStringValue(encodedStringValue, 137);
    }

    public void setMessageType(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 140);
    }

    public void setMmsVersion(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 141);
    }
}

