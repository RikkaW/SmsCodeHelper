package com.android.mms.dom.smil;

import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;

class SmilDocumentImpl$1
  extends ElementSequentialTimeContainerImpl
{
  SmilDocumentImpl$1(SmilDocumentImpl paramSmilDocumentImpl, SMILElement paramSMILElement)
  {
    super(paramSMILElement);
  }
  
  public boolean beginElement()
  {
    Event localEvent = this$0.createEvent("Event");
    localEvent.initEvent("SmilDocumentStart", false, false);
    this$0.dispatchEvent(localEvent);
    return true;
  }
  
  public boolean endElement()
  {
    Event localEvent = this$0.createEvent("Event");
    localEvent.initEvent("SimlDocumentEnd", false, false);
    this$0.dispatchEvent(localEvent);
    return true;
  }
  
  ElementTime getParentElementTime()
  {
    return null;
  }
  
  public NodeList getTimeChildren()
  {
    return this$0.getBody().getElementsByTagName("par");
  }
  
  public void pauseElement() {}
  
  public void resumeElement() {}
  
  public void seekElement(float paramFloat) {}
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilDocumentImpl.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */