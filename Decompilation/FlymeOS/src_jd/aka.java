public abstract class aka
  extends ajz
{
  private String i;
  private String j;
  private String k;
  private String l;
  
  public aka(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (paramString1 == null) {
      throw new IllegalArgumentException("Name must not be null");
    }
    i = paramString1;
    j = paramString2;
    k = paramString3;
    l = paramString4;
  }
  
  public String b()
  {
    return i;
  }
  
  public String c()
  {
    return j;
  }
  
  public String d()
  {
    return k;
  }
  
  public String e()
  {
    return l;
  }
}

/* Location:
 * Qualified Name:     aka
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */