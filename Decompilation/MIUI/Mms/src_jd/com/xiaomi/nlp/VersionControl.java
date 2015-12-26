package com.xiaomi.nlp;

public class VersionControl
{
  protected int platform = 7;
  protected int systemLevel = 1;
  protected String version;
  
  public boolean matchPlatform(int paramInt)
  {
    return (platform & paramInt) != 0;
  }
  
  public String matchResourceVersion(String paramString)
  {
    Object localObject = null;
    String[] arrayOfString = version.split("\\.");
    paramString = paramString.split("\\.");
    int j = Math.min(arrayOfString.length, paramString.length);
    int i = 0;
    if (i >= j)
    {
      paramString = (String)localObject;
      if (j >= arrayOfString.length) {
        paramString = version;
      }
    }
    int k;
    int m;
    do
    {
      return paramString;
      k = Integer.valueOf(arrayOfString[i]).intValue();
      m = Integer.valueOf(paramString[i]).intValue();
      if (k == m)
      {
        i += 1;
        break;
      }
      paramString = (String)localObject;
    } while (k >= m);
    return version;
  }
  
  public boolean matchSystemLevel(int paramInt)
  {
    return systemLevel <= paramInt;
  }
  
  public String matchVersion(int paramInt1, int paramInt2, String paramString)
  {
    if ((!matchPlatform(paramInt1)) || (!matchSystemLevel(paramInt2))) {
      return null;
    }
    return matchResourceVersion(paramString);
  }
  
  public void setPlatform(int paramInt)
  {
    platform = paramInt;
  }
  
  public void setSystemLevel(int paramInt)
  {
    systemLevel = paramInt;
  }
  
  public void setVersion(String paramString)
  {
    version = paramString;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.VersionControl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */