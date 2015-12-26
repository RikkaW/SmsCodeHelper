package org.w3c.dom.smil;

import org.w3c.dom.NodeList;

public abstract interface SMILLayoutElement
  extends SMILElement
{
  public abstract NodeList getRegions();
  
  public abstract SMILRootLayoutElement getRootLayout();
}

/* Location:
 * Qualified Name:     org.w3c.dom.smil.SMILLayoutElement
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */