package com.google.android.mms.util;

import android.content.Context;
import android.drm.DrmConvertedStatus;
import android.drm.DrmManagerClient;
import android.util.Log;

public class DrmConvertSession
{
  private static final String TAG = "DrmConvertSession";
  private int mConvertSessionId;
  private DrmManagerClient mDrmClient;
  
  private DrmConvertSession(DrmManagerClient paramDrmManagerClient, int paramInt)
  {
    mDrmClient = paramDrmManagerClient;
    mConvertSessionId = paramInt;
  }
  
  public static DrmConvertSession open(Context paramContext, String paramString)
  {
    localObject3 = null;
    localObject4 = null;
    Object localObject5 = null;
    int i = -1;
    int j = i;
    Object localObject1 = localObject5;
    if (paramContext != null)
    {
      j = i;
      localObject1 = localObject5;
      if (paramString != null)
      {
        j = i;
        localObject1 = localObject5;
        if (paramString.equals("")) {}
      }
    }
    for (;;)
    {
      try
      {
        paramContext = new DrmManagerClient(paramContext);
      }
      catch (IllegalStateException paramContext)
      {
        localObject2 = localObject4;
        continue;
      }
      catch (IllegalArgumentException paramContext)
      {
        Object localObject2 = localObject3;
        continue;
      }
      try
      {
        j = paramContext.openConvertSession(paramString);
        i = j;
      }
      catch (IllegalArgumentException localIllegalArgumentException) {}catch (IllegalStateException paramString)
      {
        try
        {
          Log.w("DrmConvertSession", "Conversion of Mimetype: " + paramString + " is not supported.", localIllegalArgumentException);
        }
        catch (IllegalArgumentException paramString)
        {
          localObject2 = paramContext;
          Log.w("DrmConvertSession", "DrmManagerClient instance could not be created, context is Illegal.");
          j = i;
          continue;
          paramString = paramString;
          Log.w("DrmConvertSession", "Could not access Open DrmFramework.", paramString);
        }
        catch (IllegalStateException paramString)
        {
          localObject2 = paramContext;
        }
        Log.w("DrmConvertSession", "DrmManagerClient didn't initialize properly.");
        j = i;
      }
    }
    localObject1 = paramContext;
    j = i;
    if ((localObject1 == null) || (j < 0)) {
      return null;
    }
    return new DrmConvertSession((DrmManagerClient)localObject2, j);
  }
  
  /* Error */
  public int close(String paramString)
  {
    // Byte code:
    //   0: sipush 491
    //   3: istore_3
    //   4: iload_3
    //   5: istore_2
    //   6: aload_0
    //   7: getfield 19	com/google/android/mms/util/DrmConvertSession:mDrmClient	Landroid/drm/DrmManagerClient;
    //   10: ifnull +61 -> 71
    //   13: iload_3
    //   14: istore_2
    //   15: aload_0
    //   16: getfield 21	com/google/android/mms/util/DrmConvertSession:mConvertSessionId	I
    //   19: iflt +52 -> 71
    //   22: iload_3
    //   23: istore_2
    //   24: aload_0
    //   25: getfield 19	com/google/android/mms/util/DrmConvertSession:mDrmClient	Landroid/drm/DrmManagerClient;
    //   28: aload_0
    //   29: getfield 21	com/google/android/mms/util/DrmConvertSession:mConvertSessionId	I
    //   32: invokevirtual 88	android/drm/DrmManagerClient:closeConvertSession	(I)Landroid/drm/DrmConvertedStatus;
    //   35: astore 10
    //   37: aload 10
    //   39: ifnull +28 -> 67
    //   42: iload_3
    //   43: istore_2
    //   44: aload 10
    //   46: getfield 93	android/drm/DrmConvertedStatus:statusCode	I
    //   49: iconst_1
    //   50: if_icmpne +17 -> 67
    //   53: iload_3
    //   54: istore_2
    //   55: aload 10
    //   57: getfield 97	android/drm/DrmConvertedStatus:convertedData	[B
    //   60: astore 4
    //   62: aload 4
    //   64: ifnonnull +9 -> 73
    //   67: sipush 406
    //   70: istore_2
    //   71: iload_2
    //   72: ireturn
    //   73: aconst_null
    //   74: astore 7
    //   76: aconst_null
    //   77: astore 8
    //   79: aconst_null
    //   80: astore 9
    //   82: aconst_null
    //   83: astore 4
    //   85: aconst_null
    //   86: astore 6
    //   88: iload_3
    //   89: istore_2
    //   90: new 99	java/io/RandomAccessFile
    //   93: dup
    //   94: aload_1
    //   95: ldc 101
    //   97: invokespecial 104	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   100: astore 5
    //   102: aload 5
    //   104: aload 10
    //   106: getfield 107	android/drm/DrmConvertedStatus:offset	I
    //   109: i2l
    //   110: invokevirtual 111	java/io/RandomAccessFile:seek	(J)V
    //   113: aload 5
    //   115: aload 10
    //   117: getfield 97	android/drm/DrmConvertedStatus:convertedData	[B
    //   120: invokevirtual 115	java/io/RandomAccessFile:write	([B)V
    //   123: sipush 200
    //   126: istore_3
    //   127: iload_3
    //   128: istore_2
    //   129: aload 5
    //   131: ifnull -60 -> 71
    //   134: iload_3
    //   135: istore_2
    //   136: aload 5
    //   138: invokevirtual 117	java/io/RandomAccessFile:close	()V
    //   141: sipush 200
    //   144: ireturn
    //   145: astore 4
    //   147: sipush 492
    //   150: istore_2
    //   151: ldc 8
    //   153: new 47	java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   160: ldc 119
    //   162: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: aload_1
    //   166: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: ldc 121
    //   171: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: aload 4
    //   179: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   182: pop
    //   183: sipush 492
    //   186: ireturn
    //   187: astore_1
    //   188: ldc 8
    //   190: new 47	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   197: ldc 123
    //   199: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: aload_0
    //   203: getfield 21	com/google/android/mms/util/DrmConvertSession:mConvertSessionId	I
    //   206: invokevirtual 126	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   209: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   212: aload_1
    //   213: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   216: pop
    //   217: iload_2
    //   218: ireturn
    //   219: astore 4
    //   221: aload 6
    //   223: astore 5
    //   225: aload 4
    //   227: astore 6
    //   229: sipush 492
    //   232: istore_3
    //   233: iload_3
    //   234: istore_2
    //   235: aload 5
    //   237: astore 4
    //   239: ldc 8
    //   241: new 47	java/lang/StringBuilder
    //   244: dup
    //   245: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   248: ldc -128
    //   250: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: aload_1
    //   254: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: ldc -126
    //   259: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   265: aload 6
    //   267: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   270: pop
    //   271: iload_3
    //   272: istore_2
    //   273: aload 5
    //   275: ifnull -204 -> 71
    //   278: iload_3
    //   279: istore_2
    //   280: aload 5
    //   282: invokevirtual 117	java/io/RandomAccessFile:close	()V
    //   285: sipush 492
    //   288: ireturn
    //   289: astore 4
    //   291: sipush 492
    //   294: istore_2
    //   295: ldc 8
    //   297: new 47	java/lang/StringBuilder
    //   300: dup
    //   301: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   304: ldc 119
    //   306: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: aload_1
    //   310: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: ldc 121
    //   315: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   321: aload 4
    //   323: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   326: pop
    //   327: sipush 492
    //   330: ireturn
    //   331: astore 6
    //   333: aload 7
    //   335: astore 5
    //   337: sipush 492
    //   340: istore_3
    //   341: iload_3
    //   342: istore_2
    //   343: aload 5
    //   345: astore 4
    //   347: ldc 8
    //   349: new 47	java/lang/StringBuilder
    //   352: dup
    //   353: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   356: ldc -124
    //   358: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: aload_1
    //   362: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: ldc -122
    //   367: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   373: aload 6
    //   375: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   378: pop
    //   379: iload_3
    //   380: istore_2
    //   381: aload 5
    //   383: ifnull -312 -> 71
    //   386: iload_3
    //   387: istore_2
    //   388: aload 5
    //   390: invokevirtual 117	java/io/RandomAccessFile:close	()V
    //   393: sipush 492
    //   396: ireturn
    //   397: astore 4
    //   399: sipush 492
    //   402: istore_2
    //   403: ldc 8
    //   405: new 47	java/lang/StringBuilder
    //   408: dup
    //   409: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   412: ldc 119
    //   414: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: aload_1
    //   418: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: ldc 121
    //   423: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   429: aload 4
    //   431: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   434: pop
    //   435: sipush 492
    //   438: ireturn
    //   439: astore 6
    //   441: aload 8
    //   443: astore 5
    //   445: sipush 492
    //   448: istore_3
    //   449: iload_3
    //   450: istore_2
    //   451: aload 5
    //   453: astore 4
    //   455: ldc 8
    //   457: ldc -120
    //   459: aload 6
    //   461: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   464: pop
    //   465: iload_3
    //   466: istore_2
    //   467: aload 5
    //   469: ifnull -398 -> 71
    //   472: iload_3
    //   473: istore_2
    //   474: aload 5
    //   476: invokevirtual 117	java/io/RandomAccessFile:close	()V
    //   479: sipush 492
    //   482: ireturn
    //   483: astore 4
    //   485: sipush 492
    //   488: istore_2
    //   489: ldc 8
    //   491: new 47	java/lang/StringBuilder
    //   494: dup
    //   495: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   498: ldc 119
    //   500: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   503: aload_1
    //   504: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: ldc 121
    //   509: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   512: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   515: aload 4
    //   517: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   520: pop
    //   521: sipush 492
    //   524: ireturn
    //   525: astore 6
    //   527: aload 9
    //   529: astore 5
    //   531: iload_3
    //   532: istore_2
    //   533: aload 5
    //   535: astore 4
    //   537: ldc 8
    //   539: new 47	java/lang/StringBuilder
    //   542: dup
    //   543: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   546: ldc -118
    //   548: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   551: aload_1
    //   552: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   555: ldc -116
    //   557: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   563: aload 6
    //   565: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   568: pop
    //   569: iload_3
    //   570: istore_2
    //   571: aload 5
    //   573: ifnull -502 -> 71
    //   576: iload_3
    //   577: istore_2
    //   578: aload 5
    //   580: invokevirtual 117	java/io/RandomAccessFile:close	()V
    //   583: sipush 491
    //   586: ireturn
    //   587: astore 4
    //   589: sipush 492
    //   592: istore_2
    //   593: ldc 8
    //   595: new 47	java/lang/StringBuilder
    //   598: dup
    //   599: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   602: ldc 119
    //   604: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   607: aload_1
    //   608: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   611: ldc 121
    //   613: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   619: aload 4
    //   621: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   624: pop
    //   625: sipush 492
    //   628: ireturn
    //   629: astore 5
    //   631: iload_2
    //   632: istore_3
    //   633: iload_3
    //   634: istore_2
    //   635: aload 4
    //   637: ifnull +12 -> 649
    //   640: iload_3
    //   641: istore_2
    //   642: aload 4
    //   644: invokevirtual 117	java/io/RandomAccessFile:close	()V
    //   647: iload_3
    //   648: istore_2
    //   649: aload 5
    //   651: athrow
    //   652: astore 4
    //   654: sipush 492
    //   657: istore_3
    //   658: iload_3
    //   659: istore_2
    //   660: ldc 8
    //   662: new 47	java/lang/StringBuilder
    //   665: dup
    //   666: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   669: ldc 119
    //   671: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   674: aload_1
    //   675: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   678: ldc 121
    //   680: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   683: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   686: aload 4
    //   688: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   691: pop
    //   692: iload_3
    //   693: istore_2
    //   694: goto -45 -> 649
    //   697: astore 6
    //   699: aload 5
    //   701: astore 4
    //   703: aload 6
    //   705: astore 5
    //   707: goto -74 -> 633
    //   710: astore 6
    //   712: goto -181 -> 531
    //   715: astore 6
    //   717: goto -272 -> 445
    //   720: astore 6
    //   722: goto -385 -> 337
    //   725: astore 6
    //   727: goto -498 -> 229
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	730	0	this	DrmConvertSession
    //   0	730	1	paramString	String
    //   5	689	2	i	int
    //   3	690	3	j	int
    //   60	24	4	arrayOfByte	byte[]
    //   145	33	4	localIOException1	java.io.IOException
    //   219	7	4	localFileNotFoundException1	java.io.FileNotFoundException
    //   237	1	4	localObject1	Object
    //   289	33	4	localIOException2	java.io.IOException
    //   345	1	4	localObject2	Object
    //   397	33	4	localIOException3	java.io.IOException
    //   453	1	4	localObject3	Object
    //   483	33	4	localIOException4	java.io.IOException
    //   535	1	4	localObject4	Object
    //   587	56	4	localIOException5	java.io.IOException
    //   652	35	4	localIOException6	java.io.IOException
    //   701	1	4	localObject5	Object
    //   100	479	5	localObject6	Object
    //   629	71	5	localObject7	Object
    //   705	1	5	localObject8	Object
    //   86	180	6	localFileNotFoundException2	java.io.FileNotFoundException
    //   331	43	6	localIOException7	java.io.IOException
    //   439	21	6	localIllegalArgumentException1	IllegalArgumentException
    //   525	39	6	localSecurityException1	SecurityException
    //   697	7	6	localObject9	Object
    //   710	1	6	localSecurityException2	SecurityException
    //   715	1	6	localIllegalArgumentException2	IllegalArgumentException
    //   720	1	6	localIOException8	java.io.IOException
    //   725	1	6	localFileNotFoundException3	java.io.FileNotFoundException
    //   74	260	7	localObject10	Object
    //   77	365	8	localObject11	Object
    //   80	448	9	localObject12	Object
    //   35	81	10	localDrmConvertedStatus	DrmConvertedStatus
    // Exception table:
    //   from	to	target	type
    //   136	141	145	java/io/IOException
    //   24	37	187	java/lang/IllegalStateException
    //   44	53	187	java/lang/IllegalStateException
    //   55	62	187	java/lang/IllegalStateException
    //   136	141	187	java/lang/IllegalStateException
    //   151	183	187	java/lang/IllegalStateException
    //   280	285	187	java/lang/IllegalStateException
    //   295	327	187	java/lang/IllegalStateException
    //   388	393	187	java/lang/IllegalStateException
    //   403	435	187	java/lang/IllegalStateException
    //   474	479	187	java/lang/IllegalStateException
    //   489	521	187	java/lang/IllegalStateException
    //   578	583	187	java/lang/IllegalStateException
    //   593	625	187	java/lang/IllegalStateException
    //   642	647	187	java/lang/IllegalStateException
    //   649	652	187	java/lang/IllegalStateException
    //   660	692	187	java/lang/IllegalStateException
    //   90	102	219	java/io/FileNotFoundException
    //   280	285	289	java/io/IOException
    //   90	102	331	java/io/IOException
    //   388	393	397	java/io/IOException
    //   90	102	439	java/lang/IllegalArgumentException
    //   474	479	483	java/io/IOException
    //   90	102	525	java/lang/SecurityException
    //   578	583	587	java/io/IOException
    //   90	102	629	finally
    //   239	271	629	finally
    //   347	379	629	finally
    //   455	465	629	finally
    //   537	569	629	finally
    //   642	647	652	java/io/IOException
    //   102	123	697	finally
    //   102	123	710	java/lang/SecurityException
    //   102	123	715	java/lang/IllegalArgumentException
    //   102	123	720	java/io/IOException
    //   102	123	725	java/io/FileNotFoundException
  }
  
  public byte[] convert(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramArrayOfByte != null) {
      try
      {
        if (paramInt != paramArrayOfByte.length)
        {
          byte[] arrayOfByte = new byte[paramInt];
          System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramInt);
          paramArrayOfByte = mDrmClient.convertData(mConvertSessionId, arrayOfByte);
        }
        while ((paramArrayOfByte != null) && (statusCode == 1) && (convertedData != null))
        {
          return convertedData;
          paramArrayOfByte = mDrmClient.convertData(mConvertSessionId, paramArrayOfByte);
          continue;
          throw new IllegalArgumentException("Parameter inBuffer is null");
        }
      }
      catch (IllegalArgumentException paramArrayOfByte)
      {
        Log.w("DrmConvertSession", "Buffer with data to convert is illegal. Convertsession: " + mConvertSessionId, paramArrayOfByte);
        return null;
      }
      catch (IllegalStateException paramArrayOfByte)
      {
        Log.w("DrmConvertSession", "Could not convert data. Convertsession: " + mConvertSessionId, paramArrayOfByte);
        return null;
      }
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.util.DrmConvertSession
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */