package org.w3c.dom.events;

public abstract interface EventTarget
{
  public abstract void addEventListener(String paramString, EventListener paramEventListener, boolean paramBoolean);
  
  public abstract boolean dispatchEvent(Event paramEvent)
    throws EventException;
  
  public abstract void removeEventListener(String paramString, EventListener paramEventListener, boolean paramBoolean);
}

/* Location:
 * Qualified Name:     org.w3c.dom.events.EventTarget
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */