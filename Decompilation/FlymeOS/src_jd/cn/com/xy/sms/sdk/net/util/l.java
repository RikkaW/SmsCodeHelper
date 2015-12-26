package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.util.Date;
import java.util.HashMap;

public final class l
{
  private static String a = "95ad98c4ba9a0ec12a7dca2af77f312bef6fd02580c23fc082b28f1cab03d9d5b7694bd5dd9693a8b6786c9480dfbcc462373bd1b9f5bed66151be80a370465d6516f89e66d6d70ba52a3d063acbe4544a585d62896d953b3269efd345ff888e5ed7f7f7b60c862ca5a27f20ccdba704113a9861fcd91cf3f0fd7115987568d04f444224b3c2436b833ed0439b4fa8c92e938827f360b6a4a070fed4608a46c8a52023fabfd2561bcd4205052254caaffe9a55aa73254537a1a2c0efbcd76254bef3e01902ffee20b0a45b6c8e6beb496c9c3494d263dedf0fff4702ebbfee0cb568da4940b8f5f8c89aa96b2c21e2ff9596e30e26b18e1b563353843ee95787";
  private static HashMap<String, Boolean> b = new HashMap();
  private static HashMap<String, Integer> c = new HashMap();
  
  public static Boolean a(String paramString)
  {
    if (!d.a(paramString)) {
      localObject = Boolean.valueOf(false);
    }
    Boolean localBoolean;
    do
    {
      return (Boolean)localObject;
      localBoolean = (Boolean)b.get(paramString);
      localObject = localBoolean;
    } while (localBoolean != null);
    Constant.getContext();
    Object localObject = b(paramString);
    if ((!StringUtils.isNull((String)localObject)) && (((String)localObject).indexOf("95ad98c4ba9a0ec12a7dca2af77f312bef6fd02580c23fc082b28f1cab03d9d5b7694bd5dd9693a8b6786c9480dfbcc462373bd1b9f5bed66151be80a370465d6516f89e66d6d70ba52a3d063acbe4544a585d62896d953b3269efd345ff888e5ed7f7f7b60c862ca5a27f20ccdba704113a9861fcd91cf3f0fd7115987568d04f444224b3c2436b833ed0439b4fa8c92e938827f360b6a4a070fed4608a46c8a52023fabfd2561bcd4205052254caaffe9a55aa73254537a1a2c0efbcd76254bef3e01902ffee20b0a45b6c8e6beb496c9c3494d263dedf0fff4702ebbfee0cb568da4940b8f5f8c89aa96b2c21e2ff9596e30e26b18e1b563353843ee95787") != -1)) {}
    for (localObject = Boolean.valueOf(true);; localObject = Boolean.valueOf(false))
    {
      b.put(paramString, localObject);
      return (Boolean)localObject;
    }
  }
  
  /* Error */
  public static String a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 73	java/io/BufferedReader
    //   5: dup
    //   6: new 75	java/io/FileReader
    //   9: dup
    //   10: new 77	java/io/File
    //   13: dup
    //   14: new 79	java/lang/StringBuilder
    //   17: dup
    //   18: aload_0
    //   19: invokestatic 82	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   22: invokespecial 85	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   25: aload_1
    //   26: invokevirtual 89	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   32: invokespecial 94	java/io/File:<init>	(Ljava/lang/String;)V
    //   35: invokespecial 97	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   38: invokespecial 100	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   41: astore_0
    //   42: new 102	java/lang/StringBuffer
    //   45: dup
    //   46: invokespecial 103	java/lang/StringBuffer:<init>	()V
    //   49: astore_1
    //   50: aload_0
    //   51: invokevirtual 106	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   54: astore_2
    //   55: aload_2
    //   56: ifnonnull +17 -> 73
    //   59: aload_1
    //   60: invokevirtual 107	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   63: invokevirtual 110	java/lang/String:trim	()Ljava/lang/String;
    //   66: astore_1
    //   67: aload_0
    //   68: invokevirtual 113	java/io/BufferedReader:close	()V
    //   71: aload_1
    //   72: areturn
    //   73: aload_1
    //   74: aload_2
    //   75: invokevirtual 116	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   78: pop
    //   79: goto -29 -> 50
    //   82: astore_1
    //   83: aload_0
    //   84: ifnull +7 -> 91
    //   87: aload_0
    //   88: invokevirtual 113	java/io/BufferedReader:close	()V
    //   91: ldc 118
    //   93: areturn
    //   94: astore_0
    //   95: aload_0
    //   96: invokevirtual 121	java/io/IOException:printStackTrace	()V
    //   99: aload_1
    //   100: areturn
    //   101: astore_0
    //   102: aload_0
    //   103: invokevirtual 121	java/io/IOException:printStackTrace	()V
    //   106: goto -15 -> 91
    //   109: astore_1
    //   110: aconst_null
    //   111: astore_0
    //   112: aload_0
    //   113: ifnull +7 -> 120
    //   116: aload_0
    //   117: invokevirtual 113	java/io/BufferedReader:close	()V
    //   120: aload_1
    //   121: athrow
    //   122: astore_0
    //   123: aload_0
    //   124: invokevirtual 121	java/io/IOException:printStackTrace	()V
    //   127: goto -7 -> 120
    //   130: astore_1
    //   131: goto -19 -> 112
    //   134: astore_0
    //   135: aload_2
    //   136: astore_0
    //   137: goto -54 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	paramString1	String
    //   0	140	1	paramString2	String
    //   1	135	2	str	String
    // Exception table:
    //   from	to	target	type
    //   42	50	82	java/lang/Throwable
    //   50	55	82	java/lang/Throwable
    //   59	67	82	java/lang/Throwable
    //   73	79	82	java/lang/Throwable
    //   67	71	94	java/io/IOException
    //   87	91	101	java/io/IOException
    //   2	42	109	finally
    //   116	120	122	java/io/IOException
    //   42	50	130	finally
    //   50	55	130	finally
    //   59	67	130	finally
    //   73	79	130	finally
    //   2	42	134	java/lang/Throwable
  }
  
  public static boolean a()
  {
    KeyManager.initAppKey();
    return true;
  }
  
  public static boolean a(byte paramByte)
  {
    boolean bool2 = false;
    boolean bool1;
    if ("XwIDAQABYUN".equals(KeyManager.channel)) {
      if (paramByte != 2)
      {
        bool1 = bool2;
        if (paramByte != 4) {}
      }
      else
      {
        bool1 = true;
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              do
                              {
                                do
                                {
                                  do
                                  {
                                    do
                                    {
                                      do
                                      {
                                        do
                                        {
                                          do
                                          {
                                            do
                                            {
                                              do
                                              {
                                                do
                                                {
                                                  do
                                                  {
                                                    do
                                                    {
                                                      do
                                                      {
                                                        do
                                                        {
                                                          do
                                                          {
                                                            do
                                                            {
                                                              do
                                                              {
                                                                do
                                                                {
                                                                  do
                                                                  {
                                                                    return bool1;
                                                                    if (!"NQIDAQABCOOL".equals(KeyManager.channel)) {
                                                                      break;
                                                                    }
                                                                    bool1 = bool2;
                                                                  } while (paramByte == 1);
                                                                  return true;
                                                                  if (!"6QIDAQABSTARRYSKY".equals(KeyManager.channel)) {
                                                                    break;
                                                                  }
                                                                  bool1 = bool2;
                                                                } while (paramByte == 1);
                                                                return true;
                                                                if (!"vwIDAQABLIANLUOOS".equals(KeyManager.channel)) {
                                                                  break;
                                                                }
                                                                bool1 = bool2;
                                                              } while (paramByte == 1);
                                                              return true;
                                                              if (!"FEhNrwHTXL".equals(KeyManager.channel)) {
                                                                break;
                                                              }
                                                              bool1 = bool2;
                                                            } while (paramByte != 1);
                                                            return true;
                                                            if (!"1i1BDH2wONE+".equals(KeyManager.channel)) {
                                                              break;
                                                            }
                                                            bool1 = bool2;
                                                          } while (paramByte == 1);
                                                          return true;
                                                          if (!"Oq1QGcwIYUNOS".equals(KeyManager.channel)) {
                                                            break;
                                                          }
                                                          bool1 = bool2;
                                                        } while (paramByte == 1);
                                                        bool1 = bool2;
                                                      } while (paramByte == 2);
                                                      return true;
                                                      if (!"j3FIT5mwLETV".equals(KeyManager.channel)) {
                                                        break;
                                                      }
                                                      bool1 = bool2;
                                                    } while (paramByte == 1);
                                                    return true;
                                                    if (!"D6mKXM8MEIZU".equals(KeyManager.channel)) {
                                                      break;
                                                    }
                                                    bool1 = bool2;
                                                  } while (paramByte == 1);
                                                  return true;
                                                  if (!"3GdfMSKwHUAWEI".equals(KeyManager.channel)) {
                                                    break;
                                                  }
                                                  bool1 = bool2;
                                                } while (paramByte == 1);
                                                return true;
                                                if (!"0GCSqGSITOS".equals(KeyManager.channel)) {
                                                  break;
                                                }
                                                bool1 = bool2;
                                              } while (paramByte == 1);
                                              return true;
                                              if (!"wupzCqnwGUAIWU".equals(KeyManager.channel)) {
                                                break;
                                              }
                                              bool1 = bool2;
                                            } while (paramByte == 1);
                                            return true;
                                            if (!"XRyvMvZwSMARTISAN".equals(KeyManager.channel)) {
                                              break;
                                            }
                                            bool1 = bool2;
                                          } while (paramByte == 1);
                                          return true;
                                          if (!"dToXA5JQDAKELE".equals(KeyManager.channel)) {
                                            break;
                                          }
                                          bool1 = bool2;
                                        } while (paramByte == 1);
                                        return true;
                                        if (!"p5O4wKmwGIONEE".equals(KeyManager.channel)) {
                                          break;
                                        }
                                        bool1 = bool2;
                                      } while (paramByte == 1);
                                      return true;
                                      if (!"z5N7W51wKINGSUN".equals(KeyManager.channel)) {
                                        break;
                                      }
                                      bool1 = bool2;
                                    } while (paramByte == 1);
                                    return true;
                                    if (!"Cko59T6wSUGAR".equals(KeyManager.channel)) {
                                      break;
                                    }
                                    bool1 = bool2;
                                  } while (paramByte == 1);
                                  return true;
                                  if (!"oWIH+3ZQLEIDIANOS".equals(KeyManager.channel)) {
                                    break;
                                  }
                                  bool1 = bool2;
                                } while (paramByte == 1);
                                return true;
                                if (!"al30zFgQTEST_T".equals(KeyManager.channel)) {
                                  break;
                                }
                                bool1 = bool2;
                              } while (paramByte == 1);
                              return true;
                              if (!"gsjHPHwIKOOBEE".equals(KeyManager.channel)) {
                                break;
                              }
                              bool1 = bool2;
                            } while (paramByte == 1);
                            return true;
                            if (!"AjAFrJSQWENTAI".equals(KeyManager.channel)) {
                              break;
                            }
                            bool1 = bool2;
                          } while (paramByte == 1);
                          return true;
                          if (!"JqyMtaHQNUBIA".equals(KeyManager.channel)) {
                            break;
                          }
                          bool1 = bool2;
                        } while (paramByte == 1);
                        return true;
                        if (!"15Du354QGIONEECARD".equals(KeyManager.channel)) {
                          break;
                        }
                        bool1 = bool2;
                      } while (paramByte == 1);
                      return true;
                      if (!"rahtBH7wTCL".equals(KeyManager.channel)) {
                        break;
                      }
                      bool1 = bool2;
                    } while (paramByte == 1);
                    return true;
                    if (!"xU6UT6pwTOS2".equals(KeyManager.channel)) {
                      break;
                    }
                    bool1 = bool2;
                  } while (paramByte == 1);
                  return true;
                  if (!"5Gx84kmwYULONG_COOLPAD".equals(KeyManager.channel)) {
                    break;
                  }
                  bool1 = bool2;
                } while (paramByte == 1);
                return true;
                if (!"tnjdWFeQKTOUCH".equals(KeyManager.channel)) {
                  break;
                }
                bool1 = bool2;
              } while (paramByte == 1);
              return true;
              if (!"Uj2pznXQHCT".equals(KeyManager.channel)) {
                break;
              }
              bool1 = bool2;
            } while (paramByte == 1);
            return true;
            if (!"XkXZJmwIPPTV".equals(KeyManager.channel)) {
              break;
            }
            bool1 = bool2;
          } while (paramByte == 1);
          return true;
          if (!"PzqP0ONQTOSWATCH".equals(KeyManager.channel)) {
            break;
          }
          bool1 = bool2;
        } while (paramByte == 1);
        return true;
        if (!"VCTyBOSwSmartisan".equals(KeyManager.channel)) {
          break;
        }
        bool1 = bool2;
      } while (paramByte == 1);
      return true;
      if (!"5rLWVKgQMEITU_PHONE".equals(KeyManager.channel)) {
        break;
      }
      bool1 = bool2;
    } while (paramByte == 1);
    return true;
    return true;
  }
  
  /* Error */
  private static java.security.cert.Certificate[] a(java.util.jar.JarFile paramJarFile, java.util.jar.JarEntry paramJarEntry)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: sipush 1024
    //   8: newarray <illegal type>
    //   10: astore 5
    //   12: aload_0
    //   13: aload_1
    //   14: invokevirtual 206	java/util/jar/JarFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   17: astore_0
    //   18: aload_0
    //   19: astore_2
    //   20: aload_0
    //   21: aload 5
    //   23: iconst_0
    //   24: sipush 1024
    //   27: invokevirtual 212	java/io/InputStream:read	([BII)I
    //   30: iconst_m1
    //   31: if_icmpne -13 -> 18
    //   34: aload 4
    //   36: astore_2
    //   37: aload_1
    //   38: ifnull +12 -> 50
    //   41: aload_0
    //   42: astore_2
    //   43: aload_1
    //   44: invokevirtual 218	java/util/jar/JarEntry:getCertificates	()[Ljava/security/cert/Certificate;
    //   47: astore_1
    //   48: aload_1
    //   49: astore_2
    //   50: aload_2
    //   51: astore_1
    //   52: aload_0
    //   53: ifnull +9 -> 62
    //   56: aload_0
    //   57: invokevirtual 219	java/io/InputStream:close	()V
    //   60: aload_2
    //   61: astore_1
    //   62: aload_1
    //   63: areturn
    //   64: astore_1
    //   65: aconst_null
    //   66: astore_0
    //   67: aload_0
    //   68: astore_2
    //   69: aload_1
    //   70: invokevirtual 121	java/io/IOException:printStackTrace	()V
    //   73: aload_3
    //   74: astore_1
    //   75: aload_0
    //   76: ifnull -14 -> 62
    //   79: aload_0
    //   80: invokevirtual 219	java/io/InputStream:close	()V
    //   83: aconst_null
    //   84: areturn
    //   85: astore_0
    //   86: aconst_null
    //   87: areturn
    //   88: astore_1
    //   89: aconst_null
    //   90: astore_0
    //   91: aload_0
    //   92: astore_2
    //   93: aload_1
    //   94: invokevirtual 220	java/lang/Throwable:printStackTrace	()V
    //   97: aload_3
    //   98: astore_1
    //   99: aload_0
    //   100: ifnull -38 -> 62
    //   103: aload_0
    //   104: invokevirtual 219	java/io/InputStream:close	()V
    //   107: aconst_null
    //   108: areturn
    //   109: astore_0
    //   110: aconst_null
    //   111: areturn
    //   112: astore_0
    //   113: aconst_null
    //   114: astore_2
    //   115: aload_2
    //   116: ifnull +7 -> 123
    //   119: aload_2
    //   120: invokevirtual 219	java/io/InputStream:close	()V
    //   123: aload_0
    //   124: athrow
    //   125: astore_0
    //   126: aload_2
    //   127: areturn
    //   128: astore_1
    //   129: goto -6 -> 123
    //   132: astore_0
    //   133: goto -18 -> 115
    //   136: astore_1
    //   137: goto -46 -> 91
    //   140: astore_1
    //   141: goto -74 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	144	0	paramJarFile	java.util.jar.JarFile
    //   0	144	1	paramJarEntry	java.util.jar.JarEntry
    //   19	108	2	localObject1	Object
    //   1	97	3	localObject2	Object
    //   3	32	4	localObject3	Object
    //   10	12	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   5	18	64	java/io/IOException
    //   79	83	85	java/lang/Throwable
    //   5	18	88	java/lang/Throwable
    //   103	107	109	java/lang/Throwable
    //   5	18	112	finally
    //   56	60	125	java/lang/Throwable
    //   119	123	128	java/lang/Throwable
    //   20	34	132	finally
    //   43	48	132	finally
    //   69	73	132	finally
    //   93	97	132	finally
    //   20	34	136	java/lang/Throwable
    //   43	48	136	java/lang/Throwable
    //   20	34	140	java/io/IOException
    //   43	48	140	java/io/IOException
  }
  
  /* Error */
  private static String b(String paramString)
  {
    // Byte code:
    //   0: new 202	java/util/jar/JarFile
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 221	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   8: astore_1
    //   9: aload_1
    //   10: astore_0
    //   11: aload_1
    //   12: aload_1
    //   13: ldc -33
    //   15: invokevirtual 227	java/util/jar/JarFile:getJarEntry	(Ljava/lang/String;)Ljava/util/jar/JarEntry;
    //   18: invokestatic 229	cn/com/xy/sms/sdk/net/util/l:a	(Ljava/util/jar/JarFile;Ljava/util/jar/JarEntry;)[Ljava/security/cert/Certificate;
    //   21: astore_2
    //   22: aload_2
    //   23: ifnull +35 -> 58
    //   26: aload_1
    //   27: astore_0
    //   28: aload_2
    //   29: arraylength
    //   30: ifle +28 -> 58
    //   33: aload_1
    //   34: astore_0
    //   35: aload_2
    //   36: arraylength
    //   37: ifle +21 -> 58
    //   40: aload_1
    //   41: astore_0
    //   42: aload_2
    //   43: iconst_0
    //   44: aaload
    //   45: invokevirtual 235	java/security/cert/Certificate:getPublicKey	()Ljava/security/PublicKey;
    //   48: invokevirtual 236	java/lang/Object:toString	()Ljava/lang/String;
    //   51: astore_2
    //   52: aload_1
    //   53: invokevirtual 237	java/util/jar/JarFile:close	()V
    //   56: aload_2
    //   57: areturn
    //   58: aload_1
    //   59: astore_0
    //   60: aload_1
    //   61: invokevirtual 237	java/util/jar/JarFile:close	()V
    //   64: aload_1
    //   65: invokevirtual 237	java/util/jar/JarFile:close	()V
    //   68: ldc 118
    //   70: areturn
    //   71: astore_2
    //   72: aconst_null
    //   73: astore_1
    //   74: aload_1
    //   75: astore_0
    //   76: aload_2
    //   77: invokevirtual 220	java/lang/Throwable:printStackTrace	()V
    //   80: aload_1
    //   81: ifnull -13 -> 68
    //   84: aload_1
    //   85: invokevirtual 237	java/util/jar/JarFile:close	()V
    //   88: goto -20 -> 68
    //   91: astore_0
    //   92: goto -24 -> 68
    //   95: astore_1
    //   96: aconst_null
    //   97: astore_0
    //   98: aload_0
    //   99: ifnull +7 -> 106
    //   102: aload_0
    //   103: invokevirtual 237	java/util/jar/JarFile:close	()V
    //   106: aload_1
    //   107: athrow
    //   108: astore_0
    //   109: aload_2
    //   110: areturn
    //   111: astore_0
    //   112: goto -6 -> 106
    //   115: astore_0
    //   116: goto -48 -> 68
    //   119: astore_1
    //   120: goto -22 -> 98
    //   123: astore_2
    //   124: goto -50 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	127	0	paramString	String
    //   8	77	1	localJarFile	java.util.jar.JarFile
    //   95	12	1	localObject1	Object
    //   119	1	1	localObject2	Object
    //   21	36	2	localObject3	Object
    //   71	39	2	localThrowable1	Throwable
    //   123	1	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	9	71	java/lang/Throwable
    //   84	88	91	java/lang/Throwable
    //   0	9	95	finally
    //   52	56	108	java/lang/Throwable
    //   102	106	111	java/lang/Throwable
    //   64	68	115	java/lang/Throwable
    //   11	22	119	finally
    //   28	33	119	finally
    //   35	40	119	finally
    //   42	52	119	finally
    //   60	64	119	finally
    //   76	80	119	finally
    //   11	22	123	java/lang/Throwable
    //   28	33	123	java/lang/Throwable
    //   35	40	123	java/lang/Throwable
    //   42	52	123	java/lang/Throwable
    //   60	64	123	java/lang/Throwable
  }
  
  private static boolean b(byte paramByte)
  {
    if (paramByte == 1) {
      try
      {
        if (!b.a(Constant.getContext())) {
          return false;
        }
        paramByte = new Date().getHours();
        Integer localInteger = (Integer)c.get(paramByte);
        if (localInteger == null) {
          c.clear();
        }
        int i;
        for (localInteger = Integer.valueOf(1);; localInteger = Integer.valueOf(i + 1))
        {
          c.put(paramByte, localInteger);
          if (localInteger.intValue() <= 300) {
            break;
          }
          return false;
          i = localInteger.intValue();
        }
        return true;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return false;
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */