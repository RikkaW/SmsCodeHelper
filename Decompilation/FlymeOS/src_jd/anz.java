import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class anz
{
  private static final String a = anz.class.getSimpleName();
  
  /* Error */
  public static void a(String paramString, anz.b paramb)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 30	java/util/zip/ZipFile
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 33	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: aload_0
    //   12: invokevirtual 37	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   15: astore_3
    //   16: aload_3
    //   17: invokeinterface 43 1 0
    //   22: istore_2
    //   23: iload_2
    //   24: ifne +12 -> 36
    //   27: aload_0
    //   28: ifnull +7 -> 35
    //   31: aload_0
    //   32: invokevirtual 46	java/util/zip/ZipFile:close	()V
    //   35: return
    //   36: aload_1
    //   37: aload_0
    //   38: aload_3
    //   39: invokeinterface 50 1 0
    //   44: checkcast 52	java/util/zip/ZipEntry
    //   47: invokeinterface 55 3 0
    //   52: istore_2
    //   53: iload_2
    //   54: ifeq -38 -> 16
    //   57: goto -30 -> 27
    //   60: astore_0
    //   61: aload_3
    //   62: astore_0
    //   63: aload_0
    //   64: ifnull -29 -> 35
    //   67: aload_0
    //   68: invokevirtual 46	java/util/zip/ZipFile:close	()V
    //   71: return
    //   72: astore_0
    //   73: return
    //   74: astore_1
    //   75: aconst_null
    //   76: astore_0
    //   77: aload_0
    //   78: ifnull +7 -> 85
    //   81: aload_0
    //   82: invokevirtual 46	java/util/zip/ZipFile:close	()V
    //   85: aload_1
    //   86: athrow
    //   87: astore_0
    //   88: goto -3 -> 85
    //   91: astore_0
    //   92: return
    //   93: astore_1
    //   94: goto -17 -> 77
    //   97: astore_1
    //   98: goto -35 -> 63
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	101	0	paramString	String
    //   0	101	1	paramb	anz.b
    //   22	32	2	bool	boolean
    //   1	61	3	localEnumeration	java.util.Enumeration
    // Exception table:
    //   from	to	target	type
    //   2	11	60	java/lang/Exception
    //   67	71	72	java/lang/Exception
    //   2	11	74	finally
    //   81	85	87	java/lang/Exception
    //   31	35	91	java/lang/Exception
    //   11	16	93	finally
    //   16	23	93	finally
    //   36	53	93	finally
    //   11	16	97	java/lang/Exception
    //   16	23	97	java/lang/Exception
    //   36	53	97	java/lang/Exception
  }
  
  /* Error */
  private static void a(java.util.zip.ZipOutputStream paramZipOutputStream, File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 63	java/io/File:isDirectory	()Z
    //   4: ifeq +70 -> 74
    //   7: aload_1
    //   8: invokevirtual 67	java/io/File:listFiles	()[Ljava/io/File;
    //   11: astore_1
    //   12: aload_1
    //   13: ifnull +15 -> 28
    //   16: aload_1
    //   17: arraylength
    //   18: istore 4
    //   20: iconst_0
    //   21: istore_3
    //   22: iload_3
    //   23: iload 4
    //   25: if_icmplt +4 -> 29
    //   28: return
    //   29: aload_1
    //   30: iload_3
    //   31: aaload
    //   32: astore 5
    //   34: aload_0
    //   35: aload 5
    //   37: new 69	java/lang/StringBuilder
    //   40: dup
    //   41: aload_2
    //   42: invokestatic 75	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   45: invokespecial 76	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   48: ldc 78
    //   50: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload 5
    //   55: invokevirtual 85	java/io/File:getName	()Ljava/lang/String;
    //   58: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: invokestatic 90	anz:a	(Ljava/util/zip/ZipOutputStream;Ljava/io/File;Ljava/lang/String;)V
    //   67: iload_3
    //   68: iconst_1
    //   69: iadd
    //   70: istore_3
    //   71: goto -49 -> 22
    //   74: aload_1
    //   75: invokevirtual 93	java/io/File:canRead	()Z
    //   78: ifeq -50 -> 28
    //   81: aconst_null
    //   82: astore 5
    //   84: new 95	java/io/FileInputStream
    //   87: dup
    //   88: aload_1
    //   89: invokespecial 98	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   92: astore_1
    //   93: aload_0
    //   94: new 52	java/util/zip/ZipEntry
    //   97: dup
    //   98: aload_2
    //   99: invokespecial 99	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   102: invokevirtual 105	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   105: aload_1
    //   106: aload_0
    //   107: invokestatic 111	com/ted/android/contacts/common/util/FileUtil:copyStream	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   110: aload_0
    //   111: invokevirtual 114	java/util/zip/ZipOutputStream:closeEntry	()V
    //   114: aload_1
    //   115: ifnull -87 -> 28
    //   118: aload_1
    //   119: invokevirtual 115	java/io/FileInputStream:close	()V
    //   122: return
    //   123: astore_0
    //   124: return
    //   125: astore_0
    //   126: aconst_null
    //   127: astore_1
    //   128: aload_1
    //   129: ifnull -101 -> 28
    //   132: aload_1
    //   133: invokevirtual 115	java/io/FileInputStream:close	()V
    //   136: return
    //   137: astore_0
    //   138: return
    //   139: astore_0
    //   140: aload 5
    //   142: astore_1
    //   143: aload_1
    //   144: ifnull +7 -> 151
    //   147: aload_1
    //   148: invokevirtual 115	java/io/FileInputStream:close	()V
    //   151: aload_0
    //   152: athrow
    //   153: astore_1
    //   154: goto -3 -> 151
    //   157: astore_0
    //   158: goto -15 -> 143
    //   161: astore_0
    //   162: goto -34 -> 128
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	paramZipOutputStream	java.util.zip.ZipOutputStream
    //   0	165	1	paramFile	File
    //   0	165	2	paramString	String
    //   21	50	3	i	int
    //   18	8	4	j	int
    //   32	109	5	localFile	File
    // Exception table:
    //   from	to	target	type
    //   118	122	123	java/io/IOException
    //   84	93	125	java/lang/Exception
    //   132	136	137	java/io/IOException
    //   84	93	139	finally
    //   147	151	153	java/io/IOException
    //   93	114	157	finally
    //   93	114	161	java/lang/Exception
  }
  
  /* Error */
  public static boolean a(File paramFile1, File paramFile2, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 101	java/util/zip/ZipOutputStream
    //   5: dup
    //   6: new 118	java/io/FileOutputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 119	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   14: invokespecial 122	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   17: astore_0
    //   18: aload_0
    //   19: aload_1
    //   20: aload_2
    //   21: invokestatic 90	anz:a	(Ljava/util/zip/ZipOutputStream;Ljava/io/File;Ljava/lang/String;)V
    //   24: aload_0
    //   25: invokevirtual 125	java/util/zip/ZipOutputStream:finish	()V
    //   28: aload_0
    //   29: invokevirtual 128	java/util/zip/ZipOutputStream:flush	()V
    //   32: aload_0
    //   33: ifnull +7 -> 40
    //   36: aload_0
    //   37: invokevirtual 129	java/util/zip/ZipOutputStream:close	()V
    //   40: iconst_1
    //   41: ireturn
    //   42: astore_0
    //   43: aconst_null
    //   44: astore_0
    //   45: aload_0
    //   46: ifnull +7 -> 53
    //   49: aload_0
    //   50: invokevirtual 129	java/util/zip/ZipOutputStream:close	()V
    //   53: iconst_0
    //   54: ireturn
    //   55: astore_0
    //   56: aload_3
    //   57: astore_1
    //   58: aload_1
    //   59: ifnull +7 -> 66
    //   62: aload_1
    //   63: invokevirtual 129	java/util/zip/ZipOutputStream:close	()V
    //   66: aload_0
    //   67: athrow
    //   68: astore_0
    //   69: goto -29 -> 40
    //   72: astore_0
    //   73: goto -20 -> 53
    //   76: astore_1
    //   77: goto -11 -> 66
    //   80: astore_2
    //   81: aload_0
    //   82: astore_1
    //   83: aload_2
    //   84: astore_0
    //   85: goto -27 -> 58
    //   88: astore_1
    //   89: goto -44 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	paramFile1	File
    //   0	92	1	paramFile2	File
    //   0	92	2	paramString	String
    //   1	56	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	18	42	java/lang/Exception
    //   2	18	55	finally
    //   36	40	68	java/io/IOException
    //   49	53	72	java/io/IOException
    //   62	66	76	java/io/IOException
    //   18	32	80	finally
    //   18	32	88	java/lang/Exception
  }
  
  public static boolean a(String paramString, File paramFile)
  {
    anz.a locala = new anz.a();
    b = paramFile;
    a(paramString, locala);
    return a;
  }
  
  static class a
    implements anz.b
  {
    boolean a = true;
    File b;
    
    /* Error */
    public boolean a(ZipFile paramZipFile, ZipEntry paramZipEntry)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: aconst_null
      //   4: astore 5
      //   6: iconst_1
      //   7: istore_3
      //   8: new 24	java/io/File
      //   11: dup
      //   12: aload_0
      //   13: getfield 26	anz$a:b	Ljava/io/File;
      //   16: aload_2
      //   17: invokevirtual 32	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
      //   20: invokespecial 35	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   23: astore 6
      //   25: aload_2
      //   26: invokevirtual 39	java/util/zip/ZipEntry:isDirectory	()Z
      //   29: ifeq +30 -> 59
      //   32: aload 6
      //   34: invokevirtual 42	java/io/File:exists	()Z
      //   37: ifne +11 -> 48
      //   40: aload 6
      //   42: invokevirtual 45	java/io/File:mkdirs	()Z
      //   45: ifeq +7 -> 52
      //   48: iconst_0
      //   49: istore_3
      //   50: iload_3
      //   51: ireturn
      //   52: aload_0
      //   53: iconst_0
      //   54: putfield 18	anz$a:a	Z
      //   57: iconst_1
      //   58: ireturn
      //   59: aload 6
      //   61: invokevirtual 49	java/io/File:getParentFile	()Ljava/io/File;
      //   64: astore 7
      //   66: aload 7
      //   68: invokevirtual 42	java/io/File:exists	()Z
      //   71: ifne +11 -> 82
      //   74: aload 7
      //   76: invokevirtual 45	java/io/File:mkdirs	()Z
      //   79: ifeq +94 -> 173
      //   82: aload_1
      //   83: aload_2
      //   84: invokevirtual 55	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
      //   87: astore_1
      //   88: new 57	java/io/FileOutputStream
      //   91: dup
      //   92: aload 6
      //   94: invokespecial 60	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   97: astore_2
      //   98: aload_1
      //   99: aload_2
      //   100: invokestatic 66	com/ted/android/contacts/common/util/FileUtil:copyStream	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
      //   103: aload_1
      //   104: ifnull +7 -> 111
      //   107: aload_1
      //   108: invokevirtual 71	java/io/InputStream:close	()V
      //   111: aload_2
      //   112: ifnull +7 -> 119
      //   115: aload_2
      //   116: invokevirtual 74	java/io/OutputStream:close	()V
      //   119: iconst_0
      //   120: ireturn
      //   121: astore_1
      //   122: aconst_null
      //   123: astore_1
      //   124: aload 5
      //   126: astore_2
      //   127: aload_0
      //   128: iconst_0
      //   129: putfield 18	anz$a:a	Z
      //   132: aload_2
      //   133: ifnull +7 -> 140
      //   136: aload_2
      //   137: invokevirtual 71	java/io/InputStream:close	()V
      //   140: aload_1
      //   141: ifnull -91 -> 50
      //   144: aload_1
      //   145: invokevirtual 74	java/io/OutputStream:close	()V
      //   148: iconst_1
      //   149: ireturn
      //   150: astore_2
      //   151: aconst_null
      //   152: astore_1
      //   153: aload_1
      //   154: ifnull +7 -> 161
      //   157: aload_1
      //   158: invokevirtual 71	java/io/InputStream:close	()V
      //   161: aload 4
      //   163: ifnull +8 -> 171
      //   166: aload 4
      //   168: invokevirtual 74	java/io/OutputStream:close	()V
      //   171: aload_2
      //   172: athrow
      //   173: aload_0
      //   174: iconst_0
      //   175: putfield 18	anz$a:a	Z
      //   178: iconst_1
      //   179: ireturn
      //   180: astore_2
      //   181: goto -28 -> 153
      //   184: astore 5
      //   186: aload_2
      //   187: astore 4
      //   189: aload 5
      //   191: astore_2
      //   192: goto -39 -> 153
      //   195: astore 4
      //   197: aload_2
      //   198: astore 5
      //   200: aload 4
      //   202: astore_2
      //   203: aload_1
      //   204: astore 4
      //   206: aload 5
      //   208: astore_1
      //   209: goto -56 -> 153
      //   212: astore_2
      //   213: aconst_null
      //   214: astore 4
      //   216: aload_1
      //   217: astore_2
      //   218: aload 4
      //   220: astore_1
      //   221: goto -94 -> 127
      //   224: astore 4
      //   226: aload_1
      //   227: astore 4
      //   229: aload_2
      //   230: astore_1
      //   231: aload 4
      //   233: astore_2
      //   234: goto -107 -> 127
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	237	0	this	a
      //   0	237	1	paramZipFile	ZipFile
      //   0	237	2	paramZipEntry	ZipEntry
      //   7	44	3	bool	boolean
      //   1	187	4	localZipEntry1	ZipEntry
      //   195	6	4	localObject1	Object
      //   204	15	4	localZipFile1	ZipFile
      //   224	1	4	localException	Exception
      //   227	5	4	localZipFile2	ZipFile
      //   4	121	5	localObject2	Object
      //   184	6	5	localObject3	Object
      //   198	9	5	localZipEntry2	ZipEntry
      //   23	70	6	localFile1	File
      //   64	11	7	localFile2	File
      // Exception table:
      //   from	to	target	type
      //   82	88	121	java/lang/Exception
      //   82	88	150	finally
      //   88	98	180	finally
      //   98	103	184	finally
      //   127	132	195	finally
      //   88	98	212	java/lang/Exception
      //   98	103	224	java/lang/Exception
    }
  }
  
  public static abstract interface b
  {
    public abstract boolean a(ZipFile paramZipFile, ZipEntry paramZipEntry);
  }
}

/* Location:
 * Qualified Name:     anz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */