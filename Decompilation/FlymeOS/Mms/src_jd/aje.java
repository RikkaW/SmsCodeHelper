import java.io.InputStream;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;

public class aje
{
  static final String a = c[0];
  static final String b = d[3];
  private static final String[] c = { "sl" };
  private static final String[] d = { "action=signal-low", "action=signal-high", "action=cache", "href", "href=http://", "href=http://www.", "href=https://", "href=https://www." };
  private static final String[] e = { ".com/", ".edu/", ".net/", ".org/" };
  
  public ajd a(InputStream paramInputStream)
  {
    ajf localajf = new ajf();
    ajd localajd = new ajd();
    aje.a locala = new aje.a(localajd);
    ajh localajh = new ajh();
    localajh.a(c);
    localajh.b(d);
    localajh.c(e);
    try
    {
      localajh.a(paramInputStream, localajf, locala);
      return localajd;
    }
    catch (Exception paramInputStream)
    {
      a = paramInputStream;
    }
    return localajd;
  }
  
  class a
    implements DocumentHandler
  {
    String a;
    String b;
    private ajd d;
    
    public a(ajd paramajd)
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
}

/* Location:
 * Qualified Name:     aje
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */