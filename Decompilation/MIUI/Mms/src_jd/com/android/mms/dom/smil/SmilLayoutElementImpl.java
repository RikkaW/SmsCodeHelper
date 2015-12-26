package com.android.mms.dom.smil;

import com.android.mms.layout.LayoutManager;
import com.android.mms.layout.LayoutParameters;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.SMILRootLayoutElement;

public class SmilLayoutElementImpl
  extends SmilElementImpl
  implements SMILLayoutElement
{
  SmilLayoutElementImpl(SmilDocumentImpl paramSmilDocumentImpl, String paramString)
  {
    super(paramSmilDocumentImpl, paramString);
  }
  
  public NodeList getRegions()
  {
    return getElementsByTagName("region");
  }
  
  public SMILRootLayoutElement getRootLayout()
  {
    Object localObject = getChildNodes();
    SMILRootLayoutElement localSMILRootLayoutElement = null;
    int j = ((NodeList)localObject).getLength();
    int i = 0;
    while (i < j)
    {
      if (((NodeList)localObject).item(i).getNodeName().equals("root-layout")) {
        localSMILRootLayoutElement = (SMILRootLayoutElement)((NodeList)localObject).item(i);
      }
      i += 1;
    }
    localObject = localSMILRootLayoutElement;
    if (localSMILRootLayoutElement == null)
    {
      localObject = (SMILRootLayoutElement)getOwnerDocument().createElement("root-layout");
      ((SMILRootLayoutElement)localObject).setWidth(LayoutManager.getInstance().getLayoutParameters().getWidth());
      ((SMILRootLayoutElement)localObject).setHeight(LayoutManager.getInstance().getLayoutParameters().getHeight());
      appendChild((Node)localObject);
    }
    return (SMILRootLayoutElement)localObject;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilLayoutElementImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */