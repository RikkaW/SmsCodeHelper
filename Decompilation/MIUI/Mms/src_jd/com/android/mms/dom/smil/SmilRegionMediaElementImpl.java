package com.android.mms.dom.smil;

import org.w3c.dom.NodeList;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.SMILRegionElement;
import org.w3c.dom.smil.SMILRegionMediaElement;

public class SmilRegionMediaElementImpl
  extends SmilMediaElementImpl
  implements SMILRegionMediaElement
{
  private SMILRegionElement mRegion;
  
  SmilRegionMediaElementImpl(SmilDocumentImpl paramSmilDocumentImpl, String paramString)
  {
    super(paramSmilDocumentImpl, paramString);
  }
  
  public SMILRegionElement getRegion()
  {
    if (mRegion == null)
    {
      NodeList localNodeList = ((SMILDocument)getOwnerDocument()).getLayout().getElementsByTagName("region");
      int i = 0;
      while (i < localNodeList.getLength())
      {
        SMILRegionElement localSMILRegionElement = (SMILRegionElement)localNodeList.item(i);
        if (localSMILRegionElement.getId().equals(getAttribute("region"))) {
          mRegion = localSMILRegionElement;
        }
        i += 1;
      }
    }
    return mRegion;
  }
  
  public void setRegion(SMILRegionElement paramSMILRegionElement)
  {
    setAttribute("region", paramSMILRegionElement.getId());
    mRegion = paramSMILRegionElement;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilRegionMediaElementImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */