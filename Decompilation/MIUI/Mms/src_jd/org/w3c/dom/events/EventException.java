package org.w3c.dom.events;

public class EventException
  extends RuntimeException
{
  public short code;
  
  public EventException(short paramShort, String paramString)
  {
    super(paramString);
    code = paramShort;
  }
}

/* Location:
 * Qualified Name:     org.w3c.dom.events.EventException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */