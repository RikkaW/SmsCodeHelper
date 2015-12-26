package com.android.mms.dom.smil;

import java.util.ArrayList;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.TimeList;

class SmilParElementImpl$1
  extends ElementParallelTimeContainerImpl
{
  SmilParElementImpl$1(SmilParElementImpl paramSmilParElementImpl, SMILElement paramSMILElement)
  {
    super(paramSMILElement);
  }
  
  public boolean beginElement()
  {
    Event localEvent = ((DocumentEvent)this$0.getOwnerDocument()).createEvent("Event");
    localEvent.initEvent("SmilSlideStart", false, false);
    this$0.dispatchEvent(localEvent);
    return true;
  }
  
  public boolean endElement()
  {
    Event localEvent = ((DocumentEvent)this$0.getOwnerDocument()).createEvent("Event");
    localEvent.initEvent("SmilSlideEnd", false, false);
    this$0.dispatchEvent(localEvent);
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
    return this$0.getChildNodes();
  }
  
  public void pauseElement() {}
  
  public void resumeElement() {}
  
  public void seekElement(float paramFloat) {}
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilParElementImpl.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */