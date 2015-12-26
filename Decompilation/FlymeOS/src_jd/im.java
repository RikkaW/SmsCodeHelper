public class im
  implements aum
{
  short a;
  boolean b;
  double c;
  
  im(String paramString, int paramInt)
  {
    if ((paramString.equals("indefinite")) && ((paramInt & 0x1) != 0))
    {
      a = 0;
      return;
    }
    if ((paramInt & 0x2) != 0) {
      if (paramString.startsWith("+"))
      {
        paramString = paramString.substring(1);
        paramInt = 1;
      }
    }
    for (;;)
    {
      c = (paramInt * a(paramString) / 1000.0D);
      b = true;
      a = 1;
      return;
      if (paramString.startsWith("-"))
      {
        paramString = paramString.substring(1);
        paramInt = -1;
        continue;
        throw new IllegalArgumentException("Unsupported time value");
      }
      else
      {
        paramInt = 1;
      }
    }
  }
  
  public static float a(String paramString)
  {
    int i = 0;
    try
    {
      paramString = paramString.trim();
      if (paramString.endsWith("ms")) {
        return a(paramString, 2, true);
      }
      if (paramString.endsWith("s")) {
        return a(paramString, 1, true) * 1000.0F;
      }
      if (paramString.endsWith("min")) {
        return a(paramString, 3, true) * 60000.0F;
      }
      float f1;
      if (paramString.endsWith("h"))
      {
        f1 = a(paramString, 1, true);
        return 3600000.0F * f1;
      }
      try
      {
        f1 = a(paramString, 0, true);
        return f1 * 1000.0F;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        paramString = paramString.split(":");
        if (paramString.length == 2) {
          f1 = 0.0F;
        }
        for (;;)
        {
          int j = (int)a(paramString[i], 0, false);
          if ((j < 0) || (j > 59)) {
            break label220;
          }
          float f2 = j * 60000;
          float f3 = a(paramString[(i + 1)], 0, true);
          if ((f3 < 0.0F) || (f3 >= 60.0F)) {
            break label228;
          }
          return f3 * 60000.0F + (f1 + f2);
          if (paramString.length != 3) {
            break;
          }
          f1 = 3600000 * (int)a(paramString[0], 0, false);
          i = 1;
        }
        throw new IllegalArgumentException();
      }
      throw new IllegalArgumentException();
    }
    catch (NumberFormatException paramString)
    {
      throw new IllegalArgumentException();
    }
    label220:
    label228:
    throw new IllegalArgumentException();
  }
  
  private static float a(String paramString, int paramInt, boolean paramBoolean)
  {
    paramString = paramString.substring(0, paramString.length() - paramInt);
    paramInt = paramString.indexOf('.');
    if (paramInt != -1)
    {
      if (!paramBoolean) {
        throw new IllegalArgumentException("int value contains decimal");
      }
      paramString = paramString + "000";
      float f = Float.parseFloat(paramString.substring(0, paramInt));
      return Float.parseFloat(paramString.substring(paramInt + 1, paramInt + 4)) / 1000.0F + f;
    }
    return Integer.parseInt(paramString);
  }
  
  public double a()
  {
    return 0.0D;
  }
  
  public boolean b()
  {
    return b;
  }
  
  public double c()
  {
    return c;
  }
  
  public short d()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     im
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */