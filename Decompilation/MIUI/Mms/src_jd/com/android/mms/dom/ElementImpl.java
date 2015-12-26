package com.android.mms.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class ElementImpl
  extends NodeImpl
  implements Element
{
  private NamedNodeMap mAttributes = new NamedNodeMapImpl();
  private String mTagName;
  
  protected ElementImpl(DocumentImpl paramDocumentImpl, String paramString)
  {
    super(paramDocumentImpl);
    mTagName = paramString;
  }
  
  public String getAttribute(String paramString)
  {
    Attr localAttr = getAttributeNode(paramString);
    paramString = "";
    if (localAttr != null) {
      paramString = localAttr.getValue();
    }
    return paramString;
  }
  
  public String getAttributeNS(String paramString1, String paramString2)
  {
    return null;
  }
  
  public Attr getAttributeNode(String paramString)
  {
    return (Attr)mAttributes.getNamedItem(paramString);
  }
  
  public Attr getAttributeNodeNS(String paramString1, String paramString2)
  {
    return null;
  }
  
  public NamedNodeMap getAttributes()
  {
    return mAttributes;
  }
  
  public NodeList getElementsByTagName(String paramString)
  {
    return new NodeListImpl(this, paramString, true);
  }
  
  public NodeList getElementsByTagNameNS(String paramString1, String paramString2)
  {
    return null;
  }
  
  public String getNodeName()
  {
    return mTagName;
  }
  
  public short getNodeType()
  {
    return 1;
  }
  
  public TypeInfo getSchemaTypeInfo()
  {
    return null;
  }
  
  public String getTagName()
  {
    return mTagName;
  }
  
  public boolean hasAttribute(String paramString)
  {
    return getAttributeNode(paramString) != null;
  }
  
  public boolean hasAttributeNS(String paramString1, String paramString2)
  {
    return false;
  }
  
  public boolean hasAttributes()
  {
    return mAttributes.getLength() > 0;
  }
  
  public void removeAttribute(String paramString)
    throws DOMException
  {}
  
  public void removeAttributeNS(String paramString1, String paramString2)
    throws DOMException
  {}
  
  public Attr removeAttributeNode(Attr paramAttr)
    throws DOMException
  {
    return null;
  }
  
  public void setAttribute(String paramString1, String paramString2)
    throws DOMException
  {
    Attr localAttr2 = getAttributeNode(paramString1);
    Attr localAttr1 = localAttr2;
    if (localAttr2 == null) {
      localAttr1 = mOwnerDocument.createAttribute(paramString1);
    }
    localAttr1.setNodeValue(paramString2);
    mAttributes.setNamedItem(localAttr1);
  }
  
  public void setAttributeNS(String paramString1, String paramString2, String paramString3)
    throws DOMException
  {}
  
  public Attr setAttributeNode(Attr paramAttr)
    throws DOMException
  {
    return null;
  }
  
  public Attr setAttributeNodeNS(Attr paramAttr)
    throws DOMException
  {
    return null;
  }
  
  public void setIdAttribute(String paramString, boolean paramBoolean)
    throws DOMException
  {
    throw new DOMException((short)9, null);
  }
  
  public void setIdAttributeNS(String paramString1, String paramString2, boolean paramBoolean)
    throws DOMException
  {
    throw new DOMException((short)9, null);
  }
  
  public void setIdAttributeNode(Attr paramAttr, boolean paramBoolean)
    throws DOMException
  {
    throw new DOMException((short)9, null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.ElementImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */