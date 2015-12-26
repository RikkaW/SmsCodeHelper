package com.android.mms.dom.events;

import android.util.Log;
import java.util.ArrayList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class EventTargetImpl
  implements EventTarget
{
  private ArrayList<EventListenerEntry> mListenerEntries;
  private EventTarget mNodeTarget;
  
  public EventTargetImpl(EventTarget paramEventTarget)
  {
    mNodeTarget = paramEventTarget;
  }
  
  public void addEventListener(String paramString, EventListener paramEventListener, boolean paramBoolean)
  {
    if ((paramString == null) || (paramString.equals("")) || (paramEventListener == null)) {
      return;
    }
    removeEventListener(paramString, paramEventListener, paramBoolean);
    if (mListenerEntries == null) {
      mListenerEntries = new ArrayList();
    }
    mListenerEntries.add(new EventListenerEntry(paramString, paramEventListener, paramBoolean));
  }
  
  public boolean dispatchEvent(Event paramEvent)
    throws EventException
  {
    paramEvent = (EventImpl)paramEvent;
    if (!paramEvent.isInitialized()) {
      throw new EventException((short)0, "Event not initialized");
    }
    if ((paramEvent.getType() == null) || (paramEvent.getType().equals(""))) {
      throw new EventException((short)0, "Unspecified even type");
    }
    paramEvent.setTarget(mNodeTarget);
    paramEvent.setEventPhase((short)2);
    paramEvent.setCurrentTarget(mNodeTarget);
    if ((!paramEvent.isPropogationStopped()) && (mListenerEntries != null))
    {
      int i = 0;
      for (;;)
      {
        if (i < mListenerEntries.size())
        {
          EventListenerEntry localEventListenerEntry = (EventListenerEntry)mListenerEntries.get(i);
          if ((!mUseCapture) && (mType.equals(paramEvent.getType()))) {}
          try
          {
            mListener.handleEvent(paramEvent);
            i += 1;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              Log.w("EventTargetImpl", "Catched EventListener exception", localException);
            }
          }
        }
      }
    }
    if (paramEvent.getBubbles()) {}
    return paramEvent.isPreventDefault();
  }
  
  public void removeEventListener(String paramString, EventListener paramEventListener, boolean paramBoolean)
  {
    if (mListenerEntries == null) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < mListenerEntries.size())
      {
        EventListenerEntry localEventListenerEntry = (EventListenerEntry)mListenerEntries.get(i);
        if ((mUseCapture == paramBoolean) && (mListener == paramEventListener) && (mType.equals(paramString)))
        {
          mListenerEntries.remove(i);
          return;
        }
        i += 1;
      }
    }
  }
  
  static class EventListenerEntry
  {
    final EventListener mListener;
    final String mType;
    final boolean mUseCapture;
    
    EventListenerEntry(String paramString, EventListener paramEventListener, boolean paramBoolean)
    {
      mType = paramString;
      mListener = paramEventListener;
      mUseCapture = paramBoolean;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.events.EventTargetImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */