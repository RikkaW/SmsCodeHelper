package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.List;

class q
{
  protected final a[] a;
  protected final int b;
  protected final int c;
  protected final a[] d;
  private boolean e = false;
  private long f = 0L;
  private Paint g = null;
  private Path h = null;
  
  public q(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong)
  {
    b = paramInt1;
    c = paramInt2;
    e = paramBoolean;
    f = (1000000L * paramLong);
    if (b > 0)
    {
      a = new a[b];
      d = new a[c];
      return;
    }
    a = null;
    d = null;
  }
  
  private void a(Bitmap paramBitmap, List<cf> paramList)
  {
    paramList = new q.1(this, paramList);
    o localo = new o(null);
    localo.a(paramBitmap);
    localo.a(paramList);
  }
  
  private long d()
  {
    return System.nanoTime();
  }
  
  protected int a()
  {
    int i = 0;
    while (i < c)
    {
      d[i] = null;
      i += 1;
    }
    i = 0;
    int j;
    while (i < b)
    {
      Object localObject = a[i];
      j = 0;
      if (j < c)
      {
        if (d[j] == null) {
          d[j] = localObject;
        }
      }
      else
      {
        i += 1;
        continue;
      }
      if (d[j].d <= d) {
        break label181;
      }
      a locala = d[j];
      d[j] = localObject;
      localObject = locala;
    }
    label181:
    for (;;)
    {
      j += 1;
      break;
      j = -1;
      i = 0;
      while (i < c)
      {
        int k = j;
        if (d[i] != null)
        {
          d[i].c = false;
          k = j;
          if (j < 0) {
            k = d[i].e;
          }
        }
        i += 1;
        j = k;
      }
      return j;
    }
  }
  
  protected int a(String paramString)
  {
    if (paramString.equals("") == true) {
      return -1;
    }
    int i = 0;
    if (i < b)
    {
      if (a[i] == null) {}
      while (!a[i].b.equals(paramString))
      {
        i += 1;
        break;
      }
      if (!a[i].c) {
        return -1;
      }
      if ((e == true) && (d() - a[i].f > f))
      {
        a[i].c = false;
        return -1;
      }
      a[i].d = d();
      return i;
    }
    return -1;
  }
  
  /* Error */
  protected int a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, boolean paramBoolean, List<cf> paramList, String paramString)
  {
    // Byte code:
    //   0: iconst_m1
    //   1: istore 8
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_1
    //   6: ifnonnull +21 -> 27
    //   9: aload_2
    //   10: ifnonnull +17 -> 27
    //   13: aload 4
    //   15: ifnonnull +12 -> 27
    //   18: iload 8
    //   20: istore 7
    //   22: aload_0
    //   23: monitorexit
    //   24: iload 7
    //   26: ireturn
    //   27: aload_0
    //   28: invokevirtual 102	com/amap/api/mapcore2d/q:b	()I
    //   31: istore 7
    //   33: iload 7
    //   35: istore 6
    //   37: iload 7
    //   39: ifge +9 -> 48
    //   42: aload_0
    //   43: invokevirtual 104	com/amap/api/mapcore2d/q:a	()I
    //   46: istore 6
    //   48: iload 8
    //   50: istore 7
    //   52: iload 6
    //   54: iflt -32 -> 22
    //   57: iload 8
    //   59: istore 7
    //   61: aload_0
    //   62: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   65: ifnull -43 -> 22
    //   68: aload_0
    //   69: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   72: iload 6
    //   74: aaload
    //   75: ifnull +56 -> 131
    //   78: aload_0
    //   79: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   82: iload 6
    //   84: aaload
    //   85: getfield 107	com/amap/api/mapcore2d/q$a:a	Landroid/graphics/Bitmap;
    //   88: ifnull +43 -> 131
    //   91: aload_0
    //   92: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   95: iload 6
    //   97: aaload
    //   98: getfield 107	com/amap/api/mapcore2d/q$a:a	Landroid/graphics/Bitmap;
    //   101: invokevirtual 113	android/graphics/Bitmap:isRecycled	()Z
    //   104: ifne +27 -> 131
    //   107: aload_0
    //   108: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   111: iload 6
    //   113: aaload
    //   114: getfield 107	com/amap/api/mapcore2d/q$a:a	Landroid/graphics/Bitmap;
    //   117: invokevirtual 116	android/graphics/Bitmap:recycle	()V
    //   120: aload_0
    //   121: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   124: iload 6
    //   126: aaload
    //   127: aconst_null
    //   128: putfield 107	com/amap/api/mapcore2d/q$a:a	Landroid/graphics/Bitmap;
    //   131: aload_0
    //   132: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   135: iload 6
    //   137: aaload
    //   138: getfield 119	com/amap/api/mapcore2d/q$a:g	Ljava/util/List;
    //   141: ifnull +29 -> 170
    //   144: aload_0
    //   145: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   148: iload 6
    //   150: aaload
    //   151: getfield 119	com/amap/api/mapcore2d/q$a:g	Ljava/util/List;
    //   154: invokeinterface 124 1 0
    //   159: aload_0
    //   160: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   163: iload 6
    //   165: aaload
    //   166: aconst_null
    //   167: putfield 119	com/amap/api/mapcore2d/q$a:g	Ljava/util/List;
    //   170: iload_3
    //   171: iconst_1
    //   172: if_icmpne +197 -> 369
    //   175: aload_1
    //   176: ifnull +193 -> 369
    //   179: aload_0
    //   180: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   183: iload 6
    //   185: aaload
    //   186: aload_1
    //   187: iconst_0
    //   188: aload_1
    //   189: arraylength
    //   190: invokestatic 130	android/graphics/BitmapFactory:decodeByteArray	([BII)Landroid/graphics/Bitmap;
    //   193: putfield 107	com/amap/api/mapcore2d/q$a:a	Landroid/graphics/Bitmap;
    //   196: aload 4
    //   198: ifnull +41 -> 239
    //   201: aload_0
    //   202: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   205: iload 6
    //   207: aaload
    //   208: getstatic 135	com/amap/api/mapcore2d/y:i	I
    //   211: getstatic 135	com/amap/api/mapcore2d/y:i	I
    //   214: getstatic 141	android/graphics/Bitmap$Config:ARGB_4444	Landroid/graphics/Bitmap$Config;
    //   217: invokestatic 145	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   220: putfield 107	com/amap/api/mapcore2d/q$a:a	Landroid/graphics/Bitmap;
    //   223: aload_0
    //   224: aload_0
    //   225: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   228: iload 6
    //   230: aaload
    //   231: getfield 107	com/amap/api/mapcore2d/q$a:a	Landroid/graphics/Bitmap;
    //   234: aload 4
    //   236: invokespecial 147	com/amap/api/mapcore2d/q:a	(Landroid/graphics/Bitmap;Ljava/util/List;)V
    //   239: iload 8
    //   241: istore 7
    //   243: aload_0
    //   244: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   247: ifnull -225 -> 22
    //   250: aload_0
    //   251: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   254: iload 6
    //   256: aaload
    //   257: getfield 107	com/amap/api/mapcore2d/q$a:a	Landroid/graphics/Bitmap;
    //   260: ifnonnull +20 -> 280
    //   263: iload 8
    //   265: istore 7
    //   267: aload_0
    //   268: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   271: iload 6
    //   273: aaload
    //   274: getfield 119	com/amap/api/mapcore2d/q$a:g	Ljava/util/List;
    //   277: ifnull -255 -> 22
    //   280: aload_0
    //   281: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   284: iload 6
    //   286: aaload
    //   287: ifnull +126 -> 413
    //   290: aload_0
    //   291: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   294: iload 6
    //   296: aaload
    //   297: iconst_1
    //   298: putfield 78	com/amap/api/mapcore2d/q$a:c	Z
    //   301: aload_0
    //   302: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   305: iload 6
    //   307: aaload
    //   308: aload 5
    //   310: putfield 92	com/amap/api/mapcore2d/q$a:b	Ljava/lang/String;
    //   313: aload_0
    //   314: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   317: iload 6
    //   319: aaload
    //   320: aload_0
    //   321: invokespecial 94	com/amap/api/mapcore2d/q:d	()J
    //   324: putfield 76	com/amap/api/mapcore2d/q$a:d	J
    //   327: aload_0
    //   328: getfield 27	com/amap/api/mapcore2d/q:e	Z
    //   331: iconst_1
    //   332: if_icmpne +81 -> 413
    //   335: aload_0
    //   336: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   339: iload 6
    //   341: aaload
    //   342: aload_0
    //   343: invokespecial 94	com/amap/api/mapcore2d/q:d	()J
    //   346: putfield 95	com/amap/api/mapcore2d/q$a:f	J
    //   349: goto +64 -> 413
    //   352: astore_1
    //   353: aload_1
    //   354: ldc -107
    //   356: ldc -105
    //   358: invokestatic 156	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   361: goto -165 -> 196
    //   364: astore_1
    //   365: aload_0
    //   366: monitorexit
    //   367: aload_1
    //   368: athrow
    //   369: aload_2
    //   370: ifnull -174 -> 196
    //   373: aload_0
    //   374: getfield 41	com/amap/api/mapcore2d/q:a	[Lcom/amap/api/mapcore2d/q$a;
    //   377: iload 6
    //   379: aaload
    //   380: aload_2
    //   381: iconst_0
    //   382: aload_2
    //   383: arraylength
    //   384: invokestatic 130	android/graphics/BitmapFactory:decodeByteArray	([BII)Landroid/graphics/Bitmap;
    //   387: putfield 107	com/amap/api/mapcore2d/q$a:a	Landroid/graphics/Bitmap;
    //   390: goto -194 -> 196
    //   393: astore_1
    //   394: goto -198 -> 196
    //   397: astore_1
    //   398: aload_1
    //   399: ldc -107
    //   401: ldc -105
    //   403: invokestatic 156	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   406: goto -210 -> 196
    //   409: astore_1
    //   410: goto -214 -> 196
    //   413: iload 6
    //   415: istore 7
    //   417: goto -395 -> 22
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	420	0	this	q
    //   0	420	1	paramArrayOfByte1	byte[]
    //   0	420	2	paramArrayOfByte2	byte[]
    //   0	420	3	paramBoolean	boolean
    //   0	420	4	paramList	List<cf>
    //   0	420	5	paramString	String
    //   35	379	6	i	int
    //   20	396	7	j	int
    //   1	263	8	k	int
    // Exception table:
    //   from	to	target	type
    //   179	196	352	java/lang/Throwable
    //   27	33	364	finally
    //   42	48	364	finally
    //   61	131	364	finally
    //   131	170	364	finally
    //   179	196	364	finally
    //   201	239	364	finally
    //   243	263	364	finally
    //   267	280	364	finally
    //   280	349	364	finally
    //   353	361	364	finally
    //   373	390	364	finally
    //   398	406	364	finally
    //   373	390	393	java/lang/OutOfMemoryError
    //   373	390	397	java/lang/Throwable
    //   179	196	409	java/lang/OutOfMemoryError
  }
  
  protected Bitmap a(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= b)) {}
    while (a[paramInt] == null) {
      return null;
    }
    return a[paramInt].a;
  }
  
  protected int b()
  {
    int j = -1;
    int i = 0;
    while (i < b)
    {
      if (a[i] == null)
      {
        a[i] = new a();
        a[i].e = i;
        return i;
      }
      int k = j;
      if (!a[i].c)
      {
        k = j;
        if (j < 0) {
          k = i;
        }
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  protected void c()
  {
    int i = 0;
    if (i < b)
    {
      if (a[i] == null) {}
      for (;;)
      {
        i += 1;
        break;
        if ((a[i].a != null) && (!a[i].a.isRecycled())) {
          a[i].a.recycle();
        }
        a[i].a = null;
      }
    }
  }
  
  class a
  {
    Bitmap a = null;
    String b = "";
    boolean c = false;
    long d = 0L;
    int e = -1;
    long f = 0L;
    List<cf> g = null;
    
    public a() {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.q
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */