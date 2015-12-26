import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;

class aje$a
  implements DocumentHandler
{
  String a;
  String b;
  private ajd d;
  
  public aje$a(aje paramaje, ajd paramajd)
  {
    d = paramajd;
  }
  
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    b = new String(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void endDocument() {}
  
  public void endElement(String paramString) {}
  
  public void ignorableWhitespace(char[] paramArrayOfChar, int paramInt1, int paramInt2) {}
  
  public void processingInstruction(String paramString1, String paramString2) {}
  
  public void setDocumentLocator(Locator paramLocator) {}
  
  public void startDocument() {}
  
  public void startElement(String paramString, AttributeList paramAttributeList)
  {
    a = paramString;
    if (a.equals(aje.a)) {
      d.a(paramAttributeList);
    }
  }
}

/* Location:
 * Qualified Name:     aje.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */