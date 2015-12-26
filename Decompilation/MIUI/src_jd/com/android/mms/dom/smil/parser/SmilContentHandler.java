package com.android.mms.dom.smil.parser;

import com.android.mms.dom.smil.SmilDocumentImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.smil.SMILDocument;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SmilContentHandler
  extends DefaultHandler
{
  private Node mCurrentNode;
  private SMILDocument mSmilDocument;
  
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2) {}
  
  public void endElement(String paramString1, String paramString2, String paramString3)
  {
    mCurrentNode = mCurrentNode.getParentNode();
  }
  
  public SMILDocument getSmilDocument()
  {
    return mSmilDocument;
  }
  
  public void reset()
  {
    mSmilDocument = new SmilDocumentImpl();
    mCurrentNode = mSmilDocument;
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    paramString1 = mSmilDocument.createElement(paramString2);
    if (paramAttributes != null)
    {
      int i = 0;
      while (i < paramAttributes.getLength())
      {
        paramString1.setAttribute(paramAttributes.getLocalName(i), paramAttributes.getValue(i));
        i += 1;
      }
    }
    mCurrentNode.appendChild(paramString1);
    mCurrentNode = paramString1;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.parser.SmilContentHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */