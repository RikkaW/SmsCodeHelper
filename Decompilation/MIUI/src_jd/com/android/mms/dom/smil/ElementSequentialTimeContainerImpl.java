package com.android.mms.dom.smil;

import com.android.mms.dom.NodeListImpl;
import java.util.ArrayList;
import org.w3c.dom.NodeList;
import org.w3c.dom.smil.ElementSequentialTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;

public abstract class ElementSequentialTimeContainerImpl
  extends ElementTimeContainerImpl
  implements ElementSequentialTimeContainer
{
  ElementSequentialTimeContainerImpl(SMILElement paramSMILElement)
  {
    super(paramSMILElement);
  }
  
  public NodeList getActiveChildrenAt(float paramFloat)
  {
    NodeList localNodeList = getTimeChildren();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < localNodeList.getLength())
    {
      paramFloat -= ((ElementTime)localNodeList.item(i)).getDur();
      if (paramFloat < 0.0F)
      {
        localArrayList.add(localNodeList.item(i));
        return new NodeListImpl(localArrayList);
      }
      i += 1;
    }
    return new NodeListImpl(localArrayList);
  }
  
  public float getDur()
  {
    float f1 = super.getDur();
    float f2 = f1;
    if (f1 == 0.0F)
    {
      NodeList localNodeList = getTimeChildren();
      int i = 0;
      for (;;)
      {
        f2 = f1;
        if (i >= localNodeList.getLength()) {
          break;
        }
        ElementTime localElementTime = (ElementTime)localNodeList.item(i);
        if (localElementTime.getDur() < 0.0F) {
          return -1.0F;
        }
        f1 += localElementTime.getDur();
        i += 1;
      }
    }
    return f2;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.ElementSequentialTimeContainerImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */