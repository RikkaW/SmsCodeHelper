import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class aii$f
{
  public String a;
  public String b;
  
  private static f a(DataInputStream paramDataInputStream)
  {
    f localf = new f();
    a = paramDataInputStream.readUTF();
    b = paramDataInputStream.readUTF();
    return localf;
  }
  
  public static ArrayList<f> a(byte[] paramArrayOfByte)
  {
    ArrayList localArrayList = new ArrayList();
    DataInputStream localDataInputStream;
    if (paramArrayOfByte != null)
    {
      paramArrayOfByte = new ByteArrayInputStream(paramArrayOfByte);
      localDataInputStream = new DataInputStream(paramArrayOfByte);
    }
    try
    {
      while (localDataInputStream.available() > 0) {
        localArrayList.add(a(localDataInputStream));
      }
      try
      {
        paramArrayOfByte.close();
        if (localDataInputStream != null) {
          localDataInputStream.close();
        }
      }
      catch (IOException paramArrayOfByte)
      {
        for (;;)
        {
          paramArrayOfByte.printStackTrace();
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      if (paramArrayOfByte != null) {}
      for (;;)
      {
        try
        {
          paramArrayOfByte.close();
          if (localDataInputStream != null) {
            localDataInputStream.close();
          }
          return localArrayList;
        }
        catch (IOException paramArrayOfByte)
        {
          paramArrayOfByte.printStackTrace();
          return localArrayList;
        }
        if (paramArrayOfByte != null) {}
        try
        {
          paramArrayOfByte.close();
          if (localDataInputStream != null)
          {
            localDataInputStream.close();
            return localArrayList;
          }
        }
        catch (IOException paramArrayOfByte)
        {
          paramArrayOfByte.printStackTrace();
          return localArrayList;
        }
      }
    }
    finally
    {
      if (paramArrayOfByte == null) {}
    }
    throw ((Throwable)localObject);
  }
}

/* Location:
 * Qualified Name:     aii.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */