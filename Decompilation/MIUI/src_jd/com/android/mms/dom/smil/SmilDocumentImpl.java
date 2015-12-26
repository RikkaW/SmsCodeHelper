package com.android.mms.dom.smil;

import com.android.mms.dom.DocumentImpl;
import com.android.mms.dom.events.EventImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.smil.ElementSequentialTimeContainer;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILDocument;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILLayoutElement;
import org.w3c.dom.smil.TimeList;

public class SmilDocumentImpl
  extends DocumentImpl
  implements DocumentEvent, SMILDocument
{
  ElementSequentialTimeContainer mSeqTimeContainer;
  
  public boolean beginElement()
  {
    return mSeqTimeContainer.beginElement();
  }
  
  public Element createElement(String paramString)
    throws DOMException
  {
    paramString = paramString.toLowerCase();
    if ((paramString.equals("text")) || (paramString.equals("img")) || (paramString.equals("video"))) {
      return new SmilRegionMediaElementImpl(this, paramString);
    }
    if (paramString.equals("audio")) {
      return new SmilMediaElementImpl(this, paramString);
    }
    if (paramString.equals("layout")) {
      return new SmilLayoutElementImpl(this, paramString);
    }
    if (paramString.equals("root-layout")) {
      return new SmilRootLayoutElementImpl(this, paramString);
    }
    if (paramString.equals("region")) {
      return new SmilRegionElementImpl(this, paramString);
    }
    if (paramString.equals("ref")) {
      return new SmilRefElementImpl(this, paramString);
    }
    if (paramString.equals("par")) {
      return new SmilParElementImpl(this, paramString);
    }
    return new SmilElementImpl(this, paramString);
  }
  
  public Event createEvent(String paramString)
    throws DOMException
  {
    if ("Event".equals(paramString)) {
      return new EventImpl();
    }
    throw new DOMException((short)9, "Not supported interface");
  }
  
  public boolean endElement()
  {
    return mSeqTimeContainer.endElement();
  }
  
  public NodeList getActiveChildrenAt(float paramFloat)
  {
    return mSeqTimeContainer.getActiveChildrenAt(paramFloat);
  }
  
  public TimeList getBegin()
  {
    return mSeqTimeContainer.getBegin();
  }
  
  public SMILElement getBody()
  {
    SMILElement localSMILElement = getDocumentElement();
    Node localNode = getHead().getNextSibling();
    Object localObject;
    if (localNode != null)
    {
      localObject = localNode;
      if ((localNode instanceof SMILElement)) {}
    }
    else
    {
      localObject = createElement("body");
      localSMILElement.appendChild((Node)localObject);
    }
    mSeqTimeContainer = new ElementSequentialTimeContainerImpl((SMILElement)localObject)
    {
      public boolean beginElement()
      {
        Event localEvent = createEvent("Event");
        localEvent.initEvent("SmilDocumentStart", false, false);
        dispatchEvent(localEvent);
        return true;
      }
      
      public boolean endElement()
      {
        Event localEvent = createEvent("Event");
        localEvent.initEvent("SimlDocumentEnd", false, false);
        dispatchEvent(localEvent);
        return true;
      }
      
      ElementTime getParentElementTime()
      {
        return null;
      }
      
      public NodeList getTimeChildren()
      {
        return getBody().getElementsByTagName("par");
      }
      
      public void pauseElement() {}
      
      public void resumeElement() {}
      
      public void seekElement(float paramAnonymousFloat) {}
    };
    return (SMILElement)localObject;
  }
  
  public SMILElement getDocumentElement()
  {
    Node localNode = getFirstChild();
    Object localObject;
    if (localNode != null)
    {
      localObject = localNode;
      if ((localNode instanceof SMILElement)) {}
    }
    else
    {
      localObject = createElement("smil");
      appendChild((Node)localObject);
    }
    return (SMILElement)localObject;
  }
  
  public float getDur()
  {
    return mSeqTimeContainer.getDur();
  }
  
  public TimeList getEnd()
  {
    return mSeqTimeContainer.getEnd();
  }
  
  public short getFill()
  {
    return mSeqTimeContainer.getFill();
  }
  
  public SMILElement getHead()
  {
    SMILElement localSMILElement = getDocumentElement();
    Node localNode = localSMILElement.getFirstChild();
    Object localObject;
    if (localNode != null)
    {
      localObject = localNode;
      if ((localNode instanceof SMILElement)) {}
    }
    else
    {
      localObject = createElement("head");
      localSMILElement.appendChild((Node)localObject);
    }
    return (SMILElement)localObject;
  }
  
  public SMILLayoutElement getLayout()
  {
    SMILElement localSMILElement = getHead();
    for (Node localNode = localSMILElement.getFirstChild(); (localNode != null) && (!(localNode instanceof SMILLayoutElement)); localNode = localNode.getNextSibling()) {}
    Object localObject = localNode;
    if (localNode == null)
    {
      localObject = new SmilLayoutElementImpl(this, "layout");
      localSMILElement.appendChild((Node)localObject);
    }
    return (SMILLayoutElement)localObject;
  }
  
  public NodeList getTimeChildren()
  {
    return mSeqTimeContainer.getTimeChildren();
  }
  
  public void pauseElement()
  {
    mSeqTimeContainer.pauseElement();
  }
  
  public void resumeElement()
  {
    mSeqTimeContainer.resumeElement();
  }
  
  public void seekElement(float paramFloat)
  {
    mSeqTimeContainer.seekElement(paramFloat);
  }
  
  public void setDur(float paramFloat)
    throws DOMException
  {
    mSeqTimeContainer.setDur(paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilDocumentImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */