public class il
  extends ia
  implements aul
{
  il(hy paramhy, String paramString)
  {
    super(paramhy, paramString);
  }
  
  private int b(String paramString)
  {
    String str = paramString;
    if (paramString.endsWith("px")) {
      str = paramString.substring(0, paramString.indexOf("px"));
    }
    try
    {
      int i = Integer.parseInt(str);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return 0;
  }
  
  public void c(int paramInt)
  {
    setAttribute("height", String.valueOf(paramInt) + "px");
  }
  
  public void c(String paramString)
  {
    setAttribute("backgroundColor", paramString);
  }
  
  public String d()
  {
    return getAttribute("backgroundColor");
  }
  
  public void d(int paramInt)
  {
    setAttribute("width", String.valueOf(paramInt) + "px");
  }
  
  public int e()
  {
    return b(getAttribute("height"));
  }
  
  public int f()
  {
    return b(getAttribute("width"));
  }
}

/* Location:
 * Qualified Name:     il
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */