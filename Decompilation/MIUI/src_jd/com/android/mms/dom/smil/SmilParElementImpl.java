package com.android.mms.dom.smil;

import java.util.ArrayList;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.smil.ElementParallelTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILParElement;
import org.w3c.dom.smil.TimeList;

public class SmilParElementImpl
  extends SmilElementImpl
  implements SMILParElement
{
  ElementParallelTimeContainer mParTimeContainer = new ElementParallelTimeContainerImpl(this)
  {
    public boolean beginElement()
    {
      Event localEvent = ((DocumentEvent)getOwnerDocument()).createEvent("Event");
      localEvent.initEvent("SmilSlideStart", false, false);
      dispatchEvent(localEvent);
      return true;
    }
    
    public boolean endElement()
    {
      Event localEvent = ((DocumentEvent)getOwnerDocument()).createEvent("Event");
      localEvent.initEvent("SmilSlideEnd", false, false);
      dispatchEvent(localEvent);
      return true;
    }
    
    public TimeList getBegin()
    {
      TimeList localTimeList = super.getBegin();
      Object localObject = localTimeList;
      if (localTimeList.getLength() > 1)
      {
        localObject = new ArrayList();
        ((ArrayList)localObject).add(localTimeList.item(0));
        localObject = new TimeListImpl((ArrayList)localObject);
      }
      return (TimeList)localObject;
    }
    
    ElementTime getParentElementTime()
    {
      return mSmilElement.getOwnerDocument()).mSeqTimeContainer;
    }
    
    public NodeList getTimeChildren()
    {
      return getChildNodes();
    }
    
    public void pauseElement() {}
    
    public void resumeElement() {}
    
    public void seekElement(float paramAnonymousFloat) {}
  };
  
  SmilParElementImpl(SmilDocumentImpl paramSmilDocumentImpl, String paramString)
  {
    super(paramSmilDocumentImpl, paramString.toUpperCase());
  }
  
  public boolean beginElement()
  {
    return mParTimeContainer.beginElement();
  }
  
  public boolean endElement()
  {
    return mParTimeContainer.endElement();
  }
  
  public NodeList getActiveChildrenAt(float paramFloat)
  {
    return mParTimeContainer.getActiveChildrenAt(paramFloat);
  }
  
  public TimeList getBegin()
  {
    return mParTimeContainer.getBegin();
  }
  
  public float getDur()
  {
    return mParTimeContainer.getDur();
  }
  
  public TimeList getEnd()
  {
    return mParTimeContainer.getEnd();
  }
  
  public short getFill()
  {
    return mParTimeContainer.getFill();
  }
  
  public NodeList getTimeChildren()
  {
    return mParTimeContainer.getTimeChildren();
  }
  
  public void pauseElement()
  {
    mParTimeContainer.pauseElement();
  }
  
  public void resumeElement()
  {
    mParTimeContainer.resumeElement();
  }
  
  public void seekElement(float paramFloat)
  {
    mParTimeContainer.seekElement(paramFloat);
  }
  
  public void setDur(float paramFloat)
    throws DOMException
  {
    mParTimeContainer.setDur(paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilParElementImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */