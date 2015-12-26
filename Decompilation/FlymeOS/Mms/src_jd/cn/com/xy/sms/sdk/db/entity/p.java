package cn.com.xy.sms.sdk.db.entity;

import java.util.ArrayList;
import java.util.List;

public final class p
{
  public String a;
  public String b;
  public int c;
  public int d = 0;
  public int e = 0;
  public List<SceneRule> f;
  private int g;
  
  public final void a(SceneRule paramSceneRule)
  {
    if (f == null) {
      f = new ArrayList();
    }
    f.add(paramSceneRule);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return false;
      try
      {
        if (hashCode() == paramObject.hashCode()) {
          return true;
        }
        if (getClass() == paramObject.getClass())
        {
          paramObject = (p)paramObject;
          if (a != null)
          {
            boolean bool = a.equals(a);
            if (!bool) {}
          }
        }
      }
      catch (Throwable paramObject)
      {
        for (;;)
        {
          ((Throwable)paramObject).printStackTrace();
        }
      }
    }
    return true;
  }
  
  public final int hashCode()
  {
    return super.hashCode();
  }
  
  public final String toString()
  {
    return "Sceneconfig [sceneId=" + a + ", sceneVersion=" + b + ", isDownload=" + d + "]";
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.p
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */