package com.android.mms.dom.events;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;

public class EventImpl
  implements Event
{
  private boolean mCanBubble;
  private boolean mCancelable;
  private EventTarget mCurrentTarget;
  private short mEventPhase;
  private String mEventType;
  private boolean mInitialized;
  private boolean mPreventDefault;
  private int mSeekTo;
  private boolean mStopPropagation;
  private EventTarget mTarget;
  private final long mTimeStamp = System.currentTimeMillis();
  
  public boolean getBubbles()
  {
    return mCanBubble;
  }
  
  public int getSeekTo()
  {
    return mSeekTo;
  }
  
  public String getType()
  {
    return mEventType;
  }
  
  public void initEvent(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    mEventType = paramString;
    mCanBubble = paramBoolean1;
    mCancelable = paramBoolean2;
    mInitialized = true;
  }
  
  public void initEvent(String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    mSeekTo = paramInt;
    initEvent(paramString, paramBoolean1, paramBoolean2);
  }
  
  boolean isInitialized()
  {
    return mInitialized;
  }
  
  boolean isPreventDefault()
  {
    return mPreventDefault;
  }
  
  boolean isPropogationStopped()
  {
    return mStopPropagation;
  }
  
  void setCurrentTarget(EventTarget paramEventTarget)
  {
    mCurrentTarget = paramEventTarget;
  }
  
  void setEventPhase(short paramShort)
  {
    mEventPhase = paramShort;
  }
  
  void setTarget(EventTarget paramEventTarget)
  {
    mTarget = paramEventTarget;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.events.EventImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */