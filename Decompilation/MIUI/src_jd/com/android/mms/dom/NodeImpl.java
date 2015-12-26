package com.android.mms.dom;

import com.android.mms.dom.events.EventTargetImpl;
import java.util.NoSuchElementException;
import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public abstract class NodeImpl
  implements Node, EventTarget
{
  private final Vector<Node> mChildNodes = new Vector();
  private final EventTarget mEventTarget = new EventTargetImpl(this);
  DocumentImpl mOwnerDocument;
  private Node mParentNode;
  
  protected NodeImpl(DocumentImpl paramDocumentImpl)
  {
    mOwnerDocument = paramDocumentImpl;
  }
  
  private void setParentNode(Node paramNode)
  {
    mParentNode = paramNode;
  }
  
  public void addEventListener(String paramString, EventListener paramEventListener, boolean paramBoolean)
  {
    mEventTarget.addEventListener(paramString, paramEventListener, paramBoolean);
  }
  
  public Node appendChild(Node paramNode)
    throws DOMException
  {
    ((NodeImpl)paramNode).setParentNode(this);
    mChildNodes.remove(paramNode);
    mChildNodes.add(paramNode);
    return paramNode;
  }
  
  public Node cloneNode(boolean paramBoolean)
  {
    return null;
  }
  
  public short compareDocumentPosition(Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)9, null);
  }
  
  public boolean dispatchEvent(Event paramEvent)
    throws EventException
  {
    return mEventTarget.dispatchEvent(paramEvent);
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
    return new NodeListImpl(this, null, false);
  }
  
  public Object getFeature(String paramString1, String paramString2)
  {
    return null;
  }
  
  public Node getFirstChild()
  {
    try
    {
      Node localNode = (Node)mChildNodes.firstElement();
      return localNode;
    }
    catch (NoSuchElementException localNoSuchElementException) {}
    return null;
  }
  
  public Node getLastChild()
  {
    try
    {
      Node localNode = (Node)mChildNodes.lastElement();
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
    if ((mParentNode != null) && (this != mParentNode.getLastChild()))
    {
      Vector localVector = mParentNode).mChildNodes;
      return (Node)localVector.elementAt(localVector.indexOf(this) + 1);
    }
    return null;
  }
  
  public String getNodeValue()
    throws DOMException
  {
    return null;
  }
  
  public Document getOwnerDocument()
  {
    return mOwnerDocument;
  }
  
  public Node getParentNode()
  {
    return mParentNode;
  }
  
  public String getPrefix()
  {
    return null;
  }
  
  public Node getPreviousSibling()
  {
    if ((mParentNode != null) && (this != mParentNode.getFirstChild()))
    {
      Vector localVector = mParentNode).mChildNodes;
      return (Node)localVector.elementAt(localVector.indexOf(this) - 1);
    }
    return null;
  }
  
  public String getTextContent()
    throws DOMException
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
    return !mChildNodes.isEmpty();
  }
  
  public Node insertBefore(Node paramNode1, Node paramNode2)
    throws DOMException
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
    throws DOMException
  {
    if (mChildNodes.contains(paramNode))
    {
      mChildNodes.remove(paramNode);
      ((NodeImpl)paramNode).setParentNode(null);
      return null;
    }
    throw new DOMException((short)8, "Child does not exist");
  }
  
  public void removeEventListener(String paramString, EventListener paramEventListener, boolean paramBoolean)
  {
    mEventTarget.removeEventListener(paramString, paramEventListener, paramBoolean);
  }
  
  public Node replaceChild(Node paramNode1, Node paramNode2)
    throws DOMException
  {
    if (mChildNodes.contains(paramNode2)) {}
    try
    {
      mChildNodes.remove(paramNode1);
      mChildNodes.setElementAt(paramNode1, mChildNodes.indexOf(paramNode2));
      ((NodeImpl)paramNode1).setParentNode(this);
      ((NodeImpl)paramNode2).setParentNode(null);
      return paramNode2;
      throw new DOMException((short)8, "Old child does not exist");
    }
    catch (DOMException localDOMException)
    {
      for (;;) {}
    }
  }
  
  public void setNodeValue(String paramString)
    throws DOMException
  {}
  
  public void setPrefix(String paramString)
    throws DOMException
  {}
  
  public void setTextContent(String paramString)
    throws DOMException
  {
    throw new DOMException((short)9, null);
  }
  
  public Object setUserData(String paramString, Object paramObject, UserDataHandler paramUserDataHandler)
  {
    throw new DOMException((short)9, null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.NodeImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */