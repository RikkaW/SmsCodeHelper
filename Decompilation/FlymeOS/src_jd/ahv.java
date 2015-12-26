import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ahv
{
  private static ahv a = null;
  
  static String a(Object paramObject, String paramString)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("#", new DecimalFormatSymbols(Locale.US));
    localDecimalFormat.applyPattern(paramString);
    return localDecimalFormat.format(paramObject);
  }
  
  static byte[] a(int paramInt)
  {
    return new byte[] { (byte)(paramInt & 0xFF), (byte)(paramInt >> 8 & 0xFF), (byte)(paramInt >> 16 & 0xFF), (byte)(paramInt >> 24 & 0xFF) };
  }
  
  public static byte[] a(long paramLong)
  {
    byte[] arrayOfByte = new byte[8];
    int i = 0;
    while (i < arrayOfByte.length)
    {
      arrayOfByte[i] = ((byte)(int)(paramLong >> i * 8 & 0xFF));
      i += 1;
    }
    return arrayOfByte;
  }
  
  static byte[] a(String paramString)
  {
    return a(Integer.parseInt(paramString));
  }
  
  static byte[] b(int paramInt)
  {
    return new byte[] { (byte)(paramInt & 0xFF), (byte)(paramInt >> 8 & 0xFF) };
  }
  
  static byte[] b(String paramString)
  {
    return b(Integer.parseInt(paramString));
  }
}

/* Location:
 * Qualified Name:     ahv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */