import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class hy
  extends hn
  implements ats, auc
{
  atz b;
  
  public att a(String paramString)
  {
    if ("Event".equals(paramString)) {
      return new hs();
    }
    throw new DOMException((short)9, "Not supported interface");
  }
  
  public NodeList a(float paramFloat)
  {
    return b.a(paramFloat);
  }
  
  public float b()
  {
    return b.b();
  }
  
  public void b(float paramFloat)
  {
    b.b(paramFloat);
  }
  
  public void c(float paramFloat)
  {
    b.c(paramFloat);
  }
  
  public boolean c()
  {
    return b.c();
  }
  
  public Element createElement(String paramString)
  {
    paramString = paramString.toLowerCase();
    if ((paramString.equals("text")) || (paramString.equals("img")) || (paramString.equals("video"))) {
      return new ik(this, paramString);
    }
    if ((paramString.equals("audio")) || (paramString.equals("talk"))) {
      return new ic(this, paramString);
    }
    if (paramString.equals("layout")) {
      return new ib(this, paramString);
    }
    if (paramString.equals("root-layout")) {
      return new il(this, paramString);
    }
    if (paramString.equals("region")) {
      return new ij(this, paramString);
    }
    if ((paramString.equals("ref")) || (paramString.equals("file")) || (paramString.equals("vcard"))) {
      return new ii(this, paramString);
    }
    if (paramString.equals("par")) {
      return new ie(this, paramString);
    }
    return new ia(this, paramString);
  }
  
  public boolean d()
  {
    return b.d();
  }
  
  public aun g()
  {
    return b.g();
  }
  
  public aun h()
  {
    return b.h();
  }
  
  public void h_()
  {
    b.h_();
  }
  
  public short i()
  {
    return b.i();
  }
  
  public void i_()
  {
    b.i_();
  }
  
  public aud j()
  {
    Node localNode = getFirstChild();
    Object localObject;
    if (localNode != null)
    {
      localObject = localNode;
      if ((localNode instanceof aud)) {}
    }
    else
    {
      localObject = createElement("smil");
      appendChild((Node)localObject);
    }
    return (aud)localObject;
  }
  
  public NodeList j_()
  {
    return b.j_();
  }
  
  public aud k()
  {
    aud localaud = j();
    Node localNode = localaud.getFirstChild();
    Object localObject;
    if (localNode != null)
    {
      localObject = localNode;
      if ((localNode instanceof aud)) {}
    }
    else
    {
      localObject = createElement("head");
      localaud.appendChild((Node)localObject);
    }
    return (aud)localObject;
  }
  
  public aud l()
  {
    aud localaud = j();
    Node localNode = k().getNextSibling();
    Object localObject;
    if (localNode != null)
    {
      localObject = localNode;
      if ((localNode instanceof aud)) {}
    }
    else
    {
      localObject = createElement("body");
      localaud.appendChild((Node)localObject);
    }
    b = new hz(this, (aud)localObject);
    return (aud)localObject;
  }
  
  public aue m()
  {
    aud localaud = k();
    for (Node localNode = localaud.getFirstChild(); (localNode != null) && (!(localNode instanceof aue)); localNode = localNode.getNextSibling()) {}
    Object localObject = localNode;
    if (localNode == null)
    {
      localObject = new ib(this, "layout");
      localaud.appendChild((Node)localObject);
    }
    return (aue)localObject;
  }
}

/* Location:
 * Qualified Name:     hy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */