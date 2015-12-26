import java.util.ArrayList;
import java.util.List;

class ae$a
{
  String a;
  String b;
  List<ae.c> c;
  
  public ae$a(ae paramae, String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException();
    }
    a = paramString1;
    b = paramString2;
    c = new ArrayList();
  }
  
  public void a(String paramString)
  {
    c.add(new ae.c(d, paramString));
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (this == paramObject) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == null);
      bool1 = bool2;
    } while (getClass() != paramObject.getClass());
    paramObject = (a)paramObject;
    return a.equals(a);
  }
  
  public int hashCode()
  {
    return a.hashCode();
  }
}

/* Location:
 * Qualified Name:     ae.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */