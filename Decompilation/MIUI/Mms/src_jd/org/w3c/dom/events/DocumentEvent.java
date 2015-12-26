package org.w3c.dom.events;

import org.w3c.dom.DOMException;

public abstract interface DocumentEvent
{
  public abstract Event createEvent(String paramString)
    throws DOMException;
}

/* Location:
 * Qualified Name:     org.w3c.dom.events.DocumentEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */