package com.android.mms.dom.events;

import org.w3c.dom.events.EventListener;

class EventTargetImpl$EventListenerEntry
{
  final EventListener mListener;
  final String mType;
  final boolean mUseCapture;
  
  EventTargetImpl$EventListenerEntry(String paramString, EventListener paramEventListener, boolean paramBoolean)
  {
    mType = paramString;
    mListener = paramEventListener;
    mUseCapture = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.events.EventTargetImpl.EventListenerEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */