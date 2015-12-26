import java.io.File;

class anz$a
  implements anz.b
{
  boolean a = true;
  File b;
  
  /* Error */
  public boolean a(java.util.zip.ZipFile paramZipFile, java.util.zip.ZipEntry paramZipEntry)
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
    //   0	237	1	paramZipFile	java.util.zip.ZipFile
    //   0	237	2	paramZipEntry	java.util.zip.ZipEntry
    //   7	44	3	bool	boolean
    //   1	187	4	localZipEntry1	java.util.zip.ZipEntry
    //   195	6	4	localObject1	Object
    //   204	15	4	localZipFile1	java.util.zip.ZipFile
    //   224	1	4	localException	Exception
    //   227	5	4	localZipFile2	java.util.zip.ZipFile
    //   4	121	5	localObject2	Object
    //   184	6	5	localObject3	Object
    //   198	9	5	localZipEntry2	java.util.zip.ZipEntry
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

/* Location:
 * Qualified Name:     anz.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */