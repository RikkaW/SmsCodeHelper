/*
 * Decompiled with CFR 0_110.
 */
package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MultimediaMessagePdu;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduHeaders;

public class RetrieveConf
extends MultimediaMessagePdu {
    public RetrieveConf() throws InvalidHeaderValueException {
        this.setMessageType(132);
    }

    RetrieveConf(PduHeaders pduHeaders) {
        super(pduHeaders);
    }

    RetrieveConf(PduHeaders pduHeaders, PduBody pduBody) {
        super(pduHeaders, pduBody);
    }

    public void addCc(EncodedStringValue encodedStringValue) {
        this.mPduHeaders.appendEncodedStringValue(encodedStringValue, 130);
    }

    public EncodedStringValue[] getCc() {
        return this.mPduHeaders.getEncodedStringValues(130);
    }

    public byte[] getContentType() {
        return this.mPduHeaders.getTextString(132);
    }

    public int getDeliveryReport() {
        return this.mPduHeaders.getOctet(134);
    }

    @Override
    public EncodedStringValue getFrom() {
        return this.mPduHeaders.getEncodedStringValue(137);
    }

    public byte[] getMessageClass() {
        return this.mPduHeaders.getTextString(138);
    }

    public byte[] getMessageId() {
        return this.mPduHeaders.getTextString(139);
    }

    public int getReadReport() {
        return this.mPduHeaders.getOctet(144);
    }

    public int getRetrieveStatus() {
        return this.mPduHeaders.getOctet(153);
    }

    public EncodedStringValue getRetrieveText() {
        return this.mPduHeaders.getEncodedStringValue(154);
    }

    public byte[] getTransactionId() {
        return this.mPduHeaders.getTextString(152);
    }

    public void setContentType(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 132);
    }

    public void setDeliveryReport(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 134);
    }

    @Override
    public void setFrom(EncodedStringValue encodedStringValue) {
        this.mPduHeaders.setEncodedStringValue(encodedStringValue, 137);
    }

    public void setMessageClass(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 138);
    }

    public void setMessageId(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 139);
    }

    public void setReadReport(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 144);
    }

    public void setRetrieveStatus(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 153);
    }

    public void setRetrieveText(EncodedStringValue encodedStringValue) {
        this.mPduHeaders.setEncodedStringValue(encodedStringValue, 154);
    }

    public void setTransactionId(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 152);
    }
}

