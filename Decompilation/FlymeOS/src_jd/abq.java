import android.content.Context;
import android.os.AsyncTask;
import com.android.mms.util.TimerMessageHelpler;

public final class abq
  extends AsyncTask<Void, Void, Void>
{
  public abq(Context paramContext) {}
  
  /* Error */
  protected Void a(Void... paramVarArgs)
  {
    // Byte code:
    //   0: ldc 20
    //   2: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   5: aload_0
    //   6: getfield 11	abq:a	Landroid/content/Context;
    //   9: invokevirtual 31	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   12: astore 12
    //   14: aload 12
    //   16: getstatic 37	android/provider/Telephony$Sms:CONTENT_URI	Landroid/net/Uri;
    //   19: iconst_4
    //   20: anewarray 39	java/lang/String
    //   23: dup
    //   24: iconst_0
    //   25: ldc 41
    //   27: aastore
    //   28: dup
    //   29: iconst_1
    //   30: ldc 43
    //   32: aastore
    //   33: dup
    //   34: iconst_2
    //   35: ldc 45
    //   37: aastore
    //   38: dup
    //   39: iconst_3
    //   40: ldc 47
    //   42: aastore
    //   43: ldc 49
    //   45: aconst_null
    //   46: aconst_null
    //   47: invokevirtual 55	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   50: astore_1
    //   51: new 57	java/lang/StringBuilder
    //   54: dup
    //   55: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   58: ldc 60
    //   60: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload_1
    //   64: invokeinterface 70 1 0
    //   69: invokevirtual 73	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   72: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   78: aload_1
    //   79: invokeinterface 70 1 0
    //   84: ifle +324 -> 408
    //   87: aload_1
    //   88: invokeinterface 81 1 0
    //   93: pop
    //   94: lconst_0
    //   95: lstore_3
    //   96: aload_1
    //   97: aload_1
    //   98: ldc 43
    //   100: invokeinterface 85 2 0
    //   105: invokeinterface 89 2 0
    //   110: lstore 7
    //   112: aload_1
    //   113: aload_1
    //   114: ldc 41
    //   116: invokeinterface 85 2 0
    //   121: invokeinterface 89 2 0
    //   126: lstore 5
    //   128: aload_1
    //   129: aload_1
    //   130: ldc 47
    //   132: invokeinterface 85 2 0
    //   137: invokeinterface 93 2 0
    //   142: istore_2
    //   143: aload_1
    //   144: aload_1
    //   145: ldc 45
    //   147: invokeinterface 85 2 0
    //   152: invokeinterface 97 2 0
    //   157: astore 13
    //   159: invokestatic 103	java/lang/System:currentTimeMillis	()J
    //   162: lstore 9
    //   164: lload 7
    //   166: lload 9
    //   168: lsub
    //   169: lconst_0
    //   170: lcmp
    //   171: ifle +255 -> 426
    //   174: lload 7
    //   176: invokestatic 103	java/lang/System:currentTimeMillis	()J
    //   179: lsub
    //   180: ldc2_w 104
    //   183: lcmp
    //   184: ifge +236 -> 420
    //   187: iconst_1
    //   188: istore 11
    //   190: new 57	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   197: ldc 107
    //   199: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: lload 7
    //   204: invokevirtual 110	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   207: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   210: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   213: new 57	java/lang/StringBuilder
    //   216: dup
    //   217: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   220: ldc 112
    //   222: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: lload 9
    //   227: invokevirtual 110	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   230: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   236: new 57	java/lang/StringBuilder
    //   239: dup
    //   240: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   243: ldc 114
    //   245: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: lload 5
    //   250: invokevirtual 110	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   253: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   256: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   259: new 57	java/lang/StringBuilder
    //   262: dup
    //   263: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   266: ldc 116
    //   268: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: iload_2
    //   272: invokevirtual 73	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   275: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   278: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   281: new 57	java/lang/StringBuilder
    //   284: dup
    //   285: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   288: ldc 118
    //   290: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: aload 13
    //   295: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   301: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   304: new 57	java/lang/StringBuilder
    //   307: dup
    //   308: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   311: ldc 120
    //   313: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: iload 11
    //   318: invokevirtual 123	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   321: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   324: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   327: new 57	java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   334: ldc 125
    //   336: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: getstatic 131	zv:e	I
    //   342: invokevirtual 73	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   345: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   348: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   351: iload 11
    //   353: ifeq +272 -> 625
    //   356: getstatic 131	zv:e	I
    //   359: ifgt +6 -> 365
    //   362: goto +263 -> 625
    //   365: aload_0
    //   366: getfield 11	abq:a	Landroid/content/Context;
    //   369: lload 5
    //   371: iload_2
    //   372: aload 13
    //   374: invokestatic 134	com/android/mms/util/TimerMessageHelpler:a	(Landroid/content/Context;JILjava/lang/String;)V
    //   377: lload_3
    //   378: lstore 5
    //   380: lload 5
    //   382: lstore_3
    //   383: aload_1
    //   384: invokeinterface 137 1 0
    //   389: ifne -293 -> 96
    //   392: lload 5
    //   394: lconst_0
    //   395: lcmp
    //   396: ifle +12 -> 408
    //   399: aload_0
    //   400: getfield 11	abq:a	Landroid/content/Context;
    //   403: lload 5
    //   405: invokestatic 140	com/android/mms/util/TimerMessageHelpler:a	(Landroid/content/Context;J)V
    //   408: aload_1
    //   409: ifnull +9 -> 418
    //   412: aload_1
    //   413: invokeinterface 143 1 0
    //   418: aconst_null
    //   419: areturn
    //   420: iconst_0
    //   421: istore 11
    //   423: goto -233 -> 190
    //   426: lload 9
    //   428: lload 7
    //   430: lsub
    //   431: ldc2_w 144
    //   434: lcmp
    //   435: ifge +9 -> 444
    //   438: iconst_1
    //   439: istore 11
    //   441: goto -251 -> 190
    //   444: iconst_0
    //   445: istore 11
    //   447: goto -257 -> 190
    //   450: lload 7
    //   452: invokestatic 103	java/lang/System:currentTimeMillis	()J
    //   455: lcmp
    //   456: iflt +14 -> 470
    //   459: iload 11
    //   461: ifeq +107 -> 568
    //   464: getstatic 131	zv:e	I
    //   467: ifne +101 -> 568
    //   470: ldc -109
    //   472: invokestatic 25	com/android/mms/util/TimerMessageHelpler:a	(Ljava/lang/String;)V
    //   475: new 149	android/content/ContentValues
    //   478: dup
    //   479: invokespecial 150	android/content/ContentValues:<init>	()V
    //   482: astore 13
    //   484: aload 13
    //   486: ldc -104
    //   488: iconst_5
    //   489: invokestatic 158	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   492: invokevirtual 162	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   495: aload 13
    //   497: ldc -92
    //   499: iconst_0
    //   500: invokestatic 158	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   503: invokevirtual 162	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   506: aload 13
    //   508: ldc -90
    //   510: lload 7
    //   512: invokestatic 171	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   515: invokevirtual 174	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   518: aload 12
    //   520: getstatic 37	android/provider/Telephony$Sms:CONTENT_URI	Landroid/net/Uri;
    //   523: lload 5
    //   525: invokestatic 180	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   528: aload 13
    //   530: aconst_null
    //   531: aconst_null
    //   532: invokevirtual 184	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   535: pop
    //   536: invokestatic 190	com/android/mms/MmsApp:c	()Lcom/android/mms/MmsApp;
    //   539: invokestatic 195	com/android/mms/transaction/MessagingNotification:d	(Landroid/content/Context;)V
    //   542: lload_3
    //   543: lstore 5
    //   545: goto -165 -> 380
    //   548: astore 12
    //   550: aload 12
    //   552: invokevirtual 198	java/lang/Exception:printStackTrace	()V
    //   555: aload_1
    //   556: ifnull -138 -> 418
    //   559: aload_1
    //   560: invokeinterface 143 1 0
    //   565: goto -147 -> 418
    //   568: lload_3
    //   569: lconst_0
    //   570: lcmp
    //   571: ifeq +13 -> 584
    //   574: lload_3
    //   575: lstore 5
    //   577: lload 7
    //   579: lload_3
    //   580: lcmp
    //   581: ifge -201 -> 380
    //   584: lload 7
    //   586: lstore 5
    //   588: goto -208 -> 380
    //   591: astore 12
    //   593: aconst_null
    //   594: astore_1
    //   595: aload_1
    //   596: ifnull +9 -> 605
    //   599: aload_1
    //   600: invokeinterface 143 1 0
    //   605: aload 12
    //   607: athrow
    //   608: astore 12
    //   610: goto -15 -> 595
    //   613: astore 12
    //   615: goto -20 -> 595
    //   618: astore 12
    //   620: aconst_null
    //   621: astore_1
    //   622: goto -72 -> 550
    //   625: iload_2
    //   626: sipush 256
    //   629: if_icmpne -179 -> 450
    //   632: goto -267 -> 365
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	635	0	this	abq
    //   0	635	1	paramVarArgs	Void[]
    //   142	488	2	i	int
    //   95	485	3	l1	long
    //   126	461	5	l2	long
    //   110	475	7	l3	long
    //   162	265	9	l4	long
    //   188	272	11	bool	boolean
    //   12	507	12	localContentResolver	android.content.ContentResolver
    //   548	3	12	localException1	Exception
    //   591	15	12	localObject1	Object
    //   608	1	12	localObject2	Object
    //   613	1	12	localObject3	Object
    //   618	1	12	localException2	Exception
    //   157	372	13	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   51	94	548	java/lang/Exception
    //   96	164	548	java/lang/Exception
    //   174	187	548	java/lang/Exception
    //   190	351	548	java/lang/Exception
    //   356	362	548	java/lang/Exception
    //   365	377	548	java/lang/Exception
    //   383	392	548	java/lang/Exception
    //   399	408	548	java/lang/Exception
    //   450	459	548	java/lang/Exception
    //   464	470	548	java/lang/Exception
    //   470	542	548	java/lang/Exception
    //   5	51	591	finally
    //   51	94	608	finally
    //   96	164	608	finally
    //   174	187	608	finally
    //   190	351	608	finally
    //   356	362	608	finally
    //   365	377	608	finally
    //   383	392	608	finally
    //   399	408	608	finally
    //   450	459	608	finally
    //   464	470	608	finally
    //   470	542	608	finally
    //   550	555	613	finally
    //   5	51	618	java/lang/Exception
  }
  
  protected void a(Void paramVoid)
  {
    TimerMessageHelpler.a(null);
  }
}

/* Location:
 * Qualified Name:     abq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */