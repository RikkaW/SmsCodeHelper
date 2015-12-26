public class aok
{
  private static final String a = aok.class.getSimpleName();
  
  /* Error */
  public static void a(android.database.sqlite.SQLiteDatabase paramSQLiteDatabase, android.content.Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 27	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 28	java/lang/StringBuilder:<init>	()V
    //   7: astore 7
    //   9: aconst_null
    //   10: astore 6
    //   12: aconst_null
    //   13: astore 5
    //   15: aload_1
    //   16: invokevirtual 34	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   19: aload_2
    //   20: invokevirtual 40	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   23: astore_1
    //   24: aload_1
    //   25: astore 5
    //   27: aload_1
    //   28: astore 6
    //   30: new 42	java/io/BufferedReader
    //   33: dup
    //   34: new 44	java/io/InputStreamReader
    //   37: dup
    //   38: aload_1
    //   39: invokespecial 47	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   42: invokespecial 50	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   45: astore_2
    //   46: aload_1
    //   47: astore 5
    //   49: aload_1
    //   50: astore 6
    //   52: aload_2
    //   53: invokevirtual 53	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   56: astore 8
    //   58: aload 8
    //   60: ifnonnull +49 -> 109
    //   63: aload_1
    //   64: ifnull +7 -> 71
    //   67: aload_1
    //   68: invokevirtual 58	java/io/InputStream:close	()V
    //   71: aload 7
    //   73: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: ldc 63
    //   78: ldc 65
    //   80: invokevirtual 71	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   83: ldc 73
    //   85: ldc 65
    //   87: invokevirtual 71	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   90: ldc 75
    //   92: invokevirtual 79	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   95: astore_1
    //   96: aload_1
    //   97: arraylength
    //   98: istore 4
    //   100: iconst_0
    //   101: istore_3
    //   102: iload_3
    //   103: iload 4
    //   105: if_icmplt +118 -> 223
    //   108: return
    //   109: aload_1
    //   110: astore 5
    //   112: aload_1
    //   113: astore 6
    //   115: aload 7
    //   117: aload 8
    //   119: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: pop
    //   123: aload_1
    //   124: astore 5
    //   126: aload_1
    //   127: astore 6
    //   129: aload 7
    //   131: ldc 73
    //   133: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: pop
    //   137: goto -91 -> 46
    //   140: astore_1
    //   141: aload 5
    //   143: astore 6
    //   145: getstatic 16	aok:a	Ljava/lang/String;
    //   148: ldc 85
    //   150: aload_1
    //   151: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   154: pop
    //   155: aload 5
    //   157: ifnull -86 -> 71
    //   160: aload 5
    //   162: invokevirtual 58	java/io/InputStream:close	()V
    //   165: goto -94 -> 71
    //   168: astore_1
    //   169: getstatic 16	aok:a	Ljava/lang/String;
    //   172: ldc 93
    //   174: aload_1
    //   175: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   178: pop
    //   179: goto -108 -> 71
    //   182: astore_0
    //   183: aload 6
    //   185: ifnull +8 -> 193
    //   188: aload 6
    //   190: invokevirtual 58	java/io/InputStream:close	()V
    //   193: aload_0
    //   194: athrow
    //   195: astore_1
    //   196: getstatic 16	aok:a	Ljava/lang/String;
    //   199: ldc 93
    //   201: aload_1
    //   202: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   205: pop
    //   206: goto -13 -> 193
    //   209: astore_1
    //   210: getstatic 16	aok:a	Ljava/lang/String;
    //   213: ldc 93
    //   215: aload_1
    //   216: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   219: pop
    //   220: goto -149 -> 71
    //   223: aload_1
    //   224: iload_3
    //   225: aaload
    //   226: astore_2
    //   227: aload_2
    //   228: invokevirtual 96	java/lang/String:trim	()Ljava/lang/String;
    //   231: invokevirtual 100	java/lang/String:length	()I
    //   234: ifle +8 -> 242
    //   237: aload_0
    //   238: aload_2
    //   239: invokevirtual 106	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   242: iload_3
    //   243: iconst_1
    //   244: iadd
    //   245: istore_3
    //   246: goto -144 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	249	0	paramSQLiteDatabase	android.database.sqlite.SQLiteDatabase
    //   0	249	1	paramContext	android.content.Context
    //   0	249	2	paramString	String
    //   101	145	3	i	int
    //   98	8	4	j	int
    //   13	148	5	localContext	android.content.Context
    //   10	179	6	localObject	Object
    //   7	123	7	localStringBuilder	StringBuilder
    //   56	62	8	str	String
    // Exception table:
    //   from	to	target	type
    //   15	24	140	java/lang/Exception
    //   30	46	140	java/lang/Exception
    //   52	58	140	java/lang/Exception
    //   115	123	140	java/lang/Exception
    //   129	137	140	java/lang/Exception
    //   160	165	168	java/io/IOException
    //   15	24	182	finally
    //   30	46	182	finally
    //   52	58	182	finally
    //   115	123	182	finally
    //   129	137	182	finally
    //   145	155	182	finally
    //   188	193	195	java/io/IOException
    //   67	71	209	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     aok
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */