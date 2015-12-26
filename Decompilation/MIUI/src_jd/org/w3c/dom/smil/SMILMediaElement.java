package org.w3c.dom.smil;

import org.w3c.dom.DOMException;

public abstract interface SMILMediaElement
  extends ElementTime, SMILElement
{
  public abstract String getSrc();
  
  public abstract void setSrc(String paramString)
    throws DOMException;
}

/* Location:
 * Qualified Name:     org.w3c.dom.smil.SMILMediaElement
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */