import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class ho
  extends hq
  implements Element
{
  private String b;
  private NamedNodeMap c = new hp();
  
  protected ho(hn paramhn, String paramString)
  {
    super(paramhn);
    b = paramString;
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
    return (Attr)c.getNamedItem(paramString);
  }
  
  public Attr getAttributeNodeNS(String paramString1, String paramString2)
  {
    return null;
  }
  
  public NamedNodeMap getAttributes()
  {
    return c;
  }
  
  public NodeList getElementsByTagName(String paramString)
  {
    return new hr(this, paramString, true);
  }
  
  public NodeList getElementsByTagNameNS(String paramString1, String paramString2)
  {
    return null;
  }
  
  public String getNodeName()
  {
    return b;
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
    return b;
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
    return c.getLength() > 0;
  }
  
  public void removeAttribute(String paramString) {}
  
  public void removeAttributeNS(String paramString1, String paramString2) {}
  
  public Attr removeAttributeNode(Attr paramAttr)
  {
    return null;
  }
  
  public void setAttribute(String paramString1, String paramString2)
  {
    Attr localAttr2 = getAttributeNode(paramString1);
    Attr localAttr1 = localAttr2;
    if (localAttr2 == null) {
      localAttr1 = a.createAttribute(paramString1);
    }
    localAttr1.setNodeValue(paramString2);
    c.setNamedItem(localAttr1);
  }
  
  public void setAttributeNS(String paramString1, String paramString2, String paramString3) {}
  
  public Attr setAttributeNode(Attr paramAttr)
  {
    return null;
  }
  
  public Attr setAttributeNodeNS(Attr paramAttr)
  {
    return null;
  }
  
  public void setIdAttribute(String paramString, boolean paramBoolean)
  {
    throw new DOMException((short)9, null);
  }
  
  public void setIdAttributeNS(String paramString1, String paramString2, boolean paramBoolean)
  {
    throw new DOMException((short)9, null);
  }
  
  public void setIdAttributeNode(Attr paramAttr, boolean paramBoolean)
  {
    throw new DOMException((short)9, null);
  }
}

/* Location:
 * Qualified Name:     ho
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */