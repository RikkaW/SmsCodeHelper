package com.android.mms.dom.smil;

import org.w3c.dom.DOMException;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.SMILRegionElement;
import org.w3c.dom.smil.SMILRootLayoutElement;

public class SmilRegionElementImpl
  extends SmilElementImpl
  implements SMILRegionElement
{
  SmilRegionElementImpl(SmilDocumentImpl paramSmilDocumentImpl, String paramString)
  {
    super(paramSmilDocumentImpl, paramString);
  }
  
  private int parseRegionLength(String paramString, boolean paramBoolean)
  {
    if (paramString.endsWith("px")) {
      return Integer.parseInt(paramString.substring(0, paramString.indexOf("px")));
    }
    if (paramString.endsWith("%"))
    {
      double d = 0.01D * Integer.parseInt(paramString.substring(0, paramString.length() - 1));
      if (paramBoolean) {}
      for (d *= ((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getWidth();; d *= ((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getHeight()) {
        return (int)Math.round(d);
      }
    }
    return Integer.parseInt(paramString);
  }
  
  public String getBackgroundColor()
  {
    return getAttribute("backgroundColor");
  }
  
  public String getFit()
  {
    String str = getAttribute("fit");
    if ("fill".equalsIgnoreCase(str)) {
      return "fill";
    }
    if ("meet".equalsIgnoreCase(str)) {
      return "meet";
    }
    if ("scroll".equalsIgnoreCase(str)) {
      return "scroll";
    }
    if ("slice".equalsIgnoreCase(str)) {
      return "slice";
    }
    return "hidden";
  }
  
  public int getHeight()
  {
    int j;
    int i;
    try
    {
      j = parseRegionLength(getAttribute("height"), false);
      i = j;
      if (j == 0) {
        i = ((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getHeight();
      }
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      i = ((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getHeight();
    }
    for (;;)
    {
      try
      {
        j = parseRegionLength(getAttribute("top"), false);
        i -= j;
      }
      catch (NumberFormatException localNumberFormatException3)
      {
        continue;
      }
      try
      {
        j = parseRegionLength(getAttribute("bottom"), false);
        i -= j;
      }
      catch (NumberFormatException localNumberFormatException2) {}
    }
    return i;
  }
  
  public String getId()
  {
    return getAttribute("id");
  }
  
  public int getLeft()
  {
    try
    {
      i = parseRegionLength(getAttribute("left"), true);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        int i = ((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getWidth();
        int j = parseRegionLength(getAttribute("right"), true);
        int k = parseRegionLength(getAttribute("width"), true);
        return i - j - k;
      }
      catch (NumberFormatException localNumberFormatException2) {}
    }
    return 0;
  }
  
  public int getTop()
  {
    try
    {
      i = parseRegionLength(getAttribute("top"), false);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        int i = ((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getHeight();
        int j = parseRegionLength(getAttribute("bottom"), false);
        int k = parseRegionLength(getAttribute("height"), false);
        return i - j - k;
      }
      catch (NumberFormatException localNumberFormatException2) {}
    }
    return 0;
  }
  
  public int getWidth()
  {
    int j;
    int i;
    try
    {
      j = parseRegionLength(getAttribute("width"), true);
      i = j;
      if (j == 0) {
        i = ((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getWidth();
      }
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      i = ((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getWidth();
    }
    for (;;)
    {
      try
      {
        j = parseRegionLength(getAttribute("left"), true);
        i -= j;
      }
      catch (NumberFormatException localNumberFormatException3)
      {
        continue;
      }
      try
      {
        j = parseRegionLength(getAttribute("right"), true);
        i -= j;
      }
      catch (NumberFormatException localNumberFormatException2) {}
    }
    return i;
  }
  
  public void setBackgroundColor(String paramString)
    throws DOMException
  {
    setAttribute("backgroundColor", paramString);
  }
  
  public void setFit(String paramString)
    throws DOMException
  {
    if ((paramString.equalsIgnoreCase("fill")) || (paramString.equalsIgnoreCase("meet")) || (paramString.equalsIgnoreCase("scroll")) || (paramString.equalsIgnoreCase("slice")))
    {
      setAttribute("fit", paramString.toLowerCase());
      return;
    }
    setAttribute("fit", "hidden");
  }
  
  public void setHeight(int paramInt)
    throws DOMException
  {
    setAttribute("height", String.valueOf(paramInt) + "px");
  }
  
  public void setId(String paramString)
    throws DOMException
  {
    setAttribute("id", paramString);
  }
  
  public void setLeft(int paramInt)
    throws DOMException
  {
    setAttribute("left", String.valueOf(paramInt));
  }
  
  public void setTop(int paramInt)
    throws DOMException
  {
    setAttribute("top", String.valueOf(paramInt));
  }
  
  public void setWidth(int paramInt)
    throws DOMException
  {
    setAttribute("width", String.valueOf(paramInt) + "px");
  }
  
  public String toString()
  {
    return super.toString() + ": id=" + getId() + ", width=" + getWidth() + ", height=" + getHeight() + ", left=" + getLeft() + ", top=" + getTop();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilRegionElementImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */