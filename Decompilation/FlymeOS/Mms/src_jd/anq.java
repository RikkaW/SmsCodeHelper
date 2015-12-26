public class anq
{
  public static int a(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9')) {
      return paramChar - '0';
    }
    if ((paramChar >= 'A') && (paramChar <= 'F')) {
      return paramChar - 'A' + 10;
    }
    if ((paramChar >= 'a') && (paramChar <= 'f')) {
      return paramChar - 'a' + 10;
    }
    throw new RuntimeException("invalid hex char '" + paramChar + "'");
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfByte.length) {
        return localStringBuilder.toString();
      }
      localStringBuilder.append("0123456789abcdef".charAt(paramArrayOfByte[i] >> 4 & 0xF));
      localStringBuilder.append("0123456789abcdef".charAt(paramArrayOfByte[i] & 0xF));
      i += 1;
    }
  }
  
  public static byte[] a(String paramString)
  {
    Object localObject;
    if (paramString == null)
    {
      localObject = null;
      return (byte[])localObject;
    }
    int j = paramString.length();
    byte[] arrayOfByte = new byte[j / 2];
    int i = 0;
    for (;;)
    {
      localObject = arrayOfByte;
      if (i >= j) {
        break;
      }
      arrayOfByte[(i / 2)] = ((byte)(a(paramString.charAt(i)) << 4 | a(paramString.charAt(i + 1))));
      i += 2;
    }
  }
}

/* Location:
 * Qualified Name:     anq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */