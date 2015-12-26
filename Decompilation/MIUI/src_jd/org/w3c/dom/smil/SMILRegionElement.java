package org.w3c.dom.smil;

import org.w3c.dom.DOMException;

public abstract interface SMILRegionElement
  extends ElementLayout, SMILElement
{
  public abstract String getFit();
  
  public abstract int getLeft();
  
  public abstract int getTop();
  
  public abstract void setFit(String paramString)
    throws DOMException;
  
  public abstract void setLeft(int paramInt)
    throws DOMException;
  
  public abstract void setTop(int paramInt)
    throws DOMException;
}

/* Location:
 * Qualified Name:     org.w3c.dom.smil.SMILRegionElement
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */