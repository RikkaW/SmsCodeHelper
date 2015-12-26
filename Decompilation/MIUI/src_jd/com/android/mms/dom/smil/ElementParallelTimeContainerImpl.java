package com.android.mms.dom.smil;

import com.android.mms.dom.NodeListImpl;
import java.util.ArrayList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.smil.ElementParallelTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.Time;
import org.w3c.dom.smil.TimeList;

public abstract class ElementParallelTimeContainerImpl
  extends ElementTimeContainerImpl
  implements ElementParallelTimeContainer
{
  ElementParallelTimeContainerImpl(SMILElement paramSMILElement)
  {
    super(paramSMILElement);
  }
  
  public NodeList getActiveChildrenAt(float paramFloat)
  {
    ArrayList localArrayList = new ArrayList();
    NodeList localNodeList = getTimeChildren();
    int n = localNodeList.getLength();
    int j = 0;
    while (j < n)
    {
      double d1 = 0.0D;
      int i = 0;
      ElementTime localElementTime = (ElementTime)localNodeList.item(j);
      TimeList localTimeList = localElementTime.getBegin();
      int i1 = localTimeList.getLength();
      int k = 0;
      Time localTime;
      int m;
      double d2;
      double d3;
      while (k < i1)
      {
        localTime = localTimeList.item(k);
        m = i;
        d2 = d1;
        if (localTime.getResolved())
        {
          d3 = localTime.getResolvedOffset() * 1000.0D;
          m = i;
          d2 = d1;
          if (d3 <= paramFloat)
          {
            m = i;
            d2 = d1;
            if (d3 >= d1)
            {
              d2 = d3;
              m = 1;
            }
          }
        }
        k += 1;
        i = m;
        d1 = d2;
      }
      localTimeList = localElementTime.getEnd();
      i1 = localTimeList.getLength();
      k = 0;
      while (k < i1)
      {
        localTime = localTimeList.item(k);
        m = i;
        d2 = d1;
        if (localTime.getResolved())
        {
          d3 = localTime.getResolvedOffset() * 1000.0D;
          m = i;
          d2 = d1;
          if (d3 <= paramFloat)
          {
            m = i;
            d2 = d1;
            if (d3 >= d1)
            {
              d2 = d3;
              m = 0;
            }
          }
        }
        k += 1;
        i = m;
        d1 = d2;
      }
      if (i != 0) {
        localArrayList.add((Node)localElementTime);
      }
      j += 1;
    }
    return new NodeListImpl(localArrayList);
  }
  
  public float getDur()
  {
    float f2 = super.getDur();
    float f1 = f2;
    if (f2 == 0.0F) {
      f1 = getImplicitDuration();
    }
    return f1;
  }
  
  public String getEndSync()
  {
    String str2 = mSmilElement.getAttribute("endsync");
    String str1;
    if ((str2 == null) || (str2.length() == 0))
    {
      setEndSync("last");
      str1 = "last";
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return str1;
            str1 = str2;
          } while ("first".equals(str2));
          str1 = str2;
        } while ("last".equals(str2));
        str1 = str2;
      } while ("all".equals(str2));
      str1 = str2;
    } while ("media".equals(str2));
    setEndSync("last");
    return "last";
  }
  
  public float getImplicitDuration()
  {
    float f1 = -1.0F;
    float f2 = f1;
    if ("last".equals(getEndSync()))
    {
      NodeList localNodeList = getTimeChildren();
      int i = 0;
      for (;;)
      {
        f2 = f1;
        if (i >= localNodeList.getLength()) {
          break;
        }
        TimeList localTimeList = ((ElementTime)localNodeList.item(i)).getEnd();
        int j = 0;
        while (j < localTimeList.getLength())
        {
          Time localTime = localTimeList.item(j);
          if (localTime.getTimeType() == 0) {
            return -1.0F;
          }
          f2 = f1;
          if (localTime.getResolved())
          {
            float f3 = (float)localTime.getResolvedOffset();
            f2 = f1;
            if (f3 > f1) {
              f2 = f3;
            }
          }
          j += 1;
          f1 = f2;
        }
        i += 1;
      }
    }
    return f2;
  }
  
  public void setEndSync(String paramString)
    throws DOMException
  {
    if (("first".equals(paramString)) || ("last".equals(paramString)) || ("all".equals(paramString)) || ("media".equals(paramString)))
    {
      mSmilElement.setAttribute("endsync", paramString);
      return;
    }
    throw new DOMException((short)9, "Unsupported endsync value" + paramString);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.ElementParallelTimeContainerImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */