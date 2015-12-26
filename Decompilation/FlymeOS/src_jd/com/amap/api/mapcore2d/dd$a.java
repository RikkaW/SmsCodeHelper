package com.amap.api.mapcore2d;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

class dd$a
  extends DefaultHandler
{
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (dd.a()) {
      dd.a(new String(paramArrayOfChar, paramInt1, paramInt2));
    }
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3)
  {
    dd.a(false);
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    if ((paramString2.equals("string")) && ("UTDID".equals(paramAttributes.getValue("name")))) {
      dd.a(true);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dd.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */