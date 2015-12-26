import org.w3c.dom.NodeList;

public class ik
  extends ic
  implements auk
{
  private aui c;
  
  ik(hy paramhy, String paramString)
  {
    super(paramhy, paramString);
  }
  
  public void a(aui paramaui)
  {
    setAttribute("region", paramaui.j());
    c = paramaui;
  }
  
  public aui l()
  {
    if (c == null)
    {
      NodeList localNodeList = ((auc)getOwnerDocument()).m().getElementsByTagName("region");
      int i = 0;
      while (i < localNodeList.getLength())
      {
        aui localaui = (aui)localNodeList.item(i);
        if (localaui.j().equals(getAttribute("region"))) {
          c = localaui;
        }
        i += 1;
      }
    }
    return c;
  }
}

/* Location:
 * Qualified Name:     ik
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */