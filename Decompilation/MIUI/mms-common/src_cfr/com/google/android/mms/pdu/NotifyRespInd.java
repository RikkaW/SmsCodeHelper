/*
 * Decompiled with CFR 0_110.
 */
package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.PduHeaders;

public class NotifyRespInd
extends GenericPdu {
    public NotifyRespInd(int n, byte[] arrby, int n2) throws InvalidHeaderValueException {
        this.setMessageType(131);
        this.setMmsVersion(n);
        this.setTransactionId(arrby);
        this.setStatus(n2);
    }

    NotifyRespInd(PduHeaders pduHeaders) {
        super(pduHeaders);
    }

    public int getReportAllowed() {
        return this.mPduHeaders.getOctet(145);
    }

    public int getStatus() {
        return this.mPduHeaders.getOctet(149);
    }

    public byte[] getTransactionId() {
        return this.mPduHeaders.getTextString(152);
    }

    public void setReportAllowed(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 145);
    }

    public void setStatus(int n) throws InvalidHeaderValueException {
        this.mPduHeaders.setOctet(n, 149);
    }

    public void setTransactionId(byte[] arrby) {
        this.mPduHeaders.setTextString(arrby, 152);
    }
}

