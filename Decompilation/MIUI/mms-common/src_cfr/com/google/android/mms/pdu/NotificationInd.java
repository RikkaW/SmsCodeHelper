/*
 * Decompiled with CFR 0_110.
 */
package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.PduHeaders;

public class NotificationInd
extends GenericPdu {
    public NotificationInd() throws InvalidHeaderValueException {
        this.setMessageType(130);
    }

    NotificationInd(PduHeaders pduHeaders) {
        super(pduHeaders);
    }

    public int getContentClass() {
        return this.mPduHeaders.getOctet(186);
    }

    public byte[] getContentLocation() {
        return this.mPduHeaders.getTextString(131);
    }

    public int getDeliveryReport() {
        return this.mPduHeaders.getOctet(134);
    }

    public long getExpiry() {
        return this.mPduHeaders.getLongInteger(136);
    }

    @Override
    public EncodedStringValue getFrom() {
        return this.mPduHeaders.getEncodedStringValue(137);
    }

    public byte[] getMessageClass() {
        return this.mPduHeaders.getTextString(138);
    }

    public long getMessageSize() {
        return this.mPduHeaders.getLongInteger(142);
    }

    public EncodedStringValue getSubject() {
        return this.mPduHeaders.getEncodedStringValue(150);
    }

    public byte[] getTransactionId() {
        return this.mPduHeaders.getTextString(152);
    }

    public void setContentClass(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 186);
    }

    public void setContentLocation(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 131);
    }

    public void setDeliveryReport(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 134);
    }

    public void setExpiry(long l) {
        this.mPduHeaders.setLongInteger(l, 136);
    }

    @Override
    public void setFrom(EncodedStringValue encodedStringValue) {
        this.mPduHeaders.setEncodedStringValue(encodedStringValue, 137);
    }

    public void setMessageClass(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 138);
    }

    public void setMessageSize(long l) {
        this.mPduHeaders.setLongInteger(l, 142);
    }

    public void setSubject(EncodedStringValue encodedStringValue) {
        this.mPduHeaders.setEncodedStringValue(encodedStringValue, 150);
    }

    public void setTransactionId(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 152);
    }
}

