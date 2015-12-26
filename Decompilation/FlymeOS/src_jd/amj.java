import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

public class amj
  implements amm
{
  private Context a;
  private int b;
  private String c;
  private long d;
  private String e;
  private int f;
  private boolean g = true;
  private boolean h = false;
  
  public amj(Context paramContext, int paramInt1, String paramString1, long paramLong, String paramString2, int paramInt2)
  {
    a = paramContext;
    b = paramInt1;
    c = paramString1;
    d = paramLong;
    e = paramString2;
    f = paramInt2;
    b("Checker limit:" + toString());
  }
  
  private boolean a(int paramInt)
  {
    return (b & paramInt) > 0;
  }
  
  private void b(String paramString)
  {
    anf.d(paramString);
  }
  
  public aml a(long paramLong1, long paramLong2)
  {
    int i = 1;
    if (g)
    {
      boolean bool;
      if (paramLong2 <= 0L)
      {
        bool = true;
        h = bool;
        if ((d <= 0L) || (h) || (!a(1))) {
          break label129;
        }
        if (paramLong1 + paramLong2 != d) {
          break label123;
        }
      }
      for (;;)
      {
        if (i != 0) {
          break label129;
        }
        String str = "File length not match(" + d + "->" + (paramLong1 + paramLong2) + ")";
        b(str);
        return aml.a(str);
        bool = false;
        break;
        label123:
        i = 0;
      }
    }
    label129:
    return aml.a();
  }
  
  public aml a(String paramString)
  {
    int k = 1;
    Object localObject;
    if (g)
    {
      if ((TextUtils.isEmpty(c)) || (!a(8))) {
        break label543;
      }
      localObject = anl.d(a, paramString);
      if (localObject != null)
      {
        if (!c.equalsIgnoreCase(packageName))
        {
          paramString = "Package name not match(" + c + "->" + packageName + ")";
          b(paramString);
          return aml.a(paramString);
        }
        if ((f > 0) && (a(16)))
        {
          if (f == versionCode) {}
          for (i = 1; i == 0; i = 0)
          {
            paramString = "Package version code not match(" + f + "->" + versionCode + ")";
            b(paramString);
            return aml.a(paramString);
          }
        }
      }
    }
    label451:
    label534:
    label538:
    label543:
    for (int i = 1;; i = 0)
    {
      int j = i;
      if (!TextUtils.isEmpty(e))
      {
        if (!a(2)) {
          break label451;
        }
        localObject = anh.a(paramString);
        if (!e.equalsIgnoreCase((String)localObject))
        {
          paramString = "Whole md5 not match(" + e + "->" + (String)localObject + ")";
          b(paramString);
          return aml.a(paramString);
          paramString = "File cant parse to package info(" + c + "->" + f + ")";
          b(paramString);
          return aml.a(paramString);
        }
        j = 1;
      }
      for (;;)
      {
        if ((j == 0) && (d > 0L) && (h) && (a(1)))
        {
          h = false;
          long l = anl.a(paramString);
          if (l > 0L)
          {
            if (l == d) {}
            for (i = k;; i = 0)
            {
              if (i != 0) {
                break label534;
              }
              paramString = "Download File length not match(" + d + "->" + l + ")";
              b(paramString);
              return aml.a(paramString);
              j = i;
              if (!a(4)) {
                break;
              }
              localObject = anh.a(paramString, 1048576);
              if (e.equalsIgnoreCase((String)localObject)) {
                break label538;
              }
              paramString = "HeadTail md5 not match(" + e + "->" + (String)localObject + ")";
              b(paramString);
              return aml.a(paramString);
            }
          }
        }
        return aml.a();
        j = 1;
      }
    }
  }
  
  public String a()
  {
    if ((!TextUtils.isEmpty(e)) && (a(2))) {
      return e;
    }
    return null;
  }
  
  public void a(boolean paramBoolean)
  {
    g = paramBoolean;
  }
  
  public String b()
  {
    if ((!TextUtils.isEmpty(e)) && (a(4))) {
      return e;
    }
    return null;
  }
  
  public long c()
  {
    long l2 = 0L;
    long l1 = l2;
    if (d > 0L)
    {
      l1 = l2;
      if (a(1)) {
        l1 = d;
      }
    }
    return l1;
  }
  
  public String d()
  {
    if (f > 0) {
      return String.valueOf(f);
    }
    return null;
  }
  
  public String toString()
  {
    Object localObject2 = "";
    if (a(1)) {
      localObject2 = "" + "size ";
    }
    Object localObject1 = localObject2;
    if (a(4)) {
      localObject1 = (String)localObject2 + "1mmd5 ";
    }
    localObject2 = localObject1;
    if (a(8)) {
      localObject2 = (String)localObject1 + "pkg ";
    }
    localObject1 = localObject2;
    if (a(16)) {
      localObject1 = (String)localObject2 + "vcode ";
    }
    localObject2 = localObject1;
    if (a(2)) {
      localObject2 = (String)localObject1 + "md5 ";
    }
    localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = "null";
    }
    return "verify_mode=" + (String)localObject1 + ",pk=" + c + ",size=" + d + ",md5=" + e + ",v_code=" + f;
  }
}

/* Location:
 * Qualified Name:     amj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */