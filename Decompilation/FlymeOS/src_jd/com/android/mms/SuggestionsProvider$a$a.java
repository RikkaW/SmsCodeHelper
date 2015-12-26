package com.android.mms;

class SuggestionsProvider$a$a
{
  private String b;
  private long c;
  private long d;
  private String e;
  private String f;
  private int g;
  private String h;
  
  public SuggestionsProvider$a$a(SuggestionsProvider.a parama, int paramInt1, String paramString1, long paramLong1, long paramLong2, int paramInt2, String paramString2, String paramString3)
  {
    b = paramString1.trim();
    c = paramLong1;
    d = paramLong2;
    if (paramInt2 == 1) {
      e = "sms";
    }
    for (;;)
    {
      f = paramString2;
      g = paramInt1;
      h = paramString3;
      return;
      if (paramInt2 == 2) {
        e = "mms";
      }
    }
  }
  
  public String a()
  {
    return b;
  }
  
  public long b()
  {
    return c;
  }
  
  public long c()
  {
    return d;
  }
  
  public String d()
  {
    return e;
  }
  
  public String e()
  {
    return f;
  }
  
  public String f()
  {
    return h;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.SuggestionsProvider.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */