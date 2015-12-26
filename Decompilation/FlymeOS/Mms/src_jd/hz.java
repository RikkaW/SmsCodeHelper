import org.w3c.dom.NodeList;

class hz
  extends hv
{
  hz(hy paramhy, aud paramaud)
  {
    super(paramaud);
  }
  
  public void c(float paramFloat) {}
  
  public boolean c()
  {
    att localatt = b.a("Event");
    localatt.a("SmilDocumentStart", false, false);
    b.a(localatt);
    return true;
  }
  
  public boolean d()
  {
    att localatt = b.a("Event");
    localatt.a("SimlDocumentEnd", false, false);
    b.a(localatt);
    return true;
  }
  
  aua f()
  {
    return null;
  }
  
  public void h_() {}
  
  public void i_() {}
  
  public NodeList j_()
  {
    return b.l().getElementsByTagName("par");
  }
}

/* Location:
 * Qualified Name:     hz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */