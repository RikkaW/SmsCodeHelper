package org.w3c.dom.smil;

import org.w3c.dom.NodeList;

public abstract interface ElementTimeContainer
  extends ElementTime
{
  public abstract NodeList getActiveChildrenAt(float paramFloat);
  
  public abstract NodeList getTimeChildren();
}

/* Location:
 * Qualified Name:     org.w3c.dom.smil.ElementTimeContainer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */