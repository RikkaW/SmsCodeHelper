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
package android.support.v4.content;

import android.support.v4.content.ModernAsyncTask;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class ModernAsyncTask$3
extends FutureTask<Result> {
    final /* synthetic */ ModernAsyncTask this$0;

    ModernAsyncTask$3(ModernAsyncTask modernAsyncTask, Callable callable) {
        this.this$0 = modernAsyncTask;
        super(callable);
    }

    protected void done() {
        try {
            Object object = this.get();
            ModernAsyncTask.access$400(this.this$0, object);
            return;
        }
        catch (InterruptedException var1_2) {
            Log.w((String)"AsyncTask", (Throwable)var1_2);
            return;
        }
        catch (ExecutionException var1_3) {
            throw new RuntimeException("An error occured while executing doInBackground()", var1_3.getCause());
        }
        catch (CancellationException var1_4) {
            ModernAsyncTask.access$400(this.this$0, null);
            return;
        }
        catch (Throwable var1_5) {
            throw new RuntimeException("An error occured while executing doInBackground()", var1_5);
        }
    }
}

