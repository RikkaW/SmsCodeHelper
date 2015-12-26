package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Point;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import com.amap.api.maps2d.model.LatLng;

class bg
  extends ViewGroup
{
  private ag a;
  
  public bg(Context paramContext, ag paramag)
  {
    super(paramContext);
    a = paramag;
  }
  
  private void a(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int j = paramInt5 & 0x7;
    int i = paramInt5 & 0x70;
    if (j == 5)
    {
      paramInt5 = paramInt3 - paramInt1;
      if (i != 80) {
        break label77;
      }
      paramInt3 = paramInt4 - paramInt2;
    }
    for (;;)
    {
      paramView.layout(paramInt5, paramInt3, paramInt5 + paramInt1, paramInt3 + paramInt2);
      return;
      paramInt5 = paramInt3;
      if (j != 1) {
        break;
      }
      paramInt5 = paramInt3 - paramInt1 / 2;
      break;
      label77:
      paramInt3 = paramInt4;
      if (i == 16) {
        paramInt3 = paramInt4 - paramInt2 / 2;
      }
    }
  }
  
  private void a(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    if ((paramView instanceof ListView))
    {
      View localView = (View)paramView.getParent();
      if (localView != null)
      {
        paramArrayOfInt[0] = localView.getWidth();
        paramArrayOfInt[1] = localView.getHeight();
      }
    }
    if ((paramInt1 <= 0) || (paramInt2 <= 0)) {
      paramView.measure(0, 0);
    }
    if (paramInt1 == -2) {
      paramArrayOfInt[0] = paramView.getMeasuredWidth();
    }
    while (paramInt2 == -2)
    {
      paramArrayOfInt[1] = paramView.getMeasuredHeight();
      return;
      if (paramInt1 == -1) {
        paramArrayOfInt[0] = getMeasuredWidth();
      } else {
        paramArrayOfInt[0] = paramInt1;
      }
    }
    if (paramInt2 == -1)
    {
      paramArrayOfInt[1] = getMeasuredHeight();
      return;
    }
    paramArrayOfInt[1] = paramInt2;
  }
  
  private void a(View paramView, a parama)
  {
    int[] arrayOfInt = new int[2];
    a(paramView, width, height, arrayOfInt);
    a(paramView, arrayOfInt[0], arrayOfInt[1], c, d, e);
  }
  
  private void a(cn paramcn, int[] paramArrayOfInt, int paramInt)
  {
    int i = paramcn.b();
    if (i == 1) {
      a(paramcn, paramArrayOfInt[0], paramArrayOfInt[1], getWidth() - paramArrayOfInt[0], (getHeight() + paramArrayOfInt[1]) / 2, paramInt);
    }
    while (i != 0) {
      return;
    }
    a(paramcn, paramArrayOfInt[0], paramArrayOfInt[1], getWidth() - paramArrayOfInt[0], getHeight(), paramInt);
  }
  
  private void b(View paramView, a parama)
  {
    int[] arrayOfInt = new int[2];
    a(paramView, width, height, arrayOfInt);
    if ((paramView instanceof cn)) {
      a((cn)paramView, arrayOfInt, e);
    }
    for (;;)
    {
      return;
      if ((paramView instanceof ay))
      {
        a(paramView, arrayOfInt[0], arrayOfInt[1], getWidth() - arrayOfInt[0], arrayOfInt[1], e);
        return;
      }
      if ((paramView instanceof x))
      {
        a(paramView, arrayOfInt[0], arrayOfInt[1], 0, 0, e);
        return;
      }
      if (b == null) {
        continue;
      }
      Object localObject1 = new ae((int)(b.latitude * 1000000.0D), (int)(b.longitude * 1000000.0D));
      try
      {
        localObject1 = a.s().a((ae)localObject1, null);
        if (localObject1 == null) {
          continue;
        }
        x += c;
        y += d;
        a(paramView, arrayOfInt[0], arrayOfInt[1], x, y, e);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          cy.a(localRemoteException, "MapOverlayViewGroup", "layoutMap");
          Object localObject2 = null;
        }
      }
    }
  }
  
  public void a()
  {
    onLayout(false, 0, 0, 0, 0);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = getChildCount();
    paramInt1 = 0;
    if (paramInt1 < paramInt2)
    {
      View localView = getChildAt(paramInt1);
      if (localView == null) {}
      for (;;)
      {
        paramInt1 += 1;
        break;
        if ((localView.getLayoutParams() instanceof a))
        {
          a locala = (a)localView.getLayoutParams();
          if (a == 0) {
            b(localView, locala);
          } else {
            a(localView, locala);
          }
        }
        else
        {
          a(localView, new a(localView.getLayoutParams()));
        }
      }
    }
  }
  
  public static class a
    extends ViewGroup.LayoutParams
  {
    public int a = 1;
    public LatLng b = null;
    public int c = 0;
    public int d = 0;
    public int e = 51;
    
    public a(int paramInt1, int paramInt2, LatLng paramLatLng, int paramInt3, int paramInt4, int paramInt5)
    {
      super(paramInt2);
      a = 0;
      b = paramLatLng;
      c = paramInt3;
      d = paramInt4;
      e = paramInt5;
    }
    
    public a(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public a(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */