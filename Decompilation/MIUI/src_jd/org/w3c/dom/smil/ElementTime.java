package org.w3c.dom.smil;

import org.w3c.dom.DOMException;

public abstract interface ElementTime
{
  public abstract boolean beginElement();
  
  public abstract boolean endElement();
  
  public abstract TimeList getBegin();
  
  public abstract float getDur();
  
  public abstract TimeList getEnd();
  
  public abstract short getFill();
  
  public abstract void pauseElement();
  
  public abstract void resumeElement();
  
  public abstract void seekElement(float paramFloat);
  
  public abstract void setDur(float paramFloat)
    throws DOMException;
}

/* Location:
 * Qualified Name:     org.w3c.dom.smil.ElementTime
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */