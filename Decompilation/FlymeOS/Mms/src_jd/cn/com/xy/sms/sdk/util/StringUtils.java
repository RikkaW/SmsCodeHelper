package cn.com.xy.sms.sdk.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;

public class StringUtils
{
  public static final String MPLUG86 = "+86";
  private static final String a = "0123456789ABCDEF";
  private static final String b = "UTF-8";
  private static final String c = "+86";
  private static final String d = "\\+86";
  private static final String e = "0086";
  private static final String f = "86";
  private static final String g = "17951";
  private static final String h = "12593";
  public static final String phoneFiled10193 = "10193";
  public static final String phoneFiled12520 = "12520";
  public static final String phoneFiled17908 = "17908";
  public static final String phoneFiled17909 = "17909";
  public static final String phoneFiled17911 = "17911";
  public static final String phoneFiled179110 = "179110";
  
  public static byte[] MD5(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      if (localMessageDigest != null)
      {
        localMessageDigest.update(paramArrayOfByte);
        arrayOfByte = localMessageDigest.digest();
      }
      return arrayOfByte;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        localNoSuchAlgorithmException.printStackTrace();
        Object localObject = null;
      }
    }
  }
  
  public static List<String> arryToList(JSONArray paramJSONArray)
  {
    if (paramJSONArray != null) {
      try
      {
        if (paramJSONArray.length() > 0)
        {
          ArrayList localArrayList = new ArrayList();
          int i = 0;
          for (;;)
          {
            if (i >= paramJSONArray.length()) {
              return localArrayList;
            }
            localArrayList.add(paramJSONArray.get(i).toString());
            i += 1;
          }
        }
        return null;
      }
      catch (JSONException paramJSONArray)
      {
        paramJSONArray.printStackTrace();
      }
    }
  }
  
  public static String bytesToHexString(byte[] paramArrayOfByte)
  {
    int j = 0;
    if (paramArrayOfByte == null) {
      return null;
    }
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfByte.length) {
        return String.valueOf(arrayOfChar);
      }
      arrayOfChar[j] = "0123456789abcdef".charAt(paramArrayOfByte[i] >> 4 & 0xF);
      int k = paramArrayOfByte[i];
      j += 1;
      arrayOfChar[j] = "0123456789abcdef".charAt(k & 0xF);
      i += 1;
      j += 1;
    }
  }
  
  public static byte[] compressGZip(byte[] paramArrayOfByte)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.finish();
      localGZIPOutputStream.close();
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      localThrowable1.printStackTrace();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        localByteArrayOutputStream.close();
        return paramArrayOfByte;
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
      localThrowable1 = localThrowable1;
      paramArrayOfByte = null;
    }
    return paramArrayOfByte;
  }
  
  /* Error */
  public static String decode(String paramString)
  {
    // Byte code:
    //   0: new 124	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: aload_0
    //   5: invokevirtual 149	java/lang/String:length	()I
    //   8: iconst_2
    //   9: idiv
    //   10: invokespecial 152	java/io/ByteArrayOutputStream:<init>	(I)V
    //   13: astore_3
    //   14: iconst_0
    //   15: istore_1
    //   16: aload_3
    //   17: astore_2
    //   18: iload_1
    //   19: aload_0
    //   20: invokevirtual 149	java/lang/String:length	()I
    //   23: if_icmplt +25 -> 48
    //   26: aload_3
    //   27: astore_2
    //   28: new 109	java/lang/String
    //   31: dup
    //   32: aload_3
    //   33: invokevirtual 142	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   36: ldc 14
    //   38: invokespecial 155	java/lang/String:<init>	([BLjava/lang/String;)V
    //   41: astore_0
    //   42: aload_3
    //   43: invokevirtual 143	java/io/ByteArrayOutputStream:close	()V
    //   46: aload_0
    //   47: areturn
    //   48: aload_3
    //   49: astore_2
    //   50: aload_3
    //   51: ldc 11
    //   53: aload_0
    //   54: iload_1
    //   55: invokevirtual 119	java/lang/String:charAt	(I)C
    //   58: invokevirtual 159	java/lang/String:indexOf	(I)I
    //   61: iconst_4
    //   62: ishl
    //   63: ldc 11
    //   65: aload_0
    //   66: iload_1
    //   67: iconst_1
    //   68: iadd
    //   69: invokevirtual 119	java/lang/String:charAt	(I)C
    //   72: invokevirtual 159	java/lang/String:indexOf	(I)I
    //   75: ior
    //   76: invokevirtual 161	java/io/ByteArrayOutputStream:write	(I)V
    //   79: iload_1
    //   80: iconst_2
    //   81: iadd
    //   82: istore_1
    //   83: goto -67 -> 16
    //   86: astore_2
    //   87: aload_2
    //   88: invokevirtual 162	java/io/IOException:printStackTrace	()V
    //   91: aload_0
    //   92: areturn
    //   93: astore 4
    //   95: aconst_null
    //   96: astore_0
    //   97: aload_0
    //   98: astore_2
    //   99: aload 4
    //   101: invokevirtual 144	java/lang/Throwable:printStackTrace	()V
    //   104: aload_0
    //   105: ifnull +7 -> 112
    //   108: aload_0
    //   109: invokevirtual 143	java/io/ByteArrayOutputStream:close	()V
    //   112: aconst_null
    //   113: areturn
    //   114: astore_0
    //   115: aload_0
    //   116: invokevirtual 162	java/io/IOException:printStackTrace	()V
    //   119: goto -7 -> 112
    //   122: astore_0
    //   123: aconst_null
    //   124: astore_2
    //   125: aload_2
    //   126: ifnull +7 -> 133
    //   129: aload_2
    //   130: invokevirtual 143	java/io/ByteArrayOutputStream:close	()V
    //   133: aload_0
    //   134: athrow
    //   135: astore_2
    //   136: aload_2
    //   137: invokevirtual 162	java/io/IOException:printStackTrace	()V
    //   140: goto -7 -> 133
    //   143: astore_0
    //   144: goto -19 -> 125
    //   147: astore 4
    //   149: aload_3
    //   150: astore_0
    //   151: goto -54 -> 97
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	paramString	String
    //   15	68	1	i	int
    //   17	33	2	localByteArrayOutputStream1	ByteArrayOutputStream
    //   86	2	2	localIOException1	java.io.IOException
    //   98	32	2	str	String
    //   135	2	2	localIOException2	java.io.IOException
    //   13	137	3	localByteArrayOutputStream2	ByteArrayOutputStream
    //   93	7	4	localThrowable1	Throwable
    //   147	1	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   42	46	86	java/io/IOException
    //   0	14	93	java/lang/Throwable
    //   108	112	114	java/io/IOException
    //   0	14	122	finally
    //   129	133	135	java/io/IOException
    //   18	26	143	finally
    //   28	42	143	finally
    //   50	79	143	finally
    //   99	104	143	finally
    //   18	26	147	java/lang/Throwable
    //   28	42	147	java/lang/Throwable
    //   50	79	147	java/lang/Throwable
  }
  
  public static String encode(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      StringBuilder localStringBuilder = new StringBuilder(paramString.length << 1);
      int i = 0;
      for (;;)
      {
        if (i >= paramString.length) {
          return localStringBuilder.toString();
        }
        localStringBuilder.append("0123456789ABCDEF".charAt((paramString[i] & 0xF0) >> 4));
        localStringBuilder.append("0123456789ABCDEF".charAt(paramString[i] & 0xF));
        i += 1;
      }
      return null;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static String getFileMD5(String paramString)
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    try
    {
      paramString = new FileInputStream(paramString);
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      for (;;)
      {
        int i = paramString.read(arrayOfByte);
        if (i <= 0)
        {
          paramString.close();
          return getMD5(localMessageDigest.digest());
        }
        localMessageDigest.update(arrayOfByte, 0, i);
      }
      return null;
    }
    catch (Throwable paramString) {}
  }
  
  public static long getLongByString(String paramString)
  {
    long l = -1L;
    try
    {
      if (!isNull(paramString)) {
        l = Long.parseLong(paramString);
      }
      return l;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return -1L;
  }
  
  public static String getMD5(String paramString)
  {
    return getMD5(paramString.getBytes());
  }
  
  public static String getMD5(byte[] paramArrayOfByte)
  {
    return bytesToHexString(MD5(paramArrayOfByte));
  }
  
  public static String getNoNullString(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return paramString.trim();
  }
  
  public static String getPhoneNumberNo86(String paramString)
  {
    String str = paramString;
    if (!isNull(paramString))
    {
      paramString = paramString.replace(" ", "").replace("-", "").replace("(", "").replace(")", "");
      if (!paramString.startsWith("+86")) {
        break label59;
      }
      str = paramString.replaceFirst("\\+86", "");
    }
    label59:
    do
    {
      do
      {
        return str;
        if (paramString.startsWith("0086")) {
          return paramString.replaceFirst("0086", "");
        }
        if (paramString.startsWith("86")) {
          return paramString.replaceFirst("86", "");
        }
        if ((paramString.startsWith("17951")) && (paramString.length() > 10)) {
          return paramString.replaceFirst("17951", "");
        }
        if ((paramString.startsWith("12593")) && (paramString.length() > 10)) {
          return paramString.replaceFirst("12593", "");
        }
        str = paramString;
      } while (!paramString.startsWith("12520"));
      str = paramString;
    } while (paramString.length() <= 10);
    return paramString.replaceFirst("12520", "");
  }
  
  public static String getSubString(String paramString)
  {
    String str = paramString;
    if (!isNull(paramString))
    {
      str = paramString;
      if (paramString.length() >= 7) {
        str = paramString.substring(0, 7);
      }
    }
    return str;
  }
  
  public static String getTwoDigitType(String paramString)
  {
    String str;
    if (isNull(paramString)) {
      str = "";
    }
    do
    {
      return str;
      if (paramString.length() < 2) {
        return "0" + paramString;
      }
      str = paramString;
    } while (paramString.length() <= 2);
    return "99";
  }
  
  public static String getValueByKey(Map<String, String> paramMap, String paramString)
  {
    if ((paramMap != null) && (!paramMap.isEmpty()) && (!isNull(paramString))) {
      return (String)paramMap.get(paramString);
    }
    return "";
  }
  
  public static String handlerAssemble2(String paramString)
  {
    int i = 0;
    try
    {
      paramString = paramString.getBytes("UTF-8");
      int k = paramString.length;
      int j = 0;
      for (;;)
      {
        if (i >= k) {
          return new String(paramString);
        }
        paramString[j] = Byte.valueOf(paramString[i] - (j + 1) % 3).byteValue();
        j += 1;
        i += 1;
      }
      return "";
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean isNull(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0) || (paramString.trim().equalsIgnoreCase("null"));
  }
  
  public static boolean isNull2(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }
  
  public static boolean isNumber(String paramString)
  {
    if (!isNull(paramString)) {
      paramString = getPhoneNumberNo86(paramString);
    }
    return Pattern.compile("[0-9]*").matcher(paramString).matches();
  }
  
  public static boolean isPhoneNumber(String paramString)
  {
    if (isNull(paramString)) {
      return false;
    }
    return sj(getPhoneNumberNo86(paramString));
  }
  
  public static String[] jsonArryToArray(JSONArray paramJSONArray)
  {
    if ((paramJSONArray != null) && (paramJSONArray.length() > 0))
    {
      String[] arrayOfString = new String[paramJSONArray.length()];
      int i = 0;
      for (;;)
      {
        if (i >= paramJSONArray.length()) {
          return arrayOfString;
        }
        try
        {
          arrayOfString[i] = paramJSONArray.getString(i);
          i += 1;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            localJSONException.printStackTrace();
          }
        }
      }
    }
    return null;
  }
  
  public static String replaceBlank(String paramString)
  {
    if (isNull(paramString)) {
      return null;
    }
    return paramString.replaceAll("\\s", "");
  }
  
  public static boolean sj(String paramString)
  {
    if ((paramString == null) || (paramString.length() != 11) || ("13800138000".equals(paramString))) {}
    while ((!paramString.startsWith("13")) && (!paramString.startsWith("14")) && (!paramString.startsWith("15")) && (!paramString.startsWith("18"))) {
      return false;
    }
    return true;
  }
  
  /* Error */
  public static org.w3c.dom.Document stringConvertXML(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 205	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   4: ifeq +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: astore_2
    //   11: aload_0
    //   12: ldc_w 341
    //   15: invokevirtual 344	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   18: iconst_m1
    //   19: if_icmpeq +17 -> 36
    //   22: aload_0
    //   23: aload_0
    //   24: ldc_w 341
    //   27: invokevirtual 344	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   30: iconst_2
    //   31: iadd
    //   32: invokevirtual 346	java/lang/String:substring	(I)Ljava/lang/String;
    //   35: astore_2
    //   36: new 171	java/lang/StringBuilder
    //   39: dup
    //   40: aload_1
    //   41: invokespecial 251	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   44: astore_0
    //   45: aload_0
    //   46: aload_2
    //   47: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: invokestatic 352	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   54: astore_2
    //   55: new 354	java/io/ByteArrayInputStream
    //   58: dup
    //   59: aload_0
    //   60: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: ldc_w 356
    //   66: invokevirtual 169	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   69: invokespecial 357	java/io/ByteArrayInputStream:<init>	([B)V
    //   72: astore_1
    //   73: aload_1
    //   74: astore_0
    //   75: aload_2
    //   76: invokevirtual 361	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   79: aload_1
    //   80: invokevirtual 367	javax/xml/parsers/DocumentBuilder:parse	(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
    //   83: astore_2
    //   84: aload_1
    //   85: invokestatic 372	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   88: aload_2
    //   89: areturn
    //   90: astore_2
    //   91: aconst_null
    //   92: astore_1
    //   93: aload_1
    //   94: astore_0
    //   95: aload_2
    //   96: invokevirtual 144	java/lang/Throwable:printStackTrace	()V
    //   99: aload_1
    //   100: invokestatic 372	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   103: aconst_null
    //   104: areturn
    //   105: astore_0
    //   106: aconst_null
    //   107: astore_2
    //   108: aload_0
    //   109: astore_1
    //   110: aload_2
    //   111: invokestatic 372	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   114: aload_1
    //   115: athrow
    //   116: astore_1
    //   117: aload_0
    //   118: astore_2
    //   119: goto -9 -> 110
    //   122: astore_2
    //   123: goto -30 -> 93
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	paramString1	String
    //   0	126	1	paramString2	String
    //   10	79	2	localObject1	Object
    //   90	6	2	localThrowable1	Throwable
    //   107	12	2	localObject2	Object
    //   122	1	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   11	36	90	java/lang/Throwable
    //   36	73	90	java/lang/Throwable
    //   11	36	105	finally
    //   36	73	105	finally
    //   75	84	116	finally
    //   95	99	116	finally
    //   75	84	122	java/lang/Throwable
  }
  
  public static byte[] uncompressGZip(byte[] paramArrayOfByte)
  {
    paramArrayOfByte = new ByteArrayInputStream(paramArrayOfByte);
    GZIPInputStream localGZIPInputStream = new GZIPInputStream(paramArrayOfByte);
    byte[] arrayOfByte = new byte['Ѐ'];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    for (;;)
    {
      int i = localGZIPInputStream.read(arrayOfByte, 0, 1024);
      if (i == -1)
      {
        arrayOfByte = localByteArrayOutputStream.toByteArray();
        localByteArrayOutputStream.flush();
        localByteArrayOutputStream.close();
        localGZIPInputStream.close();
        paramArrayOfByte.close();
        return arrayOfByte;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.StringUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */