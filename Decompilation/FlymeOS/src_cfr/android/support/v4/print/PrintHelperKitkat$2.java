/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Canvas
 *  android.graphics.Matrix
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.pdf.PdfDocument
 *  android.graphics.pdf.PdfDocument$Page
 *  android.graphics.pdf.PdfDocument$PageInfo
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.CancellationSignal
 *  android.os.ParcelFileDescriptor
 *  android.print.PageRange
 *  android.print.PrintAttributes
 *  android.print.PrintDocumentAdapter
 *  android.print.PrintDocumentAdapter$LayoutResultCallback
 *  android.print.PrintDocumentAdapter$WriteResultCallback
 *  android.print.PrintDocumentInfo
 *  android.print.PrintDocumentInfo$Builder
 *  android.print.pdf.PrintedPdfDocument
 *  android.util.Log
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.support.v4.print.PrintHelperKitkat;
import android.support.v4.print.PrintHelperKitkat$2$1;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class PrintHelperKitkat$2
extends PrintDocumentAdapter {
    private PrintAttributes mAttributes;
    Bitmap mBitmap;
    AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
    final /* synthetic */ PrintHelperKitkat this$0;
    final /* synthetic */ PrintHelperKitkat.OnPrintFinishCallback val$callback;
    final /* synthetic */ int val$fittingMode;
    final /* synthetic */ Uri val$imageFile;
    final /* synthetic */ String val$jobName;

    PrintHelperKitkat$2(PrintHelperKitkat printHelperKitkat, String string2, Uri uri, PrintHelperKitkat.OnPrintFinishCallback onPrintFinishCallback, int n2) {
        this.this$0 = printHelperKitkat;
        this.val$jobName = string2;
        this.val$imageFile = uri;
        this.val$callback = onPrintFinishCallback;
        this.val$fittingMode = n2;
        this.mBitmap = null;
    }

    static /* synthetic */ void access$100(PrintHelperKitkat$2 printHelperKitkat$2) {
        printHelperKitkat$2.cancelLoad();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void cancelLoad() {
        Object object = PrintHelperKitkat.access$300(this.this$0);
        synchronized (object) {
            if (this.this$0.mDecodeOptions != null) {
                this.this$0.mDecodeOptions.requestCancelDecode();
                this.this$0.mDecodeOptions = null;
            }
            return;
        }
    }

    public void onFinish() {
        super.onFinish();
        this.cancelLoad();
        if (this.mLoadBitmap != null) {
            this.mLoadBitmap.cancel(true);
        }
        if (this.val$callback != null) {
            this.val$callback.onFinish();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
        boolean bl2 = true;
        this.mAttributes = printAttributes2;
        if (cancellationSignal.isCanceled()) {
            layoutResultCallback.onLayoutCancelled();
            return;
        }
        if (this.mBitmap == null) {
            this.mLoadBitmap = new PrintHelperKitkat$2$1(this, cancellationSignal, printAttributes2, printAttributes, layoutResultCallback).execute((Object[])new Uri[0]);
            return;
        }
        cancellationSignal = new PrintDocumentInfo.Builder(this.val$jobName).setContentType(1).setPageCount(1).build();
        if (printAttributes2.equals((Object)printAttributes)) {
            bl2 = false;
        }
        layoutResultCallback.onLayoutFinished((PrintDocumentInfo)cancellationSignal, bl2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onWrite(PageRange[] printedPdfDocument, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        printedPdfDocument = new PrintedPdfDocument(this.this$0.mContext, this.mAttributes);
        try {
            cancellationSignal = printedPdfDocument.startPage(1);
            RectF rectF = new RectF(cancellationSignal.getInfo().getContentRect());
            rectF = PrintHelperKitkat.access$000(this.this$0, this.mBitmap.getWidth(), this.mBitmap.getHeight(), rectF, this.val$fittingMode);
            cancellationSignal.getCanvas().drawBitmap(this.mBitmap, (Matrix)rectF, null);
            printedPdfDocument.finishPage((PdfDocument.Page)cancellationSignal);
            try {
                printedPdfDocument.writeTo((OutputStream)new FileOutputStream(parcelFileDescriptor.getFileDescriptor()));
                writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                {
                    do {
                        return;
                        break;
                    } while (true);
                }
            }
            catch (IOException var3_6) {
                Log.e((String)"PrintHelperKitkat", (String)"Error writing printed content", (Throwable)var3_6);
                writeResultCallback.onWriteFailed(null);
                return;
            }
        }
        finally {
            if (printedPdfDocument != null) {
                printedPdfDocument.close();
            }
            if (parcelFileDescriptor != null) {
                parcelFileDescriptor.close();
            }
        }
    }
}

