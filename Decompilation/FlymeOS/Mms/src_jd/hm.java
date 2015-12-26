import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

public class hm
  extends hq
  implements Attr
{
  private String b;
  private String c;
  
  protected hm(hn paramhn, String paramString)
  {
    super(paramhn);
    b = paramString;
  }
  
  public String getName()
  {
    return b;
  }
  
  public Node getNextSibling()
  {
    return null;
  }
  
  public String getNodeName()
  {
    return b;
  }
  
  public short getNodeType()
  {
    return 2;
  }
  
  public Element getOwnerElement()
  {
    return null;
  }
  
  public Node getParentNode()
  {
    return null;
  }
  
  public Node getPreviousSibling()
  {
    return null;
  }
  
  public TypeInfo getSchemaTypeInfo()
  {
    return null;
  }
  
  public boolean getSpecified()
  {
    return c != null;
  }
  
  public String getValue()
  {
    return c;
  }
  
  public boolean isId()
  {
    return false;
  }
  
  public void setNodeValue(String paramString)
  {
    setValue(paramString);
  }
  
  public void setValue(String paramString)
  {
    c = paramString;
  }
}

/* Location:
 * Qualified Name:     hm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */