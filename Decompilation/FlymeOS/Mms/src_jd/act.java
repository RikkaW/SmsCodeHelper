import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

public class act
{
  private static act a;
  private Object b;
  private Object c;
  
  private act()
  {
    try
    {
      b = aau.a("libcore.icu.Transliterator", String.class, "Han-Latin/Names; Latin-Ascii; Any-Upper");
      c = aau.a("libcore.icu.Transliterator", String.class, "Latin-Ascii");
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w("HanziToPinyin", "Han-Latin/Names transliterator data is missing, HanziToPinyin is disabled");
    }
  }
  
  private void a(char paramChar, act.a parama)
  {
    b = Character.toString(paramChar);
    if (paramChar < '')
    {
      a = 1;
      c = b;
    }
    do
    {
      return;
      if ((paramChar < 'ɐ') || (('Ḁ' <= paramChar) && (paramChar < 'ỿ')))
      {
        a = 1;
        if (c == null) {}
        for (String str = b;; str = (String)aau.c(c, "transliterate", String.class, b))
        {
          c = str;
          return;
        }
      }
      a = 2;
      c = ((String)aau.c(b, "transliterate", String.class, b));
    } while ((!TextUtils.isEmpty(c)) && (!TextUtils.equals(b, c)));
    a = 3;
    c = b;
  }
  
  private void a(StringBuilder paramStringBuilder, ArrayList<act.a> paramArrayList, int paramInt)
  {
    String str = paramStringBuilder.toString();
    paramArrayList.add(new act.a(paramInt, str, str));
    paramStringBuilder.setLength(0);
  }
  
  public static act b()
  {
    try
    {
      if (a == null) {
        a = new act();
      }
      act localact = a;
      return localact;
    }
    finally {}
  }
  
  public ArrayList<act.a> a(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if ((!a()) || (TextUtils.isEmpty(paramString))) {
      return localArrayList;
    }
    int m = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject1 = new act.a();
    int j = 1;
    int i = 0;
    while (i < m)
    {
      char c1 = paramString.charAt(i);
      Object localObject2;
      int k;
      if (Character.isSpaceChar(c1))
      {
        localObject2 = localObject1;
        k = j;
        if (localStringBuilder.length() > 0)
        {
          a(localStringBuilder, localArrayList, j);
          k = j;
          localObject2 = localObject1;
        }
        i += 1;
        localObject1 = localObject2;
        j = k;
      }
      else
      {
        a(c1, (act.a)localObject1);
        if (a == 2)
        {
          if (localStringBuilder.length() > 0) {
            a(localStringBuilder, localArrayList, j);
          }
          localArrayList.add(localObject1);
          localObject1 = new act.a();
        }
        for (;;)
        {
          k = a;
          localObject2 = localObject1;
          break;
          if ((j != a) && (localStringBuilder.length() > 0)) {
            a(localStringBuilder, localArrayList, j);
          }
          localStringBuilder.append(c);
        }
      }
    }
    if (localStringBuilder.length() > 0) {
      a(localStringBuilder, localArrayList, j);
    }
    return localArrayList;
  }
  
  public boolean a()
  {
    return b != null;
  }
  
  public static class a
  {
    public int a;
    public String b;
    public String c;
    
    public a() {}
    
    public a(int paramInt, String paramString1, String paramString2)
    {
      a = paramInt;
      b = paramString1;
      c = paramString2;
    }
  }
}

/* Location:
 * Qualified Name:     act
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */