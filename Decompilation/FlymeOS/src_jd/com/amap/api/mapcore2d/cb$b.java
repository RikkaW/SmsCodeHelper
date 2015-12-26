package com.amap.api.mapcore2d;

import java.util.List;

class cb$b
  extends cq<ag, Void, List<cb.a>>
{
  private int c;
  private boolean d;
  
  public cb$b(cb paramcb, boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  protected List<cb.a> a(ag... paramVarArgs)
  {
    int j = 0;
    int i;
    try
    {
      int k = paramVarArgs[0].c();
      i = paramVarArgs[0].d();
      c = ((int)paramVarArgs[0].f());
      j = k;
    }
    catch (Exception paramVarArgs)
    {
      for (;;)
      {
        cy.a(paramVarArgs, "TileOverDelegateImp", "doInBackground");
        i = 0;
      }
    }
    if ((j <= 0) || (i <= 0)) {
      return null;
    }
    return cb.a(a, c, j, i);
  }
  
  protected void a(List<cb.a> paramList)
  {
    if (paramList == null) {}
    while (paramList.size() <= 0) {
      return;
    }
    cb.a(a, paramList, c, d);
    paramList.clear();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cb.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */