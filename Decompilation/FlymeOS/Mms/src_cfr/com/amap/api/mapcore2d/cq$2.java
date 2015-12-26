/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.concurrent.FutureTask
 */
package com.amap.api.mapcore2d;

import android.util.Log;
import com.amap.api.mapcore2d.cq;
import com.amap.api.mapcore2d.cy;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class cq$2
extends FutureTask<Result> {
    final /* synthetic */ cq a;

    cq$2(cq cq2, Callable callable) {
        this.a = cq2;
        super(callable);
    }

    protected void done() {
        try {
            cq.b(this.a, cq.b(this.a).get());
            return;
        }
        catch (InterruptedException var1_1) {
            cy.a(var1_1, "AsyncTask", "done");
            Log.w((String)"AsyncTask", (Throwable)var1_1);
            return;
        }
        catch (ExecutionException var1_2) {
            cy.a(var1_2, "AsyncTask", "done");
            throw new RuntimeException("An error occured while executing doInBackground()", var1_2.getCause());
        }
        catch (CancellationException var1_3) {
            cy.a(var1_3, "AsyncTask", "done");
            cq.b(this.a, null);
            return;
        }
    }
}

