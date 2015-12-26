public class aml
{
  private final boolean a;
  private final String b;
  
  private aml(boolean paramBoolean, String paramString)
  {
    a = paramBoolean;
    b = paramString;
  }
  
  protected static aml a()
  {
    return new aml(true, null);
  }
  
  protected static aml a(String paramString)
  {
    return new aml(false, paramString);
  }
  
  public boolean b()
  {
    return a;
  }
  
  public String c()
  {
    return b;
  }
}

/* Location:
 * Qualified Name:     aml
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */