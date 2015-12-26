/*
 * Decompiled with CFR 0_110.
 */
package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.PduHeaders;

public class AcknowledgeInd
extends GenericPdu {
    public AcknowledgeInd(int n, byte[] arrby) throws InvalidHeaderValueException {
        this.setMessageType(133);
        this.setMmsVersion(n);
        this.setTransactionId(arrby);
    }

    AcknowledgeInd(PduHeaders pduHeaders) {
        super(pduHeaders);
    }

    public int getReportAllowed() {
        return this.mPduHeaders.getOctet(145);
    }

    public byte[] getTransactionId() {
        return this.mPduHeaders.getTextString(152);
    }

    public void setReportAllowed(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 145);
    }

    public void setTransactionId(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 152);
    }
}

