import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class agb
{
  protected File a;
  protected int[] b;
  private ArrayList c;
  private boolean d = false;
  
  protected agb(File paramFile, ArrayList paramArrayList, int[] paramArrayOfInt)
  {
    a = paramFile;
    c = paramArrayList;
    b = paramArrayOfInt;
  }
  
  protected final void a(boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  public byte[] a()
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext())
    {
      byte[] arrayOfByte = (byte[])localIterator.next();
      try
      {
        localDataOutputStream.writeInt(arrayOfByte.length);
        localDataOutputStream.write(arrayOfByte);
      }
      catch (IOException localIOException2) {}
    }
    try
    {
      localByteArrayOutputStream.close();
      localDataOutputStream.close();
      return localByteArrayOutputStream.toByteArray();
    }
    catch (IOException localIOException1)
    {
      for (;;) {}
    }
  }
  
  protected final boolean b()
  {
    return d;
  }
  
  protected final int c()
  {
    if (c == null) {
      return 0;
    }
    int i = 0;
    int j = 0;
    if (i < c.size())
    {
      if (c.get(i) != null) {}
      for (int k = ((byte[])c.get(i)).length;; k = 0)
      {
        j += k;
        i += 1;
        break;
      }
    }
    return j;
  }
}

/* Location:
 * Qualified Name:     agb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */