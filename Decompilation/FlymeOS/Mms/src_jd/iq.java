import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class iq
{
  public static void a(auc paramauc, OutputStream paramOutputStream)
  {
    try
    {
      paramOutputStream = new BufferedWriter(new OutputStreamWriter(paramOutputStream, "UTF-8"), 2048);
      a(paramOutputStream, paramauc.getDocumentElement());
      paramOutputStream.flush();
      return;
    }
    catch (UnsupportedEncodingException paramauc)
    {
      paramauc.printStackTrace();
      return;
    }
    catch (IOException paramauc)
    {
      paramauc.printStackTrace();
    }
  }
  
  private static void a(Writer paramWriter, Element paramElement)
  {
    paramWriter.write(60);
    paramWriter.write(paramElement.getTagName());
    Object localObject2;
    if (paramElement.hasAttributes())
    {
      localObject1 = paramElement.getAttributes();
      int i = 0;
      while (i < ((NamedNodeMap)localObject1).getLength())
      {
        localObject2 = (Attr)((NamedNodeMap)localObject1).item(i);
        paramWriter.write(" " + ((Attr)localObject2).getName());
        paramWriter.write("=\"" + ((Attr)localObject2).getValue() + "\"");
        i += 1;
      }
    }
    Object localObject1 = (aud)paramElement.getFirstChild();
    if (localObject1 != null)
    {
      paramWriter.write(62);
      do
      {
        a(paramWriter, (Element)localObject1);
        localObject2 = (aud)((aud)localObject1).getNextSibling();
        localObject1 = localObject2;
      } while (localObject2 != null);
      paramWriter.write("</");
      paramWriter.write(paramElement.getTagName());
      paramWriter.write(62);
      return;
    }
    paramWriter.write("/>");
  }
}

/* Location:
 * Qualified Name:     iq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */