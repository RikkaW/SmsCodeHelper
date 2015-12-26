package com.android.mms.dom.smil.parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILElement;

public class SmilXmlSerializer
{
  public static void serialize(SMILDocument paramSMILDocument, OutputStream paramOutputStream)
  {
    try
    {
      paramOutputStream = new BufferedWriter(new OutputStreamWriter(paramOutputStream, "UTF-8"), 2048);
      writeElement(paramOutputStream, paramSMILDocument.getDocumentElement());
      paramOutputStream.flush();
      return;
    }
    catch (UnsupportedEncodingException paramSMILDocument)
    {
      paramSMILDocument.printStackTrace();
      return;
    }
    catch (IOException paramSMILDocument)
    {
      paramSMILDocument.printStackTrace();
    }
  }
  
  private static void writeElement(Writer paramWriter, Element paramElement)
    throws IOException
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
    Object localObject1 = (SMILElement)paramElement.getFirstChild();
    if (localObject1 != null)
    {
      paramWriter.write(62);
      do
      {
        writeElement(paramWriter, (Element)localObject1);
        localObject2 = (SMILElement)((SMILElement)localObject1).getNextSibling();
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
 * Qualified Name:     com.android.mms.dom.smil.parser.SmilXmlSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */