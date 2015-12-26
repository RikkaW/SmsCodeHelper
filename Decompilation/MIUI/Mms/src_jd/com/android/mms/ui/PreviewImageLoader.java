package com.android.mms.ui;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ImageView;
import com.android.mms.MmsApp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class PreviewImageLoader
{
  private static PreviewImageLoader sInstance = null;
  private LinkedHashMap<Long, CacheNode> mCachedPreviews = new LinkedHashMap(32);
  private Context mContext = MmsApp.getApp().getApplicationContext();
  private Handler mHandler = new Handler();
  private LoaderThread mLoaderThread;
  private LinkedHashMap<Long, ArrayList<ImageView>> mRequestedIds = new LinkedHashMap();
  
  public static PreviewImageLoader getInstance()
  {
    try
    {
      if (sInstance == null) {
        sInstance = new PreviewImageLoader();
      }
      PreviewImageLoader localPreviewImageLoader = sInstance;
      return localPreviewImageLoader;
    }
    finally {}
  }
  
  public void cancel(long paramLong, ImageView paramImageView)
  {
    synchronized (mRequestedIds)
    {
      ArrayList localArrayList = (ArrayList)mRequestedIds.get(Long.valueOf(paramLong));
      if (localArrayList != null) {
        localArrayList.remove(paramImageView);
      }
      return;
    }
  }
  
  public void cancelAllRequests(boolean paramBoolean)
  {
    synchronized (mRequestedIds)
    {
      mRequestedIds.clear();
      if (paramBoolean) {
        mCachedPreviews.clear();
      }
      if (mLoaderThread != null)
      {
        mLoaderThread.interrupt();
        mLoaderThread = null;
      }
      return;
    }
  }
  
  public void cancelAndClear(long paramLong)
  {
    synchronized (mRequestedIds)
    {
      mRequestedIds.remove(Long.valueOf(paramLong));
      mCachedPreviews.remove(Long.valueOf(paramLong));
      return;
    }
  }
  
  public void onLowMemory() {}
  
  public boolean request(long paramLong1, long paramLong2, ImageView paramImageView)
  {
    CacheNode localCacheNode = (CacheNode)mCachedPreviews.get(Long.valueOf(paramLong1));
    if (localCacheNode != null)
    {
      paramImageView.setImageDrawable(drawable);
      if (timestamp > paramLong2) {}
    }
    for (;;)
    {
      return true;
      if (mLoaderThread == null)
      {
        mLoaderThread = new LoaderThread(null);
        mLoaderThread.setName("PreviewImageLoader");
        mLoaderThread.setPriority(1);
        mLoaderThread.start();
      }
      synchronized (mRequestedIds)
      {
        ArrayList localArrayList2 = (ArrayList)mRequestedIds.get(Long.valueOf(paramLong1));
        ArrayList localArrayList1 = localArrayList2;
        if (localArrayList2 == null)
        {
          localArrayList1 = new ArrayList();
          mRequestedIds.put(Long.valueOf(paramLong1), localArrayList1);
        }
        localArrayList1.add(paramImageView);
        if (localArrayList1.size() == 1) {
          mRequestedIds.notify();
        }
        if (localCacheNode != null) {
          continue;
        }
        return false;
      }
    }
  }
  
  private class CacheNode
  {
    public Drawable drawable;
    public long timestamp;
    
    private CacheNode() {}
  }
  
  private class LoaderThread
    extends Thread
  {
    private LoaderThread() {}
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: invokestatic 31	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   3: invokevirtual 35	java/lang/Thread:isInterrupted	()Z
      //   6: ifeq +13 -> 19
      //   9: ldc 37
      //   11: iconst_0
      //   12: anewarray 39	java/lang/Object
      //   15: invokestatic 45	com/android/mms/LogTag:verbose	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   18: return
      //   19: lconst_0
      //   20: lstore_3
      //   21: aconst_null
      //   22: astore 7
      //   24: aload_0
      //   25: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   28: invokestatic 49	com/android/mms/ui/PreviewImageLoader:access$100	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   31: astore 8
      //   33: aload 8
      //   35: monitorenter
      //   36: aload_0
      //   37: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   40: invokestatic 49	com/android/mms/ui/PreviewImageLoader:access$100	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   43: invokevirtual 55	java/util/LinkedHashMap:size	()I
      //   46: istore_1
      //   47: iload_1
      //   48: ifne +13 -> 61
      //   51: aload_0
      //   52: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   55: invokestatic 49	com/android/mms/ui/PreviewImageLoader:access$100	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   58: invokevirtual 58	java/lang/Object:wait	()V
      //   61: aload_0
      //   62: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   65: invokestatic 49	com/android/mms/ui/PreviewImageLoader:access$100	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   68: invokevirtual 55	java/util/LinkedHashMap:size	()I
      //   71: ifle +41 -> 112
      //   74: aload_0
      //   75: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   78: invokestatic 49	com/android/mms/ui/PreviewImageLoader:access$100	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   81: invokevirtual 62	java/util/LinkedHashMap:eldest	()Ljava/util/Map$Entry;
      //   84: astore 7
      //   86: aload 7
      //   88: invokeinterface 68 1 0
      //   93: checkcast 70	java/lang/Long
      //   96: invokevirtual 74	java/lang/Long:longValue	()J
      //   99: lstore_3
      //   100: aload 7
      //   102: invokeinterface 77 1 0
      //   107: checkcast 79	java/util/ArrayList
      //   110: astore 7
      //   112: aload 8
      //   114: monitorexit
      //   115: lload_3
      //   116: lconst_0
      //   117: lcmp
      //   118: ifeq -118 -> 0
      //   121: getstatic 85	android/provider/Telephony$Mms:CONTENT_URI	Landroid/net/Uri;
      //   124: invokevirtual 91	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
      //   127: ldc 93
      //   129: ldc 95
      //   131: invokevirtual 101	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
      //   134: invokevirtual 105	android/net/Uri$Builder:build	()Landroid/net/Uri;
      //   137: astore 8
      //   139: aload_0
      //   140: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   143: invokestatic 109	com/android/mms/ui/PreviewImageLoader:access$200	(Lcom/android/mms/ui/PreviewImageLoader;)Landroid/content/Context;
      //   146: invokevirtual 115	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
      //   149: astore 9
      //   151: new 117	java/lang/StringBuilder
      //   154: dup
      //   155: invokespecial 118	java/lang/StringBuilder:<init>	()V
      //   158: ldc 120
      //   160: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   163: lload_3
      //   164: invokevirtual 127	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   167: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   170: astore 10
      //   172: aload 9
      //   174: aload 8
      //   176: iconst_2
      //   177: anewarray 133	java/lang/String
      //   180: dup
      //   181: iconst_0
      //   182: ldc -121
      //   184: aastore
      //   185: dup
      //   186: iconst_1
      //   187: ldc -119
      //   189: aastore
      //   190: aload 10
      //   192: aconst_null
      //   193: aconst_null
      //   194: invokevirtual 143	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   197: astore 10
      //   199: aload 10
      //   201: ifnull +115 -> 316
      //   204: aload 10
      //   206: invokeinterface 148 1 0
      //   211: ifeq +98 -> 309
      //   214: aload 10
      //   216: iconst_0
      //   217: invokeinterface 152 2 0
      //   222: astore 11
      //   224: aload 10
      //   226: iconst_1
      //   227: invokeinterface 156 2 0
      //   232: lstore 5
      //   234: aload 11
      //   236: ifnull +73 -> 309
      //   239: aconst_null
      //   240: astore 8
      //   242: aload 11
      //   244: iconst_0
      //   245: aload 11
      //   247: arraylength
      //   248: invokestatic 162	android/graphics/BitmapFactory:decodeByteArray	([BII)Landroid/graphics/Bitmap;
      //   251: astore 9
      //   253: aload 9
      //   255: astore 8
      //   257: aload 9
      //   259: ifnull -17 -> 242
      //   262: new 164	android/graphics/drawable/BitmapDrawable
      //   265: dup
      //   266: aload_0
      //   267: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   270: invokestatic 109	com/android/mms/ui/PreviewImageLoader:access$200	(Lcom/android/mms/ui/PreviewImageLoader;)Landroid/content/Context;
      //   273: invokevirtual 168	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   276: aload 9
      //   278: invokespecial 171	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
      //   281: astore 8
      //   283: aload_0
      //   284: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   287: invokestatic 175	com/android/mms/ui/PreviewImageLoader:access$500	(Lcom/android/mms/ui/PreviewImageLoader;)Landroid/os/Handler;
      //   290: new 9	com/android/mms/ui/PreviewImageLoader$LoaderThread$1
      //   293: dup
      //   294: aload_0
      //   295: lload_3
      //   296: aload 8
      //   298: lload 5
      //   300: aload 7
      //   302: invokespecial 178	com/android/mms/ui/PreviewImageLoader$LoaderThread$1:<init>	(Lcom/android/mms/ui/PreviewImageLoader$LoaderThread;JLandroid/graphics/drawable/Drawable;JLjava/util/ArrayList;)V
      //   305: invokevirtual 184	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   308: pop
      //   309: aload 10
      //   311: invokeinterface 187 1 0
      //   316: aload_0
      //   317: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   320: invokestatic 49	com/android/mms/ui/PreviewImageLoader:access$100	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   323: astore 7
      //   325: aload 7
      //   327: monitorenter
      //   328: aload_0
      //   329: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   332: invokestatic 49	com/android/mms/ui/PreviewImageLoader:access$100	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   335: lload_3
      //   336: invokestatic 191	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   339: invokevirtual 195	java/util/LinkedHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   342: pop
      //   343: aload 7
      //   345: monitorexit
      //   346: goto -346 -> 0
      //   349: astore 8
      //   351: aload 7
      //   353: monitorexit
      //   354: aload 8
      //   356: athrow
      //   357: astore 7
      //   359: ldc 37
      //   361: iconst_0
      //   362: anewarray 39	java/lang/Object
      //   365: invokestatic 45	com/android/mms/LogTag:verbose	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   368: aload 8
      //   370: monitorexit
      //   371: return
      //   372: astore 7
      //   374: aload 8
      //   376: monitorexit
      //   377: aload 7
      //   379: athrow
      //   380: astore 9
      //   382: aload_0
      //   383: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   386: invokestatic 198	com/android/mms/ui/PreviewImageLoader:access$300	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   389: invokevirtual 55	java/util/LinkedHashMap:size	()I
      //   392: istore_2
      //   393: iload_2
      //   394: ifne +20 -> 414
      //   397: ldc -56
      //   399: iconst_0
      //   400: anewarray 39	java/lang/Object
      //   403: invokestatic 203	com/android/mms/LogTag:error	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   406: aload 10
      //   408: invokeinterface 187 1 0
      //   413: return
      //   414: ldc -51
      //   416: iconst_0
      //   417: anewarray 39	java/lang/Object
      //   420: invokestatic 208	com/android/mms/LogTag:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   423: iconst_0
      //   424: istore_1
      //   425: aload 8
      //   427: astore 9
      //   429: iload_1
      //   430: iload_2
      //   431: iconst_2
      //   432: idiv
      //   433: if_icmpgt -180 -> 253
      //   436: aload_0
      //   437: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   440: invokestatic 198	com/android/mms/ui/PreviewImageLoader:access$300	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   443: aload_0
      //   444: getfield 15	com/android/mms/ui/PreviewImageLoader$LoaderThread:this$0	Lcom/android/mms/ui/PreviewImageLoader;
      //   447: invokestatic 198	com/android/mms/ui/PreviewImageLoader:access$300	(Lcom/android/mms/ui/PreviewImageLoader;)Ljava/util/LinkedHashMap;
      //   450: invokevirtual 62	java/util/LinkedHashMap:eldest	()Ljava/util/Map$Entry;
      //   453: invokeinterface 68 1 0
      //   458: invokevirtual 195	java/util/LinkedHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   461: pop
      //   462: iload_1
      //   463: iconst_1
      //   464: iadd
      //   465: istore_1
      //   466: goto -41 -> 425
      //   469: astore 7
      //   471: aload 10
      //   473: invokeinterface 187 1 0
      //   478: aload 7
      //   480: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	481	0	this	LoaderThread
      //   46	420	1	i	int
      //   392	41	2	j	int
      //   20	316	3	l1	long
      //   232	67	5	l2	long
      //   357	1	7	localInterruptedException	InterruptedException
      //   372	6	7	localObject2	Object
      //   469	10	7	localObject3	Object
      //   31	266	8	localObject4	Object
      //   349	77	8	localObject5	Object
      //   149	128	9	localObject6	Object
      //   380	1	9	localOutOfMemoryError	OutOfMemoryError
      //   427	1	9	localObject7	Object
      //   170	302	10	localObject8	Object
      //   222	24	11	arrayOfByte	byte[]
      // Exception table:
      //   from	to	target	type
      //   328	346	349	finally
      //   351	354	349	finally
      //   51	61	357	java/lang/InterruptedException
      //   36	47	372	finally
      //   51	61	372	finally
      //   61	112	372	finally
      //   112	115	372	finally
      //   359	371	372	finally
      //   374	377	372	finally
      //   242	253	380	java/lang/OutOfMemoryError
      //   204	234	469	finally
      //   242	253	469	finally
      //   262	309	469	finally
      //   382	393	469	finally
      //   397	406	469	finally
      //   414	423	469	finally
      //   429	462	469	finally
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PreviewImageLoader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */