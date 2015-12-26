import java.util.List;

public final class aic
{
  private boolean a = false;
  private String b = "";
  private boolean c = false;
  private double d = 0.0D;
  private double e = 0.0D;
  
  protected aic(List paramList, String paramString1, String paramString2, String paramString3)
  {
    b = paramString3;
    d();
  }
  
  private void d()
  {
    int k = 0;
    Object localObject = b;
    int j;
    if ((localObject != null) && (((String)localObject).length() > 8))
    {
      i = 1;
      j = 0;
      while (i < ((String)localObject).length() - 3)
      {
        j ^= ((String)localObject).charAt(i);
        i += 1;
      }
      if (!Integer.toHexString(j).equalsIgnoreCase(((String)localObject).substring(((String)localObject).length() - 2, ((String)localObject).length()))) {}
    }
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        localObject = b.substring(0, b.length() - 3);
        j = 0;
        i = k;
        while (i < ((String)localObject).length())
        {
          k = j;
          if (((String)localObject).charAt(i) == ',') {
            k = j + 1;
          }
          i += 1;
          j = k;
        }
        localObject = ((String)localObject).split(",", j + 1);
        if (localObject.length < 6) {
          return;
        }
        if ((!localObject[2].equals("")) && (!localObject[(localObject.length - 3)].equals("")) && (!localObject[(localObject.length - 2)].equals("")) && (!localObject[(localObject.length - 1)].equals("")))
        {
          Integer.valueOf(localObject[2]).intValue();
          d = Double.valueOf(localObject[(localObject.length - 3)]).doubleValue();
          e = Double.valueOf(localObject[(localObject.length - 2)]).doubleValue();
          c = true;
        }
      }
      a = c;
      return;
    }
  }
  
  protected final boolean a()
  {
    return a;
  }
  
  protected final double b()
  {
    return d;
  }
  
  protected final double c()
  {
    return e;
  }
}

/* Location:
 * Qualified Name:     aic
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */