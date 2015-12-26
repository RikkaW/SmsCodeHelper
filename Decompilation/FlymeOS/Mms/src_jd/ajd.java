import org.xml.sax.AttributeList;

public class ajd
{
  public Exception a = null;
  protected AttributeList b;
  
  public String a()
  {
    if (b != null) {
      return b.getValue("href");
    }
    return "";
  }
  
  public void a(AttributeList paramAttributeList)
  {
    b = paramAttributeList;
  }
  
  public Exception b()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     ajd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */