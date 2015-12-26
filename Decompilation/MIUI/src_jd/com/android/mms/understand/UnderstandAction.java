package com.android.mms.understand;

import java.util.List;

public class UnderstandAction
{
  public String mAction;
  public int mCallType;
  public String mClassName;
  public int mCount;
  public boolean mEnterMain;
  public String mFallback;
  public int mFlag;
  public List<Params> mLists;
  public String mPackageName;
  public int mParamCount;
  public List<Params> mParams;
  public int mSlotId;
  public String mSlotIdName;
  public String mUriBase;
  public int mUrlPartCount;
  public List<String> mUrlParts;
  
  /* Error */
  public boolean parseActionString(String paramString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 6
    //   3: new 44	org/json/JSONObject
    //   6: dup
    //   7: aload_1
    //   8: invokespecial 47	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   11: astore_1
    //   12: aload_0
    //   13: aload_1
    //   14: ldc 49
    //   16: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   19: putfield 55	com/android/mms/understand/UnderstandAction:mPackageName	Ljava/lang/String;
    //   22: aload_0
    //   23: aload_1
    //   24: ldc 57
    //   26: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   29: putfield 59	com/android/mms/understand/UnderstandAction:mClassName	Ljava/lang/String;
    //   32: aload_0
    //   33: aload_1
    //   34: ldc 61
    //   36: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   39: putfield 63	com/android/mms/understand/UnderstandAction:mAction	Ljava/lang/String;
    //   42: aload_0
    //   43: aload_1
    //   44: ldc 65
    //   46: invokevirtual 69	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   49: putfield 71	com/android/mms/understand/UnderstandAction:mCallType	I
    //   52: aload_0
    //   53: aload_1
    //   54: ldc 73
    //   56: invokevirtual 69	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   59: putfield 75	com/android/mms/understand/UnderstandAction:mSlotId	I
    //   62: aload_0
    //   63: aload_1
    //   64: ldc 77
    //   66: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   69: putfield 79	com/android/mms/understand/UnderstandAction:mUriBase	Ljava/lang/String;
    //   72: aload_0
    //   73: aload_1
    //   74: ldc 81
    //   76: invokevirtual 69	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   79: putfield 83	com/android/mms/understand/UnderstandAction:mParamCount	I
    //   82: aload_0
    //   83: aload_1
    //   84: ldc 85
    //   86: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   89: putfield 87	com/android/mms/understand/UnderstandAction:mFallback	Ljava/lang/String;
    //   92: aload_0
    //   93: aload_1
    //   94: ldc 89
    //   96: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   99: putfield 91	com/android/mms/understand/UnderstandAction:mSlotIdName	Ljava/lang/String;
    //   102: aload_0
    //   103: iconst_0
    //   104: putfield 93	com/android/mms/understand/UnderstandAction:mEnterMain	Z
    //   107: ldc 95
    //   109: aload_1
    //   110: ldc 97
    //   112: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   115: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   118: ifeq +8 -> 126
    //   121: aload_0
    //   122: iconst_1
    //   123: putfield 93	com/android/mms/understand/UnderstandAction:mEnterMain	Z
    //   126: aload_0
    //   127: getfield 83	com/android/mms/understand/UnderstandAction:mParamCount	I
    //   130: ifle +109 -> 239
    //   133: aload_1
    //   134: ldc 105
    //   136: invokevirtual 109	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   139: astore 7
    //   141: aload 7
    //   143: ifnull +96 -> 239
    //   146: aload_0
    //   147: aload 7
    //   149: invokevirtual 115	org/json/JSONArray:length	()I
    //   152: putfield 83	com/android/mms/understand/UnderstandAction:mParamCount	I
    //   155: aload_0
    //   156: new 117	java/util/ArrayList
    //   159: dup
    //   160: invokespecial 118	java/util/ArrayList:<init>	()V
    //   163: putfield 120	com/android/mms/understand/UnderstandAction:mParams	Ljava/util/List;
    //   166: iconst_0
    //   167: istore_2
    //   168: iload_2
    //   169: aload_0
    //   170: getfield 83	com/android/mms/understand/UnderstandAction:mParamCount	I
    //   173: if_icmpge +66 -> 239
    //   176: aload 7
    //   178: iload_2
    //   179: invokevirtual 124	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   182: astore 9
    //   184: aload 9
    //   186: ifnull +421 -> 607
    //   189: aload 9
    //   191: ldc 126
    //   193: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   196: astore 8
    //   198: aload 8
    //   200: invokestatic 132	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   203: ifne +404 -> 607
    //   206: aload 9
    //   208: ldc -122
    //   210: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   213: astore 9
    //   215: aload_0
    //   216: getfield 120	com/android/mms/understand/UnderstandAction:mParams	Ljava/util/List;
    //   219: new 6	com/android/mms/understand/UnderstandAction$Params
    //   222: dup
    //   223: aload 8
    //   225: aload 9
    //   227: invokespecial 137	com/android/mms/understand/UnderstandAction$Params:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   230: invokeinterface 142 2 0
    //   235: pop
    //   236: goto +371 -> 607
    //   239: aload_0
    //   240: aload_1
    //   241: ldc -112
    //   243: invokevirtual 69	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   246: putfield 146	com/android/mms/understand/UnderstandAction:mUrlPartCount	I
    //   249: aload_0
    //   250: getfield 146	com/android/mms/understand/UnderstandAction:mUrlPartCount	I
    //   253: ifle +77 -> 330
    //   256: aload_1
    //   257: ldc -108
    //   259: invokevirtual 109	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   262: astore 7
    //   264: aload 7
    //   266: ifnull +64 -> 330
    //   269: aload_0
    //   270: aload 7
    //   272: invokevirtual 115	org/json/JSONArray:length	()I
    //   275: putfield 146	com/android/mms/understand/UnderstandAction:mUrlPartCount	I
    //   278: aload_0
    //   279: new 117	java/util/ArrayList
    //   282: dup
    //   283: invokespecial 118	java/util/ArrayList:<init>	()V
    //   286: putfield 150	com/android/mms/understand/UnderstandAction:mUrlParts	Ljava/util/List;
    //   289: iconst_0
    //   290: istore_2
    //   291: iload_2
    //   292: aload_0
    //   293: getfield 146	com/android/mms/understand/UnderstandAction:mUrlPartCount	I
    //   296: if_icmpge +34 -> 330
    //   299: aload 7
    //   301: iload_2
    //   302: invokevirtual 153	org/json/JSONArray:optString	(I)Ljava/lang/String;
    //   305: astore 8
    //   307: aload 8
    //   309: invokestatic 132	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   312: ifne +302 -> 614
    //   315: aload_0
    //   316: getfield 150	com/android/mms/understand/UnderstandAction:mUrlParts	Ljava/util/List;
    //   319: aload 8
    //   321: invokeinterface 142 2 0
    //   326: pop
    //   327: goto +287 -> 614
    //   330: aload_0
    //   331: aload_1
    //   332: ldc -101
    //   334: invokevirtual 69	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   337: putfield 157	com/android/mms/understand/UnderstandAction:mCount	I
    //   340: aload_0
    //   341: getfield 157	com/android/mms/understand/UnderstandAction:mCount	I
    //   344: ifle +114 -> 458
    //   347: aload_1
    //   348: ldc -97
    //   350: invokevirtual 109	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   353: astore 7
    //   355: aload_0
    //   356: iconst_0
    //   357: putfield 157	com/android/mms/understand/UnderstandAction:mCount	I
    //   360: aload 7
    //   362: ifnull +96 -> 458
    //   365: aload_0
    //   366: aload 7
    //   368: invokevirtual 115	org/json/JSONArray:length	()I
    //   371: putfield 157	com/android/mms/understand/UnderstandAction:mCount	I
    //   374: aload_0
    //   375: new 117	java/util/ArrayList
    //   378: dup
    //   379: invokespecial 118	java/util/ArrayList:<init>	()V
    //   382: putfield 161	com/android/mms/understand/UnderstandAction:mLists	Ljava/util/List;
    //   385: iconst_0
    //   386: istore_2
    //   387: iload_2
    //   388: aload_0
    //   389: getfield 157	com/android/mms/understand/UnderstandAction:mCount	I
    //   392: if_icmpge +66 -> 458
    //   395: aload 7
    //   397: iload_2
    //   398: invokevirtual 124	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   401: astore 9
    //   403: aload 9
    //   405: ifnull +216 -> 621
    //   408: aload 9
    //   410: ldc 126
    //   412: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   415: astore 8
    //   417: aload 8
    //   419: invokestatic 132	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   422: ifne +199 -> 621
    //   425: aload 9
    //   427: ldc -122
    //   429: invokevirtual 53	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   432: astore 9
    //   434: aload_0
    //   435: getfield 161	com/android/mms/understand/UnderstandAction:mLists	Ljava/util/List;
    //   438: new 6	com/android/mms/understand/UnderstandAction$Params
    //   441: dup
    //   442: aload 8
    //   444: aload 9
    //   446: invokespecial 137	com/android/mms/understand/UnderstandAction$Params:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   449: invokeinterface 142 2 0
    //   454: pop
    //   455: goto +166 -> 621
    //   458: iload 6
    //   460: istore 5
    //   462: aload_1
    //   463: ldc -93
    //   465: invokevirtual 69	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   468: ifle +126 -> 594
    //   471: aload_1
    //   472: ldc -91
    //   474: invokevirtual 109	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   477: astore_1
    //   478: iload 6
    //   480: istore 5
    //   482: aload_1
    //   483: ifnull +111 -> 594
    //   486: aload_1
    //   487: invokevirtual 115	org/json/JSONArray:length	()I
    //   490: istore_3
    //   491: aload_0
    //   492: iconst_0
    //   493: putfield 167	com/android/mms/understand/UnderstandAction:mFlag	I
    //   496: iconst_0
    //   497: istore_2
    //   498: iload 6
    //   500: istore 5
    //   502: iload_2
    //   503: iload_3
    //   504: if_icmpge +90 -> 594
    //   507: aload_1
    //   508: iload_2
    //   509: invokevirtual 153	org/json/JSONArray:optString	(I)Ljava/lang/String;
    //   512: astore 7
    //   514: ldc -87
    //   516: aload 7
    //   518: invokevirtual 175	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   521: astore 8
    //   523: aload_0
    //   524: getfield 167	com/android/mms/understand/UnderstandAction:mFlag	I
    //   527: istore 4
    //   529: aload_0
    //   530: aload 8
    //   532: aload 8
    //   534: invokevirtual 181	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
    //   537: iload 4
    //   539: ior
    //   540: putfield 167	com/android/mms/understand/UnderstandAction:mFlag	I
    //   543: iload_2
    //   544: iconst_1
    //   545: iadd
    //   546: istore_2
    //   547: goto -49 -> 498
    //   550: astore 8
    //   552: ldc -73
    //   554: new 185	java/lang/StringBuilder
    //   557: dup
    //   558: invokespecial 186	java/lang/StringBuilder:<init>	()V
    //   561: ldc -68
    //   563: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: aload 7
    //   568: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   574: invokestatic 202	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   577: pop
    //   578: goto -35 -> 543
    //   581: astore_1
    //   582: ldc -73
    //   584: ldc -52
    //   586: aload_1
    //   587: invokestatic 207	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   590: pop
    //   591: iconst_0
    //   592: istore 5
    //   594: iload 5
    //   596: ireturn
    //   597: astore 7
    //   599: aload 7
    //   601: invokevirtual 210	java/lang/IllegalAccessException:printStackTrace	()V
    //   604: goto -61 -> 543
    //   607: iload_2
    //   608: iconst_1
    //   609: iadd
    //   610: istore_2
    //   611: goto -443 -> 168
    //   614: iload_2
    //   615: iconst_1
    //   616: iadd
    //   617: istore_2
    //   618: goto -327 -> 291
    //   621: iload_2
    //   622: iconst_1
    //   623: iadd
    //   624: istore_2
    //   625: goto -238 -> 387
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	628	0	this	UnderstandAction
    //   0	628	1	paramString	String
    //   167	458	2	i	int
    //   490	15	3	j	int
    //   527	13	4	k	int
    //   460	135	5	bool1	boolean
    //   1	498	6	bool2	boolean
    //   139	428	7	localObject1	Object
    //   597	3	7	localIllegalAccessException	IllegalAccessException
    //   196	337	8	localObject2	Object
    //   550	1	8	localNoSuchFieldException	NoSuchFieldException
    //   182	263	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   514	543	550	java/lang/NoSuchFieldException
    //   3	126	581	org/json/JSONException
    //   126	141	581	org/json/JSONException
    //   146	166	581	org/json/JSONException
    //   168	184	581	org/json/JSONException
    //   189	236	581	org/json/JSONException
    //   239	264	581	org/json/JSONException
    //   269	289	581	org/json/JSONException
    //   291	327	581	org/json/JSONException
    //   330	360	581	org/json/JSONException
    //   365	385	581	org/json/JSONException
    //   387	403	581	org/json/JSONException
    //   408	455	581	org/json/JSONException
    //   462	478	581	org/json/JSONException
    //   486	496	581	org/json/JSONException
    //   507	514	581	org/json/JSONException
    //   514	543	581	org/json/JSONException
    //   552	578	581	org/json/JSONException
    //   599	604	581	org/json/JSONException
    //   514	543	597	java/lang/IllegalAccessException
  }
  
  public static class Params
  {
    public String mName;
    public String mValue;
    
    public Params() {}
    
    public Params(String paramString1, String paramString2)
    {
      mName = paramString1;
      mValue = paramString2;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.understand.UnderstandAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */