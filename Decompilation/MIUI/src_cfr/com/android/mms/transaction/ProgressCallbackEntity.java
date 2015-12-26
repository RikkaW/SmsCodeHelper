/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.OutputStream
 *  java.lang.String
 *  org.apache.http.entity.ByteArrayEntity
 */
package com.android.mms.transaction;

import com.android.mms.transaction.ProgressReceiver;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.entity.ByteArrayEntity;

public class ProgressCallbackEntity
extends ByteArrayEntity {
    private final byte[] mContent;
    private final ProgressReceiver mReceiver;

    public ProgressCallbackEntity(ProgressReceiver progressReceiver, byte[] arrby) {
        super(arrby);
        this.mContent = arrby;
        this.mReceiver = progressReceiver;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void writeTo(OutputStream var1_1) throws IOException {
        block10 : {
            if (var1_1 == null) {
                throw new IllegalArgumentException("Output stream may not be null");
            }
            var3_3 = 0;
            var5_4 = this.mContent.length;
            var2_5 = var3_3;
            if (this.mReceiver != null) {
                this.mReceiver.onProgress(-1, var5_4);
                var2_5 = var3_3;
            }
lbl10: // 4 sources:
            if (var2_5 < var5_4) {
                if (this.mReceiver != null) {
                    this.mReceiver.onProgress(var2_5, var5_4);
                }
                break block10;
            }
            if (this.mReceiver == null) return;
            this.mReceiver.onProgress(var5_4, var5_4);
            return;
        }
        var3_3 = var4_6 = var5_4 - var2_5;
        if (var4_6 > 4096) {
            var3_3 = 4096;
        }
        var1_1.write(this.mContent, var2_5, var3_3);
        var1_1.flush();
        var2_5 += var3_3;
        ** GOTO lbl10
    }
}

