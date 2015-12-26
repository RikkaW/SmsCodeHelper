package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import java.util.Random;

public class StringUtils
{
  private static final char[] AMP_ENCODE;
  private static final char[] APOS_ENCODE;
  private static final char[] GT_ENCODE;
  private static final char[] LT_ENCODE;
  private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
  private static char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  private static Random randGen;
  
  static
  {
    APOS_ENCODE = "&apos;".toCharArray();
    AMP_ENCODE = "&amp;".toCharArray();
    LT_ENCODE = "&lt;".toCharArray();
    GT_ENCODE = "&gt;".toCharArray();
    randGen = new Random();
  }
  
  public static String encodeBase64(byte[] paramArrayOfByte)
  {
    return String.valueOf(Base64Coder.encode(paramArrayOfByte));
  }
  
  public static String escapeForXML(String paramString)
  {
    if (paramString == null) {
      paramString = null;
    }
    int j;
    int k;
    char[] arrayOfChar;
    StringBuilder localStringBuilder;
    do
    {
      return paramString;
      j = 0;
      k = 0;
      arrayOfChar = paramString.toCharArray();
      int m = arrayOfChar.length;
      localStringBuilder = new StringBuilder((int)(m * 1.3D));
      if (j < m)
      {
        int n = arrayOfChar[j];
        int i;
        if (n > 62) {
          i = k;
        }
        for (;;)
        {
          j += 1;
          k = i;
          break;
          if (n == 60)
          {
            if (j > k) {
              localStringBuilder.append(arrayOfChar, k, j - k);
            }
            i = j + 1;
            localStringBuilder.append(LT_ENCODE);
          }
          else if (n == 62)
          {
            if (j > k) {
              localStringBuilder.append(arrayOfChar, k, j - k);
            }
            i = j + 1;
            localStringBuilder.append(GT_ENCODE);
          }
          else if (n == 38)
          {
            if (j > k) {
              localStringBuilder.append(arrayOfChar, k, j - k);
            }
            if ((m > j + 5) && (arrayOfChar[(j + 1)] == '#') && (Character.isDigit(arrayOfChar[(j + 2)])) && (Character.isDigit(arrayOfChar[(j + 3)])) && (Character.isDigit(arrayOfChar[(j + 4)])))
            {
              i = k;
              if (arrayOfChar[(j + 5)] == ';') {}
            }
            else
            {
              i = j + 1;
              localStringBuilder.append(AMP_ENCODE);
            }
          }
          else if (n == 34)
          {
            if (j > k) {
              localStringBuilder.append(arrayOfChar, k, j - k);
            }
            i = j + 1;
            localStringBuilder.append(QUOTE_ENCODE);
          }
          else
          {
            i = k;
            if (n == 39)
            {
              if (j > k) {
                localStringBuilder.append(arrayOfChar, k, j - k);
              }
              i = j + 1;
              localStringBuilder.append(APOS_ENCODE);
            }
          }
        }
      }
    } while (k == 0);
    if (j > k) {
      localStringBuilder.append(arrayOfChar, k, j - k);
    }
    return localStringBuilder.toString();
  }
  
  public static boolean isValidXmlChar(char paramChar)
  {
    return ((paramChar >= ' ') && (paramChar <= 55295)) || ((paramChar >= 57344) && (paramChar <= 65533)) || ((paramChar >= 65536) && (paramChar <= 1114111)) || (paramChar == '\t') || (paramChar == '\n') || (paramChar == '\r');
  }
  
  public static String randomString(int paramInt)
  {
    if (paramInt < 1) {
      return null;
    }
    char[] arrayOfChar = new char[paramInt];
    paramInt = 0;
    while (paramInt < arrayOfChar.length)
    {
      arrayOfChar[paramInt] = numbersAndLetters[randGen.nextInt(71)];
      paramInt += 1;
    }
    return new String(arrayOfChar);
  }
  
  public static final String replace(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null) {
      localObject = null;
    }
    do
    {
      return (String)localObject;
      i = paramString1.indexOf(paramString2, 0);
      localObject = paramString1;
    } while (i < 0);
    Object localObject = paramString1.toCharArray();
    paramString3 = paramString3.toCharArray();
    int k = paramString2.length();
    StringBuilder localStringBuilder = new StringBuilder(localObject.length);
    localStringBuilder.append((char[])localObject, 0, i).append(paramString3);
    int j = i + k;
    for (int i = j;; i = j)
    {
      j = paramString1.indexOf(paramString2, j);
      if (j <= 0) {
        break;
      }
      localStringBuilder.append((char[])localObject, i, j - i).append(paramString3);
      j += k;
    }
    localStringBuilder.append((char[])localObject, i, localObject.length - i);
    return localStringBuilder.toString();
  }
  
  public static String stripInvalidXMLChars(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    int i = 0;
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (isValidXmlChar(c)) {
        localStringBuilder.append(c);
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static final String unescapeFromXML(String paramString)
  {
    return replace(replace(replace(replace(replace(paramString, "&lt;", "<"), "&gt;", ">"), "&quot;", "\""), "&apos;", "'"), "&amp;", "&");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.StringUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */