import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ahu
{
  private static List<File> a = new ArrayList();
  private ahl b;
  private int c;
  
  private ahu(File paramFile, int paramInt, long paramLong)
  {
    c = paramInt;
    b = ahl.a(paramFile, paramInt, 1, paramLong);
  }
  
  public static ahu a(File paramFile, int paramInt, long paramLong)
  {
    try
    {
      if (a.contains(paramFile)) {
        throw new IllegalStateException("Cache dir " + paramFile.getAbsolutePath() + " was used before.");
      }
    }
    finally {}
    a.add(paramFile);
    paramFile = new ahu(paramFile, paramInt, paramLong);
    return paramFile;
  }
  
  /* Error */
  private Map<String, Serializable> a(ahl.c paramc)
  {
    // Byte code:
    //   0: new 78	java/io/ObjectInputStream
    //   3: dup
    //   4: new 80	java/io/BufferedInputStream
    //   7: dup
    //   8: aload_1
    //   9: iconst_0
    //   10: invokevirtual 85	ahl$c:a	(I)Ljava/io/InputStream;
    //   13: invokespecial 88	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   16: invokespecial 89	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   19: astore_2
    //   20: aload_2
    //   21: astore_1
    //   22: aload_2
    //   23: invokevirtual 93	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   26: checkcast 95	java/util/Map
    //   29: astore_3
    //   30: aload_2
    //   31: ifnull +7 -> 38
    //   34: aload_2
    //   35: invokevirtual 98	java/io/ObjectInputStream:close	()V
    //   38: aload_3
    //   39: areturn
    //   40: astore_3
    //   41: aconst_null
    //   42: astore_1
    //   43: new 100	java/lang/RuntimeException
    //   46: dup
    //   47: aload_3
    //   48: invokespecial 103	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   51: athrow
    //   52: astore_3
    //   53: aload_1
    //   54: astore_2
    //   55: aload_3
    //   56: astore_1
    //   57: aload_2
    //   58: ifnull +7 -> 65
    //   61: aload_2
    //   62: invokevirtual 98	java/io/ObjectInputStream:close	()V
    //   65: aload_1
    //   66: athrow
    //   67: astore_1
    //   68: aconst_null
    //   69: astore_2
    //   70: goto -13 -> 57
    //   73: astore_3
    //   74: aload_2
    //   75: astore_1
    //   76: goto -33 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	this	ahu
    //   0	79	1	paramc	ahl.c
    //   19	56	2	localObject1	Object
    //   29	10	3	localMap	Map
    //   40	8	3	localClassNotFoundException1	ClassNotFoundException
    //   52	4	3	localObject2	Object
    //   73	1	3	localClassNotFoundException2	ClassNotFoundException
    // Exception table:
    //   from	to	target	type
    //   0	20	40	java/lang/ClassNotFoundException
    //   22	30	52	finally
    //   43	52	52	finally
    //   0	20	67	finally
    //   22	30	73	java/lang/ClassNotFoundException
  }
  
  private String b(String paramString)
  {
    return c(paramString);
  }
  
  private String c(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes("UTF-8"));
      paramString = new BigInteger(1, localMessageDigest.digest()).toString(16);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new AssertionError();
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new AssertionError();
    }
  }
  
  public OutputStream a(String paramString, Map<String, ? extends Serializable> paramMap)
  {
    paramString = b.b(b(paramString));
    if (paramString == null) {
      return null;
    }
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(paramString.a(0));
      localObjectOutputStream.writeObject(paramMap);
      paramMap = new ahu.a(localObjectOutputStream, paramString, null);
      return paramMap;
    }
    catch (IOException paramMap)
    {
      paramString.b();
      throw paramMap;
    }
  }
  
  public Map<String, Serializable> a(String paramString)
  {
    paramString = b.a(b(paramString));
    if (paramString == null) {
      return null;
    }
    try
    {
      Map localMap = a(paramString);
      return localMap;
    }
    finally
    {
      paramString.close();
    }
  }
  
  public void a()
  {
    try
    {
      if (a != null) {
        a.clear();
      }
      if (b != null) {
        b.close();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public void b(String paramString, Map<String, ? extends Serializable> paramMap)
  {
    try
    {
      paramString = a(paramString, paramMap);
      if (paramString != null) {
        paramString.close();
      }
      return;
    }
    finally
    {
      if (0 != 0) {
        throw new NullPointerException();
      }
    }
  }
  
  static class a
    extends FilterOutputStream
  {
    private final ahl.a a;
    private boolean b = false;
    
    private a(OutputStream paramOutputStream, ahl.a parama)
    {
      super();
      a = parama;
    }
    
    public void close()
    {
      Object localObject = null;
      try
      {
        super.close();
        if (b) {
          a.b();
        }
        while (localObject != null)
        {
          throw ((Throwable)localObject);
          a.a();
        }
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
    }
    
    public void flush()
    {
      try
      {
        super.flush();
        return;
      }
      catch (IOException localIOException)
      {
        b = true;
        throw localIOException;
      }
    }
    
    public void write(int paramInt)
    {
      try
      {
        super.write(paramInt);
        return;
      }
      catch (IOException localIOException)
      {
        b = true;
        throw localIOException;
      }
    }
    
    public void write(byte[] paramArrayOfByte)
    {
      try
      {
        super.write(paramArrayOfByte);
        return;
      }
      catch (IOException paramArrayOfByte)
      {
        b = true;
        throw paramArrayOfByte;
      }
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      try
      {
        super.write(paramArrayOfByte, paramInt1, paramInt2);
        return;
      }
      catch (IOException paramArrayOfByte)
      {
        b = true;
        throw paramArrayOfByte;
      }
    }
  }
}

/* Location:
 * Qualified Name:     ahu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */