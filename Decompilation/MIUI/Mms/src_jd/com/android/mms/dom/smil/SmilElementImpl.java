package com.android.mms.dom.smil;

import com.android.mms.dom.ElementImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.smil.SMILElement;

public class SmilElementImpl
  extends ElementImpl
  implements SMILElement
{
  SmilElementImpl(SmilDocumentImpl paramSmilDocumentImpl, String paramString)
  {
    super(paramSmilDocumentImpl, paramString.toLowerCase());
  }
  
  public String getId()
  {
    return null;
  }
  
  public void setId(String paramString)
    throws DOMException
  {}
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilElementImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */