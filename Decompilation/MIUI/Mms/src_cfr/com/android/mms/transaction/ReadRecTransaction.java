/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Sent
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.PduComposer
 *  com.google.android.mms.pdu.ReadRecInd
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.telephony.TelephonyManager
 */
package com.android.mms.transaction;

import android.content.Context;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.transaction.ProgressReceiver;
import com.android.mms.transaction.Transaction;
import com.android.mms.transaction.TransactionSettings;
import com.android.mms.transaction.TransactionState;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduComposer;
import com.google.android.mms.pdu.ReadRecInd;
import java.io.IOException;
import miui.telephony.TelephonyManager;

public class ReadRecTransaction
extends Transaction {
    private final Uri mReadReportURI;

    public ReadRecTransaction(Context context, TransactionSettings transactionSettings, String string) {
        super(context, transactionSettings);
        this.mReadReportURI = Uri.parse((String)string);
        this.mTransactionState.setContentUri(this.mReadReportURI);
        this.mId = string;
    }

    @Override
    public int getType() {
        return 3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void process() {
        block10 : {
            MiuiPduPersister miuiPduPersister = MiuiPduPersister.getPduPersister((Context)this.mContext);
            try {
                String string;
                ReadRecInd readRecInd = (ReadRecInd)miuiPduPersister.load(this.mReadReportURI);
                int n = MSimUtils.getSlotIdBySimInfoId(this.mSimId);
                String string2 = string = TelephonyManager.getDefault().getLine1NumberForSlot(n);
                if (TextUtils.isEmpty((CharSequence)string)) {
                    Log.e((String)"ReadRecTransaction", (String)"lineNumber is empty");
                    string2 = "";
                }
                readRecInd.setFrom(new EncodedStringValue(string2));
                this.sendPdu(new PduComposer(this.mContext, (GenericPdu)readRecInd).make(), null);
                string2 = miuiPduPersister.move(this.mReadReportURI, Telephony.Mms.Sent.CONTENT_URI);
                this.mTransactionState.setState(1);
                this.mTransactionState.setContentUri((Uri)string2);
            }
            catch (Throwable var2_6) {
                if (this.mTransactionState.getState() != 1) {
                    this.mTransactionState.setState(2);
                    this.mTransactionState.setContentUri(this.mReadReportURI);
                }
                this.notifyObservers();
                throw var2_6;
            }
            catch (IOException var2_7) {
                if (this.mTransactionState.getState() != 1) {
                    this.mTransactionState.setState(2);
                    this.mTransactionState.setContentUri(this.mReadReportURI);
                }
            }
            catch (MmsException var2_8) {
                if (this.mTransactionState.getState() != 1) {
                    this.mTransactionState.setState(2);
                    this.mTransactionState.setContentUri(this.mReadReportURI);
                }
            }
            catch (RuntimeException var2_9) {
                if (this.mTransactionState.getState() == 1) break block10;
                this.mTransactionState.setState(2);
                this.mTransactionState.setContentUri(this.mReadReportURI);
            }
            if (this.mTransactionState.getState() != 1) {
                this.mTransactionState.setState(2);
                this.mTransactionState.setContentUri(this.mReadReportURI);
            }
        }
        this.notifyObservers();
    }
}

