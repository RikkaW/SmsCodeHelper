import org.xml.sax.AttributeList;

public class ajb
{
  public Exception a = null;
  protected ajb.a b = new ajb.a();
  
  public ajb.a a()
  {
    return b;
  }
  
  public String b()
  {
    return b.a();
  }
  
  public String c()
  {
    return b.b();
  }
  
  public Exception d()
  {
    return a;
  }
  
  public class a
  {
    protected String a;
    protected AttributeList b;
    
    public a() {}
    
    public String a()
    {
      return a;
    }
    
    public void a(String paramString)
    {
      a = paramString;
    }
    
    public void a(AttributeList paramAttributeList)
    {
      b = paramAttributeList;
    }
    
    public String b()
    {
      if (b != null) {
        return b.getValue("href");
      }
      return "";
    }
  }
}

/* Location:
 * Qualified Name:     ajb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */