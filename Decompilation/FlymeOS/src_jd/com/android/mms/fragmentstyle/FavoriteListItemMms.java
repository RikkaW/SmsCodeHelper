package com.android.mms.fragmentstyle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.android.mms.ui.MessageListView;
import com.android.mms.view.MessageListItem;
import com.android.mms.view.MessageRoundCornerImageView;
import com.android.mms.view.MmsFoldableTextView;
import com.meizu.common.widget.AnimCheckBox;
import com.meizu.common.widget.AnimCheckBox.UpdateListener;
import gm;
import vv;

public class FavoriteListItemMms
  extends MessageListItem
  implements AnimCheckBox.UpdateListener
{
  private static int aA;
  private static int aB;
  private static int aC;
  private static int aD;
  private static int al = 0;
  private static int am = 0;
  private static int an = 0;
  private static int ao = 0;
  private static int at = 0;
  private static int au = 0;
  private static int av;
  private static int aw;
  private static int ax;
  private static int ay;
  private static int az;
  private boolean aE = false;
  private FavoriteListItemCommon ak;
  private AnimCheckBox ap;
  private View aq;
  private int ar;
  private int as;
  
  public FavoriteListItemMms(Context paramContext)
  {
    super(paramContext);
  }
  
  public FavoriteListItemMms(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void e(vv paramvv)
  {
    Bundle localBundle = new Bundle();
    localBundle.putLong("thread_id", f);
    localBundle.putLong("select_id", e);
    localBundle.putString("msg_type", d);
    localBundle.putString("group_msg_id", O);
    setTag(localBundle);
  }
  
  protected void a() {}
  
  protected void a(View paramView) {}
  
  protected void a(vv paramvv) {}
  
  public void a(vv paramvv, boolean paramBoolean1, int paramInt, MessageListView paramMessageListView, boolean paramBoolean2)
  {
    int i = 0;
    super.a(paramvv, paramBoolean1, paramInt, paramMessageListView, paramBoolean2);
    ak.a(paramvv);
    E.setTextSize(0, getResources().getDimensionPixelSize(2131558996));
    F.setTextSize(0, getResources().getDimensionPixelSize(2131558997));
    e(paramvv);
    if (b(l))
    {
      paramvv = (ViewGroup.MarginLayoutParams)l.getLayoutParams();
      topMargin = az;
      l.setLayoutParams(paramvv);
    }
    paramvv = w;
    int j = w.getPaddingLeft();
    int k = w.getPaddingTop();
    int m = w.getPaddingRight();
    if (((b(y)) || (b(z))) && (!b(B)))
    {
      paramInt = -aA;
      paramvv.setPadding(j, k, m, paramInt);
      if (b(z))
      {
        paramvv = (ViewGroup.MarginLayoutParams)z.getLayoutParams();
        if (!b(y)) {
          break label294;
        }
      }
    }
    label294:
    for (paramInt = aC;; paramInt = aB)
    {
      topMargin = paramInt;
      z.setLayoutParams(paramvv);
      paramvv = (ViewGroup)B.getParent();
      paramMessageListView = (ViewGroup.MarginLayoutParams)paramvv.getLayoutParams();
      paramInt = i;
      if (b(D)) {
        paramInt = aD;
      }
      topMargin = paramInt;
      paramvv.setLayoutParams(paramMessageListView);
      gm.a(ak);
      return;
      paramInt = 0;
      break;
    }
  }
  
  protected void b() {}
  
  public void c()
  {
    if (((B.getVisibility() == 0) || (V.getVisibility() == 0)) && (ae))
    {
      A.setBackgroundResource(2130837570);
      return;
    }
    A.setBackground(null);
  }
  
  protected void d() {}
  
  public void e()
  {
    super.e();
    gm.b(ak);
  }
  
  public View getDragView()
  {
    d();
    s.setDragView(this);
    return getChildAt(0);
  }
  
  public void getHitRect(Rect paramRect)
  {
    super.a(paramRect);
  }
  
  public void getUpdateTransition(float paramFloat)
  {
    boolean bool = ap.isChecked();
    if ((bool) && (!aE))
    {
      aE = true;
      i = getResources().getDimensionPixelOffset(2131559021);
      if (z != null) {
        z.setMaxWidth(i);
      }
      if (y != null) {
        y.setMaxWidth(i);
      }
      localLayoutParams = (FrameLayout.LayoutParams)aq.getLayoutParams();
      rightMargin = (ar + ap.getMeasuredWidth() + as);
      aq.setLayoutParams(localLayoutParams);
    }
    while ((bool) || (!aE))
    {
      int i;
      return;
    }
    aE = false;
    if (z != null) {
      z.setMaxWidth(Integer.MAX_VALUE);
    }
    if (y != null) {
      y.setMaxWidth(Integer.MAX_VALUE);
    }
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)aq.getLayoutParams();
    rightMargin = ar;
    aq.setLayoutParams(localLayoutParams);
  }
  
  public boolean needBackground()
  {
    return true;
  }
  
  public void onClick(View paramView)
  {
    super.onClick(paramView);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    ak = ((FavoriteListItemCommon)findViewById(2131886453));
    aq = findViewById(2131886452);
    ar = getResources().getDimensionPixelOffset(2131559008);
    ap = ((AnimCheckBox)findViewById(16908289));
    ap.setUpdateListner(this);
    as = getResources().getDimensionPixelSize(2131558830);
    aD = getResources().getDimensionPixelSize(2131558978);
    if (av == 0)
    {
      at = getResources().getDimensionPixelSize(2131559013);
      au = getResources().getDimensionPixelSize(2131559012);
      av = getResources().getDimensionPixelSize(2131559000);
      aw = getResources().getDimensionPixelSize(2131558999);
      ax = getResources().getDimensionPixelSize(2131559000);
      ay = getResources().getDimensionPixelSize(2131558999);
    }
    if (al == 0)
    {
      al = getResources().getDimensionPixelSize(2131558991);
      am = getResources().getDimensionPixelSize(2131558992);
      an = getResources().getDimensionPixelSize(2131558993);
      ao = getResources().getDimensionPixelSize(2131558990);
    }
    az = getResources().getDimensionPixelOffset(2131559004);
    aA = getResources().getDimensionPixelOffset(2131558986);
    aB = getResources().getDimensionPixelOffset(2131559002);
    aC = getResources().getDimensionPixelOffset(2131559001);
    m = false;
    n = true;
  }
  
  protected void setImageMargin(boolean paramBoolean)
  {
    boolean bool = true;
    Object localObject;
    if (paramBoolean)
    {
      localObject = A.getLayoutParams();
      height = aw;
      A.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = B.getLayoutParams();
      width = av;
      height = aw;
      B.setLayoutParams((ViewGroup.LayoutParams)localObject);
      if (paramBoolean) {
        break label153;
      }
      paramBoolean = true;
      label65:
      ae = paramBoolean;
      localObject = B;
      if (ae) {
        break label158;
      }
    }
    label153:
    label158:
    for (paramBoolean = bool;; paramBoolean = false)
    {
      ((MessageRoundCornerImageView)localObject).setIsNeedRedraw(paramBoolean);
      return;
      localObject = A.getLayoutParams();
      height = ay;
      width = ax;
      A.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = B.getLayoutParams();
      width = at;
      height = au;
      B.setLayoutParams((ViewGroup.LayoutParams)localObject);
      break;
      paramBoolean = false;
      break label65;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.FavoriteListItemMms
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */