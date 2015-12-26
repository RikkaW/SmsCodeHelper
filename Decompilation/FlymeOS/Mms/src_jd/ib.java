import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ib
  extends ia
  implements aue
{
  ib(hy paramhy, String paramString)
  {
    super(paramhy, paramString);
  }
  
  public NodeList a()
  {
    return getElementsByTagName("region");
  }
  
  public aul b()
  {
    Object localObject = getChildNodes();
    int j = ((NodeList)localObject).getLength();
    aul localaul = null;
    int i = 0;
    while (i < j)
    {
      if (((NodeList)localObject).item(i).getNodeName().equals("root-layout")) {
        localaul = (aul)((NodeList)localObject).item(i);
      }
      i += 1;
    }
    localObject = localaul;
    if (localaul == null)
    {
      localObject = (aul)getOwnerDocument().createElement("root-layout");
      ((aul)localObject).d(kd.a().b().a());
      ((aul)localObject).c(kd.a().b().b());
      appendChild((Node)localObject);
    }
    return (aul)localObject;
  }
}

/* Location:
 * Qualified Name:     ib
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */