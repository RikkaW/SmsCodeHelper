import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class hr
  implements NodeList
{
  private ArrayList<Node> a;
  private ArrayList<Node> b;
  private Node c;
  private String d;
  private boolean e;
  
  public hr(ArrayList<Node> paramArrayList)
  {
    b = paramArrayList;
  }
  
  public hr(Node paramNode, String paramString, boolean paramBoolean)
  {
    c = paramNode;
    d = paramString;
    e = paramBoolean;
  }
  
  private void a(Node paramNode)
  {
    if (paramNode == c)
    {
      a = new ArrayList();
      paramNode = paramNode.getFirstChild();
      label26:
      if (paramNode == null) {
        return;
      }
      if (!e) {
        break label87;
      }
      a(paramNode);
    }
    for (;;)
    {
      paramNode = paramNode.getNextSibling();
      break label26;
      if ((d != null) && (!paramNode.getNodeName().equals(d))) {
        break;
      }
      a.add(paramNode);
      break;
      label87:
      if ((d == null) || (paramNode.getNodeName().equals(d))) {
        a.add(paramNode);
      }
    }
  }
  
  public int getLength()
  {
    if (b == null)
    {
      a(c);
      return a.size();
    }
    return b.size();
  }
  
  public Node item(int paramInt)
  {
    if (b == null)
    {
      a(c);
      try
      {
        Node localNode1 = (Node)a.get(paramInt);
        return localNode1;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException1)
      {
        return null;
      }
    }
    try
    {
      Node localNode2 = (Node)b.get(paramInt);
      return localNode2;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException2) {}
    return null;
  }
}

/* Location:
 * Qualified Name:     hr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */