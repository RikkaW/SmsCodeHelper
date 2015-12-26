package com.android.mms.service;

import android.database.Cursor;
import android.text.TextUtils;

public class ApnSettings
{
  private static final String[] APN_PROJECTION = { "type", "mmsc", "mmsproxy", "mmsport", "name", "apn", "bearer", "protocol", "roaming_protocol", "authtype", "mvno_type", "mvno_match_data", "proxy", "port", "server", "user", "password" };
  private final String mDebugText;
  private final String mProxyAddress;
  private final int mProxyPort;
  private final String mServiceCenter;
  
  public ApnSettings(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    mServiceCenter = paramString1;
    mProxyAddress = paramString2;
    mProxyPort = paramInt;
    mDebugText = paramString3;
  }
  
  private static String getDebugText(Cursor paramCursor)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("APN [");
    int i = 0;
    if (i < paramCursor.getColumnCount())
    {
      String str1 = paramCursor.getColumnName(i);
      String str2 = paramCursor.getString(i);
      if (TextUtils.isEmpty(str2)) {}
      for (;;)
      {
        i += 1;
        break;
        if (i > 0) {
          localStringBuilder.append(' ');
        }
        localStringBuilder.append(str1).append('=').append(str2);
      }
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  private static boolean isValidApnType(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return true;
    }
    paramString1 = paramString1.split(",");
    int j = paramString1.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label60;
      }
      String str = paramString1[i].trim();
      if ((str.equals(paramString2)) || (str.equals("*"))) {
        break;
      }
      i += 1;
    }
    label60:
    return false;
  }
  
  /* Error */
  public static ApnSettings load(android.content.Context paramContext, String paramString, int paramInt)
    throws com.android.mms.service.exception.ApnException
  {
    // Byte code:
    //   0: ldc -125
    //   2: iconst_2
    //   3: invokestatic 137	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   6: ifeq +28 -> 34
    //   9: ldc -125
    //   11: new 69	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   18: ldc -117
    //   20: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_1
    //   24: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: invokestatic 143	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   33: pop
    //   34: aconst_null
    //   35: astore 4
    //   37: aconst_null
    //   38: astore 5
    //   40: aload_1
    //   41: ifnull +275 -> 316
    //   44: aload_1
    //   45: invokevirtual 115	java/lang/String:trim	()Ljava/lang/String;
    //   48: astore_1
    //   49: aload_1
    //   50: invokestatic 95	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   53: ifne +18 -> 71
    //   56: ldc -111
    //   58: astore 4
    //   60: iconst_1
    //   61: anewarray 16	java/lang/String
    //   64: astore 5
    //   66: aload 5
    //   68: iconst_0
    //   69: aload_1
    //   70: aastore
    //   71: aconst_null
    //   72: astore_1
    //   73: aload_0
    //   74: aload_0
    //   75: invokevirtual 151	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   78: getstatic 157	android/provider/Telephony$Carriers:CONTENT_URI	Landroid/net/Uri;
    //   81: new 69	java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   88: ldc -97
    //   90: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: iload_2
    //   94: invokevirtual 162	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   97: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   100: invokestatic 168	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   103: getstatic 52	com/android/mms/service/ApnSettings:APN_PROJECTION	[Ljava/lang/String;
    //   106: aload 4
    //   108: aload 5
    //   110: aconst_null
    //   111: invokestatic 174	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   114: astore 4
    //   116: aload 4
    //   118: ifnull +309 -> 427
    //   121: iconst_m1
    //   122: istore_3
    //   123: aload 4
    //   125: astore_1
    //   126: aload 4
    //   128: invokeinterface 178 1 0
    //   133: ifeq +294 -> 427
    //   136: aload 4
    //   138: astore_1
    //   139: aload 4
    //   141: iconst_0
    //   142: invokeinterface 89 2 0
    //   147: ldc -76
    //   149: invokestatic 182	com/android/mms/service/ApnSettings:isValidApnType	(Ljava/lang/String;Ljava/lang/String;)Z
    //   152: ifeq -29 -> 123
    //   155: aload 4
    //   157: astore_1
    //   158: aload 4
    //   160: iconst_1
    //   161: invokeinterface 89 2 0
    //   166: invokestatic 186	com/android/mms/service/ApnSettings:trimWithNullCheck	(Ljava/lang/String;)Ljava/lang/String;
    //   169: astore_0
    //   170: aload 4
    //   172: astore_1
    //   173: aload_0
    //   174: invokestatic 95	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   177: ifne -54 -> 123
    //   180: aload 4
    //   182: astore_1
    //   183: aload_0
    //   184: invokestatic 191	android/net/NetworkUtils:trimV4AddrZeros	(Ljava/lang/String;)Ljava/lang/String;
    //   187: astore 6
    //   189: aload 4
    //   191: astore_1
    //   192: new 193	java/net/URI
    //   195: dup
    //   196: aload 6
    //   198: invokespecial 196	java/net/URI:<init>	(Ljava/lang/String;)V
    //   201: pop
    //   202: aload 4
    //   204: astore_1
    //   205: aload 4
    //   207: iconst_2
    //   208: invokeinterface 89 2 0
    //   213: invokestatic 186	com/android/mms/service/ApnSettings:trimWithNullCheck	(Ljava/lang/String;)Ljava/lang/String;
    //   216: astore 5
    //   218: aload 5
    //   220: astore_0
    //   221: iload_3
    //   222: istore_2
    //   223: aload 4
    //   225: astore_1
    //   226: aload 5
    //   228: invokestatic 95	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   231: ifne +51 -> 282
    //   234: aload 4
    //   236: astore_1
    //   237: aload 5
    //   239: invokestatic 191	android/net/NetworkUtils:trimV4AddrZeros	(Ljava/lang/String;)Ljava/lang/String;
    //   242: astore 5
    //   244: aload 4
    //   246: astore_1
    //   247: aload 4
    //   249: iconst_3
    //   250: invokeinterface 89 2 0
    //   255: invokestatic 186	com/android/mms/service/ApnSettings:trimWithNullCheck	(Ljava/lang/String;)Ljava/lang/String;
    //   258: astore 7
    //   260: aload 5
    //   262: astore_0
    //   263: iload_3
    //   264: istore_2
    //   265: aload 7
    //   267: ifnull +15 -> 282
    //   270: aload 4
    //   272: astore_1
    //   273: aload 7
    //   275: invokestatic 202	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   278: istore_2
    //   279: aload 5
    //   281: astore_0
    //   282: aload 4
    //   284: astore_1
    //   285: new 2	com/android/mms/service/ApnSettings
    //   288: dup
    //   289: aload 6
    //   291: aload_0
    //   292: iload_2
    //   293: aload 4
    //   295: invokestatic 204	com/android/mms/service/ApnSettings:getDebugText	(Landroid/database/Cursor;)Ljava/lang/String;
    //   298: invokespecial 206	com/android/mms/service/ApnSettings:<init>	(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    //   301: astore_0
    //   302: aload 4
    //   304: ifnull +10 -> 314
    //   307: aload 4
    //   309: invokeinterface 209 1 0
    //   314: aload_0
    //   315: areturn
    //   316: aconst_null
    //   317: astore_1
    //   318: goto -269 -> 49
    //   321: astore_0
    //   322: aload 4
    //   324: astore_1
    //   325: new 125	com/android/mms/service/exception/ApnException
    //   328: dup
    //   329: new 69	java/lang/StringBuilder
    //   332: dup
    //   333: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   336: ldc -45
    //   338: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: aload 6
    //   343: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: invokespecial 212	com/android/mms/service/exception/ApnException:<init>	(Ljava/lang/String;)V
    //   352: athrow
    //   353: astore_0
    //   354: aload_1
    //   355: ifnull +9 -> 364
    //   358: aload_1
    //   359: invokeinterface 209 1 0
    //   364: aload_0
    //   365: athrow
    //   366: astore_0
    //   367: aload 4
    //   369: astore_1
    //   370: ldc -125
    //   372: new 69	java/lang/StringBuilder
    //   375: dup
    //   376: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   379: ldc -42
    //   381: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: aload 7
    //   386: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   392: invokestatic 217	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   395: pop
    //   396: aload 4
    //   398: astore_1
    //   399: new 125	com/android/mms/service/exception/ApnException
    //   402: dup
    //   403: new 69	java/lang/StringBuilder
    //   406: dup
    //   407: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   410: ldc -42
    //   412: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload 7
    //   417: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   423: invokespecial 212	com/android/mms/service/exception/ApnException:<init>	(Ljava/lang/String;)V
    //   426: athrow
    //   427: aload 4
    //   429: ifnull +10 -> 439
    //   432: aload 4
    //   434: invokeinterface 209 1 0
    //   439: new 125	com/android/mms/service/exception/ApnException
    //   442: dup
    //   443: ldc -37
    //   445: invokespecial 212	com/android/mms/service/exception/ApnException:<init>	(Ljava/lang/String;)V
    //   448: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	449	0	paramContext	android.content.Context
    //   0	449	1	paramString	String
    //   0	449	2	paramInt	int
    //   122	142	3	i	int
    //   35	398	4	localObject1	Object
    //   38	242	5	localObject2	Object
    //   187	155	6	str1	String
    //   258	158	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   192	202	321	java/net/URISyntaxException
    //   73	116	353	finally
    //   126	136	353	finally
    //   139	155	353	finally
    //   158	170	353	finally
    //   173	180	353	finally
    //   183	189	353	finally
    //   192	202	353	finally
    //   205	218	353	finally
    //   226	234	353	finally
    //   237	244	353	finally
    //   247	260	353	finally
    //   273	279	353	finally
    //   285	302	353	finally
    //   325	353	353	finally
    //   370	396	353	finally
    //   399	427	353	finally
    //   273	279	366	java/lang/NumberFormatException
  }
  
  private static String trimWithNullCheck(String paramString)
  {
    if (paramString != null) {
      return paramString.trim();
    }
    return null;
  }
  
  public String getMmscUrl()
  {
    return mServiceCenter;
  }
  
  public String getProxyAddress()
  {
    return mProxyAddress;
  }
  
  public int getProxyPort()
  {
    return mProxyPort;
  }
  
  public boolean isProxySet()
  {
    return !TextUtils.isEmpty(mProxyAddress);
  }
  
  public String toString()
  {
    return mDebugText;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.ApnSettings
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */