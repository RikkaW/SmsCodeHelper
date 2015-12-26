package com.android.mms.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

public class AttrImpl
  extends NodeImpl
  implements Attr
{
  private String mName;
  private String mValue;
  
  protected AttrImpl(DocumentImpl paramDocumentImpl, String paramString)
  {
    super(paramDocumentImpl);
    mName = paramString;
  }
  
  public String getName()
  {
    return mName;
  }
  
  public Node getNextSibling()
  {
    return null;
  }
  
  public String getNodeName()
  {
    return mName;
  }
  
  public short getNodeType()
  {
    return 2;
  }
  
  public Element getOwnerElement()
  {
    return null;
  }
  
  public Node getParentNode()
  {
    return null;
  }
  
  public Node getPreviousSibling()
  {
    return null;
  }
  
  public TypeInfo getSchemaTypeInfo()
  {
    return null;
  }
  
  public boolean getSpecified()
  {
    return mValue != null;
  }
  
  public String getValue()
  {
    return mValue;
  }
  
  public boolean isId()
  {
    return false;
  }
  
  public void setNodeValue(String paramString)
    throws DOMException
  {
    setValue(paramString);
  }
  
  public void setValue(String paramString)
    throws DOMException
  {
    mValue = paramString;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.AttrImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */