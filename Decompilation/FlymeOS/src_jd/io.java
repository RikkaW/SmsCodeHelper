import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class io
  extends DefaultHandler
{
  private auc a;
  private Node b;
  
  public void a()
  {
    a = new hy();
    b = a;
  }
  
  public auc b()
  {
    return a;
  }
  
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2) {}
  
  public void endElement(String paramString1, String paramString2, String paramString3)
  {
    b = b.getParentNode();
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    paramString1 = a.createElement(paramString2);
    if (paramAttributes != null)
    {
      int i = 0;
      while (i < paramAttributes.getLength())
      {
        paramString1.setAttribute(paramAttributes.getLocalName(i), paramAttributes.getValue(i));
        i += 1;
      }
    }
    b.appendChild(paramString1);
    b = paramString1;
  }
}

/* Location:
 * Qualified Name:     io
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */