import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ad
{
  public static m a(String paramString, int paramInt, l[] paramArrayOfl)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("函数名为空");
    }
    try
    {
      Method localMethod = ae.a(paramString);
      Class[] arrayOfClass = localMethod.getParameterTypes();
      if (paramArrayOfl.length == arrayOfClass.length)
      {
        i = paramArrayOfl.length - 1;
        if (i < 0)
        {
          paramArrayOfl = localMethod.getReturnType();
          if ((Boolean.TYPE != paramArrayOfl) && (Boolean.class != paramArrayOfl)) {
            break label264;
          }
          return new m(l.a.c, Boolean.FALSE);
        }
        Class localClass = paramArrayOfl[i].n();
        if ((localClass != null) && (!a(arrayOfClass[(arrayOfClass.length - i - 1)], localClass))) {
          throw new i("函数\"" + paramString + "\"参数类型不匹配,函数参数定义类型为：" + arrayOfClass[i].getName() + " 传入参数实际类型为：" + localClass.getName(), paramString, paramInt);
        }
      }
    }
    catch (SecurityException paramArrayOfl)
    {
      for (;;)
      {
        int i;
        throw new i("函数\"" + paramString + "\"不存在或参数类型不匹配", paramString, paramInt);
        i -= 1;
      }
      throw new i("函数\"" + paramString + "\"参数个数不匹配", paramString, paramInt);
    }
    catch (NoSuchMethodException paramArrayOfl)
    {
      throw new i("函数\"" + paramString + "\"不存在或参数类型不匹配", paramString, paramInt);
    }
    label264:
    if (Date.class == paramArrayOfl) {
      return new m(l.a.h, null);
    }
    if ((Double.TYPE == paramArrayOfl) || (Double.class == paramArrayOfl)) {
      return new m(l.a.g, Double.valueOf(0.0D));
    }
    if ((Float.TYPE == paramArrayOfl) || (Float.class == paramArrayOfl)) {
      return new m(l.a.f, Float.valueOf(0.0F));
    }
    if ((Integer.TYPE == paramArrayOfl) || (Integer.class == paramArrayOfl)) {
      return new m(l.a.d, Integer.valueOf(0));
    }
    if ((Long.TYPE == paramArrayOfl) || (Long.class == paramArrayOfl)) {
      return new m(l.a.e, Long.valueOf(0L));
    }
    if (String.class == paramArrayOfl) {
      return new m(l.a.b, null);
    }
    if (List.class == paramArrayOfl) {
      return new m(l.a.i, null);
    }
    if (Object.class == paramArrayOfl) {
      return new m(l.a.j, null);
    }
    if ((Void.TYPE == paramArrayOfl) || (Void.class == paramArrayOfl)) {
      return new m(l.a.j, null);
    }
    throw new IllegalStateException("解析器内部错误：不支持的函数返回类型");
  }
  
  /* Error */
  public static m a(String paramString, int paramInt, m[] paramArrayOfm)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 12	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 14
    //   10: invokespecial 18	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_2
    //   15: ifnonnull +13 -> 28
    //   18: new 12	java/lang/IllegalArgumentException
    //   21: dup
    //   22: ldc -95
    //   24: invokespecial 18	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   27: athrow
    //   28: iconst_0
    //   29: istore_3
    //   30: iload_3
    //   31: aload_2
    //   32: arraylength
    //   33: if_icmplt +37 -> 70
    //   36: aload_0
    //   37: iload_1
    //   38: aload_2
    //   39: invokestatic 164	ad:b	(Ljava/lang/String;I[Lm;)[Ljava/lang/Object;
    //   42: astore_2
    //   43: aload_0
    //   44: aload_2
    //   45: invokestatic 167	ae:a	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    //   48: astore_2
    //   49: aload_2
    //   50: instanceof 35
    //   53: ifeq +78 -> 131
    //   56: new 41	m
    //   59: dup
    //   60: getstatic 47	l$a:c	Ll$a;
    //   63: aload_2
    //   64: invokespecial 54	m:<init>	(Ll$a;Ljava/lang/Object;)V
    //   67: astore_2
    //   68: aload_2
    //   69: areturn
    //   70: aload_2
    //   71: iload_3
    //   72: aaload
    //   73: invokevirtual 171	m:p	()Z
    //   76: ifeq +18 -> 94
    //   79: aload_2
    //   80: iload_3
    //   81: aload_2
    //   82: iload_3
    //   83: aaload
    //   84: invokevirtual 174	m:b	()Ljava/lang/Object;
    //   87: checkcast 175	n
    //   90: invokevirtual 178	n:b	()Lm;
    //   93: aastore
    //   94: iload_3
    //   95: iconst_1
    //   96: iadd
    //   97: istore_3
    //   98: goto -68 -> 30
    //   101: astore_2
    //   102: new 12	java/lang/IllegalArgumentException
    //   105: dup
    //   106: new 66	java/lang/StringBuilder
    //   109: dup
    //   110: ldc 68
    //   112: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   115: aload_0
    //   116: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: ldc -76
    //   121: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: invokespecial 18	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   130: athrow
    //   131: aload_2
    //   132: instanceof 95
    //   135: ifeq +51 -> 186
    //   138: new 41	m
    //   141: dup
    //   142: getstatic 98	l$a:h	Ll$a;
    //   145: aload_2
    //   146: invokespecial 54	m:<init>	(Ll$a;Ljava/lang/Object;)V
    //   149: astore_2
    //   150: aload_2
    //   151: areturn
    //   152: astore_2
    //   153: aload_2
    //   154: invokevirtual 184	java/lang/NoSuchMethodException:printStackTrace	()V
    //   157: new 152	java/lang/IllegalStateException
    //   160: dup
    //   161: new 66	java/lang/StringBuilder
    //   164: dup
    //   165: ldc 68
    //   167: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   170: aload_0
    //   171: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: ldc 91
    //   176: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokespecial 155	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   185: athrow
    //   186: aload_2
    //   187: instanceof 100
    //   190: ifeq +51 -> 241
    //   193: new 41	m
    //   196: dup
    //   197: getstatic 104	l$a:g	Ll$a;
    //   200: aload_2
    //   201: invokespecial 54	m:<init>	(Ll$a;Ljava/lang/Object;)V
    //   204: astore_2
    //   205: aload_2
    //   206: areturn
    //   207: astore_2
    //   208: aload_2
    //   209: invokevirtual 185	java/lang/IllegalArgumentException:printStackTrace	()V
    //   212: new 152	java/lang/IllegalStateException
    //   215: dup
    //   216: new 66	java/lang/StringBuilder
    //   219: dup
    //   220: ldc 68
    //   222: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   225: aload_0
    //   226: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: ldc -69
    //   231: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   237: invokespecial 155	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   240: athrow
    //   241: aload_2
    //   242: instanceof 110
    //   245: ifeq +58 -> 303
    //   248: new 41	m
    //   251: dup
    //   252: getstatic 114	l$a:f	Ll$a;
    //   255: aload_2
    //   256: invokespecial 54	m:<init>	(Ll$a;Ljava/lang/Object;)V
    //   259: astore_2
    //   260: aload_2
    //   261: areturn
    //   262: astore_2
    //   263: aload_2
    //   264: invokevirtual 188	java/lang/Exception:printStackTrace	()V
    //   267: new 152	java/lang/IllegalStateException
    //   270: dup
    //   271: new 66	java/lang/StringBuilder
    //   274: dup
    //   275: ldc 68
    //   277: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   280: aload_0
    //   281: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: ldc -66
    //   286: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: aload_2
    //   290: invokevirtual 193	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   293: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   299: invokespecial 155	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   302: athrow
    //   303: aload_2
    //   304: instanceof 119
    //   307: ifeq +15 -> 322
    //   310: new 41	m
    //   313: dup
    //   314: getstatic 123	l$a:d	Ll$a;
    //   317: aload_2
    //   318: invokespecial 54	m:<init>	(Ll$a;Ljava/lang/Object;)V
    //   321: areturn
    //   322: aload_2
    //   323: instanceof 128
    //   326: ifeq +15 -> 341
    //   329: new 41	m
    //   332: dup
    //   333: getstatic 132	l$a:e	Ll$a;
    //   336: aload_2
    //   337: invokespecial 54	m:<init>	(Ll$a;Ljava/lang/Object;)V
    //   340: areturn
    //   341: aload_2
    //   342: instanceof 137
    //   345: ifeq +15 -> 360
    //   348: new 41	m
    //   351: dup
    //   352: getstatic 140	l$a:b	Ll$a;
    //   355: aload_2
    //   356: invokespecial 54	m:<init>	(Ll$a;Ljava/lang/Object;)V
    //   359: areturn
    //   360: aload_2
    //   361: instanceof 142
    //   364: ifeq +15 -> 379
    //   367: new 41	m
    //   370: dup
    //   371: getstatic 144	l$a:i	Ll$a;
    //   374: aload_2
    //   375: invokespecial 54	m:<init>	(Ll$a;Ljava/lang/Object;)V
    //   378: areturn
    //   379: new 41	m
    //   382: dup
    //   383: getstatic 147	l$a:j	Ll$a;
    //   386: aload_2
    //   387: invokespecial 54	m:<init>	(Ll$a;Ljava/lang/Object;)V
    //   390: astore_2
    //   391: aload_2
    //   392: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	393	0	paramString	String
    //   0	393	1	paramInt	int
    //   0	393	2	paramArrayOfm	m[]
    //   29	69	3	i	int
    // Exception table:
    //   from	to	target	type
    //   36	43	101	i
    //   43	68	152	java/lang/NoSuchMethodException
    //   131	150	152	java/lang/NoSuchMethodException
    //   186	205	152	java/lang/NoSuchMethodException
    //   241	260	152	java/lang/NoSuchMethodException
    //   303	322	152	java/lang/NoSuchMethodException
    //   322	341	152	java/lang/NoSuchMethodException
    //   341	360	152	java/lang/NoSuchMethodException
    //   360	379	152	java/lang/NoSuchMethodException
    //   379	391	152	java/lang/NoSuchMethodException
    //   43	68	207	java/lang/IllegalArgumentException
    //   131	150	207	java/lang/IllegalArgumentException
    //   186	205	207	java/lang/IllegalArgumentException
    //   241	260	207	java/lang/IllegalArgumentException
    //   303	322	207	java/lang/IllegalArgumentException
    //   322	341	207	java/lang/IllegalArgumentException
    //   341	360	207	java/lang/IllegalArgumentException
    //   360	379	207	java/lang/IllegalArgumentException
    //   379	391	207	java/lang/IllegalArgumentException
    //   43	68	262	java/lang/Exception
    //   131	150	262	java/lang/Exception
    //   186	205	262	java/lang/Exception
    //   241	260	262	java/lang/Exception
    //   303	322	262	java/lang/Exception
    //   322	341	262	java/lang/Exception
    //   341	360	262	java/lang/Exception
    //   360	379	262	java/lang/Exception
    //   379	391	262	java/lang/Exception
  }
  
  private static boolean a(Class<?> paramClass1, Class<?> paramClass2)
  {
    if (Object.class == paramClass1) {}
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
                    return true;
                  } while (paramClass1 == paramClass2);
                  if (Double.TYPE != paramClass1) {
                    break;
                  }
                } while ((Float.TYPE == paramClass2) || (Long.TYPE == paramClass2) || (Integer.TYPE == paramClass2));
                return false;
                if (Double.class != paramClass1) {
                  break;
                }
              } while (Double.TYPE == paramClass2);
              return false;
              if (Float.TYPE != paramClass1) {
                break;
              }
            } while ((Long.TYPE == paramClass2) || (Integer.TYPE == paramClass2));
            return false;
            if (Float.class != paramClass1) {
              break;
            }
          } while (Float.TYPE == paramClass2);
          return false;
          if (Long.TYPE != paramClass1) {
            break;
          }
        } while (Integer.TYPE == paramClass2);
        return false;
        if (Long.class != paramClass1) {
          break;
        }
      } while (Long.TYPE == paramClass2);
      return false;
      if (Integer.class != paramClass1) {
        break;
      }
    } while (Integer.TYPE == paramClass2);
    return false;
    return false;
  }
  
  private static Object[] b(String paramString, int paramInt, m[] paramArrayOfm)
  {
    Object localObject;
    if (paramArrayOfm == null)
    {
      localObject = new Object[0];
      return (Object[])localObject;
    }
    Object[] arrayOfObject = new Object[paramArrayOfm.length];
    paramInt = paramArrayOfm.length - 1;
    for (;;)
    {
      localObject = arrayOfObject;
      if (paramInt < 0) {
        break;
      }
      try
      {
        arrayOfObject[(paramArrayOfm.length - 1 - paramInt)] = paramArrayOfm[paramInt].o();
        paramInt -= 1;
      }
      catch (ParseException paramArrayOfm)
      {
        throw new i("函数\"" + paramString + "\"参数转化Java对象错误");
      }
    }
  }
}

/* Location:
 * Qualified Name:     ad
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */