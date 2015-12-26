public class alv
  extends Exception
{
  private int a;
  
  public alv(int paramInt, String paramString)
  {
    super(paramString);
    a = paramInt;
  }
  
  public int a()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     alv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */