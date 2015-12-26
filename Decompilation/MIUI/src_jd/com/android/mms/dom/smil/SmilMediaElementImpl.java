package com.android.mms.dom.smil;

import android.util.Log;
import com.android.mms.dom.events.EventImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.SMILMediaElement;
import org.w3c.dom.smil.TimeList;

public class SmilMediaElementImpl
  extends SmilElementImpl
  implements SMILMediaElement
{
  ElementTime mElementTime = new ElementTimeImpl(this)
  {
    private Event createEvent(String paramAnonymousString)
    {
      Event localEvent = ((DocumentEvent)getOwnerDocument()).createEvent("Event");
      localEvent.initEvent(paramAnonymousString, false, false);
      return localEvent;
    }
    
    private Event createEvent(String paramAnonymousString, int paramAnonymousInt)
    {
      EventImpl localEventImpl = (EventImpl)((DocumentEvent)getOwnerDocument()).createEvent("Event");
      localEventImpl.initEvent(paramAnonymousString, false, false, paramAnonymousInt);
      return localEventImpl;
    }
    
    public boolean beginElement()
    {
      Event localEvent = createEvent("SmilMediaStart");
      dispatchEvent(localEvent);
      return true;
    }
    
    public boolean endElement()
    {
      Event localEvent = createEvent("SmilMediaEnd");
      dispatchEvent(localEvent);
      return true;
    }
    
    public float getDur()
    {
      float f2 = super.getDur();
      float f1 = f2;
      String str;
      if (f2 == 0.0F)
      {
        str = getTagName();
        if ((str.equals("video")) || (str.equals("audio"))) {
          f1 = -1.0F;
        }
      }
      else
      {
        return f1;
      }
      if ((str.equals("text")) || (str.equals("img"))) {
        return 0.0F;
      }
      Log.w("Mms:smil", "Unknown media type");
      return f2;
    }
    
    ElementTime getParentElementTime()
    {
      return mSmilElement.getParentNode()).mParTimeContainer;
    }
    
    public void pauseElement()
    {
      Event localEvent = createEvent("SmilMediaPause");
      dispatchEvent(localEvent);
    }
    
    public void resumeElement()
    {
      Event localEvent = createEvent("SmilMediaStart");
      dispatchEvent(localEvent);
    }
    
    public void seekElement(float paramAnonymousFloat)
    {
      Event localEvent = createEvent("SmilMediaSeek", (int)paramAnonymousFloat);
      dispatchEvent(localEvent);
    }
  };
  
  SmilMediaElementImpl(SmilDocumentImpl paramSmilDocumentImpl, String paramString)
  {
    super(paramSmilDocumentImpl, paramString);
  }
  
  public boolean beginElement()
  {
    return mElementTime.beginElement();
  }
  
  public boolean endElement()
  {
    return mElementTime.endElement();
  }
  
  public TimeList getBegin()
  {
    return mElementTime.getBegin();
  }
  
  public float getDur()
  {
    return mElementTime.getDur();
  }
  
  public TimeList getEnd()
  {
    return mElementTime.getEnd();
  }
  
  public short getFill()
  {
    return mElementTime.getFill();
  }
  
  public String getSrc()
  {
    return getAttribute("src");
  }
  
  public void pauseElement()
  {
    mElementTime.pauseElement();
  }
  
  public void resumeElement()
  {
    mElementTime.resumeElement();
  }
  
  public void seekElement(float paramFloat)
  {
    mElementTime.seekElement(paramFloat);
  }
  
  public void setDur(float paramFloat)
    throws DOMException
  {
    mElementTime.setDur(paramFloat);
  }
  
  public void setSrc(String paramString)
    throws DOMException
  {
    setAttribute("src", paramString);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilMediaElementImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */