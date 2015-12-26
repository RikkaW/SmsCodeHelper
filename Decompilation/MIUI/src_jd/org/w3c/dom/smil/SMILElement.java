package org.w3c.dom.smil;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

public abstract interface SMILElement
  extends Element
{
  public abstract String getId();
  
  public abstract void setId(String paramString)
    throws DOMException;
}

/* Location:
 * Qualified Name:     org.w3c.dom.smil.SMILElement
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */