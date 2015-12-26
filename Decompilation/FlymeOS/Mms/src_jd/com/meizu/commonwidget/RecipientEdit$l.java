package com.meizu.commonwidget;

import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.view.View.DragShadowBuilder;

class RecipientEdit$l
  extends View.DragShadowBuilder
{
  public RecipientEdit$l(RecipientEdit paramRecipientEdit, View paramView)
  {
    super(paramView);
  }
  
  public void onProvideShadowMetrics(Point paramPoint1, Point paramPoint2)
  {
    RecipientEdit.ItemView localItemView = (RecipientEdit.ItemView)getView();
    if (localItemView != null)
    {
      paramPoint1.set(localItemView.getWidth(), localItemView.getHeight());
      paramPoint2.set(a, y - 10);
      ga).a = a;
      ga).b = (y - 10 + 20);
      return;
    }
    Log.e("RecipientEdit", "Asked for drag thumb metrics but no view");
  }
}

/* Location:
 * Qualified Name:     com.meizu.commonwidget.RecipientEdit.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */