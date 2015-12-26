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

public final class ahl
  implements Closeable
{
  static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
  private static final OutputStream p = new ahn();
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
  private final LinkedHashMap<String, ahl.b> l = new LinkedHashMap(0, 0.75F, true);
  private int m;
  private long n = 0L;
  private final Callable<Void> o = new ahm(this);
  
  private ahl(File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    c = paramFile;
    g = paramInt1;
    d = new File(paramFile, "journal");
    e = new File(paramFile, "journal.tmp");
    f = new File(paramFile, "journal.bkp");
    i = paramInt2;
    h = paramLong;
  }
  
  private ahl.a a(String paramString, long paramLong)
  {
    for (;;)
    {
      ahl.a locala;
      try
      {
        Object localObject = k;
        if (localObject == null)
        {
          paramString = null;
          return paramString;
        }
        g();
        e(paramString);
        localObject = (ahl.b)l.get(paramString);
        if ((paramLong != -1L) && ((localObject == null) || (ahl.b.e((ahl.b)localObject) != paramLong))) {
          break label185;
        }
        if (localObject == null)
        {
          localObject = new ahl.b(paramString, null);
          l.put(paramString, localObject);
          locala = new ahl.a((ahl.b)localObject, null);
          ahl.b.a((ahl.b)localObject, locala);
          k.write("DIRTY " + paramString + '\n');
          k.flush();
          paramString = locala;
          continue;
        }
        locala = ahl.b.a((ahl.b)localObject);
      }
      finally {}
      if (locala != null)
      {
        paramString = null;
      }
      else
      {
        continue;
        label185:
        paramString = null;
      }
    }
  }
  
  public static ahl a(File paramFile, int paramInt1, int paramInt2, long paramLong)
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
      localObject = new ahl(paramFile, paramInt1, paramInt2, paramLong);
      if (!d.exists()) {
        break label209;
      }
      try
      {
        ((ahl)localObject).c();
        ((ahl)localObject).d();
        k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(d, true), ahy.a));
        return (ahl)localObject;
      }
      catch (IOException localIOException)
      {
        label148:
        System.out.println("DiskLruCache " + paramFile + " is corrupt: " + localIOException.getMessage() + ", removing");
        ((ahl)localObject).a();
      }
      a((File)localObject, localFile, false);
    }
    label209:
    paramFile.mkdirs();
    paramFile = new ahl(paramFile, paramInt1, paramInt2, paramLong);
    paramFile.e();
    return paramFile;
  }
  
  private void a(ahl.a parama, boolean paramBoolean)
  {
    int i3 = 0;
    ahl.b localb;
    try
    {
      localb = ahl.a.a(parama);
      if (ahl.b.a(localb) != parama) {
        throw new IllegalStateException();
      }
    }
    finally {}
    int i2 = i3;
    if (paramBoolean)
    {
      i2 = i3;
      if (!ahl.b.d(localb))
      {
        int i1 = 0;
        for (;;)
        {
          i2 = i3;
          if (i1 >= i) {
            break;
          }
          if (ahl.a.b(parama)[i1] == 0)
          {
            parama.b();
            throw new IllegalStateException("Newly created entry didn't create value for index " + i1);
          }
          if (!localb.b(i1).exists())
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
        parama = localb.b(i2);
        if (paramBoolean)
        {
          if (parama.exists())
          {
            File localFile = localb.a(i2);
            parama.renameTo(localFile);
            l1 = ahl.b.b(localb)[i2];
            long l2 = localFile.length();
            ahl.b.b(localb)[i2] = l2;
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
        ahl.b.a(localb, null);
        if ((ahl.b.d(localb) | paramBoolean))
        {
          ahl.b.a(localb, true);
          k.write("CLEAN " + ahl.b.c(localb) + localb.a() + '\n');
          if (paramBoolean)
          {
            l1 = n;
            n = (1L + l1);
            ahl.b.a(localb, l1);
          }
        }
        for (;;)
        {
          k.flush();
          if ((j <= h) && (!f())) {
            break;
          }
          b.submit(o);
          break;
          l.remove(ahl.b.c(localb));
          k.write("REMOVE " + ahl.b.c(localb) + '\n');
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
  
  private void c()
  {
    ahw localahw = new ahw(new FileInputStream(d), ahy.a);
    int i1;
    try
    {
      String str1 = localahw.a();
      String str2 = localahw.a();
      String str3 = localahw.a();
      String str4 = localahw.a();
      String str5 = localahw.a();
      if ((!"libcore.io.DiskLruCache".equals(str1)) || (!"1".equals(str2)) || (!Integer.toString(g).equals(str3)) || (!Integer.toString(i).equals(str4)) || (!"".equals(str5))) {
        throw new IOException("unexpected journal header: [" + str1 + ", " + str2 + ", " + str4 + ", " + str5 + "]");
      }
    }
    finally
    {
      ahy.a(localahw);
      throw ((Throwable)localObject);
    }
  }
  
  private void d()
  {
    a(e);
    Iterator localIterator = l.values().iterator();
    while (localIterator.hasNext())
    {
      ahl.b localb = (ahl.b)localIterator.next();
      int i1;
      if (ahl.b.a(localb) == null)
      {
        i1 = 0;
        while (i1 < i)
        {
          j += ahl.b.b(localb)[i1];
          i1 += 1;
        }
      }
      else
      {
        ahl.b.a(localb, null);
        i1 = 0;
        while (i1 < i)
        {
          a(localb.a(i1));
          a(localb.b(i1));
          i1 += 1;
        }
        localIterator.remove();
      }
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
      ahl.b localb2 = (ahl.b)l.get(str);
      ahl.b localb1 = localb2;
      if (localb2 == null)
      {
        localb1 = new ahl.b(str, null);
        l.put(str, localb1);
      }
      if ((i3 != -1) && (i1 == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
      {
        paramString = paramString.substring(i3 + 1).split(" ");
        ahl.b.a(localb1, true);
        ahl.b.a(localb1, null);
        ahl.b.a(localb1, paramString);
        return;
      }
      if ((i3 == -1) && (i1 == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
      {
        ahl.b.a(localb1, new ahl.a(localb1, null));
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
    for (;;)
    {
      try
      {
        if (k != null) {
          k.close();
        }
        BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(e), ahy.a));
        ahl.b localb;
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
          localb = (ahl.b)localIterator.next();
          if (ahl.b.a(localb) != null)
          {
            localBufferedWriter.write("DIRTY " + ahl.b.c(localb) + '\n');
            continue;
            localObject1 = finally;
          }
        }
        finally
        {
          localBufferedWriter.close();
        }
        ((Writer)localObject1).write("CLEAN " + ahl.b.c(localb) + localb.a() + '\n');
      }
      finally {}
    }
    ((Writer)localObject1).close();
    if (d.exists()) {
      a(d, f, true);
    }
    a(e, d, false);
    f.delete();
    k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(d, true), ahy.a));
  }
  
  private void e(String paramString)
  {
    if (!a.matcher(paramString).matches()) {
      throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + paramString + "\"");
    }
  }
  
  private boolean f()
  {
    return (m >= 2000) && (m >= l.size());
  }
  
  private void g()
  {
    if (k == null) {
      throw new IllegalStateException("cache is closed");
    }
  }
  
  private void h()
  {
    while (j > h) {
      c((String)((Map.Entry)l.entrySet().iterator().next()).getKey());
    }
  }
  
  /* Error */
  public ahl.c a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 131	ahl:k	Ljava/io/Writer;
    //   9: astore_3
    //   10: aload_3
    //   11: ifnonnull +10 -> 21
    //   14: aload 4
    //   16: astore_3
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_3
    //   20: areturn
    //   21: aload_0
    //   22: invokespecial 133	ahl:g	()V
    //   25: aload_0
    //   26: aload_1
    //   27: invokespecial 136	ahl:e	(Ljava/lang/String;)V
    //   30: aload_0
    //   31: getfield 73	ahl:l	Ljava/util/LinkedHashMap;
    //   34: aload_1
    //   35: invokevirtual 140	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   38: checkcast 13	ahl$b
    //   41: astore 6
    //   43: aload 4
    //   45: astore_3
    //   46: aload 6
    //   48: ifnull -31 -> 17
    //   51: aload 4
    //   53: astore_3
    //   54: aload 6
    //   56: invokestatic 273	ahl$b:d	(Lahl$b;)Z
    //   59: ifeq -42 -> 17
    //   62: aload_0
    //   63: getfield 123	ahl:i	I
    //   66: anewarray 481	java/io/InputStream
    //   69: astore 5
    //   71: iconst_0
    //   72: istore_2
    //   73: iload_2
    //   74: aload_0
    //   75: getfield 123	ahl:i	I
    //   78: if_icmpge +65 -> 143
    //   81: aload 5
    //   83: iload_2
    //   84: new 340	java/io/FileInputStream
    //   87: dup
    //   88: aload 6
    //   90: iload_2
    //   91: invokevirtual 289	ahl$b:a	(I)Ljava/io/File;
    //   94: invokespecial 342	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   97: aastore
    //   98: iload_2
    //   99: iconst_1
    //   100: iadd
    //   101: istore_2
    //   102: goto -29 -> 73
    //   105: astore_1
    //   106: iconst_0
    //   107: istore_2
    //   108: aload 4
    //   110: astore_3
    //   111: iload_2
    //   112: aload_0
    //   113: getfield 123	ahl:i	I
    //   116: if_icmpge -99 -> 17
    //   119: aload 4
    //   121: astore_3
    //   122: aload 5
    //   124: iload_2
    //   125: aaload
    //   126: ifnull -109 -> 17
    //   129: aload 5
    //   131: iload_2
    //   132: aaload
    //   133: invokestatic 373	ahy:a	(Ljava/io/Closeable;)V
    //   136: iload_2
    //   137: iconst_1
    //   138: iadd
    //   139: istore_2
    //   140: goto -32 -> 108
    //   143: aload_0
    //   144: aload_0
    //   145: getfield 128	ahl:m	I
    //   148: iconst_1
    //   149: iadd
    //   150: putfield 128	ahl:m	I
    //   153: aload_0
    //   154: getfield 131	ahl:k	Ljava/io/Writer;
    //   157: new 160	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   164: ldc_w 483
    //   167: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload_1
    //   171: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: bipush 10
    //   176: invokevirtual 170	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   179: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokevirtual 486	java/io/Writer:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   185: pop
    //   186: aload_0
    //   187: invokespecial 318	ahl:f	()Z
    //   190: ifeq +15 -> 205
    //   193: aload_0
    //   194: getfield 93	ahl:b	Ljava/util/concurrent/ThreadPoolExecutor;
    //   197: aload_0
    //   198: getfield 100	ahl:o	Ljava/util/concurrent/Callable;
    //   201: invokevirtual 322	java/util/concurrent/ThreadPoolExecutor:submit	(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
    //   204: pop
    //   205: new 16	ahl$c
    //   208: dup
    //   209: aload_0
    //   210: aload_1
    //   211: aload 6
    //   213: invokestatic 145	ahl$b:e	(Lahl$b;)J
    //   216: aload 5
    //   218: aload 6
    //   220: invokestatic 296	ahl$b:b	(Lahl$b;)[J
    //   223: aconst_null
    //   224: invokespecial 489	ahl$c:<init>	(Lahl;Ljava/lang/String;J[Ljava/io/InputStream;[JLahm;)V
    //   227: astore_3
    //   228: goto -211 -> 17
    //   231: astore_1
    //   232: aload_0
    //   233: monitorexit
    //   234: aload_1
    //   235: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	236	0	this	ahl
    //   0	236	1	paramString	String
    //   72	68	2	i1	int
    //   9	219	3	localObject1	Object
    //   1	119	4	localObject2	Object
    //   69	148	5	arrayOfInputStream	InputStream[]
    //   41	178	6	localb	ahl.b
    // Exception table:
    //   from	to	target	type
    //   73	98	105	java/io/FileNotFoundException
    //   5	10	231	finally
    //   21	43	231	finally
    //   54	71	231	finally
    //   73	98	231	finally
    //   111	119	231	finally
    //   129	136	231	finally
    //   143	205	231	finally
    //   205	228	231	finally
  }
  
  public void a()
  {
    close();
    ahy.a(c);
  }
  
  public ahl.a b(String paramString)
  {
    return a(paramString, -1L);
  }
  
  public boolean c(String paramString)
  {
    int i1 = 0;
    for (;;)
    {
      try
      {
        g();
        e(paramString);
        ahl.b localb = (ahl.b)l.get(paramString);
        Object localObject;
        if (localb != null)
        {
          localObject = ahl.b.a(localb);
          if (localObject == null) {}
        }
        else
        {
          bool = false;
          return bool;
          j -= ahl.b.b(localb)[i1];
          ahl.b.b(localb)[i1] = 0L;
          i1 += 1;
        }
        if (i1 < i)
        {
          localObject = localb.a(i1);
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
      if (f()) {
        b.submit(o);
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
          ahl.b localb = (ahl.b)((Iterator)localObject1).next();
          if (ahl.b.a(localb) == null) {
            continue;
          }
          ahl.b.a(localb).b();
          continue;
        }
        h();
      }
      finally {}
      k.close();
      k = null;
    }
  }
  
  public final class a
  {
    private final ahl.b b;
    private final boolean[] c;
    private boolean d;
    private boolean e;
    
    private a(ahl.b paramb)
    {
      b = paramb;
      if (ahl.b.d(paramb)) {}
      for (this$1 = null;; this$1 = new boolean[ahl.e(ahl.this)])
      {
        c = ahl.this;
        return;
      }
    }
    
    public OutputStream a(int paramInt)
    {
      if ((paramInt < 0) || (paramInt >= ahl.e(ahl.this))) {
        throw new IllegalArgumentException("Expected index " + paramInt + " to " + "be greater than 0 and less than the maximum value count " + "of " + ahl.e(ahl.this));
      }
      synchronized (ahl.this)
      {
        if (ahl.b.a(b) != this) {
          throw new IllegalStateException();
        }
      }
      if (!ahl.b.d(b)) {
        c[paramInt] = true;
      }
      File localFile = b.b(paramInt);
      try
      {
        Object localObject2 = new FileOutputStream(localFile);
        localObject2 = new ahl.a.a((OutputStream)localObject2, null);
        return (OutputStream)localObject2;
      }
      catch (FileNotFoundException localFileNotFoundException1)
      {
        for (;;)
        {
          ahl.f(ahl.this).mkdirs();
          try
          {
            FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
          }
          catch (FileNotFoundException localFileNotFoundException2)
          {
            OutputStream localOutputStream = ahl.b();
            return localOutputStream;
          }
        }
      }
    }
    
    public void a()
    {
      if (d)
      {
        ahl.a(ahl.this, this, false);
        c(ahl.b.c(b));
      }
      for (;;)
      {
        e = true;
        return;
        ahl.a(ahl.this, this, true);
      }
    }
    
    public void b()
    {
      ahl.a(ahl.this, this, false);
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
          ahl.a.a(ahl.a.this, true);
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
          ahl.a.a(ahl.a.this, true);
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
          ahl.a.a(ahl.a.this, true);
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
          ahl.a.a(ahl.a.this, true);
        }
      }
    }
  }
  
  final class b
  {
    private final String b;
    private final long[] c;
    private boolean d;
    private ahl.a e;
    private long f;
    
    private b(String paramString)
    {
      b = paramString;
      c = new long[ahl.e(ahl.this)];
    }
    
    private void a(String[] paramArrayOfString)
    {
      if (paramArrayOfString.length != ahl.e(ahl.this)) {
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
      return new File(ahl.f(ahl.this), b + "." + paramInt);
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
      return new File(ahl.f(ahl.this), b + "." + paramInt + ".tmp");
    }
  }
  
  public final class c
    implements Closeable
  {
    private final String b;
    private final long c;
    private final InputStream[] d;
    private final long[] e;
    
    private c(String paramString, long paramLong, InputStream[] paramArrayOfInputStream, long[] paramArrayOfLong)
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
        ahy.a(arrayOfInputStream[i]);
        i += 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     ahl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */