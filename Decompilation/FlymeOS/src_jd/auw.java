import android.content.Context;
import android.os.FileUtils;
import android.text.TextUtils;
import com.ted.android.contacts.common.util.FileUtil;
import com.ted.android.contacts.common.util.NovoFileUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class auw
{
  private auy a;
  private bd b = null;
  
  public auw(auy paramauy)
  {
    a = paramauy;
  }
  
  private String a(be parambe)
  {
    return a.e().concat("/" + parambe.f());
  }
  
  /* Error */
  public static String a(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 55	java/io/BufferedReader
    //   3: dup
    //   4: new 57	java/io/InputStreamReader
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 60	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: invokespecial 63	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   15: astore_1
    //   16: new 27	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 64	java/lang/StringBuilder:<init>	()V
    //   23: astore_0
    //   24: aload_1
    //   25: invokevirtual 67	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_2
    //   29: aload_2
    //   30: ifnonnull +12 -> 42
    //   33: aload_1
    //   34: invokevirtual 70	java/io/BufferedReader:close	()V
    //   37: aload_0
    //   38: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: areturn
    //   42: aload_0
    //   43: aload_2
    //   44: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc 72
    //   49: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: goto -29 -> 24
    //   56: astore_2
    //   57: aload_1
    //   58: invokevirtual 70	java/io/BufferedReader:close	()V
    //   61: goto -24 -> 37
    //   64: astore_1
    //   65: goto -28 -> 37
    //   68: astore_0
    //   69: aload_1
    //   70: invokevirtual 70	java/io/BufferedReader:close	()V
    //   73: aload_0
    //   74: athrow
    //   75: astore_1
    //   76: goto -3 -> 73
    //   79: astore_1
    //   80: goto -43 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	paramInputStream	java.io.InputStream
    //   15	43	1	localBufferedReader	java.io.BufferedReader
    //   64	6	1	localIOException1	IOException
    //   75	1	1	localIOException2	IOException
    //   79	1	1	localIOException3	IOException
    //   28	16	2	str	String
    //   56	1	2	localIOException4	IOException
    // Exception table:
    //   from	to	target	type
    //   24	29	56	java/io/IOException
    //   42	53	56	java/io/IOException
    //   57	61	64	java/io/IOException
    //   24	29	68	finally
    //   42	53	68	finally
    //   69	73	75	java/io/IOException
    //   33	37	79	java/io/IOException
  }
  
  /* Error */
  public static String a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 76	java/io/FileInputStream
    //   5: dup
    //   6: new 78	java/io/File
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 79	java/io/File:<init>	(Ljava/lang/String;)V
    //   14: invokespecial 82	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   17: astore_0
    //   18: aload_0
    //   19: invokestatic 84	auw:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   22: astore_1
    //   23: aload_1
    //   24: astore_2
    //   25: aload_0
    //   26: ifnull +9 -> 35
    //   29: aload_0
    //   30: invokevirtual 85	java/io/FileInputStream:close	()V
    //   33: aload_1
    //   34: astore_2
    //   35: aload_2
    //   36: areturn
    //   37: astore_0
    //   38: aconst_null
    //   39: astore_0
    //   40: aload_0
    //   41: ifnull -6 -> 35
    //   44: aload_0
    //   45: invokevirtual 85	java/io/FileInputStream:close	()V
    //   48: aconst_null
    //   49: areturn
    //   50: astore_0
    //   51: aconst_null
    //   52: areturn
    //   53: astore_1
    //   54: aconst_null
    //   55: astore_0
    //   56: aload_0
    //   57: ifnull +7 -> 64
    //   60: aload_0
    //   61: invokevirtual 85	java/io/FileInputStream:close	()V
    //   64: aload_1
    //   65: athrow
    //   66: astore_0
    //   67: goto -3 -> 64
    //   70: astore_0
    //   71: aload_1
    //   72: areturn
    //   73: astore_1
    //   74: goto -18 -> 56
    //   77: astore_1
    //   78: goto -38 -> 40
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	81	0	paramString	String
    //   22	12	1	str1	String
    //   53	19	1	str2	String
    //   73	1	1	localObject1	Object
    //   77	1	1	localFileNotFoundException	java.io.FileNotFoundException
    //   1	35	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	18	37	java/io/FileNotFoundException
    //   44	48	50	java/io/IOException
    //   2	18	53	finally
    //   60	64	66	java/io/IOException
    //   29	33	70	java/io/IOException
    //   18	23	73	finally
    //   18	23	77	java/io/FileNotFoundException
  }
  
  private void a()
  {
    Object localObject;
    if (b())
    {
      if (anw.b(a.d()))
      {
        localObject = new File(a.l());
        if (!((File)localObject).exists()) {}
      }
      try
      {
        bd localbd = bd.a(FileUtils.readTextFile((File)localObject, 0, null));
        if (localbd != null) {
          a(localbd);
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          if (aux.a) {
            localIOException.printStackTrace();
          }
        }
      }
      ((File)localObject).delete();
    }
    for (;;)
    {
      return;
      localObject = a.j() + "/" + "u.zip";
      String str = a.e() + "/" + "u.zip";
      if (!bl.a(a.d(), (String)localObject, str)) {
        continue;
      }
      bj.a(new File(str), new File(a.e()), true);
      try
      {
        new File(str).delete();
        localObject = c();
        if (localObject == null) {
          continue;
        }
        a((bd)localObject);
        FileUtil.safeRenameTo(new File(a.i()), new File(a.f()));
        FileUtil.safeRenameTo(new File(a.h()), new File(a.g()));
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public static void a(auy paramauy)
  {
    if (paramauy.a())
    {
      paramauy.b();
      paramauy.c();
      new auw(paramauy).a();
    }
  }
  
  private void a(bd parambd)
  {
    int i;
    if ((parambd.c() != null) && (parambd.c().size() > 0))
    {
      localObject1 = parambd.c().iterator();
      i = 0;
      if (!((Iterator)localObject1).hasNext()) {
        label42:
        if (i != 0) {
          break label148;
        }
      }
    }
    Object localObject3;
    label148:
    do
    {
      return;
      localObject3 = (be)((Iterator)localObject1).next();
      localObject2 = a((be)localObject3);
      boolean bool = bl.a(a.d(), ((be)localObject3).a(), (String)localObject2);
      i = bool;
      if (!bool) {
        break label42;
      }
      localObject3 = b(((be)localObject3).b());
      FileUtil.safeRenameTo(new File((String)localObject2), new File((String)localObject3));
      NovoFileUtil.writeTimestamp2File(a.d(), (String)localObject3, System.currentTimeMillis() / 1000L);
      i = bool;
      break;
      if ((parambd.c() != null) && (parambd.c().size() > 0)) {
        a.a(parambd.c());
      }
      if ((parambd.b() != null) && (parambd.b().size() > 0)) {
        a.a((be)parambd.b().get(0));
      }
      if ((parambd.d() != null) && (parambd.d().size() > 0))
      {
        localObject1 = parambd.d().iterator();
        if (((Iterator)localObject1).hasNext()) {
          break label398;
        }
      }
    } while ((parambd.a() == null) || (parambd.a().size() <= 0));
    Object localObject2 = anw.a(a.d());
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = "U" + UUID.randomUUID().toString().replaceAll("-", "").replace(":", "").toLowerCase();
    }
    localObject1 = a.k().concat("/" + (String)localObject1);
    parambd = parambd.a().iterator();
    for (;;)
    {
      if (!parambd.hasNext())
      {
        FileUtil.deleteFile((String)localObject1);
        return;
        label398:
        localObject2 = b(((be)((Iterator)localObject1).next()).b());
        try
        {
          localObject2 = new File((String)localObject2);
          if (!((File)localObject2).exists()) {
            break;
          }
          ((File)localObject2).delete();
        }
        catch (Exception localException1) {}
        break;
      }
      be localbe = (be)parambd.next();
      try
      {
        localObject3 = b(localbe.b());
        anz.a(new File((String)localObject1), new File((String)localObject3), localbe.f());
      }
      catch (Exception localException2) {}
    }
  }
  
  private boolean b()
  {
    boolean bool = false;
    String str1 = a.j() + "/" + "u.md5";
    String str2 = a.i();
    if (bl.a(a.d(), str1, str2)) {
      bool = a(str2).equals(a(a.f()));
    }
    return bool;
  }
  
  private bd c()
  {
    Object localObject6 = null;
    Object localObject4 = a(a.h());
    Object localObject1 = a.g();
    for (;;)
    {
      try
      {
        localObject1 = FileUtils.readTextFile(new File((String)localObject1), 0, null);
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          break label496;
        }
        localObject2 = bd.a((String)localObject1);
        if (!TextUtils.isEmpty((CharSequence)localObject4))
        {
          localObject1 = bd.a((String)localObject4);
          if ((b == null) && (localObject1 != null))
          {
            b = new bd();
            b.b(new ArrayList());
            b.a(new ArrayList());
            b.c(new ArrayList());
            b.d(new ArrayList());
          }
          if (localObject1 == null)
          {
            localObject4 = null;
            if (localObject2 != null) {
              continue;
            }
            localObject5 = null;
            if (b != null) {
              continue;
            }
            localList = null;
            a((List)localObject4, (List)localObject5, localList);
            if (localObject1 != null) {
              continue;
            }
            localObject4 = null;
            if (localObject2 != null) {
              continue;
            }
            localObject5 = null;
            if (b != null) {
              continue;
            }
            localList = null;
            a((List)localObject4, (List)localObject5, localList);
            if (localObject1 != null) {
              continue;
            }
            localObject4 = null;
            if (localObject2 != null) {
              continue;
            }
            localObject5 = null;
            if (b != null) {
              continue;
            }
            localList = null;
            a((List)localObject4, (List)localObject5, localList);
            if (localObject1 != null) {
              continue;
            }
            localObject4 = null;
            if (localObject2 != null) {
              continue;
            }
            localObject2 = null;
            if (b != null) {
              continue;
            }
            localObject5 = localObject6;
            a((List)localObject4, (List)localObject2, (List)localObject5);
            if (!anw.b(a.d())) {
              if ((b.b().size() <= 0) && (b.c().size() <= 0) && (b.a().size() <= 0)) {
                if (b.d().size() <= 0) {
                  continue;
                }
              }
            }
          }
        }
      }
      catch (IOException localIOException1)
      {
        try
        {
          FileUtils.stringToFile(a.l(), b.e());
          return (bd)localObject1;
          localIOException1 = localIOException1;
          localbd = null;
          continue;
          localObject4 = localbd.c();
          continue;
          Object localObject5 = ((bd)localObject2).c();
          continue;
          List localList = b.c();
          continue;
          localObject4 = localbd.b();
          continue;
          localObject5 = ((bd)localObject2).b();
          continue;
          localList = b.b();
          continue;
          localObject4 = localbd.d();
          continue;
          localObject5 = ((bd)localObject2).d();
          continue;
          localList = b.d();
          continue;
          localObject4 = localbd.a();
          continue;
          Object localObject2 = ((bd)localObject2).a();
          continue;
          localObject5 = b.a();
        }
        catch (IOException localIOException2)
        {
          if (!aux.a) {
            continue;
          }
          localIOException2.printStackTrace();
          return localbd;
        }
      }
      bd localbd = null;
      continue;
      label496:
      Object localObject3 = null;
    }
  }
  
  public void a(List<be> paramList1, List<be> paramList2, List<be> paramList3)
  {
    int i;
    if ((paramList1 != null) && (paramList2 != null))
    {
      i = 0;
      if (i < paramList1.size()) {}
    }
    else
    {
      if ((paramList1 != null) && (paramList1.size() > 0))
      {
        i = 0;
        if (i < paramList1.size()) {
          break label136;
        }
      }
      return;
    }
    Object localObject = (be)paramList1.get(i);
    Iterator localIterator = paramList2.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        i += 1;
        break;
      }
      be localbe = (be)localIterator.next();
      if (((be)localObject).c().equals(localbe.c()))
      {
        paramList1.remove(i);
        i -= 1;
      }
    }
    label136:
    paramList2 = (be)paramList1.get(i);
    localObject = b(paramList2.b());
    int j;
    if ((new File((String)localObject).exists()) && (anv.a((String)localObject).equalsIgnoreCase(paramList2.c())))
    {
      paramList1.remove(i);
      j = i - 1;
    }
    for (;;)
    {
      i = j + 1;
      break;
      j = i;
      if (!TextUtils.isEmpty(paramList2.d()))
      {
        localObject = f.a(paramList2.d());
        int k;
        if ((!(localObject instanceof Boolean)) || (((Boolean)localObject).booleanValue()))
        {
          k = i;
          if ((localObject instanceof Integer))
          {
            k = i;
            if (((Integer)localObject).intValue() > 0) {}
          }
        }
        else
        {
          paramList1.remove(i);
          k = i - 1;
        }
        j = k;
        if (paramList2.d().indexOf("$WIFI") >= 0)
        {
          j = k;
          if (!anw.b(a.d()))
          {
            j = k;
            if (paramList3 != null)
            {
              paramList3.add(paramList2);
              j = k;
            }
          }
        }
      }
    }
  }
  
  public String b(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (!paramString.startsWith("/")) {
        str = new File(a.d().getFilesDir(), paramString).getAbsolutePath();
      }
    }
    return str;
  }
}

/* Location:
 * Qualified Name:     auw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */