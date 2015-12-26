import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class gq
  extends ArrayList<gm>
{
  private static final long serialVersionUID = 1L;
  
  public static gq a(Iterable<String> paramIterable, boolean paramBoolean)
  {
    gq localgq = new gq();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      String str = (String)paramIterable.next();
      if (!TextUtils.isEmpty(str))
      {
        gm localgm = gm.a(str, paramBoolean);
        localgm.a(str);
        localgq.add(localgm);
      }
    }
    return localgq;
  }
  
  public static gq a(String paramString, boolean paramBoolean)
  {
    gq localgq = new gq();
    long l = System.currentTimeMillis();
    Iterator localIterator1 = gm.a(paramBoolean).iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((List)localIterator1.next()).iterator();
      while (localIterator2.hasNext())
      {
        gm localgm = (gm)localIterator2.next();
        if (localgm.g().toLowerCase().contains(paramString)) {
          localgq.add(localgm);
        }
      }
    }
    wd.a("Meizu_mms_search", "getByName", l);
    return localgq;
  }
  
  public static gq a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    gq localgq = new gq();
    paramString = paramString.split(";");
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      CharSequence localCharSequence = paramString[i];
      if (!TextUtils.isEmpty(localCharSequence))
      {
        gm localgm = gm.a(localCharSequence, paramBoolean1);
        if (paramBoolean2) {
          localgm.a(localCharSequence);
        }
        localgq.add(localgm);
      }
      i += 1;
    }
    return localgq;
  }
  
  public static gq b(String paramString, boolean paramBoolean)
  {
    gq localgq = new gq();
    paramString = gx.a(paramString).iterator();
    while (paramString.hasNext())
    {
      gx.a locala = (gx.a)paramString.next();
      if ((locala != null) && (!TextUtils.isEmpty(b)))
      {
        gm localgm = gm.a(b, paramBoolean);
        localgm.a(a);
        localgq.add(localgm);
      }
    }
    return localgq;
  }
  
  public String a()
  {
    return TextUtils.join(";", c());
  }
  
  public String a(String paramString)
  {
    String[] arrayOfString = new String[size()];
    Iterator localIterator = iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      arrayOfString[i] = ((gm)localIterator.next()).g();
      i += 1;
    }
    return TextUtils.join(paramString, arrayOfString);
  }
  
  public String[] a(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      String str2 = ((gm)localIterator.next()).d();
      String str1 = str2;
      if (paramBoolean) {
        str1 = wd.c(str2);
      }
      if ((!TextUtils.isEmpty(str1)) && (!localArrayList.contains(str1))) {
        localArrayList.add(str1);
      }
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  public boolean b()
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext()) {
      if (((gm)localIterator.next()).l()) {
        return true;
      }
    }
    return false;
  }
  
  public String[] c()
  {
    return a(false);
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      paramObject = (gq)paramObject;
      if (size() != ((gq)paramObject).size()) {
        return false;
      }
      Iterator localIterator = iterator();
      while (localIterator.hasNext())
      {
        boolean bool = ((gq)paramObject).contains((gm)localIterator.next());
        if (!bool) {
          return false;
        }
      }
      return true;
    }
    catch (ClassCastException paramObject) {}
    return false;
  }
}

/* Location:
 * Qualified Name:     gq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */