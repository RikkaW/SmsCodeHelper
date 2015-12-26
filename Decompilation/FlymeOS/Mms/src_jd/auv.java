import android.util.Log;
import java.io.UnsupportedEncodingException;
import org.apache.http.Header;

public abstract class auv
  extends atg
{
  public auv(String paramString)
  {
    b(paramString);
  }
  
  public static String a(byte[] paramArrayOfByte, String paramString)
  {
    if (paramArrayOfByte == null) {}
    for (paramArrayOfByte = null; paramArrayOfByte != null; paramArrayOfByte = new String(paramArrayOfByte, paramString)) {
      try
      {
        if (!paramArrayOfByte.startsWith("﻿")) {
          break;
        }
        return paramArrayOfByte.substring(1);
      }
      catch (UnsupportedEncodingException paramArrayOfByte)
      {
        Log.e("TextHttpResponseHandler", "Encoding response into string failed", paramArrayOfByte);
        return null;
      }
    }
    return paramArrayOfByte;
  }
  
  public abstract void a(int paramInt, Header[] paramArrayOfHeader, String paramString);
  
  public abstract void a(int paramInt, Header[] paramArrayOfHeader, String paramString, Throwable paramThrowable);
  
  public void a(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte)
  {
    a(paramInt, paramArrayOfHeader, a(paramArrayOfByte, e()));
  }
  
  public void b(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable)
  {
    a(paramInt, paramArrayOfHeader, a(paramArrayOfByte, e()), paramThrowable);
  }
}

/* Location:
 * Qualified Name:     auv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */