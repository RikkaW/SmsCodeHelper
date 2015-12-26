import com.ted.android.contacts.common.DataBus;
import com.ted.android.contacts.common.util.FileUtil;
import java.io.File;

public class bj
{
  public static boolean a(File paramFile1, File paramFile2, boolean paramBoolean)
  {
    if ((paramFile1.exists()) && (paramFile1.length() > 0L))
    {
      String str = null;
      if (paramBoolean) {
        str = DataBus.U1_YEK;
      }
      return a(paramFile1, str, paramFile2);
    }
    return false;
  }
  
  public static boolean a(File paramFile1, String paramString, File paramFile2)
  {
    paramFile1 = FileUtil.readFileByte(paramFile1);
    if (paramFile1 != null) {
      return a(paramFile1, paramString, paramFile2);
    }
    return false;
  }
  
  /* Error */
  public static boolean a(byte[] paramArrayOfByte, String paramString, File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: iconst_0
    //   4: istore 4
    //   6: new 41	java/io/ByteArrayInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 45	java/io/ByteArrayInputStream:<init>	([B)V
    //   14: astore 5
    //   16: new 47	bi
    //   19: dup
    //   20: aload 5
    //   22: aload_1
    //   23: invokespecial 50	bi:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   26: astore_1
    //   27: aload_2
    //   28: invokevirtual 12	java/io/File:exists	()Z
    //   31: ifne +8 -> 39
    //   34: aload_2
    //   35: invokevirtual 53	java/io/File:mkdirs	()Z
    //   38: pop
    //   39: aload_1
    //   40: invokevirtual 57	bi:b	()Lbh;
    //   43: astore_0
    //   44: aload_0
    //   45: ifnonnull +27 -> 72
    //   48: aload_1
    //   49: ifnull +7 -> 56
    //   52: aload_1
    //   53: invokevirtual 61	bi:close	()V
    //   56: aload 5
    //   58: ifnull +8 -> 66
    //   61: aload 5
    //   63: invokevirtual 64	java/io/InputStream:close	()V
    //   66: iconst_1
    //   67: istore 4
    //   69: iload 4
    //   71: ireturn
    //   72: new 66	java/io/ByteArrayOutputStream
    //   75: dup
    //   76: invokespecial 68	java/io/ByteArrayOutputStream:<init>	()V
    //   79: astore 6
    //   81: sipush 1024
    //   84: newarray <illegal type>
    //   86: astore 7
    //   88: aload_1
    //   89: aload 7
    //   91: invokevirtual 72	bi:read	([B)I
    //   94: istore_3
    //   95: iload_3
    //   96: ifgt +65 -> 161
    //   99: aload 6
    //   101: invokevirtual 76	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   104: astore 7
    //   106: aload 6
    //   108: invokevirtual 77	java/io/ByteArrayOutputStream:close	()V
    //   111: aload 7
    //   113: new 8	java/io/File
    //   116: dup
    //   117: aload_2
    //   118: aload_0
    //   119: invokevirtual 83	bh:getName	()Ljava/lang/String;
    //   122: invokespecial 86	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   125: invokestatic 90	com/ted/android/contacts/common/util/FileUtil:writeByteFile	([BLjava/io/File;)Z
    //   128: pop
    //   129: goto -90 -> 39
    //   132: astore_2
    //   133: aload 5
    //   135: astore_0
    //   136: aload_2
    //   137: invokevirtual 93	java/lang/Throwable:printStackTrace	()V
    //   140: aload_1
    //   141: ifnull +7 -> 148
    //   144: aload_1
    //   145: invokevirtual 61	bi:close	()V
    //   148: aload_0
    //   149: ifnull -80 -> 69
    //   152: aload_0
    //   153: invokevirtual 64	java/io/InputStream:close	()V
    //   156: iconst_0
    //   157: ireturn
    //   158: astore_0
    //   159: iconst_0
    //   160: ireturn
    //   161: aload 6
    //   163: aload 7
    //   165: iconst_0
    //   166: iload_3
    //   167: invokevirtual 97	java/io/ByteArrayOutputStream:write	([BII)V
    //   170: goto -82 -> 88
    //   173: astore_0
    //   174: aload_1
    //   175: ifnull +7 -> 182
    //   178: aload_1
    //   179: invokevirtual 61	bi:close	()V
    //   182: aload 5
    //   184: ifnull +8 -> 192
    //   187: aload 5
    //   189: invokevirtual 64	java/io/InputStream:close	()V
    //   192: aload_0
    //   193: athrow
    //   194: astore_0
    //   195: goto -139 -> 56
    //   198: astore_0
    //   199: goto -133 -> 66
    //   202: astore_1
    //   203: goto -55 -> 148
    //   206: astore_1
    //   207: goto -25 -> 182
    //   210: astore_1
    //   211: goto -19 -> 192
    //   214: astore_0
    //   215: aconst_null
    //   216: astore_1
    //   217: aconst_null
    //   218: astore 5
    //   220: goto -46 -> 174
    //   223: astore_0
    //   224: aconst_null
    //   225: astore_1
    //   226: goto -52 -> 174
    //   229: astore_2
    //   230: aload_0
    //   231: astore 5
    //   233: aload_2
    //   234: astore_0
    //   235: goto -61 -> 174
    //   238: astore_2
    //   239: aconst_null
    //   240: astore_1
    //   241: aload 6
    //   243: astore_0
    //   244: goto -108 -> 136
    //   247: astore_2
    //   248: aconst_null
    //   249: astore_1
    //   250: aload 5
    //   252: astore_0
    //   253: goto -117 -> 136
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	256	0	paramArrayOfByte	byte[]
    //   0	256	1	paramString	String
    //   0	256	2	paramFile	File
    //   94	73	3	i	int
    //   4	66	4	bool	boolean
    //   14	237	5	localObject	Object
    //   1	241	6	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   86	78	7	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   27	39	132	java/lang/Throwable
    //   39	44	132	java/lang/Throwable
    //   72	88	132	java/lang/Throwable
    //   88	95	132	java/lang/Throwable
    //   99	129	132	java/lang/Throwable
    //   161	170	132	java/lang/Throwable
    //   152	156	158	java/lang/Exception
    //   27	39	173	finally
    //   39	44	173	finally
    //   72	88	173	finally
    //   88	95	173	finally
    //   99	129	173	finally
    //   161	170	173	finally
    //   52	56	194	java/lang/Exception
    //   61	66	198	java/lang/Exception
    //   144	148	202	java/lang/Exception
    //   178	182	206	java/lang/Exception
    //   187	192	210	java/lang/Exception
    //   6	16	214	finally
    //   16	27	223	finally
    //   136	140	229	finally
    //   6	16	238	java/lang/Throwable
    //   16	27	247	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     bj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */