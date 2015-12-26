/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.CancellationSignal
 *  android.os.CancellationSignal$OnCancelListener
 *  android.print.PrintAttributes
 *  android.print.PrintDocumentAdapter
 *  android.print.PrintDocumentAdapter$LayoutResultCallback
 *  android.print.PrintDocumentInfo
 *  android.print.PrintDocumentInfo$Builder
 *  java.io.FileNotFoundException
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.print;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.support.v4.print.PrintHelperKitkat;
import android.support.v4.print.PrintHelperKitkat$2;
import android.support.v4.print.PrintHelperKitkat$2$1$1;
import java.io.FileNotFoundException;

class PrintHelperKitkat$2$1
extends AsyncTask<Uri, Boolean, Bitmap> {
    final /* synthetic */ PrintHelperKitkat$2 this$1;
    final /* synthetic */ CancellationSignal val$cancellationSignal;
    final /* synthetic */ PrintDocumentAdapter.LayoutResultCallback val$layoutResultCallback;
    final /* synthetic */ PrintAttributes val$newPrintAttributes;
    final /* synthetic */ PrintAttributes val$oldPrintAttributes;

    PrintHelperKitkat$2$1(PrintHelperKitkat$2 printHelperKitkat$2, CancellationSignal cancellationSignal, PrintAttributes printAttributes, PrintAttributes printAttributes2, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback) {
        this.this$1 = printHelperKitkat$2;
        this.val$cancellationSignal = cancellationSignal;
        this.val$newPrintAttributes = printAttributes;
        this.val$oldPrintAttributes = printAttributes2;
        this.val$layoutResultCallback = layoutResultCallback;
    }

    protected /* varargs */ Bitmap doInBackground(Uri ... bitmap) {
        try {
            bitmap = PrintHelperKitkat.access$200(this.this$1.this$0, this.this$1.val$imageFile, 3500);
            return bitmap;
        }
        catch (FileNotFoundException var1_2) {
            return null;
        }
    }

    protected void onCancelled(Bitmap bitmap) {
        this.val$layoutResultCallback.onLayoutCancelled();
        this.this$1.mLoadBitmap = null;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onPostExecute(Bitmap bitmap) {
        boolean bl2 = true;
        super.onPostExecute((Object)bitmap);
        this.this$1.mBitmap = bitmap;
        if (bitmap != null) {
            bitmap = new PrintDocumentInfo.Builder(this.this$1.val$jobName).setContentType(1).setPageCount(1).build();
            if (this.val$newPrintAttributes.equals((Object)this.val$oldPrintAttributes)) {
                bl2 = false;
            }
            this.val$layoutResultCallback.onLayoutFinished((PrintDocumentInfo)bitmap, bl2);
        } else {
            this.val$layoutResultCallback.onLayoutFailed(null);
        }
        this.this$1.mLoadBitmap = null;
    }

    protected void onPreExecute() {
        this.val$cancellationSignal.setOnCancelListener((CancellationSignal.OnCancelListener)new PrintHelperKitkat$2$1$1(this));
    }
}

