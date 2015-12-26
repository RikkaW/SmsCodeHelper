package com.android.mms.dom.smil;

import org.w3c.dom.DOMException;
import org.w3c.dom.smil.SMILRootLayoutElement;

public class SmilRootLayoutElementImpl
  extends SmilElementImpl
  implements SMILRootLayoutElement
{
  SmilRootLayoutElementImpl(SmilDocumentImpl paramSmilDocumentImpl, String paramString)
  {
    super(paramSmilDocumentImpl, paramString);
  }
  
  private int parseAbsoluteLength(String paramString)
  {
    String str = paramString;
    if (paramString.endsWith("px")) {
      str = paramString.substring(0, paramString.indexOf("px"));
    }
    try
    {
      int i = Integer.parseInt(str);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return 0;
  }
  
  public String getBackgroundColor()
  {
    return getAttribute("backgroundColor");
  }
  
  public int getHeight()
  {
    return parseAbsoluteLength(getAttribute("height"));
  }
  
  public int getWidth()
  {
    return parseAbsoluteLength(getAttribute("width"));
  }
  
  public void setBackgroundColor(String paramString)
    throws DOMException
  {
    setAttribute("backgroundColor", paramString);
  }
  
  public void setHeight(int paramInt)
    throws DOMException
  {
    setAttribute("height", String.valueOf(paramInt) + "px");
  }
  
  public void setWidth(int paramInt)
    throws DOMException
  {
    setAttribute("width", String.valueOf(paramInt) + "px");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilRootLayoutElementImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */