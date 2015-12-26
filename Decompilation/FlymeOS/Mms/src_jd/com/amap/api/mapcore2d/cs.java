package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class cs
{
  private static final Bitmap.CompressFormat a = Bitmap.CompressFormat.JPEG;
  private ep b;
  private cx<String, Bitmap> c;
  private a d;
  private final Object e = new Object();
  private boolean f = true;
  private HashMap<String, WeakReference<Bitmap>> g;
  
  private cs(a parama)
  {
    b(parama);
  }
  
  public static int a(Bitmap paramBitmap)
  {
    if (cy.d()) {
      return paramBitmap.getByteCount();
    }
    return paramBitmap.getRowBytes() * paramBitmap.getHeight();
  }
  
  public static long a(File paramFile)
  {
    if (cy.b()) {
      return paramFile.getUsableSpace();
    }
    paramFile = new StatFs(paramFile.getPath());
    long l = paramFile.getBlockSize();
    return paramFile.getAvailableBlocks() * l;
  }
  
  public static cs a(a parama)
  {
    return new cs(parama);
  }
  
  public static File a(Context paramContext)
  {
    if (cy.a()) {
      return paramContext.getExternalCacheDir();
    }
    paramContext = "/Android/data/" + paramContext.getPackageName() + "/cache/";
    return new File(Environment.getExternalStorageDirectory().getPath() + paramContext);
  }
  
  public static File a(Context paramContext, String paramString)
  {
    File localFile = a(paramContext);
    if ((("mounted".equals(Environment.getExternalStorageState())) || (!e())) && (localFile != null)) {}
    for (paramContext = localFile.getPath();; paramContext = paramContext.getCacheDir().getPath())
    {
      cw.a("ImageCache", "Disk cachePath: " + paramContext, 111);
      return new File(paramContext + File.separator + paramString);
    }
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() == 1) {
        localStringBuilder.append('0');
      }
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private void b(a parama)
  {
    d = parama;
    if (a.a(d))
    {
      cw.a("ImageCache", "Memory cache created (size = " + a.b(d) + ")", 111);
      if (cy.c()) {
        g = new HashMap();
      }
      c = new cs.1(this, a.b(d));
    }
    if (a.c(parama)) {
      a();
    }
  }
  
  public static String c(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = a(((MessageDigest)localObject).digest());
      return (String)localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      cy.a(localNoSuchAlgorithmException, "ImageCache", "hashKeyForDisk");
    }
    return String.valueOf(paramString.hashCode());
  }
  
  public static boolean e()
  {
    if (cy.b()) {
      return Environment.isExternalStorageRemovable();
    }
    return true;
  }
  
  public Bitmap a(String paramString)
  {
    Object localObject1;
    Object localObject2;
    if ((cy.c()) && (g != null))
    {
      localObject1 = (WeakReference)g.get(paramString);
      if (localObject1 != null)
      {
        localObject2 = (Bitmap)((WeakReference)localObject1).get();
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (!((Bitmap)localObject2).isRecycled()) {}
        }
        else
        {
          localObject1 = null;
        }
        g.remove(paramString);
      }
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = localObject1;
        if (c != null) {
          localObject2 = (Bitmap)c.a(paramString);
        }
      }
      if ((localObject2 == null) || (((Bitmap)localObject2).isRecycled())) {
        return null;
      }
      cw.a("ImageCache", "Memory cache hit", 111);
      return (Bitmap)localObject2;
      localObject1 = null;
    }
  }
  
  public void a()
  {
    synchronized (e)
    {
      File localFile;
      if ((b == null) || (b.a()))
      {
        localFile = a.d(d);
        if ((a.e(d)) && (localFile != null))
        {
          if (!localFile.exists()) {
            localFile.mkdirs();
          }
          long l = a(localFile);
          int i = a.f(d);
          if (l <= i) {}
        }
      }
      try
      {
        b = ep.a(localFile, 1, 1, a.f(d));
        cw.a("ImageCache", "Disk cache initialized", 111);
        f = false;
        e.notifyAll();
        return;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          cy.a(localIOException, "ImageCache", "initDiskCache");
          a.a(d, null);
          cw.a("ImageCache", "initDiskCache - " + localIOException, 112);
        }
      }
    }
  }
  
  /* Error */
  public void a(String paramString, Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +14 -> 15
    //   4: aload_2
    //   5: ifnull +10 -> 15
    //   8: aload_2
    //   9: invokevirtual 250	android/graphics/Bitmap:isRecycled	()Z
    //   12: ifeq +4 -> 16
    //   15: return
    //   16: aload_0
    //   17: getfield 194	com/amap/api/mapcore2d/cs:c	Lcom/amap/api/mapcore2d/cx;
    //   20: ifnull +13 -> 33
    //   23: aload_0
    //   24: getfield 194	com/amap/api/mapcore2d/cs:c	Lcom/amap/api/mapcore2d/cx;
    //   27: aload_1
    //   28: aload_2
    //   29: invokevirtual 305	com/amap/api/mapcore2d/cx:b	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   32: pop
    //   33: aload_0
    //   34: getfield 38	com/amap/api/mapcore2d/cs:e	Ljava/lang/Object;
    //   37: astore 4
    //   39: aload 4
    //   41: monitorenter
    //   42: aload_0
    //   43: getfield 263	com/amap/api/mapcore2d/cs:b	Lcom/amap/api/mapcore2d/ep;
    //   46: ifnull +80 -> 126
    //   49: aload_1
    //   50: invokestatic 307	com/amap/api/mapcore2d/cs:c	(Ljava/lang/String;)Ljava/lang/String;
    //   53: astore_3
    //   54: aconst_null
    //   55: astore_1
    //   56: aload_0
    //   57: getfield 263	com/amap/api/mapcore2d/cs:b	Lcom/amap/api/mapcore2d/ep;
    //   60: aload_3
    //   61: invokevirtual 310	com/amap/api/mapcore2d/ep:a	(Ljava/lang/String;)Lcom/amap/api/mapcore2d/ep$b;
    //   64: astore 5
    //   66: aload 5
    //   68: ifnonnull +68 -> 136
    //   71: aload_0
    //   72: getfield 263	com/amap/api/mapcore2d/cs:b	Lcom/amap/api/mapcore2d/ep;
    //   75: aload_3
    //   76: invokevirtual 313	com/amap/api/mapcore2d/ep:b	(Ljava/lang/String;)Lcom/amap/api/mapcore2d/ep$a;
    //   79: astore_3
    //   80: aload_3
    //   81: ifnull +37 -> 118
    //   84: aload_3
    //   85: iconst_0
    //   86: invokevirtual 318	com/amap/api/mapcore2d/ep$a:a	(I)Ljava/io/OutputStream;
    //   89: astore_1
    //   90: aload_2
    //   91: aload_0
    //   92: getfield 169	com/amap/api/mapcore2d/cs:d	Lcom/amap/api/mapcore2d/cs$a;
    //   95: invokestatic 321	com/amap/api/mapcore2d/cs$a:g	(Lcom/amap/api/mapcore2d/cs$a;)Landroid/graphics/Bitmap$CompressFormat;
    //   98: aload_0
    //   99: getfield 169	com/amap/api/mapcore2d/cs:d	Lcom/amap/api/mapcore2d/cs$a;
    //   102: invokestatic 324	com/amap/api/mapcore2d/cs$a:h	(Lcom/amap/api/mapcore2d/cs$a;)I
    //   105: aload_1
    //   106: invokevirtual 328	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   109: pop
    //   110: aload_3
    //   111: invokevirtual 329	com/amap/api/mapcore2d/ep$a:a	()V
    //   114: aload_1
    //   115: invokevirtual 334	java/io/OutputStream:close	()V
    //   118: aload_1
    //   119: ifnull +7 -> 126
    //   122: aload_1
    //   123: invokevirtual 334	java/io/OutputStream:close	()V
    //   126: aload 4
    //   128: monitorexit
    //   129: return
    //   130: astore_1
    //   131: aload 4
    //   133: monitorexit
    //   134: aload_1
    //   135: athrow
    //   136: aload 5
    //   138: iconst_0
    //   139: invokevirtual 339	com/amap/api/mapcore2d/ep$b:a	(I)Ljava/io/InputStream;
    //   142: invokevirtual 342	java/io/InputStream:close	()V
    //   145: goto -27 -> 118
    //   148: astore_3
    //   149: aconst_null
    //   150: astore_2
    //   151: aload_2
    //   152: astore_1
    //   153: aload_3
    //   154: ldc -119
    //   156: ldc_w 344
    //   159: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   162: aload_2
    //   163: astore_1
    //   164: ldc -119
    //   166: new 98	java/lang/StringBuilder
    //   169: dup
    //   170: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   173: ldc_w 346
    //   176: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: aload_3
    //   180: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   183: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: bipush 112
    //   188: invokestatic 144	com/amap/api/mapcore2d/cw:a	(Ljava/lang/String;Ljava/lang/String;I)V
    //   191: aload_2
    //   192: ifnull -66 -> 126
    //   195: aload_2
    //   196: invokevirtual 334	java/io/OutputStream:close	()V
    //   199: goto -73 -> 126
    //   202: astore_1
    //   203: aload_1
    //   204: ldc -119
    //   206: ldc_w 344
    //   209: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   212: goto -86 -> 126
    //   215: astore_1
    //   216: aload_1
    //   217: ldc -119
    //   219: ldc_w 344
    //   222: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   225: goto -99 -> 126
    //   228: astore_3
    //   229: aconst_null
    //   230: astore_2
    //   231: aload_2
    //   232: astore_1
    //   233: aload_3
    //   234: ldc -119
    //   236: ldc_w 344
    //   239: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   242: aload_2
    //   243: astore_1
    //   244: ldc -119
    //   246: new 98	java/lang/StringBuilder
    //   249: dup
    //   250: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   253: ldc_w 346
    //   256: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: aload_3
    //   260: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   263: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: bipush 112
    //   268: invokestatic 144	com/amap/api/mapcore2d/cw:a	(Ljava/lang/String;Ljava/lang/String;I)V
    //   271: aload_2
    //   272: ifnull -146 -> 126
    //   275: aload_2
    //   276: invokevirtual 334	java/io/OutputStream:close	()V
    //   279: goto -153 -> 126
    //   282: astore_1
    //   283: aload_1
    //   284: ldc -119
    //   286: ldc_w 344
    //   289: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   292: goto -166 -> 126
    //   295: astore_2
    //   296: aconst_null
    //   297: astore_1
    //   298: aload_1
    //   299: ifnull +7 -> 306
    //   302: aload_1
    //   303: invokevirtual 334	java/io/OutputStream:close	()V
    //   306: aload_2
    //   307: athrow
    //   308: astore_1
    //   309: aload_1
    //   310: ldc -119
    //   312: ldc_w 344
    //   315: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   318: goto -12 -> 306
    //   321: astore_2
    //   322: goto -24 -> 298
    //   325: astore_2
    //   326: goto -28 -> 298
    //   329: astore_3
    //   330: aload_1
    //   331: astore_2
    //   332: goto -101 -> 231
    //   335: astore_3
    //   336: aload_1
    //   337: astore_2
    //   338: goto -187 -> 151
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	341	0	this	cs
    //   0	341	1	paramString	String
    //   0	341	2	paramBitmap	Bitmap
    //   53	58	3	localObject1	Object
    //   148	32	3	localIOException1	IOException
    //   228	32	3	localException1	Exception
    //   329	1	3	localException2	Exception
    //   335	1	3	localIOException2	IOException
    //   37	95	4	localObject2	Object
    //   64	73	5	localb	ep.b
    // Exception table:
    //   from	to	target	type
    //   42	54	130	finally
    //   122	126	130	finally
    //   126	129	130	finally
    //   131	134	130	finally
    //   195	199	130	finally
    //   203	212	130	finally
    //   216	225	130	finally
    //   275	279	130	finally
    //   283	292	130	finally
    //   302	306	130	finally
    //   306	308	130	finally
    //   309	318	130	finally
    //   56	66	148	java/io/IOException
    //   71	80	148	java/io/IOException
    //   84	90	148	java/io/IOException
    //   136	145	148	java/io/IOException
    //   195	199	202	java/io/IOException
    //   122	126	215	java/io/IOException
    //   56	66	228	java/lang/Exception
    //   71	80	228	java/lang/Exception
    //   84	90	228	java/lang/Exception
    //   136	145	228	java/lang/Exception
    //   275	279	282	java/io/IOException
    //   56	66	295	finally
    //   71	80	295	finally
    //   84	90	295	finally
    //   136	145	295	finally
    //   302	306	308	java/io/IOException
    //   90	118	321	finally
    //   153	162	325	finally
    //   164	191	325	finally
    //   233	242	325	finally
    //   244	271	325	finally
    //   90	118	329	java/lang/Exception
    //   90	118	335	java/io/IOException
  }
  
  /* Error */
  public Bitmap b(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_1
    //   6: invokestatic 307	com/amap/api/mapcore2d/cs:c	(Ljava/lang/String;)Ljava/lang/String;
    //   9: astore_1
    //   10: aload_0
    //   11: getfield 38	com/amap/api/mapcore2d/cs:e	Ljava/lang/Object;
    //   14: astore 7
    //   16: aload 7
    //   18: monitorenter
    //   19: aload_0
    //   20: getfield 40	com/amap/api/mapcore2d/cs:f	Z
    //   23: istore_2
    //   24: iload_2
    //   25: ifeq +34 -> 59
    //   28: aload_0
    //   29: getfield 38	com/amap/api/mapcore2d/cs:e	Ljava/lang/Object;
    //   32: invokevirtual 351	java/lang/Object:wait	()V
    //   35: goto -16 -> 19
    //   38: astore 4
    //   40: aload 4
    //   42: ldc -119
    //   44: ldc_w 353
    //   47: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   50: goto -31 -> 19
    //   53: astore_1
    //   54: aload 7
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    //   59: aload_0
    //   60: getfield 263	com/amap/api/mapcore2d/cs:b	Lcom/amap/api/mapcore2d/ep;
    //   63: astore 5
    //   65: aload 6
    //   67: astore 4
    //   69: aload 5
    //   71: ifnull +82 -> 153
    //   74: aload_0
    //   75: getfield 263	com/amap/api/mapcore2d/cs:b	Lcom/amap/api/mapcore2d/ep;
    //   78: aload_1
    //   79: invokevirtual 310	com/amap/api/mapcore2d/ep:a	(Ljava/lang/String;)Lcom/amap/api/mapcore2d/ep$b;
    //   82: astore_1
    //   83: aload_1
    //   84: ifnull +208 -> 292
    //   87: ldc -119
    //   89: ldc_w 355
    //   92: bipush 111
    //   94: invokestatic 144	com/amap/api/mapcore2d/cw:a	(Ljava/lang/String;Ljava/lang/String;I)V
    //   97: aload_1
    //   98: iconst_0
    //   99: invokevirtual 339	com/amap/api/mapcore2d/ep$b:a	(I)Ljava/io/InputStream;
    //   102: astore_1
    //   103: aload_1
    //   104: astore 5
    //   106: aload_1
    //   107: ifnull +30 -> 137
    //   110: aload_1
    //   111: astore_3
    //   112: aload_1
    //   113: checkcast 357	java/io/FileInputStream
    //   116: invokevirtual 361	java/io/FileInputStream:getFD	()Ljava/io/FileDescriptor;
    //   119: ldc_w 362
    //   122: ldc_w 362
    //   125: aload_0
    //   126: invokestatic 367	com/amap/api/mapcore2d/cu:a	(Ljava/io/FileDescriptor;IILcom/amap/api/mapcore2d/cs;)Landroid/graphics/Bitmap;
    //   129: astore 4
    //   131: aload 4
    //   133: astore_3
    //   134: aload_1
    //   135: astore 5
    //   137: aload_3
    //   138: astore 4
    //   140: aload 5
    //   142: ifnull +11 -> 153
    //   145: aload 5
    //   147: invokevirtual 342	java/io/InputStream:close	()V
    //   150: aload_3
    //   151: astore 4
    //   153: aload 7
    //   155: monitorexit
    //   156: aload 4
    //   158: areturn
    //   159: astore_1
    //   160: aload_1
    //   161: ldc -119
    //   163: ldc_w 353
    //   166: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   169: aload_3
    //   170: astore 4
    //   172: goto -19 -> 153
    //   175: astore 4
    //   177: aconst_null
    //   178: astore_1
    //   179: aload_1
    //   180: astore_3
    //   181: aload 4
    //   183: ldc -119
    //   185: ldc_w 353
    //   188: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   191: aload_1
    //   192: astore_3
    //   193: ldc -119
    //   195: new 98	java/lang/StringBuilder
    //   198: dup
    //   199: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   202: ldc_w 369
    //   205: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload 4
    //   210: invokevirtual 299	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   213: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: bipush 112
    //   218: invokestatic 144	com/amap/api/mapcore2d/cw:a	(Ljava/lang/String;Ljava/lang/String;I)V
    //   221: aload 6
    //   223: astore 4
    //   225: aload_1
    //   226: ifnull -73 -> 153
    //   229: aload_1
    //   230: invokevirtual 342	java/io/InputStream:close	()V
    //   233: aload 6
    //   235: astore 4
    //   237: goto -84 -> 153
    //   240: astore_1
    //   241: aload_1
    //   242: ldc -119
    //   244: ldc_w 353
    //   247: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   250: aload 6
    //   252: astore 4
    //   254: goto -101 -> 153
    //   257: astore_1
    //   258: aconst_null
    //   259: astore_3
    //   260: aload_3
    //   261: ifnull +7 -> 268
    //   264: aload_3
    //   265: invokevirtual 342	java/io/InputStream:close	()V
    //   268: aload_1
    //   269: athrow
    //   270: astore_3
    //   271: aload_3
    //   272: ldc -119
    //   274: ldc_w 353
    //   277: invokestatic 227	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   280: goto -12 -> 268
    //   283: astore_1
    //   284: goto -24 -> 260
    //   287: astore 4
    //   289: goto -110 -> 179
    //   292: aconst_null
    //   293: astore 5
    //   295: goto -158 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	298	0	this	cs
    //   0	298	1	paramString	String
    //   23	2	2	bool	boolean
    //   4	261	3	localObject1	Object
    //   270	2	3	localIOException1	IOException
    //   38	3	4	localInterruptedException	InterruptedException
    //   67	104	4	localObject2	Object
    //   175	34	4	localIOException2	IOException
    //   223	30	4	localObject3	Object
    //   287	1	4	localIOException3	IOException
    //   63	231	5	localObject4	Object
    //   1	250	6	localObject5	Object
    //   14	140	7	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   28	35	38	java/lang/InterruptedException
    //   19	24	53	finally
    //   28	35	53	finally
    //   40	50	53	finally
    //   54	57	53	finally
    //   59	65	53	finally
    //   145	150	53	finally
    //   153	156	53	finally
    //   160	169	53	finally
    //   229	233	53	finally
    //   241	250	53	finally
    //   264	268	53	finally
    //   268	270	53	finally
    //   271	280	53	finally
    //   145	150	159	java/io/IOException
    //   74	83	175	java/io/IOException
    //   87	103	175	java/io/IOException
    //   229	233	240	java/io/IOException
    //   74	83	257	finally
    //   87	103	257	finally
    //   264	268	270	java/io/IOException
    //   112	131	283	finally
    //   181	191	283	finally
    //   193	221	283	finally
    //   112	131	287	java/io/IOException
  }
  
  public void b()
  {
    if ((cy.c()) && (g != null)) {
      g.clear();
    }
    if (c != null)
    {
      c.a();
      cw.a("ImageCache", "Memory cache cleared", 111);
    }
    synchronized (e)
    {
      f = true;
      if (b != null)
      {
        boolean bool = b.a();
        if (bool) {}
      }
      try
      {
        b.c();
        cw.a("ImageCache", "Disk cache cleared", 111);
        b = null;
        a();
        return;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          cy.a(localIOException, "ImageCache", "clearCache");
          cw.a("ImageCache", "clearCache - " + localIOException, 112);
        }
      }
    }
  }
  
  public void c()
  {
    synchronized (e)
    {
      ep localep = b;
      if (localep != null) {}
      try
      {
        b.b();
        cw.a("ImageCache", "Disk cache flushed", 111);
        return;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          cy.a(localIOException, "ImageCache", "flush");
          cw.a("ImageCache", "flush - " + localIOException, 112);
        }
      }
    }
  }
  
  public void d()
  {
    if ((cy.c()) && (g != null)) {
      g.clear();
    }
    if (c != null)
    {
      c.a();
      cw.a("ImageCache", "Memory cache cleared", 111);
    }
    synchronized (e)
    {
      ep localep = b;
      if (localep != null) {}
      try
      {
        if (!b.a())
        {
          b.c();
          b = null;
          cw.a("ImageCache", "Disk cache closed", 111);
        }
        return;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          cy.a(localIOException, "ImageCache", "close");
          cw.a("ImageCache", "close - " + localIOException, 112);
        }
      }
    }
  }
  
  public static class a
  {
    private int a = 5120;
    private int b = 10485760;
    private File c;
    private Bitmap.CompressFormat d = cs.f();
    private int e = 100;
    private boolean f = true;
    private boolean g = true;
    private boolean h = false;
    
    public a(Context paramContext, String paramString)
    {
      c = cs.a(paramContext, paramString);
    }
    
    public void a(int paramInt)
    {
      a = paramInt;
    }
    
    public void a(String paramString)
    {
      c = new File(paramString);
    }
    
    public void a(boolean paramBoolean)
    {
      f = paramBoolean;
    }
    
    public void b(int paramInt)
    {
      b = paramInt;
    }
    
    public void b(boolean paramBoolean)
    {
      g = paramBoolean;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */