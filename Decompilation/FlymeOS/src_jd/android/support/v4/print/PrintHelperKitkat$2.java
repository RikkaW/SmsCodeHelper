package android.support.v4.print;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentInfo.Builder;

class PrintHelperKitkat$2
  extends PrintDocumentAdapter
{
  private PrintAttributes mAttributes;
  Bitmap mBitmap = null;
  AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
  
  PrintHelperKitkat$2(PrintHelperKitkat paramPrintHelperKitkat, String paramString, Uri paramUri, PrintHelperKitkat.OnPrintFinishCallback paramOnPrintFinishCallback, int paramInt) {}
  
  private void cancelLoad()
  {
    synchronized (PrintHelperKitkat.access$300(this$0))
    {
      if (this$0.mDecodeOptions != null)
      {
        this$0.mDecodeOptions.requestCancelDecode();
        this$0.mDecodeOptions = null;
      }
      return;
    }
  }
  
  public void onFinish()
  {
    super.onFinish();
    cancelLoad();
    if (mLoadBitmap != null) {
      mLoadBitmap.cancel(true);
    }
    if (val$callback != null) {
      val$callback.onFinish();
    }
  }
  
  public void onLayout(PrintAttributes paramPrintAttributes1, PrintAttributes paramPrintAttributes2, CancellationSignal paramCancellationSignal, PrintDocumentAdapter.LayoutResultCallback paramLayoutResultCallback, Bundle paramBundle)
  {
    boolean bool = true;
    mAttributes = paramPrintAttributes2;
    if (paramCancellationSignal.isCanceled())
    {
      paramLayoutResultCallback.onLayoutCancelled();
      return;
    }
    if (mBitmap != null)
    {
      paramCancellationSignal = new PrintDocumentInfo.Builder(val$jobName).setContentType(1).setPageCount(1).build();
      if (!paramPrintAttributes2.equals(paramPrintAttributes1)) {}
      for (;;)
      {
        paramLayoutResultCallback.onLayoutFinished(paramCancellationSignal, bool);
        return;
        bool = false;
      }
    }
    mLoadBitmap = new PrintHelperKitkat.2.1(this, paramCancellationSignal, paramPrintAttributes2, paramPrintAttributes1, paramLayoutResultCallback).execute(new Uri[0]);
  }
  
  /* Error */
  public void onWrite(android.print.PageRange[] paramArrayOfPageRange, android.os.ParcelFileDescriptor paramParcelFileDescriptor, CancellationSignal paramCancellationSignal, android.print.PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
  {
    // Byte code:
    //   0: new 131	android/print/pdf/PrintedPdfDocument
    //   3: dup
    //   4: aload_0
    //   5: getfield 25	android/support/v4/print/PrintHelperKitkat$2:this$0	Landroid/support/v4/print/PrintHelperKitkat;
    //   8: getfield 135	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
    //   11: aload_0
    //   12: getfield 77	android/support/v4/print/PrintHelperKitkat$2:mAttributes	Landroid/print/PrintAttributes;
    //   15: invokespecial 138	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
    //   18: astore_1
    //   19: aload_1
    //   20: iconst_1
    //   21: invokevirtual 142	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
    //   24: astore_3
    //   25: new 144	android/graphics/RectF
    //   28: dup
    //   29: aload_3
    //   30: invokevirtual 150	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
    //   33: invokevirtual 156	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
    //   36: invokespecial 159	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
    //   39: astore 5
    //   41: aload_0
    //   42: getfield 25	android/support/v4/print/PrintHelperKitkat$2:this$0	Landroid/support/v4/print/PrintHelperKitkat;
    //   45: aload_0
    //   46: getfield 38	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
    //   49: invokevirtual 165	android/graphics/Bitmap:getWidth	()I
    //   52: aload_0
    //   53: getfield 38	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
    //   56: invokevirtual 168	android/graphics/Bitmap:getHeight	()I
    //   59: aload 5
    //   61: aload_0
    //   62: getfield 33	android/support/v4/print/PrintHelperKitkat$2:val$fittingMode	I
    //   65: invokestatic 172	android/support/v4/print/PrintHelperKitkat:access$000	(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
    //   68: astore 5
    //   70: aload_3
    //   71: invokevirtual 176	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
    //   74: aload_0
    //   75: getfield 38	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
    //   78: aload 5
    //   80: aconst_null
    //   81: invokevirtual 182	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
    //   84: aload_1
    //   85: aload_3
    //   86: invokevirtual 186	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
    //   89: aload_1
    //   90: new 188	java/io/FileOutputStream
    //   93: dup
    //   94: aload_2
    //   95: invokevirtual 194	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   98: invokespecial 197	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   101: invokevirtual 201	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
    //   104: aload 4
    //   106: iconst_1
    //   107: anewarray 203	android/print/PageRange
    //   110: dup
    //   111: iconst_0
    //   112: getstatic 207	android/print/PageRange:ALL_PAGES	Landroid/print/PageRange;
    //   115: aastore
    //   116: invokevirtual 213	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFinished	([Landroid/print/PageRange;)V
    //   119: aload_1
    //   120: ifnull +7 -> 127
    //   123: aload_1
    //   124: invokevirtual 216	android/print/pdf/PrintedPdfDocument:close	()V
    //   127: aload_2
    //   128: ifnull +7 -> 135
    //   131: aload_2
    //   132: invokevirtual 217	android/os/ParcelFileDescriptor:close	()V
    //   135: return
    //   136: astore_3
    //   137: ldc -37
    //   139: ldc -35
    //   141: aload_3
    //   142: invokestatic 227	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   145: pop
    //   146: aload 4
    //   148: aconst_null
    //   149: invokevirtual 231	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFailed	(Ljava/lang/CharSequence;)V
    //   152: goto -33 -> 119
    //   155: astore_3
    //   156: aload_1
    //   157: ifnull +7 -> 164
    //   160: aload_1
    //   161: invokevirtual 216	android/print/pdf/PrintedPdfDocument:close	()V
    //   164: aload_2
    //   165: ifnull +7 -> 172
    //   168: aload_2
    //   169: invokevirtual 217	android/os/ParcelFileDescriptor:close	()V
    //   172: aload_3
    //   173: athrow
    //   174: astore_1
    //   175: return
    //   176: astore_1
    //   177: goto -5 -> 172
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	180	0	this	2
    //   0	180	1	paramArrayOfPageRange	android.print.PageRange[]
    //   0	180	2	paramParcelFileDescriptor	android.os.ParcelFileDescriptor
    //   0	180	3	paramCancellationSignal	CancellationSignal
    //   0	180	4	paramWriteResultCallback	android.print.PrintDocumentAdapter.WriteResultCallback
    //   39	40	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   89	119	136	java/io/IOException
    //   19	89	155	finally
    //   89	119	155	finally
    //   137	152	155	finally
    //   131	135	174	java/io/IOException
    //   168	172	176	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     android.support.v4.print.PrintHelperKitkat.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */