package com.android.mms.service;

import android.net.Uri;
import java.util.concurrent.Callable;

class MmsService$2
  implements Callable<byte[]>
{
  MmsService$2(MmsService paramMmsService, Uri paramUri, int paramInt) {}
  
  /* Error */
  public byte[] call()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: new 42	android/os/ParcelFileDescriptor$AutoCloseInputStream
    //   8: dup
    //   9: aload_0
    //   10: getfield 22	com/android/mms/service/MmsService$2:this$0	Lcom/android/mms/service/MmsService;
    //   13: invokevirtual 46	com/android/mms/service/MmsService:getContentResolver	()Landroid/content/ContentResolver;
    //   16: aload_0
    //   17: getfield 24	com/android/mms/service/MmsService$2:val$contentUri	Landroid/net/Uri;
    //   20: ldc 48
    //   22: invokevirtual 54	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;
    //   25: invokespecial 57	android/os/ParcelFileDescriptor$AutoCloseInputStream:<init>	(Landroid/os/ParcelFileDescriptor;)V
    //   28: astore_3
    //   29: aload_0
    //   30: getfield 26	com/android/mms/service/MmsService$2:val$maxSize	I
    //   33: iconst_1
    //   34: iadd
    //   35: newarray <illegal type>
    //   37: astore_2
    //   38: aload_3
    //   39: aload_2
    //   40: iconst_0
    //   41: aload_0
    //   42: getfield 26	com/android/mms/service/MmsService$2:val$maxSize	I
    //   45: iconst_1
    //   46: iadd
    //   47: invokevirtual 61	android/os/ParcelFileDescriptor$AutoCloseInputStream:read	([BII)I
    //   50: istore_1
    //   51: iload_1
    //   52: ifne +21 -> 73
    //   55: ldc 63
    //   57: ldc 65
    //   59: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   62: pop
    //   63: aload_3
    //   64: ifnull +7 -> 71
    //   67: aload_3
    //   68: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
    //   71: aconst_null
    //   72: areturn
    //   73: iload_1
    //   74: aload_0
    //   75: getfield 26	com/android/mms/service/MmsService$2:val$maxSize	I
    //   78: if_icmpgt +19 -> 97
    //   81: aload_2
    //   82: iload_1
    //   83: invokestatic 80	java/util/Arrays:copyOf	([BI)[B
    //   86: astore_2
    //   87: aload_3
    //   88: ifnull +7 -> 95
    //   91: aload_3
    //   92: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
    //   95: aload_2
    //   96: areturn
    //   97: ldc 63
    //   99: ldc 82
    //   101: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   104: pop
    //   105: aload_3
    //   106: ifnull +7 -> 113
    //   109: aload_3
    //   110: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
    //   113: aconst_null
    //   114: areturn
    //   115: astore_2
    //   116: aload 4
    //   118: astore_3
    //   119: aload_2
    //   120: astore 4
    //   122: aload_3
    //   123: astore_2
    //   124: ldc 63
    //   126: ldc 84
    //   128: aload 4
    //   130: invokestatic 87	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   133: pop
    //   134: aload_3
    //   135: ifnull -64 -> 71
    //   138: aload_3
    //   139: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
    //   142: aconst_null
    //   143: areturn
    //   144: astore_2
    //   145: aconst_null
    //   146: areturn
    //   147: astore_3
    //   148: aload_2
    //   149: ifnull +7 -> 156
    //   152: aload_2
    //   153: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
    //   156: aload_3
    //   157: athrow
    //   158: astore_2
    //   159: goto -88 -> 71
    //   162: astore_3
    //   163: goto -68 -> 95
    //   166: astore_2
    //   167: goto -54 -> 113
    //   170: astore_2
    //   171: goto -15 -> 156
    //   174: astore 4
    //   176: aload_3
    //   177: astore_2
    //   178: aload 4
    //   180: astore_3
    //   181: goto -33 -> 148
    //   184: astore 4
    //   186: goto -64 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	this	2
    //   50	33	1	i	int
    //   1	95	2	arrayOfByte	byte[]
    //   115	5	2	localIOException1	java.io.IOException
    //   123	1	2	localObject1	Object
    //   144	9	2	localIOException2	java.io.IOException
    //   158	1	2	localIOException3	java.io.IOException
    //   166	1	2	localIOException4	java.io.IOException
    //   170	1	2	localIOException5	java.io.IOException
    //   177	1	2	localObject2	Object
    //   28	111	3	localObject3	Object
    //   147	10	3	localObject4	Object
    //   162	15	3	localIOException6	java.io.IOException
    //   180	1	3	localObject5	Object
    //   3	126	4	localIOException7	java.io.IOException
    //   174	5	4	localObject6	Object
    //   184	1	4	localIOException8	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   5	29	115	java/io/IOException
    //   138	142	144	java/io/IOException
    //   5	29	147	finally
    //   124	134	147	finally
    //   67	71	158	java/io/IOException
    //   91	95	162	java/io/IOException
    //   109	113	166	java/io/IOException
    //   152	156	170	java/io/IOException
    //   29	51	174	finally
    //   55	63	174	finally
    //   73	87	174	finally
    //   97	105	174	finally
    //   29	51	184	java/io/IOException
    //   55	63	184	java/io/IOException
    //   73	87	184	java/io/IOException
    //   97	105	184	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsService.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */