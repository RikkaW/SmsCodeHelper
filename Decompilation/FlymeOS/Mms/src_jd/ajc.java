import java.io.InputStream;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;

public class ajc
{
  static final String a = e[0];
  static final String b = e[1];
  static final String c = f[6];
  static final String d = f[12];
  private static final String[] e = { "si", "indication", "info", "item" };
  private static final String[] f = { "action=signal-none", "action=signal-low", "action=signal-medium", "action=signal-high", "action=delete", "create", "href", "href=http://", "href=http://www.", "href=https://", "href=https://www.", "si-expires", "si-id", "class" };
  private static final String[] g = { ".com/", ".edu/", ".net/", ".org/" };
  
  public ajb a(InputStream paramInputStream)
  {
    ajf localajf = new ajf();
    ajb localajb = new ajb();
    ajc.a locala = new ajc.a(localajb);
    ajh localajh = new ajh();
    localajh.a(e);
    localajh.b(f);
    localajh.c(g);
    try
    {
      localajh.a(paramInputStream, localajf, locala);
      return localajb;
    }
    catch (Exception paramInputStream)
    {
      a = paramInputStream;
    }
    return localajb;
  }
  
  class a
    implements DocumentHandler
  {
    String a;
    String b;
    private ajb d;
    
    public a(ajb paramajb)
    {
      d = paramajb;
    }
    
    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      b = new String(paramArrayOfChar, paramInt1, paramInt2);
      if (a.equals("indication")) {
        d.a().a(b);
      }
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
      if (a.equals("indication")) {
        d.a().a(paramAttributeList);
      }
    }
  }
}

/* Location:
 * Qualified Name:     ajc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */