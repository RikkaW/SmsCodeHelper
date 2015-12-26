package com.google.android.mms.pdu;

import java.io.ByteArrayOutputStream;

public class QuotedPrintable
{
  private static byte ESCAPE_CHAR = 61;
  
  public static final byte[] decodeQuotedPrintable(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i = 0;
    for (;;)
    {
      if (i < paramArrayOfByte.length)
      {
        int j = paramArrayOfByte[i];
        if (j == ESCAPE_CHAR)
        {
          if (('\r' == (char)paramArrayOfByte[(i + 1)]) && ('\n' == (char)paramArrayOfByte[(i + 2)]))
          {
            i += 2;
            break label133;
          }
          i += 1;
          try
          {
            j = Character.digit((char)paramArrayOfByte[i], 16);
            i += 1;
            int k = Character.digit((char)paramArrayOfByte[i], 16);
            if ((j == -1) || (k == -1)) {
              break;
            }
            localByteArrayOutputStream.write((char)((j << 4) + k));
          }
          catch (ArrayIndexOutOfBoundsException paramArrayOfByte)
          {
            return null;
          }
        }
        localByteArrayOutputStream.write(j);
        break label133;
      }
      return localByteArrayOutputStream.toByteArray();
      label133:
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.QuotedPrintable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */