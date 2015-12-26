import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class hp
  implements NamedNodeMap
{
  private Vector<Node> a = new Vector();
  
  public int getLength()
  {
    return a.size();
  }
  
  public Node getNamedItem(String paramString)
  {
    int i = 0;
    while (i < a.size())
    {
      if (paramString.equals(((Node)a.elementAt(i)).getNodeName())) {
        return (Node)a.elementAt(i);
      }
      i += 1;
    }
    return null;
  }
  
  public Node getNamedItemNS(String paramString1, String paramString2)
  {
    return null;
  }
  
  public Node item(int paramInt)
  {
    if (paramInt < a.size()) {
      return (Node)a.elementAt(paramInt);
    }
    return null;
  }
  
  public Node removeNamedItem(String paramString)
  {
    paramString = getNamedItem(paramString);
    if (paramString == null) {
      throw new DOMException((short)8, "Not found");
    }
    a.remove(paramString);
    return paramString;
  }
  
  public Node removeNamedItemNS(String paramString1, String paramString2)
  {
    return null;
  }
  
  public Node setNamedItem(Node paramNode)
  {
    Node localNode = getNamedItem(paramNode.getNodeName());
    if (localNode != null) {
      a.remove(localNode);
    }
    a.add(paramNode);
    return localNode;
  }
  
  public Node setNamedItemNS(Node paramNode)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     hp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */