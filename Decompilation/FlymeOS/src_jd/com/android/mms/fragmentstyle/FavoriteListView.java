package com.android.mms.fragmentstyle;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import com.android.mms.ui.MessageListView;
import com.android.mms.ui.MessageListView.a;
import md;

public class FavoriteListView
  extends MessageListView
{
  public FavoriteListView(Context paramContext)
  {
    super(paramContext);
  }
  
  public FavoriteListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void a()
  {
    super.f();
  }
  
  public void b() {}
  
  public void c()
  {
    setEditMode(MessageListView.a.c);
  }
  
  public void d()
  {
    setEditMode(MessageListView.a.a);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.a(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.a(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean performItemClick(View paramView, int paramInt, long paramLong)
  {
    return super.a(paramView, paramInt, paramLong);
  }
  
  public void setEnableDivider(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      md localmd = new md(this);
      localmd.a(new int[] { getResources().getDimensionPixelOffset(2131558994), getResources().getDimensionPixelOffset(2131558995) });
      localmd.a();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.FavoriteListView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */