package com.android.mms.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import nd.c;

public class NewConsationTitleFrm
  extends FrameLayout
{
  private volatile boolean a = true;
  private nd.c b = nd.c.a;
  private Context c;
  
  public NewConsationTitleFrm(Context paramContext)
  {
    super(paramContext);
    c = paramContext;
  }
  
  public NewConsationTitleFrm(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    c = paramContext;
  }
  
  public NewConsationTitleFrm(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    c = paramContext;
  }
  
  public boolean a(nd.c paramc)
  {
    boolean bool2 = true;
    if (!Log.isLoggable("Mms:app", 2))
    {
      b = paramc;
      if (b != nd.c.c) {
        break label129;
      }
    }
    label76:
    label124:
    label129:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      a = bool1;
      return a;
      StringBuilder localStringBuilder = new StringBuilder().append("onSipStateChanged(), mFlymeOn = ").append(a).append(", sipYes = ");
      if (b == nd.c.c)
      {
        bool1 = true;
        localStringBuilder = localStringBuilder.append(bool1).append(", sipOffline = ");
        if (b != nd.c.b) {
          break label124;
        }
      }
      for (bool1 = true;; bool1 = false)
      {
        Log.d("NewConsationTitleFrm", bool1);
        break;
        bool1 = false;
        break label76;
      }
    }
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    inflate(getContext(), 2130968613, this);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.NewConsationTitleFrm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */