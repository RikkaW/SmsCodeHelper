public class ane
  extends Exception
{
  private int a;
  private boolean b = false;
  
  public ane(int paramInt, String paramString)
  {
    super(paramString);
    a = paramInt;
    b = true;
  }
  
  public ane(String paramString)
  {
    super(paramString);
  }
  
  public int a()
  {
    return a;
  }
  
  public boolean b()
  {
    return b;
  }
}

/* Location:
 * Qualified Name:     ane
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */