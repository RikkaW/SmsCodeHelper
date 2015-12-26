class aey$q
  implements aey.f
{
  private StringBuilder b;
  private boolean c;
  
  private aey$q(aey paramaey) {}
  
  public void a()
  {
    b = new StringBuilder();
    b.append("[[hash: " + a.hashCode() + "\n");
  }
  
  public void a(aey.g paramg)
  {
    b.append(paramg.toString() + ": ");
    c = true;
  }
  
  public boolean a(aey.e parame)
  {
    if (!c)
    {
      b.append(", ");
      c = false;
    }
    b.append("[").append(parame.toString()).append("]");
    return true;
  }
  
  public void b()
  {
    b.append("]]\n");
  }
  
  public void c()
  {
    b.append("\n");
  }
  
  public String toString()
  {
    return b.toString();
  }
}

/* Location:
 * Qualified Name:     aey.q
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */