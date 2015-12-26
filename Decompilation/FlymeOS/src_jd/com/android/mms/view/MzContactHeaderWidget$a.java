package com.android.mms.view;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import gm;
import gq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import nd.a;
import nd.c;

class MzContactHeaderWidget$a
  extends Handler
{
  private MzContactHeaderWidget$a(MzContactHeaderWidget paramMzContactHeaderWidget) {}
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    }
    label85:
    int n;
    int i1;
    label248:
    label282:
    label294:
    label297:
    label364:
    for (;;)
    {
      return;
      Object localObject1 = (Object[])obj;
      paramMessage = (String)localObject1[0];
      nd.c localc = (nd.c)localObject1[1];
      int i2 = ((Integer)localObject1[2]).intValue();
      localObject1 = a.b.iterator();
      int k = 0;
      i = 0;
      int j = 0;
      int m = k;
      n = i;
      i1 = j;
      Object localObject2;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (gm)((Iterator)localObject1).next();
        if (!paramMessage.equals(((gm)localObject2).d().replaceAll(" ", ""))) {
          break label297;
        }
        a.d.put(((gm)localObject2).d(), localc);
        MzContactHeaderWidget.b(a).remove(((gm)localObject2).d());
        MzContactHeaderWidget localMzContactHeaderWidget = a;
        if (i2 >= MzContactHeaderWidget.c(a)) {
          break label282;
        }
        k = i2;
        MzContactHeaderWidget.a(localMzContactHeaderWidget, k);
        if (i == 0) {
          break label294;
        }
        m = 1;
        i1 = j;
        n = i;
      }
      for (;;)
      {
        if ((m == 0) || (MzContactHeaderWidget.b(a).size() != 0)) {
          break label364;
        }
        if (i1 == 0) {
          break label371;
        }
        MzContactHeaderWidget.d(a).setVisibility(8);
        if (MzContactHeaderWidget.g(a) == null) {
          break label478;
        }
        MzContactHeaderWidget.g(a).a(a, MzContactHeaderWidget.h(a));
        return;
        k = MzContactHeaderWidget.c(a);
        break;
        k = 1;
        localObject2 = (nd.c)a.d.get(((gm)localObject2).d());
        if (localObject2 == nd.c.b) {
          j = 1;
        }
        if ((localObject2 != null) && ((localObject2 == nd.c.c) || (localObject2 == nd.c.b))) {
          break label480;
        }
        if (k == 0) {
          break label366;
        }
        n = 1;
        m = k;
        i1 = j;
      }
    }
    label366:
    int i = 1;
    label371:
    label478:
    label480:
    for (;;)
    {
      break label85;
      if ((i1 != 0) && (!a.c()))
      {
        paramMessage = a;
        if (MzContactHeaderWidget.e(a)) {}
        for (boolean bool = false;; bool = MzContactHeaderWidget.f(a))
        {
          MzContactHeaderWidget.a(paramMessage, bool);
          MzContactHeaderWidget.d(a).setVisibility(0);
          MzContactHeaderWidget.b(a, false);
          break;
        }
      }
      if (n == 0)
      {
        MzContactHeaderWidget.d(a).setVisibility(0);
        break label248;
      }
      MzContactHeaderWidget.d(a).setVisibility(8);
      break label248;
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MzContactHeaderWidget.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */