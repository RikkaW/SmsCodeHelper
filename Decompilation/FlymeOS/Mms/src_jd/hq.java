import java.util.NoSuchElementException;
import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public abstract class hq
  implements atw, Node
{
  hn a;
  private Node b;
  private final Vector<Node> c = new Vector();
  private final atw d = new ht(this);
  
  protected hq(hn paramhn)
  {
    a = paramhn;
  }
  
  private void a(Node paramNode)
  {
    b = paramNode;
  }
  
  public void a(String paramString, atv paramatv, boolean paramBoolean)
  {
    d.a(paramString, paramatv, paramBoolean);
  }
  
  public boolean a(att paramatt)
  {
    return d.a(paramatt);
  }
  
  public Node appendChild(Node paramNode)
  {
    ((hq)paramNode).a(this);
    c.remove(paramNode);
    c.add(paramNode);
    return paramNode;
  }
  
  public void b(String paramString, atv paramatv, boolean paramBoolean)
  {
    d.b(paramString, paramatv, paramBoolean);
  }
  
  public Node cloneNode(boolean paramBoolean)
  {
    return null;
  }
  
  public short compareDocumentPosition(Node paramNode)
  {
    throw new DOMException((short)9, null);
  }
  
  public NamedNodeMap getAttributes()
  {
    return null;
  }
  
  public String getBaseURI()
  {
    return null;
  }
  
  public NodeList getChildNodes()
  {
    return new hr(this, null, false);
  }
  
  public Object getFeature(String paramString1, String paramString2)
  {
    return null;
  }
  
  public Node getFirstChild()
  {
    try
    {
      Node localNode = (Node)c.firstElement();
      return localNode;
    }
    catch (NoSuchElementException localNoSuchElementException) {}
    return null;
  }
  
  public Node getLastChild()
  {
    try
    {
      Node localNode = (Node)c.lastElement();
      return localNode;
    }
    catch (NoSuchElementException localNoSuchElementException) {}
    return null;
  }
  
  public String getLocalName()
  {
    return null;
  }
  
  public String getNamespaceURI()
  {
    return null;
  }
  
  public Node getNextSibling()
  {
    if ((b != null) && (this != b.getLastChild()))
    {
      Vector localVector = b).c;
      return (Node)localVector.elementAt(localVector.indexOf(this) + 1);
    }
    return null;
  }
  
  public String getNodeValue()
  {
    return null;
  }
  
  public Document getOwnerDocument()
  {
    return a;
  }
  
  public Node getParentNode()
  {
    return b;
  }
  
  public String getPrefix()
  {
    return null;
  }
  
  public Node getPreviousSibling()
  {
    if ((b != null) && (this != b.getFirstChild()))
    {
      Vector localVector = b).c;
      return (Node)localVector.elementAt(localVector.indexOf(this) - 1);
    }
    return null;
  }
  
  public String getTextContent()
  {
    throw new DOMException((short)9, null);
  }
  
  public Object getUserData(String paramString)
  {
    return null;
  }
  
  public boolean hasAttributes()
  {
    return false;
  }
  
  public boolean hasChildNodes()
  {
    return !c.isEmpty();
  }
  
  public Node insertBefore(Node paramNode1, Node paramNode2)
  {
    return null;
  }
  
  public boolean isDefaultNamespace(String paramString)
  {
    throw new DOMException((short)9, null);
  }
  
  public boolean isEqualNode(Node paramNode)
  {
    throw new DOMException((short)9, null);
  }
  
  public boolean isSameNode(Node paramNode)
  {
    throw new DOMException((short)9, null);
  }
  
  public boolean isSupported(String paramString1, String paramString2)
  {
    return false;
  }
  
  public String lookupNamespaceURI(String paramString)
  {
    return null;
  }
  
  public String lookupPrefix(String paramString)
  {
    return null;
  }
  
  public void normalize() {}
  
  public Node removeChild(Node paramNode)
  {
    if (c.contains(paramNode))
    {
      c.remove(paramNode);
      ((hq)paramNode).a(null);
      return null;
    }
    throw new DOMException((short)8, "Child does not exist");
  }
  
  public Node replaceChild(Node paramNode1, Node paramNode2)
  {
    if (c.contains(paramNode2)) {}
    try
    {
      c.remove(paramNode1);
      c.setElementAt(paramNode1, c.indexOf(paramNode2));
      ((hq)paramNode1).a(this);
      ((hq)paramNode2).a(null);
      return paramNode2;
      throw new DOMException((short)8, "Old child does not exist");
    }
    catch (DOMException localDOMException)
    {
      for (;;) {}
    }
  }
  
  public void setNodeValue(String paramString) {}
  
  public void setPrefix(String paramString) {}
  
  public void setTextContent(String paramString)
  {
    throw new DOMException((short)9, null);
  }
  
  public Object setUserData(String paramString, Object paramObject, UserDataHandler paramUserDataHandler)
  {
    throw new DOMException((short)9, null);
  }
}

/* Location:
 * Qualified Name:     hq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */