import java.util.ArrayList;
import org.w3c.dom.NodeList;

public abstract class hv
  extends hw
  implements atz
{
  hv(aud paramaud)
  {
    super(paramaud);
  }
  
  public NodeList a(float paramFloat)
  {
    NodeList localNodeList = j_();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < localNodeList.getLength())
    {
      paramFloat -= ((aua)localNodeList.item(i)).b();
      if (paramFloat < 0.0F)
      {
        localArrayList.add(localNodeList.item(i));
        return new hr(localArrayList);
      }
      i += 1;
    }
    return new hr(localArrayList);
  }
  
  public float b()
  {
    float f1 = super.b();
    if (f1 == 0.0F)
    {
      NodeList localNodeList = j_();
      int i = 0;
      for (;;)
      {
        float f2 = f1;
        aua localaua;
        if (i < localNodeList.getLength())
        {
          localaua = (aua)localNodeList.item(i);
          if (localaua.b() < 0.0F) {
            f2 = -1.0F;
          }
        }
        else
        {
          return f2;
        }
        f1 += localaua.b();
        i += 1;
      }
    }
    return f1;
  }
}

/* Location:
 * Qualified Name:     hv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */