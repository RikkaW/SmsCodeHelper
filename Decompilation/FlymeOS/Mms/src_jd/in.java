import java.util.ArrayList;

public class in
  implements aun
{
  private final ArrayList<aum> a;
  
  in(ArrayList<aum> paramArrayList)
  {
    a = paramArrayList;
  }
  
  public int a()
  {
    return a.size();
  }
  
  public aum a(int paramInt)
  {
    try
    {
      aum localaum = (aum)a.get(paramInt);
      return localaum;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
    return null;
  }
}

/* Location:
 * Qualified Name:     in
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */