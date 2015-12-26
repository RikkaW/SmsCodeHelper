package com.android.mms.update;

import android.content.Context;

public class StreamRequest
  extends Request
{
  public StreamRequest(Context paramContext, String paramString)
  {
    super(paramContext, paramString);
  }
  
  /* Error */
  private java.io.ByteArrayOutputStream getTempDownloadData()
  {
    // Byte code:
    //   0: new 17	java/io/File
    //   3: dup
    //   4: new 19	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 22	java/lang/StringBuilder:<init>	()V
    //   11: aload_0
    //   12: getfield 26	com/android/mms/update/StreamRequest:mContext	Landroid/content/Context;
    //   15: invokevirtual 32	android/content/Context:getCacheDir	()Ljava/io/File;
    //   18: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   21: getstatic 40	java/io/File:separator	Ljava/lang/String;
    //   24: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc 45
    //   29: iconst_1
    //   30: anewarray 47	java/lang/Object
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: invokevirtual 51	com/android/mms/update/StreamRequest:getRequestUrl	()Ljava/lang/String;
    //   39: invokestatic 57	miui/util/HashUtils:getSHA1	(Ljava/lang/String;)Ljava/lang/String;
    //   42: aastore
    //   43: invokestatic 63	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   46: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokespecial 69	java/io/File:<init>	(Ljava/lang/String;)V
    //   55: astore 8
    //   57: aconst_null
    //   58: astore_3
    //   59: aconst_null
    //   60: astore 5
    //   62: aconst_null
    //   63: astore 4
    //   65: aconst_null
    //   66: astore 7
    //   68: aconst_null
    //   69: astore_2
    //   70: aconst_null
    //   71: astore 6
    //   73: aload 8
    //   75: invokevirtual 73	java/io/File:exists	()Z
    //   78: ifeq +85 -> 163
    //   81: aload_2
    //   82: astore_3
    //   83: new 75	java/io/FileInputStream
    //   86: dup
    //   87: aload 8
    //   89: invokespecial 78	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   92: astore_2
    //   93: new 80	java/io/ByteArrayOutputStream
    //   96: dup
    //   97: invokespecial 81	java/io/ByteArrayOutputStream:<init>	()V
    //   100: astore_3
    //   101: sipush 1024
    //   104: newarray <illegal type>
    //   106: astore 4
    //   108: aload_2
    //   109: aload 4
    //   111: invokevirtual 87	java/io/InputStream:read	([B)I
    //   114: istore_1
    //   115: iload_1
    //   116: iconst_m1
    //   117: if_icmpeq +48 -> 165
    //   120: aload_3
    //   121: aload 4
    //   123: iconst_0
    //   124: iload_1
    //   125: invokevirtual 91	java/io/ByteArrayOutputStream:write	([BII)V
    //   128: goto -20 -> 108
    //   131: astore 5
    //   133: aload_3
    //   134: astore 4
    //   136: aload_2
    //   137: astore_3
    //   138: aload 5
    //   140: invokevirtual 94	java/io/FileNotFoundException:printStackTrace	()V
    //   143: aload 4
    //   145: astore_3
    //   146: aload_2
    //   147: ifnull +10 -> 157
    //   150: aload_2
    //   151: invokevirtual 97	java/io/InputStream:close	()V
    //   154: aload 4
    //   156: astore_3
    //   157: aload 8
    //   159: invokevirtual 100	java/io/File:delete	()Z
    //   162: pop
    //   163: aload_3
    //   164: areturn
    //   165: aload_3
    //   166: invokevirtual 103	java/io/ByteArrayOutputStream:flush	()V
    //   169: aload_2
    //   170: ifnull +7 -> 177
    //   173: aload_2
    //   174: invokevirtual 97	java/io/InputStream:close	()V
    //   177: aload 8
    //   179: invokevirtual 100	java/io/File:delete	()Z
    //   182: pop
    //   183: aload_3
    //   184: areturn
    //   185: astore 4
    //   187: aload 7
    //   189: astore_2
    //   190: aload_2
    //   191: astore_3
    //   192: aload 4
    //   194: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   197: aload 5
    //   199: astore_3
    //   200: aload_2
    //   201: ifnull -44 -> 157
    //   204: aload_2
    //   205: invokevirtual 97	java/io/InputStream:close	()V
    //   208: aload 5
    //   210: astore_3
    //   211: goto -54 -> 157
    //   214: astore_2
    //   215: aload_2
    //   216: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   219: aload 5
    //   221: astore_3
    //   222: goto -65 -> 157
    //   225: astore_2
    //   226: aload_3
    //   227: ifnull +7 -> 234
    //   230: aload_3
    //   231: invokevirtual 97	java/io/InputStream:close	()V
    //   234: aload 8
    //   236: invokevirtual 100	java/io/File:delete	()Z
    //   239: pop
    //   240: aload_2
    //   241: athrow
    //   242: astore_3
    //   243: aload_3
    //   244: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   247: goto -13 -> 234
    //   250: astore_2
    //   251: aload_2
    //   252: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   255: aload 4
    //   257: astore_3
    //   258: goto -101 -> 157
    //   261: astore_2
    //   262: aload_2
    //   263: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   266: goto -89 -> 177
    //   269: astore 4
    //   271: aload_2
    //   272: astore_3
    //   273: aload 4
    //   275: astore_2
    //   276: goto -50 -> 226
    //   279: astore 4
    //   281: aload_2
    //   282: astore_3
    //   283: aload 4
    //   285: astore_2
    //   286: goto -60 -> 226
    //   289: astore 4
    //   291: goto -101 -> 190
    //   294: astore 4
    //   296: aload_3
    //   297: astore 5
    //   299: goto -109 -> 190
    //   302: astore 5
    //   304: aload 6
    //   306: astore_2
    //   307: goto -171 -> 136
    //   310: astore 5
    //   312: goto -176 -> 136
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	315	0	this	StreamRequest
    //   114	11	1	i	int
    //   69	136	2	localObject1	Object
    //   214	2	2	localIOException1	java.io.IOException
    //   225	16	2	localObject2	Object
    //   250	2	2	localIOException2	java.io.IOException
    //   261	11	2	localIOException3	java.io.IOException
    //   275	32	2	localObject3	Object
    //   58	173	3	localObject4	Object
    //   242	2	3	localIOException4	java.io.IOException
    //   257	40	3	localObject5	Object
    //   63	92	4	localObject6	Object
    //   185	71	4	localIOException5	java.io.IOException
    //   269	5	4	localObject7	Object
    //   279	5	4	localObject8	Object
    //   289	1	4	localIOException6	java.io.IOException
    //   294	1	4	localIOException7	java.io.IOException
    //   60	1	5	localObject9	Object
    //   131	89	5	localFileNotFoundException1	java.io.FileNotFoundException
    //   297	1	5	localObject10	Object
    //   302	1	5	localFileNotFoundException2	java.io.FileNotFoundException
    //   310	1	5	localFileNotFoundException3	java.io.FileNotFoundException
    //   71	234	6	localObject11	Object
    //   66	122	7	localObject12	Object
    //   55	180	8	localFile	java.io.File
    // Exception table:
    //   from	to	target	type
    //   101	108	131	java/io/FileNotFoundException
    //   108	115	131	java/io/FileNotFoundException
    //   120	128	131	java/io/FileNotFoundException
    //   165	169	131	java/io/FileNotFoundException
    //   83	93	185	java/io/IOException
    //   204	208	214	java/io/IOException
    //   83	93	225	finally
    //   138	143	225	finally
    //   192	197	225	finally
    //   230	234	242	java/io/IOException
    //   150	154	250	java/io/IOException
    //   173	177	261	java/io/IOException
    //   93	101	269	finally
    //   101	108	279	finally
    //   108	115	279	finally
    //   120	128	279	finally
    //   165	169	279	finally
    //   93	101	289	java/io/IOException
    //   101	108	294	java/io/IOException
    //   108	115	294	java/io/IOException
    //   120	128	294	java/io/IOException
    //   165	169	294	java/io/IOException
    //   83	93	302	java/io/FileNotFoundException
    //   93	101	310	java/io/FileNotFoundException
  }
  
  /* Error */
  private void saveTemporaryDownloadedData(java.io.ByteArrayOutputStream paramByteArrayOutputStream)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_2
    //   5: aconst_null
    //   6: astore 4
    //   8: new 108	java/io/FileOutputStream
    //   11: dup
    //   12: new 19	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 22	java/lang/StringBuilder:<init>	()V
    //   19: aload_0
    //   20: getfield 26	com/android/mms/update/StreamRequest:mContext	Landroid/content/Context;
    //   23: invokevirtual 32	android/content/Context:getCacheDir	()Ljava/io/File;
    //   26: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   29: getstatic 40	java/io/File:separator	Ljava/lang/String;
    //   32: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: ldc 45
    //   37: iconst_1
    //   38: anewarray 47	java/lang/Object
    //   41: dup
    //   42: iconst_0
    //   43: aload_0
    //   44: invokevirtual 51	com/android/mms/update/StreamRequest:getRequestUrl	()Ljava/lang/String;
    //   47: invokestatic 57	miui/util/HashUtils:getSHA1	(Ljava/lang/String;)Ljava/lang/String;
    //   50: aastore
    //   51: invokestatic 63	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   54: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokespecial 109	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   63: astore_3
    //   64: aload_3
    //   65: aload_1
    //   66: invokevirtual 113	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   69: invokevirtual 116	java/io/FileOutputStream:write	([B)V
    //   72: aload_3
    //   73: invokevirtual 117	java/io/FileOutputStream:flush	()V
    //   76: aload_3
    //   77: ifnull +7 -> 84
    //   80: aload_3
    //   81: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   84: return
    //   85: astore_3
    //   86: aload 4
    //   88: astore_1
    //   89: aload_1
    //   90: astore_2
    //   91: aload_3
    //   92: invokevirtual 94	java/io/FileNotFoundException:printStackTrace	()V
    //   95: aload_1
    //   96: ifnull -12 -> 84
    //   99: aload_1
    //   100: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   103: return
    //   104: astore_1
    //   105: aload_1
    //   106: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   109: return
    //   110: astore_3
    //   111: aload 5
    //   113: astore_1
    //   114: aload_1
    //   115: astore_2
    //   116: aload_3
    //   117: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   120: aload_1
    //   121: ifnull -37 -> 84
    //   124: aload_1
    //   125: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   128: return
    //   129: astore_1
    //   130: goto -25 -> 105
    //   133: astore_1
    //   134: aload_2
    //   135: ifnull +7 -> 142
    //   138: aload_2
    //   139: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   142: aload_1
    //   143: athrow
    //   144: astore_2
    //   145: aload_2
    //   146: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   149: goto -7 -> 142
    //   152: astore_1
    //   153: aload_1
    //   154: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   157: goto -73 -> 84
    //   160: astore_1
    //   161: aload_3
    //   162: astore_2
    //   163: goto -29 -> 134
    //   166: astore_2
    //   167: aload_3
    //   168: astore_1
    //   169: aload_2
    //   170: astore_3
    //   171: goto -57 -> 114
    //   174: astore_2
    //   175: aload_3
    //   176: astore_1
    //   177: aload_2
    //   178: astore_3
    //   179: goto -90 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	182	0	this	StreamRequest
    //   0	182	1	paramByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   4	135	2	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   144	2	2	localIOException1	java.io.IOException
    //   162	1	2	localObject1	Object
    //   166	4	2	localIOException2	java.io.IOException
    //   174	4	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   63	18	3	localFileOutputStream	java.io.FileOutputStream
    //   85	7	3	localFileNotFoundException2	java.io.FileNotFoundException
    //   110	58	3	localIOException3	java.io.IOException
    //   170	9	3	localObject2	Object
    //   6	81	4	localObject3	Object
    //   1	111	5	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   8	64	85	java/io/FileNotFoundException
    //   99	103	104	java/io/IOException
    //   8	64	110	java/io/IOException
    //   124	128	129	java/io/IOException
    //   8	64	133	finally
    //   91	95	133	finally
    //   116	120	133	finally
    //   138	142	144	java/io/IOException
    //   80	84	152	java/io/IOException
    //   64	76	160	finally
    //   64	76	166	java/io/IOException
    //   64	76	174	java/io/FileNotFoundException
  }
  
  protected String getRequestUrl()
  {
    return mRequestUrl;
  }
  
  /* Error */
  public int requestStream(java.io.OutputStream paramOutputStream, java.util.Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +17 -> 18
    //   4: ldc 127
    //   6: ldc -127
    //   8: invokestatic 135	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   11: pop
    //   12: iconst_3
    //   13: istore 4
    //   15: iload 4
    //   17: ireturn
    //   18: aload_0
    //   19: getfield 26	com/android/mms/update/StreamRequest:mContext	Landroid/content/Context;
    //   22: invokestatic 141	com/android/mms/update/Network:isNetWorkConnected	(Landroid/content/Context;)Z
    //   25: ifne +13 -> 38
    //   28: ldc 127
    //   30: ldc -113
    //   32: invokestatic 146	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   35: pop
    //   36: iconst_1
    //   37: ireturn
    //   38: iconst_3
    //   39: istore 4
    //   41: aconst_null
    //   42: astore 16
    //   44: aconst_null
    //   45: astore 10
    //   47: aconst_null
    //   48: astore 13
    //   50: aconst_null
    //   51: astore 19
    //   53: aconst_null
    //   54: astore 20
    //   56: aconst_null
    //   57: astore 21
    //   59: aconst_null
    //   60: astore 18
    //   62: aconst_null
    //   63: astore 23
    //   65: aconst_null
    //   66: astore 12
    //   68: aconst_null
    //   69: astore 24
    //   71: aconst_null
    //   72: astore 22
    //   74: aload 22
    //   76: astore 8
    //   78: aload 18
    //   80: astore 14
    //   82: aload 23
    //   84: astore 9
    //   86: aload 19
    //   88: astore 17
    //   90: aload 24
    //   92: astore 7
    //   94: aload 21
    //   96: astore 11
    //   98: iload 4
    //   100: istore 5
    //   102: aload_0
    //   103: invokespecial 150	com/android/mms/update/Request:getConn	()Ljava/net/HttpURLConnection;
    //   106: astore 15
    //   108: aload 12
    //   110: astore 7
    //   112: aload 20
    //   114: astore 12
    //   116: iload 4
    //   118: istore_3
    //   119: aload 15
    //   121: ifnull +1035 -> 1156
    //   124: aload_2
    //   125: ifnull +361 -> 486
    //   128: aload 22
    //   130: astore 8
    //   132: aload 15
    //   134: astore 13
    //   136: aload 18
    //   138: astore 14
    //   140: aload 23
    //   142: astore 9
    //   144: aload 15
    //   146: astore 16
    //   148: aload 19
    //   150: astore 17
    //   152: aload 24
    //   154: astore 7
    //   156: aload 15
    //   158: astore 10
    //   160: aload 21
    //   162: astore 11
    //   164: iload 4
    //   166: istore 5
    //   168: aload_2
    //   169: invokeinterface 156 1 0
    //   174: invokeinterface 162 1 0
    //   179: astore 12
    //   181: aload 22
    //   183: astore 8
    //   185: aload 15
    //   187: astore 13
    //   189: aload 18
    //   191: astore 14
    //   193: aload 23
    //   195: astore 9
    //   197: aload 15
    //   199: astore 16
    //   201: aload 19
    //   203: astore 17
    //   205: aload 24
    //   207: astore 7
    //   209: aload 15
    //   211: astore 10
    //   213: aload 21
    //   215: astore 11
    //   217: iload 4
    //   219: istore 5
    //   221: aload 12
    //   223: invokeinterface 167 1 0
    //   228: ifeq +258 -> 486
    //   231: aload 22
    //   233: astore 8
    //   235: aload 15
    //   237: astore 13
    //   239: aload 18
    //   241: astore 14
    //   243: aload 23
    //   245: astore 9
    //   247: aload 15
    //   249: astore 16
    //   251: aload 19
    //   253: astore 17
    //   255: aload 24
    //   257: astore 7
    //   259: aload 15
    //   261: astore 10
    //   263: aload 21
    //   265: astore 11
    //   267: iload 4
    //   269: istore 5
    //   271: aload 12
    //   273: invokeinterface 171 1 0
    //   278: checkcast 173	java/util/Map$Entry
    //   281: astore 25
    //   283: aload 22
    //   285: astore 8
    //   287: aload 15
    //   289: astore 13
    //   291: aload 18
    //   293: astore 14
    //   295: aload 23
    //   297: astore 9
    //   299: aload 15
    //   301: astore 16
    //   303: aload 19
    //   305: astore 17
    //   307: aload 24
    //   309: astore 7
    //   311: aload 15
    //   313: astore 10
    //   315: aload 21
    //   317: astore 11
    //   319: iload 4
    //   321: istore 5
    //   323: aload 15
    //   325: aload 25
    //   327: invokeinterface 176 1 0
    //   332: checkcast 59	java/lang/String
    //   335: invokevirtual 181	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   338: astore 26
    //   340: aload 22
    //   342: astore 8
    //   344: aload 15
    //   346: astore 13
    //   348: aload 18
    //   350: astore 14
    //   352: aload 23
    //   354: astore 9
    //   356: aload 15
    //   358: astore 16
    //   360: aload 19
    //   362: astore 17
    //   364: aload 24
    //   366: astore 7
    //   368: aload 15
    //   370: astore 10
    //   372: aload 21
    //   374: astore 11
    //   376: iload 4
    //   378: istore 5
    //   380: aload_2
    //   381: aload 25
    //   383: invokeinterface 176 1 0
    //   388: aload 26
    //   390: invokeinterface 185 3 0
    //   395: pop
    //   396: goto -215 -> 181
    //   399: astore_1
    //   400: iconst_2
    //   401: istore_3
    //   402: aload 8
    //   404: astore 7
    //   406: aload 13
    //   408: astore 10
    //   410: aload 14
    //   412: astore 11
    //   414: iload_3
    //   415: istore 5
    //   417: aload_1
    //   418: invokevirtual 186	java/net/SocketTimeoutException:printStackTrace	()V
    //   421: aload 13
    //   423: ifnull +8 -> 431
    //   426: aload 13
    //   428: invokevirtual 189	java/net/HttpURLConnection:disconnect	()V
    //   431: aload 14
    //   433: ifnull +8 -> 441
    //   436: aload 14
    //   438: invokevirtual 97	java/io/InputStream:close	()V
    //   441: iconst_2
    //   442: ifeq +22 -> 464
    //   445: aload 8
    //   447: ifnull +17 -> 464
    //   450: aload 8
    //   452: invokevirtual 193	java/io/ByteArrayOutputStream:size	()I
    //   455: ifle +9 -> 464
    //   458: aload_0
    //   459: aload 8
    //   461: invokespecial 195	com/android/mms/update/StreamRequest:saveTemporaryDownloadedData	(Ljava/io/ByteArrayOutputStream;)V
    //   464: iload_3
    //   465: istore 4
    //   467: aload 8
    //   469: ifnull -454 -> 15
    //   472: aload 8
    //   474: invokevirtual 196	java/io/ByteArrayOutputStream:close	()V
    //   477: iconst_2
    //   478: ireturn
    //   479: astore_1
    //   480: aload_1
    //   481: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   484: iload_3
    //   485: ireturn
    //   486: aload 22
    //   488: astore 8
    //   490: aload 15
    //   492: astore 13
    //   494: aload 18
    //   496: astore 14
    //   498: aload 23
    //   500: astore 9
    //   502: aload 15
    //   504: astore 16
    //   506: aload 19
    //   508: astore 17
    //   510: aload 24
    //   512: astore 7
    //   514: aload 15
    //   516: astore 10
    //   518: aload 21
    //   520: astore 11
    //   522: iload 4
    //   524: istore 5
    //   526: aload_0
    //   527: invokespecial 198	com/android/mms/update/StreamRequest:getTempDownloadData	()Ljava/io/ByteArrayOutputStream;
    //   530: astore_2
    //   531: aload_2
    //   532: ifnull +526 -> 1058
    //   535: aload_2
    //   536: astore 8
    //   538: aload 15
    //   540: astore 13
    //   542: aload 18
    //   544: astore 14
    //   546: aload_2
    //   547: astore 9
    //   549: aload 15
    //   551: astore 16
    //   553: aload 19
    //   555: astore 17
    //   557: aload_2
    //   558: astore 7
    //   560: aload 15
    //   562: astore 10
    //   564: aload 21
    //   566: astore 11
    //   568: iload 4
    //   570: istore 5
    //   572: aload 15
    //   574: ldc -56
    //   576: ldc -54
    //   578: iconst_1
    //   579: anewarray 47	java/lang/Object
    //   582: dup
    //   583: iconst_0
    //   584: aload_2
    //   585: invokevirtual 193	java/io/ByteArrayOutputStream:size	()I
    //   588: invokestatic 208	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   591: aastore
    //   592: invokestatic 63	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   595: invokevirtual 212	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   598: aload_2
    //   599: astore 8
    //   601: aload 15
    //   603: astore 13
    //   605: aload 18
    //   607: astore 14
    //   609: aload_2
    //   610: astore 9
    //   612: aload 15
    //   614: astore 16
    //   616: aload 19
    //   618: astore 17
    //   620: aload_2
    //   621: astore 7
    //   623: aload 15
    //   625: astore 10
    //   627: aload 21
    //   629: astore 11
    //   631: iload 4
    //   633: istore 5
    //   635: aload 15
    //   637: invokevirtual 215	java/net/HttpURLConnection:connect	()V
    //   640: aload_2
    //   641: astore 8
    //   643: aload 15
    //   645: astore 13
    //   647: aload 18
    //   649: astore 14
    //   651: aload_2
    //   652: astore 9
    //   654: aload 15
    //   656: astore 16
    //   658: aload 19
    //   660: astore 17
    //   662: aload_2
    //   663: astore 7
    //   665: aload 15
    //   667: astore 10
    //   669: aload 21
    //   671: astore 11
    //   673: iload 4
    //   675: istore 5
    //   677: aload 15
    //   679: invokevirtual 218	java/net/HttpURLConnection:getResponseCode	()I
    //   682: istore_3
    //   683: aload_2
    //   684: astore 8
    //   686: aload 15
    //   688: astore 13
    //   690: aload 18
    //   692: astore 14
    //   694: aload_2
    //   695: astore 9
    //   697: aload 15
    //   699: astore 16
    //   701: aload 19
    //   703: astore 17
    //   705: aload_2
    //   706: astore 7
    //   708: aload 15
    //   710: astore 10
    //   712: aload 21
    //   714: astore 11
    //   716: iload 4
    //   718: istore 5
    //   720: ldc 127
    //   722: new 19	java/lang/StringBuilder
    //   725: dup
    //   726: invokespecial 22	java/lang/StringBuilder:<init>	()V
    //   729: ldc -36
    //   731: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   734: iload_3
    //   735: invokevirtual 223	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   738: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   741: invokestatic 146	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   744: pop
    //   745: iload_3
    //   746: sipush 200
    //   749: if_icmpeq +10 -> 759
    //   752: iload_3
    //   753: sipush 206
    //   756: if_icmpne +462 -> 1218
    //   759: aload_2
    //   760: astore 8
    //   762: aload 15
    //   764: astore 13
    //   766: aload 18
    //   768: astore 14
    //   770: aload_2
    //   771: astore 9
    //   773: aload 15
    //   775: astore 16
    //   777: aload 19
    //   779: astore 17
    //   781: aload_2
    //   782: astore 7
    //   784: aload 15
    //   786: astore 10
    //   788: aload 21
    //   790: astore 11
    //   792: iload 4
    //   794: istore 5
    //   796: aload 15
    //   798: invokevirtual 227	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   801: astore 12
    //   803: aload_2
    //   804: astore 8
    //   806: aload 15
    //   808: astore 13
    //   810: aload 12
    //   812: astore 14
    //   814: aload_2
    //   815: astore 9
    //   817: aload 15
    //   819: astore 16
    //   821: aload 12
    //   823: astore 17
    //   825: aload_2
    //   826: astore 7
    //   828: aload 15
    //   830: astore 10
    //   832: aload 12
    //   834: astore 11
    //   836: iload 4
    //   838: istore 5
    //   840: sipush 1024
    //   843: newarray <illegal type>
    //   845: astore 18
    //   847: aload_2
    //   848: astore 8
    //   850: aload 15
    //   852: astore 13
    //   854: aload 12
    //   856: astore 14
    //   858: aload_2
    //   859: astore 9
    //   861: aload 15
    //   863: astore 16
    //   865: aload 12
    //   867: astore 17
    //   869: aload_2
    //   870: astore 7
    //   872: aload 15
    //   874: astore 10
    //   876: aload 12
    //   878: astore 11
    //   880: iload 4
    //   882: istore 5
    //   884: aload 12
    //   886: aload 18
    //   888: invokevirtual 87	java/io/InputStream:read	([B)I
    //   891: istore_3
    //   892: iload_3
    //   893: iconst_m1
    //   894: if_icmpeq +212 -> 1106
    //   897: aload_2
    //   898: astore 8
    //   900: aload 15
    //   902: astore 13
    //   904: aload 12
    //   906: astore 14
    //   908: aload_2
    //   909: astore 9
    //   911: aload 15
    //   913: astore 16
    //   915: aload 12
    //   917: astore 17
    //   919: aload_2
    //   920: astore 7
    //   922: aload 15
    //   924: astore 10
    //   926: aload 12
    //   928: astore 11
    //   930: iload 4
    //   932: istore 5
    //   934: aload_2
    //   935: aload 18
    //   937: iconst_0
    //   938: iload_3
    //   939: invokevirtual 91	java/io/ByteArrayOutputStream:write	([BII)V
    //   942: goto -95 -> 847
    //   945: astore_1
    //   946: iload 4
    //   948: istore_3
    //   949: aload 9
    //   951: astore 7
    //   953: aload 16
    //   955: astore 10
    //   957: aload 17
    //   959: astore 11
    //   961: iload 4
    //   963: istore 5
    //   965: aload_0
    //   966: getfield 26	com/android/mms/update/StreamRequest:mContext	Landroid/content/Context;
    //   969: invokestatic 141	com/android/mms/update/Network:isNetWorkConnected	(Landroid/content/Context;)Z
    //   972: ifne +5 -> 977
    //   975: iconst_1
    //   976: istore_3
    //   977: aload 9
    //   979: astore 7
    //   981: aload 16
    //   983: astore 10
    //   985: aload 17
    //   987: astore 11
    //   989: iload_3
    //   990: istore 5
    //   992: aload_1
    //   993: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   996: aload 16
    //   998: ifnull +8 -> 1006
    //   1001: aload 16
    //   1003: invokevirtual 189	java/net/HttpURLConnection:disconnect	()V
    //   1006: aload 17
    //   1008: ifnull +8 -> 1016
    //   1011: aload 17
    //   1013: invokevirtual 97	java/io/InputStream:close	()V
    //   1016: iload_3
    //   1017: ifeq +22 -> 1039
    //   1020: aload 9
    //   1022: ifnull +17 -> 1039
    //   1025: aload 9
    //   1027: invokevirtual 193	java/io/ByteArrayOutputStream:size	()I
    //   1030: ifle +9 -> 1039
    //   1033: aload_0
    //   1034: aload 9
    //   1036: invokespecial 195	com/android/mms/update/StreamRequest:saveTemporaryDownloadedData	(Ljava/io/ByteArrayOutputStream;)V
    //   1039: iload_3
    //   1040: istore 4
    //   1042: aload 9
    //   1044: ifnull -1029 -> 15
    //   1047: aload 9
    //   1049: invokevirtual 196	java/io/ByteArrayOutputStream:close	()V
    //   1052: iload_3
    //   1053: ireturn
    //   1054: astore_1
    //   1055: goto -575 -> 480
    //   1058: aload_2
    //   1059: astore 8
    //   1061: aload 15
    //   1063: astore 13
    //   1065: aload 18
    //   1067: astore 14
    //   1069: aload_2
    //   1070: astore 9
    //   1072: aload 15
    //   1074: astore 16
    //   1076: aload 19
    //   1078: astore 17
    //   1080: aload_2
    //   1081: astore 7
    //   1083: aload 15
    //   1085: astore 10
    //   1087: aload 21
    //   1089: astore 11
    //   1091: iload 4
    //   1093: istore 5
    //   1095: new 80	java/io/ByteArrayOutputStream
    //   1098: dup
    //   1099: invokespecial 81	java/io/ByteArrayOutputStream:<init>	()V
    //   1102: astore_2
    //   1103: goto -505 -> 598
    //   1106: aload_2
    //   1107: astore 8
    //   1109: aload 15
    //   1111: astore 13
    //   1113: aload 12
    //   1115: astore 14
    //   1117: aload_2
    //   1118: astore 9
    //   1120: aload 15
    //   1122: astore 16
    //   1124: aload 12
    //   1126: astore 17
    //   1128: aload_2
    //   1129: astore 7
    //   1131: aload 15
    //   1133: astore 10
    //   1135: aload 12
    //   1137: astore 11
    //   1139: iload 4
    //   1141: istore 5
    //   1143: aload_1
    //   1144: aload_2
    //   1145: invokevirtual 113	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   1148: invokevirtual 230	java/io/OutputStream:write	([B)V
    //   1151: iconst_0
    //   1152: istore_3
    //   1153: aload_2
    //   1154: astore 7
    //   1156: aload 15
    //   1158: ifnull +8 -> 1166
    //   1161: aload 15
    //   1163: invokevirtual 189	java/net/HttpURLConnection:disconnect	()V
    //   1166: aload 12
    //   1168: ifnull +8 -> 1176
    //   1171: aload 12
    //   1173: invokevirtual 97	java/io/InputStream:close	()V
    //   1176: iload_3
    //   1177: ifeq +22 -> 1199
    //   1180: aload 7
    //   1182: ifnull +17 -> 1199
    //   1185: aload 7
    //   1187: invokevirtual 193	java/io/ByteArrayOutputStream:size	()I
    //   1190: ifle +9 -> 1199
    //   1193: aload_0
    //   1194: aload 7
    //   1196: invokespecial 195	com/android/mms/update/StreamRequest:saveTemporaryDownloadedData	(Ljava/io/ByteArrayOutputStream;)V
    //   1199: iload_3
    //   1200: istore 4
    //   1202: aload 7
    //   1204: ifnull -1189 -> 15
    //   1207: aload 7
    //   1209: invokevirtual 196	java/io/ByteArrayOutputStream:close	()V
    //   1212: iload_3
    //   1213: ireturn
    //   1214: astore_1
    //   1215: goto -735 -> 480
    //   1218: aload_2
    //   1219: astore 8
    //   1221: aload 15
    //   1223: astore 13
    //   1225: aload 18
    //   1227: astore 14
    //   1229: aload_2
    //   1230: astore 9
    //   1232: aload 15
    //   1234: astore 16
    //   1236: aload 19
    //   1238: astore 17
    //   1240: aload_2
    //   1241: astore 7
    //   1243: aload 15
    //   1245: astore 10
    //   1247: aload 21
    //   1249: astore 11
    //   1251: iload 4
    //   1253: istore 5
    //   1255: aload_0
    //   1256: iload_3
    //   1257: invokevirtual 234	com/android/mms/update/StreamRequest:isServerError	(I)Z
    //   1260: istore 6
    //   1262: iload 6
    //   1264: ifeq +15 -> 1279
    //   1267: iconst_4
    //   1268: istore_3
    //   1269: aload_2
    //   1270: astore 7
    //   1272: aload 20
    //   1274: astore 12
    //   1276: goto -120 -> 1156
    //   1279: iconst_5
    //   1280: istore_3
    //   1281: aload_2
    //   1282: astore 7
    //   1284: aload 20
    //   1286: astore 12
    //   1288: goto -132 -> 1156
    //   1291: astore_1
    //   1292: aload 10
    //   1294: ifnull +8 -> 1302
    //   1297: aload 10
    //   1299: invokevirtual 189	java/net/HttpURLConnection:disconnect	()V
    //   1302: aload 11
    //   1304: ifnull +8 -> 1312
    //   1307: aload 11
    //   1309: invokevirtual 97	java/io/InputStream:close	()V
    //   1312: iload 5
    //   1314: ifeq +22 -> 1336
    //   1317: aload 7
    //   1319: ifnull +17 -> 1336
    //   1322: aload 7
    //   1324: invokevirtual 193	java/io/ByteArrayOutputStream:size	()I
    //   1327: ifle +9 -> 1336
    //   1330: aload_0
    //   1331: aload 7
    //   1333: invokespecial 195	com/android/mms/update/StreamRequest:saveTemporaryDownloadedData	(Ljava/io/ByteArrayOutputStream;)V
    //   1336: aload 7
    //   1338: ifnull +8 -> 1346
    //   1341: aload 7
    //   1343: invokevirtual 196	java/io/ByteArrayOutputStream:close	()V
    //   1346: aload_1
    //   1347: athrow
    //   1348: astore_2
    //   1349: aload_2
    //   1350: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   1353: goto -41 -> 1312
    //   1356: astore_2
    //   1357: aload_2
    //   1358: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   1361: goto -15 -> 1346
    //   1364: astore_1
    //   1365: aload_1
    //   1366: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   1369: goto -928 -> 441
    //   1372: astore_1
    //   1373: aload_1
    //   1374: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   1377: goto -361 -> 1016
    //   1380: astore_1
    //   1381: aload_1
    //   1382: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   1385: goto -209 -> 1176
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1388	0	this	StreamRequest
    //   0	1388	1	paramOutputStream	java.io.OutputStream
    //   0	1388	2	paramMap	java.util.Map<String, String>
    //   118	1163	3	i	int
    //   13	1239	4	j	int
    //   100	1213	5	k	int
    //   1260	3	6	bool	boolean
    //   92	1250	7	localObject1	Object
    //   76	1144	8	localObject2	Object
    //   84	1147	9	localObject3	Object
    //   45	1253	10	localObject4	Object
    //   96	1212	11	localObject5	Object
    //   66	1221	12	localObject6	Object
    //   48	1176	13	localObject7	Object
    //   80	1148	14	localObject8	Object
    //   106	1138	15	localHttpURLConnection	java.net.HttpURLConnection
    //   42	1193	16	localObject9	Object
    //   88	1151	17	localObject10	Object
    //   60	1166	18	arrayOfByte	byte[]
    //   51	1186	19	localObject11	Object
    //   54	1231	20	localObject12	Object
    //   57	1191	21	localObject13	Object
    //   72	415	22	localObject14	Object
    //   63	436	23	localObject15	Object
    //   69	442	24	localObject16	Object
    //   281	101	25	localEntry	java.util.Map.Entry
    //   338	51	26	str	String
    // Exception table:
    //   from	to	target	type
    //   102	108	399	java/net/SocketTimeoutException
    //   168	181	399	java/net/SocketTimeoutException
    //   221	231	399	java/net/SocketTimeoutException
    //   271	283	399	java/net/SocketTimeoutException
    //   323	340	399	java/net/SocketTimeoutException
    //   380	396	399	java/net/SocketTimeoutException
    //   526	531	399	java/net/SocketTimeoutException
    //   572	598	399	java/net/SocketTimeoutException
    //   635	640	399	java/net/SocketTimeoutException
    //   677	683	399	java/net/SocketTimeoutException
    //   720	745	399	java/net/SocketTimeoutException
    //   796	803	399	java/net/SocketTimeoutException
    //   840	847	399	java/net/SocketTimeoutException
    //   884	892	399	java/net/SocketTimeoutException
    //   934	942	399	java/net/SocketTimeoutException
    //   1095	1103	399	java/net/SocketTimeoutException
    //   1143	1151	399	java/net/SocketTimeoutException
    //   1255	1262	399	java/net/SocketTimeoutException
    //   472	477	479	java/io/IOException
    //   102	108	945	java/io/IOException
    //   168	181	945	java/io/IOException
    //   221	231	945	java/io/IOException
    //   271	283	945	java/io/IOException
    //   323	340	945	java/io/IOException
    //   380	396	945	java/io/IOException
    //   526	531	945	java/io/IOException
    //   572	598	945	java/io/IOException
    //   635	640	945	java/io/IOException
    //   677	683	945	java/io/IOException
    //   720	745	945	java/io/IOException
    //   796	803	945	java/io/IOException
    //   840	847	945	java/io/IOException
    //   884	892	945	java/io/IOException
    //   934	942	945	java/io/IOException
    //   1095	1103	945	java/io/IOException
    //   1143	1151	945	java/io/IOException
    //   1255	1262	945	java/io/IOException
    //   1047	1052	1054	java/io/IOException
    //   1207	1212	1214	java/io/IOException
    //   102	108	1291	finally
    //   168	181	1291	finally
    //   221	231	1291	finally
    //   271	283	1291	finally
    //   323	340	1291	finally
    //   380	396	1291	finally
    //   417	421	1291	finally
    //   526	531	1291	finally
    //   572	598	1291	finally
    //   635	640	1291	finally
    //   677	683	1291	finally
    //   720	745	1291	finally
    //   796	803	1291	finally
    //   840	847	1291	finally
    //   884	892	1291	finally
    //   934	942	1291	finally
    //   965	975	1291	finally
    //   992	996	1291	finally
    //   1095	1103	1291	finally
    //   1143	1151	1291	finally
    //   1255	1262	1291	finally
    //   1307	1312	1348	java/io/IOException
    //   1341	1346	1356	java/io/IOException
    //   436	441	1364	java/io/IOException
    //   1011	1016	1372	java/io/IOException
    //   1171	1176	1380	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.StreamRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */