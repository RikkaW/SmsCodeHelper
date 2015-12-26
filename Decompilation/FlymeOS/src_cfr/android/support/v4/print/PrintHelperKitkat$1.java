/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Canvas
 *  android.graphics.Matrix
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.pdf.PdfDocument
 *  android.graphics.pdf.PdfDocument$Page
 *  android.graphics.pdf.PdfDocument$PageInfo
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
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.support.v4.print.PrintHelperKitkat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class PrintHelperKitkat$1
extends PrintDocumentAdapter {
    private PrintAttributes mAttributes;
    final /* synthetic */ PrintHelperKitkat this$0;
    final /* synthetic */ Bitmap val$bitmap;
    final /* synthetic */ PrintHelperKitkat.OnPrintFinishCallback val$callback;
    final /* synthetic */ int val$fittingMode;
    final /* synthetic */ String val$jobName;

    PrintHelperKitkat$1(PrintHelperKitkat printHelperKitkat, String string2, Bitmap bitmap, int n2, PrintHelperKitkat.OnPrintFinishCallback onPrintFinishCallback) {
        this.this$0 = printHelperKitkat;
        this.val$jobName = string2;
        this.val$bitmap = bitmap;
        this.val$fittingMode = n2;
        this.val$callback = onPrintFinishCallback;
    }

    public void onFinish() {
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
            rectF = PrintHelperKitkat.access$000(this.this$0, this.val$bitmap.getWidth(), this.val$bitmap.getHeight(), rectF, this.val$fittingMode);
            cancellationSignal.getCanvas().drawBitmap(this.val$bitmap, (Matrix)rectF, null);
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

