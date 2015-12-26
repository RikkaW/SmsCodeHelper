package org.w3c.dom.smil;

import org.w3c.dom.DOMException;

public abstract interface ElementLayout
{
  public abstract String getBackgroundColor();
  
  public abstract int getHeight();
  
  public abstract int getWidth();
  
  public abstract void setBackgroundColor(String paramString)
    throws DOMException;
  
  public abstract void setHeight(int paramInt)
    throws DOMException;
  
  public abstract void setWidth(int paramInt)
    throws DOMException;
}

/* Location:
 * Qualified Name:     org.w3c.dom.smil.ElementLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */