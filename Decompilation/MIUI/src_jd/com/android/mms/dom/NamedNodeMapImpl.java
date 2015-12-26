package com.android.mms.dom;

import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NamedNodeMapImpl
  implements NamedNodeMap
{
  private Vector<Node> mNodes = new Vector();
  
  public int getLength()
  {
    return mNodes.size();
  }
  
  public Node getNamedItem(String paramString)
  {
    Object localObject2 = null;
    int i = 0;
    for (;;)
    {
      Object localObject1 = localObject2;
      if (i < mNodes.size())
      {
        if (paramString.equals(((Node)mNodes.elementAt(i)).getNodeName())) {
          localObject1 = (Node)mNodes.elementAt(i);
        }
      }
      else {
        return (Node)localObject1;
      }
      i += 1;
    }
  }
  
  public Node getNamedItemNS(String paramString1, String paramString2)
  {
    return null;
  }
  
  public Node item(int paramInt)
  {
    if (paramInt < mNodes.size()) {
      return (Node)mNodes.elementAt(paramInt);
    }
    return null;
  }
  
  public Node removeNamedItem(String paramString)
    throws DOMException
  {
    paramString = getNamedItem(paramString);
    if (paramString == null) {
      throw new DOMException((short)8, "Not found");
    }
    mNodes.remove(paramString);
    return paramString;
  }
  
  public Node removeNamedItemNS(String paramString1, String paramString2)
    throws DOMException
  {
    return null;
  }
  
  public Node setNamedItem(Node paramNode)
    throws DOMException
  {
    Node localNode = getNamedItem(paramNode.getNodeName());
    if (localNode != null) {
      mNodes.remove(localNode);
    }
    mNodes.add(paramNode);
    return localNode;
  }
  
  public Node setNamedItemNS(Node paramNode)
    throws DOMException
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.NamedNodeMapImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */