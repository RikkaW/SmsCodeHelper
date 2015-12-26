import android.util.Base64;
import android.util.Log;
import com.ted.android.contacts.common.DataBus;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class apm
{
  private static final byte[] a = "0000000000000000".getBytes();
  private static final byte[] b = a();
  private static final byte[] c = b();
  
  public static String a(String paramString)
  {
    return a(paramString, b);
  }
  
  private static String a(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      IvParameterSpec localIvParameterSpec = new IvParameterSpec(a);
      paramArrayOfByte = new SecretKeySpec(paramArrayOfByte, "AES");
      Cipher localCipher = Cipher.getInstance("AES/CBC/NoPadding");
      localCipher.init(1, paramArrayOfByte, localIvParameterSpec);
      paramString = new String(Base64.encode(localCipher.doFinal(d(paramString).getBytes("utf-8")), 0));
      return paramString;
    }
    catch (Exception paramString)
    {
      Log.e("EncryptUtil", "encrypt error:", paramString);
    }
    return null;
  }
  
  public static byte[] a()
  {
    return e(c());
  }
  
  public static String b(String paramString)
  {
    return b(paramString, b);
  }
  
  private static String b(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      paramString = Base64.decode(paramString, 0);
      IvParameterSpec localIvParameterSpec = new IvParameterSpec(a);
      paramArrayOfByte = new SecretKeySpec(paramArrayOfByte, "AES");
      Cipher localCipher = Cipher.getInstance("AES/CBC/NoPadding");
      localCipher.init(2, paramArrayOfByte, localIvParameterSpec);
      paramString = new String(localCipher.doFinal(paramString)).trim();
      return paramString;
    }
    catch (Exception paramString)
    {
      Log.e("EncryptUtil", "decrypt error:", paramString);
    }
    return null;
  }
  
  private static byte[] b()
  {
    return e(DataBus.FILE_MASK);
  }
  
  private static String c()
  {
    return DataBus.CONTACT;
  }
  
  public static String c(String paramString)
  {
    str = paramString;
    if (paramString != null)
    {
      str = paramString;
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        str = paramString;
        localMessageDigest.update(paramString.getBytes());
        str = paramString;
        for (paramString = new BigInteger(1, localMessageDigest.digest()).toString(16);; paramString = "0" + paramString)
        {
          str = paramString;
          if (paramString.length() >= 32) {
            return paramString;
          }
          str = paramString;
        }
        return str;
      }
      catch (NoSuchAlgorithmException paramString)
      {
        Log.e("EncryptUtil", "init MessageDigest instance error", paramString);
      }
    }
  }
  
  private static String d(String paramString)
  {
    try
    {
      i = paramString.getBytes("UTF-8").length % 16;
      paramString = new StringBuilder(paramString);
      j = 0;
      if (j >= 16 - i) {
        return paramString.toString();
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        int j;
        int i = paramString.length();
        continue;
        paramString.append(' ');
        j += 1;
      }
    }
  }
  
  private static byte[] e(String paramString)
  {
    return c(paramString).getBytes();
  }
}

/* Location:
 * Qualified Name:     apm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */