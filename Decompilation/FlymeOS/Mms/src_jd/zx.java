import android.content.Context;
import java.io.IOException;
import java.nio.ByteBuffer;

public class zx
{
  private static long[] b = new long['Ā'];
  private zi a;
  private Context c;
  
  static
  {
    int i = 0;
    while (i < 256)
    {
      long l1 = i;
      int j = 0;
      if (j < 8)
      {
        if (((int)l1 & 0x1) != 0) {}
        for (long l2 = -7661587058870466123L;; l2 = 0L)
        {
          l1 = l1 >> 1 ^ l2;
          j += 1;
          break;
        }
      }
      b[i] = l1;
      i += 1;
    }
  }
  
  public zx(Context paramContext)
  {
    a = zk.a(paramContext, "imgcache", 500, 20971520, 3);
    c = paramContext;
  }
  
  public static final long a(byte[] paramArrayOfByte)
  {
    long l = -1L;
    int i = 0;
    int j = paramArrayOfByte.length;
    while (i < j)
    {
      l = l >> 8 ^ b[(((int)l ^ paramArrayOfByte[i]) & 0xFF)];
      i += 1;
    }
    return l;
  }
  
  private static boolean a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int j = paramArrayOfByte1.length;
    if (paramArrayOfByte2.length < j) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label34;
      }
      if (paramArrayOfByte1[i] != paramArrayOfByte2[i]) {
        break;
      }
      i += 1;
    }
    label34:
    return true;
  }
  
  public static byte[] a(String paramString)
  {
    int i = 0;
    byte[] arrayOfByte = new byte[paramString.length() * 2];
    paramString = paramString.toCharArray();
    int k = paramString.length;
    int j = 0;
    while (i < k)
    {
      int m = paramString[i];
      int n = j + 1;
      arrayOfByte[j] = ((byte)(m & 0xFF));
      j = n + 1;
      arrayOfByte[n] = ((byte)(m >> 8));
      i += 1;
    }
    return arrayOfByte;
  }
  
  private static byte[] b(String paramString, int paramInt)
  {
    return a(paramString + "+" + paramInt);
  }
  
  public zx.a a(String arg1, int paramInt)
  {
    Object localObject1 = null;
    byte[] arrayOfByte1 = b(???, paramInt);
    long l = a(arrayOfByte1);
    try
    {
      byte[] arrayOfByte2;
      synchronized (a)
      {
        arrayOfByte2 = a.a(l);
        if (arrayOfByte2 == null) {
          return null;
        }
      }
      ??? = (String)localObject2;
      if (a(arrayOfByte1, arrayOfByte2)) {
        ??? = new zx.a(arrayOfByte2, arrayOfByte1.length);
      }
      return (zx.a)???;
    }
    catch (IOException ???) {}
    return null;
  }
  
  public void a()
  {
    zk.a(c);
  }
  
  public void a(String paramString, int paramInt, byte[] arg3)
  {
    byte[] arrayOfByte = b(paramString, paramInt);
    long l = a(arrayOfByte);
    paramString = ByteBuffer.allocate(arrayOfByte.length + ???.length);
    paramString.put(arrayOfByte);
    paramString.put(???);
    try
    {
      synchronized (a)
      {
        a.a(l, paramString.array());
        return;
      }
    }
    catch (IOException paramString)
    {
      for (;;) {}
    }
  }
  
  public static class a
  {
    public byte[] a;
    public int b;
    
    public a(byte[] paramArrayOfByte, int paramInt)
    {
      a = paramArrayOfByte;
      b = paramInt;
    }
  }
}

/* Location:
 * Qualified Name:     zx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */