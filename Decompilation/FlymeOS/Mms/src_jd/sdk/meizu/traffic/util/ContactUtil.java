package sdk.meizu.traffic.util;

public class ContactUtil
{
  /* Error */
  public static android.util.Pair<String, String> loadPhoneData(android.content.Context paramContext, android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 19	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: aload_1
    //   5: iconst_2
    //   6: anewarray 21	java/lang/String
    //   9: dup
    //   10: iconst_0
    //   11: ldc 23
    //   13: aastore
    //   14: dup
    //   15: iconst_1
    //   16: ldc 25
    //   18: aastore
    //   19: aconst_null
    //   20: aconst_null
    //   21: aconst_null
    //   22: invokevirtual 31	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   25: astore_0
    //   26: aload_0
    //   27: astore_1
    //   28: aload_0
    //   29: invokeinterface 37 1 0
    //   34: ifeq +115 -> 149
    //   37: aload_0
    //   38: astore_1
    //   39: aload_0
    //   40: iconst_0
    //   41: invokeinterface 41 2 0
    //   46: astore_2
    //   47: aload_2
    //   48: ifnonnull +98 -> 146
    //   51: ldc 43
    //   53: astore_2
    //   54: aload_0
    //   55: astore_1
    //   56: aload_0
    //   57: iconst_1
    //   58: invokeinterface 41 2 0
    //   63: astore_3
    //   64: aload_3
    //   65: ifnonnull +78 -> 143
    //   68: ldc 43
    //   70: astore_3
    //   71: aload_0
    //   72: astore_1
    //   73: new 45	android/util/Pair
    //   76: dup
    //   77: aload_2
    //   78: aload_3
    //   79: invokespecial 48	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   82: astore_2
    //   83: aload_2
    //   84: astore_1
    //   85: aload_0
    //   86: ifnull +9 -> 95
    //   89: aload_0
    //   90: invokeinterface 51 1 0
    //   95: aload_1
    //   96: areturn
    //   97: astore_2
    //   98: aconst_null
    //   99: astore_0
    //   100: aload_0
    //   101: astore_1
    //   102: aload_2
    //   103: invokevirtual 54	java/lang/Exception:printStackTrace	()V
    //   106: aload_0
    //   107: ifnull +34 -> 141
    //   110: aload_0
    //   111: invokeinterface 51 1 0
    //   116: aconst_null
    //   117: areturn
    //   118: astore_0
    //   119: aconst_null
    //   120: astore_1
    //   121: aload_1
    //   122: ifnull +9 -> 131
    //   125: aload_1
    //   126: invokeinterface 51 1 0
    //   131: aload_0
    //   132: athrow
    //   133: astore_0
    //   134: goto -13 -> 121
    //   137: astore_2
    //   138: goto -38 -> 100
    //   141: aconst_null
    //   142: areturn
    //   143: goto -72 -> 71
    //   146: goto -92 -> 54
    //   149: aconst_null
    //   150: astore_1
    //   151: goto -66 -> 85
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	paramContext	android.content.Context
    //   0	154	1	paramUri	android.net.Uri
    //   46	38	2	localObject	Object
    //   97	6	2	localException1	Exception
    //   137	1	2	localException2	Exception
    //   63	16	3	str	String
    // Exception table:
    //   from	to	target	type
    //   0	26	97	java/lang/Exception
    //   0	26	118	finally
    //   28	37	133	finally
    //   39	47	133	finally
    //   56	64	133	finally
    //   73	83	133	finally
    //   102	106	133	finally
    //   28	37	137	java/lang/Exception
    //   39	47	137	java/lang/Exception
    //   56	64	137	java/lang/Exception
    //   73	83	137	java/lang/Exception
  }
  
  /* Error */
  public static java.util.ArrayList<android.util.Pair<String, String>> searchContactsByNum(android.content.Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 60	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 61	java/util/ArrayList:<init>	()V
    //   9: astore 6
    //   11: aload_0
    //   12: invokevirtual 19	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   15: astore_0
    //   16: getstatic 67	android/provider/ContactsContract$CommonDataKinds$Phone:CONTENT_URI	Landroid/net/Uri;
    //   19: astore 4
    //   21: new 69	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   28: ldc 72
    //   30: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_1
    //   34: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: ldc 72
    //   39: invokevirtual 76	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: astore_1
    //   46: aload_0
    //   47: aload 4
    //   49: iconst_2
    //   50: anewarray 21	java/lang/String
    //   53: dup
    //   54: iconst_0
    //   55: ldc 23
    //   57: aastore
    //   58: dup
    //   59: iconst_1
    //   60: ldc 25
    //   62: aastore
    //   63: ldc 82
    //   65: iconst_1
    //   66: anewarray 21	java/lang/String
    //   69: dup
    //   70: iconst_0
    //   71: aload_1
    //   72: aastore
    //   73: aconst_null
    //   74: invokevirtual 31	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   77: astore_0
    //   78: aload_0
    //   79: astore_1
    //   80: aload_0
    //   81: invokeinterface 37 1 0
    //   86: ifeq +75 -> 161
    //   89: aload_0
    //   90: astore_1
    //   91: aload_0
    //   92: iconst_0
    //   93: invokeinterface 41 2 0
    //   98: astore_3
    //   99: aload_3
    //   100: ifnonnull +119 -> 219
    //   103: ldc 43
    //   105: astore_3
    //   106: aload_0
    //   107: astore_1
    //   108: aload_0
    //   109: iconst_1
    //   110: invokeinterface 41 2 0
    //   115: astore 5
    //   117: aload 5
    //   119: astore 4
    //   121: aload 5
    //   123: ifnonnull +7 -> 130
    //   126: ldc 43
    //   128: astore 4
    //   130: aload_0
    //   131: astore_1
    //   132: aload 6
    //   134: new 45	android/util/Pair
    //   137: dup
    //   138: aload_3
    //   139: aload 4
    //   141: invokespecial 48	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   144: invokevirtual 86	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   147: pop
    //   148: aload_0
    //   149: astore_1
    //   150: aload_0
    //   151: invokeinterface 89 1 0
    //   156: istore_2
    //   157: iload_2
    //   158: ifne -69 -> 89
    //   161: aload_0
    //   162: ifnull +9 -> 171
    //   165: aload_0
    //   166: invokeinterface 51 1 0
    //   171: aload 6
    //   173: areturn
    //   174: astore_3
    //   175: aconst_null
    //   176: astore_0
    //   177: aload_0
    //   178: astore_1
    //   179: aload_3
    //   180: invokevirtual 54	java/lang/Exception:printStackTrace	()V
    //   183: aload_0
    //   184: ifnull -13 -> 171
    //   187: aload_0
    //   188: invokeinterface 51 1 0
    //   193: aload 6
    //   195: areturn
    //   196: astore_0
    //   197: aload_3
    //   198: astore_1
    //   199: aload_1
    //   200: ifnull +9 -> 209
    //   203: aload_1
    //   204: invokeinterface 51 1 0
    //   209: aload_0
    //   210: athrow
    //   211: astore_0
    //   212: goto -13 -> 199
    //   215: astore_3
    //   216: goto -39 -> 177
    //   219: goto -113 -> 106
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	222	0	paramContext	android.content.Context
    //   0	222	1	paramString	String
    //   156	2	2	bool	boolean
    //   1	138	3	str1	String
    //   174	24	3	localException1	Exception
    //   215	1	3	localException2	Exception
    //   19	121	4	localObject	Object
    //   115	7	5	str2	String
    //   9	185	6	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   11	78	174	java/lang/Exception
    //   11	78	196	finally
    //   80	89	211	finally
    //   91	99	211	finally
    //   108	117	211	finally
    //   132	148	211	finally
    //   150	157	211	finally
    //   179	183	211	finally
    //   80	89	215	java/lang/Exception
    //   91	99	215	java/lang/Exception
    //   108	117	215	java/lang/Exception
    //   132	148	215	java/lang/Exception
    //   150	157	215	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.util.ContactUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */