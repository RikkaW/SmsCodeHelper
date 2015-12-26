package com.amap.api.services.core;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class bk
  implements Closeable
{
  static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
  private static final OutputStream q = new bm();
  final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  private final File c;
  private final File d;
  private final File e;
  private final File f;
  private final int g;
  private long h;
  private final int i;
  private long j = 0L;
  private Writer k;
  private final LinkedHashMap<String, c> l = new LinkedHashMap(0, 0.75F, true);
  private int m;
  private bn n;
  private long o = 0L;
  private final Callable<Void> p = new bl(this);
  
  private bk(File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    c = paramFile;
    g = paramInt1;
    d = new File(paramFile, "journal");
    e = new File(paramFile, "journal.tmp");
    f = new File(paramFile, "journal.bkp");
    i = paramInt2;
    h = paramLong;
  }
  
  private a a(String paramString, long paramLong)
  {
    for (;;)
    {
      a locala;
      try
      {
        i();
        e(paramString);
        c localc = (c)l.get(paramString);
        if (paramLong != -1L) {
          if (localc != null)
          {
            long l1 = c.e(localc);
            if (l1 == paramLong) {}
          }
          else
          {
            paramString = null;
            return paramString;
          }
        }
        if (localc == null)
        {
          localc = new c(paramString, null);
          l.put(paramString, localc);
          locala = new a(localc, null);
          c.a(localc, locala);
          k.write("DIRTY " + paramString + '\n');
          k.flush();
          paramString = locala;
          continue;
        }
        locala = c.a(localc);
      }
      finally {}
      if (locala != null) {
        paramString = null;
      }
    }
  }
  
  public static bk a(File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    if (paramLong <= 0L) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    if (paramInt2 <= 0) {
      throw new IllegalArgumentException("valueCount <= 0");
    }
    Object localObject = new File(paramFile, "journal.bkp");
    File localFile;
    if (((File)localObject).exists())
    {
      localFile = new File(paramFile, "journal");
      if (!localFile.exists()) {
        break label148;
      }
      ((File)localObject).delete();
    }
    for (;;)
    {
      localObject = new bk(paramFile, paramInt1, paramInt2, paramLong);
      if (!d.exists()) {
        break label209;
      }
      try
      {
        ((bk)localObject).e();
        ((bk)localObject).f();
        k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(d, true), bp.a));
        return (bk)localObject;
      }
      catch (IOException localIOException)
      {
        label148:
        System.out.println("DiskLruCache " + paramFile + " is corrupt: " + localIOException.getMessage() + ", removing");
        ((bk)localObject).c();
      }
      a((File)localObject, localFile, false);
    }
    label209:
    paramFile.mkdirs();
    paramFile = new bk(paramFile, paramInt1, paramInt2, paramLong);
    paramFile.g();
    return paramFile;
  }
  
  private void a(a parama, boolean paramBoolean)
  {
    int i3 = 0;
    c localc;
    try
    {
      localc = a.a(parama);
      if (c.a(localc) != parama) {
        throw new IllegalStateException();
      }
    }
    finally {}
    int i2 = i3;
    if (paramBoolean)
    {
      i2 = i3;
      if (!c.d(localc))
      {
        int i1 = 0;
        for (;;)
        {
          i2 = i3;
          if (i1 >= i) {
            break;
          }
          if (a.b(parama)[i1] == 0)
          {
            parama.b();
            throw new IllegalStateException("Newly created entry didn't create value for index " + i1);
          }
          if (!localc.b(i1).exists())
          {
            parama.b();
            return;
          }
          i1 += 1;
        }
      }
    }
    for (;;)
    {
      long l1;
      if (i2 < i)
      {
        parama = localc.b(i2);
        if (paramBoolean)
        {
          if (parama.exists())
          {
            File localFile = localc.a(i2);
            parama.renameTo(localFile);
            l1 = c.b(localc)[i2];
            long l2 = localFile.length();
            c.b(localc)[i2] = l2;
            j = (j - l1 + l2);
          }
        }
        else {
          a(parama);
        }
      }
      else
      {
        m += 1;
        c.a(localc, null);
        if ((c.d(localc) | paramBoolean))
        {
          c.a(localc, true);
          k.write("CLEAN " + c.c(localc) + localc.a() + '\n');
          if (paramBoolean)
          {
            l1 = o;
            o = (1L + l1);
            c.a(localc, l1);
          }
        }
        for (;;)
        {
          k.flush();
          if ((j <= h) && (!h())) {
            break;
          }
          b.submit(p);
          break;
          l.remove(c.c(localc));
          k.write("REMOVE " + c.c(localc) + '\n');
        }
      }
      i2 += 1;
    }
  }
  
  private static void a(File paramFile)
  {
    if ((paramFile.exists()) && (!paramFile.delete())) {
      throw new IOException();
    }
  }
  
  private static void a(File paramFile1, File paramFile2, boolean paramBoolean)
  {
    if (paramBoolean) {
      a(paramFile2);
    }
    if (!paramFile1.renameTo(paramFile2)) {
      throw new IOException();
    }
  }
  
  private void d(String paramString)
  {
    int i1 = paramString.indexOf(' ');
    if (i1 == -1) {
      throw new IOException("unexpected journal line: " + paramString);
    }
    int i2 = i1 + 1;
    int i3 = paramString.indexOf(' ', i2);
    String str;
    if (i3 == -1)
    {
      str = paramString.substring(i2);
      if ((i1 == "REMOVE".length()) && (paramString.startsWith("REMOVE"))) {
        l.remove(str);
      }
    }
    else
    {
      str = paramString.substring(i2, i3);
    }
    for (;;)
    {
      c localc2 = (c)l.get(str);
      c localc1 = localc2;
      if (localc2 == null)
      {
        localc1 = new c(str, null);
        l.put(str, localc1);
      }
      if ((i3 != -1) && (i1 == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
      {
        paramString = paramString.substring(i3 + 1).split(" ");
        c.a(localc1, true);
        c.a(localc1, null);
        c.a(localc1, paramString);
        return;
      }
      if ((i3 == -1) && (i1 == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
      {
        c.a(localc1, new a(localc1, null));
        return;
      }
      if ((i3 == -1) && (i1 == "READ".length()) && (paramString.startsWith("READ"))) {
        break;
      }
      throw new IOException("unexpected journal line: " + paramString);
    }
  }
  
  private void e()
  {
    bo localbo = new bo(new FileInputStream(d), bp.a);
    int i1;
    try
    {
      String str1 = localbo.a();
      String str2 = localbo.a();
      String str3 = localbo.a();
      String str4 = localbo.a();
      String str5 = localbo.a();
      if ((!"libcore.io.DiskLruCache".equals(str1)) || (!"1".equals(str2)) || (!Integer.toString(g).equals(str3)) || (!Integer.toString(i).equals(str4)) || (!"".equals(str5))) {
        throw new IOException("unexpected journal header: [" + str1 + ", " + str2 + ", " + str4 + ", " + str5 + "]");
      }
    }
    finally
    {
      bp.a(localbo);
      throw ((Throwable)localObject);
    }
  }
  
  private void e(String paramString)
  {
    if (!a.matcher(paramString).matches()) {
      throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + paramString + "\"");
    }
  }
  
  private void f()
  {
    a(e);
    Iterator localIterator = l.values().iterator();
    while (localIterator.hasNext())
    {
      c localc = (c)localIterator.next();
      int i1;
      if (c.a(localc) == null)
      {
        i1 = 0;
        while (i1 < i)
        {
          j += c.b(localc)[i1];
          i1 += 1;
        }
      }
      else
      {
        c.a(localc, null);
        i1 = 0;
        while (i1 < i)
        {
          a(localc.a(i1));
          a(localc.b(i1));
          i1 += 1;
        }
        localIterator.remove();
      }
    }
  }
  
  private void g()
  {
    for (;;)
    {
      try
      {
        if (k != null) {
          k.close();
        }
        BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(e), bp.a));
        c localc;
        try
        {
          localBufferedWriter.write("libcore.io.DiskLruCache");
          localBufferedWriter.write("\n");
          localBufferedWriter.write("1");
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(g));
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(i));
          localBufferedWriter.write("\n");
          localBufferedWriter.write("\n");
          Iterator localIterator = l.values().iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          localc = (c)localIterator.next();
          if (c.a(localc) != null)
          {
            localBufferedWriter.write("DIRTY " + c.c(localc) + '\n');
            continue;
            localObject1 = finally;
          }
        }
        finally
        {
          localBufferedWriter.close();
        }
        ((Writer)localObject1).write("CLEAN " + c.c(localc) + localc.a() + '\n');
      }
      finally {}
    }
    ((Writer)localObject1).close();
    if (d.exists()) {
      a(d, f, true);
    }
    a(e, d, false);
    f.delete();
    k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(d, true), bp.a));
  }
  
  private boolean h()
  {
    return (m >= 2000) && (m >= l.size());
  }
  
  private void i()
  {
    if (k == null) {
      throw new IllegalStateException("cache is closed");
    }
  }
  
  private void j()
  {
    while (j > h)
    {
      String str = (String)((Map.Entry)l.entrySet().iterator().next()).getKey();
      c(str);
      if (n != null) {
        n.a(str);
      }
    }
  }
  
  /* Error */
  public b a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: invokespecial 133	com/amap/api/services/core/bk:i	()V
    //   9: aload_0
    //   10: aload_1
    //   11: invokespecial 136	com/amap/api/services/core/bk:e	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: getfield 75	com/amap/api/services/core/bk:l	Ljava/util/LinkedHashMap;
    //   18: aload_1
    //   19: invokevirtual 140	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast 16	com/amap/api/services/core/bk$c
    //   25: astore 6
    //   27: aload 6
    //   29: ifnonnull +10 -> 39
    //   32: aload 4
    //   34: astore_3
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_3
    //   38: areturn
    //   39: aload 4
    //   41: astore_3
    //   42: aload 6
    //   44: invokestatic 275	com/amap/api/services/core/bk$c:d	(Lcom/amap/api/services/core/bk$c;)Z
    //   47: ifeq -12 -> 35
    //   50: aload_0
    //   51: getfield 125	com/amap/api/services/core/bk:i	I
    //   54: anewarray 489	java/io/InputStream
    //   57: astore 5
    //   59: iconst_0
    //   60: istore_2
    //   61: iload_2
    //   62: aload_0
    //   63: getfield 125	com/amap/api/services/core/bk:i	I
    //   66: if_icmpge +65 -> 131
    //   69: aload 5
    //   71: iload_2
    //   72: new 387	java/io/FileInputStream
    //   75: dup
    //   76: aload 6
    //   78: iload_2
    //   79: invokevirtual 291	com/amap/api/services/core/bk$c:a	(I)Ljava/io/File;
    //   82: invokespecial 389	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   85: aastore
    //   86: iload_2
    //   87: iconst_1
    //   88: iadd
    //   89: istore_2
    //   90: goto -29 -> 61
    //   93: astore_1
    //   94: iconst_0
    //   95: istore_2
    //   96: aload 4
    //   98: astore_3
    //   99: iload_2
    //   100: aload_0
    //   101: getfield 125	com/amap/api/services/core/bk:i	I
    //   104: if_icmpge -69 -> 35
    //   107: aload 4
    //   109: astore_3
    //   110: aload 5
    //   112: iload_2
    //   113: aaload
    //   114: ifnull -79 -> 35
    //   117: aload 5
    //   119: iload_2
    //   120: aaload
    //   121: invokestatic 416	com/amap/api/services/core/bp:a	(Ljava/io/Closeable;)V
    //   124: iload_2
    //   125: iconst_1
    //   126: iadd
    //   127: istore_2
    //   128: goto -32 -> 96
    //   131: aload_0
    //   132: aload_0
    //   133: getfield 130	com/amap/api/services/core/bk:m	I
    //   136: iconst_1
    //   137: iadd
    //   138: putfield 130	com/amap/api/services/core/bk:m	I
    //   141: aload_0
    //   142: getfield 160	com/amap/api/services/core/bk:k	Ljava/io/Writer;
    //   145: new 162	java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial 163	java/lang/StringBuilder:<init>	()V
    //   152: ldc_w 491
    //   155: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload_1
    //   159: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: bipush 10
    //   164: invokevirtual 172	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   167: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   170: invokevirtual 494	java/io/Writer:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   173: pop
    //   174: aload_0
    //   175: invokespecial 320	com/amap/api/services/core/bk:h	()Z
    //   178: ifeq +15 -> 193
    //   181: aload_0
    //   182: getfield 95	com/amap/api/services/core/bk:b	Ljava/util/concurrent/ThreadPoolExecutor;
    //   185: aload_0
    //   186: getfield 102	com/amap/api/services/core/bk:p	Ljava/util/concurrent/Callable;
    //   189: invokevirtual 324	java/util/concurrent/ThreadPoolExecutor:submit	(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
    //   192: pop
    //   193: new 13	com/amap/api/services/core/bk$b
    //   196: dup
    //   197: aload_0
    //   198: aload_1
    //   199: aload 6
    //   201: invokestatic 145	com/amap/api/services/core/bk$c:e	(Lcom/amap/api/services/core/bk$c;)J
    //   204: aload 5
    //   206: aload 6
    //   208: invokestatic 298	com/amap/api/services/core/bk$c:b	(Lcom/amap/api/services/core/bk$c;)[J
    //   211: aconst_null
    //   212: invokespecial 497	com/amap/api/services/core/bk$b:<init>	(Lcom/amap/api/services/core/bk;Ljava/lang/String;J[Ljava/io/InputStream;[JLcom/amap/api/services/core/bl;)V
    //   215: astore_3
    //   216: goto -181 -> 35
    //   219: astore_1
    //   220: aload_0
    //   221: monitorexit
    //   222: aload_1
    //   223: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	224	0	this	bk
    //   0	224	1	paramString	String
    //   60	68	2	i1	int
    //   34	182	3	localObject1	Object
    //   1	107	4	localObject2	Object
    //   57	148	5	arrayOfInputStream	InputStream[]
    //   25	182	6	localc	c
    // Exception table:
    //   from	to	target	type
    //   61	86	93	java/io/FileNotFoundException
    //   5	27	219	finally
    //   42	59	219	finally
    //   61	86	219	finally
    //   99	107	219	finally
    //   117	124	219	finally
    //   131	193	219	finally
    //   193	216	219	finally
  }
  
  public void a(bn parambn)
  {
    n = parambn;
  }
  
  /* Error */
  public boolean a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 160	com/amap/api/services/core/bk:k	Ljava/io/Writer;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnonnull +9 -> 17
    //   11: iconst_1
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: iconst_0
    //   18: istore_1
    //   19: goto -6 -> 13
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	27	0	this	bk
    //   12	7	1	bool	boolean
    //   6	2	2	localWriter	Writer
    //   22	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  public a b(String paramString)
  {
    return a(paramString, -1L);
  }
  
  public void b()
  {
    try
    {
      i();
      j();
      k.flush();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void c()
  {
    close();
    bp.a(c);
  }
  
  public boolean c(String paramString)
  {
    int i1 = 0;
    for (;;)
    {
      try
      {
        i();
        e(paramString);
        c localc = (c)l.get(paramString);
        Object localObject;
        if (localc != null)
        {
          localObject = c.a(localc);
          if (localObject == null) {}
        }
        else
        {
          bool = false;
          return bool;
          j -= c.b(localc)[i1];
          c.b(localc)[i1] = 0L;
          i1 += 1;
        }
        if (i1 < i)
        {
          localObject = localc.a(i1);
          if ((!((File)localObject).exists()) || (((File)localObject).delete())) {
            continue;
          }
          throw new IOException("failed to delete " + localObject);
        }
      }
      finally {}
      m += 1;
      k.append("REMOVE " + paramString + '\n');
      l.remove(paramString);
      if (h()) {
        b.submit(p);
      }
      boolean bool = true;
    }
  }
  
  public void close()
  {
    for (;;)
    {
      try
      {
        Object localObject1 = k;
        if (localObject1 == null) {
          return;
        }
        localObject1 = new ArrayList(l.values()).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          c localc = (c)((Iterator)localObject1).next();
          if (c.a(localc) == null) {
            continue;
          }
          c.a(localc).b();
          continue;
        }
        j();
      }
      finally {}
      k.close();
      k = null;
    }
  }
  
  public final class a
  {
    private final bk.c b;
    private final boolean[] c;
    private boolean d;
    private boolean e;
    
    private a(bk.c paramc)
    {
      b = paramc;
      if (bk.c.d(paramc)) {}
      for (this$1 = null;; this$1 = new boolean[bk.e(bk.this)])
      {
        c = bk.this;
        return;
      }
    }
    
    public OutputStream a(int paramInt)
    {
      if ((paramInt < 0) || (paramInt >= bk.e(bk.this))) {
        throw new IllegalArgumentException("Expected index " + paramInt + " to " + "be greater than 0 and less than the maximum value count " + "of " + bk.e(bk.this));
      }
      synchronized (bk.this)
      {
        if (bk.c.a(b) != this) {
          throw new IllegalStateException();
        }
      }
      if (!bk.c.d(b)) {
        c[paramInt] = true;
      }
      File localFile = b.b(paramInt);
      try
      {
        Object localObject2 = new FileOutputStream(localFile);
        localObject2 = new a((OutputStream)localObject2, null);
        return (OutputStream)localObject2;
      }
      catch (FileNotFoundException localFileNotFoundException1)
      {
        for (;;)
        {
          bk.f(bk.this).mkdirs();
          try
          {
            FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
          }
          catch (FileNotFoundException localFileNotFoundException2)
          {
            OutputStream localOutputStream = bk.d();
            return localOutputStream;
          }
        }
      }
    }
    
    public void a()
    {
      if (d)
      {
        bk.a(bk.this, this, false);
        c(bk.c.c(b));
      }
      for (;;)
      {
        e = true;
        return;
        bk.a(bk.this, this, true);
      }
    }
    
    public void b()
    {
      bk.a(bk.this, this, false);
    }
    
    class a
      extends FilterOutputStream
    {
      private a(OutputStream paramOutputStream)
      {
        super();
      }
      
      public void close()
      {
        try
        {
          out.close();
          return;
        }
        catch (IOException localIOException)
        {
          bk.a.a(bk.a.this, true);
        }
      }
      
      public void flush()
      {
        try
        {
          out.flush();
          return;
        }
        catch (IOException localIOException)
        {
          bk.a.a(bk.a.this, true);
        }
      }
      
      public void write(int paramInt)
      {
        try
        {
          out.write(paramInt);
          return;
        }
        catch (IOException localIOException)
        {
          bk.a.a(bk.a.this, true);
        }
      }
      
      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        try
        {
          out.write(paramArrayOfByte, paramInt1, paramInt2);
          return;
        }
        catch (IOException paramArrayOfByte)
        {
          bk.a.a(bk.a.this, true);
        }
      }
    }
  }
  
  public final class b
    implements Closeable
  {
    private final String b;
    private final long c;
    private final InputStream[] d;
    private final long[] e;
    
    private b(String paramString, long paramLong, InputStream[] paramArrayOfInputStream, long[] paramArrayOfLong)
    {
      b = paramString;
      c = paramLong;
      d = paramArrayOfInputStream;
      e = paramArrayOfLong;
    }
    
    public InputStream a(int paramInt)
    {
      return d[paramInt];
    }
    
    public void close()
    {
      InputStream[] arrayOfInputStream = d;
      int j = arrayOfInputStream.length;
      int i = 0;
      while (i < j)
      {
        bp.a(arrayOfInputStream[i]);
        i += 1;
      }
    }
  }
  
  final class c
  {
    private final String b;
    private final long[] c;
    private boolean d;
    private bk.a e;
    private long f;
    
    private c(String paramString)
    {
      b = paramString;
      c = new long[bk.e(bk.this)];
    }
    
    private void a(String[] paramArrayOfString)
    {
      if (paramArrayOfString.length != bk.e(bk.this)) {
        throw b(paramArrayOfString);
      }
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          c[i] = Long.parseLong(paramArrayOfString[i]);
          i += 1;
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw b(paramArrayOfString);
      }
    }
    
    private IOException b(String[] paramArrayOfString)
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }
    
    public File a(int paramInt)
    {
      return new File(bk.f(bk.this), b + "." + paramInt);
    }
    
    public String a()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      long[] arrayOfLong = c;
      int j = arrayOfLong.length;
      int i = 0;
      while (i < j)
      {
        long l = arrayOfLong[i];
        localStringBuilder.append(' ').append(l);
        i += 1;
      }
      return localStringBuilder.toString();
    }
    
    public File b(int paramInt)
    {
      return new File(bk.f(bk.this), b + "." + paramInt + ".tmp");
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */