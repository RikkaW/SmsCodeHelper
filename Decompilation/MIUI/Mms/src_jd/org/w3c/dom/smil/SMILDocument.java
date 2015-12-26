package org.w3c.dom.smil;

import org.w3c.dom.Document;

public abstract interface SMILDocument
  extends Document, ElementSequentialTimeContainer
{
  public abstract SMILElement getBody();
  
  public abstract SMILElement getHead();
  
  public abstract SMILLayoutElement getLayout();
}

/* Location:
 * Qualified Name:     org.w3c.dom.smil.SMILDocument
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */