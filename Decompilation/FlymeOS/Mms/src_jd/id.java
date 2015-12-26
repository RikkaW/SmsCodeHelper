import android.util.Log;

class id
  extends hx
{
  id(ic paramic, aud paramaud)
  {
    super(paramaud);
  }
  
  private att a(String paramString)
  {
    att localatt = ((ats)b.getOwnerDocument()).a("Event");
    localatt.a(paramString, false, false);
    return localatt;
  }
  
  private att a(String paramString, int paramInt)
  {
    hs localhs = (hs)((ats)b.getOwnerDocument()).a("Event");
    localhs.a(paramString, false, false, paramInt);
    return localhs;
  }
  
  public float b()
  {
    float f2 = super.b();
    float f1 = f2;
    String str;
    if (f2 == 0.0F)
    {
      str = b.getTagName();
      if ((str.equals("video")) || (str.equals("audio"))) {
        f1 = -1.0F;
      }
    }
    else
    {
      return f1;
    }
    if ((str.equals("text")) || (str.equals("img"))) {
      return 0.0F;
    }
    Log.w("Mms:smil", "Unknown media type");
    return f2;
  }
  
  public void c(float paramFloat)
  {
    att localatt = a("SmilMediaSeek", (int)paramFloat);
    b.a(localatt);
  }
  
  public boolean c()
  {
    att localatt = a("SmilMediaStart");
    b.a(localatt);
    return true;
  }
  
  public boolean d()
  {
    att localatt = a("SmilMediaEnd");
    b.a(localatt);
    return true;
  }
  
  aua f()
  {
    return a.getParentNode()).b;
  }
  
  public void h_()
  {
    att localatt = a("SmilMediaPause");
    b.a(localatt);
  }
  
  public void i_()
  {
    att localatt = a("SmilMediaStart");
    b.a(localatt);
  }
}

/* Location:
 * Qualified Name:     id
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */