/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Process
 *  java.lang.Object
 */
package android.support.v4.content;

import android.os.Process;
import android.support.v4.content.ModernAsyncTask;

class ModernAsyncTask$2
extends ModernAsyncTask.WorkerRunnable<Params, Result> {
    final /* synthetic */ ModernAsyncTask this$0;

    ModernAsyncTask$2(ModernAsyncTask modernAsyncTask) {
        this.this$0 = modernAsyncTask;
    }

    @Override
    public Result call() {
        ModernAsyncTask.access$200(this.this$0).set(true);
        Process.setThreadPriority((int)10);
        return (Result)ModernAsyncTask.access$300(this.this$0, this.this$0.doInBackground(this.mParams));
    }
}

