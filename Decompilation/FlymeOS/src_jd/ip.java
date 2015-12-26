import com.google.android.mms.MmsException;
import java.io.InputStream;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ip
{
  private XMLReader a;
  private io b;
  
  public ip()
  {
    System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver");
    try
    {
      a = XMLReaderFactory.createXMLReader();
      b = new io();
      a.setContentHandler(b);
      return;
    }
    catch (SAXException localSAXException)
    {
      throw new MmsException(localSAXException);
    }
  }
  
  private void a(auc paramauc)
  {
    paramauc.l();
    paramauc.m();
  }
  
  public auc a(InputStream paramInputStream)
  {
    b.a();
    a.parse(new InputSource(paramInputStream));
    paramInputStream = b.b();
    a(paramInputStream);
    return paramInputStream;
  }
}

/* Location:
 * Qualified Name:     ip
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */