import android.net.Uri;
import android.util.Log;

public class pa
{
  private Uri a = null;
  private int b = 0;
  private byte[] c = null;
  
  public int a()
  {
    try
    {
      int i = b;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void a(int paramInt)
  {
    if ((paramInt < 0) && (paramInt > 2)) {
      try
      {
        throw new IllegalArgumentException("Bad state: " + paramInt);
      }
      finally {}
    }
    b = paramInt;
  }
  
  public void a(Uri paramUri)
  {
    try
    {
      Log.d("TransactionState", "handleTransactionProcess setContentUri" + paramUri);
      a = paramUri;
      return;
    }
    finally
    {
      paramUri = finally;
      throw paramUri;
    }
  }
  
  public void a(byte[] paramArrayOfByte)
  {
    try
    {
      c = paramArrayOfByte;
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  public Uri b()
  {
    try
    {
      Uri localUri = a;
      return localUri;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public byte[] c()
  {
    try
    {
      byte[] arrayOfByte = c;
      return arrayOfByte;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     pa
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */