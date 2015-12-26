package com.ted.android.contacts.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil
{
  private static final boolean DEBUG = false;
  public static final int MAX_FILE_SIZE_TO_GET_MD5 = 10485760;
  
  /* Error */
  public static boolean copyAssetToFile(android.content.Context paramContext, String paramString, File paramFile, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: iconst_0
    //   7: istore 4
    //   9: aload_0
    //   10: invokevirtual 27	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   13: aload_1
    //   14: invokevirtual 33	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   17: astore 7
    //   19: aload 7
    //   21: astore 5
    //   23: new 35	java/io/FileOutputStream
    //   26: dup
    //   27: aload_2
    //   28: invokespecial 38	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   31: astore_2
    //   32: aload 5
    //   34: aload_2
    //   35: invokestatic 42	com/ted/android/contacts/common/util/FileUtil:copyStream	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   38: iload_3
    //   39: ifeq +13 -> 52
    //   42: aload_0
    //   43: aload_1
    //   44: aload_0
    //   45: aload_1
    //   46: invokestatic 48	com/ted/android/contacts/common/util/NovoFileUtil:readAssetsTimestamp	(Landroid/content/Context;Ljava/lang/String;)J
    //   49: invokestatic 52	com/ted/android/contacts/common/util/NovoFileUtil:writeTimestamp2File	(Landroid/content/Context;Ljava/lang/String;J)V
    //   52: iconst_1
    //   53: istore 4
    //   55: aload 5
    //   57: ifnull +8 -> 65
    //   60: aload 5
    //   62: invokevirtual 57	java/io/InputStream:close	()V
    //   65: iload 4
    //   67: istore_3
    //   68: aload_2
    //   69: ifnull +10 -> 79
    //   72: aload_2
    //   73: invokevirtual 60	java/io/OutputStream:close	()V
    //   76: iload 4
    //   78: istore_3
    //   79: iload_3
    //   80: ireturn
    //   81: astore_0
    //   82: aconst_null
    //   83: astore_0
    //   84: aload 5
    //   86: astore_1
    //   87: aload_1
    //   88: ifnull +7 -> 95
    //   91: aload_1
    //   92: invokevirtual 57	java/io/InputStream:close	()V
    //   95: iload 4
    //   97: istore_3
    //   98: aload_0
    //   99: ifnull -20 -> 79
    //   102: aload_0
    //   103: invokevirtual 60	java/io/OutputStream:close	()V
    //   106: iconst_0
    //   107: ireturn
    //   108: astore_0
    //   109: iconst_0
    //   110: ireturn
    //   111: astore_0
    //   112: aconst_null
    //   113: astore 5
    //   115: aload 6
    //   117: astore_1
    //   118: aload 5
    //   120: ifnull +8 -> 128
    //   123: aload 5
    //   125: invokevirtual 57	java/io/InputStream:close	()V
    //   128: aload_1
    //   129: ifnull +7 -> 136
    //   132: aload_1
    //   133: invokevirtual 60	java/io/OutputStream:close	()V
    //   136: aload_0
    //   137: athrow
    //   138: astore_1
    //   139: goto -44 -> 95
    //   142: astore_2
    //   143: goto -15 -> 128
    //   146: astore_1
    //   147: goto -11 -> 136
    //   150: astore_0
    //   151: goto -86 -> 65
    //   154: astore_0
    //   155: iconst_1
    //   156: ireturn
    //   157: astore_0
    //   158: aload 6
    //   160: astore_1
    //   161: goto -43 -> 118
    //   164: astore_0
    //   165: aload_2
    //   166: astore_1
    //   167: goto -49 -> 118
    //   170: astore_0
    //   171: aconst_null
    //   172: astore_0
    //   173: aload 5
    //   175: astore_1
    //   176: goto -89 -> 87
    //   179: astore_0
    //   180: aload 5
    //   182: astore_1
    //   183: aload_2
    //   184: astore_0
    //   185: goto -98 -> 87
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	paramContext	android.content.Context
    //   0	188	1	paramString	String
    //   0	188	2	paramFile	File
    //   0	188	3	paramBoolean	boolean
    //   7	89	4	bool	boolean
    //   4	177	5	localObject1	Object
    //   1	158	6	localObject2	Object
    //   17	3	7	localInputStream	InputStream
    // Exception table:
    //   from	to	target	type
    //   9	19	81	java/io/IOException
    //   102	106	108	java/lang/Exception
    //   9	19	111	finally
    //   91	95	138	java/lang/Exception
    //   123	128	142	java/lang/Exception
    //   132	136	146	java/lang/Exception
    //   60	65	150	java/lang/Exception
    //   72	76	154	java/lang/Exception
    //   23	32	157	finally
    //   32	38	164	finally
    //   42	52	164	finally
    //   23	32	170	java/io/IOException
    //   32	38	179	java/io/IOException
    //   42	52	179	java/io/IOException
  }
  
  /* Error */
  public static boolean copyFile(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iload_3
    //   3: istore_2
    //   4: aload_0
    //   5: ifnull +48 -> 53
    //   8: iload_3
    //   9: istore_2
    //   10: aload_0
    //   11: invokevirtual 68	java/io/File:exists	()Z
    //   14: ifeq +39 -> 53
    //   17: new 70	java/io/FileInputStream
    //   20: dup
    //   21: aload_0
    //   22: invokespecial 71	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   25: astore 4
    //   27: aload 4
    //   29: astore_0
    //   30: aload 4
    //   32: aload_1
    //   33: invokestatic 75	com/ted/android/contacts/common/util/FileUtil:copyToFile	(Ljava/io/InputStream;Ljava/io/File;)Z
    //   36: istore_2
    //   37: iload_2
    //   38: istore_3
    //   39: iload_3
    //   40: istore_2
    //   41: aload 4
    //   43: ifnull +10 -> 53
    //   46: aload 4
    //   48: invokevirtual 57	java/io/InputStream:close	()V
    //   51: iload_3
    //   52: istore_2
    //   53: iload_2
    //   54: ireturn
    //   55: astore 5
    //   57: aconst_null
    //   58: astore_1
    //   59: aload_1
    //   60: astore_0
    //   61: aload 5
    //   63: invokevirtual 78	java/lang/Exception:printStackTrace	()V
    //   66: iload_3
    //   67: istore_2
    //   68: aload_1
    //   69: ifnull -16 -> 53
    //   72: aload_1
    //   73: invokevirtual 57	java/io/InputStream:close	()V
    //   76: iconst_0
    //   77: ireturn
    //   78: astore_0
    //   79: iconst_0
    //   80: ireturn
    //   81: astore_1
    //   82: aconst_null
    //   83: astore_0
    //   84: aload_0
    //   85: ifnull +7 -> 92
    //   88: aload_0
    //   89: invokevirtual 57	java/io/InputStream:close	()V
    //   92: aload_1
    //   93: athrow
    //   94: astore_0
    //   95: goto -3 -> 92
    //   98: astore_0
    //   99: iload_3
    //   100: ireturn
    //   101: astore_1
    //   102: goto -18 -> 84
    //   105: astore 5
    //   107: aload 4
    //   109: astore_1
    //   110: goto -51 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	paramFile1	File
    //   0	113	1	paramFile2	File
    //   3	65	2	bool1	boolean
    //   1	99	3	bool2	boolean
    //   25	83	4	localFileInputStream	FileInputStream
    //   55	7	5	localException1	Exception
    //   105	1	5	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   17	27	55	java/lang/Exception
    //   72	76	78	java/lang/Exception
    //   17	27	81	finally
    //   88	92	94	java/lang/Exception
    //   46	51	98	java/lang/Exception
    //   30	37	101	finally
    //   61	66	101	finally
    //   30	37	105	java/lang/Exception
  }
  
  public static boolean copyFolder(String paramString1, String paramString2)
  {
    try
    {
      File localFile = new File(paramString2);
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      String[] arrayOfString = new File(paramString1).list();
      int j;
      int i;
      if (arrayOfString != null)
      {
        j = arrayOfString.length;
        i = 0;
      }
      for (;;)
      {
        String str = arrayOfString[i];
        FileInputStream localFileInputStream;
        FileOutputStream localFileOutputStream;
        byte[] arrayOfByte;
        if (paramString1.endsWith(File.separator))
        {
          localFile = new File(paramString1 + str);
          if (localFile.isFile())
          {
            localFileInputStream = new FileInputStream(localFile);
            localFileOutputStream = new FileOutputStream(paramString2 + "/" + localFile.getName().toString());
            arrayOfByte = new byte['᐀'];
          }
        }
        for (;;)
        {
          int k = localFileInputStream.read(arrayOfByte);
          if (k == -1)
          {
            localFileOutputStream.flush();
            localFileOutputStream.close();
            localFileInputStream.close();
            if (!localFile.isDirectory()) {
              break label310;
            }
            copyFolder(paramString1 + "/" + str, paramString2 + "/" + str);
            break label310;
            localFile = new File(paramString1 + File.separator + str);
            break;
          }
          localFileOutputStream.write(arrayOfByte, 0, k);
        }
        while (i >= j)
        {
          return true;
          label310:
          i += 1;
        }
      }
      return false;
    }
    catch (Exception paramString1) {}
  }
  
  public static void copyStream(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    byte[] arrayOfByte = new byte['က'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i <= 0)
      {
        paramOutputStream.flush();
        return;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  /* Error */
  public static boolean copyToFile(InputStream paramInputStream, File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aload_1
    //   4: invokevirtual 150	java/io/File:getParentFile	()Ljava/io/File;
    //   7: invokevirtual 68	java/io/File:exists	()Z
    //   10: ifne +73 -> 83
    //   13: aload_1
    //   14: invokevirtual 150	java/io/File:getParentFile	()Ljava/io/File;
    //   17: invokevirtual 86	java/io/File:mkdirs	()Z
    //   20: pop
    //   21: new 35	java/io/FileOutputStream
    //   24: dup
    //   25: aload_1
    //   26: invokespecial 38	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   29: astore 5
    //   31: sipush 4096
    //   34: newarray <illegal type>
    //   36: astore 6
    //   38: aload_0
    //   39: aload 6
    //   41: invokevirtual 144	java/io/InputStream:read	([B)I
    //   44: istore_2
    //   45: iload_2
    //   46: ifge +73 -> 119
    //   49: iconst_1
    //   50: istore_3
    //   51: iconst_1
    //   52: istore 4
    //   54: aload 5
    //   56: ifnull +16 -> 72
    //   59: aload 5
    //   61: invokevirtual 145	java/io/OutputStream:flush	()V
    //   64: iload 4
    //   66: istore_3
    //   67: aload 5
    //   69: invokevirtual 60	java/io/OutputStream:close	()V
    //   72: iload_3
    //   73: ifne +8 -> 81
    //   76: aload_1
    //   77: invokevirtual 153	java/io/File:delete	()Z
    //   80: pop
    //   81: iload_3
    //   82: ireturn
    //   83: aload_1
    //   84: invokevirtual 68	java/io/File:exists	()Z
    //   87: ifeq -66 -> 21
    //   90: aload_1
    //   91: invokevirtual 153	java/io/File:delete	()Z
    //   94: pop
    //   95: goto -74 -> 21
    //   98: astore_0
    //   99: aload 6
    //   101: astore_0
    //   102: aload_0
    //   103: ifnull +91 -> 194
    //   106: aload_0
    //   107: invokevirtual 145	java/io/OutputStream:flush	()V
    //   110: aload_0
    //   111: invokevirtual 60	java/io/OutputStream:close	()V
    //   114: iconst_0
    //   115: istore_3
    //   116: goto -44 -> 72
    //   119: aload 5
    //   121: aload 6
    //   123: iconst_0
    //   124: iload_2
    //   125: invokevirtual 146	java/io/OutputStream:write	([BII)V
    //   128: goto -90 -> 38
    //   131: astore_0
    //   132: aload 5
    //   134: astore_0
    //   135: goto -33 -> 102
    //   138: astore_0
    //   139: iconst_0
    //   140: istore_3
    //   141: goto -69 -> 72
    //   144: astore_0
    //   145: aconst_null
    //   146: astore 5
    //   148: aload 5
    //   150: ifnull +13 -> 163
    //   153: aload 5
    //   155: invokevirtual 145	java/io/OutputStream:flush	()V
    //   158: aload 5
    //   160: invokevirtual 60	java/io/OutputStream:close	()V
    //   163: aload_0
    //   164: athrow
    //   165: astore_0
    //   166: iconst_0
    //   167: istore_3
    //   168: goto -101 -> 67
    //   171: astore_0
    //   172: iconst_0
    //   173: istore_3
    //   174: goto -102 -> 72
    //   177: astore 5
    //   179: goto -69 -> 110
    //   182: astore_1
    //   183: goto -25 -> 158
    //   186: astore_1
    //   187: goto -24 -> 163
    //   190: astore_0
    //   191: goto -43 -> 148
    //   194: iconst_0
    //   195: istore_3
    //   196: goto -124 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	199	0	paramInputStream	InputStream
    //   0	199	1	paramFile	File
    //   44	81	2	i	int
    //   50	146	3	bool1	boolean
    //   52	13	4	bool2	boolean
    //   29	130	5	localFileOutputStream	FileOutputStream
    //   177	1	5	localException	Exception
    //   1	121	6	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   3	21	98	java/lang/Exception
    //   21	31	98	java/lang/Exception
    //   83	95	98	java/lang/Exception
    //   31	38	131	java/lang/Exception
    //   38	45	131	java/lang/Exception
    //   119	128	131	java/lang/Exception
    //   110	114	138	java/lang/Exception
    //   3	21	144	finally
    //   21	31	144	finally
    //   83	95	144	finally
    //   59	64	165	java/lang/Exception
    //   67	72	171	java/lang/Exception
    //   106	110	177	java/lang/Exception
    //   153	158	182	java/lang/Exception
    //   158	163	186	java/lang/Exception
    //   31	38	190	finally
    //   38	45	190	finally
    //   119	128	190	finally
  }
  
  public static void deleteDir(String paramString)
  {
    paramString = new File(paramString);
    File[] arrayOfFile;
    if ((paramString.exists()) && (paramString.isDirectory()))
    {
      arrayOfFile = paramString.listFiles();
      if (arrayOfFile != null)
      {
        if (arrayOfFile.length != 0) {
          break label43;
        }
        paramString.delete();
      }
    }
    return;
    label43:
    int j = arrayOfFile.length;
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        paramString.delete();
        return;
      }
      File localFile = arrayOfFile[i];
      if (localFile != null)
      {
        if (localFile.isDirectory()) {
          deleteDir(localFile.getAbsolutePath());
        }
        localFile.delete();
      }
      i += 1;
    }
  }
  
  public static void deleteFile(File paramFile)
  {
    File[] arrayOfFile;
    int j;
    int i;
    if ((paramFile != null) && (paramFile.exists())) {
      if (paramFile.isDirectory())
      {
        arrayOfFile = paramFile.listFiles();
        if (arrayOfFile != null)
        {
          j = arrayOfFile.length;
          i = 0;
        }
      }
    }
    for (;;)
    {
      if (i >= j)
      {
        paramFile.delete();
        return;
      }
      deleteFile(arrayOfFile[i]);
      i += 1;
    }
  }
  
  public static void deleteFile(String paramString)
  {
    paramString = new File(paramString);
    File[] arrayOfFile;
    int j;
    int i;
    if (paramString.exists()) {
      if (paramString.isDirectory())
      {
        arrayOfFile = paramString.listFiles();
        if (arrayOfFile != null)
        {
          j = arrayOfFile.length;
          i = 0;
        }
      }
    }
    for (;;)
    {
      if (i >= j)
      {
        paramString.delete();
        return;
      }
      File localFile = arrayOfFile[i];
      if (localFile != null) {
        deleteFile(localFile.getAbsolutePath());
      }
      i += 1;
    }
  }
  
  public static boolean ensurePathExists(File paramFile)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramFile != null)
    {
      bool1 = bool2;
      if (!paramFile.exists()) {
        bool1 = paramFile.mkdirs();
      }
    }
    return bool1;
  }
  
  public static boolean ensurePathExists(String paramString)
  {
    return ensurePathExists(new File(paramString));
  }
  
  public static String pathAppend(String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramString1);
    if (!paramString1.endsWith(File.separator)) {
      localStringBuffer.append(File.separator);
    }
    localStringBuffer.append(paramString2);
    return localStringBuffer.toString();
  }
  
  /* Error */
  public static byte[] readFileByte(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_1
    //   4: new 70	java/io/FileInputStream
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 71	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   12: astore_2
    //   13: aload_2
    //   14: invokevirtual 187	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   17: astore_0
    //   18: aload_3
    //   19: astore_1
    //   20: aload_0
    //   21: invokevirtual 193	java/nio/channels/FileChannel:size	()J
    //   24: l2i
    //   25: newarray <illegal type>
    //   27: astore_3
    //   28: aload_3
    //   29: astore_1
    //   30: aload_0
    //   31: aload_3
    //   32: invokestatic 199	java/nio/ByteBuffer:wrap	([B)Ljava/nio/ByteBuffer;
    //   35: invokevirtual 202	java/nio/channels/FileChannel:read	(Ljava/nio/ByteBuffer;)I
    //   38: pop
    //   39: aload_0
    //   40: ifnull +7 -> 47
    //   43: aload_0
    //   44: invokevirtual 203	java/nio/channels/FileChannel:close	()V
    //   47: aload_3
    //   48: astore_0
    //   49: aload_2
    //   50: ifnull +9 -> 59
    //   53: aload_2
    //   54: invokevirtual 134	java/io/FileInputStream:close	()V
    //   57: aload_3
    //   58: astore_0
    //   59: aload_0
    //   60: areturn
    //   61: astore_0
    //   62: aconst_null
    //   63: astore_0
    //   64: aconst_null
    //   65: astore_2
    //   66: aload_0
    //   67: ifnull +7 -> 74
    //   70: aload_0
    //   71: invokevirtual 203	java/nio/channels/FileChannel:close	()V
    //   74: aload_1
    //   75: astore_0
    //   76: aload_2
    //   77: ifnull -18 -> 59
    //   80: aload_2
    //   81: invokevirtual 134	java/io/FileInputStream:close	()V
    //   84: aload_1
    //   85: areturn
    //   86: astore_0
    //   87: aload_1
    //   88: areturn
    //   89: astore_1
    //   90: aconst_null
    //   91: astore_2
    //   92: aconst_null
    //   93: astore_0
    //   94: aload_0
    //   95: ifnull +7 -> 102
    //   98: aload_0
    //   99: invokevirtual 203	java/nio/channels/FileChannel:close	()V
    //   102: aload_2
    //   103: ifnull +7 -> 110
    //   106: aload_2
    //   107: invokevirtual 134	java/io/FileInputStream:close	()V
    //   110: aload_1
    //   111: athrow
    //   112: astore_0
    //   113: goto -39 -> 74
    //   116: astore_0
    //   117: goto -15 -> 102
    //   120: astore_0
    //   121: goto -11 -> 110
    //   124: astore_0
    //   125: goto -78 -> 47
    //   128: astore_0
    //   129: aload_3
    //   130: areturn
    //   131: astore_1
    //   132: aconst_null
    //   133: astore_0
    //   134: goto -40 -> 94
    //   137: astore_1
    //   138: goto -44 -> 94
    //   141: astore_0
    //   142: aconst_null
    //   143: astore_0
    //   144: goto -78 -> 66
    //   147: astore_3
    //   148: goto -82 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	paramFile	File
    //   3	85	1	arrayOfByte1	byte[]
    //   89	22	1	localObject1	Object
    //   131	1	1	localObject2	Object
    //   137	1	1	localObject3	Object
    //   12	95	2	localFileInputStream	FileInputStream
    //   1	129	3	arrayOfByte2	byte[]
    //   147	1	3	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   4	13	61	java/lang/Exception
    //   80	84	86	java/io/IOException
    //   4	13	89	finally
    //   70	74	112	java/io/IOException
    //   98	102	116	java/io/IOException
    //   106	110	120	java/io/IOException
    //   43	47	124	java/io/IOException
    //   53	57	128	java/io/IOException
    //   13	18	131	finally
    //   20	28	137	finally
    //   30	39	137	finally
    //   13	18	141	java/lang/Exception
    //   20	28	147	java/lang/Exception
    //   30	39	147	java/lang/Exception
  }
  
  public static boolean safeRenameTo(File paramFile1, File paramFile2)
  {
    ensurePathExists(paramFile2.getParentFile());
    boolean bool2 = paramFile1.renameTo(paramFile2);
    boolean bool1 = bool2;
    if (!bool2)
    {
      bool2 = copyFile(paramFile1, paramFile2);
      bool1 = bool2;
      if ((bool2) && (!paramFile1.isDirectory())) {
        break label50;
      }
    }
    label50:
    try
    {
      deleteDir(paramFile1.getAbsolutePath());
      bool1 = bool2;
      return bool1;
    }
    catch (Exception paramFile1) {}
    paramFile1.delete();
    return bool2;
    return bool2;
  }
  
  /* Error */
  public static boolean writeByteFile(byte[] paramArrayOfByte, File paramFile)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iload_3
    //   3: istore_2
    //   4: aload_0
    //   5: ifnull +38 -> 43
    //   8: aconst_null
    //   9: astore 4
    //   11: new 35	java/io/FileOutputStream
    //   14: dup
    //   15: aload_1
    //   16: invokespecial 38	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   19: astore_1
    //   20: aload_1
    //   21: aload_0
    //   22: invokevirtual 214	java/io/FileOutputStream:write	([B)V
    //   25: iconst_1
    //   26: istore_3
    //   27: iload_3
    //   28: istore_2
    //   29: aload_1
    //   30: ifnull +13 -> 43
    //   33: aload_1
    //   34: invokevirtual 132	java/io/FileOutputStream:flush	()V
    //   37: aload_1
    //   38: invokevirtual 133	java/io/FileOutputStream:close	()V
    //   41: iload_3
    //   42: istore_2
    //   43: iload_2
    //   44: ireturn
    //   45: astore_0
    //   46: aconst_null
    //   47: astore_1
    //   48: iload_3
    //   49: istore_2
    //   50: aload_1
    //   51: ifnull -8 -> 43
    //   54: aload_1
    //   55: invokevirtual 132	java/io/FileOutputStream:flush	()V
    //   58: aload_1
    //   59: invokevirtual 133	java/io/FileOutputStream:close	()V
    //   62: iconst_0
    //   63: ireturn
    //   64: astore_0
    //   65: iconst_0
    //   66: ireturn
    //   67: astore_0
    //   68: aload 4
    //   70: astore_1
    //   71: aload_1
    //   72: ifnull +11 -> 83
    //   75: aload_1
    //   76: invokevirtual 132	java/io/FileOutputStream:flush	()V
    //   79: aload_1
    //   80: invokevirtual 133	java/io/FileOutputStream:close	()V
    //   83: aload_0
    //   84: athrow
    //   85: astore_0
    //   86: goto -28 -> 58
    //   89: astore 4
    //   91: goto -12 -> 79
    //   94: astore_1
    //   95: goto -12 -> 83
    //   98: astore_0
    //   99: goto -62 -> 37
    //   102: astore_0
    //   103: iconst_1
    //   104: ireturn
    //   105: astore_0
    //   106: goto -35 -> 71
    //   109: astore_0
    //   110: goto -62 -> 48
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	paramArrayOfByte	byte[]
    //   0	113	1	paramFile	File
    //   3	47	2	bool1	boolean
    //   1	48	3	bool2	boolean
    //   9	60	4	localObject	Object
    //   89	1	4	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   11	20	45	java/lang/Exception
    //   58	62	64	java/io/IOException
    //   11	20	67	finally
    //   54	58	85	java/lang/Exception
    //   75	79	89	java/lang/Exception
    //   79	83	94	java/io/IOException
    //   33	37	98	java/lang/Exception
    //   37	41	102	java/io/IOException
    //   20	25	105	finally
    //   20	25	109	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.common.util.FileUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */