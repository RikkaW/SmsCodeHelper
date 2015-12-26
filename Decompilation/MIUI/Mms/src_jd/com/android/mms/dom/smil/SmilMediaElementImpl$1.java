package com.android.mms.dom.smil;

import android.util.Log;
import com.android.mms.dom.events.EventImpl;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;

class SmilMediaElementImpl$1
  extends ElementTimeImpl
{
  SmilMediaElementImpl$1(SmilMediaElementImpl paramSmilMediaElementImpl, SMILElement paramSMILElement)
  {
    super(paramSMILElement);
  }
  
  private Event createEvent(String paramString)
  {
    Event localEvent = ((DocumentEvent)this$0.getOwnerDocument()).createEvent("Event");
    localEvent.initEvent(paramString, false, false);
    return localEvent;
  }
  
  private Event createEvent(String paramString, int paramInt)
  {
    EventImpl localEventImpl = (EventImpl)((DocumentEvent)this$0.getOwnerDocument()).createEvent("Event");
    localEventImpl.initEvent(paramString, false, false, paramInt);
    return localEventImpl;
  }
  
  public boolean beginElement()
  {
    Event localEvent = createEvent("SmilMediaStart");
    this$0.dispatchEvent(localEvent);
    return true;
  }
  
  public boolean endElement()
  {
    Event localEvent = createEvent("SmilMediaEnd");
    this$0.dispatchEvent(localEvent);
    return true;
  }
  
  public float getDur()
  {
    float f2 = super.getDur();
    float f1 = f2;
    String str;
    if (f2 == 0.0F)
    {
      str = this$0.getTagName();
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
    this$0.dispatchEvent(localEvent);
  }
  
  public void resumeElement()
  {
    Event localEvent = createEvent("SmilMediaStart");
    this$0.dispatchEvent(localEvent);
  }
  
  public void seekElement(float paramFloat)
  {
    Event localEvent = createEvent("SmilMediaSeek", (int)paramFloat);
    this$0.dispatchEvent(localEvent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilMediaElementImpl.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */