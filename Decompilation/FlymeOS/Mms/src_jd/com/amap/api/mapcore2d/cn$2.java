package com.amap.api.mapcore2d;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

class cn$2
  implements View.OnClickListener
{
  cn$2(cn paramcn) {}
  
  public void onClick(View paramView)
  {
    cn.e(a).setImageBitmap(cn.f(a));
    a.a(cn.c(a).f() - 1.0F);
    if (cn.c(a).f() < (int)cn.c(a).i() + 2) {
      cn.b(a).setImageBitmap(cn.h(a));
    }
    for (;;)
    {
      cn.g(a).d();
      return;
      cn.b(a).setImageBitmap(cn.a(a));
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cn.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */