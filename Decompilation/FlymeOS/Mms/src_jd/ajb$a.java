import org.xml.sax.AttributeList;

public class ajb$a
{
  protected String a;
  protected AttributeList b;
  
  public ajb$a(ajb paramajb) {}
  
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

/* Location:
 * Qualified Name:     ajb.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */